package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * 契税完税证主数据值对象
 */
public class Qswszz implements Serializable {

    /**
     * 完税证号
     */
    public java.lang.String wszh;

    /**
     * 年度字别
     */
    public java.lang.String ndzb;

    /**
     * 票证种类代码
     */
    public java.lang.String pzzldm;

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;

    /**
     * 税务机关组织机构名称
     */
    public java.lang.String swjgzzjgmc;

    /**
     * 登记注册类型代码
     */
    public java.lang.String djzclxdm;

    /**
     * 地址
     */
    public java.lang.String dz;

    /**
     * 合计实缴税额
     */
    public java.math.BigDecimal hjsjje;

    /**
     * 处理标记代码
     */
    public java.lang.String clbjdm;

    /**
     * 申报汇总单号
     */
    public java.lang.String sbhzdh;

    /**
     * 结报单号
     */
    public java.lang.String jbdh;

    /**
     * 证件类型代码
     */
    public java.lang.String zjlxdm;

    /**
     * 证件号码
     */
    public java.lang.String zjhm;

    /**
     * 登记申报方式代码
     */
    public java.lang.String fsdm;

    /**
     * 国家标准行业代码
     */
    public java.lang.String gjbzhydm;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建时间
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入时间
     */
    public java.sql.Timestamp lrrq;

    /**
     * 逾期天数
     */
    public java.math.BigDecimal yqts;

    /**
     * 滞纳金总额
     */
    public java.math.BigDecimal znjzje;

    /**
     * 房地产位置
     */
    public java.lang.String fdcwz;

    /**
     * 契税约（合同）成立日期
     */
    public java.sql.Timestamp htclrq;

    /**
     * 减税金额
     */
    public java.math.BigDecimal jsje;

    /**
     * 免税金额
     */
    public java.math.BigDecimal msjs;
    /**
     * 关联申报表号
     */
    public String sbbh;
    ;
    /**
     * 税务机关组织机构代码2
     */
    public java.lang.String swjgzzjgdm2;

    /**
     * 纳税人计算机代码
     */
    public java.lang.String nsrjsjdm;

    /**
     * 登记注册类型名称
     */
    public String djzclxmc;

    /**
     * 证件类型名称
     */
    public String zjlxmc;
    /**
     * 登记申报方式名称
     */
    public String fsmc;

    /**
     * 国家标准行业名称
     */
    public String gjbzhymc;

    /**
     * 缴税方式代码
     */
    public String jsfsdm;

    /**
     * 缴税方式名称
     */
    public String jsfsmc;
    /**
     * 纳税人名称
     */
    public String nsrmc;

    /**
     * 纳税人代码
     */
    public String nsrdm;

    /**
     * 征收点代码
     */
    public String zsddm;

    /**
     * 征收点名称
     */
    public String zsdmc;

    /**
     * 缴款凭证号
     */
    public String jkpzh;

    /**
     * 完税证明细列表
     */
    public ArrayList mxList = new ArrayList();

    /**
     * 获得完税证号
     */

    public String getWszh() {
        return wszh;
    }

    /**
     * 获得年度字别
     */
    public String getNdzb() {
        return ndzb;
    }

    /**
     * 获得票证种类代码
     */
    public String getPzzldm() {
        return pzzldm;
    }

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 获得税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得登记注册类型代码
     */
    public String getDjzclxdm() {
        return djzclxdm;
    }

    /**
     * 获得纳税人计算机代码
     */
    public String getNsrjsjdm() {
        return nsrjsjdm;
    }

    /**
     * 获得地址
     */
    public String getDz() {
        return dz;
    }

    /**
     * 获得合计实缴税额
     */
    public BigDecimal getHjsjje() {
        return hjsjje;
    }

    /**
     * 获得处理标记代码
     */
    public String getClbjdm() {
        return clbjdm;
    }

    /**
     * 获得申报汇总单号
     */
    public String getSbhzdh() {
        return sbhzdh;
    }

    /**
     * 获得结报单号
     */
    public String getJbdh() {
        return jbdh;
    }

    /**
     * 获得证件类型代码
     */
    public String getZjlxdm() {
        return zjlxdm;
    }

    /**
     * 获得证件号码
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * 获得登记申报方式代码
     */
    public String getFsdm() {
        return fsdm;
    }

