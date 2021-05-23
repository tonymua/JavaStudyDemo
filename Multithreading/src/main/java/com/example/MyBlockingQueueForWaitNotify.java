package com.example;

import java.util.LinkedList;

/*
 * 使用wait/notify来实现简易版阻塞队列
 */
public class MyBlockingQueueForWaitNotify {
    private int maxSize;
    private LinkedList<Object> queue;

    public MyBlockingQueueForWaitNotify(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList<>();
    }

    public synchronized void put(Object object) throws InterruptedException {
        while (queue.size() == maxSize) {
            this.wait();
        }
        queue.add(object);
        this.notifyAll();
    }

    public synchronized Object take() throws InterruptedException {
        while (queue.size() == 0) {
            this.wait();
        }
        Object item = queue.remove();
        this.notifyAll();
        return item;
    }
}
