package com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.QysdsBbjcUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税核定申报信息
 * </p>
 * 
 * @author yugw
 * @version 1.1
 */

public class QysdsBbjcAction extends SmsbAction{
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;核定申报信息</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业所得税年度基本信息表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}
	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsBbjcForm
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
		System.out.println("企业所得税申报信息doShow start");
		//获取基本信息
		UserData userData = null;
		QysdsBbjcForm qysdsBbjcForm = (QysdsBbjcForm) form;
		qysdsBbjcForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qysdsBbjcForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor.QysdsBbjcProcessor");
		vo.setUserData(userData);
		try {
			//调用process
			qysdsBbjcForm= (QysdsBbjcForm) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			qysdsBbjcForm.setTbrq(sdf.format(curTime));
			List qysdssbfsList=qysdsBbjcForm.getQysdssbfsList();
			//把下拉框的list放入session，在页面解析
			request.getSession(false).setAttribute("qysdsbbjcList", qysdssbfsList);
			//设置查询结果
			request.setAttribute(mapping.getAttribute(), qysdsBbjcForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	/**
     * 查询
     * @param mapping struts.action.ActionMapping
     * @param form QysdsbbjcForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doQuery (ActionMapping mapping, ActionForm form,
    HttpServletRequest request,HttpServletResponse response)throws BaseException{
        HttpSession session=request.getSession(false);
		//查询前清除Session
		session.removeAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
    	UserData userData = null;
    	QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) form;
        userData = this.getUserData(request);
        SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(qysdsbbjcForm.getJsjdm(), userData);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(qysdsbbjcForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor.QysdsBbjcProcessor");
        vo.setUserData(userData);
        try
        {
        	qysdsbbjcForm = (QysdsBbjcForm) SbzlProxy.getInstance().process(vo);
			//查询成功
        	qysdsbbjcForm.setIsQuery("1");
            request.setAttribute(mapping.getAttribute(), qysdsbbjcForm);
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        	qysdsbbjcForm.reset(mapping, request);
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }
	
	/**
	 * 页面跳转,确定申报方式
	 * @param mapping
	 * @param QysdsBbjcForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doNextStep(ActionMapping mapping, ActionForm form,
	   HttpServletRequest request,HttpServletResponse response)throws BaseException{
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm)form;
		//QysdsBbjcUtil2014 util=new QysdsBbjcUtil2014();
		//QysdsBbjcForm xsQysdsbbjcForm=util.getAccount(qysdsbbjcForm);
		qysdsbbjcForm.setNsrmc((qysdsbbjcForm.getNsrmcSubmit()));
		qysdsbbjcForm.setNsrsbh(qysdsbbjcForm.getNsrsbhSubmit());
		qysdsbbjcForm.setSshymc(qysdsbbjcForm.getSshymcSubmit());
		qysdsbbjcForm.setSsjjlxmc(qysdsbbjcForm.getSsjjlxmcSubmit());
		System.out.println("纳税人名称22222"+(qysdsbbjcForm.getNsrmc()));
		//QysdsBbjcForm qysdsbbjcForm2 = qysdsbbjcForm;
		UserData userData=new UserData();
		VOPackage vo = new VOPackage();
		//设置后台调用Action值
		vo.setAction(CodeConstant.SMSB_NEXTSTEPACTION);
		// 设置容器里的Data数据,这里存放QysdsbbjcForm对象
		vo.setData(qysdsbbjcForm);
		// 设置本系统用户信息
		vo.setUserData(userData);
		// 设置Proxy要调用的processor的类---QysdssbxxProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDS_BBJC_PROCESSOR);
		try {
		  // 调用Proxy，初始化QysdsbbjcForm中值
		  qysdsbbjcForm= (QysdsBbjcForm) SbzlProxy.getInstance().process(vo);
		  // 将QysdsbjcForm的对象的值放入request中
		  request.setAttribute(mapping.getAttribute(), qysdsbbjcForm);
		} catch (Exception ex) {
			System.out.println("异常信息："+ex.getMessage());
			System.out.println("qysdsbbjcForm：纳税人名称"+qysdsbbjcForm.getNsrmc());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		    System.out.println("跳转前："+qysdsbbjcForm.getSkssksrq());
		    String path=qysdsbbjcForm.getPath();
		    return new ActionForward(path);
	 }
	
	
}
