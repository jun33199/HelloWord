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
 * <p>Title: ��˰����-��������-�����������¼��</p>
 *
 * <p>Description: ����xml�ļ��Ĺ�����</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author fujx
 * @version 1.0
 */
public class MakeXMLUtil {
    public MakeXMLUtil() {
    }

    /**
     * ����xml�ļ�����
     * @param rootName String ���ڵ�����
     * @param nameList List parameters�б�
     * @param valueList List values�б�
     * @param fileName String �����xml�ļ�����
     * @throws IOException
     * @throws JDOMException
     */
    public static String createXML(String rootName, List itemList) throws
            IOException, JDOMException {
        // �������ڵ� list;
        Element root = new Element(rootName);
        //�����ڵ���Ӷ�docment��
        Document Doc = new Document(root);
        for (int i = 0; i < itemList.size(); i++) {
            PzxxXMLItem item = (PzxxXMLItem) itemList.get(i);
            // �����ڵ� ;
            Element element = new Element(item.getItemName());
            //����value
            element.setText(item.getItemValue());
            root.addContent(element);
        }
        //����encoding����Ϊjb23112�������������
        XMLOutputter XMLOut = new XMLOutputter("  ", true, "GB2312");
        //ȡ��dom�ַ���
        String str = XMLOut.outputString(Doc);
        System.out.println("xmlStr === " + str);
        return str;
    }

//     public static void main(String[] args) {
//        try {
//
//            System.out.println("���� mxl �ļ�...");
//            List list = new ArrayList();
//            PzxxXMLItem item = new PzxxXMLItem();
//           item.setItemName("namme");
//           item.setItemValue("����");
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
//             //��ȡ�ļ���xml�ļ��ַ���
//             System.out.println("xmlStr==="+xmlStr);
//             //����xml�ļ��ַ���
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
