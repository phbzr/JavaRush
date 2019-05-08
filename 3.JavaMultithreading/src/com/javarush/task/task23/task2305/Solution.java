package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {

    }

    public static Solution[] getTwoSolutions() {
        Solution[] lol = new Solution[2];
        lol[0] = new Solution();
        lol[1] = new Solution();
        lol[0].innerClasses[0] = lol[0].new InnerClass();
        lol[0].innerClasses[1] = lol[0].new InnerClass();
        lol[1].innerClasses[0] = lol[1].new InnerClass();
        lol[1].innerClasses[1] = lol[1].new InnerClass();

        return lol;
    }

    public static void main(String[] args) {

    }
}
