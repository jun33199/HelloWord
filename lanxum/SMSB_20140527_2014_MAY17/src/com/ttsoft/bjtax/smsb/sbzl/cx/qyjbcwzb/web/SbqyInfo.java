package com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ--�����걨</p>
 * <p>Description: ��ҵ��Ϣ����</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class SbqyInfo
    implements Serializable {

  /**
   * ��ѯ������
   */
  private int index;

  /**
   * ���������
   */
  private String jsjdm;

  /**
   * ��ҵ����
   */
  private String nsrmc;

  /**
   * ��ҵ״̬����
   */
  private String qyztdm;

  /**
   * ��ҵ״̬����
   */
  private String qyztmc;

  /**
   * �Ǽ�ע�����ʹ���
   */
  private String djzclxdm;

  /**
   * �Ǽ�ע����������
   */
  private String djzclxmc;

  /**
   * ��ϵ�绰
   */
  private String lxdh;

  /**
   * ��ҵ��Ӫ��ַ
   */
  private String jydz;

  /**
   * ���캯��
   */
  public SbqyInfo() {
  }

  ////////////////////////getter and setter//////////////////////////////////
  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getQyztdm() {
    return qyztdm;
  }

  public void setQyztdm(String qyztdm) {
    this.qyztdm = qyztdm;
  }

  public String getDjzclxdm() {
    return djzclxdm;
  }

  public void setDjzclxdm(String djzclxdm) {
    this.djzclxdm = djzclxdm;
  }

  public String getDjzclxmc() {
    return djzclxmc;
  }

  public void setDjzclxmc(String djzclxmc) {
    this.djzclxmc = djzclxmc;
  }

  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  public String getQyztmc() {
    return qyztmc;
  }

  public void setQyztmc(String qyztmc) {
    this.qyztmc = qyztmc;
  }

  public String getJydz() {
    return jydz;
  }

  public void setJydz(String jydz) {
    this.jydz = jydz;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

}