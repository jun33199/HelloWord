package com.ttsoft.bjtax.smsb.gghsb.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description: 定期定额户入库情况查询Form</p>
 * @author zhou jinguang
 * @version 1.0
 */

public class DqdehrkcxForm
    extends BaseForm {
  public DqdehrkcxForm() {
    dataList = new ArrayList();
  }

  /**
   * 明细项目集合
   */
  private java.util.ArrayList dataList;

  /**
   * 分页：每页纪录数
   */
  private int length;

  /**
   * 分页：当前页数
   */
  private int pgNum;

  /**
   * 分页：总页数
   */
  private int pgSum;

  /**
   * 计算机代码
   */
  private String jsjdm;

  /**
   * 纳税人状态
   */
  private String nsrzt;

  /**
   * 区县分局
   */
  private String qx;

  /**
   * 税务所
   */
  private String swjs;

  /**
   * 所处街乡
   */
  private String jx;

  /**
   * 税种
   */
  private String sz;

  /**
   * 认定年度
   */
  private String nd;

  /**
   * 纳税区间
   */
  private String nsqj;

  /**
 * 纳税区间list
 */
  private List nsqjList = null;

  /**
   * 登记注册类型
   */
  private String[] dkzclx;

  /**
   * 入库方式
   */
  private String rkfs;

  /**
   * 入库情况
   */
  private String rkqk;

  /**
   * 起始征期
   */
  private String fromzq;

  /**
   * 结束征期
   */
  private String endzq;

  /**
   * 街乡列表
   */
  private ArrayList jxList = null;

  /**
   * 税务所列表
   */
  private ArrayList swjsList = null;

  /**
   * 纳税人状态list
   */
  private List nsrztList = null;

  /**
   * 入库方式list
   */
  private List rkfsList = null;

  /**
   * 入库情况list
   */
  private List rkqkList = null;

  /**
   * 区县列表
   */
  private ArrayList qxList = null;

  /**核定金额合计*/
  private String hdjehj;
  /** 缴款金额合计 */
  private String jkjehj;
  /**记录数*/
  private String jls;

  /**入库查询信息中文名称 */
  private String[] dataListTitle;
  /**入库查询信息key */
  private String[] dataListKey;
  /**合计信息数据集*/
  private List sumList;
  /** 合计信息名称*/
  private String[] sumListTitle;
  /** 合计信息key*/
  private String[] sumListKey;

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    pgNum = 0;
    pgSum = 0;
    length = 0;
  }

  public java.util.ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(java.util.ArrayList dataList) {
    this.dataList = dataList;
  }

  public String[] getDkzclx() {
    return dkzclx;
  }

  public void setDkzclx(String[] dkzclx) {
    this.dkzclx = dkzclx;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getJx() {
    return jx;
  }

  public void setJx(String jx) {
    this.jx = jx;
  }

  public ArrayList getJxList() {
    return jxList;
  }

  public void setJxList(ArrayList jxList) {
    this.jxList = jxList;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getNd() {
    return nd;
   }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getNsrzt() {
    return nsrzt;
  }

  public void setNsrzt(String nsrzt) {
    this.nsrzt = nsrzt;
  }

  public int getPgNum() {
    return pgNum;
  }

  public void setPgNum(int pgNum) {
    this.pgNum = pgNum;
  }

  public int getPgSum() {
    return pgSum;
  }

  public void setPgSum(int pgSum) {
    this.pgSum = pgSum;
  }

  public String getQx() {
    return qx;
  }

  public void setQx(String qx) {
    this.qx = qx;
  }

  public String getRkfs() {
    return rkfs;
  }

  public void setRkfs(String rkfs) {
    this.rkfs = rkfs;
  }

  public String getSwjs() {
    return swjs;
  }

  public void setSwjs(String swjs) {
    this.swjs = swjs;
  }

  public ArrayList getSwjsList() {
    return swjsList;
  }

  public void setSwjsList(ArrayList swjsList) {
    this.swjsList = swjsList;
  }

  public String getSz() {
    return sz;
  }

  public void setSz(String sz) {
    this.sz = sz;
  }

  public String getEndzq() {
    return endzq;
  }

  public void setEndzq(String endzq) {
    this.endzq = endzq;
  }

  public String getFromzq() {
    return fromzq;
  }

  public void setFromzq(String fromzq) {
    this.fromzq = fromzq;
  }

  public List getNsrztList() {
    return nsrztList;
  }

  public void setNsrztList(List nsrztList) {
    this.nsrztList = nsrztList;
  }

  public List getRkfsList() {
    return rkfsList;
  }

  public void setRkfsList(List rkfsList) {
    this.rkfsList = rkfsList;
  }

  public String[] getDataListKey() {
    return dataListKey;
  }

  public void setDataListKey(String[] dataListKey) {
    this.dataListKey = dataListKey;
  }

  public String[] getDataListTitle() {
    return dataListTitle;
  }

  public void setDataListTitle(String[] dataListTitle) {
    this.dataListTitle = dataListTitle;
  }

  public String getHdjehj() {
    return hdjehj;
  }

  public void setHdjehj(String hdjehj) {
    this.hdjehj = hdjehj;
  }

  public String getJkjehj() {
    return jkjehj;
  }

  public void setJkjehj(String jkjehj) {
    this.jkjehj = jkjehj;
  }

  public String getJls() {
    return jls;
  }

  public void setJls(String jls) {
    this.jls = jls;
  }

  public List getSumList() {
    return sumList;
  }

  public void setSumList(List sumList) {
    this.sumList = sumList;
  }

  public String[] getSumListKey() {
    return sumListKey;
  }

  public void setSumListKey(String[] sumListKey) {
    this.sumListKey = sumListKey;
  }

  public String[] getSumListTitle() {
    return sumListTitle;
  }

  public void setSumListTitle(String[] sumListTitle) {
    this.sumListTitle = sumListTitle;
  }

  public ArrayList getQxList() {
    return qxList;
  }

  public void setQxList(ArrayList qxList) {
    this.qxList = qxList;
  }

  public List getRkqkList() {
    return rkqkList;
  }


  public void setRkqkList(List rkqkList) {
    this.rkqkList = rkqkList;
  }

//add by hsm


  public String getNsqj() {
    return nsqj;
  }

  public void setNsqj(String nsqj) {
    this.nsqj = nsqj;
  }


  public void setNsqjList(List nsqjList) {
    this.nsqjList = nsqjList;
  }

  public List getNsqjList() {
    return nsqjList;
  }

//hsm code finish

  public String getRkqk() {
    return rkqk;
  }

  public void setRkqk(String rkqk) {
    this.rkqk = rkqk;
  }
}