package com.javarush.task.task22.task2201;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Строки нитей или строковые нити? Вот в чем вопрос
*/
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }

    public static final String FIRST_THREAD_NAME = "1#";
    public static final String SECOND_THREAD_NAME = "2#";

    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Solution() {
        initThreads();
    }

    protected void initThreads() {
        this.thread1 = new Thread(new Task(this, "FDSFS\t"), FIRST_THREAD_NAME);
        this.thread2 = new Thread(new Task(this, "FDSFS\t"), SECOND_THREAD_NAME);
        this.thread3 = new Thread(new Task(this, "\t"), "3#");

        Thread.setDefaultUncaughtExceptionHandler(new OurUncaughtExceptionHandler());

        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
    }

    public synchronized String getPartOfString(String string, String threadName) {
        try {
            int v = string.indexOf("\t");
            int z = string.lastIndexOf("\t");
            String mow = string.substring(v+1,z);
            return mow;
        } catch (Exception e){
            if (threadName.equals(FIRST_THREAD_NAME)){
                StringForFirstThreadTooShortException ex1 = new StringForFirstThreadTooShortException();
                ex1.initCause(e);
                throw ex1;
            } else if(threadName.equals(SECOND_THREAD_NAME)){
                StringForSecondThreadTooShortException ex2=new StringForSecondThreadTooShortException();
                ex2.initCause(e);
                throw ex2;
            } else {
                RuntimeException ex3=new RuntimeException();
                ex3.initCause(e);
                throw ex3;
            }
        }
    }
}
