package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: 北京地税核心征管网上申报-税库行联网-银行申报基本信息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhsbInfo
    implements Serializable {

  //税款类型代码
  String sklxdm = null;

  //申报编号
  String sbbh = null;

  //计算机代码
  String jsjdm = null;
  //征收税务机关组织机构代码
  String zsswjgzzjgdm = null;
  //区县代码
  String qxdm = null;
  //银行代码
  String yhdm = null;
  //银行名称
  String yhmc = null;
  //帐号	ZH
  String zh = null;

  //申报日期
  Timestamp sbrq = null;
  //银行收款时间
  Timestamp jksj = null;
  //帐页日期
  Timestamp zyrq = null;
  //限缴日期
  Timestamp xjrq = null;

  //录入人代码
  String lrr = null;
  //备注
  String bz = null;

  //税种条数
  int szCount = -1;

  //税种明细,内部成员为com.ttsoft.bjtax.shenbao.service.vo.YhsbSzMx
  List szList = new ArrayList();

  public YhsbInfo() {
  }

  public String getBz() {
    return bz;
  }

  public Timestamp getJksj() {
    return jksj;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public String getQxdm() {
    return qxdm;
  }

  public String getSbbh() {
    return sbbh;
  }

  public Timestamp getSbrq() {
    return sbrq;
  }

  public String getSklxdm() {
    return sklxdm;
  }

  public int getSzCount() {
    return szCount;
  }

  public List getSzList() {
    return szList;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getYhmc() {
    return yhmc;
  }

  public String getZh() {
    return zh;
  }

  public String getZsswjgzzjgdm() {
    return zsswjgzzjgdm;
  }

  public Timestamp getZyrq() {
    return zyrq;
  }

  public void setBz(String bz) {
    this.bz = bz;
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

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public void setSbrq(Timestamp sbrq) {
    this.sbrq = sbrq;
  }

  public void setSklxdm(String sklxdm) {
    this.sklxdm = sklxdm;
  }

  public void setSzCount(int szCount) {
    this.szCount = szCount;
  }

  public void setSzList(List szList) {
    this.szList = szList;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public void setYhmc(String yhmc) {
    this.yhmc = yhmc;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

  public void setZsswjgzzjgdm(String zsswjgzzjgdm) {
    this.zsswjgzzjgdm = zsswjgzzjgdm;
  }

  public void setZyrq(Timestamp zyrq) {
    this.zyrq = zyrq;
  }

  public Timestamp getXjrq() {
    return xjrq;
  }

  public void setXjrq(Timestamp xjrq) {
    this.xjrq = xjrq;
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException {
    ois.defaultReadObject();
  }

  public void printContent() {
    System.out.println("[YhsbInfo] {sklxdm:" + sklxdm + "|"
                       + "jsjdm:" + jsjdm + "|"
                       + "sbbh:" + sbbh + "|"
                       + "zsswjgzzjgdm:" + zsswjgzzjgdm + "|"
                       + "qxdm:" + qxdm + "|"
                       + "yhdm:" + yhdm + "|"
                       + "yhmc:" + yhmc + "|"
                       + "zh:" + zh + "|"
                       + "sbrq:" + sbrq + "|"
                       + "jksj:" + jksj + "|"
                       + "zyrq:" + zyrq + "|"
                       + "xjrq:" + xjrq + "|"
                       + "lrr:" + lrr + "|"
                       + "bz:" + bz + "|"
                       + "}");
    for (int i = 0; i < this.szList.size(); i++) {
      ( (YhsbSzMx) szList.get(i)).printContent();
    }
  }

  public boolean checkValidation(){
    boolean flag=true;
    YhsbSzMx yhsbSzMx=null;
    if(this.szList.size()==0){
      System.out.println("====>"+this.sbbh+"没有对应的税种!");
      flag=false;
    }
    for (int i = 0; i < this.szList.size(); i++) {
      yhsbSzMx=(YhsbSzMx)szList.get(i);
      flag=flag&&yhsbSzMx.checkValidation();
    }
    return flag;
  }


}