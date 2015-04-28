/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jbxxb.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
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
 * Description:企业所得税年报基本信息表
 * </p>
 * 
 * @author lianglw
 * @version 1.1
 */

public class JbxxbAction2009 extends SmsbAction {


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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税基本信息表</td>");
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
	 *            JskffmxbForm
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
		UserData userData = null;
		//获取基本信息
		JbxxbForm2009 jbxxbForm = (JbxxbForm2009) form;
		jbxxbForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(jbxxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jbxxb.processor.JbxxbProcessor2009");
		vo.setUserData(userData);
		try {
			//调用process
			jbxxbForm = (JbxxbForm2009) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			jbxxbForm.setTbrq(sdf.format(curTime));

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
			
		}
		request.setAttribute(mapping.getAttribute(), jbxxbForm);
		return mapping.findForward("Show");
	}

	

	/**
	 * 保存申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JskffmxbForm
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
		ActionForward forward = this.doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		UserData userData = null;
		JbxxbForm2009 jbxxbForm = (JbxxbForm2009) form;
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(jbxxbForm.getJsjdm(), userData);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		  String sybs = jbxxbForm.getSybs();
		if(Integer.valueOf(jbxxbForm.getSknd()).intValue()>2012){
			if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_OTHER)){
				throw new ApplicationException("该企业的企业所得税不由地方税务局管辖!");
			}
			if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
				throw new ApplicationException("该企业只需填报企业所得税分支机构年度纳税申报表！");
			}
		}
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(jbxxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jbxxb.processor.JbxxbProcessor2009");
		vo.setUserData(userData);
		try {

			jbxxbForm = (JbxxbForm2009) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			jbxxbForm.setTbrq(sdf.format(curTime));
			this.getSybsMc(jbxxbForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jbxxbForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
		return mapping.findForward("Save");
	}
	/**
     * 删除申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = this.doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        UserData userData = null;

        JbxxbForm2009 jbxxbForm = (JbxxbForm2009) form;
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(jbxxbForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //调用processor
            SbzlProxy.getInstance().process(vo);
            jbxxbForm.reset(mapping, request);
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
        return mapping.findForward("Delete");
    }
	
    /**
     * 查询已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
    	//modify   by  yugw  
/*//    	-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = this.doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }*/
    	UserData userData = null;

    	JbxxbForm2009 jbxxbForm = (JbxxbForm2009) form;
        userData = this.getUserData(request);
        
        SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(jbxxbForm.getJsjdm(), userData);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}
		
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(jbxxbForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jbxxb.processor.JbxxbProcessor2009");
        vo.setUserData(userData);
        try
        {
        	//调用校验
			CheckBean checkBean = this.setCheckInf(jbxxbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
        	
        	jbxxbForm = (JbxxbForm2009) SbzlProxy.getInstance().process(vo);
			//查询成功
			jbxxbForm.setIsQuery("1");
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			jbxxbForm.setTbrq(sdf.format(curTime));
			
			jbxxbForm.setSybs(checkBean.getJdlx());		//设置税源标识;
			
            request.setAttribute(mapping.getAttribute(), jbxxbForm);
            this.getSybsMc(jbxxbForm);
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        	jbxxbForm.reset(mapping, request);
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        
        //String sybs=FriendHelper.getNsrSybs(djsj);
        String sybs = jbxxbForm.getSybs();
		if(Integer.valueOf(jbxxbForm.getSknd()).intValue()>2012){
			if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_OTHER)){
				throw new ApplicationException("该企业的企业所得税不由地方税务局管辖!");
			}
			if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
				throw new ApplicationException("该企业只需填报企业所得税分支机构年度纳税申报表！");
			}
		}
        return mapping.findForward("Query");
    }
	/**
	 * 退出页面
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
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
	 * 根据税源标识获取对应的税源标识名称
	 * @param jbxxbForm
	 */
	public void getSybsMc(JbxxbForm2009 jbxxbForm){
		String sybs=jbxxbForm.getSybs();
		if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_DLNSR)){
			jbxxbForm.setSybsmc(CodeConstant.CODE_NAME_QYSDS_ZGFWJD_DLNSR);
		}
		if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG)){
			jbxxbForm.setSybsmc(CodeConstant.CODE_NAME_QYSDS_ZGFWJD_KSSZJG);
		}
		if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			jbxxbForm.setSybsmc(CodeConstant.CODE_NAME_QYSDS_ZGFWJD_KSSFZJG);
		}
		if(sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_ZFJGJZBSZJG)){
			jbxxbForm.setSybsmc(CodeConstant.CODE_NAME_QYSDS_ZGFWJD_ZFJGJZBSZJG);
		}
	}
	
	/**
	 * @Description: TODO 设置校验参数
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(JbxxbForm2009 jbxxbForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//校验版本材料
		checkBean.setCurrentTime(jbxxbForm.getSknd());
		checkBean.setVersionStartTime("2009");
		checkBean.setVersionEndTime("2012");
		
		//校验清算期的材料
		checkBean.setJsjdm(jbxxbForm.getJsjdm());
		
		//校验征管范围鉴定类型材料
		checkBean.setSkssrqq(jbxxbForm.getSknd()+"0101");
		checkBean.setSkssrqz(jbxxbForm.getSknd()+"1231");
		return checkBean;
	}
}