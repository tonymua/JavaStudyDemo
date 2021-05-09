package demo.innerclass;

import demo.dao.Person;

/**
 * @author:
 * @date: created in 9:43 2020/10/19
 * @version:
 */
/*
匿名内部类：没有名字的内部类，必须继承一个父类或者实现一个接口
常用于多线程的实现，因为实现多线必须继承Thread类或者实现Runnable接口

 */
public class OuterClass03 {
    /*public static void main(String[] args) {
        Person person = new Person() {
            @Override
            public void eat() {
                System.out.println("eat...");
            }
        };
        person.eat();
    }*/
    //Thread的匿名内部类实现
    /*public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i + "");
                }
            }
        };
        thread.start();
    }*/
    //Runnable接口的匿名内部类实现
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(i + ""+Thread.currentThread());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}