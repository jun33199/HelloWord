package com.ttsoft.bjtax.smsb.gghsb.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>Title: 北京地税核心征管系统-上门申报</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhkkhzxxLw
    implements Serializable {

  private String jkpzh;
  private String sphm;
  private String bizph;
  private String jsjdm;
  private String nsrmc;
  private String yhdm;
  private String zh;
  private String szsmdm;
  private String szsmmc;
  private BigDecimal sjrd;
  private BigDecimal sjje;
  private Timestamp skssksrq;
  private Timestamp skssjsrq;
  private String ysjcdm;
  private String ysjcmc;
  private String yskmdm;
  private String gkzzjgdm;
  private String gkzzjgmc;
  private String swjgzzjgdm;
  private String swjgzzjgmc;
  private String scjxdm;
  private String qxdm;
  private String nd;
  private String yd;
  private String jydzlxdm;
  private String gjbzhydm;
  private String djzclxdm;
  private String lsgxdm;
  private BigDecimal rkje;
  private BigDecimal sjfc;
  private BigDecimal qjfc;
  private String kkbz;
  private Timestamp kkrq;
  private String kkdwdm;
  private String bcgyydm;
  private String cjr;
  private Timestamp cjrq;
  private String lrr;
  private Timestamp lrrq;
  private String fszt;
  private String xyshm;

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException {
    ois.defaultReadObject();
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  public String getBcgyydm() {
    return bcgyydm;
  }

  public String getBizph() {
    return bizph;
  }

  public String getCjr() {
    return cjr;
  }

  public Timestamp getCjrq() {
    return cjrq;
  }

  public String getDjzclxdm() {
    return djzclxdm;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public String getGkzzjgdm() {
    return gkzzjgdm;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public String getGkzzjgmc() {
    return gkzzjgmc;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getJydzlxdm() {
    return jydzlxdm;
  }

  public String getKkbz() {
    return kkbz;
  }

  public String getKkdwdm() {
    return kkdwdm;
  }

  public Timestamp getKkrq() {
    return kkrq;
  }

  public String getLrr() {
    return lrr;
  }

  public Timestamp getLrrq() {
    return lrrq;
  }

  public String getLsgxdm() {
    return lsgxdm;
  }

  public String getNd() {
    return nd;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public BigDecimal getQjfc() {
    return qjfc;
  }

  public String getQxdm() {
    return qxdm;
  }

  public BigDecimal getRkje() {
    return rkje;
  }

  public String getScjxdm() {
    return scjxdm;
  }

  public BigDecimal getSjfc() {
    return sjfc;
  }

  public BigDecimal getSjje() {
    return sjje;
  }

  public BigDecimal getSjrd() {
    return sjrd;
  }

  public Timestamp getSkssjsrq() {
    return skssjsrq;
  }

  public Timestamp getSkssksrq() {
    return skssksrq;
  }

  public String getSphm() {
    return sphm;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public String getSzsmdm() {
    return szsmdm;
  }

  public String getSzsmmc() {
    return szsmmc;
  }

  public String getYd() {
    return yd;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getYsjcdm() {
    return ysjcdm;
  }

  public String getYsjcmc() {
    return ysjcmc;
  }

  public String getYskmdm() {
    return yskmdm;
  }

  public String getZh() {
    return zh;
  }

  public void setBcgyydm(String bcgyydm) {
    this.bcgyydm = bcgyydm;
  }

  public void setBizph(String bizph) {
    this.bizph = bizph;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }

  public void setCjrq(Timestamp cjrq) {
    this.cjrq = cjrq;
  }

  public void setDjzclxdm(String djzclxdm) {
    this.djzclxdm = djzclxdm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
  }

  public void setGkzzjgdm(String gkzzjgdm) {
    this.gkzzjgdm = gkzzjgdm;
  }

  public void setGkzzjgmc(String gkzzjgmc) {
    this.gkzzjgmc = gkzzjgmc;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setJydzlxdm(String jydzlxdm) {
    this.jydzlxdm = jydzlxdm;
  }

  public void setKkbz(String kkbz) {
    this.kkbz = kkbz;
  }

  public void setKkdwdm(String kkdwdm) {
    this.kkdwdm = kkdwdm;
  }

  public void setKkrq(Timestamp kkrq) {
    this.kkrq = kkrq;
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

  public void setNd(String nd) {
    this.nd = nd;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public void setQjfc(BigDecimal qjfc) {
    this.qjfc = qjfc;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public void setRkje(BigDecimal rkje) {
    this.rkje = rkje;
  }

  public void setScjxdm(String scjxdm) {
    this.scjxdm = scjxdm;
  }

  public void setSjfc(BigDecimal sjfc) {
    this.sjfc = sjfc;
  }

  public void setSjje(BigDecimal sjje) {
    this.sjje = sjje;
  }

  public void setSjrd(BigDecimal sjrd) {
    this.sjrd = sjrd;
  }

  public void setSkssjsrq(Timestamp skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public void setSkssksrq(Timestamp skssksrq) {
    this.skssksrq = skssksrq;
  }

  public void setSphm(String sphm) {
    this.sphm = sphm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public void setSzsmdm(String szsmdm) {
    this.szsmdm = szsmdm;
  }

  public void setSzsmmc(String szsmmc) {
    this.szsmmc = szsmmc;
  }

  public void setYd(String yd) {
    this.yd = yd;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
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

  public void setZh(String zh) {
    this.zh = zh;
  }

  public String getFszt() {
    return fszt;
  }

  public void setFszt(String fszt) {
    this.fszt = fszt;
  }

  public String getXyshm() {
    return xyshm;
  }

  public void setXyshm(String xyshm) {
    this.xyshm = xyshm;
  }

}