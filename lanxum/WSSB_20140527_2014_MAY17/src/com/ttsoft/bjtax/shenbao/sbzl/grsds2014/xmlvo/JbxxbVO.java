/**
 * @Title:       JbxxbVO.java
 * @Description: TODO
 * @date:        2014-11-7下午01:48:44
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Djzclx;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Gjbzhy;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Gjdq;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Sfzjlx;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Swjgzzjg;

/**
 * @Description: TODO 基本信息VO
 * @author: 	 Lijn
 * @time:        2014-11-7
 */
public class JbxxbVO extends YWRootVO implements XMLVOInterface 
{

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description：身份證件類型代碼表
	 */
	private List sfzjlxList = new ArrayList();
	
	/**
	 * Description：国籍
	 */
	private List gjList = new ArrayList();
	
	/**
	 * 国家标准行业
	 */
	private List gjbzhyList = new ArrayList();
	
	private List swjgzzjgList = new ArrayList();
	
	/**
	 * Description：登记注册类型
	 */
	private List djzclxList = new ArrayList();
	
	/**
	 * Description：企业信息
	 */
	private GrsdsQyxxVO qyxxvo = new GrsdsQyxxVO();
	
	/**
	 * Description：个人信息
	 */
	private GrsdsGrxxVO grxx = new GrsdsGrxxVO();
	
	
	//private List grListVOList = new ArrayList();
	
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>");
		sb.append(toXMLHead());
		
		
		sb.append(toXMLChilds());
		sb.append("</taxdoc>");
	     return sb.toString();
	}

	public String toXMLChilds() {
		StringBuffer sb = new StringBuffer();	
		
		sb.append("<sfzjlxList>");
		Iterator sfzjlxListIterator =sfzjlxList.iterator();
		while(sfzjlxListIterator.hasNext())
		{
			sb.append(((Sfzjlx)sfzjlxListIterator.next()).toXML());
		}
		sb.append("</sfzjlxList>");
	
		sb.append("<gjList>");
		Iterator glListIterator =gjList.iterator();
		while(glListIterator.hasNext())
		{
			sb.append(((Gjdq)glListIterator.next()).toXML());
		}
		sb.append("</gjList>");
		
		//国家标准行业
		sb.append("<gjbzhyList>");
		Iterator gjbzhyIterator =gjbzhyList.iterator();
		while(gjbzhyIterator.hasNext())
		{
			sb.append(((Gjbzhy)gjbzhyIterator.next()).toXML());
		}
		sb.append("</gjbzhyList>");
		
		//税务机关组织机构
		sb.append("<swjgzzjgList>");
		Iterator swjgzzjgIterator =swjgzzjgList.iterator();
		while(swjgzzjgIterator.hasNext())
		{
			sb.append(((Swjgzzjg)swjgzzjgIterator.next()).toXML());
		}
		sb.append("</swjgzzjgList>");
		
		//登记注册类型
		sb.append("<djzclxList>");
		Iterator djzclxListIterator =djzclxList.iterator();
		while(djzclxListIterator.hasNext())
		{
			sb.append(((Djzclx)djzclxListIterator.next()).toXML());
		}
		sb.append("</djzclxList>");
		
		sb.append(qyxxvo.toXML());
        sb.append(grxx.toXML());
        return sb.toString();
	}

	/**
	 * @Description: TODO 将xmlString 转为特定对象
	 * @param xmlDate
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public void resoveXML(String xmlDate) throws Exception{
		//Document doc = XMLHelper.fromString(xmlDate); 
		System.out.println(xmlDate);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream input = new ByteArrayInputStream(xmlDate.getBytes());
		Document doc = builder.parse(input);
		NodeList ls = doc.getChildNodes();
		
		doc.getElementsByTagName("query_sfzjl");
		System.out.println("正在解析xml");
		
	}
	
	
	/**
	 * @description: getter-- gjList
	 * @return the gjList
	 */
	public final List getGjList() {
		return gjList;
	}

	/**
	 * @description: setter-- gjList
	 * @param gjList the gjList to set
	 */
	public final void setGjList(List gjList) {
		this.gjList = gjList;
	}

	/**
	 * @description: getter-- sfzjlxList
	 * @return the sfzjlxList
	 */
	public List getSfzjlxList() {
		return sfzjlxList;
	}

	/**
	 * @description: setter-- sfzjlxList
	 * @param sfzjlxList the sfzjlxList to set
	 */
	public void setSfzjlxList(List sfzjlxList) {
		this.sfzjlxList = sfzjlxList;
	}

	/**
	 * @description: getter-- qyxxvo
	 * @return the qyxxvo
	 */
	public GrsdsQyxxVO getQyxxvo() {
		return qyxxvo;
	}

	/**
	 * @description: setter-- qyxxvo
	 * @param qyxxvo the qyxxvo to set
	 */
	public void setQyxxvo(GrsdsQyxxVO qyxxvo) {
		this.qyxxvo = qyxxvo;
	}

	/**
	 * @description: getter-- grxx
	 * @return the grxx
	 */
	public GrsdsGrxxVO getGrxx() {
		return grxx;
	}

	/**
	 * @description: setter-- grxx
	 * @param grxx the grxx to set
	 */
	public void setGrxx(GrsdsGrxxVO grxx) {
		this.grxx = grxx;
	}

	public List getSwjgzzjgList() {
		return swjgzzjgList;
	}

	public void setSwjgzzjgList(List swjgzzjgList) {
		this.swjgzzjgList = swjgzzjgList;
	}

	public List getGjbzhyList() {
		return gjbzhyList;
	}

	public void setGjbzhyList(List gjbzhyList) {
		this.gjbzhyList = gjbzhyList;
	}

	/**
	 * @description: getter-- djzclxList
	 * @return the djzclxList
	 */
	public List getDjzclxList() {
		return djzclxList;
	}

	/**
	 * @description: setter-- djzclxList
	 * @param djzclxList the djzclxList to set
	 */
	public void setDjzclxList(List djzclxList) {
		this.djzclxList = djzclxList;
	}

	
//	/**
//	 * @description: getter-- grListVOList
//	 * @return the grListVOList
//	 */
//	public List getGrListVOList() {
//		return grListVOList;
//	}
//
//	/**
//	 * @description: setter-- grListVOList
//	 * @param grListVOList the grListVOList to set
//	 */
//	public void setGrListVOList(List grListVOList) {
//		this.grListVOList = grListVOList;
//	}

	
	
}
