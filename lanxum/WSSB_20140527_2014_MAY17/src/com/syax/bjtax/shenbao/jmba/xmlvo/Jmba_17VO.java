package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description: (十七)固定资产缩短折旧年限或加速折旧 vo</p>
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

public class Jmba_17VO implements JmbamxVoInterface {

	// 序号
	private String xh = "";

	// 备案申请文书号
	private String basqwsh = "";

	// 计算机代码
	private String jsjdm = "";

	// 备案年度
	private String band = "";

	// 税务机关组织机关代码
	private String swjgzzjgdm = "";

	// 固定资产名称（缩短折旧年限）
	private String gdzcmc_sd = "";

	// 年限短于最低，0：是，1：否
	private String sfnxdyzd_sd = "";

	// 提供处置情况说明，0：是，1：否
	private String sftgczqksm_sd = "";

	// 固定资产原值（缩短折旧年限）
	private String gdzcyz_sd = "";

	// 固定资产计税基础（缩短折旧年限）
	private String gdzcjsjc_sd = "";

	// 税法规定最低年限
	private String sfgdzdnx_sd = "";

	// 加速折旧最低年限
	private String jszjzdnx_sd = "";

	// 计提折旧起始年度
	private String zjqsnd_sd = "";

	// 计提折旧终止年度
	private String zjzznd_sd = "";

	// 每年可扣除的折旧额
	private String zje_sd = "";

	// 已计提折旧的年限
	private String yjtzjnx_sd = "";

	// 已计提的折旧额
	private String yjtzje_sd = "";

	// 固定资产名称（加速折旧）
	private String gdzcmc_js = "";

	// 提供方法说明，0：是，1：否
	private String sftgffsm_js = "";

	// 固定资产原值（加速折旧）
	private String gdzcyz_js = "";

	// 固定资产计税基础（加速折旧）
	private String gdzcjsjc_js = "";

	// 计算折旧的方法代码，0：双倍余额递减法，1：年数总额法
	private String jszjffdm_js = "";

	// 年折旧额
	private String zje_js = "";

	// 审核标记，0：通过，1：不通过
	private String shbj = "";

	// 创建人
	private String cjr = "";

	// 创建日期
	private String cjrq = "";

	// 录入人
	private String lrr = "";

	// 录入日期
	private String lrrq = "";

	// 填报表类型，0：缩短折旧年限表，1：加速折旧表
	private String tbblx = "";

