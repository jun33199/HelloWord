package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzssdsnb.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzsssyhmx.web.HdzsssyhmxForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class HdzssdsnbAction2009 extends SmsbAction {
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
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��B�ࣩ</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2009��ҵ����˰�����˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			// ��ȡHdzssdsnbForm����
			HdzssdsnbForm HdzssdsnbForm = (HdzssdsnbForm) form;
			// ��ȡ���ɷ�ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			HdzssdsnbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������HdzssdsnbForm
			vo.setData(HdzssdsnbForm);
			// ���ñ��ɷ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
			// ����Proxy����ʼ��HdzssdsnbForm��ֵ
			HdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��HdzssdsnbForm ����request��
			request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ��ת������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doToFb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		HdzssdsnbForm cform = (HdzssdsnbForm) form;
		cform.setDataList(SfRequestUtil.getValuesToList(request, cform
				.getSb_columns()));
		List list = cform.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			String hc = (String) map.get("hc");
			if ("3".equals(hc)) {
				request.setAttribute("zbh3", (String) map.get("je"));
				break;
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM, form);
		return mapping.findForward("ToFb");
	}

	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		HttpSession session = request.getSession();
		HdzssdsnbForm cform = (HdzssdsnbForm) session
				.getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		request.setAttribute(mapping.getAttribute(), cform);
		Double jmshj = (Double) request.getAttribute("jmshj");
		if (jmshj != null) {
			cform.setJmshj(jmshj.doubleValue());
		}
		cform.setFbreturnbs(true);
		return mapping.findForward("Show");
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
		HttpSession session = request.getSession(false);
		
		System.out.println("pppppppppppppppppppp");
		// ��ǰ��ActionForm---HdzssdsnbForm
		HdzssdsnbForm cform = (HdzssdsnbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ���ñ���������
		cform.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����˰����������
		// HdzssdsnbForm.setSwjsjdm(userData.getXtsbm1());
		// ����¼��������
		cform.setLrr(userData.getYhid());

		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������HdzssdsnbForm
		vo.setData(cform);
		// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(cform);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);

			cform = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cform);
			request.setAttribute(mapping.getAttribute(), cform);
			session.setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM, cform);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			cform.reset(mapping, request);
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
		
		System.out.println("sssssssssssssssssssssssss");
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		
		
		
		// ��ǰ��ActionForm---HdzssdsnbForm
		HdzssdsnbForm HdzssdsnbForm = (HdzssdsnbForm) form;
		HdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				HdzssdsnbForm.getSb_columns()));
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		HdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		HdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ��HdzssdsnbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(HdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡHdzssdsnbForm����ֵ
			HdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��HdzssdsnbForm�еĻ��������ÿ�
			// HdzssdsnbForm.reset(mapping, request);
			// ��HdzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			ex.printStackTrace();
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
		// ��ǰ��ActionForm---HdzssdsnbForm
		HdzssdsnbForm HdzssdsnbForm = (HdzssdsnbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		HdzssdsnbForm.setLrr(userData.getYhid());
		// ���ñ���������
		HdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����HdzssdsnbForm��DataList�У�
		HdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				HdzssdsnbForm.getSb_columns()));
		// ��HdzssdsnbForm����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(HdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡHdzssdsnbForm����ֵ
			HdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// ��HdzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
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
		HdzssdsnbForm cform = (HdzssdsnbForm) form;
		this.getBaseForm(request, cform);
		// HdzsssyhmxForm.setDataList(SfRequestUtil.getValuesToList(request,
		// HdzsssyhmxForm.getSb_columns()));
		VOPackage vo = new VOPackage();
		vo.setData(cform);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		try {
			// ����processor
			cform = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cform);
			// HdzsssyhmxForm.reset(mapping, request);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
		return mapping.findForward("Show");
	}

	/**
	 * ��session�л�ȡ������Ϣ
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, HdzssdsnbForm form) {
		userData = this.getUserData(request);
		QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);

		String ksrq = request.getParameter("skksrq");
		String jsrq = request.getParameter("skjsrq");

		if ((ksrq != null && !"".equals(ksrq))
				&& (jsrq != null && !"".equals(jsrq))) {
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(
					CodeConstant.SESSIONKEY_QYSDSNEWFORM, baseForm);
		}

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
			form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm, "N_"
					+ CodeConstant.TABLE_ID_2009_13));
			form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm, "P_"
					+ CodeConstant.TABLE_ID_2009_13));
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
		checkBean.setVersionStartTime("2009");
		checkBean.setVersionEndTime("2011");
		
		checkBean.setJsjdm(hdzssdsnbForm.getJsjdm());
		
		checkBean.setSkssrqq(hdzssdsnbForm.getSkssksrq());
		checkBean.setSkssrqz(hdzssdsnbForm.getSkssjsrq());
		return checkBean;
	}
}