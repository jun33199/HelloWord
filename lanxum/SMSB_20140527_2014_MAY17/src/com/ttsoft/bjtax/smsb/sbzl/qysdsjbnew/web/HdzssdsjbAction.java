/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.web.CzzssdsjbForm;
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
 * Description:�˶�������ҵ����˰����
 * </p>
 * 
 * @author li wenhua
 * @version 1.1
 */

public class HdzssdsjbAction extends SmsbAction {
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
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			// ��ȡHdzssdsjbForm����
			HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) form;
			// ��ȡ���ɷ�ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			hdzssdsjbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������HdzssdsjbForm
			vo.setData(hdzssdsjbForm);
			// ���ñ��ɷ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR);
			// ����Proxy����ʼ��HdzssdsjbForm��ֵ
			hdzssdsjbForm = (HdzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��HdzssdsjbForm ����request��
			request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);

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
	 *            HdzssdsjbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ���ñ���������
		hdzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����˰����������
		// hdzssdsjbForm.setSwjsjdm(userData.getXtsbm1());
		// ����¼��������
		hdzssdsjbForm.setLrr(userData.getYhid());
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������HdzssdsjbForm
		vo.setData(hdzssdsjbForm);
		// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(hdzssdsjbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject2, checkBean);
			
			// ����Proxy��ִ��processor,��ȡhdzssdsjbForm����ֵ
			hdzssdsjbForm = (HdzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
			// �����ɹ���ת
			return mapping.findForward("Show");
		} catch (Exception ex) {
			hdzssdsjbForm.reset(mapping,request);
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
	 *            HdzssdsjbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		hdzssdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hdzssdsjbForm��DataList�У�
		hdzssdsjbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
				hdzssdsjbForm.getColumns()));
		// ��hdzssdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(hdzssdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsjbForm����ֵ
			hdzssdsjbForm = (HdzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsjbForm�еĻ��������ÿ�
			hdzssdsjbForm.reset(mapping, request);			
			// ��hdzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
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
	 *            ZcmxbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		hdzssdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hdzssdsjbForm��DataList�У�
		hdzssdsjbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
				hdzssdsjbForm.getColumns()));
		// ��hdzssdsjbForm����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(hdzssdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsjbForm����ֵ
			hdzssdsjbForm = (HdzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
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
	 *            HdzssdsjbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) form;
		// ����¼��������
		hdzssdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hdzssdsjbForm��DataList�У�
		hdzssdsjbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
				hdzssdsjbForm.getColumns()));
		// ��hdzssdsjbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(hdzssdsjbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsjbForm����ֵ
			hdzssdsjbForm = (HdzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsjbForm�еĻ��������ÿ�
			hdzssdsjbForm.reset(mapping, request);
			// ��hdzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsjbForm);
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
	private CheckBean setCheckInf(HdzssdsjbForm hdzssdsjbForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//У��汾����
		checkBean.setCurrentTime(hdzssdsjbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2006");
		checkBean.setVersionEndTime("2007");
		
		//У�������ڵĲ���
		checkBean.setJsjdm(hdzssdsjbForm.getJsjdm());
		
		return checkBean;
	}

}