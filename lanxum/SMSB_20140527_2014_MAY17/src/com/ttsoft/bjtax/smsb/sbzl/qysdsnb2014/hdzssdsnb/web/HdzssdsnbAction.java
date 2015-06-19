/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsHdzsNbUtil2014;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * 2014�˶�������ҵ����˰�걨
 * ��Ŀ���ƣ���ҵ����˰  
 * �����ƣ�HdzssdsnbAction   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-4-10 ����3:36:29   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-4-10 ����3:36:29   
 * �޸ı�ע��   
 * @version  1.0
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
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;�˶�������ҵ����˰����걨��2014�棩</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2014��ҵ����˰�����˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
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
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			// ��ȡhdzssdsnbForm����
			HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
			// ��ȡ��ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼����id
			hdzssdsnbForm.setLrr(userData.getYhid());
			//��ʼ�������
			QysdsHdzsNbUtil2014.initCodeTable(hdzssdsnbForm);
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������hdzssdsnbForm
			vo.setData(hdzssdsnbForm);
			// ���ñ��ɷ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
			vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
			// ����Proxy����ʼ��hdzssdsnbForm��ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsnbForm ����request��
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
	 *            hdzssdsnbForm
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
		// ��ǰ��ActionForm---hdzssdsnbForm
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����¼����id
		hdzssdsnbForm.setLrr(userData.getYhid());
		//��ʼ�������
		QysdsHdzsNbUtil2014.initCodeTable(hdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������hdzssdsnbForm
		vo.setData(hdzssdsnbForm);
		// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(hdzssdsnbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// ����Proxy��ִ��processor,��ȡhdzssdsnbForm����ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsnbForm����request,��Ϊ����������
			
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// �����ɹ���ת
			return mapping.findForward("Show");
		} catch (Exception ex) {
			hdzssdsnbForm.reset(mapping, request);
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
	 *            hdzssdsnbForm
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
		// ��ǰ��ActionForm---HdzssdsnbForm
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼����id
		hdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hdzssdsnbForm��DataList�У�
		hdzssdsnbForm.setQysdsnbList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// ��hdzssdsnbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(hdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsnbForm����ֵ
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��hdzssdsnbForm�еĻ��������ÿ�
			hdzssdsnbForm.reset(mapping, request);
			// ��hdzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
			return doQuery(mapping,form,request,response);
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
	 *            hdzssdsnbForm
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
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ǰ��ActionForm---hdzssdsnbForm
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// ����¼����id
		hdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hdzssdsnbForm��DataList�У�
		hdzssdsnbForm.setQysdsnbList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// ��hdzssdsnbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(hdzssdsnbForm);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
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
			return doQuery(mapping,form,request,response);
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
		checkBean.setCurrentTime(hdzssdsnbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2014");
		checkBean.setVersionEndTime("#");
		
		checkBean.setJsjdm(hdzssdsnbForm.getJsjdm());
		
		checkBean.setSkssrqq(hdzssdsnbForm.getSkssksrq());
		checkBean.setSkssrqz(hdzssdsnbForm.getSkssjsrq());
		return checkBean;
	}

}
