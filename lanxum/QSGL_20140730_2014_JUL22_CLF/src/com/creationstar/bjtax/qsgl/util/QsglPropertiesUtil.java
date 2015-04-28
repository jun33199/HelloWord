/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.ttsoft.framework.util.PropertiesUtil;

/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 契税管理模块配置文件的操作工具类</p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class QsglPropertiesUtil extends PropertiesUtil {
    /**
     * 配置文件的名称
     */
    private static String PROPERTIES_FILE_NAME = Constants.
                                                 QSGL_PROPERTIES_FILE_NAME;

    /**
     * 配置文件Properties文件
     */
    private static Properties properties = null;

    /**
     * 存储Properties属性的key和value
     */
    private static HashMap map = new HashMap();

    /**
     * 默认构造函数
     */
    private QsglPropertiesUtil() {
    }

    /**
     * 根据前缀，过滤所有的系统properties中的属性，返回一个新的properties。
     *
     * @param prefixName 前缀的名字。
     * @param propertyName properties的名称。
     * @return java.util.Properties 返回一个新的properties，其中的key不包含前缀。
     */
    public static Properties getPropertiesByPrefix(String prefixName,
            String propertyName) {
        Properties returnProps = (Properties) map.get(prefixName);

        if (returnProps != null) {
            return returnProps;
        }

        properties = getProperties();

        if (properties == null) {
            return null;
        }

        String prefix = prefixName;

        if (prefix == null) {
            prefix = "";
        } else {
            prefix = prefix.trim() + ".";
        }

        returnProps = new Properties();

        Enumeration enume = properties.propertyNames();

        while (enume.hasMoreElements()) {
            String key = (String) enume.nextElement();

            if (key.startsWith(prefix)) {
                returnProps.setProperty(key.substring(prefix.length()),
                                        properties.getProperty(key));
            }
        }

        map.put(prefixName, returnProps);

        return returnProps;
    }

    /**
     * 取得所有values
     *
     * @param pro 要从中取得值的Properties
     * @return String[]; null, if pro is null;
     */
    public static String[] getAllValues(Properties pro) {
        if (pro == null) {
            return null;
        }

        String[] values = new String[pro.size()];
        pro.values().toArray(values);

        return values;
    }

    /**
     * 根据value取得key的内容
     *
     * @param vv 值。
     * @param pro 属性对象
     * @return 返回键值。
     */
    public static String getKeyByValue(String vv, Properties pro) {
        if (pro != null) {
            // 遍历属性对象中的key-value对
            java.util.Enumeration enume = pro.keys();

            while (enume.hasMoreElements()) {
                String key = (String) enume.nextElement();
                String value = pro.getProperty(key);

                if (vv.trim().equals(value.trim())) {
                    return key;
                }
            }
        }

        // 没有的时候，返回空字符串
        return "";
    }

    /**
     * 将 ISO 8859-1 字符集的字符串转化为 GBK 字符集的字符串。
     * 如果系统不支持GBK字符集，将返回输入的字符串。（该情况应该不会出现）
     *
     * @param isoString 需要编码转换的字符串。
     * @return 转换过的字符串。null，如果传入的字符串是null。
     */
    public static String encode(String isoString) {
        if (isoString == null) {
            return null;
        }

        String gbkString = isoString;

        try {
            byte[] temp = isoString.getBytes("ISO8859_1");
            gbkString = new String(temp, "GBK");
        } catch (Exception ex) {
        }

        return gbkString;
    }

    public static String getPROPERTIES_FILE_NAME() {
        return PROPERTIES_FILE_NAME;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
