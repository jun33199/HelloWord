/*
 * Created on 2009-12-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.List;
import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Jmba02VO implements JmbamxVoInterface {
	private String hc;
    /*
     * ���
     */
    private String xh;

 
    /*
     * �з������������
     */
    private String yffylydm;

    /*
     * �з�������������
     */
    private String yffylymc;

    /*
     * �Ƿ��мƻ����Ԥ��,0:��,1:��
     */
    private String sfyjhys = "0";

    /*
     * �Ƿ��б��������רҵ��Ա����,0:��,1:��
     */
    private String sfybzry = "0";

    /*
     * �Ƿ����з���������鼯��,0:��,1:��
     */
    private String sfyyffyqk = "0";

    /*
     * �Ƿ��о����ļ�,0:��,1:��
     */
    private String sfyjywj = "0";

    /*
     * �Ƿ��к�ͬЭ��,0:��,1:��
     */
    private String sfyhtxy = "0";

    /*
     * �Ƿ����о��ɹ�����,0:��,1:��
     */
    private String sfyyjcg = "0";

    /*
     * �н������֤����,0:��,1:��
     */
    private String sfyzjjzbg = "0";
    /*
     * �Ƿ��мƻ����Ԥ��,0:��,1:��
     */
    private String sfyjhysmc = "��";

    /*
     * �Ƿ��б��������רҵ��Ա����,0:��,1:��
     */
    private String sfybzrymc = "��";

    /*
     * �Ƿ����з���������鼯��,0:��,1:��
     */
    private String sfyyffyqkmc = "��";

    /*
     * �Ƿ��о����ļ�,0:��,1:��
     */
    private String sfyjywjmc = "��";

    /*
     * �Ƿ��к�ͬЭ��,0:��,1:��
     */
    private String sfyhtxymc = "��";

    /*
     * �Ƿ����о��ɹ�����,0:��,1:��
     */
    private String sfyyjcgmc = "��";

    /*
     * �н������֤����,0:��,1:��
     */
    private String sfyzjjzbgmc = "��";

    /*
     * ���ڿ۳����
     */
    private String dqkcje;

    /*
     * �γ������ʲ����
     */
    private String wxzcje;

    /*
     * �Ӽƿ۳���
     */
    private String jjkcje;

    /*
     * ��������
     */
    private String qtzl;
    /*
     * ��Ŀ����
     */
    private String xmmc;


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
		xmlstr += XMLBuildUtil.appendStringElement("hc", hc);
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("dqkcje", dqkcje);
		xmlstr += XMLBuildUtil.appendStringElement("jjkcje", jjkcje);
