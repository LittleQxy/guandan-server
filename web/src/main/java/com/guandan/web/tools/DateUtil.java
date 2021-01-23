package com.school.practice.market.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工具类
 *
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 19:18 2020/12/9
 */
public class DateUtil {

    public static final String ISO8601_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ISO8601_TIME_PATTERN = "HH:mm:ss";

    public static String format(long date, String pattern) {
        return format(new Date(date), pattern);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    public static String formatToDateTimeString(Date date) {
        return format(date, ISO8601_DATETIME_PATTERN);
    }

    public static String formatToDateString(Date date) {
        return format(date, ISO8601_DATE_PATTERN);
    }

    public static String formatToTimeString(Date date) {
        return format(date, ISO8601_TIME_PATTERN);
    }

    public static Date stringToDateTime(String str){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(ISO8601_DATETIME_PATTERN);
            Date date = dateFormat.parse(str);
            return date;
        }catch (ParseException e) {
            throw new RuntimeException("字符串转换日期出错,msg = " + e.getMessage());
        }
    }

    /**
     * 判断两个时间是否为同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean checkInOneDay(Date date1,Date date2){
        ZoneId zoneId = ZoneId.systemDefault();
        Instant nowInstant = date1.toInstant();
        Instant compareInstant = date2.toInstant();

        LocalDate nowDate = nowInstant.atZone(zoneId).toLocalDate();
        LocalDate compareDate = compareInstant.atZone(zoneId).toLocalDate();
        //不是同一天返回false
        if (nowDate.isBefore(compareDate) || nowDate.isAfter(compareDate)){
            return false;
        }
        return true;
    }


}
