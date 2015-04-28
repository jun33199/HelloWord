package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * 土地、房屋信息表值对象
 */
public class Tufwxx implements Serializable {
    /**
     * 相关申报主表表号
     */
    public String sbbh;

    /**
     * 土地、房屋系统唯一标识
     */
    public java.lang.String tdfwid;

    /**
     * 房地产项目名称
     */
    public java.lang.String fdcxmmc;

    /**
     * 合同（契约）签订时间
     */
    public java.sql.Timestamp htqdsj;

    /**
     * 分类
     */
    public java.lang.String fldm;

    /**
     * 分类名称
     */
    public java.lang.String flmc;

    /**
     * 是否为二手房
     * 默认为非二手房
     */
    public String sfesf = Constants.TUFWXX_SFESF_TRUE;
//    public String sfesf;

    /**
     * 土地、房屋座落地址
     */
    public java.lang.String tdfwzldz;

    /**
     * 土地、房屋权属转移类型
     */
    public java.lang.String tdfwqszylx;
    public java.lang.String tdfwqszymc;
    /**
     * 房屋类别
     */
    public java.lang.String fwlxdm;
    public java.lang.String fwlxmc;

    /**
     * 土地、房屋权属转移面积
     */
    public java.math.BigDecimal tdfwqszymj;

    /**
     * 房屋建筑面积
     */
    public java.math.BigDecimal fwjzmj;

    /**
     * 成交价格（人民币）
     */
    public java.math.BigDecimal cjjgrmb;

    /**
     * 成交价格（外币）
     */
    public java.math.BigDecimal cjjgwb;

    /**
     * 币种代码
     */
    public java.lang.String bzdm;
    public java.lang.String bzmc;

    /**
     * 汇率
     */
    public java.math.BigDecimal hldm;

    /**
     * 折合价格（人民币）
     */
    public java.math.BigDecimal zhjgrmb;

    /**
     * 评估价格（人民币）
     */
    public java.math.BigDecimal pgjgrmb;

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
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 年度
     */
    public java.lang.String nd;


    /**
     * 房屋土地部门受理号
     */
    public java.lang.String fwtdbmdm;

    /**
     * 税额调整
     */
    public String setz;

    /**
     * 容积率
     */
    public String rjl;

    /**
     * 土地级次，已转义为房屋所在区域
     */
    public String tdjc;


    /**
     * 获得土地、房屋系统唯一标识
     */
    public String getTdfwid() {
        return tdfwid;
    }

    /**
     * 获得房地产项目名称
     */
    public String getFdcxmmc() {
        return fdcxmmc;
    }

    /**
     * 获得合同（契约）签订时间
     */
    public Timestamp getHtqdsj() {
        return htqdsj;
    }

    /**
     * 获得分类
     */
    public String getFldm() {
        return fldm;
    }

    /**
     * 获得土地、房屋座落地址
     */
    public String getTdfwzldz() {
        return tdfwzldz;
    }

    /**
     * 获得房屋类别
     */
    public String getFwlxdm() {
        return fwlxdm;
    }

    /**
     * 获得土地、房屋权属转移面积
     */
    public BigDecimal getTdfwqszymj() {
        return tdfwqszymj;
    }

    /**
     * 获得房屋建筑面积
     */
    public BigDecimal getFwjzmj() {
        return fwjzmj;
    }

    /**
     * 获得成交价格（人民币）
     */
    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    /**
     * 获得成交价格（外币）
     */
    public BigDecimal getCjjgwb() {
        return cjjgwb;
    }

    /**
     * 获得币种代码
     */
    public String getBzdm() {
        return bzdm;
    }

    /**
     * 获得汇率代码
     */
    public BigDecimal getHldm() {
        return hldm;
    }

    /**
     * 获得折合价格（人民币）
     */
    public BigDecimal getZhjgrmb() {
        return zhjgrmb;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
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
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    public BigDecimal getPgjgrmb() {
        return pgjgrmb;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getBz() {
        return bz;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public String getTdfwqszymc() {
        return tdfwqszymc;
    }

    public String getFwtdbmdm() {
        return fwtdbmdm;
    }

    public String getTdjc() {
        return tdjc;
    }

    public String getRjl() {
        return rjl;
    }

    public String getSfesf() {
        return sfesf;
    }


    /**
     * 赋值土地、房屋系统唯一标识
     * @param tdfwid 土地、房屋系统唯一标识
     */
    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    /**
     * 赋值房地产项目名称
     * @param fdcxmmc 房地产项目名称
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    /**
     * 赋值合同（契约）签订时间
     * @param htqdsj 合同（契约）签订时间
     */
    public void setHtqdsj(Timestamp htqdsj) {
        this.htqdsj = htqdsj;
    }

    /**
     * 赋值分类
     * @param fldm 分类
     */
    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    /**
     * 赋值土地、房屋座落地址
     * @param tdfwzldz 土地、房屋座落地址
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     * 赋值房屋类别
     * @param fwlxdm 房屋类别
     */
    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    /**
     * 赋值土地、房屋权属转移面积
     * @param tdfwqszymj 土地、房屋权属转移面积
     */
    public void setTdfwqszymj(BigDecimal tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     * 赋值房屋建筑面积
     * @param fwjzmj 房屋建筑面积
     */
    public void setFwjzmj(BigDecimal fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     * 赋值成交价格（人民币）
     * @param cjjgrmb 成交价格（人民币）
     */
    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    /**
     * 赋值成交价格（外币）
     * @param cjjgwb 成交价格（外币）
     */
    public void setCjjgwb(BigDecimal cjjgwb) {
        this.cjjgwb = cjjgwb;
    }

    /**
     * 赋值币种代码
     * @param bzdm 币种代码
     */
    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    /**
     * 赋值汇率代码
     * @param hldm 汇率代码
     */
    public void setHldm(BigDecimal hldm) {
        this.hldm = hldm;
    }

    /**
     * 赋值折合价格（人民币）
     * @param zhjgrmb 折合价格（人民币）
     */
    public void setZhjgrmb(BigDecimal zhjgrmb) {
        this.zhjgrmb = zhjgrmb;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
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
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    public void setPgjgrmb(BigDecimal pgjgrmb) {
        this.pgjgrmb = pgjgrmb;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwqszymc(String tdfwqszymc) {
        this.tdfwqszymc = tdfwqszymc;
    }

    public void setFwtdbmdm(String fwtdbmdm) {
        this.fwtdbmdm = fwtdbmdm;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setSfesf(String sfesf) {
        this.sfesf = sfesf;
    }


    /**
     * @return Returns the setz.
     */
    public String getSetz() {
        return setz;
    }

    /**
     * @param setz The setz to set.
     */
    public void setSetz(String setz) {
        this.setz = setz;
    }
}
