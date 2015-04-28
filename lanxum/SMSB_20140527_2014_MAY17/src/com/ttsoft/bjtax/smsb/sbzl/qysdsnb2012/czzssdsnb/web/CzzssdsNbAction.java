package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.QysdsNb2012Util;
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

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:2012 企业所得税分支机构年度纳税申报表
 * </p>
 *
 * @author wangcy
 * @version 1.0
 */

public class CzzssdsNbAction  extends SmsbAction {
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
	 *            czzssdsNbForm
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
			// 获取czzssdsNbForm对象
			CzzssdsNbForm czzssdsNbForm = new CzzssdsNbForm();
			// 获取本系统用户信息
			UserData userData = this.getUserData(request);
			// 设置录入人名称
			czzssdsNbForm.setLrr(userData.getYhid());
			// 初始化数据传递容器
			VOPackage vo = new VOPackage();
			// 设置后台调用Action值---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// 设置容器里的Data数据,这里存放czzssdsNbForm
			vo.setData(czzssdsNbForm);
			// 设置本系统用户信息
			vo.setUserData(userData);
			// 设置Proxy要调用的processor的类---CzzssdsNbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
			// 调用Proxy，初始化czzssdsNbForm中值
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			// 将czzssdsNbForm 放入request中
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
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
	 *            czzssdsNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws BaseException
	{
		// 当前的ActionForm---czzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzssdsNbForm.setSybs(sybs);
		czzssdsNbForm.setIsQuery("1");

		
		
		Timestamp skssksrq = QysdsNb2012Util.getTimestamp(czzssdsNbForm.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			throw new ApplicationException("该企业税务登记开业登记日期在税款所属期内，不需填写企业所得税分支机构年度纳税申报表!");
        }
		// 设置报表期类型
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		System.out.println("getBbqlx====="+czzssdsNbForm.getBbqlx());
		// 设置录入人名称
		czzssdsNbForm.setLrr(userData.getYhid());

		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置后台调用Action值---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// 设置容器里的Data数据,这里存放CzzssdsnbForm
		vo.setData(czzssdsNbForm);
		// 设置Proxy要调用的processor的类
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		// 设置系统用户信息
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean = this.setCheckInf(czzssdsNbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)){
				// 将CzzssdsnbForm置入request,作为回显数据用
				request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
				throw new ApplicationException("该企业不是跨省市分支机构，不需填写企业所得税分支机构年度纳税申报表!");
			}
				