//		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfybzry", sfybzry);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyhtxy", sfyhtxy);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyjhys", sfyjhys);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyjywj", sfyjywj);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyyffyqk", sfyyffyqk);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyyjcg", sfyyjcg);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyzjjzbg", sfyzjjzbg);
//		xmlstr += XMLBuildUtil.appendStringElement("sfybzrymc", sfybzrymc);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyhtxymc", sfyhtxymc);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyjhysmc", sfyjhysmc);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyjywjmc", sfyjywjmc);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyyffyqkmc", sfyyffyqkmc);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyyjcgmc", sfyyjcgmc);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyzjjzbgmc", sfyzjjzbgmc);
		xmlstr += XMLBuildUtil.appendStringElement("wxzcje", wxzcje);
		xmlstr += XMLBuildUtil.appendStringElement("xmmc", xmmc);
		xmlstr += XMLBuildUtil.appendStringElement("yffylydm", yffylydm);
		xmlstr += XMLBuildUtil.appendStringElement("yffylymc", yffylymc);
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
	 * @return Returns the dqkcje.
	 */
	public String getDqkcje() {
		return dqkcje;
	}
	/**
	 * @param dqkcje The dqkcje to set.
	 */
	public void setDqkcje(String dqkcje) {
		this.dqkcje = dqkcje;
	}
	/**
	 * @return Returns the hc.
	 */
	public String getHc() {
		return hc;
	}
	/**
	 * @param hc The hc to set.
	 */
	public void setHc(String hc) {
		this.hc = hc;
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
	 * @return Returns the sfybzry.
	 */
	public String getSfybzry() {
		return sfybzry;
	}
	/**
	 * @param sfybzry The sfybzry to set.
	 */
	public void setSfybzry(String sfybzry) {
		this.sfybzry = sfybzry;
	}
	/**
	 * @return Returns the sfyhtxy.
	 */
	public String getSfyhtxy() {
		return sfyhtxy;
	}
	/**
	 * @param sfyhtxy The sfyhtxy to set.
	 */
	public void setSfyhtxy(String sfyhtxy) {
		this.sfyhtxy = sfyhtxy;
	}
	/**
	 * @return Returns the sfyjhys.
	 */
	public String getSfyjhys() {
		return sfyjhys;
	}
	/**
	 * @param sfyjhys The sfyjhys to set.
	 */
	public void setSfyjhys(String sfyjhys) {
		this.sfyjhys = sfyjhys;
	}
	/**
	 * @return Returns the sfyjywj.
	 */
	public String getSfyjywj() {
		return sfyjywj;
	}
	/**
	 * @param sfyjywj The sfyjywj to set.
	 */
	public void setSfyjywj(String sfyjywj) {
		this.sfyjywj = sfyjywj;
	}
	/**
	 * @return Returns the sfyyffyqk.
	 */
	public String getSfyyffyqk() {
		return sfyyffyqk;
	}
	/**
	 * @param sfyyffyqk The sfyyffyqk to set.
	 */
	public void setSfyyffyqk(String sfyyffyqk) {
		this.sfyyffyqk = sfyyffyqk;
	}
	/**
	 * @return Returns the sfyyjcg.
	 */
	public String getSfyyjcg() {
		return sfyyjcg;
	}
	/**
	 * @param sfyyjcg The sfyyjcg to set.
	 */
	public void setSfyyjcg(String sfyyjcg) {
		this.sfyyjcg = sfyyjcg;
	}
	/**
	 * @return Returns the sfyzjjzbg.
	 */
	public String getSfyzjjzbg() {
		return sfyzjjzbg;
	}
	/**
	 * @param sfyzjjzbg The sfyzjjzbg to set.
	 */
	public void setSfyzjjzbg(String sfyzjjzbg) {
		this.sfyzjjzbg = sfyzjjzbg;
	}
	/**
	 * @return Returns the wxzcje.
	 */
	public String getWxzcje() {
		return wxzcje;
	}
	/**
	 * @param wxzcje The wxzcje to set.
	 */
	public void setWxzcje(String wxzcje) {
		this.wxzcje = wxzcje;
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
	 * @return Returns the xmmc.
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc The xmmc to set.
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return Returns the yffylydm.
	 */
	public String getYffylydm() {
		return yffylydm;
	}
	/**
	 * @param yffylydm The yffylydm to set.
	 */
	public void setYffylydm(String yffylydm) {
		this.yffylydm = yffylydm;
	}
	/**
	 * @return Returns the yffylymc.
	 */
	public String getYffylymc() {
		return yffylymc;
	}
	/**
	 * @param yffylymc The yffylymc to set.
	 */
	public void setYffylymc(String yffylymc) {
		this.yffylymc = yffylymc;
	}
	/**
	 * @return Returns the sfybzrymc.
	 */
	public String getSfybzrymc() {
		return sfybzrymc;
	}
	/**
	 * @param sfybzrymc The sfybzrymc to set.
	 */
	public void setSfybzrymc(String sfybzrymc) {
		this.sfybzrymc = sfybzrymc;
	}
	/**
	 * @return Returns the sfyhtxymc.
	 */
	public String getSfyhtxymc() {
		return sfyhtxymc;
	}
	/**
	 * @param sfyhtxymc The sfyhtxymc to set.
	 */
	public void setSfyhtxymc(String sfyhtxymc) {
		this.sfyhtxymc = sfyhtxymc;
	}
	/**
	 * @return Returns the sfyjhysmc.
	 */
	public String getSfyjhysmc() {
		return sfyjhysmc;
	}
	/**
	 * @param sfyjhysmc The sfyjhysmc to set.
	 */
	public void setSfyjhysmc(String sfyjhysmc) {
		this.sfyjhysmc = sfyjhysmc;
	}
	/**
	 * @return Returns the sfyjywjmc.
	 */
	public String getSfyjywjmc() {
		return sfyjywjmc;
	}
	/**
	 * @param sfyjywjmc The sfyjywjmc to set.
	 */
	public void setSfyjywjmc(String sfyjywjmc) {
		this.sfyjywjmc = sfyjywjmc;
	}
	/**
	 * @return Returns the sfyyffyqkmc.
	 */
	public String getSfyyffyqkmc() {
		return sfyyffyqkmc;
	}
	/**
	 * @param sfyyffyqkmc The sfyyffyqkmc to set.
	 */
	public void setSfyyffyqkmc(String sfyyffyqkmc) {
		this.sfyyffyqkmc = sfyyffyqkmc;
	}
	/**
	 * @return Returns the sfyyjcgmc.
	 */
	public String getSfyyjcgmc() {
		return sfyyjcgmc;
	}
	/**
	 * @param sfyyjcgmc The sfyyjcgmc to set.
	 */
	public void setSfyyjcgmc(String sfyyjcgmc) {
		this.sfyyjcgmc = sfyyjcgmc;
	}
	/**
	 * @return Returns the sfyzjjzbgmc.
	 */
	public String getSfyzjjzbgmc() {
		return sfyzjjzbgmc;
	}
	/**
	 * @param sfyzjjzbgmc The sfyzjjzbgmc to set.
	 */
	public void setSfyzjjzbgmc(String sfyzjjzbgmc) {
		this.sfyzjjzbgmc = sfyzjjzbgmc;
	}
}
