package com.ttsoft.bjtax.shenbao.wsksb.web;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 四一安信股份有限公司，版权所有.</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  guzhixian
 * @version 1.1
 */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.wsksb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class WsksbAction extends ShenbaoAction
{

    protected static MessageResources messages = MessageResources.getMessageResources("ApplicationResources");

    static int CANDO = 0;

    static int CANNOTDO = 1;

    public WsksbAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.WSKSB;
        // return SbzlAccess.ALWAYSPASS;
    }

    /**
     * 获取纳税人的无税申报数据并显示申报页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws BaseException
    {
        try
        {
            System.out.println("||||||||||||||   WsksbAction---doShow  |||||||||||||| ");
            WsksbForm oForm = (WsksbForm) form;
            oForm.setMsg(new ArrayList());
            // 取得登记数据
            Map djMap = (Map) FriendHelper.getDjInfo(request);
            UserData userData = getUserData(request);
            // 获得计算机代码
            String jsjdm = userData.yhid;
            // 设置计算机代码
            oForm.setJsjdm(jsjdm);

            SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

            // 取税费管理数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            Calendar cc = new GregorianCalendar();
            cc.add(Calendar.MONTH, -1);
            int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
            cc.set(Calendar.DAY_OF_MONTH, maxday);

            // 上个月最后一天
            Date dd = cc.getTime();
            List dqdeList = sfglProxy.getYnsje(jsjdm, dd, dd);
            boolean dqde = false;
            for (int i = 0; i < dqdeList.size(); i++)
            {
                Dqdedlmx1 dqdedl = (Dqdedlmx1) dqdeList.get(i);
                if (dqdedl != null)
                {
                    String zsfsdm = dqdedl.getZsfsdm();
                    if (zsfsdm != null && zsfsdm.equals("01")) // 定额征收
                    {
                        dqde = true;
                        break;
                    }
                }
            }

            // 设置申报日期（当前时间）
            Date now = new Date();
            List msgList = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 设置form中的数据
            oForm.setNsrmc(jbsj.getNsrmc()); // 设置纳税人名称
            oForm.setSbrq(sdf.format(now));
            oForm.setLxdh(jbsj.getJydzlxdm());
            oForm.setNd((new SimpleDateFormat("yyyy")).format(now));
            oForm.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            String skssksrq = sdf.format((Timestamp) Skssrq.monthSkssrq(now).get(Skssrq.SKSSKSRQ));
            String skssjsrq = sdf.format((Timestamp) Skssrq.monthSkssrq(now).get(Skssrq.SKSSJSRQ));
            oForm.setSkssksrq(skssksrq);
            oForm.setSkssjsrq(skssjsrq);
            // 构造查询条件查询后台

            Map data = new HashMap();
            data.put(SessionKey.JSJDM, jsjdm);
            data.put("WSSB_SWJGZZJGDM", oForm.getSwjgzzjgdm());
            data.put("WSSB_DJZCLXDM", jbsj.getDjzclxdm());
            data.put(SessionKey.WHRQ, oForm.getSbrq());
            data.put(SessionKey.SKSSKSRQ, skssksrq);
            data.put(SessionKey.SKSSJSRQ, skssjsrq);
            VOPackage voPackage = oForm.getQueryData(userData, data, SessionKey.INT_ACTION_QUERYWSKJL);

            // voPackage.setAction(SessionKey.INT_ACTION_QUERYWSKJL);
            Map wskMap = (Map) ShenbaoProxy.getInstance().process(voPackage);
            Wynsksb wynsksb = (Wynsksb) wskMap.get(SessionKey.WSKJKS);
        	System.out.print("--------wynsksb-------");
        	System.out.println(wynsksb);
            String zqstr = TinyTools.getProperty(SessionKey.WSKSBZQRLPROPERTY
                    + (new SimpleDateFormat("MM")).format(now));
            System.out.println(" ||||||||||||||   zqstr == " + zqstr + " ||||||||||||||   dqde == " + dqde);
            boolean withinzq = withinZq(zqstr, now);
            // 测试用!!!!!
            // withinzq = true;//测试用

            if (wynsksb != null)
            {
                oForm.setSbrq(sdf.format(wynsksb.getSbrq()));
                oForm.setBz(wynsksb.getBz());
                oForm.setSbbh(wynsksb.getSbbh());
            }

            // 定期定额征收方式不需要做无应纳税款申报
            if (dqde)
            {
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.dqde"));
            }
            else if (!withinzq)// 征期已过，有关申报事宜请与主管税务机关联系
            {
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.zqyg"));
            }
            else if (wynsksb == null)
            {// 如果您本期没有税款，请单击保存按钮进行无应纳税款申报
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANDO);
                msgList.add(getMessage("message.web.wsksb.cansave"));
                // 您本期已经做了无应纳税款申报,可以单击删除按钮，删除本期无应纳税款申报书
            }
            else if (wynsksb.getFsdm() != null && wynsksb.getFsdm().equals(CodeConstant.FSDM_WSSB))
            {
                oForm.setCanDel(CANDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.candel"));
                // 您本期已经做了无应纳税款申报,上门
            }
            else
            {
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.notwssb"));
            }
            msgList.add(getMessage("message.web.wsksb.yssbts"));
            oForm.setMsg(msgList);
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除纳税人的无税申报数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws BaseException
    {
        UserData ud = getUserData(request);

        try
        {
            System.out.println("WsksbAction---doDelete");
            WsksbForm oForm = (WsksbForm) form;
            if (oForm.getCanDel() == CANDO)
            {
                // 取得登记数据

                // started added by qianchao 2005-11-24
                DzyjsjVO dzyj = null;

                String strOrginData = request.getParameter("SecX_OrginData");
                String signData = request.getParameter("SecX_SignData");
                if (ud.getCaflag())
                {
                    // /检测签名并保存
                    String SecX_Error = request.getParameter("SecX_Error");
                    if (!"0".equals(SecX_Error))
                    {
                        String tempstr;
                        tempstr = "解密验签名错误!Error:" + SecX_Error
                        + " SecX_OD " + ud.getYhid() + ":" + strOrginData
                        + "-----SecX_SD:" + signData  + "-----";
                        System.out.println(tempstr);
                        ActionErrors errors = new ActionErrors();
                        errors.add("", new ActionError("error.server.custom", "解密验签名错误！Error:" + SecX_Error));
                        saveErrors(request, errors);
                        return (new ActionForward(mapping.getInput()));
                    }
                    System.out.println("============保存签名数据开始==============");
                    System.out.println(oForm.getSbbh());

                    try
                    {
//                        dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(ud, strOrginData, signData,
//                                codeConstants.YWDM_SB_WS_ZHSB_WYNSSB, oForm.getSbbh(), codeConstants.YWCZLX_DELETE);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();

                        throw ExceptionUtil.getBaseException(ex);
                    }
                    System.out.println("============保存签名数据结束==============");
                }
                // ended added by qianchao 2005-11-24

                Map djMap = (Map) FriendHelper.getDjInfo(request);
                SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
                Timestamp now = new Timestamp(System.currentTimeMillis());
                Wynsksb wynsksb = new Wynsksb();
                wynsksb.setJsjdm(oForm.getJsjdm());
                wynsksb.setSbbh(oForm.getSbbh());
                wynsksb.setNd((new SimpleDateFormat("yyyy")).format(now));
                wynsksb.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));
                VOPackage voPackage = new VOPackage();
                voPackage.setData(wynsksb);
                voPackage.setAction(SessionKey.INT_ACTION_DELETE);
                voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);

                ShenbaoProxy.getInstance().process(voPackage);
                LogUtil.getInstance().log(FriendHelper.getUserData(request), "无税申报删除",
                        (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
                return mapping.findForward("Delete");
            }
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            // started added by qianchao 2005-11-24

            // if (ud.getCaflag() && (calsh != 0))
            // {
            // System.out.println("============出错删除签名开始=============="
            // + calsh);
            // try
            // {
            // YYAQProxy.getInstance().deleteSignedData(calsh);
            // }
            // catch (YYAQException e)
            // {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // System.out.println("============出错删除签名结束=============="
            // + calsh);
            // }
            // ended added by qianchao 2005-11-24
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public ActionForward doReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws BaseException
    {
        return mapping.findForward("Return");
    }

    /**
     * 保存纳税人的无税申报数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws BaseException
    {
        // 检查token
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        //
        UserData ud = getUserData(request);

        // start added code by qianchao 2006-2-11

        DzyjsjVO dzyj = null;

        String strOrginData = request.getParameter("SecX_OrginData");
        String signData = request.getParameter("SecX_SignData");

//        System.out.println("strOrginData : " + strOrginData);
//        System.out.println("signData     : " + signData);

        if (ud.getCaflag())
        {
            String SecX_Error = request.getParameter("SecX_Error");
            if (!"0".equals(SecX_Error))
            {
                String tempstr;
                tempstr = "解密验签名错误!Error:" + SecX_Error
                + " SecX_OD " + ud.getYhid() + ":" + strOrginData
                + "-----SecX_SD:" + signData  + "-----";
                System.out.println(tempstr);
                ActionErrors errors = new ActionErrors();
                errors.add("", new ActionError("error.server.custom", "解密验签名错误！Error:" + SecX_Error));
                saveErrors(request, errors);
                return (new ActionForward(mapping.getInput()));
            }
            System.out.println("============保存签名数据开始==============");
            try
            {
//                dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(ud, strOrginData, signData,
//                        codeConstants.YWDM_SB_WS_ZHSB_WYNSSB, "0", codeConstants.YWCZLX_NEW);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
            System.out.println("============保存签名数据结束==============");
        }
        // end added code by qianchao 2006-2-11

        //
        try
        {
            WsksbForm oForm = (WsksbForm) form;
            List msgList = new ArrayList();
            oForm.setMsg(msgList);
            if (oForm.getCanSave() == CANDO)
            {
                // 取得登记数据
                Map djMap = (Map) FriendHelper.getDjInfo(request);
                SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

                Timestamp now = new Timestamp(System.currentTimeMillis());
                Wynsksb wynsksb = getWynsksb(jbsj, oForm, now);
                // 构造查询条件查询后台
                VOPackage voPackage = new VOPackage();
                voPackage.setUserData(ud);

                HashMap hm = new HashMap();
                hm.put(ZhsbMapConstant.SBSJ, wynsksb);
                hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

                voPackage.setData(hm);
                voPackage.setAction(SessionKey.INT_ACTION_SAVE);
                voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);

                //start added code by qianchao 2006-2-11
                //否则申报表号无法返回，导致页面没有申报表号，导致立即删除会出错。
                //ShenbaoProxy.getInstance().process(voPackage)
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
                // wynsksb = (Wynsksb)voPackage.getData();
                wynsksb = (Wynsksb)(((HashMap)voPackage.getData()).get(ZhsbMapConstant.SBSJ));
                //end   added code by qianchao 2006-2-11
                
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // 设置form中的数据
                oForm.setNsrmc(jbsj.getNsrmc()); // 设置纳税人名称
                oForm.setSbrq(sdf.format(now));
                oForm.setLxdh(jbsj.getJydzlxdm());
                oForm.setNd((new SimpleDateFormat("yyyy")).format(now));
                oForm.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
                oForm.setSbbh(wynsksb.getSbbh());


                oForm.setCanDel(CANDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.savesuccess"));
                msgList.add(getMessage("message.web.wsksb.yssbts"));
                oForm.setMsg(msgList);
                LogUtil.getInstance().log(FriendHelper.getUserData(request), "无税申报保存",
                        (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");

            }

            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            // started added by qianchao 2006-2-11

            if (ud.getCaflag() && (dzyj != null))
            {
                System.out.println("============出错删除签名开始==============" + dzyj.getLsh());
                try
                {
                    CAProxy.getInstance().deleteSignedData(dzyj);
                }
                catch (ApplicationException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("============出错删除签名结束==============" + dzyj.getLsh());
            }

            // ended added by qianchao 2006-2-11
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    // 判断当前日期是否在征期内
    private boolean withinZq(String zqstr, Date now)
    {
        try
        {
            int fromDate = Integer.parseInt(zqstr.substring(0, 4));
            int toDate = Integer.parseInt(zqstr.substring(5));
            int nowDate = Integer.parseInt((new SimpleDateFormat("MMdd")).format(now));

            if (nowDate <= toDate && nowDate >= fromDate)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            return false;
        }
        finally
        {

        }
    }

    // 用WsksbForm数据构造Wynsksb对象
    private Wynsksb getWynsksb(SWDJJBSJ jbsj, WsksbForm oForm, Timestamp now) throws Exception
    {
        Wynsksb wynsksb = new Wynsksb();
        long currentTime = now.getTime();

        wynsksb.setJsjdm(oForm.getJsjdm());
        wynsksb.setSbrq(now);
        wynsksb.setBz(oForm.getBz());
        wynsksb.setFsdm(CodeConstant.FSDM_WSSB);
        wynsksb.setLrr(oForm.getJsjdm());
        wynsksb.setLrrq(now);
        wynsksb.setCjrq(now);
        wynsksb.setNd((new SimpleDateFormat("yyyy")).format(now));
        wynsksb.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));
        // wynsksb.setSbbh(sbbh);
        wynsksb.setSjly("11");
        wynsksb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        wynsksb.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
        /*
         * Map map = Skssrq.getSksssq(oForm.getJsjdm(), SzsmdmConstant.YYS_TLYS,
         * jbsj.getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
         * CodeConstant.ZQLXDM_MONTH, now, new BigDecimal(0), new BigDecimal(0),
         * new BigDecimal(0));
         * 
         * wynsksb.setSkssksrq((Timestamp)map.get(Skssrq.SKSSKSRQ));
         * wynsksb.setSkssjsrq((Timestamp)map.get(Skssrq.SKSSJSRQ));
         */
        wynsksb.setSkssksrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSKSRQ));
        wynsksb.setSkssjsrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSJSRQ));
        // 如果税款所属的截止日期还是为空，则默认为上月的最后一天
        if (wynsksb.getSkssjsrq() == null)
        {
            // 税款所属截止日期，默认为上个月的最后一天
            wynsksb.setSkssjsrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSJSRQ));
        }

        return wynsksb;
    }

    private String getMessage(String msgName)
    {
        return TinyTools.toGBK(messages.getMessage(msgName));
    }

}