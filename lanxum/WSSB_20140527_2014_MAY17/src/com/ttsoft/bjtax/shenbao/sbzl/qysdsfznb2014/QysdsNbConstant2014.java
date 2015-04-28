package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 08企业所得税常量类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: </p>
 *
 * @author wangcy
 * @version 1.0
 */
public class QysdsNbConstant2014
{
	//暂时不知道干嘛使用
	public static String JUMP_FLAG_NAME="QYSDS_2014_JUMP_FLAG_NAME";
	public static String REQ_KEY_QYSBSNB_2014="REQ_KEY_QYSBSNB_2014";
	/**
	 * 2014查帐征收所得税年报业务代码
	 */
	public static String CA_YWLXDM_CZZSSDSNB_2014="010033";
	public static String CA_SCHEMADM_CZZSSDSNB_2014="20141203";
	public static String CA_XSLTDM_CZZSSDSNB_2014="20141203";
	
	
    /**
     * 2014企业所得税汇总纳税分支机构分配表季报业务代码
    */
    public static String CA_YWLXDM_ZFJGSDSNB_2014 = "010034";
    public static String CA_SCHEMADM_ZFJGSDSNB_2014 = "20141203";
    public static String CA_XSLTDM_ZFJGSDSNB_2014 = "20141203";
	
    public static String ZFJGSDSNB_2014_MAX_ROW = "rows";
    public static int ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER = 15;

	/**
    * 企业所得税年度预缴纳税申报表id
    */
   public static String TABLE_ID_CZZSSDSNB_2014 = "31";
   public static String TABLE_NAME_CZZSSDSNB_2014 = "企业所得税年度预缴纳税申报表";

   /**
    * 企业所得税汇总纳税分支机构分配表id
    */
   public static String TABLE_ID_ZFJGSDSNB_2014 = "32";
   public static String TABLE_NAME_ZFJGSDSNB_2014 = "企业所得税汇总纳税分支机构分配表";

    /**
	 * 2014企业所得税年报版本号
	 */
	public static String QYSDSNB_VERSION_2014 = "20140101";
	
	
	/**
	 * 年度类型
	 */
	public static String STRING_KEY_NDLX="NDLX";
	
	/**
	 * 年度类型值及期号且值为１
	 */
	public static String STRING_KEY_NDLX_VALUE="1";
    /**
     * 汇总纳税方法相关常量及提示信息
     */
    //汇总纳税
    public final static String HZNSFF_QYSDSNB2014_CZZSSDS_HZNS = "1";
    //独立纳税
    public final static String HZNSFF_QYSDSNB2014_CZZSSDS_DLNS = "2";
    //汇总纳税方式-总机构
    public final static String HZNSFS_QYSDSNB2014_CZZSSDS_ZJG = "1";
    //汇总纳税方式-分支机构
    public final static String HZNSFS_QYSDSNB2014_CZZSSDS_FZJG = "2";
    
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
	 * 2014查账征收企业所得税处理器常量定义
	 */
	public static final String CZZSQYSDSNB_PROCESSOR_2014 = "com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.processor.CzzsfzjgNbProcessor";
	/**
	 * 2014企业所得税汇总纳税分支机构分配表处理器常量定义
	 */
	public static final String ZFJGQYSDSNB_2014_PROCESSOR = "com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.processor.ZfjgfzjgNbProcessor";
}
