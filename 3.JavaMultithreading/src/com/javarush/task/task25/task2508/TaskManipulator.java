package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable,CustomThreadManipulator {
    Thread x;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(String threadName) {
        x = new Thread(this);
        x.setName(threadName);
        x.start();
    }

    @Override
    public void stop() {
        x.interrupt();
    }
}
