/**
 * Copyright (c) Beans factory Pte Ltd. All Rights Reserved.
 * Generated: 10-����-2003 16:36:00
 *
 * <p>
 * This SOURCE CODE FILE, which has been generated by Designer for use
 * ONLY by licensed users of the product, includes CONFIDENTIAL and
 * PROPRIETARY information of Beans Factory.
 *
 */
package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
* This is the entity / business object
* We will use this business object to demonstrate OR Mapping in Kernl can
* be used for composition.
**/
public class Gtgshsdsmx implements ORObject {

	BigDecimal je;
	String jsjdm;
	Timestamp sbrq;
	String xmmc;
	Timestamp lrrq;
	Timestamp cjrq;
	String hc;
        String swjgzzjgdm;
        String nd;
    private String qxdm;
	public Gtgshsdsmx () {}
	public BigDecimal getJe() {
		return je;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public Timestamp getSbrq() {
		return sbrq;
	}
	public String getXmmc() {
		return xmmc;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public Timestamp getCjrq() {
        return cjrq;
	}
	public String getHc() {
		return hc;
	}
	public void setJe( BigDecimal _je ) {
		je = _je;
	}
	public void setJsjdm( String _jsjdm ) {
		jsjdm = _jsjdm;
	}
	public void setSbrq( Timestamp _sbrq ) {
		sbrq = _sbrq;
	}
	public void setXmmc( String _xmmc ) {
		xmmc = _xmmc;
	}
	public void setLrrq( Timestamp _lrrq ) {
		lrrq = _lrrq;
	}
	public void setCjrq(Timestamp cjrq) {
//		cjsj = _cjsj;
        this.cjrq = cjrq;
	}
	public void setHc( String _hc ) {
		hc = _hc;
	}
  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }
  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
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
