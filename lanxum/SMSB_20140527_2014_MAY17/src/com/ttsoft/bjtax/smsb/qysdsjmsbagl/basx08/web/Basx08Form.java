package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx08.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx08Form extends QysdsJmsbajlMainForm {
	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 高新技术领域列表
	 */
	private List gxjslyList;
	/*
	 * 高新技术领域
	 */
	private String gxjs;
	/*
	 * 高新技术领域代码
	 */
	private String gxjslydm;
	/*
	 * 高新技术领域名称
	 */
	private String gxjslymc;
	/*
	 * 证书编号
	 */
	private String zsbh;
	/*
	 * 证书有效期起
	 */
	private String zsyxqq;
	/*
	 * 证书有效期止
	 */
	private String zsyxqz;
	/*
	 * 企业具有大学专科以上学历的科技人员占企业当年职工总数的比例
	 */
	private String zkysbl ;
	/*
	 * 研发人员占企业当年职工总数的比例
	 */
	private String yfrybl;
	
	/*
	 * 企业近三个会计年度的研究开发费用总额占销售收入总额的比例
	 */
	private String yffybl ;
	/*
	 * 企业当年高新技术产品（服务）收入占企业总收入的比例
	 */
	private String  gxcpsrbl ;
	
	/*
	 * 处理标示
	 */
	private String clbs = "0";
	/*
	 * 多条数据列表
	 */
	private List resultList;
	/*
	 * 
	 */
	private String checkedStr;
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getCheckedStr() {
		return checkedStr;
	}
	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
	public String getGxcpsrbl() {
		return gxcpsrbl;
	}
	public void setGxcpsrbl(String gxcpsrbl) {
		this.gxcpsrbl = gxcpsrbl;
	}
	public String getGxjs() {
		return gxjs;
	}
	public void setGxjs(String gxjs) {
		this.gxjs = gxjs;
	}
	public String getGxjslydm() {
		return gxjslydm;
	}
	public void setGxjslydm(String gxjslydm) {
		this.gxjslydm = gxjslydm;
	}
	public String getGxjslymc() {
		return gxjslymc;
	}
	public void setGxjslymc(String gxjslymc) {
		this.gxjslymc = gxjslymc;
	}
	public String getYffybl() {
		return yffybl;
	}
	public void setYffybl(String yffybl) {
		this.yffybl = yffybl;
	}
	public String getYfrybl() {
		return yfrybl;
	}
	public void setYfrybl(String yfrybl) {
		this.yfrybl = yfrybl;
	}
	public String getZkysbl() {
		return zkysbl;
	}
	public void setZkysbl(String zkysbl) {
		this.zkysbl = zkysbl;
	}
	public String getZsbh() {
		return zsbh;
	}
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	public String getZsyxqq() {
		return zsyxqq;
	}
	public void setZsyxqq(String zsyxqq) {
		this.zsyxqq = zsyxqq;
	}
	public String getZsyxqz() {
		return zsyxqz;
	}
	public void setZsyxqz(String zsyxqz) {
		this.zsyxqz = zsyxqz;
	}
	public List getGxjslyList() {
		return gxjslyList;
	}
	public void setGxjslyList(List gxjslyList) {
		this.gxjslyList = gxjslyList;
	}
	
	


}