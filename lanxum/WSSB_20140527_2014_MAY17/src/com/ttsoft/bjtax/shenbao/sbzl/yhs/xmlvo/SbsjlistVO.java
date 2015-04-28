package com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjlistVO implements XMLVOInterface {
	
	private SbsjVO sbsj=new SbsjVO();
	/**
	 *合计份数
	 */
	private String hjfs="";
	/**
	 * 合计计税金额
	 *
	 */
	private String hjjsje="";
	/**
	 * 合计已纳税额
	 *
	 */
	private String hjynse="";
	
	

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
		xmlstr += XMLBuildUtil.appendStringElement("hjfs",hjfs);
		xmlstr += XMLBuildUtil.appendStringElement("hjjsje",hjjsje); 
		xmlstr += XMLBuildUtil.appendStringElement("hjynse",hjynse);
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
	public String getHjfs() {
		return hjfs;
	}

	public void setHjfs(String hjfs) {
		this.hjfs = hjfs;
	}

	public String getHjjsje() {
		return hjjsje;
	}

	public void setHjjsje(String hjjsje) {
		this.hjjsje = hjjsje;
	}

	public String getHjynse() {
		return hjynse;
	}

	public void setHjynse(String hjynse) {
		this.hjynse = hjynse;
	}
}
