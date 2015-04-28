package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 卖方申报信息查询打印VO
 * @author zhangyj
 * 2013-06-14
 */
public class MfsbxxprintSeller implements Serializable {

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;
    
    /**
     * 完税证序号
     */
    public java.lang.String wszxh;
	
	/**
     * 年度字别
     */
    public java.lang.String ndzb;
    
    /**
     * 完税证号
     */
    public java.lang.String wszh;
    
    /**
     * 合计实缴金额-完税证
     */
    public java.math.BigDecimal hjsjje;

    /**
     * 申报编号
     */
    public java.lang.String sbbh;
    
    /**
     * 合计入库金额-缴款书
     */
    public java.math.BigDecimal rkjehj;
    
    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 纳税人名称
     */
    public java.lang.String nsrmc;
    
    /**
     * 地址
     */
    public java.lang.String dz;   
    
    
    /**
     * 课税数量
     */
    public java.math.BigDecimal kssl;    
    
    /**
     * 缴税金额
     */
    public java.math.BigDecimal jsje;  
    
    /**
     * 税率
     */
    public java.math.BigDecimal sl;  
    
    /**
     * 已缴或扣除
     */
    public java.math.BigDecimal yjhkc;  
    
    /**
     * 实缴税额
     */
    public java.math.BigDecimal sjse;  
    
    /**
     * 税款所属开始日期
     */
    public java.sql.Timestamp skssksrq;
    
    /**
     * 税款所属结束日期
     */
    public java.sql.Timestamp skssjsrq;
    
    /**
     * 税种代码
     */
    public java.lang.String szdm;  
    
    /**
     * 税种税目代码
     */
    public java.lang.String szsmdm;

    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;

    /**
     * 备注
     */
    public java.lang.String bz;
    
    /**
     * 申报日期
     */
    public java.sql.Timestamp sbrq;
    
    /**
     * 银行名称
     */
    public java.lang.String yhmc;
        
    /**
     * 银行帐号
     */
    public java.lang.String yhzh;
    
    /**
     * 预算科目代码
     */
    public java.lang.String yskmdm;
        
    /**
     * 国库组织机构代码
     */
    public java.lang.String gkzzjgdm;
    
	public java.lang.String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(java.lang.String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public java.lang.String getWszxh() {
		return wszxh;
	}

	public void setWszxh(java.lang.String wszxh) {
		this.wszxh = wszxh;
	}

	public java.lang.String getNdzb() {
		return ndzb;
	}

	public void setNdzb(java.lang.String ndzb) {
		this.ndzb = ndzb;
	}

	public java.lang.String getWszh() {
		return wszh;
	}

	public void setWszh(java.lang.String wszh) {
		this.wszh = wszh;
	}

	public java.math.BigDecimal getHjsjje() {
		return hjsjje;
	}

	public void setHjsjje(java.math.BigDecimal hjsjje) {
		this.hjsjje = hjsjje;
	}

	public java.lang.String getSbbh() {
		return sbbh;
	}

	public void setSbbh(java.lang.String sbbh) {
		this.sbbh = sbbh;
	}

	public java.math.BigDecimal getRkjehj() {
		return rkjehj;
	}

	public void setRkjehj(java.math.BigDecimal rkjehj) {
		this.rkjehj = rkjehj;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	public java.lang.String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(java.lang.String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public java.lang.String getDz() {
		return dz;
	}

	public void setDz(java.lang.String dz) {
		this.dz = dz;
	}

	public java.math.BigDecimal getKssl() {
		return kssl;
	}

	public void setKssl(java.math.BigDecimal kssl) {
		this.kssl = kssl;
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

	public java.math.BigDecimal getYjhkc() {
		return yjhkc;
	}

	public void setYjhkc(java.math.BigDecimal yjhkc) {
		this.yjhkc = yjhkc;
	}

	public java.math.BigDecimal getSjse() {
		return sjse;
	}

	public void setSjse(java.math.BigDecimal sjse) {
		this.sjse = sjse;
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

	public java.lang.String getSzdm() {
		return szdm;
	}

	public void setSzdm(java.lang.String szdm) {
		this.szdm = szdm;
	}

	public java.lang.String getSzsmdm() {
		return szsmdm;
	}

	public void setSzsmdm(java.lang.String szsmdm) {
		this.szsmdm = szsmdm;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.sql.Timestamp getSbrq() {
		return sbrq;
	}

	public void setSbrq(java.sql.Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	public java.lang.String getYhmc() {
		return yhmc;
	}

	public void setYhmc(java.lang.String yhmc) {
		this.yhmc = yhmc;
	}

	public java.lang.String getYhzh() {
		return yhzh;
	}

	public void setYhzh(java.lang.String yhzh) {
		this.yhzh = yhzh;
	}

	public java.lang.String getYskmdm() {
		return yskmdm;
	}

	public void setYskmdm(java.lang.String yskmdm) {
		this.yskmdm = yskmdm;
	}

	public java.lang.String getGkzzjgdm() {
		return gkzzjgdm;
	}

	public void setGkzzjgdm(java.lang.String gkzzjgdm) {
		this.gkzzjgdm = gkzzjgdm;
	}    
	
}
