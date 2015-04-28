package com.lscdz.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import yangjian.frame.util.FrameException;


/**
 * 
 * 项目名称：BjgsFjmServer   
 * 类名称：StringUtil   
 * 类描述：   String工具类
 * 创建人：wangcy 
 * 创建时间：2014-2-24 下午5:24:40   
 * 修改人：wangcy   
 * 修改时间：2014-2-24 下午5:24:40   
 * 修改备注：   
 * @version  1.0
 */
public class StringUtil 
{
	
	/**
	 * 过滤字符串
	 * @param str
	 * @return
	 */
	public static String filterString(String str)
	{
		if(str!=null&&!"".equals(str))
		{
			str=str.replaceAll("'", "\"");
			str=str.replaceAll("&", "@");
		}
		return str;
	}
	
	/**
	 * 根据字符串字节长度获取字符串长度
	 * @param str
	 * @param length
	 * @return
	 */
	public static String getStringByLength(String str,int length)throws FrameException
	{
		//如果字符串长度大于指定长度，则截取
		if(length<getStrByteCount(str))
		{
			str=bSubstring(str,length);
		}
		return str;
	}
	/**
	 * 按照字节截取字符串
	 * @param s
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String bSubstring(String s, int length) throws FrameException
    {
		try 
		{
			byte[] bytes = s.getBytes("Unicode");
			int n = 0; // 表示当前的字节数
			int i = 2; // 要截取的字节数，从第3个字节开始
			for (; i < bytes.length && n < length; i++) 
			{
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1) 
				{
					n++; // 在UCS2第二个字节时n加1
				}
				else 
				{
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0)
					{
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1)
			{
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
					i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
				else
					i = i + 1;
			}
			return new String(bytes, 0, i, "Unicode");
		} 
		catch (Exception e) 
		{
			throw new FrameException(e.getMessage());
		}
    }
	/**
	 * 获取字符串字节长度
	 * @param s
	 * @return
	 */
	public static int getStrByteCount(String str)
    {
		int length=0;
		if(str!=null&&!"".equals(str))
		{
			//使用正则表达式截取
			str = str.replaceAll("[^\\x00-\\xff]", "**");
			length = str.length();
		}
        return length;
    }
	/**
	 * 杀空函数，将"null"和null对象转换为""
	 * @param str 输入字符串
	 * @return 输出字符串
	 */
	public static String killNull(String str) {
		String returnStr = null;
		if (str == null || "null".equals(str.toLowerCase())) {
			returnStr = "";
		}
		else {
			returnStr = str;
		}
		return returnStr;
	}

	/**
	 * 去除字符串两边的空格并处理空字符串
	 * @param str 输入字符串
	 * @return 输出字符串
	 */
	public static String trim(String str) {
		String returnStr = null;
		returnStr = killNull(str);
		returnStr = returnStr.trim();
		return returnStr;
	}

