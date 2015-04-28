/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsnbForm
    extends BaseForm {
  public QysdsnbForm() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 所得税年报列表标题项目代码 行次、本期累计数
   * String[]
   */
  private String[] sds_columns = {
      "hc", "bqljs"};

  /**
   * 联营、股份表标题项目代码
   * String[]
   */
  private String[] lygf_columns = {
      "dwmc", "fl", "sl", "lr", "ynssde", "ynsdse", "sskce", "ybsdse"};

  /**
   * 申报日期 String
   */
  private String sbrq;

  /**
   * 计算机代码 String
   */
  private String jsjdm;

  /**
   * 纳税人名称 String
   */
  private String nsrmc;

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
   * 税款所属开始年度日期 String
   *
   * 系统根据申报日期自动计算
   */

  private String skssksrq;

  /**
   * 税款所属年度结束日期 String
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

  private String swjgzzjgdm2;

  /**
   * 注册地址联系电话  String
   */
  private String zcdzlxdh;

  /**
   * 主管税务机关代码 String
   */
  private String zgswjgzzjgdm;

  /**
   * 主管税务机关名称 String
   */
  private String zgswjgzzjgmc;

  /**
   * 税务所代码 String
   */
  private String swjgzzjgdm;

  /**
   * 税务所名称 String
   */
  private String swjgzzjgmc;

  /**
   * 代理中介机构名称 String
   */
  private String dljgdwmc;

  /**
   * 代理中介机构计算机代码 String
   */
  private String dljgjsjdm;

  /**
   * 登记注册类型代码 String
   */
  private String djzclxdm;

  /**
   * 征收方式代码 String
   */
  private String zsfsdm;

  /**
   * 使用税率 String
   */
  private String zssl;

  /**
   * 定额征收方式企业的征收定额 String
   */
  private String de;

  /**
   * 高新技术企业标志 String
   */
  private boolean gxjsqy = false;

  /**
   * 乡镇企业标志 String
   */
  private boolean xzqy = false;

  /**
   * 联营、股份企业数据 String
   */
  private List lygfData = new ArrayList();

  /**
   * 联营企业数据 String
   */
  private List lyqyDataList = new ArrayList();

  /**
   * 股份企业数据 String
   */
  private List gfqyDataList = new ArrayList();

  /**
   * 中外合资企业数据 String
   */
  private List hzqyDataList = new ArrayList();

  /**
   * 投资企业数据 String
   */
  private List tzDataList = new ArrayList();

  //===== 24 63 77 82 行次的审批数据，如果没有则为空=========

  /**
   * 财产损失扣除额 24
   */
  private java.math.BigDecimal spzg24;

  /**
   * 弥补以前年度亏损 63
   */
  private java.math.BigDecimal spzg63;

  /**
   * 免税的技术转让收益 69
   */
  private java.math.BigDecimal spzg69;

  /**
   * 免税的治理“三废”收益 70
   */
  private java.math.BigDecimal spzg70;

  /**
   * 研究新产品、新技术、新工艺费用抵扣额 77
   */
  private java.math.BigDecimal spzg77;

  /**
   * 减免所得税额 81
   */
  private java.math.BigDecimal spzg81;

  /**
   * 技术改造国产设备投资抵免税额 82
   */
  private java.math.BigDecimal spzg82;

  /**
   * 企业所得税年报数据
   */
  private List qysdsnb = new ArrayList();

  /**
   *  综合申报跳转用
   */
  private String iszhsb;

  /**
   * 区县代码
   */
  private String qxdm;
  private String yhbl;
  private String xzqyjm;

  /**
   * 征收方式代码，当企业为高新企业时，征收代码会清空，此为征收代码备份
   */
  private String zsfsdm2;

  /**
   * 征收税率代码，当企业为高新企业时，征收税率会清空，此为征收税率备份
   */
  private String zssl2;

  /**
   * 纯益率
   */
  private String cyl = "0";

  public boolean isXzqy() {
    return this.xzqy;
  }

  public void SetXzqy(boolean _xzqy) {
    this.xzqy = _xzqy;
  }

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

  public List getLyqyDataList() {
    SfHashList _dataList = new SfHashList(lygfData);
    SfHashList _lyDataList = _dataList.findChildSet("fl", "1");
    return _lyDataList.getRecords();
  }

  public List getGfqyDataList() {
    SfHashList _dataList = new SfHashList(lygfData);
    SfHashList _lyDataList = _dataList.findChildSet("fl", "2");
    return _lyDataList.getRecords();
  }

  public List getHzqyDataList() {
    SfHashList _dataList = new SfHashList(lygfData);
    SfHashList _lyDataList = _dataList.findChildSet("fl", "3");
    return _lyDataList.getRecords();
  }

  public List getTzqyDataList() {
    SfHashList _dataList = new SfHashList(lygfData);
    SfHashList _lyDataList = _dataList.findChildSet("fl", "4");
    return _lyDataList.getRecords();
  }

  public String getDjzclxdm() {
    return this.djzclxdm;
  }

  public void setDjzclxdm(String _djzclxdm) {
    this.djzclxdm = _djzclxdm;
  }

  public String getZgswjgzzjgdm() {
    return this.zgswjgzzjgdm;
  }

  public void setZgswjgzzjgdm(String _zgswjgzzjgdm) {
    this.zgswjgzzjgdm = _zgswjgzzjgdm;
  }

  public String getZgswjgzzjgmc() {
    return this.zgswjgzzjgmc;
  }

  public void setZgswjgzzjgmc(String _zgswjgzzjgmc) {
    this.zgswjgzzjgmc = _zgswjgzzjgmc;
  }

  public String getSwjgzzjgdm() {
    return this.swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String _swjgzzjgdm) {
    this.swjgzzjgdm = _swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return this.swjgzzjgmc;
  }

  public void setSwjgzzjgmc(String _swjgzzjgmc) {
    this.swjgzzjgmc = _swjgzzjgmc;
  }

  public String getDljgdwmc() {
    return this.dljgdwmc;
  }

  public void setDljgdwmc(String _dljgdwmc) {
    this.dljgdwmc = _dljgdwmc;
  }

  public String getDljgjsjdm() {
    return this.dljgjsjdm;
  }

  public void setDljgjsjdm(String _dljgjsjdm) {
    this.dljgjsjdm = _dljgjsjdm;
  }

  public String getJsjdm() {
    return this.jsjdm;
  }

  public void setJsjdm(String _jsjdm) {
    this.jsjdm = _jsjdm;
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

  public String getSbrq() {
    return this.sbrq;
  }

  public void setSbrq(String rq) {
    this.sbrq = rq;
  }

  public String[] getColumns() {
    return this.sds_columns;
  }

  public void setColumns(String[] columns) {
    this.sds_columns = columns;
  }

  public List getDataList() {
    return this.qysdsnb;
  }

  public void setDataList(List qysdsnb) {
    this.qysdsnb = qysdsnb;
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

  public String getSwjgzzjgdm2() {
    return swjgzzjgdm2;
  }

  public void setSwjgzzjgdm2(String _swjgzzjgdm2) {
    swjgzzjgdm2 = _swjgzzjgdm2;
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

  public String getZsfsdm() {
    //return com.ttsoft.bjtax.smsb.constant.CodeConstant.ZSFSDM_CYLZS;
    return this.zsfsdm;
  }

  public void setZsfsdm(String _zsfsdm) {
    this.zsfsdm = _zsfsdm;
  }

  public String getZssl() {
    return this.zssl;
  }

  public void setZssl(String _zssl) {
    this.zssl = _zssl;
  }

  public String getDe() {
    if (this.de == null) {
      return "0.00";
    }
    else {
      return this.de;
    }
  }

  public void setDe(String _de) {
    this.de = _de;
  }

  public BigDecimal getSpzg82() {
    return this.spzg82;
  }

  public void setSpzg82(java.math.BigDecimal _spzg82) {
    this.spzg82 = _spzg82;
  }

  public BigDecimal getSpzg81() {
    return this.spzg81;
  }

  public void setSpzg81(java.math.BigDecimal _spzg81) {
    this.spzg81 = _spzg81;
  }

  public BigDecimal getSpzg77() {
    return this.spzg77;
  }

  public void setSpzg77(java.math.BigDecimal _spzg77) {
    this.spzg77 = _spzg77;
  }

  public BigDecimal getSpzg63() {
    return this.spzg63;
  }

  public void setSpzg63(java.math.BigDecimal _spzg63) {
    this.spzg63 = _spzg63;
  }

  public BigDecimal getSpzg24() {
    return this.spzg24;
  }

  public void setSpzg24(java.math.BigDecimal _spzg24) {
    this.spzg24 = _spzg24;
  }

  public BigDecimal getSpzg69() {
    return this.spzg69;
  }

  public void setSpzg69(java.math.BigDecimal _spzg69) {
    this.spzg69 = _spzg69;
  }

  public BigDecimal getSpzg70() {
    return this.spzg70;
  }

  public void setSpzg70(java.math.BigDecimal _spzg70) {
    this.spzg70 = _spzg70;
  }

  public List getLygfDataList() {
    return this.lygfData;
  }

  public void setLygfDataList(List _lygf) {
    this.lygfData = _lygf;
  }

  public String[] getLygfColumns() {
    return this.lygf_columns;
  }

  public void setLygfColumns(String[] _cols) {
    this.lygf_columns = _cols;
  }

  public boolean isGxjsqy() {
    return this.gxjsqy;
  }

  public void setGxjsqy(boolean _gxjsqy) {
    this.gxjsqy = _gxjsqy;
  }

  /**
   * 页面要素清除
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {

    swjgzzjgdm2 = null;

    zcdzlxdh = null;

    zgswjgzzjgmc = null;

    swjgzzjgdm = null;

    swjgzzjgmc = null;

    dljgdwmc = null;

    dljgjsjdm = null;

    zcdzlxdh = null;

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
    de = null;

    zsfsdm = "";
    zsfsdm2 = "";

    getDataList().clear();
    getLygfDataList().clear();
  }

  public String getYhbl() {
    return yhbl;
  }

  public void setYhbl(String yhbl) {
    this.yhbl = yhbl;
  }

  public String getXzqyjm() {
    return xzqyjm;
  }

  public void setXzqyjm(String xzqyjm) {
    this.xzqyjm = xzqyjm;
  }

  public String getZsfsdm2() {
    return zsfsdm2;
  }

  public void setZsfsdm2(String zsfsdm2) {
    this.zsfsdm2 = zsfsdm2;
  }

  public String getZssl2() {
    return zssl2;
  }

  public void setZssl2(String zssl2) {
    this.zssl2 = zssl2;
  }

  /* start added by huxiaofeng 2005.8.16*/
  /**
   * 纳税人状态
   */
  private String nsrzt="99"; //纳税人状态

  public void setNsrzt(String nsrzt) {
    this.nsrzt = nsrzt;
  }

  public void setCyl(String cyl) {
    this.cyl = cyl;
  }

  public String getNsrzt() {
    return nsrzt;
  }

  public String getCyl() {
    return cyl;
  }

  private void jbInit() throws Exception {
  }

  /* end added by huxiaofeng 2005.8.16*/

}
