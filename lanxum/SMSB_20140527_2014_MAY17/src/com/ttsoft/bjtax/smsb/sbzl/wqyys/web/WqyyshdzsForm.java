/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.wqyys.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 北京市外国企业营业税纳税申报表－－核定征收－－ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class WqyyshdzsForm
    extends BaseForm {
  /**
   * 登记申报方式代码
   */
  private String fsdm;

  /**
   * 计算机代码
   */
  private String jsjdm;

  /**
   * 录入人代码
   */
  private String lrr;

  /**
   * 纳税人名称
   */
  private String nsrmc;

  /**
   * 申报日期
   */
  private String sbrq;

  /**
   * 税款所属结束日期
   */
  private String skssjsrq;

  /**
   * 税款所属开始日期
   */
  private String skssksrq;

  /**
   * 税务机关组织机构代码
   */
  private String swjgzzjgdm;

  /**
   * 税种代码
   */
  private String szdm;

  /**
   * 税种名称
   */
  private String szmc;

  /**
   * 征税方法代码
   */
  private String zsffdm;

  /**
   * 征税方法名称
   */
  private String zsffmc;

  /**
   * js数组
   */
  private String scriptStr;

  /**
   * 明细数据列表
   */
  private ArrayList dataList = new ArrayList();

  /**
   * 税种税目代码、税种税目名称、收入额、合同成交额、佣金率、核定收入额、经费支出额、换算收入额、计税金额、税率、应纳税额、已纳税额、本期应补税额
   */
  private String columns[] = {
      "szsmdm", "szsmmc", "sre", "htcje", "yjl",
      "hdsre", "jfzce", "hssre", "jsje", "sl", "ynse",
      "yinse", "bqybse"};

  /**
   * 征收方式
   */
  private String zsfs;

  /**
   * 是否有综合申报进入
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

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
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

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String skssksrq) {
    this.skssksrq = skssksrq;
  }

  public void setSzdm(String szdm) {
    this.szdm = szdm;
  }

  public String getSzdm() {
    return szdm;
  }

  public void setSzmc(String szmc) {
    this.szmc = szmc;
  }

  public String getSzmc() {
    return szmc;
  }

  public void setZsffdm(String zsffdm) {
    this.zsffdm = zsffdm;
  }

  public String getZsffdm() {
    return zsffdm;
  }

  public void setZsffmc(String zsffmc) {
    this.zsffmc = zsffmc;
  }

  public String getZsffmc() {
    return zsffmc;
  }

  public String getScriptStr() {
    return scriptStr;
  }

  public void setScriptStr(String scriptStr) {
    this.scriptStr = scriptStr;
  }

  public ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(ArrayList dataList) {
    this.dataList = dataList;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.nsrmc = null;
    this.jsjdm = null;

  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
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
