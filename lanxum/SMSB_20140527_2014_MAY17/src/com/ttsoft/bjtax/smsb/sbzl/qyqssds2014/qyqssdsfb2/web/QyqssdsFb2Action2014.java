package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsFb2Action2014   
 * ��������   ���������ծ�峥������ϸ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����3:29:14   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����3:29:14   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsFb2Action2014 extends SmsbAction {
	/**
	 * ����Ա����
	 */
	private UserData userData = null;

	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QyqssdsBaseForm
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
				"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ��������˰�걨</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ��������˰�걨");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssds-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
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
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);
		VOPackage vo = new VOPackage();
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");

		try {
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
		} catch (Exception ex) {
			fb2Form.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}

	/**
	 * �����걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
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
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);
		fb2Form.setDataList(SfRequestUtil.getValuesToList(request,fb2Form.getSb_columns1()));
		VOPackage vo = new VOPackage();
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");
		request.setAttribute(mapping.getAttribute(), fb2Form);
		try {
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
		return mapping.findForward("Show");
	}

	/**
	 * ������ݣ����ͨ������б���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);
		fb2Form.setDataList(SfRequestUtil.getValuesToList(request,fb2Form.getSb_columns1()));
		request.setAttribute("ActionForm", fb2Form);
		VOPackage vo = new VOPackage();
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");
		try {
			// ����processor
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance()
					.process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
			if (fb2Form.getCheckList() == null) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "У��ͨ����");
			} else if (fb2Form.getCheckList().size() == 0) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "У��ͨ����");
			} else if (fb2Form.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,QyqssdsUtil2014.getCheckResults(fb2Form.getCheckList()));
			}
			System.out.println("------------Action_doCheck----------------");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}

	/**
	 * ɾ���걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
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
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);

		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");
		try {
			// ����processor
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
		return mapping.findForward("Show");
	}

	/**
	 * �˳�ҳ��
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaseForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doExit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return mapping.findForward("Return");
	}

	/**
	 * ��session�л�ȡ������Ϣ
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, QyqssdsFb2Form2014 form) {
		userData = this.getUserData(request);
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);

		String ksrq = request.getParameter("skksrq");
		String jsrq = request.getParameter("skjsrq");

		if ((ksrq != null && !"".equals(ksrq))
				&& (jsrq != null && !"".equals(jsrq))) {
			request.getSession(false).setAttribute(
					CodeConstant.SESSIONKEY_QYQSSDSBASEFORM, baseForm);
		}

		if (baseForm != null) {
			form.setJsjdm(baseForm.getJsjdm());
			form.setTbrq(baseForm.getTbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			form.setQssbksrq(baseForm.getQssbksrq());
			form.setQssbjsrq(baseForm.getQssbjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			
			//-----�걨���״̬
			form.setSbShztbs(baseForm.getSbShztbs());
			//-----��������
			form.setSqlx(baseForm.getSqlx());
			
			form.setLrr(userData.getYhid());
			form.setNextTableURL(QyqssdsUtil2014.getTableURL(baseForm, "N_" + CodeConstant.QYQSSDS_TABLE_ID_2014_2));
			form.setPreviousTableURL(QyqssdsUtil2014.getTableURL(baseForm, "P_" + CodeConstant.QYQSSDS_TABLE_ID_2014_2));
		}
	}

}