	public Jmba_17VO() {
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
		xmlstr += XMLBuildUtil.appendStringElement("gdzcmc_sd", gdzcmc_sd);
		xmlstr += XMLBuildUtil.appendStringElement("sfnxdyzd_sd", sfnxdyzd_sd);
		xmlstr += XMLBuildUtil.appendStringElement("sftgczqksm_sd",
				sftgczqksm_sd);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcyz_sd", gdzcyz_sd);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcjsjc_sd", gdzcjsjc_sd);
		xmlstr += XMLBuildUtil.appendStringElement("sfgdzdnx_sd", sfgdzdnx_sd);
		xmlstr += XMLBuildUtil.appendStringElement("jszjzdnx_sd", jszjzdnx_sd);
		xmlstr += XMLBuildUtil.appendStringElement("zjqsnd_sd", zjqsnd_sd);
		xmlstr += XMLBuildUtil.appendStringElement("zjzznd_sd", zjzznd_sd);
		xmlstr += XMLBuildUtil.appendStringElement("zje_sd", zje_sd);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzjnx_sd", yjtzjnx_sd);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzje_sd", yjtzje_sd);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcmc_js", gdzcmc_js);
		xmlstr += XMLBuildUtil.appendStringElement("sftgffsm_js", sftgffsm_js);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcyz_js", gdzcyz_js);
		xmlstr += XMLBuildUtil.appendStringElement("gdzcjsjc_js", gdzcjsjc_js);
		xmlstr += XMLBuildUtil.appendStringElement("jszjffdm_js", jszjffdm_js);
		xmlstr += XMLBuildUtil.appendStringElement("zje_js", zje_js);
		xmlstr += XMLBuildUtil.appendStringElement("shbj", shbj);
		xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
		xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
		xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
		xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
		xmlstr += XMLBuildUtil.appendStringElement("tbblx", tbblx);
		xmlstr += "</qysdsjmba>";

		return xmlstr;

	}

	public String toXMLChilds() {
		return "";
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getBasqwsh() {
		return basqwsh;
	}

	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
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

	public String getGdzcjsjc_js() {
		return gdzcjsjc_js;
	}

	public void setGdzcjsjc_js(String gdzcjsjc_js) {
		this.gdzcjsjc_js = gdzcjsjc_js;
	}

	public String getGdzcjsjc_sd() {
		return gdzcjsjc_sd;
	}

	public void setGdzcjsjc_sd(String gdzcjsjc_sd) {
		this.gdzcjsjc_sd = gdzcjsjc_sd;
	}

	public String getGdzcmc_js() {
		return gdzcmc_js;
	}

	public void setGdzcmc_js(String gdzcmc_js) {
		this.gdzcmc_js = gdzcmc_js;
	}

	public String getGdzcmc_sd() {
		return gdzcmc_sd;
	}

	public void setGdzcmc_sd(String gdzcmc_sd) {
		this.gdzcmc_sd = gdzcmc_sd;
	}

	public String getGdzcyz_js() {
		return gdzcyz_js;
	}

	public void setGdzcyz_js(String gdzcyz_js) {
		this.gdzcyz_js = gdzcyz_js;
	}

	public String getGdzcyz_sd() {
		return gdzcyz_sd;
	}

	public void setGdzcyz_sd(String gdzcyz_sd) {
		this.gdzcyz_sd = gdzcyz_sd;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getJszjffdm_js() {
		return jszjffdm_js;
	}

	public void setJszjffdm_js(String jszjffdm_js) {
		this.jszjffdm_js = jszjffdm_js;
	}

	public String getJszjzdnx_sd() {
		return jszjzdnx_sd;
	}

	public void setJszjzdnx_sd(String jszjzdnx_sd) {
		this.jszjzdnx_sd = jszjzdnx_sd;
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

	public String getSfgdzdnx_sd() {
		return sfgdzdnx_sd;
	}

	public void setSfgdzdnx_sd(String sfgdzdnx_sd) {
		this.sfgdzdnx_sd = sfgdzdnx_sd;
	}

	public String getSfnxdyzd_sd() {
		return sfnxdyzd_sd;
	}

	public void setSfnxdyzd_sd(String sfnxdyzd_sd) {
		this.sfnxdyzd_sd = sfnxdyzd_sd;

	}

	public String getSftgczqksm_sd() {
		return sftgczqksm_sd;
	}

	public void setSftgczqksm_sd(String sftgczqksm_sd) {
		this.sftgczqksm_sd = sftgczqksm_sd;
	}

	public String getSftgffsm_js() {
		return sftgffsm_js;
	}

	public void setSftgffsm_js(String sftgffsm_js) {
		this.sftgffsm_js = sftgffsm_js;
	}

	public String getShbj() {
		return shbj;
	}

	public void setShbj(String shbj) {
		this.shbj = shbj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYjtzje_sd() {
		return yjtzje_sd;
	}

	public void setYjtzje_sd(String yjtzje_sd) {
		this.yjtzje_sd = yjtzje_sd;
	}

	public String getYjtzjnx_sd() {
		return yjtzjnx_sd;
	}

	public void setYjtzjnx_sd(String yjtzjnx_sd) {
		this.yjtzjnx_sd = yjtzjnx_sd;
	}

	public String getZje_js() {
		return zje_js;
	}

	public void setZje_js(String zje_js) {
		this.zje_js = zje_js;
	}

	public String getZje_sd() {
		return zje_sd;
	}

	public void setZje_sd(String zje_sd) {
		this.zje_sd = zje_sd;
	}

	public String getZjqsnd_sd() {
		return zjqsnd_sd;
	}

	public void setZjqsnd_sd(String zjqsnd_sd) {
		this.zjqsnd_sd = zjqsnd_sd;
	}

	public String getZjzznd_sd() {
		return zjzznd_sd;
	}

	public void setZjzznd_sd(String zjzznd_sd) {
		this.zjzznd_sd = zjzznd_sd;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getTbblx() {
		return tbblx;
	}

	public void setTbblx(String tbblx) {
		this.tbblx = tbblx;
	}

}
