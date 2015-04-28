package com.ttsoft.bjtax.shenbao.util;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import com.ttsoft.common.util.Debug;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.bjtax.shenbao.proxy.*;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: һЩС�İ����������ܺ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */

public class TinyTools
{

	/**
     * �����ļ�������
     */
    public final static String WSSB_PROPERTIES_FILE_NAME = "ApplicationResources.properties";

    /**
     * ��ʱ������ת��Ϊ��ʽ�ַ���
     * @param date ����
     * @param format String
     * @return ��ʽ������ַ���
     */
    public static String Date2String(java.util.Date date, String format)
    {
        if(date == null)
            return null;

        // ��ʽ��Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);

        return sdf.format(date);
    }

    /**
     * ��ʱ������ת��Ϊ "yyyy-mm-dd" ��ʽ���ַ���
     * @param date ����
     * @return ��ʽ������ַ���
     */
    public static String Date2String(java.util.Date date)
    {
        return TinyTools.Date2String(date, "yyyy-MM-dd");
    }

    /**
     * ����ָ���ĸ�ʽ�����ַ�ʱ�䲢ת��Ϊ���ڸ�ʽʱ��
     * @param stringDate �ַ�ʱ��
     * @param pattern ʱ���ʽ
     * @return ת���������
     */
    public static Date stringToDate(String stringDate, String pattern)
    {

        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(stringDate);
        }
        catch(Exception ex)
        {
            //������쳣ת��Ϊ��ֵ����,���÷�������һ�����
            return null;
        }
    }

    /**
     * ����Ĭ�ϵĸ�ʽ(yyyy-MM-dd)�����ַ�ʱ�䲢ת��Ϊ���ڸ�ʽʱ��
     * @param stringDate �ַ���ʱ��
     * @return ת��������ڸ�ʽʱ��
     */
    public static Date stringToDate(String stringDate)
    {
        return stringToDate(stringDate, "yyyy-MM-dd");
    }

    //�����ʽ��Timestampת��Ϊ�����ʽ��Timestamp
    public static java.sql.Timestamp second2Day(java.util.Date date)
    {
        if (date==null)
            return null;
        java.sql.Timestamp tempStamp = null;

        try
        {
            String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
        }
        catch (Exception ex) {}

        return tempStamp;
    }


    /**
     * ��String���ͱ���ת��ΪASCII��
     * 1�����ASCIIΪ73[I]��79[O]������
     * 2��ֻת��'0-9'��'A-Z'(��'I'��'O')��ASCII�룬��Ӧ��ASCII��Ϊ'48-57'��'65-90'(��'73'��'79')
     * @param transParam ��ת���ı�
     * @return ת�����ASCII��
     */
    public static String stringToASCII(String transParam) throws BaseException {
      //������Ҫ��ֱ�ӷ���null
      if(transParam == null || transParam.length() == 0) {
        return null;
      }

      //������ת���Ĳ���
      transParam = formatTransParam(transParam);

      char[] transChars = transParam.toCharArray();
      String ascii = "";

      //�ַ�ת��Ϊ���֣���ƴ��ΪASCII��
      int charASCII = -1;
      for (int i = 0; i < transChars.length; i++) {
        charASCII = (int) transChars[i];

        //���Ϊ73(I)��79(O)������
        if(charASCII == 73 || charASCII == 79){
          charASCII++;
        }
        ascii += charASCII;
      }

      return ascii;
    }

    /**
     * ���ݹ����ʽ������������
     * 1�� �������ZZZ�����׳��쳣����ˮ���Ѿ���������Χ��ZZZ��
     * 2�� �������9����������10
     * 3�� �������999����������A
     * 4�� �������Z����������A0
     * 5�� �������Z99����������AA0
     * 6�� �������ZZ9����������AAA
     * 7�� �����0-999��Χ�ڣ�ĩβ����9��������
     * 8�� �����A-ZZZ��Χ�ڣ�ĩβ����9����ĩβת��ΪA����ǰλ���ΪZ��9�����չ���5��6
     * 9'�� �����A-ZZZ��Χ�ڣ�ĩβ����Z����ĩβת��Ϊ0����ǰλ���ΪZ��9�����չ���5��6
     * @param ascii
     * @return
     * @exception BaseException
     */
    private static String formatTransParam(String transParam) throws BaseException {

      //�������ZZZ���״�
      if(transParam.length() == 3 && transParam.substring(0, 2).equals("ZZ")) {
          //���һλ�����ӦASCII��
          int lastASCII = (int) transParam.charAt(2);
          //����90(Z),�״�
          if(lastASCII > 90) {
              throw new ApplicationException("��ˮ���Ѿ���������Χ��ZZZ");
          }
      }

      //--�������9
      if(transParam.equals(":")) {
        return "10";
      }
      //--�������999
      if(transParam.equals("99:")) {
        return "A";
      }
      //--�������Z
      if(transParam.equals("[")) {
        return "A0";
      }

      //--�������Z99
      if(transParam.equals("Z9:")) {
        return "AA0";
      }
      //--�������ZZ9
      if(transParam.equals("ZZ:")) {
        return "AAA";
      }

      //--�����Χ��0-999
      try{
        if(transParam.charAt(transParam.length()-1) == ':') {
          int transNum =  new Integer(transParam.substring(transParam.length()-2)).intValue() + 1;
          return transNum+"0";
        }
      } catch(NumberFormatException e) {
        //˵���������֣�����ִ��
      }

      //--�����Χ��A-ZZZ
      String frontBit = transParam;
      String lastBit = "";
      while(frontBit.charAt(frontBit.length()-1) == ':'
            || frontBit.charAt(frontBit.length()-1) == '[') {
        //ĩβ����9
        if(frontBit.charAt(frontBit.length()-1) == ':') {
          //��ǰλ����Ϊ0
          lastBit += "0";
        }
        //ĩβ����Z
        else if(frontBit.charAt(frontBit.length()-1) == '[') {
          //��ǰλ����ΪA
          lastBit += "A";
        }
        frontBit = frontBit.substring(0, frontBit.length()-1);

        //ǰλ�������������ʽ
        char[] transChars = frontBit.toCharArray();
        String ascii = "";

        //�ַ�ת��Ϊ���֣���ƴ��ΪASCII��
        int charASCII = -1;
        for (int i = 0; i < transChars.length; i++) {
          charASCII = (int) transChars[i];

          //���Ϊ73(I)��79(O)������
          if(charASCII == 73 || charASCII == 79){
            charASCII++;
          }
          ascii += charASCII;
        }

        frontBit = asciiToString(new Integer(ascii).intValue() + 1);
        //�ݹ�
        frontBit = formatTransParam(frontBit);
      }
      transParam = frontBit + lastBit;

      return transParam;
    }

    /**
     * ��ASCII��ת��ΪString[ASCII��0(48),�ض�Ϊż��]
     * @param int ASCII����
     * @return String ASCII��Ӧ���ַ���
     */
    public static String asciiToString(int ascii) {

      String asciiStr = new Integer(ascii).toString();
      String transParam = "";
      //����ת��
      while(asciiStr.length() > 0) {
        transParam += (char) new Integer(asciiStr.substring(0, 2)).intValue();

        asciiStr = asciiStr.substring(2);
      }

      return transParam;
    }

    /**
     * ��ʽ�����Ϊ"000"��ʽ
     * @param xh �ɿ�ƾ֤��
     * @return String ��ʽ����Ľɿ�ƾ֤��
     */
    public static String xhFormat(String xh) {
        if (xh.length() == 3)
        {
            return xh;
        }
        else if (xh.length() == 1)
        {
            return "00" + xh;
        }
        else if (xh.length() == 2)
        {
            return "0" + xh;
        }

        //Ϊ���ַ���������"000"
        return "000";

    }

    /**
     * ��̬��������ԭ�ַ�����sourceString���Էָ��־��divideFlag���ָ�����طָ���
     * �ַ�������,ȥ���ո�����ָ��־�����ڣ���Żص������н�����һ��Ԫ�أ���Ԫ��Ϊԭ�ַ�����
     * sourceString��
     * @param source ԭ�ַ���
     * @param divideFlag �ָ��־
     * @return String[] ���طָ����ַ�������
     * @roseuid 3F3A00B603AB
     */
    public static String[] divideString(String source, String divideFlag) {
      StringTokenizer st = new StringTokenizer(source, divideFlag);
//�����鳤��
      int count = st.countTokens();
      String apple[] = new String[count];
//�������
      for (int ii = 0; ii < count; ii++) {
        apple[ii] = st.nextToken().trim();
      } //end for
      return apple;
    }

    /**
     * ����properties�����ƣ��������ļ��ж�ȡ��properties��Ӧ��ֵ��
     * @param propertyName properties������
     * @return java.lang.String
     */
    public static String getProperty(String propertyName)
    {
        String value = null;
        try
        {
            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_GETPROPERTY);
            vo.setData(propertyName);

            Object reObject = ShenbaoProxy.getInstance().process(vo);
            value = (String)reObject;
        }
        catch (BaseException ex)
        {
            ex.printStackTrace(System.out);
        }
        if(value == null)
            value = "";
        return value.trim();
    }

    /**
     * ����properties�����ƣ��������ļ��ж�ȡ��properties��Ӧ��ֵ��
     * @param propertyName properties������
     * @return java.lang.String
     */
    public static String getPropertyWithFile(String propertyName)
    {
        String value = toGBK(PropertiesUtil.getProperty(WSSB_PROPERTIES_FILE_NAME,
                                                  propertyName));
        if(value == null)
            value = "";
        return value.trim();
    }

    public static String toGBK(String iso8859)
    {
        if (iso8859==null)
            return null;

        try
        {
            return new String(iso8859.getBytes("ISO8859-1"), "GBK");
        }
        catch (java.io.UnsupportedEncodingException ex)
        {
            return iso8859;
        }
    }

   public static void main(String[] args){

   }
   
   
   /**
    * �õ�ʱ�����
    * @param swjgzzjgdm ˰�������֯�ṹ����
    * @return String ���
    */
   public static String getXh(String swjgzzjgdm) {
     return swjgzzjgdm
         + TinyTools.Date2String(new Date(), "yyyyMMddHHmmssSSS");
   }
   
   /**
    * ���� ���з����Ʊ�����س���
    * @param str
    * @return
    */
   public static String replaceBlank(String str) {

       String dest = "";

       if (str!=null) {

           Pattern p = Pattern.compile("\t|\r|\n");
           Matcher m = p.matcher(str);
           dest = m.replaceAll("");
       }
       return dest;
   }

}