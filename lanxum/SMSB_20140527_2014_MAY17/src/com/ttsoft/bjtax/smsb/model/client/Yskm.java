/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现北京地税申报模块 预算科目信息</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class Yskm
    implements Serializable
{
    public Yskm (String yskmdm, String yskmmc)
    {
        this.yskmdm = yskmdm;
        this.yskmmc = yskmmc;
    }

    public Yskm ()
    {
    }

    /**
     * 预算科目代码
     */
    public String yskmdm;

    /**
     * 预算科目名称
     */
    private String yskmmc;

    /**
     * 市局分成比例
     */
    private BigDecimal sjfcbl;

    /**
     * 区县分成比例
     */
    private BigDecimal qxfcbl;

    /**
     * 缺省分成比率
     */
    private BigDecimal defaultBl = new BigDecimal(0);

    public String getYskmdm ()
    {
        return yskmdm;
    }

    public String getYskmmc ()
    {
        return yskmmc;
    }

    public void setYskmdm (String yskmdm)
    {
        this.yskmdm = yskmdm;
    }

    public void setYskmmc (String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public BigDecimal getQxfcbl ()
    {
        return (qxfcbl == null ? defaultBl : qxfcbl);
    }

    public void setQxfcbl (BigDecimal qxfcbl)
    {
        this.qxfcbl = qxfcbl;
    }

    public BigDecimal getSjfcbl ()
    {
        return (sjfcbl == null ? defaultBl : sjfcbl);
    }

    public void setSjfcbl (BigDecimal sjfcbl)
    {
        this.sjfcbl = sjfcbl;
    }
}