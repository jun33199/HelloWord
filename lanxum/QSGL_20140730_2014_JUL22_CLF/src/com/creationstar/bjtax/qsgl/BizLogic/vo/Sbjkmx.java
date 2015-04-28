package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 申报缴款明细数据值对象
 */
public class Sbjkmx implements Serializable {

    /**
     * 税种税目代码
     */
    public java.lang.String szsmdm;
    public String szsmmc;

    /**
     * 缴款凭证号
     */
    public java.lang.String jkpzh;

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 预算科目代码
     */
    public java.lang.String yskmdm;
    public String yskmmc;

    /**
     * 预算级次
     */
    public java.lang.String ysjcdm;
    public String ysjcmc;

    /**
     * 预算科目分成比例
     */
    public String yskmfcbl;
    
    /**
     * 课税数量
     */
    public java.math.BigDecimal kssl;

    /**
     * 计税金额
     */
    public java.math.BigDecimal jsje;

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
     * 入库金额
     */
    public java.math.BigDecimal rkje;

    /**
     * 申报编号
     */
    public java.lang.String sbbh;

    /**
     * 市级分成
     */
    public java.math.BigDecimal sjfc;

    /**
     * 区级分成
     */
    public java.math.BigDecimal qjfc;

    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    public String swjgzzjgmc;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 税率
     */
    public java.math.BigDecimal sl;

    /**
     * 创建时间
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入时间
     */
    public java.sql.Timestamp lrrq;

    /**
     * 区县代码
     */
    public java.lang.String qxdm;

    /**
     * 获得税种税目代码
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * 获得缴款凭证号
     */
    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 获得预算科目代码
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * 获得预算级次
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * 获得预算分成比例
     */
    public String getYskmfcbl() {
        return yskmfcbl;
    }
    
    /**
     * 获得课税数量
     */
    public BigDecimal getKssl() {
        return kssl;
    }

    /**
     * 获得计税金额
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * 获得实缴税额
     */
    public BigDecimal getSjse() {
        return sjse;
    }

    /**
     * 获得税款所属开始日期
     */
    public Timestamp getSkssksrq() {
        return skssksrq;
    }

    /**
     * 获得税款所属结束日期
     */
    public Timestamp getSkssjsrq() {
        return skssjsrq;
    }

    /**
     * 获得入库金额
     */
    public BigDecimal getRkje() {
        return rkje;
    }

    /**
     * 获得申报编号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得市级分成
     */
    public BigDecimal getSjfc() {
        return sjfc;
    }

    /**
     * 获得区级分成
     */
    public BigDecimal getQjfc() {
        return qjfc;
    }

    /**
     * 获得税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得税率
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * 获得创建时间
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得录入时间
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得区县代码
     */
    public String getQxdm() {
        return qxdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getYsjcmc() {
        return ysjcmc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    public String getSzsmmc() {
        return szsmmc;
    }

    /**
     * 赋值税种税目代码
     * @param szsmdm 税种税目代码
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * 赋值缴款凭证号
     * @param jkpzh 缴款凭证号
     */
    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * 赋值预算科目分成比例
     * @param yskmfcbl 预算科目分成比例
     */
    public void setYskmfcbl(String yskmfcbl) {
        this.yskmfcbl = yskmfcbl;
    }
    
    /**
     * 赋值预算科目代码
     * @param yskmdm 预算科目代码
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * 赋值预算级次
     * @param ysjcdm 预算级次
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * 赋值课税数量
     * @param kssl 课税数量
     */
    public void setKssl(BigDecimal kssl) {
        this.kssl = kssl;
    }

    /**
     * 赋值计税金额
     * @param jsje 计税金额
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * 赋值实缴税额
     * @param sjse 实缴税额
     */
    public void setSjse(BigDecimal sjse) {
        this.sjse = sjse;
    }

    /**
     * 赋值税款所属开始日期
     * @param skssksrq 税款所属开始日期
     */
    public void setSkssksrq(Timestamp skssksrq) {
        this.skssksrq = skssksrq;
    }

    /**
     * 赋值税款所属结束日期
     * @param skssjsrq 税款所属结束日期
     */
    public void setSkssjsrq(Timestamp skssjsrq) {
        this.skssjsrq = skssjsrq;
    }

    /**
     * 赋值入库金额
     * @param rkje 入库金额
     */
    public void setRkje(BigDecimal rkje) {
        this.rkje = rkje;
    }

    /**
     * 赋值申报编号
     * @param sbbh 申报编号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值市级分成
     * @param sjfc 市级分成
     */
    public void setSjfc(BigDecimal sjfc) {
        this.sjfc = sjfc;
    }

    /**
     * 赋值区级分成
     * @param qjfc 区级分成
     */
    public void setQjfc(BigDecimal qjfc) {
        this.qjfc = qjfc;
    }

    /**
     * 赋值税务机关组织机构代码
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值税率
     * @param sl 税率
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * 赋值创建时间
     * @param cjrq 创建时间
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值录入时间
     * @param lrrq 录入时间
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值区县代码
     * @param qxdm 区县代码
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }


}
