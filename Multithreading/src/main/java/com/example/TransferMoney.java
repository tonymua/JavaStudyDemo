package com.example;

/**
 * 调整锁的获取顺序来避免死锁问题
 */
public class TransferMoney implements Runnable {
    static class Account {
        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }

    int flag;
    static Account a = new Account(500);
    static Account b = new Account(500);

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int account) {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        // 模拟网络延迟
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (fromHash > toHash) {
            // 先获取两把锁，然后开始转账
            synchronized (to) {
                synchronized (from) {
                    if (from.balance - account < 0) {
                        System.out.println("余额不足，转账失败！");
                        return;
                    }
                    from.balance -= account;
                    to.balance += account;
                    System.out.println("成功转账" + account + "元！");
                }
            }
        }
        if (toHash > fromHash) {
            // 先获取两把锁，然后开始转账
            synchronized (from) {
                synchronized (to) {
                    if (from.balance - account < 0) {
                        System.out.println("余额不足，转账失败！");
                        return;
                    }
                    from.balance -= account;
                    to.balance += account;
                    System.out.println("成功转账" + account + "元！");
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);
    }
}
