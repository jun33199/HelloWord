package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class CzzssdsjbAction  extends SmsbAction {
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税月(季)度预缴纳税申报表(A类)</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2008企业所得税年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsjbForm
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
			// 获取CzzssdsjbForm对象
			CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
			// 获取本系统用户信息
			UserData userData = this.getUserData(request);
			// 设置录入人名称
			CzzssdsjbForm.setLrr(userData.getYhid());
			// 初始化数据传递容器
			VOPackage vo = new VOPackage();
			// 设置后台调用Action值---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// 设置容器里的Data数据,这里存放CzzssdsjbForm
			vo.setData(CzzssdsjbForm);
			// 设置本系统用户信息
			vo.setUserData(userData);
			// 设置Proxy要调用的processor的类---HdzssdsjbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSJB2008_CZZSSDS_PROCESSOR);
			// 调用Proxy，初始化CzzssdsjbForm中值
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm 放入request中
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 跳转页面数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsjbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doJump(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			System.out.println("I am jumping....");
			// 获取CzzssdsjbForm对象
			CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;

			// 将CzzssdsjbForm 放入request中
			//request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			//
			ZfjgqysdsjbForm zfjgForm=new ZfjgqysdsjbForm();
			zfjgForm.setJsjdm(CzzssdsjbForm.getJsjdm());
			request.setAttribute("zfjgForm", zfjgForm);
			return mapping.findForward("Jump");

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
	 *            CzzssdsjbForm
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
		// 当前的ActionForm---CzzssdsjbForm
		CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		// 设置报表期类型
		CzzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// 设置税务计算机代码
		// CzzssdsjbForm.setSwjsjdm(userData.getXtsbm1());
		// 设置录入人名称
		CzzssdsjbForm.setLrr(userData.getYhid());

		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,这里存放CzzssdsjbForm
		vo.setData(CzzssdsjbForm);
		// 设置Proxy要调用的processor的类---HdzssdsjbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSJB2008_CZZSSDS_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean = this.setCheckInf(CzzssdsjbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// 调用Proxy，执行processor,获取CzzssdsjbForm返回值
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			// 操作成功流转
			return mapping.findForward("Show");
		} catch (Exception ex) {
			CzzssdsjbForm.reset(mapping,request);
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
	 *            CzzssdsjbForm
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
		// 当前的ActionForm---CzzssdsjbForm
		CzzssdsjbForm czzssdsjbForm = (CzzssdsjbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		czzssdsjbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsjbForm的DataList中，
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzssdsjbForm.setQysdsjbList(getRequestValuesToList(request,
				czzssdsjbForm.getColumns()));
		// 将CzzssdsjbForm放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), czzssdsjbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzssdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJB2008_CZZSSDS_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取CzzssdsjbForm返回值
			czzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm中的基本数据置空
			if(!("1".equals(request.getParameter("lje_nsfs"))&&"1".equals(request.getParameter("lje_zfjg")))){
				czzssdsjbForm.reset(mapping, request);
				czzssdsjbForm.setQysdsjbList(new ArrayList());
			}
			czzssdsjbForm.setSAVE_FLAG("1");//修改标志位
			System.out.println("存储标记="+czzssdsjbForm.getSAVE_FLAG());
			// 将CzzssdsjbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzssdsjbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "查帐征收企业企业所得税季度申报表保存成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "查帐征收企业企业所得税季度申报表保存失败！"+ex.getMessage());
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
		// 当前的ActionForm---CzzssdsjbForm
		CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		CzzssdsjbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		CzzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsjbForm的DataList中，
		CzzssdsjbForm.setQysdsjbList(getRequestValuesToList(request,
				CzzssdsjbForm.getColumns()));
		// 将CzzssdsjbForm放入request,当校验失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(CzzssdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJBNEW_HDZSSDS_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取CzzssdsjbForm返回值
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
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
	 *            CzzssdsjbForm
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
		// 当前的ActionForm---CzzssdsjbForm
		CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
		// 设置录入人名称
		CzzssdsjbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		CzzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsjbForm的DataList中，
		CzzssdsjbForm.setQysdsjbList(getRequestValuesToList(request,
				CzzssdsjbForm.getColumns()));
		// 将CzzssdsjbForm放入request,当删除失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(CzzssdsjbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJB2008_CZZSSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			// 调用Proxy，执行processor,获取CzzssdsjbForm返回值
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm中的基本数据置空
			CzzssdsjbForm.reset(mapping, request);
			// 将CzzssdsjbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			// 操作成功流转，该提示信息在头文件header.jsp中获取
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "查帐征收企业企业所得税季度申报表保存失败！"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	private List getRequestValuesToList(HttpServletRequest request,String []columns){
		List arrayList=new ArrayList();
		Map map=null;
		map=new HashMap();
		map.put("hc","1");
		System.out.println("request.getParameter(\"lje_nsfs\")="+request.getParameter("lje_nsfs"));
		map.put("lje",request.getParameter("lje_nsfs"));
		arrayList.add(map);
		map=new HashMap();
		map.put("hc","2");
		System.out.println("request.getParameter(\"lje_zfjg\")="+request.getParameter("lje_zfjg"));
		map.put("lje",request.getParameter("lje_zfjg"));
		arrayList.add(map);
		List tmpList=SfRequestUtil.getValuesToList(request,columns);
		for(int i=0;i<tmpList.size();i++){
			arrayList.add(tmpList.get(i));
		}
		return arrayList;
	}

	/**
	 * @Description: TODO 设置校验参数
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(CzzssdsjbForm CzzssdsjbForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(CzzssdsjbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2008");
		checkBean.setVersionEndTime("2011");
		
		checkBean.setJsjdm(CzzssdsjbForm.getJsjdm());
		
		checkBean.setSkssrqq(CzzssdsjbForm.getSkssksrq());
		checkBean.setSkssrqz(CzzssdsjbForm.getSkssjsrq());
		return checkBean;
	}
}
