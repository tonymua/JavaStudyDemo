package demo;

/**
 * @author:
 * @date: created in 10:18 2021/2/4
 * @version:
 */
public class Test3 {
    public static void main(String[] args) {
        /*// 存在入参为空，则返回-1D
        if (obj1 == null || obj2 == null)
            System.out.println("null");*/
        double item1 = 1, item2 = 0;
        // 被除数为0，返回Double最大值
        if (item2 == 0D)
            System.out.println(Double.MAX_VALUE);
        System.out.println(item1 / item2);
    }
}