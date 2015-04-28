package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba07Vo implements JmbamxVoInterface {
    //序号
    private String xh = "";
    //备案申请文书号
    private String basqbh = "";
    //计算机代码
    private String jsjdm = "";
    //备案年度
    private String band = "";
    //技术转让类型代码
    private String jszrlxdm = "";
    //技术转让类型代码
    private String jszrlxmc = "";
	//经科学技术行政部门认定登记的技术转让合同
    private String jszyht = "";
    //技术转让合同认定登记证明和认定登记表
    private String djb = "";
    //实际发生的技术性收入收入明细表
    private String mxb = "";
    //项目所得核算情况表明
    private String hsqksm = "";
    //主管机关要求报送的其他资料
    private String qtzl = "";
    //取得技术转让所得
    private String jszrsd = "";
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
    //文书号
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
