/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypdsActionForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 一票多税</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshJksYpdsForm
    extends ZhsbjksypdsActionForm
{
    /**
     * 返回标识
     */
    private String fhbs;

    /**
     * 一票一税标识
     */
    private String ypys;

    /**
     * 汇总类型
     */
    private String hzlx;

    /**
     * 汇总开始日期
     */
    private String hzksrq;

    /**
     * 汇总结束日期
     */
    private String hzjsrq;

    /**
     * 申报汇总单号
     */
    private String sbhzdh;

    /**
     * 个体工商户计算机代码
     */
    private String gtgshJsjdm;

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getFhbs ()
    {
        return fhbs;
    }

    public void setFhbs (String fhbs)
    {
        this.fhbs = fhbs;
    }

    public String getHzjsrq ()
    {
        return hzjsrq;
    }

    public String getHzksrq ()
    {
        return hzksrq;
    }

    public String getHzlx ()
    {
        return hzlx;
    }

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public String getYpys ()
    {
        return ypys;
    }

    public void setYpys (String ypys)
    {
        this.ypys = ypys;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public void setHzlx (String hzlx)
    {
        this.hzlx = hzlx;
    }

    public void setHzksrq (String hzksrq)
    {
        this.hzksrq = hzksrq;
    }

    public void setHzjsrq (String hzjsrq)
    {
        this.hzjsrq = hzjsrq;
    }

    public String getGtgshJsjdm ()
    {
        return gtgshJsjdm;
    }

    public void setGtgshJsjdm (String gtgshJsjdm)
    {
        this.gtgshJsjdm = gtgshJsjdm;
    }

}