package com.ttsoft.bjtax.smsb.zhsb.web;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>Title: ������˰��������ϵͳ-�����걨-�걨��Ϣ</p>
 *
 * <p>Description: �����걨�ϼ���Ϣ</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */
public class SingleSbInfo
    implements Serializable {

  /**
   * �걨���
   */
  private String sbbh;

  /**
   * �걨����
   */
  private String sbrq;

//added by zhangyj 20070719
  /**
   * �޽�����
   */
//end  
  
  private String xjrq;  

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
   * ˰������
   */
  private String sklx;

  /**
   * ��ע
   */
  private String bz;

  /**
   * �걨��ϸ���ݼ���,�ڴ���ϸΪSbjkzb.java
   */
  private ArrayList mxList = new ArrayList(); //�����걨��ϸ����

  public String getBz() {
    return bz;
  }

  public String getRkje() {
    return rkje;
  }

  public String getSbbh() {
    return sbbh;
  }

  public String getSbrq() {
    return sbrq;
  }

  public String getXjrq() {
    return xjrq;
  }  
  
  public String getSjje() {
    return sjje;
  }

  public ArrayList getMxList() {
    return mxList;
  }

  public String getCe() {
    return ce;
  }

  public String getSklx() {
    return sklx;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setRkje(String rkje) {
    this.rkje = rkje;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }
  
  public void setXjrq(String xjrq) {
	this.xjrq = xjrq;
  }  

  public void setSjje(String sjje) {
    this.sjje = sjje;
  }

  public void setMxList(ArrayList mxList) {
    this.mxList = mxList;
  }

  public void setCe(String ce) {
    this.ce = ce;
  }

  public void setSklx(String sklx) {
    this.sklx = sklx;
  }

}
