package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 契税申报主表值对象
 */
public class Sbzb implements Serializable {

    /**
     * 申报表号
     */
    public java.lang.String sbbh;
    /**
     * 增加建委业务编号字段， fujx，20081125
     */
    private String jwywbh;

    /**
     * 增加合同编号字段， fujx，20081125
     */
    private String htbh;


    /**
     * 申报日期
     */
    public java.sql.Timestamp sbrq;

    /**
     * 隶属关系名称
     */
    public String lsgxmc;

    /**
     * 注册类型名称
     */
    public String zclxmc;

    /**
     * 税款类型名称
     */
    public String sklxmc;

    /**
     * 电话
     */
    public String dh;

    /**
     * 缴税方式
     */
    public java.lang.String jsfsdm;

    /**
     * 缴税方式名称
     */
    public java.lang.String jsfsmc;

    /**
     * 房屋土地部门受理号
     */
    public java.lang.String fwtdbmdm;

    /**
     * 纳税人类型代码
     */
    public java.lang.String nsrlxdm;

    /**
     * 纳税人类型名称
     */
    public java.lang.String nsrlxmc;

    /**
     * 征收税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;

    /**
     * 征收税务机关组织机构名称
     */
    public java.lang.String swjgzzjgmc;

    /**
     * 征收人员名称
     */
    public java.lang.String zsrymc;

    /**
     * 税率
     */
    public java.math.BigDecimal sl;

    /**
     * 计税金额
     */
    public java.math.BigDecimal jsje;

    /**
     * 应纳税额
     */
    public java.math.BigDecimal ynse;

    /**
     * 办理减免税标识
     */
    public java.lang.String bljmsbs;

    /**
     * 补录标识
     */
    public java.lang.String blbs;

    /**
     * 用户标识
     */
    public java.lang.String yhbs;

    /**
     * 状态标识
     */
    public java.lang.String ztbs;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 对方申报表号
     */
    public String dfsbbh;

    /**
     * 票证帐户代码
     */
    public String pzzhdm;

    /**
     * 票证帐户名称
     */
    public String pzzhmc;

    /**
     * 导入批次号
     */
    public String drpch;

    /**
     * 税额调整
     */
    public String setz;

    /**
     * 数据来源
     */
    public String sjly;

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得申报日期
     */
    public Timestamp getSbrq() {
        return sbrq;
    }

    /**
     * 获得缴税方式
     */
    public String getJsfsdm() {
        return jsfsdm;
    }

    /**
     * 获得缴税方式名称
     */
    public String getJsfsmc() {
        return jsfsmc;
    }

    /**
     * 获得房屋土地部门受理号
     */
    public String getFwtdbmdm() {
        return fwtdbmdm;
    }

    /**
     * 获得纳税人类型代码
     */
    public String getNsrlxdm() {
        return nsrlxdm;
    }

    /**
     * 获得征收税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得征收人员名称
     */
    public String getZsrymc() {
        return zsrymc;
    }

    /**
     * 获得税率
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * 获得计税金额
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * 获得应纳税额
     */
    public BigDecimal getYnse() {
        return ynse;
    }

    /**
     * 获得办理减免税标识
     */
    public String getBljmsbs() {
        return bljmsbs;
    }

    /**
     * 获得补录标识
     */
    public String getBlbs() {
        return blbs;
    }

    /**
     * 获得用户标识
     */
    public String getYhbs() {
        return yhbs;
    }

    /**
     * 获得状态标识
     */
    public String getZtbs() {
        return ztbs;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得创建人
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * 获得创建日期
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    public String getDfsbbh() {
        return dfsbbh;
    }

    public String getDh() {
        return dh;
    }

    public String getLsgxmc() {
        return lsgxmc;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public String getSklxmc() {
        return sklxmc;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getZclxmc() {
        return zclxmc;
    }

    public String getPzzhdm() {
        return pzzhdm;
    }

    public String getPzzhmc() {
        return pzzhmc;
    }

    public String getDrpch() {
        return drpch;
    }

    public String getSetz() {
        return setz;
    }

    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    public String getSjly() {
        return sjly;
    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值申报日期
     * @param sbrq 申报日期
     */
    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * 赋值缴税方式
     * @param jsfsdm 缴税方式
     */
    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    /**
     * 赋值缴税方式名称
     * @param jsfsmc 缴税方式名称
     */
    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }

    /**
     * 赋值房屋土地部门受理号
     * @param fwtdbmdm 房屋土地部门受理号
     */
    public void setFwtdbmdm(String fwtdbmdm) {
        this.fwtdbmdm = fwtdbmdm;
    }

    /**
     * 赋值纳税人类型代码
     * @param nsrlxdm 纳税人类型代码
     */
    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    /**
     * 赋值征收税务机关组织机构代码
     * @param swjgzzjgdm 征收税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值征收人员名称
     * @param zsrymc 征收人员名称
     */
    public void setZsrymc(String zsrymc) {
        this.zsrymc = zsrymc;
    }

    /**
     * 赋值税率
     * @param sl 税率
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * 赋值计税金额
     * @param jsje 计税金额
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * 赋值应纳税额
     * @param ynse 应纳税额
     */
    public void setYnse(BigDecimal ynse) {
        this.ynse = ynse;
    }

    /**
     * 赋值办理减免税标识
     * @param bljmsbs 办理减免税标识
     */
    public void setBljmsbs(String bljmsbs) {
        this.bljmsbs = bljmsbs;
    }

    /**
     * 赋值补录标识
     * @param blbs 补录标识
     */
    public void setBlbs(String blbs) {
        this.blbs = blbs;
    }

    /**
     * 赋值用户标识
     * @param yhbs 用户标识
     */
    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    /**
     * 赋值状态标识
     * @param ztbs 状态标识
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值创建人
     * @param cjr 创建人
     */
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    /**
     * 赋值创建日期
     * @param cjrq 创建日期
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setDfsbbh(String dfsbbh) {
        this.dfsbbh = dfsbbh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public void setLsgxmc(String lsgxmc) {
        this.lsgxmc = lsgxmc;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setSklxmc(String sklxmc) {
        this.sklxmc = sklxmc;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setZclxmc(String zclxmc) {
        this.zclxmc = zclxmc;
    }

    public void setPzzhdm(String pzzhdm) {
        this.pzzhdm = pzzhdm;
    }

    public void setPzzhmc(String pzzhmc) {
        this.pzzhmc = pzzhmc;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setSetz(String setz) {
        this.setz = setz;
    }

    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }
}
