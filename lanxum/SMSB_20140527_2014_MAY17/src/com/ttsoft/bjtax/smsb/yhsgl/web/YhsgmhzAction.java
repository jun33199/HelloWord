/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;


import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰������� Action</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmhzAction extends SmsbAction {
    UserData userData = null;

    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>����ӡ��˰�������");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsgmhz-000.htm");
    }

    /**
     * ��ʾ����
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm aForm, HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        try {
            //����ҳ�����趨��������
            YhsgmhzForm yForm = (YhsgmhzForm) aForm;
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            // ȡ�Ǽǽӿ�
//            ServiceProxy proxy = new ServiceProxy();
//            // ȡ��������Ϣ
//            YHZH yhzh = new YHZH();
//            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//                    "swjgzzjgdm", userData.getSsdwdm(), "jsjdm");
//            Map djMap = proxy.getDjInfo(dsJsjdm);
//            if (djMap != null) {
//                List yhList = (List) djMap.get("YHZH");
//                if (yhList != null && yhList.size() > 0) {
//                    yhzh = (YHZH) yhList.get(0);
//                }
//            }
            // �ж��Ƿ�����
            if(LWUtil.isZsjgLW(getServlet().getServletContext(),
                                      userData.getSsdwdm()))
            {
                yForm.setLw("01");
            }
            else
            {
                yForm.setLw("00");
            }
            yForm.setLrr(userData.getYhid());
            yForm.setZsjgdm(userData.getSsdwdm());
            yForm.setHzqsrq(SfDateUtil.getDate());
            yForm.setHzjsrq(SfDateUtil.getDate());
            return mapping.findForward("Show");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���ܴ���
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doHzjks(ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsgmhzForm yForm = (YhsgmhzForm) aForm;

        vo.setAction(CodeConstant.SMSB_HZJKSACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMHZ_PROCESSOR);
        vo.setData(yForm);

        try { //����processor
            vo.setUserData(this.getUserData(request));
            yForm = (YhsgmhzForm) ZhsbProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Hzjks");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��������
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks(ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            //��ת
            YhsgmhzcxForm yForm = new YhsgmhzcxForm();
            yForm.reset(mapping, request);
            return mapping.findForward("Cxjks");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ӡ����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        YhsgmhzForm yForm = (YhsgmhzForm) form;
        userData = this.getUserData(request);
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

            pf.setH_jsjdm(yForm.getDsjsjdm()); // ���۵�λ���������
            pf.setH_sbbh(yForm.getSbbh()); // �걨���
            pf.setSbbh(yForm.getSbbh());
            pf.setJsjdm(yForm.getDsjsjdm());

            pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //������Դ:ӡ��˰����
//            pf.setHjjexx(yForm.getSjje());//�ϼƽ��Сд
//            pf.setHjjedx(Currency.convert(Double.parseDouble(yForm.getSjje())));//�ϼƽ���д
            pf.setSkje(yForm.getSjje()); //�տ���
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
            pf.setHeadJkpzh(yForm.getJkpzh());
            pf.setHeadJsjdm(yForm.getDsjsjdm());
            pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //������Դ:ӡ��˰����
            pf.setActionType("Show");
            request.setAttribute("jksPrintForm", pf);
            return mapping.findForward("Print");
        }
    }
}
