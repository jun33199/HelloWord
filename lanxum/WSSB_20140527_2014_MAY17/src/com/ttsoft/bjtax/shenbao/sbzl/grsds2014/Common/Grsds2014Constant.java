/**
 * @Title:       Grsds2014Constant.java
 * @Description: TODO
 * @date:        2014-11-10上午10:27:45
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-10
 */
public class Grsds2014Constant {
	
	
	/* Description：session——————代码表—————— */
	public static final String SESSION_DM_SFZJLXS = "SESSION_DM_SFZJLXS";
	public static final String SESSION_DM_GJ = "SESSION_DM_GJ";
	public static final String SESSION_DM_GJBZHY = "SESSION_DM_GJBZHY";
	public static final String SESSION_DM_SSJGZZJG = "SESSION_DM_SSJGZZJG";
	public static final String SESSION_DM_DJZCLX = "SESSION_DM_DJZCLX";
	
	public static final String SESSION_GRLIST = "SESSION_GRLIST";
	public static final int SESSION_PAGE_NUM = 10;
	
	
	/* Description：XML样式表版本用以查找XSLT文件 2014版*/
	public static String XSLT_GRSDS_JBXXB_2014 = "grsdsjbxx2014";
	public static String XSLT_GRSDS_NDSBB_2014 = "grsdsndsbb2014";
	public static String XSLT_GRSDS_GRLIST_2014 = "grlist2014";
	
	public static String XSLT_GRSDS_DATE = "GRSDS_XSLT_GRSDS_DATE";
	public static String XSLT_GRSDS_VERSION = "GRSDS_XSLT_GRSDS_VERSION";
	public static String XSLT_GRSDS_SCHEMA_VERSION = "GRSDS_XSLT_GRSDS_SCHEMA_VERSION";
	
	
	public static final String XML_RESPONSE_Data = "strXMLData";
	
	/* Description：processor*/
	public static final String PROCESSOR_LIST="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.processor.GrlistProcessor";
	public static final String PROCESSOR_JBXXB="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.processor.GrsdsJbxxbProcessor";
	public static final String PROCESSOR_NDSBB="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.processor.GrsdsNdsbbProcessor";
	
	public static final int PROCESSOR_ACTION_QUERY = 0;
	public static final int PROCESSOR_ACTION_ADD = 1;
	public static final int PROCESSOR_ACTION_DELETE = 2;
	public static final int PROCESSOR_ACTION_SAVE = 3;	
}
