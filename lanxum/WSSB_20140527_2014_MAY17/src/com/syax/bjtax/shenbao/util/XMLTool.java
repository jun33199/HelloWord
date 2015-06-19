package com.syax.bjtax.shenbao.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
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

import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;


/**
 *
 * <p>Title:XMLͨ�ô��� </p>
 *
 * <p>Description:XMLͨ�ô��� </p>
 *
 * <p>Copyright: Copyright (c) 20050329</p>
 *
 * <p>Company:syax </p>
 *
 * @author ������
 * @version 1.0
 */
public class XMLTool {


	public static void test(String xml)throws IOException {
		try {
			
			System.out.println("xml111111111");
			Element root = getRootElement(xml);
			/*
			System.out.println("xml2222222");
			root = getOneElement(root,"xsltVersion");
			System.out.println("xm3333333333");
			String str = getElementText(root);
			System.out.print("�����������ݣ�"+str);
			System.out.println("xm4444444");
			*/
			try {
				System.out.println("xml111111111");
				Document doc = getDoc(xml);
				System.out.println("xml2222222");
				NodeList node =  doc.getElementsByTagName("xsltVersion");
				System.out.println("xm3333333333");
				Node nod = node.item(0);
				
				NodeList  nd =  nod.getChildNodes();
				System.out.println("xm4444444");
				Node d = nd.item(0); 
				String st = d.getNodeValue();
				System.out.print("�����������ݣ�"+st);
				getNodeListValue(doc,"qysdsjmba");
				//x=xmlDoc.getElementsByTagName("title")[0];
                //y=x.childNodes[0];
                //txt=y.nodeValue;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     * ��ȡDocument�е�һ�������ڵ���ı���Ϣ
     * ����ȡ�����ڵ��ֵ���ýڵ���û�к��ӽڵ�
     * @param doc Document
     * @return String 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void parseXMLString(String xml, JmbaVO vo) throws IOException

    {
    	Document doc = null;
    	try {
			doc = getDoc(xml);
	    	//����ͷ��Ϣ
	        vo.setXsltVersion(getNodeValue(doc,"xsltVersion"));
	        vo.setYwlx(getNodeValue(doc,"ywlx"));
	        vo.setYwczlx(getNodeValue(doc,"ywczlx"));
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }	
	
    /**
     * ��ȡDocument�е�һ�������ڵ���ı���Ϣ
     * ����ȡ�����ڵ��ֵ���ýڵ���û�к��ӽڵ�
     * @param doc Document
     * @return String 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static String getNodeValue(Document doc, String nodeName)

    {
    	NodeList node =  doc.getElementsByTagName(nodeName);
		//ȡ�õ�ǰ�ڵ�
    	Node nod = node.item(0);
		NodeList  nd =  nod.getChildNodes();
		//ȡ�ĵ�ǰ�ڵ��µĵ��ı��ڵ�
		Node d = nd.item(0); 
		//ȡ�ı��ڵ��ֵ
		String str = d.getNodeValue();
		return str;
    }
    
    
    /**
     * ��ȡDocument�и����ڵ�����к��ӽڵ��ֵ
     * ��ĳ���ڵ����кܶຢ�ӽڵ�����
     * @param doc Document
     * @return List 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static List getNodeListValue(Document doc, String nodeName)

    {
    	List list = new ArrayList();
    	NodeList node =  doc.getElementsByTagName(nodeName);
    	Node nod = node.item(0);
    	NodeList nd = nod.getChildNodes();
    	String nodevalue;
        for (int i=0; i< nd.getLength(); i++){	
        	nodevalue = getNodeValue(doc,nd.item(i).getNodeName());
        	list.add(nodevalue);        	
        }
        return list;
    }   
	
    
    
    
    
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
