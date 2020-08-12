package com.example;

/**
 * 线程中断
 * 
 * @author:
 * @date: created in 15:08 2020/7/29
 * @version:
 */
public class Demo_03 {
    public static void main(String[] args)
        throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int n = 0;
                while (!isInterrupted()) {
                    System.out.println("Hello!" + n++);
                }
            }
        };
        thread.start();
        Thread.sleep(1); // 暂停1毫秒
        thread.interrupt(); // 中断线程
        thread.join(); // 等待线程结束
        System.out.println("end");
    }
}