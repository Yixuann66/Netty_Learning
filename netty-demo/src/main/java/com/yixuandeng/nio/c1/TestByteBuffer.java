package com.yixuandeng.nio.c1;/**
 * @Author 85067
 * @create 21/04/2023 17:52
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 85067
 * @version 1.0
 * @description:
 *      channel 有一点类似于 stream，它就是读写数据的**双向通道**，可
 *              以从 channel 将数据读入 buffer，也可以将 buffer 的数据写入 channel，
 *              而之前的 stream 要么是输入，要么是输出，channel 比 stream 更为底层
 *      nio 三大组件: buffer 则用来缓冲读写数据，常见的 buffer 有
 * @date 21/04/2023 17:52
 */


public class TestByteBuffer {
    public static void main(String[] args) {
        // fileChannel
        // 1. IF there is a error : finally will close the inputstream
        File file = new File("D:\\NewWorkSpacee\\Netty_Learning\\nio-demo\\data.txt");

        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            // prepare ByteBuffer : Allocate 10 byte to be a buffer
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // read data from channel
            // write data to buffer
            while(true){

                // no content : -1
                int len = channel.read(buffer);

                // if no date : the len will be -1 break;
                if(len == -1 ) {
                    break;

                }

                // print context in buffer

                // switch buffer to read mode
                buffer.flip();
                // read ONE byte a time
                while(buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                // buffer : switch to write mode
                buffer.clear();
            }



        } catch (IOException e) {
        }
    }



}
