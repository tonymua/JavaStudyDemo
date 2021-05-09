package demo;

import java.util.Calendar;
import java.util.Date;

/**
 * @author:
 * @date: created in 18:05 2020/10/26
 * @version:
 */
public class Test2 {
    public static void main(String[] args) {
        String cm = getCM("1603707294000");
        System.out.println(cm);
    }
    public static String getCM(Object time) {
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
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long time2 = calendar.getTimeInMillis();
        return (int)((time1 - time2) / (1000 * 3600 * 24))+"d";

    }

}