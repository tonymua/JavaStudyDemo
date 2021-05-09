package demo;

/**
 * @author:
 * @date: created in 14:55 2021/4/9
 * @version:
 */
class Foo {

    public Foo() {

    }
    volatile int count=1;
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (count!=2);
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (count!=3);
        printThird.run();
    }
}