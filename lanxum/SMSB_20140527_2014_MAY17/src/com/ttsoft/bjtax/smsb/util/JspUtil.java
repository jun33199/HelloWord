package com.ttsoft.bjtax.smsb.util;

import java.util.*;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.framework.util.StringUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JspUtil
{
    /**
     * 获取代码表数据的两维数组javascript字符串
     * @param dataSource java.util.List  数据源，List中包含符合CodeTableInterface接口的对象.
     * @param allowAnonymous 是否需要在数组中加入一个值=""的选项。
     * @return 表示两维数组的字符串，例如：[["",""],["01","身份证"],["02","护照"]]
     */
    public static String displaySelectDataSource(SfHashList dataSource, boolean allowAnonymous)
    {
        if (allowAnonymous==false)
        {
            return displaySelectDataSource(dataSource,null);
        }
        return displaySelectDataSource(dataSource,new String[]{"",""});
    }

    /**
     * 获取代码表数据的两维数组javascript字符串
     * @param dataSource java.util.List  数据源，List中包含符合CodeTableInterface接口的对象.
     * @param allowAnonymous 是否需要在数组中加入一个值=""的选项。
     * @return 表示两维数组的字符串，例如：[["",""],["01","身份证"],["02","护照"]]
     */
    public static String displaySelectDataSource(SfHashList dataSource, String[] anonymousValue)
    {
        String arrayString = "";
        int length = dataSource.size();
        if (anonymousValue!=null)
        {
            if (anonymousValue.length==1)
            {
                arrayString = "[\"" + anonymousValue[0] + "\",\"\"]";
            }
            else if (anonymousValue.length>1)
            {
                arrayString = "[\"" + anonymousValue[0] + "\",\""+anonymousValue[1]+"\"]";
            }
        }
        if (dataSource==null && length==0)
        {
            return "[[\"\",\"\"]]";
        }

        for (int i=0; i<length; i++)
        {
            if (arrayString.length()>0)
            {
                arrayString +=",";
            }
/*System.out.println("i="+i);
            HashMap map = dataSource.getRow(i);
            Set keySet = map.keySet();
            Iterator keys = keySet.iterator();
            while  (keys.hasNext()) {
              String key = (String) keys.next();
              System.out.println("keyi="+key);
              System.out.println("valuei="+dataSource.get(i, key));
            }
            Iterator v = map.values().iterator();
            while (v.hasNext()) {
              String value = (String) v.next();
              System.out.println("valuei="+value);
            }
*/

            arrayString += "[\""+StringUtil.escapeForJavascript(dataSource.get(i, "szsmdm"))
                         +"\",\""+StringUtil.escapeForJavascript(dataSource.get(i, "szsmdm||szsmmc"))+"\"]";
        }
        arrayString = "["+arrayString+"]";
        return arrayString;
    }


    public static String format(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        else if (obj instanceof java.sql.Timestamp)
        {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(obj);
        }
        else if (obj instanceof String)
        {
            return (String)obj;
        }
        else if (obj instanceof java.math.BigDecimal)
        {
            java.math.BigDecimal decimal = (java.math.BigDecimal)obj;
            if (decimal.scale() != 2)
            {
                decimal = decimal.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
            }
            return decimal.toString();
        }
        else
        {
            return obj.toString();
        }
    }


}