package com.creationstar.bjtax.qsgl.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.model.bo.PzxxXMLItem;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;


/**
 * <p>Title: 契税管理-批量受理-批量软件配置录入</p>
 *
 * <p>Description: 生成xml文件的工具类</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author fujx
 * @version 1.0
 */
public class MakeXMLUtil {
    public MakeXMLUtil() {
    }

    /**
     * 生成xml文件方法
     * @param rootName String 根节点名称
     * @param nameList List parameters列表
     * @param valueList List values列表
     * @param fileName String 保存的xml文件名称
     * @throws IOException
     * @throws JDOMException
     */
    public static String createXML(String rootName, List itemList) throws
            IOException, JDOMException {
        // 创建根节点 list;
        Element root = new Element(rootName);
        //将根节点添加都docment中
        Document Doc = new Document(root);
        for (int i = 0; i < itemList.size(); i++) {
            PzxxXMLItem item = (PzxxXMLItem) itemList.get(i);
            // 创建节点 ;
            Element element = new Element(item.getItemName());
            //设置value
            element.setText(item.getItemValue());
            root.addContent(element);
        }
        //设置encoding属性为jb23112，解决乱码问题
        XMLOutputter XMLOut = new XMLOutputter("  ", true, "GB2312");
        //取得dom字符串
        String str = XMLOut.outputString(Doc);
        System.out.println("xmlStr === " + str);
        return str;
    }

//     public static void main(String[] args) {
//        try {
//
//            System.out.println("生成 mxl 文件...");
//            List list = new ArrayList();
//            PzxxXMLItem item = new PzxxXMLItem();
//           item.setItemName("namme");
//           item.setItemValue("我是");
//           list.add(item);
//           createXML("user",list);
//           // System.out.println(createXML("user",nameList,valueList,"user"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//     }

    public List readXml() {
        List list = new ArrayList();
        return list;
    }

//    public static void main(String[] args){
//         try{
//             java.io.BufferedReader reader = new java.io.BufferedReader(
//                     new java.io.FileReader("d:/pzxx.xml"));
//             String s = reader.readLine();
//             StringBuffer sb = new StringBuffer();
//             while (s != null)
//             {
//                 sb.append(s);
////                 sb.append("\r\n");
//                 s = reader.readLine();
//             }
//             String xmlStr = sb.toString();
//             //读取的加密xml文件字符串
//             System.out.println("xmlStr==="+xmlStr);
//             //解密xml文件字符串
//             DESUtil des = new DESUtil();
//            String aaaa = des.decrypt(xmlStr);
//            System.out.println("bbbbb =====" +aaaa);
//            //System.out.println("aaaaaa =====" + new String(aaaa.getBytes("gbk"),"utf-8"));
//
//             reader.close();
//         }      catch(Exception e){
////            e.printStackTrace();
////        }
//
//}
//
////        String configFile="d:\\pzxx.xml";
////        try{
////            File file = new File(configFile);
////            String ordStr = file.toString();
////            DESUtil des = new DESUtil();
////            String xmlStr = des.decrypt(ordStr);
////            System.out.println("xml =====" + new String(xmlStr.getBytes("gbk"),"utf-8"));
////
////        }        catch(Exception e){
////            e.printStackTrace();
////        }
//    //}
//
//    }
}
