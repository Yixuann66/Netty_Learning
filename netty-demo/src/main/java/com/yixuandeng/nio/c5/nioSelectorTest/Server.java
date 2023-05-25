package com.yixuandeng.nio.c5.nioSelectorTest;/**
 * @Author 85067
 * @create 26/04/2023 15:46
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 85067
 * @version 1.0
 * @description: 非阻塞式的Selector测试
 * @date 26/04/2023 15:46
 */


public class Server {
    public static void main(String[] args) throws IOException {
        // a. 创建selector,管理多个Channel
        Selector selector = Selector.open();




        // 使用nio来理解阻塞模式 单线程
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //2 监听端口
        // 非阻塞模式
        ssc.configureBlocking(false);

        // b. 建立selector和channel之间的联系注册
        // SelectionKey:接口 将来事件发生之后，是哪一个channel发生的事件 0 表示不关注任何事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        /**
         *  事件的四种类型： 1. accept - 有连接请求时触发
         *      2. connect - 客户端，连接建立的时候触发
         *      3. read - 可读事件
         *      4. write - 可写事件
         *
          */
        // key只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        System.out.println(sscKey);

        ssc.bind(new InetSocketAddress(8080));
        //3. 连接的集合
        List<SocketChannel> channels = new ArrayList<>();
        //4. 建立和客户端的连接 accept
        while (true){
            // c. 调用selector的方法 没有事件就阻塞，有事件发生，线程才会恢复运行
            selector.select();
            // d. 处理事件 selectionKeys ： 集合内部包含获取所有事件集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                System.out.println("key"+key);
                // 拿到发生事件的channel
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel sc = channel.accept();
                System.out.println("sc"+sc);
            }


        }
    }

}
