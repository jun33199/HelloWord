package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;
/**
 *查询批量扣税情况model
 *kanght 
 * 201307
 */
public class PLKKPLCXModel implements Serializable{
	//行号
	private int rownum;
	//银行
	private String yh;
	//银行代码
	private String yhdm;
	
	//分局
	private String fj;
	//分局代码
	private String fjdm;

	//税务所
	private String sws;
	//税务所代码
	private String swsdm;
	
	//扣款时间
	private String kksj;
	//成功笔数
	private String cgbs;
	//失败笔数
	private String sbbs;
	//总笔数
	private String zbs;
	//笔数成功率
	private String bscgl;
	//成功金额
	private String cgje;
	//失败金额
	private String sbje;
	//总金额
	private String zje;
	//金额成功率
	private String jecgl;
	public String getYh() {
		return yh;
	}
	public void setYh(String yh) {
		this.yh = yh;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}
	public String getSws() {
		return sws;
	}
	public void setSws(String sws) {
		this.sws = sws;
	}
	public String getKksj() {
		return kksj;
	}
	public void setKksj(String kksj) {
		this.kksj = kksj;
	}
	public String getCgbs() {
		return cgbs;
	}
	public void setCgbs(String cgbs) {
		this.cgbs = cgbs;
	}
	public String getSbbs() {
		return sbbs;
	}
	public void setSbbs(String sbbs) {
		this.sbbs = sbbs;
	}
	public String getZbs() {
		return zbs;
	}
	public void setZbs(String zbs) {
		this.zbs = zbs;
	}
	public String getBscgl() {
		return bscgl;
	}
	public void setBscgl(String bscgl) {
		this.bscgl = bscgl;
	}
	public String getCgje() {
		return cgje;
	}
	public void setCgje(String cgje) {
		this.cgje = cgje;
	}
	public String getSbje() {
		return sbje;
	}
	public void setSbje(String sbje) {
		this.sbje = sbje;
	}
	public String getZje() {
		return zje;
	}
	public void setZje(String zje) {
		this.zje = zje;
	}
	public String getJecgl() {
		return jecgl;
	}
	public void setJecgl(String jecgl) {
		this.jecgl = jecgl;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getFjdm() {
		return fjdm;
	}
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
	public String getSwsdm() {
		return swsdm;
	}
	public void setSwsdm(String swsdm) {
		this.swsdm = swsdm;
	}
	
	
	
}

