package com.lscdz.qysds.application.qysdsnb2014.vo.request;

import com.lscdz.util.PageInfo;

public class NsrjbxxVo2014 extends PageInfo{
	/**
	 * 计算机代码
	 */
    private String jsjdm;
    /**
     * 年度
     */
    private String nd;
    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 主管税务组织机构
     */
    private String zgswjgdm;
    /**
     * 申请类型
     */
    private String sqlx;
    private String smwsbz;//上门（0），网上（1）标志
    
	public String getSmwsbz() {
		return smwsbz;
	}
	public void setSmwsbz(String smwsbz) {
		this.smwsbz = smwsbz;
	}
	public String getSqlx() {
		return sqlx;
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZgswjgdm() {
		return zgswjgdm;
	}
	public void setZgswjgdm(String zgswjgdm) {
		this.zgswjgdm = zgswjgdm;
	}
    
}
