
/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
package com.ttsoft.bjtax.shenbao.model.domain;

import com.ekernel.db.or.ORObject;
import java.sql.Timestamp;

public class Fs implements ORObject{
   String sbdm;
   String sbmc;
   String lrr;
   java.sql.Timestamp lrrq;
  public Fs() {
  }
  public String getSbdm() {
    return sbdm;
  }
  public String getSbmc() {
    return sbmc;
  }
  public String getLrr() {
    return lrr;
  }
  public java.sql.Timestamp getLrrq() {
    return lrrq;
  }
  public void setSbdm(String sbdm) {
    this.sbdm = sbdm;
  }
  public void setSbmc(String sbmc) {
    this.sbmc = sbmc;
  }
  public void setLrr(String lrr) {
    this.lrr = lrr;
  }
  public void setLrrq(java.sql.Timestamp lrrq) {
    this.lrrq = lrrq;
  }

}