package demo;

/**
 * @author:
 * @date: created in 15:38 2021/4/10
 * @version:
 */
public class Test7 {
    public void method(){
        synchronized (this){
            System.out.println("test");
        }
    }
}