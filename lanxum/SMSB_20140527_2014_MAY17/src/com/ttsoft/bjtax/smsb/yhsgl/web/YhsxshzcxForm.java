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
 * <p>Description: 撤销印花税代售单位销售汇总 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsxshzcxForm
    extends BaseForm
{
    /**
     * 代售单位计算机代码
     */
    private String dsjsjdm = "";

    /**
     * 代售单位名称
     */
    private String dsdwmc = "";

    /**
     * 代售单位联系电话
     */
    private String dsdwlxdh = "";

    /**
     * 撤销缴款凭证号
     */
    private String cxjkpzh = "";

    /**
     * 缴款凭证号
     */
    private String jkpzh = "";

    /**
     * 实缴金额
     */
    private String sjje = "";

    /**
     * 判断是否是撤销后自动调用查询功能
     */
    private boolean isFromCx = false; // 判断是否是撤销后自动调用查询功能

    /**
     *从表数据（list包含的）
     */
    private String columns[] =
        {
        "jkpzh", "sjje"};

    /**
     *明细项目集合
     */
    private ArrayList dataList = new ArrayList();

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        cxjkpzh = "";
        dataList.clear();
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public String getCxjkpzh ()
    {
        return cxjkpzh;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setCxjkpzh (String cxjkpzh)
    {
        this.cxjkpzh = cxjkpzh;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public String getDsdwlxdh ()
    {
        return dsdwlxdh;
    }

    public String getDsdwmc ()
    {
        return dsdwmc;
    }

    public void setDsdwlxdh (String dsdwlxdh)
    {
        this.dsdwlxdh = dsdwlxdh;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

    public boolean isIsFromCx ()
    {
        return isFromCx;
    }

    public void setIsFromCx (boolean isFromCx)
    {
        this.isFromCx = isFromCx;
    }
}