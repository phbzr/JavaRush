package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread x;
    State currentState;

    public LoggingStateThread(Thread thread) {
        x = thread;
        setDaemon(true);
    }

    @Override
    public void run() {

        while (true) {
            if (x.getState() != currentState) {
                currentState = x.getState();
                System.out.println(currentState);
            }
            if (currentState == State.TERMINATED) {
                break;
            }
        }
        interrupt();
    }
}
