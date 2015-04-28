package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 房屋信息VO
 * @author tutu
 * 2013-05-10(1)
 */
public class Fwxx implements Serializable {
	
	/**
     * 申报表号
     */
    public java.lang.String sbbh;
    
    /**
     * 合同编号
     */
    public java.lang.String htbh;
    
    /**
     * 房屋产权证号
     */
    public java.lang.String fwcqzh;
    
    /**
     * 房屋所有权证填发日期
     */
    public java.sql.Timestamp fwsyqztfrq;
    
    /**
     * 房屋坐落区县
     */
    public java.lang.String fwzlqx;
    
    /**
     * 房屋坐落地址
     */
    public java.lang.String fwzldz;
    
    /**
     * 房屋建筑面积
     */
    public java.math.BigDecimal fwjzmj;
    
    /**
     * 建筑结构代码
     */
    public java.lang.String jzjgdm;
    
    /**
     * 规划用途
     */
    public java.lang.String ghyt;
    
    /**
     * 房屋权属转移类型
     */
    public java.lang.String fwqszylx;
    
    /**
     * 总层数
     */
    public java.math.BigDecimal zcs;
    
    /**
     * 所在楼层
     */
    //public java.math.BigDecimal szlc;
    public java.lang.String szlc;
    
    /**
     * 合同网上签约日期
     */
    public java.sql.Timestamp htwsqyrq;
    
    /**
     * 是否为首次上市已购公房
     */
    public java.lang.String sfwscsssggf;
    
    /**
     * 合同总价
     */
    public java.math.BigDecimal htzj;
    
    /**
     * 币种代码
     */
    public java.lang.String bzdm;
    
    /**
     * 币种名称
     */
    public java.lang.String bzmc;
    
    /**
     * 汇率
     */
    public java.math.BigDecimal hl;
    
    /**
     * 外币金额
     */
    public java.math.BigDecimal wbje;
    
    /**
     * 房地产中介机构名称
     */
    public java.lang.String fdczjjgmc;
    
    /**
     * 版本标示
     */
    public java.lang.String bbbs;
    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    
    /**
     * 是否为二手房,01：二手房,00 非二手房,默认值为：''01'''
     */
    public java.lang.String sfesf;
    
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
     * 备注
     */
    public java.lang.String bz;
    
    /**
     * 二维码数据
     */
    public java.lang.String ewmsj;
    
    /**
     * 
     * 新加字段:房屋性质代码
     */
    
    public java.lang.String fwxzdm;   
    
    /**
     * 信息来源（01：二维码，02：手工采集）
     */
    public java.lang.String xxly;

	public java.lang.String getXxly() {
		return xxly;
	}

	public void setXxly(java.lang.String xxly) {
		this.xxly = xxly;
	}

	public java.lang.String getSbbh() {
		return sbbh;
	}

	public void setSbbh(java.lang.String sbbh) {
		this.sbbh = sbbh;
	}

	public java.lang.String getHtbh() {
		return htbh;
	}

	public void setHtbh(java.lang.String htbh) {
		this.htbh = htbh;
	}

	public java.lang.String getFwcqzh() {
		return fwcqzh;
	}

	public void setFwcqzh(java.lang.String fwcqzh) {
		this.fwcqzh = fwcqzh;
	}

	public java.sql.Timestamp getFwsyqztfrq() {
		return fwsyqztfrq;
	}

	public void setFwsyqztfrq(java.sql.Timestamp fwsyqztfrq) {
		this.fwsyqztfrq = fwsyqztfrq;
	}

	public java.lang.String getFwzlqx() {
		return fwzlqx;
	}

	public void setFwzlqx(java.lang.String fwzlqx) {
		this.fwzlqx = fwzlqx;
	}

	public java.lang.String getFwzldz() {
		return fwzldz;
	}

	public void setFwzldz(java.lang.String fwzldz) {
		this.fwzldz = fwzldz;
	}

	public java.math.BigDecimal getFwjzmj() {
		return fwjzmj;
	}

	public void setFwjzmj(java.math.BigDecimal fwjzmj) {
		this.fwjzmj = fwjzmj;
	}

	public java.lang.String getJzjgdm() {
		return jzjgdm;
	}

	public void setJzjgdm(java.lang.String jzjgdm) {
		this.jzjgdm = jzjgdm;
	}

	public java.lang.String getGhyt() {
		return ghyt;
	}

	public void setGhyt(java.lang.String ghyt) {
		this.ghyt = ghyt;
	}

	public java.lang.String getFwqszylx() {
		return fwqszylx;
	}

	public void setFwqszylx(java.lang.String fwqszylx) {
		this.fwqszylx = fwqszylx;
	}

	public java.math.BigDecimal getZcs() {
		return zcs;
	}

	public void setZcs(java.math.BigDecimal zcs) {
		this.zcs = zcs;
	}

	

	public java.lang.String getSzlc() {
		return szlc;
	}

	public void setSzlc(java.lang.String szlc) {
		this.szlc = szlc;
	}

	public java.sql.Timestamp getHtwsqyrq() {
		return htwsqyrq;
	}

	public void setHtwsqyrq(java.sql.Timestamp htwsqyrq) {
		this.htwsqyrq = htwsqyrq;
	}

	public java.lang.String getSfwscsssggf() {
		return sfwscsssggf;
	}

	public void setSfwscsssggf(java.lang.String sfwscsssggf) {
		this.sfwscsssggf = sfwscsssggf;
	}

	public java.math.BigDecimal getHtzj() {
		return htzj;
	}

	public void setHtzj(java.math.BigDecimal htzj) {
		this.htzj = htzj;
	}

	public java.lang.String getBzdm() {
		return bzdm;
	}

	public void setBzdm(java.lang.String bzdm) {
		this.bzdm = bzdm;
	}

	public java.lang.String getBzmc() {
		return bzmc;
	}

	public void setBzmc(java.lang.String bzmc) {
		this.bzmc = bzmc;
	}

	public java.math.BigDecimal getHl() {
		return hl;
	}

	public void setHl(java.math.BigDecimal hl) {
		this.hl = hl;
	}

	public java.math.BigDecimal getWbje() {
		return wbje;
	}

	public void setWbje(java.math.BigDecimal wbje) {
		this.wbje = wbje;
	}

	public java.lang.String getFdczjjgmc() {
		return fdczjjgmc;
	}

	public void setFdczjjgmc(java.lang.String fdczjjgmc) {
		this.fdczjjgmc = fdczjjgmc;
	}

	public java.lang.String getBbbs() {
		return bbbs;
	}

	public void setBbbs(java.lang.String bbbs) {
		this.bbbs = bbbs;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.lang.String getSfesf() {
		return sfesf;
	}

	public void setSfesf(java.lang.String sfesf) {
		this.sfesf = sfesf;
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

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.String getEwmsj() {
		return ewmsj;
	}

	public void setEwmsj(java.lang.String ewmsj) {
		this.ewmsj = ewmsj;
	}

	public java.lang.String getFwxzdm() {
		return fwxzdm;
	}

	public void setFwxzdm(java.lang.String fwxzdm) {
		this.fwxzdm = fwxzdm;
	}
    

}
