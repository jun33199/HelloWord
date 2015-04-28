package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web;

import com.ttsoft.framework.form.BaseForm;


/**
 * <p>
 * Title: 企业所得税减免备案-公用From
 * </p>
 * <p>
 * Description: 企业所得税减免备案-公用From
 * </p>
 * 
 * @author 开发二部 - 刘超
 * @version 1.0
 */
public class QysdsJmsbajlPrintForm extends BaseForm{
	// 备案申请文书号
	private String basqwsh;
	// 备案申请编号
	private String basqbh;
	// 纳税人名称
	private String nsrmc;
	// 减免备案事项名称
	private String jmbasxdm;
	// 减免备案事项名称
	private String jmbasxmc;
	// 税务机关组织机构名称
	private String swjgzzjgmc;
	// 审核时间_年
	private String shsj_y;
	// 审核时间_月
	private String shsj_m;
	// 审核时间_月
	private String shsj_d;
	// 申请状态
	private String sqzt;
	// 操作类型
	private String czlx;
	
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public String getBasqbh() {
		return basqbh;
	}
	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}
	public String getJmbasxmc() {
		return jmbasxmc;
	}
	public void setJmbasxmc(String jmbasxmc) {
		this.jmbasxmc = jmbasxmc;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}
	public String getBasqwsh() {
		return basqwsh;
	}
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	public String getShsj_d() {
		return shsj_d;
	}
	public void setShsj_d(String shsj_d) {
		this.shsj_d = shsj_d;
	}
	public String getShsj_m() {
		return shsj_m;
	}
	public void setShsj_m(String shsj_m) {
		this.shsj_m = shsj_m;
	}
	public String getShsj_y() {
		return shsj_y;
	}
	public void setShsj_y(String shsj_y) {
		this.shsj_y = shsj_y;
	}
	public String getCzlx() {
		return czlx;
	}
	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	
}
