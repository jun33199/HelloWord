package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class JksInfor
    implements Serializable {
  public JksInfor() {
  }

  public JksInfor(String jkpzh, BigDecimal je) {
    this.jkpzh = jkpzh;
    this.je = je;
  }

  //缴款凭证号
  private String jkpzh;

  private BigDecimal je;

  public BigDecimal getJe() {
    return je;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public void setJe(BigDecimal je) {
    this.je = je;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }
}