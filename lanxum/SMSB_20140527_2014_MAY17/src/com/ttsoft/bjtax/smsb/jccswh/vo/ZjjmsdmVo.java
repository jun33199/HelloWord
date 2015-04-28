package com.ttsoft.bjtax.smsb.jccswh.vo;

import java.io.Serializable;
import java.util.Date;

public class ZjjmsdmVo implements Serializable {
	private int indexId;
	/**
	 * 减免税类型代码
	 */
	private String jmslxdm;
	/**
	 * 文号
	 */
	private String wh;
	/**
	 * 减免税类型名称
	 */
	private String jmslxmc;
	/**
	 * 税种
	 */
	private String sz;
	/**
	 * 注销标识
	 */
	private String zxbz;	
	/**
	 * 录入人
	 */
	private String lrr;	
	/**
	 * 录入日期
	 */
	private Date lrrq;
	/**
	 * 减免税政策起始年度
	 */
	private String jmszcqsnd;
	/**
	 * 税种代码
	 */
	private String szdm;
	
	/**
	 * 减免税类型大类代码
	 */
	private String jmslxdldm;
	private String jmslxdldmmc;//大类名称
	/**
	 * 减免税类型小类代码
	 */
	private String jmslxxldm;
	private String jmslxxldmmc;//小类名称
	/**
	 * 有效标识（0-有效；9-无效）
	 */
	private String yxbs;
	/**
	 * 备注
	 */
	private String bz;
	/**
	 * 历史编号（初始值为0，每次变更自动加一）
	 */
	private String lsbh;
	
	//用于修改重现录入信息
	private String reShowStr="";
	
	
	
	
	
	
	public String getJmslxdm() {
		return jmslxdm;
	}
	public void setJmslxdm(String jmslxdm) {
		this.jmslxdm = jmslxdm;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getJmslxmc() {
		return jmslxmc;
	}
	public void setJmslxmc(String jmslxmc) {
		this.jmslxmc = jmslxmc;
	}
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
	public String getZxbz() {
		return zxbz;
	}
	public void setZxbz(String zxbz) {
		this.zxbz = zxbz;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Date getLrrq() {
		return lrrq;
	}
	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	public String getJmszcqsnd() {
		return jmszcqsnd;
	}
	public void setJmszcqsnd(String jmszcqsnd) {
		this.jmszcqsnd = jmszcqsnd;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	public String getJmslxdldm() {
		return jmslxdldm;
	}
	public void setJmslxdldm(String jmslxdldm) {
		this.jmslxdldm = jmslxdldm;
	}
	public String getJmslxxldm() {
		return jmslxxldm;
	}
	public void setJmslxxldm(String jmslxxldm) {
		this.jmslxxldm = jmslxxldm;
	}
	public String getYxbs() {
		return yxbs;
	}
	public void setYxbs(String yxbs) {
		this.yxbs = yxbs;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getLsbh() {
		return lsbh;
	}
	public void setLsbh(String lsbh) {
		this.lsbh = lsbh;
	}
	public String getJmslxdldmmc() {
		return jmslxdldmmc;
	}
	public void setJmslxdldmmc(String jmslxdldmmc) {
		this.jmslxdldmmc = jmslxdldmmc;
	}
	public String getJmslxxldmmc() {
		return jmslxxldmmc;
	}
	public void setJmslxxldmmc(String jmslxxldmmc) {
		this.jmslxxldmmc = jmslxxldmmc;
	}
	public int getIndexId() {
		return indexId;
	}
	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}
	public String getReShowStr() {
		return reShowStr;
	}
	public void setReShowStr(String reShowStr) {
		this.reShowStr = reShowStr;
	}
}
