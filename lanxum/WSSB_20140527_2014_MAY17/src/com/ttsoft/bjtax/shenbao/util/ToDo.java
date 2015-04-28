package com.ttsoft.bjtax.shenbao.util;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 为了解决ToDo病毒</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone 2003-8-22
 * @version 1.0
 */

public class ToDo
{
    public ToDo()
    {
    }
    /**
     * 打印ToDo信息
     * @param message todo信息
     */
    public static void print(String message)
    {
//        System.out.println(message);
        Exception ex = new Exception();
//        StackTraceElement[] stacktrace = ex.getStackTrace();
        System.out.println("********************ToDo()****************");
        System.out.println(message);
        System.out.println("*************************************************");

    }

    public static void print(Class source,String method,String message)
    {
        System.out.println("********************ToDo()****************");
        System.out.println("类名为：" + source.getName());
        System.out.println("方法名为：" + method +"()");
        System.out.println("ToDo信息为:" + message);
        System.out.println("*************************************************");
    }

    public static void main(String[] argc)
    {
       test();
    }
    //测试用
    private static void test()
    {
//        ToDo.print("税款类型还不确定");
        ToDo.print(ToDo.class,"test","税款类型还不确定");
    }

}