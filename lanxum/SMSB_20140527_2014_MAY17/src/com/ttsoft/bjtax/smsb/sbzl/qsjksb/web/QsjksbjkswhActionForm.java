/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现欠税缴款申报功能：包括缴款书录入，维护。</p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbjkswhActionForm
    extends BaseForm
{

  /**
   * 计算机代码
   */
  private String jsjdm;
  /**
   * 纳税人名称
   */
  private String nsrmc;

  /**
   * 联网明细数据列表
   */
  private List dataList = new ArrayList();

  /**
   * 非联网明细数据列表
   */
  private List nlwDataList = new ArrayList();

  /**
   * 缴款凭证号
   */
  private String jkpzhStr;

  /**
   * 数据来源
   */
  private String sjly;

  /**
   * 申报编号
   */
  private String sbbh;

  /**
   * 预设定申报编号
   */
  private String presbbh;  

  /**
   * 缴款书类型 一票一税、一票多税
   */
  private int jksType;

  /**
   * 撤销缴款书后显示相关提示信息
   */
  private String cxStr;

  /**
   * 被撤销的缴款书的相关缴款书列表
   */
  private java.util.ArrayList coList = new ArrayList();

  /**
   * 申报日期
   */
  private String sbrq; 
  
  /**
   * 申报编号数据列表
   */
  private List sbbhList = new ArrayList();
  
  /**
   * 申报欠税对照表关联缴款凭证号
   */
  private String gljkpzh; 
  
  /**
   * 申报欠税对照表缴款凭证号
   */
  private String dzjkpzh; 
  
  /**
   * 申报欠税对照表欠税序号
   */
  private String qsxh; 
  
  /**
   * 申报欠税对照表申报金额
   */
  private String sbje; 
  
  
  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest)
  {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest)
  {
    this.actionType = "Query";

  }

  public String getJsjdm()
  {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm)
  {
    this.jsjdm = jsjdm;
  }

  public List getDataList()
  {
    return dataList;
  }

  public void setDataList(List dataList)
  {
    this.dataList = dataList;
  }

  public String getJkpzhStr()
  {
    return jkpzhStr;
  }

  public void setJkpzhStr(String jkpzhStr)
  {
    this.jkpzhStr = jkpzhStr;
  }

  public String getSjly()
  {
    return sjly;
  }

  public void setSjly(String sjly)
  {
    this.sjly = sjly;
  }

  public String getSbbh()
  {
    return sbbh;
  }

  public void setSbbh(String sbbh)
  {
    this.sbbh = sbbh;
  }

  public String getPresbbh()
  {
    return presbbh;
  }

  public void setPresbbh(String presbbh)
  {
    this.presbbh = presbbh;
  }

  public String getCxStr()
  {
    return cxStr;
  }

  public void setCxStr(String cxStr)
  {
    this.cxStr = cxStr;
  }

  public java.util.ArrayList getCoList()
  {
    return coList;
  }

  public void setCoList(java.util.ArrayList coList)
  {
    this.coList = coList;
  }

  public String getSbrq()
  {
    return sbrq;
  }

  public int getJksType()
  {
    return jksType;
  }

  public List getNlwDataList()
  {
    return nlwDataList;
  }

  public String getNsrmc()
  {
    return nsrmc;
  }

  public void setSbrq(String sbrq)
  {
    this.sbrq = sbrq;
  }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }

  public void setNlwDataList(List nlwDataList)
  {
    this.nlwDataList = nlwDataList;
  }

  public void setNsrmc(String nsrmc)
  {
    this.nsrmc = nsrmc;
  }
  
  public List getSbbhList ()
  {
      return sbbhList;
  }

  public void setSbbhList (List sbbhList)
  {
      this.sbbhList = sbbhList;
  }

  public String getGljkpzh()
  {
    return gljkpzh;
  }

  public void setGljkpzh(String gljkpzh)
  {
    this.gljkpzh = gljkpzh;
  }
  
  public String getDzjkpzh()
  {
    return dzjkpzh;
  }

  public void setDzjkpzh(String dzjkpzh)
  {
    this.dzjkpzh = dzjkpzh;
  }  
  
  public String getQsxh()
  {
    return qsxh;
  }

  public void setQsxh(String qsxh)
  {
    this.qsxh = qsxh;
  }  
  
  public String getSbje()
  {
    return sbje;
  }

  public void setSbje(String sbje)
  {
    this.sbje = sbje;
  } 
}