	/**
	 * 得到一个SQL用字符串类型字段值
	 * @param str 输入字符串
	 * @return 输出字符串
   	*/
	public static String getSQLStr(String str) {
		String tmp = killNull(str);
		if (!tmp.equals("")) {
			tmp = "'" + tmp + "'";
		}
		else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * 得到一个SQL用日期类型字段值
	 * @param str 输入字符串 yyyymmdd
	 * @return 输出字符串
	 */
	public static String getSQLDate(String str) {
		//1.杀空
    	String tmp = killNull(str);
    	//2.去除"-"
    	tmp = tmp.replaceAll("-", "");
    	//3.去除两端的空格
    	tmp = tmp.trim();
    	//4.转换格式
    	if (!tmp.equals("")) {
    		tmp = "to_date('" + tmp + "','yyyymmdd')";
    	}
    	else {
    		tmp = "null";
    	}
    	return tmp;
	}

	/**
	 * 得到一个SQL用日期类型字段值
	 * @param str 输入字符串 yyyy-mm-dd
	 * @return 输出字符串
	 */
	public static String getSQLDate2(String str) {
		//1.杀空
		String tmp = killNull(str);
		//2.去除两端的空格
		tmp = tmp.trim();
		//3.转换格式
		if (!tmp.equals("")) {
			tmp = "to_date('" + tmp + "','yyyy-mm-dd')";
		}
		else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * 得到一个SQL用日期类型字段值
	 * 		加上了时分秒235959,用于时间区间条件的上行区间
	 * @param str 输入字符串 yyyy-mm-dd或yyyymmdd
	 * @return 输出字符串
	 */
	public static String getSQLEndDate(String str) {
		//1.杀空
		String tmp = killNull(str);
		//2.去除"-"
		tmp = tmp.replaceAll("-", "");
		//3.去除两端的空格
		tmp = tmp.trim();
		//4.转换格式
		if (!tmp.equals("")) {
			tmp = "to_date('" + tmp + " 235959','yyyymmdd hh24miss')";
		}
		else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * 获取一个TimeStamp类型的SQL转换
	 * @param time 时间
	 * @return SQL
	 */
	public static String getSQLFromTimestamp(Timestamp time)
	{
		String result = "";
		String strTime = "";
		StringBuffer sb = null;
 	   if (time == null) {
 		   result = "null";
 	   }
	   else {
	      strTime = String.valueOf(time);
	      if (strTime.length() >= 19) {
	        strTime = strTime.substring(0, 19);
	      }
	      sb = new StringBuffer();
	      sb.append("to_date('");
	      sb.append(strTime);
	      sb.append("','yyyy-mm-dd hh24:mi:ss')");
	      result = sb.toString();
	    }
 	   	return result;
  	}

	/**
	 * 比较函数
	 * @param str1 第一个参数A
	 * @param str2 第二个参数B
	 * @return A>=B则返回true,A<B则返回False
 	 */
	public static boolean compare(String str1, String str2) throws Exception {
		boolean flag = false;
		try {
			if (Integer.parseInt(str1) >= Integer.parseInt(str2)) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		catch (Exception e) {
			throw e;
		}
		return flag;
	}
	
	/**
	 * 比较函数
	 * @param str1 第一个参数A
	 * @param str2 第二个参数B
	 * @return A>B则返回true,A<=B则返回False
	 */
	public static boolean compare2(String str1, String str2) throws Exception {
		boolean flag = false;
		try {
			if (Integer.parseInt(str1) > Integer.parseInt(str2)) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 获取当前年份
	 * @return YYYY
	 */
	public static String getNowYear()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate); 
	}
	/**
	 * 字符串左补
     *    在指定字符串左边补充指定字符到指定长度
     * @param str String 需要补充的字符串
     * @param totalLength int 补充字符后的字符串长度
     * @param paddingStr String 指定的补充字符
     * @return String 补充后的字符串
     */
    public static String LPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // 获取待补充字符串长度
        int strLength = str.trim().length();
        // 计算与总长度的差值
        int dif_length = totalLength - strLength;
        
        // 1、添加补充字符
        while(dif_length > 0)
        {
            reStr.append(paddingStr);
            dif_length--;
        }
        // 2、添加原字符串
        reStr.append(str.trim());

        return reStr.toString();
    }
    
    /**
     * 字符串右补
     *    在指定字符串右边补充指定字符到指定长度
     * @param str String 需要补充的字符串
     * @param totalLength int 补充字符后的字符串长度
     * @param paddingStr String 指定的补充字符
     * @return String 补充后的字符串
     */
    public static String RPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // 获取待补充字符串长度
        int strLength = str.trim().length();
        // 计算与总长度的差值
        int dif_length = totalLength - strLength;
        
        // 1、添加原字符串
        reStr.append(str.trim());
        // 2、添加补充字符
        while(dif_length > 0)
        {
            reStr.append(paddingStr);
            dif_length--;
        }

        return reStr.toString();
    }
    
    /**
     * 判断指定字符串在已有字符串中是否已存在
	 * @param parentStr 已有字符串
	 * @param str 指定的字符串
     * @return boolean true-已存在；false-不存在
     */
    public static boolean checkRepeatedStr(String parentStr, String str)
    {
        boolean type = false;
        int i = parentStr.indexOf(str);
        if(i < 0)
        {
            type = false;
        }
        else
        {
            type = true;
        }

        return type;
    }
    
    /**
	 * 将指定字符串以特定的分隔符隔断,并将分隔的字符串分开并分别添加到List中
	 * @param str 需要分割的字符串
	 * @param splitChar 指定的分隔记号符
	 * @return 转换后的ArrayList
	 */
	public static List splitByChar(String str, String splitChar)
	{
		//数据封装后的ArrayList
		List list = new ArrayList();
		//起始Index
		int beginIndex = 0;
		//结束Index
		int endIndex = 0;
		//截取的字符串
		String tmpStr = new String();
        //开始截取
		while ((endIndex = str.indexOf(splitChar, beginIndex)) != -1) {
			//截取字符串
			tmpStr = str.substring(beginIndex, endIndex);
            //没有重复对象则添加
//            if(!list.contains(tmpStr))
//            {
				list.add(tmpStr);
//			}
			//更新起始Index
			beginIndex = endIndex + 1;
		}
		//截取最后一个","之后的字符串
		endIndex = str.length();
		tmpStr = str.substring(beginIndex, endIndex);
		list.add(tmpStr);

		return list;
	}
	
	/**
	 * 无重复分割字符串
	 *    将指定字符串以特定的分隔符隔断,并将分隔的字符串分开并分别添加到List中，如有重复的，则不添加
	 * @param str 需要分割的字符串
	 * @param splitChar 指定的分隔记号符
	 * @return 转换后的ArrayList
	 */
	public static List splitByCharNoreptn(String str, String splitChar)
	{
		//数据封装后的ArrayList
		List list = new ArrayList();
		//起始Index
		int beginIndex = 0;
		//结束Index
		int endIndex = 0;
		//截取的字符串
		String tmpStr = new String();
        //开始截取
		while ((endIndex = str.indexOf(splitChar, beginIndex)) != -1) {
			//截取字符串
			tmpStr = str.substring(beginIndex, endIndex);
            //没有重复对象则添加
            if(!list.contains(tmpStr))
            {
				list.add(tmpStr);
			}
			//更新起始Index
			beginIndex = endIndex + 1;
		}
		//截取最后一个","之后的字符串
		endIndex = str.length();
		tmpStr = str.substring(beginIndex, endIndex);
		list.add(tmpStr);

		return list;
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}



}
