package com.ttsoft.bjtax.shenbao.szsm.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SzsmVO implements XMLVOInterface {
	/*
	 * 税种税目代码
	 */
	private String [] szsmdm;
	/*
	 * 税种税目名称
	 */
	private String [] szsmmc;
	/*
	 * 父节点代码
	 */
	private String [] fjddm;
	/*
	 * 层次标识
	 */
	private String [] ccbs;
	
	/*
	 * 是否可以修改
	 */
	private String [] editable;
	/*
	 *  是否被选中
	 */
	private String [] isSelected;
	
	public Map getListTypeMap() {
		// TODO 自动生成方法存根
		return null;
	}

	public String toXML() {
		return toXMLChilds();
	}

	public String toXMLChilds() {
			String xmlstr = "";
			for(int i=0;i<szsmdm.length;i++){
		        xmlstr += "<szsm>"+XMLBuildUtil.appendStringElement("szsmdm",szsmdm[i]);
		        xmlstr += XMLBuildUtil.appendStringElement("szsmmc",szsmmc[i]);
		        xmlstr += XMLBuildUtil.appendStringElement("fjddm",fjddm[i]);
		        xmlstr += XMLBuildUtil.appendStringElement("editable",editable[i]);
		        xmlstr += XMLBuildUtil.appendStringElement("isSelected",isSelected[i]);
		        xmlstr += XMLBuildUtil.appendStringElement("ccbs",ccbs[i])+"</szsm>";
			}
		   return xmlstr;
	}

	/**
	 * @return 返回 ccbs。
	 */
	public String[] getCcbs() {
		return ccbs;
	}

	/**
	 * @param ccbs 要设置的 ccbs。
	 */
	public void setCcbs(String[] ccbs) {
		this.ccbs = ccbs;
	}

	/**
	 * @return 返回 fjddm。
	 */
	public String[] getFjddm() {
		return fjddm;
	}

	/**
	 * @param fjddm 要设置的 fjddm。
	 */
	public void setFjddm(String[] fjddm) {
		this.fjddm = fjddm;
	}

	/**
	 * @return 返回 szsmdm。
	 */
	public String[] getSzsmdm() {
		return szsmdm;
	}

	/**
	 * @param szsmdm 要设置的 szsmdm。
	 */
	public void setSzsmdm(String[] szsmdm) {
		this.szsmdm = szsmdm;
	}

	public String[] getEditable() {
		return editable;
	}

	public void setEditable(String[] editable) {
		this.editable = editable;
	}

	public String[] getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String[] isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * @return 返回 szsmmc。
	 */
	public String[] getSzsmmc() {
		return szsmmc;
	}

	/**
	 * @param szsmmc 要设置的 szsmmc。
	 */
	public void setSzsmmc(String[] szsmmc) {
		this.szsmmc = szsmmc;
	}

}
