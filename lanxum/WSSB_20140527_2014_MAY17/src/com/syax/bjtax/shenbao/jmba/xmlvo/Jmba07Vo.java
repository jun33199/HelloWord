package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba07Vo implements JmbamxVoInterface {
    //���
    private String xh = "";
    //�������������
    private String basqbh = "";
    //���������
    private String jsjdm = "";
    //�������
    private String band = "";
    //����ת�����ʹ���
    private String jszrlxdm = "";
    //����ת�����ʹ���
    private String jszrlxmc = "";
	//����ѧ�������������϶��Ǽǵļ���ת�ú�ͬ
    private String jszyht = "";
    //����ת�ú�ͬ�϶��Ǽ�֤�����϶��ǼǱ�
    private String djb = "";
    //ʵ�ʷ����ļ���������������ϸ��
    private String mxb = "";
    //��Ŀ���ú����������
    private String hsqksm = "";
    //���ܻ���Ҫ���͵���������
    private String qtzl = "";
    //ȡ�ü���ת������
    private String jszrsd = "";
    //������
    private String cjr = "";
    //��������
    private String cjrq = "";
    //¼����
    private String lrr = "";
    //¼������
    private String lrrq = "";
    //˰�������֯��������
    private String swjgzzjgdm = "";
    //�����
    private String basqwsh = "";
    private String jszrhtmc = "";
    private String jnjwbs = "";
	public  Jmba07Vo() {
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqbh);
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("jszrlxdm", jszrlxdm);
        xmlstr += XMLBuildUtil.appendStringElement("jszrlxmc", jszrlxmc);
		xmlstr += XMLBuildUtil.appendStringElement("jszyht", jszyht);
		xmlstr += XMLBuildUtil.appendStringElement("djb", djb);
		xmlstr += XMLBuildUtil.appendStringElement("mxb", mxb);
		xmlstr += XMLBuildUtil.appendStringElement("hsqksm", hsqksm);
		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("jszrsd", jszrsd);
		xmlstr += XMLBuildUtil.appendStringElement("jszrhtmc", jszrhtmc);
		xmlstr += XMLBuildUtil.appendStringElement("jnjwbs", jnjwbs);
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

	public String getBasqbh() {
		return basqbh;
	}

	public void setBasqbh(String basqwsh) {
		this.basqbh = basqwsh;
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

	public String getJszrlxdm() {
		return jszrlxdm;
	}

	public void setJszrlxdm(String jszrlxdm) {
		this.jszrlxdm = jszrlxdm;
	}

	public String getJszyht() {
		return jszyht;
	}

	public void setJszyht(String jszyht) {
		this.jszyht = jszyht;
	}

	public String getDjb() {
		return djb;
	}

	public void setDjb(String djb) {
		this.djb = djb;
	}

	public String getMxb() {
		return mxb;
	}

	public void setMxb(String mxb) {
		this.mxb = mxb;
	}

	public String getHsqksm() {
		return hsqksm;
	}

	public void setHsqksm(String hsqksm) {
		this.hsqksm = hsqksm;
	}

	public String getQtzl() {
		return qtzl;
	}

	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}

	public String getJszrsd() {
		return jszrsd;
	}

	public void setJszrsd(String jszrsd) {
		this.jszrsd = jszrsd;
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

	/**
	 * @return Returns the basqwsh.
	 */
	public String getBasqwsh() {
		return basqwsh;
	}
	/**
	 * @param basqwsh The basqwsh to set.
	 */
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	/**
	 * @return Returns the swjgzzjgdm.
	 */
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	/**
	 * @param swjgzzjgdm The swjgzzjgdm to set.
	 */
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getJszrlxmc() {
		return jszrlxmc;
	}

	public void setJszrlxmc(String jszrlxmc) {
		this.jszrlxmc = jszrlxmc;
	}

	public String getJszrhtmc() {
		return jszrhtmc;
	}

	public void setJszrhtmc(String jszrhtmc) {
		this.jszrhtmc = jszrhtmc;
	}

	public String getJnjwbs() {
		return jnjwbs;
	}

	public void setJnjwbs(String jnjwbs) {
		this.jnjwbs = jnjwbs;
	}
}
