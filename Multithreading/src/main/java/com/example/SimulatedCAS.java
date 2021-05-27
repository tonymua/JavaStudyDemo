package com.example;

/**
 * 模拟CAS操作 等价代码
 */
public class SimulatedCAS implements Runnable {
    private int value;

    public synchronized int compareAngSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
            System.out.println(Thread.currentThread().getName() + "执行成功！");
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        SimulatedCAS simulatedCAS = new SimulatedCAS();
        simulatedCAS.value = 100;
        Thread thread1 = new Thread(simulatedCAS);
        Thread thread2 = new Thread(simulatedCAS);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(simulatedCAS.value);
    }

    @Override
    public void run() {
        compareAngSwap(100, 150);
    }
}
