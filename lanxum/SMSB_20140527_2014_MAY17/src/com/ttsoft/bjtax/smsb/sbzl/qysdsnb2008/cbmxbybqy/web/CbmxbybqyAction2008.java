/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.web.CbmxbybqyForm2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:������������ɱ�������ϸ��
 * </p>
 * 
 * @author li wenhua
 * @version 1.1
 */
public class CbmxbybqyAction2008 extends SmsbAction {

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
	 *            CbmxbybqyForm2008
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
	 *            CbmxbybqyForm2008
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// ��ȡCbmxbybqyForm����
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ������Ϣ����cbmxbybqyForm2008��
		this.getBaseForm(request, cbmxbybqyForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// �����������Data����,������cbmxbybqyForm
		vo.setData(cbmxbybqyForm);
		// ����ProxyҪ���õ�processor����---CbmxbybqyProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_CBMXBYBQY_PROCESSOR2008);
		// ���ñ��ɷ�ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡcbmxbybqyForm����ֵ
			cbmxbybqyForm = (CbmxbybqyForm2008) SbzlProxy.getInstance().process(vo);
			// ��cbmxbybqyForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), cbmxbybqyForm);

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
	 *            CbmxbybqyForm
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
		// ��ȡCbmxbybqyForm����
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) form;
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����cbmxbybqyForm2008��DataList��
		cbmxbybqyForm.setDataList(SfRequestUtil.getValuesToList(request,
				cbmxbybqyForm.getColumns()));
		// ������Ϣ����cbmxbybqyForm2008��
		this.getBaseForm(request, cbmxbybqyForm);
		// ��czqysdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), cbmxbybqyForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(cbmxbybqyForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_CBMXBYBQY_PROCESSOR2008);
		try {
			// ����Proxy��ִ��processor,��ȡcbmxbybqyForm����ֵ
			cbmxbybqyForm = (CbmxbybqyForm2008) SbzlProxy.getInstance().process(vo);
			// ��cbmxbybqyForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), cbmxbybqyForm);
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
	 *            CbmxbybqyForm
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
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡCzqysdsjbForm����
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) form;
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����cbmxbybqyForm��DataList��
		cbmxbybqyForm.setDataList(SfRequestUtil.getValuesToList(request,
				cbmxbybqyForm.getColumns()));
		// ������Ϣ����cbmxbybqyForm��
		this.getBaseForm(request, cbmxbybqyForm);
		// ��cbmxbybqyForm����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), cbmxbybqyForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(cbmxbybqyForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_CBMXBYBQY_PROCESSOR2008);
		try {
			// ����Proxy��ִ��processor,��ȡcbmxbybqyForm����ֵ
			cbmxbybqyForm = (CbmxbybqyForm2008) SbzlProxy.getInstance().process(vo);
			// ��cbmxbybqyForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), cbmxbybqyForm);
			
			if(cbmxbybqyForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(cbmxbybqyForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(cbmxbybqyForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2008.getCheckResults(cbmxbybqyForm.getCheckList()));
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
	 *            CbmxbybqyForm
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
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) form;
		// ������Ϣ����cbmxbybqyForm��
		this.getBaseForm(request, cbmxbybqyForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(cbmxbybqyForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_CBMXBYBQY_PROCESSOR2008);
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡcbmxbybqyForm����ֵ
			cbmxbybqyForm = (CbmxbybqyForm2008) SbzlProxy.getInstance().process(vo);
			// ��cbmxbybqyForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), cbmxbybqyForm);
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
	 *            CbmxbybqyForm
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
	 * ��session�л�ȡ������Ϣ��������CbmxbybqyForm2008������
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, CbmxbybqyForm2008 form) {
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
		
		// �ѻ�����Ϣ���뵽CbmxbybqyForm2008������
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
			form.setNextTableURL(QysdsUtil2008.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_2008_2_1));
			form.setPreviousTableURL(QysdsUtil2008.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_2008_2_1));
		}
	}
}