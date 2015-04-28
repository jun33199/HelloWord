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
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 一些小的帮助方法的总和</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */

public class TinyTools
{

	/**
     * 配置文件的名称
     */
    public final static String WSSB_PROPERTIES_FILE_NAME = "ApplicationResources.properties";

    /**
     * 将时间类型转换为格式字符串
     * @param date 日期
     * @param format String
     * @return 格式化后的字符串
     */
    public static String Date2String(java.util.Date date, String format)
    {
        if(date == null)
            return null;

        // 格式化Class
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);

        return sdf.format(date);
    }

    /**
     * 将时间类型转换为 "yyyy-mm-dd" 格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String Date2String(java.util.Date date)
    {
        return TinyTools.Date2String(date, "yyyy-MM-dd");
    }

    /**
     * 按照指定的格式分析字符时间并转换为日期格式时间
     * @param stringDate 字符时间
     * @param pattern 时间格式
     * @return 转换后的日期
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
            //这里把异常转换为空值返回,调用方必须做一个检查
            return null;
        }
    }

    /**
     * 按照默认的格式(yyyy-MM-dd)分析字符时间并转换为日期格式时间
     * @param stringDate 字符串时间
     * @return 转换后的日期格式时间
     */
    public static Date stringToDate(String stringDate)
    {
        return stringToDate(stringDate, "yyyy-MM-dd");
    }

    //将秒格式的Timestamp转换为到天格式的Timestamp
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
     * 将String类型变量转换为ASCII码
     * 1、如果ASCII为73[I]、79[O]，跳过
     * 2、只转换'0-9'和'A-Z'(除'I'、'O')的ASCII码，对应的ASCII码为'48-57'和'65-90'(除'73'、'79')
     * @param transParam 待转换的变
     * @return 转换后的ASCII码
     */
    public static String stringToASCII(String transParam) throws BaseException {
      //不满足要求，直接返回null
      if(transParam == null || transParam.length() == 0) {
        return null;
      }

      //完善需转换的参数
      transParam = formatTransParam(transParam);

      char[] transChars = transParam.toCharArray();
      String ascii = "";

      //字符转换为数字，并拼接为ASCII码
      int charASCII = -1;
      for (int i = 0; i < transChars.length; i++) {
        charASCII = (int) transChars[i];

        //如果为73(I)、79(O)，自增
        if(charASCII == 73 || charASCII == 79){
          charASCII++;
        }
        ascii += charASCII;
      }

      return ascii;
    }

    /**
     * 根据规则格式化，规则如下
     * 1、 如果超过ZZZ——抛出异常“流水号已经超出允许范围－ZZZ”
     * 2、 如果超过9——上升至10
     * 3、 如果超过999——上升至A
     * 4、 如果超过Z——上升至A0
     * 5、 如果超过Z99——上升至AA0
     * 6、 如果超过ZZ9——上升至AAA
     * 7、 如果在0-999范围内，末尾超过9——自增
     * 8、 如果在A-ZZZ范围内，末尾超过9——末尾转换为A，其前位如果为Z或9，参照规则5和6
     * 9'、 如果在A-ZZZ范围内，末尾超过Z——末尾转换为0，其前位如果为Z或9，参照规则5和6
     * @param ascii
     * @return
     * @exception BaseException
     */
    private static String formatTransParam(String transParam) throws BaseException {

      //如果超出ZZZ，抛错
      if(transParam.length() == 3 && transParam.substring(0, 2).equals("ZZ")) {
          //最后一位编码对应ASCII码
          int lastASCII = (int) transParam.charAt(2);
          //超过90(Z),抛错
          if(lastASCII > 90) {
              throw new ApplicationException("流水号已经超出允许范围－ZZZ");
          }
      }

      //--如果超过9
      if(transParam.equals(":")) {
        return "10";
      }
      //--如果超过999
      if(transParam.equals("99:")) {
        return "A";
      }
      //--如果超过Z
      if(transParam.equals("[")) {
        return "A0";
      }

      //--如果超过Z99
      if(transParam.equals("Z9:")) {
        return "AA0";
      }
      //--如果超过ZZ9
      if(transParam.equals("ZZ:")) {
        return "AAA";
      }

      //--如果范围在0-999
      try{
        if(transParam.charAt(transParam.length()-1) == ':') {
          int transNum =  new Integer(transParam.substring(transParam.length()-2)).intValue() + 1;
          return transNum+"0";
        }
      } catch(NumberFormatException e) {
        //说明不是数字，向下执行
      }

      //--如果范围在A-ZZZ
      String frontBit = transParam;
      String lastBit = "";
      while(frontBit.charAt(frontBit.length()-1) == ':'
            || frontBit.charAt(frontBit.length()-1) == '[') {
        //末尾超过9
        if(frontBit.charAt(frontBit.length()-1) == ':') {
          //当前位更改为0
          lastBit += "0";
        }
        //末尾超过Z
        else if(frontBit.charAt(frontBit.length()-1) == '[') {
          //当前位更改为A
          lastBit += "A";
        }
        frontBit = frontBit.substring(0, frontBit.length()-1);

        //前位递增，并处理格式
        char[] transChars = frontBit.toCharArray();
        String ascii = "";

        //字符转换为数字，并拼接为ASCII码
        int charASCII = -1;
        for (int i = 0; i < transChars.length; i++) {
          charASCII = (int) transChars[i];

          //如果为73(I)、79(O)，自增
          if(charASCII == 73 || charASCII == 79){
            charASCII++;
          }
          ascii += charASCII;
        }

        frontBit = asciiToString(new Integer(ascii).intValue() + 1);
        //递归
        frontBit = formatTransParam(frontBit);
      }
      transParam = frontBit + lastBit;

      return transParam;
    }

    /**
     * 将ASCII码转换为String[ASCII：0(48),必定为偶数]
     * @param int ASCII编码
     * @return String ASCII对应的字符串
     */
    public static String asciiToString(int ascii) {

      String asciiStr = new Integer(ascii).toString();
      String transParam = "";
      //进行转换
      while(asciiStr.length() > 0) {
        transParam += (char) new Integer(asciiStr.substring(0, 2)).intValue();

        asciiStr = asciiStr.substring(2);
      }

      return transParam;
    }

    /**
     * 格式化序号为"000"形式
     * @param xh 缴款凭证号
     * @return String 格式化后的缴款凭证号
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

        //为空字符串，返回"000"
        return "000";

    }

    /**
     * 静态方法，把原字符串（sourceString）以分割标志（divideFlag）分割，并返回分割后的
     * 字符串数组,去掉空格。如果分割标志不存在，则放回的数组中仅仅有一个元素，该元素为原字符串（
     * sourceString）
     * @param source 原字符串
     * @param divideFlag 分割标志
     * @return String[] 返回分割后的字符串数组
     * @roseuid 3F3A00B603AB
     */
    public static String[] divideString(String source, String divideFlag) {
      StringTokenizer st = new StringTokenizer(source, divideFlag);
//求数组长度
      int count = st.countTokens();
      String apple[] = new String[count];
//填充数组
      for (int ii = 0; ii < count; ii++) {
        apple[ii] = st.nextToken().trim();
      } //end for
      return apple;
    }

    /**
     * 根据properties的名称，从配置文件中读取该properties对应的值。
     * @param propertyName properties的名称
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
     * 根据properties的名称，从配置文件中读取该properties对应的值。
     * @param propertyName properties的名称
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
    * 得到时间序号
    * @param swjgzzjgdm 税务机关组织结构代码
    * @return String 序号
    */
   public static String getXh(String swjgzzjgdm) {
     return swjgzzjgdm
         + TinyTools.Date2String(new Date(), "yyyyMMddHHmmssSSS");
   }
   
   /**
    * 过滤 换行符、制表符、回车符
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