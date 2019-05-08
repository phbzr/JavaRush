package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = (Thread.UncaughtExceptionHandler) new Down();
        //init handler here

    }
    class Down implements Thread.UncaughtExceptionHandler {

        public void uncaughtException(Thread t, Throwable e) {

            String hide = t.getName().replaceAll(".","*");
            String z = e.getMessage().replaceAll(t.getName(),hide);
            System.out.println(z);
        }
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {

    }
}