package com.ttsoft.bjtax.shenbao.model.domain;

import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

public class Sbjkclrz implements ORObject{
	
	//JKPZH	VARCHAR2(20)	N			缴款凭证号
	String jkpzh;
	//JSJDM	VARCHAR2(8)	N			计算机代码
	String jsjdm;
	//ZRLXDM	VARCHAR2(2)	N			转入类型代码,用来标识历史数据的类型
	String zrlxdm;
	//ZRRQ	TIMESTAMP(6)	N			转入日期
	Timestamp zrrq;
	//ZRMS1	VARCHAR2(200)	Y			转入描述1
	String zrms1;
	//ZRMS2	VARCHAR2(200)	Y			转入描述2
	String zrms2;
	//ZRMS3	VARCHAR2(200)	Y			转入描述3
	String zrms3;
	//ZRMS4	VARCHAR2(200)	Y			转入描述4
	String zrms4;
	//BZ	CHAR(10)	Y			备注
	String bz;
	//ZRR	VARCHAR2(12)	Y			转入人
	String zrr;
	//CJRQ	DATE	N			创建时间
	Timestamp cjrq;
	//LRRQ	DATE	N			录入时间
	Timestamp lrrq;
	//QXDM	VARCHAR2(2)	N			区县代码
	String qxdm;
	//ND	VARCHAR2(4)	N			年度
	String nd;
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public String getJkpzh() {
		return jkpzh;
	}
	public void setJkpzh(String jkpzh) {
		this.jkpzh = jkpzh;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public String getZrlxdm() {
		return zrlxdm;
	}
	public void setZrlxdm(String zrlxdm) {
		this.zrlxdm = zrlxdm;
	}
	public String getZrms1() {
		return zrms1;
	}
	public void setZrms1(String zrms1) {
		this.zrms1 = zrms1;
	}
	public String getZrms2() {
		return zrms2;
	}
	public void setZrms2(String zrms2) {
		this.zrms2 = zrms2;
	}
	public String getZrms3() {
		return zrms3;
	}
	public void setZrms3(String zrms3) {
		this.zrms3 = zrms3;
	}
	public String getZrms4() {
		return zrms4;
	}
	public void setZrms4(String zrms4) {
		this.zrms4 = zrms4;
	}
	public String getZrr() {
		return zrr;
	}
	public void setZrr(String zrr) {
		this.zrr = zrr;
	}
	public Timestamp getZrrq() {
		return zrrq;
	}
	public void setZrrq(Timestamp zrrq) {
		this.zrrq = zrrq;
	}

}
