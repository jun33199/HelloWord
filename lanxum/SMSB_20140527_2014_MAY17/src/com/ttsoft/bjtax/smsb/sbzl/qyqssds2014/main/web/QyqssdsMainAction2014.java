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
 * 项目名称：smsb   
 * 类名称：QyqssdsMainAction2014   
 * 类描述：  企业所得税清算申报　上门模块 
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午12:26:42   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午12:26:42   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsMainAction2014 extends SmsbAction {
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
	 * @param mapping struts.action.ActionMapping
	 * @param form QyqssdsBaseForm   
	 * @param request HttpServletRequest    
	 * @param response  HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException  系统捕捉异常，根据异常类型抛出    
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
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	/**
	 * 接受网上申请备案数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
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
			//如果校验不通过，将校验不通过结果返回给页面
			if (baseForm!=null&&baseForm.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
						QyqssdsUtil2014.getCheckResults(baseForm.getCheckList()));
			}

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}
	
	/**
	 * 拒绝网上清算备案数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
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
			throw new ApplicationException("该企业的企业清算所得税申报不是提交未审核状态，不能执行审核驳回！");
		}
		try {
			baseForm = (QyqssdsBaseForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);

		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}
	/**
	 * 作废申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
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
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}
	/**
	 * 保存申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
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
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}

	/**
	 * 查询已申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		
		HttpSession session = request.getSession(false);

		// 查询前清除Session
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
			// 系统捕捉异常，根据异常类型抛出
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
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "全部校验通过！");
			} else if (baseForm.getCheckList().size() == 0) {
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "全部校验通过！");
			} else if (baseForm.getCheckList().size() > 0) {
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
						QyqssdsUtil2014.getCheckResults(baseForm.getCheckList()));
			}
		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

		return mapping.findForward("Query");
	}

	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
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
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	/**
     * 返回页面
     * @param mapping struts.action.ActionMapping
     * @param form QyqssdsBaseForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDSSB_QUERY);
	}
}