package com.ttsoft.bjtax.smsb.jmssb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


public class CjrjyjmsbAction extends SmsbAction {

	private UserData userData = null;

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;安置残疾人就业企业营业税减免税申报表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"安置残疾人就业企业营业税减免税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/cjrjyjmsb/Cjrjyjmsb-000.htm");
	}

	public ActionForward doShow(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws BaseException {
		// 当前的ActionForm
		CjrjyjmsForm form = (CjrjyjmsForm) actionForm;
		userData = this.getUserData(httpServletRequest);
		form.setLrr(userData.getYhid());
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// 设置容器里的Data数据
		vo.setData(form);
		vo.setUserData(userData);
		// 设置Proxy要调用的processor的类名
		vo
				.setProcessor(CodeConstant.JMSSB_CJRJYJMSB_PROCESSOR);
		try {
			// 调用Proxy，初始化ActionForm中值
			form = (CjrjyjmsForm) SbzlProxy.getInstance().process(vo);
			//用于测试代码运行流程
			//System.out.println(form.getLrrq() + "------------------");
			httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
			return actionMapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			Debug.out("-------------------------");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public ActionForward doQuery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		CjrjyjmsForm form = (CjrjyjmsForm) actionForm;
		userData = this.getUserData(httpServletRequest);
		form.setLrr(userData.getYhid());
		// 补充默认展现需要带出的内容
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,在我们这儿就是ActionForm
		vo.setUserData(userData);
		vo.setData(form);
		// 设置Proxy要调用的processor的类名
		vo
				.setProcessor(CodeConstant.JMSSB_CJRJYJMSB_PROCESSOR);
		try {
			// 调用Proxy，初始化ActionForm中值
			//System.out.println("!!!!!!!!!!!!!!!!!!"+form.getIsError());
			form = (CjrjyjmsForm) SbzlProxy.getInstance().process(vo);
			// 流转
			//System.out.println("===============sing"+form.getSign_re());
			if("01".equals(form.getSign_re())){
				//System.out.println("=============ceshi===============");
				form.resetA(actionMapping, httpServletRequest);
			}
				httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
				httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
						form.getIsError());
			return actionMapping.findForward("Query");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			Debug.out("-------------------------");
			form.reset(actionMapping, httpServletRequest);
			form.setJsjdm("");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public ActionForward doSave(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
		if (forward != null) {
			return forward;
		}

		// 当前的ActionForm
		CjrjyjmsForm form = (CjrjyjmsForm) actionForm;
		userData = this.getUserData(httpServletRequest);
		form.setLrr(userData.getYhid());
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		// 设置容器里的Data数据,在我们这儿就是ActionForm
		vo.setData(form);
		// 设置Proxy要调用的processor的类名
		vo.setProcessor("com.ttsoft.bjtax.smsb.jmssb.processor.CjrjyjmsbProcessor");
		try {
			// 调用Proxy，初始化ActionForm中值
			form = (CjrjyjmsForm) SbzlProxy.getInstance()
					.process(vo);
				//form.reset(actionMapping, httpServletRequest);
				httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
				httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
						form.getIsError());
			//}
			return actionMapping.findForward("Save");
			// 流转
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			form.reset(actionMapping, httpServletRequest);
			form.setJsjdm("");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public ActionForward doDelete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		return null;
	}
}
