package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class TimeDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020,4,19);
        System.out.println(date.getYear()); // 2020
        System.out.println(date.getMonth());// APRIL
        System.out.println(date.getDayOfMonth());// 19
        System.out.println(date.getDayOfWeek());// SUNDAY
        System.out.println(date.getDayOfYear());// 110
        System.out.println(date.lengthOfMonth());// 30
        System.out.println(date.lengthOfYear());// 366
        System.out.println(date.isLeapYear());// 是否是闰年 true
        System.out.println(LocalDate.now());// 2020-04-19
        System.out.println(date.get(ChronoField.YEAR));// 2020
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));// 4

        // 天数差
        LocalDateTime time1 = LocalDateTime.of(2020,4,19,15,51,55);
        LocalDateTime time2 = LocalDateTime.of(2022,4,19,15,51,40);
        Duration between = Duration.between(time1, time2);
        System.out.println(between.toDays());// 729
        // 时间戳
        System.out.println(Instant.now().getEpochSecond());// 1587290410
        Instant instant1 = time1.atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2 = time2.atZone(ZoneId.systemDefault()).toInstant();
        long days = Duration.between(instant1, instant2).toDays();
        // 天数差
        System.out.println(days);// 729

        LocalDate date1 = LocalDate.of(2020,4,19);
        LocalDate date2 = LocalDate.of(2022,5,30);
        Period period = Period.between(date1, date2);
        // 这个只能算年月日中日的差值
        System.out.println(period.getDays());//11天

        // 天数差
        long d1 = ChronoUnit.DAYS.between(date1, date2);
        System.out.println(d1);// 771
        // 月份差
        long m1 = ChronoUnit.MONTHS.between(date1, date2);
        System.out.println(m1);// 25
        // 年差
        long y1 = ChronoUnit.YEARS.between(date1, date2);
        System.out.println(y1);// 2
        // 本周周一
        LocalDate date3 = date1.with(nextOrSame(DayOfWeek.MONDAY));
        System.out.println(date3);// 2020-04-20

        //本月第几周的周一   -5~5   负数表述倒数第几周
        LocalDate date4 = date1.with(TemporalAdjusters.dayOfWeekInMonth(-2, DayOfWeek.MONDAY));
        System.out.println(date4);// 2020-04-20

        // 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String format = date4.format(formatter);
        System.out.println(format);// 20042020
        LocalDate date5 = LocalDate.parse(format, formatter);
        System.out.println(date5);// 2020-04-20

        ZoneId romeZone = ZoneId.of("Europe/Rome"); // 罗马时间
        ZonedDateTime zdt1 = date5.atStartOfDay(romeZone);// 罗马时区当天最早的时间
        System.out.println(zdt1);// 2020-04-20T00:00+02:00[Europe/Rome]

        LocalDateTime dateTime = LocalDateTime.of(2020,4,19,17,58,00);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);// 罗马时间  2020-04-19T17:58+02:00[Europe/Rome]

        Instant now = Instant.now();
        ZonedDateTime zdt3 = now.atZone(romeZone);
        System.out.println(zdt3); // 2020-04-19T12:00:10.254446800+02:00[Europe/Rome]

    }
}
