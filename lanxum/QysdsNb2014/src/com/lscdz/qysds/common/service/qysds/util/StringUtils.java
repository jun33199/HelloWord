package com.lscdz.qysds.common.service.qysds.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *  字符串工具
 * 项目名称：QysdsNb2014   
 * 类名称：StringUtils   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午3:02:57   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午3:02:57   
 * 修改备注：   
 * @version  1.0
 */
public class StringUtils {
	/**
	 * 杀空函数，将"null"和null对象转换为""
	 * 
	 * @param str  输入字符串
	 * @return 输出字符串
	 */
	public static String killNull(String str) {
		String returnStr = null;
		if (str == null || "null".equals(str.toLowerCase())) {
			returnStr = "";
		} else {
			returnStr = str;
		}
		return returnStr;
	}

	/**
	 * 杀空函数，将"null"和""对象转换为null
	 * 
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
	 * 得到一个SQL用字符串类型字段值
	 * 
	 * @param str 输入字符串
	 * @return 输出字符串
	 */
	public static String getSQLStr(String str) {
		String tmp = StringUtils.killNull(str);
		if (!tmp.equals("")) {
			tmp = "'" + tmp + "'";
		} else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * 得到一个SQL用日期类型字段值
	 * 
	 * @param str 输入字符串 yyyy-mm-dd或yyyymmdd
	 * @return 输出字符串
	 */
	public static String getSQLDate(String str) {
		// 1.杀空
		String tmp = StringUtils.killNull(str);
		// 2.去除"-"
		tmp = StringUtils.replaceAll(tmp, "-", "");
		// 3.去除两端的空格
		tmp = tmp.trim();
		// 4.转换格式
		if (!tmp.equals("")) {
			tmp = "to_date('" + tmp + "','yyyymmdd')";
		} else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * 替换字符串
	 * 
	 * @param original 源字符串
	 * @param find  查找字符串
	 * @param replacement 替换字符串
	 * @return 替换后的字符串
	 */
	public final static String replaceAll(String original, String find,
			String replacement) {
		StringBuffer buffer = new StringBuffer(original);
		int idx = original.length();
		int offset = find.length();

		while ((idx = original.lastIndexOf(find, idx - 1)) > -1) {
			buffer.replace(idx, idx + offset, replacement);
		}
		return buffer.toString();
	}

	/**
	 * 将字符串分割为字符串数组
	 * 
	 * @param source 源字符串
	 * @param separator 分割符
	 * @return 字符串数组
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final static String[] split(String source, String separator) {
		ArrayList arr = new ArrayList();
		if (source.indexOf(separator) < 0) {
			arr.add(source);
		} else {
			int start = 0, end = 0;
			while ((end = source.indexOf(separator, start)) > 0) {
				arr.add(source.substring(start, end));
				start = end + separator.length();
			}
		}
		String array[] = new String[arr.size()];
		arr.toArray(array);
		return array;
	}

	/**
	 * 转换String到Int
	 * 
	 * @param strInt 源String
	 * @param defInt 默认Int值
	 * @return 转换值 出错返回默认值
	 */
	public final static int getInt(String strInt, int defInt) {
		int value = defInt;
		try {
			value = Integer.parseInt(strInt);
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			return defInt;
		}
		return value;
	}
}
