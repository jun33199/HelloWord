package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.web;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsMainAction2014   
 * ��������  ��ҵ����˰�����걨������ģ�� 
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����12:26:42   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����12:26:42   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsMainAction2014 extends SmsbAction {
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
	 * @param mapping struts.action.ActionMapping
	 * @param form QyqssdsBaseForm   
	 * @param request HttpServletRequest    
	 * @param response  HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException  ϵͳ��׽�쳣�������쳣�����׳�    
	*/
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	/**
	 * �����������뱸������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(10);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			//���У�鲻ͨ������У�鲻ͨ��������ظ�ҳ��
			if (baseForm!=null&&baseForm.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
						QyqssdsUtil2014.getCheckResults(baseForm.getCheckList()));
			}

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}
	
	/**
	 * �ܾ��������㱸������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doRefuse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		System.out.println(baseForm.getTbrq());
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(11);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		Map map=QyqssdsActionHelper.getShztbs(baseForm.getJsjdm());
		if(map.isEmpty() || !map.get(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS).toString().equals("1")){
			throw new ApplicationException("����ҵ����ҵ��������˰�걨�����ύδ���״̬������ִ����˲��أ�");
		}
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}
	/**
	 * �����걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
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
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(13);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}
	/**
	 * �����걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
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
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(12);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}

	/**
	 * ��ѯ���걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
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

		
		HttpSession session = request.getSession(false);

		// ��ѯǰ���Session
		session.removeAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);

		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		System.out.println(baseForm.getTbrq());
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			session.setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM, baseForm);

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Show");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;

		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			if (baseForm.getCheckList() == null) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ȫ��У��ͨ����");
			} else if (baseForm.getCheckList().size() == 0) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ȫ��У��ͨ����");
			} else if (baseForm.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
						QyqssdsUtil2014.getCheckResults(baseForm.getCheckList()));
			}
		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
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

	public ActionForward doReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) form;
		baseForm=(QyqssdsBaseForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
//		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor.QyqssdsMainProcessor2014");
		vo.setUserData(userData);
		try {
			/*--------*/
			//System.out.println("====---"+sessionForm.getJsjdm()+"---====");

//			if (sessionForm != null) {
//				sessionForm.setLinkUrlHTML(QyqssdsUtil2014.getLinkUrlHtml(sessionForm.getTableList(), sessionForm));
//				request.setAttribute(mapping.getAttribute(), sessionForm);
//			} else {
				baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
				baseForm.setLinkUrlHTML(QyqssdsUtil2014.getLinkUrlHtml(baseForm.getTableList(), baseForm));
				request.setAttribute(mapping.getAttribute(), baseForm);
//			}

			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	/**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QyqssdsBaseForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDSSB_QUERY);
	}
}