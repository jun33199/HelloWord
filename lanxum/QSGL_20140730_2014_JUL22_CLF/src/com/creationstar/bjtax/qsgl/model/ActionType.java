package com.creationstar.bjtax.qsgl.model;

import java.io.Serializable;

/**
 *
 * <p>Title: 前台类型代码类</p>
 *
 * <p>Description: 用于前台Action通过Proxy代理定位后台processor的方法之用，
 * 所有的操作都要在这个常量类中做标识，不能直接写字符串调用方法；
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 *
 * @author 契税开发组——赵博
 * @version 1.0
 */
public class ActionType implements Serializable {
    /**
     * 添加
     */
    public static final int INSERT = 0;

    /**
     * 删除
     */
    public static final int DELETE = 1;

    /**
     * 更改
     */
    public static final int MODIFY = 2;

    /**
     * 查询
     */
    public static final int QUERY = 3;

    /**
     * 获取所有代码
     */
    public static final int LOADCODES = 4;

    /**
     * 获取所有代码
     */
    public static final int LOADCODESMAP = 5;

    /**
     * 获取单条数据记录
     */
    public static final int GET = 6;

    /**
     * 审核同意
     */
    public static final int CONFIRM = 7;

    /**
     * 审核不同意
     */
    public static final int REJECT = 8;

    /**
     * 由税务分局所辖的大企业银行转帐的方式缴税而发生的生成缴款书
     */
    public static final int CREATE_JKS = 9;

    /**
     * 通过汇总完税证生成的缴款书
     */
    public static final int GENERAL_JKS = 10;

    /**
     * 作废申报申请表
     */
    public static final int CANCEL = 11;

    /**
     * 打印申报申请表
     */
    public static final int PRINT_SBB = 12;

    /**
     * 打印审核通知书
     */
    public static final int PRINT_HDTZS = 13;

    /**
     * 撤销上次操作，恢复原先的状态
     */
    public static final int ROLLBACK = 14;

    /**
     * 复核
     */
    public static final int CHECK = 15;

    /**
     * 生成完税证
     */
    public static final int CREATE_WSZ = 16;

    /**
     * 撤销完税证
     */
    public static final int CX_WSZ = 17;

    /**
     * 汇总完税证、生成缴款书
     */
    public static final int HZ_WSZ = 18;

    /**
     * 撤销汇总完税证、撤销汇总生成的缴款书
     */
    public static final int CX_HZWSZ = 19;

    /**
     * 更新缴款书的状态
     */
    public static final int CHANGE_JKS_STATUS = 20;

    /**
     * 更新完税证的打印状态
     */
    public static final int CHANGE_WSZ_STATUS = 21;

    /**
     * 撤销直接生成的缴款书
     */
    public static final int CX_JKS = 22;

    /**
     * 获取指定币种的汇率
     */
    public static final int GET_HL = 23;

    /**
     * 获取指定缴款书所包含的完税证的信息
     */
    public static final int JKS_QUERY_WSZ = 24;

    /**
     * 获取指定完税证被汇总到哪张缴款书中的的信息
     */
    public static final int WSZ_QUERY_JKS = 25;

    /**
     * 获取拆迁、共有住房、房屋等信息的使用情况
     */
    public static final int QUERY_USAGE = 26;
    /**
     * 更新
     */
    public static final int UPDATE = 27;

    public static final int QUERY_SBZT = 28;

    /**
     * 查询批量倒入主表
     */
    public static final int QUERY_DRZB = 29;

    /**
     * 批量受理
     */
    public static final int RECEIVE_DRZB = 30;

    /**
     * 导入xml文件
     */
    public static final int UPLOAD_FILE = 31;

    /**
     * 删除导入批次全部数据
     */
    public static final int DELETE_ALLDR = 32;

    /**
     * 打印完税证
     */
    public static final int PRINT_WSZ = 33;

    /**
     * 变号打印
     */
    public static final int CHANGE_WSZH_PRINT = 34;

    /**
     * 补录--查询补录的缴款书(申报缴款书)
     */
    public static final int BL_QUERY_JKS = 35;

    /**
     * 补录--根据申报表号查询补录的完税证号
     */
    public static final int BL_QUERY_WSZH = 36;

    /**
     * 核对金额
     */
    public static final int BL_CHECK_SBJKS = 37;

    /**
     * 核对金额
     */
    public static final int BL_CHECK_HZJKS = 38;

    /**
     * 和补录的申报缴款书建立关联
     */
    public static final int BL_CREATECONNECT_SBJKS = 39;

    /**
     * 和补录的汇总缴款书建立关联
     */
    public static final int BL_CREATECONNECT_HZJKS = 40;

    /**
     * 撤销关联
     */
    public static final int BL_REMOVECONNECT = 41;

    /**
     * 更新剩余额
     */
    public static final int UPDATE_SYE = 42;

    /**
     *
     */
    public static final int PRINT_CQQKB = 43;

    /**
     * 更新核定通知书
     */
    public static final int UPDATE_HDTZS = 44;

    /**
     * 查询核定通知书
     */
    public static final int Query_HDTZS = 45;

    /**
     * 根据防伪号码查询核定通知书
     */
    public static final int Query_HDTZSBYFWHM = 46;

    /**
     *
     */
    public static final int QUERYERR = 47;

    /**
     *
     */
    public static final int SAVEERR = 48;

    /**
     * 根据核定通知书号码查询核定通知书
     */
    public static final int Query_HDTZSBYHDTZSHM = 49;


    /**
     * 根据核定通知书号码更新核定通知书号码
     */
    public static final int UPDATE_HDTZSHM_BY_HDTZSHM = 50;

    /**
     * 保存程序性减免审批结果
     */
    public static final int SAVE_CXXJMSP = 51;

    /**
     * 存量房_根据证件号码查询上次缴纳税款信息
     */
    public static final int Query_SBXXHISBYZJHM = 52;
 
    /**
     * 存量房_根据申报编号查询上次缴纳税款信息
     */
    public static final int Query_SBXXHISBYSBBH = 53;
    
    /**
     * 存量房_税种税目初始化
     */
    public static final int INITSZSMLIST = 54;
    
    /**
     * 存量房_卖方申报信息维护
     */
    public static final int CLF_MFSBXXWH = 55;
    
    /**
     * 存量房_根据合同编号查询卖方申报数据
     */
    public static final int Query_SBXXBYHTBH = 56;
    
    /**
     * 存量房_卖方申报数据维护数据同步
     */
    public static final int DataSynchronism = 57;  

    /**
     * 打印电子缴款专用缴款书
     */
    public static final int PRINT_JKS = 58; 
    /**
     * 查询完税证
     */
    public static final int QUERY_WSZ = 59; 
    
}
