/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户撤销缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshCxjksForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();

    /**
     * 缴款凭证号，计算机代码，税种代码，纳税人名称，税务机关组织名称，
     * 申报日期，实缴金额
     */
    private String columns[] =
        {
        "jkpzh", "jsjdm", "szdm", "nsrmc", "swjgzzjgmc",
        "sbrq",
        "sjje"};

    /**
     * 录入人
     */
    private String lrrdm; //录入人

    /**
     * 缴款凭证号
     */
    private String headJkpzh; //缴款凭证号

    /**
     * 计算机代码
     */
    private String headJsjdm; //计算机代码

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getLrrdm ()
    {
        return lrrdm;
    }

    public void setLrrdm (String lrrdm)
    {
        this.lrrdm = lrrdm;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

}
