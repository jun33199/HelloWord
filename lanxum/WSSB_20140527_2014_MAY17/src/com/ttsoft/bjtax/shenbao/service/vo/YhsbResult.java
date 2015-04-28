package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: 银行申报处理返回结果对象</p>
 * <p>Description: 用于为税库行接口处理的结果！</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhsbResult
    implements Serializable {

  /**
   * 处理结果代码，8位字符组成，1、2位为处理结果，3、4位为业务接口编码，5、6、7、8为详细信息代码
   * 暂定1、2位，例如，00xxxxxx表示业务完全成功，99xxxxxx表示业务完全失败
   * 暂定3、4位,暂时不使用，
   * 暂定5、6、7、8位 表示返回信息，例如，xxxx0000表示业务完全成功，xxxx9999表示业务完全失败
   */
  private String resultCode = null;

  /**
   * 处理结果文字描述
   */
  private String resultDescription = null;

  /**
   * 业务计算机代码
   */
  private String jsjdm = null;

  /**
   * 税务机关组织机构代码
   */
  private String swjgzzjgdm=null;

  /**
   * 区县代码
   */
  private String qxdm = null;

  /**
   * 返回值对象,例如,对于有申报银行缴款和无申报银行缴款该对象为List,对于扣款结果处理则该对象为空
   */
  private Object rnObject = null;

  /**
   * 返回值对象的Class名,用于强制类型转换
   */
  private String rnObjectClassName = null;

  //
  public String printContent(){
    StringBuffer sb=new StringBuffer();
    sb.append("YhsbResult[");
    sb.append("resultCode:");
    sb.append(resultCode);
    sb.append("|resultDescription:");
    sb.append(resultDescription);
    sb.append("|jsjdm:");
    sb.append(jsjdm);
    sb.append("|swjgzzjgdm:");
    sb.append(swjgzzjgdm);
    sb.append("|qxdm:");
    sb.append(qxdm);
    sb.append("|rnObjectClassName:");
    sb.append(rnObjectClassName);
    sb.append("]");
    String content=sb.toString();
    System.out.println(content);
    return content;
  }

  //
  public boolean checkVaild(){
    return true;
  }

  //////////////////////////////////////////////////////////////////////////////
  ////////////         properties's getter and setter         //////////////////
  //////////////////////////////////////////////////////////////////////////////
  public String getJsjdm() {
    return jsjdm;
  }

  public String getQxdm() {
    return qxdm;
  }

  public String getResultCode() {
    return resultCode;
  }

  public String getResultDescription() {
    return resultDescription;
  }

  public Object getRnObject() {
    return rnObject;
  }

  public String getRnObjectClassName() {
    return rnObjectClassName;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public void setResultDescription(String resultDescription) {
    this.resultDescription = resultDescription;
  }

  public void setRnObject(Object rnObject) {
    this.rnObject = rnObject;
  }

  public void setRnObjectClassName(String rnObjectClassName) {
    this.rnObjectClassName = rnObjectClassName;
  }
  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }
  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

}