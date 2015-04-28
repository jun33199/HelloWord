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
 * <p>Description: 录入印花税购买情况 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmlrForm
    extends BaseForm
{
    //主表数据

    /**
     * 销售凭证号
     */
    private String xspzh = "";

    /**
     * 代售单位计算机代码
     */
    private String dsjsjdm = "";

    /**
     * 创建时间
     */
    private String cjsj = "";

    /**
     * 录入日期
     */
    private String lrrq = "";

    /**
     * 代售单位名称
     */
    private String dsdwmc = "";

    /**
     * 数据来源
     */
    private String sjly = "";

    /**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm = "";

    /**
     * 方式代码
     */
    private String fsdm = "";

    /**
     * 录入人
     */
    private String lrr = "";

    /**
     * 合计金额
     */
    private String hjje = "";

    //主表数据（汇总回填内容）
    /**
     * 缴款凭证号
     */
    private String jkpzh = "";

    /**
     * 汇总日期
     */
    private String hzrq = "";

    /**
     * 汇总人
     */
    private String hzr = "";

    /**
     * 已汇总标示
     */
    private String yhzbs = "";

    //从表数据（list之外的）
    /**
     * 计算机代码
     */
    private String jsjdm = "";

    /**
     * 单位名称
     */
    private String dwmc = "";

    /**
     * 购花单位名称
     */
    private String ghdwmc = "";

    /**
     * 购花人姓名
     */
    private String ghrxm = "";

    /**
     * 国家地区代码
     */
    private String gjdqdm = "";

    /**
     * 证件类型代码
     */
    private String zjlxdm = "";

    /**
     * 证件号码
     */
    private String zjhm = "";

    /**
     *从表数据（list包含的）
     */
    private String columns[] =
        {
        "spmzdm", "spmzje", "gpsl", "je"};

    /**
     * 明细项目集合
     */
    private ArrayList dataList = new ArrayList();

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        //主表数据
        xspzh = "";
        dsjsjdm = "";
        lrrq = "";
        cjsj = "";
        dsdwmc = "";
        sjly = "0"; //录入
        swjgzzjgdm = "";
        fsdm = "1"; //上门
        lrr = "";
        hjje = "";
        //主表数据（汇总回填内容）
        jkpzh = "";
        hzrq = "";
        hzr = "";
        yhzbs = "";

        //从表数据（list之外的）
        jsjdm = "";
        dwmc = "";
        ghdwmc = "";
        ghrxm = "";
        gjdqdm = "";
        zjlxdm = "02";
        zjhm = "";

        dataList.clear();
    }

    public String getCjsj ()
    {
        return cjsj;
    }

    public void setCjsj (String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String getDsdwmc ()
    {
        return dsdwmc;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public String getDwmc ()
    {
        return dwmc;
    }

    public String getGhdwmc ()
    {
        return ghdwmc;
    }

    public String getGhrxm ()
    {
        return ghrxm;
    }

    public String getFsdm ()
    {
        return fsdm;
    }

    public String getGjdqdm ()
    {
        return gjdqdm;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public String getHzr ()
    {
        return hzr;
    }

    public String getHzrq ()
    {
        return hzrq;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public String getSjly ()
    {
        return sjly;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public String getXspzh ()
    {
        return xspzh;
    }

    public String getYhzbs ()
    {
        return yhzbs;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public String getZjlxdm ()
    {
        return zjlxdm;
    }

    public void setZjlxdm (String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public void setYhzbs (String yhzbs)
    {
        this.yhzbs = yhzbs;
    }

    public void setXspzh (String xspzh)
    {
        this.xspzh = xspzh;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSjly (String sjly)
    {
        this.sjly = sjly;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setHzrq (String hzrq)
    {
        this.hzrq = hzrq;
    }

    public void setHzr (String hzr)
    {
        this.hzr = hzr;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public void setGjdqdm (String gjdqdm)
    {
        this.gjdqdm = gjdqdm;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setDwmc (String dwmc)
    {
        this.dwmc = dwmc;
    }

    public void setGhdwmc (String ghdwmc)
    {
        this.ghdwmc = ghdwmc;
    }

    public void setGhrxm (String ghrxm)
    {
        this.ghrxm = ghrxm;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

}