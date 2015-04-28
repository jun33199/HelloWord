package com.lscdz.qysds.common.service.qysds.bo.qysds;

import com.lscdz.qysds.common.service.qysds.Constants;

/**
 * 企业所得税报表常量定义
 * 项目名称：QysdsNb2014   
 * 类名称：QysdsReportsConstants   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:10:49   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:10:49   
 * 修改备注：   
 * @version  1.0
 */
public final class QysdsReportsConstants extends Constants {

	/**
	 * 应用ID，企业所得税
	 */
	public static String CREPORTS_APPID_QYSDS = "001";

	/**
	 * 当前版本号，企业所得税
	 */
	public static String CREPORTS_VERSION_QYSDS = "20140101";

	/**
	 * 审核标志，未通过审核
	 */
	public static String CREPORTS_CHECK_QYSDS_NOPASS = "0";

	/**
	 * 审核标志，已通过审核
	 */
	public static String CREPORTS_CHECK_QYSDS_PASS = "1";

	/**
	 * 企业所得税年度报表表ID列表
	 */
	public static String[] CREPORTS_TABLEIDS_QYSDS_ND = { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22" };

	public static String[] CREPORTS_TABLEIDS_QYSDS_ND_2014 = { "A100000","A101010","A101020","A102010","A102020","A103000","A104000","A105000","A105010",
		"A105020","A105030","A105040","A105050","A105060","A105070","A105080","A105081",
		"A105090","A105091","A105100","A105110","A105120","A106000","A107010","A107011",
		"A107012","A107013","A107014","A107020","A107030","A107040","A107041","A107042",
		"A107050","A108000","A108010","A108020","A108030","A109000","A109010","A200000","A300000" };
	/**
	 * 企业所得税季度报表表ID列表
	 */
	public static String[] CREPORTS_TABLEIDS_QYSDS_JD = { "23", "24", "25",
			"28", "29", "30", "31", "32" };

	/**
	 * 企业所得税年度报表存储主表名
	 */
	public static String CREPORTS_TABLEIDS_QYSDS_ND_TABLENAME = "SB_JL_QYSDSSBB_ZB_ND";

	/**
	 * 企业所得税年度报表存储子表名
	 */
	public static String CREPORTS_TABLEIDS_QYSDS_ND_CHILDTABLENAME = "SB_JL_QYSDSSBB_CB_ND";

	/**
	 * 企业所得税季度报表存储主表名
	 */
	public static String CREPORTS_TABLEIDS_QYSDS_JD_TABLENAME = "SB_JL_QYSDSSBB_ZB_JD";

	/**
	 * 企业所得税季度报表存储子表名
	 */
	public static String CREPORTS_TABLEIDS_QYSDS_JD_CHILDTABLENAME = "SB_JL_QYSDSSBB_CB_JD";

	public static final String ERROR_TYPE_LOGINERROR = "LOGIN_ERROR";

	public static final String ERROR_TYPE_SYSTEMERROR = "SYSTEM_ERROR";

	public static final String ERROR_TYPE_CHECKERROR = "CHECK_ERROR";

	public static final String ERROR_TYPE_NODATAFOUND = "NO_DATA_FOUND";
}
