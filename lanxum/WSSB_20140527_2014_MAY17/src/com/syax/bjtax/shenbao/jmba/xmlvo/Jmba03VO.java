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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Jmba03VO implements JmbamxVoInterface {
	/*
	 * ���
	 */
	private String xh;
	/*
	 * �м���Ա������
	 */
	private String cjrmc = "0";
	/*
	 * �м���Ա֤�������֤
	 */
	private String cjrzmsf = "0";
	/*
	 * �м���Աǩ�����Ͷ���ͬ�����Э��
	 */
	private String cjrltht = "0";
	/*
	 * �м���Ա������ᱣ�յĽɷ�֤��
	 */
	private String cjrbx = "0";
	/*
	 * ����֤��
	 */
	private String gzzm = "0";
	/*
	 * �м���Ա������
	 */
	private String cjrmcmc = "��";
	/*
	 * �м���Ա֤�������֤
	 */
	private String cjrzmsfmc = "��";
	/*
	 * �м���Աǩ�����Ͷ���ͬ�����Э��
	 */
	private String cjrlthtmc = "��";
	/*
	 * �м���Ա������ᱣ�յĽɷ�֤��
	 */
	private String cjrbxmc = "��";
	/*
	 * ����֤��
	 */
	private String gzzmmc = "��";
	/*
	 * �м�ְ������
	 */
	private String zfgz ;
	/*
	 * ����˰�����Ҫ���͵���������
	 */
	private String qtzl;
	/*
	 * �Ӽƿ۳���
	 */
	private String jjkcje;
	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
	 */
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrbx", this.cjrbx);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrltht", this.cjrltht);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrmc", this.cjrmc);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrzmsf", this.cjrzmsf);
//		xmlstr += XMLBuildUtil.appendStringElement("gzzm", this.gzzm);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrbxmc", this.cjrbxmc);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrlthtmc", this.cjrlthtmc);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrmcmc", this.cjrmcmc);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrzmsfmc", this.cjrzmsfmc);
//		xmlstr += XMLBuildUtil.appendStringElement("gzzmmc", this.gzzmmc);
		xmlstr += XMLBuildUtil.appendStringElement("jjkcje", this.jjkcje);
//		xmlstr += XMLBuildUtil.appendStringElement("qtzl", this.qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("zfgz", this.zfgz);

		xmlstr += "</qysdsjmba>";

	return xmlstr;
	}
	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the cjrbx.
	 */
	public String getCjrbx() {
		return cjrbx;
	}
	/**
	 * @param cjrbx The cjrbx to set.
	 */
	public void setCjrbx(String cjrbx) {
		this.cjrbx = cjrbx;
	}
	/**
	 * @return Returns the cjrltht.
	 */
	public String getCjrltht() {
		return cjrltht;
	}
	/**
	 * @param cjrltht The cjrltht to set.
	 */
	public void setCjrltht(String cjrltht) {
		this.cjrltht = cjrltht;
	}
	/**
	 * @return Returns the cjrmc.
	 */
	public String getCjrmc() {
		return cjrmc;
	}
	/**
	 * @param cjrmc The cjrmc to set.
	 */
	public void setCjrmc(String cjrmc) {
		this.cjrmc = cjrmc;
	}
	/**
	 * @return Returns the cjrzmsf.
	 */
	public String getCjrzmsf() {
		return cjrzmsf;
	}
	/**
	 * @param cjrzmsf The cjrzmsf to set.
	 */
	public void setCjrzmsf(String cjrzmsf) {
		this.cjrzmsf = cjrzmsf;
	}
	/**
	 * @return Returns the gzzm.
	 */
	public String getGzzm() {
		return gzzm;
	}
	/**
	 * @param gzzm The gzzm to set.
	 */
	public void setGzzm(String gzzm) {
		this.gzzm = gzzm;
	}
	/**
	 * @return Returns the jjkcje.
	 */
	public String getJjkcje() {
		return jjkcje;
	}
	/**
	 * @param jjkcje The jjkcje to set.
	 */
	public void setJjkcje(String jjkcje) {
		this.jjkcje = jjkcje;
	}
	/**
	 * @return Returns the qtzl.
	 */
	public String getQtzl() {
		return qtzl;
	}
	/**
	 * @param qtzl The qtzl to set.
	 */
	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}
	/**
	 * @return Returns the xh.
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh The xh to set.
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return Returns the zfgz.
	 */
	public String getZfgz() {
		return zfgz;
	}
	/**
	 * @param zfgz The zfgz to set.
	 */
	public void setZfgz(String zfgz) {
		this.zfgz = zfgz;
	}
	/**
	 * @return Returns the cjrbxmc.
	 */
	public String getCjrbxmc() {
		return cjrbxmc;
	}
	/**
	 * @param cjrbxmc The cjrbxmc to set.
	 */
	public void setCjrbxmc(String cjrbxmc) {
		this.cjrbxmc = cjrbxmc;
	}
	/**
	 * @return Returns the cjrlthtmc.
	 */
	public String getCjrlthtmc() {
		return cjrlthtmc;
	}
	/**
	 * @param cjrlthtmc The cjrlthtmc to set.
	 */
	public void setCjrlthtmc(String cjrlthtmc) {
		this.cjrlthtmc = cjrlthtmc;
	}
	/**
	 * @return Returns the cjrmcmc.
	 */
	public String getCjrmcmc() {
		return cjrmcmc;
	}
	/**
	 * @param cjrmcmc The cjrmcmc to set.
	 */
	public void setCjrmcmc(String cjrmcmc) {
		this.cjrmcmc = cjrmcmc;
	}
	/**
	 * @return Returns the cjrzmsfmc.
	 */
	public String getCjrzmsfmc() {
		return cjrzmsfmc;
	}
	/**
	 * @param cjrzmsfmc The cjrzmsfmc to set.
	 */
	public void setCjrzmsfmc(String cjrzmsfmc) {
		this.cjrzmsfmc = cjrzmsfmc;
	}
	/**
	 * @return Returns the gzzmmc.
	 */
	public String getGzzmmc() {
		return gzzmmc;
	}
	/**
	 * @param gzzmmc The gzzmmc to set.
	 */
	public void setGzzmmc(String gzzmmc) {
		this.gzzmmc = gzzmmc;
	}
}
