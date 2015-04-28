/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户营业所得</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrCxForm
    extends BaseForm
{
    /**
     * 计算机代码
     */
    private String headJsjdm;

    /**
     * 申报日期
     */
    private String headSbrq;

    /**
     *所有主表字段
     */
    private String[] columns =
        {
        "jsjdm", "sbrq", "swdjzh", "swjgzzjgdm", "lrrdm",
        "lrrq", "fsdm", "skssksrq", "skssjsrq", "nd",
        "cjrq", "qxdm", "nsrmc"};

    /**
     * 用于存储明细中具体数值 List
     */
    private List dataList = new ArrayList();

    /**
     * 计算机代码
     */
    private String tempJsjdm;

    /**
     * 申报日期
     */
    private String tempSbrq;

    /**
     * 页面清除
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     */

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.dataList.clear();
        this.headJsjdm = "";
        this.actionType = "Show";
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

    public String getHeadSbrq ()
    {
        return headSbrq;
    }

    public void setHeadSbrq (String headSbrq)
    {
        this.headSbrq = headSbrq;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getTempJsjdm ()
    {
        return tempJsjdm;
    }

    public void setTempJsjdm (String tempJsjdm)
    {
        this.tempJsjdm = tempJsjdm;
    }

    public String getTempSbrq ()
    {
        return tempSbrq;
    }

    public void setTempSbrq (String tempSbrq)
    {
        this.tempSbrq = tempSbrq;
    }

}
