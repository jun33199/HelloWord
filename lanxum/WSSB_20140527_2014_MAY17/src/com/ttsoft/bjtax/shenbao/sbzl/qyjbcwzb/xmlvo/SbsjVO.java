package com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjVO implements XMLVOInterface {

	/**
	 * 项目名称
	 */
	private String[] xmmc = null;

	/**
	 * 行次
	 */
	private String[] hc = null;

	/**
	 * 年初数
	 */
	private String[] ncs = null;

	/**
	 * 年末数
	 */
	private String[] nms = null;

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
		System.out.println(xmmc.length+"(((((****");
		for(int i=0;i<xmmc.length;i++){
			xmlstr += "<sbsj>";
			xmlstr += XMLBuildUtil.appendStringElement("xmmc", xmmc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("hc", hc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("ncs", ncs[i]);
			xmlstr += XMLBuildUtil.appendStringElement("nms", nms[i]);
			xmlstr += "</sbsj>";
		}

		return xmlstr;
			
	}

	public String[] getHc() {
		return hc;
	}

	public void setHc(String[] hc) {
		this.hc = hc;
	}

	public String[] getNcs() {
		return ncs;
	}

	public void setNcs(String[] ncs) {
		this.ncs = ncs;
	}

	public String[] getNms() {
		return nms;
	}

	public void setNms(String[] nms) {
		this.nms = nms;
	}

	public String[] getXmmc() {
		return xmmc;
	}

	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}

	

}
