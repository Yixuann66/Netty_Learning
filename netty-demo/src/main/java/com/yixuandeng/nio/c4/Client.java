package com.yixuandeng.nio.c4;/**
 * @Author 85067
 * @create 26/04/2023 12:15
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 26/04/2023 12:15
 */


public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));
        //socketChannel.write(Charset.defaultCharset().encode("hello!"));
        System.out.println("waiting...");
    }

}
