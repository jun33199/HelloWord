package com.ttsoft.bjtax.smsb.util.gzsxexcel;


/*
 * <p>Title: 北京地税核心征管系统－－税库行--框架</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */


/**
 * <p>Title: 税库行--框架</p>
 * <p>Description: 字符串类型检查包，均为静态方法。</p>
 * @author 开发部 - 杨青松
 * @version 1.0
 */
public class TypeChecker
{

    /**
     * 是否为Email，标志是中间必定包含符号。
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE47021E
     */
    public static boolean isEmail(String str)
    {
        if (str == null || str.trim() == "") {
            return false;
        }
        return true;
    }

    /**
     * 是否为整形数
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE470229
     */
    public static boolean isInteger(String str)
    {
        try {
            Integer.parseInt(str);
        }
        catch (Exception ex) {
            return false;
        } //end try catch
        return true;
    }

    /**
     * 判断是否是合法的长整型
     * @param str 输入的字符串
     * @return boolean
     * @roseuid 3F39FE470233
     */
    public static boolean isLong(String str)
    {
        try {
            Long.parseLong(str);
        }
        catch (Exception ex) {
            return false;
        } //end try catch
        return true;
    }

    /**
     * 判断是否为空
     * @param str 输入的字符串
     * @return boolean
     * @roseuid 3F39FE470246
     */
    public static boolean isEmpty(String str)
    {
        if (str == null || str.equals("") || str.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 是否为浮点数。
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE470250
     */
    public static boolean isFloat(String str)
    {
        try {
            float tmp = Float.parseFloat(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        } //end try - catch
    }

    /**
     * 是否为数字。
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE47025B
     */
    public static boolean isNumber(String str)
    {
        try {
            double tmp = Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        } //end try - catch
    }

    /**
     * 是否为日期，必须使用2002-12-12格式，月份和天一位时必须有前导零
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE470265
     */
    public static boolean isDate(String str)
    {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            java.util.Date date = df.parse(str);
            //是否变形
            String apple = df.format(date);
            if (!apple.equals(str)) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        } //end try - catch
        return true;
    }

    /**
     * 是否为合法的日期时间，格式为2002-02-02 15:00:03 .
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE470278
     */
    public static boolean isDatetime(String str)
    {
        if (str.length() < 19) {
            return false;
        }

        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");
        java.text.SimpleDateFormat df400 = new java.text.SimpleDateFormat(
                "yyyy-MM-dd-HH.mm.ss.SSS");
        //补上点
        if (!str.endsWith(".") && str.length() == 19) {
            str = str + ".";
        } //end if
        //补充长度
        while (str.length() < 23) {
            str = str + "0";
        } //end while
        try {
            java.util.Date date = df.parse(str);
            //是否变形
            String apple = df.format(date);
            if (!apple.equals(str)) {
                return false;
            }
        }
        catch (java.text.ParseException ex) {
            try {
                java.util.Date date2 = df400.parse(str);
                //是否变形
                String apple1 = df400.format(date2);
                if (!apple1.equals(str)) {
                    return false;
                }
            }
            catch (java.text.ParseException exa) {
                exa.printStackTrace();
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * 是否为合法时间。
     * @param str - 待检查字符串
     * @return boolean
     * @roseuid 3F39FE470282
     */
    public static boolean isTime(String str)
    {
        return true;
    }

    /**
     * 判断jsjdm是不是企业用户，"1" 表示企业用户，"2" 表示自然人用户
     * @param jsjdm - 计算机代码
     * @return string
     * add by zhangp 20031219
     */
    public static String isQyyh(String jsjdm)
    {
        if (jsjdm == null || jsjdm.length() != 8) {
            return "计算机代码长度必须为8位";
        }
        int lastLetter = jsjdm.charAt(7);
        if (lastLetter > 47 && lastLetter < 58) {
            return "1";
        }
        else if (lastLetter > 64 && lastLetter < 91) {
            return "2";
        }
        else {
            return "无效的计算机代码！";
        }
    }

    public static void main(String[] args)
    {
        //企业用户
        System.out.println("06010000 is: " + isQyyh("06010000"));
        //自然人用户
        System.out.println("0601000A is: " + isQyyh("0601000A"));
        //无效数据
        System.out.println("0601000a is: " + isQyyh("0601000a"));
        //长度不正确
        System.out.println("0601000 is: " + isQyyh("0601000"));
    }
}

