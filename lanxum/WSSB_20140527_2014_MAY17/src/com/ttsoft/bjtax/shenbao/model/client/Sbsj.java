package com.ttsoft.bjtax.shenbao.model.client;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 其他模块调用申报模块的缴款数据生成的数据类</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Stone
 * @version 1.0 2003-8-28
 */

public class Sbsj implements Serializable
{
    public Sbsj()
    {
    }

    //税种税目代码；
    String szsmdm;
    //税种税目名称
    String szsmmc;
    //计算机代码
    String jsjdm;
    //纳税人名称
    String nsrmc;
    //金额
    BigDecimal je;
    //税款类型代码
    String sklxdm;
    //税款类型名称
    String sklxmc;
    //征收机关代码
    String zsjgdm;
    //征收机关名称
    String zsjgmc;
    //限缴日期
    Timestamp xjrq;
    //税种代码
    String szdm;
    //税种名称
    String szmc;
    //录入人
    String lrr;
    //税款所属开始日期
    Timestamp skssksrq;
    //税款所属结束日期
    Timestamp skssjsrq;
    //数据来源
    String sjly;
    //备注
    String bz;

    //add by wanghw :(
    //预算级次代码
    String ysjcdm;
    //国家标准行业
    String gjbzhydm;
    //登记注册类型代码
    String djzclxdm;
    //隶属关系代码
    String lsgxdm;

    public BigDecimal getJe()
    {
        return je;
    }

    public void setJe(BigDecimal je)
    {
        this.je = je;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public String getSklxdm()
    {
        return sklxdm;
    }

    public String getLrr()
    {
        return lrr;
    }

    public String getSklxmc()
    {
        return sklxmc;
    }

    public String getSzdm()
    {
        return szdm;
    }

    public String getSzmc()
    {
        return szmc;
    }

    public String getSzsmdm()
    {
        return szsmdm;
    }

    public String getSzsmmc()
    {
        return szsmmc;
    }

    public Timestamp getXjrq()
    {
        return xjrq;
    }

    public String getZsjgdm()
    {
        return zsjgdm;
    }

    public String getZsjgmc()
    {
        return zsjgmc;
    }

    public void setZsjgmc(String zsjgmc)
    {
        this.zsjgmc = zsjgmc;
    }

    public void setZsjgdm(String zsjgdm)
    {
        this.zsjgdm = zsjgdm;
    }

    public void setXjrq(Timestamp xjrq)
    {
        this.xjrq = xjrq;
    }

    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }

    public void setSzmc(String szmc)
    {
        this.szmc = szmc;
    }

    public void setSzdm(String szdm)
    {
        this.szdm = szdm;
    }

    public void setSklxmc(String sklxmc)
    {
        this.sklxmc = sklxmc;
    }

    public void setSklxdm(String sklxdm)
    {
        this.sklxdm = sklxdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getSjly()
    {
        return sjly;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }

    public void setSjly(String sjly)
    {
        this.sjly = sjly;
    }

    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getBz()
    {
        return bz;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public String getYsjcdm()
    {
        return ysjcdm;
    }

    public void setYsjcdm(String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public String getDjzclxdm()
    {
        return djzclxdm;
    }

    public String getGjbzhydm()
    {
        return gjbzhydm;
    }

    public void setDjzclxdm(String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }

    public void setGjbzhydm(String gjbzhydm)
    {
        this.gjbzhydm = gjbzhydm;
    }

    public String getLsgxdm()
    {
        return lsgxdm;
    }

    public void setLsgxdm(String lsgxdm)
    {
        this.lsgxdm = lsgxdm;
    }
}