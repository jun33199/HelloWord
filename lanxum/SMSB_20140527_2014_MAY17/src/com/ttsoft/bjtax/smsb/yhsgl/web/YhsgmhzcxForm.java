/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 撤销印花税购买情况汇总 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmhzcxForm
    extends BaseForm
{
    /**
     *撤销汇总对象
     */
    private String cxhzdx = "";

    /**
     *印花税销售人员
     */
    private String yhsxsry = "";

    /**
     *代售单位计算机代码
     */
    private String dsjsjdm = "";

    /**
     *撤销申报表号
     */
    private String cxsbbh = "";

    /**
     *撤销缴款凭证号
     */
    private String cxjkpzh = "";
    
    /**
     *缴款凭证号
     */
    private String jkpzh = "";

    /**
     * 申报表号
     */
    private String sbbh = "";

    /**
     *实缴金额
     */
    private String sjje = "";

    /**
     * 从表数据（list包含的）
     */
    private String columns[] =
        {
        "jkpzh", "sjje"};
    /**
     * 明细项目集合
     */
    private ArrayList dataList = new ArrayList();

    /**
     * 来源标记
     */
    private boolean isFromCx;

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        cxhzdx = "0";
        yhsxsry = "";
        cxsbbh = "";
        dataList.clear();
    }

    public void setCxhzdx (String cxhzdx)
    {
        this.cxhzdx = cxhzdx;
    }

    public void setYhsxsry (String yhsxsry)
    {
        this.yhsxsry = yhsxsry;
    }

    public String getYhsxsry ()
    {
        return yhsxsry;
    }

    public String getCxhzdx ()
    {
        return cxhzdx;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public boolean isIsFromCx ()
    {
        return isFromCx;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getCxsbbh() {
        return cxsbbh;
    }

    public void setIsFromCx (boolean isFromCx)
    {
        this.isFromCx = isFromCx;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setCxsbbh(String cxsbbh) {
        this.cxsbbh = cxsbbh;
    }

	public String getCxjkpzh() {
		return cxjkpzh;
	}

	public void setCxjkpzh(String cxjkpzh) {
		this.cxjkpzh = cxjkpzh;
	}

}
