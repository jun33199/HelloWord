package com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjVO implements XMLVOInterface {

	/**
	 * �д�
	 */
	private String[] hc=null; 
	/**
	 * ����������
	 */
	private String[] jmfldm=null; 
	/**
	 * �����������
	 */
	private String[] jmflmc=null; 
	/**
	 * ˰�ִ���
	 */
	private String[] szdm=null; 
	/**
	 * ˰������
	 */
	private String[] szmc=null; 
	/**
	 * ˰��˰Ŀ����
	 */
	private String[] szsmdm=null; 
	/**
	 * ˰��˰Ŀ����
	 */
	private String[] szsmmc=null; 
	/**
	 * �������Ʊ��
	 */
	private String[] aslj=null; 
	/**
	 * ��˰����
	 */
	private String[] kssl=null; 
	/**
	 * ��˰���
	 */
	private String[] jsje=null; 
	/**
	 * ����˰��
	 */
	private String[] jmse=null; 
	/**
	 * ����ʱ��
	 */
	private String[] cjsj=null; 
	/**
	 * ������Ŀ˰�ִ���
	 */
	private String[] jmxmszdm=null; 
	/**
	 * ������Ŀ����
	 */
	private String[] jmxmdm=null; 
	/**
	 * ������Ŀ���ƣ�����ţ�
	 */
	private String[] jmxmjdm=null; 
	
	/**
	 * �����Żݿ�ʼ����
	 */
	private String[] jmxmksrq=null; 
	
	/**
	 * �����Żݽ�������
	 */
	private String[] jmxmjsrq=null; 
	
	/**
	 *  ˰��������ʼ����
	 */
	private String[] skssksrq=null; 
	
	/**
	 * ˰��������������
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
