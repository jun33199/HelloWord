package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;

public class GtgshwszInfo implements Serializable
{
    private Timestamp hzrq;
    String jzbz;
    Timestamp skssksrq;
    String nsrmc;
    String ysjcdm;
    Timestamp cjrq;
    BigDecimal yjhkc;
    String szdm;
    BigDecimal jsje;
    BigDecimal kssl;
    String szsmdm;
    String szmc;
    String wszh;
    String pzzldm;
    BigDecimal sjse;
    String yskmdm;
    String yskmmc;
    String szsmmc;
    BigDecimal sl;
    String swjgzzjgdm;
    String jsjdm;
    String nsrjsjdm;
    Timestamp skssjsrq;
    String nd;
    private String wszxh;
    private java.sql.Timestamp lrrq;
    private String qxdm;
    private String ndzb;

    public GtgshwszInfo()
    {
    }

    public void setData(Gtgshwszmx mx)
    {
        this.setCjrq(mx.getCjrq());
        this.setJsjdm(mx.getJsjdm());
        this.setJsje(mx.getJsje());
        this.setJzbz(mx.getJzbz());
        this.setKssl(mx.getKssl());
        this.setLrrq(mx.getLrrq());
        this.setNd(mx.getNd());
        this.setNdzb(mx.getNdzb());
        this.setNsrjsjdm(mx.getNsrjsjdm());
        this.setNsrmc(mx.getNsrmc());
        this.setPzzldm(mx.getPzzldm());
        this.setQxdm(mx.getQxdm());
        this.setSjse(mx.getSjse());
        this.setSkssjsrq(mx.getSkssjsrq());
        this.setSkssksrq(mx.getSkssksrq());
        this.setSl(mx.getSl());
        this.setSwjgzzjgdm(mx.getSwjgzzjgdm());
        this.setSzdm(mx.getSzdm());
        this.setSzmc(mx.getSzmc());
        this.setSzsmdm(mx.getSzsmdm());
        this.setSzsmmc(mx.getSzsmmc());
        this.setWszh(mx.getWszh());
        this.setWszxh(mx.getWszxh());
        this.setYjhkc(mx.getYjhkc());
        this.setYsjcdm(mx.getYsjcdm());
        this.setYskmdm(mx.getYskmdm());
        this.setYskmmc(mx.getYskmmc());
    }

    public String getJzbz()
    {
        return jzbz;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public String getYsjcdm()
    {
        return ysjcdm;
    }

    public Timestamp getCjrq()
    {
        return cjrq;
    }

    public String getSzdm()
    {
        return szdm;
    }

    public String getSzsmdm()
    {
        return szsmdm;
    }

    public String getSzmc()
    {
        return szmc;
    }

    public String getWszh()
    {
        return wszh;
    }

    public String getPzzldm()
    {
        return pzzldm;
    }

    public String getYskmdm()
    {
        return yskmdm;
    }

    public String getSzsmmc()
    {
        return szsmmc;
    }

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getNsrjsjdm()
    {
        return nsrjsjdm;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public void setJzbz(String _jzbz)
    {
        jzbz = _jzbz;
    }

    public void setSkssksrq(Timestamp _skssksrq)
    {
        skssksrq = _skssksrq;
    }

    public void setNsrmc(String _nsrmc)
    {
        nsrmc = _nsrmc;
    }

    public void setYsjcdm(String _ysjcdm)
    {
        ysjcdm = _ysjcdm;
    }

    public void setCjrq(Timestamp cjrq)
    {
        this.cjrq = cjrq;
    }

    public void setSzdm(String _szdm)
    {
        szdm = _szdm;
    }

    public void setSzsmdm(String _szsmdm)
    {
        szsmdm = _szsmdm;
    }

    public void setSzmc(String _szmc)
    {
        szmc = _szmc;
    }

    public void setWszh(String _wszh)
    {
        wszh = _wszh;
    }

    public void setPzzldm(String _pzzldm)
    {
        pzzldm = _pzzldm;
    }

    public void setYskmdm(String _yskmdm)
    {
        yskmdm = _yskmdm;
    }

    public void setSzsmmc(String _szsmmc)
    {
        szsmmc = _szsmmc;
    }

    public void setSwjgzzjgdm(String _swjgzzjgdm)
    {
        swjgzzjgdm = _swjgzzjgdm;
    }

    public void setJsjdm(String _jsjdm)
    {
        jsjdm = _jsjdm;
    }

    public void setNsrjsjdm(String _nsrjsjdm)
    {
        nsrjsjdm = _nsrjsjdm;
    }

    public void setSkssjsrq(Timestamp _skssjsrq)
    {
        skssjsrq = _skssjsrq;
    }

    public BigDecimal getJsje()
    {
        return jsje;
    }

    public void setJsje(BigDecimal jsje)
    {
        this.jsje = jsje;
    }

    public BigDecimal getKssl()
    {
        return kssl;
    }

    public void setKssl(BigDecimal kssl)
    {
        this.kssl = kssl;
    }

    public BigDecimal getSjse()
    {
        return sjse;
    }

    public void setSjse(BigDecimal sjse)
    {
        this.sjse = sjse;
    }

    public BigDecimal getSl()
    {
        return sl;
    }

    public void setSl(BigDecimal sl)
    {
        this.sl = sl;
    }

    public BigDecimal getYjhkc()
    {
        return yjhkc;
    }

    public void setYjhkc(BigDecimal yjhkc)
    {
        this.yjhkc = yjhkc;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public String getWszxh()
    {
        return wszxh;
    }

    public void setWszxh(String wszxh)
    {
        this.wszxh = wszxh;
    }

    public java.sql.Timestamp getLrrq()
    {
        return lrrq;
    }

    public void setLrrq(java.sql.Timestamp lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getQxdm()
    {
        return qxdm;
    }

    public void setQxdm(String qxdm)
    {
        this.qxdm = qxdm;
    }

    public String getNdzb()
    {
        return ndzb;
    }

    public void setNdzb(String ndzb)
    {
        this.ndzb = ndzb;
    }

    public String getYskmmc()
    {
        return yskmmc;
    }

    public void setYskmmc(String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public Timestamp getHzrq()
    {
        return hzrq;
    }

    public void setHzrq(Timestamp hzrq)
    {
        this.hzrq = hzrq;
    }

}