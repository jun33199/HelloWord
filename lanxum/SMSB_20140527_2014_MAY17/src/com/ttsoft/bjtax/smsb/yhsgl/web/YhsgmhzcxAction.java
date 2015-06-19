/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>
 * Title: ������˰��������ϵͳ���������걨
 * </p>
 * <p>
 * Description: ����ӡ��˰����������� Action
 * </p>
 *
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmhzcxAction extends SmsbAction {
    UserData userData = null;

    protected void initialRequest(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        request
                .setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                              "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>����ӡ��˰�����������");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsgmhzcx-000.htm");
    }

    /**
     * ��ʾ����
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm aForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        try {
            YhsgmhzcxForm yForm = (YhsgmhzcxForm) aForm;
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            return mapping.findForward("Show");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ѯ����
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery(ActionMapping mapping, ActionForm aForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        VOPackage vo = new VOPackage();
        YhsgmhzcxForm yForm = (YhsgmhzcxForm) aForm;

        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMHZCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));

        try { // ת����ѯ
            yForm = (YhsgmhzcxForm) ZhsbProxy.getInstance().process(vo);
            // ��ǣ��Ǵ� ��ѯ �� ��ѯ
            yForm.setIsFromCx(false);
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Query");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��������
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks(ActionMapping mapping, ActionForm aForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsgmhzcxForm yForm = (YhsgmhzcxForm) aForm;

        vo.setAction(CodeConstant.SMSB_CXJKSACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMHZCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));
        try { // �������ɹ���ֱ��ת����ѯ
            yForm = (YhsgmhzcxForm) ZhsbProxy.getInstance().process(vo);
            // ��ǣ��Ǵ� ���� �� ��ѯ
            yForm.setIsFromCx(true);
            vo.setUserData(this.getUserData(request));
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.YHSGL_GMHZCX_PROCESSOR);
            vo.setData(yForm);
            yForm = (YhsgmhzcxForm) ZhsbProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), yForm);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "�����ɹ���");
            return mapping.findForward("Query");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���ش���
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doTurnback(ActionMapping mapping, ActionForm aForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws
            BaseException {
        try {
            // ����Ԥ��ҳ���� ת�� ӡ��˰������� ҳ��
            YhsgmhzForm yForm = new YhsgmhzForm();
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(userData.getYhid());
            yForm.setZsjgdm(userData.getSsdwdm());
            return mapping.findForward("Turnback");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ӡ����
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            YhsgmhzcxForm yForm = (YhsgmhzcxForm) form;
            // ��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            // ȡ�Ǽǽӿ�
            ServiceProxy proxy = new ServiceProxy();
            // ȡ��������Ϣ
            YHZH yhzh = new YHZH();
            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
            Map djMap = proxy.getDjInfo(dsJsjdm);
            if (djMap != null) {
                List yhList = (List) djMap.get("YHZH");
                if (yhList != null && yhList.size() > 0) {
                    yhzh = (YHZH) yhList.get(0);
                }
            }

            if (LWUtil.isZsjgLW(getServlet().getServletContext(), ud.getSsdwdm())) {
                //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
                JksqdPrintForm pf = new JksqdPrintForm();

                pf.setH_jsjdm(yForm.getDsjsjdm()); // ���۵�λ���������
                pf.setH_sbbh(yForm.getCxsbbh()); // �걨���
                pf.setSbbh(yForm.getCxsbbh());
                pf.setJsjdm(yForm.getDsjsjdm());

                pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //������Դ
                pf.setLrr(ud.getYhid()); //¼����
                pf.setYhdm(yhzh.getYhdm()); //���д���
                pf.setYhmc(yhzh.getKhyhmc()); //��������
                pf.setZh(yhzh.getZh()); //�����˺�
                pf.setSwjgzzjgdm(ud.getSsdwdm()); //˰�������֯��������
                pf.setActionType("Show");
                request.setAttribute("jksqdPrintForm", pf);
                return mapping.findForward("PrintJksqd");
            } else {
                //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
                JksPrintForm pf = new JksPrintForm();
                pf.setHeadJkpzh(yForm.getCxjkpzh());
                pf.setHeadJsjdm(yForm.getDsjsjdm());
                pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); // ������Դ:ӡ��˰����
                pf.setActionType("Show");
                request.setAttribute("jksPrintForm", pf);
            }
        } catch (Exception ex) {
        }
        return mapping.findForward("Print");
    }
}
