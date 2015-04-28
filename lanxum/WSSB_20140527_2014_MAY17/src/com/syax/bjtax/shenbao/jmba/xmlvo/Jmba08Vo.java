package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba08Vo implements JmbamxVoInterface{
    //序号
    private String xh = "";
    //备案申请文书号
    private String basqwsh = "";
    //计算机代码
    private String jsjdm = "";
    //备案年度
    private String band = "";
    //高新技术领域代码
    private String gxjslydm = "";
	//证书编号
    private String zsbh = "";
    //证书有效起始日期
    private String zsyxqsrq = "";
    //证书有效终止日期
    private String zsyxzzrq = "";
    //是否属于规定范围，0：是，1：否
    private String sfysygdfw = "";
    //中介机构鉴证报告，0：有，1：无
    private String sfyzjjzbg = "";
    //专科以上比例
    private String zkysbl = "";
    //研发人员比例
    private String yfrybl = "";
    //费用结构明细表，0：有，1：无
    private String sfyfyjgmxb = "";
    //研发费用比例
    private String yffybl = "";
    //高新产品收入比例
    private String gxcpsrbl = "";
    //创建人
    private String cjr = "";
    //创建日期
    private String cjrq = "";
    //录入人
    private String lrr = "";
    //录入日期
    private String lrrq = "";
    //税务机关组织机构代码
    private String swjgzzjgdm = "";
	public  Jmba08Vo() {
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
        xmlstr += XMLBuildUtil.appendStringElement("gxjslydm", gxjslydm);
		xmlstr += XMLBuildUtil.appendStringElement("zsbh", zsbh);
		xmlstr += XMLBuildUtil.appendStringElement("zsyxqsrq", zsyxqsrq);
		xmlstr += XMLBuildUtil.appendStringElement("zsyxzzrq", zsyxzzrq);
		xmlstr += XMLBuildUtil.appendStringElement("sfysygdfw", sfysygdfw);
		xmlstr += XMLBuildUtil.appendStringElement("sfyzjjzbg", sfyzjjzbg);
		xmlstr += XMLBuildUtil.appendStringElement("zkysbl", zkysbl);
		xmlstr += XMLBuildUtil.appendStringElement("yfrybl", yfrybl);
		xmlstr += XMLBuildUtil.appendStringElement("sfyfyjgmxb", sfyfyjgmxb);
		xmlstr += XMLBuildUtil.appendStringElement("yffybl", yffybl);
		xmlstr += XMLBuildUtil.appendStringElement("gxcpsrbl", gxcpsrbl);
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

	public String getGxjslydm() {
		return gxjslydm;
	}

	public void setGxjslydm(String gxjslydm) {
		this.gxjslydm = gxjslydm;
	}

	public String getZsbh() {
		return zsbh;
	}

	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}

	public String getZsyxqsrq() {
		return zsyxqsrq;
	}

	public void setZsyxqsrq(String zsyxqsrq) {
		this.zsyxqsrq = zsyxqsrq;
	}

	public String getZsyxzzrq() {
		return zsyxzzrq;
	}

	public void setZsyxzzrq(String zsyxzzrq) {
		this.zsyxzzrq = zsyxzzrq;
	}

	public String getSfysygdfw() {
		return sfysygdfw;
	}

	public void setSfysygdfw(String sfysygdfw) {
		this.sfysygdfw = sfysygdfw;
	}

	public String getSfyzjjzbg() {
		return sfyzjjzbg;
	}

	public void setSfyzjjzbg(String sfyzjjzbg) {
		this.sfyzjjzbg = sfyzjjzbg;
	}

	public String getZkysbl() {
		return zkysbl;
	}

	public void setZkysbl(String zkysbl) {
		this.zkysbl = zkysbl;
	}

	public String getYfrybl() {
		return yfrybl;
	}

	public void setYfrybl(String yfrybl) {
		this.yfrybl = yfrybl;
	}

	public String getSfyfyjgmxb() {
		return sfyfyjgmxb;
	}

	public void setSfyfyjgmxb(String sfyfyjgmxb) {
		this.sfyfyjgmxb = sfyfyjgmxb;
	}

	public String getYffybl() {
		return yffybl;
	}

	public void setYffybl(String yffybl) {
		this.yffybl = yffybl;
	}

	public String getGxcpsrbl() {
		return gxcpsrbl;
	}

	public void setGxcpsrbl(String gxcpsrbl) {
		this.gxcpsrbl = gxcpsrbl;
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

	
}
