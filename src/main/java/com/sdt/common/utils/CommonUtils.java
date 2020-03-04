package com.sdt.common.utils;

import com.sdt.common.constant.DatePattern;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 通用工具类
 *
 * @author shihaoran
 * @date 2019/11/15
 */
public class CommonUtils {

    /**
     * 时间转字符串
     *
     * @param date    时间
     * @param pattern 格式
     * @return 时间转为的字符串
     */
    public static String dateToString(Date date, String pattern) {

        SimpleDateFormat format0 = new SimpleDateFormat(pattern);
        String time = format0.format(date.getTime());
        return time;
    }

    /**
     * String 转 date
     *
     * @param time    时间的字符串
     * @param pattern 时间格式
     * @return {@link Date}
     */
    public static Date stringToDate(String time, String pattern) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(time, pos);
        return date;
    }

    /**
     * 判断当前时间是否在一个范围内
     *
     * @param nowDate   当前时间
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return true | false
     */
    public static boolean belongCalendar(Date nowDate, Date startDate, Date endDate) {

        boolean flag;

        Calendar date = Calendar.getInstance();
        date.setTime(nowDate);

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DAY_OF_MONTH, 1);

        flag = date.after(start) && date.before(end);
        return flag;
    }

    /**
     * 比较两个Double值是否相等
     *
     * @param v           魔法值
     * @param scaleFactor 待比较的值
     * @return true | false
     */
    public static boolean equals(double v, double scaleFactor) {

        boolean flag = false;
        if ((v - scaleFactor > -0.000001) && (v - scaleFactor) < 0.000001) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取当前系统时间
     *
     * @param pattern 时间格式
     * @return 当前系统的时间
     */
    public static String getCurrentTime(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatePattern.DATE_ALL);
        return simpleDateFormat.format(date);
    }
}
