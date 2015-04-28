package com.ttsoft.bjtax.smsb.util.gzsxexcel;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.syax.frame.exception.BaseException;


/**
 * <p>Title: ˰����--���</p>
 * <p>Description: ���ڴ������ࡣ</p>
 * @author ������ - ������
 * @version 1.0
 */

public class DateTimeUtil
{
    //������ʱ���ʽJAVA
    public static final String SQL_DATEFORMAT_JAVA = "yyyy-MM-dd HH:mm:ss";
    public static final String SQL_DATEFORMAT_SQL = "yyyy-MM-dd HH24:mi:ss";
    public static final String JAVA_TIMESTAMPFORMAT = "yyyy-MM-dd HH:mm:ss";

    //�򻯵�ʱ���ʽJAVA
    public static final String JAVA_DATEFORMAT = "yyyyMMdd";

    /**
     * ��Ĭ�ϸ�ʽ(yyyy-MM-dd hh:mm:ss)��java.sql.Timestampת����String����
     * @param timestamp Date
     * @return String
     * @throws BaseException
     */
    public static String timestampToString(Date timestamp) throws Exception
    {
        return timestampToString(timestamp, JAVA_TIMESTAMPFORMAT);
    }

    /**
     * ��ָ����ʽ��java.sql.Timestampת����String����
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
            throw new Exception("��ʱ��ת�����ַ�����ʽʱ����");
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
     * ��Ĭ�ϸ�ʽ��String  ת����java.sql.Timestamp  ����
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
     * ��ָ����ʽ��String  ת����java.sql.Timestamp  ����
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
            throw new Exception("���ַ���ת����ʱ���ʽʱ����");
        }
    }

    /**
     * ������ת��Ϊ����sql
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
     * ����ת��
     * @param value String
     * @return String
     */
    public static String getStringToDateSQL(String value)
    {
        String sql = "to_date('" + value + "','" + SQL_DATEFORMAT_SQL + "')";
        return sql;
    }

    /**
     * ��ȡ��ǰϵͳ�����ַ���
     * @return String
     * @throws BaseException
     */
    public static String getCurrentDate() throws Exception
    {
        return timestampToString(new Timestamp(System.currentTimeMillis()),
                                 JAVA_DATEFORMAT);
    }


    /**
     * ��ȡ��ǰϵͳ�����ַ���
     * @return String
     * @throws BaseException
     */
    public static String getCurrentDateTime() throws Exception
    {
        return timestampToString(new Timestamp(System.currentTimeMillis()),
                                 JAVA_TIMESTAMPFORMAT);
    }

    /**
     * ��ȡ��ǰϵͳ�����ַ���
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
            System.out.println("��ȡ�ƶ�ʱ�����ȳ���!" + e.getMessage());
            return " ";
        }
    }

    /**
     * ��ȡ��ǰϵͳ�����ַ���
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
            System.out.println("��ȡ�ƶ�ʱ����¶ȳ���!" + e.getMessage());
            return " ";
        }
    }

    /**
     * ��ȡ��ǰϵͳ�����ַ���
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
            System.out.println("��ȡ�ƶ�ʱ������ڳ���!" + e.getMessage());
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
     * ��ȡ��ǰϵͳ�����ַ���
     * @return String
     * @throws BaseException
     */
    public static String getCurrentYear() throws Exception
    {
        return getCurrentDate().substring(0, 4);
    }
}

