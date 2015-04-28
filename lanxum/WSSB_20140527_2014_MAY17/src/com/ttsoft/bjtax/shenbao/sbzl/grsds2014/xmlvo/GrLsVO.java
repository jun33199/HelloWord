/**
 * @Title:       GrLsVO.java
 * @Description: TODO
 * @date:        2014-11-25上午11:10:33
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Sfzjlx;

/**
 * @Description: TODO
 * @author: Lijn
 * @time: 2014-11-25
 */
public class GrLsVO extends YWRootVO implements XMLVOInterface {
	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;

	private List grList = new ArrayList();
	
	/**
	 * Description：身份证件类型代码表
	 */
	private List sfzjlxList = new ArrayList();
	
	private String sumPage="0";
	
	private String currentPage="1";
	
	/**
	 * Description：计算机代码
	 */
	private String jsjdm="";	
	
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>");
		sb.append(toXMLHead());
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("sumPage", this.sumPage));
		sb.append(XMLBuildUtil.appendStringElement("currentPage", this.currentPage));
		sb.append(toXMLChilds());
		
		sb.append("</taxdoc>");
		return sb.toString();
	}

	public String toXMLWithoutSchema() {

		StringBuffer sb = new StringBuffer();
		sb.append("<taxdoc>");
		sb.append(toXMLHead());
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("sumPage", this.sumPage));
		sb.append(XMLBuildUtil.appendStringElement("currentPage", this.currentPage));
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
		
		
		sb.append("<GrList>");
		Iterator grListIterator =grList.iterator();
		while(grListIterator.hasNext()){
			sb.append(((GrVO)grListIterator.next()).toXML());
		}
		sb.append("</GrList>");
		return sb.toString();
	}

	
	
	/**
	 * @description: getter-- sumPage
	 * @return the sumPage
	 */
	public String getSumPage() {
		return sumPage;
	}

	/**
	 * @description: setter-- sumPage
	 * @param sumPage the sumPage to set
	 */
	public void setSumPage(String sumPage) {
		this.sumPage = sumPage;
	}

	/**
	 * @description: getter-- currentPage
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}

	/**
	 * @description: setter-- currentPage
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @description: getter-- jsjdm
	 * @return the jsjdm
	 */
	public String getJsjdm() {
		return jsjdm;
	}

	/**
	 * @description: setter-- jsjdm
	 * @param jsjdm the jsjdm to set
	 */
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	/**
	 * @description: getter-- grList
	 * @return the grList
	 */
	public List getGrList() {
		return grList;
	}

	/**
	 * @description: setter-- grList
	 * @param grList the grList to set
	 */
	public void setGrList(List grList) {
		this.grList = grList;
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
	
	
}
