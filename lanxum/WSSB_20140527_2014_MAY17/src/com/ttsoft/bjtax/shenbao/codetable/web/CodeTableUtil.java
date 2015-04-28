package com.ttsoft.bjtax.shenbao.codetable.web;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import java.util.HashMap;
import java.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 代码表工具类</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class CodeTableUtil
{
    public CodeTableUtil()
    {
    }

    public static final String FLAG = "_need_flush";

    public static boolean loadCodeTable(ServletContext application)
    {
        try
        {
            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.CODEPROCESSOR_PROCESSOR);

            Map codes = (Map)ShenbaoProxy.getInstance().process(vo);

            // 税种税目map
            application.setAttribute(CodeTable.SZSM_MAP, codes.get(CodeTable.SZSM_MAP));

            // 税种税目list
            application.setAttribute(CodeTable.SZSM_LIST,
                                     codes.get(CodeTable.SZSM_LIST));

            // 可用的税目List
            application.setAttribute(CodeTable.SZSM_LIST_AVAILABLE,
                                     codes.get(CodeTable.SZSM_LIST_AVAILABLE));

            // 可用的税目Map
            application.setAttribute(CodeTable.SZSM_MAP_AVAILABLE,
                                     codes.get(CodeTable.SZSM_MAP_AVAILABLE));

            // 税种税目附加税list
            application.setAttribute(CodeTable.SZSMFJS_LIST,
                                     codes.get(CodeTable.SZSMFJS_LIST));

            application.setAttribute(CodeTable.SZSMFJS_WQ_LIST,
                                     codes.get(CodeTable.SZSMFJS_WQ_LIST));

            // 国籍List
            application.setAttribute(CodeTable.GJ_LIST, codes.get(CodeTable.GJ_LIST));

            // 国籍Map
            application.setAttribute(CodeTable.GJ_MAP, codes.get(CodeTable.GJ_MAP));

            // 证件类型List
            application.setAttribute(CodeTable.ZJLX_LIST,
                                     codes.get(CodeTable.ZJLX_LIST));

            // 证件类型map
            application.setAttribute(CodeTable.ZJLX_MAP, codes.get(CodeTable.ZJLX_MAP));

            // 职业list
            application.setAttribute(CodeTable.ZY_LIST, codes.get(CodeTable.ZY_LIST));

            // 职业map
            application.setAttribute(CodeTable.ZY_MAP, codes.get(CodeTable.ZY_MAP));

            // 银行List
            application.setAttribute(CodeTable.YH_LIST, codes.get(CodeTable.YH_LIST));

            // 银行Map
            application.setAttribute(CodeTable.YH_MAP, codes.get(CodeTable.YH_MAP));

            // 隶属关系Map
            application.setAttribute(CodeTable.LSGX_MAP, codes.get(CodeTable.LSGX_MAP));

            // 隶属关系List
            application.setAttribute(CodeTable.LSGX_LIST, codes.get(CodeTable.LSGX_LIST));

            // 登记注册类型Map
            application.setAttribute(CodeTable.DJZCLX_MAP, codes.get(CodeTable.DJZCLX_MAP));

            // 登记注册类型List
            application.setAttribute(CodeTable.DJZCLX_LIST,
                                     codes.get(CodeTable.DJZCLX_LIST));

            // 国家标准行业Map
            application.setAttribute(CodeTable.GJBZHY_MAP, codes.get(CodeTable.GJBZHY_MAP));

            // 国家标准行业List
            application.setAttribute(CodeTable.GJBZHY_LIST,
                                     codes.get(CodeTable.GJBZHY_LIST));

            // 预算科目Map
            application.setAttribute(CodeTable.YSKM_MAP, codes.get(CodeTable.YSKM_MAP));

            // 预算科目List
            application.setAttribute(CodeTable.YSKM_LIST, codes.get(CodeTable.YSKM_LIST));

            // 预算级次Map
            application.setAttribute(CodeTable.YSJC_MAP, codes.get(CodeTable.YSJC_MAP));

            // 预算级次List
            application.setAttribute(CodeTable.YSJC_LIST, codes.get(CodeTable.YSJC_LIST));

            // 税务机关组织结构Map
            application.setAttribute(CodeTable.SWJJZZJG_MAP,
                                     codes.get(CodeTable.SWJJZZJG_MAP));

            // 税务机关组织结构List
            application.setAttribute(CodeTable.SWJJZZJG_LIST,
                                     codes.get(CodeTable.SWJJZZJG_LIST));

            // 税款类型Map
            application.setAttribute(CodeTable.SKLX_MAP, codes.get(CodeTable.SKLX_MAP));

            // 税款类型List
            application.setAttribute(CodeTable.SKLX_LIST, codes.get(CodeTable.SKLX_LIST));

            // 减免分类Map
            application.setAttribute(CodeTable.JMFL_MAP, codes.get(CodeTable.JMFL_MAP));

            // 减免分类List
            application.setAttribute(CodeTable.JMFL_LIST, codes.get(CodeTable.JMFL_LIST));

            // 税款负担情况Map
            application.setAttribute(CodeTable.SKFDQK_MAP, codes.get(CodeTable.SKFDQK_MAP));

            // 税款负担情况List
            application.setAttribute(CodeTable.SKFDQK_LIST, codes.get(CodeTable.SKFDQK_LIST));

            // 外币换算Map
            application.setAttribute(CodeTable.WBHS_MAP, codes.get(CodeTable.WBHS_MAP));

            // 外币换算List
            application.setAttribute(CodeTable.WBHS_LIST, codes.get(CodeTable.WBHS_LIST));


            /*设置税务机关和银行的联网状态***/
            setLWZT(application);

            //减免备案代码数据
            // 备案事项代码Map
           application.setAttribute(CodeTable.JMBA_NLMYJMXM_MAP, codes.get(CodeTable.JMBA_NLMYJMXM_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_NLMYJMXM_LIST, codes.get(CodeTable.JMBA_NLMYJMXM_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_BASX_MAP, codes.get(CodeTable.JMBA_BASX_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_BASX_LIST, codes.get(CodeTable.JMBA_BASX_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_DMQYLX_MAP, codes.get(CodeTable.JMBA_DMQYLX_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_DMQYLX_LIST, codes.get(CodeTable.JMBA_DMQYLX_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_FWYWFW_MAP, codes.get(CodeTable.JMBA_FWYWFW_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_FWYWFW_LIST, codes.get(CodeTable.JMBA_FWYWFW_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_GGJCSSXMLX_MAP, codes.get(CodeTable.JMBA_GGJCSSXMLX_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_GGJCSSXMLX_LIST, codes.get(CodeTable.JMBA_GGJCSSXMLX_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_GXJSLY_MAP, codes.get(CodeTable.JMBA_GXJSLY_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_GXJSLY_LIST, codes.get(CodeTable.JMBA_GXJSLY_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_JNJSXMLX_MAP, codes.get(CodeTable.JMBA_JNJSXMLX_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_JNJSXMLX_LIST, codes.get(CodeTable.JMBA_JNJSXMLX_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_WHSYDWLX_MAP, codes.get(CodeTable.JMBA_WHSYDWLX_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_WHSYDWLX_LIST, codes.get(CodeTable.JMBA_WHSYDWLX_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_YFFYLY_MAP, codes.get(CodeTable.JMBA_YFFYLY_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_YFFYLY_LIST, codes.get(CodeTable.JMBA_YFFYLY_LIST));

            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_ZYSBLX_MAP, codes.get(CodeTable.JMBA_ZYSBLX_MAP));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_ZYSBLX_LIST, codes.get(CodeTable.JMBA_ZYSBLX_LIST));
            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_JSZRLX_LIST, codes.get(CodeTable.JMBA_JSZRLX_LIST));

            // 备案事项代码List
            application.setAttribute(CodeTable.JMBA_JSZRLX_MAP, codes.get(CodeTable.JMBA_JSZRLX_MAP));


            // 备案事项代码Map
            application.setAttribute(CodeTable.JMBA_ZYZHLYZL_MAP, codes.get(CodeTable.JMBA_ZYZHLYZL_MAP));
            //  备案事项代码List
            application.setAttribute(CodeTable.JMBA_ZYZHLYZL_LIST, codes.get(CodeTable.JMBA_ZYZHLYZL_LIST));

            
            // 减免备案资料清单代码List
            application.setAttribute(CodeTable.JMBA_ZLQD_LIST, codes.get(CodeTable.JMBA_ZLQD_LIST));

            // 减免备案资料清单Map
            application.setAttribute(CodeTable.JMBA_ZLQD_MAP, codes.get(CodeTable.JMBA_ZLQD_MAP));

            // 减免备案资料清单代码Map
            application.setAttribute(CodeTable.JMBA_JMBAZLQD_MAP, codes.get(CodeTable.JMBA_JMBAZLQD_MAP));

            // 节能减排技术改造项目List
            application.setAttribute(CodeTable.JMBA_JNJPJSGZXM_LIST, codes.get(CodeTable.JMBA_JNJPJSGZXM_LIST));

            // 节能减排技术改造项目Map
            application.setAttribute(CodeTable.JMBA_JNJPJSGZXM_MAP, codes.get(CodeTable.JMBA_JNJPJSGZXM_MAP));

            // 无税减免原因List 减免税项目  tujb 200404
            application.setAttribute(CodeTable.WSYY_BASX_LIST, codes.get(CodeTable.WSYY_BASX_LIST));
         
            // 减免项目List 减免税项目  tujb 200404
            application.setAttribute(CodeTable.JMXM_BASX_LIST, codes.get(CodeTable.JMXM_BASX_LIST));
            
            application.setAttribute(FLAG, "random");

            
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 处理银行、税务机关联网状态
     */
    private static void setLWZT(ServletContext application){
        /*取出银行代码List*/
        List yhList = (List)application.getAttribute(CodeTable.YH_LIST);
        /*取出银行代码和联网状态，放入到一个Map中，再放入到ServletContext中*/
        Map yhLwztMap  = new HashMap();
        for (Iterator iter = yhList.iterator(); iter.hasNext(); ) {
            Yh item = (Yh)iter.next();
            yhLwztMap.put(item.getYhdm(),item.getLwzt());
        }
        application.setAttribute(CodeTable.YH_LWZT_MAP,yhLwztMap);


        /*取出税务机关List*/
        List swjgList = (List)application.getAttribute(CodeTable.SWJJZZJG_LIST);
        /*取出税务机关组织机构代码和联网状态，放入到一个Map中，再放入到ServletContext中*/
        Map swjgLwztMap  = new HashMap();
        for (Iterator iter = swjgList.iterator(); iter.hasNext(); ) {
            Swjgzzjg item = (Swjgzzjg)iter.next();
            swjgLwztMap.put(item.getSwjgzzjgdm(),item.getLwzt());
        }
        application.setAttribute(CodeTable.SWJGZZJG_LWZT_MAP,swjgLwztMap);
        
        

    }

    private static void init(HttpServletRequest request)
    {
        if (request.getSession().getServletContext().getAttribute(FLAG) != null)
        {
            return;
        }
        else
        {
            if (loadCodeTable(request.getSession().getServletContext()))
            {
                Debug.out("Internet declaration code tables' data reloaded!");
            }
            else
            {
                Debug.out("Reloading code table of internet declaration failed!");
            }
        }
    }

    public static List getCodeTableList(HttpServletRequest request, String tableKey)
    {
        init(request);
        return (List)request.getSession().getServletContext().getAttribute(tableKey);
    }

    public static Map getCodeTableMap(HttpServletRequest request, String tableKey)
    {
        init(request);
        return (Map)request.getSession().getServletContext().getAttribute(tableKey);
    }
}