package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
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

  //�ɿ�ƾ֤��
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