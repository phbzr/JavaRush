package com.javarush.task.task25.task2512;

import java.util.Stack;

/*
Живем своим умом
*/
public class Solution extends Thread implements Thread.UncaughtExceptionHandler {

    public Solution() {
        setDefaultUncaughtExceptionHandler(this::uncaughtException);
    }



    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.start();
        Thread.sleep(500);
        //throw new IllegalAccessError();
        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
    }

    @Override
    public void run() {
        super.run();
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Stack<String> stack = new Stack<>();
        while (e != null){
            t.interrupt();
            stack.push(e.toString());
            e = e.getCause();
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}
