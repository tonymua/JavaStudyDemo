package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask futureTask = new FutureTask(task);
        new Thread(futureTask).start();
        try {
            System.out.println("task运行结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class Task implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在计算！");
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
