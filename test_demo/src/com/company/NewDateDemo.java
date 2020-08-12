package com.company;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class NewDateDemo {
    public static void main(String[] args) {
        /*DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = dateTime1.plusDays(3).minusHours(5);
        LocalDateTime dateTime3 = dateTime1.withMonth(2).withDayOfMonth(1).withHour(2);
        System.out.println(dateTime1);
        System.out.println(dateTimeFormatter.format(dateTime2));
        System.out.println(dateTime3);*/

        LocalDate start = LocalDate.of(2020, 1, 1);
        int count=0;
        for (int i = 0; i < 365*30; i++) {
            LocalDate localDate = start.plusDays(i);
            while (localDate.getDayOfWeek()==DayOfWeek.SATURDAY||localDate.getDayOfWeek()==DayOfWeek.SUNDAY){
                count++;
                System.out.println(localDate);
                break;
            }
        }
        System.out.println(count);
        /*
         * System.out.println(dateTimeFormatter.format(LocalDateTime.now())); LocalDateTime dateTime =
         * LocalDateTime.parse("2020/07/28 19:19:33", dateTimeFormatter); System.out.println(dateTime);
         */
        /*
         * // 本月第一天0:00时刻: LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
         * System.out.println(firstDay);
         * 
         * // 本月最后1天: LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()); System.out.println(lastDay);
         * // 下月第1天: LocalDate nextDayFirstMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
         * System.out.println(nextDayFirstMonth); // 本月第1个周一: LocalDate firstWeekday =
         * LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); System.out.println(firstWeekday);
         * 
         * LocalDateTime start = LocalDateTime.of(2020, 5, 30, 12, 8, 0); LocalDateTime end = LocalDateTime.now(); Duration
         * between = Duration.between(start, end); System.out.println(between); //PT1423H34M58.019S 1423小时34分钟58.019秒
         * 
         * Period period = LocalDate.of(2020, 1, 2).until(LocalDate.now()); System.out.println(period); //P6M26D 6个月26天
         */
        /*Instant instant1 = new Date().toInstant();
        Instant instant2 = Calendar.getInstance().toInstant();
        ZonedDateTime zonedDateTime =
            instant2.atZone(Calendar.getInstance().getTimeZone().toZoneId());*/

    }
}