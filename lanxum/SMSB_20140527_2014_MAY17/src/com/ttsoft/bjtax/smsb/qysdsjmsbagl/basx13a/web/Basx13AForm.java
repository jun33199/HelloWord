package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13a.web;


import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx13AForm extends QysdsJmsbajlMainForm {
	//序号
	private String xh;
	//中小高新技术企业领域列表
	private List gxjsqylyList;
	//中小高新技术企业领域
	private String gxjsqyly;
	//中小高新技术企业领域代码
	private String gxjsqylydm;
	//中小高新技术企业领域名称
	private String gxjsqylymc;
	//经有权部门批准认定的创业投资企业证书或文件及其编号
	private String  ctqyzsjbh;
	//被投资的中小高新技术企业的高新技术企业名称及证书编号
	private String btzqymcjzsbh;
	//处理标识
	private String clbs = "0";
	
	
	
	
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getGxjsqyly() {
		return gxjsqyly;
	}
	public void setGxjsqyly(String gxjsqyly) {
		this.gxjsqyly = gxjsqyly;
	}
	public String getCtqyzsjbh() {
		return ctqyzsjbh;
	}
	public void setCtqyzsjbh(String ctqyzsjbh) {
		this.ctqyzsjbh = ctqyzsjbh;
	}
	public String getBtzqymcjzsbh() {
		return btzqymcjzsbh;
	}
	public void setBtzqymcjzsbh(String btzqymcjzsbh) {
		this.btzqymcjzsbh = btzqymcjzsbh;
	}
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}

	public String getGxjsqylydm() {
		return gxjsqylydm;
	}
	public void setGxjsqylydm(String gxjsqylydm) {
		this.gxjsqylydm = gxjsqylydm;
	}
	public String getGxjsqylymc() {
		return gxjsqylymc;
	}
	public void setGxjsqylymc(String gxjsqylymc) {
		this.gxjsqylymc = gxjsqylymc;
	}
	public List getGxjsqylyList() {
		return gxjsqylyList;
	}
	public void setGxjsqylyList(List gxjsqylyList) {
		this.gxjsqylyList = gxjsqylyList;
	}
	


}