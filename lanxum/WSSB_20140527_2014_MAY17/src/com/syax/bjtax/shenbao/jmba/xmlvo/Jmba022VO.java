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
 * 
 * ��Ŀ���ƣ�wssb   
 * �����ƣ�Jmba022VO   
 * ��������  ���ӽ��ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-13 ����4:50:39   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-13 ����4:50:39   
 * �޸ı�ע��   
 * @version  1.0
 */
public class Jmba022VO implements JmbamxVoInterface {
	private String hc;
    /*
     * ���
     */
    private String xh;


    /*
     * ���ܼ��ż���������������
     */
    private String jnjpjsgzxmmc;

    /*
     * ���ܼ��ż����������ʹ���
     */
    private String jnjpjsgzxmdm;

    /*
     * ȡ�õ�һ����������֤����������
     */
    private String dybzlmc;

    /*
     * ȡ�õ�һ��������Ӫ�����ʱ��
     */
    private String dybrq;
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
    private String jbzsqsnd;

    /*
     * ������ֹ���
     */
    private String jbzszznd;
    
    
    /**
     * �Ƿ�����ͬ��Դ������Ŀת�á�����
     */
    private String ZRHTXM="";
    /**
     * ��Ŀת�ú�ͬ����Ŀԭ�����Żݱ����ļ�
     */
    private String ZRHTXMYHWJ="";
    /**
     * ��Ŀת�ú�ͬ����Ŀԭ�����Ż�
     */
    private String ZRHTXMYH="";
    
    
	public String getZRHTXM() {
		return ZRHTXM;
	}

	public void setZRHTXM(String zRHTXM) {
		ZRHTXM = zRHTXM;
	}

	public String getZRHTXMYHWJ() {
		return ZRHTXMYHWJ;
	}

	public void setZRHTXMYHWJ(String zRHTXMYHWJ) {
		ZRHTXMYHWJ = zRHTXMYHWJ;
	}

	public String getZRHTXMYH() {
		return ZRHTXMYH;
	}

	public void setZRHTXMYH(String zRHTXMYH) {
		ZRHTXMYH = zRHTXMYH;
	}

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
		xmlstr += XMLBuildUtil.appendStringElement("jnjpjsgzxmdm", jnjpjsgzxmdm);
		xmlstr += XMLBuildUtil.appendStringElement("jnjpjsgzxmmc", jnjpjsgzxmmc);
		xmlstr += XMLBuildUtil.appendStringElement("dybzlmc", dybzlmc);
		xmlstr += XMLBuildUtil.appendStringElement("dybrq", dybrq);
		xmlstr += XMLBuildUtil.appendStringElement("jbzsqsnd", jbzsqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jbzszznd", jbzszznd);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		
		xmlstr += XMLBuildUtil.appendStringElement("ZRHTXM", ZRHTXM);
		xmlstr += XMLBuildUtil.appendStringElement("ZRHTXMYH", ZRHTXMYH);
		xmlstr += XMLBuildUtil.appendStringElement("ZRHTXMYHWJ", ZRHTXMYHWJ);
		
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
	public String getJnjpjsgzxmmc() {
		return jnjpjsgzxmmc;
	}

	public void setJnjpjsgzxmmc(String jnjpjsgzxmmc) {
		this.jnjpjsgzxmmc = jnjpjsgzxmmc;
	}

	public String getJnjpjsgzxmdm() {
		return jnjpjsgzxmdm;
	}

	public void setJnjpjsgzxmdm(String jnjpjsgzxmdm) {
		this.jnjpjsgzxmdm = jnjpjsgzxmdm;
	}

	public String getJbzsqsnd() {
		return jbzsqsnd;
	}

	public void setJbzsqsnd(String jbzsqsnd) {
		this.jbzsqsnd = jbzsqsnd;
	}

	public String getJbzszznd() {
		return jbzszznd;
	}

	public void setJbzszznd(String jbzszznd) {
		this.jbzszznd = jbzszznd;
	}
	
	
}
