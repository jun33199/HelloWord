package com.ttsoft.bjtax.smsb.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.zhsb.ZhsbMapConstant;

public class TranslateHelper
{
    public TranslateHelper()
    {
    }

    /**
     * ��LIST--LIST��ʽ���걨�ɿ����ݱ��SBData��hashmap
     * @param obj List
     * @return HashMap
     */
    public static HashMap translateLL2Map(List obj)
    {
        //��ѯ

        HashMap sbmaps = new HashMap();
        List dilist = null;
        SBData sb = null;
        for (int i = 0; i < obj.size(); i++)
        {
            //��ʽ��ÿ����¼
            dilist = (List) obj.get(i);
            for (int i1 = 0; i1 < obj.size(); i1++)
            {
                DeclareInfor di = (DeclareInfor) dilist.get(i);
                sb = (SBData) sbmaps.get(di.getSbjkzb().getSbbh());
                if (sb == null)
                {
                    sb = new SBData();
                    sbmaps.put(di.getSbjkzb().getSbbh(), sb);
                }
                sb.addDeclareInfor(di);
            }
        }
        return sbmaps;
    }

    /**
     * ��map--map--map��ʽ���걨�ɿ����ݱ��SBData��hashmap
     * @param obj List
     * @return HashMap
     */
    public static HashMap translateMMM2Map(HashMap datamap)
    {
        //��ѯ
        if ( (datamap == null) || (datamap.size() == 0))
        {
            return new HashMap();
        }
        HashMap sbmaps = new HashMap();
        SBData sb = null;
        HashMap sbmap = null;
        HashMap declaremap = null;
        Iterator sbite = datamap.keySet().iterator();
        Iterator jksite = null;
        String sbbh;
        List dilist = null;
        DeclareInfor di = null;
        String jkpzh15;
        int i;

        while (sbite.hasNext())
        {
            sbbh = (String) sbite.next();
            sb = (SBData) sbmaps.get(sbbh);
            if (sb == null)
            {
                sb = new SBData();
                sbmaps.put(sbbh, sb);
            }
            sbmap = (HashMap) datamap.get(sbbh);

            Debug.out("0==sbbh=" + sbbh);

            jksite = sbmap.keySet().iterator();
            while (jksite.hasNext())
            {
                jkpzh15 = (String) jksite.next();

                if (jkpzh15.equals(ZhsbMapConstant.PRINTTAG) ||
                    jkpzh15.equals(ZhsbMapConstant.SKLX) ||
                    jkpzh15.equals(ZhsbMapConstant.SBRQ))
                {
                    continue;
                }
                Debug.out("1===jkpzh15=" + jkpzh15);

                declaremap = (HashMap) sbmap.get(jkpzh15);
                if (declaremap.get(ZhsbMapConstant.SBSJ) instanceof java.util.List)
                {
                    dilist = (List) declaremap.get(ZhsbMapConstant.SBSJ);
                    for (i = 0; i < dilist.size(); i++)
                    {
                        di = (DeclareInfor) dilist.get(i);
                        sb.addDeclareInfor(di);
                        Debug.out("di.getSbjkzb().getJkpzh()=" + di.getSbjkzb().getJkpzh());

                    }
                }
                else
                {
                    di = (DeclareInfor) declaremap.get(ZhsbMapConstant.SBSJ);
                    sb.addDeclareInfor(di);

                    Debug.out("di.getSbjkzb().getJkpzh()=" + di.getSbjkzb().getJkpzh());

                }
            }
        }

        return sbmaps;
    }

