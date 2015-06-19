package com.lscdz.qysds.common.service.qysds.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *  �ַ�������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�StringUtils   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����3:02:57   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����3:02:57   
 * �޸ı�ע��   
 * @version  1.0
 */
public class StringUtils {
	/**
	 * ɱ�պ�������"null"��null����ת��Ϊ""
	 * 
	 * @param str  �����ַ���
	 * @return ����ַ���
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
	 * ɱ�պ�������"null"��""����ת��Ϊnull
	 * 
	 * @param str �����ַ���
	 * @return ����ַ���
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
	 * �õ�һ��SQL���ַ��������ֶ�ֵ
	 * 
	 * @param str �����ַ���
	 * @return ����ַ���
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
	 * �õ�һ��SQL�����������ֶ�ֵ
	 * 
	 * @param str �����ַ��� yyyy-mm-dd��yyyymmdd
	 * @return ����ַ���
	 */
	public static String getSQLDate(String str) {
		// 1.ɱ��
		String tmp = StringUtils.killNull(str);
		// 2.ȥ��"-"
		tmp = StringUtils.replaceAll(tmp, "-", "");
		// 3.ȥ�����˵Ŀո�
		tmp = tmp.trim();
		// 4.ת����ʽ
		if (!tmp.equals("")) {
			tmp = "to_date('" + tmp + "','yyyymmdd')";
		} else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * �滻�ַ���
	 * 
	 * @param original Դ�ַ���
	 * @param find  �����ַ���
	 * @param replacement �滻�ַ���
	 * @return �滻����ַ���
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
	 * ���ַ����ָ�Ϊ�ַ�������
	 * 
	 * @param source Դ�ַ���
	 * @param separator �ָ��
	 * @return �ַ�������
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
	 * ת��String��Int
	 * 
	 * @param strInt ԴString
	 * @param defInt Ĭ��Intֵ
	 * @return ת��ֵ ������Ĭ��ֵ
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
