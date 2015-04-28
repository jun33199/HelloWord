/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 导入代售单位印花税销售情况 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsxsdrForm
    extends BaseForm
{
    //主表字段
    /**
     * 代售单位计算机代码
     */
    private String dsjsjdm = "";

    /**
     * 代售单位名称
     */
    private String dsdwmc = "";

    /**
     * 录入日期
     */
    private String lrrq = "";

    /**
     * 创建时间
     */
    private String cjsj = "";

    /**
     * 数据来源
     */
    private String sjly = "";

    /**
     * 税务机关组织机构代码
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

    //仅页面显示
    /**
     * 购票数量合计
     */
    private String gpslhj = "";

    /**
     * 代售单位联系电话
     */
    private String dsdwlxdh = "";

    /**
     *导入文件
     */
    private FormFile theFile;

    /**
     * 销售数据列表
     */
    private ArrayList xsList = new ArrayList();

    /**
     *名细字段
     */
    private String columns[] =
        {
        "xspzh", "jsjdm", "dwmc", "gjdqdm", "zjlxdm",
        "zjhm", "spmzdm", "spmzje",
        "gpsl", "je"};

    /**
     * 明细项目集合
     */
    private ArrayList dataList = new ArrayList();

    /**
     * 分页：每页显示记录数
     */
    private int length;

    /**
     * 分页：当前页数
     */
    private int pgNum;

    /**
     * 分页：查询结果总页数
     */
    private int pgSum;

    private java.util.Map mapFile = new HashMap();

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dsjsjdm = "";
        dsdwmc = "";
        dsdwlxdh = "";
        swjgzzjgdm = "";
        lrrq = "";
        cjsj = "";
        lrr = "";
        hjje = "0.0";
        gpslhj = "0";
        sjly = "1";
        fsdm = "1";
        pgNum = 0;
        pgSum = 0;
        length = 0;

        dataList.clear();
        xsList.clear();
    }

    public String getFsdm ()
    {
        return fsdm;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public String getCjsj ()
    {
        return this.cjsj;
    }

    public String getDsjsjdm ()
    {
        return this.dsjsjdm;
    }

    public String getDsdwmc ()
    {
        return this.dsdwmc;
    }

    public String getDsdwlxdh ()
    {
        return this.dsdwlxdh;
    }

    public FormFile getTheFile ()
    {
        return theFile;
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

    public String getGpslhj ()
    {
        return gpslhj;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setCjsj (String cjsj)
    {
        this.cjsj = cjsj;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

    public void setDsdwlxdh (String dsdwlxdh)
    {
        this.dsdwlxdh = dsdwlxdh;
    }

    public void setTheFile (FormFile theFile)
    {
        this.theFile = theFile;
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

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setGpslhj (String gpslhj)
    {
        this.gpslhj = gpslhj;
    }

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

    public void setXsList (ArrayList xsList)
    {
        this.xsList = xsList;
    }

    public ArrayList getXsList ()
    {
        return xsList;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public java.util.Map getMapFile ()
    {
        return mapFile;
    }

    public void setMapFile (java.util.Map mapFile)
    {
        this.mapFile = mapFile;
    }

}