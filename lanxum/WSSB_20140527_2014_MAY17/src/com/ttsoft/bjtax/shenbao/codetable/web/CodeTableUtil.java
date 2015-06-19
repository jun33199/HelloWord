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
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���������</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
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

            // ˰��˰Ŀmap
            application.setAttribute(CodeTable.SZSM_MAP, codes.get(CodeTable.SZSM_MAP));

            // ˰��˰Ŀlist
            application.setAttribute(CodeTable.SZSM_LIST,
                                     codes.get(CodeTable.SZSM_LIST));

            // ���õ�˰ĿList
            application.setAttribute(CodeTable.SZSM_LIST_AVAILABLE,
                                     codes.get(CodeTable.SZSM_LIST_AVAILABLE));

            // ���õ�˰ĿMap
            application.setAttribute(CodeTable.SZSM_MAP_AVAILABLE,
                                     codes.get(CodeTable.SZSM_MAP_AVAILABLE));

            // ˰��˰Ŀ����˰list
            application.setAttribute(CodeTable.SZSMFJS_LIST,
                                     codes.get(CodeTable.SZSMFJS_LIST));

            application.setAttribute(CodeTable.SZSMFJS_WQ_LIST,
                                     codes.get(CodeTable.SZSMFJS_WQ_LIST));

            // ����List
            application.setAttribute(CodeTable.GJ_LIST, codes.get(CodeTable.GJ_LIST));

            // ����Map
            application.setAttribute(CodeTable.GJ_MAP, codes.get(CodeTable.GJ_MAP));

            // ֤������List
            application.setAttribute(CodeTable.ZJLX_LIST,
                                     codes.get(CodeTable.ZJLX_LIST));

            // ֤������map
            application.setAttribute(CodeTable.ZJLX_MAP, codes.get(CodeTable.ZJLX_MAP));

            // ְҵlist
            application.setAttribute(CodeTable.ZY_LIST, codes.get(CodeTable.ZY_LIST));

            // ְҵmap
            application.setAttribute(CodeTable.ZY_MAP, codes.get(CodeTable.ZY_MAP));

            // ����List
            application.setAttribute(CodeTable.YH_LIST, codes.get(CodeTable.YH_LIST));

            // ����Map
            application.setAttribute(CodeTable.YH_MAP, codes.get(CodeTable.YH_MAP));

            // ������ϵMap
            application.setAttribute(CodeTable.LSGX_MAP, codes.get(CodeTable.LSGX_MAP));

            // ������ϵList
            application.setAttribute(CodeTable.LSGX_LIST, codes.get(CodeTable.LSGX_LIST));

            // �Ǽ�ע������Map
            application.setAttribute(CodeTable.DJZCLX_MAP, codes.get(CodeTable.DJZCLX_MAP));

            // �Ǽ�ע������List
            application.setAttribute(CodeTable.DJZCLX_LIST,
                                     codes.get(CodeTable.DJZCLX_LIST));

            // ���ұ�׼��ҵMap
            application.setAttribute(CodeTable.GJBZHY_MAP, codes.get(CodeTable.GJBZHY_MAP));

            // ���ұ�׼��ҵList
            application.setAttribute(CodeTable.GJBZHY_LIST,
                                     codes.get(CodeTable.GJBZHY_LIST));

            // Ԥ���ĿMap
            application.setAttribute(CodeTable.YSKM_MAP, codes.get(CodeTable.YSKM_MAP));

            // Ԥ���ĿList
            application.setAttribute(CodeTable.YSKM_LIST, codes.get(CodeTable.YSKM_LIST));

            // Ԥ�㼶��Map
            application.setAttribute(CodeTable.YSJC_MAP, codes.get(CodeTable.YSJC_MAP));

            // Ԥ�㼶��List
            application.setAttribute(CodeTable.YSJC_LIST, codes.get(CodeTable.YSJC_LIST));

            // ˰�������֯�ṹMap
            application.setAttribute(CodeTable.SWJJZZJG_MAP,
                                     codes.get(CodeTable.SWJJZZJG_MAP));

            // ˰�������֯�ṹList
            application.setAttribute(CodeTable.SWJJZZJG_LIST,
                                     codes.get(CodeTable.SWJJZZJG_LIST));

            // ˰������Map
            application.setAttribute(CodeTable.SKLX_MAP, codes.get(CodeTable.SKLX_MAP));

            // ˰������List
            application.setAttribute(CodeTable.SKLX_LIST, codes.get(CodeTable.SKLX_LIST));

            // �������Map
            application.setAttribute(CodeTable.JMFL_MAP, codes.get(CodeTable.JMFL_MAP));

            // �������List
            application.setAttribute(CodeTable.JMFL_LIST, codes.get(CodeTable.JMFL_LIST));

            // ˰������Map
            application.setAttribute(CodeTable.SKFDQK_MAP, codes.get(CodeTable.SKFDQK_MAP));

            // ˰������List
            application.setAttribute(CodeTable.SKFDQK_LIST, codes.get(CodeTable.SKFDQK_LIST));

            // ��һ���Map
            application.setAttribute(CodeTable.WBHS_MAP, codes.get(CodeTable.WBHS_MAP));

            // ��һ���List
            application.setAttribute(CodeTable.WBHS_LIST, codes.get(CodeTable.WBHS_LIST));


            /*����˰����غ����е�����״̬***/
            setLWZT(application);

            //���ⱸ����������
            // �����������Map
           application.setAttribute(CodeTable.JMBA_NLMYJMXM_MAP, codes.get(CodeTable.JMBA_NLMYJMXM_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_NLMYJMXM_LIST, codes.get(CodeTable.JMBA_NLMYJMXM_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_BASX_MAP, codes.get(CodeTable.JMBA_BASX_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_BASX_LIST, codes.get(CodeTable.JMBA_BASX_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_DMQYLX_MAP, codes.get(CodeTable.JMBA_DMQYLX_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_DMQYLX_LIST, codes.get(CodeTable.JMBA_DMQYLX_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_FWYWFW_MAP, codes.get(CodeTable.JMBA_FWYWFW_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_FWYWFW_LIST, codes.get(CodeTable.JMBA_FWYWFW_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_GGJCSSXMLX_MAP, codes.get(CodeTable.JMBA_GGJCSSXMLX_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_GGJCSSXMLX_LIST, codes.get(CodeTable.JMBA_GGJCSSXMLX_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_GXJSLY_MAP, codes.get(CodeTable.JMBA_GXJSLY_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_GXJSLY_LIST, codes.get(CodeTable.JMBA_GXJSLY_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_JNJSXMLX_MAP, codes.get(CodeTable.JMBA_JNJSXMLX_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_JNJSXMLX_LIST, codes.get(CodeTable.JMBA_JNJSXMLX_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_WHSYDWLX_MAP, codes.get(CodeTable.JMBA_WHSYDWLX_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_WHSYDWLX_LIST, codes.get(CodeTable.JMBA_WHSYDWLX_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_YFFYLY_MAP, codes.get(CodeTable.JMBA_YFFYLY_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_YFFYLY_LIST, codes.get(CodeTable.JMBA_YFFYLY_LIST));

            // �����������Map
            application.setAttribute(CodeTable.JMBA_ZYSBLX_MAP, codes.get(CodeTable.JMBA_ZYSBLX_MAP));

            // �����������List
            application.setAttribute(CodeTable.JMBA_ZYSBLX_LIST, codes.get(CodeTable.JMBA_ZYSBLX_LIST));
            // �����������Map
            application.setAttribute(CodeTable.JMBA_JSZRLX_LIST, codes.get(CodeTable.JMBA_JSZRLX_LIST));

            // �����������List
            application.setAttribute(CodeTable.JMBA_JSZRLX_MAP, codes.get(CodeTable.JMBA_JSZRLX_MAP));


            // �����������Map
            application.setAttribute(CodeTable.JMBA_ZYZHLYZL_MAP, codes.get(CodeTable.JMBA_ZYZHLYZL_MAP));
            //  �����������List
            application.setAttribute(CodeTable.JMBA_ZYZHLYZL_LIST, codes.get(CodeTable.JMBA_ZYZHLYZL_LIST));

            
            // ���ⱸ�������嵥����List
            application.setAttribute(CodeTable.JMBA_ZLQD_LIST, codes.get(CodeTable.JMBA_ZLQD_LIST));

            // ���ⱸ�������嵥Map
            application.setAttribute(CodeTable.JMBA_ZLQD_MAP, codes.get(CodeTable.JMBA_ZLQD_MAP));

            // ���ⱸ�������嵥����Map
            application.setAttribute(CodeTable.JMBA_JMBAZLQD_MAP, codes.get(CodeTable.JMBA_JMBAZLQD_MAP));

            // ���ܼ��ż���������ĿList
            application.setAttribute(CodeTable.JMBA_JNJPJSGZXM_LIST, codes.get(CodeTable.JMBA_JNJPJSGZXM_LIST));

            // ���ܼ��ż���������ĿMap
            application.setAttribute(CodeTable.JMBA_JNJPJSGZXM_MAP, codes.get(CodeTable.JMBA_JNJPJSGZXM_MAP));

            // ��˰����ԭ��List ����˰��Ŀ  tujb 200404
            application.setAttribute(CodeTable.WSYY_BASX_LIST, codes.get(CodeTable.WSYY_BASX_LIST));
         
            // ������ĿList ����˰��Ŀ  tujb 200404
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
     * �������С�˰���������״̬
     */
    private static void setLWZT(ServletContext application){
        /*ȡ�����д���List*/
        List yhList = (List)application.getAttribute(CodeTable.YH_LIST);
        /*ȡ�����д��������״̬�����뵽һ��Map�У��ٷ��뵽ServletContext��*/
        Map yhLwztMap  = new HashMap();
        for (Iterator iter = yhList.iterator(); iter.hasNext(); ) {
            Yh item = (Yh)iter.next();
            yhLwztMap.put(item.getYhdm(),item.getLwzt());
        }
        application.setAttribute(CodeTable.YH_LWZT_MAP,yhLwztMap);


        /*ȡ��˰�����List*/
        List swjgList = (List)application.getAttribute(CodeTable.SWJJZZJG_LIST);
        /*ȡ��˰�������֯�������������״̬�����뵽һ��Map�У��ٷ��뵽ServletContext��*/
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