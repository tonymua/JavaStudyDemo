package com.example;

/**
 * 哲学家就餐问题
 */
public class DiningPhilosophers {

    public static class Philosophers implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosophers(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("思考人生...");
                    synchronized (leftChopstick) {
                        doAction("拿起左边的筷子！");
                        synchronized (rightChopstick) {
                            doAction("拿起右边的筷子！");
                            doAction("吃饭...");
                            doAction("放下右边的筷子！");
                        }
                        doAction("放下左边的筷子！");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "：" + action);
            Thread.sleep((long)(Math.random() * 10));
        }
    }

    public static void main(String[] args) {
        Philosophers[] philosophers = new Philosophers[5];
        Object[] chopsticks = new Object[philosophers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopsStick = chopsticks[(i + 1) % chopsticks.length];
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosophers(rightChopsStick, leftChopstick);
            } else {
                philosophers[i] = new Philosophers(leftChopstick, rightChopsStick);
            }
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }
}
