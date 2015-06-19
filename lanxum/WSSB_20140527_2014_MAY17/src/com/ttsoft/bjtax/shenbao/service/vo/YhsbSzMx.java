package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ������˰�������������걨-˰��������-�����걨˰����ϸ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhsbSzMx
    implements Serializable {

  //˰�ִ���
  String szdm = null;
  //Ӧ��˰��
  BigDecimal ynse = null;
  //ʵ�ɽ��
  BigDecimal sjje = null;
  //˰��������ʼ����
  Timestamp skssksrq = null;
  //˰��������������
  Timestamp skssjsrq = null;
  //�޽�����
  Timestamp xjrq = null;

  //˰��˰Ŀ����
  int szsmCount = -1;

  //˰��˰Ŀ��ϸ,�ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YhsbSzsmMx
  List szsmList = new ArrayList();

  public YhsbSzMx() {
  }

  public static void main(String[] args) {
    YhsbSzMx mx = new YhsbSzMx();
  }

  public BigDecimal getSjje() {
    return sjje;
  }

  public Timestamp getSkssjsrq() {
    return skssjsrq;
  }

  public Timestamp getSkssksrq() {
    return skssksrq;
  }

  public String getSzdm() {
    return szdm;
  }

  public int getSzsmCount() {
    return szsmCount;
  }

  public List getSzsmList() {
    return szsmList;
  }

  public Timestamp getXjrq() {
    return xjrq;
  }

  public BigDecimal getYnse() {
    return ynse;
  }

  public void setSjje(BigDecimal sjje) {
    this.sjje = sjje;
  }

  public void setSkssjsrq(Timestamp skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public void setSkssksrq(Timestamp skssksrq) {
    this.skssksrq = skssksrq;
  }

  public void setSzdm(String szdm) {
    this.szdm = szdm;
  }

  public void setSzsmList(List szsmList) {
    this.szsmList = szsmList;
  }

  public void setSzsmCount(int szsmCount) {
    this.szsmCount = szsmCount;
  }

  public void setXjrq(Timestamp xjrq) {
    this.xjrq = xjrq;
  }

  public void setYnse(BigDecimal ynse) {
    this.ynse = ynse;
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException {
    ois.defaultReadObject();
  }

  public void printContent() {
    System.out.println("--[YhsbSzMx] {szdm:" + szdm + "|" + "ynse:" + ynse +
                       "|" + "sjje:" + sjje + "|" + "skssksrq:" + skssksrq +
                       "|" + "skssjsrq:" + skssjsrq + "|" + "xjrq:" + xjrq +
                       "}");
    for (int i = 0; i < this.szsmList.size(); i++) {
      ( (YhsbSzsmmx) szsmList.get(i)).printContent();
    }
  }

  public boolean checkValidation(){
    boolean flag=true;
    YhsbSzsmmx yhsbSzsmmx=null;
    if(this.szsmList.size()==0){
      System.out.println("====>"+szdm+"û�ж�Ӧ��˰��˰Ŀ!");
      flag=false;
    }
    for (int i = 0; i < this.szsmList.size(); i++) {
      yhsbSzsmmx=(YhsbSzsmmx)szsmList.get(i);
      if(!((yhsbSzsmmx.getSzsmdm().substring(0,2)).equals(this.szdm))){
        System.out.println("======>"+szdm+"��Ӧ��˰��˰Ŀ�Ƿ�!"+yhsbSzsmmx.getSzsmdm());
        flag=false;
      }
    }
    return flag;
  }

}