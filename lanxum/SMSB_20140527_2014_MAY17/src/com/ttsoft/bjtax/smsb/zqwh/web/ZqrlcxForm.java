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
 * <p>Description: 征期日历查询</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqrlcxForm
    extends BaseForm
{
    /**
     * 征期类型，查询使用
     */
    private String headZqlx;

    /**
     * 征期类型代码
     */
    private String zqlxdm;

    /**
     * 征期类型名称
     */
    private String zqlxmc;

    /**
     * 征期所属日期起
     */
    private String zqssrqq;

    /**
     * 征期所属日期止
     */
    private String zqssrqz;

    /**
     * 征期起始日期
     */
    private String zqqsrq;

    /**
     * 征期终止日期
     */
    private String zqzzrq;

    /**
     * 明细列表数据集
     */
    private String[] columns =
        {"szsmdm", "szsmmc", "dqkjfpje", "dqwkjfpje",
        "dqyysrjehj", "jzyysr"};

    /**
     * 页面数据集
     */
    private java.util.List dataList = new ArrayList();

    /**
     * 选中的纪录集
     */
    private java.util.List selectOptionList = new ArrayList();

    /**
     * 维护年份
     */
    private String whnf;

    /**
     * 选中标记
     */
    private String selectTypeRadio;

    public ZqrlcxForm ()
    {
        Calendar c = Calendar.getInstance();
        whnf = "" + c.get(Calendar.YEAR);
        headZqlx = "*";
        selectTypeRadio = "1";
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getHeadZqlx ()
    {
        return headZqlx;
    }

    public void setHeadZqlx (String headZqlx)
    {
        this.headZqlx = headZqlx;
    }

    public String getZqlxdm ()
    {
        return zqlxdm;
    }

    public void setZqlxdm (String zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }

    public String getZqlxmc ()
    {
        return zqlxmc;
    }

    public void setZqlxmc (String zqlxmc)
    {
        this.zqlxmc = zqlxmc;
    }

    public String getZqssrqq ()
    {
        return zqssrqq;
    }

    public void setZqssrqq (String zqssrqq)
    {
        this.zqssrqq = zqssrqq;
    }

    public String getZqssrqz ()
    {
        return zqssrqz;
    }

    public void setZqssrqz (String zqssrqz)
    {
        this.zqssrqz = zqssrqz;
    }

    public String getZqqsrq ()
    {
        return zqqsrq;
    }

    public void setZqqsrq (String zqqsrq)
    {
        this.zqqsrq = zqqsrq;
    }

    public String getZqzzrq ()
    {
        return zqzzrq;
    }

    public void setZqzzrq (String zqzzrq)
    {
        this.zqzzrq = zqzzrq;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getSelectOptionList ()
    {
        return selectOptionList;
    }

    public void setSelectOptionList (java.util.List selectOptionList)
    {
        this.selectOptionList = selectOptionList;
    }

    public String getWhnf ()
    {
        return whnf;
    }

    public void setWhnf (String whnf)
    {
        this.whnf = whnf;
    }

    public String getSelectTypeRadio ()
    {
        return selectTypeRadio;
    }

    public void setSelectTypeRadio (String selectTypeRadio)
    {
        this.selectTypeRadio = selectTypeRadio;
    }
}