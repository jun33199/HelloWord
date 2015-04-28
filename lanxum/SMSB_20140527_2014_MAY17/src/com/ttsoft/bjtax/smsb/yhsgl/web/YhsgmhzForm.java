/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 汇总印花税购买情况 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmhzForm
    extends BaseForm
{
    /**
     * 是否联网
     */
    private String lw = "00";

    /**
     *汇总起始日期
     */
    private String hzqsrq = "";

    /**
     *汇总结束日期
     */
    private String hzjsrq = "";

    /**
     *汇总对象
     */
    private String hzdx = "";

    /**
     *汇总对象名称
     */
    private String hzdxmc = "";

    /**
     *缴款凭证号
     */
    private String jkpzh = "";

    /**
     * 申报编号
     */
    private String sbbh = "";

    /**
     *实缴金额
     */
    private String sjje = "";

    /**
     *印花税销售人员
     */
    private String yhsxsry = "";

    /**
     *代售单位计算机代码
     */
    private String dsjsjdm = "";

    /**
     *录入人
     */
    private String lrr = "";

    /**
     *纳税人名称
     */
    private String nsrmc = "";

    /**
     *征收机关代码
     */
    private String zsjgdm = "";

    /**
     * 明细项目集合
     */
    private ArrayList dataList = new ArrayList();

    /**
     *当前时间
     */
    private String strNow;

    public YhsgmhzForm ()
    {
        //得到当前时间
        strNow = SfDateUtil.getDate();
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        hzqsrq = "";
        hzjsrq = "";
        hzdx = "0";
        hzdxmc = "";
        jkpzh = "";
        sjje = "";
        yhsxsry = "";
        dsjsjdm = "";
        lrr = "";
        nsrmc = "";
        zsjgdm = "";
    }

    public String getHzdx ()
    {
        return hzdx;
    }

    public String getHzjsrq ()
    {
        return hzjsrq;
    }

    public String getHzqsrq ()
    {
        return hzqsrq;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getHzdxmc ()
    {
        return hzdxmc;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public String getYhsxsry ()
    {
        return yhsxsry;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setHzdxmc (String hzdxmc)
    {
        this.hzdxmc = hzdxmc;
    }

    public void setHzqsrq (String hzqsrq)
    {
        this.hzqsrq = hzqsrq;
    }

    public void setHzjsrq (String hzjsrq)
    {
        this.hzjsrq = hzjsrq;
    }

    public void setHzdx (String hzdx)
    {
        this.hzdx = hzdx;
    }

    public void setYhsxsry (String yhsxsry)
    {
        this.yhsxsry = yhsxsry;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getZsjgdm ()
    {
        return zsjgdm;
    }

    public void setZsjgdm (String zsjgdm)
    {
        this.zsjgdm = zsjgdm;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getStrNow ()
    {
        return strNow;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getLw() {
        return lw;
    }

    public void setStrNow (String strNow)
    {
        this.strNow = strNow;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }
}
