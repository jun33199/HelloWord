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
 * @author ������
 *
 */
public class DateUtils {

    public static final String[] months = {"һ��", "����", "����", "����", "����", "����",
                                          "����", "����", "����", "ʮ��", "ʮһ��", "ʮ����", };

    public static final String[] quarters = {"һ����", "������", "������", "�ļ���"};

    public DateUtils() {
    }

    /**
     * ��ȡ�����ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� yyyyMMdd
     *      ���У�
     *          yyyy   ��ʾ4λ�ꡣ
     *          MM     ��ʾ2λ�¡�
     *          dd     ��ʾ2λ�ա�
     * </pre>
     *
     * @return String "yyyyMMdd"��ʽ�������ַ�����
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(new Date());
    }

    /**
     * ��ȡ��ǰ����ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� yyyy
     *      ���У�
     *          yyyy   ��ʾ4λ�ꡣ
     * </pre>
     *
     * @return String "yyyy"��ʽ�ĵ�ǰ����ַ�����
     */
    public static String getNowYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        return formatter.format(new Date());
    }

    /**
     * ��ȡ��ǰ�¶��ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� MM
     *      ���У�
     *          MM   ��ʾ4λ�ꡣ
     * </pre>
     *
     * @return String "yyyy"��ʽ�ĵ�ǰ�¶��ַ�����
     */
    public static String getNowMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");

        return formatter.format(new Date());
    }

    /**
     * ��ȡ��ǰ�¶��ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� dd
     *      ���У�
     *          dd   ��ʾ4λ�ꡣ
     * </pre>
     *
     * @return String "yyyy"��ʽ�ĵ�ǰ�¶��ַ�����
     */
    public static String getNowDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");

        return formatter.format(new Date());
    }

    /**
     * ��ȡ�����ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� yyyyMMdd
     *      ���У�
     *          yyyy   ��ʾ4λ�ꡣ
     *          MM     ��ʾ2λ�¡�
     *          dd     ��ʾ2λ�ա�
     * </pre>
     *
     * @param date
     *            ��Ҫת�������ڡ�
     * @return String "yyyyMMdd"��ʽ�������ַ�����
     */
    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(date);
    }

    /**
     * ��ȡ�����ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� yyyyMMdd
     *      ���У�
     *          yyyy   ��ʾ4λ�ꡣ
     *          MM     ��ʾ2λ�¡�
     *          dd     ��ʾ2λ�ա�
     * </pre>
     *
     * @param date
     *            ��Ҫת�������ڡ�
     * @return String "yyyyMMdd"��ʽ�������ַ�����
     */
    /**
     * ��ָ���������ַ���ת��Ϊ���ڶ���
     *
     * @param dateStr
     *            �����ַ���
     * @return java.util.Date
     * @roseuid 3F39FE450385
     */
    public static Date getDate(String dateStr) {
        if (TypeChecker.isDate(dateStr)) { // ������
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            try {
                java.util.Date date = df.parse(dateStr);
                return date;
            } catch (Exception ex) {
                ex.printStackTrace();
            } // end try - catch
        } else if (TypeChecker.isDatetime(dateStr)) { // ����ʱ����
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
      * ��ϸ��ƣ�
      * 1.ָ�����ڼ�30��
      */
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
     GregorianCalendar gc = ( GregorianCalendar ) Calendar.getInstance();
     gc.setTime( new Date() );
     gc.add( Calendar.DATE, 30 );
     
     
     return df.format(gc.getTime());
    }

    /**
     * ��ȡ�����ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� yyyy-MM-dd
     *      ���У�
     *          yyyy   ��ʾ4λ�ꡣ
     *          MM     ��ʾ2λ�¡�
     *          dd     ��ʾ2λ�ա�
     * </pre>
     *
     * @return String "yyyy-MM-dd"��ʽ�������ַ�����
     */
    public static String getHyphenDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(new Date());
    }

    /**
     * ��ȡ�����ַ�����
     *
     * <pre>
     *      �����ַ�����ʽ�� yyyy-MM-dd
     *      ���У�
     *          yyyy   ��ʾ4λ�ꡣ
     *          MM     ��ʾ2λ�¡�
     *          dd     ��ʾ2λ�ա�
     * </pre>
     *
     * @param date
     *            ��Ҫת�������ڡ�
     * @return String "yyyy-MM-dd"��ʽ�������ַ�����
     */
    public static String getHyphenDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(date);
    }

    /**
     * ��"yyyyMMdd"��ʽ�������ַ���ת��Ϊ���ڶ���
     *
     * @param source
     *            �����ַ�����
     * @return Date ���ڶ���
     */
    public static Date parsePlainDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.parse(source, new ParsePosition(0));
    }

    /**
     * ����yyyy-MM-dd����ʽ�������ַ���ת��Ϊ���ڶ���
     *
     * @param source
     *            �����ַ�����
     * @return Date ���ڶ���
     */
    public static Date parseHyphenDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse(source, new ParsePosition(0));
    }

    /**
     * ��ָ����ʽ�������ַ���ת��Ϊ���ڶ���
     *
     * @param source
     *            �����ַ�����
     * @param pattern
     *            ģʽ��
     * @return Date ���ڶ���
     */
    public static Date parseDate(String source, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.parse(source, new ParsePosition(0));
    }

    /**
     * ����yyyy-MM-dd����ʽ�������ַ���ת��Ϊ��yyyyMMdd����ʽ�������ַ�����
     *
     * @param source
     *            �����ַ�����
     * @return String "yyyymmdd"��ʽ�������ַ�����
     */
    public static String toPlainDate(String source) {
        Date date = parseHyphenDate(source);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(date);
    }

    /**
     * ����yyyyMMdd����ʽ�������ַ���ת��Ϊ��yyyy-MM-dd����ʽ�������ַ�����
     *
     * @param source
     *            �����ַ�����
     * @return String "yyyy-MM-dd"��ʽ�������ַ�����
     */
    public static String toHyphenDate(String source) {
        Date date = parsePlainDate(source);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(date);
    }

    /**
     * ��ȡʱ����������ڶ���ת��Ϊʱ������͡�
     *
     * @param date
     *            ���ڶ���
     * @return Timestamp ʱ���
     */
    public static Timestamp getTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * ��ȡʱ���������ǰ����ת��Ϊʱ������͡�
     *
     * @return Timestamp ʱ���
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * ����yyyyMMdd����ʽ�������ַ���ת��ΪTimestamp���͵Ķ���
     *
     * @param source
     *            �����ַ���
     * @return Timestamp ʱ���
     */
    public static Timestamp parseTimestamp(String source) {
        Date date = parsePlainDate(source);

        return getTimestamp(date);
    }

    /**
     * �����Ч�����·��б�
     *
     * @return ArrayList ��Ч�����·��б�
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
     * ��������·��б�
     *
     * @return ArrayList �����·��б�
     */
    public static ArrayList getAllMonths() {
        ArrayList al = new ArrayList();

        for (int i = 0; i < 12; i++) {
            al.add(new NameValue(months[i], "" + (i + 1)));
        }

        return al;
    }

    /**
     * �����Ч���ȡ�
     *
     * @return ArrayList ��Ч�����б�
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
     * ������м��ȡ�
     *
     * @return ArrayList ���м����б�
     */
    public static ArrayList getAllQuar() {
        ArrayList al = new ArrayList();

        for (int i = 0; i < 4; i++) {
            al.add(new NameValue(quarters[i], "" + (i + 1)));
        }

        return al;
    }

    /**
     * ���������� <br>
     * Example:<br>
     * CcsDateUtil.getPeriodYear("20040229" , -1);<br>
     * CcsDateUtil.getPeriodYear("20040228" , -1);<br>
     * CcsDateUtil.getPeriodYear("20020230" , 2);<br>
     *
     * @param source
     *            ʱ�䴮
     * @param yearPeriod
     *            ������� -1����ʱ�����һ���,�Դ����ơ�
     * @return String ʱ�䡣
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
     * ��ҳ������ʾDate��ֵ��
     *
     * @param dateValue ��Ҫ��ʾ��dateֵ��
     * @param formatString ��Ҫ����ĸ�ʽ������java.text.SimpleDateFormat��Ҫ��
     * @return String ���ص��ַ�����
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
     * �����±����͵�˰����������
     * @param curDate ����
     * @return Map ʹ��Key �� SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� SKSSJSRQ �õ� ˰��������������Timestamp
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
