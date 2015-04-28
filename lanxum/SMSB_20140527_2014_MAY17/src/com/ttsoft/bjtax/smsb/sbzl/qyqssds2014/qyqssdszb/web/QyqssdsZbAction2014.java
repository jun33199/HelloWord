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
 * 项目名称：smsb   
 * 类名称：QyqssdsZbAction2014   
 * 类描述：   中华人民共和国企业清算所得税申报表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 上午11:42:14   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 上午11:42:14   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsZbAction2014 extends SmsbAction {

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
	 *            QyqssdsZbForm2014
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
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 获取ZbForm对象
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		// 基本信息放入QyqssdsZbForm2014中
		this.getBaseForm(request, zbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置本缴费系统用户信息
		vo.setUserData(userData);
		// 设置后台调用Action值---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// 设置容器里的Data数据,这里存放QyqssdsZbForm2014
		vo.setData(zbForm);
		// 设置Proxy要调用的processor的类---QyqssdsZbProcessor2014
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// 调用Proxy，执行processor,获取QyqssdsZbForm2014返回值
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			// 将QyqssdsZbForm2014置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), zbForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 保存页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsZbForm2014
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
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 获取QyqssdsZbForm2014对象
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		// 按照columns中的字段提取所需要的前台列表数据，赋给QyqssdsZbForm2014的DataList中
		zbForm.setDataList(SfRequestUtil.getValuesToList(request, zbForm
				.getColumns()));
		// 基本信息放入QyqssdsZbForm2014中
		this.getBaseForm(request, zbForm);
		// 将QyqssdsZbForm2014放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), zbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		vo.setData(zbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// 调用Proxy，执行processor,获取zbForm返回值
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			// 将QyqssdsZbForm2014置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), zbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 审核数据，审核通过后进行保存
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsZbForm2014
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
		// 获取QyqssdsZbForm2014对象
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 按照columns中的字段提取所需要的前台列表数据，赋给zbForm的DataList中
		zbForm.setDataList(SfRequestUtil.getValuesToList(request, zbForm.getColumns()));
		// 基本信息放入QyqssdsZbForm2014中
		this.getBaseForm(request, zbForm);
		// 将QyqssdsZbForm2014放入request,当校验失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), zbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(zbForm);
		vo.setUserData(userData);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// 调用Proxy，执行processor,获取QyqssdsZbForm2014返回值
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			
			System.out.println(zbForm.getSkssq()+"====================");
			// 将QyqssdsZbForm2014置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), zbForm);
			
			if(zbForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "校验通过！");
			}else if(zbForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "校验通过！");
			}else if(zbForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QyqssdsUtil2014.getCheckResults(zbForm.getCheckList()));
			}
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 删除页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsZbForm2014
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
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);

		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) form;
		// 基本信息放入QyqssdsZbForm2014中
		this.getBaseForm(request, zbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setUserData(userData);
		vo.setData(zbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor.QyqssdsZbProcessor2014");
		try {
			// 调用Proxy，执行processor,获取QyqssdsZbForm2014返回值
			zbForm = (QyqssdsZbForm2014) SbzlProxy.getInstance().process(vo);
			// 将QyqssdsZbForm2014置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), zbForm);
			// 操作成功流转，该提示信息在头文件header.jsp中获取
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 退出页面
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsZbForm2014
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

		request.setAttribute(mapping.getAttribute(), form);
		return mapping.findForward("Return");
	}

	/**
	 * 从session中获取基本信息，并放入QyqssdsZbForm2014对象中
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, QyqssdsZbForm2014 form) {
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 从session中获取企业所得税的基本信息
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
//			baseForm.setSkssksrq(request.getParameter("skksrq"));
//			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM,baseForm);
		}
		
		// 把基本信息放入到QyqssdsZbForm2014对象中
		if (baseForm != null) {
			Debug.out("2014版企业清算所得税申报  主表  QyqssdsZbAction2014 getBaseForm ");
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
			//-----申报审核状态
			form.setSbShztbs(baseForm.getSbShztbs());
			//-----申请类型
			form.setSqlx(baseForm.getSqlx());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setNextTableURL(QyqssdsUtil2014.getTableURL(baseForm,"N_"+CodeConstant.QYQSSDS_TABLE_ID_2014_ZB));
		}
	}

}