    /**
     * ���걨�ɿ����Ϊ�������С����������С������걨�Ž�����֯
     * ע�⣡ ������ʹ���걨�Ļ�������
     *
     * @param request HttpServletRequest
     */
    public static List splitMAPInto2(List sblist, HttpServletRequest request)
    {
        if ( (sblist == null) || (sblist.size() == 0))
        {
            ArrayList ar = new ArrayList();
            ar.add(new ArrayList());
            ar.add(new ArrayList());
            return ar;
        }
        //��SBData��listת��ҳ����ʾ��list
        HashMap itemmap = null;
        ArrayList lwlist = new ArrayList();
        ArrayList nlwlist = new ArrayList();
        HashMap jkpzmap = null;
        ArrayList jkpzlist = null;

        HashMap declaremaps = null;
        DeclareInfor di = null;
        SBData sb = null;
        String yhdm = null;
        String ssdwdm = null;
        for (int i = 0; i < sblist.size(); i++)
        {
            sb = (SBData) sblist.get(i);

            declaremaps = sb.getDeclareInforMap();
            Iterator declareite = declaremaps.values().iterator();
            di = (DeclareInfor) declareite.next();
            yhdm = di.getSbjkzb().getYhdm();
            ssdwdm = di.getSbjkzb().getSwjgzzjgdm();
            if (LWUtil.isLW(request.getSession().getServletContext(), ssdwdm, yhdm))
            {
                Debug.out("������ssdwdm=" + ssdwdm + ";yhdm=" + yhdm + ";jkpzh=" + di.getSbjkzb().getJkpzh());
                itemmap = new HashMap();
                itemmap.put("sbbh", sb.getSbbh());
                itemmap.put("sbrq",
                            (new SimpleDateFormat("yyyy-MM-dd")).format(sb.getSbrq()));
                itemmap.put("sjje", String.valueOf(sb.getHjsjje()));
                itemmap.put("jsjdm", sb.getJsjdm());
                lwlist.add(itemmap);
            }
            else
            {
                Debug.out("��������ssdwdm=" + ssdwdm + ";yhdm=" + yhdm + ";jkpzh=" + di.getSbjkzb().getJkpzh());
                itemmap = new HashMap();
                itemmap.put("sbbh", sb.getSbbh());
                itemmap.put("sbrq",
                            (new SimpleDateFormat("yyyy-MM-dd")).format(sb.getSbrq()));
                nlwlist.add(itemmap);

                jkpzlist = new ArrayList();
                jkpzmap = new HashMap();
                jkpzmap.put("jkpzh", di.getSbjkzb().getJkpzh());
                jkpzmap.put("szmc", di.getSbjkzb().getSzmc());
                jkpzmap.put("sjje", di.getSbjkzb().getSjje().toString());
                jkpzmap.put("jsjdm", di.getSbjkzb().getJsjdm());
                jkpzlist.add(jkpzmap);

                while (declareite.hasNext())
                {
                    jkpzmap = new HashMap();
                    di = (DeclareInfor) declareite.next();

                    jkpzmap.put("jkpzh", di.getSbjkzb().getJkpzh());
                    jkpzmap.put("szmc", di.getSbjkzb().getSzmc());
                    jkpzmap.put("sjje", di.getSbjkzb().getSjje().toString());
                    jkpzmap.put("jsjdm", di.getSbjkzb().getJsjdm());
                    jkpzlist.add(jkpzmap);
                }
                itemmap.put("JKPZLIST", jkpzlist);
            }
        }
        ArrayList ar = new ArrayList();
        ar.add(lwlist);
        ar.add(nlwlist);
        return ar;
        //end add by qianchao 2005.10.27

    }

    public static List splitMAPInto2(Map sbmap, HttpServletRequest request)
    {
        if ( (sbmap == null) || (sbmap.size() == 0))
        {
            ArrayList ar = new ArrayList();
            ar.add(new ArrayList());
            ar.add(new ArrayList());
            return ar;
        }
        Iterator sbite = sbmap.values().iterator();
        ArrayList sblist = new ArrayList();

        while (sbite.hasNext())
        {
            sblist.add(sbite.next());
        }
        return splitMAPInto2(sblist, request);
    }

