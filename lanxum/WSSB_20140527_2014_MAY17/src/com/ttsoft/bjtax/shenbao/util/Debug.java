package com.ttsoft.bjtax.shenbao.util;

import java.text.*;
import java.util.*;

/**
 * <p>Title: 调试用类，调试完成后删除该类</p>
 * <p>Description: 提供输入函数</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: VicSoft</p>
 * @author Xie Chuanyue
 * @version 1.0
 */

public class Debug
{

    private static boolean debug = true;

    private static boolean error = true;

    /**
     * 设置调试标志
     */
    public static void setDebug(boolean aDebug)
    {
        debug = aDebug;
    }

    /**
     * 打印输入字符串
     * @param outStr 输入字符串
     */
    public static void out(String outStr)
    {
        if (!debug)
            return;
        System.out.println("\n" + getTimeStamp() + " " + outStr);
    }

    /**
     * 打印传入的对象
     * @param obj 要打印的对象
     */
    public static void out(Object obj)
    {
        if (!debug)
            return;
        System.out.println("\n" + getTimeStamp()  + " " + obj);
    }

    /**
     * 打印异常堆栈
     * @param obj 要打印的异常
     */
    public static void printException(Exception  ex)
    {
        if (!error)
            return;
        ex.printStackTrace();
    }

    private static String getTimeStamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

}
