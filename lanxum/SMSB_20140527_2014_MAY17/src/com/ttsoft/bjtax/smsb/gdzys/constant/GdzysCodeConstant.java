package com.ttsoft.bjtax.smsb.gdzys.constant;

/**
 * <p>
 * Title: 北京地税综合管理系统  耕地占有税征收
 * </p>
 * <p>
 * Description:常量定义
 * </p>
 * 
 * @author wangxq
 * @version 1.1
 */
public class GdzysCodeConstant {
	public GdzysCodeConstant() {
	}
    /** SMSB_  上门申报通用事件常量 */
    public final static int SMSB_SHOWACTION = 0;

    public final static int SMSB_SAVEACTION = 1;

    public final static int SMSB_DELETEACTION = 2;

    public final static int SMSB_QUERYACTION = 3;
    public final static int SMSB_GDZYS_CKJKSACTION = 10;
    public final static int SMSB_GDZYS_GETJKPZHCTION = 11;
    
    //耕地占有税-申报征收-出具缴款书PROCESSOR
	public final static String GDZYS_SBZS_CJJKS_PROCESSOR =
         "com.ttsoft.bjtax.smsb.gdzys.sbzs.processor.GdzysCjjksProcessor";
	
	  //耕地占有税-申报征收-出具滞纳金缴款书PROCESSOR
	public final static String GDZYS_SBZS_CJZNJJKS_PROCESSOR =
         "com.ttsoft.bjtax.smsb.gdzys.sbzs.processor.GdzysCjznjjksProcessor";
	
	
    //耕地占有税-申报征收-撤销缴款书PROCESSOR
	public final static String GDZYS_SBZS_CXJKS_PROCESSOR =
         "com.ttsoft.bjtax.smsb.gdzys.sbzs.processor.GdzysCxjksProcessor";

   //耕地占有税-减免税管理-减免税查询PROCESSOR
	public final static String 	GDZYS_JMSGL_JMSCX_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor.GdzysJmscxProcessor";
   //耕地占有税-减免税管理-减免税证明打印PROCESSOR
	public final static String GDZYS_JMSGL_JMSZM_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor.GdzysJmszmPrintProcessor";
	//耕地占有税-减免税管理-撤销减免税证明PROCESSOR
	public final static String GDZYS_JMSGL_JMSCANCLE_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor.GdzysJmsCancleProcessor";
	//耕地占用税-业务查询-减免税证明查询PROCESSOR
	public final static String GDZYS_CX_JMSZMCX_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.cx.processor.GdzysJmszmCxProcessor";
	
	//耕地占有税-业务查询-缴款书查询PROCESSOR
	public final static String GDZYS_YWCX_JKSCX_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.cx.processor.GdzysJksCxProcessor";
	
	 //耕地占用税-税源信息录入 action
    
    public final static int GDZYS_SYXXLRACTION_INIT = 1; //初始化
    public final static int GDZYS_SYXXLRACTION_QUERY = 2; //查询
    public final static int GDZYS_SYXXLRACTION_CONFIRM = 3; //确认
    public final static int GDZYS_SYXXLRACTION_PRINT_SBB = 4; //打印
    public final static int GDZYS_SYXXLRACTION_PRINT_JMSBB = 10; //打印
    public final static int GDZYS_SYXXLRACTION_REMARK = 5; //备注
    public final static int GDZYS_SYXXLRACTION_SAVE = 6; //保存
    public final static int GDZYS_SYXXLRACTION_DJXX = 7; //获取登记信息
    public final static int GDZYS_SYXXLRACTION_SYSE = 8;//根据年度获取适用税额
    public final static int GDZYS_SYXXLRACTION_JMSYJ = 9;//减免税依据
    public final static int GDZYS_SYXXLRACTION_ZRR = 11;//自然人
    public final static int GDZYS_SYXXLRACTION_QXSH = 12;//区县审核
    public final static int GDZYS_SYXXLRACTION_SJSH = 13;//诗句市局审核
    public final static int GDZYS_SYXXLRACTION_UPDATE = 14;//诗句市局审核
    public final static int GDZYS_SYXXLRACTION_BGQUERY = 15; //查询
   //耕地占用税-税源信息录入 action
    public final static String GDZYS_SYXXLRPROCESSOR = "com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.processor.GdzysSydjxxlrProcessor"; //新建税源登记

    /**
     * 耕地占有税的缴款书
     */
    public final static String SMSB_SJLY_GDZYS = "38";

    
    
  //耕地占有税税种代码
	public final static String SMSB_GDZYS_SZDM = "73";
	//耕地占有税税种名称
	public final static String SMSB_GDZYS_SZMC = "耕地占用税";
	
	//耕地占有税税种税目代码
	public final static String SMSB_GDZYS_SZSMDM = "730010";
	//耕地占有税减免项目代码
	public final static String SMSB_GDZYS_JMXMDM = "9990";
	//耕地占有税减免性质代码
	public final static String SMSB_GDZYS_JMXZDM = "14121301";
	
	//耕地占有税税种税目名称
	public final static String SMSB_GDZYS_SZSMMC = "耕地占用税";
	
	//耕地占有滞纳金税税种税目代码
	public final static String SMSB_GDZYS_ZNJ_SZSMDM = "730091";
	//耕地占有滞纳金税税种税目名称
	public final static String SMSB_GDZYS_ZNJ_SZSMMC = "耕地占用税滞纳金";
	
	//耕地地占用税登记查询
	public static final int SMSB_GDZYS_DJQuery = 1;
	public static final int SMSB_GDZYS_DJQueryDetail = 2;
	public static final int SMSB_GDZYS_UPDATESH = 3;
	
	//耕地占用税变更 
	public static final int SMSB_GDZYS_SYBGQUERY = 1;
	public static final int SMSB_GDZYS_SYBGQUERYDETAIL = 2;


}
