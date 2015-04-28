package com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjVO implements XMLVOInterface {

	/**
	 * ˰��˰Ŀ����
	 */
	private String[] szsmdm = null;

	/**
	 * ˰��˰Ŀ����
	 */
	private String[] szsmmc = null;

	/**
	 * ����
	 */
	private String[] fs = null;

	/**
	 * ��˰���
	 */
	private String[] jsje = null;
	/**
	 * ˰��
	 */
	private String[] sl = null;
	/**
	 * ����˰��
	 */
	private String[] ynse = null;
	
	

	public SbsjVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		String xmlstr="";
		xmlstr += toXMLChilds();
		
		return xmlstr;
	}

	public String toXMLChilds() {
		String xmlstr = "";
		//System.out.println(szsmdm.length+"(((((");
		for(int i=0;i<szsmdm.length;i++){
			xmlstr += "<sbsj>";
			xmlstr += XMLBuildUtil.appendStringElement("szsmdm", szsmdm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("szsmmc", szsmmc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("fs", fs[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jsje", jsje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("sl", sl[i]);
			xmlstr += XMLBuildUtil.appendStringElement("ynse", ynse[i]);
			xmlstr += "</sbsj>";
		}
		
			return xmlstr;
	}
	
	public String[] getFs() {
		return fs;
	}

	public void setFs(String[] fs) {
		this.fs = fs;
	}

	public String[] getJsje() {
		return jsje;
	}

	public void setJsje(String[] jsje) {
		this.jsje = jsje;
	}

	public String[] getSl() {
		return sl;
	}

	public void setSl(String[] sl) {
		this.sl = sl;
	}

	public String[] getSzsmdm() {
		return szsmdm;
	}

	public void setSzsmdm(String[] szsmdm) {
		this.szsmdm = szsmdm;
	}

	public String[] getSzsmmc() {
		return szsmmc;
	}

	public void setSzsmmc(String[] szsmmc) {
		this.szsmmc = szsmmc;
	}

	public String[] getYnse() {
		return ynse;
	}

	public void setYnse(String[] ynse) {
		this.ynse = ynse;
	}
	

}
