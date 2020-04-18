package com.qiancheng.redis.practice.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocket {

    public static void main(String[] args) {
        System.out.println("hi");
        try {
            ServerSocket server = new ServerSocket(8989);
            System.out.println("server.....");
            while (true) {
                Socket client = server.accept();
                System.out.println("port: " + client.getPort());
                new Thread(() -> {
                    try {
                        InputStream in = client.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        while (true) {
                            System.out.println(reader.readLine());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
