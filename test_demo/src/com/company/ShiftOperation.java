package com.company;

public class ShiftOperation {
    public static void main(String[] args) {
        int n=7;    //00000000 00000000 00000000 00000111
        int a=n<<1; //00000000 00000000 00000000 00001110 对整数7左移1位
        int b=n<<28;//01110000 00000000 00000000 00000000 对整数7左移28位
        int c=n<<29;//10000000 00000000 00000000 00000000 对整数7左移29位
        System.out.println("a:"+a+"\n"+"b:"+b+"\n"+"c:"+c);
    }
}
