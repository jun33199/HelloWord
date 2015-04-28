package com.ttsoft.bjtax.shenbao.sbzl.grsds;

/**
 * 个人所得税action constant
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class GrsdsActionConstant
{
    public GrsdsActionConstant()
    {
    }

    /**
     * 增加
     */
    public final static int ACTION_INSERT = 1;

    /**
     * 先删后加
     */
    public final static int ACTION_REFRESH = 2;

    /**
     * 删除
     */
    public final static int ACTION_DELETE = 3;

    /**
     * 查询
     */
    public final static int ACTION_QUERY = 4;
}