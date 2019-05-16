package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String filename = args[0];
        String numbert = args[1];
        String text = args[2];
        long number = Long.parseLong(numbert);

        RandomAccessFile raf = new RandomAccessFile(filename,"rw");
        raf.length();
        if (raf.length() < number){
            raf.seek(raf.length());
            raf.write(text.getBytes());
        }else {
            raf.seek(number);
            raf.write(text.getBytes());
        }
        raf.close();

    }
}
