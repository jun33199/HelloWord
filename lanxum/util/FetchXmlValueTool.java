package com.lscdz.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import yangjian.frame.util.FrameException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * 从 XML 中获取值工具类
 * @author jasper
 * 2013/03/15
 */
public class FetchXmlValueTool {

	/**
	 * 获取某个节点下的 tagName 值 
	 * @param nodeParent
	 * @param tagName
	 * @return
	 * @throws FrameException
	 */
	public static String getValueByTag(Node nodeParent, String tagName) {
		// 获取所有子项
		NodeList nl = nodeParent.getChildNodes();
		try {
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node.getFirstChild() == null) continue;
				if (((Element)node).getTagName().equalsIgnoreCase(tagName)) {
					return node.getFirstChild().getNodeValue();
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 获取 Document 中指定 tagName 的值
	 * @param doc
	 * @param tagName
	 * @return
	 */
	public static String getValueByTag(Document doc, String tagName) {
		NodeList nl = doc.getElementsByTagName(tagName);
		if (nl.getLength() > 0) {
			if (nl.item(0).getFirstChild() != null)
				return nl.item(0).getFirstChild().getNodeValue();
		}
		return null;
	}
	
    public static String formatXmlText(String unformattedXml) {  
        try {  
            final Document document = parseXmlFile(unformattedXml);  
            OutputFormat format = new OutputFormat(document);  
            format.setLineWidth(65);  
            format.setIndenting(true);  
            format.setIndent(2);  
            Writer out = new StringWriter();  
            XMLSerializer serializer = new XMLSerializer(out, format);  
            serializer.serialize(document);  
            return out.toString();  
        } catch (Exception e) {  
            return "formatXmlText() failed!";
        }  
    }  
  
    private static Document parseXmlFile(String in) {  
        try {  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            DocumentBuilder db = dbf.newDocumentBuilder();  
            InputSource is = new InputSource(new StringReader(in));  
            return db.parse(is);  
        } catch (ParserConfigurationException e) {  
            throw new RuntimeException(e);  
        } catch (SAXException e) {  
            throw new RuntimeException(e);  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
//    public static void main(String[] args) throws Exception{  
//        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><PARAM><DBID>35</DBID><SEQUENCE>atgtca</SEQUENCE><MAXNS>10</MAXNS><MINIDENTITIES>90</MINIDENTITIES><MAXEVALUE>10</MAXEVALUE><USERNAME>admin</USERNAME><PASSWORD>111111</PASSWORD><TYPE>P</TYPE><RETURN_TYPE>2</RETURN_TYPE></PARAM>";//未格式化前的xml  
//        System.out.println(format(s));
//    }
}
