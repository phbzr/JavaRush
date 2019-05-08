package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileInputStream inputStreamer =  new FileInputStream(reader.readLine());
        load(inputStreamer);
        inputStreamer.close();
        FileOutputStream outputStreamer = new FileOutputStream(reader.readLine());
        save(outputStreamer);
        outputStreamer.close();
        reader.close();


        //етод fillInPropertiesMap должен вызывать метод load
        //передавая только что созданный FileInputStream в качестве параметра.

    }

    public void save(OutputStream outputStream) throws Exception {
            Properties propertiesConfig = new Properties();
            propertiesConfig.putAll(properties);
            propertiesConfig.store(outputStream,"ConfigProperties");
            //implement this method - реализуйте этот метод
    }

    public void load(InputStream inputStream) throws Exception {
        Properties propertiesConfig = new Properties();
        propertiesConfig.load(inputStream);
        properties.putAll((Map)propertiesConfig);
        //implement this method - реализуйте этот метод
    }

    public static void main(String[] args) {

    }
}
