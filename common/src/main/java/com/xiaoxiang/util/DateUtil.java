package com.xiaoxiang.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author xiang.xiaox
 */

public class DateUtil {
    /**
     * 默认的时区，一般是使用东八区。即比标准的格林威治时间快8个小时。
     */
    public static TimeZone TIME_ZONE = TimeZone.getDefault();

    /**
     * 增加或减少指定数量的日数。
     *
     * @param date   <code>java.util.Date</code>
     * @param amount 数量
     * @return 运算后的 <code>java.util.Date</code>
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 增加或减少指定数量的月份。
     *
     * @param date   <code>java.util.Date</code>
     * @param amount 数量
     * @return 运算后的 <code>java.util.Date</code>
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 增加或减少指定数量的年份。
     *
     * @param date   日期格式的字符串
     * @param amount 数量
     * @return 运算后的日期格式的字符串
     */
    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }


    /**
     * 增加不同类型
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            throw new NullPointerException("日期不能为空！");
        }
        Calendar cal = GregorianCalendar.getInstance(TIME_ZONE);
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    /**
     * 返回日期时间
     *
     * @param stringDate
     * @param parsePatterns
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String stringDate, String parsePatterns)
            throws ParseException {
        DateFormat df = new SimpleDateFormat(parsePatterns);
        if (stringDate == null)
            return null;
        return df.parse(stringDate);
    }

    /**
     * 返回日期字符串："yyyy-MM-dd" 格式。
     *
     * @param date
     * @param parsePatterns
     * @return
     */
    public static String getDateStr(Date date, String parsePatterns) {
        DateFormat df = new SimpleDateFormat(parsePatterns);
        if (date == null)
            return "";
        return df.format(date);
    }


    /**
     * 截取日期，只保留年、月、日部分。
     *
     * @param isoString 日期格式的字符串
     * @return 截取后的日期格式的字符串
     * @throws ParseException
     */
    public static Date trunc(Date isoString) throws ParseException {
        return stringToDate(getDateStr(isoString, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }
}
