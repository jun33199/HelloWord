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
 * <p>Title: 数据的转换工具</p>
 *
 * <p>Description: 将不同格式、不同类型的数据进行转换</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 *
 * @author awanteeo zhao
 * @version 1.0
 */
public class DataConvert {

    /**
     * 改变字符串编码的静态方法，用来解决中文乱码问题
     * @param iso8859 ISO8859－1编码的字符串
     * @return GBK编码的字符串
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
     * 将时间类型转换为 "yyyyMMdd" 格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String Date2String(java.util.Date date) {
        if (date == null) {
            return null;
        }

        // 日期格式化字符
        String dateFormat = "yyyyMMdd";

        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }


    /**
     * 将时间类型转换为 "yyyyMMdd" 格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String TimeStamp2String(Timestamp time) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // 日期格式化字符
        String dateFormat = "yyyyMMdd";

        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }
    
    /**
     * 将时间类型转换为 "yyyyMMdd" 格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String TS2String(Timestamp time) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // 日期格式化字符
        String dateFormat = "yyyy-MM-dd";

        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }

    /**
     * 将时间类型转换为 指定格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String TimeStamp2String(Timestamp time, String dateFormat) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }


    /**
     * 将时间类型转换为 "yyyy 年 MM 月 dd 日" 格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String TS2JksDate(Timestamp time) {
        if (time == null) {
            return null;
        }

        java.util.Date date = new java.util.Date(time.getTime());

        // 日期格式化字符
        String dateFormat = "yyyy 年 MM 月 dd 日";

        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);

        return sdf.format(date);
    }

    /**
     * 将"yyyy-mm-dd" 格式的字符串转换为时间类型
     * @param date String
     * @param format String
     * @return java.sql.Date
     */
    public static java.util.Date String2Date(String date, String format) throws
            Exception {
        if (date == null) {
            return null;
        }

        // 日期格式化字符
        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);

        return sdf.parse(date);
    }

    /**
     * 将"yyyy-mm-dd" 格式的字符串转换为时间类型
     * @param date String
     * @param format String
     * @return java.sql.Date
     */
    public static java.sql.Date String2SqlDate(String date) throws Exception {
        if (date == null) {
            return null;
        }

        // 日期格式化字符
        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");

        return utilDateToSqlDate(sdf.parse(date));
    }

    /**
     * 将"yyyyMMdd" 格式的字符串转换为TimeStamp类型
     * @param date 日期字符串
     * @return     TimeStamp类型变量
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

        // 日期格式化字符
        return timeStamp;
    }

    /**
     * 将界面得到的字符串格式的money转换为BigDecimal类型
     * @param inputMoney 输入的金额字符串
     * @return     BigDecimal类型变量
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
     *将普通日期类型转换成sql日期类型
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
     * 把双精度数值转换成指定小数位数货币类型
     * @param d double 双精度数值
     * @param i int    保留小数位数
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
     * 把双精度数值转换成指定小数位数字符串类型
     * @param d double 双精度数值
     * @param i int    保留小数位数
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
     *把BigDecimal数值转换成指定小数位数字符串类型
     * @param BigDecimal
     * @param int    保留小数位数
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
     *把BigDecimal数值转换成指定小数位数字符串类型
     * @param BigDecimal
     * @param int    保留小数位数
     * @param format 是否有格式
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
     *把BigDecimal数值转换成指定小数位数字符串类型
     * @param BigDecimal
     * @param int    保留小数位数
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
     * 将失去 "%" 约束的 double 转换成有"%"约束时相应的double
     * @param d  输入
     * @return  输出
     */
    public static double PercentFormat(double d) {
        return d / 100.0;
    }

    /**
     * 将BigDecimal 转换成 对应的 double
     * 将double 倍乘后转换成要求格式的字符串
     * @param big  输入的大数
     * @param i    小数点后限制位数
     * @param multi 倍乘数 通常为100
     * @return    String 转换后输出
     */

    public static String FormatPercent(BigDecimal big, int i, double multi) {
        double d = BigDecimal2double(big) * multi;
        String s = double2String(d, i);
        return s;
    }

    /**
     * 将两个整数相除后保留小数点后两位
     * 这个方法目前用于 得到
     * 合格数（int）: 2
     * 总数 (int)   : 3
     * 求  合格比例（double(0.2d)） =  66.77
     * 结果放大100倍 是用于显示  66.67%
     * @param i1 除数
     * @param i2 被除数
     * @return  返回结果
     */
    public static double DoubleFormat(int i1, int i2) {
        double d = 10000 * (double) i1 / i2;
        long l = Math.round(d);
        d = (double) l / 100;
        return d;
    }

    /**
     * 将double 转换成 对应的 string
     * 将double 倍乘后转换成要求格式的字符串
     * @param big  输入的大数
     * @param i    小数点后限制位数
     * @param multi 倍乘数 通常为100
     * @return    String 转换后输出
     */

    public static String doubleFormatPercent(double big, int i, double multi) {
        double d = big * multi;
        String s = double2String(d, i);
        return s;
    }

    /**
     * 字符串替换,实现类似jdk1.4中String.replaceFirst()的功能。
     * @param s String 原先的字符串
     * @param origin String 要找的字符串
     * @param replace String 要替换成的字符串
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
     * 全角数字转换成半角数字。
     * @param s String 原先的字符串
     * @param origin String 要找的字符串
     * @param replace String 要替换成的字符串
     * @return String
     */
    public static String qjToBj(String origin) {
        String[] bj = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String qjS = "０１２３４５６７８９";
        StringBuffer newStr = new StringBuffer("");
        if (origin == null) {
            return "";
        }
        int len = origin.length();

        for (int i = 0; i < len; i++) {
            if (origin.charAt(i) == '　' || origin.charAt(i) == '\n') {
                newStr.append(' ');
                continue;
            }
            if (origin.charAt(i) == '“' || origin.charAt(i) == '”'
                || origin.charAt(i) == '＂' || origin.charAt(i) == '＂'
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
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
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
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
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
     * 根据位数补全字符串
     * @param s String
     * @param fill String 需要填充的字符串
     * @param len int
     * @return String
     */
    public static String fillString(String s, String fill, int len) throws
            ApplicationException {
        if (s == null) {
            throw new ApplicationException("补全字符串的时候，传入的字符串不能为空指针！");
        }
        if (s.length() == 0) {
            throw new ApplicationException("补全字符串的时候，传入的字符串不能为空的字符串！");
        }
        int s_len = s.length();
        if (s_len > len) {
            throw new ApplicationException("传入的字符串长度不合理！");
        }
        //计算出来需要加的字符个数
        s_len = len - s_len;
        for (int i = 0; i < s_len; i++) {
            s = fill + s;
            if (s.length() > len) {
                break;
            }
        }
        Debug.out("填充后的字符串的值为：" + s);
        return s;
    }

    /**
     * 按照给定规则分割银行字符串
     * @param s String
     * @return String[]
     */
    public static String[] splitYh(String s) {
        String splitString = "--";
        String[] sz = new String[2];
        int len = s.length();
        int site = s.indexOf(splitString);
        int siteLen = splitString.length();
        //字符串型如 光大银行--110108983747585
        //取出银行名称
        sz[0] = s.substring(0, site);

        //取出银行帐号
        sz[1] = s.substring(site + siteLen, len);
        return sz;
    }

    public static void main(String[] args) {
        //String sz[] = splitYh("光大银行--110108983747585");
        //System.out.println("按照给定规则分割银行字符串   '" + sz[0]);
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
        System.out.println(qjToBj("\"１１０１０５１９６\n６,１;２ ０　２０“”０１１\""));
    }

}
