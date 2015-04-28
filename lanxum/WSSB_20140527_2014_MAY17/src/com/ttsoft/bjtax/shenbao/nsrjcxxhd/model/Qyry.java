package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;

import com.syax.common.xml.util.XMLBuildUtil;

public class Qyry implements Serializable {
	
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		if("01".equals(this.getZwdm())) {//法人
			buildQyryXml(sb, "fr");
		} else if("04".equals(this.getZwdm())) {//财务负责人
			buildQyryXml(sb, "cwfzr");
		} else if("05".equals(this.getZwdm())) {//办税人员
			buildQyryXml(sb, "bsr");
		} else if("99".equals(this.getZwdm())) {//其他人员
			buildQyryXml(sb, "qtry");
		}
		
		return sb.toString();
	}
	
	/**
	 * 构建企业人员
	 * @param sb
	 * @param type
	 */
	private void buildQyryXml(StringBuffer sb, String type) {
		sb.append("<" + type + ">");
		sb.append(XMLBuildUtil.appendStringElement("dzyx", this.dzyx));
		sb.append(XMLBuildUtil.appendStringElement("gddh", this.gddh));
		sb.append(XMLBuildUtil.appendStringElement("gjdz", this.gjdz));
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("xm", this.xm));
		sb.append(XMLBuildUtil.appendStringElement("yddh", this.yddh));
		sb.append(XMLBuildUtil.appendStringElement("zjhm", this.zjhm));
		sb.append(XMLBuildUtil.appendStringElement("zjlxdm", this.zjlxdm));
		sb.append(XMLBuildUtil.appendStringElement("zwdm", this.zwdm));
		sb.append("</" + type + ">");
	}

	private static final long serialVersionUID = 1L;
	/**
     * 证件类型代码
     */
    String gjdz;
   /**
     * 证件类型代码
     */
    String zjlxdm;
    /**
     * 证件号码
     */

    String zjhm;
    /**
     * 计算机代码
     */

    String jsjdm;
    /**
     * 固定电话
     */

    String gddh;
    /**
     * 移动电话
     */

    String yddh;
    /**
     * 电子邮箱
     */

    String dzyx;
    /**
     * 职务代码
     */

    String zwdm;
    /**
     * 姓名
     */

    String xm;
	public String getGjdz() {
		return gjdz;
	}
	public void setGjdz(String gjdz) {
		this.gjdz = gjdz;
	}
	public String getZjlxdm() {
		return zjlxdm;
	}
	public void setZjlxdm(String zjlxdm) {
		this.zjlxdm = zjlxdm;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getGddh() {
		return gddh;
	}
	public void setGddh(String gddh) {
		this.gddh = gddh;
	}
	public String getYddh() {
		return yddh;
	}
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getZwdm() {
		return zwdm;
	}
	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
}
