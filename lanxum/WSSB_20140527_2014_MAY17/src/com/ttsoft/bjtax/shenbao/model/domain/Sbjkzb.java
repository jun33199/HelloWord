package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.*;
import java.sql.*;

import com.ekernel.db.or.*;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 申报缴款主表</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */
public class Sbjkzb
    implements ORObject {

  //税票号码     SPHM	VARCHAR2(18)	FALSE	FALSE	FALSE
  String sphm;
//缴款凭证号	JKPZH	VARCHAR2(20)	TRUE	FALSE	TRUE
  String jkpzh;
//税款类型代码	SKLXDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String sklxdm;
//税款类型名称	SKLXMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String sklxmc;
//计算机代码	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
  String jsjdm;
//登记申报方式代码	FSDM	VARCHAR2(2)	FALSE	FALSE	TRUE
  String fsdm;
//隶属关系代码	LSGXDM	VARCHAR2(4)	FALSE	FALSE	FALSE
  String lsgxdm;
//隶属关系名称	LSGXMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String lsgxmc;
//银行代码	YHDM	VARCHAR2(12)	FALSE	FALSE	FALSE
  String yhdm;
//银行名称	YHMC	VARCHAR2(100)	FALSE	FALSE	FALSE
  String yhmc;
//帐号	ZH	VARCHARz   2(50)	FALSE	FALSE	FALSE
  String zh;
//登记注册类型代码	DJZCLXDM	VARCHAR2(6)	FALSE	FALSE	TRUE
  String djzclxdm;
//登记注册类型名称	DJZCLXMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String djzclxmc;
//税务机关组织机构代码	SWJGZZJGDM	VARCHAR2(8)	FALSE	FALSE	TRUE
  String swjgzzjgdm;
//税务机关组织机构名称	SWJGZZJGMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String swjgzzjgmc;
//征收机关代码	ZSSWJGZZJGDM	VARCHAR2(8)	FALSE	FALSE	TRUE
  String zsswjgzzjgdm;
//征收机关名称	ZSSWJGZZJGMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String zsswjgzzjgmc;
//国家标准行业代码	GJBZHYDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String gjbzhydm;
//国家标准行业名称	GJBZHYMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String gjbzhymc;
//国库组织机构代码	GKZZJGDM	VARCHAR2(10)	FALSE	FALSE	FALSE
  String gkzzjgdm;
//国库组织机构名称	GKZZJGMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String gkzzjgmc;
//预算科目代码	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
  String yskmdm;
//预算科目名称	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String yskmmc;
//预算级次代码	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String ysjcdm;
//预算级次名z  宄?YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String ysjcmc;
//税种代码	SZDM	VARCHAR2(9)	FALSE	FALSE	TRUE
  String szdm;
//税种名称	SZMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String szmc;
//录入日期	LRRQ	DATE	FALSE	FALSE	TRUE
  Timestamp lrrq;
//申报日期	SBRQ	DATE	FALSE	FALSE	TRUE
  Timestamp sbrq;
//银行收款时间	JKSJ	DATE	FALSE	FALSE	FALSE
  Timestamp jksj;
//限缴日期	XJRQ	DATE	FALSE	FALSE	FALSE
  Timestamp xjrq;
//处理标记代码	CLBJDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String clbjdm;
//实缴金额	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
  BigDecimal sjje;
//帐页日期	ZYRQ	DATE	FALSE	FALSE	FALSE
  Timestamp zyrq;
//入库金额	RKJE	NUMBER(15,2)	FALSE	FALSE	TRUE
  BigDecimal rkje;
//帐务标识	ZWBS	VARCHAR2(20)	FALSE	FALSE	TRUE
  String zwbs;
//核销人代码	HXRDM	VARCHAR2(20)	FALSE	FALSE	FALSE
  String hxrdm;
//核销人名称	HXRMC	VARCHAR2(30)	FALSE	FALSE	FALSE
  String hxrmc;
//录入人代码	LRR	VARCHAR2(30)	FALSE	FALSE	TRUE
  String lrr;
//备注	BZ	VARCHAR2(100)	FALSE	FALSE	FALSE
  String bz;
//创建时间	CJSJ	DATE	FALSE	FALSE	TRUE
  Timestamp cjrq;
//核销日期
  Timestamp hxrq;
//申报编号    SBBH	VARCHAR2(20)
  String sbbh;
  //经营地址联系电话
  String jydzlxdm;

  Timestamp skssksrq; // 税款所属开始日期

  Timestamp skssjsrq; // 税款所属结束日期
  String sjly; //数据来源

  //纳税人名称	NSRMC	VARCHAR2(200)	FALSE	FALSE	TRUE
  String nsrmc;
  String nd; //年度
  String qxdm;

  public String getBz() {
    return bz;
  }

  public Timestamp getCjrq() {
    return cjrq;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setCjrq(Timestamp cjrq) {
    this.cjrq = cjrq;
  }

  public void setClbjdm(String clbjdm) {
    this.clbjdm = clbjdm;
  }

  public void setDjzclxdm(String djzclxdm) {
    this.djzclxdm = djzclxdm;
  }

  public void setDjzclxmc(String djzclxmc) {
    this.djzclxmc = djzclxmc;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
  }

  public void setGjbzhymc(String gjbzhymc) {
    this.gjbzhymc = gjbzhymc;
  }

  public void setGkzzjgdm(String gkzzjgdm) {
    this.gkzzjgdm = gkzzjgdm;
  }

  public void setGkzzjgmc(String gkzzjgmc) {
    this.gkzzjgmc = gkzzjgmc;
  }

  public void setHxrdm(String hxrdm) {
    this.hxrdm = hxrdm;
  }

  public void setHxrmc(String hxrmc) {
    this.hxrmc = hxrmc;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

  public void setJksj(Timestamp jksj) {
    this.jksj = jksj;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setLrrq(Timestamp lrrq) {
    this.lrrq = lrrq;
  }

  public void setLsgxdm(String lsgxdm) {
    this.lsgxdm = lsgxdm;
  }

  public void setLsgxmc(String lsgxmc) {
    this.lsgxmc = lsgxmc;
  }

  public Timestamp getZyrq() {
    return zyrq;
  }

  public String getZwbs() {
    return zwbs;
  }

  public void setZwbs(String zwbs) {
    this.zwbs = zwbs;
  }

  public void setZyrq(Timestamp zyrq) {
    this.zyrq = zyrq;
  }

  public void setZsswjgzzjgmc(String zsswjgzzjgmc) {
    this.zsswjgzzjgmc = zsswjgzzjgmc;
  }

  public void setZsswjgzzjgdm(String zsswjgzzjgdm) {
    this.zsswjgzzjgdm = zsswjgzzjgdm;
  }

  public String getZsswjgzzjgdm() {
    return zsswjgzzjgdm;
  }

  public String getZsswjgzzjgmc() {
    return zsswjgzzjgmc;
  }

  public String getZh() {
    return zh;
  }

  public String getYskmmc() {
    return yskmmc;
  }

  public String getYskmdm() {
    return yskmdm;
  }

  public String getYsjcmc() {
    return ysjcmc;
  }

  public String getYsjcdm() {
    return ysjcdm;
  }

  public String getYhmc() {
    return yhmc;
  }

  public String getYhdm() {
    return yhdm;
  }

  public Timestamp getXjrq() {
    return xjrq;
  }

  public String getSzmc() {
    return szmc;
  }

  public String getSzdm() {
    return szdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public String getSklxmc() {
    return sklxmc;
  }

  public String getSklxdm() {
    return sklxdm;
  }

  public BigDecimal getSjje() {
    return sjje;
  }

  public void setSjje(BigDecimal sjje) {
    this.sjje = sjje;
  }

  public void setSklxdm(String sklxdm) {
    this.sklxdm = sklxdm;
  }

  public void setSklxmc(String sklxmc) {
    this.sklxmc = sklxmc;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public void setSzdm(String szdm) {
    this.szdm = szdm;
  }

  public void setSzmc(String szmc) {
    this.szmc = szmc;
  }

  public void setXjrq(Timestamp xjrq) {
    this.xjrq = xjrq;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public void setYhmc(String yhmc) {
    this.yhmc = yhmc;
  }

  public void setYsjcdm(String ysjcdm) {
    this.ysjcdm = ysjcdm;
  }

  public void setYsjcmc(String ysjcmc) {
    this.ysjcmc = ysjcmc;
  }

  public void setYskmdm(String yskmdm) {
    this.yskmdm = yskmdm;
  }

  public void setYskmmc(String yskmmc) {
    this.yskmmc = yskmmc;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public void setSbrq(Timestamp sbrq) {
    this.sbrq = sbrq;
  }

  public void setRkje(BigDecimal rkje) {
    this.rkje = rkje;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public Timestamp getSbrq() {
    return sbrq;
  }

  public BigDecimal getRkje() {
    return rkje;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public String getLsgxmc() {
    return lsgxmc;
  }

  public String getLsgxdm() {
    return lsgxdm;
  }

  public Timestamp getLrrq() {
    return lrrq;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public Timestamp getJksj() {
    return jksj;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public String getHxrmc() {
    return hxrmc;
  }

  public String getHxrdm() {
    return hxrdm;
  }

  public String getGkzzjgmc() {
    return gkzzjgmc;
  }

  public String getGkzzjgdm() {
    return gkzzjgdm;
  }

  public String getGjbzhymc() {
    return gjbzhymc;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public String getFsdm() {
    return fsdm;
  }

  public String getDjzclxmc() {
    return djzclxmc;
  }

  public String getDjzclxdm() {
    return djzclxdm;
  }

  public String getClbjdm() {
    return clbjdm;
  }

  //得到当前值对象的clone拷贝
  public Sbjkzb getCopy() {
    try {
      return (Sbjkzb)this.clone();
    }
    catch (Exception ex) {
      return null;
    }

  }

  public Timestamp getHxrq() {
    return hxrq;
  }

  public void setHxrq(Timestamp hxrq) {
    this.hxrq = hxrq;
  }

  public String getSbbh() {
    return sbbh;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public String getJydzlxdm() {
    return jydzlxdm;
  }

  public void setJydzlxdm(String jydzlxdm) {
    this.jydzlxdm = jydzlxdm;
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

  public String getSjly() {
    return sjly;
  }

  public void setSjly(String sjly) {
    this.sjly = sjly;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }
  public String getSphm() {
    return sphm;
  }
  public void setSphm(String sphm) {
    this.sphm = sphm;
  }
}