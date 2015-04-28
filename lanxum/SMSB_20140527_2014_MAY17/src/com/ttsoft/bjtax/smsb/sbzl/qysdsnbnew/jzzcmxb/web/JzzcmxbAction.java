/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jzzcmxb.web;

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
 * Description:����˾���֧����ϸ��
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class JzzcmxbAction extends SmsbAction {

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
	 *            JzzcmxbForm
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
	 *            JzzcmxbForm
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
		// ��ȡJzzcmxbForm����
		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ������Ϣ����cbmxbybqyForm��
		this.getBaseForm(request, jzzcmxbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// �����������Data����,������cbmxbybqyForm
		vo.setData(jzzcmxbForm);
		// ����ProxyҪ���õ�processor����---JzzcmxbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_JZZCMXB_PROCESSOR);
		// ���ñ��ɷ�ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {

			// ����Proxy��ִ��processor,��ȡjzzcmxbForm����ֵ
			jzzcmxbForm = (JzzcmxbForm) SbzlProxy.getInstance().process(vo);
			// ��jzzcmxbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), jzzcmxbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
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
	 *            JzzcmxbForm
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

		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) form;

		// ��ȡ�̶��е�����
		jzzcmxbForm.setDataList(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getJzzc_columns()));

		// ��ȡȫ��۳��ľ����ϼ�
		jzzcmxbForm.setQekclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getQekc_Columns()));

		// ��10%�۳��ľ����ϼ�
		jzzcmxbForm.setBfzskclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getBfzskc_Columns()));
		// ��3%�۳��ľ����ϼ�
		jzzcmxbForm.setBfzwkclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getBfzwkc_Columns()));
		// ��1.5%�۳��ľ����ϼ�
		jzzcmxbForm.setBfzydwkclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getBfzydwkc_Columns()));
		// �ǹ���ȼ��Ծ����ϼ�
		jzzcmxbForm.setFgyjjxlist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getFgyjjx_Columns()));
		// ������Ϣ����cbmxbybqyForm��
		this.getBaseForm(request, jzzcmxbForm);
		// ��czqysdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), jzzcmxbForm);

		userData = this.getUserData(request);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(jzzcmxbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_JZZCMXB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡjzzcmxbForm����ֵ
			jzzcmxbForm = (JzzcmxbForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), jzzcmxbForm);
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

		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) form;
		userData = this.getUserData(request);
		// ������Ϣ����cbmxbybqyForm��
		this.getBaseForm(request, jzzcmxbForm);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����cbmxbybqyForm��DataList��
		jzzcmxbForm.setDataList(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getJzzc_columns()));
		// ��ȡȫ��۳��ľ����ϼ�
		jzzcmxbForm.setQekclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getQekc_Columns()));

		// ��10%�۳��ľ����ϼ�
		jzzcmxbForm.setBfzskclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getBfzskc_Columns()));
		// ��3%�۳��ľ����ϼ�
		jzzcmxbForm.setBfzwkclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getBfzwkc_Columns()));
		// ��1.5%�۳��ľ����ϼ�
		jzzcmxbForm.setBfzydwkclist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getBfzydwkc_Columns()));
		// �ǹ���ȼ��Ծ����ϼ�
		jzzcmxbForm.setFgyjjxlist(SfRequestUtil.getValuesToList(request,
				jzzcmxbForm.getFgyjjx_Columns()));

		request.setAttribute(mapping.getAttribute(), jzzcmxbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(jzzcmxbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_JZZCMXB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡcbmxbybqyForm����ֵ
			jzzcmxbForm = (JzzcmxbForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), jzzcmxbForm);

			if (jzzcmxbForm.getCheckList() == null) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			} else if (jzzcmxbForm.getCheckList().size() == 0) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			} else if (jzzcmxbForm.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
						QysdsNewUtil.getCheckResults(jzzcmxbForm.getCheckList()));
			}

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
	 *            JzzcmxbForm
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
	 * ɾ���걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JzzcmxbForm
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

		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) form;
		userData = this.getUserData(request);
		// ������Ϣ����jzzcmxbForm��
		this.getBaseForm(request, jzzcmxbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(jzzcmxbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_JZZCMXB_PROCESSOR);
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡjzzcmxbForm����ֵ
			jzzcmxbForm = (JzzcmxbForm) SbzlProxy.getInstance().process(vo);
			// jzzcmxbForm.reset(mapping, request);
			request.setAttribute(mapping.getAttribute(), jzzcmxbForm);
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {

			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ��session�л�ȡ������Ϣ��������JzzcmxbForm������
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, JzzcmxbForm form) {
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
		
		// �ѻ�����Ϣ���뵽CbmxbybqyForm������
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
			form.setNextTableURL(QysdsNewUtil.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_JZZCMXB));
			form.setPreviousTableURL(QysdsNewUtil.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_JZZCMXB));
		}
	}
}