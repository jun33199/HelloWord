/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 综合申报告知事项，如果有告知事项则显示列表，并且在确认后转回综合申报页面。<br>
 * 如果没有告知事项则直接转回综合申报页面。 </p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbGzsxActionForm
    extends BaseForm
{
    /**
     * 域名称数组
     */
  private String[] columns;
  /**
   * 明细数据列表
   */
  private ArrayList dataList = new ArrayList();
  /**
   * 计算机代码
   */
  private String jsjdm;
  /**
   * 告知事项
   */
  private String gzsx;
  /**
   * 非正常标示
   */
  private String fzcbs;
  /**
   * 申报日期
   */
  private String sbrq;
  public void setColumns(String[] columns)
  {
    this.columns = columns;
  }

  public String[] getColumns()
  {
    return columns;
  }

  public void setDataList(ArrayList dataList)
  {
    this.dataList = dataList;
  }

  public ArrayList getDataList()
  {
    return dataList;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest)
  {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest)
  {
    this.gzsx = "1";
  }

  public String getJsjdm()
  {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm)
  {
    this.jsjdm = jsjdm;
  }

  public String getGzsx()
  {
    return gzsx;
  }

  public void setGzsx(String gzsx)
  {
    this.gzsx = gzsx;
  }

  public String getFzcbs()
  {
    return fzcbs;
  }

  public void setFzcbs(String fzcbs)
  {
    this.fzcbs = fzcbs;
  }

  public String getSbrq()
  {
    return sbrq;
  }

  public void setSbrq(String sbrq)
  {
    this.sbrq = sbrq;
  }
}