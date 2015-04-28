package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.bo;

import java.io.Serializable;

public class NsrJbxxBo implements Serializable {
	/**
	 * 纳税人计算机代码 String
	 */
	private String jsjdm = "";

	/**
	 * 纳税人识别号－税务登记证号 String
	 */
	private String nsrsbh = "";

	/**
	 * 纳税人名称 String
	 */
	private String nsrmc = "";

	/**
	 * 所属经济类型-登记注册类型代码 String
	 */
	private String ssjjlxdm = "";

	/**
	 * 所属经济类型-登记注册类型名称 String
	 */
	private String ssjjlxmc = "";

	/**
	 * 管理人或清算组联络人员
	 */
	private String qsllry = "";
	/**
	 * 联系电话 String
	 */
	private String lxdh = "";

	/**
	 * 经营地址 String
	 */
	private String jydz = "";

	/**
	 * 所属行业代码
	 */
	private String sshydm = "";

	/**
	 * 所属行业名称
	 */
	private String sshymc = "";

	/**
	 * 税务机关组织机构代码 String
	 * 
	 * 从登记数据中取得
	 */
	private String swjgzzjgdm = "";

	/**
	 * 税务机关组织机构名称 String
	 */
	private String swjgzzjgmc = "";

	/**
	 * 创建人代码 String
	 * 
	 * 从登录数据中取得
	 */
	private String cjr = "";

	/**
	 * 创建时间 String
	 */
	private String cjrq = "";

	/**
	 * 录入人代码 String
	 * 
	 * 从登录数据中取得
	 */
	private String lrr = "";

	/**
	 * 录入日期 String
	 */

	private String lrrq = "";

	/**
	 * 申报日期 String
	 */

	private String tbrq = "";

	/**
	 * 系统级别 String
	 */

	private String xtjb = "";

	/**
	 * 报表描述符 String
	 */

	private String bbmsf = "";

	/**
	 * 清算备案开始日期 String
	 */
	private String qsbaksrq = "";

	/**
	 * 清算备案结束日期 String 等于备案审核日期
	 */
	private String qsbajsrq = "";

	/**
	 * 清算申报开始日期 String
	 */
	private String qssbksrq = "";

	/**
	 * 清算申报所属结束日期 String
	 */
	private String qssbjsrq = "";
	/**
	 * 版本号 String
	 */

	private String version = "";
	/**
	 * 申请类型代码
	 * */
	private String sqlxdm = "0";

	/**
	 * 申请审批表号 String
	 */

	private String sqspbh = "";

	// 企业章程规定的经营期限届满
	private String jyqxjm = "";
	// 企业股东会、股东大会或类似机构决议解散
	private String gdjyjs = "";
	// 企业依法被吊销营业执照、责令关闭或者被撤销
	private String yfdxgb = "";
	// 企业被人民法院依法予以解散或宣告破产
	private String yfxgpc = "";
	// 有关法律、行政法规规定清算
	private String yfgdqs = "";
	// 企业因其他原因解散或进行清算
	private String qtyy = "";

	// 备案审核状态标识
	private String baShztbs = "";
	// 备案审核状态描述
	private String baShztMessage = "";
	// 备案审核通过日期
	private String baShtgrq = "";

	// 申报审核状态标识 填写清算申报数据
	private String sbShztbs = "";
	// 备案审核状态描述
	private String sbShztMessage = "";
	// 备案审核通过日期
	private String sbShtgrq = "";
	
	

	public String getSqlxdm() {
		return sqlxdm;
	}

	public void setSqlxdm(String sqlxdm) {
		this.sqlxdm = sqlxdm;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getSsjjlxdm() {
		return ssjjlxdm;
	}

	public void setSsjjlxdm(String ssjjlxdm) {
		this.ssjjlxdm = ssjjlxdm;
	}

	public String getSsjjlxmc() {
		return ssjjlxmc;
	}

	public void setSsjjlxmc(String ssjjlxmc) {
		this.ssjjlxmc = ssjjlxmc;
	}

	public String getQsllry() {
		return qsllry;
	}

	public void setQsllry(String qsllry) {
		this.qsllry = qsllry;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getJydz() {
		return jydz;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public String getSshydm() {
		return sshydm;
	}

	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}

	public String getSshymc() {
		return sshymc;
	}

	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
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

	public String getTbrq() {
		return tbrq;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public String getXtjb() {
		return xtjb;
	}

	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}

	public String getBbmsf() {
		return bbmsf;
	}

	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}

	public String getQsbajsrq() {
		return qsbajsrq;
	}

	public void setQsbajsrq(String qsbajsrq) {
		this.qsbajsrq = qsbajsrq;
	}

	public String getQssbksrq() {
		return qssbksrq;
	}

	public void setQssbksrq(String qssbksrq) {
		this.qssbksrq = qssbksrq;
	}

	public String getQssbjsrq() {
		return qssbjsrq;
	}

	public void setQssbjsrq(String qssbjsrq) {
		this.qssbjsrq = qssbjsrq;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSqspbh() {
		return sqspbh;
	}

	public void setSqspbh(String sqspbh) {
		this.sqspbh = sqspbh;
	}

	public String getJyqxjm() {
		return jyqxjm;
	}

	public void setJyqxjm(String jyqxjm) {
		this.jyqxjm = jyqxjm;
	}

	public String getGdjyjs() {
		return gdjyjs;
	}

	public void setGdjyjs(String gdjyjs) {
		this.gdjyjs = gdjyjs;
	}

	public String getYfdxgb() {
		return yfdxgb;
	}

	public void setYfdxgb(String yfdxgb) {
		this.yfdxgb = yfdxgb;
	}

	public String getYfxgpc() {
		return yfxgpc;
	}

	public void setYfxgpc(String yfxgpc) {
		this.yfxgpc = yfxgpc;
	}

	public String getYfgdqs() {
		return yfgdqs;
	}

	public void setYfgdqs(String yfgdqs) {
		this.yfgdqs = yfgdqs;
	}

	public String getQtyy() {
		return qtyy;
	}

	public void setQtyy(String qtyy) {
		this.qtyy = qtyy;
	}

	public String getQsbaksrq() {
		return qsbaksrq;
	}

	public void setQsbaksrq(String qsbaksrq) {
		this.qsbaksrq = qsbaksrq;
	}

	public String getBaShztbs() {
		return baShztbs;
	}

	public void setBaShztbs(String baShztbs) {
		this.baShztbs = baShztbs;
	}

	public String getBaShztMessage() {
		return baShztMessage;
	}

	public void setBaShztMessage(String baShztMessage) {
		this.baShztMessage = baShztMessage;
	}

	public String getBaShtgrq() {
		return baShtgrq;
	}

	public void setBaShtgrq(String baShtgrq) {
		this.baShtgrq = baShtgrq;
	}

	public String getSbShztbs() {
		return sbShztbs;
	}

	public void setSbShztbs(String sbShztbs) {
		this.sbShztbs = sbShztbs;
	}

	public String getSbShztMessage() {
		return sbShztMessage;
	}

	public void setSbShztMessage(String sbShztMessage) {
		this.sbShztMessage = sbShztMessage;
	}

	public String getSbShtgrq() {
		return sbShtgrq;
	}

	public void setSbShtgrq(String sbShtgrq) {
		this.sbShtgrq = sbShtgrq;
	}

	

}
