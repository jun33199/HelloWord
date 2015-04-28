package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014;

public class QysdsHdzsConstant {
	/**
	 * 核定征收年报报表id
	 */
	public static String NB = "24";
	public static String WENNB = "24";

	/**
	 * 核定征收年报报表名称
	 */
	public static String WENNB_TABLENAME = "中华人民共和国企业所得税年度纳税申报表(B类)主表";

	public static String CREPORTS_VERSION_HDQYSDSNB_2014 = "20140101";
	/**
	 * 核定征收所得税年报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSNB = "010024";
	public static String CA_SCHEMADM_HDZSSDSNB_2014 = "20140915";
	public static String CA_XSLTDM_HDZSSDSNB_2014 = "20140915";

	/**
	 * 核定征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSJB="010023";
	/**
	 * 系统级别代码
	 */
	public static String SDS_1 = "1";
	public static String SDS_2 = "2";
	public static String SDS_3 = "3";


	// ******************征收方式代码表*****************************************
	/**
	 * 纯益率征收
	 */
	public static final String ZSFSDM_CYLZS = "01";

	/**
	 * 定额征收
	 */
	public static final String ZSFSDM_DEZS = "02";

	/**
	 * 查账征收
	 */
	public static final String ZSFSDM_CZZS = "03";
	/**
	 * 纯益率征收
	 */
	public static final String ZSFSNAME_CYLZS = "纯益率征收";

	/**
	 * 定额征收
	 */
	public static final String ZSFSNAME_DEZS = "定额征收";

	/**
	 * 查账征收
	 */
	public static final String ZSFSNAME_CZZS = "查账征收";

	/**
	 * 2012核定征收企业所得税年报处理器常量定义
	 */
	public static final String HDZSQYSDSNB_PROCESSOR_2014 = "com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.processor.HdzssdsnbProcessor";

}
