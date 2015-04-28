package com.lscdz.qysds.common.service.qysds.xml.bs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.exolab.castor.xml.Marshaller;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.XMLSerializer;
import org.xml.sax.ContentHandler;

import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
import com.lscdz.qysds.common.service.qysds.xml.XMLEncrypt;



public class WriteReports implements ReportsInterface
{
    //�ļ����ݼ���
    private XMLEncrypt xmlEncrypt = new XMLEncrypt();

    public WriteReports()
    {

    }

    /**
     * ʵ�ֽӿڶ����getEncryptReports
     * @param type
     * @param password
     * @param fileDir
     * @return
     * @throws IOException
     */
    public Object getEncryptReports(String type, String password, String fileDir) throws IOException
    {
        return null;
    }

    /**
     * ʵ�ֽӿڶ����getConfigApps
     * @param fileDir
     * @return
     * @throws IOException
     */
    public Object getConfigApps(String fileDir) throws IOException
    {
        return null;
    }

    /**
     * ʵ�ֽӿڶ����readReport
     * @param type
     * @param strXML
     * @return
     */
    public Object readReport(String type, String strXML)
    {
        return null;
    }

    /**
     * �������xml�ַ������ݴ����������ܺ�д������ָ��·��
     * @param xmlStr ��Ҫ���ܱ����xml�ַ���
     * @param dir �ļ�����·��
     * @param password ��������
     * @throws java.lang.Exception
     */
    public void writeXmlToLocal(String xmlStr, String dir, String password) throws Exception
    {
        //��XML���ܺ������ָ���ļ�
        byte[] byt = xmlEncrypt.Encrypt(password, xmlStr);
        //�õ�ָ��·�����ļ������
        FileOutputStream outPutStream = new FileOutputStream(dir);
        //���ֽ�д���ļ�
        outPutStream.write(byt);
        //�ر�������
        outPutStream.close();
    }

    /**
     * ��������APPS����ת����xml�ַ���
     * return ת������ַ���
     */
    public String getXMLStr(Object obj) throws Exception
    {
        String str = new String();
        //���������ʽ - ��3������true����׼xml��ʽ�����false��һ�����
        OutputFormat format = new OutputFormat(Method.XML, "GBK", true);

        StringWriter strWriter = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(strWriter, format);
        ContentHandler handler = serializer.asContentHandler();


        //����XML
//        StringWriter strWriter = new StringWriter();
        //����Castor������
        Marshaller mash = new Marshaller(handler);
        //����Castor����ת�������ʽ
//        mash.setEncoding("GBK");
        //����ҵ���ĸ��ڵ�
//        mash.setRootElement("APPS");
        //���ñ�����֤״̬
        mash.setValidation(false);
        //ʹ��Castor�ṩ�࣬��Castor��ת����xml�ַ���
        mash.marshal(obj);
        strWriter.flush();
        str = strWriter.getBuffer().toString();
        //�ر�StringWriter
        strWriter.close();
        return str;
    }
}