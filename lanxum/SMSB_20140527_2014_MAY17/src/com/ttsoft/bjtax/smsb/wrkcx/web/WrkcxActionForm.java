package com.ttsoft.bjtax.smsb.wrkcx.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.*;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class WrkcxActionForm
    extends BaseForm
    implements Serializable {
  public WrkcxActionForm() {
  }

  /**
   * 明细项目集合
   */
  private java.util.ArrayList dataList;

  /**
   * 分局列表容器
   */
  private List fjList = new ArrayList();

  /**
   * 税务所列表容器
   */
  private List swsList = new ArrayList();
  /** 街乡列表*/
  private ArrayList jxList = null;
  /**
   * 查询对象计算机代码
   */
  private String queryjx;

  /**
   * 查询对象计算机代码
   */
  private String jsjdm;

  /**
   * 查询对象计算机代码
   */
  private String yhjb;

  /**
   * 查询申报日期起
   */
  private String sbrqq;

  /**
   * 查询申报日期止
   */
  private String sbrqz;

  /**
   * 税款类型代码
   */
  private String[] sklxdm;

  /**
   * 缴款凭证号
   */
  private String jkpzh;

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
   private String[] queryDjzclx;

  /**
   * 查询登记注册类型2
   */
  private String queryDjzclx2;

  /**
   *包括所有被选登记注册类型代码
   */
  private String alldjzclx;

  /**
   * 登记注册类型列表
   */
  private List djzclx = new ArrayList();

  /**
   * 登记注册类型没有被选列表
   */
  private List djzclx2 = new ArrayList();

  /**
   * 查询分局
   */
  private String queryfj;

  /**
   * 查询税务所
   */
  private String querysws;
  /**
   * 限缴日期
   */
  private String xjrq;
  /**
   * 总笔数
   */
  private String bs;
  /**
   * 申报金额合计
   */
  private String sbjehj;
  /**
   * 入库金额合计
   */
  private String rkjehj;
  /**
   * 差额合计
   */
  private String cehj;
  /**
   * 查询详细信息的计算机代码
   */
  private String dj_jsjdm;
  /**
   * 主管税务机关代码
   */
  private String swjgdm;

  /**
   * 主管税务所代码
   */
  private String swsdm;

  Map zgswjg;
  Map zgsws;
  Map scjx;
  String hs;
  private List sklxlist = new ArrayList();
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);
    this.nextPage = "1";
    this.totalpage = "0";
    this.message = "";
  }

  public java.util.ArrayList getDataList() {
    return dataList;
  }

  /**
   * 设置数据集
   * @param dataList
   */
  public void setDataList(java.util.ArrayList dataList) {
    this.dataList = dataList;
  }

  public List getDjzclx2() {
    return djzclx2;
  }

  public void setDjzclx2(List djzclx2) {
    this.djzclx2 = djzclx2;
  }

  public String getCurPage() {
    return curPage;
  }

  public void setCurPage(String curPage) {
    this.curPage = curPage;
  }

  public List getFjList() {
    return fjList;
  }

  public void setFjList(List fjList) {
    this.fjList = fjList;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getNextPage() {
    return nextPage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  public String getPageSize() {
    return pageSize;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public String[] getQueryDjzclx() {
    return queryDjzclx;
  }

  public void setQueryDjzclx(String[] queryDjzclx) {
    this.queryDjzclx = queryDjzclx;
  }

  public String getSbrqq() {
    return sbrqq;
  }

  public String getSbrqz() {
    return sbrqz;
  }

  public void setSbrqz(String sbrqz) {
    this.sbrqz = sbrqz;
  }

  public void setSbrqq(String sbrqq) {
    this.sbrqq = sbrqq;
  }

  public String[] getSklxdm() {
    return sklxdm;
  }

  public void setSklxdm(String[] sklxdm) {
    this.sklxdm = sklxdm;
  }

  public List getSwsList() {
    return swsList;
  }

  public void setSwsList(List swsList) {
    this.swsList = swsList;
  }

  public String getTotalpage() {
    return totalpage;
  }

  public String getYhjb() {
    return yhjb;
  }

  public void setYhjb(String yhjb) {
    this.yhjb = yhjb;
  }

  public void setTotalpage(String totalpage) {
    this.totalpage = totalpage;
  }

  public List getDjzclx() {
    return djzclx;
  }

  public void setDjzclx(List djzclx) {
    this.djzclx = djzclx;
  }

  public String getQuerysws() {
    return querysws;
  }

  public String getQueryfj() {
    return queryfj;
  }

  public void setQueryfj(String queryfj) {
    this.queryfj = queryfj;
  }

  public void setQuerysws(String querysws) {
    this.querysws = querysws;
  }

  public String getQueryDjzclx2() {
    return queryDjzclx2;
  }

  public void setQueryDjzclx2(String queryDjzclx2) {
    this.queryDjzclx2 = queryDjzclx2;
  }

  public String getAlldjzclx() {
    return alldjzclx;
  }

  public void setAlldjzclx(String alldjzclx) {
    this.alldjzclx = alldjzclx;
  }

  public ArrayList getJxList() {
    return jxList;
  }

  public void setJxList(ArrayList jxList) {
    this.jxList = jxList;
  }

  public String getQueryjx() {
    return queryjx;
  }

  public void setQueryjx(String queryjx) {
    this.queryjx = queryjx;
  }

  public String getXjrq() {
    return xjrq;
  }

  public void setXjrq(String xjrq) {
    this.xjrq = xjrq;
  }

  public String getBs() {
    return bs;
  }

  public String getCehj() {
    return cehj;
  }

  public void setBs(String bs) {
    this.bs = bs;
  }

  public void setCehj(String cehj) {
    this.cehj = cehj;
  }

  public String getRkjehj() {
    return rkjehj;
  }

  public String getSbjehj() {
    return sbjehj;
  }

  public void setRkjehj(String rkjehj) {
    this.rkjehj = rkjehj;
  }

  public void setSbjehj(String sbjehj) {
    this.sbjehj = sbjehj;
  }

  public List getSklxlist() {
    return sklxlist;
  }

  public void setSklxlist(List sklxlist) {
    this.sklxlist = sklxlist;
  }

  public String getDj_jsjdm() {
    return dj_jsjdm;
  }

  public void setDj_jsjdm(String dj_jsjdm) {
    this.dj_jsjdm = dj_jsjdm;
  }

  public Map getZgswjg() {
    return zgswjg;
  }

  public Map getZgsws() {
    return zgsws;
  }

  public void setZgswjg(Map zgswjg) {
    this.zgswjg = zgswjg;
  }

  public void setZgsws(Map zgsws) {
    this.zgsws = zgsws;
  }
  public String getSwjgdm() {
    return swjgdm;
  }
  public String getSwsdm() {
    return swsdm;
  }
  public void setSwjgdm(String swjgdm) {
    this.swjgdm = swjgdm;
  }
  public void setSwsdm(String swsdm) {
    this.swsdm = swsdm;
  }
  public Map getScjx() {
    return scjx;
  }
  public void setScjx(Map scjx) {
    this.scjx = scjx;
  }
  public String getHs() {
    return hs;
  }
  public void setHs(String hs) {
    this.hs = hs;
  }

}