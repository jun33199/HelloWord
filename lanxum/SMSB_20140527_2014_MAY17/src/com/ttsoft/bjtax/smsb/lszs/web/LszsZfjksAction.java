/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ�������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ�������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshJksYpdsForm;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshJksYpysForm;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import java.util.List;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ˰����������˰֤���ܵĽɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsZfjksAction extends SmsbAction {

    /**
     * ������ǰ�ô���������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">��ɢ˰����</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "���Ͻɿ���");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/jkscx-000.htm");
    }

    /**
     * ҳ���ʼ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        LszsZfjksForm pf = (LszsZfjksForm) form;
        pf.setHzfs("1"); //Ĭ�ϵĲ�ѯ��ʽ
        try {
            //���Ԥװ�ص���Ϣ
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            // ȡ�Ǽǽӿ�
//            ServiceProxy proxy = new ServiceProxy();
//            // ȡ��������Ϣ
//            YHZH yhzh = new YHZH();
//            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
//            Map djMap = proxy.getDjInfo(dsJsjdm);
//            if (djMap != null) {
//                List yhList = (List) djMap.get("YHZH");
//                if (yhList != null && yhList.size() > 0) {
//                    yhzh = (YHZH) yhList.get(0);
//                }
//            }
            // �����Ƿ�����,�жϴ�ӡ�ɿ�����߽ɿ����뵥
            if (LWUtil.isZsjgLW(getServlet().getServletContext(), ud.getSsdwdm())) {
                pf.setLw("01");
            } else {
                pf.setLw("00");
            }
            pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //��ñ����ļ��������

            //pf.se
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);
            //����processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Show");
    }

    /**
     * ��ѯ
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {

        UserData ud = this.getUserData(request);
        LszsZfjksForm pf = (LszsZfjksForm) form;
        try {
            //
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);
            //����processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }

    /**
     * ɾ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        LszsZfjksForm pf = (LszsZfjksForm) form;
        //��Ԥװ�ص���Ϣ��ò���д
        try {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_DELETEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);

            //����processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);
            //���½��в�ѯ
            vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);
            //����processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);

            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ϳɹ���");
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        pf.reset(mapping, request);
        return mapping.findForward("Delete");
    }

    /**
     * һƱһ˰
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doYpys(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        try {
            LszsZfjksForm pf1 = (LszsZfjksForm) form;
            GtgshJksYpysForm pf = new GtgshJksYpysForm();
            pf.setActionType("Query");
            pf.setJkpzh(pf1.getJkpzh());
            pf.setHzlx(pf1.getHzfs()); //���ܷ�ʽ����������
            pf.setGtgshJsjdm(pf1.getJsjdm()); //���������
            pf.setFhbs("LszsZf"); //���÷��ر�ʶ
            request.setAttribute("gtgshJksYpysForm", pf);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Ypys");
    }

    /**
     * һƱ��˰
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doYpds(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        try {
            LszsZfjksForm pf1 = (LszsZfjksForm) form;
            GtgshJksYpdsForm pf = new GtgshJksYpdsForm();
            pf.setActionType("Query");
            pf.setJkpzh(pf1.getJkpzh());
            pf.setHzlx(pf1.getHzfs()); //���ܷ�ʽ����������
            pf.setGtgshJsjdm(pf1.getJsjdm()); //���������
            pf.setFhbs("LszsZf"); //���÷��ر�ʶ
            request.setAttribute("gtgshJksYpdsForm", pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Ypds");
    }

    /**
     * ��ӡ
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            LszsZfjksForm zfpf = (LszsZfjksForm) form;
            UserData userData = this.getUserData(request);
            // ȡ�Ǽǽӿ�
            ServiceProxy proxy = new ServiceProxy();
            // ȡ��������Ϣ
            YHZH yhzh = new YHZH();
            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", userData.getSsdwdm(), "jsjdm");
            Map djMap = proxy.getDjInfo(dsJsjdm);
            if (djMap != null) {
                List yhList = (List) djMap.get("YHZH");
                if (yhList != null && yhList.size() > 0) {
                    yhzh = (YHZH) yhList.get(0);
                }
            }
            // �����Ƿ�����,�жϴ�ӡ�ɿ�����߽ɿ����뵥
            if (LWUtil.isZsjgLW(getServlet().getServletContext(),
                                userData.getSsdwdm())) {

                //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
                JksqdPrintForm pf = new JksqdPrintForm();

                pf.setH_jsjdm(zfpf.getJsjdm()); // ���۵�λ���������
                pf.setH_sbbh(zfpf.getJkpzh()); // �걨���
                pf.setSbbh(zfpf.getJkpzh());
                System.out.println(zfpf.getSbbh());
                System.out.println("sbbh================="+pf.getSbbh());
                pf.setJsjdm(zfpf.getJsjdm());

                pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSHZ); //������Դ
                pf.setLrr(userData.getYhid()); //¼����
                pf.setYhdm(yhzh.getYhdm()); //���д���
                pf.setYhmc(yhzh.getKhyhmc()); //��������
                pf.setZh(yhzh.getZh()); //�����˺�
                pf.setSwjgzzjgdm(userData.getSsdwdm()); //˰�������֯��������
                pf.setActionType("Show");

                request.setAttribute("jksqdPrintForm", pf);
                return mapping.findForward("PrintJksqd");
            } else {
                //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
                JksPrintForm pf = new JksPrintForm();
                LszsZfjksForm pf1 = (LszsZfjksForm) form;
                pf.setHeadJkpzh(pf1.getJkpzh());
                pf.setHeadJsjdm(pf1.getJsjdm());
                pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSHZ); //������Դ
                pf.setActionType("Show");
                request.setAttribute("jksPrintForm", pf);
            }
            } catch (Exception ex) {
        }
        return mapping.findForward("Print");
    }
}