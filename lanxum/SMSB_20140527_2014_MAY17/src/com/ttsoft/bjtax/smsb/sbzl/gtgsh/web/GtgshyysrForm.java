/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户营业所得</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrForm
    extends BaseForm {
  /**
   * 税务计算机代码 String
   *
   * 从录入数据中取得
   */
  private String jsjdm;

  /**
   * 创建时间 String
   */
  /**
   * 申报日期 String
   */
  private String sbrq;

  /**
   * 税务登记证号 String
   *
   * 从录入税务计算机代码查询数据中取得
   */
  private String swdjzh;

  /**
   * 纳税人名称 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String nsrmc;

  /**
   * 税务机关组织机构代码 String
   *
   * 从登录数据中取得
   */
  private String swjgzzjgdm;

  /**
   * 录入日期 String
   */
  /**
   * 登记申报方式代码 String
   *
   * 从登录数据中取得
   */
  private String fsdm;

  /**
   * 录入人代码 String
   *
   * 从登录数据中取得
   */
  private String lrrdm;

  /**
   * 包括：税种税目代码、税种税目名称、当期开具发票金额、当期未开具发票金额、当期营业收入金额合、计征营业收入
   * 明细信息标示 String[]
   */
  private String[] columns = {
      "szsmdm", "szsmmc", "dqkjfpje", "dqwkjfpje",
      "dqyysrjehj", "jzyysr"};

  /**
   * 用于存储明细中具体数值 List
   */
  private List dataList = new ArrayList();

  /**
   * 税款所属开始日期 String
   *
   * 系统根据申报日期自动计算
   */
  private String skssksrq;

  /**
   * 税款所属结束日期 String
   *
   * 系统根据申报日期自动计算
   */
  private String skssjsrq;

  /**
   * 申报所属年度
   *
   * 系统根据申报日期自动计算
   */
  private String nd;

  /**
   * 是否来自综合申报
   */
  private String iszhsb;

  /**
   * 区县代码
   */
  private String qxdm;

  /**
   * 创建日期
   */
  private String cjrq;

  /**
   *录入日期
   */
  private String lrrq;

  /**
   * 申报日期
   */
  private String tempSbrq;

  /**
   * 计算机代码
   */
  private String tempJsjdm;

  /**
   * 页面清除
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.jsjdm = null;
    this.nsrmc = null;
    this.actionType = "Show";
    this.swdjzh = null;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSwdjzh() {
    return swdjzh;
  }

  public void setSwdjzh(String swdjzh) {
    this.swdjzh = swdjzh;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getLrrdm() {
    return lrrdm;
  }

  public void setLrrdm(String lrrdm) {
    this.lrrdm = lrrdm;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
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

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getIszhsb() {
    return iszhsb;
  }

  public void setIszhsb(String iszhsb) {
    this.iszhsb = iszhsb;
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public String getTempSbrq() {
    return tempSbrq;
  }

  public void setTempSbrq(String tempSbrq) {
    this.tempSbrq = tempSbrq;
  }

  public String getTempJsjdm() {
    return tempJsjdm;
  }

  public void setTempJsjdm(String tempJsjdm) {
    this.tempJsjdm = tempJsjdm;
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