    /**
     * 获得国家标准行业代码
     */
    public String getGjbzhydm() {
        return gjbzhydm;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得创建人
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * 获得创建时间
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入时间
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得逾期天数
     */
    public BigDecimal getYqts() {
        return yqts;
    }

    /**
     * 获得滞纳金总额
     */
    public BigDecimal getZnjzje() {
        return znjzje;
    }

    /**
     * 获得房地产位置
     */
    public String getFdcwz() {
        return fdcwz;
    }

    /**
     * 获得契税约（合同）成立日期
     */
    public Timestamp getHtclrq() {
        return htclrq;
    }

    /**
     * 获得减税金额
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * 获得免税金额
     */
    public BigDecimal getMsjs() {
        return msjs;
    }

    public String getSwjgzzjgdm2() {
        return swjgzzjgdm2;
    }

    public String getSbbh() {
        return sbbh;
    }

    public ArrayList getMxList() {
        return mxList;
    }

    public String getDjzclxmc() {
        return djzclxmc;
    }

    public String getFsmc() {
        return fsmc;
    }

    public String getGjbzhymc() {
        return gjbzhymc;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public String getJsfsmc() {
        return jsfsmc;
    }

    public String getZjlxmc() {
        return zjlxmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }


    public String getZsddm() {
        return zsddm;
    }

    public String getZsdmc() {
        return zsdmc;
    }

    public String getNsrdm() {
        return nsrdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * 赋值完税证号
     * @param wszh 完税证号
     */
    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    /**
     * 赋值年度字别
     * @param ndzb 年度字别
     */
    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    /**
     * 赋值票证种类代码
     * @param pzzldm 票证种类代码
     */
    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * 赋值税务机关组织机构代码
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值登记注册类型代码
     * @param djzclxdm 登记注册类型代码
     */
    public void setDjzclxdm(String djzclxdm) {
        this.djzclxdm = djzclxdm;
    }

    /**
     * 赋值纳税人计算机代码
     * @param nsrdm 纳税人代码
     */
    public void setNsrjsjdm(String nsrjsjdm) {
        this.nsrjsjdm = nsrjsjdm;
    }

    /**
     * 赋值地址
     * @param dz 地址
     */
    public void setDz(String dz) {
        this.dz = dz;
    }

    /**
     * 赋值合计实缴税额
     * @param hjsjje 合计实缴税额
     */
    public void setHjsjje(BigDecimal hjsjje) {
        this.hjsjje = hjsjje;
    }

    /**
     * 赋值处理标记代码
     * @param clbjdm 处理标记代码
     */
    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    /**
     * 赋值申报汇总单号
     * @param sbhzdh 申报汇总单号
     */
    public void setSbhzdh(String sbhzdh) {
        this.sbhzdh = sbhzdh;
    }

    /**
     * 赋值结报单号
     * @param jbdh 结报单号
     */
    public void setJbdh(String jbdh) {
        this.jbdh = jbdh;
    }

    /**
     * 赋值证件类型代码
     * @param zjlxdm 证件类型代码
     */
    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    /**
     * 赋值证件号码
     * @param zjhm 证件号码
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * 赋值登记申报方式代码
     * @param fsdm 登记申报方式代码
     */
    public void setFsdm(String fsdm) {
        this.fsdm = fsdm;
    }

    /**
     * 赋值国家标准行业代码
     * @param gjbzhydm 国家标准行业代码
     */
    public void setGjbzhydm(String gjbzhydm) {
        this.gjbzhydm = gjbzhydm;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值创建人
     * @param cjr 创建人
     */
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    /**
     * 赋值创建时间
     * @param cjrq 创建时间
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入时间
     * @param lrrq 录入时间
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值逾期天数
     * @param yqts 逾期天数
     */
    public void setYqts(BigDecimal yqts) {
        this.yqts = yqts;
    }

    /**
     * 赋值滞纳金总额
     * @param znjzje 滞纳金总额
     */
    public void setZnjzje(BigDecimal znjzje) {
        this.znjzje = znjzje;
    }

    /**
     * 赋值房地产位置
     * @param fdcwz 房地产位置
     */
    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    /**
     * 赋值契税约（合同）成立日期
     * @param htclrq 契税约（合同）成立日期
     */
    public void setHtclrq(Timestamp htclrq) {
        this.htclrq = htclrq;
    }

    /**
     * 赋值减税金额
     * @param jsje 减税金额
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * 赋值免税金额
     * @param msjs 免税金额
     */
    public void setMsjs(BigDecimal msjs) {
        this.msjs = msjs;
    }

    public void setSwjgzzjgdm2(String swjgzzjgdm2) {
        this.swjgzzjgdm2 = swjgzzjgdm2;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setDjzclxmc(String djzclxmc) {
        this.djzclxmc = djzclxmc;
    }

    public void setFsmc(String fsmc) {
        this.fsmc = fsmc;
    }

    public void setGjbzhymc(String gjbzhymc) {
        this.gjbzhymc = gjbzhymc;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }

    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }


    public void setZsddm(String zsddm) {
        this.zsddm = zsddm;
    }

    public void setZsdmc(String zsdmc) {
        this.zsdmc = zsdmc;
    }

    public void setNsrdm(String nsrdm) {
        this.nsrdm = nsrdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

}
