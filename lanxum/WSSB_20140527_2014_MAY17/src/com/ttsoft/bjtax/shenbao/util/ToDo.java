package com.ttsoft.bjtax.shenbao.util;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: Ϊ�˽��ToDo����</p>
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
     * ��ӡToDo��Ϣ
     * @param message todo��Ϣ
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
        System.out.println("����Ϊ��" + source.getName());
        System.out.println("������Ϊ��" + method +"()");
        System.out.println("ToDo��ϢΪ:" + message);
        System.out.println("*************************************************");
    }

    public static void main(String[] argc)
    {
       test();
    }
    //������
    private static void test()
    {
//        ToDo.print("˰�����ͻ���ȷ��");
        ToDo.print(ToDo.class,"test","˰�����ͻ���ȷ��");
    }

}