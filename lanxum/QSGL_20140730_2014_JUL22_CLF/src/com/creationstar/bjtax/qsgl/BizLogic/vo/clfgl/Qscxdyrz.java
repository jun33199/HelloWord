package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 存量房交易契税查询打印操作记录日志VO
 * @author tutu
 * 2013-08-19(1)
 */
public class Qscxdyrz implements Serializable {

	/**
     * 序号
     */
    public java.lang.String xh;
    /**
     * 合同编号
     */
    public java.lang.String htbh;
    
    /**
     * 申报编号(合同编号对应的申报编号)
     */
    public java.lang.String sbbh_clf;
    
    /**
     * 申报编号(契税查询打印选中的契税申报编号)
     */
    public java.lang.String sbbh_qs;
    
    /**
     * 创建人(即为操作人，为【yhid+:+yhmc】)
     */
    public java.lang.String cjr;
    
    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

	public java.lang.String getXh() {
		return xh;
	}

	public void setXh(java.lang.String xh) {
		this.xh = xh;
	}

	public java.lang.String getHtbh() {
		return htbh;
	}

	public void setHtbh(java.lang.String htbh) {
		this.htbh = htbh;
	}

	public java.lang.String getSbbh_clf() {
		return sbbh_clf;
	}

	public void setSbbh_clf(java.lang.String sbbh_clf) {
		this.sbbh_clf = sbbh_clf;
	}

	public java.lang.String getSbbh_qs() {
		return sbbh_qs;
	}

	public void setSbbh_qs(java.lang.String sbbh_qs) {
		this.sbbh_qs = sbbh_qs;
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
}
