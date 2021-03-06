package com.javarush.task.task25.task2505;

/* 
Без дураков
*/
public class Solution {

    public static void main(String[] args)  {

        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            //setDaemon(true);

        }
        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{



            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(String.format("%s, %s, %s",MyThread.this.secretKey,MyThread.this.getName(),e.getMessage()));
            }
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }
    }

}

