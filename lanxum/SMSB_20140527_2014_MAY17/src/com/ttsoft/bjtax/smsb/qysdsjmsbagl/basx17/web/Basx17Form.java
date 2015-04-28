package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx17.web;

import java.util.ArrayList;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx17Form extends QysdsJmsbajlMainForm {
	
	// 序号
	private String xh;
	//温室气体减排量转让收入金额
	private String zrsrje;
	//上缴给国家的HFC和PFC类CDM项目的金额
	private String sjje1;
	//上缴给国家的N2O类CDM项目的金额
	private String sjje2;
	
	//取得第一笔减排量转让收入所属纳税年度 
	private String hlnd;
	//主管税务机关要求报送的其他资料
	private String qtzl;
	//免征起始年度
	private String mzqsnd;
	//免征终止年度
	private String mzzznd;
	//减征起始年度
	private String jzqsnd;
	//减征终止年度
	private String jzzznd;
	//处理标识
	private String clbs="0";
	//能否从数据库中查到值的标识
	private String flag;
	//备案年度别称
	private String band1;
	
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
	public String getZrsrje() {
		return zrsrje;
	}
	public void setZrsrje(String zrsrje) {
		this.zrsrje = zrsrje;
	}
	public String getSjje1() {
		return sjje1;
	}
	public void setSjje1(String sjje1) {
		this.sjje1 = sjje1;
	}
	public String getSjje2() {
		return sjje2;
	}
	public void setSjje2(String sjje2) {
		this.sjje2 = sjje2;
	}
	
	public String getHlnd() {
		return hlnd;
	}
	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}
	public String getQtzl() {
		return qtzl;
	}
	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}
	public String getMzqsnd() {
		return mzqsnd;
	}
	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}
	public String getMzzznd() {
		return mzzznd;
	}
	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}
	public String getJzqsnd() {
		return jzqsnd;
	}
	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}
	public String getJzzznd() {
		return jzzznd;
	}
	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getBand1() {
		return band1;
	}
	public void setBand1(String band1) {
		this.band1 = band1;
	}
	
}