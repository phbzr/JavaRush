package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        for (int i = 0; i<2;i++ ){
            Random r = new Random();
            char c =  (char) (r.nextInt(26) + 'a');
            bao.write(c);
            c =  (char) (r.nextInt(26) + 'A');
            bao.write(c);
            int d = r.nextInt(9);
            String x = Integer.toString(d);
            try {
                bao.write(x.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            c =  (char) (r.nextInt(26) + 'a');
            bao.write(c);
        }
        return bao;
    }
}