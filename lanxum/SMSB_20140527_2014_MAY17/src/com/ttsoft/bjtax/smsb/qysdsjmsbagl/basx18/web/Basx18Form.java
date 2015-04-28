package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx18.web;



import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;
/**
 * @ author  王智江
 * @ date    2010-2-2
 **/
public class Basx18Form extends QysdsJmsbajlMainForm {
	
	//序号
	private String xh;
	//证书编号
	private String zsbh;
	//技术先进型服务业务范围列表
	private List fwywfwList;
	//技术先进型服务业务范围
	private String fwywfw;
	//技术先进型服务业务范围代码
	private String fwywfwdm;
	//技术先进型服务业务范围名称；
	private String fwywfwmc;
	//证书有效期起
	private String zsqsrq;
	//证书有效期止
	private String zszzrq;
	//本年预计的减免税额
	private String bnyjjmse;
	//处理标识
	private String clbs="0";
	
	
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZsbh() {
		return zsbh;
	}
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	public String getFwywfw() {
		return fwywfw;
	}
	public void setFwywfw(String fwywfw) {
		this.fwywfw = fwywfw;
	}
	public String getFwywfwdm() {
		return fwywfwdm;
	}
	public void setFwywfwdm(String fwywfwdm) {
		this.fwywfwdm = fwywfwdm;
	}
	public String getFwywfwmc() {
		return fwywfwmc;
	}
	public void setFwywfwmc(String fwywfwmc) {
		this.fwywfwmc = fwywfwmc;
	}
	public String getZsqsrq() {
		return zsqsrq;
	}
	public void setZsqsrq(String zsqsrq) {
		this.zsqsrq = zsqsrq;
	}
	public String getZszzrq() {
		return zszzrq;
	}
	public void setZszzrq(String zszzrq) {
		this.zszzrq = zszzrq;
	}
	
	public String getBnyjjmse() {
		return bnyjjmse;
	}
	public void setBnyjjmse(String bnyjjmse) {
		this.bnyjjmse = bnyjjmse;
	}
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	public List getFwywfwList() {
		return fwywfwList;
	}
	public void setFwywfwList(List fwywfwList) {
		this.fwywfwList = fwywfwList;
	}
	
	
	
}