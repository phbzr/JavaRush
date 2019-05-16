package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {

        StringWriter sw = new StringWriter();
        if (is == null){
            return sw;
        }
        byte[] bytes = new byte[is.available()];
        if (is.available()>-1) {
            is.read(bytes);
        }
        String line = new String(bytes);
        sw.write(line);

        return sw;
    }

}