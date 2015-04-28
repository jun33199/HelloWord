/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbsydw.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:����һ��������ҵ��λ��������塢������ҵ��λ������Ŀ��ϸ��
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class SrbsydwAction extends SmsbAction {

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
	 *            SrbsydwForm
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
	 *            SrbsydwForm
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
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡSrbsydwForm����
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// ������Ϣ����srbsydwForm��
		this.getBaseForm(request, srbsydwForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ñ��ɷ�ϵͳ�û���Ϣ
		vo.setUserData(userData);
		// ���ú�̨����Actionֵ---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// �����������Data����,������cbmxbybqyForm
		vo.setData(srbsydwForm);
		// ����ProxyҪ���õ�processor����---SrbsydwProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡsrbsydwForm����ֵ
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// ��srbsydwForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), srbsydwForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ����ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            SrbsydwForm
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
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡSrbsydwForm����
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����srbsydwForm��DataList��
		srbsydwForm.setDataList(SfRequestUtil.getValuesToList(request,
				srbsydwForm.getSrb_columns()));
		// ������Ϣ����srbsydwForm��
		this.getBaseForm(request, srbsydwForm);
		// ��czqysdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), srbsydwForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		vo.setData(srbsydwForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡsrbsydwForm����ֵ
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// ��srbsydwForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), srbsydwForm);
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
	 *            SrbsydwForm
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
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����srbsydwForm��DataList��
		srbsydwForm.setDataList(SfRequestUtil.getValuesToList(request,
				srbsydwForm.getSrb_columns()));
		// ��srbsydwForm����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), srbsydwForm);
		// ������Ϣ����cbmxbybqyForm��
		this.getBaseForm(request, srbsydwForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(srbsydwForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡSrbsydwForm����ֵ
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// ��SrbsydwForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), srbsydwForm);
			
			if(srbsydwForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(srbsydwForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(srbsydwForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsNewUtil.getCheckResults(srbsydwForm.getCheckList()));
			}
			
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ɾ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            SrbsydwForm
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
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// ������Ϣ����srbsydwForm��
		this.getBaseForm(request, srbsydwForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setUserData(userData);
		vo.setData(srbsydwForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡsrbsydwForm����ֵ
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// ��srbsydwForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), srbsydwForm);
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * �˳�ҳ��
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            SrbsydwForm
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
	 * ��session�л�ȡ������Ϣ��������SrbsydwForm������
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, SrbsydwForm form) {
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��session�л�ȡ��ҵ����˰�Ļ�����Ϣ
		QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		// �ѻ�����Ϣ���뵽SrbsydwForm������
		if (baseForm != null) {
			form.setJsjdm(baseForm.getJsjdm());
			form.setSbrq(baseForm.getSbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			form.setSkssksrq(baseForm.getSkssksrq());
			form.setSkssjsrq(baseForm.getSkssjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setCkzd(baseForm.getCkzd());
			form.setZsfs(baseForm.getZsfs());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setGzglxs(baseForm.getGzglxs());
			form.setJmlx(baseForm.getJmlx());
			form.setNextTableURL(QysdsNewUtil.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_SRBSYDW));
			form.setPreviousTableURL(QysdsNewUtil.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_SRBSYDW));
		}
	}
}