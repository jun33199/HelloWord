package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

public class Sbjkmx_His implements ORObject{
	
//	ZRLXDM	VARCHAR2(2)	N			转入类型代码,用来标识历史数据的类型
	String zrlxdm;
//	ZRRQ	TIMESTAMP(6)	N			转入日期
	Timestamp zrrq;
//	ZRR	VARCHAR2(12)	Y			转入人
	String zrr;
	
//	税种税目代码	SZSMDM	VARCHAR2(9)	TRUE	FALSE	TRUE
    String szsmdm;
//缴款凭证号	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
    String jkpzh;
//计算机代码	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
    String jsjdm;
//预算科目代码	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
    String yskmdm;
//预算科目名称	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String yskmmc;
//预算级次代码	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
    String ysjcdm;
//预算级次名称	YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String ysjcmc;
//税种税目名称	SZSMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String szsmmc;
//课税数量	KSSL	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal kssl;
//计税金额	JSJE	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal jsje;
//实z  [缴税额	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal sjse;
//税款所属开始日期	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
    Timestamp skssksrq;
//税款所属结束日期	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
    Timestamp skssjsrq;
//税种代码        SZDM    VARCHAR2(6)     这不是表中的字段
    String szdm;
//税种名称        SZMC    VARCHAR2(6)     这不是表中的字段
    String szmc;
//征期类型代码
    String zqlxdm; //这不是数据库中的字段,为了后台获取得到税款所属时期必须有这个信息
//入库金额	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
    BigDecimal rkje;
//申报编号    SBBH	VARCHAR2(20)
    String sbbh;
    //市局级分成(保留4为小数)
    BigDecimal sjfc;
    //区县级分成(保留4为小数)
    BigDecimal qjfc;
    String swjgzzjgdm;//税务机关组织机构代码
    String nd;//年度
    BigDecimal sl;//税率

    Timestamp cjrq;
    Timestamp lrrq;
    String qxdm;
    
    /**
     * @Description: TODO 复制bean到历史表
     * @param sbjkmx
     */
    public void copyFrom(Sbjkmx sbjkmx)
    {
    	if(sbjkmx==null)
    	{
    		return;
    	}
//    	this.zrlxdm = sbjkmx.zrlxdm;
//    	this.zrrq = sbjkmx.zrrq;
//    	this.zrr = sbjkmx.zrr;
    	this.szsmdm = sbjkmx.szsmdm;
    	this.jkpzh = sbjkmx.jkpzh;
    	this.jsjdm = sbjkmx.jsjdm;
    	this.yskmdm = sbjkmx.yskmdm;
    	this.yskmmc = sbjkmx.yskmmc;
    	this.ysjcdm = sbjkmx.ysjcdm;
    	this.ysjcmc = sbjkmx.ysjcmc;
    	this.szsmmc = sbjkmx.szsmmc;
    	this.kssl = sbjkmx.kssl;
    	this.jsje = sbjkmx.jsje;
    	this.sjse = sbjkmx.sjse;
    	this.skssksrq = sbjkmx.skssksrq;
    	this.skssjsrq = sbjkmx.skssjsrq;
    	this.szdm = sbjkmx.szdm;
    	this.szmc = sbjkmx.szmc;
    	this.zqlxdm = sbjkmx.zqlxdm;
    	this.rkje = sbjkmx.rkje;
    	this.sbbh = sbjkmx.sbbh;
    	this.sjfc  = sbjkmx.sjfc;
    	this.qjfc = sbjkmx.qjfc;
    	this.swjgzzjgdm = sbjkmx.swjgzzjgdm;
    	this.nd = sbjkmx.nd;
    	this.sl = sbjkmx.sl;
    	this.cjrq = sbjkmx.cjrq;
    	this.lrrq = sbjkmx.lrrq;
    	this.qxdm = sbjkmx.qxdm;

    }
    
	public Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public String getJkpzh() {
		return jkpzh;
	}
	public void setJkpzh(String jkpzh) {
		this.jkpzh = jkpzh;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public BigDecimal getJsje() {
		return jsje;
	}
	public void setJsje(BigDecimal jsje) {
		this.jsje = jsje;
	}
	public BigDecimal getKssl() {
		return kssl;
	}
	public void setKssl(BigDecimal kssl) {
		this.kssl = kssl;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public BigDecimal getQjfc() {
		return qjfc;
	}
	public void setQjfc(BigDecimal qjfc) {
		this.qjfc = qjfc;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public BigDecimal getRkje() {
		return rkje;
	}
	public void setRkje(BigDecimal rkje) {
		this.rkje = rkje;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public BigDecimal getSjfc() {
		return sjfc;
	}
	public void setSjfc(BigDecimal sjfc) {
		this.sjfc = sjfc;
	}
	public BigDecimal getSjse() {
		return sjse;
	}
	public void setSjse(BigDecimal sjse) {
		this.sjse = sjse;
	}
	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}
	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	public Timestamp getSkssksrq() {
		return skssksrq;
	}
	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	public String getSzmc() {
		return szmc;
	}
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	public String getSzsmdm() {
		return szsmdm;
	}
	public void setSzsmdm(String szsmdm) {
		this.szsmdm = szsmdm;
	}
	public String getSzsmmc() {
		return szsmmc;
	}
	public void setSzsmmc(String szsmmc) {
		this.szsmmc = szsmmc;
	}
	public String getYsjcdm() {
		return ysjcdm;
	}
	public void setYsjcdm(String ysjcdm) {
		this.ysjcdm = ysjcdm;
	}
	public String getYsjcmc() {
		return ysjcmc;
	}
	public void setYsjcmc(String ysjcmc) {
		this.ysjcmc = ysjcmc;
	}
	public String getYskmdm() {
		return yskmdm;
	}
	public void setYskmdm(String yskmdm) {
		this.yskmdm = yskmdm;
	}
	public String getYskmmc() {
		return yskmmc;
	}
	public void setYskmmc(String yskmmc) {
		this.yskmmc = yskmmc;
	}
	public String getZqlxdm() {
		return zqlxdm;
	}
	public void setZqlxdm(String zqlxdm) {
		this.zqlxdm = zqlxdm;
	}
	public String getZrlxdm() {
		return zrlxdm;
	}
	public void setZrlxdm(String zrlxdm) {
		this.zrlxdm = zrlxdm;
	}
	public String getZrr() {
		return zrr;
	}
	public void setZrr(String zrr) {
		this.zrr = zrr;
	}
	public Timestamp getZrrq() {
		return zrrq;
	}
	public void setZrrq(Timestamp zrrq) {
		this.zrrq = zrrq;
	}
	

}
