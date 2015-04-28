package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 卖方申报信息表子表VO
 * @author zhangyj
 * 2013-06-09
 */
public class MfsbxxmxSeller implements Serializable {
	
	/**
     * 申报表号
     */
    public java.lang.String sbbh;
    
    /**
     * 税种税目代码
     */
    public java.lang.String szsmdm;
    
    /**
     * 计税金额
     */
    public java.math.BigDecimal jsje;
    
    /**
     * 税率
     */
    public java.math.BigDecimal sl;
    
    /**
     * 应纳税额
     */
    public java.math.BigDecimal ynse;
    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    
    /**
     * 创建人
     */
    public java.lang.String cjr;
    
    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 录入人
     */
    public java.lang.String lrr;
    
    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;
    
    /**
     * 税款所属开始日期
     */
    public java.sql.Timestamp skssksrq;
    
    
    /**
     * 税款所属结束日期
     */
    public java.sql.Timestamp skssjsrq;   
    
    /**
     * 课税数量
     */
    public java.math.BigDecimal kssl;
    
    
    /**
     * 序号
     */
    public java.lang.String xh;     
    
    /**
     * 减免金额
     */
    public java.math.BigDecimal jmse;
    
    /**
     * 实缴金额
     */
    public java.math.BigDecimal sjje;
    
    

	public java.math.BigDecimal getJmse() {
		return jmse;
	}

	public void setJmse(java.math.BigDecimal jmse) {
		this.jmse = jmse;
	}

	public java.math.BigDecimal getSjje() {
		return sjje;
	}

	public void setSjje(java.math.BigDecimal sjje) {
		this.sjje = sjje;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getSbbh() {
		return sbbh;
	}

	public void setSbbh(java.lang.String sbbh) {
		this.sbbh = sbbh;
	}

	public java.lang.String getSzsmdm() {
		return szsmdm;
	}

	public void setSzsmdm(java.lang.String szsmdm) {
		this.szsmdm = szsmdm;
	}

	public java.math.BigDecimal getJsje() {
		return jsje;
	}

	public void setJsje(java.math.BigDecimal jsje) {
		this.jsje = jsje;
	}

	public java.math.BigDecimal getSl() {
		return sl;
	}

	public void setSl(java.math.BigDecimal sl) {
		this.sl = sl;
	}

	public java.math.BigDecimal getYnse() {
		return ynse;
	}

	public void setYnse(java.math.BigDecimal ynse) {
		this.ynse = ynse;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.lang.String getCjr() {
		return cjr;
	}

	public void setCjr(java.lang.String cjr) {
		this.cjr = cjr;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}
	public java.sql.Timestamp getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(java.sql.Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	public java.sql.Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(java.sql.Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public java.math.BigDecimal getKssl() {
		return kssl;
	}

	public void setKssl(java.math.BigDecimal kssl) {
		this.kssl = kssl;
	}

	public java.lang.String getXh() {
		return xh;
	}

	public void setXh(java.lang.String xh) {
		this.xh = xh;
	}
	
}
