package com.yixuandeng.netty.c3;/**
 * @Author 85067
 * @create 25/05/2023 10:40
 */

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 25/05/2023 10:40
 */
@Slf4j
public class EnentLoopGroupTest {

    public static void main(String[] args) {
        // 1. 创建事件循环组
        // io 事件， 普通任务， 定时任务
        // 如果不指定线程数 则使用默认的线程数 一个线程对应一个selector
        //     private static final int DEFAULT_EVENT_LOOP_THREADS = Math.max(1
        //     // cpu核心数量乘2
        //     , SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        // 普通任务， 定时任务
        EventLoopGroup defaultEventLoop = new DefaultEventLoop();

        // 2. 获取下一个事件循环对象 轮询的效果 一个事件负责一个channel
        System.out.println(nioEventLoopGroup.next());
        System.out.println(nioEventLoopGroup.next());

        //11:11:16.170 [nioEventLoopGroup-2-1] DEBUG com.yixuandeng.netty.c3.EnentLoopGroupTest - ok
        //11:11:16.171 [main] DEBUG com.yixuandeng.netty.c3.EnentLoopGroupTest - 主线程
        //11:11:16.171 [nioEventLoopGroup-2-2] DEBUG com.yixuandeng.netty.c3.EnentLoopGroupTest - timer task
        //11:11:17.182 [nioEventLoopGroup-2-2] DEBUG com.yixuandeng.netty.c3.EnentLoopGroupTest - timer task

        // 3. 执行普通任务 给事件循环组中的某一个事件循环对象执行
        nioEventLoopGroup.next().submit(() -> {
            log.debug("ok");
        });

        // 4. 执行定时任务
        nioEventLoopGroup.next().scheduleAtFixedRate(()->{
            log.debug("timer task");
        },0,1, TimeUnit.SECONDS);

        log.debug("主线程");

    }
}
