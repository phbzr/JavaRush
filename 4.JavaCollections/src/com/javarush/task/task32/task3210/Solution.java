package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String filename = args[0];
        String tmp = args[1];
        String text = args[2];
        long number = Long.parseLong(tmp);
        byte[] buffer = new byte[text.getBytes().length];
        RandomAccessFile raf = new RandomAccessFile(filename,"rw");
        raf.seek(number);
        raf.read(buffer, 0,buffer.length);
        String result = new String(buffer);
        if (result.equals(text)){
            raf.seek(raf.length());
            raf.write("true".getBytes());
        } else {
            raf.seek(raf.length());
            raf.write("false".getBytes());
        }
        raf.close();
    }
}
