package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014;

/**
 * 
 * 项目名称：wssb_20140528   
 * 类名称：Constant   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-4-16 下午2:32:02   
 * 修改人：wangcy   
 * 修改时间：2014-4-16 下午2:32:02   
 * 修改备注：   
 * @version  1.0
 */
public class Constant
{
	
	public static String JUMP_FLAG_NAME="QYSDS_2014_JUMP_FLAG_NAME";
	
    /**
     * 企业所得税月(季)度预缴纳税申报表(A类)id
     */
    public static String TABLE_ID_CZZSSDS_2014 = "28";
    public static String TABLE_NAME_CZZSSDS_2014 = "企业所得税月(季)度预缴纳税申报表(A类)";
    /**
     * 保存在REQUEST中的返回地址2012版
     */
    public static final String REQ_KEY_RETURN_CZZSSDS_QYJB2014 ="/shenbao/czzsqyjb2014.dc?actionType=Show"; 
    public static final String REQ_KEY_RETURN_HDZSSDS_QYJB2014 ="/shenbao/hdzsqyjb2014.dc?actionType=Show";
    /**
     * 企业所得税月（季）度预缴纳税申报表（B类）id
     */
    public static String TABLE_ID_HDZSSDS_2014 = "29";
    public static String TABLE_NAME_HDZSSDS_2014 = "企业所得税月（季）度预缴纳税申报表（B类）id";
    public static String ZFJGSDSJB_2014_MAX_ROW = "rows";
    public static int ZFJGSDSJB_2014_DEFAULT_MX_ROW_NUMBER = 15;
    /**
     * 企业所得税汇总纳税分支机构分配表id
     */
    public static String TABLE_ID_ZFJGSDS_2014 = "30";
    public static String TABLE_NAME_ZFJGSDS_2014 = "企业所得税汇总纳税分支机构分配表";
    
    /**固定资产加速折旧（扣除）预缴情况统计表  ID、NAME,added by zhangj,2014.12.01*/
    public final static String TABLE_ID_GDZCJSZJYJQK_2014 = "33";
    public final static String TABLE_NAME_GDZCJSZJYJQK_2014 = "固定资产加速折旧（扣除）预缴情况统计表";


	/******************begin add by wangcy 2014-03-13**************************/
	/**
	 * 2014企业所得税季报版本号
	 */
	public static String QYSDSJB_VERSION_2014 = "20140101";
	/**
	 * 2014查帐征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_CZZSSDSJB_2014="010031";
	public static String CA_SCHEMADM_CZZSSDSJB_2014="20140603";
	public static String CA_XSLTDM_CZZSSDSJB_2014="20140603";
	
    /**
     * 2014企业所得税汇总纳税分支机构分配表季报业务代码
     */
    public static String CA_YWLXDM_ZFJGSDSJB_2014 = "010030";
    public static String CA_SCHEMADM_ZFJGSDSJB_2014 = "20140603";
    public static String CA_XSLTDM_ZFJGSDSJB_2014 = "20140603";


	/**
	 * 2014核定征收所得税季报业务代码
	 */
	public static String CA_YWLXDM_HDZSSDSJB_2014="010029";	
	public static String CA_SCHEMADM_HDZSSDSJB_2014="20140603";
	public static String CA_XSLTDM_HDZSSDSJB_2014="20140603";
	
	/******************end add by wangcy 2014-06-03***************************/



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
     
     /**
      * 固定资产加速折旧（扣除）预缴情况统计表业务代码2014,ADDED BY ZHANGJ 2014.12.01
      */
     public static String CA_XSLTDM_GDZCJSZJYJQKJB_2014 = "20141201";
     public static String CA_YWLXDM_GDZCJSZJYJQKJB_2014 = "010037";
     public static String CA_SCHEMADM_GDZCJSZJYJQKJB_2014 = "20141201";
     
     
     public static String CZZSSDSJB_CBSJ_MSSRXM="cbMssrxmList";
     public static String CZZSSDSJB_CBSJ_JZMZXM="cbJzmzxmList";
     public static String CZZSSDSJB_CBSJ_JMXM="cbJmxmList";
     
     public static String CZZSSDSJB_DM_MSSRXM="MssrxmDmList";
     public static String CZZSSDSJB_DM_JZMZXM="JzmzxmDmList";
     public static String CZZSSDSJB_DM_JMXM="JmxmDmList";
     
     public static String CZZSSDSJB_DM_MAPS="CZZSSDSJB_DM_MAPS";
}
