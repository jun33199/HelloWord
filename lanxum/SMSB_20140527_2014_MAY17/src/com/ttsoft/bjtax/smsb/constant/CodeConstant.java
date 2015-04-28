/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.constant;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:常量定义</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class CodeConstant {
    public CodeConstant() {
    }

    //税款入库方式代码
    public static final String SKRKFSDM_SANFANG = "03"; //三方协议银行缴款
    /**********************减免项目代码********************************/
    public static final String JM_CY_JMXMDM = "9990";

    /*****************营业税税种代码***********************/
    public static final String SZSM_YYS = "02";

    /*****************企业所得税种税目***********************/
    public static final String SZSM_QYSDS = "30";
    /*****************企业所得税种税目***********************/
    public static final String SZSM_QYSDSCODE = "300000";

    /*****************查询申报数据是否入库标志***********************/

    public static final String SMSB_ZWBS = "0";
    /*****************查询申报数据是否入库标志***********************/
    public static final String SMSB_ZWBS20 = "0";

    /*****************个体工商户代码***********************/

    public static final String GTGSH_CODE = "410";

    /*****************纳税人状态代码***********************/
    /**
     * 正常
     */
    public static final String NSRZT_ZC = "10";

    /**
     * 非正常
     */
    public static final String NSRZT_FZC = "30";
    /**
     * 证件类型-无
     */
    public static final String ZJLXDM_NULL = "80";

    /*****************集体企业代码***********************/
    public static final String JITIQIYE_CODE = "120";

    /****************企业优惠**********************/
    /**
     * 乡镇企业标示
     */
    public static final String XZQYBS = "XIANZHENQIYE";

    /**
     * 乡镇企业减免
     */
    public static final String JM_XIANZHEN = "0.1";

    /**
     * 乡镇企业优惠
     */
    public static final String YHBL_XIANZHEN = "0.9";

    /**
     * 其它企业优惠
     */
    public static final String YHBL_QITA = "1";

    /************时间类型*****************/
    public static final String DATETYPE = "yyyy-MM-dd HH:mm:ss";

    /******************企业季报类型征收*******************************************/
    /**
     * 高新企业企业季报方式
     */
    public static final String GAOXINQIYE = "98";

    /**
     * 高新企业税率
     */
    public static final String GAOXINQIYESL = "0.15";

    /**
     * 非高新企业、非纯益率征收、非定额征收剩下的其他企业征收方式
     */
    public static final String QITAQIYE = "99";

    /******************企业季报类型征收*******************************************/

    /******************外企营业税征收方式*******************************************/
    /**
     * 外企营业税按实申报
     */
    public static final String WQYYSZSFS_ASSB = "01";

    /**
     * 外企营业税换算申报
     */
    public static final String WQYYSZSFS_JFHS = "02";

    /**
     * 外企营业税核定申报
     */
    public static final String WQYYSZSFS_HDZS = "03";

    /******************外企营业税征收方式*******************************************/
    /** START   by qinwei    20060530    **/
    // 保存成功显示的提示信息
    public static final String SMSB_SAVE_SUCCESS = "SAVE_SUCCESS";
    // 删除成功显示的提示信息
    public static final String SMSB_DELETE_SUCCESS = "DELETE_SUCCESS";
    // 查询成功显示的提示信息
    public static final String SMSB_QUERY_SUCCESS = "QUERY_SUCCESS";
    /** END     by qinwei    20060530    **/

    /**
     *  联营股份企业类型代码
         联营代码
     */

    public static final String LYGFQYLXDM_LY = "LYGFNB";

    /**
     * 股份代码
     */
    public static final String LYGFQYLXDM_GF = "GFZQYNB";

    /**
     * 中外合资
     */
    public static final String LYGFQYLXDM_ZWHZ = "ZWHZQYNB";

    /**
     * 投资
     */
    public static final String LYGFQYLXDM_TZ = "TZQYNB";

    /******************自然人中使用*******************************************/
    /**
     * 登记注册类型
     */
    public static final String ZRR_JKS_DJZCLXDM = "410";

    /**
     * 登记注册类型  自然人申报缴款，当国籍为中国时登记注册类型为410，当国籍为非中国时登记注册类型为390
     */

    public static final String ZRR_JKS_DJZCLXDM_UNCN = "390";

    /**
     * 中国国家代码
     */
    public static final String GJDM_CHN = "CHN";

    /**
     * 国家标准行业
     * 2012年1月开始启用 Modified by Zhangyj 20120111
     */
    //public static final String ZRR_JKS_GJBZHYDM = "8390";//2006-09-13 国家标准行业修改
    public static final String ZRR_JKS_GJBZHYDM = "8190";

    /**
     * 减费用额
     */
    public static final String ZRR_JFYE_CHN = "1200";
    /**
     * 减费用额
     */

    public static final String ZRR_JFYE_ELSE = "4000";
    /**
     * 减费用额
     */

    public static final String ZRR_JFYE_NORMAL = "800";

    /**
     * 自然人所得税申报中使用工薪所得
     */
    public static final String ZRR_SZSMDM_GXSD = "0501";

    /******************生成缴款书的返回值*******************************************/
    /**
     * 生成的缴款书类型
     */
    //start modifying by qianchao 2005.11.1
    //public static final String ZHSB_JKS_TYPE = "JKS_TYPE";
    //end modifying by qianchao 2005.11.1

    //add by lijn 20141014
    /**
     * Description：综合申报__小微企业减免营业税
     */
    public static final String ZHSB_JM01="ZHSB_JM_YYS_XWQY";
    
    /**
     * Description：减免税处理
     */
    public static final int ZHSB_JM01_ACTION = 1001;

    /**
     * Description：查看是否是当期税款
     */
    public static final int ZHSB_JM01_CHECKCURRENT_ACTION = 1002;
    
    
    
    /**
     * 生成的缴款书列表
     */
    public static final String ZHSB_JKS_LIST = "JKS_LIST";

    /*******************************************/
    /**              Shi Yanfeng
     * 综合申报缴款书维护一票一税和一票多税中使用判断**/
    /*******************************************/

    public static final String ZHSB_JKSMX_SZSMDM023 = "023";
    /**
     * 营业税
     */
    public static final String ZHSB_JKSMX_SZSMDM02 = "02";

    /**
     * 车房土税种
     */
    public static final String ZHSB_SZSMDM_C = "11";

    /**
     * 车房土税种
     */

    public static final String ZHSB_SZSMDM_F = "12";
    /**
     * 车房土税种
     */

    public static final String ZHSB_SZSMDM_T = "15";

    /**
     * 征收方式为定额
     */
    public static final String ZHSB_ZSFS_DE = "01";

    /**
     * 征收方式为定律
     */
    public static final String ZHSB_ZSFS_DL = "02";

    /**
     * 非正常告知事项
     */
    public static final String ZHSB_FZCBS = "1";
    /**
     * 分页使用
     * 2013 kanght
     */

    public final static int SMSB_PK_PG_LENGTH = 5;

    /**
     * 分页使用
     */
    public final static String SESSIONKEY_YHSDATALIST = "YHSDATALIST";
    /**
     * 分页使用
     */

    public final static String SESSIONKEY_XSLIST = "XSLIST";
    /**
     * 分页使用
     */

    public final static int YHS_PG_LENGTH = 20;
    /**
     * 分页使用
     */

    public final static String SESSIONKEY_SDDATALIST = "SDDATALIST";
    /**
     * 分页使用
     */

    public final static int SD_PG_LENGTH = 20;

    /**
     * 分页使用
     */

    public final static int JKS_PG_LENGTH = 50;

    /**
     * 分页使用
     */

    public final static String SESSIONKEY_GZDATALIST = "GZDATALIST";
    /**
     * 分页使用
     */

    public final static int GZ_PG_LENGTH = 20;

    /**
     * 页头页尾使用属性名
     */
    public final static String SMSB_HEADER_TITLE = "SMSBHEADER";
    /**
     * 页头页尾使用属性名
     */

    public final static String SMSB_HEADER_POSITION = "SMSBPOSITION";
    /**
     * 页头页尾使用属性名
     */

    public final static String SMSB_HEADER_HELP = "SMSBHELP";

    /**
     * 税款类型：正常缴款代码
     */
    public final static String SKLXDM_ZCJK = "100";

    /**
     * 税款类型代码：三代汇总使用
     */
    public final static String SKLXDM_SDHZ = "200";

    /**
     * 税款类型代码：个体工商户使用
     */
    public final static String SKLXDM_GTGSH = "120";

    /**
     * 税款类型：正常缴款名称
     */
    public final static String SKLXMC_ZCJK = "正常";

    /**
     * 税款类型：补缴欠税名称
     */
    public final static String SKLXDM_BJQS = "4";

    /**
     * 税款类型：补缴欠税名称
     */
    public final static String SKLXMC_BJQS = "补缴欠税";

    /**
     * 税款类型：三代解缴代码
     */
    public final static String SKLXDM_SDJJ = "8";

    /**
     * 四代解缴税款类型代码
     */
    public final static String SKLXDM_SDJJDM = "200";

    /**
     * 税款类型：三代解缴名称
     */
    public final static String SKLXMC_SDJJ = "三代解缴";
    /**
     *
     */

    /**
     * 网上申报的方式代码
     */
    public final static String FSDM_WSSB = "5";

    /**
     * 上门申报的方式代码
     */
    public final static String FSDM_SMSB = "1";

    /**
     * 上门申报 完税证 处理标记代码（个体工商户和零散征收的初始状态）
     * */
    public final static String SMSB_CLBJDM_WCL = "0";

    /**
     * 上门申报 完税证、减免申报 记账标志（个体工商户和零散征收的初始状态）
     *
     * */
    public final static String SMSB_JZBZ = "000000";

    /**
     * 上门申报 完税证、减免申报 记账标志（个体工商户和零散征收的可编辑）
     * */
    public final static String SMSB_JZBZ_EDIT = "0";

    /**
     * 上门申报 完税证、减免申报 记账标志（个体工商户和零散征收的可编辑）
     * */
    public final static String SMSB_JZBZ_UNEDIT = "1";

    /**
     * 上门申报 获得银行信息的  基本帐户表示
     * */
    public final static String SMSB_JBZHBS = "1";

    /*******************************************/
    /**              Shi Yanfeng              **/
    //银行联网状态标示
    public final static String SMSB_LWZT = "T";

    /*******************************************/

    /********申报历史表转入类型常量定义 Start add by hazhengze 20061213******/
    public final static String SMSB_SBJK_HIS_ZRLXDM_ZNJ1="01";
    public final static String SMSB_SBJK_HIS_ZRLXDM_ZNJ2="02";
    public final static String SMSB_SBJK_HIS_ZRLXDM_SBWRKZQS="03";//申报未入库转欠税
    public final static String SMSB_SBJK_HIS_ZRLXDM_SBWRKQRCFSB="04";//申报未入库确认重复申报
    public final static String SMSB_SBJK_HIS_ZRLXDM_SBYRKBYZZQS="05";//申报与入库不等转欠税
    public final static String SMSB_SBJK_HIS_ZRLXDM_SBYRKBDQRSBCW="06";//申报与入库不等确认申报错误
    /********申报历史表转入类型常量定义 End add by hazhengze 20061213******/


    /******************数据来源*******************************************/

    /**
     * 申报主表对应的数据来源：导入
     */
    public final static String SMSB_SJLY_DR = "12";

    /**
     * 申报录入
     */
    public final static String SMSB_SJLY_SBLR = "11";

    /**
     * 零散汇总
     */
    public final static String SMSB_SJLY_LSHZ = "13";

    /**
     * 个体工商户汇总
     */
    public final static String SMSB_SJLY_GTGSHHZ = "14";

    /**
     * 印花税汇总
     */
    public final static String SMSB_SJLY_YHSHZ = "15";

    /**
     * 三代汇总
     */
    public final static String SMSB_SJLY_SDHZ = "16";

    /**
     * 个提工商户录入的缴款书
     */
    public final static String SMSB_SJLY_GTGSHLR = "18";

    /**
     * 零散税征收录入的缴款书
     */
    public final static String SMSB_SJLY_LSZSLR = "17";

    /**
     * 零散税征收录入的缴款书
     */
    public final static String SMSB_SJLY_ZRRLR = "19";

    /**
     *定期定额国地共管户银行扣款导入
     */
    public final static String SJLY_DQDEDR = "23";
    /**
     * 申报方式代码 三方协议银行扣款代码
     */
    public final static String FSDM_QTFS = "6";
    /**
     * 四代解缴代征的税款类型
     */
    public final static String SKLXDM_4DJJ = "201";

    /**  自然人中使用税种代码 **/
    public final static String SMSB_ZRR_SZDM = "05";
    /**
     * 源泉扣缴企业所得税形成的缴款书数据
     */
    public final static String SMSB_SJLY_YQKJ = "36";

//*****************登记注册类型******************************************
     /**
      * 登记注册类型代码--事业单位
      */
     public static final String DJZCLXDM_SYDW = "196";

    /**
     * 登记注册类型代码--社会团体
     */
    public static final String DJZCLXDM_SHTT = "193";

    /**
     * 登记注册类型代码--个体工商户
     */
    public static final String DJZCLXDM_GTGSH = "410";

    /**
     * 登记注册类型代码--个人合伙
     */
    public static final String DJZCLXDM_GRHH = "420";

    /**
     *  应税收入标志
     */
    public static String YSSR_TRUE = "1";
    /**
     *  应税收入标志
     */

    public static String YSSR_FALSE = "0";

//***********************************************************

//******************征收方式代码表*****************************************
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
     * 定率征收
     */
    public static final String ZSFSDM_DLZS = "04";

    /**
     * 个人查账征收
     */
    public static final String ZSFSDM_CZZS_GR = "12";

    /**
     * 核定征收
     */
    public static final String ZSFSDM_HDZS = "11";
    //start add by zhangyijun 2006.12.20
    /**
     * 补缴欠税形成的申报数据
     */
    public final static String SMSB_SJLY_BJQS = "29";

    //end add by zhangyijun 2006.12.20

//***********************************************************

     /*-----------------打印方式-----------------------*/
     /**
      * 一票一税
      */
     public static final int PRINT_YPYS = 1;

    /**
     * 一票多税(科目)
     */
    public static final int PRINT_YPDS_KM = 2;

    /**
     * 一票多税(税目)
     */
    public static final int PRINT_YPDS_SM = 3;

    /*------------------------征期类型代码--------------------------*/
    /**
     * 按月
     */
    public static final String ZQLXDM_MONTH = "12";

    /**
     * 季度
     */
    public static final String ZQLXDM_QUARTER = "4";

    /**
     * 半年
     */
    public static final String ZQLXDM_SEMIYEAR = "2";

    /**
     * 年
     */
    public static final String ZQLXDM_YEAR = "1";

    /*------------------------减免类型代码------------------------*/
    /*******************************************/
    /**              Shi Yanfeng              **/
    /**
     * 港、澳、台商投资企业
     */
    public static final String NWZFLDM_GAT = "1";

    /**
     * 外商投资企业
     */
    public static final String NWZFLDM_WS = "2";

    /*******************************************/
    /**
     * 法定性减免类型
     */
    public static final String JMLX_FD = "2";

    /**
     * 审批减免类型
     */
    public static final String JMLX_SP = "1";

    /*------------------------申报的处理标记代码------------------------*/
    /**
     * 未处理
     */
    public static final String CLBJDM_WCL = "10";

    /**
     * 已申报
     */
    public static final String CLBJDM_YSB = "11";

    /**
     * 已申报
     */
    public static final String CLBJDM_YJK = "12";

    /**
     * 已完税
     */
    public static final String CLBJDM_YWS = "14";

    /*-----------------------印花税税种代码和税种名称----------------*/
    public static final String YHS_SZDM = "16";
    /*-----------------------印花税税种代码和税种名称----------------*/
    public static final String YHS_SZMC = "印花税";

    /**
     * 印花税其他帐簿的税种税目代码（按份数×单位税额计算已纳税额）
     */
    public static final String YHS_QTZB_SZSMDM = "161220";

    /**
     * 印花税权力、许可证照的税种税目代码（按份数×单位税额计算已纳税额）
     */
    public static final String YHS_QLXKZZ_SZSMDM = "161300";

    /**
     * 工薪定额代码
     */
    public static final String GRSDS_GXDE_SZSMDM = "050130";

    /**
     * 工薪定额名称
     */
    public static final String GRSDS_GXDE_SZSMMC = "工薪定额";

    /**
     * 工薪所得代码
     */
    public static final String ZRR_GXSD_SZSMDM = "050130";

    /**
     * 预算级次
     */
    public static final String YSJC_GTGSH = "21"; //地方级

    /**
     * 预算级次-地方级
     */
    public static final String YSJC_DIFANGJI = "21"; //地方级

    /**上门申报，汇总方式——单位/个人*/
    public static final String HZFS_DW = "0";
    /**上门申报，汇总方式——单位/个人*/
    public static final String HZFS_GR = "1";

    /**上门申报 个体工商户和零散税征收 的票证种类代码*/
    //public static final String SMSB_PZZLDM = "1006";
    public static final String SMSB_PZZLDM = "1103"; //票样变更 完税证接口 tujb-20131212
    
    /**上门申报 税收完税证明 的票证种类代码*/
    //public static final String SMSB_SWWSZM_PZZLDM = "1056";
    public static final String SMSB_SWWSZM_PZZLDM = "1110"; //票样变更 完税证明表格式接口 tujb-20131212

    /**上门申报 货运发票方式导入的完税证 */
    public static final String SMSB_FSDM = "8";

    /** SMSB_  上门申报通用事件常量 */
    public final static int SMSB_SHOWACTION = 0;

    public final static int SMSB_SAVEACTION = 1;

    public final static int SMSB_DELETEACTION = 2;

    public final static int SMSB_QUERYACTION = 3;
    public final static int SMSB_EXPORT = 111;
    public final static int SMSB_EXPORTDETAIL = 113;
    public final static int SMSB_QUERYA_DETATILACTION = 112;
    public final static int SMSB_UPDATEACTION = 4;
    /*批扣任务类型begin*/
    public final static int SMSB_INSERT_LOG = 1;//初始化

    public final static int SMSB_INIT = 52;//初始化
    public final static int SMSB_PRINTDATA = 50;//2013-4-22获取打印数据
    public final static int SMSB_UPDATESTATE = 100;//2013-07-19修改时间计划表状态
    public final static int SMSB_WZXTASKACTION = 51;//2013-4-22修改执行状态
    public final static int SMSB_PKFSXX = 51;//2013-6-13发送信息
    public final static int SMSB_SFDYWSZ = 45;//2013-6-13发送信息
    /*批扣任务类型end*/
	public final static int SMSB_CHECKACTION = 29;//2006年10月30日 数据表内、表间关系检验

    public final static int SMSB_HZSBJKDACTION = 5; //汇总申报缴款单

    public final static int SMSB_HZJKSACTION = 6; //汇总缴款书，即：把完税证汇总成缴款书

    public final static int SMSB_READERACTION = 7; //读入块查询

    public final static int SMSB_CXWSZACTION = 8; //撤消完税证

    public final static int SMSB_CXJKSACTION = 9; //撤消缴款书

    public final static int SMSB_LOADACTION = 10; //导入文件

    public final static int SMSB_CREATECALENDAR = 11; //生成征期日历

    public final static int SMSB_PRINTACTION = 16; //打印

    public final static int SMSB_REPRINTACTION = 17; //打印完税证，重新取号进行打印

    public final static int SMSB_UPLOADACTION = 12; //文件上传

    public final static int SMSB_CXHZSBJKDACTION = 14; //撤销汇总申报缴款单

    public final static int SMSB_DELETEALLACTION = 15; //全部删除

    public final static int SMSB_INPUTACTION = 20; //录入

    public final static int SMSB_TOEXCELACTION = 21; //导出Excel文件

    public final static int SMSB_TOEXCELACTION1 = 22; //导出Excel文件1

    public final static int SMSB_QUERYACTION1 = 23; //查询1

    public final static int SMSB_CHANGEPAGEACTION = 24; //翻页

    public final static int SMSB_CHANGEPAGEACTION1 = 25; //翻页1

    public final static int SMSB_QUERYACTION2 = 26; //查询1

    public final static int SMSB_QUERYACTION3 = 27; //查询1

    public final static int SMSB_QUERYACTION4 = 28; //查询1
    /** SMSB_  上门申报减免税备案公用 */
    public final static int SMSB_ADDBAXX =103;
    //2009.4.7wcl添加，通过算计代码的方式查询告知反馈
    public final static int SMSB_GZSXFKJSJSDMQUERY = 29;
    //2009.4.7wcl添加，通过查询条件非算计代码的方式查询告知反馈
    public final static int SMSB_GZSXFKTJQUERY = 30;
  //2009.4.7wcl添加，查看告知事项的已阅读清单
    public final static int SMSB_GZSXYYDQUERY = 31;
  //2009.4.7wcl添加，查看告知事项的未阅读清单
    public final static int SMSB_GZSXWYDQUERY = 32;
    //2009.4.7wcl添加，查看告知事项的详细反馈
    public final static int SMSB_GZSXFKNR = 33;
   //2009.4.7wcl添加，构造行业类型
    public final static int SMSB_HYLXLIST = 34;
  //2009.4.7wcl添加，构造登记注册类型
    public final static int SMSB_DJLXLIST = 35;
  //2009.4.7wcl添加，构造纳税人状态
    public final static int SMSB_NSRZTLIST = 36;
  //2009.4.7wcl添加，构造所处街巷
    public final static int SMSB_SCJXLIST = 37;
  //2009.4.7wcl添加，构造地区居所
    public final static int SMSB_DQJSLIST = 38;
    public final static int SMSB_CXDQJSLIST = 39;
    public final static int SMSB_CXYHLIST = 46;

    //---------------- 扣缴企业所得税相关 --------------------
    // 查看明细信息
    public final static int SMSB_VIEWMX = 40;
    // 审核备案登记信息
    public final static int SMSB_SHENHE= 41;
    //构造xml
    public final static int SJJH_BUILDXML= 42;      
    //构造excel
    public final static int SJJH_BUILDXLS= 43;
    //查询地税分局机构列表
    public final static int SJJH_QUERYFJJG= 44;
    //报文保存
    public final static int SJJH_BWBC= 45; 
 // new add by yugw 20100827
    public final static int SMSB_NEXTSTEPACTION=50;//下一步
    /**企业所得税版本集成*/
    public final static String SBZL_QYSDS_BBJC_PROCESSOR="com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor.QysdsBbjcProcessor";
    //end 
    /**上门申报-综合申报中 初始化税种税目相关列表 **/
    public final static int SMSB_ZHSB_INITLIST = 100;

    public final static int SMSB_ZHSB_ADDSKSSRQ = 101;

    public final static int SMSB_ZHSB_GZSX = 102;
    
    public final static int SMSB_GRSDS_CXTZF = 201;
    public final static int SMSB_GRSDS_CXZJLX = 202;
    public final static int SMSB_GRSDS_ADD_TZF = 203;
    public final static int SMSB_GRSDS_DELETE_TZF = 204;
    public final static String SMSB_GRSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.grsds.processor.GrsdsProcessor";

    /** SMSB_ 上门申报的Processor常量列表 */

    /**个体工商户，零散税征收的完税证打印处理标志*/
    public final static String SMSB_WSZ_UNPRINT = "0"; //未打印

    public final static String SMSB_WSZ_PRINT = "1"; //已打印
    //定期定额户入库情况查询
    public final static String GTGSH_DQDEHRKCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gghsb.processor.DqdehrkcxProcessor";

    /** 个人工商户 使用 */
    public final static String GTGSH_WSZLR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gtgsh.processor.GtgshWszlrProcessor";

    public final static String GTGSH_JKSLR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gtgsh.processor.GtgshJkslrProcessor";

    public final static String GTGSH_HZJKS_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gtgsh.processor.GtgshHzjksProcessor";

    public final static String GTGSH_ZFJKS_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gtgsh.processor.GtgshZfjksProcessor";

    public final static String GTGSH_CXWSZ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gtgsh.processor.GtgshCxwszProcessor";

    public final static String GTGSH_CXJKS_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gtgsh.processor.GtgshCxjksProcessor";

    public final static String GTGSH_JKSPRINT_PROCESSOR =
            "com.ttsoft.bjtax.smsb.print.processor.JksPrintProcessor";

    public final static String GTGSH_WSZPRINT_PROCESSOR =
            "com.ttsoft.bjtax.smsb.print.processor.WszPrintProcessor";
    public final static String GTGSH_KKXXCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gghsb.processor.KkxxcxProcessor";

    public final static String JKSCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.jkscx.processor.JkscxProcessor";

    /**
     * 公用的 电子缴款专用缴款书
     */
    public final static String PRINT_JKSQDPRINT_PROCESSOR =
            "com.ttsoft.bjtax.smsb.print.processor.JksqdPrintProcessor";


    /**
     * 扣款状态查询processor
     * add by hxf 20050617
     */
    public final static String GTGSH_KKZTCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gghsb.processor.KkztcxProcessor";

    /** 零散证收 使用 */
    public final static String LSZS_WSZLR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.lszs.processor.LszsWszlrProcessor";

    public final static String LSZS_JKSLR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.lszs.processor.LszsJkslrProcessor";

    public final static String LSZS_HZJKS_PROCESSOR =
            "com.ttsoft.bjtax.smsb.lszs.processor.LszsHzjksProcessor";

    public final static String LSZS_ZFJKS_PROCESSOR =
            "com.ttsoft.bjtax.smsb.lszs.processor.LszsZfjksProcessor";

    public final static String LSZS_CXWSZ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.lszs.processor.LszsCxwszProcessor";

    public final static String LSZS_CXJKS_PROCESSOR =
            "com.ttsoft.bjtax.smsb.lszs.processor.LszsCxjksProcessor";

    public final static String LSZS_JKSPRINT_PROCESSOR =
            "com.ttsoft.bjtax.smsb.print.processor.JksPrintProcessor";

    public final static String LSZS_WSZPRINT_PROCESSOR =
            "com.ttsoft.bjtax.smsb.print.processor.WszPrintProcessor";

    /** 告知维护 使用 */
    public final static String GZWH_GZSXWH_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gzwh.processor.GzsxwhProcessor";

    public final static String GZWH_XZGZSX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gzwh.processor.XzgzsxProcessor";
    /** 告知反馈查询 使用 */
    public final static String GZWH_GZSXFKCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.gzsxfkcx.processor.GzsxfkcxProcessor";

    /** 征期维护 使用 */
    public final static String ZQWH_ZQRLCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.ZqrlcxProcessor";

    public final static String ZQWH_ZQLXWH_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.ZqlxwhProcessor";

    public final static String ZQWH_JJRWH_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.JjrwhProcessor";

    public final static String ZQWH_SZZQDZ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.SzzqdzProcessor";

    public final static String ZQWH_MXCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.ZqrlmxcxProcessor";

    public final static String ZQWH_MXWH_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.ZqrlmxwhProcessor";

    public final static String ZQWH_ZQLXMXWH_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zqwh.processor.ZqlxmxwhProcessor";

    /** 综合申报 使用 */
    public final static String ZHSB_SBLR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbProcessor";

    /** 综合申报_评估补税 使用 20090327*/
    public final static String ZHSB_PGBS_SBLR_PROCESSOR =
        "com.ttsoft.bjtax.smsb.zhsb.pgbs.processor.ZhsbPgbsProcessor";


    /** 印花税管理 使用*/
    public final static String YHSGL_GMLR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsgmlrProcessor";

    public final static String YHSGL_GMHZ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsgmhzProcessor";

    public final static String YHSGL_GMHZCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsgmhzcxProcessor";

    public final static String YHSGL_XSDR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsxsdrProcessor";

    public final static String YHSGL_XSHZ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsxshzProcessor";

    public final static String YHSGL_XSHZCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsxshzcxProcessor";

    public final static String YHSGL_GMLRCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsgmlrcxProcessor";

    public final static String YHSGL_GMLRCK_PROCESSOR =
            "com.ttsoft.bjtax.smsb.yhsgl.processor.YhsgmlrckProcessor";

    /** 企业所得税年报 使用 */
    public final static String SBZL_QYSDSNB_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor.QysdsnbProcessor";

    /** 事业单位社会团体所得税申报 使用 */
    public final static String SBZL_SYDWSHTTSRSB_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor.SydwshttsrsbProcessor";

    /** 企业基本财务指标情况 使用 */
    public final static String SBZL_QYJBCWZB_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor.QyjbcwzbProcessor";

    /** 代售代征代扣税款申报表 使用 */
    public final static String SBZL_DSDZDKSB_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor";

    /** 代售代征代扣税款申报表-增加一行 使用 */
    public final static String SBZL_DSDZDKSBA_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdka.processor.DsdzdkaProcessor";

    public final static String SBZL_DSDZDKCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkcxProcessor";

    /** 外籍个人所得税月份申报表 使用 */
    public final static String WJGR_PROCESSOR =
            "com.ttsoft.bjtax.smsb.wjgr.processor.WjgrProcessor";
    /** 无应纳税（费）款申报 使用 */
    public final static String WYNSKSB_PROCESSOR =
            "com.ttsoft.bjtax.smsb.wynsk.processor.WynsksbProcessor";
    public final static String WYNSKCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.wynsk.processor.WynskcxProcessor";
    public final static String WRKCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.wrkcx.processor.WrkcxProcessor";
    /** 打印电子专用完税证 使用 */
    public final static String DYDZWSZ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.dzwsz.processor.DzwszProcessor";

    // start add by xinyy 20091121
    public final static String JMSSB_CJRJYJMSCXTJ_PROCESSOR = 
    	"com.ttsoft.bjtax.smsb.jmssb.processor.CjrjyjmscxtjProcessor";
    // start add by chendy 20091130
    public final static String JMSSB_CJRJYJMSB_PROCESSOR = 
    	"com.ttsoft.bjtax.smsb.jmssb.processor.CjrjyjmsbProcessor";
    public final static String JMSSB_CJRJYJMSB_DWXZ_SQL = 
    	"select a.cjrdwxzdm||'|'||a.cjrdwxzmc from dmdb.sf_dm_azcjrdwxz a order by a.cjrdwxzdm";

    public final static String SBZL_QYJBCZB_PROCESSOR =
            "com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.processor.QyjbcwzbCxProcessor";
    public final static String JKCX_PROCESSOR =
            "com.ttsoft.bjtax.smsb.jkcx.processor.JkcxProcessor";
    //start add by qinw 2006.06.28
    public final static String ZJYJMSB_PROCESSOR =
        "com.ttsoft.bjtax.smsb.zjyjmsb.processor.ZjyjmsbProcessor";
    public final static String ZJYJMCX_PROCESSOR =
        "com.ttsoft.bjtax.smsb.zjyjmsb.processor.ZjyjmcxProcessor";
    public final static String DHKSCX_Processor =
        "com.ttsoft.bjtax.smsb.lwpk.processor.DhkscxProcessor";
    public final static String PLBCX_Processor =
        "com.ttsoft.bjtax.smsb.lwpk.processor.PlkscxProcessor";
    
    
    //end   add by qinw 2006.06.28
    //start add by qianchao 2005.10.25
    public final static String MAP_VO_FORM = "FORM";
    public final static String MAP_VO_YPYSorYPDS = "YPYSorYPDS";
    public final static String MAP_VO_YHLW = "YHLW";
    //end add by qianchao 2005.10.25

    //start add by hazhengze 2005.12.20
    public final static String SFGL_QYSDS_BBFS_JB = "00";
    public final static String SFGL_QYSDS_BBFS_NB = "01";
    //end add by hazhengze 2005.12.20

    //start added code by qianchao 2006-2-24
    public final static String DZYJ_PROCESSOR =
            "com.ttsoft.bjtax.smsb.dzyj.processor.DzyjProcessor";

    //end   added code by qianchao 2006-2-24

    //start added code by hazhengze 2006-5-20
    public final static String SBBYZZCL_PROCESSOR =
            "com.ttsoft.bjtax.smsb.zhsb.processor.SbbyzclProcessor";

    //end added code by hazhengze 2006-5-20
    public final static String SBZL_QSJKSB_PROCESSOR =
        "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbProcessor";


	//******************************************modify code start 2006-10-20*******************************************//
	//  start added code by liwenhua 2006-10-20
	/** 核定征收企业所得税季报 */
	public final static String SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.processor.HdzssdsjbProcessor";

	/** 查账征收企业所得税季报 */
	public final static String SBZL_QYSDSJBNEW_CZQYSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.processor.CzqysdsjbProcessor";

	/** 查账征收企业所得税年报(主表) */
	public final static String SBZL_QYSDSNBNEW_ZB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zb.processor.ZbProcessor";

	/** 销售(营业)收入及其他收入明细表(适用于一般企业) */
	public final static String SBZL_QYSDSNBNEW_SRBYBQY_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbybqy.processor.SrbybqyProcessor";

	/** 金融保险企业主营业务收入明细表(适用于金融企业) */
	public final static String SBZL_QYSDSNBNEW_SRBJRQY_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbjrqy.processor.SrbjrqyProcessor";

	/** 事业单位、社会团体、民办非企业单位收入项目明细表(适用于事业单位) */
	public final static String SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbsydw.processor.SrbsydwProcessor";

	/** 成本费用明细表（适用于一般企业） */
	public final static String SBZL_QYSDSNBNEW_CBMXBYBQY_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbybqy.processor.CbmxbybqyProcessor";

	/** 核定征收企业所得税年报 */
	public final static String SBZL_QYSDSNBNEW_HDZSSDSNB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.processor.HdzssdsnbProcessor";

	/** 保险准备金提转差纳税调整表（适用于金融企业） */
	public final static String SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.processor.BxzbjbProcessor";

	/** 呆帐准备计提明细表（适用于金融企业） */
	public final static String SBZL_QYSDSNBNEW_DZZBJTMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.dzzbjtmxb.processor.DzzbjtmxbProcessor";

	/** 工资薪金和三项费用明细表 */
	public final static String SBZL_QYSDSNBNEW_GZXJMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gzxjmxb.processor.GzxjmxbProcessor";

	/** 坏帐损失明细表（适用于一般企业） */
	public final static String SBZL_QYSDSNBNEW_HZSSMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hzssmxb.processor.HzssmxbProcessor";

	/** 资产折旧、摊销明细表 */
	public final static String SBZL_QYSDSNBNEW_ZCMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zcmxb.processor.ZcmxbProcessor";

	/** 捐赠支出明细表 */
	public final static String SBZL_QYSDSNBNEW_JZZCMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jzzcmxb.processor.JzzcmxbProcessor";

	/** 技术开发费加计扣除额明细表 */
	public final static String SBZL_QYSDSNBNEW_JSKFFMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jskffmxb.processor.JskffmxbProcessor";

	/** 境外所得计算明细表 */
	public final static String SBZL_QYSDSNBNEW_JWSDMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jwsdmxb.processor.JwsdmxbProcessor";

	/** 广告费支出明细表 */
	public final static String SBZL_QYSDSNBNEW_GKFZCMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gkfzcmxb.processor.GkfzcmxbProcessor";

	/** 免税所得明细表 */
	public final static String SBZL_QYSDSNBNEW_MSSDMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.processor.MssdmxbProcessor";

	//  end added code by liwenhua 2006-10-20
	//  start added code by fujiangxia 2006-10-18
	/** 金融保险企业营业成本明细表（适用于金融企业） */
	public final static String SBZL_QYSDSNBNEW_CBMXBJRQY_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.processor.CbmxbjrqyProcessor";

	/** 事业单位、社会团体、民办非企业单位支出项目明细表(适用于事业单位) */
	public final static String SBZL_QYSDSNBNEW_CBMXBSYDW_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbsydw.processor.CbmxbsydwProcessor";

	/** 投资所得(损失)明细表 */
	public final static String SBZL_QYSDSNBNEW_TZMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.tzmxb.processor.TzmxbProcessor";

	/** 纳税调整增加项目明细表 */
	public final static String SBZL_QYSDSNBNEW_NSTZMXZJB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.processor.NstzmxzjbProcessor";

	/** 纳税调整减少项目明细表 */
	public final static String SBZL_QYSDSNBNEW_NSTZMXJSB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxjsb.processor.NstzmxjsbProcessor";

	/** 税前弥补亏损明细表 */
	public final static String SBZL_QYSDSNBNEW_MBKSMXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mbksmxb.processor.MbksmxbProcessor";

	/** 查账企业所得税年报主页面 */
	public final static String SBZL_QYSDSNBNEW_MAIN_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.main.processor.QysdsMainProcessor";

	//  end added code by fujiangxia 2006-10-18
	//******************************************modify code end 2006-10-20*********************************************//

	/** 2008版 企业所得税 processor  */

	/*---------张一军------------*/
	public final static String SBZL_QYSDSNB2008_SRMXB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srmxb.processor.SrmxbProcessor2008";
    public final static String SBZL_QYSDSNB2008_JRQYCBFYMXB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jrqycbfymxb.processor.JrqycbfymxbProcessor2008";
    public final static String SBZL_QYSDSNB2008_QYSDSMBKSMXB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.qysdsmbksmxb.processor.QysdsmbksmxbProcessor2008";
    public final static String SBZL_QYSDSNB2008_ZCZJTXNSTZMXB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zczjtxnstzmxb.processor.ZczjtxnstzmxbProcessor2008";

    /*---------贺智勇------------*/
    public final static String SBZL_QYSDSNB2008_SRBSYDW_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srbsydw.processor.SrbsydwProcessor2008";
    public final static String SBZL_QYSDSNB2008_NSTZMXB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.nstzmxb.processor.NstzmxbProcessor2008";
    public final static String SBZL_QYSDSNB2008_YGYJZJLNSTZB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ygyjzjlnstzb.processor.YgyjzjlnstzbProcessor";
    public final static String SBZL_QYSDSNB2008_GQTZSDMXB_PROCESSOR2008 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.gqtzsdmxb.processor.GqtzsdmxbProcessor";

  
    
    /*---------李海华------------*/
    /** 附表一（2）金融企业收入明细表 */
    public final static String SBZL_QYSDSNB2008_JRQYSRMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jrqysrmx.processor.JrqysrmxProcessor";

    /** 附表二（3）事业单位、社会团体、民办非企业单位支出明细表 */
    public final static String SBZL_QYSDSNB2008_SYSTMBFQYZCMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.systmbfqyzcmx.processor.SystmbfqyzcmxProcessor";

    /** 附表五 税收优惠明细表 */
    public final static String SBZL_QYSDSNB2008_SSYHMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ssyhmx.processor.SsyhmxProcessor";

    /** 附表十 资产减值准备项目调整明细表 */
    public final static String SBZL_QYSDSNB2008_ZCJZTZMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.processor.ZcjztzmxProcessor";

    /** 企业所得税年度纳税申报表（B类）主表 */
    public final static String SBZL_QYSDSNB2008_HDZSSDSNB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.hdzssdsnb.processor.HdzssdsnbProcessor";

    /** B类 附表一 税收优惠明细表 */
    public final static String SBZL_QYSDSNB2008_HDZSSSYHMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.hdzsssyhmx.processor.HdzsssyhmxProcessor";

    /*---------王晓敏------------*/
    /**成本费用明细表*/
    public final static String SBZL_QYSDSNB2008_CBMXBYBQY_PROCESSOR2008="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.processor.CbmxbybqyProcessor2008";

    /**----------------扣缴企业所得税processor-----------------*/
    public final static String SBZL_KJQYSDS_HTBADJB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.processor.BadjbProcessor";
	/**扣缴企业所得税报告表*/
    public final static String SBZL_KJQYSDSBGB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.processor.KjqysdsbgbProcessor";
  /**扣缴企业所得税生成税收缴款书*/
    public final static String SBZL_KJSSJKS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.processor.KjssjksProcessor";
    /**扣缴企业所得税电子台帐*/
    public final static String SBZL_KJQYSDS_DZTZ_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.processor.DztzProcessor";
    /**汇总企业所得税报文交换*/
    public final static String SMSB_SJJH_PROCESSOR = "com.ttsoft.bjtax.smsb.sjjh.processor.SjjhProcessor";

	//******************************************modify code start 2006-11-01*******************************************//
    
    
    
	//  start added code by liwenhua 2006-11-01
	/** 企业所得税年度纳税申报表 ID、NAME*/
	public final static String TABLE_ID_ZB = "1";
	public final static String TABLE_NAME_ZB = "企业所得税年度纳税申报表";

	/** 附表一(１)销售(营业)收入及其他收入明细表 ID、NAME*/
	public final static String TABLE_ID_SRBYBQY = "2";
	public final static String TABLE_NAME_SRBYBQY = "附表一(1)销售(营业)收入及其他收入明细表";

	/**附表一(２)金融企业收入明细表  ID、NAME*/
	public final static String TABLE_ID_SRBJRQY = "3";
	public final static String TABLE_NAME_SRBJRQY = "附表一(2)金融企业收入明细表";

	/**附表一(３)事业单位、社会团体、民办非企业单位收入项目明细表  ID、NAME*/
	public final static String TABLE_ID_SRBSYDW = "4";
	public final static String TABLE_NAME_SRBSYDW = "附表一(3)事业单位、社会团体、民办非企业单位收入项目明细表";

	/**附表二(１)成本费用明细表  ID、NAME*/
	public final static String TABLE_ID_CBMXBYBQY = "5";
	public final static String TABLE_NAME_CBMXBYBQY = "附表二(1)成本费用明细表";

	/**附表二(２)金融企业成本费用明细表  ID、NAME*/
	public final static String TABLE_ID_CBMXBJRQY = "6";
	public final static String TABLE_NAME_CBMXBJRQY= "附表二(2)金融企业成本费用明细表";

	/**附表二(３)事业单位、社会团体、民办非企业单位支出项目明细表  ID、NAME*/
	public final static String TABLE_ID_CBMXBSYDW = "7";
	public final static String TABLE_NAME_CBMXBSYDW = "附表二(3)事业单位、社会团体、民办非企业单位支出项目明细表";

	/**附表三 投资所得(损失)明细表  ID、NAME*/
	public final static String TABLE_ID_TZMXB = "8";
	public final static String TABLE_NAME_TZMXB = "附表三投资所得(损失)明细表";

	/**附表四纳税调整增加项目明细表  ID、NAME*/
	public final static String TABLE_ID_NSTZMXZJB = "9";
	public final static String TABLE_NAME_NSTZMXZJB = "附表四纳税调整增加项目明细表";

	/**附表五纳税调整减少项目明细表  ID、NAME*/
	public final static String TABLE_ID_NSTZMXJSB = "10";
	public final static String TABLE_NAME_NSTZMXJSB = "附表五纳税调整减少项目明细表";

	/**附表六税前弥补亏损明细表  ID、NAME*/
	public final static String TABLE_ID_MBKSMXB = "11";
	public final static String TABLE_NAME_MBKSMXB = "附表六税前弥补亏损明细表";

	/**附表七免税所得及减免税明细表  ID、NAME*/
	public final static String TABLE_ID_MSSDMXB = "12";
	public final static String TABLE_NAME_MSSDMXB = "附表七免税所得及减免税明细表";

	/**附表八捐赠支出明细表  ID、NAME*/
	public final static String TABLE_ID_JZZCMXB = "13";
	public final static String TABLE_NAME_JZZCMXB = "附表八捐赠支出明细表";

	/**附表九技术开发费加计扣除明细表  ID、NAME*/
	public final static String TABLE_ID_JSKFFMXB = "14";
	public final static String TABLE_NAME_JSKFFMXB= "附表九技术开发费加计扣除明细表";

	/**附表十境外所得税抵扣计算明细表  ID、NAME*/
	public final static String TABLE_ID_JWSDMXB = "15";
	public final static String TABLE_NAME_JWSDMXB = "附表十境外所得税抵扣计算明细表";

	/**附表十一广告费支出明细表  ID、NAME*/
	public final static String TABLE_ID_GKFZCMXB = "16";
	public final static String TABLE_NAME_GKFZCMXB = "附表十一广告费支出明细表";

	/**附表十二工资薪金和工会经费等三项经费明细表(非工效挂钩企业)ID、NAME*/
	public final static String TABLE_ID_GZXJMXB_FGX = "17";
	public final static String TABLE_NAME_GZXJMXB_FGX = "附表十二工资薪金和工会经费等三项经费明细表(非工效挂钩企业)";

	/**附表十二工资薪金和工会经费等三项经费明细表(工效挂钩企业)  ID、NAME*/
	public final static String TABLE_ID_TABLE_ID_GZXJMXB_GX = "18";
	public final static String TABLE_NAME_GZXJMXB_GX= "附表十二工资薪金和工会经费等三项经费明细表(工效挂钩企业)";

	/**附表十三资产折旧、摊销明细表  ID、NAME*/
	public final static String TABLE_ID_ZCMXB = "19";
	public final static String TABLE_NAME_ZCMXB = "附表十三资产折旧、摊销明细表";

	/**附表十四(１)坏帐损失明细表  ID、NAME*/
	public final static String TABLE_ID_HZSSMXB = "20";
	public final static String TABLE_NAME_HZSSMXB = "附表十四(1)坏帐损失明细表";

	/**附表十四(２)呆帐准备计提明细表  ID、NAME*/
	public final static String TABLE_ID_DZZBJTMXB = "21";
	public final static String TABLE_NAME_DZZBJTMXB = "附表十四(2)呆帐准备计提明细表";

	/**附表十四(３)保险准备金提转差纳税调整表  ID、NAME*/
	public final static String TABLE_ID_BXZBJ = "22";
	public final static String TABLE_NAME_BXZBJ = "附表十四(3)保险准备金提转差纳税调整表";

	/**企业所得税季度纳税申报表(适用于核定征收企业)  ID、NAME*/
	public final static String TABLE_ID_HDZSSDSJB = "23";
	public final static String TABLE_NAME_HDZSSDSJB = "企业所得税季度纳税申报表(适用于核定征收企业)";

	/**企业所得税年度纳税申报表(适用于核定征收企业)  ID、NAME*/
	public final static String TABLE_ID_HDZSSDSNB = "24";
	public final static String TABLE_NAME_HDZSSDSNB = "企业所得税年度纳税申报表(适用于核定征收企业)";

	/**企业所得税预缴纳税申报表  ID、NAME*/
	public final static String TABLE_ID_CZQYSDSJB = "25";
	public final static String TABLE_NAME_CZQYSDSJB = "企业所得税预缴纳税申报表";

	//  end added code by liwenhua 2006-11-01
	//******************************************modify code end 2006-11-01*********************************************//

    /********************************** start modify by tum 2008-02-29 ******************************************/
    /**企业所得税月(季)度预缴纳税申报表(A类)  ID、NAME*/
    public final static String TABLE_ID_CZQYSDSJB_2008 = "28";
    public final static String TABLE_NAME_CZQYSDSJB_2008 = "企业所得税月(季)度预缴纳税申报表(A类)";

    /**企业所得税月（季）度预缴纳税申报表（B类）  ID、NAME*/
    public final static String TABLE_ID_HDZSSDSJB_2008 = "29";
    public final static String TABLE_NAME_HDZSSDSJB_2008 = "企业所得税月（季）度预缴纳税申报表（B类）";

    /**企业所得税汇总纳税分支机构分配表  ID、NAME*/
    public final static String TABLE_ID_HZJNFZJG_2008 = "30";
    public final static String TABLE_NAME_HZJNFZJG_2008 = "企业所得税汇总纳税分支机构分配表";
    
    /**固定资产加速折旧（扣除）预缴情况统计表  ID、NAME,added by zhangj,2014.12.01*/
    public final static String TABLE_ID_GDZCJSZJYJQK_2014 = "33";
    public final static String TABLE_NAME_GDZCJSZJYJQK_2014 = "固定资产加速折旧（扣除）预缴情况统计表";

    /********************************** end modify by tum 2008-02-29 ******************************************/

    /********************************** start modify by wofei 2008-12-10 ******************************************/
    /**2008 企业所得税年度纳税申报表  ID、NAME*/
    public final static String TABLE_ID_2008_ZB ="1";
    public final static String TABLE_NAME_2008_ZB = "中华人民共和国企业所得税年度纳税申报表（A类）";

    public final static String TABLE_ID_2008_1_1 = "2";
    public final static String TABLE_NAME_2008_1_1 = "附表一（1）收入明细表";

    public final static String TABLE_ID_2008_1_2 = "3";
    public final static String TABLE_NAME_2008_1_2 = "附表一（2）金融企业收入明细表";

    public final static String TABLE_ID_2008_1_3 = "4";
    public final static String TABLE_NAME_2008_1_3 = "附表一（3）事业单位、社会团体、民办非企业单位收入明细表";

    public final static String TABLE_ID_2008_2_1 ="5";
    public final static String TABLE_NAME_2008_2_1 = "附表二（1）成本费用明细表";

    public final static String TABLE_ID_2008_2_2 = "6";
    public final static String TABLE_NAME_2008_2_2 = "附表二（2）金融企业成本费用明细表";

    public final static String TABLE_ID_2008_2_3 = "7";
    public final static String TABLE_NAME_2008_2_3 = "附表二（3） 事业单位、社会团体、民办非企业单位支出明细表";

    public final static String TABLE_ID_2008_3 ="8";
    public final static String TABLE_NAME_2008_3 = "附表三 纳税调整项目明细表";

    public final static String TABLE_ID_2008_4 = "9";
    public final static String TABLE_NAME_2008_4 = "附表四 企业所得税弥补亏损明细表";

    public final static String TABLE_ID_2008_5 ="10";
    public final static String TABLE_NAME_2008_5 = "附表五 税收优惠明细表";

    public final static String TABLE_ID_2008_6 ="11";
    public final static String TABLE_NAME_2008_6 = "附表六 境外所得税抵免计算明细表";

    public final static String TABLE_ID_2008_7 = "12";
    public final static String TABLE_NAME_2008_7 = "附表七 以公允价值计量资产纳税调整表";

    public final static String TABLE_ID_2008_8 = "13";
    public final static String TABLE_NAME_2008_8 = "附表八 广告费和业务宣传费跨年度纳税调整表";

    public final static String TABLE_ID_2008_9 = "14";
    public final static String TABLE_NAME_2008_9 = "附表九 资产折旧、摊销纳税调整明细表";

    public final static String TABLE_ID_2008_12 = "24";
    public final static String TABLE_NAME_2008_12 = "企业所得税年度纳税申报表（B类）主表";

    public final static String TABLE_ID_2008_13 = "30";
    public final static String TABLE_NAME_2008_13 = "B类 附表一 税收优惠明细表";

    public final static String TABLE_ID_2008_10 = "15";
    public final static String TABLE_NAME_2008_10 = "附表十 资产减值准备项目调整明细表";

    public final static String TABLE_ID_2008_11 = "16";
    public final static String TABLE_NAME_2008_11 = "附表十一 长期股权投资所得（损失）明细表";
    


	/**
	 * 企业所得税基本信息
	 */
	public final static String SESSIONKEY_QYSDSNEWFORM="SESSION_KEY_QYSDSNEWFORM";

	/**
	 * 工资管理类型-非功效
	 */
	public final static String SMSB_GZGLLX_FGX="FGX";
	/**
	 * 工资管理类型-功效
	 */
	public final static String SMSB_GZGLLX_GX="GX";
	/**
	 * 表内、表间关系校验结果
	 */
	public final static String CHECK_RESULT_HTML="CHECK_RESULT_HTML";

	//*****************************************MODIFY BY LIANGLW 2006-11-6***********************************************//
	/**
	 * 企业执行的财务会计制度	 */
	/**
	 * 12,13企业（小企业）会计制度
	 */
	public static final String CWKJZD01 = "12";//
	public static final String CWKJZDMC01 = "12,13企业（小企业）会计制度";//

	/**
	 * 14金融企业会计制度
	 */
	public static final String CWKJZD02 = "14";//
	public static final String CWKJZDMC02 = "14金融企业会计制度";//

	/**
	 * 15事业单位、社会团体、民办非企业单位会计制度
	 */
	public static final String CWKJZD03 = "15";//
	public static final String CWKJZDMC03 = "15事业单位、社会团体、民办非企业单位会计制度";//




	/**
	 * 工资管理形式，前两项为非工效挂钩
	 * 12月27号新需求，非工效挂钩的分为计税工资和全额扣除两项，在基本信息表中只显示 计税工资 的值 文字描述为非功效
	 *
	 * */
	/**
	 * 计税工资
	 */
	public static final String GZGLXS01 = "01";//
	public static final String GZGLXSMC01 = "计税工资";//
	/**
	 * 全额扣除
	 */
	public static final String GZGLXS02 = "02";//
	public static final String GZGLXSMC02 = "全额扣除";//

	/**
	 * 工资管理形式，前两项为非工效挂钩
	 * 11月30号新需求，非工效挂钩的不再分为计税工资和全额扣除两项 合为一项
	 *
	 * */
//	/**
//	 * 非工效挂钩
//	 */
//	public static final String GZGLXS01 = "01";//
//	public static final String GZGLXSMC01 = "非工效挂钩";//
	/**
	 * 工效挂钩
	 */
	public static final String GZGLXS03 = "03";//
	public static final String GZGLXSMC03 = "工效挂钩";//

	/**
	 * 2006版 报表描述符，需要填写的表样
	 */
	/**
	 * 企业（小企业）会计制度 非工效挂钩
	 */
	public static final String BBMSF10 = "1,2,5,8,9,10,11,12,13,14,15,16,17,19,20";//填写的报表
	/**
	 * 企业（小企业）会计制度 工效挂钩
	 */
	public static final String BBMSF11 = "1,2,5,8,9,10,11,12,13,14,15,16,18,19,20";//填写的报表
	/**
	 * 金融企业会计制度 非工效挂钩
	 */
	public static final String BBMSF20 = "1,3,6,8,9,10,11,12,13,14,15,16,17,19,21,22";//填写的报表
	/**
	 * 金融企业会计制度 工效挂钩
	 */
	public static final String BBMSF21 = "1,3,6,8,9,10,11,12,13,14,15,16,18,19,21,22";//填写的报表
	/**
	 * 事业单位、社会团体、民办非企业单位会计制度 非工效挂钩
	 */
	public static final String BBMSF30 = "1,4,7,8,9,10,11,12,13,14,15,16,17,19,20";//填写的报表
	/**
	 * 事业单位、社会团体、民办非企业单位会计制度 工效挂钩
	 */
	public static final String BBMSF31 = "1,4,7,8,9,10,11,12,13,14,15,16,18,19,20";//填写的报表

	/**
	 * 2008版 报表描述符，需要填写的表样
	 */
	/**
	 * 企业（小企业）会计制度 （一般企业）
	 */
	public static final String BBMSF_2008_10 = "1,2,5,8,9,10,11,12,13,14,15,16";//填写的报表
	/**
	 * 金融企业会计制度
	 */
	public static final String BBMSF_2008_20 = "1,3,6,8,9,10,11,12,13,14,15,16";//填写的报表
	/**
	 * 事业单位、社会团体、民办非企业单位会计制度
	 */
	public static final String BBMSF_2008_30 = "1,4,7,8,9,10,11,12,13,14,15,16";//填写的报表

	/**
	 * 2008版 企业所得税 版本号
	 */
	public static final String QYSDS_VERSION_2008="20080101";

	/**
	 * 减免企业所得税的类型	 */
	/**
	 * 没有优惠
	 */
	public static final String JMLXNO = "";
	public static final String JMLXMCNO = "没有优惠";
	/**
	 * 高新技术企业
	 */
	public static final String JMLX9010 = "9010";
	public static final String JMLXMC9010 = "高新技术企业";
	/**
	 * 软件产业
	 */
	public static final String JMLX9020 = "9020";//
	public static final String JMLXMC9020 = "软件产业";//
	/**
	 * 集成电路产业
	 */
	public static final String JMLX9030 = "9030";//
	public static final String JMLXMC9030 = "集成电路产业";//
	/**
	 * 福利生产企业
	 */
	public static final String JMLX9090 = "9090";//
	public static final String JMLXMC9090 = "福利生产企业";//
	/**
	 * 劳动就业服务企业
	 */
	public static final String JMLX9070 = "9070";//
	public static final String JMLXMC9070 = "劳动就业服务企业";//
	/**
	 * 其他优惠
	 */
	public static final String JMLXOTHER = "9990";//
	public static final String JMLXMCOTHER = "其他优惠";//

	/** 企业所得税年报基本信息表 */
	public final static String SBZL_QYSDSNBNEW_JBXXB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jbxxb.processor.JbxxbProcessor";

	/**
	 * 附表十用到的设置项 不用在基本信息页面展示。
	 */
	/**
	 * 定率抵扣方法
	 */
	public static final String JWSDDK01 = "01";//
	/**
	 * 分国不分项抵扣方法
	 */
	public static final String JWSDDK02 = "02";//

	//******************************************MODIFY BY LIANGLW 2006-11-6***********************************************//

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

    //************************* modify by tum 2008-2-26 **********************************
    public final static String SBZL_QYSDSJB2008_ZFJG_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.processor.ZfjgqysdsjbProcessor";
	/** 2008核定征收企业所得税季报 */
	public final static String SBZL_QYSDSJB2008_HDZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.processor.HdzssdsjbProcessor";
	/** 2008查帐征收企业所得税季报 */
	public final static String SBZL_QYSDSJB2008_CZZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.processor.CzzssdsjbProcessor";
	

	/********************************** start add by gaoyh 2012-3-1 ******************************************/
    /** 2012查帐征收企业所得税季报 */
	public final static String SBZL_QYSDSJB2012_CZZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.processor.CzzssdsjbProcessor";
	/** 2012核定征收企业所得税季报 */
	public final static String SBZL_QYSDSJB2012_HDZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.processor.HdzssdsjbProcessor";	
	/** 2012汇总纳税分支机构所得税分配表 */
    public final static String SBZL_QYSDSJB2012_ZFJG_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.processor.ZfjgqysdsjbProcessor";  
    /** 企业所得税汇总纳税总机构分配表 */
    public final static String SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hznszjgfpb.processor.HznszjgfpbProcessor";
   
    
    
    /** 2012企业所得税季报版本号 */
    public final static String SBZL_QYSDSJB_VERSION_2012 = "20120101";
   
    /********************************** end add by gaoyh 2012-3-1 ******************************************/

	
	/** 2012核定征收企业所得税年报  gaoyh added by 2013-01-23 */
	public final static String SBZL_QYSDSNB2012_HDZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.hdzssdsnb.processor.HdzssdsnbProcessor";	

	/** 减免税备案新增 */


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

    // -------------------------- 扣缴企业所得税常量 begin --------------------------
    // 国家地区列表
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ = "GJDQ";
    // 币种
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_BZ = "BZ";
    //申报所得类型列表
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX="SBSDLX";
    //每页显示的条数
    public final static String PAGE_MYTS="10";
    //非居民企业国别：01-港澳台|02-外国
    public final static String PAGE_FJMQYGB_GAT="01";
    public final static String PAGE_FJMQYGB_WG="02";
    
    //分局机构
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_DEPT = "DEPT";
    //报文类型
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_BWLX = "BWLX";
    // 其它提示信息
    public static final String SMSB_OTHER_SUCCESS = "OTHER_SUCCESS";
    // -------------------------- 扣缴企业所得税常量 end   --------------------------
    
    
    /** 2009版 企业所得税 processor  */

	/*---------张一军------------*/
	public final static String SBZL_QYSDSNB2009_SRMXB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srmxb.processor.SrmxbProcessor2009";
    public final static String SBZL_QYSDSNB2009_JRQYCBFYMXB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jrqycbfymxb.processor.JrqycbfymxbProcessor2009";
    public final static String SBZL_QYSDSNB2009_QYSDSMBKSMXB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.qysdsmbksmxb.processor.QysdsmbksmxbProcessor2009";
    public final static String SBZL_QYSDSNB2009_ZCZJTXNSTZMXB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zczjtxnstzmxb.processor.ZczjtxnstzmxbProcessor2009";

    /*---------贺智勇------------*/
    public final static String SBZL_QYSDSNB2009_SRBSYDW_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srbsydw.processor.SrbsydwProcessor2009";
    public final static String SBZL_QYSDSNB2009_NSTZMXB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.nstzmxb.processor.NstzmxbProcessor2009";
    public final static String SBZL_QYSDSNB2009_YGYJZJLNSTZB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ygyjzjlnstzb.processor.YgyjzjlnstzbProcessor";
    public final static String SBZL_QYSDSNB2009_GQTZSDMXB_PROCESSOR2009 = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gqtzsdmxb.processor.GqtzsdmxbProcessor";

  
    
    /*---------李海华------------*/
    /** 附表一（2）金融企业收入明细表 */
    public final static String SBZL_QYSDSNB2009_JRQYSRMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jrqysrmx.processor.JrqysrmxProcessor";

    /** 附表二（3）事业单位、社会团体、民办非企业单位支出明细表 */
    public final static String SBZL_QYSDSNB2009_SYSTMBFQYZCMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.systmbfqyzcmx.processor.SystmbfqyzcmxProcessor";

    /** 附表五 税收优惠明细表 */
    public final static String SBZL_QYSDSNB2009_SSYHMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ssyhmx.processor.SsyhmxProcessor";

    /** 附表十 资产减值准备项目调整明细表 */
    public final static String SBZL_QYSDSNB2009_ZCJZTZMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zcjztzmx.processor.ZcjztzmxProcessor";

    /** 企业所得税年度纳税申报表（B类）主表 */
    public final static String SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzssdsnb.processor.HdzssdsnbProcessor";

    /** B类 附表一 税收优惠明细表 */
    public final static String SBZL_QYSDSNB2009_HDZSSSYHMX_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzsssyhmx.processor.HdzsssyhmxProcessor";

    /*---------王晓敏------------*/
    /**成本费用明细表*/
    public final static String SBZL_QYSDSNB2009_CBMXBYBQY_PROCESSOR2009="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.cbmxbybqy.processor.CbmxbybqyProcessor2009";
    
    /********************************** start modify by wofei 2009-12-10 ******************************************/
    
    /********************************** begin of qysdsndsb2009 ******************************************/
    /**2009 企业所得税年度纳税申报表  ID、NAME*/
    public final static String TABLE_ID_2009_ZB ="1";
    public final static String TABLE_NAME_2009_ZB = "中华人民共和国企业所得税年度纳税申报表（A类）";

    public final static String TABLE_ID_2009_1_1 = "2";
    public final static String TABLE_NAME_2009_1_1 = "附表一（1）收入明细表";

    public final static String TABLE_ID_2009_1_2 = "3";
    public final static String TABLE_NAME_2009_1_2 = "附表一（2）金融企业收入明细表";

    public final static String TABLE_ID_2009_1_3 = "4";
    public final static String TABLE_NAME_2009_1_3 = "附表一（3）事业单位、社会团体、民办非企业单位收入明细表";

    public final static String TABLE_ID_2009_2_1 ="5";
    public final static String TABLE_NAME_2009_2_1 = "附表二（1）成本费用明细表";

    public final static String TABLE_ID_2009_2_2 = "6";
    public final static String TABLE_NAME_2009_2_2 = "附表二（2）金融企业成本费用明细表";

    public final static String TABLE_ID_2009_2_3 = "7";
    public final static String TABLE_NAME_2009_2_3 = "附表二（3） 事业单位、社会团体、民办非企业单位支出明细表";

    public final static String TABLE_ID_2009_3 ="8";
    public final static String TABLE_NAME_2009_3 = "附表三 纳税调整项目明细表";

    public final static String TABLE_ID_2009_4 = "9";
    public final static String TABLE_NAME_2009_4 = "附表四 企业所得税弥补亏损明细表";

    public final static String TABLE_ID_2009_5 ="10";
    public final static String TABLE_NAME_2009_5 = "附表五 税收优惠明细表";

    public final static String TABLE_ID_2009_6 ="11";
    public final static String TABLE_NAME_2009_6 = "附表六 境外所得税抵免计算明细表";

    public final static String TABLE_ID_2009_7 = "12";
    public final static String TABLE_NAME_2009_7 = "附表七 以公允价值计量资产纳税调整表";

    public final static String TABLE_ID_2009_8 = "13";
    public final static String TABLE_NAME_2009_8 = "附表八 广告费和业务宣传费跨年度纳税调整表";

    public final static String TABLE_ID_2009_9 = "14";
    public final static String TABLE_NAME_2009_9 = "附表九 资产折旧、摊销纳税调整明细表";

    public final static String TABLE_ID_2009_12 = "24";
    public final static String TABLE_NAME_2009_12 = "企业所得税年度纳税申报表（B类）主表";

    public final static String TABLE_ID_2009_13 = "30";
    public final static String TABLE_NAME_2009_13 = "B类 附表一 税收优惠明细表";

    public final static String TABLE_ID_2009_10 = "15";
    public final static String TABLE_NAME_2009_10 = "附表十 资产减值准备项目调整明细表";

    public final static String TABLE_ID_2009_11 = "16";
    public final static String TABLE_NAME_2009_11 = "附表十一 长期股权投资所得（损失）明细表";
    
    public final static String TABLE_ID_2012_12 = "17";
    public final static String TABLE_NAME_2012_12 = "企业所得税汇总纳税总机构分配表";
    
    /**
	 * 2009版 报表描述符，需要填写的表样
	 */
	/**
	 * 企业（小企业）会计制度 （一般企业）
	 */
	public static final String BBMSF_2009_10 = "1,2,5,8,9,10,11,12,13,14,15,16";//填写的报表
	/**
	 * 金融企业会计制度
	 */
	public static final String BBMSF_2009_20 = "1,3,6,8,9,10,11,12,13,14,15,16";//填写的报表
	/**
	 * 事业单位、社会团体、民办非企业单位会计制度
	 */
	public static final String BBMSF_2009_30 = "1,4,7,8,9,10,11,12,13,14,15,16";//填写的报表
	
		/**
	 * 税源标识为总填写总机构分配表 表17
	 */
	public static final String BBMSF_2012_17 = ",17";//填写的报表
	
	
    
    public static final String QYSDS_VERSION_2009="20090101";
    /********************************** end of qysdsndsb2009 ******************************************/

	/**********************************hdzsqysdsnb2012 gaoyh added by 2013-01-23******************************************/
    public static final String QYSDS_VERSION_2012="20120101";
    
    /********************************** begin of qysdsjmsbagl******************************************/
    
    public final static int QYSDSJMSBAJL_CZLX_ADD=1;
    public final static int QYSDSJMSBAJL_CZLX_MODIFY=2;
    public final static int QYSDSJMSBAJL_CZLX_CHECK=3;
    public final static int QYSDSJMSBAJL_CZLX_VIEW=4;
    public final static int QYSDSJMSBAJL_CZLX_ZFBG=5;    
    public final static int QYSDSJMSBAJL_CZLX_BGZX=6;    
    public final static int SMSB_ZFBGACTION = 30;    
    public final static int SMSB_BGACTION = 31;    
    public final static int SMSB_QUERYZFBG = 29; //查询作废变更    
    public final static int QYSDSJMSBAJL_BEFORCHECK= 56;
    public final static int QYSDSJMSBAJL_CHECK= 56;
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASXDM = "JMSBASXDM";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX = "JMSBASQLX";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQZT = "JMSBASQZT";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG = "JMSZGSWJG";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JMSBAZFYY = "JMSBAZFYY";    
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_ZYZHLYZLDM = "JMSBAZYLYZL";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JSZRLXDM = "JMSBAJSZRLX";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_YFFYLYDM = "JMSBAYFFYLY";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_FWYWFWDM = "JMSBAFWYWFW";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM = "JMSBAGXJSQYLY";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_ZYSBLXDM = "JMSBAZYSBLX";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_GGJCSSXMLXDM = "JMSBAGGJCSSXMLX";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JNJSXMLXDM = "JMSBAJNJSXMLX";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_GXJSLYDM = "JMSBAGXJSLYDM";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_ZXLXDM = "JMSBAZXLXDM";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_WHSYLXDM = "JMSBAWHSYLXDM";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_DMLXDM = "JMSBADMLXDM";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_MZQYSDSDM = "JMSBAMZQYSDSDM";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JBQYSDSDM = "JMSBAJBQYSDSDM";
    public final static String SESSIONKEY_QYSDSJMSBAJLFORM="SESSION_KEY_QYSDSJMSBAJLFORM";
    public final static String SMSB_QYSDSJMSBAJLMAIN_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.processor.QysdsJmsbajlMainProcessor";
	public final static String SMSB_QYSDSJMSBAJLPRINT_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.processor.QysdsJmsbajlPrintProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX01_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.processor.Basx01Processor";
    public final static String SMSB_QYSDSJMSBA_BASX01DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.processor.Basx01DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX02_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx02.processor.Basx02Processor";
    public final static String SMSB_QYSDSJMSBA_BASX02DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx02.processor.Basx02DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX03_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx03.processor.Basx03Processor";
    public final static String SMSB_QYSDSJMSBA_BASX04_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx04.processor.Basx04Processor";
    public final static String SMSB_QYSDSJMSBA_BASX04DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx04.processor.Basx04DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX05_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx05.processor.Basx05Processor";
    public final static String SMSB_QYSDSJMSBA_BASX06_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx06.processor.Basx06Processor";
    public final static String SMSB_QYSDSJMSBA_BASX06DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx06.processor.Basx06DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX07_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx07.processor.Basx07Processor";
    public final static String SMSB_QYSDSJMSBA_BASX07DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx07.processor.Basx07DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX08_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx08.processor.Basx08Processor";
    public final static String SMSB_QYSDSJMSBA_BASX09_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx09.processor.Basx09Processor";
    public final static String SMSB_QYSDSJMSBA_BASX10_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx10.processor.Basx10Processor";
    public final static String SMSB_QYSDSJMSBA_BASX11_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx11.processor.Basx11Processor";
    public final static String SMSB_QYSDSJMSBA_BASX12_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx12.processor.Basx12Processor";
    public final static String SMSB_QYSDSJMSBA_BASX13A_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13a.processor.Basx13AProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX13ADETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13a.processor.Basx13ADetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX13B_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.processor.Basx13BProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX13BDETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.processor.Basx13BDetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX14A_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14a.processor.Basx14AProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX14ADETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14a.processor.Basx14ADetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX14B_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.processor.Basx14BProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX14BDETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.processor.Basx14BDetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX15_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx15.processor.Basx15Processor";
    public final static String SMSB_QYSDSJMSBA_BASX15DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx15.processor.Basx15DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX16_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx16.processor.Basx16Processor";
    public final static String SMSB_QYSDSJMSBA_BASX16DETAIL_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx16.processor.Basx16DetailProcessor";
    public final static String SMSB_QYSDSJMSBA_BASX17_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx17.processor.Basx17Processor";
    public final static String SMSB_QYSDSJMSBA_BASX18_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx18.processor.Basx18Processor";
    public final static String SMSB_QYSDSJMSBA_BASX19_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx19.processor.Basx19Processor";
    public final static String SMSB_QYSDSJMSBA_BASX20_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx20.processor.Basx20Processor";
    /********************************** end of qysdsjmsbagl******************************************/
    /********************************** begin of wszm tujb******************************************/
    public final static String WSZM_PROCESSOR =  "com.ttsoft.bjtax.smsb.wszm.processor.WszmProcessor";
    public final static String WSZMCX_PROCESSOR =  "com.ttsoft.bjtax.smsb.wszm.processor.WszmCxProcessor";
    public final static String WSZM_JKS ="01"; //缴款书
    public final static String WSZM_LSWSZ ="02"; //零散完税证
    public final static String WSZM_GTWSZ ="03"; //个体工商户完税证
    public final static String WSZM_QSWSZ ="04"; //契税完税证
    public final static String WSZM_CCWSZ ="05"; //车船完税证
    public final static String WSZM_BD ="06"; //保单
    public final static String WSZM_SRTHS ="07"; //收入退还书
    public final static String WSZM_SGWSPZ ="08";  //手工完税凭证
    
    public final static String WSZ_CLBJDM ="7777";  //手工退税完税凭证
    public final static String CCWSZ_LYDM_01 ="01";  //车船完税证来源代码01
    public final static String CCWSZ_LYDM_21 ="21";  //车船完税证来源代码21
    public final static String CCWSZ_LYDM_04 ="04";  //车船完税证来源代码04(保险代征)
    public final static String CCWSZ_RKBZ_1 ="1";  //车船完税证入库标志（已入库）
    public final static String CCWSZ_RKBZ_0 ="0";  //车船完税证入库标志（未入库）
    public final static String SRTHS_DZBZ_1 ="1";  //收入退还书（已对账）
    public final static String SRTHS_HXBZ_1 ="1";  //收入退还书（已核销）
    public final static String WSZM_SGWSPZ_JS ="0";  //手工缴税完税凭证
    public final static String WSZM_SGWSPZ_TS ="1";  //手工缴税完税凭证
    
  
    //完税证明有效标志
	public static final String WSZM_YXBZ_0="0";// 0--有效
	public static final String WSZM_YXBZ_1="1";// 1-- 注销
    /*
     * 车船税税款滞纳金收入对应税种税目代码
     */
    public static final String SZSM_FOR_ZNJFK = "090091";
    
    /********************************** end of wszm tujb******************************************/
    
    /********************************** add by tangchangfu 2013-11-23 (新票样 --手工录入税收收入完税证) start******************************************/
    public final static String SMSSB_SGSSWSZMLR_PROCESSOR = "com.ttsoft.bjtax.smsb.sgsswszmlr.processor.SgsswszmlrProcessor";
    /********************************** end of qysdsjmsbagl add by tangchangfu 2013-11-23 (新票样 --手工录入税收收入完税证) start******************************************/

	/**
	 * 2013年后离线客户端用户需要填写的表
	 * 暂时用户循环删除表数据用
	 * add by  wangcy   2013-12-06
	 */
	public final static String[] TABLE_ID_ALL= { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
        "14", "15", "16", "17"};
	
	/**
	 * 企业所得税年度预缴纳税申报表id
	*/
	public static String TABLE_ID_CZZSSDSNB_2012 = "31";
	public static String TABLE_NAME_CZZSSDSNB_2012 = "企业所得税年度预缴纳税申报表";

	/**
	 * 企业所得税汇总纳税分支机构分配表id
	*/
	public static String TABLE_ID_ZFJGSDSNB_2012 = "32";
	public static String TABLE_NAME_ZFJGSDSNB_2012 = "企业所得税汇总纳税分支机构分配表";
	
    /**
	 * 企业所得税税源标识   与网上申报保持一致
	 * “地”、“总”、“分”  
	 * “国”或“不”标识填报为空其他的纳税人"
	 * add by wangcy 2013-12-03
	 */
	public final static String CODE_QYSDS_SYBS_D = "01";
	public final static String CODE_NAME_QYSDS_SYBS_D = "地";
	
	public final static String CODE_QYSDS_SYBS_Z = "02";
	public final static String CODE_NAME_QYSDS_SYBS_Z = "总";
	
	public final static String CODE_QYSDS_SYBS_F = "03";
	public final static String CODE_NAME_QYSDS_SYBS_F = "分";
	
	public final static String CODE_QYSDS_SYBS_OTHER = "04";
	public final static String CODE_NAME_QYSDS_SYBS_OTHER = "国、不或其他";
	
	public final static String SBZL_QYSDSNB_VERSION_2012="20130101";
	
	/**
	 * 税源鉴定名称常量
	 */
	public final static String CODE_QYSDS_ZGFWJD_DLNSR = "01";
	public final static String CODE_NAME_QYSDS_ZGFWJD_DLNSR = "独立纳税人";
	
	public final static String CODE_QYSDS_ZGFWJD_KSSZJG = "02";
	public final static String CODE_NAME_QYSDS_ZGFWJD_KSSZJG = "跨省市总机构纳税人";
	
	public final static String CODE_QYSDS_ZGFWJD_KSSFZJG = "03";
	public final static String CODE_NAME_QYSDS_ZGFWJD_KSSFZJG = "跨省市分支机构纳税人";
	
	public final static String CODE_QYSDS_ZGFWJD_ZFJGJZBSZJG = "04";
	public final static String CODE_NAME_QYSDS_ZGFWJD_ZFJGJZBSZJG = "总分机构均在本市的的总机构纳税人";
	
	public final static String CODE_QYSDS_ZGFWJD_OTHER = "05";
	
    /**
     * 汇总纳税方法相关常量及提示信息
     */
    //汇总纳税
    public final static String HZNSFF_QYSDSNB2012_CZZSSDS_HZNS = "1";
    //独立纳税
    public final static String HZNSFF_QYSDSNB2012_CZZSSDS_DLNS = "2";
    //汇总纳税方式-总机构
    public final static String HZNSFS_QYSDSNB2012_CZZSSDS_ZJG = "1";
    //汇总纳税方式-分支机构
    public final static String HZNSFS_QYSDSNB2012_CZZSSDS_FZJG = "2";
	
	/********************************** start add by wangcy 2013-12-07 ******************************************/
    /** 2012企业所得税汇总纳税分支机构 */
	public final static String SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.processor.CzzssdsNbProcessor";
	/** 2012汇总纳税分支机构所得税分配表 */
    public final static String SBZL_QYSDSNB2012_ZFJG_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.processor.ZfjgqysdsNbProcessor";   
   
    //减免税备案  生产和装配伤残人员专门用品备案事项 add by lijn
    public final static String SMSB_QYSDSJMSBA_BASX21_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx21.processor.Basx21Processor";
    //减免税备案  节能服务公司实施合同能源管理项目的所得备案事项 add by wangcy 
    public final static String SMSB_QYSDSJMSBA_BASX22_PROCESSOR = "com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.processor.Basx22Processor";
    public final static String PAGE_SELECT_ITEMS_SESSION_KEY_JNJPJSGZXMLX = "JNJPJSGZXMLX";
    // add by tangchangfu 2014-04-08 无税减免税项目 start
    public final static String SMSB_ZJJMSDMWH_PROCESSOR="com.ttsoft.bjtax.smsb.jccswh.processor.ZjjmsdmwhProcessor";
    /**
     * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
     * 无税申报--无税申报原因--造册户标志（即如果申报原因的造册户标志为1，表示需要把该户置成造册户）
     */
	public final static String SMSB_WYNSKSB_ZCHBSDM = "1";
	/**
	 * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
	 * 无纳税申报原因为“其他”
	 */
	public final static String SMSB_WYNSKSB_WSSBYY_QT = "99";
	/**
	 * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
	 * （更新纳税人基本信息表）申报分类状态--当期有税户
	 */
	public final static String SMSB_NSRSBFLZT_YSH ="11";
	/**
	 * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
	 * （更新纳税人基本信息表）申报分类状态--造册户
	 */
	public final static String SMSB_NSRSBFLZT_ZCH ="14";
	/**
	 * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
	 * 更新（纳税人基本信息表）申报分类状态操作
	 */
	public final static String SMSB_SBFLZT_OPRATOR_UPDATE ="UPDATE";
	/**
	 * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
	 * 重置（纳税人基本信息表）申报分类状态操作
	 */
	public final static String SMSB_SBFLZT_OPRATOR_RESET ="RESET";
	/**
	 * @author tangchangfu
     * @category 无税减免税项目
     * @since 2014-04-08
	 * （纳税人基本信息表）申报分类状态变更项目代码
	 */
	public final static String SMSB_SBFLZT_BGXMDM_143 ="143";
	
	
	//用于总局减免税代码维护初始化时获得各种数据的map键值
	public final static String SMSB_MAP_KEY_SZ="SMSB_MAP_KEY_SZ";//税种
	public final static String SMSB_MAP_KEY_JMXZDL="SMSB_MAP_KEY_JMXZDL";//减免性质大类
	public final static String SMSB_MAP_KEY_JMXZXL="SMSB_MAP_KEY_JMXZXL";//减免性质小类
	
	
	
	//总局减免税维护 两个值：“updateYxbs”--更新有效标识  “updateAll”--修改所有信息
	public final static String SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS="UPDATE_YXBS";//更新有效标识
	public final static String SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL="UPDATE_ALL";//更新条目所有信息
	
	//查询单条信息
	public static final int QUERYONE=100;
	
	//申报分类变更来源
	/**
	 * 有税申报变更
	 */
	public static final String SMSB_SBFLBGLY_YSSBBG_20="20";
	
	/**
	 * 无税申报变更 
	 */
	public static final String SMSB_SBFLBGLY_WSSBBG_30="30";	
	/**
	 * 无税申报变更 之申报造册户原因的无税申报变更
	 */
	public static final String SMSB_SBFLBGLY_WSSBBG_33="33";
	
	
	
    
    // add by tangchangfu 2014-04-08 无税减免税项目 end

	
	
    /**
	 * 企业所得税清算基本信息
	 */
    public final static String SESSIONKEY_QYQSSDSBAGLFORM="SESSION_KEY_QYQSSDSBAGLFORM";
    public final static String SESSIONKEY_QYQSSDSSBGLFORM="SESSION_KEY_QYQSSDSSBGLFORM";
    public final static int QYQSSDSBAGL_CZLX_CHECK=13;
    public final static int QYQSSDSBAGL_CZLX_VIEW=14;
    public final static int QYQSSDSBAGL_CZLX_ADD=15;
    public final static int SMSB_ADDACTION = 104;
	public final static String SESSIONKEY_QYQSSDSBASEFORM="SESSION_KEY_QYQSSDSBASEFORM";
    /**2014 企业所得税清算申报  ID、NAME */ 
    public final static String QYQSSDS_TABLE_ID_2014_ZB ="0101";
    public final static String QYQSSDS_TABLE_NAME_2014_ZB = "中华人民共和国企业清算所得税申报表";
    
    public final static String QYQSSDS_TABLE_ID_2014_1 ="0102";
    public final static String QYQSSDS_TABLE_NAME_2014_1 = "附表一 资产处置损益明细表";

    public final static String QYQSSDS_TABLE_ID_2014_2 = "0103";
    public final static String QYQSSDS_TABLE_NAME_2014_2 = "附表二 负债清偿损益明细表";

    public final static String QYQSSDS_TABLE_ID_2014_3 ="0104";
    public final static String QYQSSDS_TABLE_NAME_2014_3 = "附表三 剩余财产计算和分配明细表";
    
    public static final String QYQSSDS_VERSION_2014="20140101";
    public final static String[] QYQSSDS_TABLE_ID_ALL = { "0101", "0102", "0103", "0104" };
    
    public static final String SMSB_QYQSSDS2014_BASHZTBS="BASHZTBS";
    public static final String SMSB_QYQSSDS2014_SBSHZTBS="SBSHZTBS";
    public static final String SMSB_QYQSSDS2014_BASHTGRQ="BASHTGRQ";

    //清算期校验
    public static final String QYQSSDS_CHECKQSQ="com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.processor.CheckQsqprocessor";
    
    
    
    //独立纳税预缴
    public final static String SZSMDM_DLNSYS = "300000";
    //总机构预缴
    public final static String SZSMDM_ZJGYS = "300001";
    //分支机构预缴
    public final static String SZSMDM_FZJGYS = "300002";
    //独立纳税汇算清缴
    public final static String SZSMDM_DLNSHSQJ = "300011";
    //总机构汇算清缴(2013年以前)
    public final static String SZSMDM_ZJGHSQJ_2013YQ = "300012";
    //总机构汇算清缴(2013年以后)
    public final static String SZSMDM_ZJGHSQJ_2013YH = "300013";
    //分支机构汇算清缴
    public final static String SZSMDM_FZJGHSQJ = "300015";
    
   //企业所得税征管范围鉴定类型代码
    //独立纳税人
    public final static String QYSDSZGFWJDLX_DLNSR= "01";
    //总分机构均在本市的总机构纳税人
    public final static String QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR= "04";
    //跨省市总机构纳税人
    public final static String QYSDSZGFWJDLX_KSSZJGNSR= "02";
   //跨省市分支机构纳税人
    public final static String QYSDSZGFWJDLX_KSSFZJGNSR= "03";
    
    /** 2014企业所得税季报版本号 */
    public final static String SBZL_QYSDSJB_VERSION_2014 = "20140101";
    /** 2014查帐征收企业所得税季报 */
	public final static String SBZL_QYSDSJB2014_CZZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.processor.CzzssdsjbProcessor";
	/** 2014核定征收企业所得税季报 */
	public final static String SBZL_QYSDSJB2014_HDZSSDS_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.processor.HdzssdsjbProcessor";	
	/** 2014汇总纳税分支机构所得税分配表 */
    public final static String SBZL_QYSDSJB2014_ZFJG_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.processor.ZfjgqysdsjbProcessor";  
	/** 2014固定资产加速折旧（扣除）预缴情况统计表 */
    public final static String SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR = "com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.processor.GdzcjszjyjqkjbProcessor";  
     
  // add by tujb 2014-07-08 申报入库不一致过滤滞纳金项目 begin
	/**
	 * 申报方式代码 滞纳金
	 */
	public static final String SMSB_FSDM_ZNJ="10";
	// add by tujb 2014-07-08 申报入库不一致过滤滞纳金项目 end
	//added by zhangj
    public final static String SESSIONKEY_QYQSSDSWXBAGLFORM="SESSION_KEY_QYQSSDSWXBAGLFORM";
    public static final String SMSB_QYQSSDS2014_SFWXJXBA="SFWXJXBA";
    public static final String SMSB_QYQSSDS2014_WXBA="0";
    public static final String SMSB_QYQSSDS2014_XBA="1";
}
