package com.yixuandeng.netty.c3;/**
 * @Author 85067
 * @create 25/05/2023 13:58
 */

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author 85067
 * @version 1.0
 * @description: TestJDKFuture
 * @date 25/05/2023 13:58
 */

@Slf4j
public class TestJDKFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 线程池
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>()
                , new DefaultThreadFactory("TestJDKFutureName"));
        // 2. 提交任务
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Integer call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(2000);
                return 50;
            }
        });
        // 3. 通过future对象，将这个线程和主线程进行交流 阻塞同步等待 主线程等待结果
        Integer integer = future.get();
        log.debug("结果是"+integer);


    }

}
