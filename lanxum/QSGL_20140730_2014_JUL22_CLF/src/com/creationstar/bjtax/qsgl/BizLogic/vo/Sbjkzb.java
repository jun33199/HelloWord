package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * 申报缴款主表数据值对象
 */
public class Sbjkzb implements Serializable {

    /**
     * 缴款凭证号
     */
    public java.lang.String jkpzh;

    /**
     * 纳税人名称
     */
    public java.lang.String nsrmc;

    /**
     * 税款类型代码
     */
    public java.lang.String sklxdm;

    /**
     * 税款类型名称
     */
    public java.lang.String sklxmc;

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 登记申报方式代码
     */
    public java.lang.String fsdm;

    /**
     * 隶属关系代码
     */
    public java.lang.String lsgxdm;

    /**
     * 隶属关系名称
     */
    public java.lang.String lsgxmc;

    /**
     * 银行代码
     */
    public java.lang.String yhdm;

    /**
     * 银行名称
     */
    public java.lang.String yhmc;

    /**
     * 帐号
     */
    public java.lang.String zh;

    /**
     * 登记注册类型代码
     */
    public java.lang.String djzclxdm;
    public String djzclxmc;

    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    public String swjgzzjgmc;

    /**
     * 征收机关代码
     */
    public java.lang.String zsswjgzzjgdm;
    public String zsswjgzzjgmc;

    /**
     * 国家标准行业代码
     */
    public java.lang.String gjbzhydm;
    public String gjbzhymc;

    /**
     * 国库组织机构代码
     */
    public java.lang.String gkzzjgdm;
    public String gkzzjgmc;

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
     * 税种代码
     */
    public java.lang.String szdm;
    public String szmc;

    /**
     * 录入时间
     */
    public java.sql.Timestamp lrrq;

    /**
     * 申报日期
     */
    public java.sql.Timestamp sbrq;

    /**
     * 银行收款时间
     */
    public java.sql.Timestamp jksj;

    /**
     * 限缴日期
     */
    public java.sql.Timestamp xjrq;

    /**
     * 处理标记代码
     */
    public java.lang.String clbjdm;

    /**
     * 实缴金额
     */
    public java.math.BigDecimal sjje;

    /**
     * 帐页日期
     */
    public java.sql.Timestamp zyrq;

    /**
     * 入库金额
     */
    public java.math.BigDecimal rkje;

    /**
     * 帐务标识
     */
    public java.lang.String zwbs;

    /**
     * 核销人代码
     */
    public java.lang.String hxrdm;

    /**
     * 核销人名称
     */
    public java.lang.String hxrmc;

    /**
     * 录入人代码
     */
    public java.lang.String lrr;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 核销日期
     */
    public java.sql.Timestamp hxrq;

    /**
     * 申报编号
     */
    public java.lang.String sbbh;

    /**
     * 经营地址联系电话
     */
    public java.lang.String jydzlxdm;

    /**
     * 税款所属开始日期
     */
    public java.sql.Timestamp skssksrq;

    /**
     * 税款所属结束日期
     */
    public java.sql.Timestamp skssjsrq;

    /**
     * 数据来源
     */
    public java.lang.String sjly;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 创建时间
     */
    public java.sql.Timestamp cjrq;

    /**
     * 区县代码
     */
    public java.lang.String qxdm;

    /**
     * 包含的完税证的数量，也就是一张缴款书全部的课税数量
     */
    public int kssl;

    /**
     * 房地产位置，直接生成的缴款书打印用
     */
    public String fdcwz;

    /**
     * 国库交换号
     */
    public String gkjhh;

    /**
     * 明细数据的集合
     */
    public ArrayList mxList = new ArrayList();

    /**
     * 获得缴款凭证号
     */
    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * 获得税款类型代码
     */
    public String getSklxdm() {
        return sklxdm;
    }

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 获得登记申报方式代码
     */
    public String getFsdm() {
        return fsdm;
    }

    /**
     * 获得隶属关系代码
     */
    public String getLsgxdm() {
        return lsgxdm;
    }

    /**
     * 获得银行代码
     */
    public String getYhdm() {
        return yhdm;
    }

