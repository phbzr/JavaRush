package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(getTokens("lox","o"));
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer s = new StringTokenizer(query, delimiter);
        List<String>list = new ArrayList<>();
        while (s.hasMoreTokens())
        {
            String token = s.nextToken();
            list.add(token);
        }
        String[] result = new String[list.size()];
        for (int i = 0; i< list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }
}
