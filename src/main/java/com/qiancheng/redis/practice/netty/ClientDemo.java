package com.qiancheng.redis.practice.netty;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class ClientDemo {
    public static String _pattern = "yyyy-MM-dd HH:mm:ss SSS";
    public static SimpleDateFormat format = new SimpleDateFormat(_pattern);

    public static void main(String[] args) {
        try {
            while (true) {
                // 与服务端建立连接
                Socket socket = new Socket("127.0.0.1", 9999);
                // 获得输出流,给服务端发送信息
                OutputStream dout = socket.getOutputStream();
                String str = "数据传输------";
                dout.write(str.getBytes());
                // 通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
                socket.shutdownOutput();

                // 接收服务端发送的消息
                InputStream din = socket.getInputStream();
                byte[] outPut = new byte[4096];
                while (din.read(outPut) > 0) {
                    String result = new String(outPut);
                    System.out.println("服务端反回的的消息是：" + result);
                }
                din.close();
                dout.close();
                socket.close();
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
