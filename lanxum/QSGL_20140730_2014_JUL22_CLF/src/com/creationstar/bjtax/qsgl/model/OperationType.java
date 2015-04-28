package com.creationstar.bjtax.qsgl.model;

import java.io.Serializable;

/**
 *
 * <p>Title: 前台类型代码类</p>
 *
 * <p>Description: 用于前台页面与Action类之间的操作定位之用，所有的操作都要在这个
 * 常量类中做标识，不能直接写字符串调用方法；
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 *
 * @author 契税开发组——赵博
 * @version 1.0
 */
public class OperationType implements Serializable {
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
     * 获取一条数据（利用主键）
     */
    public static final int GET = 6;

    /**
     * 完税证变号打印
     */
    public static final int CHANGE = 7;

    /**
     * 改变打印状态
     */
    public static final int UPDATESTATE = 8;

}
