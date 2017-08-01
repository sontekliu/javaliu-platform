package com.javaliu.platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期或者时间的工具类
 */
public class DateUtils {

    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_HH_MM_SS = "HH:mm:ss";
    public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_YYYY_YEAR_MM_MONTH_DD_DAY = "yyyy年MM月dd日";

    /**
     * 将日期格式化为字符串，格式为:yyyy-MM-dd
     * @param date
     * @return
     * @author javaliu
     */
    public static String date2String(Date date){
        return date2String(date, PATTERN_YYYY_MM_DD);
    }

    /**
     * 按照指定的格式，格式化日期
     * @param date          需要格式化的日期
     * @param pattern       格式化之后样式
     * @return
     * @author javaliu
     */
    public static String date2String(Date date, String pattern){
        if(null == date){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     *  将字符创类型的时间转换成Date对象
     * @param date
     * @param pattern
     * @return
     * @author  javaliu
     */
    public static Date string2Date(String date, String pattern){
        Date formatDate = null;
        if(StringUtils.isBlank(date)){
            return formatDate;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("格式化失败");
        }
        return formatDate;
    }

    /**
     * 获取日期的年份
     * @param date  日期
     * @return
     * @author javaliu
     */
    public static String getYear(Date date){
        return date2String(date, "yyyy");
    }

    /**
     * 获取日期的月份
     * @param date   日期
     * @return
     * @author  javaliu
     */
    public static String getMonth(Date date){
        return date2String(date, "MM");
    }

    /**
     * 获取日期的日
     * @param date  日期
     * @return
     * @author  javaliu
     */
    public static String getDay(Date date){
        return date2String(date, "dd");
    }

    /**
     * 获取传入日期是周几
     * @param date  日期
     * @return
     * @author  javaliu
     */
    public static String getWeek(Date date){
        return date2String(date, "E");
    }

    /**
     *  比较两个日期的大小，如果date1>date2则返回1，如果date1<date2则返回-1，如果date1==date2则返回0
     * @param date1
     * @param date2
     * @return
     * @author  javaliu
     */
    public static int compareDate(Date date1, Date date2){
        if(null == date1){
            throw new RuntimeException("待比较的时间不能为空");
        }
        if(null == date2){
            throw new RuntimeException("待比较的时间不能为空");
        }
        if(date1.getTime() > date2.getTime()){
            return 1;
        }else if(date1.getTime() < date2.getTime()){
            return -1;
        }else {
            return 0;
        }
    }
}
