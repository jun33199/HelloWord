package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;
import java.util.Date;

import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.DateUtil;

/**
 * �ܻ���
 * @author guow
 *
 */
public class Zjg implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * �ܻ������������
	 */
	private String zjgjsjdm;
	/**
	 * �ܻ���˰��Ǽ�֤��
	 */
	private String zjgswdjzh;
	/**
	 * ��˰������ 
	 */
	private String nsrmc;
	/**
	 * ������Ӫ��ַ
	 */
	private String scjydz;
	/**
	 * ������Ӫ��ַ
	 */
	private String scjydzlxdh;
	/**
	 * ���˴�������
	 */
	private String frdbxm;
	/**
	 * ���˴���֤������
	 */
	private String frdbzjlx;
	/**
	 * ���˴���֤������
	 */
	private String frdbzjhm;
	/**
	 * ���������
	 */
	private String jsjdm;
	/**
	 * ����ʱ��
	 */
	private Date cjrq;
	/**
	 * ¼��ʱ��
	 */
	private Date lrrq;
	/**
	 * ˰�������֯��������
	 */
	private String swjgzzjgdm;
	private String zcdzyb;
	private String zjgjyfw;
	
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<zjg>");
		sb.append(XMLBuildUtil.appendStringElement("zjgjsjdm", this.zjgjsjdm));
		sb.append(XMLBuildUtil.appendStringElement("zjgswdjzh", this.zjgswdjzh));
		sb.append(XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc));
		sb.append(XMLBuildUtil.appendStringElement("scjydz", this.scjydz));
		sb.append(XMLBuildUtil.appendStringElement("scjydzlxdh", this.scjydzlxdh));
		sb.append(XMLBuildUtil.appendStringElement("frdbxm", this.frdbxm));
		sb.append(XMLBuildUtil.appendStringElement("frdbzjlx", this.frdbzjlx));
		sb.append(XMLBuildUtil.appendStringElement("frdbzjhm", this.frdbzjhm));
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("cjrq", DateUtil.dateFormat(this.cjrq)));
		sb.append(XMLBuildUtil.appendStringElement("lrrq", DateUtil.dateFormat(this.lrrq)));
		sb.append(XMLBuildUtil.appendStringElement("swjgzzjgdm", this.swjgzzjgdm));
		sb.append(XMLBuildUtil.appendStringElement("zcdzyb", this.zcdzyb));
		sb.append(XMLBuildUtil.appendStringElement("zjgjyfw", this.zjgjyfw));
		sb.append("</zjg>");
		return sb.toString();
	}

	public String getZjgjsjdm() {
		return zjgjsjdm;
	}

	public void setZjgjsjdm(String zjgjsjdm) {
		this.zjgjsjdm = zjgjsjdm;
	}

	public String getZjgswdjzh() {
		return zjgswdjzh;
	}

	public void setZjgswdjzh(String zjgswdjzh) {
		this.zjgswdjzh = zjgswdjzh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getScjydz() {
		return scjydz;
	}

	public void setScjydz(String scjydz) {
		this.scjydz = scjydz;
	}

	public String getScjydzlxdh() {
		return scjydzlxdh;
	}

	public void setScjydzlxdh(String scjydzlxdh) {
		this.scjydzlxdh = scjydzlxdh;
	}

	public String getFrdbxm() {
		return frdbxm;
	}

	public void setFrdbxm(String frdbxm) {
		this.frdbxm = frdbxm;
	}

	public String getFrdbzjlx() {
		return frdbzjlx;
	}

	public void setFrdbzjlx(String frdbzjlx) {
		this.frdbzjlx = frdbzjlx;
	}

	public String getFrdbzjhm() {
		return frdbzjhm;
	}

	public void setFrdbzjhm(String frdbzjhm) {
		this.frdbzjhm = frdbzjhm;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public Date getLrrq() {
		return lrrq;
	}

	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getZcdzyb() {
		return zcdzyb;
	}

	public void setZcdzyb(String zcdzyb) {
		this.zcdzyb = zcdzyb;
	}

	public String getZjgjyfw() {
		return zjgjyfw;
	}

	public void setZjgjyfw(String zjgjyfw) {
		this.zjgjyfw = zjgjyfw;
	}
	
}
