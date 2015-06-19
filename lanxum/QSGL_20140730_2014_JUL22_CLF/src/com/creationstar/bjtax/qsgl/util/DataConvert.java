package com.creationstar.bjtax.qsgl.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;


/**
 *
 * <p>Title: ���ݵ�ת������</p>
 *
 * <p>Description: ����ͬ��ʽ����ͬ���͵����ݽ���ת��</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾</p>
 *
 * @author awanteeo zhao
 * @version 1.0
 */
public class DataConvert {

    /**
     * �ı��ַ�������ľ�̬�������������������������
     * @param iso8859 ISO8859��1������ַ���
     * @return GBK������ַ���
     */
    public static String toGBK(String iso8859) {
        if (iso8859 == null) {
            return null;
        }
        String oldStr = "";
        String newStr = "";
        try {
            oldStr = new String(iso8859.getBytes("ISO8859-1"), "GBK");
            newStr = new String(oldStr.getBytes("GBK"), "ISO8859-1");

            if (newStr.equals(iso8859)) {
                return oldStr;
            } else {
                return iso8859;
            }
        } catch (java.io.UnsupportedEncodingException ex) {
            return iso8859;
        }

    }

    /**
     * ��ʱ������ת��Ϊ "yyyyMMdd" ��ʽ���ַ���
     * @param date ����
     * @return ��ʽ������ַ���
     */
    public static String Date2String(java.util.Date date) {
        if (date == null) {
            return null;
        }

        // ���ڸ�ʽ���ַ�
        String dateFormat = "yyyyMMdd";

        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }


