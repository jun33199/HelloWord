/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web;

import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 企业基本财务指标</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QyjbcwzbForm
    extends BaseForm {

  /**
   * 列表标题项目代码
   * String[]
   */
  private String[] columns = {
      "hc", "ncs", "nms"};

  /**
   * 计算机代码 String
   */
  private String jsjdm;

  /**
   * 纳税人名称 String
   */
  private String nsrmc;

  /**
   * 注册地址联系电话 String
   */
  private String zcdzlxdh;

  /**
   * 应税收入收入标识 String
   */
  private String sfyyssr;

  /**
   * 登记注册类型代码 String
   */
  private String djzclxdm;

  /**
   * 申报年度 String
   */

  private String nd;

  /**
   * 录入人代码 String
   *
   * 从登录数据中取得
   */

  private String lrr;

  /**
   * 录入日期 String
   */

  private String lrrq;

  /**
   * 创建时间 String
   */

  private String cjsj;

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
   * 登记申报方式代码 String
   *
   * 从登录数据中取得
   */
  private String fsdm;

  /**
   * 税务机关组织机构代码 String
   *
   * 从登录数据中取得
   */
  private String swjgzzjgdm;

  /**
   * 事业单位、社会团体收入申报数据 List
   */
  private List srsb = new ArrayList();

  /**
   * 企业申报日期 String
   */
  private String sbrq;

  /**
   *  综合申报跳转用
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

  public String getJsjdm() {
    return this.jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getNsrmc() {
    return this.nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getZcdzlxdh() {
    return this.zcdzlxdh;
  }

  public void setZcdzlxdh(String zcdzlxdh) {
    this.zcdzlxdh = zcdzlxdh;
  }

  public String getSfyyssr() {
    return sfyyssr;
  }

  public void setSfyyssr(String _sfyyssr) {
    this.sfyyssr = _sfyyssr;
  }

  public String getDjzclxdm() {
    return this.djzclxdm;
  }

  public void setDjzclxdm(String _djzclxdm) {
    this.djzclxdm = _djzclxdm;
  }

  public String getSbrq() {
    return this.sbrq;
  }

  public void setSbrq(String rq) {
    this.sbrq = rq;
  }

  public String[] getColumns() {
    return this.columns;
  }

  public void setColumns(String[] _columns) {
    this.columns = _columns;
  }

  public List getDataList() {
    return this.srsb;
  }

  public void setDataList(List _srsb) {
    this.srsb = _srsb;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String _nd) {
    nd = _nd;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String _lrr) {
    lrr = _lrr;
  }

  public String getCjrq() {
    return cjsj;
  }

  public void setCjrq(String _cjsj) {
    cjsj = _cjsj;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String _swjgzzjgdm) {
    swjgzzjgdm = _swjgzzjgdm;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String _fsdm) {
    fsdm = _fsdm;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String _lrrq) {
    lrrq = _lrrq;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String _skssksrq) {
    skssksrq = _skssksrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String _skssjsrq) {
    skssjsrq = _skssjsrq;
  }

  /**
   * 页面要素清除
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {

    zcdzlxdh = null;

    sfyyssr = null;

    djzclxdm = null;

    jsjdm = null;
    nd = null;
    cjsj = null;
    lrrq = null;
    //skssksrq = null;
    //skssjsrq = null;
    swjgzzjgdm = null;
    lrr = null;
    //sbrq = null;
    fsdm = null;
    nsrmc = null;

    getDataList().clear();
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
