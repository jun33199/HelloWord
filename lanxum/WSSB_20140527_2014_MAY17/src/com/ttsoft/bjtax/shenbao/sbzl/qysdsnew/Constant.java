package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew;

public class Constant {
	/**
	 * 核定征收年报报表id
	 */
	public static String NB="24";
        public static String WENNB="24";
        public static String NBFB="30";

        /**
         * 核定征收年报报表名称
         */
        public static String WENNB_TABLENAME = "中华人民共和国企业所得税年度纳税申报表(B类)主表";
        public static String NBFB_TABLENAME  ="附表一 税收优惠明细表";

         public static String CREPORTS_VERSION_HDQYSDSNB = "20080101";
	/**
	 * 核定征收季报报表id
	 */
	public static String JB="23";

	/**
	 * 查账征收季报报表id
	 */
	public static String CZJB="25";

	/**
	 * 查账征收年报报表id
	 */
	public static String CZNB="26";

	/**
	 * 纳税人基本信息表id
	 */
	public static String NSRJBXXB="27";


	/**
	 * 核定征收所得税年报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSNB="010024";
//	public static String CA_SCHEMADM_HDZSSDSNB="20061102";
//	public static String CA_XSLTDM_HDZSSDSNB="20061102";
	public static String CA_SCHEMADM_HDZSSDSNB="20091101";
	public static String CA_XSLTDM_HDZSSDSNB="20091101";

        /**
         * 核定征收所得税年报附表业务代码
         */
        public static String CA_YWLXDM_HDZSSDSFBNB="010024";
        public static String CA_SCHEMADM_HDZSSDSFBNB="20090115";
        public static String CA_XSLTDM_HDZSSDSFBNB="20090115";

	/**
	 * 核定征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSJB="010023";

	/**
	 * 查账征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_CZZSSDSJB="010025";
	public static String CA_SCHEMADM_CZZSSDSJB="20061102";
	public static String CA_XSLTDM_CZSSDSJB="20061102";

	/**
	 * 查账征收所得税年报业务代码
	 */
//	public static String CA_YWLXDM_CZZSSDSNB="010026";


	/**
	 *  纳税人基本信息表业务代码
	 */
	public static String CA_YWLXDM_NSRJBXXB="010027";
//	public static String CA_SCHEMADM_NSRJBXXB="20080101";
//	public static String CA_XSLTDM_NSRJBXXB="20080101";
	public static String CA_SCHEMADM_NSRJBXXB="20091101";
	public static String CA_XSLTDM_NSRJBXXB="20091101";
//	public static String REPORT_VERSION_NSRJBXXB="20080101";
//	 TODO: 网上申报纳税人基本信息表中版本常量
	public static String REPORT_VERSION_NSRJBXXB="20090101";

	/**
	 *  系统级别代码
	 */
	public static String SDS_1="1";
	public static String SDS_2="2";
	public static String SDS_3="3";


	/**
	 * 下载地址
	 */
	public static String REQUEST_LINK_QYSDS="REQUEST_LINK_QYSDS";


//	******************征收方式代码表*****************************************
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

	//***********************************************************


//	******************征收方式名称代码表*****************************************
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


	//***********************************************************

}
