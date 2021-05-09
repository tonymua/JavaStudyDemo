package com.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author:
 * @date: created in 21:45 2021/3/27
 * @version:
 */

public class ForkJoinPoolExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask<Integer> task = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(task.get());
        }
    }

    /*public static int Fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return Fib(n - 1) + Fib(n - 2);
        }
    }*/

}

class Fibonacci extends RecursiveTask<Integer> {
    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1 || n == 2) {
            return 1;
        }
        Fibonacci fibonacci1 = new Fibonacci(n - 1);
        fibonacci1.fork();
        Fibonacci fibonacci2 = new Fibonacci(n - 2);
        fibonacci2.fork();
        return fibonacci1.join() + fibonacci2.join();
    }
}