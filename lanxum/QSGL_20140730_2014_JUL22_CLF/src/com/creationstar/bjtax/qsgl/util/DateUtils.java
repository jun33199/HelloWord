package com.creationstar.bjtax.qsgl.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 哈正则
 *
 */
public class DateUtils {

    public static final String[] months = {"一月", "二月", "三月", "四月", "五月", "六月",
                                          "七月", "八月", "九月", "十月", "十一月", "十二月", };

    public static final String[] quarters = {"一季度", "二季度", "三季度", "四季度"};

    public DateUtils() {
    }

    /**
     * 获取日期字符串。
     *
     * <pre>
     *      日期字符串格式： yyyyMMdd
     *      其中：
     *          yyyy   表示4位年。
     *          MM     表示2位月。
     *          dd     表示2位日。
     * </pre>
     *
     * @return String "yyyyMMdd"格式的日期字符串。
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(new Date());
    }

    /**
     * 获取当前年度字符串。
     *
     * <pre>
     *      日期字符串格式： yyyy
     *      其中：
     *          yyyy   表示4位年。
     * </pre>
     *
     * @return String "yyyy"格式的当前年度字符串。
     */
    public static String getNowYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        return formatter.format(new Date());
    }

    /**
     * 获取当前月度字符串。
     *
     * <pre>
     *      日期字符串格式： MM
     *      其中：
     *          MM   表示4位年。
     * </pre>
     *
     * @return String "yyyy"格式的当前月度字符串。
     */
    public static String getNowMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");

        return formatter.format(new Date());
    }

    /**
     * 获取当前月度字符串。
     *
     * <pre>
     *      日期字符串格式： dd
     *      其中：
     *          dd   表示4位年。
     * </pre>
     *
     * @return String "yyyy"格式的当前月度字符串。
     */
    public static String getNowDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");

        return formatter.format(new Date());
    }

    /**
     * 获取日期字符串。
     *
     * <pre>
     *      日期字符串格式： yyyyMMdd
     *      其中：
     *          yyyy   表示4位年。
     *          MM     表示2位月。
     *          dd     表示2位日。
     * </pre>
     *
     * @param date
     *            需要转化的日期。
     * @return String "yyyyMMdd"格式的日期字符串。
     */
    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(date);
    }

    /**
     * 获取日期字符串。
     *
     * <pre>
     *      日期字符串格式： yyyyMMdd
     *      其中：
     *          yyyy   表示4位年。
     *          MM     表示2位月。
     *          dd     表示2位日。
     * </pre>
     *
     * @param date
     *            需要转化的日期。
     * @return String "yyyyMMdd"格式的日期字符串。
     */
    /**
     * 将指定的日期字符串转化为日期对象
     *
     * @param dateStr
     *            日期字符串
     * @return java.util.Date
     * @roseuid 3F39FE450385
     */
    public static Date getDate(String dateStr) {
        if (TypeChecker.isDate(dateStr)) { // 日期型
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            try {
                java.util.Date date = df.parse(dateStr);
                return date;
            } catch (Exception ex) {
                ex.printStackTrace();
            } // end try - catch
        } else if (TypeChecker.isDatetime(dateStr)) { // 日期时间型
            SimpleDateFormat df = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS");
            try {
                java.util.Date date = df.parse(dateStr);
                return date;
            } catch (Exception ex) {
                return null;
            } // end try - catch
        } // end if
        return null;
    }
    
    public static String getAfter30Days()
    {
     /**
      * 详细设计：
      * 1.指定日期加30天
      */
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
     GregorianCalendar gc = ( GregorianCalendar ) Calendar.getInstance();
     gc.setTime( new Date() );
     gc.add( Calendar.DATE, 30 );
     
     
     return df.format(gc.getTime());
    }

    /**
     * 获取日期字符串。
     *
     * <pre>
     *      日期字符串格式： yyyy-MM-dd
     *      其中：
     *          yyyy   表示4位年。
     *          MM     表示2位月。
     *          dd     表示2位日。
     * </pre>
     *
     * @return String "yyyy-MM-dd"格式的日期字符串。
     */
    public static String getHyphenDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(new Date());
    }

    /**
     * 获取日期字符串。
     *
     * <pre>
     *      日期字符串格式： yyyy-MM-dd
     *      其中：
     *          yyyy   表示4位年。
     *          MM     表示2位月。
     *          dd     表示2位日。
     * </pre>
     *
     * @param date
     *            需要转化的日期。
     * @return String "yyyy-MM-dd"格式的日期字符串。
     */
    public static String getHyphenDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(date);
    }

    /**
     * 将"yyyyMMdd"格式的日期字符串转换为日期对象。
     *
     * @param source
     *            日期字符串。
     * @return Date 日期对象。
     */
    public static Date parsePlainDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.parse(source, new ParsePosition(0));
    }

    /**
     * 将“yyyy-MM-dd”格式的日期字符串转换为日期对象。
     *
     * @param source
     *            日期字符串。
     * @return Date 日期对象。
     */
    public static Date parseHyphenDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse(source, new ParsePosition(0));
    }

    /**
     * 将指定格式的日期字符串转换为日期对象。
     *
     * @param source
     *            日期字符串。
     * @param pattern
     *            模式。
     * @return Date 日期对象。
     */
    public static Date parseDate(String source, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.parse(source, new ParsePosition(0));
    }

    /**
     * 将“yyyy-MM-dd”格式的日期字符串转换为“yyyyMMdd”格式的日期字符串。
     *
     * @param source
     *            日期字符串。
     * @return String "yyyymmdd"格式的日期字符串。
     */
    public static String toPlainDate(String source) {
        Date date = parseHyphenDate(source);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(date);
    }

    /**
     * 将“yyyyMMdd”格式的日期字符串转换为“yyyy-MM-dd”格式的日期字符串。
     *
     * @param source
     *            日期字符串。
     * @return String "yyyy-MM-dd"格式的日期字符串。
     */
    public static String toHyphenDate(String source) {
        Date date = parsePlainDate(source);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(date);
    }

    /**
     * 获取时间戳，将日期对象转换为时间戳类型。
     *
     * @param date
     *            日期对象
     * @return Timestamp 时间戳
     */
    public static Timestamp getTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 获取时间戳，将当前日期转换为时间戳类型。
     *
     * @return Timestamp 时间戳
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 将“yyyyMMdd”格式的日期字符串转换为Timestamp类型的对象。
     *
     * @param source
     *            日期字符串
     * @return Timestamp 时间戳
     */
    public static Timestamp parseTimestamp(String source) {
        Date date = parsePlainDate(source);

        return getTimestamp(date);
    }

    /**
     * 获得有效输入月份列表。
     *
     * @return ArrayList 有效输入月份列表。
     */
    public static ArrayList getValidMonths() {
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        ArrayList al = new ArrayList();

        for (int i = 0; i < curMonth; i++) {
            al.add(new NameValue(months[i], "" + (i + 1)));
        }

        return al;
    }

    /**
     * 获得所有月份列表。
     *
     * @return ArrayList 所有月份列表。
     */
    public static ArrayList getAllMonths() {
        ArrayList al = new ArrayList();

        for (int i = 0; i < 12; i++) {
            al.add(new NameValue(months[i], "" + (i + 1)));
        }

        return al;
    }

    /**
     * 获得有效季度。
     *
     * @return ArrayList 有效季度列表。
     */
    public static ArrayList getValidQuar() {
        int curQuar = Calendar.getInstance().get(Calendar.MONTH) / 3;
        ArrayList al = new ArrayList();

        for (int i = 0; i < curQuar; i++) {
            al.add(new NameValue(quarters[i], "" + (i + 1)));
        }

        return al;
    }

    /**
     * 获得所有季度。
     *
     * @return ArrayList 所有季度列表。
     */
    public static ArrayList getAllQuar() {
        ArrayList al = new ArrayList();

        for (int i = 0; i < 4; i++) {
            al.add(new NameValue(quarters[i], "" + (i + 1)));
        }

        return al;
    }

    /**
     * 获得年度周期 <br>
     * Example:<br>
     * CcsDateUtil.getPeriodYear("20040229" , -1);<br>
     * CcsDateUtil.getPeriodYear("20040228" , -1);<br>
     * CcsDateUtil.getPeriodYear("20020230" , 2);<br>
     *
     * @param source
     *            时间串
     * @param yearPeriod
     *            年度周期 -1代表本时间的上一年度,以次类推。
     * @return String 时间。
     */
    public static String getPeriodYear(String source, int yearPeriod) {
        int p = Integer.parseInt(source.substring(0, 4)) + yearPeriod;
        String newYear = String.valueOf(p)
                         + source.substring(4, source.length());
        Date date = parsePlainDate(newYear);
        String s = getDate(date);
        int ny = Integer.parseInt(s);
        int sy = Integer.parseInt(newYear);

        while (ny > sy) {
            sy--;
            ny = Integer.parseInt(getDate(parsePlainDate(String.valueOf(sy))));
        }

        return String.valueOf(sy);
    }

    public static void main(String [] args){
        Timestamp NEW_JZRQ = DateUtils.parseTimestamp("20050601");
        Timestamp tmp=DateUtils.parseTimestamp("20050531");
        System.out.println(tmp.before(NEW_JZRQ));
    }
    
    /**
     * 在页面上显示Date的值。
     *
     * @param dateValue 需要显示的date值。
     * @param formatString 需要输出的格式，符合java.text.SimpleDateFormat的要求。
     * @return String 返回的字符串。
     */
    public static String displayValue(Date dateValue, String formatString)
    {
        if (dateValue == null)
        {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(formatString);

        return sdf.format(dateValue).trim();
    }
    
    
    /**
     * 计算月报类型的税款所属日期
     * @param curDate 日期
     * @return Map 使用Key ＝ SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ SKSSJSRQ 得到 税款所属结束日期Timestamp
     */
    public static Map monthSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        calendar.add(calendar.MONTH, -1);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int month = calendar.get(calendar.MONTH);
        int maxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year,
            month, 1).getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            month, maxDay).getTime().getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");        
        calendar.add(calendar.MONTH, 1);
        Map retMap = new HashMap();
        retMap.put("SKSSKSRQ", df.format(skssksrqDate).toString());
        retMap.put("SKSSJSRQ", df.format(skssjsrqDate).toString());
        return retMap;
    }    
}
