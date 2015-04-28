package com.ttsoft.bjtax.shenbao.wsksb;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 四一安信股份有限公司，版权所有.</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  guzhixian
 * @version 1.1
 */

public class SessionKey
{
    public SessionKey()
    {
    }

    /**
     * 计算机代码
     */
    public static final String JSJDM = "JSJDM";

    /**
     * 征期属性名称
     */
    public static final String WSKSBZQRLPROPERTY = "WSSB_WYNSKSB_ZQRL_MONTH_";

    /**
     * 提示信息属性名称----定期定额
     */
    public static final String MESSAGE_NAME_DQDE = "message.web.wsksb.dqde";

    /**
     * 提示信息属性名称----征期已过

    public static final String MESSAGE_NAME_ZQYG = "message.web.wsksb.zqyg";

    public static final String MESSAGE_NAME_CANSAVE = "message.web.wsksb.cansave";
    public static final String MESSAGE_NAME_CANDEL = "message.web.wsksb.candel";
    public static final String MESSAGE_NAME_NOTWSSB = "message.web.wsksb.notwssb";
    public static final String MESSAGE_NAME_YSSBTS = "message.web.wsksb.yssbts";
*/
    /**
     * 申报所属日期
     */
    public static final String SKSSRQ = "SKSSRQ";

    /**
     * 登记基本数据
     */
    public static final String JBSJ = "JBSJ";

    /**
     * 登记数据Map
     */
    public static final String MAP_DJSJ = "MAP_DJSJ";

    /**
     * 无应纳税款缴款书
     */
    public static final String WSKJKS = "WSKJKS";

    /**
     * 无应纳税款税种税目
     */
    public static final String WSKSZSM = "WSKSZSM";

    /**
     * 无应纳税款征期日历
     */
    public static final String ZQRL = "ZRQL";


    /**
     * 维护日期
     */
    public final static String WHRQ = "whrq";

    /**
     * 税款所属开始日期
     */
    public final static String SKSSKSRQ = "skssksrq";

    /**
     * 税款所属结束日期
     */
    public final static String SKSSJSRQ = "skssjsrq";


    /**
     * action执行查询操作
     */
    public static final int INT_ACTION_QUERYWSKJL = 1;

    
    /**
     * action 查询告知事项说明
     */
    public static final int INT_ACTION_QUERYGZSX = 6;
    
    /**
     * action执行查询操作
     */
    public static final int INT_ACTION_QUERYJKSJL = 2;

    /**
     * action执行保存操作
     */
    public static final int INT_ACTION_SAVE = 3;

    /**
     * action执行返回操作
     */
    public static final int INT_ACTION_RETURN = 4;

    /**
     * action执行删除操作
     */
    public static final int INT_ACTION_DELETE = 5;

}