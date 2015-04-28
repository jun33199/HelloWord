package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsCzzsNb2014Util;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlx;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;
/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:2014 企业所得税分支机构年度纳税申报表
 * </p>
 *
 * @author wangcy
 * @version 1.0
 */

public class CzzsfzjgNbAction  extends SmsbAction {
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

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,HttpServletRequest httpServletRequest, HttpServletResponse response)
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税分支机构年度纳税申报表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业所得税分支机构年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            czzsfzjgNbForm
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
			// 获取czzsfzjgNbForm对象
			CzzsfzjgNbForm czzsfzjgNbForm = new CzzsfzjgNbForm();
			// 获取本系统用户信息
			UserData userData = this.getUserData(request);
			// 设置录入人名称
			czzsfzjgNbForm.setLrr(userData.getYhid());
			// 初始化数据传递容器
			VOPackage vo = new VOPackage();
			// 设置后台调用Action值---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// 设置容器里的Data数据,这里存放czzsfzjgNbForm
			vo.setData(czzsfzjgNbForm);
			// 设置本系统用户信息
			vo.setUserData(userData);
			// 设置Proxy要调用的processor的类---CzzssdsNbProcessor
			vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
			// 调用Proxy，初始化czzsfzjgNbForm中值
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			// 将czzsfzjgNbForm 放入request中
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
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
	 *            czzsfzjgNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws BaseException
	{System.out.println("doquery。。。。。。。。。。。。。。。。。。。");
		// 当前的ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(czzsfzjgNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzsfzjgNbForm.setSybs(sybs);
		czzsfzjgNbForm.setIsQuery("1");

		
		
		Timestamp skssksrq = QysdsCzzsNb2014Util.getTimestamp(czzsfzjgNbForm.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			throw new ApplicationException("该企业税务登记开业登记日期在税款所属期内，不需填写企业所得税分支机构年度纳税申报表!");
        }
		// 设置报表期类型
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		System.out.println("getBbqlx====="+czzsfzjgNbForm.getBbqlx());
		// 设置录入人名称
		czzsfzjgNbForm.setLrr(userData.getYhid());

		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,这里存放czzsfzjgNbForm
		vo.setData(czzsfzjgNbForm);
		// 设置Proxy要调用的processor的类
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean = this.setCheckInf(czzsfzjgNbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			//if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)){
			if(!CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())){
				// 将czzsfzjgNbForm置入request,作为回显数据用
				request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
				throw new ApplicationException("该企业不是跨省市分支机构，不需填写企业所得税分支机构年度纳税申报表!");
			}
				
			// 调用Proxy，执行processor,获取czzsfzjgNbForm返回值
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);	
			czzsfzjgNbForm.setJdlx(checkBean.getJdlx());
			if("04".equals(checkBean.getJdlx()))
			{checkBean.setJdlx("01");}
			czzsfzjgNbForm.setSybs(checkBean.getJdlx());
			
			// 将czzsfzjgNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
		} catch (Exception ex) {
			czzsfzjgNbForm.reset(mapping,request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		// 操作成功流转
		return mapping.findForward("Show");
	}
	
	
	/**
	 * 保存申报数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            czzsfzjgNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 * @throws ApplicationException 
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws BaseException, ApplicationException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// 当前的ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		QysdsCzzsNb2014Util qysdsUtil = new QysdsCzzsNb2014Util();
		// 获取本系统用户信息
		userData = this.getUserData(request);
		
		SWDJJBSJ djsj = null;
		
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(czzsfzjgNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzsfzjgNbForm.setSybs(sybs);
		String sybs="";
		sybs = czzsfzjgNbForm.getSybs();
		czzsfzjgNbForm.setIsQuery("1");

		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// 将czzsfzjgNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			throw new ApplicationException("该企业的税源标识不为分，填写的企业所得税分支机构年度纳税申报表数据不予保存!");
		}
		
		// 设置录入人名称
		czzsfzjgNbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给czzsfzjgNbForm的DataList中，
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzsfzjgNbForm.setQysdsnbList(getRequestValuesToList(request,czzsfzjgNbForm));
		// 将CzczzsfzjgNbFormrequest,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);

		Map pData = new HashMap();
		
		String nsfsNow = czzsfzjgNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzsfzjgNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzsfzjgNbForm", czzsfzjgNbForm);
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//查询A类表中纳税方法与总分机构
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzsfzjgNbForm.getNsfs_fsjg().get("nsfs"))) || czzsfzjgNbForm.getNsfs_fsjg().get("nsfs")==null){
			czzsfzjgNbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzsfzjgNbForm.getNsfs_fsjg().get("zfjg"))) || czzsfzjgNbForm.getNsfs_fsjg().get("zfjg")==null){
			czzsfzjgNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzsfzjgNbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzsfzjgNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzsfzjgNbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzsfzjgNbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);
		
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzsfzjgNbForm);
		vo.setUserData(userData);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取czzsfzjgNbForm返回值
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm中的基本数据置空
			//if(!("1".equals(request.getParameter("lje_nsfs"))&&"1".equals(request.getParameter("lje_zfjg")))){
			if(!("1".equals(czzsfzjgNbForm.getNsfs()))){
				czzsfzjgNbForm.reset(mapping, request);
				czzsfzjgNbForm.setQysdsnbList(new ArrayList());
			}
			czzsfzjgNbForm.setSAVE_FLAG("1");//修改标志位
			System.out.println("存储标记="+czzsfzjgNbForm.getSAVE_FLAG());
			
			System.out.println("===================save process end =========================");
			String nsfsOld = (String)nsfs_zfjg.get("nsfs_old");
			String zfjgOld = (String)nsfs_zfjg.get("zfjg_old");
			
			if(nsfsNow.equals("1")){
				if(!zfjgOld.equals(zfjgNow)){
					qysdsUtil.deleteCascadeZfjgData(pData);
				}
				qysdsUtil.saveCascadeZfjgData(pData);
			}else{
				qysdsUtil.deleteCascadeZfjgData(pData);
			}			
			// 将czzsfzjgNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "企业所得税分支机构年度纳税申报表保存成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "企业所得税分支机构年度纳税申报表保存失败！"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	private List getRequestValuesToList(HttpServletRequest request,CzzsfzjgNbForm form){
		List arrayList=new ArrayList();
		Map map=null;
		map=new HashMap();
		map.put("hc","1");
		
		map.put("lje",form.getNsfs());
		arrayList.add(map);
		map=new HashMap();
		map.put("hc","2");
		
		map.put("lje",form.getZfjg());
		arrayList.add(map);
		List tmpList=SfRequestUtil.getValuesToList(request,form.getColumns());
		for(int i=0;i<tmpList.size();i++){
			arrayList.add(tmpList.get(i));
		}
		return arrayList;
	}
	
	
	/**
	 * 跳转页面数据
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            czzsfzjgNbForm
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

		// 当前的ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(czzsfzjgNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzsfzjgNbForm.setSybs(sybs);
		String sybs = czzsfzjgNbForm.getSybs();
		czzsfzjgNbForm.setIsQuery("1");
		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// 将czzsfzjgNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			throw new ApplicationException("该企业的税源标识不为分，填写的企业所得税分支机构年度纳税申报表数据不予保存!");
		}
		// 设置录入人名称
		czzsfzjgNbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给czzsfzjgNbForm的DataList中，
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzsfzjgNbForm.setQysdsnbList(getRequestValuesToList(request,czzsfzjgNbForm));
		try {
			// 将zfjgfzjgNbForm 放入request中
			ZfjgfzjgNbForm zfjgfzjgNbForm=new ZfjgfzjgNbForm();
			zfjgfzjgNbForm.setJsjdm(czzsfzjgNbForm.getJsjdm());

			//将czzsfzjgNbForm放入session中
			request.getSession(false).setAttribute("REQ_KEY_CZZSFZJGNBFORM", czzsfzjgNbForm);
			
			request.setAttribute("zfjgfzjgNbForm2014", zfjgfzjgNbForm);
			request.setAttribute("czzsfzjgNbForm2014", czzsfzjgNbForm);
			
			if(czzsfzjgNbForm.getQueryFlag().equals("0")){
				return mapping.findForward("JumpNew");
			}
			return mapping.findForward("Jump");
//			return mapping.findForward("JumpNew");
//			return new ActionForward("/webapp/smsb/qysds/2014/zfjgfzjgNbAction2014.do?actionType=Query");
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
	 *            czzsfzjgNbForm
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
		// 当前的ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		// 设置录入人名称
		czzsfzjgNbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsjbForm的DataList中，
		czzsfzjgNbForm.setQysdsnbList(getRequestValuesToList(request,
				czzsfzjgNbForm));
		// 将CzzssdsjbForm放入request,当删除失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
		
		QysdsCzzsNb2014Util qysdsUtil = new QysdsCzzsNb2014Util();
		Map pData = new HashMap();
		pData.put("czzsfzjgNbForm", czzsfzjgNbForm);
		
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(czzsfzjgNbForm);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		vo.setUserData(userData);
		try {
			//删除从表中数据
			qysdsUtil.deleteCascadeZfjgData(pData);
			
			// 调用Proxy，执行processor,获取czzsfzjgNbForm返回值
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			// 将czzsfzjgNbForm中的基本数据置空
			czzsfzjgNbForm.reset(mapping, request);
			// 将czzsfzjgNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			
			// 操作成功流转，该提示信息在头文件header.jsp中获取
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "查帐征收企业企业所得税季度申报表保存失败！"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	 /**
	 * @Description: TODO 设置校验参数
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(CzzsfzjgNbForm czzsfzjgNbForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(czzsfzjgNbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2014");
		checkBean.setVersionEndTime("#");
		
		checkBean.setJsjdm(czzsfzjgNbForm.getJsjdm());
		
		checkBean.setSkssrqq(czzsfzjgNbForm.getSkssksrq());
		checkBean.setSkssrqz(czzsfzjgNbForm.getSkssjsrq());
		return checkBean;
	}
}
