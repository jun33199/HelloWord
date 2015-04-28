/**
 * Copyright (c) Beans factory Pte Ltd. All Rights Reserved.
 * Generated: 21-����-2006 13:07:56
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
public class CZFWJBXX implements ORObject {

	String lrr;
	Timestamp fhrq;
	String fwzl;
	String cqzsh;
	double nzjsr;
	String fhr;
	String cjr;
	Timestamp lrrq;
	String id;
	String fhbs;
	String djbh;
	double nynse;
	String bz;
	Timestamp cjrq;
	String swjgzzjgdm;
	Timestamp tbrq;
	String jsjdm;
	String qxdm;
	
	/**
	 * ���ⷿ��ԭֵ
	 * add by wofei 2009-2-3
	 */
	double czfwyz;
	/**
	 * ���г��۸�����˳������ھ�ס��ס��
	 * add by wofei 2009-2-3
	 */
	String xgrczbs;
	
	public CZFWJBXX () {}
	public String getLrr() {
		return lrr;
	}
	public Timestamp getFhrq() {
		return fhrq;
	}
	public String getFwzl() {
		return fwzl;
	}
	public String getCqzsh() {
		return cqzsh;
	}
	public double getNzjsr() {
		return nzjsr;
	}
	public String getFhr() {
		return fhr;
	}
	public String getCjr() {
		return cjr;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public String getId() {
		return id;
	}
	public String getFhbs() {
		return fhbs;
	}
	public String getDjbh() {
		return djbh;
	}
	public double getNynse() {
		return nynse;
	}
	public String getBz() {
		return bz;
	}
	public Timestamp getCjrq() {
		return cjrq;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public Timestamp getTbrq() {
		return tbrq;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setLrr( String _lrr ) {
		lrr = _lrr;
	}
	public void setFhrq( Timestamp _fhrq ) {
		fhrq = _fhrq;
	}
	public void setFwzl( String _fwzl ) {
		fwzl = _fwzl;
	}
	public void setCqzsh( String _cqzsh ) {
		cqzsh = _cqzsh;
	}
	public void setNzjsr( double _nzjsr ) {
		nzjsr = _nzjsr;
	}
	public void setNzjsr( BigDecimal _nzjsr ) {
		nzjsr = _nzjsr.doubleValue();
	}
	public void setFhr( String _fhr ) {
		fhr = _fhr;
	}
	public void setCjr( String _cjr ) {
		cjr = _cjr;
	}
	public void setLrrq( Timestamp _lrrq ) {
		lrrq = _lrrq;
	}
	public void setId( String _id ) {
		id = _id;
	}
	public void setFhbs( String _fhbs ) {
		fhbs = _fhbs;
	}
	public void setDjbh( String _djbh ) {
		djbh = _djbh;
	}
	public void setNynse( double _nynse ) {
		nynse = _nynse;
	}
	public void setNynse( BigDecimal _nynse ) {
		nynse = _nynse.doubleValue();
	}
	public void setBz( String _bz ) {
		bz = _bz;
	}
	public void setCjrq( Timestamp _cjrq ) {
		cjrq = _cjrq;
	}
	public void setSwjgzzjgdm( String _swjgzzjgdm ) {
		swjgzzjgdm = _swjgzzjgdm;
	}
	public void setTbrq( Timestamp _tbrq ) {
		tbrq = _tbrq;
	}
	public void setJsjdm( String _jsjdm ) {
		jsjdm = _jsjdm;
	}
	public void setQxdm( String _qxdm ) {
		qxdm = _qxdm;
	}
	public String getXgrczbs() {
		return xgrczbs;
	}
	public void setXgrczbs(String xgrczbs) {
		this.xgrczbs = xgrczbs;
	}
	public double getCzfwyz() {
		return czfwyz;
	}
	public void setCzfwyz(double czfwyz) {
		this.czfwyz = czfwyz;
	}
	public void setCzfwyz(BigDecimal czfwyz) {
		this.czfwyz = czfwyz.doubleValue();
	}
}