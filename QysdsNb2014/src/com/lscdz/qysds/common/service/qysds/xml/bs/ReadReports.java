package com.lscdz.qysds.common.service.qysds.xml.bs;


/**
 * ���ĵĶ������
 * @author lm
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
import com.lscdz.qysds.common.service.qysds.xml.XMLDecrypt;


public class ReadReports implements ReportsInterface
{
    //����XMLDecrypt ����
    private XMLDecrypt xmlDecrypt = new XMLDecrypt();

    public ReadReports()
    {
    }

    /**
     * ʵ�ֽӿ��ж����writeXmlToLocal�շ���
     * @param xmlStr String
     * @param dir String
     * @param password String
     * @throws Exception
     */
    public void writeXmlToLocal(String xmlStr, String dir, String password) throws Exception
    {
    }

    /**
     * ʵ�ֽӿ��ж����getXMLStr�շ���
     * @param obj Object
     * @return String
     * @throws Exception
     */
    public String getXMLStr(Object obj) throws Exception
    {
        return null;
    }

    /**
     * ��InputStream����ת��ΪString����
     * @param buffer StringBuffer
     * @param is InputStream
     * @return String
     * @throws IOException
     */
    private String readToBuffer(StringBuffer buffer, InputStream is) throws IOException
    {
        String returnStr;
        String line; // ��������ÿ�ж�ȡ������
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // ��ȡ��һ��
        while (line != null) { // ��� line Ϊ��˵��������
            buffer.append(line); // ��������������ӵ� buffer ��
            buffer.append("\n"); // ��ӻ��з�
            line = reader.readLine(); // ��ȡ��һ��
        }
        returnStr = buffer.toString();
        reader.close();
        return returnStr;
    }

    /**
     * ��ȡ���ܵı�����Ϣ
     * @param type ��Ҫ��ȡ�ı������ͣ�ֻ��JBXX���ĺ�DATA����
     * @param password ��������
     * @param fileDir �����ı���·��
     * @return ���ؽ��ܺ�Ķ�Ӧ���ĵ�APPS����
     * @throws IOException
     */
    public Object getEncryptReports(String type, String password, String fileDir) throws IOException
    {
        //�����¶���
        Object obj = new Object();
        String strXML = new String();

        //��˰�˻�����Ϣ
        com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS nsrjbxxApps = new com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS();
        //��������
        com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS reportDataApps = new com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS();
        //����StringBuffer
        //StringBuffer buffer = new StringBuffer();

        try
        {
            //��String ����ת��ΪInputStream����
            InputStream is = new FileInputStream(fileDir);
            //��ָ���ļ��Ķ��������ݸ�����������н��� ���ؽ��ܺ���ַ���strXML
            strXML = xmlDecrypt.Decrypte(password, is);

            
            //��ȡ���ܵĻ�����Ϣ����
            if (type.equals(REPROTTYPE_NSRJBXX))
            {
                //�����˰�˻�����ϢApps
                nsrjbxxApps = (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           userInfo.APPS.class, new StringReader(strXML));
                //�ı�obj��ֵΪqysdsApps
                obj = nsrjbxxApps;
            }
            //��ȡ���ܵ����ݱ���
            else if (type.equals(REPROTTYPE_REPORTDATA)) {
                //��ñ�������Apps
                reportDataApps = (com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           data.APPS.class, new StringReader(strXML));
                //�ı�obj��ֵΪreportDataApps
                obj = reportDataApps;
            }
            

            is.close();
        }
        catch (MarshalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * ����ָ���ļ�·����ȡconfig���ģ���������ļ�ת���ɶ�Ӧ��APPS���󷵻�
     * @param fileDir config����·��
     * @return configAPPS
     * @throws IOException
     */
    public Object getConfigApps(String fileDir) throws IOException
    {
        //�����¶���
        Object obj = new Object();
        String strXML = new String();
        //��ҵ����˰�걨
        com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS qysdsApps = new com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS();
        //����StringBuffer
        StringBuffer buffer = new StringBuffer();

        try {
            //��String ����ת��ΪInputStream����
            InputStream is = new FileInputStream(fileDir);
            //��qysdsnbXML����ת��ΪString ����
            strXML = readToBuffer(buffer, is);
            //�����ҵ����˰�걨Apps
            qysdsApps = (com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS) Unmarshaller.
                unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS.class,
                          new StringReader(strXML));
            //�ı�obj�����ֵΪqysdsApps
            obj = qysdsApps;
            is.close();
        }
        catch (MarshalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * �������xml�ַ���ת���ɶ�Ӧ��APPS����
     * @param type �����xml�ַ�������
     * @param strXML �����xml�ַ���
     * @return ת�����APPS����
     */
    public Object readReport(String type, String strXML)
    {
        //�����¶���
        Object obj = new Object();

        //��ҵ����˰�걨
        com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS qysdsApps = new com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS();
        //��˰�˻�����Ϣ
        com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS nsrjbxxApps = new com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS();
        //��������
        com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS reportDataApps = new com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS();
        try
        {
            //��������
            if (type.equals(REPROTTYPE_QYSDSNB))
            {
                //�����ҵ����˰�걨Apps
                qysdsApps = (com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS) Unmarshaller.
                    unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS.class,
                              new StringReader(strXML));
                //�ı�obj�����ֵΪqysdsApps
                obj = qysdsApps;
            }
            //������Ϣ����
            else if (type.equals(REPROTTYPE_NSRJBXX))
            {
                //�����˰�˻�����ϢApps
                nsrjbxxApps = (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           userInfo.APPS.class, new StringReader(strXML));
                //�ı�obj��ֵΪqysdsApps
                obj = nsrjbxxApps;
            }
            //������������
            else if (type.equals(REPROTTYPE_REPORTDATA))
            {
                //��ñ�������Apps
                reportDataApps = (com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           data.APPS.class, new StringReader(strXML));
                //�ı�obj��ֵΪreportDataApps
                obj = reportDataApps;
            }
        }
        catch (MarshalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

}
