package com.qiancheng.redis.practice.nettyStudyAgain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class SelectorThread implements Runnable {

    Selector selector = null;

    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();
    SelectorThreadGroup stg;

    SelectorThread(SelectorThreadGroup stg) {
        try {
            this.stg = stg;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //1.select()
                System.out.println(Thread.currentThread().getName() + "|before select...." + selector.keys().size());
                int nums = selector.select();
//                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "|after select ..." + selector.keys().size());


                //2.处理selectKeys
                if (nums > 0) {
                    System.out.println(Thread.currentThread().getName() + "|selector.select():" + nums + "|selector.keys.size():" + selector.keys().size());
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isAcceptable()) {
                            //复杂，接受客户端的过程(接收之后)
                            System.out.println("accept");
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            System.out.println("read");
                            readHandler(key);
                        } else if (key.isWritable()) {

                        }
                    }

                }
                //3.处理一些task
                if (!lbq.isEmpty()) {
                    //队列-堆里的对象，线程的栈是独立的，堆是共享的。只有方法的逻辑
                    Channel c = lbq.take();
                    if (c instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) c;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName() + "|lbq server.register...|" + server.getLocalAddress());
                    } else if (c instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) c;
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName() + "|" + client.getRemoteAddress());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);

            //choose a selector and register!!
            stg.nextSelector(client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        while (true) {
            try {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);

                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else if (num < 0) {
                    //客户端断开了
                    System.out.println("client:" + client.getRemoteAddress());
                    key.cancel();
                    break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
