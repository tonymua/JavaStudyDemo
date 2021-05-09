package demo.innerclass;

/**
 * @author:
 * @date: created in 21:28 2020/10/18
 * @version:
 */
/*
成员内部类：最普通的内部类，它是外围类的一个成员，
可以无限制的访问外围的所有成员属性和方法，尽管是private的；
外围类要访问内部类的成员属性和方法则需要通过内部类实例来访问
 */
public class OuterClass01 {
    private String str;

    public void outerDisplay(){
        System.out.println("outerClass01...");
    }

    public class InnerClass01{
        public void innerDisplay(){
            //使用外围内的属性
            str="chenssy...";
            System.out.println(str);
            //使用外围内的方法
            outerDisplay();
        }
    }
    //获取成员内部类
    public InnerClass01 getInnerClass01(){
        return new InnerClass01();
    }

    public static void main(String[] args) {
        OuterClass01 outerClass01=new OuterClass01();
        InnerClass01 innerClass01 = outerClass01.getInnerClass01();
        innerClass01.innerDisplay();
    }
}