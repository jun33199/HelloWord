package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;
/**
 * 房屋性质
 * @author tangchangfu
 *
 */
public class Fwxz implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 房屋性质代码
     */
    public java.lang.String fwxzdm;

    /**
     * 房屋性质名称
     */
    public java.lang.String fwxzmc;
    
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

	public java.lang.String getFwxzdm() {
		return fwxzdm;
	}

	public void setFwxzdm(java.lang.String fwxzdm) {
		this.fwxzdm = fwxzdm;
	}

	public java.lang.String getFwxzmc() {
		return fwxzmc;
	}

	public void setFwxzmc(java.lang.String fwxzmc) {
		this.fwxzmc = fwxzmc;
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
    
    
	
	
	

}
