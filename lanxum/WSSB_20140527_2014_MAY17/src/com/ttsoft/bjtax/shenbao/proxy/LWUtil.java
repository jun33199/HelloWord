package com.ttsoft.bjtax.shenbao.proxy;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ������ع���</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author not attributable
 * @version 1.1
 */

public class LWUtil
{
    /**
     * �����걨Context����
     */
    private static final String WSSB_CONTEXT_NAME = "/shenbao";

    /**
     * ����״̬------------δ����
     */
    private static final String LWZT_WLW = "0";

    /**
     * ����״̬------------������
     */
    private static final String LWZT_YLW = "1";

    /**
     * ��������״̬Map  Key
     */
    private static final String YH_LWZT_MAP = "YH_LWZT_MAP";

    /**
     * ˰�������֯��������״̬Map  Key
     */
    private static final String SWJGZZJG_LWZT_MAP = "SWJGZZJG_LWZT_MAP";

    /**
     *
     * @param context       ServletContext
     * @param swjgzzjgdm    ˰�������֯��������
     * @param yhdm          ���д���
     * @return              �Ƿ��������������
     */
    public static boolean isLW(ServletContext context, String swjgzzjgdm,
                               String yhdm)
    {

        /*��������걨��ServletContext*/
        ServletContext wssbcontext = context.getContext(WSSB_CONTEXT_NAME);

//        /*�ж�˰������Ƿ�������*/
//        boolean swjgret = false;
//        Map swjgzzjgMap = (Map)wssbcontext.getAttribute(SWJGZZJG_LWZT_MAP); //
//        if(swjgzzjgdm != null)
//        {
//            String swjg = (String)swjgzzjgMap.get(swjgzzjgdm);
//            /*�����˰������������*/
//            if(swjg != null && swjg.equals(LWZT_YLW))
//            {
//                swjgret = true;
//            }
//        }
//
//        /*�ж������Ƿ�����*/
//        boolean yhret = false;
//        Map yhMap = (Map)wssbcontext.getAttribute(YH_LWZT_MAP);
//        if(yhdm != null)
//        {
//            String yh = (String)yhMap.get(yhdm);
//            /*������������*/
//            if(yh != null && yh.equals(LWZT_YLW))
//            {
//                yhret = true;
//            }
//        }
//        return(swjgret && yhret);
        return (isZsjgLW(context,swjgzzjgdm) && isYHLW(context,yhdm));
    }

    /**
     *
     * @param context       ServletContext
     * @param swjgzzjgdm    ˰�������֯��������
     * @return              �Ƿ��������������
     */
    public static boolean isZsjgLW(ServletContext context, String swjgzzjgdm)
    {

        /*��������걨��ServletContext*/
        ServletContext wssbcontext = context.getContext(WSSB_CONTEXT_NAME);
        /*�ж�˰������Ƿ�������*/
        boolean swjgret = false;
        Map swjgzzjgMap = (Map)wssbcontext.getAttribute(SWJGZZJG_LWZT_MAP); //
        if(swjgzzjgdm != null)
        {
            String swjg = (String)swjgzzjgMap.get(swjgzzjgdm);
            /*�����˰������������*/
            if(swjg != null && swjg.equals(LWZT_YLW))
            {
                swjgret = true;
            }
        }
        return swjgret;
    }

    /**
     *
     * @param context       ServletContext
     * @param yhdm          ���д���
     * @return              �Ƿ��������������
     */
    public static boolean isYHLW(ServletContext context, String yhdm)
    {
        /*��������걨��ServletContext*/
        ServletContext wssbcontext = context.getContext(WSSB_CONTEXT_NAME);
        /*�ж������Ƿ�����*/
        boolean yhret = false;
        Map yhMap = (Map)wssbcontext.getAttribute(YH_LWZT_MAP);
        if(yhdm != null&&!"".equals(yhdm.trim()))
        {
            String yh = (String)yhMap.get(yhdm);
            /*������������*/
            if(yh != null && yh.equals(LWZT_YLW))
            {
                yhret = true;
            }
        }
        else
        {
            yhret = true;
        }
        return yhret;
    }

}
