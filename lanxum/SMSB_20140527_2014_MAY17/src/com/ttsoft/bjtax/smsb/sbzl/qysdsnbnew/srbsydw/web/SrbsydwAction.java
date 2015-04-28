/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:附表一（３）事业单位、社会团体、民办非企业单位收入项目明细表
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class SrbsydwAction extends SmsbAction {

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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税年度纳税申报表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业所得税年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            SrbsydwForm
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
		// 获取SrbsydwForm对象
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// 基本信息放入srbsydwForm中
		this.getBaseForm(request, srbsydwForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置本缴费系统用户信息
		vo.setUserData(userData);
		// 设置后台调用Action值---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// 设置容器里的Data数据,这里存放cbmxbybqyForm
		vo.setData(srbsydwForm);
		// 设置Proxy要调用的processor的类---SrbsydwProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取srbsydwForm返回值
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// 将srbsydwForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), srbsydwForm);

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
	 *            SrbsydwForm
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
		// 获取SrbsydwForm对象
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// 按照columns中的字段提取所需要的前台列表数据，赋给srbsydwForm的DataList中
		srbsydwForm.setDataList(SfRequestUtil.getValuesToList(request,
				srbsydwForm.getSrb_columns()));
		// 基本信息放入srbsydwForm中
		this.getBaseForm(request, srbsydwForm);
		// 将czqysdsjbForm放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), srbsydwForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		vo.setData(srbsydwForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取srbsydwForm返回值
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// 将srbsydwForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), srbsydwForm);
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
	 *            SrbsydwForm
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
		// 获取CzqysdsjbForm对象
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 按照columns中的字段提取所需要的前台列表数据，赋给srbsydwForm的DataList中
		srbsydwForm.setDataList(SfRequestUtil.getValuesToList(request,
				srbsydwForm.getSrb_columns()));
		// 将srbsydwForm放入request,当校验失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), srbsydwForm);
		// 基本信息放入cbmxbybqyForm中
		this.getBaseForm(request, srbsydwForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(srbsydwForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取SrbsydwForm返回值
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// 将SrbsydwForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), srbsydwForm);
			
			if(srbsydwForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
			}else if(srbsydwForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
			}else if(srbsydwForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsNewUtil.getCheckResults(srbsydwForm.getCheckList()));
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
	 *            SrbsydwForm
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
		SrbsydwForm srbsydwForm = (SrbsydwForm) form;
		// 基本信息放入srbsydwForm中
		this.getBaseForm(request, srbsydwForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setUserData(userData);
		vo.setData(srbsydwForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_SRBSYDW_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取srbsydwForm返回值
			srbsydwForm = (SrbsydwForm) SbzlProxy.getInstance().process(vo);
			// 将srbsydwForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), srbsydwForm);
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
	 *            SrbsydwForm
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
	 * 从session中获取基本信息，并放入SrbsydwForm对象中
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, SrbsydwForm form) {
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 从session中获取企业所得税的基本信息
		QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		// 把基本信息放入到SrbsydwForm对象中
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