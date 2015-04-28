package com.ttsoft.bjtax.smsb.util.gzsxexcel;


/*
 * <p>Title: ������˰��������ϵͳ����˰����--���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */


/**
 * <p>Title: ˰����--���</p>
 * <p>Description: �ַ������ͼ�������Ϊ��̬������</p>
 * @author ������ - ������
 * @version 1.0
 */
public class TypeChecker
{

    /**
     * �Ƿ�ΪEmail����־���м�ض��������š�
     * @param str - ������ַ���
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
     * �Ƿ�Ϊ������
     * @param str - ������ַ���
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
     * �ж��Ƿ��ǺϷ��ĳ�����
     * @param str ������ַ���
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
     * �ж��Ƿ�Ϊ��
     * @param str ������ַ���
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
     * �Ƿ�Ϊ��������
     * @param str - ������ַ���
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
     * �Ƿ�Ϊ���֡�
     * @param str - ������ַ���
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
     * �Ƿ�Ϊ���ڣ�����ʹ��2002-12-12��ʽ���·ݺ���һλʱ������ǰ����
     * @param str - ������ַ���
     * @return boolean
     * @roseuid 3F39FE470265
     */
    public static boolean isDate(String str)
    {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            java.util.Date date = df.parse(str);
            //�Ƿ����
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
     * �Ƿ�Ϊ�Ϸ�������ʱ�䣬��ʽΪ2002-02-02 15:00:03 .
     * @param str - ������ַ���
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
        //���ϵ�
        if (!str.endsWith(".") && str.length() == 19) {
            str = str + ".";
        } //end if
        //���䳤��
        while (str.length() < 23) {
            str = str + "0";
        } //end while
        try {
            java.util.Date date = df.parse(str);
            //�Ƿ����
            String apple = df.format(date);
            if (!apple.equals(str)) {
                return false;
            }
        }
        catch (java.text.ParseException ex) {
            try {
                java.util.Date date2 = df400.parse(str);
                //�Ƿ����
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
     * �Ƿ�Ϊ�Ϸ�ʱ�䡣
     * @param str - ������ַ���
     * @return boolean
     * @roseuid 3F39FE470282
     */
    public static boolean isTime(String str)
    {
        return true;
    }

    /**
     * �ж�jsjdm�ǲ�����ҵ�û���"1" ��ʾ��ҵ�û���"2" ��ʾ��Ȼ���û�
     * @param jsjdm - ���������
     * @return string
     * add by zhangp 20031219
     */
    public static String isQyyh(String jsjdm)
    {
        if (jsjdm == null || jsjdm.length() != 8) {
            return "��������볤�ȱ���Ϊ8λ";
        }
        int lastLetter = jsjdm.charAt(7);
        if (lastLetter > 47 && lastLetter < 58) {
            return "1";
        }
        else if (lastLetter > 64 && lastLetter < 91) {
            return "2";
        }
        else {
            return "��Ч�ļ�������룡";
        }
    }

    public static void main(String[] args)
    {
        //��ҵ�û�
        System.out.println("06010000 is: " + isQyyh("06010000"));
        //��Ȼ���û�
        System.out.println("0601000A is: " + isQyyh("0601000A"));
        //��Ч����
        System.out.println("0601000a is: " + isQyyh("0601000a"));
        //���Ȳ���ȷ
        System.out.println("0601000 is: " + isQyyh("0601000"));
    }
}

