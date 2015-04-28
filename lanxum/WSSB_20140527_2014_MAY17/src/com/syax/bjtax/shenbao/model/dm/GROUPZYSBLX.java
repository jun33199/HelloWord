/***********************************************************************
 * Module:  SF_DM_ZYSBLX.java
 * Author:  guzx
 * Purpose: Defines the Class SF_DM_ZYSBLX
 ***********************************************************************/

package com.syax.bjtax.shenbao.model.dm;

import java.io.Serializable;
import java.util.*;

/** 专用设备类型代码�?
 * 
 * @pdOid a65536c1-705c-4324-bfc2-b353d82a07b8 */
public class GROUPZYSBLX  implements Serializable{
   /** 专用设备类型代码
    * 
    * @pdOid c65cf84c-5b03-459b-a05f-b51137924385 */
   public java.lang.String ZYSBLXDM;
   /** 专用设备类型名称
    * 
    * @pdOid f57e52c0-9b1e-4c68-8420-f867a7f3b444 */
   public java.lang.String ZYSBLXMC;
   public java.lang.String LEVEL;
   /** 创建�?
    * 
    * @pdOid cfb4677a-0e3d-4e64-aec0-7cbf203b6576 */
   
   /** 创建时间
    * 
    * @pdOid f40bfc8b-1da7-4160-917d-2a1f2c451e9a */
   
   /** 录入�?
    * 
    * @pdOid 3815304b-28aa-4aae-a437-9640e100796c */
   
   /** 录入时间
    * 
    * @pdOid fac2fab5-bebd-4b29-a0fa-2f21f2e7e50d */
   

/**
 * @return Returns the zYSBLXDM.
 */
public java.lang.String getZYSBLXDM() {
	return ZYSBLXDM;
}
/**
 * @param zysblxdm The zYSBLXDM to set.
 */
public void setZYSBLXDM(java.lang.String zysblxdm) {
	ZYSBLXDM = zysblxdm;
}
/**
 * @return Returns the zYSBLXMC.
 */
public java.lang.String getZYSBLXMC() {
	return ZYSBLXMC;
}
/**
 * @param zysblxmc The zYSBLXMC to set.
 */
public void setZYSBLXMC(java.lang.String zysblxmc) {
	ZYSBLXMC = zysblxmc;
}
/**
 * @return Returns the lEVEL.
 */
public java.lang.String getLEVEL() {
	return LEVEL;
}
/**
 * @param level The lEVEL to set.
 */
public void setLEVEL(java.lang.String level) {
	LEVEL = level;
}
}