package com.example;

/**
 * @author:
 * @date: created in 20:47 2021/5/2
 * @version:
 */
public class ThreadLocalDemo07 {
    public static void main(String[] args) {
        new Service1().service1();
    }
}

class User {
    String username;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

class userContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1 {
    public void service1() {
        User user = new User("张三");
        userContextHolder.holder.set(user);
        new Service2().service2();
    }
}

class Service2 {
    public void service2() {
        User user = userContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.getUsername());
        new Service3().service3();
    }
}

class Service3 {
    public void service3() {
        User user = userContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.getUsername());
        userContextHolder.holder.remove();
    }
}