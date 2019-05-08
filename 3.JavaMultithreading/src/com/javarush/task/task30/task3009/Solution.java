package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
    private static Set<Integer> getRadix(String number){
        Integer tmp = null;
        Set<Integer> result = new HashSet<>();
        String s;
        try {
            tmp  = Integer.parseInt(number);
            for (int i = 2; i<37; i++) {
                s = Integer.toString(tmp, i);
                String s2 = new StringBuilder(s).reverse().toString();
                if (s.equals(s2)){
                    result.add(i);
                }
            }
        } catch (NumberFormatException e){

        }

        return result;
    }


}