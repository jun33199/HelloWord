package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014;

public class QyqssdsConstant {

	/**
	 * 查询
	 */
	public static final int INT_ACTION_QUERY = 1;

	/**
	 * 保存
	 */
	public static final int INT_ACTION_SAVE = 2;

	/**
	 * 删除
	 */
	public static final int INT_ACTION_DELETE = 3;
	/**
	 * 计算机代码
	 */
	public static String STRING_KEY_JSJDM = "JSJDM";

	/**
	 * 日期
	 */
	public static String STRING_KEY_DATE = "DATE";

	/**
	 * 登记数据对象
	 */
	public static String OBJECT_KEY_DJSJ = "DJSJ";
	
	public static String REPORT_VERSION_QYQSSDS_NSRJBXXB="20140101";
	/**
	 *  清算系统级别代码
	 */
	public static String QSSDS_1="1";
	public static String QSSDS_2="2";
	public static String QSSDS_3="3";
	/**
	 * 企业清算所得税备案
	 */
	public static String CA_YWLXDM_QYQSSDS = "010036";
	public static String CA_SCHEMADM_QYQSSDS = "20140101";
	public static String CA_XSLTDM_QYQSSDS = "20140101";
	public static String REPORT_VERSION_QYQSSDS = "20140101";
	
	public static String QYQSSDS_PROCESSOR="com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.processor.QyqssdsBaProcessor";
	/**
     * 保存在REQUEST中的返回地址
     */
    public static final String REQ_KEY_RETURN_QYQSSDSBA_SAVE =
        "/shenbao//qyqssdsba2014.dc?actionType=Show";
}
