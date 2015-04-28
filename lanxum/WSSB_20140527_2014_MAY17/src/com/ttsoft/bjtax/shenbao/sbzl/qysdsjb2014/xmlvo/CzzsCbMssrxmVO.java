package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class CzzsCbMssrxmVO implements XMLVOInterface {
	/**
	 * 行次
	 */
	private String cbMssrxmYzhc=null;
	/**
	 * 域值
	 */
	private String cbMssrxmYz=null;
	/**
	 * 行次
	 */
	private String cbMssrxmDmhc=null;
	/**
	 * 域值
	 */
	private String cbMssrxmDm=null;
	
	public Map getListTypeMap() {
		return null;
	}
	public String toXML() {
		
		String xmlstr="<cbMssrxmList>";
		xmlstr += XMLBuildUtil.appendStringElement("cbMssrxmYzhc", cbMssrxmYzhc);
		xmlstr += XMLBuildUtil.appendStringElement("cbMssrxmYz", cbMssrxmYz);
		xmlstr += XMLBuildUtil.appendStringElement("cbMssrxmDmhc", cbMssrxmDmhc);
		xmlstr += XMLBuildUtil.appendStringElement("cbMssrxmDm", cbMssrxmDm);
		xmlstr += "</cbMssrxmList>";
		return xmlstr;
	}
	public String toXMLChilds() {
		String xmlstr = "";
		return xmlstr;
	}
	public String getCbMssrxmYzhc() {
		return cbMssrxmYzhc;
	}
	public void setCbMssrxmYzhc(String cbMssrxmYzhc) {
		this.cbMssrxmYzhc = cbMssrxmYzhc;
	}
	public String getCbMssrxmYz() {
		return cbMssrxmYz;
	}
	public void setCbMssrxmYz(String cbMssrxmYz) {
		this.cbMssrxmYz = cbMssrxmYz;
	}
	public String getCbMssrxmDmhc() {
		return cbMssrxmDmhc;
	}
	public void setCbMssrxmDmhc(String cbMssrxmDmhc) {
		this.cbMssrxmDmhc = cbMssrxmDmhc;
	}
	public String getCbMssrxmDm() {
		return cbMssrxmDm;
	}
	public void setCbMssrxmDm(String cbMssrxmDm) {
		this.cbMssrxmDm = cbMssrxmDm;
	}



}
