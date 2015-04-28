package com.ttsoft.bjtax.shenbao.wsksb.web;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 ��һ���Źɷ����޹�˾����Ȩ����.</p>
 * <p>Company: ��һ���Źɷ����޹�˾</p>
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
     * ��ȡ��˰�˵���˰�걨���ݲ���ʾ�걨ҳ��
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
            // ȡ�õǼ�����
            Map djMap = (Map) FriendHelper.getDjInfo(request);
            UserData userData = getUserData(request);
            // ��ü��������
            String jsjdm = userData.yhid;
            // ���ü��������
            oForm.setJsjdm(jsjdm);

            SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

            // ȡ˰�ѹ�������
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            Calendar cc = new GregorianCalendar();
            cc.add(Calendar.MONTH, -1);
            int maxday = cc.getActualMaximum(Calendar.DAY_OF_MONTH);
            cc.set(Calendar.DAY_OF_MONTH, maxday);

            // �ϸ������һ��
            Date dd = cc.getTime();
            List dqdeList = sfglProxy.getYnsje(jsjdm, dd, dd);
            boolean dqde = false;
            for (int i = 0; i < dqdeList.size(); i++)
            {
                Dqdedlmx1 dqdedl = (Dqdedlmx1) dqdeList.get(i);
                if (dqdedl != null)
                {
                    String zsfsdm = dqdedl.getZsfsdm();
                    if (zsfsdm != null && zsfsdm.equals("01")) // ��������
                    {
                        dqde = true;
                        break;
                    }
                }
            }

            // �����걨���ڣ���ǰʱ�䣩
            Date now = new Date();
            List msgList = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // ����form�е�����
            oForm.setNsrmc(jbsj.getNsrmc()); // ������˰������
            oForm.setSbrq(sdf.format(now));
            oForm.setLxdh(jbsj.getJydzlxdm());
            oForm.setNd((new SimpleDateFormat("yyyy")).format(now));
            oForm.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            String skssksrq = sdf.format((Timestamp) Skssrq.monthSkssrq(now).get(Skssrq.SKSSKSRQ));
            String skssjsrq = sdf.format((Timestamp) Skssrq.monthSkssrq(now).get(Skssrq.SKSSJSRQ));
            oForm.setSkssksrq(skssksrq);
            oForm.setSkssjsrq(skssjsrq);
            // �����ѯ������ѯ��̨

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
            // ������!!!!!
            // withinzq = true;//������

            if (wynsksb != null)
            {
                oForm.setSbrq(sdf.format(wynsksb.getSbrq()));
                oForm.setBz(wynsksb.getBz());
                oForm.setSbbh(wynsksb.getSbbh());
            }

            // ���ڶ������շ�ʽ����Ҫ����Ӧ��˰���걨
            if (dqde)
            {
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.dqde"));
            }
            else if (!withinzq)// �����ѹ����й��걨������������˰�������ϵ
            {
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.zqyg"));
            }
            else if (wynsksb == null)
            {// ���������û��˰��뵥�����水ť������Ӧ��˰���걨
                oForm.setCanDel(CANNOTDO);
                oForm.setCanSave(CANDO);
                msgList.add(getMessage("message.web.wsksb.cansave"));
                // �������Ѿ�������Ӧ��˰���걨,���Ե���ɾ����ť��ɾ��������Ӧ��˰���걨��
            }
            else if (wynsksb.getFsdm() != null && wynsksb.getFsdm().equals(CodeConstant.FSDM_WSSB))
            {
                oForm.setCanDel(CANDO);
                oForm.setCanSave(CANNOTDO);
                msgList.add(getMessage("message.web.wsksb.candel"));
                // �������Ѿ�������Ӧ��˰���걨,����
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
     * ɾ����˰�˵���˰�걨����
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
                // ȡ�õǼ�����

                // started added by qianchao 2005-11-24
                DzyjsjVO dzyj = null;

                String strOrginData = request.getParameter("SecX_OrginData");
                String signData = request.getParameter("SecX_SignData");
                if (ud.getCaflag())
                {
                    // /���ǩ��������
                    String SecX_Error = request.getParameter("SecX_Error");
                    if (!"0".equals(SecX_Error))
                    {
                        String tempstr;
                        tempstr = "������ǩ������!Error:" + SecX_Error
                        + " SecX_OD " + ud.getYhid() + ":" + strOrginData
                        + "-----SecX_SD:" + signData  + "-----";
                        System.out.println(tempstr);
                        ActionErrors errors = new ActionErrors();
                        errors.add("", new ActionError("error.server.custom", "������ǩ������Error:" + SecX_Error));
                        saveErrors(request, errors);
                        return (new ActionForward(mapping.getInput()));
                    }
                    System.out.println("============����ǩ�����ݿ�ʼ==============");
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
                    System.out.println("============����ǩ�����ݽ���==============");
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
                LogUtil.getInstance().log(FriendHelper.getUserData(request), "��˰�걨ɾ��",
                        (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
                return mapping.findForward("Delete");
            }
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            // started added by qianchao 2005-11-24

            // if (ud.getCaflag() && (calsh != 0))
            // {
            // System.out.println("============����ɾ��ǩ����ʼ=============="
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
            // System.out.println("============����ɾ��ǩ������=============="
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
     * ������˰�˵���˰�걨����
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
        // ���token
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
                tempstr = "������ǩ������!Error:" + SecX_Error
                + " SecX_OD " + ud.getYhid() + ":" + strOrginData
                + "-----SecX_SD:" + signData  + "-----";
                System.out.println(tempstr);
                ActionErrors errors = new ActionErrors();
                errors.add("", new ActionError("error.server.custom", "������ǩ������Error:" + SecX_Error));
                saveErrors(request, errors);
                return (new ActionForward(mapping.getInput()));
            }
            System.out.println("============����ǩ�����ݿ�ʼ==============");
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
            System.out.println("============����ǩ�����ݽ���==============");
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
                // ȡ�õǼ�����
                Map djMap = (Map) FriendHelper.getDjInfo(request);
                SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

                Timestamp now = new Timestamp(System.currentTimeMillis());
                Wynsksb wynsksb = getWynsksb(jbsj, oForm, now);
                // �����ѯ������ѯ��̨
                VOPackage voPackage = new VOPackage();
                voPackage.setUserData(ud);

                HashMap hm = new HashMap();
                hm.put(ZhsbMapConstant.SBSJ, wynsksb);
                hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

                voPackage.setData(hm);
                voPackage.setAction(SessionKey.INT_ACTION_SAVE);
                voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);

                //start added code by qianchao 2006-2-11
                //�����걨����޷����أ�����ҳ��û���걨��ţ���������ɾ�������
                //ShenbaoProxy.getInstance().process(voPackage)
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
                // wynsksb = (Wynsksb)voPackage.getData();
                wynsksb = (Wynsksb)(((HashMap)voPackage.getData()).get(ZhsbMapConstant.SBSJ));
                //end   added code by qianchao 2006-2-11
                
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // ����form�е�����
                oForm.setNsrmc(jbsj.getNsrmc()); // ������˰������
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
                LogUtil.getInstance().log(FriendHelper.getUserData(request), "��˰�걨����",
                        (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");

            }

            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            // started added by qianchao 2006-2-11

            if (ud.getCaflag() && (dzyj != null))
            {
                System.out.println("============����ɾ��ǩ����ʼ==============" + dzyj.getLsh());
                try
                {
                    CAProxy.getInstance().deleteSignedData(dzyj);
                }
                catch (ApplicationException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("============����ɾ��ǩ������==============" + dzyj.getLsh());
            }

            // ended added by qianchao 2006-2-11
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    // �жϵ�ǰ�����Ƿ���������
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

    // ��WsksbForm���ݹ���Wynsksb����
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
        // ���˰�������Ľ�ֹ���ڻ���Ϊ�գ���Ĭ��Ϊ���µ����һ��
        if (wynsksb.getSkssjsrq() == null)
        {
            // ˰��������ֹ���ڣ�Ĭ��Ϊ�ϸ��µ����һ��
            wynsksb.setSkssjsrq((Timestamp) Skssrq.monthSkssrq(new Date(currentTime)).get(Skssrq.SKSSJSRQ));
        }

        return wynsksb;
    }

    private String getMessage(String msgName)
    {
        return TinyTools.toGBK(messages.getMessage(msgName));
    }

}