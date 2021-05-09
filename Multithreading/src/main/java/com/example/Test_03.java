package com.example;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author:
 * @date: created in 13:54 2021/3/20
 * @version:
 */
public class Test_03 implements Callable {
    @Override
    public Integer call() throws Exception {
        int i = new Random().nextInt();
        System.out.println(Thread.currentThread().getName() +" : "+ i);
        return i;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            Future<Integer> future = executorService.submit(new Test_03());
        }
        executorService.shutdown();
    }
}