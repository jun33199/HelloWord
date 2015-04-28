/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.jmssb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 北京市减免税申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JmssbForm
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
   * 录入日期 String
   */
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
   * 录入人代码 String
   *
   * 从登录数据中取得
   */
  private String lrr;

  /**
   * 登记申报方式代码 String
   *
   * 从登录数据中取得
   */
  private String fsdm;

  /**
   * 记账标示 String
   *
   * 初始值000000
   */
  private String jzbz;

  /**
   * 登记注册类型代码 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String djzclxdm;

  /**
   * 国家标准行业代码 String
   *
   * 根据输入的税务计算机代码查询得到
   */
  private String gjbzhydm;

  /**
   * 用于存储明细中具体数值 List
   */
  private ArrayList dataList = new ArrayList();

  /**
   * 包括：税种税目名称、税种税目代码、计税金额、课税数量、减免税额、减免类型代码、预算科目代码、预算级次代码、税款所属结束日期、税款所属开始日期、减免类型
   * 明细信息标示 String[]
   * modify by tangchangfu 2014-04-04 无税减免税项目  新增字段 "jmxmjdm","jmxmksrq","jmxmjsrq"
   */
  private String[] columns = {
      "szsmmc", "szsmdm", "jsje", "kssl", "jmse",
      "jmxmdm", "yskmdm", "ysjcdm", "skssjsrq",
      "skssksrq", "jmlx","jmxmjdm","jmxmksrq","jmxmjsrq"};

  //private String[] columns = {"szsmdm","jsje","kssl","jmse","skssjsrq","skssksrq","jmlx"};
  /**
   * 显示下拉菜单内容 String
   */
  private String scriptStr;

  /**
   * 页面显示警告内容 String
   */
  private String warnStr;

  /**
   * 申报年度 String
   *
   * 根据申报日期决定
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
   * 录入日期
   */
  private String lrrq;
  
  /**
   * add by tangchangfu 无税减免税项目  2014-04-04 
   * 当期销售额
   */
  private String dqxse;
  /**
   * add by tangchangfu 无税减免税项目  2014-04-04 
   * 当期利润总额
   */
  private String dqlrze;
  /**
   * add by tangchangfu 无税减免税项目  2014-04-04 
   * 企业人数
   */
  private String qyrs;
  /**
   * add by tangchangfu 无税减免税项目  2014-04-04 
   * 安置人数
   */
  private String azrs;

  /**
   * 页面要素清除
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.actionType = "Show";
    this.jsjdm = null;
    this.nsrmc = null;
    this.dqxse = null;
    this.dqlrze = null;
    this.qyrs = null;
    this.azrs = null;
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

  public String getJzbz() {
    return jzbz;
  }

  public void setJzbz(String jzbz) {
    this.jzbz = jzbz;
  }

  public String getDjzclxdm() {
    return djzclxdm;
  }

  public void setDjzclxdm(String djzclxdm) {
    this.djzclxdm = djzclxdm;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
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

  public String getScriptStr() {
    return scriptStr;
  }

  public void setScriptStr(String scriptStr) {
    this.scriptStr = scriptStr;
  }

  public String getWarnStr() {
    return warnStr;
  }

  public void setWarnStr(String warnStr) {
    this.warnStr = warnStr;
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

public String getDqxse() {
	return dqxse;
}

public void setDqxse(String dqxse) {
	this.dqxse = dqxse;
}

public String getDqlrze() {
	return dqlrze;
}

public void setDqlrze(String dqlrze) {
	this.dqlrze = dqlrze;
}

public String getQyrs() {
	return qyrs;
}

public void setQyrs(String qyrs) {
	this.qyrs = qyrs;
}

public String getAzrs() {
	return azrs;
}

public void setAzrs(String azrs) {
	this.azrs = azrs;
}

  /* end added by huxiaofeng 2005.8.16*/

}
