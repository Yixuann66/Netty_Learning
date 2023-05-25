package com.yixuandeng.netty.c3;/**
 * @Author 85067
 * @create 25/05/2023 11:12
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 25/05/2023 11:12
 */

@Slf4j
public class EventLoopServer {

    public static void main(String[] args) {

        DefaultEventLoopGroup loopGroup = new DefaultEventLoopGroup();

        new ServerBootstrap()
                .group(new NioEventLoopGroup(),new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    /**
                     * 连接建立之后调用这个方法
                     * @param nioSocketChannel
                     * @throws Exception
                     */
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 处理Inbound的读事件 msg是ByteBuf类型 ByteBufnetty进行的优化buffer
                                ByteBuf msg1 = (ByteBuf) msg;
                                log.debug(msg1.toString(Charset.defaultCharset()));
                            }
                        });

                    }
                }).bind(8080);
    }
}
