package com.lscdz.qysds.application.qysdsjb2014.base.vo;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */


/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报页面表单基类</p>
 * @author 王志民
 * @version 1.1
 */

public class QysdsjbBaseVo{


	private static final long serialVersionUID = 1L;//序列号
	
	private String sbrq;//申报日期 String
	private String nsrmc;//纳税人名称 String
	private String sknd;//税款年度 String	
	private String qh;//期号
	private String skssksrq;//税款所属时期起 String
	private String skssjsrq;	//税款所属时期止 String
	private String djzclxdm;//登记注册类型代码 String
	private String zsfsdm;//征收方式代码 String
	private String jdlx = "";//鉴定类型
    private String ssjjlx;//所属经济类型
    private String sshy;//所属行业
    private String bbqlx;//报表期类型
	private String nsrsbh;  // 纳税人识别号
    private String jsjdm;//计算机代码
    private String reportData;//查询返回值
    private String swjgzzjgdm;//税务机关组织机构代码
    private String dzyjQmhz;   //电子签名回执信息
    private String certificate;//证书
    private String AID;//
    private String lrr;//录入人
	public String getJdlx() {
		return jdlx;
	}
	public void setJdlx(String jdlx) {
		this.jdlx = jdlx;
	}
	public String getSbrq() {
		return sbrq;
	}
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getSknd() {
		return sknd;
	}
	public void setSknd(String sknd) {
		this.sknd = sknd;
	}
	public String getSkssksrq() {
		return skssksrq;
	}
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	public String getSkssjsrq() {
		return skssjsrq;
	}
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	public String getDjzclxdm() {
		return djzclxdm;
	}
	public void setDjzclxdm(String djzclxdm) {
		this.djzclxdm = djzclxdm;
	}
	public String getZsfsdm() {
		return zsfsdm;
	}
	public void setZsfsdm(String zsfsdm) {
		this.zsfsdm = zsfsdm;
	}
	public String getSsjjlx() {
		return ssjjlx;
	}
	public void setSsjjlx(String ssjjlx) {
		this.ssjjlx = ssjjlx;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getBbqlx() {
		return bbqlx;
	}
	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}
	public String getQh() {
		return qh;
	}
	public void setQh(String qh) {
		this.qh = qh;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getReportData() {
		return reportData;
	}
	public void setReportData(String reportData) {
		this.reportData = reportData;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getDzyjQmhz() {
		return dzyjQmhz;
	}
	public void setDzyjQmhz(String dzyjQmhz) {
		this.dzyjQmhz = dzyjQmhz;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}



}

