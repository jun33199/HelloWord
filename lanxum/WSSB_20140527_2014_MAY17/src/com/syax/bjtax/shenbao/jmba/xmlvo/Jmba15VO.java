/*
 * Created on 2009-12-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @author guzx
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Jmba15VO implements JmbamxVoInterface {
	/*
	 * ���
	 */
	private String xh;

	// �̶��ʲ����ƣ������۾����ޣ�
	private String gdzcmc_sd;

	// ���޶�����ͣ�0���ǣ�1����
	// private String sfnxdyzd_sd = "";
	//
	// // �ṩ�������˵����0���ǣ�1����
	// private String sftgczqksm_sd = "";

	// ������ͣ�0�������۾����ޱ�1�������۾ɱ�
	private String tbblx = "0";

	// �̶��ʲ�ԭֵ�������۾����ޣ�
	private String gdzcyz_sd;

	// �̶��ʲ���˰�����������۾����ޣ�
	private String gdzcjsjc_sd;

	// ˰���涨�������
	private String sfgdzdnx_sd;

	// �����۾��������
	private String jszjzdnx_sd;

	// �����۾���ʼ���
	private String zjqsnd_sd;

	// �����۾���ֹ���
	private String zjzznd_sd;

	// ÿ��ɿ۳����۾ɶ�
	private String zje_sd;

	// �Ѽ����۾ɵ�����
	private String yjtzjnx_sd;

	// �Ѽ�����۾ɶ�
	private String yjtzje_sd;

	// �̶��ʲ����ƣ������۾ɣ�
	private String gdzcmc_js;

	// �ṩ����˵����0���ǣ�1����
	// private String sftgffsm_js = "";

	// �̶��ʲ�ԭֵ�������۾ɣ�
	private String gdzcyz_js;

	// �̶��ʲ���˰�����������۾ɣ�
	private String gdzcjsjc_js;

	// �����۾ɵķ������룬0��˫�����ݼ�����1�������ܶ
	private String jszjffdm_js;

	// ���۾ɶ�
	private String zje_js;

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", this.xh);
		// xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
		// xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
		// xmlstr += XMLBuildUtil.appendStringElement("band", band);
		// xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcmc_sd", this.gdzcmc_sd);
		// xmlstr += XMLBuildUtil.appendStringElement("sfnxdyzd_sd",
		// sfnxdyzd_sd);
		// xmlstr += XMLBuildUtil.appendStringElement("sftgczqksm_sd",
		// sftgczqksm_sd);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcyz_sd", this.gdzcyz_sd);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcjsjc_sd", this.gdzcjsjc_sd);
		xmlstr += XMLBuildUtil.appendStringElement("sfgdzdnx_sd", this.sfgdzdnx_sd);
		xmlstr += XMLBuildUtil.appendStringElement("jszjzdnx_sd", this.jszjzdnx_sd);
		xmlstr += XMLBuildUtil.appendStringElement("zjqsnd_sd", this.zjqsnd_sd);
		xmlstr += XMLBuildUtil.appendStringElement("zjzznd_sd", this.zjzznd_sd);
		xmlstr += XMLBuildUtil.appendStringElement("zje_sd", this.zje_sd);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzjnx_sd", this.yjtzjnx_sd);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzje_sd", this.yjtzje_sd);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcmc_js", this.gdzcmc_js);
		// xmlstr += XMLBuildUtil.appendStringElement("sftgffsm_js",
		// sftgffsm_js);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcyz_js", this.gdzcyz_js);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcjsjc_js", this.gdzcjsjc_js);
		xmlstr += XMLBuildUtil.appendStringElement("jszjffdm_js", this.jszjffdm_js);
		xmlstr += XMLBuildUtil.appendStringElement("zje_js", this.zje_js);
		xmlstr += XMLBuildUtil.appendStringElement("tbblx", this.tbblx);
		// xmlstr += XMLBuildUtil.appendStringElement("shbj", shbj);
		// xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
		// xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
		// xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
		// xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
		// xmlstr += XMLBuildUtil.appendStringElement("tbblx", tbblx);
		xmlstr += "</qysdsjmba>";

		return xmlstr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGdzcjsjc_js() {
		return gdzcjsjc_js;
	}

	public void setGdzcjsjc_js(String gdzcjsjc_js) {
		this.gdzcjsjc_js = gdzcjsjc_js;
	}

	public String getGdzcjsjc_sd() {
		return gdzcjsjc_sd;
	}

	public void setGdzcjsjc_sd(String gdzcjsjc_sd) {
		this.gdzcjsjc_sd = gdzcjsjc_sd;
	}

	public String getGdzcmc_js() {
		return gdzcmc_js;
	}

	public void setGdzcmc_js(String gdzcmc_js) {
		this.gdzcmc_js = gdzcmc_js;
	}

	public String getGdzcmc_sd() {
		return gdzcmc_sd;
	}

	public void setGdzcmc_sd(String gdzcmc_sd) {
		this.gdzcmc_sd = gdzcmc_sd;
	}

	public String getGdzcyz_js() {
		return gdzcyz_js;
	}

	public void setGdzcyz_js(String gdzcyz_js) {
		this.gdzcyz_js = gdzcyz_js;
	}

	public String getGdzcyz_sd() {
		return gdzcyz_sd;
	}

	public void setGdzcyz_sd(String gdzcyz_sd) {
		this.gdzcyz_sd = gdzcyz_sd;
	}

	public String getJszjffdm_js() {
		return jszjffdm_js;
	}

	public void setJszjffdm_js(String jszjffdm_js) {
		this.jszjffdm_js = jszjffdm_js;
	}

	public String getJszjzdnx_sd() {
		return jszjzdnx_sd;
	}

	public void setJszjzdnx_sd(String jszjzdnx_sd) {
		this.jszjzdnx_sd = jszjzdnx_sd;
	}

	public String getSfgdzdnx_sd() {
		return sfgdzdnx_sd;
	}

	public void setSfgdzdnx_sd(String sfgdzdnx_sd) {
		this.sfgdzdnx_sd = sfgdzdnx_sd;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYjtzje_sd() {
		return yjtzje_sd;
	}

	public void setYjtzje_sd(String yjtzje_sd) {
		this.yjtzje_sd = yjtzje_sd;
	}

	public String getYjtzjnx_sd() {
		return yjtzjnx_sd;
	}

	public void setYjtzjnx_sd(String yjtzjnx_sd) {
		this.yjtzjnx_sd = yjtzjnx_sd;
	}

	public String getZje_js() {
		return zje_js;
	}

	public void setZje_js(String zje_js) {
		this.zje_js = zje_js;
	}

	public String getZje_sd() {
		return zje_sd;
	}

	public void setZje_sd(String zje_sd) {
		this.zje_sd = zje_sd;
	}

	public String getZjqsnd_sd() {
		return zjqsnd_sd;
	}

	public void setZjqsnd_sd(String zjqsnd_sd) {
		this.zjqsnd_sd = zjqsnd_sd;
	}

	public String getZjzznd_sd() {
		return zjzznd_sd;
	}

	public void setZjzznd_sd(String zjzznd_sd) {
		this.zjzznd_sd = zjzznd_sd;
	}

	public String getTbblx() {
		return tbblx;
	}

	public void setTbblx(String tbblx) {
		this.tbblx = tbblx;
	}

}
