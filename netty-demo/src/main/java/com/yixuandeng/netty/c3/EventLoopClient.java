package com.yixuandeng.netty.c3;/**
 * @Author 85067
 * @create 24/05/2023 17:25
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author 85067
 * @version 1.0
 * @description: 客户端
 * @date 24/05/2023 17:25
 */
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                // 2. 添加EventLoop
                .group(new NioEventLoopGroup())
                // 3. 选择客户端channel事件
                .channel(NioSocketChannel.class)
                // 4. 添加处理器，连接建立之后被调用
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    // 连接建立之后被调用
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 5. 连接到服务器 异步非阻塞 不关心结果
                .connect(new InetSocketAddress("localhost", 8080))
                .sync() // 标识同步执行
                .channel();
        System.out.println(channel);
        // 6. 向服务器发送数据
//                .writeAndFlush("hello, this is my world");
        System.out.println("");
    }
}