    /**
     * ���걨�ɿ����Ϊ�������С�����������
     * ע�⣡ ������ʹ���걨�Ļ�������
     *
     * @param request HttpServletRequest
     */
    public static List splitMAP(List sblist, HttpServletRequest request)
    {
        if ( (sblist == null) || (sblist.size() == 0))
        {
            ArrayList ar = new ArrayList();
            ar.add(new ArrayList());
            ar.add(new ArrayList());
            return ar;
        }

        //start add by qianchao 2005.10.27
        //��SBData��listת��ҳ����ʾ��list
        HashMap itemmap = null;
        ArrayList lwlist = new ArrayList();
        ArrayList nlwlist = new ArrayList();

        HashMap declaremaps = null;
        DeclareInfor di = null;
        SBData sb = null;
        String yhdm = null;
        String ssdwdm = null;
        for (int i = 0; i < sblist.size(); i++)
        {
            sb = (SBData) sblist.get(i);

            declaremaps = sb.getDeclareInforMap();
            Iterator declareite = declaremaps.values().iterator();
            di = (DeclareInfor) declareite.next();
            yhdm = di.getSbjkzb().getYhdm();
            ssdwdm = di.getSbjkzb().getSwjgzzjgdm();

            if (LWUtil.isLW(request.getSession().getServletContext(), ssdwdm, yhdm))
            {
                itemmap = new HashMap();
                itemmap.put("sbbh", sb.getSbbh());
                itemmap.put("sbrq",
                            (new SimpleDateFormat("yyyy-MM-dd")).format(sb.getSbrq()));
                itemmap.put("sjje", String.valueOf(sb.getHjsjje()));
                itemmap.put("jsjdm", sb.getJsjdm());
                lwlist.add(itemmap);
            }
            else
            {
                itemmap = new HashMap();
                itemmap.put("jkpzh", di.getSbjkzb().getJkpzh());
                itemmap.put("szmc", di.getSbjkzb().getSzmc());
                itemmap.put("sbrq",
                            (new SimpleDateFormat("yyyy-MM-dd")).format(sb.getSbrq()));
                itemmap.put("sjje", di.getSbjkzb().getSjje().toString());
                itemmap.put("jsjdm", di.getSbjkzb().getJsjdm());
                nlwlist.add(itemmap);

                while (declareite.hasNext())
                {
                    itemmap = new HashMap();
                    di = (DeclareInfor) declareite.next();

                    itemmap.put("jkpzh", di.getSbjkzb().getJkpzh());
                    itemmap.put("szmc", di.getSbjkzb().getSzmc());
                    itemmap.put("sbrq",
                                new SimpleDateFormat("yyyy-MM-dd").format(sb.getSbrq()));
                    itemmap.put("sjje", di.getSbjkzb().getSjje().toString());
                    itemmap.put("jsjdm", di.getSbjkzb().getJsjdm());
                    nlwlist.add(itemmap);
                }
            }
        }
        ArrayList ar = new ArrayList();
        ar.add(lwlist);
        ar.add(nlwlist);
        return ar;
        //end add by qianchao 2005.10.27
    }

    public static List splitMAP(Map sbmap, HttpServletRequest request)
    {
        if ( (sbmap == null) || (sbmap.size() == 0))
        {
            ArrayList ar = new ArrayList();
            ar.add(new ArrayList());
            ar.add(new ArrayList());
            return ar;
        }
        Iterator sbite = sbmap.values().iterator();
        ArrayList sblist = new ArrayList();

        while (sbite.hasNext())
        {
            sblist.add(sbite.next());
        }
        return splitMAP(sblist, request);
    }

    /**
     * ��ȫmap�е�˰��˰Ŀ����
     * map��Ԫ����SBData
     */
    public static void completeSzsmmc(Map sbmap, HttpServletRequest request)
    {
        List sblist = new ArrayList();
        Iterator sbite = sbmap.values().iterator();
        while (sbite.hasNext())
        {
            sblist.add(sbite.next());
        }
        completeSzsmmc(sblist, request);
    }

    /**
     * ��ȫlist�е�˰��˰Ŀ����
     */
    public static void completeSzsmmc(List sblist, HttpServletRequest request)
    {
        SBData sb = null;
        HashMap dimap = null;
        Sbjkzb zb = null;
        Sbjkmx mx = null;
        DeclareInfor di = null;
        Iterator diite = null;
        List mxlist = null;
        String szmc = null;
        for (int i = 0; i < sblist.size(); i++)
        {
            sb = (SBData) sblist.get(i);
            dimap = sb.getDeclareInforMap();
            diite = dimap.values().iterator();
            while (diite.hasNext())
            {
                di = (DeclareInfor) diite.next();
                zb = di.getSbjkzb();
                szmc = CodeUtils.getCodeBeanLabel("DM_SZSM", zb.getSzdm());
                zb.setSzmc(szmc);
                mxlist = di.getSbjkmxInfo();
                if (mxlist != null)
                {
                    for (int j = 0; j < mxlist.size(); j++)
                    {
                        mx = (Sbjkmx) mxlist.get(j);
                        mx.setSzmc(szmc);
                        mx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                    }
                }
            }
        }
    }

}
