package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba10Vo implements JmbamxVoInterface{
    //���
    private String xh = "";
    //�������������
    private String basqwsh = "";
    //���������
    private String jsjdm = "";
    //�������
    private String band = "";
    //�滮�������ص������ҵ��0���ǣ�1����
    private String sfghbjnrjqy = "";
	//��������
    private String qtzl = "";
    //Ԥ�Ƽ�˰��
    private String yjjse = "";
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
	public  Jmba10Vo() {
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

	public String getSfghbjnrjqy() {
		return sfghbjnrjqy;
	}

	public void setSfghbjnrjqy(String sfghbjnrjqy) {
		this.sfghbjnrjqy = sfghbjnrjqy;
	}

	public String getQtzl() {
		return qtzl;
	}

	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}

	public String getYjjse() {
		return yjjse;
	}

	public void setYjjse(String yjjse) {
		this.yjjse = yjjse;
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

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("sfghbjnrjqy", sfghbjnrjqy);
		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("yjjse", yjjse);
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
}
