package com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjVO implements XMLVOInterface {

	/**
	 * 行次
	 */
	private String[] hc=null; 
	/**
	 * 减免分类代码
	 */
	private String[] jmfldm=null; 
	/**
	 * 减免分类名称
	 */
	private String[] jmflmc=null; 
	/**
	 * 税种代码
	 */
	private String[] szdm=null; 
	/**
	 * 税种名称
	 */
	private String[] szmc=null; 
	/**
	 * 税种税目代码
	 */
	private String[] szsmdm=null; 
	/**
	 * 税种税目名称
	 */
	private String[] szsmmc=null; 
	/**
	 * 按数量计标记
	 */
	private String[] aslj=null; 
	/**
	 * 课税数量
	 */
	private String[] kssl=null; 
	/**
	 * 计税金额
	 */
	private String[] jsje=null; 
	/**
	 * 减免税额
	 */
	private String[] jmse=null; 
	/**
	 * 创建时间
	 */
	private String[] cjsj=null; 
	/**
	 * 减免项目税种代码
	 */
	private String[] jmxmszdm=null; 
	/**
	 * 减免项目代码
	 */
	private String[] jmxmdm=null; 
	/**
	 * 减免项目名称（文书号）
	 */
	private String[] jmxmjdm=null; 
	
	/**
	 * 减免优惠开始日期
	 */
	private String[] jmxmksrq=null; 
	
	/**
	 * 减免优惠结束日期
	 */
	private String[] jmxmjsrq=null; 
	
	/**
	 *  税款所属开始日期
	 */
	private String[] skssksrq=null; 
	
	/**
	 * 税款所属结束日期
	 */
	private String[] skssjsrq=null; 
	
	
	
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
		for(int i=0;i<hc.length;i++){
			xmlstr += "<sbsj>";
			xmlstr += XMLBuildUtil.appendStringElement("hc", hc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmfldm", jmfldm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmflmc", jmflmc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("szdm", szdm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("szmc", szmc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("szsmdm", szsmdm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("szsmmc", szsmmc[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmxmszdm", jmxmszdm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmxmdm", jmxmdm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmxmjdm", jmxmjdm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmxmksrq", jmxmksrq[i]);	
			xmlstr += XMLBuildUtil.appendStringElement("jmxmjsrq", jmxmjsrq[i]);
			xmlstr += XMLBuildUtil.appendStringElement("skssksrq",skssksrq[i]);
		    xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",skssjsrq[i]); 
			xmlstr += XMLBuildUtil.appendStringElement("aslj", aslj[i]);			
			xmlstr += XMLBuildUtil.appendStringElement("kssl", kssl[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jsje", jsje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jmse", jmse[i]);
			xmlstr += XMLBuildUtil.appendStringElement("cjsj", cjsj[i]);
			xmlstr += "</sbsj>";
		}
		
			return xmlstr;
	}

	public String[] getAslj() {
		return aslj;
	}

	public void setAslj(String[] aslj) {
		this.aslj = aslj;
	}

	public String[] getHc() {
		return hc;
	}

	public void setHc(String[] hc) {
		this.hc = hc;
	}

	public String[] getJmfldm() {
		return jmfldm;
	}

	public void setJmfldm(String[] jmfldm) {
		this.jmfldm = jmfldm;
	}

	public String[] getJmflmc() {
		return jmflmc;
	}

	public void setJmflmc(String[] jmflmc) {
		this.jmflmc = jmflmc;
	}

	public String[] getJmse() {
		return jmse;
	}

	public void setJmse(String[] jmse) {
		this.jmse = jmse;
	}

	public String[] getJsje() {
		return jsje;
	}

	public void setJsje(String[] jsje) {
		this.jsje = jsje;
	}

	public String[] getKssl() {
		return kssl;
	}

	public void setKssl(String[] kssl) {
		this.kssl = kssl;
	}

	public String[] getSzdm() {
		return szdm;
	}

	public void setSzdm(String[] szdm) {
		this.szdm = szdm;
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

	public String[] getCjsj() {
		return cjsj;
	}

	public void setCjsj(String[] cjsj) {
		this.cjsj = cjsj;
	}

	public String[] getSzmc() {
		return szmc;
	}

	public void setSzmc(String[] szmc) {
		this.szmc = szmc;
	}

	public String[] getJmxmdm() {
		return jmxmdm;
	}

	public void setJmxmdm(String[] jmxmdm) {
		this.jmxmdm = jmxmdm;
	}

	public String[] getJmxmjdm() {
		return jmxmjdm;
	}

	public void setJmxmjdm(String[] jmxmjdm) {
		this.jmxmjdm = jmxmjdm;
	}

	public String[] getJmxmszdm() {
		return jmxmszdm;
	}

	public void setJmxmszdm(String[] jmxmszdm) {
		this.jmxmszdm = jmxmszdm;
	}
	
	public String[] getJmxmksrq() {
		return jmxmksrq;
	}

	public void setJmxmksrq(String[] jmxmksrq) {
		this.jmxmksrq = jmxmksrq;
	}

	public String[] getJmxmjsrq() {
		return jmxmjsrq;
	}

	public void setJmxmjsrq(String[] jmxmjsrq) {
		this.jmxmjsrq = jmxmjsrq;
	}

	public String[] getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(String[] skssksrq) {
		this.skssksrq = skssksrq;
	}

	public String[] getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(String[] skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

}
