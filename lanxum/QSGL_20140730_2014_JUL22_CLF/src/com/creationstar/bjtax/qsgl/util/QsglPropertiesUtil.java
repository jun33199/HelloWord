/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.ttsoft.framework.util.PropertiesUtil;

/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ��˰����ģ�������ļ��Ĳ���������</p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class QsglPropertiesUtil extends PropertiesUtil {
    /**
     * �����ļ�������
     */
    private static String PROPERTIES_FILE_NAME = Constants.
                                                 QSGL_PROPERTIES_FILE_NAME;

    /**
     * �����ļ�Properties�ļ�
     */
    private static Properties properties = null;

    /**
     * �洢Properties���Ե�key��value
     */
    private static HashMap map = new HashMap();

    /**
     * Ĭ�Ϲ��캯��
     */
    private QsglPropertiesUtil() {
    }

    /**
     * ����ǰ׺���������е�ϵͳproperties�е����ԣ�����һ���µ�properties��
     *
     * @param prefixName ǰ׺�����֡�
     * @param propertyName properties�����ơ�
     * @return java.util.Properties ����һ���µ�properties�����е�key������ǰ׺��
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
     * ȡ������values
     *
     * @param pro Ҫ����ȡ��ֵ��Properties
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
     * ����valueȡ��key������
     *
     * @param vv ֵ��
     * @param pro ���Զ���
     * @return ���ؼ�ֵ��
     */
    public static String getKeyByValue(String vv, Properties pro) {
        if (pro != null) {
            // �������Զ����е�key-value��
            java.util.Enumeration enume = pro.keys();

            while (enume.hasMoreElements()) {
                String key = (String) enume.nextElement();
                String value = pro.getProperty(key);

                if (vv.trim().equals(value.trim())) {
                    return key;
                }
            }
        }

        // û�е�ʱ�򣬷��ؿ��ַ���
        return "";
    }

    /**
     * �� ISO 8859-1 �ַ������ַ���ת��Ϊ GBK �ַ������ַ�����
     * ���ϵͳ��֧��GBK�ַ�����������������ַ������������Ӧ�ò�����֣�
     *
     * @param isoString ��Ҫ����ת�����ַ�����
     * @return ת�������ַ�����null�����������ַ�����null��
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
