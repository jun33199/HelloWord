package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class QyqsSdsWxBaAction extends SmsbAction {

	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QyqssdsWxBaForm
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
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ����˰�������㱸����</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,"��ҵ����˰�������㱸����");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,"help/smsb/sbzl/qyqssds/qyqssdsba-000.htm");
	}
	
	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsWxBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) form;
		qyqssdsWxBaForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsWxBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.processor.QyqssdsWxBaProcessor");
		vo.setUserData(userData);
		try {
			//����process
			qyqssdsWxBaForm = (QyqssdsWxBaForm) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			qyqssdsWxBaForm.setTbrq(sdf.format(curTime));
			qyqssdsWxBaForm.setQsbaksrq(sdf.format(curTime));
			//������Ĭ��ѡ�з�
			qyqssdsWxBaForm.setSfwxjxba("1");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsWxBaForm);
		return mapping.findForward("Show");
	}
	
	/**
	 * ������Ϣ
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsWxBaForm
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
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(qyqssdsWxBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.processor.QyqssdsWxBaProcessor");
		vo.setUserData(userData);
		try {
			//����process
			qyqssdsWxBaForm = (QyqssdsWxBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsWxBaForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
		return mapping.findForward("Query");
	}
	
	/**
	 * ¼�����������ѯ�����Ϣ
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsWxBaForm
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
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsWxBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.processor.QyqssdsWxBaProcessor");
		vo.setUserData(userData);
		
		try {
			//����process
			qyqssdsWxBaForm = (QyqssdsWxBaForm) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			qyqssdsWxBaForm.setTbrq(sdf.format(curTime));
			qyqssdsWxBaForm.setQsbaksrq(sdf.format(curTime));
			qyqssdsWxBaForm.setIsQuery("1");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsWxBaForm);
		return mapping.findForward("Show");
	}

	
	/**
	 * ɾ����¼
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsWxBaForm
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
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(7);
		vo.setData(qyqssdsWxBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.processor.QyqssdsWxBaProcessor");
		vo.setUserData(userData);
		
		try {
			//����process
			qyqssdsWxBaForm = (QyqssdsWxBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsWxBaForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
		return mapping.findForward("Query");
	}

	/**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDS_WXBA_QUERY);
	}
}
