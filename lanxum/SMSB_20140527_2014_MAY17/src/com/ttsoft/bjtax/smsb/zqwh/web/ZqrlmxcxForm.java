/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期日历明细维护查询</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqrlmxcxForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private java.util.List dataList = new ArrayList();

    /**
     * 明细列表数据集
     */
    private String columns[] =
        {
        "szsmdm", "szsmmc", "zqlxdm", "zqlxmc",
        "zqssrqq", "zqssrqz",
        "zqqsrq", "zqzzrq", "cjrq", "lrrq", "swjgzzjgdm",
        "nd", "djzclxdm", "djzclxmc", "zqksrq", "zqts"};

    /**
     * 月份，查询使用
     */
    private String headMonth;

    /**
     * 年度，查询使用
     */
    private String headNd;

    /**
     * 征期类型，查询使用
     */
    private String headZqlx;

    /**
     * 税种，查询使用
     */
    private String headSz;

    /**
     * 登记注册类型，查询使用
     */
    private String headDjzclx;

    /**
     * 年度，临时使用
     */
    private String tempNd;

    /**
     * 征期起始日期，临时使用
     */
    private String tempZqqsrq;

    /**
     * 税种，临时使用
     */
    private String tempSz;

    /**
     * 征期类型，临时使用
     */
    private String tempZqlx;

    /**
     * 登记注册类型，临时使用
     */
    private String tempDjzclx;

    /**
     * 月份，临时使用
     */
    private String tempMonth;

    public ZqrlmxcxForm ()
    {
        Calendar c = Calendar.getInstance();
        headNd = "" + c.get(Calendar.YEAR);
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
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

    public String getHeadMonth ()
    {
        return headMonth;
    }

    public void setHeadMonth (String headMonth)
    {
        this.headMonth = headMonth;
    }

    public String getHeadNd ()
    {
        return headNd;
    }

    public void setHeadNd (String headNd)
    {
        this.headNd = headNd;
    }

    public String getHeadZqlx ()
    {
        return headZqlx;
    }

    public void setHeadZqlx (String headZqlx)
    {
        this.headZqlx = headZqlx;
    }

    public String getHeadSz ()
    {
        return headSz;
    }

    public void setHeadSz (String headSz)
    {
        this.headSz = headSz;
    }

    public String getHeadDjzclx ()
    {
        return headDjzclx;
    }

    public void setHeadDjzclx (String headDjzclx)
    {
        this.headDjzclx = headDjzclx;
    }

    public String getTempNd ()
    {
        return tempNd;
    }

    public void setTempNd (String tempNd)
    {
        this.tempNd = tempNd;
    }

    public String getTempZqqsrq ()
    {
        return tempZqqsrq;
    }

    public void setTempZqqsrq (String tempZqqsrq)
    {
        this.tempZqqsrq = tempZqqsrq;
    }

    public String getTempSz ()
    {
        return tempSz;
    }

    public void setTempSz (String tempSz)
    {
        this.tempSz = tempSz;
    }

    public String getTempZqlx ()
    {
        return tempZqlx;
    }

    public void setTempZqlx (String tempZqlx)
    {
        this.tempZqlx = tempZqlx;
    }

    public String getTempDjzclx ()
    {
        return tempDjzclx;
    }

    public void setTempDjzclx (String tempDjzclx)
    {
        this.tempDjzclx = tempDjzclx;
    }

    public String getTempMonth ()
    {
        return tempMonth;
    }

    public void setTempMonth (String tempMonth)
    {
        this.tempMonth = tempMonth;
    }
}
