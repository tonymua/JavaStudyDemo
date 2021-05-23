package com.example;

/*
 * 内存可见性问题
 */
public class VisibilityProblem {
    int a = 10;
    int b = 20;

    private synchronized void change() {
        a = 30;
        b = a;
    }

    private synchronized void print() {
        System.out.println("b=" + b + ";a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            VisibilityProblem visibilityProblem = new VisibilityProblem();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    visibilityProblem.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    visibilityProblem.print();
                }
            }).start();
        }
    }
}
