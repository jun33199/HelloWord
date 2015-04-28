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
 * <p>Title:XMLͨ�ô��� </p>
 *
 * <p>Description:XMLͨ�ô��� </p>
 *
 * <p>Copyright: Copyright (c) 20050329</p>
 *
 * <p>Company:��Ѷ��� </p>
 *
 * @author ����
 * @version 1.0
 */
public class XMLTool {


    /**
     * ��XML�ַ���ת��ΪDocument
     * @param xml String xml�ַ���
     * @return Document ת�����Document����
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
     * ��XML������ת��ΪDocument
     * @param is InputStream xml������
     * @return Document ת�����Document����
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
     * ��XML�ļ�ת��ΪDocument
     * @param file File xml�ļ�
     * @return Document ת�����Document����
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
     * �õ�XML�ַ����ĸ��ڵ�
     * @param doc Document  �����õ�Document����
     * @return Element XML�ַ����ĸ��ڵ�
     */
    public static Element getRootElement(Document doc) {
        Element element = doc.getDocumentElement();
        return element;
    }


    /**
     * �õ�XML�ĸ��ڵ�
     * @param xml String   xml�ַ���
     * @return Element     XML�ĸ��ڵ�
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
     * �õ�XML�ĸ��ڵ�
     * @param file File  XML�ļ�
     * @return Element   XML�ĸ��ڵ�
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
     * �õ�XML�ĸ��ڵ�
     * @param is InputStream  ������
     * @return Element        XML�ĸ��ڵ�
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
     * �õ�Ԫ�ص�ֵ
     * @param root ������Ԫ��
     * @return  Ԫ�ص�ֵ
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
     * �õ���Ԫ�ص�ֵ
     * @param root ������Ԫ��
     * @param eName ����ڲ���Ԫ�ص�Ŀ¼���
     * @return  Ԫ�ص�ֵ
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
     * �õ�һ����Ԫ�ص�
     * @param root ������Ԫ��
     * @param eName ����ڲ���Ԫ�ص�Ŀ¼���
     * @return  Ԫ��
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
     * �õ������Ԫ�ص�����
     * @param root ������Ԫ��
     * @param eName ����ڲ���Ԫ�ص�Ŀ¼���
     * @return  ���Ԫ������
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
     * �õ�Ԫ���ض����Ƶ��ӽڵ������
     * @param root ������Ԫ��
     * @param TagName �ӽڵ�����
     * @return  ���Ԫ������
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
