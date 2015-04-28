package com.ttsoft.bjtax.shenbao.szsm;

public class SzsmActionConstant
{
    public SzsmActionConstant()
    {
    }

    /**
     * 查询税种税目收藏
     */
    public static final int QUERY_PREVIOUS_SZSM = 0;

    /**
     * 查询税种税目树信息
     */
    public static final int QUERY_SZSMTREE_INFO = 1;

    /**
     * 保存用户收藏
     */
    public static final int REFRESH_FAVORITE = 2;
    /**
     * 查询根税种信息
     */
    public static final int QUERY_SZSMTREE_ROOT = 4;
    /**
     * 查询支税种信息
     */
    public static final int QUERY_SZSMTREE_BRANCH= 5;
    /**
     * 查询所有税目信息
     */
    public static final int QUERY_SZSMTREE_LEAF= 6;
    
    /**
     * 查询委托代征、代扣、代售、监督代售单位认定情况信息
     */
    public static final int QUERY_WTDW_INFO = 7;
    
    /**
     * 查询税种税目树信息
     */
    public static final int QUERY_SZSMTREE_WTDZ = 8;
}
