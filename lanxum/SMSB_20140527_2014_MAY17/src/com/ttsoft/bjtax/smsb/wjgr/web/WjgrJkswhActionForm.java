/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrJkswhActionForm
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
     * 国家代码
     */
    private String gjdm;

    /**
     * 证件类型代码
     */
    private String zjlxdm;

    /**
     * 证件号码
     */
    private String zjhm;

    /**
     * 联网申报表列表
     */
    private List dataList = new ArrayList();

    /**
     * 非联网缴款书列表
     */
    private List nlwDataList = new ArrayList();

    /**
     * 缴款书类型
     */
    private int jksType = 0;

    /**
     * 缴款凭证号
     */
    private String headJkpzh;

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 申报编号
     */
    private String sbbh;

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getGjdm ()
    {
        return gjdm;
    }

    public void setGjdm (String gjdm)
    {
        this.gjdm = gjdm;
    }

    public String getZjlxdm ()
    {
        return zjlxdm;
    }

    public void setZjlxdm (String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

  public List getNlwDataList()
  {
    return nlwDataList;
  }

  public int getJksType()
  {
    return jksType;
  }

  public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
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
