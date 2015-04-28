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


public class Yhsgmmx implements ORObject {
   String xspzh;
   String dsjsjdm;
   String spmzdm;
   String jsjdm;
   String dwmc;
   String gjdqdm;
   String zjlxdm;
   String zjhm;
   String swjgzzjgdm;
   String nd;
   BigDecimal je;
   BigDecimal spmzje;
   BigDecimal gpsl;
    private java.sql.Timestamp cjrq;
    private java.sql.Timestamp lrrq;
    private String qxdm;
  public Yhsgmmx() {
  }
  public String getXspzh() {
    return xspzh;
  }
  public String getDsjsjdm() {
    return dsjsjdm;
  }
  public String getSpmzdm() {
    return spmzdm;
  }
  public String getJsjdm() {
    return jsjdm;
  }
  public String getDwmc() {
    return dwmc;
  }
  public String getGjdqdm() {
    return gjdqdm;
  }
  public String getZjlxdm() {
    return zjlxdm;
  }
  public String getZjhm() {
    return zjhm;
  }
  public String getSwjgzzjgdm() {
  return swjgzzjgdm;
}

  public java.math.BigDecimal getSpmzje() {
    return spmzje;
  }
  public java.math.BigDecimal getGpsl() {
    return gpsl;
  }
  public java.math.BigDecimal getJe() {
    return je;
  }
  public void setDsjsjdm(String _dsjsjdm) {
    dsjsjdm = _dsjsjdm;
  }
  public void setDwmc(String _dwmc) {
    dwmc = _dwmc;
  }
  public void setGjdqdm(String _gjdqdm) {
    gjdqdm = _gjdqdm;
  }
  public void setJsjdm(String _jsjdm) {
    jsjdm = _jsjdm;
  }
  public void setSpmzdm(String _spmzdm) {
    spmzdm = _spmzdm;
  }
  public void setXspzh(String _xspzh) {
    xspzh = _xspzh;
  }
  public void setZjhm(String _zjhm) {
    zjhm = _zjhm;
  }
  public void setZjlxdm(String _zjlxdm) {
    zjlxdm = _zjlxdm;
  }

  public void setSwjgzzjgdm(String _swjgzzjgdm) {
    swjgzzjgdm = _swjgzzjgdm;
  }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public java.sql.Timestamp getCjrq()
    {
        return cjrq;
    }
    public void setCjrq(java.sql.Timestamp cjrq)
    {
        this.cjrq = cjrq;
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
    public void setGpsl(java.math.BigDecimal gpsl)
    {
        this.gpsl = gpsl;
    }
    public void setJe(java.math.BigDecimal je)
    {
        this.je = je;
    }
    public void setSpmzje(java.math.BigDecimal spmzje)
    {
        this.spmzje = spmzje;
    }

}