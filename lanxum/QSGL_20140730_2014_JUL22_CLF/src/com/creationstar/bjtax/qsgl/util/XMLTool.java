package com.creationstar.bjtax.qsgl.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.crimson.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * <p>Title:XML通用处理 </p>
 *
 * <p>Description:XML通用处理 </p>
 *
 * <p>Copyright: Copyright (c) 20050329</p>
 *
 * <p>Company:创讯益达 </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class XMLTool {


    /**
     * 将XML字符串转化为Document
     * @param xml String xml字符串
     * @return Document 转换后的Document对象
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDoc(String xml) throws
            IOException, ClassNotFoundException, IllegalAccessException,
            InstantiationException,
            ParserConfigurationException, SAXException

    {
        InputSource xmlIn = new InputSource(new StringReader(xml));
        Document doc = ((DocumentBuilderFactoryImpl) Class.forName(
                "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl").
                        newInstance()).newDocumentBuilder().
                       parse(xmlIn);
        return doc;
    }


    /**
     * 将XML输入流转化为Document
     * @param is InputStream xml输入流
     * @return Document 转换后的Document对象
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDoc(InputStream is) throws
            ParserConfigurationException, SAXException, IOException {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                       parse(is);
        return doc;
    }


    /**
     * 将XML文件转化为Document
     * @param file File xml文件
     * @return Document 转换后的Document对象
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDoc(File file) throws
            ParserConfigurationException, SAXException, IOException {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                       parse(file);
        return doc;
    }


    /**
     * 得到XML字符串的根节点
     * @param doc Document  解析好的Document对象
     * @return Element XML字符串的根节点
     */
    public static Element getRootElement(Document doc) {
        Element element = doc.getDocumentElement();
        return element;
    }


    /**
     * 得到XML的根节点
     * @param xml String   xml字符串
     * @return Element     XML的根节点
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Element getRootElement(String xml) throws
            ParserConfigurationException, SAXException, IOException {

        InputSource xmlIn = new InputSource(new StringReader(xml));
        Element element = DocumentBuilderFactory.newInstance().
                          newDocumentBuilder()
                          .parse(xmlIn).getDocumentElement();
        return element;
    }


    /**
     * 得到XML的根节点
     * @param file File  XML文件
     * @return Element   XML的根节点
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Element getRootElement(File file) throws
            ParserConfigurationException, SAXException, IOException {

        Element element = DocumentBuilderFactory.newInstance().
                          newDocumentBuilder()
                          .parse(file).getDocumentElement();
        return element;
    }


    /**
     * 得到XML的根节点
     * @param is InputStream  输入流
     * @return Element        XML的根节点
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Element getRootElement(InputStream is) throws
            ParserConfigurationException, SAXException, IOException {

        Element element = DocumentBuilderFactory.newInstance().
                          newDocumentBuilder()
                          .parse(is).getDocumentElement();
        return element;
    }


    /**
     * 得到元素的值
     * @param root 操作的元素
     * @return  元素的值
     */
    public static String getElementText(Element root) {
        String text = "";
        Node node = root.getFirstChild();
        if (node != null && node.getNodeType() == Node.TEXT_NODE) {
            text = node.getNodeValue();
        }
        return text;
    }


    /**
     * 得到子元素的值
     * @param root 操作的元素
     * @param eName 相对于操作元素的目录深度
     * @return  元素的值
     */
    public static String getElementText(Element root, String eName) {
        String text = "";
        Element node = null;
        ArrayList nameList = new ArrayList();
        try {
            StringTokenizer tok = new StringTokenizer(eName, "/");
            while (tok.hasMoreTokens()) {
                nameList.add(tok.nextToken());
            }
            Element temp = null;
            Element parent = null;
            for (int i = 0; i < nameList.size(); i++) {
                if (i == 0) {
                    parent = root;
                }
                temp = (Element) parent.getElementsByTagName((String) nameList.
                        get(
                                i)).item(0);
                parent = temp;
            }
            node = temp;
            if (node == null) {} else {
                Node node1 = node.getFirstChild();
                if (node1 != null && node1.getNodeType() == Node.TEXT_NODE) {
                    text = node1.getNodeValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }


    /**
     * 得到一个子元素的
     * @param root 操作的元素
     * @param eName 相对于操作元素的目录深度
     * @return  元素
     */
    public static Element getOneElement(Element root, String eName) {
        Element node = null;
        ArrayList nameList = new ArrayList();
        try {
            StringTokenizer tok = new StringTokenizer(eName, "/");
            while (tok.hasMoreTokens()) {
                nameList.add(tok.nextToken());
            }
            Element temp = null;
            Element parent = null;
            for (int i = 0; i < nameList.size(); i++) {
                if (i == 0) {
                    parent = root;
                }
                NodeList nl = parent.getElementsByTagName((String) nameList.get(
                        i));
                if (nl != null) {
                    if (nl.getLength() > 0) {
                        temp = (Element) (nl.item(0));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
                parent = temp;
            }
            node = temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }


    /**
     * 得到相关子元素的数组
     * @param root 操作的元素
     * @param eName 相对于操作元素的目录深度
     * @return  相关元素数组
     */
    public static Element[] getElements(Element root, String eName) {
        Element[] elements = new Element[0];
        NodeList nodes = null;
        ArrayList nameList = new ArrayList();
        try {
            StringTokenizer tok = new StringTokenizer(eName, "/");
            while (tok.hasMoreTokens()) {
                nameList.add(tok.nextToken());
            }
            Element temp = null;
            Element parent = null;
            for (int i = 0; i < nameList.size(); i++) {
                if (i == 0) {
                    parent = root;
                }
                if (i == nameList.size() - 1) {
                    nodes = parent.getElementsByTagName((String) nameList.get(i));
                } else {
                    temp = (Element) parent.getElementsByTagName((String)
                            nameList.
                            get(i)).item(0);
                    parent = temp;
                }
            }
            elements = new Element[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                elements[i] = (Element) nodes.item(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }


    /**
     * 得到元素特定名称的子节点的数组
     * @param root 操作的元素
     * @param TagName 子节点名称
     * @return  相关元素数组
     */
    public static Element[] getChildrenElements(Element root, String TagName) {
        Element[] elements = new Element[0];
        NodeList nodes = null;
        ArrayList nodesList = new ArrayList();
        try {
            nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getNodeName().equalsIgnoreCase(TagName)) {
                    nodesList.add(nodes.item(i));
                }
            }
            elements = new Element[nodesList.size()];
            for (int i = 0; i < nodesList.size(); i++) {
                elements[i] = (Element) nodesList.get(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return elements;
    }

}
