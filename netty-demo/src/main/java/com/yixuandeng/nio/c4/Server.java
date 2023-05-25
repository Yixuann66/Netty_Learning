package com.yixuandeng.nio.c4;/**
 * @Author 85067
 * @create 25/04/2023 10:58
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 85067
 * @version 1.0
 * @description: nio学习
 * @date 25/04/2023 10:58
 */


public class Server {

    public static void main(String[] args) throws IOException {
        // 使用nio来理解阻塞模式 单线程
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //2 监听端口
        // 非阻塞模式
        // ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));
        //3. 连接的集合
        List<SocketChannel> channels = new ArrayList<>();
        //4. 建立和客户端的连接 accept
        while (true){
            // 可以多次调用
            // channnel 是数据读写的通道 socketChannel用来与客户端之间的通信
            System.out.println("connecting...");
            SocketChannel sc = ssc.accept();//阻塞等待
            System.out.println("connected...");
            channels.add(sc);
            // 5. 接受客户端发送的数据
            for (SocketChannel channel : channels) {
                System.out.println("before read...{}"+sc);
                channel.read(buffer);//阻塞方法，线程停止运行
                buffer.flip();
                System.out.println(buffer);

                buffer.clear();
                System.out.println("after read...{}"+sc);
            }
        }
    }
}
