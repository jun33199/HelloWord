/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期类型维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqlxwhForm
    extends BaseForm
{
    /**
     * 征期类型代码
     */
    private String zqlxdm;

    /**
     * 征期类型名称
     */
    private String zqlxmc;

    /**
     * 征期
     */
    private String zqzq;

    /**
     * 税款所属日期
     */
    private String skssq;

    /**
     * 征期开始日期
     */
    private String zqksrq;

    /**
     * 征期天数
     */
    private String zqts;

    /**
     * 录入人
     */
    private String lrr;

    /**
     * 录入日期
     */
    private String lrrq;

    /**
     * 停用日期
     */
    private String tyrq;

    /**
     * 明细列表数据集
     */
    private String[] columns =
                               {"zqlxdm", "zqlxmc", "zqzq", "skssq", "zqksrq",
                               "zqts", "lrr", "lrrq", "tyrq", "jglxdm"};

                           /**
                            * 页面数据集
                            */
    private java.util.List dataList = new java.util.ArrayList();

    /**
     * 选择框的数组
     */
    private String[] tyCheckbox;

    /**
     * 维护年份
     */
    private String whnf;

    /**
     * 间隔类型代码
     */
    private String jglxdm;

    /**
     * 当前时间
     */
    private String strNow;

    /**
     * 征期类型代码，临时使用
     */
    private String tempZqlxdm;

    public ZqlxwhForm ()
    {
        Calendar c = Calendar.getInstance();
        whnf = "" + c.get(Calendar.YEAR);
        //得到当前时间
        strNow = SfDateUtil.getDate();
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
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

    public String getZqzq ()
    {
        return zqzq;
    }

    public void setZqzq (String zqzq)
    {
        this.zqzq = zqzq;
    }

    public String getSkssq ()
    {
        return skssq;
    }

    public void setSkssq (String skssq)
    {
        this.skssq = skssq;
    }

    public String getZqksrq ()
    {
        return zqksrq;
    }

    public void setZqksrq (String zqksrq)
    {
        this.zqksrq = zqksrq;
    }

    public String getZqts ()
    {
        return zqts;
    }

    public void setZqts (String zqts)
    {
        this.zqts = zqts;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
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

    public String[] getTyCheckbox ()
    {
        return tyCheckbox;
    }

    public void setTyCheckbox (String[] tyCheckbox)
    {
        this.tyCheckbox = tyCheckbox;
    }

    public String getTyrq ()
    {
        return tyrq;
    }

    public void setTyrq (String tyrq)
    {
        this.tyrq = tyrq;
    }

    public String getWhnf ()
    {
        return whnf;
    }

    public void setWhnf (String whnf)
    {
        this.whnf = whnf;
    }

    public String getJglxdm ()
    {
        return jglxdm;
    }

    public void setJglxdm (String jglxdm)
    {
        this.jglxdm = jglxdm;
    }

    public String getStrNow ()
    {
        return strNow;
    }

    public void setStrNow (String strNow)
    {
        this.strNow = strNow;
    }

    public String getTempZqlxdm ()
    {
        return tempZqlxdm;
    }

    public void setTempZqlxdm (String tempZqlxdm)
    {
        this.tempZqlxdm = tempZqlxdm;
    }
}