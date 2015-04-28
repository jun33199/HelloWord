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
 * Description:������ҵ����˰����
 * </p>
 *
 * @author li wenhua
 * @version 1.1
 */

public class CzqysdsjbAction extends SmsbAction {
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
	 *            CzqysdsjbForm
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
			CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) form;
			// ��ȡ���ɷ�ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����
			vo.setData(czqysdsjbForm);
			// ���ñ��ɷ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---CzqysdsjbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_CZQYSDS_PROCESSOR);

			// ����Proxy����ʼ��CzqysdsjbForm��ֵ
			czqysdsjbForm = (CzqysdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��HdzssdsjbForm ����request��
			request.setAttribute(mapping.getAttribute(), czqysdsjbForm);

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
		// ��ȡCzqysdsjbForm����
		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ���ñ���������
		czqysdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����˰����������
		// czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
		// ����¼��������
		czqysdsjbForm.setLrr(userData.getYhid());

		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������CzqysdsjbForm
		vo.setData(czqysdsjbForm);
		// ����ProxyҪ���õ�processor����---CzqysdsjbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_CZQYSDS_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(czqysdsjbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject2, checkBean);
			
			// ����Proxy��ִ��processor,��ȡczqysdsjbForm����ֵ
			czqysdsjbForm = (CzqysdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��czqysdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
			// �����ɹ���ת
			return mapping.findForward("Show");

		} catch (Exception ex) {

			czqysdsjbForm.reset(mapping,request);
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
		// ��ȡCzqysdsjbForm����
		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		czqysdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czqysdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
        System.out.println("length = " + czqysdsjbForm.getColumns().length);
        System.out.println("Column[0] = " + czqysdsjbForm.getColumns()[0]);
        System.out.println("Column[1] = " + czqysdsjbForm.getColumns()[1]);
		czqysdsjbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
				czqysdsjbForm.getColumns()));
        System.out.println("list size = " + czqysdsjbForm.getQysdsjbList().size());

		// ��czqysdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czqysdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_CZQYSDS_PROCESSOR);

		try {
			// ����Proxy��ִ��processor,��ȡczqysdsjbForm����ֵ
			czqysdsjbForm = (CzqysdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��czqysdsjbForm�еĻ��������ÿ�
			czqysdsjbForm.reset(mapping, request);
			// ��czqysdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
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
	 *            CzqysdsjbForm
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
		// ��ȡCzqysdsjbForm����
		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		czqysdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czqysdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
		czqysdsjbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
				czqysdsjbForm.getColumns()));
		// ��czqysdsjbForm����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(czqysdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_CZQYSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡczqysdsjbForm����ֵ
			czqysdsjbForm = (CzqysdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
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

		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡCzqysdsjbForm����
		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) form;
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList�У�
		czqysdsjbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
				czqysdsjbForm.getColumns()));
		// ��czqysdsjbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
		// ����¼��������
		czqysdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czqysdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(czqysdsjbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_CZQYSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡczqysdsjbForm����ֵ
			czqysdsjbForm = (CzqysdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��czqysdsjbForm�еĻ��������ÿ�
			czqysdsjbForm.reset(mapping, request);
			// ��czqysdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czqysdsjbForm);
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
	private CheckBean setCheckInf(CzqysdsjbForm czqysdsjbForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//У��汾�Ĳ���
		checkBean.setCurrentTime(czqysdsjbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2006");
		checkBean.setVersionEndTime("2007");
		
		//�����ڵĲ���
		checkBean.setJsjdm(czqysdsjbForm.getJsjdm());
		
		return checkBean;
	}
}
