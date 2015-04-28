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
 * ��Ŀ���ƣ�BjgsFjmServer   
 * �����ƣ�StringUtil   
 * ��������   String������
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-24 ����5:24:40   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-24 ����5:24:40   
 * �޸ı�ע��   
 * @version  1.0
 */
public class StringUtil 
{
	
	/**
	 * �����ַ���
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
	 * �����ַ����ֽڳ��Ȼ�ȡ�ַ�������
	 * @param str
	 * @param length
	 * @return
	 */
	public static String getStringByLength(String str,int length)throws FrameException
	{
		//����ַ������ȴ���ָ�����ȣ����ȡ
		if(length<getStrByteCount(str))
		{
			str=bSubstring(str,length);
		}
		return str;
	}
	/**
	 * �����ֽڽ�ȡ�ַ���
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
			int n = 0; // ��ʾ��ǰ���ֽ���
			int i = 2; // Ҫ��ȡ���ֽ������ӵ�3���ֽڿ�ʼ
			for (; i < bytes.length && n < length; i++) 
			{
				// ����λ�ã���3��5��7�ȣ�ΪUCS2�����������ֽڵĵڶ����ֽ�
				if (i % 2 == 1) 
				{
					n++; // ��UCS2�ڶ����ֽ�ʱn��1
				}
				else 
				{
					// ��UCS2����ĵ�һ���ֽڲ�����0ʱ����UCS2�ַ�Ϊ���֣�һ�������������ֽ�
					if (bytes[i] != 0)
					{
						n++;
					}
				}
			}
			// ���iΪ����ʱ�������ż��
			if (i % 2 == 1)
			{
				// ��UCS2�ַ��Ǻ���ʱ��ȥ�������һ��ĺ���
				if (bytes[i - 1] != 0)
					i = i - 1;
				// ��UCS2�ַ�����ĸ�����֣��������ַ�
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
	 * ��ȡ�ַ����ֽڳ���
	 * @param s
	 * @return
	 */
	public static int getStrByteCount(String str)
    {
		int length=0;
		if(str!=null&&!"".equals(str))
		{
			//ʹ��������ʽ��ȡ
			str = str.replaceAll("[^\\x00-\\xff]", "**");
			length = str.length();
		}
        return length;
    }
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
	 * ��ȡ��ǰ���
	 * @return YYYY
	 */
	public static String getNowYear()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		return formatter.format(curDate); 
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



}
