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
 * <p>Description: 税种与征期类型对照关系维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class SzzqdzForm
    extends BaseForm
{
    /**
     * 删除标记的check box标记
     */
    private String[] deleteCheckBox;

    /**
     * 页面数据集
     */
    private java.util.List dataList = new ArrayList();

    /**
     * 明细列表数据集
     */
    private String[] columns =
        {
        "szdm", "zqlxdm", "djzclxdm", "zqts", "saveFlag"};

    /**
     * 查询类型
     */
    private String queryType = "2";

    /**
     * 年度，查询使用
     */
    private String headNd;

    /**
     *征期类型，查询使用
     */
    private String headZqlx;

    /**
     * 税种，查询使用
     */
    private String headSz;

    /**
     * 登记注册类型，查询使用
     */
    private String headDjzclx;

    /**
     * 年度，临时使用
     */
    private String tempNd;

    /**
     * 税种，临时使用
     */
    private String tempSz;

    /**
     * 登记注册类型，临时使用
     */
    private String tempDjzclx;

    /**
     * 征期类型，临时使用
     */
    private String tempZqlx;

    /**
     * 导入年份
     */
    private String importNd;

    public SzzqdzForm ()
    {
        Calendar c = Calendar.getInstance();
        headNd = "" + c.get(Calendar.YEAR);
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String[] getDeleteCheckBox ()
    {
        return deleteCheckBox;
    }

    public void setDeleteCheckBox (String[] deleteCheckBox)
    {
        this.deleteCheckBox = deleteCheckBox;
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
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

    public String getQueryType ()
    {
        return queryType;
    }

    public void setQueryType (String queryType)
    {
        this.queryType = queryType;
    }

    public String getHeadNd ()
    {
        return headNd;
    }

    public void setHeadNd (String headNd)
    {
        this.headNd = headNd;
    }

    public String getHeadZqlx ()
    {
        return headZqlx;
    }

    public void setHeadZqlx (String headZqlx)
    {
        this.headZqlx = headZqlx;
    }

    public String getHeadSz ()
    {
        return headSz;
    }

    public void setHeadSz (String headSz)
    {
        this.headSz = headSz;
    }

    public String getHeadDjzclx ()
    {
        return headDjzclx;
    }

    public void setHeadDjzclx (String headDjzclx)
    {
        this.headDjzclx = headDjzclx;
    }

    public String getTempNd ()
    {
        return tempNd;
    }

    public void setTempNd (String tempNd)
    {
        this.tempNd = tempNd;
    }

    public String getTempSz ()
    {
        return tempSz;
    }

    public void setTempSz (String tempSz)
    {
        this.tempSz = tempSz;
    }

    public String getTempDjzclx ()
    {
        return tempDjzclx;
    }

    public void setTempDjzclx (String tempDjzclx)
    {
        this.tempDjzclx = tempDjzclx;
    }

    public String getTempZqlx ()
    {
        return tempZqlx;
    }

    public void setTempZqlx (String tempZqlx)
    {
        this.tempZqlx = tempZqlx;
    }

    public String getImportNd ()
    {
        return importNd;
    }

    public void setImportNd (String importNd)
    {
        this.importNd = importNd;
    }
}