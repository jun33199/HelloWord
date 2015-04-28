/*
 * <p>Title:北京地税核心征管系统--上门申报</p>
 * <p>Copyright:  (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司版权所有.</p>
 * <p>Company: 清华紫光股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: 银行扣款文件生成上传Form</p>
 * @author 开发六组－－诸光林
 * @version 1.0
 */
public class YhdkwjxzbcForm
    extends BaseForm {
  //区县代码
  private String qxdm;
  //年度
  private String nd;
  //月度
  private String yd;
  //征期
  private String zq;
  //年月
  private String ny;
  //年月列表
  private List nyList = new ArrayList();
  //已生成年月列表
  private String yscNY = "";
  //区县列表
  private List qxList = new ArrayList();
  //已获取文件名列表
  private String[] fileNames = null;
  //本次获取的文件数量
  private int fileCount = 0;
  //北京商行对应区县
  private String qxdmOfBccb = "";
  //农信社对应区县
  private String qxdmOfNxs = "";
  //创建人
  private String cjr = "";
  //创建日期
  private String cjrq = "";
  //封装的下载数据
  private HashMap ftpMap;
  //录入人
  private String lrr;
  //录入日期
  private String lrrq;
  //成功信息
  private String cgxx;

  public YhdkwjxzbcForm() {
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getZq() {
    return zq;
  }

  public void setZq(String zq) {
    this.zq = zq;
  }

  public String getNy() {
    return ny;
  }

  public void setNy(String ny) {
    this.ny = ny;
  }

  public String getYd() {
    return yd;
  }

  public void setYd(String yd) {
    this.yd = yd;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public String getCjr() {
    return cjr;
  }

  public String getCjrq() {
    return cjrq;
  }

  public HashMap getFtpMap() {
    return ftpMap;
  }

  public void setFtpMap(HashMap ftpMap) {
    this.ftpMap = ftpMap;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }

  public List getNyList() {
    return nyList;
  }

  public void setNyList(List nyList) {
    this.nyList = nyList;
  }

  public String getQxdmOfBccb() {
    return qxdmOfBccb;
  }

  public String getQxdmOfNxs() {
    return qxdmOfNxs;
  }

  public List getQxList() {
    return qxList;
  }

  public void setQxList(List qxList) {
    this.qxList = qxList;
  }

  public void setQxdmOfNxs(String qxdmOfNxs) {
    this.qxdmOfNxs = qxdmOfNxs;
  }

  public void setQxdmOfBccb(String qxdmOfBccb) {
    this.qxdmOfBccb = qxdmOfBccb;
  }

  public String getYscNY() {
    return yscNY;
  }

  public void setYscNY(String yscNY) {
    this.yscNY = yscNY;
  }

  public String[] getFileNames() {
    return fileNames;
  }

  public void setFileNames(String[] fileNames) {
    this.fileNames = fileNames;
  }

  public int getFileCount() {
    return fileCount;
  }

  public void setFileCount(int fileCount) {
    this.fileCount = fileCount;
  }

  public String getCgxx() {
    return cgxx;
  }

  public void setCgxx(String cgxx) {
    this.cgxx = cgxx;
  }

}