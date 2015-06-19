package com.ttsoft.bjtax.shenbao.szsm.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SzsmVO implements XMLVOInterface {
	/*
	 * ˰��˰Ŀ����
	 */
	private String [] szsmdm;
	/*
	 * ˰��˰Ŀ����
	 */
	private String [] szsmmc;
	/*
	 * ���ڵ����
	 */
	private String [] fjddm;
	/*
	 * ��α�ʶ
	 */
	private String [] ccbs;
	
	/*
	 * �Ƿ�����޸�
	 */
	private String [] editable;
	/*
	 *  �Ƿ�ѡ��
	 */
	private String [] isSelected;
	
	public Map getListTypeMap() {
		// TODO �Զ����ɷ������
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
	 * @return ���� ccbs��
	 */
	public String[] getCcbs() {
		return ccbs;
	}

	/**
	 * @param ccbs Ҫ���õ� ccbs��
	 */
	public void setCcbs(String[] ccbs) {
		this.ccbs = ccbs;
	}

	/**
	 * @return ���� fjddm��
	 */
	public String[] getFjddm() {
		return fjddm;
	}

	/**
	 * @param fjddm Ҫ���õ� fjddm��
	 */
	public void setFjddm(String[] fjddm) {
		this.fjddm = fjddm;
	}

	/**
	 * @return ���� szsmdm��
	 */
	public String[] getSzsmdm() {
		return szsmdm;
	}

	/**
	 * @param szsmdm Ҫ���õ� szsmdm��
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
	 * @return ���� szsmmc��
	 */
	public String[] getSzsmmc() {
		return szsmmc;
	}

	/**
	 * @param szsmmc Ҫ���õ� szsmmc��
	 */
	public void setSzsmmc(String[] szsmmc) {
		this.szsmmc = szsmmc;
	}

}
