package com.ttsoft.bjtax.shenbao.service;

import java.sql.Timestamp;
import java.math.BigDecimal;

public class Sbjkyqwrk implements java.io.Serializable
{
    public Sbjkyqwrk()
    {
    }

    /**
     * �޽�����
     */
    private Timestamp xjrq;

    /**
     * ˰������
     */
    private String szmc;

    /**
     * ˰�ִ���
     */
    private String szdm;

    /**
     * ˰��˰Ŀ����
     */
    private String szsmmc;

    /**
     * ˰��˰Ŀ����
     */
    private String szsmdm;

    /**
     * ˰��������ʼ����
     */
    private Timestamp skssksrq;

    /**
     * ˰��������������
     */
    private Timestamp skssjsrq;

    /**
     * ʵ��˰��
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