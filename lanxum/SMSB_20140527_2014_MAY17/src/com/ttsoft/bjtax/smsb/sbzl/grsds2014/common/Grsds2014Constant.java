/**
 * @Title:       Grsds2014Constant.java
 * @Description: TODO
 * @date:        2014-11-10����10:27:45
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.common;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-10
 */
public class Grsds2014Constant {
	
	
	/* Description��session��������������������������� */
	public static final String SESSION_DM_SFZJLXS = "SESSION_DM_SFZJLXS";
	public static final String SESSION_DM_GJ = "SESSION_DM_GJ";
	public static final String SESSION_DM_SWJGZZJG = "SESSION_DM_SWJGZZJG";
	public static final String SESSION_DM_GJBZHY = "SESSION_DM_GJBZHY";
	public static final String SESSION_DM_DJZCLX = "SESSION_DM_DJZCLX";

	
	public static final int SESSION_PAGE_NUM = 10;
	public static final String SESSION_GRLIST = "SESSION_GRLIST";
//	/* Description��XML��ʽ��汾���Բ���XSLT�ļ� 2014��*/
//	public static String XSLT_GRSDS_JBXXB_2014 = "grsdsjbxx2014";
//	public static String XSLT_GRSDS_NDSBB_2014 = "grsdsndsbb2014";
//	public static String XSLT_GRSDS_GRLIST_2014 = "grlist2014";
//	
//	public static String XSLT_GRSDS_DATE = "GRSDS_XSLT_GRSDS_DATE";
//	public static String XSLT_GRSDS_VERSION = "GRSDS_XSLT_GRSDS_VERSION";
//	public static String XSLT_GRSDS_SCHEMA_VERSION = "GRSDS_XSLT_GRSDS_SCHEMA_VERSION";
//	
//	
//	public static final String XML_RESPONSE_Data = "strXMLData";

	/* Description��processor*/
	public static final String PROCESSOR_LIST="com.ttsoft.bjtax.smsb.sbzl.grsds2014.processor.GrlistProcessor";
	public static final String PROCESSOR_LIST_CX="com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.processor.GrlistProcessor";
	public static final String PROCESSOR_JBXXB="com.ttsoft.bjtax.smsb.sbzl.grsds2014.processor.GrsdsJbxxbProcessor";
	public static final String PROCESSOR_JBXXB_CX="com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.processor.GrsdsJbxxbProcessor";
	public static final String PROCESSOR_NDSBB="com.ttsoft.bjtax.smsb.sbzl.grsds2014.processor.GrsdsNdsbbProcessor";
	public static final String PROCESSOR_NDSBB_CX="com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.processor.GrsdsNdsbbProcessor";
	
	public static final int PROCESSOR_ACTION_QUERY = 0;
	public static final int PROCESSOR_ACTION_ADD = 1;
	public static final int PROCESSOR_ACTION_DELETE = 2;
	public static final int PROCESSOR_ACTION_SAVE = 3;	
	
	

    /**
     * Description�����˶�����ҵ
     */
    public static final String DJZCLXDM_GRDZQY = "175";
    
    /**
     * Description�����˺ϻ���ҵ
     */
    public static final String DJZCLXDM_GRHHQY = "420";
    
    /**
     * �Ǽ�ע�����ʹ��룺˽Ӫ������ҵ
     */
    public static final String DJZCLXDM_SYDZQY = "171";

    /**
     * �Ǽ�ע�����ʹ��룺˽Ӫ�ϻ���ҵ
     */
    public static final String DJZCLXDM_SYHHQY = "172";
    
    /**
     * Description�����幤�̻�
     */
    public static final String DJZCLXDM_GTGSH = "410";
    
}
