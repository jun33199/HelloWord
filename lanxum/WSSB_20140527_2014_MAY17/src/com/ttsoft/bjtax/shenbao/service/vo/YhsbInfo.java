package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ������˰�������������걨-˰��������-�����걨������Ϣ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhsbInfo
    implements Serializable {

  //˰�����ʹ���
  String sklxdm = null;

  //�걨���
  String sbbh = null;

  //���������
  String jsjdm = null;
  //����˰�������֯��������
  String zsswjgzzjgdm = null;
  //���ش���
  String qxdm = null;
  //���д���
  String yhdm = null;
  //��������
  String yhmc = null;
  //�ʺ�	ZH
  String zh = null;

  //�걨����
  Timestamp sbrq = null;
  //�����տ�ʱ��
  Timestamp jksj = null;
  //��ҳ����
  Timestamp zyrq = null;
  //�޽�����
  Timestamp xjrq = null;

  //¼���˴���
  String lrr = null;
  //��ע
  String bz = null;

  //˰������
  int szCount = -1;

  //˰����ϸ,�ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YhsbSzMx
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
      System.out.println("====>"+this.sbbh+"û�ж�Ӧ��˰��!");
      flag=false;
    }
    for (int i = 0; i < this.szList.size(); i++) {
      yhsbSzMx=(YhsbSzMx)szList.get(i);
      flag=flag&&yhsbSzMx.checkValidation();
    }
    return flag;
  }


}