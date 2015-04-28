package com.ttsoft.bjtax.shenbao.proxy;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 联网相关工具</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author not attributable
 * @version 1.1
 */

public class LWUtil
{
    /**
     * 网上申报Context名称
     */
    private static final String WSSB_CONTEXT_NAME = "/shenbao";

    /**
     * 联网状态------------未联网
     */
    private static final String LWZT_WLW = "0";

    /**
     * 联网状态------------已联网
     */
    private static final String LWZT_YLW = "1";

    /**
     * 银行联网状态Map  Key
     */
    private static final String YH_LWZT_MAP = "YH_LWZT_MAP";

    /**
     * 税务机关组织机关联网状态Map  Key
     */
    private static final String SWJGZZJG_LWZT_MAP = "SWJGZZJG_LWZT_MAP";

    /**
     *
     * @param context       ServletContext
     * @param swjgzzjgdm    税务机关组织机构代码
     * @param yhdm          银行代码
     * @return              是否是属于联网情况
     */
    public static boolean isLW(ServletContext context, String swjgzzjgdm,
                               String yhdm)
    {

        /*获得网上申报的ServletContext*/
        ServletContext wssbcontext = context.getContext(WSSB_CONTEXT_NAME);

//        /*判断税务机关是否试运行*/
//        boolean swjgret = false;
//        Map swjgzzjgMap = (Map)wssbcontext.getAttribute(SWJGZZJG_LWZT_MAP); //
//        if(swjgzzjgdm != null)
//        {
//            String swjg = (String)swjgzzjgMap.get(swjgzzjgdm);
//            /*如果是税务机关联网情况*/
//            if(swjg != null && swjg.equals(LWZT_YLW))
//            {
//                swjgret = true;
//            }
//        }
//
//        /*判断银行是否联网*/
//        boolean yhret = false;
//        Map yhMap = (Map)wssbcontext.getAttribute(YH_LWZT_MAP);
//        if(yhdm != null)
//        {
//            String yh = (String)yhMap.get(yhdm);
//            /*如果是银行情况*/
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
     * @param swjgzzjgdm    税务机关组织机构代码
     * @return              是否是属于联网情况
     */
    public static boolean isZsjgLW(ServletContext context, String swjgzzjgdm)
    {

        /*获得网上申报的ServletContext*/
        ServletContext wssbcontext = context.getContext(WSSB_CONTEXT_NAME);
        /*判断税务机关是否试运行*/
        boolean swjgret = false;
        Map swjgzzjgMap = (Map)wssbcontext.getAttribute(SWJGZZJG_LWZT_MAP); //
        if(swjgzzjgdm != null)
        {
            String swjg = (String)swjgzzjgMap.get(swjgzzjgdm);
            /*如果是税务机关联网情况*/
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
     * @param yhdm          银行代码
     * @return              是否是属于联网情况
     */
    public static boolean isYHLW(ServletContext context, String yhdm)
    {
        /*获得网上申报的ServletContext*/
        ServletContext wssbcontext = context.getContext(WSSB_CONTEXT_NAME);
        /*判断银行是否联网*/
        boolean yhret = false;
        Map yhMap = (Map)wssbcontext.getAttribute(YH_LWZT_MAP);
        if(yhdm != null&&!"".equals(yhdm.trim()))
        {
            String yh = (String)yhMap.get(yhdm);
            /*如果是银行情况*/
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
