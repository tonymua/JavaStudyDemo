package com.example;

/**
 * @author:
 * @date: created in 17:51 2020/8/10
 * @version:
 */
public class daemon_thread {

    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(getName() + "aaa" + i);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println(getName() + "bbb" + i);
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(getName() + "ccc" + i);
                }
            }
        };

        thread3.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();
        //所有非守护线程都执行完毕后,守护线程才中断停止(注意:守护线程不是立即停止,中间有个缓冲时间。)
    }
}