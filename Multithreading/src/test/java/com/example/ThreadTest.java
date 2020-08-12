package com.example;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void CreateThread() {
        /*Thread myThread = new MyThread();
        myThread.run();*/
        Thread thread=new Thread(new MyRunnable());
        thread.run();
    }
    
    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread Created!");
        }
    }
    
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Created!");
        }
    }
}