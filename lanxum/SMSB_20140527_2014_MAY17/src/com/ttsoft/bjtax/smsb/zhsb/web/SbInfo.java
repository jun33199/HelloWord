package com.ttsoft.bjtax.smsb.zhsb.web;

import java.io.*;

/**
 * <p>Title: ������˰��������ϵͳ-�����걨</p>
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

    //�걨��š��ɿ�ƾ֤�š��걨���ڡ�˰�����͡�˰�ִ��롢˰������ʱ�ڡ��걨����ҳ���ڡ������

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh;

  /**
   * ˰�ִ���
   */
  private String szdm;

  /**
   * ˰������
   */
  private String szmc;

  /**
   * ˰��������ʼ����
   */
  private String skssksrq;

  /**
   * ˰��������������
   */
  private String skssjsrq;

  /**
   * ��ҳ����
   */
  private String zyrq;

  /**
   * ʵ�ɽ��ϼ�
   */
  private String sjje;

  /**
   * �����ϼ�
   */
  private String rkje;

  /**
   * ���
   */
  private String ce;

  /**
   * ��ע
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
