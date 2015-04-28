package com.ttsoft.bjtax.shenbao.constant;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: Action常量</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */

public class ActionConstant
{
    public static final int INT_ACTION_QUERY  = 1;
    public static final int INT_ACTION_SAVE   = 2;
    public static final int INT_ACTION_DELETE = 3;
    public static final int INT_ACTION_EXIT   = 4;
    public static final int INT_ACTION_PRINT  = 5;
    public static final int INT_ACTION_GETPROPERTY  = 6;
    public static final int INT_ACTION_ZWBS_YHKK  = 7;//调整账务标示为银行扣款成功
    public static final int INT_ACTION_YPDS_GENETATE  = 8;//生成税票号码并返回一票多税对象
    public static final int INT_ACTION_DELETEALL = 9;
    public static final int INT_ACTION_QUERYYSB = 10;
    //2009.4.3wcl修该
    public static final int INT_ACTION_FKNRSAVE   = 11;
    public static final int INT_ACTION_YDSJ   = 12;
    public static final int INT_ACTION_FKNRQUERY   = 13;
    public static final int INT_ACTION_SAVEORUPDATEFKNR   = 14;
    // 2009-7-9 tum 增加
    public static final int INT_ACTION_QUERY_MXXX   = 15; // 查询明细信息
    public static final int INT_ACTION_VIEWMX   = 16; // 查询明细信息
    public static final int INT_ACTION_CREATE_JKS   = 17; //国税代征生成缴款书
    /**
     * Description：减免时查看查看是否是月报还是季报
     */
    public static final int INT_ACTION_JMZQ   = 1001; // 查询明细信息
    
    /**
     * Description：处理小微企业减免信息
     */
    public static final int INT_ACTION_JM_DEAL   = 1002; // 查询明细信息
    
}
