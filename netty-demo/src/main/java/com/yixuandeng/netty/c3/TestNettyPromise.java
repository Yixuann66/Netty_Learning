package com.yixuandeng.netty.c3;/**
 * @Author 85067
 * @create 25/05/2023 14:53
 */

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

import static com.google.common.collect.ComparisonChain.start;

/**
 * @author 85067
 * @version 1.0
 * @description: NettyPromise 和future对比： 可以手动创建一个promise对象 + 可以手动设置promise对象里面的值 promise.setSuccess(80);
 *
 * @date 25/05/2023 14:53
 */


@Slf4j
public class TestNettyPromise {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        EventLoop eventLoop = new NioEventLoopGroup().next();


        // 1. 主动创建promise对象，结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        // 2.
        new Thread(() -> {
            // 3. 任意一个线程计算完毕 向promise对象填充结果
            log.debug("开始计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 成功的结果
            promise.setSuccess(80);
        }).start();


        // 4. 接受结果
        log.debug("等待结果");
        log.debug("result is : " + promise.get());
    }
}
