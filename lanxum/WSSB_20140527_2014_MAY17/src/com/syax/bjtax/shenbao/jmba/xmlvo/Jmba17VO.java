package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description:  (十九)清洁发展机制项目的所得vo </p>
 *
 * <p>Copyright: 四一安信</p>
 *
 * <p>Company: 四一安信</p>
 *
 * @author mijun
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba17VO implements JmbamxVoInterface {

	// 序号
	private String xh = "";
//
//	// 备案申请文书号
//	private String basqwsh = "";
//
//	// 计算机代码
//	private String jsjdm = "";
//
//	// 备案年度
//	private String band = "";
//
//	// 税务机关组织机关代码
//	private String swjgzzjgdm = "";

	// 转让收入金额
	private String zrsrje = "";

	// 上缴给国家的HFC和PFC类CDM项目的金额
	private String sjje1 = "";

	// 上缴给国家的N2O类CDM项目的金额
	private String sjje2 = "";

//	// 有转让证明材料
//	private String sfyzrzmcl = "";
//
//	// 有上缴证明材料
//	private String sfysjzmcl = "";
//
//	// 有收入证明材料
//	private String sfysrzmcl = "";
//
//	// 有核算情况声明
//	private String sfyhsqksm = "";

	// 获利年度
	private String hlnd = "";

	// 其他资料
	//private String qtzl = "";

	// 免征起始年度
	private String mzqsnd = "";

	// 免征终止年度
	private String mzzznd = "";

	// 减征起始年度
	private String jzqsnd = "";

	// 减征终止年度
	private String jzzznd = "";

//	// 创建人
//	private String cjr = "";
//
//	// 创建日期
//	private String cjrq = "";
//
//	// 录入人
//	private String lrr = "";
//
//	// 录入日期
//	private String lrrq = "";

	public Jmba17VO() {
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", this.xh);
//		xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
//		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
//		xmlstr += XMLBuildUtil.appendStringElement("band", band);
//		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("zrsrje", this.zrsrje);
		xmlstr += XMLBuildUtil.appendStringElement("sjje1", this.sjje1);
		xmlstr += XMLBuildUtil.appendStringElement("sjje2", this.sjje2);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyzrzmcl", sfyzrzmcl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfysjzmcl", sfysjzmcl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfysrzmcl", sfysrzmcl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyhsqksm", sfyhsqksm);
		xmlstr += XMLBuildUtil.appendStringElement("hlnd", this.hlnd);
//		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", this.mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", this.mzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", this.jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", this.jzzznd);
//		xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
//		xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
//		xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
		xmlstr += "</qysdsjmba>";

		return xmlstr;

	}

	public String toXMLChilds() {
		return "";
	}

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
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

	public String getSjje1() {
		return sjje1;
	}

	public void setSjje1(String sjje1) {
		this.sjje1 = sjje1;
	}

	public String getSjje2() {
		return sjje2;
	}

	public void setSjje2(String sjje2) {
		this.sjje2 = sjje2;
	}

	public String getZrsrje() {
		return zrsrje;
	}

	public void setZrsrje(String zrsrje) {
		this.zrsrje = zrsrje;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

}
