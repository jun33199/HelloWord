package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class CzzsCbJzmzxmVO implements XMLVOInterface {
	/**
	 * �д�
	 */
	private String cbJzmzxmYzhc=null;
	/**
	 * ��ֵ
	 */
	private String cbJzmzxmYz=null;
	/**
	 * �д�
	 */
	private String cbJzmzxmDmhc=null;
	/**
	 * ��ֵ
	 */
	private String cbJzmzxmDm=null;
	
	public Map getListTypeMap() {
		return null;
	}
	public String toXML() {
		
		String xmlstr="<cbJzmzxmList>";
		xmlstr += XMLBuildUtil.appendStringElement("cbJzmzxmYzhc", cbJzmzxmYzhc);
		xmlstr += XMLBuildUtil.appendStringElement("cbJzmzxmYz", cbJzmzxmYz);
		xmlstr += XMLBuildUtil.appendStringElement("cbJzmzxmDmhc", cbJzmzxmDmhc);
		xmlstr += XMLBuildUtil.appendStringElement("cbJzmzxmDm", cbJzmzxmDm);
		xmlstr += "</cbJzmzxmList>";
		return xmlstr;
	}
	public String toXMLChilds() {
		String xmlstr = "";
		return xmlstr;
	}
	public String getCbJzmzxmYzhc() {
		return cbJzmzxmYzhc;
	}
	public void setCbJzmzxmYzhc(String cbJzmzxmYzhc) {
		this.cbJzmzxmYzhc = cbJzmzxmYzhc;
	}
	public String getCbJzmzxmYz() {
		return cbJzmzxmYz;
	}
	public void setCbJzmzxmYz(String cbJzmzxmYz) {
		this.cbJzmzxmYz = cbJzmzxmYz;
	}
	public String getCbJzmzxmDmhc() {
		return cbJzmzxmDmhc;
	}
	public void setCbJzmzxmDmhc(String cbJzmzxmDmhc) {
		this.cbJzmzxmDmhc = cbJzmzxmDmhc;
	}
	public String getCbJzmzxmDm() {
		return cbJzmzxmDm;
	}
	public void setCbJzmzxmDm(String cbJzmzxmDm) {
		this.cbJzmzxmDm = cbJzmzxmDm;
	}


}
