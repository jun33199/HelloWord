/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.dm.BZ;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.kjqysds.*;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web.KjssjksForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>
 * Title: 北京地税综合管理系统 扣缴企业所得税报告表-上门模块
 * </p>
 * <p>
 * Description:扣缴企业所得税报告表
 * </p>
 *
 * @author wang xiaomin
 * @version 1.1
 */

public class KjqysdsbgbAction extends SmsbAction {
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
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">扣缴企业所得税</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "扣缴企业所得税报告表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	/**
	 * 查询已申报数据
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

	public ActionForward doQuery1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// 当前的ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		kjqysdsbgbForm.setLrr(userData.getYhid());
		//包装前台信息BO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//包装后台信息BO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		//把前台form放入qbo
		qbo.setJsjdm(kjqysdsbgbForm.getJsjdm());
		qbo.setDqzt(kjqysdsbgbForm.getDqzt());
		qbo.setDqy(kjqysdsbgbForm.getDqy());
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
		// 设置容器里的Data数据,这里存放KjqysdsbgbForm
		vo.setData(qbo);
		// 设置Proxy要调用的processor的类---KjqysdsbgbForm
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			// 调用Proxy，执行processor,获取hdzssdsjbForm返回值
			System.out.println("----------kjqysdsbgbAction-------------------");
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			//将hbo接收到的信息放入form中
			kjqysdsbgbForm.setQysdsbgbList(hbo.getQysdsbgbList());
			kjqysdsbgbForm.setZts(hbo.getZts());
			kjqysdsbgbForm.setZys(hbo.getZys());
			kjqysdsbgbForm.setDqy(hbo.getDqy());
			kjqysdsbgbForm.setOldzt(kjqysdsbgbForm.getDqzt());
			// 将kjqysdsbgbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
			// 操作成功流转
			return mapping.findForward("Show");
		} catch (Exception ex) {
			kjqysdsbgbForm.reset(mapping,request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
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
			// 获取HdzssdsjbForm对象
			KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
			//包装前台信息BO
			KjqysdsbgbBO qbo = new KjqysdsbgbBO();
			//包装后台信息BO
			KjqysdsbgbBO hbo = new KjqysdsbgbBO();
			// 获取本缴费系统用户信息
			UserData userData = this.getUserData(request);
			// 设置录入人名称
			kjqysdsbgbForm.setLrr(userData.getYhid());
			// 初始化数据传递容器
			VOPackage vo = new VOPackage();
			// 设置后台调用Action值---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// 设置容器里的Data数据,这里存放KjqysdsbgbForm
			vo.setData(qbo);
			// 设置本缴费系统用户信息
			vo.setUserData(userData);
			// 设置Proxy要调用的processor的类---KjqysdsbgbProcessor
			vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
			// 调用Proxy，初始化HdzssdsjbForm中值
			//hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			// 将KjqysdsbgbForm 放入request中
			request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);

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
	 *            HdzssdsjbForm
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
		// 当前的ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		kjqysdsbgbForm.setLrr(userData.getYhid());
		//包装前台信息BO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//包装后台信息BO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		//把前台form放入qbo
		//kjqysdsbgbForm.setJsjdm("06025800");
		
		qbo.setJsjdm(kjqysdsbgbForm.getJsjdm());
		qbo.setBadjxh(kjqysdsbgbForm.getBadjxh());
		qbo.setBgbxh(kjqysdsbgbForm.getBgbxh());
		
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,这里存放KjqysdsbgbForm
		vo.setData(qbo);
		// 设置Proxy要调用的processor的类---KjqysdsbgbForm
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			// 调用Proxy，执行processor,获取hdzssdsjbForm返回值
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			if(hbo.isFlag()){
				//将hbo接收到的信息放入form中
				kjqysdsbgbForm=botoform(hbo);		
				//初始化页面列表
				// 获取session
		        HttpSession session = request.getSession(false);
				if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX) == null) {
		            List sbsdlxList = hbo.getSbsdlxList();
	
		            // 转换之后的申报所得类型
		            String[][] sbsdlx = new String[sbsdlxList.size()][2];
		            for (int i = 0; i < sbsdlxList.size(); i++) {
		            	sbsdlx[i][0] = ((SBSDLX) sbsdlxList.get(i)).getSbsdlxdm();
		            	sbsdlx[i][1] = ((SBSDLX) sbsdlxList.get(i)).getSbsdlxmc();
		            }
		            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX, sbsdlx);
		        }
		        // 获取币种列表
		        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) == null) {
		        	List bzList = CodeTableUtil.getCodeTableList(CodeTableKey.BZ);
	
		            // 转换之后的币种
		            String[][] bz = new String[bzList.size()][2];
		            for (int i = 0; i < bzList.size(); i++) {
		                bz[i][0] = ((BZ) bzList.get(i)).getBzdm();
		                bz[i][1] = ((BZ) bzList.get(i)).getBzmc();
		            }
		            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ, bz);
		        }
				// 将kjqysdsbgbForm置入request,作为回显数据用
				request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
				// 操作成功流转
				return mapping.findForward("Query");
			}
			else{
				// 将kjqysdsbgbForm置入request,作为回显数据用
				request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
				// 操作成功流转
				request.setAttribute(CodeConstant.SMSB_OTHER_SUCCESS, "此备案未确认,请先进行备案确认!");
				return doQuery1(mapping,kjqysdsbgbForm,request,response);
			}
		} catch (Exception ex) {
			kjqysdsbgbForm.reset(mapping,request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 保存报告表数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form 
	 *            KjqysdsbgbForm
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
		// 当前的ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		//包装前台信息BO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//包装后台信息BO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		// 获取本缴费系统用户信息
		userData = this.getUserData(request);
		// 设置录入人名称
		qbo.setLrr(userData.getYhid());
		// 按照columns中的字段提取所需要的前台列表数据，赋给KjqysdsbgbForm的DataList中，
		kjqysdsbgbForm.setQysdsbgbList(SfRequestUtil.getValuesToList(request,
				kjqysdsbgbForm.getBgb_columns()));
		//录入人
		kjqysdsbgbForm.setLrr(userData.getYhid());
		System.out.println("-------------"+kjqysdsbgbForm.getBahtxx().getHtbh());
		// 将kjqysdsbgbForm放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
		
		// 将form放入hbo
		qbo=this.formtobo(kjqysdsbgbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(qbo);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取hbo返回值
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			kjqysdsbgbForm.setBgbxh(hbo.getBgbxh());

			/*KjssjksForm kjssjksForm=new KjssjksForm();
			kjssjksForm.setJsjdm(kjqysdsbgbForm.getJsjdm());
			kjssjksForm.setBadjxh(kjqysdsbgbForm.getBadjxh());
			kjssjksForm.setBgbxh(kjqysdsbgbForm.getBgbxh());
			System.out.println("kjqysdsbgbForm--------"+kjqysdsbgbForm.getBgbxh());*/
			request.setAttribute("bgbxh",kjqysdsbgbForm.getBgbxh());
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "报告表保存成功,请继续开具缴款书！");
			return mapping.findForward("Save");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}


	/**
	 * 删除报告表数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            KjqysdsbgbForm
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
		// 当前的ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		//包装前台信息BO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//把前台form数据放入qbo
		qbo.setJsjdm(kjqysdsbgbForm.getJsjdm());
		qbo.setBadjxh(kjqysdsbgbForm.getBadjxh());
		qbo.setBgbxh(kjqysdsbgbForm.getBgbxh());
		qbo.setDqzt(kjqysdsbgbForm.getDqzt());
		//包装后台信息BO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		// 设置录入人名称
		kjqysdsbgbForm.setLrr(userData.getYhid());
		// 将hkjqysdsbgbForm放入request,当删除失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(qbo);
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		vo.setUserData(userData);
		try {
			// 调用Proxy，执行processor,获取hbo返回值
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			//重新取值
			vo.setAction(CodeConstant.SMSB_QUERYACTION1);
			vo.setData(qbo);
			vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
			// 调用Proxy，执行processor,获取hbo返回值
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			//将hbo接收到的信息放入form中
			kjqysdsbgbForm.setQysdsbgbList(hbo.getQysdsbgbList());
			kjqysdsbgbForm.setZts(hbo.getZts());
			kjqysdsbgbForm.setZys(hbo.getZys());
			kjqysdsbgbForm.setDqy(hbo.getDqy());
			kjqysdsbgbForm.setDqzt(hbo.getDqzt());
			// 将kjqysdsbgbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
			// 操作成功流转，该提示信息在头文件header.jsp中获取
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	/**
	 * 把form中的数据放入bo
	 * @param form
	 * @return bo
	 */
	private KjqysdsbgbBO formtobo(KjqysdsbgbForm form){
		KjqysdsbgbBO bo=new KjqysdsbgbBO();
		
		bo.setQysdsbgbList(form.getQysdsbgbList());
		bo.setFjmqyxx(form.getFjmqyxx());
		bo.setKjywrxx(form.getKjywrxx());
		bo.setBahtxx(form.getBahtxx());
		System.out.println("bo.htbh---"+form.getBahtxx().getHtbh());
		bo.setSbsdlxdm(form.getSbsdlxdm());
		bo.setSbsdqdrq(form.getSbsdqdrq());
		bo.setJsjdm(form.getJsjdm());
		bo.setBadjxh(form.getBadjxh());
		bo.setLrr(form.getLrr());
		bo.setBgbxh(form.getBgbxh());
		System.out.println("bo.bgbxh------"+bo.getBgbxh()+"form.bgxh---------"+form.getBgbxh());
		bo.setSkssksrq(form.getSkssksrq());
		bo.setSkssjsrq(form.getSkssjsrq());
		return bo;
	}
	/**
	 * 把bo中的数据放入form
	 * @param bo
	 * @return form
	 */
	private KjqysdsbgbForm botoform(KjqysdsbgbBO bo){
		KjqysdsbgbForm form=new KjqysdsbgbForm();
		
		//将bo接收到的信息放入form中
		form.setSkssksrq(bo.getSkssksrq());
		form.setSkssjsrq(bo.getSkssjsrq());
		form.setKjywrxx(bo.getKjywrxx());
		form.setFjmqyxx(bo.getFjmqyxx());
		form.setBahtxx(bo.getBahtxx());
		form.setQysdsbgbList(bo.getQysdsbgbList());
		form.setSbsdlxdm(bo.getSbsdlxdm());
		form.setSbsdqdrq(bo.getSbsdqdrq());
		form.setJsjdm(bo.getJsjdm());
		form.setBadjxh(bo.getBadjxh());
		form.setBgbxh(bo.getBgbxh());
		return form;
	}
	
	
}
