package com.javarush.task.task28.task2805;

public class MyThread extends Thread {

    private static int count = MIN_PRIORITY;

    public void setCounter(){
        if (count == Thread.currentThread().getThreadGroup().getMaxPriority()+1){
            count= MIN_PRIORITY;
        }
        setPriority(count++);
    }

    public MyThread() {
        setCounter();

    }

    public MyThread(Runnable target) {
        super(target);
        setCounter();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setCounter();
    }

    public MyThread(String name) {
        super(name);
        setCounter();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setCounter();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCounter();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setCounter();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setCounter();
    }


}
