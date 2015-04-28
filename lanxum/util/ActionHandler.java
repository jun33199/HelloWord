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
 * 交互处理接口类，所有报文方式的交互处理类都需要实现该接口
 * @author jasper
 * 2013/3/12
 */
public abstract class ActionHandler implements IActionHandler{
	//XML转换工具
	protected XmlVoConverter xmlVoConverter=new XmlVoConverter();
	//返回代码
	protected String rtnBizCode=RtnCodeMessage.Success.Code;
	//返回消息
	protected String rtnBizMessage=RtnCodeMessage.Success.Message;
	//序列号工具
	protected SeqValue seqValue=new SeqValueImpl();
	/**
	 * 处理客户报文方法，返回 XML 报文内容
	 * @param msg
	 * @return
	 */
	public abstract StringBuffer processMsg(ClientMessage msg) throws FrameException;
	
	/**
	 * 获取序列号 seqName为要访问的序列号全名
	 * @param seqName
	 * @return
	 */
	protected String getSeqValue(String seqName){
		return seqValue.getSeqValue(seqName);
	}
	/**
	 * 解析XML并转换为Vo
	 * @param node
	 * @param vo
	 * @throws FrameException
	 */
	protected void ConvertXmlToVo(ClientMessage msg, Object vo) throws FrameException {
		try {
			xmlVoConverter.convertDomNodeToVo(msg.getDoc().getDocumentElement(), vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("解析XML出错，请联系管理员");
		}
	}
	/**
	 * 解析XML并转换为Vo
	 * @param node
	 * @param vo
	 * @throws FrameException
	 */
	protected void xmlTextToVo(String xmlStr, Object vo) throws FrameException {
		try {
			xmlVoConverter.xmlTextToVo(xmlStr, vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("解析XML出错，请联系管理员");
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
			//document转Stirng并输出
			DOMSource source = new DOMSource(element);
	        StringWriter writer = new StringWriter();
	        Result result = new StreamResult(writer);
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");//去掉xml跟
	        transformer.transform(source, result);
	        returnStr=writer.getBuffer().toString();
	        
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("Vo转XML出错，请联系管理员");
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
			throw new FrameException("Vo转XML出错，请联系管理员");
		}        
		return returnStr;
	}
	/**
	 * 获取某个节点下的 tagName 值
	 * @param nodeParent
	 * @param tagName
	 * @return
	 */
	protected  String getValueByTag(Node nodeParent, String tagName) throws FrameException {
		// 获取所有子项
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
			throw new FrameException("获取节点值失败，请联系管理员");
		}
		return null;
	}
	/**
	 * 获取 Document 中指定 tagName 的值
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
			public final static String Message = "成功";
		}
		public final class Error_1001{
			public final static String Code = "NoQueryResult";
			public final static String Message = "查询无结果";
		}

		public final class Error_1002{
			public final static String Code = "DeleteFail";
			public final static String Message = "删除失败";
		}

		public final class Error_1003{
			public final static String Code = "UpdateFail";
			public final static String Message = "更新失败";
		}

		public final class Error_1004{
			public final static String Code = "BusinessValidateFail";
			public final static String Message = "业务校验不通过";
		}
		
		public final class Error_1005{
			public final static String Code = "SystemFail";
			public final static String Message = "系统异常";
		}
		
		public final class Error_1006{
			public final static String Code = "NoPermission";
			public final static String Message = "您没有访问该业务的操作权限";
		}
		
		public final class Error_1007{
			public final static String Code = "WrokFlowFail";
			public final static String Message = "工作流流转异常";
		}
		
		public final class Error_1008{
			public final static String Code = "SaveFail";
			public final static String Message = "保存失败";
		}
		
		//自定义异常 message可重写
		public final class Error_9999{
			public final static String Code = "CustomError";
			public final static  String Message = "自定义异常";
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
