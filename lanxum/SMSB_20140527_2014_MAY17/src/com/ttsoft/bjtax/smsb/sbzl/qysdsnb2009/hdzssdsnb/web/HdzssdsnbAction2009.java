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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税年度纳税申报表（B类）</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2009企业所得税年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			// 获取HdzssdsnbForm对象
			HdzssdsnbForm HdzssdsnbForm = (HdzssdsnbForm) form;
			// 获取本缴费系统用户信息
			UserData userData = this.getUserData(request);
			// 设置录入人名称
			HdzssdsnbForm.setLrr(userData.getYhid());
			// 初始化数据传递容器
			VOPackage vo = new VOPackage();
			// 设置后台调用Action值---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// 设置容器里的Data数据,这里存放HdzssdsnbForm
			vo.setData(HdzssdsnbForm);
			// 设置本缴费系统用户信息
			vo.setUserData(userData);
			// 设置Proxy要调用的processor的类---HdzssdsjbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
			// 调用Proxy，初始化HdzssdsnbForm中值
			HdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将HdzssdsnbForm 放入request中
			request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 跳转到附表
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
	 * 处理附表返回
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
	 * 查询已申报数据
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

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		HttpSession session = request.getSession(false);
		
		System.out.println("pppppppppppppppppppp");
		// 当前的ActionForm---HdzssdsnbForm
		HdzssdsnbForm cform = (HdzssdsnbForm) form;
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 设置报表期类型
		cform.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 设置税务计算机代码
		// HdzssdsnbForm.setSwjsjdm(userData.getXtsbm1());
		// 设置录入人名称
		cform.setLrr(userData.getYhid());

		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,这里存放HdzssdsnbForm
		vo.setData(cform);
		// 设置Proxy要调用的processor的类---HdzssdsjbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean = this.setCheckInf(cform);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);

			cform = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cform);
			request.setAttribute(mapping.getAttribute(), cform);
			session.setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM, cform);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			cform.reset(mapping, request);
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
	 *            HdzssdsnbForm
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
		
		System.out.println("sssssssssssssssssssssssss");
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		
		
		
		// 当前的ActionForm---HdzssdsnbForm
		HdzssdsnbForm HdzssdsnbForm = (HdzssdsnbForm) form;
		HdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				HdzssdsnbForm.getSb_columns()));
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		HdzssdsnbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		HdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 将HdzssdsnbForm放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(HdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取HdzssdsnbForm返回值
			HdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将HdzssdsnbForm中的基本数据置空
			// HdzssdsnbForm.reset(mapping, request);
			// 将HdzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			ex.printStackTrace();
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
	 *            ZcmxbForm
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
		// 当前的ActionForm---HdzssdsnbForm
		HdzssdsnbForm HdzssdsnbForm = (HdzssdsnbForm) form;
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		HdzssdsnbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		HdzssdsnbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给HdzssdsnbForm的DataList中，
		HdzssdsnbForm.setDataList(SfRequestUtil.getValuesToList(request,
				HdzssdsnbForm.getSb_columns()));
		// 将HdzssdsnbForm放入request,当校验失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(HdzssdsnbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_HDZSSDSNB_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取HdzssdsnbForm返回值
			HdzssdsnbForm = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			// 将HdzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), HdzssdsnbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "检验成功！");
			return mapping.findForward("Show");
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
	 *            HdzssdsnbForm
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
			// 调用processor
			cform = (HdzssdsnbForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cform);
			// HdzsssyhmxForm.reset(mapping, request);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
		return mapping.findForward("Show");
	}

	/**
	 * 从session中获取基本信息
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
	 * @Description: TODO 设置校验参数
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