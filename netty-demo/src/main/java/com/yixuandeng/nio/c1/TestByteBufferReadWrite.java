package com.yixuandeng.nio.c1;/**
 * @Author 85067
 * @create 22/04/2023 15:45
 */

import java.nio.ByteBuffer;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 22/04/2023 15:45
 */
public class TestByteBufferReadWrite {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);//'a'
        buffer.put((byte) 0x62);//'b'
        buffer.put((byte) 0x63);//'c'


        System.out.println(buffer);

        // 切换成读模式
        System.out.println("没有切换到读模式，使用get的指针指向空位置：>> "+buffer.get());
        buffer.flip();
        System.out.println("切换到读模式，使用get的指针position指向数组头：>>"+(char)buffer.get());
        System.out.println();
        //切换回写模式
        buffer.compact();
        System.out.println("使用compact切换回写模式，没有读到的数据向前移动，position指针指向尾巴: >>"+buffer.get());

    }
}
