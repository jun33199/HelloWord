package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;
/**
 * 房屋权属转移明细
 * @author zhangj
 *
 */
public class Qszyxsmx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 权属转移形式代码（dmdb.sf_dm_qszyxs）
     */
    public java.lang.String qszyxsdm;
	
    /**
     * 房屋权属转移明细代码
     */
    public java.lang.String qszyxsmxdm;

    /**
     * 房屋权属转移明细名称
     */
    public java.lang.String qszyxsmxmc;
    
    /**
     * 注销标识
     */
    public java.lang.String zxbs;
    
    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;
    
    
    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     *  创建人日期
     */
    public java.sql.Timestamp cjrq;

	public java.lang.String getQszyxsmxdm() {
		return qszyxsmxdm;
	}

	public void setQszyxsmxdm(java.lang.String qszyxsmxdm) {
		this.qszyxsmxdm = qszyxsmxdm;
	}

	public java.lang.String getQszyxsmxmc() {
		return qszyxsmxmc;
	}

	public void setQszyxsmxmc(java.lang.String qszyxsmxmc) {
		this.qszyxsmxmc = qszyxsmxmc;
	}

	public java.lang.String getZxbs() {
		return zxbs;
	}

	public void setZxbs(java.lang.String zxbs) {
		this.zxbs = zxbs;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getCjr() {
		return cjr;
	}

	public void setCjr(java.lang.String cjr) {
		this.cjr = cjr;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public java.lang.String getQszyxsdm() {
		return qszyxsdm;
	}

	public void setQszyxsdm(java.lang.String qszyxsdm) {
		this.qszyxsdm = qszyxsdm;
	}
    
    
}
