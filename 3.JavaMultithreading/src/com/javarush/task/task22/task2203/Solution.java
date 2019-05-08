package com.javarush.task.task22.task2203;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null){
            throw new TooShortStringException();
        }
        Pattern pt = Pattern.compile("\t");
        Matcher mt = pt.matcher(string);
        List<Integer>list = new ArrayList<>();
        while (mt.find()){
            list.add(mt.start());
        }
        if (list.size()<2){
            throw new TooShortStringException();
        }
        String v = string.substring(list.get(0)+1,list.get(1));
        return v;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
