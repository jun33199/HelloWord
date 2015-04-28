package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description: （十八）外购软件缩短折旧或摊销年限 VO</p>
 *
 * <p>Copyright: 四一安信</p>
 *
 * <p>Company: 四一安信</p>
 *
 * @author 米军
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba_18VO implements JmbamxVoInterface {

	// 序号
	private String xh = "";

	// 备案申请文书号
	private String basqwsh = "";

	// 计算机代码
	private String jsjdm = "";

	// 备案年度
	private String band = "";
//	 税务机关组织机关代码
	private String swjgzzjgdm = "";

	// 企业购进软件的凭证名称
	private String gjrjpzmc = "";

	// 符合确认条件，0：是，1：否
	private String sffhqrtj = "";

	// 有缩短年限理由，0：是，1：否
	private String sfysdnxly = "";

	// 有缩短年限证明，0：是，1：否
	private String sfysdnxzm = "";

	// 有相关情况的说明，0：是，1：否
	private String sfyxgqksm = "";

	// 外购软件缩短折旧或摊销的年限
	private String sdtxnx = "";

	// 计提折旧起始年度
	private String jtzjqsnd = "";

	// 计提折旧终止年度
	private String jtzjzznd = "";

	// 每年可扣除的折旧额
	private String kkczje = "";

	// 固定资产(无形资产)原值
	private String zcyz = "";

	// 已计提折旧的年限
	private String yjtzjnx = "";

	// 已计提的折旧额
	private String yjtzje = "";

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

	public Jmba_18VO(){
		
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("basqwsh",basqwsh);
		xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
		xmlstr += XMLBuildUtil.appendStringElement("band", band);
		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("gjrjpzmc",gjrjpzmc);
		xmlstr += XMLBuildUtil.appendStringElement("sffhqrtj",sffhqrtj);
		xmlstr += XMLBuildUtil.appendStringElement("sfysdnxly",sfysdnxly);
		xmlstr += XMLBuildUtil.appendStringElement("sfysdnxzm",sfysdnxzm);
		xmlstr += XMLBuildUtil.appendStringElement("sfyxgqksm",sfyxgqksm);
		xmlstr += XMLBuildUtil.appendStringElement("sdtxnx",sdtxnx);
		xmlstr += XMLBuildUtil.appendStringElement("jtzjqsnd",jtzjqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jtzjzznd",jtzjzznd);
		xmlstr += XMLBuildUtil.appendStringElement("kkczje",kkczje);
		xmlstr += XMLBuildUtil.appendStringElement("zcyz",zcyz);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzjnx",yjtzjnx);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzje",yjtzje);
		xmlstr += XMLBuildUtil.appendStringElement("shbj",shbj);
		xmlstr += XMLBuildUtil.appendStringElement("cjr",cjr);
		xmlstr += XMLBuildUtil.appendStringElement("cjrq",cjrq);
		xmlstr += XMLBuildUtil.appendStringElement("lrr",lrr);
		xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);
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

	public String getGjrjpzmc() {
		return gjrjpzmc;
	}

	public void setGjrjpzmc(String gjrjpzmc) {
		this.gjrjpzmc = gjrjpzmc;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getJtzjqsnd() {
		return jtzjqsnd;
	}

	public void setJtzjqsnd(String jtzjqsnd) {
		this.jtzjqsnd = jtzjqsnd;
	}

	public String getJtzjzznd() {
		return jtzjzznd;
	}

	public void setJtzjzznd(String jtzjzznd) {
		this.jtzjzznd = jtzjzznd;
	}

	public String getKkczje() {
		return kkczje;
	}

	public void setKkczje(String kkczje) {
		this.kkczje = kkczje;
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

	public String getSdtxnx() {
		return sdtxnx;
	}

	public void setSdtxnx(String sdtxnx) {
		this.sdtxnx = sdtxnx;
	}

	public String getSffhqrtj() {
		return sffhqrtj;
	}

	public void setSffhqrtj(String sffhqrtj) {
		this.sffhqrtj = sffhqrtj;
	}

	public String getSfysdnxly() {
		return sfysdnxly;
	}

	public void setSfysdnxly(String sfysdnxly) {
		this.sfysdnxly = sfysdnxly;
	}

	public String getSfysdnxzm() {
		return sfysdnxzm;
	}

	public void setSfysdnxzm(String sfysdnxzm) {
		this.sfysdnxzm = sfysdnxzm;
	}

	public String getSfyxgqksm() {
		return sfyxgqksm;
	}

	public void setSfyxgqksm(String sfyxgqksm) {
		this.sfyxgqksm = sfyxgqksm;
	}

	public String getShbj() {
		return shbj;
	}

	public void setShbj(String shbj) {
		this.shbj = shbj;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYjtzje() {
		return yjtzje;
	}

	public void setYjtzje(String yjtzje) {
		this.yjtzje = yjtzje;
	}

	public String getYjtzjnx() {
		return yjtzjnx;
	}

	public void setYjtzjnx(String yjtzjnx) {
		this.yjtzjnx = yjtzjnx;
	}

	public String getZcyz() {
		return zcyz;
	}

	public void setZcyz(String zcyz) {
		this.zcyz = zcyz;
	}



	
}
