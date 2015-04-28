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
 * <p>Title: �ַ�����</p>
 * <p>Description: �ַ�������</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: TFHZ</p>
 * @version 1.0
 */

public class StringUtil 
{
	/**
	 * ɱ�պ�������"null"��null����ת��Ϊ""
	 * @param str �����ַ���
	 * @return ����ַ���
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
	 * ɱ�պ�������"null"��""����ת��Ϊnull
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
	 * ȥ���ַ������ߵĿո񲢴�����ַ���
	 * @param str �����ַ���
	 * @return ����ַ���
	 */
	public static String trim(String str) {
		String returnStr = null;
		returnStr = killNull(str);
		returnStr = returnStr.trim();
		return returnStr;
	}

	/**
	 * �õ�һ��SQL���ַ��������ֶ�ֵ
	 * @param str �����ַ���
	 * @return ����ַ���
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
	 * �õ�һ��SQL�����������ֶ�ֵ
	 * @param str �����ַ��� yyyymmdd
	 * @return ����ַ���
	 */
	public static String getSQLDate(String str) {
		//1.ɱ��
    	String tmp = killNull(str);
    	//2.ȥ��"-"
    	tmp = tmp.replaceAll("-", "");
    	//3.ȥ�����˵Ŀո�
    	tmp = tmp.trim();
    	//4.ת����ʽ
    	if (!tmp.equals("")) {
    		tmp = "to_date('" + tmp + "','yyyymmdd')";
    	}
    	else {
    		tmp = "null";
    	}
    	return tmp;
	}

	/**
	 * �õ�һ��SQL�����������ֶ�ֵ
	 * @param str �����ַ��� yyyy-mm-dd
	 * @return ����ַ���
	 */
	public static String getSQLDate2(String str) {
		//1.ɱ��
		String tmp = killNull(str);
		//2.ȥ�����˵Ŀո�
		tmp = tmp.trim();
		//3.ת����ʽ
		if (!tmp.equals("")) {
			tmp = "to_date('" + tmp + "','yyyy-mm-dd')";
		}
		else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * �õ�һ��SQL�����������ֶ�ֵ
	 * 		������ʱ����235959,����ʱ��������������������
	 * @param str �����ַ��� yyyy-mm-dd��yyyymmdd
	 * @return ����ַ���
	 */
	public static String getSQLEndDate(String str) {
		//1.ɱ��
		String tmp = killNull(str);
		//2.ȥ��"-"
		tmp = tmp.replaceAll("-", "");
		//3.ȥ�����˵Ŀո�
		tmp = tmp.trim();
		//4.ת����ʽ
		if (!tmp.equals("")) {
			tmp = "to_date('" + tmp + " 235959','yyyymmdd hh24miss')";
		}
		else {
			tmp = "null";
		}
		return tmp;
	}

	/**
	 * ��ȡһ��TimeStamp���͵�SQLת��
	 * @param time ʱ��
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
	 * ��һ�������ַ�����������
	 * @param iterator ����������Ѱ��
	 * @param type 0-�������У�1-��������
	 * @return �������list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static List sortKeys(Iterator iterator, int type) {
		//1.�������
		List list = new ArrayList();
		String key0;
		String key1;
		//2.���ɻ�׼List
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		//3.��������
		String[] keys = {};
		keys = (String[]) list.toArray(keys);
		//4.����
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
		//5.���ɷ�������
		List tmpList = new ArrayList();
		for (int i = 0; i < keys.length; i++) {
			tmpList.add(keys[i]);
		}
		//6.����
		return tmpList;
	}

	/**
	 * �ȽϺ���
	 * @param str1 ��һ������A
	 * @param str2 �ڶ�������B
	 * @return A>=B�򷵻�true,A<B�򷵻�False
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
	 * �ȽϺ���
	 * @param str1 ��һ������A
	 * @param str2 �ڶ�������B
	 * @return A>B�򷵻�true,A<=B�򷵻�False
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
	 * �ַ�����
     *    ��ָ���ַ�����߲���ָ���ַ���ָ������
     * @param str String ��Ҫ������ַ���
     * @param totalLength int �����ַ�����ַ�������
     * @param paddingStr String ָ���Ĳ����ַ�
     * @return String �������ַ���
     */
    public static String LPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // ��ȡ�������ַ�������
        int strLength = str.trim().length();
        // �������ܳ��ȵĲ�ֵ
        int dif_length = totalLength - strLength;
        
        // 1����Ӳ����ַ�
        while(dif_length > 0)
        {
            reStr.append(paddingStr);
            dif_length--;
        }
        // 2�����ԭ�ַ���
        reStr.append(str.trim());

        return reStr.toString();
    }
    
