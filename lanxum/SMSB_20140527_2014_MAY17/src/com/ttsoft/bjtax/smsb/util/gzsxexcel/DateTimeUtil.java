package com.ttsoft.bjtax.smsb.util.gzsxexcel;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.syax.frame.exception.BaseException;


/**
 * <p>Title: 税库行--框架</p>
 * <p>Description: 日期处理辅助类。</p>
 * @author 开发部 - 杨青松
 * @version 1.0
 */

public class DateTimeUtil
{
    //正常的时间格式JAVA
    public static final String SQL_DATEFORMAT_JAVA = "yyyy-MM-dd HH:mm:ss";
    public static final String SQL_DATEFORMAT_SQL = "yyyy-MM-dd HH24:mi:ss";
    public static final String JAVA_TIMESTAMPFORMAT = "yyyy-MM-dd HH:mm:ss";

    //简化的时间格式JAVA
    public static final String JAVA_DATEFORMAT = "yyyyMMdd";

    /**
     * 按默认格式(yyyy-MM-dd hh:mm:ss)将java.sql.Timestamp转换成String类型
     * @param timestamp Date
     * @return String
     * @throws BaseException
     */
    public static String timestampToString(Date timestamp) throws Exception
    {
        return timestampToString(timestamp, JAVA_TIMESTAMPFORMAT);
    }

    /**
     * 按指定格式将java.sql.Timestamp转换成String类型
     * @param timestamp Date
     * @param format String
     * @return String
     * @throws BaseException
     */
    public static String timestampToString(Date timestamp,
                                           String format) throws Exception
    {
        String timeStr = "";
        if (timestamp == null) {
            return timeStr;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            timeStr = dateFormat.format(timestamp);
            return timeStr;
        }
        catch (Exception e) {
            throw new Exception("将时间转换成字符串格式时错误！");
        }
    }

    public static String timestampToString(java.sql.Timestamp t, String format) throws
            Exception
    {
        if (t == null) {
            return "";
        }
        java.sql.Date d = new java.sql.Date(t.getTime());
        return timestampToString(d, format);
    }

    /**
     * 按默认格式将String  转换成java.sql.Timestamp  类型
     * @param timestampStr String
     * @return Timestamp
     * @throws BaseException
     */
    public static Timestamp stringToTimestamp(String timestampStr) throws
            Exception
    {
        return stringToTimestamp(timestampStr, JAVA_TIMESTAMPFORMAT);
    }

    /**
     * 按指定格式将String  转换成java.sql.Timestamp  类型
     * @param timestampStr String
     * @param format String
     * @return Timestamp
     * @throws BaseException
     */
    public static Timestamp stringToTimestamp(String timestampStr,
                                              String format) throws
            Exception
    {
        if (timestampStr == null || timestampStr.trim().equals("")) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(timestampStr);
            return new Timestamp(date.getTime());
        }
        catch (Exception e) {
            throw new Exception("将字符串转换成时间格式时错误！");
        }
    }

    /**
     * 把日期转换为日期sql
     * @param javaDate Date
     * @return String
     */
    public static String getDateToDateSQL(Date javaDate)
    {
        SimpleDateFormat sqlformat = new SimpleDateFormat(SQL_DATEFORMAT_JAVA);
        String sql = "to_date('" + sqlformat.format(javaDate) + "','" +
                     SQL_DATEFORMAT_SQL + "')";
        return sql;
    }

    /**
     * 日期转换
     * @param value String
     * @return String
     */
    public static String getStringToDateSQL(String value)
    {
        String sql = "to_date('" + value + "','" + SQL_DATEFORMAT_SQL + "')";
        return sql;
    }

    /**
     * 获取当前系统日期字符串
     * @return String
     * @throws BaseException
     */
    public static String getCurrentDate() throws Exception
    {
        return timestampToString(new Timestamp(System.currentTimeMillis()),
                                 JAVA_DATEFORMAT);
    }


    /**
     * 获取当前系统日期字符串
     * @return String
     * @throws BaseException
     */
    public static String getCurrentDateTime() throws Exception
    {
        return timestampToString(new Timestamp(System.currentTimeMillis()),
                                 JAVA_TIMESTAMPFORMAT);
    }

    /**
     * 获取当前系统日期字符串
     * @return String
     * @throws BaseException
     */
    public static String getTimeYear(Timestamp date) throws Exception
    {
        try {
            String year = timestampToString(date, JAVA_DATEFORMAT).
                          substring(0, 4);
            return year;
        }
        catch (Exception e) {
            System.out.println("获取制定时间的年度出错!" + e.getMessage());
            return " ";
        }
    }

    /**
     * 获取当前系统日期字符串
     * @return String
     * @throws BaseException
     */
    public static String getTimeMonth(Timestamp date) throws Exception
    {
        try {
            String year = timestampToString(date, JAVA_DATEFORMAT).
                          substring(4, 6);
            return year;
        }
        catch (Exception e) {
            System.out.println("获取制定时间的月度出错!" + e.getMessage());
            return " ";
        }
    }

    /**
     * 获取当前系统日期字符串
     * @return String
     * @throws BaseException
     */
    public static String getTimeDate(Timestamp date) throws Exception
    {
        try {
            String year = timestampToString(date, JAVA_DATEFORMAT).
                          substring(6, 8);
            return year;
        }
        catch (Exception e) {
            System.out.println("获取制定时间的日期出错!" + e.getMessage());
            return " ";
        }
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(getTimeYear(new Timestamp(System.currentTimeMillis())));
        System.out.println(getTimeMonth(new Timestamp(System.currentTimeMillis())));
        System.out.println(getTimeDate(new Timestamp(System.currentTimeMillis())));
    }

    /**
     * 获取当前系统日期字符串
     * @return String
     * @throws BaseException
     */
    public static String getCurrentYear() throws Exception
    {
        return getCurrentDate().substring(0, 4);
    }
}

