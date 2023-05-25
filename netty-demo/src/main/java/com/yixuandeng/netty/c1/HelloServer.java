package com.yixuandeng.netty.c1;/**
 * @Author 85067
 * @create 24/05/2023 16:55
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 24/05/2023 16:55
 */
public class HelloServer {
    public static void main(String[] args) {

        // 1. 启动器 负责组装netty组件，启动服务器
        new ServerBootstrap()
                // 2. selector监测io事件，thread 一个线程一个selector（这两个组成一个eventLoop）
                .group(new NioEventLoopGroup())
                // 3. 服务器的serverSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4. worker（child） 负责处理读写操作
                .childHandler(
                        // 5.
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8888);
    }
}
