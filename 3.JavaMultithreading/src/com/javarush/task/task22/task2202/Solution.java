package com.javarush.task.task22.task2202;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис "));
    }

    public static String getPartOfString(String string) {
        if(string == null){
            throw new TooShortStringException();
        }
        int z = string.indexOf(" ");

        Pattern pt = Pattern.compile(" ");
        Matcher mt = pt.matcher(string);
        List<Integer>list = new ArrayList<>();
        while (mt.find()){
            list.add(mt.start());
        }
        if(z == -1 || list.size() < 4){
            throw new TooShortStringException();
        }
        String v ="";
        if(list.size()>4){
          v = string.substring(list.get(0)+1,list.get(4));
        }
        if(list.size() == 4){
            v = string.substring(list.get(0)+1);
        }


        return v;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
