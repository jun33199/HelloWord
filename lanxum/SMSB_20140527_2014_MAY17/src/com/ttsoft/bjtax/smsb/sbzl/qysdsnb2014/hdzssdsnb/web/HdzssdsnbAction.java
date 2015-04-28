/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
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
 * 2014核定征收企业所得税年报
 * 项目名称：企业所得税  
 * 类名称：HdzssdsnbAction   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-4-10 下午3:36:29   
 * 修改人：wangcy   
 * 修改时间：2014-4-10 下午3:36:29   
 * 修改备注：   
 * @version  1.0
 */
public class HdzssdsnbAction extends SmsbAction {
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;核定征收企业所得税年度申报（2014版）</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2014企业所得税年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsnbForm
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

		try {
			// 获取hdzssdsnbForm对象
			HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
			// 获取本系统用户信息
			UserData userData = this.getUserData(request);
			// 设置录入人id
			hdzssdsnbForm.setLrr(userData.getYhid());
			//初始化代码表
			QysdsHdzsNbUtil2014.initCodeTable(hdzssdsnbForm);
			// 初始化数据传递容器
			VOPackage vo = new VOPackage();
			// 设置后台调用Action值---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// 设置容器里的Data数据,这里存放hdzssdsnbForm
			vo.setData(hdzssdsnbForm);
			// 设置本缴费系统用户信息
			vo.setUserData(userData);
			// 设置Proxy要调用的processor的类---HdzssdsjbProcessor
			vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
			// 调用Proxy，初始化hdzssdsnbForm中值
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将hdzssdsnbForm 放入request中
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 查询已申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            hdzssdsnbForm
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
		// 当前的ActionForm---hdzssdsnbForm
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		// 设置报表期类型
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 设置录入人id
		hdzssdsnbForm.setLrr(userData.getYhid());
		//初始化代码表
		QysdsHdzsNbUtil2014.initCodeTable(hdzssdsnbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,这里存放hdzssdsnbForm
		vo.setData(hdzssdsnbForm);
		// 设置Proxy要调用的processor的类---HdzssdsjbProcessor
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean = this.setCheckInf(hdzssdsnbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// 调用Proxy，执行processor,获取hdzssdsnbForm返回值
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将hdzssdsnbForm置入request,作为回显数据用
			
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// 操作成功流转
			return mapping.findForward("Show");
		} catch (Exception ex) {
			hdzssdsnbForm.reset(mapping, request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 保存申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            hdzssdsnbForm
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
		// 当前的ActionForm---HdzssdsnbForm
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		// 设置录入人id
		hdzssdsnbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给hdzssdsnbForm的DataList中，
		hdzssdsnbForm.setQysdsnbList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// 将hdzssdsnbForm放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(hdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取hdzssdsnbForm返回值
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将hdzssdsnbForm中的基本数据置空
			hdzssdsnbForm.reset(mapping, request);
			// 将hdzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
			return doQuery(mapping,form,request,response);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 删除申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            hdzssdsnbForm
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
		// 获取本系统用户信息
		userData = this.getUserData(request);
		// 当前的ActionForm---hdzssdsnbForm
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) form;
		// 设置录入人id
		hdzssdsnbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		hdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给hdzssdsnbForm的DataList中，
		hdzssdsnbForm.setQysdsnbList(SfRequestUtil.getValuesToList(request,
				hdzssdsnbForm.getColumns()));
		// 将hdzssdsnbForm放入request,当删除失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(hdzssdsnbForm);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_HDZSSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			// 调用Proxy，执行processor,获取hdzssdsnbForm返回值
			hdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将hdzssdsnbForm中的基本数据置空
			hdzssdsnbForm.reset(mapping, request);
			// 将hdzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), hdzssdsnbForm);
			// 操作成功流转，该提示信息在头文件header.jsp中获取
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
			return doQuery(mapping,form,request,response);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	 /**
	 * @Description: TODO 设置校验参数
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
