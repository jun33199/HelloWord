package com.ttsoft.bjtax.smsb.zhsb.web;

import java.io.*;

/**
 * <p>Title: 北京地税核心征管系统-上门申报</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */
public class SbInfo
    implements Serializable {

    //申报表号、缴款凭证号、申报日期、税款类型、税种代码、税款所属时期、申报金额、帐页日期、入库金额

    /**
     * 缴款凭证号
     */
    private String jkpzh;

  /**
   * 税种代码
   */
  private String szdm;

  /**
   * 税种名称
   */
  private String szmc;

  /**
   * 税款所属开始日期
   */
  private String skssksrq;

  /**
   * 税款所属结束日期
   */
  private String skssjsrq;

  /**
   * 帐页日期
   */
  private String zyrq;

  /**
   * 实缴金额合计
   */
  private String sjje;

  /**
   * 入库金额合计
   */
  private String rkje;

  /**
   * 差额
   */
  private String ce;

  /**
   * 备注
   */
  private String bz;

  private void readObject(ObjectInputStream ois) throws IOException,
      ClassNotFoundException {
    ois.defaultReadObject();
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  public String getBz() {
    return bz;
  }

  public String getCe() {
    return ce;
  }

  public String getRkje() {
    return rkje;
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

  public String getSzdm() {
    return szdm;
  }

  public String getSzmc() {
    return szmc;
  }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getZyrq() {
        return zyrq;
    }

    public void setBz(String bz) {
    this.bz = bz;
  }

  public void setCe(String ce) {
    this.ce = ce;
  }

  public void setRkje(String rkje) {
    this.rkje = rkje;
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

  public void setSzdm(String szdm) {
    this.szdm = szdm;
  }

  public void setSzmc(String szmc) {
    this.szmc = szmc;
  }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setZyrq(String zyrq) {
        this.zyrq = zyrq;
    }

}
