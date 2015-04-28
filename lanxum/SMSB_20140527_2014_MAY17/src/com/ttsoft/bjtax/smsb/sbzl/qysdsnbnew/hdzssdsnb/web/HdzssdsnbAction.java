/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.web.HdzssdsjbForm;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:�˶�������ҵ����˰�걨
 * </p>
 * 
 * @author Li Wenhua
 * @version 1.1
 */

public class HdzssdsnbAction extends SmsbAction {

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
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ����˰�����˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
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

		try {
			// ��ȡHdzssdsnbForm����
			HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
			// ��ȡ���ɷ�ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			hdzssdsnbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������HdzssdsnbForm
			vo.setData(hdzssdsnbForm);
			// ���ñ��ɷ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---HdzssdsnbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_HDZSSDSNB_PROCESSOR);
			// ����Proxy����ʼ��HdzssdsnbForm��ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��HdzssdsjbForm ����request��
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ��ѯ���걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsnbForm
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

		// ��ȡCzqysdsjbForm����
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����˰����������
		// hdzssdsjbForm.setSwjsjdm(userData.getXtsbm1());
		// ����¼��������
		hdzssdsnbForm.setLrr(userData.getYhid());
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������hdzssdsnbForm
		vo.setData(hdzssdsnbForm);
		// ����ProxyҪ���õ�processor����---HdzssdsnbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_HDZSSDSNB_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(hdzssdsnbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject2, checkBean);
			
			// ����Proxy��ִ��processor,��ȡhdzssdsnbForm����ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��czqysdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// �����ɹ���ת
			return mapping.findForward("Show");
		} catch (Exception ex) {
			
			hdzssdsnbForm.reset(mapping,request);
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
	 *            HdzssdsnbForm
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
		// ��ȡHdzssdsnbForm����
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		hdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hdzssdsnbForm��DataList��
		hdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// ��hdzssdsnbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(hdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_HDZSSDSNB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsnbForm����ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsnbForm�еĻ��������ÿ�
			hdzssdsnbForm.reset(mapping, request);
			// ��czqysdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ������ݣ����ͨ������б���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsnbForm
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
		// ��ȡHdzssdsnbForm����
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		hdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
		hdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// ��hdzssdsnbForm����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(hdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_HDZSSDSNB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsnbForm����ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsnbForm�еĻ��������ÿ�
			hdzssdsnbForm.reset(mapping, request);
			// ��czqysdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// �����ɹ���ת
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
	 *            HdzssdsnbForm
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
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡHdzssdsnbForm����
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ����¼��������
		hdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
		hdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// ��hdzssdsnbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(hdzssdsnbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_HDZSSDSNB_PROCESSOR);
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsnbForm����ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsnbForm�еĻ��������ÿ�
			hdzssdsnbForm.reset(mapping, request);
			// ��hdzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");

			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * @Description: TODO ����У�����
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(HdzssdsnbForm hdzssdsnbForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//У��汾����
		checkBean.setCurrentTime(hdzssdsnbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2006");
		checkBean.setVersionEndTime("2007");
		
		//У�������ڵĲ���
		checkBean.setJsjdm(hdzssdsnbForm.getJsjdm());
		
		return checkBean;
	}
}