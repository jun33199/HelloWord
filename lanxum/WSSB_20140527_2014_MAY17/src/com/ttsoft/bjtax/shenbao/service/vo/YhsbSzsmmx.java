package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title: ������˰�������������걨-˰��������-�����걨˰��˰Ŀ��ϸ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhsbSzsmmx
    implements Serializable {

  //˰��˰Ŀ����
  private String szsmdm = null;
  //˰��
  private BigDecimal sl = null;
  //��˰���
  private BigDecimal jsje = null;
  //��˰����
  private BigDecimal kssl;
  //Ӧ��˰��
  private BigDecimal ynse = null;
  //ʵ�ɽ��
  private BigDecimal sjje = null;

  public YhsbSzsmmx() {
  }

  public BigDecimal getJsje() {
    return jsje;
  }

  public java.math.BigDecimal getKssl() {
    return kssl;
  }

  public BigDecimal getSjje() {
    return sjje;
  }

  public BigDecimal getSl() {
    return sl;
  }

  public String getSzsmdm() {
    return szsmdm;
  }

  public BigDecimal getYnse() {
    return ynse;
  }

  public void setJsje(BigDecimal jsje) {
    this.jsje = jsje;
  }

  public void setKssl(java.math.BigDecimal kssl) {
    this.kssl = kssl;
  }

  public void setSjje(BigDecimal sjje) {
    this.sjje = sjje;
  }

  public void setSl(BigDecimal sl) {
    this.sl = sl;
  }

  public void setSzsmdm(String szsmdm) {
    this.szsmdm = szsmdm;
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

  public void printContent(){
    System.out.println("---[YhsbSzsmmx] {szsmdm:"+szsmdm+"|"+"sl:"+sl+"|"+"|"+"kssl:"+kssl+"|"+"jsje:"+jsje+"|"+"ynse:"+ynse+"|"+"sjje:"+sjje+"}");
  }

}