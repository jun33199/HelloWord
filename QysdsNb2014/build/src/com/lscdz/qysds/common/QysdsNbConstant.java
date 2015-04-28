package com.lscdz.qysds.common;

public class QysdsNbConstant {

	//应用类型
	public static String APP_AID_QYSDSNB = "001"; // 企业所得税年报
	//报表版本号
	public static String REPORT_VERSION_2009="20090101";
	//纳税人基本信息2014
	public static String REPORT_VERSION_2014="20140101";
	//序列号
	public static String SEQ_SB_QYSDSNB="SBDB.SEQ_SB_QYSDSNB";
	
	public static final String ERROR_TYPE_LOGINERROR = "LOGIN_ERROR";

	public static final String ERROR_TYPE_SYSTEMERROR = "SYSTEM_ERROR";

	public static final String ERROR_TYPE_CHECKERROR = "CHECK_ERROR";

	public static final String ERROR_TYPE_NODATAFOUND = "NO_DATA_FOUND";
	
	//系统方式-网上
    public static String CREPORTS_SYSTEM_FS_WANGSHANG = "5";
    //系统方式-上门
    public static String CREPORTS_SYSTEM_FS_SHANGMENG = "1";
	
	//申报表代码
	public static final int DMFB5 = 10;//附表5
	public static final int DMFB5FB = 17;//附表5（1）
	
	public final static String QYSDS_SQLX_WS_CODE="0";
    public final static String QYSDS_SQLX_WS_NAME="网上申请";
    
    public final static String QYSDS_SQLX_SM_CODE="1";
    public final static String QYSDS_SQLX_SM_NAME="上门申请";
	
	/**
     * 填充项前后，用此分隔符。
     */
    public static final String QMYJ_DZYJ_SEPERATOR = "\r\n";
    /**
     * 原始数据前后填充项
     */
    public static final int QMYJ_DZYJ_ITEM_TYPE_ORI_DATA = 1;
    public static final String QMYJ_DZYJ_ORI_BEGIN = "-----Begin Original Data-----";
    public static final String QMYJ_DZYJ_ORI_END = "-----End Original Data-----";
    /**
     * 纳税人证书信息前后填充项
     */
    public static final int QMYJ_DZYJ_ITEM_TYPE_CLIENT_CERT = 2;
    public static final String QMYJ_DZYJ_CLIENT_CERT_BEGIN = "-----Begin Client Certificate Info-----";
    public static final String QMYJ_DZYJ_CLIENT_CERT_END = "-----End Client Certificate Info-----";
    /**
     * 纳税人签名前后填充项
     */
    public static final int QMYJ_DZYJ_ITEM_TYPE_CLIENT_SIGN = 3;
    public static final String QMYJ_DZYJ_CLIENT_SIGN_BEGIN = "-----Begin Client Sign Data-----";
    public static final String QMYJ_DZYJ_CLIENT_SIGN_END = "-----End Client Sign Data-----";
    
    /**
     * 服务器证书信息前后填充项
     */
    public static final int QMYJ_DZYJ_ITEM_TYPE_SERVER_CERT = 4;
    public static final String QMYJ_DZYJ_SERVER_CERT_BEGIN = "-----Begin Server Certificate Info-----";
    public static final String QMYJ_DZYJ_SERVER_CERT_END = "-----End Server Certificate Info-----";

    /**
     * 服务器回执签名时的时间
     */     
    public static final int QMYJ_DZYJ_ITEM_TYPE_SERVER_TIME = 5;
    public static final String QMYJ_DZYJ_SERVER_TIME_BEGIN = "-----Begin Server TimeStamp-----";
    public static final String QMYJ_DZYJ_SERVER_TIME_END = "-----End Server TimeStamp-----";
    
    /**
     * 服务器签名前后填充项
     */
    public static final int QMYJ_DZYJ_ITEM_TYPE_SERVER_SIGN = 6;
    public static final String QMYJ_DZYJ_SERVER_SIGN_BEGIN = "-----Begin Server Sign Data-----";
    public static final String QMYJ_DZYJ_SERVER_SIGN_END = "-----End Server Sign Data-----";
    //new add  20150115
    /**
     * 跨省市分支机构纳税人
     */
    public final static String CODE_QYSDS_ZGFWJD_KSSFZJG = "03";
    /**
	 * 总分机构均在本市的分支机构纳税人
	 */
	public final static String CODE_QYSDS_ZGFWJD_OTHER = "05";
	/**
     * 纯益率征收
     */
    public static final String ZSFSDM_CYLZS = "01";
    public static final String ZSFSNAME_CYLZS = "纯益率征收";
    /**
     * 定额征收
     */
    public static final String ZSFSDM_DEZS = "02";
    public static final String ZSFSNAME_DEZS = "定额征收";
    /**
     * 查账征收
     */
    public static final String ZSFSDM_CZZS = "03";
    public static final String ZSFSNAME_CZZS = "查账征收";
    /**
     * 内外资分类代码：内资
     */
    public static final String DJZCLXDM_NWZFLDM_NZ = "0";

    /**
     * 内外资分类代码：港澳台
     */
    public static final String DJZCLXDM_NWZFLDM_GAT = "1";

    /**
     * 内外资分类代码：外资
     */
    public static final String DJZCLXDM_NWZFLDM_WZ = "2";
    /**
     * 内外资分类代码：个体经营
     */
    public static final String DJZCLXDM_NWZFLDM_GTJJ = "3";
    /**
     * 自然人标志
     */
    public static final String YHLB_ZRR="03";

	//end
	
}
	
