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
    //文件内容加密
    private XMLEncrypt xmlEncrypt = new XMLEncrypt();

    public WriteReports()
    {

    }

    /**
     * 实现接口定义的getEncryptReports
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
     * 实现接口定义的getConfigApps
     * @param fileDir
     * @return
     * @throws IOException
     */
    public Object getConfigApps(String fileDir) throws IOException
    {
        return null;
    }

    /**
     * 实现接口定义的readReport
     * @param type
     * @param strXML
     * @return
     */
    public Object readReport(String type, String strXML)
    {
        return null;
    }

    /**
     * 将传入的xml字符串根据传入的密码加密后写到本地指定路径
     * @param xmlStr 需要加密保存的xml字符串
     * @param dir 文件保存路径
     * @param password 加密密码
     * @throws java.lang.Exception
     */
    public void writeXmlToLocal(String xmlStr, String dir, String password) throws Exception
    {
        //将XML加密后输出到指定文件
        byte[] byt = xmlEncrypt.Encrypt(password, xmlStr);
        //得到指定路径的文件输出流
        FileOutputStream outPutStream = new FileOutputStream(dir);
        //按字节写入文件
        outPutStream.write(byt);
        //关闭输入流
        outPutStream.close();
    }

    /**
     * 将给定的APPS对象转换成xml字符串
     * return 转换后的字符串
     */
    public String getXMLStr(Object obj) throws Exception
    {
        String str = new String();
        //定义输出格式 - 第3参数：true按标准xml格式输出；false按一行输出
        OutputFormat format = new OutputFormat(Method.XML, "GBK", true);

        StringWriter strWriter = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(strWriter, format);
        ContentHandler handler = serializer.asContentHandler();


        //生成XML
//        StringWriter strWriter = new StringWriter();
        //创建Castor工具类
        Marshaller mash = new Marshaller(handler);
        //设置Castor对象转换编码格式
//        mash.setEncoding("GBK");
        //设置业务报文根节点
//        mash.setRootElement("APPS");
        //设置报文验证状态
        mash.setValidation(false);
        //使用Castor提供类，将Castor类转换成xml字符串
        mash.marshal(obj);
        strWriter.flush();
        str = strWriter.getBuffer().toString();
        //关闭StringWriter
        strWriter.close();
        return str;
    }
}