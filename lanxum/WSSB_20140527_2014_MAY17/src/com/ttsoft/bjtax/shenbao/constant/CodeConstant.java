package com.ttsoft.bjtax.shenbao.constant;

import java.math.BigDecimal;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 一些常量</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class CodeConstant
{
    public CodeConstant()
    {
    }
     /** WSSB_网上申报通用事件常量 */
    public final static int SMSB_SHOWACTION = 0;

    public final static int SMSB_SAVEACTION = 1;

    public final static int SMSB_DELETEACTION = 2;

    public final static int SMSB_QUERYACTION = 3;
    /**
     * 税款类型：正常缴款代码
     */
    public final static String SKLXDM_ZCJK = "100";

    /**
     * 税款类型：正常缴款代码
     */
    public final static String SKLXMC_ZCJK = "正常";

    public final static String KKZT_ZF = "未缴款";
    public final static String KKZT_YJK = "已缴款";
    public final static String KKZT_DDKK = "等待银行扣款中";
    public final static String KKZT_YRK = "已入库";
    public final static String KKZT_QT = "其他";
    
    /**
     * 税款类型：补缴欠税名称
     */
    public final static String SKLXDM_BJQS = "700";

    /**
     * 税款类型：四代解缴代码
     */
    public final static String SKLXDM_SDJJ = "200";
    
    /**
     * 税款类型：正常缴款代码
     */
    public final static String SKLXMC_SDJJ = "四代解缴";
    
    /**
     * 税款类型：正常缴款代码
     */
    public final static String SKLXMC_DZJK = "委托代征";

    /**
     * 网上申报的方式代码
     */
    public final static String FSDM_WSSB = "5";
    
    /**
     * 电话申报的方式代码
     */
    public final static String FSDM_DHSB = "3";

//********************数据来源******************************************
   /**
     * 申报主表对应的数据来源：国税代征
     */
    public final static String SJLY_SB_GSDZ = "39";
    /**
     * 申报主表对应的数据来源：申报录入
     */
    public final static String SJLY_SB_SBLR = "11";
    /**
     * 申报主表对应的数据来源：三代汇总
     */
    public final static String SJLY_SB_SDHZ = "16";
    /**
     * 自然人申报录入数据
     */
    public final static String SJLY_SB_ZRR_SBLR = "19";

//*****************登记注册类型******************************************
    /**
     * 登记注册类型代码--事业单位
     */
    public static final String DJZCLXDM_SYDW = "196";

   /**
    * 登记注册类型代码--集体企业
    */
    public static final String DJZXLXDM_JTQY = "120";

    /**
     * 登记注册类型代码--社会团体
     */
    public static final String DJZCLXDM_SHTT = "193";

    /**
     * 登记注册类型代码--港、澳、台常驻代表机构
     */
    public static final String DJZCLXDM_GATCZDBJG = "250";

    /**
     * 登记注册类型代码--外国常驻代表机构
     */
    public static final String DJZCLXDM_WGCZDBJG = "350";


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
     * Description：个人独资企业
     */
    public static final String DJZCLXDM_GRDZQY = "175";
    
    /**
     * Description：个人合伙企业
     */
    public static final String DJZCLXDM_GRHHQY = "420";
    
    /**
     * 登记注册类型代码：私营独资企业
     */
    public static final String DJZCLXDM_SYDZQY = "171";

    /**
     * 登记注册类型代码：私营合伙企业
     */
    public static final String DJZCLXDM_SYHHQY = "172";
    
    /**
     * Description：个体工商户
     */
    public static final String DJZCLXDM_GTGSH = "410";
    

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
    public static final String ZSFSDM_CZZS_03 = "03";
  	
    /**
     * 定率征收
     */
  	public static final String ZSFSDM_DLZS = "04";
    /**
     * 查账征收
     */
    public static final String ZSFSDM_CZZS = "12";

    /**
     * 核定征收
     */
    public static final String ZSFSDM_HDZS = "11";

//    /**
//     * 应税所得率
//     */
//    public static final String ZSFSDM_YSSDL = "05";

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

    /*------------------------征期类型代码--------------------------*/
    /**
     * 按月
     */
    public static final String ZQLXDM_MONTH = "12";
    /**
     * 按季
     */
    public static final String ZQLXDM_QUARTER = "4";
    /**
     * 按半年
     */
    public static final String ZQLXDM_HALFYEAR = "2";
    /**
     * 按年
     */
    public static final String ZQLXDM_YEAR = "1";

    /*------------------------减免类型代码------------------------*/
    /**
     * 审批减免类型
     */
    public static final String JMLX_SP = "1";

	/**
     * 法定性减免类型
     */
    public static final String JMLX_FD = "2";
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
     * 已缴款
     */
    public static final String CLBJDM_YJK = "12";
    /**
     * 已完税
     */
    public static final String CLBJDM_YWS = "14";
    /**
     * 待划款
     */
    public static final String CLBJDM_DHK = "21";

    /**
     * 核定征收
     */
    public static final String WQYYS_HDZS = "03";

    /**
     * 经费换算
     */
    public static final String WQYYS_JFHS = "02";

    /**
     * 按实申报
     */
    public static final String WQYYS_ASSB = "01";

    /**
     * 申报表项目代码：编辑性质：计算
     */
    public static final String SBBXMDM_BJXZ_CAL = "1";

    /*----------------------自然人字符串填充值------------------------*/
    public static final String SPACE = "SPACE";

    /**
     * 中国自然人默认注册类型代码
     */
    public static final String DEFAULT_CHINA_ZRR_DJZCLXDM = "410";

    /**
     * 外国自然人默认注册类型代码
     */
    public static final String DEFAULT_FOREIGN_ZRR_DJZCLXDM = "390";

    /**
     * 自然人默认国家标准行业代码
     * 2012年1月开始启用 Modified by Zhangyj 20120111
     */
    //public static final String DEFAULT_ZRR_GJBZHYDM = "8390";
    public static final String DEFAULT_ZRR_GJBZHYDM = "8190";

    /**
     * 税款负担情况代码(单位负担全部)
     */
    public static final String SKFDQK_DWFDQB = "1";

    /**
     * 税款负担情况代码(个人负担全部)
     */
    public static final String SKFDQK_GRFDQB = "3";


   /**
    * 纳税人状态的配置属性,纳税评估接口
    */
   public static final int JKS_JIEKOU_NSPG = 0;

   /**
    * 纳税人状态的配置属性，根据申报数据生成缴款书接口
    */
   public static final int JKS_JIEKOU_SBSJ = 1;

   /**
    * 纳税人状态的配置属性，根据申报数据生成缴款书接口，带银行信息
    */
   public static final int JKS_JIEKOU_SBSJWITHYH = 2;

   /**
    * 属性值的分割字符
    */
   public static final String SEPARATOR = ",";
   
   /**
    * 保存在REQUEST中的返回地址
    */
   public static final String REQ_KEY_RETURN_ADDRESS = "REQ_KEY_RETURN_ADDRESS";
   /**
    * 保存在REQUEST中的申报结果信息
    */
   public static final String REQ_KEY_SUCCESS_MSG = "REQ_KEY_SUCCESS_MSG";
   /**
    * 保存在REQUEST中的申报申报回执的电子原件编号
    */
   public static final String REQ_SHENBAO_HUIZHI_KEY = "REQ_SHENBAO_HUIZHI_KEY";
   
   
   
   
   
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
	
//	/**
//	 * 报表描述符，需要填写的表样
//	 */	
//	/**
//	 * 企业（小企业）会计制度 非工效挂钩
//	 */
//	public static final String BBMSF10 = "1,2,5,8,9,10,11,12,13,14,15,16,17,19,20";//填写的报表
//	/**
//	 * 企业（小企业）会计制度 工效挂钩
//	 */
//	public static final String BBMSF11 = "1,2,5,8,9,10,11,12,13,14,15,16,18,19,20";//填写的报表
//	/**
//	 * 金融企业会计制度 非工效挂钩
//	 */
//	public static final String BBMSF20 = "1,3,6,8,9,10,11,12,13,14,15,16,17,19,21,22";//填写的报表
//	/**
//	 * 金融企业会计制度 工效挂钩
//	 */
//	public static final String BBMSF21 = "1,3,6,8,9,10,11,12,13,14,15,16,18,19,21,22";//填写的报表
//	/**
//	 * 事业单位、社会团体、民办非企业单位会计制度 非工效挂钩
//	 */
//	public static final String BBMSF30 = "1,4,7,8,9,10,11,12,13,14,15,16,17,19,20";//填写的报表
//	/**
//	 * 事业单位、社会团体、民办非企业单位会计制度 工效挂钩
//	 */
//	public static final String BBMSF31 = "1,4,7,8,9,10,11,12,13,14,15,16,18,19,20";//填写的报表
	
	
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
	

//	/** 企业所得税年度纳税申报表 ID、NAME*/
//	public final static String TABLE_ID_ZB = "1";
//	public final static String TABLE_NAME_ZB = "企业所得税年度纳税申报表";
//	
//	/** 附表一（１）销售（营业）收入及其他收入明细表 ID、NAME*/
//	public final static String TABLE_ID_SRBYBQY = "2";
//	public final static String TABLE_NAME_SRBYBQY = "附表一（１）销售（营业）收入及其他收入明细表";
//	
//	/**附表一（２）金融企业收入明细表  ID、NAME*/
//	public final static String TABLE_ID_SRBJRQY = "3";
//	public final static String TABLE_NAME_SRBJRQY = "附表一（２）金融企业收入明细表";
//	
//	/**附表一（３）事业单位、社会团体、民办非企业单位收入项目明细表  ID、NAME*/
//	public final static String TABLE_ID_SRBSYDW = "4";
//	public final static String TABLE_NAME_SRBSYDW = "附表一（３）事业单位、社会团体、民办非企业单位收入项目明细表";
//	
//	/**附表二（１）成本费用明细表  ID、NAME*/
//	public final static String TABLE_ID_CBMXBYBQY = "5";
//	public final static String TABLE_NAME_CBMXBYBQY = "附表二（１）成本费用明细表";
//	
//	/**附表二（２）金融企业成本费用明细表  ID、NAME*/
//	public final static String TABLE_ID_CBMXBJRQY = "6";
//	public final static String TABLE_NAME_CBMXBJRQY= "附表二（２）金融企业成本费用明细表";
//	
//	/**附表二（３）事业单位、社会团体、民办非企业单位支出项目明细表  ID、NAME*/
//	public final static String TABLE_ID_CBMXBSYDW = "7";
//	public final static String TABLE_NAME_CBMXBSYDW = "附表二（３）事业单位、社会团体、民办非企业单位支出项目明细表";
//	
//	/**附表三 投资所得（损失）明细表  ID、NAME*/
//	public final static String TABLE_ID_TZMXB = "8";
//	public final static String TABLE_NAME_TZMXB = "附表三投资所得（损失）明细表";
//	
//	/**附表四纳税调整增加项目明细表  ID、NAME*/
//	public final static String TABLE_ID_NSTZMXZJB = "9";
//	public final static String TABLE_NAME_NSTZMXZJB = "附表四纳税调整增加项目明细表";
//	
//	/**附表五纳税调整减少项目明细表  ID、NAME*/
//	public final static String TABLE_ID_NSTZMXJSB = "10";
//	public final static String TABLE_NAME_NSTZMXJSB = "附表五纳税调整减少项目明细表";
//	
//	/**附表六税前弥补亏损明细表  ID、NAME*/
//	public final static String TABLE_ID_MBKSMXB = "11";
//	public final static String TABLE_NAME_MBKSMXB = "附表六税前弥补亏损明细表";
//	
//	/**附表七免税所得及减免税明细表  ID、NAME*/
//	public final static String TABLE_ID_MSSDMXB = "12";
//	public final static String TABLE_NAME_MSSDMXB = "附表七免税所得及减免税明细表";
//	
//	/**附表八捐赠支出明细表  ID、NAME*/
//	public final static String TABLE_ID_JZZCMXB = "13";
//	public final static String TABLE_NAME_JZZCMXB = "附表八捐赠支出明细表";
//	
//	/**附表九技术开发费加计扣除明细表  ID、NAME*/
//	public final static String TABLE_ID_JSKFFMXB = "14";
//	public final static String TABLE_NAME_JSKFFMXB= "附表九技术开发费加计扣除明细表";
//	
//	/**附表十境外所得税抵扣计算明细表  ID、NAME*/
//	public final static String TABLE_ID_JWSDMXB = "15";
//	public final static String TABLE_NAME_JWSDMXB = "附表十境外所得税抵扣计算明细表";
//	
//	/**附表十一广告费支出明细表  ID、NAME*/
//	public final static String TABLE_ID_GKFZCMXB = "16";
//	public final static String TABLE_NAME_GKFZCMXB = "附表十一广告费支出明细表";
//	
//	/**附表十二工资薪金和工会经费等三项经费明细表（非工效挂钩企业）ID、NAME*/
//	public final static String TABLE_ID_GZXJMXB_FGX = "17";
//	public final static String TABLE_NAME_GZXJMXB_FGX = "附表十二工资薪金和工会经费等三项经费明细表（非工效挂钩企业）";
//	
//	/**附表十二工资薪金和工会经费等三项经费明细表（工效挂钩企业）  ID、NAME*/
//	public final static String TABLE_ID_TABLE_ID_GZXJMXB_GX = "18";
//	public final static String TABLE_NAME_GZXJMXB_GX= "附表十二工资薪金和工会经费等三项经费明细表（工效挂钩企业）";
//	
//	/**附表十三资产折旧、摊销明细表  ID、NAME*/
//	public final static String TABLE_ID_ZCMXB = "19";
//	public final static String TABLE_NAME_ZCMXB = "附表十三资产折旧、摊销明细表";
//	
//	/**附表十四（１）坏帐损失明细表  ID、NAME*/
//	public final static String TABLE_ID_HZSSMXB = "20";
//	public final static String TABLE_NAME_HZSSMXB = "附表十四（１）坏帐损失明细表";
//	
//	/**附表十四（２）呆帐准备计提明细表  ID、NAME*/
//	public final static String TABLE_ID_DZZBJTMXB = "21";
//	public final static String TABLE_NAME_DZZBJTMXB = "附表十四（２）呆帐准备计提明细表";
//	
//	/**附表十四（３）保险准备金提转差纳税调整表  ID、NAME*/
//	public final static String TABLE_ID_BXZBJ = "22";
//	public final static String TABLE_NAME_BXZBJ = "附表十四（３）保险准备金提转差纳税调整表";
	
	/**企业所得税季度纳税申报表（适用于核定征收企业）  ID、NAME*/
	public final static String TABLE_ID_HDZSSDSJB = "23";
	public final static String TABLE_NAME_HDZSSDSJB = "企业所得税季度纳税申报表（适用于核定征收企业）";
	
	/**企业所得税年度纳税申报表（适用于核定征收企业）  ID、NAME*/
	public final static String TABLE_ID_HDZSSDSNB = "24";
	public final static String TABLE_NAME_HDZSSDSNB = "企业所得税年度纳税申报表（适用于核定征收企业）";
	
	/**企业所得税预缴纳税申报表  ID、NAME*/
	public final static String TABLE_ID_CZQYSDSJB = "25";
	public final static String TABLE_NAME_CZQYSDSJB = "企业所得税预缴纳税申报表";
	

	
	
	//******************************************MODIFY BY LIANGLW 2006-11-6***********************************************//
	
   
   
   //*********企业所得税2008************
	


	/**
	 * 企业（小企业）会计制度 
	 */
	public static final String BBMSF10_2008 = "1,2,5,8,9,10,11,12,13,14,15,16";//填写的报表17,
	/**
	 * 金融企业会计制度 
	 */
	public static final String BBMSF20_2008 = "1,3,6,8,9,10,11,12,13,14,15,16";//填写的报表17,
	/**
	 * 事业单位、社会团体、民办非企业单位会计制度
	 */
	public static final String BBMSF30_2008 = "1,4,7,8,9,10,11,12,13,14,15,16";//填写的报表17,
	
	/**
	 * 税源标识为总填写总机构分配表 表17
	 */
	public static final String BBMSF17_2012 = ",17";//填写表17,
	
	

	/** 企业所得税年度纳税申报表 ID、NAME*/
	public final static String TABLE_ID_ZB_2008 = "1";
	public final static String TABLE_NAME_ZB_2008 = "企业所得税年度纳税申报表";
	
	/** 附表一（１）收入明细表 ID、NAME*/
	public final static String TABLE_ID_FB1_1_2008 = "2";
	public final static String TABLE_NAME_FB1_1_2008 = "附表一（１）销售（营业）收入及其他收入明细表";
	
	/**附表一（２）金融企业收入明细表  ID、NAME*/
	public final static String TABLE_ID_FB1_2_2008 = "3";
	public final static String TABLE_NAME_FB1_2_2008 = "附表一（２）金融企业收入明细表";
	
	/**附表一（３）事业单位、社会团体、民办非企业单位收入明细表  ID、NAME*/
	public final static String TABLE_ID_FB1_3_2008 = "4";
	public final static String TABLE_NAME_FB1_3_2008 = "附表一（３）事业单位、社会团体、民办非企业单位收入明细表";
	
	/**附表二（１）成本费用明细表  ID、NAME*/
	public final static String TABLE_ID_FB2_1_2008 = "5";
	public final static String TABLE_NAME_FB2_1_2008 = "附表二（１）成本费用明细表";
	
	/**附表二（２）金融企业成本费用明细表  ID、NAME*/
	public final static String TABLE_ID_FB2_2_2008 = "6";
	public final static String TABLE_NAME_FB2_2_2008= "附表二（２）金融企业成本费用明细表";
	
	/**附表二（３）事业单位、社会团体、民办非企业单位支出明细表  ID、NAME*/
	public final static String TABLE_ID_FB2_3_2008 = "7";
	public final static String TABLE_NAME_FB2_3_2008 = "附表二（３）事业单位、社会团体、民办非企业单位支出明细表";
	
	/**附表三 纳税调整项目明细表  ID、NAME*/
	public final static String TABLE_ID_FB3_2008 = "8";
	public final static String TABLE_NAME_FB3_2008 = "附表三纳税调整项目明细表";
	
	/**附表四企业所得税弥补亏损明细表  ID、NAME*/
	public final static String TABLE_ID_FB4_2008 = "9";
	public final static String TABLE_NAME_FB4_2008 = "附表四企业所得税弥补亏损明细表";
	
	/**附表五税收优惠明细表  ID、NAME*/
	public final static String TABLE_ID_FB5_2008 = "10";
	public final static String TABLE_NAME_FB5_2008 = "附表五税收优惠明细表";
	
	/**附表六境外所得税抵免计算明细表  ID、NAME*/
	public final static String TABLE_ID_FB6_2008 = "11";
	public final static String TABLE_NAME_FB6_2008 = "附表六境外所得税抵免计算明细表";
	
	/**附表七以公允价值计量资产纳税调整表  ID、NAME*/
	public final static String TABLE_ID_FB7_2008 = "12";
	public final static String TABLE_NAME_FB7_2008 = "附表七以公允价值计量资产纳税调整表";
	
	/**附表八广告费和业务宣传费跨年度纳税调整表  ID、NAME*/
	public final static String TABLE_ID_FB8_2008 = "13";
	public final static String TABLE_NAME_FB8_2008 = "附表八广告费和业务宣传费跨年度纳税调整表";
	
	/**附表九资产折旧、摊销纳税调整明细表  ID、NAME*/
	public final static String TABLE_ID_FB9_2008 = "14";
	public final static String TABLE_NAME_FB9_2008= "附表九资产折旧、摊销纳税调整明细表";
	
	/**附表十资产减值准备项目调整明细表  ID、NAME*/
	public final static String TABLE_ID_FB10_2008 = "15";
	public final static String TABLE_NAME_FB10_2008 = "附表十资产减值准备项目调整明细表";
	
	/**附表十一股权投资所得（损失）明细表  ID、NAME*/
	public final static String TABLE_ID_FB11_2008 = "16";
	public final static String TABLE_NAME_FB11_2008 = "股权投资所得（损失）明细表";
	
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
	public final static String TABLE_ID_CZZSSDSNB_2012 = "31";
	public final static String TABLE_NAME_CZZSSDSNB_2012 = "企业所得税年度预缴纳税申报表";

	/**
	 * 企业所得税汇总纳税分支机构分配表id
	*/
	public final static String TABLE_ID_ZFJGSDSNB_2012 = "32";
	public final static String TABLE_NAME_ZFJGSDSNB_2012 = "企业所得税汇总纳税分支机构分配表";
	
	//******企业所得税2008*******************
   
	/**
	 * 企业所得税税源标识   与上门申报保持一致
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
	
	
	/**
	 * 增加征管范围鉴定后---鉴定名称常量
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
	 * 无税申报原因注销标识 减免税项目  tujb 200404
	 */
	public final static String WSSBYY_ZXBS_0 = "0";
	public final static String WSSBYY_ZXBS_1 = "1";
	
	/**
	 * 减免项目注销标识 减免税项目  tujb 200404
	 */
	public final static String JMXM_ZXBS_0 = "0";
	/**
	 * 减免项目有效标识 减免税项目  tujb 200404
	 */
	public final static String JMXM_YXBS_0 = "0";
	
	/**
	 * 无税申报原因其他原因 减免税项目  tujb 200404
	 */
	public final static String WSSBYY_QTYY_99 = "99";
	
	/**
	 * 网上申报减免税申报税款所属日期判断 减免税项目  tujb 200404
	 */
	public final static String WSSB_JMSB_ACCESSCONTROL_NY = "WSSB_JMSB_ACCESSCONTROL_NY";
	
	/**
	 * 网上申报减免税申报税款所属日期判断 减免税项目  tujb 200404
	 */
	public final static String WSSB_JMSB_ACCESSCONTROL_SKSSQ = "WSSB_JMSB_ACCESSCONTROL_SKSSQ";
	
	/**
	 * 网上申报减免税申报数据来源 减免税项目  tujb 200404
	 */
	public final static String WSSB_JMSB_SJLY = "11";
	
	/**
	 * 企业清算所得税相关
	 */
	public final static String QYQSSDS_VERSION_2014="20140101";
	public final static String[] QYQSSDS_TABLE_ID_ALL = { "0101", "0102", "0103", "0104" };
	public final static String QYQSSDS_TABLE_STR="0101,0102,0103,0104";
	
	//add by wangxq 20140608 start
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
	
	//add by wangxq 20140608 end
	
	
	//add by tujb 20140611 start
	/**
	 * 印花税 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_YHS = "16";
	
	/**
	 * 个体工商户 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_GTGSH = "05";
	
	/**
	 * 代征个体工商户 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_DZGTGSH = "0513";
	
	/**
	 * 房产税 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_FCS = "12";
	
	/**
	 * 个人出租住房4.5% 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_GRCCZF_120032 = "120032";
	
	/**
	 * 个人出租非住房6% 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_GRCCFZF_120033 = "120033";
	
	/**
	 * 个人出租非住房 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_GRCCFZF_050703 = "050703";
	
	/**
	 * 个人出租住房 网上申报减免税申报数据来源 减免税项目  tujb 201406
	 */
	public final static String WSSB_YSSB_GRCCZF_050704 = "050704";
	
	/**
     * 减免税申报数据来源
     */
    public final static String JMSSB_SJLY = "11";
    
    /**
	 * 印花税 网上申报有税申报 减免税项目及印花税项目  tujb 201407
	 */
	public final static String WSSB_YSSB_SKLXBS = "90";
	
	/**
	 * 印花税 网上申报有税申报 减免税项目及印花税项目  tujb 201407
	 */
	public final static BigDecimal WSSB_YSSB_SJSE_200000 = new BigDecimal("200000.00");
	
	/**
	 * 印花税 网上申报有税申报 减免税项目及印花税项目  tujb 201407
	 */
	public final static String WSSB_YSSB_ZQLX_08 = "1";
	
	//add by tujb 20140611 end
}
