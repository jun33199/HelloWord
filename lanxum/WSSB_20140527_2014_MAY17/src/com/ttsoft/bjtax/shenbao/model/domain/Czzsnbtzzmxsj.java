

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

public class Czzsnbtzzmxsj implements ORObject
{
    String nd;
    String jsjdm;
    String zjlxdm;
    String zjhm;
    String hc;
    String swjgzzjgdm;
    java.math.BigDecimal bqljs;
    String qxdm;
    Timestamp cjrq;
    Timestamp lrrq;

    public Czzsnbtzzmxsj()
    {
    }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public String getZjlxdm()
    {
        return zjlxdm;
    }
    public void setZjlxdm(String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }
    public String getZjhm()
    {
        return zjhm;
    }
    public void setZjhm(String zjhm)
    {
        this.zjhm = zjhm;
    }
    public String getHc()
    {
        return hc;
    }
    public void setHc(String hc)
    {
        this.hc = hc;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public java.math.BigDecimal getBqljs()
    {
        return bqljs;
    }
    public void setBqljs(java.math.BigDecimal bqljs)
    {
        this.bqljs = bqljs;
    }
    public Timestamp getCjrq()
    {
        return cjrq;
    }
    public void setCjrq(Timestamp cjrq)
    {
        this.cjrq = cjrq;
    }
    public Timestamp getLrrq()
    {
        return lrrq;
    }
    public void setLrrq(Timestamp lrrq)
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

}