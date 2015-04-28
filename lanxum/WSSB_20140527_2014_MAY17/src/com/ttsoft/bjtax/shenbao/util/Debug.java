package com.ttsoft.bjtax.shenbao.util;

import java.text.*;
import java.util.*;

/**
 * <p>Title: �������࣬������ɺ�ɾ������</p>
 * <p>Description: �ṩ���뺯��</p>
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
     * ���õ��Ա�־
     */
    public static void setDebug(boolean aDebug)
    {
        debug = aDebug;
    }

    /**
     * ��ӡ�����ַ���
     * @param outStr �����ַ���
     */
    public static void out(String outStr)
    {
        if (!debug)
            return;
        System.out.println("\n" + getTimeStamp() + " " + outStr);
    }

    /**
     * ��ӡ����Ķ���
     * @param obj Ҫ��ӡ�Ķ���
     */
    public static void out(Object obj)
    {
        if (!debug)
            return;
        System.out.println("\n" + getTimeStamp()  + " " + obj);
    }

    /**
     * ��ӡ�쳣��ջ
     * @param obj Ҫ��ӡ���쳣
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
