/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户撤销完税证</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshCxwszForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();

    /**
     * 页面元素列表数组
     */
    private String columns[] =
        {
        "pzzldm", "wszh", "nsrjsjdm", "jsjdm",
        "swjgzzjgdm",
        "djzclxdm", "dz", "hjsjje", "clbjdm", "sbhzdh",
        "jbdh", "zjlxdm", "zjhm", "swjgzzjgdm2", "lrr",
        "fsdm", "gjbzhydm", "nd", "wszxh", "cjrq",
        "lrrq", "qxdm", "ndzb", "printflag", "nsrmc"};

    /**
     * 完税证号码
     */
    private String headWszh; //完税证号码

    /**
     * 录入人代码
     */
    private String headLrr; //录入人代码

    /**
     * 帐户代码
     */
    private String headZhdm; //帐户代码

    /**
     * 年度字别
     */
    private String headNdzb; //年度字别

    /**
     * 年度字别，临时
     */
    private String tempNdzb;

    /**
     * 纳税人计算机代码，查询使用
     */
    private String headNsrjsjdm;

    /**
     * 票征种类代码，查询使用
     */
    private String headPzzldm;

    /**
     * 完税证序号，撤销使用
     */
    private String headWszxh;

    /**
     * 处理标记代码，设置打印标记使用
     */
    private String headClbjdm;

    public GtgshCxwszForm ()
    {
        Calendar c = Calendar.getInstance();
        tempNdzb = "" + c.get(Calendar.YEAR);
    }

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
        headWszh = "";
        headWszxh = "";
        dataList = new ArrayList();

    }

    public String getHeadWszh ()
    {
        return headWszh;
    }

    public void setHeadWszh (String headWszh)
    {
        this.headWszh = headWszh;
    }

    public String getHeadLrr ()
    {
        return headLrr;
    }

    public void setHeadLrr (String headLrr)
    {
        this.headLrr = headLrr;
    }

    public String getHeadZhdm ()
    {
        return headZhdm;
    }

    public void setHeadZhdm (String headZhdm)
    {
        this.headZhdm = headZhdm;
    }

    public String getHeadNdzb ()
    {
        return headNdzb;
    }

    public void setHeadNdzb (String headNdzb)
    {
        this.headNdzb = headNdzb;
    }

    public String getTempNdzb ()
    {
        return tempNdzb;
    }

    public void setTempNdzb (String tempNdzb)
    {
        this.tempNdzb = tempNdzb;
    }

    public String getHeadNsrjsjdm ()
    {
        return headNsrjsjdm;
    }

    public void setHeadNsrjsjdm (String headNsrjsjdm)
    {
        this.headNsrjsjdm = headNsrjsjdm;
    }

    public String getHeadPzzldm ()
    {
        return headPzzldm;
    }

    public void setHeadPzzldm (String headPzzldm)
    {
        this.headPzzldm = headPzzldm;
    }

    public String getHeadWszxh ()
    {
        return headWszxh;
    }

    public void setHeadWszxh (String headWszxh)
    {
        this.headWszxh = headWszxh;
    }

    public String getHeadClbjdm ()
    {
        return headClbjdm;
    }

    public void setHeadClbjdm (String headClbjdm)
    {
        this.headClbjdm = headClbjdm;
    }

}