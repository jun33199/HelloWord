/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

//import java.math.BigDecimal;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税季报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbForm
    extends BaseForm {
  /**
   * 创建时间 String
   */
  /**
   * 登记申报方式代码 String
   *
   * 从登录数据中取得
   */
  private String fsdm;

  /**
   * 申报所在的季度 String
   */
  private String jd;

  /**
   * 税务计算机代码 String
   *
   * 从录入数据中取得
   */
  private String jsjdm;

  /**
   * 录入人代码 String
   *
   * 从登录数据中取得
   */
  private String lrr;

  /**
   * 录入日期 String
   */
  /**
   * 申报年度 String
   */
  private String nd;

  /**
   * 申报日期 String
   */
  private String sbrq;

  /**
   * 税款所属结束日期 String
   *
   * 系统根据申报日期自动计算
   */
  private String skssjsrq;

  /**
   * 税款所属开始日期 String
   *
   * 系统根据申报日期自动计算
   */
  private String skssksrq;

  /**
   * 税务机关组织机构代码 String
   *
   * 从登录数据中取得
   */
  private String swjgzzjgdm;

  /**
   * 包括：收入总额、利润总额、适用税率、应纳所得税额、期初未缴所得税额、减免所得税额、查补以前年度税额、实际已缴纳所得税额、本期申报延交所得税额、实际应补（退）所得税额
   * 明细信息标示 String[]
   */
  private String columns[] = {
      "srze", "lrze", "sl", "ynsdse", "qcwjsdse",
      "jmsdse", "cbyqndse", "sjyjsdsse", "bqyjsdse",
      "sjybsdse", "bkhlrze", "mbyqndks"};

  /**
   * 用于存储明细中具体数值 List
   */
  private List dataList = new ArrayList();

  /**
   * 纳税人名称 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String nsrmc;

  /**
   * 缴款评证号 String
   *
   * 根据输入得到
   */
  private String jkpzh;

  //private String jmzg;
  /**
   * 征收方式 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String zsfs;

  /**
   * 高新企业税率 String
   *
   * 根据高新企业条件查询得到
   */
  private String zssl;

  /**
   * 纯益率 String
   *
   * 根据税务计算机代码查询得到
   */
  private String cyl;

  /**
   * 定额 String
   *
   * 根据税务计算机代码查询得到
   */
  private String de;

  /**
   * 综合申报入口
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

  /**
   * 优惠比率（集体企业下的乡镇企业为0.9其余1）
   */
  private String yhbl;

  /**
   * 一般减免税额
   */
  private String jmse;

  /**
   * 乡镇企业减免
   */
  private String xzqyjm;

  /**
   * 弥补以前年度亏损
   */
  private String mbyqndks;

  /**
   * 补亏后利润总额
   */
  private String bkhlrze;

  /**
   * 提示纳税人的税费认定结果
   */
  private String promptStr = "";

  /**
   * 一般减免税率
   */
  private String jmsl;

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

  public String getJd() {
    return jd;
  }

  public void setJd(String jd) {
    this.jd = jd;
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

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
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

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  /**
   *
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   * @return null
   */
  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {

    return null;
  }

  /**
   * 页面要素清除
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.actionType = "Show";
    this.jsjdm = null; //计算机代码
    this.swjgzzjgdm = null; //税务机关组织机构代码
    this.nsrmc = null; //纳税人名称
    this.zssl = null;
    this.nd = null;
    this.jd = null;
    //this.jmzg=null;
    this.jkpzh = null;
    this.cyl = null;
    this.zsfs = null;
    this.zssl = null;
    this.de = null;
    this.promptStr = "";
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public java.util.List getDataList() {
    return dataList;
  }

  public void setDataList(java.util.List dataList) {
    this.dataList = dataList;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

//  public String getJmzg() {
//    return jmzg;
//  }
//  public void setJmzg(String jmzg) {
//    this.jmzg = jmzg;
//  }
  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
  }

  public String getZssl() {
    return zssl;
  }

  public void setZssl(String zssl) {
    this.zssl = zssl;
  }

  public String getCyl() {
    return cyl;
  }

  public void setCyl(String cyl) {
    this.cyl = cyl;
  }

  public String getDe() {
    return de;
  }

  public void setDe(String de) {
    this.de = de;
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

  public String getYhbl() {
    return yhbl;
  }

  public void setYhbl(String yhbl) {
    this.yhbl = yhbl;
  }

  public String getJmse() {
    return jmse;
  }

  public void setJmse(String jmse) {
    this.jmse = jmse;
  }

  public String getXzqyjm() {
    return xzqyjm;
  }

  public void setXzqyjm(String xzqyjm) {
    this.xzqyjm = xzqyjm;
  }

  public String getMbyqndks() {
    return mbyqndks;
  }

  public void setMbyqndks(String mbyqndks) {
    this.mbyqndks = mbyqndks;
  }

  public String getBkhlrze() {
    return bkhlrze;
  }

  public void setBkhlrze(String bkhlrze) {
    this.bkhlrze = bkhlrze;
  }

  public String getPromptStr() {
    return promptStr;
  }

  public void setPromptStr(String promptStr) {
    this.promptStr = promptStr;
  }

  public String getJmsl() {
    return jmsl;
  }

  public void setJmsl(String jmsl) {
    this.jmsl = jmsl;
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
