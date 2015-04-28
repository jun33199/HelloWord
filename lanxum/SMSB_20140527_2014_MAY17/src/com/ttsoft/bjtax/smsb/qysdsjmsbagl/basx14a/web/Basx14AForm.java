package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14a.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx14AForm extends QysdsJmsbajlMainForm {
	/*
	 * 序号
	 */
	private String xh;
	
	/*
	 * 处理标示0:未保存；1：保存过；2：再次备案
	 */
	private String clbs = "0";
	
	/*
	 * 企业购置并实际使用的专用设备类型列表
	 */
	private List zysblxList = new ArrayList();
	/*
	 * 企业购置并实际使用的专用设备类型
	 */
	private String zysblx;
	/*
	 * 企业购置并实际使用的专用设备类型代码
	 */
	private String zysblxdm;
	/*
	 * 企业购置并实际使用的专用设备类型名称
	 */
	private String zysblxmc;
	/*
	 * 企业购置并实际使用的专用设备名称
	 */
	private String zysbmc;
	/*
	 * 购置年度
	 */
	private String gznd;
	
	/*
	 * 购置专用设备的投资额
	 */
	private String tze;
	/*
	 * 购置专用设备投资额可抵免的应纳税额
	 */
	private String dmynse;
	
	
	
	
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
	
	public String getDmynse() {
		return dmynse;
	}
	public void setDmynse(String dmynse) {
		this.dmynse = dmynse;
	}
	public String getGznd() {
		return gznd;
	}
	public void setGznd(String gznd) {
		this.gznd = gznd;
	}
	
	public String getTze() {
		return tze;
	}
	public void setTze(String tze) {
		this.tze = tze;
	}
	public String getZysblxdm() {
		return zysblxdm;
	}
	public void setZysblxdm(String zysblxdm) {
		this.zysblxdm = zysblxdm;
	}
	public String getZysbmc() {
		return zysbmc;
	}
	public void setZysbmc(String zysbmc) {
		this.zysbmc = zysbmc;
	}
	public String getZysblxmc() {
		return zysblxmc;
	}
	public void setZysblxmc(String zysblxmc) {
		this.zysblxmc = zysblxmc;
	}
	public String getZysblx() {
		return zysblx;
	}
	public void setZysblx(String zysblx) {
		this.zysblx = zysblx;
	}
	public List getZysblxList() {
		return zysblxList;
	}
	public void setZysblxList(List zysblxList) {
		this.zysblxList = zysblxList;
	}
	
	
	


}