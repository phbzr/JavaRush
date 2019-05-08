package com.javarush.task.task20.task2019;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Исправить ошибку. Сериализация
*/
public class Solution implements Serializable {

    public static void main(String args[]) throws Exception {
        File f = new File("/home/phbzr/Desktop/JAVA/loler.txt");
        FileOutputStream fileOutput = new FileOutputStream(f);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        Solution solution = new Solution();
        solution.m.put("LOL","LOX");
        outputStream.writeObject(solution);

        fileOutput.close();
        outputStream.close();

        //load
        FileInputStream fiStream = new FileInputStream(f);
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Solution loadedObject = (Solution) objectStream.readObject();

        fiStream.close();
        objectStream.close();

        //Attention!!
        System.out.println(loadedObject.size());
    }

    private Map<String, String> m = new HashMap<>();

    public Map<String, String> getMap() {
        return m;
    }
    public  Solution() {
    }


    public int size() {

        return m.size();
    }
}
