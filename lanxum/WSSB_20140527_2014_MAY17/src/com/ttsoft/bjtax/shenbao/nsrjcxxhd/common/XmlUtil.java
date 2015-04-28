package com.ttsoft.bjtax.shenbao.nsrjcxxhd.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {
	public static void main(String[] args) {
		String xml = "<?xml version='1.0' encoding='utf-8' ?><nsrjcxxhd><zcdzyb><![CDATA[100085]]></zcdzyb><zcdzlxdh><![CDATA[62913475]]></zcdzlxdh><jydz><![CDATA[北京市海淀区清河西三旗]]></jydz><jydzyb><![CDATA[100085]]></jydzyb><jydzlxdm><![CDATA[62916020]]></jydzlxdm><cwfzrxm><![CDATA[娄亚芹]]></cwfzrxm><cwfzrzjlxdm><![CDATA[02]]></cwfzrzjlxdm><cwfzrzjhm><![CDATA[110108195502252748]]></cwfzrzjhm><cwfzrgddh><![CDATA[62916020]]></cwfzrgddh><cwfzryddh><![CDATA[13701326828]]></cwfzryddh><cwfzrdzyx><![CDATA[]]></cwfzrdzyx><bsrxm><![CDATA[张中华]]></bsrxm><bsrzjlxdm><![CDATA[02]]></bsrzjlxdm><bsrzjhm><![CDATA[110108195811022807]]></bsrzjhm><bsrgddh><![CDATA[62916020]]></bsrgddh><bsryddh><![CDATA[13681000273]]></bsryddh><bsrdzyx><![CDATA[]]></bsrdzyx><swdlmc><![CDATA[]]></swdlmc><swdlswdjzh><![CDATA[]]></swdlswdjzh><swdlgddh><![CDATA[]]></swdlgddh><swdldzyx><![CDATA[]]></swdldzyx></nsrjcxxhd>";
		parseXmlToMap(xml);
	}
	
	public static Map parseXmlToMap(String xml) {
		Map rtnMap = new HashMap();
		ByteArrayInputStream bais = null;
		Document doc = null;
		try {
			bais = new ByteArrayInputStream(xml.getBytes("UTF-8"));

			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
			Node root = doc.getDocumentElement();
			NodeList nodes = root.getChildNodes();
			String key = null;
			String value = null;
			Node node = null;
			int len = nodes.getLength();
			for (int i = 0; i < len; i++) {
				key = "";
				value = "";
                node = nodes.item(i);
                key = node.getNodeName();
                if(node.getFirstChild() != null) {
                	value = node.getFirstChild().getNodeValue();
                }
                rtnMap.put(key, value);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} finally {
			try {
				if(bais != null) {
					bais.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rtnMap;
	}
}