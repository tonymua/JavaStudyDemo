package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateDemo {
    public static void main(String[] args) {
       /* Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));*/
        /*// 当前时间:
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置2020年:
        c.set(Calendar.YEAR,2020);
        // 设置9月:注意8表示9月:
        c.set(Calendar.MONTH,8);
        // 设置2日:
        c.set(Calendar.DATE, 2);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 21);
        c.set(Calendar.MINUTE, 22);
        c.set(Calendar.SECOND, 23);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
        // 2020-09-02 21:22:23*/
        //当前时间
        Calendar calendar=Calendar.getInstance();
        //清除所有
        calendar.clear();
        //设置北京时区
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.set(2019,10 ,20,8,15,0);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
    }
}