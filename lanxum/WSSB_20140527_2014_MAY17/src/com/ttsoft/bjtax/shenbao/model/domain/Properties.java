/**
 * Copyright (c) Beans factory Pte Ltd. All Rights Reserved.
 * Generated: 13-����-2004 11:46:29
 *
 * <p>
 * This SOURCE CODE FILE, which has been generated by Designer for use
 * ONLY by licensed users of the product, includes CONFIDENTIAL and
 * PROPRIETARY information of Beans Factory.
 *
 */
package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
//import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
* This is the entity / business object
* We will use this business object to demonstrate OR Mapping in Kernl can
* be used for composition.
**/
public class Properties implements ORObject {

	String zxbs;
	String propertyname;
	String propertyvalue;
	public Properties () {}
	public String getZxbs() {
		return zxbs;
	}
	public String getPropertyname() {
		return propertyname;
	}
	public String getPropertyvalue() {
		return propertyvalue;
	}
	public void setZxbs( String _zxbs ) {
		zxbs = _zxbs;
	}
	public void setPropertyname( String _propertyname ) {
		propertyname = _propertyname;
	}
	public void setPropertyvalue( String _propertyvalue ) {
		propertyvalue = _propertyvalue;
	}

}