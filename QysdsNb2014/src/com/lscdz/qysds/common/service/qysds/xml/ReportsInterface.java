package com.lscdz.qysds.common.service.qysds.xml;

import java.io.IOException;

public interface ReportsInterface
{
    /**
     * �����ı�������
     */
    //��ҵ����˰�걨
    public static final String REPROTTYPE_QYSDSNB = "0";
    //��˰�˻�����Ϣ
    public static final String REPROTTYPE_NSRJBXX = "1";
    //��������
    public static final String REPROTTYPE_REPORTDATA = "2";

    /**
     * ��ȡ���ܵı�����Ϣ
     * @param type ��Ҫ��ȡ�ı������ͣ�ֻ��JBXX���ĺ�DATA����
     * @param password ��������
     * @param fileDir �����ı���·��
     * @return ���ؽ��ܺ�Ķ�Ӧ���ĵ�APPS����
     * @throws IOException
     */
    public Object getEncryptReports(String type, String password, String fileDir) throws IOException;

    /**
     * ����ָ���ļ�·����ȡconfig���ģ���������ļ�ת���ɶ�Ӧ��APPS���󷵻�
     * @param fileDir config����·��
     * @return configAPPS
     * @throws IOException
     */
    public Object getConfigApps(String fileDir) throws IOException;

    /**
     * �������xml�ַ���ת���ɶ�Ӧ��APPS����
     * @param type �����xml�ַ�������
     * @param strXML �����xml�ַ���
     * @return ת�����APPS����
     */
    public Object readReport(String type, String strXML);

    /**
     * �������xml�ַ������ݴ����������ܺ�д������ָ��·��
     * @param xmlStr ��Ҫ���ܱ����xml�ַ���
     * @param dir �ļ�����·��
     * @param password ��������
     * @throws java.lang.Exception
     */
    public void writeXmlToLocal(String xmlStr, String dir, String password) throws Exception;

    /**
     * ��������APPS����ת����xml�ַ���
     * return ת������ַ���
     */
    public String getXMLStr(Object obj) throws Exception;
}
