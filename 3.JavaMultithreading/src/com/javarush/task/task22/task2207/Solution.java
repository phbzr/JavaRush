package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();///home/phbzr/Desktop/JAVA/lol3.txt
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        String lastWord = "";
        List<String>list = new ArrayList<>();
        while ((line = bufferedReader.readLine())!=null){
            for (String word : line.split(" ")) {
                list.add(word);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                String reverse =  new StringBuilder(list.get(j)).reverse().toString();
                if (list.get(i).equals(reverse) && !list.get(i).isEmpty()) {
                    if (lastWord.equals(list.get(i))){
                        continue;
                    }
//                    System.out.println(list.get(i) + " " + list.get(j));
                    result.add(new Pair(list.get(i),list.get(j)));
                    lastWord = list.get(i);
                }
            }
        }
        bufferedReader.close();
    }

    public static class Pair {
        String first;
        String second;


        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
