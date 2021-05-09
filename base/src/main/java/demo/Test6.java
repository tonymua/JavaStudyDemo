package demo;

/**
 * @author:
 * @date: created in 14:37 2021/4/9
 * @version:
 */
public class Test6 {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        while (classLoader != null) {
            System.out.println("----" + classLoader.getClass().getName());
            classLoader = classLoader.getParent();
        }
        System.out.println("----" + classLoader);
    }
}