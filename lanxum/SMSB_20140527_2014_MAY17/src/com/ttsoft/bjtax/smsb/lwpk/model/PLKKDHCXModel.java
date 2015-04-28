package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *单户查询批量扣款model
 *kanght 
 * 201307
 */
public class PLKKDHCXModel implements Serializable {

	//税票号码
	private String sphm;
	//税种名称
	private String szmc;
	//实缴金额
	private BigDecimal sjje;
	//扣款时间
	private Date kksj;
	//扣款状态
	private String kkzt;
	//扣款失败原因
	private String kksbyy;
	
	
	
	public String getSphm() {
		return sphm;
	}
	public void setSphm(String sphm) {
		this.sphm = sphm;
	}
	public String getSzmc() {
		return szmc;
	}
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	public BigDecimal getSjje() {
		return sjje;
	}
	public void setSjje(BigDecimal sjje) {
		this.sjje = sjje;
	}
	public Date getKksj() {
		return kksj;
	}
	public void setKksj(Date kksj) {
		this.kksj = kksj;
	}
	public String getKkzt() {
		return kkzt;
	}
	public void setKkzt(String kkzt) {
		this.kkzt = kkzt;
	}
	public String getKksbyy() {
		return kksbyy;
	}
	public void setKksbyy(String kksbyy) {
		this.kksbyy = kksbyy;
	}
	
}
