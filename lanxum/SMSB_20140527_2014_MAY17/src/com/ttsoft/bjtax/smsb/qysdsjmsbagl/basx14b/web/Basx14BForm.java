package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx14BForm extends QysdsJmsbajlMainForm {
	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 处理标示0:未保存；1：保存过；
	 */
	private String clbs = "0";
	/*
	 * 往年备案申请编号
	 */
	private String wnbasqbh;
	/*
	 * 专用设备类型数据列表
	 */
	private List zysblxList;
	/*
	 * 专用设备类型
	 */
	private String zysblx;
	/*
	 * 专用设备类型代码
	 */
	private String zysblxdm;
	/*
	 * 专用设备类型名称
	 */
	private String zysblxmc;
	/*
	 * 专用设备名称
	 */
	private String zysbmc;
	/*
	 * 购置年度
	 */
	private String gznd;
	/*
	 * 当年购置设备投资额
	 */
	private String tze;
	/*
	 * 当年可抵扣应纳税所得额
	 */
	private String kdke;
	/*
	 * 当年实际抵扣应纳税所得额
	 */
	private String sjdke;
	/*
	 * 结转以后年度抵扣应纳税所得额
	 */
	private String jzdke;
	/*
	 * 控制该投资年度记录是否可以编辑
	 */
	private String bjkz;
	/*
	 * 查询投资信息结果 0:有结果；1：无结果
	 */
	private String tzxxjg = "1";
	private String searchAction;
	private String zlState;
	public String getZlState() {
		return zlState;
	}
	public void setZlState(String zlState) {
		this.zlState = zlState;
	}
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZysbmc() {
		return zysbmc;
	}
	public String getBjkz() {
		return bjkz;
	}
	public void setBjkz(String bjkz) {
		this.bjkz = bjkz;
	}
	public String getJzdke() {
		return jzdke;
	}
	public void setJzdke(String jzdke) {
		this.jzdke = jzdke;
	}
	public String getKdke() {
		return kdke;
	}
	public void setKdke(String kdke) {
		this.kdke = kdke;
	}
	public String getSjdke() {
		return sjdke;
	}
	public void setSjdke(String sjdke) {
		this.sjdke = sjdke;
	}
	public String getTze() {
		return tze;
	}
	public void setTze(String tze) {
		this.tze = tze;
	}
	public String getTzxxjg() {
		return tzxxjg;
	}
	public void setTzxxjg(String tzxxjg) {
		this.tzxxjg = tzxxjg;
	}
	public void setZysbmc(String zysbmc) {
		this.zysbmc = zysbmc;
	}
	public String getZysblx() {
		return zysblx;
	}
	public void setZysblx(String zysblx) {
		this.zysblx = zysblx;
	}
	public String getZysblxdm() {
		return zysblxdm;
	}
	public void setZysblxdm(String zysblxdm) {
		this.zysblxdm = zysblxdm;
	}
	public List getZysblxList() {
		return zysblxList;
	}
	public void setZysblxList(List zysblxList) {
		this.zysblxList = zysblxList;
	}
	public String getZysblxmc() {
		return zysblxmc;
	}
	public void setZysblxmc(String zysblxmc) {
		this.zysblxmc = zysblxmc;
	}
	public String getWnbasqbh() {
		return wnbasqbh;
	}
	public void setWnbasqbh(String wnbasqbh) {
		this.wnbasqbh = wnbasqbh;
	}
	public String getGznd() {
		return gznd;
	}
	public void setGznd(String gznd) {
		this.gznd = gznd;
	}
	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
}