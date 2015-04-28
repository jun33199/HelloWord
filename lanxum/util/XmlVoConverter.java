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
 * Xml <-> Vo ת����
 * ˵��������Ĳ���ȫ
 * Java�ڲ���������ֻ֧�֣� java.lang.String/java.sql.Timestamp/java.util.Date/java.math.BigDecimal
 * 						  java.lang.Long/java.lang.Integer/java.lang.Boolean/long/int/boolean
 * Java�ڲ�������ֻ֧�� ��List
 * 
 * @author jasper
 * 2014/03/13
 */
public class XmlVoConverter {
	// ����ת��ʱ���ʽ
	private  final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * XMLתVo
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
				// �жϷ������Ƿ���ڵ���һ��
				if (m.getName().substring(3).equalsIgnoreCase(nodeItem.getNodeName())) {
					Class[] parameterTypes = m.getParameterTypes();
					if (parameterTypes.length != 1) {
						continue;
					}
					String parameterName = parameterTypes[0].getName();
					// ��ȡ node value
					String nodeValue = getNodeValue(nodeItem);
					// ���� list
					if (parameterName.equalsIgnoreCase("java.util.List")) {
						convertDomNodeToList(nodeItem, parameterName, obj, m);
						continue;
					}
					//����������ӽڵ�˵���˽ڵ㲻�����ݽڵ�
					if(nodeItem.getChildNodes().getLength()>0 && parameterName.contains("com.")){
						getInnerObj(obj, m,nodeItem,parameterName);
					}
					// ��� null
					if (nodeValue == null ) {
						//m.invoke(obj, new Object[] { null });
						continue;
					}
					// ��� Java ���ͺͼ����ʹ���
					if (!convertDomNodeToVoAttributes(parameterName, nodeValue, obj, m)) {
						throw new Exception("��δ֧�ֵ�����:" + parameterName);
					}
				}
			}
		}
	}
	/**
	 * �����ڲ�vo
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
	 * ��ȡ Node value
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
	 * ת�� XML �� List
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
		// ��ȡ�����ӽڵ�
		NodeList nodeList = nodeItem.getChildNodes();
		List list = new ArrayList();
		//ȥ��set
		String listType=m.getName().substring(3);
		//����ĸ��Сдת��
		listType = listType.replace(listType.charAt(0), String.valueOf(listType.charAt(0)).toLowerCase().toCharArray()[0]);
		ParameterizedType pt = null;
		try {
			pt = (ParameterizedType) obj.getClass().getDeclaredField(listType).getGenericType();
		} catch (Exception e) {
			throw new Exception("List����"+listType+"û��ָ�����Ͳ��ܽ��н���");
		}  
		if(pt==null)return;
		Field field= obj.getClass().getDeclaredField(listType);
		
//		ParameterizedType pt = (ParameterizedType)field.getGenericType();
//		System.out.println(pt.getActualTypeArguments().length);  
//		System.out.println(pt.getActualTypeArguments()[0]); 
		//�жϷ��������Ƿ�Ϊnull �����н���
		
		// ѭ������
		for (int i = 0;i < nodeList.getLength();i++) {
			Node nodeSubItem = nodeList.item(i);
			//String itemClassName = pt.getActualTypeArguments()[0].toString();//nodeSubItem.getNodeName();
			Class itemClass = (Class)pt.getActualTypeArguments()[0];
			String itemClassName=itemClass.getName();
			String nodeSubValue = getNodeValue(nodeSubItem);
			Object objResultItem = convertDomNodeToSimpleJavaObject(itemClassName, nodeSubValue, obj, m);
			if(objResultItem!=null){
				// ��� List ���Ƿ�Ϊ Java ������
				list.add(objResultItem);
			}else{
				//����λ�� �����Ͳ���ʵ�����ᱨ��
				Object objNewItem= itemClass.newInstance();
				convertDomNodeToVo(nodeSubItem, objNewItem);
				list.add(objNewItem);
			}
			
		}
		m.invoke(obj, new Object[] { list });
	}

	/**
	 * ת�� XML Ϊ Java ���ͻ������
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
			// ʱ������ 2013-03-13 16:02:33
			try {
				return new Timestamp(timeFormat.parse(nodeValue).getTime());
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.util.Date")) {
			// ʱ������ 2013-03-13 16:02:33
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
	 * ת�� XML Ϊ VO�� Java ���ͻ������ ����
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
			// ʱ������ 2013-03-13 16:02:33
			try {
				Timestamp time = new Timestamp(timeFormat.parse(nodeValue).getTime());
				m.invoke(obj, (Object[]) new Timestamp[] { time });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.util.Date")) {
			// ʱ������ 2013-03-13 16:02:33
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
	 * ���첢�׳��쳣
	 * @param msg
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @throws Exception
	 */
	private  void throwException(String parameterName, String nodeValue, Object obj, Method m) throws Exception {
		// ��ȡ����
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
	 * VoתXML
	 * 
	 * @param vo
	 * @param node
	 * @throws Exception
	 */
	public  void convertVoToDomNode(Object vo, Node node) throws Exception {
		// ת���Զ����� vo��ͨ�����䴦�� get ����
		Method[] methods = vo.getClass().getMethods();
		Method m = null;
		for (int i = 0; i < methods.length; i++) {
			m = methods[i];
			// �ж��Ƿ� get ����
			if (m.getName().startsWith("get") && 
					m.getParameterTypes().length == 0) {
				// ��ȡ���
				Object methodReturnValue = m.invoke(vo, new Object[] {});
				String methodReturnTypeName = m.getReturnType().getName();
				String nodeName = m.getName().substring(3);
				// ������ getClass
				if (nodeName.equalsIgnoreCase("Class")) {
					continue;
				}
				// ��� get �Ľ��Ϊ null
				if (methodReturnValue == null) {
					appendNewNode(node, nodeName, null);
					continue;
				}
				// �жϲ���������������ֻ֧�� List
				if (methodReturnTypeName.equalsIgnoreCase("java.util.List")) {
					convertListToDomNode(vo, node, nodeName, m);
					continue;
				} 
				// ���԰��� java.xx ���ͺͼ����ͽ��д���
				if (!convertJavaSimpleObjectToDomNode(node, nodeName, methodReturnTypeName, methodReturnValue)) {
					// ��֧�ֵ����ͽ�������
					// �����Զ��� Vo���ݹ����
					Node itemNode = node.getOwnerDocument().createElement(nodeName);
					convertVoToDomNode(methodReturnValue, itemNode);
					node.appendChild(itemNode);
				}
			}
		}
	}
	
	/**
	 * ת�� java.xx ���ͺͼ����Ͷ���
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
			// �������ͣ�ֱ�ӷ��� false
			return false;
		}
		
		return true;
	}

	/**
	 * ת�� List ����������Ҫ���������Ǽ� Java ������ Vo
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
		// ��ȡ���ص� List ʵ��
		List list = (List) method.invoke(obj, new Object[] {});
		if (list != null && list.size() > 0) {
			// ��ȡ List �ж�����������������
			String objClassName = list.get(0).getClass().getName();
			// ѭ������ÿһ������
			for (int i = 0;i < list.size();i++) {
				Object objItem = list.get(i);
				// ����node������Ϊitem �����������
				Node itemNode = node.getOwnerDocument().createElement(
						objItem.getClass().getSimpleName());
				// ����������� Java ���ͣ������Զ����ࡣ�磺 List<String>
				// ���ܰ����Զ����ദ����Ҫֱ�Ӵ����ڵ����� Java ��������
				if (!convertJavaSimpleObjectToDomNode(itemNode, null, objClassName, objItem)) {
					// �����Զ��� Vo���ݹ����
					convertVoToDomNode(objItem, itemNode);
				}
				newNode.appendChild(itemNode);
			}
		}
		node.appendChild(newNode);
	}

	/**
	 * ׷���� Node
	 * @param node
	 * @param nodeName
	 * @param nodeText
	 */
	private  void appendNewNode(Node node, String nodeName, String nodeText) {
		// ���� nodeName �ֱ��� List ��������Ƕ Java ������Զ��� Vo ����
		if (nodeName != null) {
			// �� List ��������Ƕ Java �������ȸ��������������ڵ�
			Node newNode = node.getOwnerDocument().createElement(nodeName);
			// ����ڵ㴦������
			if (nodeText != null && nodeText.trim().length() > 0) {
				Node cdataNode = node.getOwnerDocument().createCDATASection(nodeName);
				cdataNode.setNodeValue(nodeText);
				newNode.appendChild(cdataNode);
			}
			// ׷�ӽڵ�
			node.appendChild(newNode);
		} else {
			// List ��������Ƕ Java ���󣬴��� List �ķ������Ѿ������ڵ㣬ֱ������ֵ����
			Node cdataNode = node.getOwnerDocument().createCDATASection("Value");
			cdataNode.setNodeValue(nodeText);
			node.appendChild(cdataNode);
		}
	}	

	/**
	 * �� Vo ת���� Xml
	 * @param vo
	 * @param handler
	 * @param action
	 * @param privilege
	 * @return
	 * @throws Exception
	 */
	public  String voToXmlText(Object vo, String handler, String action, String privilege) throws Exception{
		// ƴ XML ͷ
		StringBuffer strBuf = new StringBuffer("<ClientMsg><RequestAction Type=\"");
		strBuf.append(handler).append("\" Action=\"").append(action).append("\" Privilege=\"")
			.append(privilege).append("\" /></ClientMsg>");

		// ���� Document
		ByteArrayInputStream newBais = new ByteArrayInputStream(strBuf
				.toString().getBytes());
		Document newDoc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(newBais);
		Node parentNode = newDoc.getDocumentElement();

		// �� Object �е����ݵ�Dom
		convertVoToDomNode(vo, parentNode);

		// �� Dom ���� Xml
		DOMSource source = new DOMSource(parentNode);
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");// ȥ��xml��
		transformer.transform(source, result);
		return //"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				 writer.getBuffer().toString();
	}
	
	/**
	 * ��  Xml ת���� Vo
	 * @param xmlText
	 * @param dataVo
	 */
	public  void xmlTextToVo(String xmlText, Object dataVo) {
		try {
			// �� xml ת������ vo
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
	 * ���� vo ת�� xml ��ת�� vo
	 * @param vo
	 */
	public  boolean testVoToXmlToVo(Object dataVo) {
		try {
			// �Ƚ����� vo ת�� xml
			String xmlText = testVoToXmlText(dataVo);
			ByteArrayInputStream bais = new ByteArrayInputStream(
					xmlText.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(bais);

			// ���� new һ��vo�������ɵ� xml ת������ vo
			Object newDataVo = dataVo.getClass().newInstance();
			Node node = doc.getDocumentElement();
			convertDomNodeToVo(node, newDataVo);
			
			// �ٰ��� vo ת�� xml���Ƚ����ε� xml text�Ƿ�һ����ȷ��ת���Ƿ�ɹ�
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
	 * ���� xml ת�� vo ��ת�� xml
	 * @param vo
	 */
	public  boolean testXmlToVoToXml(String xmlText, Object dataVo) {
		try {
			// �� xml ת������ vo
			ByteArrayInputStream bais = new ByteArrayInputStream(
					xmlText.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(bais);

			Node node = doc.getDocumentElement();
			convertDomNodeToVo(node, dataVo);
			
			// �ٰ� vo ת�� xml���Ƚ����ε� xml text�Ƿ�һ����ȷ��ת���Ƿ�ɹ�
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
	 * �� <root/>Ϊ����vo->xml
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
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");// ȥ��xml��
		transformer.transform(source, result);

		return writer.getBuffer().toString();
	}
	
	/**
	 * ��Ԫ����
	 * @param args
	 */
//	public static void main(String[] args) throws Exception{
//		XmlVoConverter xmlVoConverter=new XmlVoConverter();
//		
//		String xmlText1="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><dddlzh>wangcy</dddlzh><groupname>123123123</groupname><id>0</id><groupcode>0</groupcode><Inner><Id><![CDATA[123123]]></Id><Name><![CDATA[����]]></Name><Phone><![CDATA[132465465]]></Phone></Inner><List><TextInner><Id>123</Id><Name>����2222</Name><Phone>1325465</Phone></TextInner><TextInner><Id>1444423</Id><Name>����44444</Name><Phone>135465</Phone></TextInner></List></root>";
//		AddMessageGroupRequest voReq=new AddMessageGroupRequest();
//		voReq.setDddlzh("234234");
//		voReq.setGroupcode(123);
//		TextInner inner=new TextInner();
//		inner.setName("����");
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
//		String name = "���� DataPostClient ���á�";
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
//		vo2.setSname("����ing");
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


