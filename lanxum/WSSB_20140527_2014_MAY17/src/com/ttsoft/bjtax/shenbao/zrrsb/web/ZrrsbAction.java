package com.ttsoft.bjtax.shenbao.zrrsb.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.FB_JBSJ;
import com.ttsoft.bjtax.dj.model.FB_KKQK;
import com.ttsoft.bjtax.dj.model.ZRRFWDW;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRYHZH;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.ZrrsbInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Skfdqk;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Wbzhrmb;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;
import com.ttsoft.bjtax.shenbao.model.domain.Zy;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zrrsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zrrsb.ZrrsbActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��Ȼ���걨ǰ̨������ </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-17
 */
public class ZrrsbAction extends ShenbaoAction
{

    public ZrrsbAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.ZRRSB;
    }

    /**
     * ��ʼ����ʾ����
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException
    {
        try
        {
            ZrrsbForm myForm = (ZrrsbForm)form;
            Zrrgrsdsz zrrgrsdsz = this.getZrrsbFormAndZrrgrsdsZ(myForm, request);

            //start add by qianchao 2005.10.25
            List yhlist = null;
            ZRRYHZH zh = null;
            int i;

            UserData ud = getUserData(request);
            if (ud.getCaflag())
            {
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                    new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
                String jsjdm = getUserData(request).getXtsbm1();
                Sfxy sfxy = proxy.getSfxyInfo(jsjdm, new Date());
                if(! (sfxy == null || sfxy.getZh() == null))
                {
                    //������Э�������ȡ���Ǽǵ���������
                    zh = new ZRRYHZH();
                    zh.setYhdm(sfxy.getYhdm());
                    zh.setZh(sfxy.getZh());
                    zh.setKhyhmc( ( (Yh)CodeTableUtil.getCodeTableMap(request,
                        CodeTable.YH_MAP).get(sfxy.getYhdm())).getYhmc());
                    zh.setJsjdm(jsjdm);
                    ArrayList ar = new ArrayList();
                    ar.add(zh);
                    myForm.setYhList(ar);
                }
            }


            yhlist = myForm.getYhList();
            for (i = 0; i < yhlist.size(); i++)
            {
                zh = (ZRRYHZH) yhlist.get(i);
                if ("1".equals(zh.getJbzhbs()))
                {
                    break;
                }
            }
            String yhzh;
            if (i >= yhlist.size())
            {
                yhzh = "";
            }
            else
            {
                yhzh = zh.getYhdm();
            }
            myForm.setYhzh(yhzh);
            
            //request.setAttribute(mapping.getAttribute(),myForm);
            
            return mapping.findForward("Show");
            //end add by qianchao 2005.10.25
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ��ʼ����ʾ����
     * modified by qianchao 2005.10.24
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException
    {
        // ���token
        System.out.println("into  zrr do save.....");
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }
        ZrrsbForm myForm = (ZrrsbForm)form;


        UserData ud = getUserData(request);

        List mxList = null;
         //�������
        String[] je = myForm.getJe();
        //�ۺ�����
        String[] zh = myForm.getZh();
        //���ִ�������
        String[] bzdm = myForm.getBzdm();
        //����Ƽ�����
        String[] whpj = myForm.getWhpj();
        // start added code by qianchao 2006-2-8

        DzyjsjVO dzyj = null;
//        String strOrginData = request.getParameter("SecX_OrginData");
//        String signData = request.getParameter("SecX_SignData");
//        
//        System.out.println("strOrginData : " + strOrginData);
//        System.out.println("signData     : " + signData);
//        
//        if (ud.getCaflag())
//        {
//            String SecX_Error = request.getParameter("SecX_Error");
//            if (!"0".equals(SecX_Error))
//            {
//                String tempstr;
//                tempstr = "������ǩ������!Error:" + SecX_Error
//                + " SecX_OD " + ud.getYhid() + ":" + strOrginData
//                + "-----SecX_SD:" + signData  + "-----";
//                System.out.println(tempstr);
//                ActionErrors errors = new ActionErrors();
//                errors.add("", new ActionError("error.server.custom", "������ǩ������Error:" + SecX_Error));
//                saveErrors(request, errors);
//
//                this.getZrrsbFormAndZrrgrsdsZ(myForm, request);
//                myForm.setZrrgrsdsmxList(mxList);
//                myForm.setZh(zh);
//                myForm.setJe(je);
//                myForm.setBzdm(bzdm);
//                myForm.setWhpj(whpj);
//                
//                return (new ActionForward(mapping.getInput()));
//            }
//            System.out.println("============����ǩ�����ݿ�ʼ==============");
//            try
//            {
////                dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(ud, strOrginData, signData,
////                        codeConstants.YWDM_SB_WS_ZRR, "0", codeConstants.YWCZLX_NEW);
//            }
//            catch (Exception ex)
//            {
//                ex.printStackTrace();
//                this.getZrrsbFormAndZrrgrsdsZ(myForm, request);
//                myForm.setZrrgrsdsmxList(mxList);
//                myForm.setZh(zh);
//                myForm.setJe(je);
//                myForm.setBzdm(bzdm);
//                myForm.setWhpj(whpj);
//
//                throw ExceptionUtil.getBaseException(ex);
//            }
//            System.out.println("============����ǩ�����ݽ���==============");
//        }
        // end added code by qianchao 2006-2-8
        try
        {
            //ȡ����Ȼ�˵Ǽǻ������ݲ���дform
            Map zrrDjInfo = FriendHelper.getZrrDjInfo(request);
            ZRRJBSJ zrrjbsj = (ZRRJBSJ)zrrDjInfo.get(DjOuterConstant.ZRRJBSJ);
            List YhList = (List)zrrDjInfo.get(DjOuterConstant.ZRRYHZH);

            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = new SimpleDateFormat("yyyy").format(now);

            // ������Ϣ
            Zrrgrsdsz zrrgrsdsz = (Zrrgrsdsz)request.getSession().getAttribute(
                    SessionKey.ZRRGRSDSZ);

            // ��ϸ��Ϣ
            mxList = myForm.getZrrgrsdsmx(zrrjbsj);

            java.math.BigDecimal sum = new java.math.BigDecimal("0");
            List jkmxList = new ArrayList(mxList.size());
            for(int i = 0; i < mxList.size(); i++)
            {
                Zrrgrsdsmx zrrgrsdsmx = (Zrrgrsdsmx)mxList.get(i);
                Sbjkmx sbjkmx = new Sbjkmx();
                sbjkmx.setJsjdm(getUserData(request).yhid);
                sbjkmx.setSzsmdm(zrrgrsdsmx.getSzsmdm());
                sbjkmx.setZqlxdm(getSzsm(zrrgrsdsmx.getSzsmdm(), request).
                                 getZqlxdm()); // �������ʹ���
                sbjkmx.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm());
                sbjkmx.setJsje(zrrgrsdsmx.getYnssde());
                sbjkmx.setSjse(zrrgrsdsmx.getYbtsk());
                sbjkmx.setRkje(sbjkmx.getSjse());
                sbjkmx.setNd(nd);
                sbjkmx.setQxdm(zrrjbsj.getSwjgzzjgdm().substring(0, 2));
                sbjkmx.setCjrq(now); // ����ʱ��
                sbjkmx.setLrrq(now); // ¼������

                sum.add(zrrgrsdsmx.getYbtsk());

                jkmxList.add(sbjkmx);
            }

            // ������Ϣ
            Map swjgzzjgMap = (Map)CodeTableUtil.getCodeTableMap(request,
                    CodeTable.SWJJZZJG_MAP);

            Sbjkzb zb = new Sbjkzb();
            zb.setSjly(CodeConstant.SJLY_SB_ZRR_SBLR); // ������Դ
            zb.setJsjdm(getUserData(request).yhid); // ���������
            zb.setSklxdm(CodeConstant.SKLXDM_ZCJK); // ˰�����ʹ���
            zb.setFsdm(CodeConstant.FSDM_WSSB); // ��ʽ����
            zb.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm()); // ˰�������֯��������
            zb.setZsswjgzzjgdm(zrrjbsj.getSwjgzzjgdm()); // ���ջ��ش���
            zb.setGkzzjgdm( ( (Swjgzzjg)swjgzzjgMap.get(zb.getSwjgzzjgdm())).
                           getGkjhh()); // ������֯��������
            zb.setLrrq(now); // ¼������
            zb.setSbrq(TinyTools.second2Day(now)); // �걨����
            zb.setZyrq(zb.getSbrq()); // ��ҳ����
            zb.setSjje(sum); // ʵ�ɽ��
            zb.setRkje(sum); // �����
            zb.setLrr(getUserData(request).yhid); // ¼���˴���
            zb.setCjrq(now); // ����ʱ��
            //zb.setLsgxdm(); // ������ϵ����
            zb.setYhdm(myForm.getYhdm()); // ���д���
            zb.setYhmc(myForm.getYhmc()); // ��������
            zb.setZh(myForm.getYhzh()); // �ʺ�
            zb.setClbjdm(CodeConstant.CLBJDM_YSB);
            if(zrrjbsj.getGjdm().equals("CHN"))
            {
                zb.setDjzclxdm(CodeConstant.DEFAULT_CHINA_ZRR_DJZCLXDM); // �Ǽ�ע�����ʹ���
            }
            else
            {
                zb.setDjzclxdm(CodeConstant.DEFAULT_FOREIGN_ZRR_DJZCLXDM); // �Ǽ�ע�����ʹ���
            }
            zb.setGjbzhydm(CodeConstant.DEFAULT_ZRR_GJBZHYDM); // ���ұ�׼��ҵ����
            zb.setJydzlxdm(zrrjbsj.getZzdh()); // ��ϵ�绰
            zb.setNd(nd);
            zb.setQxdm(zrrjbsj.getSwjgzzjgdm().substring(0, 2));

            DeclareInfor declare = new DeclareInfor();
            declare.setSbjkzb(zb);
            declare.setSbjkmxInfo(jkmxList);

            //start modifying by qianchao 2005.10.28

            if (LWUtil.isLW(request.getSession().getServletContext(),zb.getSwjgzzjgdm(),zb.getYhdm()))
            {
               declare.setPrintTag(CodeConstant.PRINT_YPDS_KM);
            }
            else
            {
               declare.setPrintTag(CodeConstant.PRINT_YPYS);
            }
            //end modifying by qianchao 2005.10.28


            declare.setIsReturnPaymentInfo(true);
            ZRRJBSJ zrrJbsj = (ZRRJBSJ)request.getSession().getAttribute(
                    SessionKey.
                    ZRRJBSJ);

            // ȡ������ۺ����������List
            List wbzhrmbList = this.getWbzhrmbList(myForm, zrrgrsdsz,
                    mxList, zrrJbsj);

            ZrrsbInfor zrrsbInfor = new ZrrsbInfor();
            zrrsbInfor.setZrrgrsdsz(zrrgrsdsz);
            zrrsbInfor.setZrrsbmxList(mxList);
            zrrsbInfor.setWbzhrmbList(wbzhrmbList);
            zrrsbInfor.setDeclareInfor(declare);
            
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_SAVE);
            vo.setProcessor(ProcessorNames.ZRRSB_PROCESSOR);
            HashMap hm = new HashMap();
            hm.put(ZhsbMapConstant.SBSJ,zrrsbInfor);
            hm.put(ZhsbMapConstant.CA_QMSJ_VO,dzyj);
            vo.setData(hm);
            vo.setUserData(ud);

            Object reObject = ShenbaoProxy.getInstance().process(vo);
            Map ret = (Map) reObject;
            if(ret.get("showMsg") != null) {
            	request.setAttribute("showMsg", ret.get("showMsg"));
            	ret.remove("showMsg");
            }
            request.getSession().setAttribute(com.ttsoft.bjtax.shenbao.zhsb.
                                              SessionKey.JKS, reObject);
        }
        catch(Exception ex)
        {
            this.getZrrsbFormAndZrrgrsdsZ(myForm, request);
            myForm.setZrrgrsdsmxList(mxList);
            myForm.setZh(zh);
            myForm.setJe(je);
            myForm.setBzdm(bzdm);
            myForm.setWhpj(whpj);
            
            // start added code by qianchao 2006-2-8
//            if (ud.getCaflag() && (dzyj != null))
//            {
//                System.out.println("============����ɾ��ǩ����ʼ==============" + dzyj.getLsh());
//                try
//                {
//                    CAProxy.getInstance().deleteSignedData(dzyj);
//                }
//                catch (Exception e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                System.out.println("============����ɾ��ǩ������==============" + dzyj.getLsh());
//            }
            // end added code by qianchao 2006-2-8
            
            throw ExceptionUtil.getBaseException(ex);
        }
        request.getSession().removeAttribute(SessionKey.ZRRGRSDSZ);
        request.getSession().removeAttribute(SessionKey.ZRRJBSJ);
        return mapping.findForward("Save");
    }

    // ���˸�˰��˰��˰Ŀ
    //modified by zhangyj 20131214 ���Ӹ�������˰˰Ŀ����
    private List getGrsdsSzsmList(List szsmList)
    {
        List grsdsSzsmList = new ArrayList();
        for(int i = 0; i < szsmList.size(); i++)
        {
            Szsm szsm = (Szsm)szsmList.get(i);
            String szsmdm = szsm.getSzsmdm();
            if(szsmdm.trim().startsWith(SzsmdmConstant.GRSDS) &&
               !szsmdm.trim().equals(SzsmdmConstant.GRSDS_ZNJ) &&
               !szsmdm.trim().equals(SzsmdmConstant.GRSDS_JCPGBS) &&
               ! (szsmdm.length() < 6) &&
               !szsmdm.trim().startsWith(SzsmdmConstant.GTGSH) &&
               !szsmdm.trim().startsWith(SzsmdmConstant.GRCB) &&
               !szsmdm.trim().startsWith(SzsmdmConstant.GRGXJJFHTZQ) &&
               !szsmdm.trim().equals(SzsmdmConstant.GRGXDETZQ) &&
               !szsmdm.trim().equals(SzsmdmConstant.GRSYJJTZQ) &&
               !szsmdm.trim().equals(SzsmdmConstant.GRTZQ) &&
               !szsmdm.trim().equals(SzsmdmConstant.GRTZFTZQ))
            {
                grsdsSzsmList.add(szsm);
            }
        }
        return grsdsSzsmList;
    }


    //��BigDecimal��String
    private String ToString(BigDecimal je)
    {
        if(je != null)
        {
            return je.toString();
        }
        else
        {
            return null;
        }
    }

    // ����˰�������֯��������ȡ�����ش���
    private String getQxdm(String swjgzzjgdm)
    {
        return swjgzzjgdm.trim().substring(0, 2);
    }

    // ����˰���������룬ȡ��˰����������
    private String getSkfdqkmc(String skfdqkdm, HttpServletRequest request)
    {
        if(skfdqkdm!=null)
        {
            Map skfdqkMap = CodeTableUtil.getCodeTableMap(request,
                CodeTable.SKFDQK_MAP);
            Skfdqk skfdqk = (Skfdqk)skfdqkMap.get(skfdqkdm);
            return skfdqk.getSkfdqkmc();
        }
        else
        {
            return null;
        }
    }

    private String getPercent(double percent)
    {
        return new Double(percent * 100).toString() + "%";
    }

    /**
     * ȡ�ñ����ۺ����������List
     * @param BzList ����List
     * @return List
     */
    public List getWbzhrmbList( ZrrsbForm myForm,
                               Zrrgrsdsz zrrgrsdsz, List mxList,
                               ZRRJBSJ zrrjbsj) throws BaseException
    {
        List wbzhrmbList = new ArrayList();
        String je[] = myForm.getJe();
        String zh[] = myForm.getZh();
        String bzdm[] = myForm.getBzdm();
        String whpj[] = myForm.getWhpj();
        Wbzhrmb wbzhrmb = null;
        for(int i = 0; i < je.length; i++)
        {
            String jeStr = je[i];
            String zhStr = zh[i];
            String bzdmStr = bzdm[i];
            String whpjStr = whpj[i];
            StringTokenizer jeSt = new StringTokenizer(jeStr, ",");
            StringTokenizer zhSt = new StringTokenizer(zhStr, ",");
            StringTokenizer bzdmSt = new StringTokenizer(bzdmStr, ",");
            StringTokenizer whpjSt = new StringTokenizer(whpjStr, ",");
            while(jeSt.hasMoreTokens())
            {
                String jeValue = jeSt.nextToken();
                String zhValue = zhSt.nextToken();
                String bzdmValue = bzdmSt.nextToken();
                String whpjValue = whpjSt.nextToken();

                    wbzhrmb = new Wbzhrmb();
                    if(bzdmValue!=null&&!bzdmValue.trim().equals(""))
                        wbzhrmb.setBzdm(bzdmValue);
                    wbzhrmb.setCjrq(zrrgrsdsz.getCjrq());
                    if(jeValue!=null&&!jeValue.trim().equals(""))
                        wbzhrmb.setJe(new BigDecimal(jeValue));
                    wbzhrmb.setJsjdm(zrrgrsdsz.getJsjdm());
                    wbzhrmb.setLrrq(zrrgrsdsz.getLrrq());
                    wbzhrmb.setQxdm(zrrgrsdsz.getQxdm());
                    wbzhrmb.setSbrq(zrrgrsdsz.getSbrq());
                    wbzhrmb.setSdjsrq( ( (Zrrgrsdsmx)mxList.get(i)).getSdjsrq());
                    wbzhrmb.setSdksrq( ( (Zrrgrsdsmx)mxList.get(i)).getSdksrq());
                    wbzhrmb.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm());
                    wbzhrmb.setSzsmdm( ( (Zrrgrsdsmx)mxList.get(i)).getSzsmdm());
                    if(whpjValue!=null&&!whpjValue.trim().equals(""))
                        wbzhrmb.setWhpj(new BigDecimal(whpjValue));
                    if(zhValue!=null&&!zhValue.trim().equals(""))
                        wbzhrmb.setZhrmb(new BigDecimal(zhValue));
                    ( (Zrrgrsdsmx)mxList.get(i)).setJeStr(jeStr);
                    ( (Zrrgrsdsmx)mxList.get(i)).setZhStr(zhStr);
                    ( (Zrrgrsdsmx)mxList.get(i)).setBzdmStr(bzdmStr);
                    ( (Zrrgrsdsmx)mxList.get(i)).setWhpjStr(whpjStr);
                    wbzhrmbList.add(wbzhrmb);
            }
        }

        // �ж��Ƿ����ظ��ı��ִ���
        for(int i=0; i<bzdm.length; i++)
        {
            List bzdmTempList = new ArrayList();
            String bzdmStr = bzdm[i];
            StringTokenizer bzdmStTemp = new StringTokenizer(bzdmStr, ",");

            while(bzdmStTemp.hasMoreTokens())
            {
                bzdmTempList.add(bzdmStTemp.nextToken());
            }
            for(int j=0; j< bzdmTempList.size(); j++)
            {
                String bzdmTemp = (String)bzdmTempList.get(j);
                for(int s=j+1; s<bzdmTempList.size(); s++)
                {
                    if(bzdmTemp.equals((String)bzdmTempList.get(s)))
                    {
                        throw  new ApplicationException("���ĵ�"+i+1+"���걨��Ŀ��ѡ������ͬ�ı��ִ��룡");
                    }
                }
            }
        }
        return wbzhrmbList;
    }


    /**
     * ���ִ�����ѯ
     * @return ���±��ִ����
     * @throws BaseException �����쳣
     */
    private  List getBzList(HttpServletRequest request, String tableKey) throws
        BaseException
    {
        // ��һ���
        VOPackage vo = new VOPackage();
        vo.setAction(ZrrsbActionConstant.INT_ACTION_QUERYBZ);
        vo.setProcessor(ProcessorNames.ZRRSB_PROCESSOR);
        //vo.setData(zrrsbInfor);
        vo.setUserData(getUserData(request));

        Map reObject = (Map)ShenbaoProxy.getInstance().process(vo);
        return (List)reObject.get(tableKey);
    }


    private Zrrgrsdsz getZrrsbFormAndZrrgrsdsZ(ZrrsbForm myForm,
                                               HttpServletRequest request) throws
            BaseException
    {
        try
        {

            Zrrgrsdsz zrrgrsdsz = new Zrrgrsdsz();
            UserData userData = getUserData(request);
            String jsjdm = userData.yhid;
            myForm.setJsjdm(jsjdm);

            //ȡ����Ȼ�˵Ǽǻ������ݲ���дform
            ZRRJBSJ zrrjbsj = FriendHelper.getZrrjbsj(request);

            //ȡ����Ȼ�˵������Ǽ�����Map
            Map zrrDjInfo = FriendHelper.getZrrDjInfo(request);
            //��Ȼ��������Ϣ�б�
            List zrryhList = (List)zrrDjInfo.get(DjOuterConstant.ZRRYHZH);
            //��Ȼ�˷���λֵ����List
            List zrrfwdwList = (List)zrrDjInfo.get(DjOuterConstant.ZRRFWDW);
            //��Ȼ�˷���λֵ����
            ZRRFWDW zrrfwdw = new ZRRFWDW();
            for(int i = 0; i < zrrfwdwList.size(); i++)
            {
                ZRRFWDW tempVo = (ZRRFWDW)zrrfwdwList.get(i);
//                if(tempVo.getZjlxdm().equals(zrrjbsj.getZjlxdm()) &&
//                   tempVo.getZjhm().equals(zrrjbsj.getZjhm()) &&
//                   tempVo.getGjdm().equals(zrrjbsj.getGjdm()))
                if (tempVo.getJsjdm().equals(zrrjbsj.getJsjdm()))
                {
                    zrrfwdw = tempVo;
                }
            }
            //����-��������ֵ����
            FB_JBSJ jbsj = (FB_JBSJ)zrrDjInfo.get(DjOuterConstant.ZRRFB);
            //�ۿ����
            List kkqkList = (List)zrrDjInfo.get(DjOuterConstant.ZRRKKQK);
            String ffkce = null;
            //�ӿۿ���������ҵ��ⷿ�ѿ۳���۳���Ŀ����Ϊ��1���ģ�
            for(int i = 0; i < kkqkList.size(); i++)
            {
                FB_KKQK kkqk = (FB_KKQK)kkqkList.get(i);
                if(kkqk.getKcxmdm().equals("1"))
                {
                    ffkce = ToString(kkqk.getKcje());
                }
            }

            //�õ���˰��˰Ŀ��Ϣ
            myForm.setGsszsmList(
                    this.getGrsdsSzsmList(CodeTableUtil.getCodeTableList(
                    request,
                    CodeTable.SZSM_LIST)));

            //�õ���һ�������List
            myForm.setBzList(getBzList(request,
                    CodeTable.WBHS_LIST));

            myForm.setYhList(zrryhList);

            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = new SimpleDateFormat("yyyy").format(now);

            Map skssrq = Skssrq.monthSkssrq(now);
            myForm.setCjr(zrrjbsj.getJsjdm());
            zrrgrsdsz.setCjr(zrrjbsj.getJsjdm());
            myForm.setSbrq(TinyTools.second2Day(now));
            myForm.setCjsj(now);
            zrrgrsdsz.setCjrq(now);
            myForm.setDh(zrrjbsj.getZzdh());
            zrrgrsdsz.setDh(zrrjbsj.getZzdh());
            myForm.setDhrq(zrrjbsj.getDhrq());
            zrrgrsdsz.setDhrq(zrrjbsj.getDhrq());
            myForm.setNsrmc(zrrjbsj.getNsrmc());
            myForm.setZjlxmc(zrrjbsj.getZjlxmc());
            zrrgrsdsz.setZjlxdm(zrrjbsj.getZjlxdm());
            zrrgrsdsz.setZjhm(zrrjbsj.getZjhm());
            myForm.setGjmc(zrrjbsj.getGjmc());
            zrrgrsdsz.setGjdm(zrrjbsj.getGjdm());
            Zy zy = (Zy)CodeTableUtil.getCodeTableMap(request,
                    CodeTable.ZY_MAP).get(zrrjbsj.getZydm());
            if(zy != null)
            {
                myForm.setZymc(zy.getZymc());
            }
            zrrgrsdsz.setZydm(zrrjbsj.getZydm());
            if(jbsj!=null)
            {
                myForm.setFdfsmc(this.getSkfdqkmc(jbsj.getSkfdqk(), request));
                myForm.setFdfsdm(jbsj.getSkfdqk());
                if(jbsj.getSkfdbl() == null)
                {
                    if(jbsj.getSkfdqk().equals(CodeConstant.SKFDQK_DWFDQB))
                    {
                        myForm.setDwfdbl("100");
                    }
                    else
                    {
                        myForm.setDwfdbl("0");
                    }
                }
                else
                {
                    myForm.setDwfdbl(jbsj.getSkfdbl().toString());
                }
                zrrgrsdsz.setDwfdbl(jbsj.getSkfdbl());
                myForm.setSfczbs(jbsj.getCzbs());
            }
            myForm.setFwdw(zrrfwdw.getDwmc());
            zrrgrsdsz.setFwdw(zrrfwdw.getDwmc());
            myForm.setGjdm(zrrjbsj.getGjdm());
            myForm.setJnzz(zrrjbsj.getTxdz());
            myForm.setJsjdm(zrrjbsj.getJsjdm());
            zrrgrsdsz.setJsjdm(zrrjbsj.getJsjdm());
            myForm.setSkssksrq( (Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            zrrgrsdsz.setSkssksrq( (Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            myForm.setSkssjsrq( (Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            zrrgrsdsz.setSkssjsrq( (Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            myForm.setTxdz(zrrjbsj.getTxdz());
            zrrgrsdsz.setTxdz(zrrjbsj.getTxdz());
            myForm.setYzbm(zrrjbsj.getYb());
            zrrgrsdsz.setYzbm(zrrjbsj.getYb());
            myForm.setZffkce(ffkce);
            myForm.setZjhm(zrrjbsj.getZjhm());
            myForm.setZjlxdm(zrrjbsj.getZjlxdm());
            myForm.setZydm(zrrjbsj.getZydm());
            myForm.setQxdm(getQxdm(zrrjbsj.getSwjgzzjgdm()));
            zrrgrsdsz.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm());
            zrrgrsdsz.setQxdm(getQxdm(zrrjbsj.getSwjgzzjgdm()));

            request.getSession().setAttribute(SessionKey.ZRRGRSDSZ, zrrgrsdsz);
            request.getSession().setAttribute(SessionKey.ZRRJBSJ, zrrjbsj);

            return zrrgrsdsz;
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}
