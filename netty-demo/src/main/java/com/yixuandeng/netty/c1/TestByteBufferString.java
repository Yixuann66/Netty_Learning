package com.yixuandeng.netty.c1;/**
 * @Author 85067
 * @create 22/04/2023 16:37
 */

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 22/04/2023 16:37
 */
public class TestByteBufferString {
    public static void main(String[] args) {
        // 1.字符串转为byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put("hello".getBytes());
        System.out.println(byteBuffer.toString());
        // 还是写模式

        // 2. charSrt
        ByteBuffer byteBuffer1 = StandardCharsets.UTF_8.encode("hello");
        // 自动切换成读模式
        System.out.println(byteBuffer1);

        // 3. wrap 方法
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("hello".getBytes());


    }

}
