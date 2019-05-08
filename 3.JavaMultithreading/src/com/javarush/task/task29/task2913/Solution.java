package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder str = new StringBuilder();
        if (a < b) {
            for (int i = a; i <= b ; i++){
                str.append(i);
                if (i!=b){
                    str.append(" ");
                }
            }
            String res = str.toString();
            return res;
        } else {
            for (int i = a; i >= b ; i--){
                str.append(i);
                if(i!= b) {
                    str.append(" ");
                }

            }
            String res = str.toString();
            return res;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(100);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
       System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}