    /**
     * �ַ����Ҳ�
     *    ��ָ���ַ����ұ߲���ָ���ַ���ָ������
     * @param str String ��Ҫ������ַ���
     * @param totalLength int �����ַ�����ַ�������
     * @param paddingStr String ָ���Ĳ����ַ�
     * @return String �������ַ���
     */
    public static String RPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // ��ȡ�������ַ�������
        int strLength = str.trim().length();
        // �������ܳ��ȵĲ�ֵ
        int dif_length = totalLength - strLength;
        
        // 1�����ԭ�ַ���
        reStr.append(str.trim());
        // 2����Ӳ����ַ�
        while(dif_length > 0)
        {
            reStr.append(paddingStr);
            dif_length--;
        }

        return reStr.toString();
    }
    
    /**
     * �ж�ָ���ַ����������ַ������Ƿ��Ѵ���
	 * @param parentStr �����ַ���
	 * @param str ָ�����ַ���
     * @return boolean true-�Ѵ��ڣ�false-������
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
	 * ��ָ���ַ������ض��ķָ�������,�����ָ����ַ����ֿ����ֱ���ӵ�List��
	 * @param str ��Ҫ�ָ���ַ���
	 * @param splitChar ָ���ķָ��Ǻŷ�
	 * @return ת�����ArrayList
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List splitByChar(String str, String splitChar)
	{
		//���ݷ�װ���ArrayList
		List list = new ArrayList();
		//��ʼIndex
		int beginIndex = 0;
		//����Index
		int endIndex = 0;
		//��ȡ���ַ���
		String tmpStr = new String();
        //��ʼ��ȡ
		while ((endIndex = str.indexOf(splitChar, beginIndex)) != -1) {
			//��ȡ�ַ���
			tmpStr = str.substring(beginIndex, endIndex);
            //û���ظ����������
//            if(!list.contains(tmpStr))
//            {
				list.add(tmpStr);
//			}
			//������ʼIndex
			beginIndex = endIndex + 1;
		}
		//��ȡ���һ��","֮����ַ���
		endIndex = str.length();
		tmpStr = str.substring(beginIndex, endIndex);
		list.add(tmpStr);

		return list;
	}
	
	/**
	 * ���ظ��ָ��ַ���
	 *    ��ָ���ַ������ض��ķָ�������,�����ָ����ַ����ֿ����ֱ���ӵ�List�У������ظ��ģ������
	 * @param str ��Ҫ�ָ���ַ���
	 * @param splitChar ָ���ķָ��Ǻŷ�
	 * @return ת�����ArrayList
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List splitByCharNoreptn(String str, String splitChar)
	{
		//���ݷ�װ���ArrayList
		List list = new ArrayList();
		//��ʼIndex
		int beginIndex = 0;
		//����Index
		int endIndex = 0;
		//��ȡ���ַ���
		String tmpStr = new String();
        //��ʼ��ȡ
		while ((endIndex = str.indexOf(splitChar, beginIndex)) != -1) {
			//��ȡ�ַ���
			tmpStr = str.substring(beginIndex, endIndex);
            //û���ظ����������
            if(!list.contains(tmpStr))
            {
				list.add(tmpStr);
			}
			//������ʼIndex
			beginIndex = endIndex + 1;
		}
		//��ȡ���һ��","֮����ַ���
		endIndex = str.length();
		tmpStr = str.substring(beginIndex, endIndex);
		list.add(tmpStr);

		return list;
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}

    /**
     * �������InputStreamת����String�������
     * @param is �����InputStream������
     * @return ת�����String����
     * @throws IOException
     */
    public static String readToBuffer(InputStream is) throws IOException {
        //���ص�String����
        String returnStr = new String();
        //����ƴ�Ӷ�ȡ���ݵ�StringBuffer
        StringBuffer buffer = new StringBuffer();
        // ��������ÿ�ж�ȡ������
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // ��ȡ��һ��
        line = reader.readLine();
        // ��� line Ϊ��˵��������
        while (line != null) {
            // ��������������ӵ� buffer ��
            buffer.append(line);
            // ��ӻ��з�
            buffer.append("\n");
            // ��ȡ��һ��
            line = reader.readLine();
        }
        returnStr = buffer.toString();
        return returnStr;
    }
    
    /**
     * �ж��Ƿ���������
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
	 * ʹ�ø������зַ����з��ַ�����
	 * 
	 * @param source    �������ַ�����
	 * @param separator ָ�����зַ���
	 * @return String[] �зֺ���ַ������顣
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
