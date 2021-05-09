package demo;

import java.util.Calendar;
import java.util.Date;

/**
 * @author:
 * @date: created in 9:48 2020/10/20
 * @version:
 */
public class Test {
    public static void main(String[] args) {
        String tw = getTw(1603161401000L);
        System.out.println(tw);
    }
    /**
     * 方法名：获取当季tw----计算日期参数当季的tw
     * 入参time：日期时间
     */
    public static String getTw(Object time) {
        if (time == null || "".equals(time)) {
            return null;
        }
        long time1 = 0;
        if (time instanceof Date) {
            time1 = ((Date) time).getTime();
        } else if (time instanceof Long) {
            time1 = (Long) time;
        } else {
            time1 = Long.valueOf(time.toString());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time1);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth % 3 == 0) {
            return "3M";
        } else if (currentMonth % 3 == 1) {
            return "1M";
        } else if (currentMonth % 3 == 2) {
            return "2M";
        } else {
            return null;
        }
    }
}