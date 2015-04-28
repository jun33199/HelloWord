package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;
import java.util.Date;

import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.DateUtil;

/**
 * ˰��Ǽ�-Ͷ�ʷ� djdb.dj_jl_tzf
 * 
 * @author guow
 * 
 */
public class Tzf implements Serializable {
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tzf>");
		sb.append(XMLBuildUtil.appendStringElement("tzfmc", this.tzfmc));
		sb.append(XMLBuildUtil.appendStringElement("zjlxdm", this.zjlxdm));
		sb.append(XMLBuildUtil.appendStringElement("zjhm", this.zjhm));
		sb.append(XMLBuildUtil.appendStringElement("tzbl", this.tzbl + ""));
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("xh", this.xh + ""));
		sb.append(XMLBuildUtil.appendStringElement("cjrq", DateUtil.dateFormat(this.cjrq)));
		sb.append(XMLBuildUtil.appendStringElement("lrrq", DateUtil.dateFormat(this.lrrq)));
		sb.append(XMLBuildUtil.appendStringElement("swjgzzjgdm", this.swjgzzjgdm));
		sb.append(XMLBuildUtil.appendStringElement("qxdm", this.qxdm));
		sb.append(XMLBuildUtil.appendStringElement("gjdz", this.gjdz));
		sb.append(XMLBuildUtil.appendStringElement("tzfjjxz", this.tzfjjxz));
		sb.append(XMLBuildUtil.appendStringElement("tzje", this.tzje + ""));
		sb.append(XMLBuildUtil.appendStringElement("fpbl", this.fpbl + ""));
		sb.append("</tzf>");
		return sb.toString();
	}

	private static final long serialVersionUID = 1L;
	/**
	 * Ͷ�ʷ�����
	 */
	private String tzfmc;
	/**
	 * ֤�����ʹ���
	 */
	private String zjlxdm;
	/**
	 * ֤������
	 */
	private String zjhm;
	/**
	 * Ͷ�ʱ���(%)
	 */
	private double tzbl;
	/**
	 * ���������
	 */
	private String jsjdm;
	/**
	 * ���
	 */
	private int xh;
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
	/**
	 * ���ش���
	 */
	private String qxdm;
	private String gjdz;
	private String tzfjjxz;
	private double tzje;
	private double fpbl;

	public String getTzfmc() {
		return tzfmc;
	}

	public void setTzfmc(String tzfmc) {
		this.tzfmc = tzfmc;
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

	public double getTzbl() {
		return tzbl;
	}

	public void setTzbl(double tzbl) {
		this.tzbl = tzbl;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public int getXh() {
		return xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
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

	public String getQxdm() {
		return qxdm;
	}

	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}

	public String getGjdz() {
		return gjdz;
	}

	public void setGjdz(String gjdz) {
		this.gjdz = gjdz;
	}

	public String getTzfjjxz() {
		return tzfjjxz;
	}

	public void setTzfjjxz(String tzfjjxz) {
		this.tzfjjxz = tzfjjxz;
	}

	public double getTzje() {
		return tzje;
	}

	public void setTzje(double tzje) {
		this.tzje = tzje;
	}

	public double getFpbl() {
		return fpbl;
	}

	public void setFpbl(double fpbl) {
		this.fpbl = fpbl;
	}

}
