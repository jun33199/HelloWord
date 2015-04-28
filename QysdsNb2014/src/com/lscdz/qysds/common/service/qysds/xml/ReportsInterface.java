package com.lscdz.qysds.common.service.qysds.xml;

import java.io.IOException;

public interface ReportsInterface
{
    /**
     * 操作的报文类型
     */
    //企业所得税年报
    public static final String REPROTTYPE_QYSDSNB = "0";
    //纳税人基本信息
    public static final String REPROTTYPE_NSRJBXX = "1";
    //报表数据
    public static final String REPROTTYPE_REPORTDATA = "2";

    /**
     * 获取加密的报文信息
     * @param type 需要获取的报文类型：只有JBXX报文和DATA报文
     * @param password 解密密码
     * @param fileDir 操作的报文路径
     * @return 返回解密后的对应报文的APPS对象
     * @throws IOException
     */
    public Object getEncryptReports(String type, String password, String fileDir) throws IOException;

    /**
     * 根据指定文件路径读取config报文，将读入的文件转换成对应的APPS对象返回
     * @param fileDir config报文路径
     * @return configAPPS
     * @throws IOException
     */
    public Object getConfigApps(String fileDir) throws IOException;

    /**
     * 将传入的xml字符串转换成对应的APPS对象
     * @param type 传入的xml字符串类型
     * @param strXML 传入的xml字符串
     * @return 转换后的APPS对象
     */
    public Object readReport(String type, String strXML);

    /**
     * 将传入的xml字符串根据传入的密码加密后写到本地指定路径
     * @param xmlStr 需要加密保存的xml字符串
     * @param dir 文件保存路径
     * @param password 加密密码
     * @throws java.lang.Exception
     */
    public void writeXmlToLocal(String xmlStr, String dir, String password) throws Exception;

    /**
     * 将给定的APPS对象转换成xml字符串
     * return 转换后的字符串
     */
    public String getXMLStr(Object obj) throws Exception;
}
