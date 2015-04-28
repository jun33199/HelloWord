/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户所得税</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsForm
    extends BaseForm {
  /**
   * 税务计算机代码 String
   *
   * 从录入数据中取得
   */
  private String jsjdm;

  /**
   * 申报日期 String
   */
  private String sbrq;

  /**
   * 纳税人名称 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String nsrmc;

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
   * 申报人姓名  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String xm;

  /**
   * 申报人地址  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String dz;

  /**
   * 申报人开始生产经营日期  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String scjyrq;

  /**
   * 申报人银行代码  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String yhdm;

  /**
   * 申报人账号  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String zh;

  /**
   * 申报人联系电话  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String lxdh;

  /**
   * 申报人邮编  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String qyyb;

  /**
   * 申报人税务机关组织机构代码  String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String swjgzzjgdm;

  /**
   * 录入人 String
   *
   * 根据登陆信息查找
   */
  private String lrr;

  /**
   * 登记申报方式代码 String
   *
   * 从登录数据中取得
   */
  private String fsdm;

  /**
   * 包括：行次、项目名称、金额
   * 明细信息标示 String[]
   */
  private String[] columns = {
      "hc", "xmmc", "je"};

  /**
   * 用于存储明细中具体数值 List
   */
  private List dataList = new ArrayList();

  /**
   * 纳税人身份证件号码 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String zjhm;

  /**
   * 纳税人证件类型 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String zjlxdm;

  /**
   * 申报年度 String
   *
   * 根据申报日期得到
   */
  private String nd;

  /**
   * 业别 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String gjbzhydm;

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
   * 录入日期
   */
  private String lrrq;

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {

    return null;
  }

  /**
   * 清除页面要素
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    super.reset(actionMapping, httpServletRequest);
    this.dataList.clear();
    this.nsrmc = null;
    this.xm = null;
    this.dz = null;
    this.jsjdm = null;
    this.actionType = "Show";
    this.zjlxdm = null;
    this.zjhm = null;
    this.scjyrq = null;
    this.zh = null;
    this.qyyb = null;
    this.lxdh = null;
    this.gjbzhydm = null;
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

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
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

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getDz() {
    return dz;
  }

  public void setDz(String dz) {
    this.dz = dz;
  }

  public String getScjyrq() {
    return scjyrq;
  }

  public void setScjyrq(String scjyrq) {
    this.scjyrq = scjyrq;
  }

  public String getYhdm() {
    return yhdm;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public String getZh() {
    return zh;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  public String getQyyb() {
    return qyyb;
  }

  public void setQyyb(String qyyb) {
    this.qyyb = qyyb;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String[] getColumns() {
    return columns;
  }

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public String getZjhm() {
    return zjhm;
  }

  public void setZjhm(String zjhm) {
    this.zjhm = zjhm;
  }

  public String getZjlxdm() {
    return zjlxdm;
  }

  public void setZjlxdm(String zjlxdm) {
    this.zjlxdm = zjlxdm;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
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
