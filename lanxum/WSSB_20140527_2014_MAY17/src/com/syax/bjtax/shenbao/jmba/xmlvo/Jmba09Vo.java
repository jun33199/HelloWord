package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba09Vo implements JmbamxVoInterface{
    //序号
    private String xh = "";
    //备案申请文书号
    private String basqwsh = "";
    //计算机代码
    private String jsjdm = "";
    //备案年度
    private String band = "";
    //证书类型代码，0：北京市软件企业证书，1：北京市集成电路设计企业证书
    private String zslxdm = "";
	//证书编号
    private String zsbh= "";
    //证书有效起始日期
    private String zsyxqsrq = "";
    //证书有效终止日期
    private String zsyxzzrq = "";
    //获利年度
    private String hlnd = "";
    //本年所得情况说明
    private String bnsdqksm = "";
    //其他资料
    private String qtzl = "";
    //免征起始年度
    private String mzqsnd = "";
    //免征终止年度
    private String mzzznd = "";
    //减征起始年度
    private String jzqsnd = "";
    //减征终止年度
    private String jzzznd = "";
    //创建人
    private String cjr = "";
    //创建日期
    private String cjrq = "";
    //录入人
    private String lrr = "";
    //录入日期
    private String lrrq = "";
    //记录条数
    private String zs = "";
  //税务机关组织机构代码
    private String swjgzzjgdm = "";
	public  Jmba09Vo() {
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
        xmlstr += XMLBuildUtil.appendStringElement("zslxdm", zslxdm);
		xmlstr += XMLBuildUtil.appendStringElement("zsbh", zsbh);
		xmlstr += XMLBuildUtil.appendStringElement("zsyxqsrq", zsyxqsrq);
		xmlstr += XMLBuildUtil.appendStringElement("zsyxzzrq", zsyxzzrq);
		xmlstr += XMLBuildUtil.appendStringElement("hlnd", hlnd);
		xmlstr += XMLBuildUtil.appendStringElement("bnsdqksm", bnsdqksm);
		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", jzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
        xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
        xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
		xmlstr += "</qysdsjmba>";
		return xmlstr;
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

	public String getZslxdm() {
		return zslxdm;
	}

	public void setZslxdm(String zslxdm) {
		this.zslxdm = zslxdm;
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

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}

	public String getBnsdqksm() {
		return bnsdqksm;
	}

	public void setBnsdqksm(String bnsdqksm) {
		this.bnsdqksm = bnsdqksm;
	}

	public String getQtzl() {
		return qtzl;
	}

	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}

	public String getMzqsnd() {
		return mzqsnd;
	}

	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}

	public String getMzzznd() {
		return mzzznd;
	}

	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}

	public String getJzqsnd() {
		return jzqsnd;
	}

	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}

	public String getJzzznd() {
		return jzzznd;
	}

	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
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

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	
}
