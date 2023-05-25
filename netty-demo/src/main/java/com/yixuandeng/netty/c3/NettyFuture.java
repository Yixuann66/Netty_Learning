package com.yixuandeng.netty.c3;/**
 * @Author 85067
 * @create 25/05/2023 14:46
 */

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 25/05/2023 14:46
 */
@Slf4j
public class NettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoop eventLoop = eventLoopGroup.next();

        // 1. 提交任务 submit传递callable方法 获取返回值
        Future<Integer> future = eventLoop.submit(new Callable<Integer>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 999;
            }
        });

        // 同步方式获取结果
//        log.debug("等待结果");
//        log.debug("结果是"+future.get());

        // 异步方式获取结果
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            /**
             * complete的时候获取结果
             * @param future
             * @throws Exception
             */
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("异步方式接收到的结果是："+ future.getNow());
            }
        });


        log.debug("主线程运行到最后");

    }
}
