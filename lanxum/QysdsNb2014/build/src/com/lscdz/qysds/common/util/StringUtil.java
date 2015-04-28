package com.lscdz.qysds.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * <p>Title: 字符工具</p>
 * <p>Description: 字符串工具</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: TFHZ</p>
 * @version 1.0
 */

public class StringUtil 
{
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
	 * 杀空函数，将"null"和""对象转换为null
	 * @param str 输入字符串
	 * @return 输出字符串
	 */
    public static String killNull2(String str) {
        String returnStr = null;
        if ("".equals(str.toLowerCase()) || "null".equals(str.toLowerCase())) {
            returnStr = null;
        } else {
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
	 * 对一组数字字符串进行排序
	 * @param iterator 输入数字轮寻器
	 * @param type 0-升序排列，1-降序排列
	 * @return 排序完的list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static List sortKeys(Iterator iterator, int type) {
		//1.句柄申明
		List list = new ArrayList();
		String key0;
		String key1;
		//2.生成基准List
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		//3.生成数组
		String[] keys = {};
		keys = (String[]) list.toArray(keys);
		//4.排序
		String tmpKey;
		for (int i = 0; i < keys.length; i++) 
		{
			for (int j = keys.length - 1; j > i; j--) 
			{
		        if (type == 0) {
		        	if (Integer.parseInt(keys[i]) > Integer.parseInt(keys[j])) {
		        		tmpKey = keys[i];
		        		keys[i] = keys[j];
		        		keys[j] = tmpKey;
		        	}
		        }
		        else if (type == 1) {
		        	if (Integer.parseInt(keys[i]) < Integer.parseInt(keys[j])) {
		        		tmpKey = keys[i];
		        		keys[i] = keys[j];
		        		keys[j] = tmpKey;
		        	}
		        }
			}
		}
		//5.生成返回容器
		List tmpList = new ArrayList();
		for (int i = 0; i < keys.length; i++) {
			tmpList.add(keys[i]);
		}
		//6.返回
		return tmpList;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

    /**
     * 将读入的InputStream转换成String对象输出
     * @param is 读入的InputStream流对象
     * @return 转换后的String对象
     * @throws IOException
     */
    public static String readToBuffer(InputStream is) throws IOException {
        //返回的String对象
        String returnStr = new String();
        //用来拼接读取内容的StringBuffer
        StringBuffer buffer = new StringBuffer();
        // 用来保存每行读取的内容
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // 读取第一行
        line = reader.readLine();
        // 如果 line 为空说明读完了
        while (line != null) {
            // 将读到的内容添加到 buffer 中
            buffer.append(line);
            // 添加换行符
            buffer.append("\n");
            // 读取下一行
            line = reader.readLine();
        }
        returnStr = buffer.toString();
        return returnStr;
    }
    
    /**
     * 判断是否在征期内
     * @param zqstr
     * @param now
     * @return
     */
    public static boolean withinZq(String zqstr, Date now) {
        try
        {
            int fromDate = Integer.parseInt(zqstr.substring(0, 4));
            int toDate = Integer.parseInt(zqstr.substring(5));
            int nowDate = Integer.parseInt((new SimpleDateFormat("MMdd")). format(now));
            if (nowDate <= toDate && nowDate >= fromDate)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            return false;
        }
    }
    
    /**
	 * 使用给定的切分符，切分字符串。
	 * 
	 * @param source    待处理字符串。
	 * @param separator 指定的切分符。
	 * @return String[] 切分后的字符串数组。
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] splitBySeparator(String source, String separator) {
		if (source == null) {
			return null;
		}
		if (separator == null) {
			return new String[] { source };
		}
		int startIndex = 0;
		int endIndex = source.indexOf(separator);
		Vector vector = new Vector();
		while (endIndex >= startIndex) {
			vector.add(source.substring(startIndex, endIndex));
			startIndex = endIndex + separator.length();
			endIndex = source.indexOf(separator, startIndex);
		}
		vector.add(source.substring(startIndex));
		String[] returnValue = new String[vector.size()];
		vector.copyInto(returnValue);
		return returnValue;
	}
}
