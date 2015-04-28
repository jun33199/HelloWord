package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx13BForm extends QysdsJmsbajlMainForm {
	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 处理标示0:未保存；1：保存过；
	 */
	private String clbs = "0";
	/*
	 * 被投资企业所属地，0：本市，1：外省市
	 */
	private String btzqyssd = "0";
	/*
	 * 被投资企业计算机代码
	 */
	private String btzqyjsjdm;
	/*
	 * 被投资企业税务登记证号
	 */
	private String btzqyswdjzh;
	/*
	 * 被投资企业名称
	 */
	private String btzqymc;
	/*
	 * 高新技术领域数据列表
	 */
	private List gxjslyList;
	/*
	 * 高新技术领域
	 */
	private String gxjsly;
	/*
	 * 高新技术领域代码
	 */
	private String gxjslydm;
	/*
	 * 高新技术领域名称
	 */
	private String gxjslymc;
	/*
	 * 当年新增投资额
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
	 * 控制是否查询纳税人名称 0:查询；1：不查询
	 */
	private String nsrmckz = "0";
	/*
	 * 查询投资信息结果 0:有结果；1：无结果
	 */
	

	
	private String searchAction;
	private String tzxxjg = "1";
	
	
	private String zlState;
	public String getTzxxjg() {
		return tzxxjg;
	}
	public void setTzxxjg(String tzxxjg) {
		this.tzxxjg = tzxxjg;
	}
	public String getBtzqyjsjdm() {
		return btzqyjsjdm;
	}
	public void setBtzqyjsjdm(String btzqyjsjdm) {
		this.btzqyjsjdm = btzqyjsjdm;
	}
	public String getBtzqymc() {
		return btzqymc;
	}
	public void setBtzqymc(String btzqymc) {
		this.btzqymc = btzqymc;
	}
	public String getBtzqyssd() {
		return btzqyssd;
	}
	public void setBtzqyssd(String btzqyssd) {
		this.btzqyssd = btzqyssd;
	}
	public String getBtzqyswdjzh() {
		return btzqyswdjzh;
	}
	public void setBtzqyswdjzh(String btzqyswdjzh) {
		this.btzqyswdjzh = btzqyswdjzh;
	}
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getGxjsly() {
		return gxjsly;
	}
	public void setGxjsly(String gxjsly) {
		this.gxjsly = gxjsly;
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
	public List getGxjslyList() {
		return gxjslyList;
	}
	public void setGxjslyList(List gxjslyList) {
		this.gxjslyList = gxjslyList;
	}
	public String getTze() {
		return tze;
	}
	public void setTze(String tze) {
		this.tze = tze;
	}
	public String getBjkz() {
		return bjkz;
	}
	public void setBjkz(String bjkz) {
		this.bjkz = bjkz;
	}
	public String getNsrmckz() {
		return nsrmckz;
	}
	public void setNsrmckz(String nsrmckz) {
		this.nsrmckz = nsrmckz;
	}
	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
	public String getZlState() {
		return zlState;
	}
	public void setZlState(String zlState) {
		this.zlState = zlState;
	}
}