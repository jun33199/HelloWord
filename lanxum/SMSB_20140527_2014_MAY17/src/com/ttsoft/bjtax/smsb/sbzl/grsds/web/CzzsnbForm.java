/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 查帐征收个人独资企业和合伙企业投资者个人所得税年度申报表理 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class CzzsnbForm
    extends BaseForm {
  /**
   * 计算机代码
   */
  private String jsjdm;

  /**
   * 年度
   */
  private String nd;

  /**
   * 创建时间
   */
  private String cjsj;

  /**
   * 录入日期
   */
  private String lrrq;

  /**
   * 税款所属开始日期
   */
  private String skssksrq;

  /**
   * 税款所属结束日期
   */
  private String skssjsrq;

  /**
   * 税务机关组织机构代码
   */
  private String swjgzzjgdm;

  /**
   * 录入人
   */
  private String lrr;

  /**
   * 申报日期
   */
  private String sbrq;

  /**
   * 方式代码
   */
  private String fsdm;

  /**
   * 纳税人名称
   */
  private String nsrmc;

  /**
   * 经营地址联系电话
   */
  private String jydzlxdm;

  /**
   * 比率
   */
  private String[] bl = {};

  /**
   * 明细域名数组
   */
  private String[] qyColumns = {
      "qyhc", "qyxmmc", "qybqljs"};

  /**
   * 行次、本期累计数、证件类型代码、证件号码、投资者姓名、财务负责人
   */
  private String[] grColumns = {
      "grhc", "grbqljs", "zjlxdm", "zjhm", "tzzxm",
      "cwfzr"};

  /**
   * 企业申报数据列表
   */
  private List qyList = new ArrayList();

  /**
   * 企业分配比例列表
   */
  private List qyfpbl = new ArrayList();

  /**
   * 企业投资人列表
   */
  private HashMap grList = new HashMap();

  /**
   *  个人申报项目列表
   */
  private HashMap grsbxm = new HashMap();

  /**
   * 个人申报数据列表
   */
  private List grDataList = new ArrayList();

  /**
   * 征收方式
   */
  private String zsfs;

  /**
   * 是否来自综合申报
   */
  private String iszhsb;

  /**
   * 区县代码
   */
  private String qxdm;

  public String getQxdm() {
    return this.qxdm;
  }

  public void setQxdm(String _qxdm) {
    this.qxdm = _qxdm;
  }

  public String getIszhsb() {
    return this.iszhsb;
  }

  public void setIszhsb(String _iszhsb) {
    this.iszhsb = _iszhsb;
  }

  public String[] getBl() {
    return bl;
  }

  public void setBl(String[] bl) {
    this.bl = bl;
  }

  public List getQyList() {
    return qyList;
  }

  public void setQyList(List qyList) {
    this.qyList = qyList;
  }

  public List getGrDataList() {
    return this.grDataList;
  }

  public void setGrDataList(List _grDataList) {
    this.grDataList = _grDataList;
  }

  public HashMap getGrList() {
    return grList;
  }

  public void setGrList(HashMap grList) {
    this.grList = grList;
  }

  public List getQyfpbl() {
    return this.qyfpbl;
  }

  public void setQyfpbl(List _qyfpbl) {
    this.qyfpbl = _qyfpbl;
  }

  public HashMap getGrsbxm() {
    return this.grsbxm;
  }

  public void setGrsbxm(HashMap _grsbxm) {
    this.grsbxm = _grsbxm;
  }

  public String getJydzlxdm() {
    return this.jydzlxdm;
  }

  public void setJydzlxdm(String _jydzlxdm) {
    this.jydzlxdm = _jydzlxdm;
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

  public String getCjrq() {
    return cjsj;
  }

  public void setCjrq(String cjsj) {
    this.cjsj = cjsj;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
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

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String[] getQyColumns() {
    return qyColumns;
  }

  public String[] getGrColumns() {
    return grColumns;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
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
    cjsj = null;
    lrrq = null;
    swjgzzjgdm = null;
    lrr = null;
    fsdm = null;
    nsrmc = null;
    jydzlxdm = null;
    getGrList().clear();
    getQyList().clear();
    getGrDataList().clear();
    getGrsbxm().clear();
  }

  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
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
