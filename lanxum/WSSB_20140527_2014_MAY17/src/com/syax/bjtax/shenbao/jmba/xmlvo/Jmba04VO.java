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
public class Jmba04VO implements JmbamxVoInterface {
	private String hc;
    /*
     * ���
     */
    private String xh;


    /*
     * ����������ʩ��Ŀ��������
     */
    private String ggjcssxmlxmc;

    /*
     * ����������ʩ��Ŀ���ʹ���
     */
    private String ggjcssxmlxdm;

    /*
     * �ļ�����
     */
    private String wjmc;

    /*
     * �ĺ�
     */
    private String wh;

    /*
     * ȡ�õ�һ����������֤����������
     */
    private String dybzlmc;

    /*
     * ȡ�õ�һ��������Ӫ�����ʱ��
     */
    private String dybrq;

    /*
     * ��Ŀ���ú���������� 0:��,1:��
     */
    private String sfyhssm = "0";
    /*
     * ��Ŀ���ú���������� 0:��,1:��
     */
    private String sfyhssmmc = "��";

    /*
     * ����˰�����Ҫ���͵���������
     */
    private String qtzl;

    /*
     * ������ʼ���
     */
    private String mzqsnd;

    /*
     * ������ֹ���
     */
    private String mzzznd;

    /*
     * ������ʼ���
     */
    private String jzqsnd;

    /*
     * ������ֹ���
     */
    private String jzzznd;

    /*
     * �ٴα���
     */
    private String zcba="1";

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
		xmlstr += XMLBuildUtil.appendStringElement("ggjcssxmlxdm", ggjcssxmlxdm);
		xmlstr += XMLBuildUtil.appendStringElement("ggjcssxmlxmc", ggjcssxmlxmc);
		xmlstr += XMLBuildUtil.appendStringElement("wjmc", wjmc);
		xmlstr += XMLBuildUtil.appendStringElement("wh", wh);
		xmlstr += XMLBuildUtil.appendStringElement("dybzlmc", dybzlmc);
		xmlstr += XMLBuildUtil.appendStringElement("dybrq", dybrq);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", jzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
//		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
//		xmlstr += XMLBuildUtil.appendStringElement("zcba", zcba);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyhssm", sfyhssm);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyhssmmc", sfyhssmmc);
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
	 * @return Returns the dybrq.
	 */
	public String getDybrq() {
		return dybrq;
	}
	/**
	 * @param dybrq The dybrq to set.
	 */
	public void setDybrq(String dybrq) {
		this.dybrq = dybrq;
	}
	/**
	 * @return Returns the dybzlmc.
	 */
	public String getDybzlmc() {
		return dybzlmc;
	}
	/**
	 * @param dybzlmc The dybzlmc to set.
	 */
	public void setDybzlmc(String dybzlmc) {
		this.dybzlmc = dybzlmc;
	}
	/**
	 * @return Returns the ggjcssxmlxdm.
	 */
	public String getGgjcssxmlxdm() {
		return ggjcssxmlxdm;
	}
	/**
	 * @param ggjcssxmlxdm The ggjcssxmlxdm to set.
	 */
	public void setGgjcssxmlxdm(String ggjcssxmlxdm) {
		this.ggjcssxmlxdm = ggjcssxmlxdm;
	}
	/**
	 * @return Returns the ggjcssxmlxmc.
	 */
	public String getGgjcssxmlxmc() {
		return ggjcssxmlxmc;
	}
	/**
	 * @param ggjcssxmlxmc The ggjcssxmlxmc to set.
	 */
	public void setGgjcssxmlxmc(String ggjcssxmlxmc) {
		this.ggjcssxmlxmc = ggjcssxmlxmc;
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
	 * @return Returns the jzqsnd.
	 */
	public String getJzqsnd() {
		return jzqsnd;
	}
	/**
	 * @param jzqsnd The jzqsnd to set.
	 */
	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}
	/**
	 * @return Returns the jzzznd.
	 */
	public String getJzzznd() {
		return jzzznd;
	}
	/**
	 * @param jzzznd The jzzznd to set.
	 */
	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
	}
	/**
	 * @return Returns the mzqsnd.
	 */
	public String getMzqsnd() {
		return mzqsnd;
	}
	/**
	 * @param mzqsnd The mzqsnd to set.
	 */
	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}
	/**
	 * @return Returns the mzzznd.
	 */
	public String getMzzznd() {
		return mzzznd;
	}
	/**
	 * @param mzzznd The mzzznd to set.
	 */
	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
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
	 * @return Returns the sfyhssm.
	 */
	public String getSfyhssm() {
		return sfyhssm;
	}
	/**
	 * @param sfyhssm The sfyhssm to set.
	 */
	public void setSfyhssm(String sfyhssm) {
		this.sfyhssm = sfyhssm;
	}
	/**
	 * @return Returns the sfyhssmmc.
	 */
	public String getSfyhssmmc() {
		return sfyhssmmc;
	}
	/**
	 * @param sfyhssmmc The sfyhssmmc to set.
	 */
	public void setSfyhssmmc(String sfyhssmmc) {
		this.sfyhssmmc = sfyhssmmc;
	}
	/**
	 * @return Returns the wh.
	 */
	public String getWh() {
		return wh;
	}
	/**
	 * @param wh The wh to set.
	 */
	public void setWh(String wh) {
		this.wh = wh;
	}
	/**
	 * @return Returns the wjmc.
	 */
	public String getWjmc() {
		return wjmc;
	}
	/**
	 * @param wjmc The wjmc to set.
	 */
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
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
	 * @return Returns the zcba.
	 */
	public String getZcba() {
		return zcba;
	}
	/**
	 * @param zcba The zcba to set.
	 */
	public void setZcba(String zcba) {
		this.zcba = zcba;
	}
}
