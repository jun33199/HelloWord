package com.ttsoft.bjtax.shenbao.fangtu;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * $Id: DateHelper.java,v 1.3 2005/06/27 03:31:52 fsha Exp $
 */
public class DateHelper {

    public static final Date getDate(int year, int month, int day,
                                     int hour, int minute) {

        // returns a Date with the specified time elements

        Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
                day, hour, minute);

        return cal.getTime();

    } // getDay

    public static final Date getDate(int year, int month, int day) {

        // returns a Date with the specified time elements,
        // with the hour and minutes both set to 0 (midnight)

        Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
                day);

        return cal.getTime();

    } // getDay

    static public final Date addDays(Date target, int days) {

        // returns a Date that is the sum of the target Date
        // and the specified number of days;
        // to subtract days from the target Date, the days
        // argument should be negative

        long msPerDay = 1000 * 60 * 60 * 24;
        long msTarget = target.getTime();
        long msSum = msTarget + (msPerDay * days);
        Date result = new Date();
        result.setTime(msSum);
        return result;
    } // addDays


    static public int dayDiff(Date first, Date second) {

        // returns the difference, in days, between the first
        // and second Date arguments

        long msPerDay = 1000 * 60 * 60 * 24;
        long diff = (first.getTime() / msPerDay) - (second.getTime() / msPerDay);
        Long convertLong = new Long(diff);
        return convertLong.intValue();
    } // dayDiff


    static public int getYear(Date date) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    } // getYear

    static public int getMonth(Date date) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int calendarMonth = cal.get(Calendar.MONTH);
        return calendarMonthToInt(calendarMonth);
    } // getMonth

    static public int getDay(Date date) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    } // getDay

    static public int getHour(Date date) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    } // geHour

    static public int getMinute(Date date) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    } // geMinute

    private static int calendarMonthToInt(int calendarMonth) {

        if (calendarMonth == Calendar.JANUARY)
            return 1;
        else if (calendarMonth == Calendar.FEBRUARY)
            return 2;
        else if (calendarMonth == Calendar.MARCH)
            return 3;
        else if (calendarMonth == Calendar.APRIL)
            return 4;
        else if (calendarMonth == Calendar.MAY)
            return 5;
        else if (calendarMonth == Calendar.JUNE)
            return 6;
        else if (calendarMonth == Calendar.JULY)
            return 7;
        else if (calendarMonth == Calendar.AUGUST)
            return 8;
        else if (calendarMonth == Calendar.SEPTEMBER)
            return 9;
        else if (calendarMonth == Calendar.OCTOBER)
            return 10;
        else if (calendarMonth == Calendar.NOVEMBER)
            return 11;
        else if (calendarMonth == Calendar.DECEMBER)
            return 12;
        else
            return 1;

    } // calendarMonthToInt

    public static String format(Date date, String pattern) {

        // returns a String representation of the date argument,
        // formatted according to the pattern argument, which
        // has the same syntax as the argument of the SimpleDateFormat
        // class1E

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);

    } // format


    private static int intToCalendarMonth(int month) {

        if (month == 1)
            return Calendar.JANUARY;
        else if (month == 2)
            return Calendar.FEBRUARY;
        else if (month == 3)
            return Calendar.MARCH;
        else if (month == 4)
            return Calendar.APRIL;
        else if (month == 5)
            return Calendar.MAY;
        else if (month == 6)
            return Calendar.JUNE;
        else if (month == 7)
            return Calendar.JULY;
        else if (month == 8)
            return Calendar.AUGUST;
        else if (month == 9)
            return Calendar.SEPTEMBER;
        else if (month == 10)
            return Calendar.OCTOBER;
        else if (month == 11)
            return Calendar.NOVEMBER;
        else if (month == 12)
            return Calendar.DECEMBER;
        else
            return Calendar.JANUARY;

    } // intToCalendarMonth


    /**
     * 去掉时分秒
     * @return
     */
    public static Timestamp getOnlyDate(Timestamp ts) {
        java.util.Date date = new java.util.Date(ts.getTime());
        date = getDate(getYear(date), getMonth(date),getDay(date));
        return new Timestamp(date.getTime());
    }

    /**
     * 去掉日、时、分、秒
     * @return
     */
    public static Timestamp getOnlyYearMonth(Timestamp ts) {
        java.util.Date date = new java.util.Date(ts.getTime());
        date = getDate(getYear(date), getMonth(date), 1);
        return new Timestamp(date.getTime());
    }


    public static Timestamp toTimestamp(String str)
            throws ParseException {
        try {
            if (str == null || "".equals(str.trim()))
                return null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
            java.util.Date dd1 = df.parse(str);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd1);
            java.util.Date dt = cal.getTime();

            long lDtm = dt.getTime();
            return new java.sql.Timestamp(lDtm);
        } catch (ParseException e) {
            throw new ParseException("You should pass the String like this:2001-4-5 21:11:11", 1);
        }
    }
    public static Timestamp toTimestamp(String str, String format)
    throws ParseException {
    	try {
    		if (str == null || "".equals(str.trim()))
    			return null;
    		SimpleDateFormat df = new SimpleDateFormat(format);
    		java.util.Date dd1 = df.parse(str);
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(dd1);
    		java.util.Date dt = cal.getTime();
    		
    		long lDtm = dt.getTime();
    		return new java.sql.Timestamp(lDtm);
    	} catch (ParseException e) {
    		throw new ParseException("You should pass the String like this:2001-4-5 21:11:11", 1);
    	}
    }
    public static Timestamp parseDateToTimestamp(String str)
            throws ParseException {
        try {
            if (str == null || "".equals(str.trim()))
                return null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dd1 = df.parse(str);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd1);
            java.util.Date dt = cal.getTime();

            long lDtm = dt.getTime();
            return new java.sql.Timestamp(lDtm);
        } catch (ParseException e) {
            throw new ParseException("You should pass the String like this:2001-4-5", 1);
        }
    }

    /**
     * 将ts与minTs进行比较，如果ts比minTs小，则把 newTs 赋值给 ts
     * 这是针对sqlserver处理时间时，有一个最小的时间限制要求
     *
     * @param ts
     * @param minTs
     * @param newTs
     * @return 处理后 ts
     */
    public static Timestamp validateDate(Timestamp ts, Timestamp minTs, Timestamp newTs)
    {
        if (ts==null) return ts;

        if (ts.before( minTs))
        {
            ts = newTs;
        }
        return ts;
    }

    /**
     * 根据 年，月，日 取得一个简单的timestamp类型
     * @param year
     * @param month
     * @param day
     * @return 一个Timestamp类型的简单日期，包括年月日
     */
    public static Timestamp simpleDate(int year, int month, int day)
    {
        return new Timestamp(getDate(year, month, day).getTime());
    }


    /**
     * 返回当前日期，yyyyMMdd格式的日期
     * sysdate 有点 oracle sql 的意思 :)
     * @return
     */
    public static String sysdate() {
    	return format(new java.util.Date(System.currentTimeMillis()),"yyyyMMdd");
    }
    
    public static void main(String[] args) {
        // getOnlyDate() example. we expire this result as: 2003-05-04 00:00:00.0
        Timestamp ts = DateHelper.getOnlyDate(new Timestamp(System.currentTimeMillis()));
        //System.out.println(ts.toString());
    }
}
