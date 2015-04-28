package com.ttsoft.bjtax.smsb.wrkcx;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class WrkVo
    implements Serializable {
  public WrkVo() {
  }

  /**
   * 查询结果序号
   */
  private int index;

  /**
   * 缴款凭证号	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
   */

  String jkpzh;
  /**
   * 计算机代码	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
   */

  String jsjdm;

  /**
   * 计算机代码	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
   */

  String nsrmc;

  /**
   * 预算科目名称	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
   */

  String yskmmc;
  /**
   * 预算级次代码	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
   */

  String szsmmc;

  /**
   * 实缴税额	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
   */

  String sjje;

  /**
   * 税款所属开始日期	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
   */

  String skssksrq;
  /**
   * 税款所属结束日期	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
   */

  String skssjsrq;

  String xjrq;
  String sbrq;

  String sklx;
  String zsswjg;
  String zwbs;
  String sjly;
  String clbj;
  String lrr;
  String hxrmc;

  String lrrq;
  String hxrq;
  String rkje;
  String zyrq;
  String yhsksj;
  /**
   * 所简称
   */
  String sjc;

  /**
   * 经营电话
   */
  String jydh;
  /**
   * 申报金额与入库金额的差额
   */
  String ce;

  /**
   * 税种名称        SZMC    VARCHAR2(6)
   */

  String szmc;

  public int getIndex() {
    return index;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getSzmc() {
    return szmc;
  }

  public String getSzsmmc() {
    return szsmmc;
  }

  public String getYskmmc() {
    return yskmmc;
  }

  public void setYskmmc(String yskmmc) {
    this.yskmmc = yskmmc;
  }

  public void setSzsmmc(String szsmmc) {
    this.szsmmc = szsmmc;
  }

  public void setSzmc(String szmc) {
    this.szmc = szmc;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getSjje() {
    return sjje;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSjje(String sjje) {
    this.sjje = sjje;
  }

  public void setSkssjsrq(String skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public void setSkssksrq(String skssksrq) {
    this.skssksrq = skssksrq;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSklx() {
    return sklx;
  }

  public void setSklx(String sklx) {
    this.sklx = sklx;
  }

  public String getZsswjg() {
    return zsswjg;
  }

  public void setZsswjg(String zsswjg) {
    this.zsswjg = zsswjg;
  }

  public String getZwbs() {
    return zwbs;
  }

  public void setZwbs(String zwbs) {
    this.zwbs = zwbs;
  }

  public String getXjrq() {
    return xjrq;
  }

  public void setXjrq(String xjrq) {
    this.xjrq = xjrq;
  }

  public String getSjly() {
    return sjly;
  }

  public void setSjly(String sjly) {
    this.sjly = sjly;
  }
  public String getClbj() {
    return clbj;
  }
  public void setClbj(String clbj) {
    this.clbj = clbj;
  }
  public String getHxrmc() {
    return hxrmc;
  }
  public void setHxrmc(String hxrmc) {
    this.hxrmc = hxrmc;
  }
  public String getHxrq() {
    return hxrq;
  }
  public void setHxrq(String hxrq) {
    this.hxrq = hxrq;
  }
  public String getLrr() {
    return lrr;
  }
  public void setLrr(String lrr) {
    this.lrr = lrr;
  }
  public String getLrrq() {
    return lrrq;
  }
  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }
  public String getRkje() {
    return rkje;
  }
  public void setRkje(String rkje) {
    this.rkje = rkje;
  }
  public String getZyrq() {
    return zyrq;
  }
  public void setZyrq(String zyrq) {
    this.zyrq = zyrq;
  }
  public String getYhsksj() {
    return yhsksj;
  }
  public void setYhsksj(String yhsksj) {
    this.yhsksj = yhsksj;
  }
  public String getJydh() {
    return jydh;
  }
  public void setJydh(String jydh) {
    this.jydh = jydh;
  }
  public String getCe() {
    return ce;
  }
  public void setCe(String ce) {
    this.ce = ce;
  }
  public String getSjc() {
    return sjc;
  }
  public void setSjc(String sjc) {
    this.sjc = sjc;
  }

}