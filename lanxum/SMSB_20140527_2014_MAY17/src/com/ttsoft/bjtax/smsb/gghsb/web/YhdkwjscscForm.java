/*
 * <p>Title:北京地税核心征管系统--上门申报</p>
 * <p>Copyright:  (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司版权所有.</p>
 * <p>Company: 清华紫光股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.framework.form.BaseForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: 银行扣款文件生成上传Form</p>
 * @author 开发六组－－诸光林
 * @version 1.0
 */
public class YhdkwjscscForm
    extends BaseForm {
  public YhdkwjscscForm() {
  }

  //年月
  private String ny = "";
  //年月列表
  private List nyList = new ArrayList();
  //已生成年月列表
  private String yscNY = "";
  //区县代码
  private String qxdm = "";
  //区县列表
  private List qxList = new ArrayList();
  //北京商行对应区县
  private String qxdmOfBccb = "";
  //农信社对应区县
  private String qxdmOfNxs = "";
  //创建人
  private String cjr = "";
  //创建日期
  private String cjrq = "";
  //录入人
  private String lrr = "";
  //录入日期
  private String lrrq = "";
  //封装的上传数据
  private HashMap ftpMap;
  //银行代码
  private String yhdm = "";
  //总户数
  private String hs = "";

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

  public String getCjr() {
    return cjr;
  }

  public String getLrr() {
    return lrr;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }

  public String getCjrq() {
    return cjrq;
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public List getQxList() {
    return qxList;
  }

  public void setQxList(List qxList) {
    this.qxList = qxList;
  }

  public List getNyList() {
    return nyList;
  }

  public void setNyList(List nyList) {
    this.nyList = nyList;
  }

  public String getNy() {
    return ny;
  }

  public void setNy(String ny) {
    this.ny = ny;
  }

  public HashMap getFtpMap() {
    return ftpMap;
  }

  public void setFtpMap(HashMap ftpMap) {
    this.ftpMap = ftpMap;
  }

  public String getYscNY() {
    return yscNY;
  }

  public void setYscNY(String yscNY) {
    this.yscNY = yscNY;
  }

  public String getQxdmOfBccb() {
    return qxdmOfBccb;
  }

  public String getQxdmOfNxs() {
    return qxdmOfNxs;
  }

  public void setQxdmOfBccb(String qxdmOfBccb) {
    this.qxdmOfBccb = qxdmOfBccb;
  }

  public void setQxdmOfNxs(String qxdmOfNxs) {
    this.qxdmOfNxs = qxdmOfNxs;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getHs() {
    return hs;
  }

  public void setHs(String hs) {
    this.hs = hs;
  }

}