    /**
     * 获得银行名称
     */
    public String getYhmc() {
        return yhmc;
    }

    /**
     * 获得帐号
     */
    public String getZh() {
        return zh;
    }

    /**
     * 获得登记注册类型代码
     */
    public String getDjzclxdm() {
        return djzclxdm;
    }

    /**
     * 获得税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得征收机关代码
     */
    public String getZsswjgzzjgdm() {
        return zsswjgzzjgdm;
    }

    /**
     * 获得国家标准行业代码
     */
    public String getGjbzhydm() {
        return gjbzhydm;
    }

    /**
     * 获得国库组织机构代码
     */
    public String getGkzzjgdm() {
        return gkzzjgdm;
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
     * 获得税种代码
     */
    public String getSzdm() {
        return szdm;
    }

    /**
     * 获得录入时间
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得申报日期
     */
    public Timestamp getSbrq() {
        return sbrq;
    }

    /**
     * 获得银行收款时间
     */
    public Timestamp getJksj() {
        return jksj;
    }

    /**
     * 获得限缴日期
     */
    public Timestamp getXjrq() {
        return xjrq;
    }

    /**
     * 获得处理标记代码
     */
    public String getClbjdm() {
        return clbjdm;
    }

    /**
     * 获得实缴金额
     */
    public BigDecimal getSjje() {
        return sjje;
    }

    /**
     * 获得帐页日期
     */
    public Timestamp getZyrq() {
        return zyrq;
    }

    /**
     * 获得入库金额
     */
    public BigDecimal getRkje() {
        return rkje;
    }

    /**
     * 获得帐务标识
     */
    public String getZwbs() {
        return zwbs;
    }

    /**
     * 获得核销人代码
     */
    public String getHxrdm() {
        return hxrdm;
    }

    /**
     * 获得核销人名称
     */
    public String getHxrmc() {
        return hxrmc;
    }

    /**
     * 获得录入人代码
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得核销日期
     */
    public Timestamp getHxrq() {
        return hxrq;
    }

    /**
     * 获得申报编号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得经营地址联系电话
     */
    public String getJydzlxdm() {
        return jydzlxdm;
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
     * 获得数据来源
     */
    public String getSjly() {
        return sjly;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得创建时间
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得区县代码
     */
    public String getQxdm() {
        return qxdm;
    }

    public ArrayList getMxList() {
        return mxList;
    }

    public String getLsgxmc() {
        return lsgxmc;
    }

    public String getSklxmc() {
        return sklxmc;
    }

    public String getDjzclxmc() {
        return djzclxmc;
    }

    public String getGjbzhymc() {
        return gjbzhymc;
    }

    public String getGkzzjgmc() {
        return gkzzjgmc;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getSzmc() {
        return szmc;
    }

    public String getYsjcmc() {
        return ysjcmc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    public String getZsswjgzzjgmc() {
        return zsswjgzzjgmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public int getKssl() {
        return kssl;
    }

    public String getFdcwz() {
        return fdcwz;
    }

    public String getGkjhh() {
        return gkjhh;
    }

    /**
     * 赋值缴款凭证号
     * @param jkpzh 缴款凭证号
     */
    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    /**
     * 赋值税款类型代码
     * @param sklxdm 税款类型代码
     */
    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * 赋值登记申报方式代码
     * @param fsdm 登记申报方式代码
     */
    public void setFsdm(String fsdm) {
        this.fsdm = fsdm;
    }

    /**
     * 赋值隶属关系代码
     * @param lsgxdm 隶属关系代码
     */
    public void setLsgxdm(String lsgxdm) {
        this.lsgxdm = lsgxdm;
    }

    /**
     * 赋值银行代码
     * @param yhdm 银行代码
     */
    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    /**
     * 赋值银行名称
     * @param yhmc 银行名称
     */
    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    /**
     * 赋值帐号
     * @param zh 帐号
     */
    public void setZh(String zh) {
        this.zh = zh;
    }

    /**
     * 赋值登记注册类型代码
     * @param djzclxdm 登记注册类型代码
     */
    public void setDjzclxdm(String djzclxdm) {
        this.djzclxdm = djzclxdm;
    }

    /**
     * 赋值税务机关组织机构代码
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值征收机关代码
     * @param zsswjgzzjgdm 征收机关代码
     */
    public void setZsswjgzzjgdm(String zsswjgzzjgdm) {
        this.zsswjgzzjgdm = zsswjgzzjgdm;
    }

    /**
     * 赋值国家标准行业代码
     * @param gjbzhydm 国家标准行业代码
     */
    public void setGjbzhydm(String gjbzhydm) {
        this.gjbzhydm = gjbzhydm;
    }

    /**
     * 赋值国库组织机构代码
     * @param gkzzjgdm 国库组织机构代码
     */
    public void setGkzzjgdm(String gkzzjgdm) {
        this.gkzzjgdm = gkzzjgdm;
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
     * 赋值税种代码
     * @param szdm 税种代码
     */
    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    /**
     * 赋值录入时间
     * @param lrrq 录入时间
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值申报日期
     * @param sbrq 申报日期
     */
    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * 赋值银行收款时间
     * @param jksj 银行收款时间
     */
    public void setJksj(Timestamp jksj) {
        this.jksj = jksj;
    }

    /**
     * 赋值限缴日期
     * @param xjrq 限缴日期
     */
    public void setXjrq(Timestamp xjrq) {
        this.xjrq = xjrq;
    }

    /**
     * 赋值处理标记代码
     * @param clbjdm 处理标记代码
     */
    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    /**
     * 赋值实缴金额
     * @param sjje 实缴金额
     */
    public void setSjje(BigDecimal sjje) {
        this.sjje = sjje;
    }

    /**
     * 赋值帐页日期
     * @param zyrq 帐页日期
     */
    public void setZyrq(Timestamp zyrq) {
        this.zyrq = zyrq;
    }

    /**
     * 赋值入库金额
     * @param rkje 入库金额
     */
    public void setRkje(BigDecimal rkje) {
        this.rkje = rkje;
    }

    /**
     * 赋值帐务标识
     * @param zwbs 帐务标识
     */
    public void setZwbs(String zwbs) {
        this.zwbs = zwbs;
    }

    /**
     * 赋值核销人代码
     * @param hxrdm 核销人代码
     */
    public void setHxrdm(String hxrdm) {
        this.hxrdm = hxrdm;
    }

    /**
     * 赋值核销人名称
     * @param hxrmc 核销人名称
     */
    public void setHxrmc(String hxrmc) {
        this.hxrmc = hxrmc;
    }

    /**
     * 赋值录入人代码
     * @param lrr 录入人代码
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 赋值核销日期
     * @param hxrq 核销日期
     */
    public void setHxrq(Timestamp hxrq) {
        this.hxrq = hxrq;
    }

    /**
     * 赋值申报编号
     * @param sbbh 申报编号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值经营地址联系电话
     * @param jydzlxdm 经营地址联系电话
     */
    public void setJydzlxdm(String jydzlxdm) {
        this.jydzlxdm = jydzlxdm;
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
     * 赋值数据来源
     * @param sjly 数据来源
     */
    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值创建时间
     * @param cjrq 创建时间
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值区县代码
     * @param qxdm 区县代码
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setLsgxmc(String lsgxmc) {
        this.lsgxmc = lsgxmc;
    }

    public void setSklxmc(String sklxmc) {
        this.sklxmc = sklxmc;
    }

    public void setDjzclxmc(String djzclxmc) {
        this.djzclxmc = djzclxmc;
    }

    public void setGjbzhymc(String gjbzhymc) {
        this.gjbzhymc = gjbzhymc;
    }

    public void setGkzzjgmc(String gkzzjgmc) {
        this.gkzzjgmc = gkzzjgmc;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc;
    }

    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    public void setZsswjgzzjgmc(String zsswjgzzjgmc) {
        this.zsswjgzzjgmc = zsswjgzzjgmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setKssl(int kssl) {
        this.kssl = kssl;
    }

    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    public void setGkjhh(String gkjhh) {
        this.gkjhh = gkjhh;
    }


}
