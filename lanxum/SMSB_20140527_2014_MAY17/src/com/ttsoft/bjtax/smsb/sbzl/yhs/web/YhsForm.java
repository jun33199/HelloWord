package com.ttsoft.bjtax.smsb.sbzl.yhs.web;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.ttsoft.framework.form.BaseForm;
import java.util.*;

/**
 * 印花税年度纳税申报　ActionForm
 * <p>Title: 北京地税局税务管理模块--上门申报</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: 清华同方</p>
 * @author 开发六组
 * @version 1.0
 */

public class YhsForm
    extends BaseForm {
  /**
   * 包括：税种税目代码、税种税目名称、课税数、计税金额、税率、实缴税额
   * 明细信息标示 String[]
   */
  private String columns[] = {
      "szsmdm", "szsmmc", "fs", "jsje", "sl", "sjse"};

  /**
   * 税务计算机代码 String
   *
   * 从录入数据中取得
   */
  private String jsjdm; //计算机代码

  /**
   * 申报年度 String
   */
  private String nd; //年度

  /**
   * 税款所属开始日期 String
   *
   * 系统根据申报日期自动计算
   */
  private String skssksrq; //申报所属开始所属日期

  /**
   * 税款所属结束日期 String
   *
   * 系统根据申报日期自动计算
   */
  private String skssjsrq; //申报所属结束所属日期

  /**
   * 纳税人名称 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String nsrmc; //纳税人名称

  /**
   * 税务机关组织机构名称 String
   *
   * 从登录数据中取得
   */
  private String swjgzzjgmc; //税务机关组织机构名称

  /**
   * 录入人代码 String
   *
   * 从登录数据中取得
   */
  private String lrr; //录入人

  /**
   * 申报日期 String
   */
  private String sbrq; //申报日期

  /**
   * 备注 String
   */
  private String bz; //备注

  private String swjgzzjgdm2; //税务机关组织机构代码

  /**
   * 税务机关组织机构代码 String
   *
   * 从登录数据中取得
   */
  private String swjgzzjgdm; //税务机关组织机构代码

  /**
   * 企业联系电话 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String qylxdh; //企业联系电话？ 从登记得到 保存时需要填写到明细表中

  /**
   * 合计应纳税额 Double
   */
  private Double hjynse; //合计应纳税额

  /**
   * 合计计税金额 Double
   */
  private Double hjjsje; //合计计税金额

  /**
   * 合计份数 String
   */
  private String hjfs; //合计份数

  /**
   * 登记申报方式代码 String
   *
   * 从登录数据中取得
   */
  private String fsdm; //登记申报方式代码

  /**
   * 用于存储明细中具体数值 List
   */
  private List dataList = new ArrayList();

  /**
   * 综合申报标志
   */
  private String iszhsb;

  /**
   * 创建日期
   */
  private String cjrq;

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String skssksrq) {
    this.skssksrq = skssksrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getQylxdh() {
    return qylxdh;
  }

  public void setQylxdh(String qylxdh) {
    this.qylxdh = qylxdh;
  }

  public String getHjfs() {
    return hjfs;
  }

  public void setHjfs(String hjfs) {
    this.hjfs = hjfs;
  }

  public Double getHjjsje() {
    return hjjsje;
  }

  public void setHjjsje(Double hjjsje) {
    this.hjjsje = hjjsje;
  }

  public Double getHjynse() {
    return hjynse;
  }

  public void setHjynse(Double hjynse) {
    this.hjynse = hjynse;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public String getSwjgzzjgdm2() {
    return swjgzzjgdm2;
  }

  public void setSwjgzzjgdm2(String swjgzzjgdm2) {
    this.swjgzzjgdm2 = swjgzzjgdm2;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  /**
   * 页面要素清除
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {

    jsjdm = null;
    nd = null;
    swjgzzjgdm = null;
    lrr = null;
    fsdm = null;
    nsrmc = null;
    qylxdh = null;
  }

  public String getIszhsb() {
    return iszhsb;
  }

  public void setIszhsb(String iszhsb) {
    this.iszhsb = iszhsb;
  }

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  /* start added by huxiaofeng 2005.8.16*/
  /**
   * 纳税人状态
   */
  private String nsrzt; //纳税人状态

  public void setNsrzt(String nsrzt) {
    this.nsrzt = nsrzt;
  }

  public String getNsrzt() {
    return nsrzt;
  }

  /* end added by huxiaofeng 2005.8.16*/

}
