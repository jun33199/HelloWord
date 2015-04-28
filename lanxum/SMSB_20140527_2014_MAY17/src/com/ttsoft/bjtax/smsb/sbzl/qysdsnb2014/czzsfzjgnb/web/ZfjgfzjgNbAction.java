package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web;


/**
 * <p>Title: 北京地税综合管理系统 申报征收-上门模块-企业所得税分支机构年报2014版-汇总纳税分支机构分配表</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 *
 * @author wangcy
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsCzzsNb2014Util;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;

public class ZfjgfzjgNbAction extends SmsbAction
{
    public ZfjgfzjgNbAction()
    {
    }

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
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;<font color=\"#7C9AAB\">企业所得税汇总纳税分支机构分配表</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                "企业所得税汇总纳税分支机构分配表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }

    /**
     * 第一次录入从session中去数据
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            ZfjgfzjgNbForm
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
    	
        // 获取ZfjgfzjgNbForm对象
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
       
        
        CzzsfzjgNbForm czzsfzjgNbForm=(CzzsfzjgNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSFZJGNBFORM");
        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 设置报表期类型-年报
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // 设置税务计算机代码
        // ZfjgfzjgNbForm.setSwjsjdm(userData.getXtsbm1());
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());
        
        Map pDataMap = new HashMap();
        pDataMap.put("CzzsfzjgNbForm", czzsfzjgNbForm);
		pDataMap.put("ZfjgfzjgNbForm", zfjgForm);
		
        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置后台调用Action值---QUERYACTION
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        // 设置容器里的Data数据
        vo.setData(pDataMap);
        // 设置Proxy要调用的processor的类---ZfjgqysdsNbProcessor
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);
        // 设置系统用户信息
        vo.setUserData(userData);
        try {
            // 调用Proxy，执行processor,获取zfjgForm返回值
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
            // 将ZfjgfzjgNbForm置入request,作为回显数据用
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            
            // 操作成功流转
            return mapping.findForward("Show");

        } catch (Exception ex) {

            zfjgForm.reset(mapping,request);
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
     *            ZfjgfzjgNbForm
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
    	
        // 获取ZfjgfzjgNbForm对象
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
        CzzsfzjgNbForm czzsfzjgNbForm=(CzzsfzjgNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSFZJGNBFORM");        
       
        
        System.out.println("jsjdm"+zfjgForm.getJsjdm());
        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 设置报表期类型-年报
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // 设置税务计算机代码
        // ZfjgfzjgNbForm.setSwjsjdm(userData.getXtsbm1());
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());

        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置后台调用Action值---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // 设置容器里的Data数据,这里存放ZfjgfzjgNbForm
        Map pDataMap = new HashMap();
        pDataMap.put("CzzsfzjgNbForm", czzsfzjgNbForm);
		pDataMap.put("ZfjgfzjgNbForm", zfjgForm);
        vo.setData(pDataMap);
        // 设置Proxy要调用的processor的类---ZfjgqysdsNbProcessor
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);
        // 设置系统用户信息
        vo.setUserData(userData);
        try {
            // 调用Proxy，执行processor,获取zfjgForm返回值
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
            // 将ZfjgfzjgNbForm置入request,作为回显数据用
            
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            
            // 操作成功流转
            return mapping.findForward("Show");

        } catch (Exception ex) {

            zfjgForm.reset(mapping,request);
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
     *            ZfjgfzjgNbForm
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
        // 获取ZfjgfzjgNbForm对象
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
        
        //保存主表数据
        this.doSaveCzzsZb(request, zfjgForm);

        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());
        // 设置报表期类型
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // 按照columns中的字段提取所需要的前台列表数据，赋给czqysdsjbForm的DataList中
        zfjgForm.setQysdsNbList(SfRequestUtil.getValuesToList(request,zfjgForm.getFzjgColumns()));
      
        System.out.println("nsrmc = " + zfjgForm.getNsrmc());

        // 将ZfjgfzjgNbForm放入request,当保存失败时，保证页面数据仍然显示
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置容器内容
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(zfjgForm);
        vo.setUserData(userData);
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);

        try {
            // 调用Proxy，执行processor,获取ZfjgfzjgNbForm返回值
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
            // 将ZfjgfzjgNbForm置入request,作为回显数据用
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            // 操作成功流转
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
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
     *            ZfjgfzjgNbForm
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
            throws BaseException
    {
        try {

            // 获取ZfjgqysdsjbForm对象
            ZfjgfzjgNbForm zfjgform = (ZfjgfzjgNbForm) form;
            String jsjdm = zfjgform.getJsjdm();
            String jumpStr = null;
            // 将CzzssdsjbForm 放入request中
            CzzsfzjgNbForm czzsForm=new CzzsfzjgNbForm();
            if(jsjdm != null && !jsjdm.equals(""))
            {
                czzsForm.setJsjdm(jsjdm);
                jumpStr = "Query";
            }
            else
            {
                jumpStr = "Jump";
            }
            request.setAttribute("czzsfzjgNbForm2014", czzsForm);
            System.out.println("jumpStr = " + jumpStr);
            return mapping.findForward(jumpStr);
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
     *            ZfjgfzjgNbForm
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
    	 System.out.println("doDelete ============Action ");
    	
        // -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 获取ZfjgfzjgNbForm对象
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
        // 按照columns中的字段提取所需要的前台列表数据，赋给zfjgForm的DataList中，
        zfjgForm.setQysdsNbList(SfRequestUtil.getValuesToList(request,zfjgForm.getFzjgColumns()));
        // 将zfjgForm放入request,当删除失败时，保证页面数据仍然显示
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());
        // 设置报表期类型
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置容器内容
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(zfjgForm);
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);
        vo.setUserData(userData);
        try {
            // 调用Proxy，执行processor,获取zfjgForm返回值
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
			System.out.println("I am jumping to Czzssdsjb....");
			// 获取CzzssdsjbForm对象
	
			return mapping.findForward("Jump");
            
        } catch (Exception ex) {
            // 系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
	 * 保存申报主表数据
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
	public void doSaveCzzsZb(HttpServletRequest request, ZfjgfzjgNbForm zfjgForm)throws BaseException, ApplicationException {
		
		// 当前的ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSFZJGNBFORM");
		QysdsCzzsNb2014Util qysdsUtil = new QysdsCzzsNb2014Util();
		// 获取本系统用户信息
		UserData userData = this.getUserData(request);
		

		Map pData = new HashMap();
		
		String nsfsNow = czzsfzjgNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzsfzjgNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzsfzjgNbForm", czzsfzjgNbForm);
		pData.put("fpb_fzjgftse", zfjgForm.getFzjgftse());
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
			if(!("1".equals(czzsfzjgNbForm.getNsfs()))){
				czzsfzjgNbForm.setQysdsnbList(new ArrayList());
			}
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
			request.getSession(false).setAttribute("REQ_KEY_CZZSFZJGNBFORM",czzsfzjgNbForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex,"企业所得税分支机构年度纳税申报表保存失败！");
		}
	}
}
