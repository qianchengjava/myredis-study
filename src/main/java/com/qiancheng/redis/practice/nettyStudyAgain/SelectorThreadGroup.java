package com.qiancheng.redis.practice.nettyStudyAgain;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectorThreadGroup {
    SelectorThread[] sts;
    ServerSocketChannel server;
    AtomicInteger xid = new AtomicInteger(0);

    SelectorThreadGroup(int num) {
        //
        sts = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            sts[i] = new SelectorThread(this);
            //这里run的!!
            new Thread(sts[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            //注册到哪个selector上呢
            nextSelector(server);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void nextSelector(Channel c) {
        //在main线程中，取到堆里的selectorThread对象
        SelectorThread st = next();

        //1.通过队列传递数据 消息
        st.lbq.add(c);

        //2.通过打断阻塞，让对应的线程去自己在打断后完成注册线程
        st.selector.wakeup();


        //重点：c有可能是server 有可能是client
//        ServerSocketChannel s = (ServerSocketChannel) c;
        //呼应上  int nums = selector.select(); //阻塞 wakeup()
//        try {
//            s.register(st.selector, SelectionKey.OP_ACCEPT);//会被阻塞的
//            st.selector.wakeup();//功能是让selector的select()方法立刻返回不阻塞
//            System.out.println("blocking here....");
//
//        } catch (ClosedChannelException e) {
//            e.printStackTrace();
//        }


    }


    //无论server是ServerSocket还是Socket都复用这个方法
    private SelectorThread next() {
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }
}
