package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��-��ҵ����˰����2014��-������˰��֧���������</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author zhangyj
 * @version 1.0
 */

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class ZfjgqysdsjbAction extends SmsbAction
{
    public ZfjgqysdsjbAction()
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
            HttpServletRequest httpServletRequest, HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;<font color=\"#7C9AAB\">������˰��֧���������</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                "������˰��֧���������");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }

    /**
     * ��ʼ��ҳ������
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */

/*    public ActionForward doShow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {

        try {
            // ��ȡZfjgqysdsjbForm����
            ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
            // ��ȡ���ɷ�ϵͳ�û���Ϣ
            UserData userData = this.getUserData(request);
            // ��ʼ�����ݴ�������
            VOPackage vo = new VOPackage();
            // ���ú�̨����Actionֵ---SHOWACTION
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            // �����������Data����
            vo.setData(zfjgForm);
            // ���ñ��ɷ�ϵͳ�û���Ϣ
            vo.setUserData(userData);
            // ����ProxyҪ���õ�processor����---ZfjgqysdsjbProcessor
            vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);

            // ����Proxy����ʼ��CzqysdsjbForm��ֵ
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // ��HdzssdsjbForm ����request��
            System.out.println("mapping.getAttribute() = " + mapping.getAttribute());
            request.setAttribute(mapping.getAttribute(), zfjgForm);

            return mapping.findForward("Show");

        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }*/

    /**
     * ��ѯ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doQuery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
    	
//    	CzzssdsjbForm czzssdsjbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjbForm");
    	
        // ��ȡZfjgqysdsjbForm����
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ���ñ���������-����
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // ����˰����������
        // czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());

        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ���ú�̨����Actionֵ---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // �����������Data����,������ZfjgqysdsjbForm
        vo.setData(zfjgForm);
        // ����ProxyҪ���õ�processor����---ZfjgqysdsjbProcessor
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);
        // ����ϵͳ�û���Ϣ
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // ��czqysdsjbForm����request,��Ϊ����������
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            // �����ɹ���ת
            return mapping.findForward("Show");

        } catch (Exception ex) {

            zfjgForm.reset(mapping,request);
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {

        // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // ��ȡZfjgqysdsjbForm����
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());
        // ���ñ���������
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
        System.out.println("length = " + zfjgForm.getFzjgColumns().length);
        System.out.println("Column[0] = " + zfjgForm.getFzjgColumns()[0]);
        System.out.println("Column[1] = " + zfjgForm.getFzjgColumns()[1]);
        zfjgForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
                zfjgForm.getFzjgColumns()));
        System.out.println("list size = " + zfjgForm.getQysdsjbList().size());
        
        System.out.println("nsrmc = " + zfjgForm.getNsrmc());

        // ��czqysdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ������������
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(zfjgForm);
        vo.setUserData(userData);
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);

        try {
            // ����Proxy��ִ��processor,��ȡczqysdsjbForm����ֵ
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // ��czqysdsjbForm�еĻ��������ÿ�
//            zfjgForm.reset(mapping, request);
            // ��czqysdsjbForm����request,��Ϊ����������
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            // �����ɹ���ת
            
            zfjgForm.setSAVE_FLAG("1");//1��ʾ����ɹ���0��ʶ����ʧ�ܣ�added by zhangj 2014.11.28
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            return mapping.findForward("Show");
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
    	 System.out.println("doDelete ============Action ");
    	
        // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡZfjgqysdsjbForm����
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
        // ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����zfjgForm��DataList�У�
        zfjgForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
                zfjgForm.getFzjgColumns()));
        // ��zfjgForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());
        // ���ñ���������
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ������������
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(zfjgForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // ��zfjgForm�еĻ��������ÿ�
//            zfjgForm.reset(mapping, request);
//            // ��zfjgForm����request,��Ϊ����������
//            request.setAttribute(mapping.getAttribute(), zfjgForm);
//            // �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
//            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
//            //this.doJump(mapping, zfjgForm, request, response);
//            return mapping.findForward("Show");
            
			System.out.println("I am jumping to Czzssdsjb....");
			// ��ȡCzzssdsjbForm����
			CzzssdsjbForm CzzssdsjbForm = new CzzssdsjbForm();

			// ��CzzssdsjbForm ����request��
			//request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			//

			CzzssdsjbForm.setJsjdm(zfjgForm.getJsjdm());
			request.setAttribute("czzssdsjbForm", CzzssdsjbForm);
			return mapping.findForward("Jump");
            
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ��תҳ������
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
    public ActionForward doJump(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException
    {
        try {
            System.out.println("I am jumping....");
            // ��ȡZfjgqysdsjbForm����
            ZfjgqysdsjbForm zfjgform = (ZfjgqysdsjbForm) form;
            String jsjdm = zfjgform.getJsjdm();
            String jumpStr = null;
            // ��CzzssdsjbForm ����request��
            CzzssdsjbForm czzsForm=new CzzssdsjbForm();
            if(jsjdm != null && !jsjdm.equals(""))
            {
                czzsForm.setJsjdm(jsjdm);
                jumpStr = "Query";
            }
            else
            {
                jumpStr = "Jump";
            }
            request.setAttribute("czzssdsjb2014Form", czzsForm);
            System.out.println("jumpStr = " + jumpStr);
            return mapping.findForward(jumpStr);
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    public ActionForward doJumpGdzcjszjyjqk(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException
    {
        try {

            // ��ȡZfjgqysdsjbForm����
            ZfjgqysdsjbForm zfjgform = (ZfjgqysdsjbForm) form;
//            String jsjdm = zfjgform.getJsjdm();
//            String jumpStr = null;
//            
//            GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm=new GdzcjszjyjqkjbForm();
////            if(jsjdm != null && !jsjdm.equals(""))
////            {
//            	gdzcjszjyjqkjbForm.setJsjdm(jsjdm);
//            	gdzcjszjyjqkjbForm.setNsrsbh(zfjgform.getNsrsbh());
//            	gdzcjszjyjqkjbForm.setNsrmc(zfjgform.getNsrmc());
//            	gdzcjszjyjqkjbForm.setSbrq(zfjgform.getSbrq());
//            	gdzcjszjyjqkjbForm.setSkssksrq(zfjgform.getSkssksrq());
//            	gdzcjszjyjqkjbForm.setSkssjsrq(zfjgform.getSkssjsrq());
//            	gdzcjszjyjqkjbForm.setSshy(zfjgform.getSshy());
////                jumpStr = "Query";
////            }
////            else
////            {
////                jumpStr = "JumpGdzcjszjyjqk";
////            }
//            request.setAttribute("gdzcjszjyjqkjbForm2014", gdzcjszjyjqkjbForm);
////            System.out.println("jumpStr = " + jumpStr);
            return mapping.findForward("JumpGdzcjszjyjqk");
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }  
    
}
