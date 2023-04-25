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
        // 切换成读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整新消息
            if (source.get(i) == '\n') {
                // 把新的新消息存入新的byteBuffer
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                System.out.println(target);
            }
        }


        // 切换成写模式 将第一次未读的向前整理
        source.compact();

    }
}
