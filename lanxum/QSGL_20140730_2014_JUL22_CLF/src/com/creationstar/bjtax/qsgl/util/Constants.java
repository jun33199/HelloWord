/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.util;

import com.ttsoft.framework.util.PropertiesUtil;

/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 契税使用的常量类</p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class Constants {
    public static final String CQRJS_INIT =
            "var aryDataSource=[[\"\",\"\",\"\",\"02\",\"\",\"CHN\",\"0\",\"\",1]];";
    /** String "true" */
    public static final String TRUE = "true";

    /** String "false" */
    public static final String FALSE = "false";

    /** STATIC_CONTEXT_PATH 系统定义的静态HTTP文件路径 */
    private static String STATIC_CONTEXT_PATH = null;

    /** 页面信息提示的KEY */
    public static final String MESSAGE_KEY = "message";

    /** STACK_MSG_CAP 将异常堆栈信息转换为字符串时需要的数据库中对应字段的长度 */
    public final static int STACK_MSG_CAP = 1000;

    /**===========================================================================*/
    /** 标示 QsglEJBSessionBean 的JNDI名称 ,以便于在需要获得该EJB的时候查询用*/
    public static final String JNDI_QsglEJBSessionBean_HOME =
            "QsglEJBHome_JNDI_Name";

    /** 标示 QsglEJBSessionBean 的JNDI名称 ,以便于在需要获得该EJB的时候查询用*/
    public static final String JNDI_DS_Name = "datasource_jndi_name";


    /**===========================================================================*/
    /** 标示 processor-map.properties 文件的名称，以供程序内调用 */
    public static final String PROCESSOR_MAP_FILE_NAME =
            "qsgl_processor-map.properties";

    /** 契税管理子系统的属性文件名称，以供程序内调用*/
    public static final String QSGL_PROPERTIES_FILE_NAME =
            "bjtax_qsgl.properties";

    //取代码表processor对应的常量
    public static final String Load_CodeTables = "load_codeTables";

    /**===========================================================================*/
    /**
     * 用于DAOFactory中定位不同DAO的
     */
    public static final String HdtzsDAO = "HdtzsDAO";
    public static final String HdjmmxDAO = "HdjmmxDAO";
    public static final String FgrxxDAO = "Fgrsb_DAO";
    public static final String GrxxDAO = "Grsb_DAO";
    //public static final String JmblcqDAO = "JmblcqDAO";
    public static final String JmsbbDAO = "JmsbbDAO";
    public static final String JsblcqDAO = "JsblcqDAO";
    public static final String JsblgyzfDAO = "JsblgyzfDAO";
    public static final String SbtdfwglDAO = "Jmsb_DAO";
    public static final String SbzbDAO = "Sbzb_DAO";
    public static final String SpjgxxDAO = "SpjgxxDAO";
    public static final String TufwxxDAO = "Fwtdxx_DAO";
    public static final String SbcqglDAO = "SbcqglDAO";
    public static final String SbgyzfDAO = "SbgyzfDAO";
    public static final String Zcwh_DAO = "Zcwh_DAO"; //政策维护DAO
    public static final String CodeTables_DAO = "CodeTable_DAO"; //代码表DAO
    public static final String Wsz_DAO = "WszDAO";
    public static final String Wszmx_DAO = "WszmxDAO";
    public static final String Wszhz_DAO = "WszhzDAO";
    public static final String Jkszb_DAO = "SbjkzbDAO";
    public static final String Jksmx_DAO = "SbjkmxDAO";
    public static final String Szsm_DAO = "SzsmDAO";
    public static final String Yskm_DAO = "YskmDAO";
    public static final String Ysjc_DAO = "YsjcDAO";
    public static final String SzsmYskm_DAO = "SzsmYskmDAO";
    public static final String Swjgzzjg_DAO = "SwjgzzjgDAO";
    public static final String Zh_DAO = "ZhDAO"; //票证帐户DAO
    public static final String Wbhs_DAO = "WbhsDAO"; //票证帐户DAO
    public static final String Drzb_DAO = "DrzbDAO";
    public static final String DrpcInfo_DAO = "DrpcInfoDAO";
    public static final String Plcx_DAO = "PlcxDAO";
    public static final String CQXXB_DAO = "CqxxbDAO";
    public static final String CqxxCwb_DAO = "CqxxCwbDAO";
    public static final String Jsfs_DAO = "JsfsDAO";
    public static final String Bl_QueryJksDAO = "BlQueryJksDAO";
    public static final String PLSL_PZLRDAO = "PzlrDAO";
    
    //存量房管理DAO
    public static final String Fwxx_DAO = "FwxxDAO";  //房屋信息
    public static final String ClfjyxxCX_DAO="ClfjyxxCXDAO";//房屋交易信息查询Dao
    public static final String MfgrxxBuyer_DAO = "MfgrxxBuyerDAO";  //买方个人信息
    public static final String MfgrxxSeller_DAO = "MfgrxxSellerDAO";  //卖方个人信息
    public static final String MfsbxxzbSeller_DAO = "MfsbxxzbSellerDAO";  //卖方申报信息表主表
    public static final String MfsbxxmxSeller_DAO = "MfsbxxmxSellerDAO";  //卖方申报信息表子表
    public static final String Htypzdzgxb_DAO = "HtypzdzgxbDAO";  //合同与凭证对照关系表
    public static final String HtypzdzgxbLs_DAO = "HtypzdzgxbLsDAO";  //合同与凭证对照关系临时表
    public static final String SzsmInit_DAO = "SzsmInitDAO";  //税种税目初始化    
    public static final String MfsbxxprintSeller_DAO = "MfsbxxprintSellerDAO";  //卖方申报信息查询 
    public static final String Fwhdxxb_DAO = "FwhdxxbDAO";  //房屋核定信息表
    public static final String Qscxdyrz_DAO = "QscxdyrzDAO";  //存量房交易契税查询打印操作记录日志
    public static final String Clfjycs_DAO = "ClfjycsDAO"; //存量房交易参数表
    
    //发票管理DAO
    public static final String Fpkf_DAO = "FpkfDAO";  //发票库房
    public static final String Fpzl_DAO = "FpzlDAO";  //发票种类
    public static final String Kplx_DAO = "KplxDAO";  //开票类型
    public static final String Fpczz_DAO = "FpczzDAO";  //发票开票主表
    public static final String Fpczmx_DAO = "FpczmxDAO";  //发票开票明细
    public static final String Fpkc_DAO = "FpkcDAO";  //发票库存
    public static final String Fpkpczrz_DAO = "FpkpczrzDAO"; //发票开票操作临时表

    //代表在servlet context中存放代码表的hashmap
    public static final String CodeTables = "CodeTablesMap";

    //代表存放在HashMap中的政策维护代码表的key
    public static final String ZCWH = "zcwh";
    //代表存放在HashMap中的汇率代码表的key
    public static final String HL = "hl";
    //代表存放在HashMap中的币种代码表的key
    public static final String BZ = "bz";
    //代表存放在HashMap中的房屋类型代码表的key
    public static final String FWLX = "fwlx";
    //代表存放在HashMap中的证件类型代码表的key
    public static final String ZJLX = "zjlx";
    //代表存放在HashMap中的证件类型代码表的key
    public static final String ZJLXMAP = "zjlxmap";
    //代表存放在HashMap中的缴税方式代码表的key
    public static final String JSFS = "jsfs";
    //代表存放在HashMap中的纳税人类型代码表的key
    public static final String NSRLX = "nsrlx";
    //代表存放在HashMap中的契税土地房屋用途代码表的key
    public static final String TDFWYT = "tdfwyt";
    //代表存放在HashMap中的不征个人契税土地房屋用途代码表的key
    public static final String BZ_TDFWYT_GR = "bz_tdfwyt_gr";
    //代表存放在HashMap中的不征非个人契税土地房屋用途代码表的key
    public static final String BZ_TDFWYT_FGR = "bz_tdfwyt_fgr";
    //代表存放在HashMap中的不征情况代码表的key
    public static final String BZQK = "bzqk";
    //代表存放在HashMap中的土地房屋权属转移情况代码表的key
    public static final String TDFWQSZY = "tdfwqszy";
    //代表存放在HashMap中的减免政策代码表的key
    public static final String JMZC = "jmzc";
    //代表存放在HashMap中的减免政策代码表的key
    public static final String JMZC_GR = "jmzc_gr";
    //代表存放在HashMap中的减免政策代码表的key
    public static final String JMZC_FGR = "jmzc_fgr";
    //代表存放在HashMap中的减免政策代码表的key
    public static final String JMZCMAP = "jmzcmap";
    //代表存放在HashMap中的国籍代码表的key
    public static final String GJ = "gj";
    //代表存放在HashMap中的币种代码表的key
    public static final String GJMAP = "gjmap";
    //代表存放在HashMap中的银行代码表的key
    public static final String YH = "yh";
    //代表存放在HashMap中的税务机关组织机构代码表的key
    public static final String SWJGZZJG = "swjgzzjg";
    //代表存放在HashMap中的土地级次代码表的key
    public static final String TDJC = "tdjc";
    //代表存放在HashMap中的所在区域代码表的key
    public static final String SZQY = "szqy";
    //代表存放在HashMap中的发票种类代码表的key
    public static final String FPZL = "fpzl";
    //代表存放在HashMap中的发票种类代码表的key
    public static final String KPLX = "kplx";
    //代表存放在HashMap中的发票种类代码表的key
    public static final String FWCQZBZZFLX = "fwcqzbzzflx";

    //代表存放在HashMap中的所在区域代码表的key
    public static final String TDFWYTJM = "tdfwytjm"; //zzb add 090820 减免
    
    //代表存放在HashMap中的房屋性质代码表的key
    public static final String FWXZ = "fwxz";//tangchangfu  add 20131013 存量房
    
    //代表存放在HashMap中的房屋性质代码表的key
    public static final String FWSZQY = "fwszqy";//tangchangfu  add 20131013 存量房(房屋所在区域)
  //代表存放在HashMap中的房屋权属转移明细代码表的key
    public static final String QSZYXSMX="qszyxsmx";////added by zhangj
    
    //代表存放在HashMap中的减免契税减免性质的key
    public static final String QS_JMXZ = "qs_jmxz";//pengyouhua  add 20140714 契税适用的减免性质代码
 


    /**=========================申报主表中各项标识的说明====================================*/
    /**
     * 申报主表中
     * 状态标示：
     * 0--保存
     * 1--打印申报表
     * 2--已打印减免申报表
     * 3--撤销打印  现在不用
     * 4--打印核定通知书
     * 5--即时办理审核同意
     * 6--即时办理审核不同意
     * 7--已复核（打印完税证/缴款书）
     * 91--作废
     * 99--已入库
     */
    public static final String ZB_ZTBS_BC = "0";
    public static final String ZB_ZTBS_DY = "1";
    public static final String ZB_ZTBS_DY_JMSBB = "2";
    public static final String ZB_ZTBS_CX_DY = "3";
    public static final String ZB_ZTBS_DY_HD = "4";
    public static final String ZB_ZTBS_JS_TY = "5";
    public static final String ZB_ZTBS_JS_BTY = "6";
    public static final String ZB_ZTBS_YFH = "7";
    public static final String ZB_ZTBS_ZF = "91";
    public static final String ZB_ZTBS_YRK = "99";

    /**
     * 不征标志
     * 0：减免
     * 1：不征
     */
    public static final String BZBS_JM = "0";
    public static final String BZBS_BZ = "1";

    /**
     * 纳税人类型 个人
     */
    public static final String NSRLX_GR = "99";

    /**
     * 用户标志
     *
     */
    public static final String YHBZ_GR = "0";
    public static final String YHBZ_FGR = "1";

    /**
     * 补偿类型
     */
    public static final int BCLX_HB = 1; //货币
    public static final int BCLX_SW = 2; //实物
    public static final int BCLX_HH = 3; //混合

    /**
     * 办理减免税标识：
     * 0--不符合办理条件 没有拆迁和公有住房
     * 1--符合办理条件，未录入 有拆迁或公有住房  TODO（ 20070523标注：只要录入，均采用该标识 ）
     * 2--符合办理条件，已录入 TODO（20070523标注：未启用该代码）
     * 3--办理程序性减免税(20070525新增)
     * 99--不征
     */
    public static final String ZB_BLJMSBS_BFHBLTJ = "0";
    public static final String ZB_BLJMSBS_FHBL_WLR = "1";
    public static final String ZB_BLJMSBS_FHBL_YLR = "2";
    public static final String ZB_BLJMSBS_CXXJM = "3";
    public static final String ZB_BLJMSBS_BZ = "99";

    /**
     * 补录标示：
     * 0--非补录、正式录入
     * 1--补录
     */
    public static final String ZB_BLBS_FBL = "0"; //非补录为0
    public static final String ZB_BLBS_BL = "1"; //补录为1
    public static final String ZB_BLBS_SJQYBL = "2"; //数据迁移补录为2

    /**
     * 用户标示
     */
    public static final String ZB_YHBS_GR = "0"; //用户标识-个人
    public static final String ZB_YHBS_FGR = "1"; //用户标识-非个人

    public static final String ZB_NSRLX_GR = "99"; //纳税人类型-个人
    /**=========================申报主表中各项标识的说明==结束===============================*/

    /**=========================减免申报表状态标识说明====================================*/
    /**
     * 减免申报表中
     * 状态标示：
     * 0--保存
     * 1--打印
     *
     * 21--审核同意（及时办理）
     * 22--审核不同意（及时办理）
     * 3--撤销审核（及时办理）
     *
     * 41--审核同意（正常审批）
     * 42--审核不同意（正常审批）
     * 5--撤销审核（正常审批）
     *
     * 6--撤销打印
     */
    public static final String JMSBB_ZTBS_BC = "0"; //保存
    public static final String JMSBB_ZTBS_DY = "1"; //打印

    public static final String JMSBB_ZTBS_JSBL_SHTY = "21";
    public static final String JMSBB_ZTBS_JSBL_SHBTY = "22";
    public static final String JMSBB_ZTBS_JSBL_CX_SH = "3";

    public static final String JMSBB_ZTBS_ZCSP_SHTY = "41";
    public static final String JMSBB_ZTBS_ZCSP_SHBTY = "42";
    public static final String JMSBB_ZTBS_ZCSP_CX_SH = "5"; //撤销审核（及时办理）

    public static final String JMSBB_ZTBS_CXDY = "6";

    /**=========================减免申报表状态标识说明====结束==============================*/


    //个人、非个人信息表的房屋交换标识
    public static final String FFWJHBS = "0"; //非房屋交换标识为0
    public static final String FWJHBS = "1"; //房屋交换标识为1

    //土地房屋权属转移类型
    public static final String TDFWQSZY_MM = "03"; //土地房屋转移类型为买卖
    public static final String TDFWQSZY_JH = "05"; //土地房屋转移类型为交换

    //身份证件类型
    public static final String SFZJLX_HZ = "04"; //护照

    //土地房屋类别
    public static final String TDFWLB = "03"; //房屋类别为——其他住宅

    //减免项目代码
    public static final String JMXMDM = "7499";

    /**=========================完税证标识说明====开始==============================*/
    public static final String YSJCDM_DF = "21"; //预算级次
    public static final String YSJCDM_DF_MC = "地方级"; //预算级次名称
    public static final String WSZ_DJZCLX = "410"; //登记注册类型--41代表其他服务业
    public static final String WSZ_DJZCLX_MC = "个体工商户";
    //public static final String WSZ_GJBZHYDM = "8390"; //国家标准行业代码--8390代表个人 //2006-09-13国家标准行业代码修改
    //2012年1月开始启用 Modified by Zhangyj 20120111
    public static final String WSZ_GJBZHYDM = "8190";
    public static final String WSZ_GJBZHYMC = "其他未列明的服务"; //国家标准行业名称--8390代表个人--8190代表个人

    public static final String WSZ_PZZLDM_OLD = "1015"; //契税完税证对应的票证种类代码----契税完税证（计算机打票）
    public static final String WSZ_PZZLDM = "1104"; ////票样变更 完税证契税专用接口 tujb-20131212
    public static final String WSZ_PZZLDM_SG = "1014"; //契税完税证对应的票证种类代码----契税完税证（手工票）

    public static final String WSZ_CLBJDM_WCL = "10"; //未处理
    public static final String WSZ_CLBJDM_YSB = "11"; //已申报
    public static final String WSZ_CLBJDM_YJK = "12"; //已缴款
    public static final String WSZ_CLBJDM_YXH = "13"; //已销号
    public static final String WSZ_CLBJDM_YWS = "14"; //已完税（或已打印）
    public static final String WSZ_CLBJDM_YJZ = "15"; //已记帐
    public static final String WSZ_CLBJDM_YZF = "16"; //已作废

    public static final String WSZ_FSDM = "1"; //登记申报方式代码——到税务机关方式
    public static final String WSZ_FSMC = "到税务机关方式"; //登记申报方式名称——到税务机关方式

    public static final String WSZ_QSSZDM = "74"; //契税税种代码
    public static final String WSZ_QSSZMC = "契税"; //契税税种名称
    public static final String WSZ_JZBZ_DEFAULT = "000000"; //记帐标志
    public static final String WSZ_JZBZ_BL = "000001"; //已记帐

    public static final String WSZ_HZFS_PL = "3"; //完税证汇总方式－－按照批次汇总
    public static final String WSZ_HZFS_SWS = "2"; //完税证汇总方式－－税务所
    public static final String WSZ_HZFS_GR = "1"; //完税证汇总方式－－个人
    public static final String WSZ_HZFS_ZSD = "0"; //完税证汇总方式－－征收点

    public static final String WSZ_JSFS_XJ = "01"; //缴税方式是现金；
    public static final String WSZ_JSFS_SK = "02"; //缴税方式是刷卡；
    public static final String WSZ_JSFS_ZP = "03"; //缴税方式是支票；
    public static final String WSZ_JSFS_ZXJN = "04"; //缴税方式是自行缴纳；

    public static final String WSZ_FPJE = "9999999.99"; //完税证分票金额，一张完税证最多的金额；
    //补录汇总生成的缴款书时，如果有分票的情况，生成的完税证汇总表的记录的sbhzdh暂时用该值
    public static final String WSZ_SBHZDH_DEFAULT = "FP000000";

    /**=========================完税证标识说明====结束==============================*/

    /**=========================缴款书标识说明====开始==============================*/
    /** @todo 需要确定的税款类型代码 数据来源 基本帐户标示*/
    public static final String JKS_SKLXDM_XHJK = "100";
    public static final String JKS_SKLXDM_XHJK_MC = "销号";
    public static final String JKS_SKLXDM_ZCJK = "110";
    public static final String JKS_SKLXDM_ZCJK_MC = "一般正常";
    public static final String JKS_SKLXDM_HZJK = "120";
    public static final String JKS_SKLXDM_HZJK_MC = "零散";
    public static final String JKS_SJLY_HZ = "1"; //汇总方式生成 缴款书生成方式－－汇总完税证生成缴款书
    public static final String JKS_SJLY_FHZ = "2"; //非汇总方式 缴款书生成方式－－直接
    public static final String JKS_SJLY_XH = "51"; //销号方式
    public static final String JKS_JBZHBS = "";
    public static final String JKS_ZWBS_DEFAULT = "00000000000000000000"; //记帐标志'00000000000000000000'
    public static final String JKS_ZWBS_XJ = "00000000000000000010"; //记帐标志'00000000000000000010'
    public static final String JKS_ZWBS_ZP = "00000000000000000020"; //记帐标志'00000000000000000020'
    public static final String JKS_ZWBS_SK = "00000000000000000120"; //记帐标志'00000000000000000120'

    /**=========================缴款书标识说明====结束==============================*/

    /**=========================与金额有关的常量====开始==============================*/
    public static final String JE_CJJG = "JE_CJJG"; //成交价格
    public static final String JE_JSYJ = "JE_JSYJ"; //计税依据
    public static final String JE_JZQS = "JE_JZQS"; //计征契税
    public static final String JE_SJYZ = "JE_SJYZ"; //实际应征
    public static final String JE_JZSE = "JE_JZSE"; //计征税额
    public static final String JE_QYZFBCDKE = "JE_QYZFBCDKE"; //出售已购公有住房的本次抵扣额
    public static final String JE_FWJHJG = "JE_FWJHJG"; //房屋交换价格
    public static final String JE_CQJMJE = "JE_CQJMJE"; //拆迁减免金额
    public static final String JE_PTZZJMJE = "JE_PTZZJMJE"; //普通住宅减税金额
    public static final String JE_JMSZE = "JE_JMSZE"; //减免税总金额
    public static final String JE_YNSE = "JE_YNSE"; //应纳税额（在复核及收款中，计算出来的最终缴税额）
    public static final String JE_ZCSPJMJE = "JE_ZCSPJMJE"; //拆迁减免金额

    /**=============================政策维护==============================================*/
    public static final String ZCWH_PTZZJMBZ = "0002"; //普通住宅减免的单位面积的标准
    public static final String ZCWH_SL = "0001"; //政策维护中的税率
    public static final String ZCWH_JBZS = "0003"; //政策维护中的普通住宅减半征收
    public static final String ZCWH_SCGMPTZFSL = "0004"; //首次购买90㎡以下普通住房税率

    /**=============================产权人类型=========================================*/
    public static final String CQRLX_ZCQR = "0"; //主产权人
    public static final String CQRLX_CCQR = "1"; //次产权人


    /**===========================================================================*/
    /**
     * 获取系统配置中指定的静态HTTP文件的路径。
     *
     * @return 系统配置中指定的static_contextpath的值
     */
    public static String getStaticContextPath() {
        if (STATIC_CONTEXT_PATH == null) {
            STATIC_CONTEXT_PATH = PropertiesUtil.getProperty(
                    "static_contextpath");
        }
        return STATIC_CONTEXT_PATH;
    }

    /**
     * @todo
     * 减免政策代码
     * JMZC_GYZF  共有住房
     * JMZC_CQFW  拆迁房屋
     * JMZC_PTZZ  普通住宅
     * JMZC_JJSYZF  经济适用住宅
     */
    public static String JMZC_GYZF = "7452";
    public static String JMZC_CQFW = "7451";
    public static String JMZC_PTZZ = "7450";
    //add by zhangyj 20090223
    public static String JMZC_JJSYZF = "7453";

    /**
     * 批量倒入主表的状态位
     */
    public static String DRZB_ZT_XINZENG = "0"; //新增
    public static String DRZB_ZT_CHECKED = "1"; //审核
    public static String DRZB_ZT_CHECKED_NO = "1N"; //审核
    public static String DRZB_ZT_RECIVE = "2"; //受理
    public static String DRZB_ZT_PRINT = "6"; //打印
    public static String SBZB_ZT_SHOULI = "3"; //受理（未使用）
    public static String SBZB_ZT_SHENHE = "4"; //审核（未使用）
    public static String SBZB_ZT_FUHE = "5"; //复核（未使用）
    public static String PC = "99";

    public static String PT_JKS = "1"; //代表普通缴款书
    public static String DZ_JKS = "2"; //代表调帐的缴款书

    /**
     * 税额调整
     */
    public static final String SETZ_ZC = "0"; //正常
    public static final String SETZ_JBZS = "1"; //减半征收
    public static final String SETZ_QEZS = "2"; //全额征收


    public static final String SETZ_SCGMPTZF = "5"; //首次购买90平米以下普通住房
    //增加税额调整项(经济适用房) modify by fujx，20081217
    public static final String SETZ_JJSYF = "6"; //经济适用房
    /**
     * 容积率和土地级次
     */
    public static final String TUFWXX_RJL_LOW = "00"; //1.0以下
    public static final String TUFWXX_RJL_HIGH = "01"; //1.0以上含1.0
    public static final String TUFWXX_TDJC_ONE = "01";
    public static final String TUFWXX_TDJC_TWO = "02";
    public static final String TUFWXX_TDJC_THREE = "03";
    public static final String TUFWXX_TDJC_FOUR = "04";
    public static final String TUFWXX_TDJC_FIVE = "05";
    public static final String TUFWXX_TDJC_SIX = "06";
    public static final String TUFWXX_TDJC_SEVEN = "07";
    public static final String TUFWXX_TDJC_EIGHT = "08";
    public static final String TUFWXX_TDJC_NINE = "09";
    public static final String TUFWXX_TDJC_TEN = "10";
    public static final String TUFWXX_TDJC_SIX_TEN = "11";

    /**
     * 是否为二手房
     */
    public static final String TUFWXX_SFESF_TRUE = "01";
    public static final String TUFWXX_SFESF_FALSE = "00";

    /**
     * 剩余额用完标志
     */
    public static final String JSBL_SYEYWBZ_YONGWAN = "01"; //用完
    public static final String JSBL_SYEYWBZ_WEIYONGWAN = "00"; //未用完


    //国籍
    public static String GJ_CHN = "CHN"; //新增

    /**
     * 迁移状态位
     */
    public static String QY_ZT_XINZENG = "0"; //新增
    public static String QY_ZT_SUCCES_1 = "1"; //第一步，导入申报信息成功
    public static String QY_ZT_SUCCES_2 = "2"; //第二步，完税证或缴款书导入成功
    public static String QY_ZT_SUCCES_3 = "3"; //第三步，汇总完税证导入成功

    public static String QY_ZT_FAILURE = "99"; //第一步失败
    public static String QY_ZT_FAILURE_2 = "98"; //第二步失败
    public static String QY_ZT_FAILURE_3 = "97"; //第三步失败

    /**
     * 迁移错误表示
     */
    public static String QY_ERROR_0 = "00"; //无错
    public static String QY_ERROR_1 = "01"; //纳税人名称出错
    public static String QY_ERROR_2 = "02"; //数据过多，db
    public static String QY_ERROR_3 = "03"; //数据不存在，db
    public static String QY_ERROR_4 = "04"; //缴款书多笔，db
    public static String QY_ERROR_5 = "05"; //违反了唯一性约束，db
    public static String QY_ERROR_6 = "06"; //插入的值对于列过大,联系电话或者身份证号，db
    public static String QY_ERROR_7 = "07"; //值为空，不能保存,核定通知书号等，db
    public static String QY_ERROR_8 = "08"; //证件为护照
    public static String QY_ERROR_9 = "09"; //银行帐号不存在
    public static String QY_ERROR_10 = "10"; //导入前检测错误
    //public static String QY_ERROR_11 = "11";   //缴款书多笔

    public static String QY_ERROR_98 = "98"; //其他错误，db
    public static String QY_ERROR_99 = "99"; //其他错误

    public static String QY_ERRORMSG_0 = "迁移正确"; //无错
    public static String QY_ERRORMSG_1 = "纳税人登记信息出错"; //纳税人名称出错
    public static String QY_ERRORMSG_2 = "数据重复"; //数据过多，db
    public static String QY_ERRORMSG_3 = "数据不存在"; //数据不存在，db
    public static String QY_ERRORMSG_4 = "缴款书多笔"; //缴款书多笔，db
    public static String QY_ERRORMSG_5 = "违反了唯一性约束"; //违反了唯一性约束，db
    public static String QY_ERRORMSG_6 = "数据过长"; //插入的值对于列过大,联系电话或者身份证号，db
    public static String QY_ERRORMSG_7 = "字段为空"; //核定通知书号，缴款书计算机代码，完税证字别为空，db
    public static String QY_ERRORMSG_8 = "证件为护照"; //证件为护照
    public static String QY_ERRORMSG_9 = "银行帐号不存在"; //银行帐号不存在
    public static String QY_ERRORMSG_10 = "核定通知书重复，房屋交换的信息，完税证号和专用缴款书号都存在"; //导入前检测错误

    // public static String QY_ERRORMSG_11 = "缴款书不存在";   //缴款书多笔

    public static String QY_ERRORMSG_98 = "数据库发生错误"; //其他错误，db
    public static String QY_ERRORMSG_99 = "其他错误"; //其他错误

    /**
     * 拆迁信息数据来源
     */
    public static String CQXXB_SJLY_LR = "0"; //录入
    public static String CQXXB_SJLY_DR = "1"; //导入

    public final static String MODHDTZSFWHM_PROCESSOR =
            "com.creationstar.bjtax.qsgl.BizLogic.processor.SbxxProcessor";

    public final static String CQXXB_PROCESSOR =
            "com.creationstar.bjtax.qsgl.BizLogic.processor.CqxxbProcessor";

    /** 修改导入错误查询数量的限制字段（操作信息）: XGDRCWXX_QUERYSIZE_DR */
    public static final int XGDRCWXX_QUERYSIZE_DR = 20;

    /**
     * 导入拆迁信息数据错误类型
     */
    public static String CQXXCWB_CWKX_01 = "01"; // 正式房屋间数、正式房屋建筑面积非数字

    public static String CQXXCWB_CWKX_02 = "02"; // 身份证号相同且姓名不一样

    /**
     * 导入拆迁信息数据错误类型说明
     */
    public static String CQXXCWB_CWKX_01_CH = "正式房屋间数、正式房屋建筑面积非数字"; // 正式房屋间数、正式房屋建筑面积非数字

    public static String CQXXCWB_CWKX_02_CH = "身份证号相同且姓名不一样"; // 身份证号相同且姓名不一样

    /**
     * 导入拆迁信息数据错误处理类型说明
     */
    public static String CQXXCWB_CWLX_01 = "01"; // 废弃

    public static String CQXXCWB_CWLX_02 = "02"; // 覆盖


    //程序性减免项目代码
    public static final String CXXJM_JMXMDM_QT = "7499";

    //二维条形码默认空内容
    public static final String QRCODE_DEFAULT_NULL = "00000000000000000000";
    
    //客户端生成的XML版本
    //由于普通住宅标准调整批量客户端软件，故使用新的客户端版本号
    //modified by gaoyh to 20141020
    //public static final String XML_VERSION = "3.0.0";
    public static final String XML_VERSION = "4.0.0";
    
    //发票操作种类
    public static final String FP_CZZL_LR = "0"; //录入czzldm
    public static final String FP_CZZL_ZTBC = "1"; //录入ztbs
    
    public static final String FP_NWYH = "1"; //外网用户标识
    public static final String FP_WWYH = "0"; //内网用户标识
    
    public static final String FP_RKBZ_RK = "1"; //入库标识（入库）
    public static final String FP_RKBZ_FRK = "0"; //入库标识（非入库）
    
    public static final String FP_SL_ZERO = "0"; //发票库存为零（0）
    
    public static final String FP_TPBZ_WTP = "0"; //发票退票标识（0）:未办理退票
    public static final String FP_TPBZ_YTP = "1"; //发票退票标识（1）:已办理退票
    
    public static final String FP_DCBZ_WTP = "0"; //发票导出标识（0）:未导出
    public static final String FP_DCBZ_YTP = "1"; //发票导出标识（1）:已导出
    
    public static final String FP_KPLX_KP = "1"; //发票开票类型（1）:开票
    public static final String FP_KPLX_TP = "2"; //发票开票类型（2）:退票
    public static final String FP_KPLX_FP = "3"; //发票开票类型（3）:废票
    
    public static final String FP_MMFBZ_SF = "1"; //买卖方标识（1）：卖方
    public static final String FP_MMFBZ_BF = "0"; //买卖方标识（0）：买方
    
    public static final String FP_PZFLDM_WSZ = "01"; //买凭证分类代码（01）：完税证
    public static final String FP_PZFLDM_JKS = "02"; //买凭证分类代码（02）：缴款书
    public static final String FP_PZFLDM_FP = "11"; //买凭证分类代码（11）：发票
    public static final double FP_MAX_JE = 99999999.00; //发票打印最大金额
    
    public static final String ONLY_ROOM_YES = "0"; //是否为家庭唯一生活用房：是
    public static final String ONLY_ROOM_NOT = "1"; //是否为家庭唯一生活用房：否
    public static final String FWLX_BUILDINGS = "0"; //房屋类型：楼房
    public static final String FWLX_BUNGALOW = "1"; //房屋类型：平房
    public static final String FWLX_BASEMENT = "2"; //房屋类型：地下室
    public static final String TDZSS_SB_GFFP = "0"; //土地增值税申报方式：提供购房发票
    public static final String TDZSS_SB_GFQSP= "3"; //土地增值税申报方式：提供购房契税票
    public static final String TDZSS_SB_GFHT = "4"; //土地增值税申报方式：提供原购房合同
    public static final String TDZSS_SB_PGBG = "1"; //土地增值税申报方式：提供评估报告
    public static final String TDZSS_SB_WPJ = "5"; //土地增值税申报方式：无任何票据
    public static final String TDZSS_SB_FLWS = "6"; //土地增值税申报方式：提供法律文书
    public static final String TDZSS_SB_HDZS = "2"; //土地增值税申报方式：核定征收
    public static final String CQZBZ_JLMX_LOW = "0"; //产权证标注建筑面积：140平米（含）以下
    public static final String CQZBZ_JLMX_HIGH = "1"; //产权证标注建筑面积：140平米以上
    public static final String FWRJL_LOW = "0"; //房屋容积率：1.0以下
    public static final String FWRJL_HIGH = "1"; //房屋容积率：1.0（含）以上
    public static final String HFBZ_PT = "0"; //划分标准：普通住房
    public static final String HFBZ_NOTPT = "1"; //划分标准：非普通住房
    public static final String HFBZ_FZF = "2"; //划分标准：非住房  by zhangj
    public static final String HFBZ_GRWCZY = "3"; //划分标准：个人无偿赠与
    public static final String HFBZ_GRJC = "4"; //划分标准：个人继承
    public static final String ZFSYSJ_FIVE = "0"; //住房使用时间：5年（含）以上
    public static final String ZFSYSJ_THREETOFOIVE = "1"; //住房使用时间：5年以下3年以上
    public static final String ZFSYSJ_THREE = "2"; //住房使用时间：3年（含）以下
    public static final String YSSZSFS_NOT = "0"; //营业税征收方式：免征营业税
    public static final String YSSZSFS_ALL = "1"; //营业税征收方式：全额征收营业税
    public static final String YSSZSFS_MINUS = "2"; //营业税征收方式：差额征收营业税
    public static final String GRSDSZSFS_FREE = "0"; //个人所得税征收方式:免征个人所得税
    public static final String GRSDSZSFS_ZS = "1"; //个人所得税征收方式:征收个人所得税
    public static final String TDZZSZSFS_NOT = "0"; //土地增值税征收方式:不征收土地增值税
    public static final String TDZZSZSFS_FREE = "1"; //土地增值税征收方式:免征土地增值税
    public static final String TDZZSZSFS_ZS = "2"; //土地增值税征收方式:全额征收土地增值税
    public static final String TDZZSZSFS_QT = "3"; //土地增值税征收方式:其他
    public static final String TDZZSZSFS_GFFPZS = "4"; //土地增值税征收方式:提供购房发票征收土地增值税
    public static final String TDZZSZSFS_HDZS = "5"; //土地增值税征收方式:核定征收土地增值税
    public static final String TDZZSZSFS_PGBGZS = "6"; //土地增值税征收方式:提供评估报告征收土地增值税
    public static final String JSSRQRFS_HTJG = "0"; //计税收入确认方式:合同价格
    public static final String JSSRQRFS_HDJSJG = "1"; //计税收入确认方式:核定计税价格
    
    public static final String CJS_SZSMDM = "100010"; //城建税税种税目
    public static final String TDZZS_SZSMDM = "080040"; //土地增值税种税目
    //六类住房
    public static final String FWCQZBZZFLX_06 = "06";//	首次上市交易的已购公房
    public static final String FWCQZBZZFLX_07 = "07";	//危改回迁房
    public static final String FWCQZBZZFLX_08 = "08";	//合作社集资建设住房
    public static final String FWCQZBZZFLX_09 = "09";	//安居房
    public static final String FWCQZBZZFLX_10 ="10";	//康居房
    public static final String FWCQZBZZFLX_11 ="11";	//绿化隔离地区农民安置住房
    public static final String FWCQZBZZFLX_13 ="13";	//历史文化街区中的腾退私房,added by zhagnj,2014.10.17

    public static final String PZZLCD = "10"; //票证种类长度设置
    public static final String FWHDLX_HOUSING="0";//房屋核定类型 0：住房，1：非住房
    public static final String FWHDLX_NONHOUSING="1";////房屋核定类型 0：住房，1：非住房
    public static final String YHSZSFS_ZS="0";//印花税征收方式：征收印花税
    public static final String YHSZSFS_FREE="1";//印花税征收方式：免征印花税
    public static final String FPCXLINK="QSGL_FPCX_LINK";//发票查询的链接地址
    
    public static final String JSJM_JMXZDM_CQJM = "15129902"; //拆迁减免
    public static final String JSJM_JMXZDM_SLJM = "15011799"; //税率减免
    
}
