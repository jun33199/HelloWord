package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.web;

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
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsZbAction2014   
 * ��������   �л����񹲺͹���ҵ��������˰�걨��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����11:42:14   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����11:42:14   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsZbAction2014 extends SmsbAction {

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
	 *            QyqssdsZbForm2014
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
	 *            QyqssdsZbForm2014
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
		// ��ȡZbForm����
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		// ������Ϣ����QyqssdsZbForm2014��
		this.getBaseForm(request, zbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ñ��ɷ�ϵͳ�û���Ϣ
		vo.setUserData(userData);
		// ���ú�̨����Actionֵ---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// �����������Data����,������QyqssdsZbForm2014
		vo.setData(zbForm);
		// ����ProxyҪ���õ�processor����---QyqssdsZbProcessor2014
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// ����Proxy��ִ��processor,��ȡQyqssdsZbForm2014����ֵ
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsZbForm2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), zbForm);
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
	 *            QyqssdsZbForm2014
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
		// ��ȡQyqssdsZbForm2014����
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����QyqssdsZbForm2014��DataList��
		zbForm.setDataList(SfRequestUtil.getValuesToList(request, zbForm
				.getColumns()));
		// ������Ϣ����QyqssdsZbForm2014��
		this.getBaseForm(request, zbForm);
		// ��QyqssdsZbForm2014����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), zbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		vo.setData(zbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// ����Proxy��ִ��processor,��ȡzbForm����ֵ
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsZbForm2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), zbForm);
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
	 *            QyqssdsZbForm2014
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
		// ��ȡQyqssdsZbForm2014����
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����zbForm��DataList��
		zbForm.setDataList(SfRequestUtil.getValuesToList(request, zbForm.getColumns()));
		// ������Ϣ����QyqssdsZbForm2014��
		this.getBaseForm(request, zbForm);
		// ��QyqssdsZbForm2014����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), zbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(zbForm);
		vo.setUserData(userData);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// ����Proxy��ִ��processor,��ȡQyqssdsZbForm2014����ֵ
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			
			System.out.println(zbForm.getSkssq()+"====================");
			// ��QyqssdsZbForm2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), zbForm);
			
			if(zbForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "У��ͨ����");
			}else if(zbForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "У��ͨ����");
			}else if(zbForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QyqssdsUtil2014.getCheckResults(zbForm.getCheckList()));
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
	 *            QyqssdsZbForm2014
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

		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		// ������Ϣ����QyqssdsZbForm2014��
		this.getBaseForm(request, zbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setUserData(userData);
		vo.setData(zbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// ����Proxy��ִ��processor,��ȡQyqssdsZbForm2014����ֵ
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsZbForm2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), zbForm);
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
	 *            QyqssdsZbForm2014
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

		request.setAttribute(mapping.getAttribute(), form);
		return mapping.findForward("Return");
	}

	/**
	 * ��session�л�ȡ������Ϣ��������QyqssdsZbForm2014������
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, QyqssdsZbForm2014 form) {
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��session�л�ȡ��ҵ����˰�Ļ�����Ϣ
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
//			baseForm.setSkssksrq(request.getParameter("skksrq"));
//			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM,baseForm);
		}
		
		// �ѻ�����Ϣ���뵽QyqssdsZbForm2014������
		if (baseForm != null) {
			Debug.out("2014����ҵ��������˰�걨  ����  QyqssdsZbAction2014 getBaseForm ");
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
			//-----�걨���״̬
			form.setSbShztbs(baseForm.getSbShztbs());
			//-----��������
			form.setSqlx(baseForm.getSqlx());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setNextTableURL(QyqssdsUtil2014.getTableURL(baseForm,"N_"+CodeConstant.QYQSSDS_TABLE_ID_2014_ZB));
		}
	}

}