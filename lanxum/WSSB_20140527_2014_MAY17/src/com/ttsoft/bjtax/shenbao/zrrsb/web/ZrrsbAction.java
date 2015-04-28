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
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 自然人申报前台控制器 </p>
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
     * 初始化显示处理
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
                    //用三方协议的银行取代登记的银行数据
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
     * 初始化显示处理
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
        // 检查token
        System.out.println("into  zrr do save.....");
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }
        ZrrsbForm myForm = (ZrrsbForm)form;


        UserData ud = getUserData(request);

        List mxList = null;
         //金额数组
        String[] je = myForm.getJe();
        //折合数组
        String[] zh = myForm.getZh();
        //币种代码数组
        String[] bzdm = myForm.getBzdm();
        //外汇牌价数组
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
//                tempstr = "解密验签名错误!Error:" + SecX_Error
//                + " SecX_OD " + ud.getYhid() + ":" + strOrginData
//                + "-----SecX_SD:" + signData  + "-----";
//                System.out.println(tempstr);
//                ActionErrors errors = new ActionErrors();
//                errors.add("", new ActionError("error.server.custom", "解密验签名错误！Error:" + SecX_Error));
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
//            System.out.println("============保存签名数据开始==============");
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
//            System.out.println("============保存签名数据结束==============");
//        }
        // end added code by qianchao 2006-2-8
        try
        {
            //取得自然人登记基本数据并填写form
            Map zrrDjInfo = FriendHelper.getZrrDjInfo(request);
            ZRRJBSJ zrrjbsj = (ZRRJBSJ)zrrDjInfo.get(DjOuterConstant.ZRRJBSJ);
            List YhList = (List)zrrDjInfo.get(DjOuterConstant.ZRRYHZH);

            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = new SimpleDateFormat("yyyy").format(now);

            // 主表信息
            Zrrgrsdsz zrrgrsdsz = (Zrrgrsdsz)request.getSession().getAttribute(
                    SessionKey.ZRRGRSDSZ);

            // 明细信息
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
                                 getZqlxdm()); // 征期类型代码
                sbjkmx.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm());
                sbjkmx.setJsje(zrrgrsdsmx.getYnssde());
                sbjkmx.setSjse(zrrgrsdsmx.getYbtsk());
                sbjkmx.setRkje(sbjkmx.getSjse());
                sbjkmx.setNd(nd);
                sbjkmx.setQxdm(zrrjbsj.getSwjgzzjgdm().substring(0, 2));
                sbjkmx.setCjrq(now); // 创建时间
                sbjkmx.setLrrq(now); // 录入日期

                sum.add(zrrgrsdsmx.getYbtsk());

                jkmxList.add(sbjkmx);
            }

            // 主表信息
            Map swjgzzjgMap = (Map)CodeTableUtil.getCodeTableMap(request,
                    CodeTable.SWJJZZJG_MAP);

            Sbjkzb zb = new Sbjkzb();
            zb.setSjly(CodeConstant.SJLY_SB_ZRR_SBLR); // 数据来源
            zb.setJsjdm(getUserData(request).yhid); // 计算机代码
            zb.setSklxdm(CodeConstant.SKLXDM_ZCJK); // 税款类型代码
            zb.setFsdm(CodeConstant.FSDM_WSSB); // 方式代码
            zb.setSwjgzzjgdm(zrrjbsj.getSwjgzzjgdm()); // 税务机关组织机构代码
            zb.setZsswjgzzjgdm(zrrjbsj.getSwjgzzjgdm()); // 征收机关代码
            zb.setGkzzjgdm( ( (Swjgzzjg)swjgzzjgMap.get(zb.getSwjgzzjgdm())).
                           getGkjhh()); // 国库组织机构代码
            zb.setLrrq(now); // 录入日期
            zb.setSbrq(TinyTools.second2Day(now)); // 申报日期
            zb.setZyrq(zb.getSbrq()); // 帐页日期
            zb.setSjje(sum); // 实缴金额
            zb.setRkje(sum); // 入库金额
            zb.setLrr(getUserData(request).yhid); // 录入人代码
            zb.setCjrq(now); // 创建时间
            //zb.setLsgxdm(); // 隶属关系代码
            zb.setYhdm(myForm.getYhdm()); // 银行代码
            zb.setYhmc(myForm.getYhmc()); // 银行名称
            zb.setZh(myForm.getYhzh()); // 帐号
            zb.setClbjdm(CodeConstant.CLBJDM_YSB);
            if(zrrjbsj.getGjdm().equals("CHN"))
            {
                zb.setDjzclxdm(CodeConstant.DEFAULT_CHINA_ZRR_DJZCLXDM); // 登记注册类型代码
            }
            else
            {
                zb.setDjzclxdm(CodeConstant.DEFAULT_FOREIGN_ZRR_DJZCLXDM); // 登记注册类型代码
            }
            zb.setGjbzhydm(CodeConstant.DEFAULT_ZRR_GJBZHYDM); // 国家标准行业代码
            zb.setJydzlxdm(zrrjbsj.getZzdh()); // 联系电话
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

            // 取得外币折合人民币数据List
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
//                System.out.println("============出错删除签名开始==============" + dzyj.getLsh());
//                try
//                {
//                    CAProxy.getInstance().deleteSignedData(dzyj);
//                }
//                catch (Exception e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                System.out.println("============出错删除签名结束==============" + dzyj.getLsh());
//            }
            // end added code by qianchao 2006-2-8
            
            throw ExceptionUtil.getBaseException(ex);
        }
        request.getSession().removeAttribute(SessionKey.ZRRGRSDSZ);
        request.getSession().removeAttribute(SessionKey.ZRRJBSJ);
        return mapping.findForward("Save");
    }

    // 过滤个税的税种税目
    //modified by zhangyj 20131214 增加个人所得税税目过滤
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


    //从BigDecimal到String
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

    // 根据税务机关组织机构代码取的区县代码
    private String getQxdm(String swjgzzjgdm)
    {
        return swjgzzjgdm.trim().substring(0, 2);
    }

    // 根据税款负担情况代码，取得税款负担情况名称
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
     * 取得币种折合人民币数据List
     * @param BzList 币种List
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

        // 判断是否有重复的币种代码
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
                        throw  new ApplicationException("您的第"+i+1+"条申报项目中选择有相同的币种代码！");
                    }
                }
            }
        }
        return wbzhrmbList;
    }


    /**
     * 币种代码表查询
     * @return 最新币种代码表
     * @throws BaseException 操作异常
     */
    private  List getBzList(HttpServletRequest request, String tableKey) throws
        BaseException
    {
        // 外币换算
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

            //取得自然人登记基本数据并填写form
            ZRRJBSJ zrrjbsj = FriendHelper.getZrrjbsj(request);

            //取得自然人的其他登记资料Map
            Map zrrDjInfo = FriendHelper.getZrrDjInfo(request);
            //自然人银行信息列表
            List zrryhList = (List)zrrDjInfo.get(DjOuterConstant.ZRRYHZH);
            //自然人服务单位值对象List
            List zrrfwdwList = (List)zrrDjInfo.get(DjOuterConstant.ZRRFWDW);
            //自然人服务单位值对象
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
            //附表-基本数据值对象
            FB_JBSJ jbsj = (FB_JBSJ)zrrDjInfo.get(DjOuterConstant.ZRRFB);
            //扣款情况
            List kkqkList = (List)zrrDjInfo.get(DjOuterConstant.ZRRKKQK);
            String ffkce = null;
            //从扣款情况表中找到租房费扣除额（扣除项目代码为“1”的）
            for(int i = 0; i < kkqkList.size(); i++)
            {
                FB_KKQK kkqk = (FB_KKQK)kkqkList.get(i);
                if(kkqk.getKcxmdm().equals("1"))
                {
                    ffkce = ToString(kkqk.getKcje());
                }
            }

            //得到个税的税目信息
            myForm.setGsszsmList(
                    this.getGrsdsSzsmList(CodeTableUtil.getCodeTableList(
                    request,
                    CodeTable.SZSM_LIST)));

            //得到外币换算数据List
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
