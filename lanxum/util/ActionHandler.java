package com.lscdz.util;



import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lscdz.util.seq.SeqValue;
import com.lscdz.util.seq.impl.SeqValueImpl;

import yangjian.frame.util.FrameException;

/**
 * ��������ӿ��࣬���б��ķ�ʽ�Ľ��������඼��Ҫʵ�ָýӿ�
 * @author jasper
 * 2013/3/12
 */
public abstract class ActionHandler implements IActionHandler{
	//XMLת������
	protected XmlVoConverter xmlVoConverter=new XmlVoConverter();
	//���ش���
	protected String rtnBizCode=RtnCodeMessage.Success.Code;
	//������Ϣ
	protected String rtnBizMessage=RtnCodeMessage.Success.Message;
	//���кŹ���
	protected SeqValue seqValue=new SeqValueImpl();
	/**
	 * ����ͻ����ķ��������� XML ��������
	 * @param msg
	 * @return
	 */
	public abstract StringBuffer processMsg(ClientMessage msg) throws FrameException;
	
	/**
	 * ��ȡ���к� seqNameΪҪ���ʵ����к�ȫ��
	 * @param seqName
	 * @return
	 */
	protected String getSeqValue(String seqName){
		return seqValue.getSeqValue(seqName);
	}
	/**
	 * ����XML��ת��ΪVo
	 * @param node
	 * @param vo
	 * @throws FrameException
	 */
	protected void ConvertXmlToVo(ClientMessage msg, Object vo) throws FrameException {
		try {
			xmlVoConverter.convertDomNodeToVo(msg.getDoc().getDocumentElement(), vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("����XML��������ϵ����Ա");
		}
	}
	/**
	 * ����XML��ת��ΪVo
	 * @param node
	 * @param vo
	 * @throws FrameException
	 */
	protected void xmlTextToVo(String xmlStr, Object vo) throws FrameException {
		try {
			xmlVoConverter.xmlTextToVo(xmlStr, vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("����XML��������ϵ����Ա");
		}
	}
	
	/**
	 * vo
	 * @param obj
	 * @return
	 * @throws FrameException
	 */
	protected String ConvertVoToXml(Object vo) throws FrameException {
		String returnStr="";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element element = doc.createElement("BODY");
			element.setAttribute("RtnBizCode", this.getRtnBizCode());
			element.setAttribute("RtnBizMessage", this.getRtnBizMessage());
			if(vo!=null){
				xmlVoConverter.convertVoToDomNode(vo,element);
			}
			//documentתStirng�����
			DOMSource source = new DOMSource(element);
	        StringWriter writer = new StringWriter();
	        Result result = new StreamResult(writer);
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");//ȥ��xml��
	        transformer.transform(source, result);
	        returnStr=writer.getBuffer().toString();
	        
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("VoתXML��������ϵ����Ա");
		}        
		return returnStr;
	}
	/**
	 * vo
	 * @param obj
	 * @return
	 * @throws FrameException
	 */
	protected String voToXmlText(Object vo,ClientMessage msg)throws FrameException {
		String returnStr="";
		try {
			returnStr=xmlVoConverter.voToXmlText(vo,msg.getType(), msg.getAction(), msg.getPrivilege());
		}catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("VoתXML��������ϵ����Ա");
		}        
		return returnStr;
	}
	/**
	 * ��ȡĳ���ڵ��µ� tagName ֵ
	 * @param nodeParent
	 * @param tagName
	 * @return
	 */
	protected  String getValueByTag(Node nodeParent, String tagName) throws FrameException {
		// ��ȡ��������
		NodeList nl = nodeParent.getChildNodes();
		try {
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node.getFirstChild() == null)
					continue;
				if (((Element) node).getTagName().equalsIgnoreCase(tagName)) {
					if (node.getFirstChild().getNodeType() == Node.CDATA_SECTION_NODE) {
						CDATASection cdataNode = (CDATASection) node.getFirstChild();
						return cdataNode.getNodeValue();
					} else {
						return node.getFirstChild().getNodeValue();
					}
				}
			}
		} catch (Exception e) {
			throw new FrameException("��ȡ�ڵ�ֵʧ�ܣ�����ϵ����Ա");
		}
		return null;
	}
	/**
	 * ��ȡ Document ��ָ�� tagName ��ֵ
	 * @param doc
	 * @param tagName
	 * @return
	 */
	protected  String getValueByTag(Document doc, String tagName) {
		NodeList nl = doc.getElementsByTagName(tagName);
		if (nl.getLength() > 0) {
			if (nl.item(0).getFirstChild() != null) {
				Node node = nl.item(0).getFirstChild();
				if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
					CDATASection cdataNode = (CDATASection) node;
					return cdataNode.getNodeValue();
				} else {
					return node.getNodeValue();
				}
			}
		}
		return null;
	}
	protected final class RtnCodeMessage{
		public final class Success{
			public final static String Code = "Success";
			public final static String Message = "�ɹ�";
		}
		public final class Error_1001{
			public final static String Code = "NoQueryResult";
			public final static String Message = "��ѯ�޽��";
		}

		public final class Error_1002{
			public final static String Code = "DeleteFail";
			public final static String Message = "ɾ��ʧ��";
		}

		public final class Error_1003{
			public final static String Code = "UpdateFail";
			public final static String Message = "����ʧ��";
		}

		public final class Error_1004{
			public final static String Code = "BusinessValidateFail";
			public final static String Message = "ҵ��У�鲻ͨ��";
		}
		
		public final class Error_1005{
			public final static String Code = "SystemFail";
			public final static String Message = "ϵͳ�쳣";
		}
		
		public final class Error_1006{
			public final static String Code = "NoPermission";
			public final static String Message = "��û�з��ʸ�ҵ��Ĳ���Ȩ��";
		}
		
		public final class Error_1007{
			public final static String Code = "WrokFlowFail";
			public final static String Message = "��������ת�쳣";
		}
		
		public final class Error_1008{
			public final static String Code = "SaveFail";
			public final static String Message = "����ʧ��";
		}
		
		//�Զ����쳣 message����д
		public final class Error_9999{
			public final static String Code = "CustomError";
			public final static  String Message = "�Զ����쳣";
		}
	}

	public String getRtnBizCode() {
		return rtnBizCode;
	}

	public void setRtnBizCode(String rtnBizCode) {
		this.rtnBizCode = rtnBizCode;
	}

	public String getRtnBizMessage() {
		return rtnBizMessage;
	}

	public void setRtnBizMessage(String rtnBizMessage) {
		this.rtnBizMessage = rtnBizMessage;
	}
	
}
