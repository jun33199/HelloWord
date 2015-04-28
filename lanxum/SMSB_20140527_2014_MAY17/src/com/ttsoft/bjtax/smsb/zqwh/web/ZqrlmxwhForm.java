/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期日历明细纪录维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqrlmxwhForm
    extends BaseForm
{

    /**
     * 税种税目代码
     */
    private String szsmdm;

    /**
     * 税种税目名称
     */
    private String szsmmc;

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
     * 登记注册类型代码
     */
    private String djzclxdm;

    /**
     * 登记注册类型名称
     */
    private String djzclxmc;

    /**
     * 年度
     */
    private String nd;

    /**
     * 年度，查询使用
     */
    private String queryNd;

    /**
     * 征期起始日期，查询使用
     */
    private String queryZqqsrq;

    /**
     * 税种，查询使用
     */
    private String querySz;

    /**
     * 征期类型，查询使用
     */
    private String queryZqlx;

    /**
     * 登记注册类型，查询使用
     */
    private String queryDjzclx;

    /**
     * 月份，查询使用
     */
    private String queryMonth;

    public ZqrlmxwhForm ()
    {
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getSzsmdm ()
    {
        return szsmdm;
    }

    public void setSzsmdm (String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public String getSzsmmc ()
    {
        return szsmmc;
    }

    public void setSzsmmc (String szsmmc)
    {
        this.szsmmc = szsmmc;
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

    public String getDjzclxdm ()
    {
        return djzclxdm;
    }

    public void setDjzclxdm (String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }

    public String getDjzclxmc ()
    {
        return djzclxmc;
    }

    public void setDjzclxmc (String djzclxmc)
    {
        this.djzclxmc = djzclxmc;
    }

    public String getNd ()
    {
        return nd;
    }

    public void setNd (String nd)
    {
        this.nd = nd;
    }

    public String getQueryDjzclx ()
    {
        return queryDjzclx;
    }

    public String getQueryNd ()
    {
        return queryNd;
    }

    public String getQuerySz ()
    {
        return querySz;
    }

    public String getQueryZqlx ()
    {
        return queryZqlx;
    }

    public String getQueryZqqsrq ()
    {
        return queryZqqsrq;
    }

    public void setQueryZqqsrq (String queryZqqsrq)
    {
        this.queryZqqsrq = queryZqqsrq;
    }

    public void setQueryZqlx (String queryZqlx)
    {
        this.queryZqlx = queryZqlx;
    }

    public void setQuerySz (String querySz)
    {
        this.querySz = querySz;
    }

    public void setQueryNd (String queryNd)
    {
        this.queryNd = queryNd;
    }

    public void setQueryDjzclx (String queryDjzclx)
    {
        this.queryDjzclx = queryDjzclx;
    }

    public String getQueryMonth ()
    {
        return queryMonth;
    }

    public void setQueryMonth (String queryMonth)
    {
        this.queryMonth = queryMonth;
    }

}