			// 调用Proxy，执行processor,获取CzzssdsnbForm返回值
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);	
			czzssdsNbForm.setJdlx(checkBean.getJdlx());
			if("04".equals(checkBean.getJdlx()))
			{checkBean.setJdlx("01");}
			czzssdsNbForm.setSybs(checkBean.getJdlx());
			
			// 将CzzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
		} catch (Exception ex) {
			czzssdsNbForm.reset(mapping,request);
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
	 *            czzssdsNbForm
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
		// 当前的ActionForm---CzzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		QysdsNb2012Util qysdsUtil = new QysdsNb2012Util();
		// 获取本系统用户信息
		userData = this.getUserData(request);
		
		SWDJJBSJ djsj = null;
		
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzssdsNbForm.setSybs(sybs);
		String sybs="";
		sybs = czzssdsNbForm.getSybs();
		czzssdsNbForm.setIsQuery("1");

		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// 将CzzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			throw new ApplicationException("该企业的税源标识不为分，填写的企业所得税分支机构年度纳税申报表数据不予保存!");
		}
		
		// 设置录入人名称
		czzssdsNbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsNbForm的DataList中，
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzssdsNbForm.setQysdsnbList(getRequestValuesToList(request,czzssdsNbForm));
		// 将CzzssdsNbForm放入request,当保存失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), czzssdsNbForm);

		Map pData = new HashMap();
		
		String nsfsNow = czzssdsNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzssdsNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzssdsNbForm", czzssdsNbForm);
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//查询A类表中纳税方法与总分机构
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzssdsNbForm.getNsfs_fsjg().get("nsfs"))) || czzssdsNbForm.getNsfs_fsjg().get("nsfs")==null){
			czzssdsNbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzssdsNbForm.getNsfs_fsjg().get("zfjg"))) || czzssdsNbForm.getNsfs_fsjg().get("zfjg")==null){
			czzssdsNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzssdsNbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzssdsNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzssdsNbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzssdsNbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);
		
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzssdsNbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		try {
			// 调用Proxy，执行processor,获取CzzssdsNbForm返回值
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			// 将CzzssdsjbForm中的基本数据置空
			//if(!("1".equals(request.getParameter("lje_nsfs"))&&"1".equals(request.getParameter("lje_zfjg")))){
			if(!("1".equals(czzssdsNbForm.getNsfs()))){
				czzssdsNbForm.reset(mapping, request);
				czzssdsNbForm.setQysdsnbList(new ArrayList());
			}
			czzssdsNbForm.setSAVE_FLAG("1");//修改标志位
			System.out.println("存储标记="+czzssdsNbForm.getSAVE_FLAG());
			
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
			// 将CzzssdsNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			// 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "企业所得税分支机构年度纳税申报表保存成功！");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "企业所得税分支机构年度纳税申报表保存失败！"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	private List getRequestValuesToList(HttpServletRequest request,CzzssdsNbForm form){
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
	 *            CzzssdsNbForm
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
		System.out.println("I am jumping....");
		// 当前的ActionForm---CzzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		// 获取本系统用户信息
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzssdsNbForm.setSybs(sybs);
		String sybs = czzssdsNbForm.getSybs();
		czzssdsNbForm.setIsQuery("1");
		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// 将CzzssdsnbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			throw new ApplicationException("该企业的税源标识不为分，填写的企业所得税分支机构年度纳税申报表数据不予保存!");
		}
		// 设置录入人名称
		czzssdsNbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsNbForm的DataList中，
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzssdsNbForm.setQysdsnbList(getRequestValuesToList(request,czzssdsNbForm));
		try {
			// 将zfjgqysdsNbForm 放入request中
			ZfjgqysdsNbForm zfjgqysdsNbForm=new ZfjgqysdsNbForm();
			zfjgqysdsNbForm.setJsjdm(czzssdsNbForm.getJsjdm());
			System.out.println("czzssdsNbForm.getJsjdm()"+czzssdsNbForm.getJsjdm());
			
			//将czzssdsNbForm放入session中
			request.getSession(false).setAttribute("REQ_KEY_CZZSSDSNBFORM", czzssdsNbForm);
			
			request.setAttribute("zfjgnb2012Form", zfjgqysdsNbForm);
			request.setAttribute("czzssdsnb2012Form", czzssdsNbForm);
			
			if(czzssdsNbForm.getQueryFlag().equals("0")){
				return mapping.findForward("JumpNew");
			}
			return mapping.findForward("Jump");

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
	 *            CzzssdsNbForm
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
		// 当前的ActionForm---czzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		// 设置录入人名称
		czzssdsNbForm.setLrr(userData.getYhid());
		// 设置报表期类型
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 按照columns中的字段提取所需要的前台列表数据，赋给CzzssdsjbForm的DataList中，
		czzssdsNbForm.setQysdsnbList(getRequestValuesToList(request,
				czzssdsNbForm));
		// 将CzzssdsjbForm放入request,当删除失败时，保证页面数据仍然显示
		request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
		
		QysdsNb2012Util qysdsUtil = new QysdsNb2012Util();
		Map pData = new HashMap();
		pData.put("czzssdsNbForm", czzssdsNbForm);
		
		// 初始化数据传递容器
		VOPackage vo = new VOPackage();
		// 设置容器内容
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(czzssdsNbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			//删除从表中数据
			qysdsUtil.deleteCascadeZfjgData(pData);
			
			// 调用Proxy，执行processor,获取czzssdsNbForm返回值
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			// 将czzssdsNbForm中的基本数据置空
			czzssdsNbForm.reset(mapping, request);
			// 将czzssdsNbForm置入request,作为回显数据用
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			
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
	private CheckBean setCheckInf(CzzssdsNbForm czzssdsNbForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(czzssdsNbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2013");
		checkBean.setVersionEndTime("2013");
		
		checkBean.setJsjdm(czzssdsNbForm.getJsjdm());
		
		checkBean.setSkssrqq(czzssdsNbForm.getSkssksrq());
		checkBean.setSkssrqz(czzssdsNbForm.getSkssjsrq());
		return checkBean;
	}
}
