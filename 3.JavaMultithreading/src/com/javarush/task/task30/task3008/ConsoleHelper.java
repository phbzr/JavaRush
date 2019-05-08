package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        String line = "";
        boolean flag = true;
        while (flag) {
            try {
                line = reader.readLine();
                flag = false;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
        return line;
    }

    public static int readInt(){
        int numb = 0;
           try {
               numb = Integer.parseInt(readString());
           } catch (NumberFormatException e){
               System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
               numb = Integer.parseInt(readString());
           }

        return numb;
    }
}
