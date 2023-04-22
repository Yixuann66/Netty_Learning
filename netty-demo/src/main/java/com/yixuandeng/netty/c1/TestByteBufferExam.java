package com.yixuandeng.netty.c1;/**
 * @Author 85067
 * @create 22/04/2023 21:17
 */

import java.nio.ByteBuffer;

/**
 * @author 85067
 * @version 1.0
 * @description: 解决粘包和半包的问题
 * @date 22/04/2023 21:17
 */

public class TestByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("hello,world\nI'mzhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {

    }
}
