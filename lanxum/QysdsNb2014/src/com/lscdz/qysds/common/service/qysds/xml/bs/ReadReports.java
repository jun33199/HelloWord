package com.lscdz.qysds.common.service.qysds.xml.bs;


/**
 * 报文的读入操作
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
    //创建XMLDecrypt 解密
    private XMLDecrypt xmlDecrypt = new XMLDecrypt();

    public ReadReports()
    {
    }

    /**
     * 实现接口中定义的writeXmlToLocal空方法
     * @param xmlStr String
     * @param dir String
     * @param password String
     * @throws Exception
     */
    public void writeXmlToLocal(String xmlStr, String dir, String password) throws Exception
    {
    }

    /**
     * 实现接口中定义的getXMLStr空方法
     * @param obj Object
     * @return String
     * @throws Exception
     */
    public String getXMLStr(Object obj) throws Exception
    {
        return null;
    }

    /**
     * 将InputStream对象转换为String对象
     * @param buffer StringBuffer
     * @param is InputStream
     * @return String
     * @throws IOException
     */
    private String readToBuffer(StringBuffer buffer, InputStream is) throws IOException
    {
        String returnStr;
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        returnStr = buffer.toString();
        reader.close();
        return returnStr;
    }

    /**
     * 获取加密的报文信息
     * @param type 需要获取的报文类型：只有JBXX报文和DATA报文
     * @param password 解密密码
     * @param fileDir 操作的报文路径
     * @return 返回解密后的对应报文的APPS对象
     * @throws IOException
     */
    public Object getEncryptReports(String type, String password, String fileDir) throws IOException
    {
        //创建新对象
        Object obj = new Object();
        String strXML = new String();

        //纳税人基本信息
        com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS nsrjbxxApps = new com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS();
        //报表数据
        com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS reportDataApps = new com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS();
        //创建StringBuffer
        //StringBuffer buffer = new StringBuffer();

        try
        {
            //将String 对象转换为InputStream对象
            InputStream is = new FileInputStream(fileDir);
            //将指定文件的读入流根据给定的密码进行解密 返回解密后的字符串strXML
            strXML = xmlDecrypt.Decrypte(password, is);

            
            //获取加密的基本信息报文
            if (type.equals(REPROTTYPE_NSRJBXX))
            {
                //获得纳税人基本信息Apps
                nsrjbxxApps = (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           userInfo.APPS.class, new StringReader(strXML));
                //改变obj的值为qysdsApps
                obj = nsrjbxxApps;
            }
            //获取加密的数据报文
            else if (type.equals(REPROTTYPE_REPORTDATA)) {
                //获得报表数据Apps
                reportDataApps = (com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           data.APPS.class, new StringReader(strXML));
                //改变obj的值为reportDataApps
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
     * 根据指定文件路径读取config报文，将读入的文件转换成对应的APPS对象返回
     * @param fileDir config报文路径
     * @return configAPPS
     * @throws IOException
     */
    public Object getConfigApps(String fileDir) throws IOException
    {
        //创建新对象
        Object obj = new Object();
        String strXML = new String();
        //企业所得税年报
        com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS qysdsApps = new com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS();
        //创建StringBuffer
        StringBuffer buffer = new StringBuffer();

        try {
            //将String 对象转换为InputStream对象
            InputStream is = new FileInputStream(fileDir);
            //将qysdsnbXML对象转换为String 对象
            strXML = readToBuffer(buffer, is);
            //获得企业所得税年报Apps
            qysdsApps = (com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS) Unmarshaller.
                unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS.class,
                          new StringReader(strXML));
            //改变obj对象的值为qysdsApps
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
     * 将传入的xml字符串转换成对应的APPS对象
     * @param type 传入的xml字符串类型
     * @param strXML 传入的xml字符串
     * @return 转换后的APPS对象
     */
    public Object readReport(String type, String strXML)
    {
        //创建新对象
        Object obj = new Object();

        //企业所得税年报
        com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS qysdsApps = new com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS();
        //纳税人基本信息
        com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS nsrjbxxApps = new com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS();
        //报表数据
        com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS reportDataApps = new com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS();
        try
        {
            //表样数据
            if (type.equals(REPROTTYPE_QYSDSNB))
            {
                //获得企业所得税年报Apps
                qysdsApps = (com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS) Unmarshaller.
                    unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS.class,
                              new StringReader(strXML));
                //改变obj对象的值为qysdsApps
                obj = qysdsApps;
            }
            //基本信息数据
            else if (type.equals(REPROTTYPE_NSRJBXX))
            {
                //获得纳税人基本信息Apps
                nsrjbxxApps = (com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           userInfo.APPS.class, new StringReader(strXML));
                //改变obj的值为qysdsApps
                obj = nsrjbxxApps;
            }
            //报表数据数据
            else if (type.equals(REPROTTYPE_REPORTDATA))
            {
                //获得报表数据Apps
                reportDataApps = (com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS)
                    Unmarshaller.unmarshal(com.lscdz.qysds.common.service.qysds.xml.vo.
                                           data.APPS.class, new StringReader(strXML));
                //改变obj的值为reportDataApps
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
