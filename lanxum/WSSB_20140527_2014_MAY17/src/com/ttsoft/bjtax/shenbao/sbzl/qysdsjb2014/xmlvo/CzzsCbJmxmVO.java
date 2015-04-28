package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class CzzsCbJmxmVO implements XMLVOInterface {
	/**
	 * 行次
	 */
	private String cbJmxmYzhc=null;
	/**
	 * 域值
	 */
	private String cbJmxmYz=null;
	/**
	 * 行次
	 */
	private String cbJmxmDmhc=null;
	/**
	 * 域值
	 */
	private String cbJmxmDm=null;
	
	public Map getListTypeMap() {
		return null;
	}
	public String toXML() {
		
		String xmlstr="<cbJmxmList>";
		xmlstr += XMLBuildUtil.appendStringElement("cbJmxmDmhc", cbJmxmDmhc);
		xmlstr += XMLBuildUtil.appendStringElement("cbJmxmDm", cbJmxmDm);
		xmlstr += XMLBuildUtil.appendStringElement("cbJmxmYzhc", cbJmxmYzhc);
		xmlstr += XMLBuildUtil.appendStringElement("cbJmxmYz", cbJmxmYz);
		xmlstr += "</cbJmxmList>";
		return xmlstr;
	}
	public String toXMLChilds() {
		String xmlstr = "";
		return xmlstr;
	}
	public String getCbJmxmYzhc() {
		return cbJmxmYzhc;
	}
	public void setCbJmxmYzhc(String cbJmxmYzhc) {
		this.cbJmxmYzhc = cbJmxmYzhc;
	}
	public String getCbJmxmYz() {
		return cbJmxmYz;
	}
	public void setCbJmxmYz(String cbJmxmYz) {
		this.cbJmxmYz = cbJmxmYz;
	}
	public String getCbJmxmDmhc() {
		return cbJmxmDmhc;
	}
	public void setCbJmxmDmhc(String cbJmxmDmhc) {
		this.cbJmxmDmhc = cbJmxmDmhc;
	}
	public String getCbJmxmDm() {
		return cbJmxmDm;
	}
	public void setCbJmxmDm(String cbJmxmDm) {
		this.cbJmxmDm = cbJmxmDm;
	}


}
