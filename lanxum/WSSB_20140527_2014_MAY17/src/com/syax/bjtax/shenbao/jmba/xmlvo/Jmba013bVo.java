package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba013bVo implements JmbamxVoInterface{
    //���
    private String xh = "";
    //�������������
    private String basqbh = "";
    //���������
    private String jsjdm = "";
    //��Ͷ����ҵ���������
    private String btzqyjsjdm = "";
    //�������
    private String band = "";
    //¼����
    private String lrr = "";
    //¼������
    private String lrrq = "";
    //˰�������֯��������
    private String swjgzzjgdm = "";
    //�����
    private String basqwsh = "";
    //˰��Ǽ�֤��
    private String swdjzh = "";
    //��˰������
    private String nsrmc = "";
    //���¼����������
    private String gxjslydm = "";
    //���¼�����������
    private String gxjslymc = "";
    //��������Ͷ�ʶ� 
    private String tze = "";
    //����ʵ�ʵֿ�Ӧ��˰���ö�
    private String dke = "";
    //����ɵֿ�Ӧ��˰���ö�
    private String dnkdke = "";
    //��ת�Ժ���ȵֿ�Ӧ��˰���ö�
    private String jze = "";
    //Ͷ�����
    private String tznd = "";
   //��Ͷ����ҵ������
    private String btzqyssd = "";
    //��Ͷ����ҵ����
    private String btzqymc = "";
    //����ɱ�����ʶ
    private String ywcbabs = "";
	public  Jmba013bVo() {
	}

	public Map getListTypeMap() {
		return null;
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

	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}

	
	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
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

	public String getBasqwsh() {
		return basqwsh;
	}

	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}

	public String getSwdjzh() {
		return swdjzh;
	}

	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getGxjslydm() {
		return gxjslydm;
	}

	public void setGxjslydm(String gxjslydm) {
		this.gxjslydm = gxjslydm;
	}

	public String getGxjslymc() {
		return gxjslymc;
	}

	public void setGxjslymc(String gxjslymc) {
		this.gxjslymc = gxjslymc;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqbh);
        xmlstr += XMLBuildUtil.appendStringElement("btzqyjsjdm", btzqyjsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
        xmlstr += XMLBuildUtil.appendStringElement("swdjzh", swdjzh);
		xmlstr += XMLBuildUtil.appendStringElement("nsrmc", nsrmc);
		xmlstr += XMLBuildUtil.appendStringElement("gxjslydm", gxjslydm);
		xmlstr += XMLBuildUtil.appendStringElement("gxjslymc", gxjslymc);
        xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
        xmlstr += XMLBuildUtil.appendStringElement("tznd", tznd);
		xmlstr += XMLBuildUtil.appendStringElement("tze", tze);
		xmlstr += XMLBuildUtil.appendStringElement("dke", dke);
		xmlstr += XMLBuildUtil.appendStringElement("dnkdke", dnkdke);
        xmlstr += XMLBuildUtil.appendStringElement("jze", jze);
        xmlstr += XMLBuildUtil.appendStringElement("btzqyssd", btzqyssd);
        xmlstr += XMLBuildUtil.appendStringElement("btzqymc", btzqymc);
        xmlstr += XMLBuildUtil.appendStringElement("ywcbabs", ywcbabs);
		xmlstr += "</qysdsjmba>";
		return xmlstr;
	}
	public String toXMLChilds() {
		return "";
	}

	public String getTze() {
		return tze;
	}

	public void setTze(String tze) {
		this.tze = tze;
	}

	public String getDke() {
		return dke;
	}

	public void setDke(String dke) {
		this.dke = dke;
	}

	public String getDnkdke() {
		return dnkdke;
	}

	public void setDnkdke(String dnkdke) {
		this.dnkdke = dnkdke;
	}

	public String getJze() {
		return jze;
	}

	public void setJze(String jze) {
		this.jze = jze;
	}

	public String getTznd() {
		return tznd;
	}

	public void setTznd(String tznd) {
		this.tznd = tznd;
	}

	public String getBtzqyssd() {
		return btzqyssd;
	}

	public void setBtzqyssd(String btzqyssd) {
		this.btzqyssd = btzqyssd;
	}

	public String getBtzqyjsjdm() {
		return btzqyjsjdm;
	}

	public void setBtzqyjsjdm(String btzqyjsjdm) {
		this.btzqyjsjdm = btzqyjsjdm;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getBtzqymc() {
		return btzqymc;
	}

	public void setBtzqymc(String btzqymc) {
		this.btzqymc = btzqymc;
	}

	public String getYwcbabs() {
		return ywcbabs;
	}

	public void setYwcbabs(String ywcbabs) {
		this.ywcbabs = ywcbabs;
	}

}
