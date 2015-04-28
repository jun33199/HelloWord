package com.ttsoft.bjtax.smsb.zhsb.web;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>Title: 北京地税核心征管系统-上门申报-申报信息</p>
 *
 * <p>Description: 单笔申报合计信息</p>
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
   * 申报编号
   */
  private String sbbh;

  /**
   * 申报日期
   */
  private String sbrq;

//added by zhangyj 20070719
  /**
   * 限缴日期
   */
//end  
  
  private String xjrq;  

  /**
   * 实缴金额合计
   */
  private String sjje;

  /**
   * 入库金额合计
   */
  private String rkje;

  /**
   * 差额
   */
  private String ce;

  /**
   * 税款类型
   */
  private String sklx;

  /**
   * 备注
   */
  private String bz;

  /**
   * 申报明细数据集合,内存明细为Sbjkzb.java
   */
  private ArrayList mxList = new ArrayList(); //单笔申报明细对象

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
