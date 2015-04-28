package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class Qysdsjd implements ORObject
{
    String jsjdm;
    String nd;
    String jd;
    java.sql.Timestamp cjrq;
    java.sql.Timestamp lrrq;
    java.sql.Timestamp sbrq;
    java.sql.Timestamp skssksrq;
    java.sql.Timestamp skssjsrq;
    String swjgzzjgdm;
    String lrr;
    String fsdm;
    String jkpzh;
    java.math.BigDecimal srze;
    java.math.BigDecimal lrze;
    java.math.BigDecimal sl;
    java.math.BigDecimal ynsdse;
    java.math.BigDecimal qcwjsdse;
    java.math.BigDecimal jmsdse;
    java.math.BigDecimal cbyqndse;
    java.math.BigDecimal sjyjsdsse;
    java.math.BigDecimal bqyjsdse;
    java.math.BigDecimal sjybsdse;
    String qxdm;
   java.math.BigDecimal mbyqndks;
   java.math.BigDecimal bkhlrze;

    public Qysdsjd()
    {
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getNd()
    {
        return nd;
    }
    public String getJd()
    {
        return jd;
    }
    public java.sql.Timestamp getCjrq()
    {
        return cjrq;
    }
    public java.sql.Timestamp getLrrq()
    {
        return lrrq;
    }
    public java.sql.Timestamp getSbrq()
    {
        return sbrq;
    }
    public java.sql.Timestamp getSkssksrq()
    {
        return skssksrq;
    }
    public java.sql.Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public String getLrr()
    {
        return lrr;
    }
    public String getFsdm()
    {
        return fsdm;
    }
    public String getJkpzh()
    {
        return jkpzh;
    }
    public java.math.BigDecimal getSrze()
    {
        return srze;
    }
    public java.math.BigDecimal getLrze()
    {
        return lrze;
    }
    public java.math.BigDecimal getSl()
    {
        return sl;
    }
    public java.math.BigDecimal getYnsdse()
    {
        return ynsdse;
    }
    public java.math.BigDecimal getQcwjsdse()
    {
        return qcwjsdse;
    }
    public java.math.BigDecimal getJmsdse()
    {
        return jmsdse;
    }
    public java.math.BigDecimal getCbyqndse()
    {
        return cbyqndse;
    }
    public java.math.BigDecimal getSjyjsdsse()
    {
        return sjyjsdsse;
    }
    public java.math.BigDecimal getBqyjsdse()
    {
        return bqyjsdse;
    }
    public java.math.BigDecimal getSjybsdse()
    {
        return sjybsdse;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public void setJd(String jd)
    {
        this.jd = jd;
    }
    public void setCjrq(java.sql.Timestamp cjrq)
    {
        this.cjrq = cjrq;
    }
    public void setLrrq(java.sql.Timestamp lrrq)
    {
        this.lrrq = lrrq;
    }
    public void setSbrq(java.sql.Timestamp sbrq)
    {
        this.sbrq = sbrq;
    }
    public void setSkssksrq(java.sql.Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public void setSkssjsrq(java.sql.Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }
    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }
    public void setJkpzh(String jkpzh)
    {
        this.jkpzh = jkpzh;
    }
    public void setSrze(java.math.BigDecimal srze)
    {
        this.srze = srze;
    }
    public void setLrze(java.math.BigDecimal lrze)
    {
        this.lrze = lrze;
    }
    public void setSl(java.math.BigDecimal sl)
    {
        this.sl = sl;
    }
    public void setYnsdse(java.math.BigDecimal ynsdse)
    {
        this.ynsdse = ynsdse;
    }
    public void setQcwjsdse(java.math.BigDecimal qcwjsdse)
    {
        this.qcwjsdse = qcwjsdse;
    }
    public void setJmsdse(java.math.BigDecimal jmsdse)
    {
        this.jmsdse = jmsdse;
    }
    public void setCbyqndse(java.math.BigDecimal cbyqndse)
    {
        this.cbyqndse = cbyqndse;
    }
    public void setSjyjsdsse(java.math.BigDecimal sjyjsdsse)
    {
        this.sjyjsdsse = sjyjsdsse;
    }
    public void setBqyjsdse(java.math.BigDecimal bqyjsdse)
    {
        this.bqyjsdse = bqyjsdse;
    }
    public void setSjybsdse(java.math.BigDecimal sjybsdse)
    {
        this.sjybsdse = sjybsdse;
    }
    public String getQxdm()
    {
        return qxdm;
    }
    public void setQxdm(String qxdm)
    {
        this.qxdm = qxdm;
    }
  public java.math.BigDecimal getMbyqndks() {
    return mbyqndks;
  }
  public void setMbyqndks(java.math.BigDecimal mbyqndks) {
    this.mbyqndks = mbyqndks;
  }
  public java.math.BigDecimal getBkhlrze() {
    return bkhlrze;
  }
  public void setBkhlrze(java.math.BigDecimal bkhlrze) {
    this.bkhlrze = bkhlrze;
  }

}