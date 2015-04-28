package com.ttsoft.bjtax.shenbao.sbzl.jms;

/**
 *
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 减免税申报Session常量类</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Wu youzhi
 * @version 1.0
 */
public class SessionKey
{
    /**
     * 减免税申报数据
     */
    public static final String SESSION_JMSB_LIST = "SESSION_JMSB_LIST";

    /**
     * 税种数据
     */
    public static final String SESSION_JMSZ_LIST = "SESSION_JMSZ_LIST";

    /**
     * 税目数据
     */
    public static final String SESSION_JMSM_LIST = "SESSION_JMSM_LIST";

    /**
     * 本期已申报数据
     */
    public static final String SESSION_BQYSB_LIST = "SESSION_BQYSB_LIST";

    /**
     * 附加税List
     */
    public static final String SESSION_FJS_LIST = "SESSION_FJS_LIST";
    /**
     * 减免分类List
     */
    public static final String SESSION_JMFL_LIST = "SESSION_JMFL_LIST";
    
    /**
     * 减免项目List 减免税项目  tujb 200404
     */
    public static final String SESSION_JMXM_LIST = "SESSION_JMXM_LIST";
    
    /**
     * 减免特定税款所属日期List 减免税项目  tujb 200404
     */
    public static final String SESSION_SKSSRQ_LIST = "SESSION_SKSSRQ_LIST";
    
    /**
     * 减免特定税款年月List 减免税项目  tujb 200404
     */
    public static final String SESSION_SKSNY_LIST = "SESSION_SKNY_LIST";
}