package com.company;

public class IProblem {
    private static int i=1;
    static {
        i++;
        System.out.println("static:"+i);
    }
    private void function(){
        i++;
        System.out.println("function:"+i);
    }

    public static void main(String[] args) {
        IProblem iProblem=new IProblem();
        iProblem.function();
        System.out.println("main:"+i);
    }
}
