package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba11Vo implements JmbamxVoInterface{
    //���
    private String xh = "";
    //�������������
    private String basqwsh = "";
    //���������
    private String jsjdm = "";
    //�������
    private String band = "";
    //���ڼ��ɵ�·��ҵ��0���ǣ�1����
    private String sfsyjcdlqy = "";
	//�������
    private String hlnd = "";
    //��������
    private String qtzl = "";
    //������ʼ���
    private String jzqsnd = "";
    //������ֹ���
    private String mzzznd = "";
    //������ʼ���
    private String mzqsnd = "";
    //������ֹ���
    private String jzzznd = "";
    //Ԥ�Ƽ���˰��
    private String yjjmse = "";
    //������
    private String cjr = "";
    //��������
    private String cjrq = "";
    //¼����
    private String lrr= "";
    //¼������
    private String lrrq = "";
    //˰�������֯��������
    private String swjgzzjgdm = "";
    private String zs = "";
	public  Jmba11Vo() {
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("sfsyjcdlqy", sfsyjcdlqy);
		xmlstr += XMLBuildUtil.appendStringElement("hlnd", hlnd);
		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", jzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("yjjmse", yjjmse);
		xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
        xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
        xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
		xmlstr += "</qysdsjmba>";
		return xmlstr;
	}

	public String toXMLChilds() {
		return "";
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	
	public String getBasqwsh() {
		return basqwsh;
	}

	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getSfsyjcdlqy() {
		return sfsyjcdlqy;
	}

	public void setSfsyjcdlqy(String sfsyjcdlqy) {
		this.sfsyjcdlqy = sfsyjcdlqy;
	}

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}

	public String getQtzl() {
		return qtzl;
	}

	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}

	public String getJzqsnd() {
		return jzqsnd;
	}

	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}

	public String getMzzznd() {
		return mzzznd;
	}

	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}

	public String getMzqsnd() {
		return mzqsnd;
	}

	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}

	public String getJzzznd() {
		return jzzznd;
	}

	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
	}

	public String getYjjmse() {
		return yjjmse;
	}

	public void setYjjmse(String yjjmse) {
		this.yjjmse = yjjmse;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	
}
