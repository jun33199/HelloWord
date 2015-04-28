package com.ttsoft.bjtax.shenbao.zrrsb;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description:自然人个税申报业务Action常量</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-17
 */
public class ZrrsbActionConstant
{
    /**
     * 计算机代码
     */
    public final static String JSJDM = "jsjdm";     //查询、删除用

    /**
     * 维护日期
     */
    public final static String WHRQ = "whrq";   //查询缴款书用 注意日期格式，必须为:(YYYY-MM-DD)

    /**
     * 申报编号
     */
    public final static String SBBH = "sbbh";      //删除用

    /**
     * 申报编号
     */
    public final static String JKPZH = "jkpzh";      //删除用

    /**
      * 打印标识：一票一税，一票多税
      */
     public final static String PRINTTAG = "printTag";

     /**
      * 税款类型
      */
     public final static String SKLX = "sklx";

     /**
      * 申报日期
      */
     public final static String SBRQ = "sbrq";

     /**
      * 金额合计
      */
     public final static String JEHJ = "jehj";

     /**
      * 申报数据
      */
     public final static String SBSJ = "sbsj";

     /**
      * 币种代码表查询
      */
     public static final int INT_ACTION_QUERYBZ  = 10;


}
