package com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjlistVO implements XMLVOInterface {
	/**
	 * �걨���ݶ���
	 */
	private SbsjVO sbsj=new SbsjVO();
	/**
	 * �ϼ�
	 */
	private String hj="";
	/**
	 * ���޼����ʸ�
	 */
	private String ifjmzg="";
	/**
	 * �޼����ʸ���д�
	 */
	private String jmzghc="";
	
	public SbsjlistVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		String xmlstr ="<sbsjlist>";
		xmlstr+=sbsj.toXML();
		xmlstr += XMLBuildUtil.appendStringElement("hj",hj);
		xmlstr += XMLBuildUtil.appendStringElement("ifjmzg",ifjmzg); 
		xmlstr += XMLBuildUtil.appendStringElement("jmzghc",jmzghc);
		xmlstr+="</sbsjlist>";

		return xmlstr;
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public SbsjVO getSbsj() {
		return sbsj;
	}

	public void setSbsj(SbsjVO sbsj) {
		this.sbsj = sbsj;
	}

	public String getHj() {
		return hj;
	}

	public void setHj(String hj) {
		this.hj = hj;
	}

	public String getIfjmzg() {
		return ifjmzg;
	}

	public void setIfjmzg(String ifjmzg) {
		this.ifjmzg = ifjmzg;
	}

	public String getJmzghc() {
		return jmzghc;
	}

	public void setJmzghc(String jmzghc) {
		this.jmzghc = jmzghc;
	}

	
}
