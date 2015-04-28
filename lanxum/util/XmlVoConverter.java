package com.lscdz.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lscdz.hlwdsj.application.messageGroup.vo.inner.TextInner;
import com.lscdz.hlwdsj.application.messageGroup.vo.request.AddMessageGroupRequest;


/**
 * Xml <-> Vo 转换类
 * 说明：处理的并不全
 * Java内部非容器类只支持： java.lang.String/java.sql.Timestamp/java.util.Date/java.math.BigDecimal
 * 						  java.lang.Long/java.lang.Integer/java.lang.Boolean/long/int/boolean
 * Java内部容器类只支持 ：List
 * 
 * @author jasper
 * 2014/03/13
 */
public class XmlVoConverter {
	// 用于转换时间格式
	private  final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * XML转Vo
	 * 
	 * @param node
	 * @param obj
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public  void convertDomNodeToVo(Node node, Object obj) throws Exception {
		NodeList childNodes = node.getChildNodes();
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node nodeItem = childNodes.item(i);
			for (int j = 0; j < methods.length; j++) {
				Method m = methods[j];
				if (!m.getName().startsWith("set")) continue;
				// 判断方法名是否与节点名一致
				if (m.getName().substring(3).equalsIgnoreCase(nodeItem.getNodeName())) {
					Class[] parameterTypes = m.getParameterTypes();
					if (parameterTypes.length != 1) {
						continue;
					}
					String parameterName = parameterTypes[0].getName();
					// 获取 node value
					String nodeValue = getNodeValue(nodeItem);
					// 处理 list
					if (parameterName.equalsIgnoreCase("java.util.List")) {
						convertDomNodeToList(nodeItem, parameterName, obj, m);
						continue;
					}
					//如果还存在子节点说明此节点不是数据节点
					if(nodeItem.getChildNodes().getLength()>0 && parameterName.contains("com.")){
						getInnerObj(obj, m,nodeItem,parameterName);
					}
					// 检查 null
					if (nodeValue == null ) {
						//m.invoke(obj, new Object[] { null });
						continue;
					}
					// 最后按 Java 类型和简单类型处理
					if (!convertDomNodeToVoAttributes(parameterName, nodeValue, obj, m)) {
						throw new Exception("尚未支持的类型:" + parameterName);
					}
				}
			}
		}
	}
	/**
	 * 处理内部vo
	 * @param obj
	 * @param m
	 * @param nodeInerItem
	 * @param parameterName
	 * @throws Exception
	 */
	private void getInnerObj(Object obj, Method m,Node nodeInerItem,String parameterName) throws Exception{
		Class itemClass = Class.forName(parameterName);
		Object objInner = itemClass.newInstance();
		convertDomNodeToVo(nodeInerItem,objInner);
		m.invoke(obj, (Object[]) new Object[] { objInner });
	}
	/**
	 * 获取 Node value
	 * @param nodeItem
	 * @return
	 */
	private  String getNodeValue(Node nodeItem) {
		if (nodeItem.getFirstChild() != null &&
				nodeItem.getFirstChild().getNodeType() == Node.CDATA_SECTION_NODE) {
			CDATASection cdataNode = (CDATASection) nodeItem.getFirstChild();
			return cdataNode.getNodeValue() == null ? null
					: cdataNode.getNodeValue();
		} else {
			return nodeItem.getFirstChild() == null ? null
					: nodeItem.getFirstChild().getNodeValue();
		}
	}
	
	/**
	 * 转换 XML 到 List
	 * @param nodeItem
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private  void convertDomNodeToList(Node nodeItem,
			String parameterName, Object obj, Method m)
			throws Exception {
		// 获取所有子节点
		NodeList nodeList = nodeItem.getChildNodes();
		List list = new ArrayList();
		//去掉set
		String listType=m.getName().substring(3);
		//首字母大小写转换
		listType = listType.replace(listType.charAt(0), String.valueOf(listType.charAt(0)).toLowerCase().toCharArray()[0]);
		ParameterizedType pt = null;
		try {
			pt = (ParameterizedType) obj.getClass().getDeclaredField(listType).getGenericType();
		} catch (Exception e) {
			throw new Exception("List类型"+listType+"没有指定泛型不能进行解析");
		}  
		if(pt==null)return;
		Field field= obj.getClass().getDeclaredField(listType);
		
//		ParameterizedType pt = (ParameterizedType)field.getGenericType();
//		System.out.println(pt.getActualTypeArguments().length);  
//		System.out.println(pt.getActualTypeArguments()[0]); 
		//判断泛型类型是否为null 不进行解析
		
		// 循环处理
		for (int i = 0;i < nodeList.getLength();i++) {
			Node nodeSubItem = nodeList.item(i);
			//String itemClassName = pt.getActualTypeArguments()[0].toString();//nodeSubItem.getNodeName();
			Class itemClass = (Class)pt.getActualTypeArguments()[0];
			String itemClassName=itemClass.getName();
			String nodeSubValue = getNodeValue(nodeSubItem);
			Object objResultItem = convertDomNodeToSimpleJavaObject(itemClassName, nodeSubValue, obj, m);
			if(objResultItem!=null){
				// 检查 List 中是否为 Java 简单类型
				list.add(objResultItem);
			}else{
				//调整位置 简单类型不能实例化会报错
				Object objNewItem= itemClass.newInstance();
				convertDomNodeToVo(nodeSubItem, objNewItem);
				list.add(objNewItem);
			}
			
		}
		m.invoke(obj, new Object[] { list });
	}

	/**
	 * 转换 XML 为 Java 类型或简单类型
	 * @param parameterName
	 * @param nodeValue
	 * @return
	 * @throws Exception
	 */
	private  Object convertDomNodeToSimpleJavaObject(String parameterName, String nodeValue, Object obj, Method m)
			throws Exception {
		if (parameterName.equalsIgnoreCase("java.lang.String")) {
			return nodeValue;
		} else if (parameterName.equals("java.sql.Timestamp")) {
			// 时间类型 2013-03-13 16:02:33
			try {
				return new Timestamp(timeFormat.parse(nodeValue).getTime());
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.util.Date")) {
			// 时间类型 2013-03-13 16:02:33
			try {
				return timeFormat.parse(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.math.BigDecimal")) {
			try {
				return new BigDecimal(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.lang.Long")) {
			try {
				return new Long(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.lang.Integer")) {
			try {
				return new Integer(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.lang.Boolean")) {
			try {
				return new Boolean(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		}
		return null;
	}
	/**
	 * 转换 XML 为 VO的 Java 类型或简单类型 属性
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @return
	 * @throws Exception
	 */
	private  boolean convertDomNodeToVoAttributes(
			String parameterName, String nodeValue, Object obj, Method m)
			throws Exception {
		if (parameterName.equalsIgnoreCase("java.lang.String")) {
			m.invoke(obj, (Object[]) new String[] { nodeValue });
		} else if (parameterName.equals("java.sql.Timestamp")) {
			// 时间类型 2013-03-13 16:02:33
			try {
				Timestamp time = new Timestamp(timeFormat.parse(nodeValue).getTime());
				m.invoke(obj, (Object[]) new Timestamp[] { time });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.util.Date")) {
			// 时间类型 2013-03-13 16:02:33
			try {
				Date date = timeFormat.parse(nodeValue);
				m.invoke(obj, (Object[]) new Date[] { date });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.math.BigDecimal")) {
			try {
				BigDecimal big = new BigDecimal(nodeValue);
				m.invoke(obj, (Object[]) new BigDecimal[] { big });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("long")) {
			try {
				Long lo = new Long(nodeValue);
				m.invoke(obj, (Object[]) new Long[] { lo });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("int")) {
			try {
				Integer integer = new Integer(nodeValue);
				m.invoke(obj, (Object[]) new Integer[] { integer });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("boolean")) {
			try {
				Boolean bool = new Boolean(nodeValue);
				m.invoke(obj, (Object[]) new Boolean[]{ bool });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * 构造并抛出异常
	 * @param msg
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @throws Exception
	 */
	private  void throwException(String parameterName, String nodeValue, Object obj, Method m) throws Exception {
		// 获取类型
		String type = parameterName;
		int index = type.lastIndexOf(".");
		if (index > 0) {
			type = type.substring(index + 1);
		}
		
		throw new Exception("Unsupport " + type + "(" + nodeValue + ") at "
				+ obj.getClass().getName() + "." + m.getName() + "("
				+ parameterName + ")");
	}

	/**
	 * Vo转XML
	 * 
	 * @param vo
	 * @param node
	 * @throws Exception
	 */
	public  void convertVoToDomNode(Object vo, Node node) throws Exception {
		// 转换自定义类 vo，通过反射处理 get 方法
		Method[] methods = vo.getClass().getMethods();
		Method m = null;
		for (int i = 0; i < methods.length; i++) {
			m = methods[i];
			// 判断是否 get 方法
			if (m.getName().startsWith("get") && 
					m.getParameterTypes().length == 0) {
				// 获取结果
				Object methodReturnValue = m.invoke(vo, new Object[] {});
				String methodReturnTypeName = m.getReturnType().getName();
				String nodeName = m.getName().substring(3);
				// 不处理 getClass
				if (nodeName.equalsIgnoreCase("Class")) {
					continue;
				}
				// 如果 get 的结果为 null
				if (methodReturnValue == null) {
					appendNewNode(node, nodeName, null);
					continue;
				}
				// 判断并处理容器对象，暂只支持 List
				if (methodReturnTypeName.equalsIgnoreCase("java.util.List")) {
					convertListToDomNode(vo, node, nodeName, m);
					continue;
				} 
				// 尝试按照 java.xx 类型和简单类型进行处理
				if (!convertJavaSimpleObjectToDomNode(node, nodeName, methodReturnTypeName, methodReturnValue)) {
					// 不支持的类型将不处理
					// 对于自定义 Vo，递归调用
					Node itemNode = node.getOwnerDocument().createElement(nodeName);
					convertVoToDomNode(methodReturnValue, itemNode);
					node.appendChild(itemNode);
				}
			}
		}
	}
	
	/**
	 * 转换 java.xx 类型和简单类型对象
	 * @param node
	 * @param nodeName
	 * @param objTypeName
	 * @param objValue
	 * @return
	 */
	private  boolean convertJavaSimpleObjectToDomNode(Node node,
			String nodeName, String objTypeName, Object objValue) {
		if (objTypeName.equalsIgnoreCase("java.lang.String")) {
			// String
			appendNewNode(node, nodeName, (String) objValue);
		} else if (objTypeName.equalsIgnoreCase("java.sql.Timestamp")) {
			// java.sql.Timestamp
			appendNewNode(node, nodeName, timeFormat.format(new Date(
					((Timestamp) objValue).getTime())));
		} else if (objTypeName.equalsIgnoreCase("java.util.Date")) {
			// Date
			appendNewNode(node, nodeName, timeFormat.format(objValue));
		} else if (objTypeName.equalsIgnoreCase("java.math.BigDecimal")) {
			// BigDecimal
			appendNewNode(node, nodeName, ((BigDecimal) objValue).toString());
		} else if (objTypeName.equalsIgnoreCase("long")
				|| objTypeName.equalsIgnoreCase("int")
				|| objTypeName.equalsIgnoreCase("boolean")) {
			// Simple Type
			appendNewNode(node, nodeName, String.valueOf(objValue));
		} else {
			// 其它类型，直接返回 false
			return false;
		}
		
		return true;
	}

	/**
	 * 转换 List 容器对象，需要区分其中是简单 Java 对象还是 Vo
	 * @param obj
	 * @param node
	 * @param nodeName
	 * @param method
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private  void convertListToDomNode(Object obj, Node node,
			String nodeName, Method method) throws Exception {
		Node newNode = node.getOwnerDocument().createElement(nodeName);
		// 获取返回的 List 实例
		List list = (List) method.invoke(obj, new Object[] {});
		if (list != null && list.size() > 0) {
			// 获取 List 中对象的类名、类名简称
			String objClassName = list.get(0).getClass().getName();
			// 循环处理每一个对象
			for (int i = 0;i < list.size();i++) {
				Object objItem = list.get(i);
				// 创建node，名字为item 的类型名简称
				Node itemNode = node.getOwnerDocument().createElement(
						objItem.getClass().getSimpleName());
				// 如果容器中是 Java 类型，而非自定义类。如： List<String>
				// 则不能按照自定义类处理，需要直接处理，节点名是 Java 类型名。
				if (!convertJavaSimpleObjectToDomNode(itemNode, null, objClassName, objItem)) {
					// 对于自定义 Vo，递归调用
					convertVoToDomNode(objItem, itemNode);
				}
				newNode.appendChild(itemNode);
			}
		}
		node.appendChild(newNode);
	}

	/**
	 * 追加新 Node
	 * @param node
	 * @param nodeName
	 * @param nodeText
	 */
	private  void appendNewNode(Node node, String nodeName, String nodeText) {
		// 根据 nodeName 分别处理 List 等容器内嵌 Java 对象和自定义 Vo 属性
		if (nodeName != null) {
			// 非 List 等容器内嵌 Java 对象，首先根据属性名创建节点
			Node newNode = node.getOwnerDocument().createElement(nodeName);
			// 处理节点处理内容
			if (nodeText != null && nodeText.trim().length() > 0) {
				Node cdataNode = node.getOwnerDocument().createCDATASection(nodeName);
				cdataNode.setNodeValue(nodeText);
				newNode.appendChild(cdataNode);
			}
			// 追加节点
			node.appendChild(newNode);
		} else {
			// List 等容器内嵌 Java 对象，处理 List 的方法中已经创建节点，直接设置值就行
			Node cdataNode = node.getOwnerDocument().createCDATASection("Value");
			cdataNode.setNodeValue(nodeText);
			node.appendChild(cdataNode);
		}
	}	

	/**
	 * 将 Vo 转换成 Xml
	 * @param vo
	 * @param handler
	 * @param action
	 * @param privilege
	 * @return
	 * @throws Exception
	 */
	public  String voToXmlText(Object vo, String handler, String action, String privilege) throws Exception{
		// 拼 XML 头
		StringBuffer strBuf = new StringBuffer("<ClientMsg><RequestAction Type=\"");
		strBuf.append(handler).append("\" Action=\"").append(action).append("\" Privilege=\"")
			.append(privilege).append("\" /></ClientMsg>");

		// 生成 Document
		ByteArrayInputStream newBais = new ByteArrayInputStream(strBuf
				.toString().getBytes());
		Document newDoc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(newBais);
		Node parentNode = newDoc.getDocumentElement();

		// 将 Object 中的内容到Dom
		convertVoToDomNode(vo, parentNode);

		// 将 Dom 导成 Xml
		DOMSource source = new DOMSource(parentNode);
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");// 去掉xml根
		transformer.transform(source, result);
		return //"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				 writer.getBuffer().toString();
	}
	
	/**
	 * 将  Xml 转换成 Vo
	 * @param xmlText
	 * @param dataVo
	 */
	public  void xmlTextToVo(String xmlText, Object dataVo) {
		try {
			// 从 xml 转换到新 vo
			ByteArrayInputStream bais = new ByteArrayInputStream(
					xmlText.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(bais);

			Node node = doc.getDocumentElement();
			convertDomNodeToVo(node, dataVo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试 vo 转换 xml 再转回 vo
	 * @param vo
	 */
	public  boolean testVoToXmlToVo(Object dataVo) {
		try {
			// 先将传入 vo 转成 xml
			String xmlText = testVoToXmlText(dataVo);
			ByteArrayInputStream bais = new ByteArrayInputStream(
					xmlText.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(bais);

			// 重新 new 一个vo，从生成的 xml 转换到新 vo
			Object newDataVo = dataVo.getClass().newInstance();
			Node node = doc.getDocumentElement();
			convertDomNodeToVo(node, newDataVo);
			
			// 再把新 vo 转成 xml，比较两次的 xml text是否一样，确认转换是否成功
			String xmlTextAgain = testVoToXmlText(newDataVo);
			if (xmlText.equals(xmlTextAgain)) {
//				System.out.println(xmlText);
				return true;
			} else {
				System.out.println(xmlText);
				System.out.println(xmlTextAgain);
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 测试 xml 转换 vo 再转回 xml
	 * @param vo
	 */
	public  boolean testXmlToVoToXml(String xmlText, Object dataVo) {
		try {
			// 从 xml 转换到新 vo
			ByteArrayInputStream bais = new ByteArrayInputStream(
					xmlText.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(bais);

			Node node = doc.getDocumentElement();
			convertDomNodeToVo(node, dataVo);
			
			// 再把 vo 转成 xml，比较两次的 xml text是否一样，确认转换是否成功
			String xmlTextAgain = testVoToXmlText(dataVo);
			if (xmlText.equals(xmlTextAgain)) {
				return true;
			} else {
				System.out.println(xmlText);
				System.out.println(xmlTextAgain);
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 以 <root/>为根，vo->xml
	 * @param dataVo
	 * @return
	 * @throws Exception
	 */
	private  String testVoToXmlText(Object dataVo) throws Exception {
		String newXmlString = "<root/>";
		ByteArrayInputStream newBais = new ByteArrayInputStream(
				newXmlString.getBytes());
		Document newDoc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(newBais);
		Node rootNode = newDoc.getDocumentElement();
		XmlVoConverter xmlVoConverter=new XmlVoConverter();
		xmlVoConverter.convertVoToDomNode(dataVo, rootNode);
		DOMSource source = new DOMSource(rootNode);
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");// 去掉xml根
		transformer.transform(source, result);

		return writer.getBuffer().toString();
	}
	
	/**
	 * 单元测试
	 * @param args
	 */
//	public static void main(String[] args) throws Exception{
//		XmlVoConverter xmlVoConverter=new XmlVoConverter();
//		
//		String xmlText1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><dddlzh>wangcy</dddlzh><groupname>123123123</groupname><id>0</id><groupcode>0</groupcode><Inner><Id><![CDATA[123123]]></Id><Name><![CDATA[测试]]></Name><Phone><![CDATA[132465465]]></Phone></Inner><List><TextInner><Id>123</Id><Name>测试2222</Name><Phone>1325465</Phone></TextInner><TextInner><Id>1444423</Id><Name>测试44444</Name><Phone>135465</Phone></TextInner></List></root>";
//		AddMessageGroupRequest voReq=new AddMessageGroupRequest();
//		voReq.setDddlzh("234234");
//		voReq.setGroupcode(123);
//		TextInner inner=new TextInner();
//		inner.setName("测试");
//		voReq.setInner(inner);
//		String xmlText2 = xmlVoConverter.testVoToXmlText(voReq);
//		System.out.println(FetchXmlValueTool.formatXmlText(xmlText2));
//		AddMessageGroupRequest voReq3=new AddMessageGroupRequest();
//		xmlVoConverter.xmlTextToVo(xmlText2, voReq3);
//		
//		System.out.println(voReq3.getInner().getName());
//		
//		TestVo vo = new TestVo();
//		int id = 100;
//		Date showTime = new Date();
//		BigDecimal showData = new BigDecimal(100.2323);
//		String name = "测试 DataPostClient 调用。";
//		vo.setId(id);
//		vo.setShowTime(showTime);
//		vo.setShowData(showData);
//		vo.setName(name);
//		List dataList = new ArrayList();
//		for (int i = 0;i < 5;i++) {
//			TestSubVo svo = new TestSubVo();
//			svo.setSid(100+i);
//			svo.setSname("Name " + i);
//			svo.setTime(new Timestamp(System.currentTimeMillis()));
//			
//			dataList.add(svo);
//		}
//		vo.setDataList(dataList);
//		TestSubVo vo1 = new TestSubVo();
//		vo1.setSid(123123123);
//		vo.setVo1(vo1);
//		
//		
//		TestSubVo vo2 = new TestSubVo();
//		vo2.setSid(123123123);
//		vo2.setSname("测试ing");
//		vo.setVo2(vo2);
//		
//		List listYear = new ArrayList();
//		listYear.add("2008");
//		listYear.add("2009");
//		listYear.add("2010");
//		vo.setListYear(listYear);
//		
//		List listTime = new ArrayList();
//		listTime.add(new Timestamp(System.currentTimeMillis()));
//		vo.setListTime(listTime);
//		String xmlText = xmlVoConverter.testVoToXmlText(vo);
//		
//		System.out.println(FetchXmlValueTool.formatXmlText(xmlText));
//		
//		TestVo textVo = new TestVo() ;
//		xmlVoConverter.xmlTextToVo(xmlText, textVo);
//		
//		System.out.println(textVo.getId());
//		System.out.println(textVo.getName());
//		System.out.println(textVo.getShowData());
//		System.out.println(textVo.getShowTime());
//		for(int i=0;i<textVo.getDataList().size();i++){
//			TestSubVo subVo=(TestSubVo)textVo.getDataList().get(i);
//			System.out.println(subVo.getSid());
//			System.out.println(subVo.getSname());
//			System.out.println(subVo.getTime());
//		}
//		for(int i=0;i<textVo.getListYear().size();i++){
//			System.out.println(textVo.getListYear().get(i));
//		}
//		
//		for(int i=0;i<textVo.getListTime().size();i++){
//			System.out.println(textVo.getListTime().get(i));
//		}
//		TestSubVo subVo1=textVo.getVo1();
//		System.out.println(subVo1.getSid());
//		System.out.println();
//		
//		TestSubVo subVo2=textVo.getVo2();
//		System.out.println(subVo2.getSid());
//		System.out.println(subVo2.getSname());
//	}
	
}


class TestSubVo{
	private int sid;
	private String sname;
	private Timestamp time;
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
}
class TestVo{
	private int id;
	private Date showTime;
	private BigDecimal showData;
	private String name;
	private List<TestSubVo> dataList;
	private List<String> listYear=new ArrayList<String>();
	private List<Timestamp> listTime;
	private TestSubVo vo1;
	private TestSubVo vo2;
	
	public TestSubVo getVo1() {
		return vo1;
	}
	public void setVo1(TestSubVo vo1) {
		this.vo1 = vo1;
	}
	public TestVo() {
		
	}
	
	public List getListYear() {
		return listYear;
	}
	public void setListYear(List listYear) {
		this.listYear = listYear;
	}
	public List getListTime() {
		return listTime;
	}
	public void setListTime(List listTime) {
		this.listTime = listTime;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public BigDecimal getShowData() {
		return showData;
	}
	public void setShowData(BigDecimal showData) {
		this.showData = showData;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TestSubVo getVo2() {
		return vo2;
	}
	public void setVo2(TestSubVo vo2) {
		this.vo2 = vo2;
	}
	
}


