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
 * <p>Title:XML通用处理 </p>
 *
 * <p>Description:XML通用处理 </p>
 *
 * <p>Copyright: Copyright (c) 20050329</p>
 *
 * <p>Company:syax </p>
 *
 * @author 王运升
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
			System.out.print("解析出的数据："+str);
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
				System.out.print("解析出的数据："+st);
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
     * 获取Document中第一个给定节点的文本信息
     * 用于取单个节点的值，该节点下没有孩子节点
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
	    	//构造头信息
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
     * 获取Document中第一个给定节点的文本信息
     * 用于取单个节点的值，该节点下没有孩子节点
     * @param doc Document
     * @return String 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static String getNodeValue(Document doc, String nodeName)

    {
    	NodeList node =  doc.getElementsByTagName(nodeName);
		//取得当前节点
    	Node nod = node.item(0);
		NodeList  nd =  nod.getChildNodes();
		//取的当前节点下的的文本节点
		Node d = nd.item(0); 
		//取文本节点的值
		String str = d.getNodeValue();
		return str;
    }
    
    
    /**
     * 获取Document中给定节点的所有孩子节点的值
     * 用某个节点下有很多孩子节点的情况
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
