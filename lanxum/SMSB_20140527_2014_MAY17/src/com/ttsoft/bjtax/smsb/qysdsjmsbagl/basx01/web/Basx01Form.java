package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx01Form extends QysdsJmsbajlMainForm {
	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 
	 */
	private String ckbox;
	/*
	 * 文件名称
	 */
	private String wjmc;
	/*
	 * 文号
	 */
	private String wh;
	/*
	 * 资源综合利用种类列表
	 */
	private List zyzhlyzlList;
	/*
	 * 资源综合利用种类
	 */
	private String zyzhlyzl;
	/*
	 * 资源综合利用种类名称
	 */
	private String zyzhlyzlmc;
	/*
	 * 资源综合利用种类代码
	 */
	private String zyzhlyzldm;
	/*
	 * 证书编号
	 */
	private String zsbh;
	/*
	 * 证书有效开始日期
	 */
	private String zsyxksrq;
	/*
	 * 证书有效截止日期
	 */
	private String zsyxjzrq;
	/*
	 * 是否提交声明,0:是,1:否
	 */
	private String sftjsm = "1";
	/*
	 * 取得收入(单位:元)
	 */
	private String qdsr;
	/*
	 * 减计收入(单位:元)
	 */
	private String jjsr;
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
	public String getCkbox() {
		return ckbox;
	}
	public void setCkbox(String ckbox) {
		this.ckbox = ckbox;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getZyzhlyzl() {
		return zyzhlyzl;
	}
	public void setZyzhlyzl(String zyzhlyzl) {
		this.zyzhlyzl = zyzhlyzl;
	}
	public String getZsbh() {
		return zsbh;
	}
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	public String getZsyxksrq() {
		return zsyxksrq;
	}
	public void setZsyxksrq(String zsyxksrq) {
		this.zsyxksrq = zsyxksrq;
	}
	public String getZsyxjzrq() {
		return zsyxjzrq;
	}
	public void setZsyxjzrq(String zsyxjzrq) {
		this.zsyxjzrq = zsyxjzrq;
	}
	public String getSftjsm() {
		return sftjsm;
	}
	public void setSftjsm(String sftjsm) {
		this.sftjsm = sftjsm;
	}
	public String getQdsr() {
		return qdsr;
	}
	public void setQdsr(String qdsr) {
		this.qdsr = qdsr;
	}
	public String getJjsr() {
		return jjsr;
	}
	public void setJjsr(String jjsr) {
		this.jjsr = jjsr;
	}
	public String getCheckedStr() {
		return checkedStr;
	}
	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
	public String getZyzhlyzldm() {
		return zyzhlyzldm;
	}
	public void setZyzhlyzldm(String zyzhlyzldm) {
		this.zyzhlyzldm = zyzhlyzldm;
	}
	public String getZyzhlyzlmc() {
		return zyzhlyzlmc;
	}
	public void setZyzhlyzlmc(String zyzhlyzlmc) {
		this.zyzhlyzlmc = zyzhlyzlmc;
	}
	public List getZyzhlyzlList() {
		return zyzhlyzlList;
	}
	public void setZyzhlyzlList(List zyzhlyzlList) {
		this.zyzhlyzlList = zyzhlyzlList;
	}
	
	


}