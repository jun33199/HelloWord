package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web;

/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Action</p>
 *
 * <p>Description: �������ǼǱ�ҳ��Ӧ���¼���Ӧ</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ApplicationException;


public class DztzAction extends SmsbAction
{

    public DztzAction()
    {
    }

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param actionForm
     *            QysdsnbForm
     * @param httpServletRequest
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */
    protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)
    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�۽���ҵ����˰����̨��</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�۽���ҵ����˰����̨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
    }

    /**
     * ��ʼ��ҳ������
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {

        DztzForm badjForm = new DztzForm();
        /**
         * ��ʼ��ҳ�������˵�
         */

        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);
        String tzxxHtml = DztzHelper.boToHtml(new DztzBO());
        request.setAttribute("QUERY_HTML", tzxxHtml);

        return mapping.findForward("Show");
    }


    public ActionForward doQueryTZXX(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
        BaseException
    {
        System.out.println("=========coming in to doQueryTZXX===========");
        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        DztzForm theForm = (DztzForm) form;
        try {
            //���ò�ѯ����
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION1);
            vo.setData(theForm.getJsjdm());
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_DZTZ_PROCESSOR);
            DztzBO bo = (DztzBO) SbzlProxy.getInstance().process(vo);
            String tzxxHtml = DztzHelper.boToHtml(bo);
            request.setAttribute("QUERY_HTML", tzxxHtml);

        }
        catch (Exception ex) {
            throw new ApplicationException("�����ǼǱ��ѯ��˰����Ϣ�����������Ա��ϵ��");
        }

        return mapping.findForward("Show");
    }


}
