package com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统--上门申报</p>
 * <p>Description: 企业信息对象</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class SbqyInfo
    implements Serializable {

  /**
   * 查询结果序号
   */
  private int index;

  /**
   * 计算机代码
   */
  private String jsjdm;

  /**
   * 企业名称
   */
  private String nsrmc;

  /**
   * 企业状态代码
   */
  private String qyztdm;

  /**
   * 企业状态名称
   */
  private String qyztmc;

  /**
   * 登记注册类型代码
   */
  private String djzclxdm;

  /**
   * 登记注册类型名称
   */
  private String djzclxmc;

  /**
   * 联系电话
   */
  private String lxdh;

  /**
   * 企业经营地址
   */
  private String jydz;

  /**
   * 构造函数
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