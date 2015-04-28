package com.ttsoft.bjtax.smsb.gghsb.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.io.*;

/**
 * <p>Title: 北京地税核心征管系统-上门申报</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class ZtLw
    implements Serializable {

  private String qxdm;
  private String nd;
  private String yd;
  private String sczt;
  private String yhfszt;
  private String yhkkresult;
  private BigDecimal zjls;
  private BigDecimal zje;
  private BigDecimal cwzjls;
  private BigDecimal cwzje;
  private String bz;
  private String cjr;
  private Timestamp cjrq;
  private String lrr;
  private Timestamp lrrq;

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

  public String getCjr() {
    return cjr;
  }

  public Timestamp getCjrq() {
    return cjrq;
  }

  public BigDecimal getCwzje() {
    return cwzje;
  }

  public BigDecimal getCwzjls() {
    return cwzjls;
  }

  public String getLrr() {
    return lrr;
  }

  public Timestamp getLrrq() {
    return lrrq;
  }

  public String getNd() {
    return nd;
  }

  public String getQxdm() {
    return qxdm;
  }

  public String getSczt() {
    return sczt;
  }

  public String getYd() {
    return yd;
  }

  public String getYhfszt() {
    return yhfszt;
  }

  public String getYhkkresult() {
    return yhkkresult;
  }

  public BigDecimal getZje() {
    return zje;
  }

  public BigDecimal getZjls() {
    return zjls;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }

  public void setCjrq(Timestamp cjrq) {
    this.cjrq = cjrq;
  }

  public void setCwzje(BigDecimal cwzje) {
    this.cwzje = cwzje;
  }

  public void setCwzjls(BigDecimal cwzjls) {
    this.cwzjls = cwzjls;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setLrrq(Timestamp lrrq) {
    this.lrrq = lrrq;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public void setSczt(String sczt) {
    this.sczt = sczt;
  }

  public void setYd(String yd) {
    this.yd = yd;
  }

  public void setYhfszt(String yhfszt) {
    this.yhfszt = yhfszt;
  }

  public void setYhkkresult(String yhkkresult) {
    this.yhkkresult = yhkkresult;
  }

  public void setZje(BigDecimal zje) {
    this.zje = zje;
  }

  public void setZjls(BigDecimal zjls) {
    this.zjls = zjls;
  }

}