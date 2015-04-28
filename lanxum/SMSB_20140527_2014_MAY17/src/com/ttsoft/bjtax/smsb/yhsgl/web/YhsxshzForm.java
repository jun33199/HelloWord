/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title:  北京地税核心征管系统－－上门申报</p>
 * <p>Description: 导入代售单位印花税销售情况汇总 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsxshzForm
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
     * 缴款凭证号
     */
    private String jkpzh = "";

    /**
     * 实缴金额
     */
    private String sjse = "";

    public String getDsdwmc ()
    {
        return this.dsdwmc;
    }

    public String getJkpzh ()
    {
        return this.jkpzh;
    }

    public String getSjse ()
    {
        return this.sjse;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSjse (String sjse)
    {
        this.sjse = sjse;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }
}