/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收撤销缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsCxjksForm
    extends BaseForm
{
  /**
   * 申报数据(联网的)
   */
  private List dataList = new ArrayList();

  /**
   * 申报数据(非联网的)
   */
  private List nlwDataList = new ArrayList();

  /**
   * 录入人
   */
  private String lrrdm; //录入人

  /**
   * 缴款凭证号
   */
  private String headJkpzh; //缴款凭证号

  /**
   * added by qianchao 2005.10.26
   * 缴款凭证号
   */
  private String headSbbh; //申报编号号

  /**
   * added by qianchao 2005.10.26
   * 缴款书类型
   */
  private int jksType;

  /**
   * 计算机代码
   */
  private String headJsjdm; //计算机代码

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest)
  {
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest)
  {
  }

  public String getLrrdm()
  {
    return lrrdm;
  }

  public void setLrrdm(String lrrdm)
  {
    this.lrrdm = lrrdm;
  }

  public String getHeadJkpzh()
  {
    return headJkpzh;
  }

  public void setHeadJkpzh(String headJkpzh)
  {
    this.headJkpzh = headJkpzh;
  }

  public String getHeadJsjdm()
  {
    return headJsjdm;
  }

  public String getHeadSbbh()
  {
    return headSbbh;
  }

  public List getDataList()
  {
    return dataList;
  }

  public List getNlwDataList()
  {
    return nlwDataList;
  }

  public int getJksType()
  {
    return jksType;
  }

  public void setHeadJsjdm(String headJsjdm)
  {
    this.headJsjdm = headJsjdm;
  }

  public void setHeadSbbh(String headSbbh)
  {
    this.headSbbh = headSbbh;
  }

  public void setDataList(List dataList)
  {
    this.dataList = dataList;
  }

  public void setNlwDataList(List nlwDataList)
  {
    this.nlwDataList = nlwDataList;
  }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }

}