    /**
     * ��ʱ������ת��Ϊ "yyyyMMdd" ��ʽ���ַ���
     * @param date ����
     * @return ��ʽ������ַ���
     */
    public static String TimeStamp2String(Timestamp time) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // ���ڸ�ʽ���ַ�
        String dateFormat = "yyyyMMdd";

        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }
    
    /**
     * ��ʱ������ת��Ϊ "yyyyMMdd" ��ʽ���ַ���
     * @param date ����
     * @return ��ʽ������ַ���
     */
    public static String TS2String(Timestamp time) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // ���ڸ�ʽ���ַ�
        String dateFormat = "yyyy-MM-dd";

        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }

    /**
     * ��ʱ������ת��Ϊ ָ����ʽ���ַ���
     * @param date ����
     * @return ��ʽ������ַ���
     */
    public static String TimeStamp2String(Timestamp time, String dateFormat) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }


    /**
     * ��ʱ������ת��Ϊ "yyyy �� MM �� dd ��" ��ʽ���ַ���
     * @param date ����
     * @return ��ʽ������ַ���
     */
    public static String TS2JksDate(Timestamp time) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // ���ڸ�ʽ���ַ�
        String dateFormat = "yyyy �� MM �� dd ��";

        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }

    /**
     * ��"yyyy-mm-dd" ��ʽ���ַ���ת��Ϊʱ������
     * @param date String
     * @param format String
     * @return java.sql.Date
     */
    public static java.util.Date String2Date(String date, String format) throws
            Exception {
        if (date == null) {
            return null;
        }

        // ���ڸ�ʽ���ַ�
        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);

        return sdf.parse(date);
    }

    /**
     * ��"yyyy-mm-dd" ��ʽ���ַ���ת��Ϊʱ������
     * @param date String
     * @param format String
     * @return java.sql.Date
     */
    public static java.sql.Date String2SqlDate(String date) throws Exception {
        if (date == null) {
            return null;
        }

        // ���ڸ�ʽ���ַ�
        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");

        return utilDateToSqlDate(sdf.parse(date));
    }

    /**
     * ��"yyyyMMdd" ��ʽ���ַ���ת��ΪTimeStamp����
     * @param date �����ַ���
     * @return     TimeStamp���ͱ���
     * @throws Exception
     */
    public static java.sql.Timestamp String2Timestamp(String date) {
        java.sql.Timestamp timeStamp = null;
        try {
            String format = "yyyyMMdd";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    format);
            timeStamp = new Timestamp(sdf.parse(date).getTime());
            //timeStamp = java.sql.Timestamp.valueOf(date + " 00:00:00.000");
        } catch (Exception ex) {
            timeStamp = null;
        }

        // ���ڸ�ʽ���ַ�
        return timeStamp;
    }

    /**
     * ������õ����ַ�����ʽ��moneyת��ΪBigDecimal����
     * @param inputMoney ����Ľ���ַ���
     * @return     BigDecimal���ͱ���
     * @throws Exception
     */
    public static java.math.BigDecimal String2BigDecimal(String inputMoney) {
        java.math.BigDecimal bigdecimal;
        try {
            NumberFormat formatter = DecimalFormat.getNumberInstance();
//            formatter.setMaximumFractionDigits(i);
//            formatter.setMinimumFractionDigits(i);
            bigdecimal = new java.math.BigDecimal(formatter.parse(inputMoney).
                                                  doubleValue());

        }

        catch (Exception ex) {
            bigdecimal = new java.math.BigDecimal("0.00");
        }
        return bigdecimal;
    }

    /**
     *����ͨ��������ת����sql��������
     * @param date
     * @return
     */

    public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        if (date == null) {
            return null;
        }
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String s = format.format(date);
        //return java.sql.Date.valueOf(s);
        return new java.sql.Date(date.getTime());
    }

    /**
     * ��˫������ֵת����ָ��С��λ����������
     * @param d double ˫������ֵ
     * @param i int    ����С��λ��
     * @return String
     */

    public static String double2Currency(double d, int i) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
        formatter.setMaximumFractionDigits(i);
        formatter.setMinimumFractionDigits(i);
        String s = formatter.format(d);
        return s;
    }

    /**
     * ��˫������ֵת����ָ��С��λ���ַ�������
     * @param d double ˫������ֵ
     * @param i int    ����С��λ��
     * @return String
     */

    public static String double2String(double d, int i) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(i);
        formatter.setMinimumFractionDigits(i);
        String s = formatter.format(d);
        return s;
    }


    /**
     *��BigDecimal��ֵת����ָ��С��λ���ַ�������
     * @param BigDecimal
     * @param int    ����С��λ��
     * @return  String
     */
    public static String BigDecimal2String(BigDecimal big, int i) {
        if (big == null) {
            return "";
        }
        double d = BigDecimal2double(big);
        String s = double2String(d, i);
        return s;
    }


    /**
     *��BigDecimal��ֵת����ָ��С��λ���ַ�������
     * @param BigDecimal
     * @param int    ����С��λ��
     * @param format �Ƿ��и�ʽ
     * @return  String
     */
    public static String BigDecimal2String(BigDecimal big, int i,
                                           boolean format) {
        if (big == null) {
            return "";
        }
        if (format) {
            return BigDecimal2String(big, i);
        } else {
            double val = big.doubleValue();
            DecimalFormat df = new DecimalFormat();
            df.setGroupingSize(0);
            df.setMaximumFractionDigits(i);
            df.setMinimumFractionDigits(i);
            return df.format(val);
        }
    }

    /**
     *��BigDecimal��ֵת����ָ��С��λ���ַ�������
     * @param BigDecimal
     * @param int    ����С��λ��
     * @return  String
     */
    public static String BigDecimal2String(BigDecimal big) {
        return BigDecimal2String(big, 2);
    }

    public static int BigDecimal2int(BigDecimal big) {
        int i = 0;
        if (big != null) {
            i = big.intValue();
        }
        return i;
    }

    public static double BigDecimal2double(BigDecimal big) {
        double d = 0;
        if (big != null) {
            d = big.doubleValue();
        }
        return d;
    }

    /**
     * ��ʧȥ "%" Լ���� double ת������"%"Լ��ʱ��Ӧ��double
     * @param d  ����
     * @return  ���
     */
    public static double PercentFormat(double d) {
        return d / 100.0;
    }

    /**
     * ��BigDecimal ת���� ��Ӧ�� double
     * ��double ���˺�ת����Ҫ���ʽ���ַ���
     * @param big  ����Ĵ���
     * @param i    С���������λ��
     * @param multi ������ ͨ��Ϊ100
     * @return    String ת�������
     */

    public static String FormatPercent(BigDecimal big, int i, double multi) {
        double d = BigDecimal2double(big) * multi;
        String s = double2String(d, i);
        return s;
    }

    /**
     * �����������������С�������λ
     * �������Ŀǰ���� �õ�
     * �ϸ�����int��: 2
     * ���� (int)   : 3
     * ��  �ϸ������double(0.2d)�� =  66.77
     * ����Ŵ�100�� ��������ʾ  66.67%
     * @param i1 ����
     * @param i2 ������
     * @return  ���ؽ��
     */
    public static double DoubleFormat(int i1, int i2) {
        double d = 10000 * (double) i1 / i2;
        long l = Math.round(d);
        d = (double) l / 100;
        return d;
    }

    /**
     * ��double ת���� ��Ӧ�� string
     * ��double ���˺�ת����Ҫ���ʽ���ַ���
     * @param big  ����Ĵ���
     * @param i    С���������λ��
     * @param multi ������ ͨ��Ϊ100
     * @return    String ת�������
     */

    public static String doubleFormatPercent(double big, int i, double multi) {
        double d = big * multi;
        String s = double2String(d, i);
        return s;
    }

    /**
     * �ַ����滻,ʵ������jdk1.4��String.replaceFirst()�Ĺ��ܡ�
     * @param s String ԭ�ȵ��ַ���
     * @param origin String Ҫ�ҵ��ַ���
     * @param replace String Ҫ�滻�ɵ��ַ���
     * @return String
     */
    public static String StringReplace(String s, String origin, String replace) {
        String newStr = "";
        int start = s.indexOf(origin);
        int len = origin.length();
        newStr = s.substring(0, start) + replace + s.substring(start + len);
        return newStr;
    }

    /**
     * ȫ������ת���ɰ�����֡�
     * @param s String ԭ�ȵ��ַ���
     * @param origin String Ҫ�ҵ��ַ���
     * @param replace String Ҫ�滻�ɵ��ַ���
     * @return String
     */
    public static String qjToBj(String origin) {
        String[] bj = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String qjS = "��������������������";
        StringBuffer newStr = new StringBuffer("");
        if (origin == null) {
            return "";
        }
        int len = origin.length();

        for (int i = 0; i < len; i++) {
            if (origin.charAt(i) == '��' || origin.charAt(i) == '\n') {
                newStr.append(' ');
                continue;
            }
            if (origin.charAt(i) == '��' || origin.charAt(i) == '��'
                || origin.charAt(i) == '��' || origin.charAt(i) == '��'
                || origin.charAt(i) == '"' || origin.charAt(i) == '"') {
                continue;
            }
            if (qjS.indexOf(origin.charAt(i)) != -1) {
                newStr.append("" + qjS.indexOf(origin.charAt(i)));
            } else {
                newStr.append(origin.charAt(i));
            }
        }
        return newStr.toString();
    }

    /**
     * �ṩ��ȷ��С��λ�������봦��
     * @param v ��Ҫ�������������
     * @param scale С���������λ
     * @return ���������Ľ��
     */

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * �ṩ��ȷ��С��λ�������봦��
     * @param v ��Ҫ�������������
     * @param scale С���������λ
     * @return ���������Ľ��
     */

    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal one = new BigDecimal("1");
        return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * ����λ����ȫ�ַ���
     * @param s String
     * @param fill String ��Ҫ�����ַ���
     * @param len int
     * @return String
     */
    public static String fillString(String s, String fill, int len) throws
            ApplicationException {
        if (s == null) {
            throw new ApplicationException("��ȫ�ַ�����ʱ�򣬴�����ַ�������Ϊ��ָ�룡");
        }
        if (s.length() == 0) {
            throw new ApplicationException("��ȫ�ַ�����ʱ�򣬴�����ַ�������Ϊ�յ��ַ�����");
        }
        int s_len = s.length();
        if (s_len > len) {
            throw new ApplicationException("������ַ������Ȳ�����");
        }
        //���������Ҫ�ӵ��ַ�����
        s_len = len - s_len;
        for (int i = 0; i < s_len; i++) {
            s = fill + s;
            if (s.length() > len) {
                break;
            }
        }
        Debug.out("������ַ�����ֵΪ��" + s);
        return s;
    }

    /**
     * ���ո�������ָ������ַ���
     * @param s String
     * @return String[]
     */
    public static String[] splitYh(String s) {
        String splitString = "--";
        String[] sz = new String[2];
        int len = s.length();
        int site = s.indexOf(splitString);
        int siteLen = splitString.length();
        //�ַ������� �������--110108983747585
        //ȡ����������
        sz[0] = s.substring(0, site);

        //ȡ�������ʺ�
        sz[1] = s.substring(site + siteLen, len);
        return sz;
    }

    public static void main(String[] args) {
        //String sz[] = splitYh("�������--110108983747585");
        //System.out.println("���ո�������ָ������ַ���   '" + sz[0]);
        int i = 0;
        String aa;
        aa = (i == 0 ? "0" : "1");

        //	String t="20061721";
        //   System.out.println("n="+t.substring(0,4) );
        //   System.out.println("y="+t.substring(4,6) );
        //   System.out.println("r="+t.substring(6) );

        //  System.out.println(DataConvert.BigDecimal2Str(new BigDecimal(500), 2));
        System.out.println(DataConvert.BigDecimal2String(new BigDecimal(
                5000000.01245), 2, false));
        System.out.println(qjToBj("\"������������������\n��,��;�� ������������������\""));
    }

}
