package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class GdzcjszjyjqkjbSbsjVO implements XMLVOInterface {

	
	/**
	 * �д�
	 */
	private String[] hc=null;
	/**
	 * ��Ŀ
	 */
	private String[] xm=null;
	/**
	 * ���ݡ�������--ԭֵ
	 */
	private String[] fwjzw_yz=null;
	/**
	 * ���ݡ�������--�����۾ɣ��۳�����
	 */
	private String[] fwjzw_bqzje=null;
	/**
	 * ���ݡ�������--�ۼ��۾ɣ��۳�����
	 */
	private String[] fwjzw_ljzje=null;
	/**
	 * �����豸�������̶��ʲ�--ԭֵ
	 */
	private String[] jqsbhqtgdzc_yz=null;
	/**
	 * �����豸�������̶��ʲ�--�����۾ɣ��۳�����
	 */
	private String[] jqsbhqtgdzc_bqzje=null;
	/**
	 * �����豸�������̶��ʲ�--�ۼ��۾ɣ��۳�����
	 */
	private String[] jqsbhqtgdzc_ljzje=null;
	/**
	 * �ϼ�--ԭֵ
	 */
	private String[] hj_yz=null;
	/**
	 * �ϼ�--�����۾ɣ��۳�����--�����۾ɶ�
	 */
	private String[] hj_bqzje_zczje=null;
	/**
	 * �ϼ�--�����۾ɣ��۳�����--�����۾ɶ�
	 */
	private String[] hj_bqzje_jszje=null;
	/**
	 * �ϼ�--�ۼ��۾ɣ��۳�����--�����۾ɶ�
	 */
	private String[] hj_ljzje_zczje=null;
	/**
	 * �ϼ�--�ۼ��۾ɣ��۳�����--�����۾ɶ�
	 */
	private String[] hj_ljzje_jszje=null;

	public GdzcjszjyjqkjbSbsjVO() {
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
			xmlstr += XMLBuildUtil.appendStringElement("xm", xm[i]);
			xmlstr += XMLBuildUtil.appendStringElement("fwjzw_yz", fwjzw_yz[i]);
			xmlstr += XMLBuildUtil.appendStringElement("fwjzw_bqzje", fwjzw_bqzje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("fwjzw_ljzje", fwjzw_ljzje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jqsbhqtgdzc_yz", jqsbhqtgdzc_yz[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jqsbhqtgdzc_bqzje", jqsbhqtgdzc_bqzje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("jqsbhqtgdzc_ljzje", jqsbhqtgdzc_ljzje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("hj_yz", hj_yz[i]);
			xmlstr += XMLBuildUtil.appendStringElement("hj_bqzje_zczje", hj_bqzje_zczje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("hj_bqzje_jszje", hj_bqzje_jszje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("hj_ljzje_zczje", hj_ljzje_zczje[i]);
			xmlstr += XMLBuildUtil.appendStringElement("hj_ljzje_jszje", hj_ljzje_jszje[i]);		
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

	public String[] getXm() {
		return xm;
	}

	public void setXm(String[] xm) {
		this.xm = xm;
	}

	public String[] getFwjzw_yz() {
		return fwjzw_yz;
	}

	public void setFwjzw_yz(String[] fwjzwYz) {
		fwjzw_yz = fwjzwYz;
	}

	public String[] getFwjzw_bqzje() {
		return fwjzw_bqzje;
	}

	public void setFwjzw_bqzje(String[] fwjzwBqzje) {
		fwjzw_bqzje = fwjzwBqzje;
	}

	public String[] getFwjzw_ljzje() {
		return fwjzw_ljzje;
	}

	public void setFwjzw_ljzje(String[] fwjzwLjzje) {
		fwjzw_ljzje = fwjzwLjzje;
	}

	public String[] getJqsbhqtgdzc_yz() {
		return jqsbhqtgdzc_yz;
	}

	public void setJqsbhqtgdzc_yz(String[] jqsbhqtgdzcYz) {
		jqsbhqtgdzc_yz = jqsbhqtgdzcYz;
	}

	public String[] getJqsbhqtgdzc_bqzje() {
		return jqsbhqtgdzc_bqzje;
	}

	public void setJqsbhqtgdzc_bqzje(String[] jqsbhqtgdzcBqzje) {
		jqsbhqtgdzc_bqzje = jqsbhqtgdzcBqzje;
	}

	public String[] getJqsbhqtgdzc_ljzje() {
		return jqsbhqtgdzc_ljzje;
	}

	public void setJqsbhqtgdzc_ljzje(String[] jqsbhqtgdzcLjzje) {
		jqsbhqtgdzc_ljzje = jqsbhqtgdzcLjzje;
	}

	public String[] getHj_yz() {
		return hj_yz;
	}

	public void setHj_yz(String[] hjYz) {
		hj_yz = hjYz;
	}

	public String[] getHj_bqzje_zczje() {
		return hj_bqzje_zczje;
	}

	public void setHj_bqzje_zczje(String[] hjBqzjeZczje) {
		hj_bqzje_zczje = hjBqzjeZczje;
	}

	public String[] getHj_bqzje_jszje() {
		return hj_bqzje_jszje;
	}

	public void setHj_bqzje_jszje(String[] hjBqzjeJszje) {
		hj_bqzje_jszje = hjBqzjeJszje;
	}

	public String[] getHj_ljzje_zczje() {
		return hj_ljzje_zczje;
	}

	public void setHj_ljzje_zczje(String[] hjLjzjeZczje) {
		hj_ljzje_zczje = hjLjzjeZczje;
	}

	public String[] getHj_ljzje_jszje() {
		return hj_ljzje_jszje;
	}

	public void setHj_ljzje_jszje(String[] hjLjzjeJszje) {
		hj_ljzje_jszje = hjLjzjeJszje;
	}


}
