package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: 申报成功回执</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.1
 */

public class SBCGHZ
    implements Serializable {

  private String sbbh;

  private String jsjdm;

  private String nsrmc;

  private String yhdm;

  private String yhmc;

  private String zh;

  private String hjjedx;

  private String hjjexx;

  private String skje;

  private String znjje;

  private String swjgzzjgdm;

  private String swjgzzjgmc;

  private String lrr;

  private String bz;

  private List mxs = new ArrayList(); //{"szdm","szmc","sjje","xjrq"}

  public SBCGHZ() {
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException {
    ois.defaultReadObject();
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  public String getBz() {
    return bz;
  }

  public String getHjjedx() {
    return hjjedx;
  }

  public String getHjjexx() {
    return hjjexx;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public List getMxs() {
    return mxs;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public String getSbbh() {
    return sbbh;
  }

  public String getSkje() {
    return skje;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getYhmc() {
    return yhmc;
  }

  public String getZnjje() {
    return znjje;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setHjjedx(String hjjedx) {
    this.hjjedx = hjjedx;
  }

  public void setHjjexx(String hjjexx) {
    this.hjjexx = hjjexx;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setMxs(List mxs) {
    this.mxs = mxs;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public void setSkje(String skje) {
    this.skje = skje;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public void setYhmc(String yhmc) {
    this.yhmc = yhmc;
  }

  public void setZnjje(String znjje) {
    this.znjje = znjje;
  }

  public String getZh() {
    return zh;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

}