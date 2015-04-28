/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 撤销印花税购买情况撤销 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmlrcxForm
    extends BaseForm
{

    public YhsgmlrcxForm ()
    {
        ghxz = "1";
        dataList = new ArrayList();
        mxDataList = new ArrayList();
    }

    /**
     *录入起始日期
     */
    private String lrqsrq; //录入起始日期

    /**
     *录入截至日期
     */
    private String lrjzrq; //录入截至日期

    /**
     *录入人
     */
    private String lrr; //录入人

    /**
     *销售凭证号
     */
    private String xspzh; //销售凭证号

    /**
     *单笔金额合计
     */
    private String dbjehj; //单笔金额合计

    /**
     *购花性质
     */
    private String ghxz; //购花性质

    /**
     *购花单位计算机代码
     */
    private String ghdwjsjdm; //购花单位计算机代码

    /**
     *购花单位名称
     */
    private String ghdwmc; //购花单位名称

    /**
     *证件号码
     */
    private String zjhm; //证件号码

    /**
     *购花人名称
     */
    private String ghrmc; //购花人名称

    /**
     *分页:每页显示纪录的数目
     */
    private int length;

    /**
     *分页:查询结果总页数
     */
    private int pgSum;

    /**
     *分页:当前页数
     */
    private int pgNum;

    /**
     * 明细项目集合
     */
    private java.util.List dataList;

    /**
     *明细项目集合
     */
    private java.util.List mxDataList;

    /**
     *删除项目的列表
     */
    private String[] deleteCheckbox;

    /**
     *来源表示
     */
    private boolean fromDelete;

    /**
     *查看ID
     */
    private String viewId;

    /**
     *明晰创建日期
     */
    private String mxCjrq;

    /**
     *明细合计金额
     */
    private String mxHjje;

    /**
     *明细单位名称
     */
    private String mxDwmc;

    /**
     *名细单位代码
     */
    private String mxDwdm;

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        length = 0;
        pgSum = 0;
        pgNum = 0;
    }

    public String getLrqsrq ()
    {
        return lrqsrq;
    }

    public void setLrqsrq (String lrqsrq)
    {
        this.lrqsrq = lrqsrq;
    }

    public String getLrjzrq ()
    {
        return lrjzrq;
    }

    public void setLrjzrq (String lrjzrq)
    {
        this.lrjzrq = lrjzrq;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getXspzh ()
    {
        return xspzh;
    }

    public void setXspzh (String xkpzh)
    {
        this.xspzh = xkpzh;
    }

    public String getDbjehj ()
    {
        return dbjehj;
    }

    public void setDbjehj (String dbjehj)
    {
        this.dbjehj = dbjehj;
    }

    public String getGhxz ()
    {
        return ghxz;
    }

    public void setGhxz (String ghxz)
    {
        this.ghxz = ghxz;
    }

    public String getGhdwjsjdm ()
    {
        return ghdwjsjdm;
    }

    public void setGhdwjsjdm (String ghdwjsjdm)
    {
        this.ghdwjsjdm = ghdwjsjdm;
    }

    public String getGhdwmc ()
    {
        return ghdwmc;
    }

    public void setGhdwmc (String ghdwmc)
    {
        this.ghdwmc = ghdwmc;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public String getGhrmc ()
    {
        return ghrmc;
    }

    public void setGhrmc (String ghrmc)
    {
        this.ghrmc = ghrmc;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getMxDataList ()
    {
        return mxDataList;
    }

    public void setMxDataList (java.util.List mxDataList)
    {
        this.mxDataList = mxDataList;
    }

    public String[] getDeleteCheckbox ()
    {
        return deleteCheckbox;
    }

    public void setDeleteCheckbox (String[] deleteCheckbox)
    {
        this.deleteCheckbox = deleteCheckbox;
    }

    public boolean isFromDelete ()
    {
        return fromDelete;
    }

    public void setFromDelete (boolean fromDelete)
    {
        this.fromDelete = fromDelete;
    }

    public String getViewId ()
    {
        return viewId;
    }

    public void setViewId (String viewId)
    {
        this.viewId = viewId;
    }

    public String getMxCjrq ()
    {
        return mxCjrq;
    }

    public void setMxCjrq (String mxCjrq)
    {
        this.mxCjrq = mxCjrq;
    }

    public String getMxHjje ()
    {
        return mxHjje;
    }

    public void setMxHjje (String mxHjje)
    {
        this.mxHjje = mxHjje;
    }

    public String getMxDwmc ()
    {
        return mxDwmc;
    }

    public void setMxDwmc (String mxDwmc)
    {
        this.mxDwmc = mxDwmc;
    }

    public String getMxDwdm ()
    {
        return mxDwdm;
    }

    public void setMxDwdm (String mxDwdm)
    {
        this.mxDwdm = mxDwdm;
    }

}