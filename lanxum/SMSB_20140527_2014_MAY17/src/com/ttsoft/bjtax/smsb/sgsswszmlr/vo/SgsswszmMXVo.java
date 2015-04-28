package com.ttsoft.bjtax.smsb.sgsswszmlr.vo;

import java.io.Serializable;
/**
 * (新票样 - 手工录入税收完税证明) add by  tangchangfu  2013-11-24 
 * @author admin
 *
 */
public class SgsswszmMXVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String old_pzh;//原凭证号
	private String select_szdm;//税种代码
	private String select_szmc;//税种名称
	private String select_zssmdm;//选择税种
	private String select_zssmmc;//税种税目名称
	private String skssksrq;//税款所属开始日期
	private String skssjsrq;//税款所属结束日期
	private String skssrq;//税款所属日期 
	private String rk_tkrq;//入（退）库日期
	private String sjje;//实缴金额
	private String swjgzzjgdm;
	private String lrr ;
	private String cjr;
	
	
	
	
	public String getOld_pzh() {
		return old_pzh;
	}
	public void setOld_pzh(String old_pzh) {
		this.old_pzh = old_pzh;
	}
	public String getSelect_zssmdm() {
		String[] szsmArr = this.select_zssmdm.split("-");
		return szsmArr[0];
	}
	public void setSelect_zssmdm(String select_zssmdm) {
		this.select_zssmdm = select_zssmdm;
	}
	public String getSelect_zssmmc() {
		return select_zssmmc;
	}
	public void setSelect_zssmmc(String select_zssmmc) {
		this.select_zssmmc = select_zssmmc;
	}
	public String getSkssksrq() {
		return skssksrq;
	}
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	public String getSkssjsrq() {
		return skssjsrq;
	}
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	public String getRk_tkrq() {
		return rk_tkrq;
	}
	public void setRk_tkrq(String rk_tkrq) {
		this.rk_tkrq = rk_tkrq;
	}
	public String getSjje() {
		return sjje;
	}
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSelect_szdm() {
		return this.getSelect_zssmdm().substring(0, 2);
	}
	public void setSelect_szdm(String select_szdm) {
		this.select_szdm = select_szdm;
	}
	public String getSelect_szmc() {
		return select_szmc;
	}
	public void setSelect_szmc(String select_szmc) {
		this.select_szmc = select_szmc;
	}
	public String getSkssrq() {
		return skssrq;
	}
	public void setSkssrq(String skssrq) {
		this.skssrq = skssrq;
	}
	
	
	
	
}
