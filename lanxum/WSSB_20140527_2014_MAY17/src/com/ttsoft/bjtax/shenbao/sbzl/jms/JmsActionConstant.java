package com.ttsoft.bjtax.shenbao.sbzl.jms;

/**
 *
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 减免税申报Action常量类</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Wu youzhi
 * @version 1.0
 */
public class JmsActionConstant
{

   /**
    * action执行查询操作
    */
   public static final int INT_ACTION_QUERY = 1;

   /**
    * action执行保存操作
    */
   public static final int INT_ACTION_SAVE = 2;

   /**
    * action执行返回操作
    */
   public static final int INT_ACTION_RETURN = 3;

   /**
    * action执行显示操作
    */
   public static final int INT_ACTION_SHOW = 4;

   /**
    * action执行显示操作
    */
   public static final int INT_ACTION_DELETE = 5;

   /**
    * 减免类型代码－政策性减免
    */
   public static final String STRING_ZCXJM = "9990";

   /**
    * 页面跳转－显示
    */
   public static final String STRING_ACTION_SHOW = "Show";

   /**
    * 页面跳转－操作成功
    */
   public static final String STRING_ACTION_SUCCESS = "Success";

   /**
    * 页面跳转－返回
    */
   public static final String STRING_ACTION_RETURN = "Return";
}
