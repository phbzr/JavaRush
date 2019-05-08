package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
//        Map<String,String>lol = new HashMap<>();
//        lol.put("name","Ivanov");
//        lol.put("country","Ukraine");
//        lol.put("city","Kiev");
//        lol.put("age",null);
//        System.out.println(lol);
//        System.out.println(getQuery(lol));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder visa = new StringBuilder();
        int count = 1;
        for (Map.Entry<String,String> pait : params.entrySet()){
            String key = pait.getKey();
            String value = pait.getValue();
            if( value !=null) {
                if (count != 1) {
                    visa = visa.append(" and ");
                }
                visa = visa.append(key);
                visa = visa.append(" = \'");
                visa = visa.append(value);
                visa = visa.append("\'");
                count++;

            }
        }
        String s = visa.toString();
        return s;
    }
}
