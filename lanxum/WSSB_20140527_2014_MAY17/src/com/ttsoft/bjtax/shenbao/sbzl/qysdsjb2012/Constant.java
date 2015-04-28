package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 08企业所得税常量类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
public class Constant
{
	
	public static String JUMP_FLAG_NAME="QYSDS_2008_JUMP_FLAG_NAME";
	
    /**
     * 企业所得税月(季)度预缴纳税申报表(A类)id
     */
    public static String TABLE_ID_CZZSSDS_2008 = "28";
    public static String TABLE_NAME_CZZSSDS_2008 = "企业所得税月(季)度预缴纳税申报表(A类)";

    /**
     * 企业所得税月（季）度预缴纳税申报表（B类）id
     */
    public static String TABLE_ID_HDZSSDS_2008 = "29";
    public static String TABLE_NAME_HDZSSDS_2008 = "企业所得税月（季）度预缴纳税申报表（B类）id";

    /**
     * 企业所得税汇总纳税分支机构分配表id
     */
    public static String TABLE_ID_ZFJGSDS_2008 = "30";
    public static String TABLE_NAME_ZFJGSDS_2008 = "企业所得税汇总纳税分支机构分配表";

    /**
     * 08企业所得税汇总纳税分支机构分配表季报业务代码
     * added by tum 2008-3-7
     */
    public static String CA_YWLXDM_ZFJGSDSJB_2008 = "010030";
    public static String CA_SCHEMADM_ZFJGSDSJB_2008 = "20080307";
    public static String CA_XSLTDM_ZFJGSDSJB_2008 = "20080307";
    public static String ZFJGSDSJB_2008_MAX_ROW = "rows";
    public static int ZFJGSDSJB_2008_DEFAULT_MX_ROW_NUMBER = 15;


	/**
	 * 2008核定征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSJB_2008="010029";
	public static String CA_SCHEMADM_HDZSSDSJB_2008="20080307";
	public static String CA_XSLTDM_HDZSSDSJB_2008="20080307";
	



	/**
	 * 2008查帐征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_CZZSSDSJB_2008="010031";
	public static String CA_SCHEMADM_CZZSSDSJB_2008="20080307";
	public static String CA_XSLTDM_CZZSSDSJB_2008="20080307";
	
	
	/******************begin add by gaoyh 2012-03-13**************************/
	/**
	 * 2012企业所得税季报版本号
	 */
	public static String QYSDSJB_VERSION_2012 = "20120101";
	/**
	 * 2012查帐征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_CZZSSDSJB_2012="010031";
	public static String CA_SCHEMADM_CZZSSDSJB_2012="20120330";
	public static String CA_XSLTDM_CZZSSDSJB_2012="20120330";
	
    /**
     * 2012企业所得税汇总纳税分支机构分配表季报业务代码
     */
    public static String CA_YWLXDM_ZFJGSDSJB_2012 = "010030";
    public static String CA_SCHEMADM_ZFJGSDSJB_2012 = "20120330";
    public static String CA_XSLTDM_ZFJGSDSJB_2012 = "20120330";


	/**
	 * 2012核定征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSJB_2012="010029";	
	public static String CA_SCHEMADM_HDZSSDSJB_2012="20120330";
	public static String CA_XSLTDM_HDZSSDSJB_2012="20120330";
	
	/******************end add by gaoyh 2012-03-13***************************/

    /**
     * 核定征收所得税年报业务代码
     */
//    public static String CA_YWLXDM_HDZSSDSNB="010024";
//    public static String CA_SCHEMADM_HDZSSDSNB="20061102";
//    public static String CA_XSLTDM_HDZSSDSNB="20061102";

    /**
     * 核定征收所得税季报业务代码
     */
//    public static String CA_YWLXDM_HDZSSDSJB="010023";

    /**
     * 查账征收所得税季报业务代码
     */
//    public static String CA_YWLXDM_CZZSSDSJB="010025";
//    public static String CA_SCHEMADM_CZZSSDSJB="20061102";
//    public static String CA_XSLTDM_CZSSDSJB="20061102";

    /**
     * 查账征收所得税年报业务代码
     */
//	public static String CA_YWLXDM_CZZSSDSNB="010026";


    /**
     *  纳税人基本信息表业务代码
     */
//    public static String CA_YWLXDM_NSRJBXXB="010027";
//    public static String CA_SCHEMADM_NSRJBXXB="20061102";
//    public static String CA_XSLTDM_NSRJBXXB="20061102";

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
     /**
      * 汇总纳税方法相关常量及提示信息
      */
     //汇总纳税
     public final static String HZNSFF_QYSDSJB2008_CZZSSDS_HZNS = "1";
     //独立纳税
     public final static String HZNSFF_QYSDSJB2008_CZZSSDS_DLNS = "2";
     //汇总纳税方式-总机构
     public final static String HZNSFS_QYSDSJB2008_CZZSSDS_ZJG = "1";
     //汇总纳税方式-分支机构
     public final static String HZNSFS_QYSDSJB2008_CZZSSDS_FZJG = "2";
     /**
      * 校验结果及提示
      */
     //汇总纳税-总机构
     public final static int CHECK_HZNSFF_TYPE_HZNS_ZJG = -1;
     //没有数据
     public final static int CHECK_HZNSFF_TYPE_NO_DATA = 0;
     public final static String CHECK_HZNSFF_MESSAGE_NO_DATA = "该企业尚未填报查帐征收季度申报表，不能在此录入，请先录入查帐征收季度申报表！";
     //独立纳税
     public final static int CHECK_HZNSFF_TYPE_DLNS = 1;
     public final static String CHECK_HZNSFF_MESSAGE_DLNS = "该企业的查帐征收汇总纳税方法为独立纳税，不能在此录入！";
     //汇总纳税-分支机构
     public final static int CHECK_HZNSFF_TYPE_HZNS_FZJG = 2;
     public final static String CHECK_HZNSFF_MESSAGE_FZJG = "该企业的查帐征收汇总纳税方法为分支机构缴纳，不能在此录入！";
     
     

}
