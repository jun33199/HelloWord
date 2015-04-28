package com.ttsoft.bjtax.smsb.wynsk.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统</p>
 * <p>Description: 上门申报--无应纳税款申报查询</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class WynskcxActionForm
    extends BaseForm {

  /**
   * 数据列表容器
   */
  private List dataList = new ArrayList();

  /**
   * 税务所列表容器
   */
  private List swsList = new ArrayList();

  /**
   * 企业状态列表
   */
  private List qyztList = new ArrayList();

  /**
   * 录入人
   */
  private String headLrr;

  /**
   * 录入人级别
   */
  private String headLrrjb;

  /**
   * 录入人税务机关组织机构代码
   */
  private String headSwjgzzjgdm;

  /**
   * 税务机关组织机构名称
   */
  private String headSwjgzzjgmc;

  /**
   * 当前日期
   */
  private String headDqrq;

  /**
   * 查询税务所
   */
  private String querySwjgzzjg;

  /**
   * 查询企业状态
   */
  private String queryQyzt;

  /**
   * 查询对象计算机代码
   */
  private String queryJsjdm;

  /**
   * 查询申报日期起
   */
  private String querySbrqq;

  /**
   * 查询申报日期止
   */
  private String querySbrqz;

  /**
   * 页面显示尺寸
   */
  private String pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);

  /**
   * 页码
   */
  private String nextPage = "1";

  /**
   * 页数
   */
  private String totalpage = "0";

  /**
   * 页面提示信息
   */
  private String message;

  /**
   * 当前页码
   */
  private String curPage;

  //*******add by zhangshubing***********
   /**
    * 查询登记注册类型
    */
   private String queryDjzclx;
  /**
   * 申报类型列表
   */
  private List sblxList = new ArrayList();

  /**
   * 登记注册类型列表
   */
  private List djzclx = new ArrayList();

  /**
   * 登记注册类型没有被选列表
   */
  private List djzclx2 = new ArrayList();

  /**
   * 申报类型查询
   */
  private String querySblx;

  /**
   * 申报类型查询
   */
  private String queryDjzclx2;

  /**
   *包括所有被选登记注册类型代码
   */
  private String alldjzclx;
//*************************************

   public void reset(ActionMapping actionMapping,
                     HttpServletRequest httpServletRequest) {
     this.pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);
     this.nextPage = "1";
     this.totalpage = "0";
     this.message = "";
   }

  public List getDataList() {
    return dataList;
  }

  public String getHeadDqrq() {
    return headDqrq;
  }

  public String getHeadLrr() {
    return headLrr;
  }

  public String getHeadLrrjb() {
    return headLrrjb;
  }

  public String getHeadSwjgzzjgdm() {
    return headSwjgzzjgdm;
  }

  public String getHeadSwjgzzjgmc() {
    return headSwjgzzjgmc;
  }

  public String getTotalpage() {
    return totalpage;
  }

  public String getNextPage() {
    return nextPage;
  }

  public String getPageSize() {
    return pageSize;
  }

  public String getQueryJsjdm() {
    return queryJsjdm;
  }

  public String getQuerySbrqq() {
    return querySbrqq;
  }

  public String getQuerySbrqz() {
    return querySbrqz;
  }

  public List getSwsList() {
    return swsList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public void setHeadDqrq(String headDqrq) {
    this.headDqrq = headDqrq;
  }

  public void setHeadLrr(String headLrr) {
    this.headLrr = headLrr;
  }

  public void setHeadLrrjb(String headLrrjb) {
    this.headLrrjb = headLrrjb;
  }

  public void setHeadSwjgzzjgdm(String headSwjgzzjgdm) {
    this.headSwjgzzjgdm = headSwjgzzjgdm;
  }

  public void setHeadSwjgzzjgmc(String headSwjgzzjgmc) {
    this.headSwjgzzjgmc = headSwjgzzjgmc;
  }

  public void setTotalpage(String totalpage) {
    this.totalpage = totalpage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public void setQueryJsjdm(String queryJsjdm) {
    this.queryJsjdm = queryJsjdm;
  }

  public void setQuerySbrqq(String querySbrqq) {
    this.querySbrqq = querySbrqq;
  }

  public void setQuerySbrqz(String querySbrqz) {
    this.querySbrqz = querySbrqz;
  }

  public void setSwsList(List swsList) {
    this.swsList = swsList;
  }

  public List getQyztList() {
    return qyztList;
  }

  public void setQyztList(List qyztList) {
    this.qyztList = qyztList;
  }

  //********add by zhangshubing***********
   public void setSblxList(List sblxList) {
     this.sblxList = sblxList;
   }

  public void setDjzclx(List djzclx) {
    this.djzclx = djzclx;
  }

  public List getSblxList() {
    return sblxList;
  }

  public List getDjzclx() {
    return djzclx;
  }

  public String getQueryDjzclx() {
    return queryDjzclx;
  }

  public void setQueryDjzclx(String queryDjzclx) {
    this.queryDjzclx = queryDjzclx;
  }

  public String getQuerySblx() {
    return querySblx;
  }

  public void setQuerySblx(String querySblx) {
    this.querySblx = querySblx;
  }

  public void setAlldjzclx(String alldjzclx) {
    this.alldjzclx = alldjzclx;
  }

  public String getAlldjzclx() {
    return alldjzclx;
  }

  public void setDjzclx2(List djzclx2) {
    this.djzclx2 = djzclx2;
  }

  public List getDjzclx2() {
    return djzclx2;
  }

  public void setQueryDjzclx2(String queryDjzclx2){
    this.queryDjzclx2 = queryDjzclx2;
  }

  public String getQueryDjzclx2(){
    return queryDjzclx2;
  }


  //*************************************


  public String getQueryQyzt() {
    return queryQyzt;
  }

  public String getQuerySwjgzzjg() {
    return querySwjgzzjg;
  }

  public void setQueryQyzt(String queryQyzt) {
    this.queryQyzt = queryQyzt;
  }

  public void setQuerySwjgzzjg(String querySwjgzzjg) {
    this.querySwjgzzjg = querySwjgzzjg;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCurPage() {
    return curPage;
  }

  public void setCurPage(String curPage) {
    this.curPage = curPage;
  }

}