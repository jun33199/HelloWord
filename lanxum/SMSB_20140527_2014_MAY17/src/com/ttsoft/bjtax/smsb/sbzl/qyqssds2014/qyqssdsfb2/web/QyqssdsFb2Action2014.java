package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.web;

import java.util.HashMap;
import java.util.Map;

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
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsFb2Action2014   
 * 类描述：   附表二：负债清偿损益明细表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午3:29:14   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午3:29:14   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsFb2Action2014 extends SmsbAction {
	/**
	 * 操作员数据
	 */
	private UserData userData = null;

	/**
	 * 公共的前置处理方法，每次进入页面都会被调用<BR>
	 * 设置页面显示时使用的导航信息和标题
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
				"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">税费管理</font>&gt;企业清算所得税申报</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业清算所得税申报");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssds-000.htm");

	}

	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);
		VOPackage vo = new VOPackage();
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");

		try {
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
		} catch (Exception ex) {
			fb2Form.reset(mapping, request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}

	/**
	 * 保存申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);
		fb2Form.setDataList(SfRequestUtil.getValuesToList(request,fb2Form.getSb_columns1()));
		VOPackage vo = new VOPackage();
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");
		request.setAttribute(mapping.getAttribute(), fb2Form);
		try {
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
		return mapping.findForward("Show");
	}

	/**
	 * 审核数据，审核通过后进行保存
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);
		fb2Form.setDataList(SfRequestUtil.getValuesToList(request,fb2Form.getSb_columns1()));
		request.setAttribute("ActionForm", fb2Form);
		VOPackage vo = new VOPackage();
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");
		try {
			// 调用processor
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance()
					.process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
			if (fb2Form.getCheckList() == null) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "校验通过！");
			} else if (fb2Form.getCheckList().size() == 0) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "校验通过！");
			} else if (fb2Form.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,QyqssdsUtil2014.getCheckResults(fb2Form.getCheckList()));
			}
			System.out.println("------------Action_doCheck----------------");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}

	/**
	 * 删除申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb2Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) form;
		this.getBaseForm(request, fb2Form);

		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(fb2Form);
		vo.setUserData(userData);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor.QyqssdsFb2Processor2014");
		try {
			// 调用processor
			fb2Form = (QyqssdsFb2Form2014) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), fb2Form);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
		return mapping.findForward("Show");
	}

	/**
	 * 退出页面
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaseForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doExit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return mapping.findForward("Return");
	}

	/**
	 * 从session中获取基本信息
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, QyqssdsFb2Form2014 form) {
		userData = this.getUserData(request);
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);

		String ksrq = request.getParameter("skksrq");
		String jsrq = request.getParameter("skjsrq");

		if ((ksrq != null && !"".equals(ksrq))
				&& (jsrq != null && !"".equals(jsrq))) {
			request.getSession(false).setAttribute(
					CodeConstant.SESSIONKEY_QYQSSDSBASEFORM, baseForm);
		}

		if (baseForm != null) {
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
			form.setQxdm(baseForm.getQxdm());
			
			//-----申报审核状态
			form.setSbShztbs(baseForm.getSbShztbs());
			//-----申请类型
			form.setSqlx(baseForm.getSqlx());
			
			form.setLrr(userData.getYhid());
			form.setNextTableURL(QyqssdsUtil2014.getTableURL(baseForm, "N_" + CodeConstant.QYQSSDS_TABLE_ID_2014_2));
			form.setPreviousTableURL(QyqssdsUtil2014.getTableURL(baseForm, "P_" + CodeConstant.QYQSSDS_TABLE_ID_2014_2));
		}
	}

}
