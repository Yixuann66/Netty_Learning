package com.yixuandeng.nio.c1;/**
 * @Author 85067
 * @create 22/04/2023 15:55
 */

import java.nio.ByteBuffer;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 22/04/2023 15:55
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
        /**
         * class java.nio.HeapByteBuffer - java 堆内存 读写效率低 收到GC的影响
         * class java.nio.DirectByteBuffer - java 直接内存 读写效率高 少一次数据的拷贝 直接使用系统的内存 不受gc影响
         */

    }

}
