package com.ttsoft.bjtax.shenbao.service;

import java.sql.Timestamp;
import java.math.BigDecimal;

public class Sbjkyqwrk implements java.io.Serializable
{
    public Sbjkyqwrk()
    {
    }

    /**
     * 限缴日期
     */
    private Timestamp xjrq;

    /**
     * 税种名称
     */
    private String szmc;

    /**
     * 税种代码
     */
    private String szdm;

    /**
     * 税种税目名称
     */
    private String szsmmc;

    /**
     * 税种税目代码
     */
    private String szsmdm;

    /**
     * 税款所属开始日期
     */
    private Timestamp skssksrq;

    /**
     * 税款所属结束日期
     */
    private Timestamp skssjsrq;

    /**
     * 实缴税额
     */
    private BigDecimal sjse;

    public BigDecimal getSjse()
    {
        return sjse;
    }
    public void setSjse(BigDecimal sjse)
    {
        this.sjse = sjse;
    }
    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }
    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }
    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public void setSzdm(String szdm)
    {
        this.szdm = szdm;
    }
    public String getSzmc()
    {
        return szmc;
    }
    public void setSzmc(String szmc)
    {
        this.szmc = szmc;
    }
    public String getSzsmdm()
    {
        return szsmdm;
    }
    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public String getSzsmmc()
    {
        return szsmmc;
    }
    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }
    public Timestamp getXjrq()
    {
        return xjrq;
    }
    public void setXjrq(Timestamp xjrq)
    {
        this.xjrq = xjrq;
    }
    public String getSzdm()
    {
        return szdm;
    }
}