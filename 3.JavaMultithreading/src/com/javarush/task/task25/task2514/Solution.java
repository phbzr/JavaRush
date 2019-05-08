package com.javarush.task.task25.task2514;

/* 
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
            Thread.yield();
        }

        public void run() {

            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldRunnable yieldRunnable = new YieldRunnable(2);
        Thread thread = new Thread(yieldRunnable);
        thread.start();

        YieldRunnable yieldRunnable2 = new YieldRunnable(1);
        Thread thread2 = new Thread(yieldRunnable2);
        thread2.start();

        YieldRunnable yieldRunnable3 = new YieldRunnable(3);
        Thread thread3 = new Thread(yieldRunnable3);
        thread3.start();


    }
}
