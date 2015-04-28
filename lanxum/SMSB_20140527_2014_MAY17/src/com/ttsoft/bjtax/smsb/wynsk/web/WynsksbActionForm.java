/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司，版权所有. </p>
 * <p>Company: 清华紫光股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wynsk.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现无应纳税费款申报模块。</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WynsksbActionForm
    extends BaseForm {
  /**
   * 计算机代码
   */
  private String jsjdm;

  /**
   * 纳税人名称
   */
  private String nsrmc;

  /**
   * 税款所属开始日期
   */
  private String skssksrq;

  /**
   * 税款所属结束日期
   */
  private String skssjsrq;

  /**
   * 申报日期
   */
  private String sbrq;

  /**
   * 纳税人税务机关组织机构代码
   */
  private String swjgzzjgdm;

  /**
   * 税务机关组织机构名称
   */
  private String swjgzzjgmc;

  /**
   * 纳税人注册地址联系电话
   *
   */
  private String zcdzlxdh;

  /**
   * 已申报数据
   */
  private java.util.ArrayList dataList = new ArrayList();

  /**
   * 申报编号
   */
  private String sbbh;

  /**
   * 税款所属日期
   */
  private String skssrqArr;
  
  
  //-------------------add  by tangchangfu 有税无税申报项目 2014-04-08  start
  /**
   * 无税申报原因代码
   */
  private String wssbyydm;
  
  private String wssbyynr;
  /**
   * 无税申报备注
   */
  private String bz;
  
  
  
  //-------------------add  by tangchangfu 有税无税申报项目 2014-04-08 end
  
  
  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
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

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public String getZcdzlxdh() {
    return zcdzlxdh;
  }

  public void setZcdzlxdh(String zcdzlxdh) {
    this.zcdzlxdh = zcdzlxdh;
  }

  public java.util.ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(java.util.ArrayList dataList) {
    this.dataList = dataList;
  }

  public String getSbbh() {
    return sbbh;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public String getSkssrqArr() {
    return skssrqArr;
  }

  public void setSkssrqArr(String skssrqArr) {
    this.skssrqArr = skssrqArr;
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

public String getWssbyydm() {
	return wssbyydm;
}

public void setWssbyydm(String wssbyydm) {
	this.wssbyydm = wssbyydm;
}

public String getWssbyynr() {
	return wssbyynr;
}

public void setWssbyynr(String wssbyynr) {
	this.wssbyynr = wssbyynr;
}

public String getBz() {
	return bz;
}

public void setBz(String bz) {
	this.bz = bz;
}

  /* end added by huxiaofeng 2005.8.16*/

}
