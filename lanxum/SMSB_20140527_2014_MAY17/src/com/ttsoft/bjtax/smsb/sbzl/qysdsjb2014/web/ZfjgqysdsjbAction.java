package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: 北京地税综合管理系统 申报征收-上门模块-企业所得税季报2014版-汇总纳税分支机构分配表</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author zhangyj
 * @version 1.0
 */

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class ZfjgqysdsjbAction extends SmsbAction
{
    public ZfjgqysdsjbAction()
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
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;<font color=\"#7C9AAB\">汇总纳税分支机构分配表</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                "汇总纳税分支机构分配表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }

    /**
     * 初始化页面数据
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */

/*    public ActionForward doShow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {

        try {
            // 获取ZfjgqysdsjbForm对象
            ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
            // 获取本缴费系统用户信息
            UserData userData = this.getUserData(request);
            // 初始化数据传递容器
            VOPackage vo = new VOPackage();
            // 设置后台调用Action值---SHOWACTION
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            // 设置容器里的Data数据
            vo.setData(zfjgForm);
            // 设置本缴费系统用户信息
            vo.setUserData(userData);
            // 设置Proxy要调用的processor的类---ZfjgqysdsjbProcessor
            vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);

            // 调用Proxy，初始化CzqysdsjbForm中值
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // 将HdzssdsjbForm 放入request中
            System.out.println("mapping.getAttribute() = " + mapping.getAttribute());
            request.setAttribute(mapping.getAttribute(), zfjgForm);

            return mapping.findForward("Show");

        } catch (Exception ex) {
            // 系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }*/

    /**
     * 查询已申报数据
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
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
    	
//    	CzzssdsjbForm czzssdsjbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjbForm");
    	
        // 获取ZfjgqysdsjbForm对象
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 设置报表期类型-季报
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // 设置税务计算机代码
        // czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());

        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置后台调用Action值---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // 设置容器里的Data数据,这里存放ZfjgqysdsjbForm
        vo.setData(zfjgForm);
        // 设置Proxy要调用的processor的类---ZfjgqysdsjbProcessor
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);
        // 设置系统用户信息
        vo.setUserData(userData);
        try {
            // 调用Proxy，执行processor,获取zfjgForm返回值
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // 将czqysdsjbForm置入request,作为回显数据用
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
     *            CzqysdsjbForm
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
        // 获取ZfjgqysdsjbForm对象
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());
        // 设置报表期类型
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // 按照columns中的字段提取所需要的前台列表数据，赋给czqysdsjbForm的DataList中
        System.out.println("length = " + zfjgForm.getFzjgColumns().length);
        System.out.println("Column[0] = " + zfjgForm.getFzjgColumns()[0]);
        System.out.println("Column[1] = " + zfjgForm.getFzjgColumns()[1]);
        zfjgForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
                zfjgForm.getFzjgColumns()));
        System.out.println("list size = " + zfjgForm.getQysdsjbList().size());
        
        System.out.println("nsrmc = " + zfjgForm.getNsrmc());

        // 将czqysdsjbForm放入request,当保存失败时，保证页面数据仍然显示
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置容器内容
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(zfjgForm);
        vo.setUserData(userData);
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);

        try {
            // 调用Proxy，执行processor,获取czqysdsjbForm返回值
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // 将czqysdsjbForm中的基本数据置空
//            zfjgForm.reset(mapping, request);
            // 将czqysdsjbForm置入request,作为回显数据用
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            // 操作成功流转
            
            zfjgForm.setSAVE_FLAG("1");//1表示保存成功，0标识保存失败，added by zhangj 2014.11.28
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
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
     *            CzqysdsjbForm
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
        // 获取ZfjgqysdsjbForm对象
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
        // 按照columns中的字段提取所需要的前台列表数据，赋给zfjgForm的DataList中，
        zfjgForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
                zfjgForm.getFzjgColumns()));
        // 将zfjgForm放入request,当删除失败时，保证页面数据仍然显示
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // 设置录入人名称
        zfjgForm.setLrr(userData.getYhid());
        // 设置报表期类型
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置容器内容
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(zfjgForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_ZFJG_PROCESSOR);
        vo.setUserData(userData);
        try {
            // 调用Proxy，执行processor,获取zfjgForm返回值
            zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
            // 将zfjgForm中的基本数据置空
//            zfjgForm.reset(mapping, request);
//            // 将zfjgForm置入request,作为回显数据用
//            request.setAttribute(mapping.getAttribute(), zfjgForm);
//            // 操作成功流转，该提示信息在头文件header.jsp中获取
//            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
//            //this.doJump(mapping, zfjgForm, request, response);
//            return mapping.findForward("Show");
            
			System.out.println("I am jumping to Czzssdsjb....");
			// 获取CzzssdsjbForm对象
			CzzssdsjbForm CzzssdsjbForm = new CzzssdsjbForm();

			// 将CzzssdsjbForm 放入request中
			//request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			//

			CzzssdsjbForm.setJsjdm(zfjgForm.getJsjdm());
			request.setAttribute("czzssdsjbForm", CzzssdsjbForm);
			return mapping.findForward("Jump");
            
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
            throws BaseException
    {
        try {
            System.out.println("I am jumping....");
            // 获取ZfjgqysdsjbForm对象
            ZfjgqysdsjbForm zfjgform = (ZfjgqysdsjbForm) form;
            String jsjdm = zfjgform.getJsjdm();
            String jumpStr = null;
            // 将CzzssdsjbForm 放入request中
            CzzssdsjbForm czzsForm=new CzzssdsjbForm();
            if(jsjdm != null && !jsjdm.equals(""))
            {
                czzsForm.setJsjdm(jsjdm);
                jumpStr = "Query";
            }
            else
            {
                jumpStr = "Jump";
            }
            request.setAttribute("czzssdsjb2014Form", czzsForm);
            System.out.println("jumpStr = " + jumpStr);
            return mapping.findForward(jumpStr);
        } catch (Exception ex) {
            // 系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    public ActionForward doJumpGdzcjszjyjqk(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException
    {
        try {

            // 获取ZfjgqysdsjbForm对象
            ZfjgqysdsjbForm zfjgform = (ZfjgqysdsjbForm) form;
//            String jsjdm = zfjgform.getJsjdm();
//            String jumpStr = null;
//            
//            GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm=new GdzcjszjyjqkjbForm();
////            if(jsjdm != null && !jsjdm.equals(""))
////            {
//            	gdzcjszjyjqkjbForm.setJsjdm(jsjdm);
//            	gdzcjszjyjqkjbForm.setNsrsbh(zfjgform.getNsrsbh());
//            	gdzcjszjyjqkjbForm.setNsrmc(zfjgform.getNsrmc());
//            	gdzcjszjyjqkjbForm.setSbrq(zfjgform.getSbrq());
//            	gdzcjszjyjqkjbForm.setSkssksrq(zfjgform.getSkssksrq());
//            	gdzcjszjyjqkjbForm.setSkssjsrq(zfjgform.getSkssjsrq());
//            	gdzcjszjyjqkjbForm.setSshy(zfjgform.getSshy());
////                jumpStr = "Query";
////            }
////            else
////            {
////                jumpStr = "JumpGdzcjszjyjqk";
////            }
//            request.setAttribute("gdzcjszjyjqkjbForm2014", gdzcjszjyjqkjbForm);
////            System.out.println("jumpStr = " + jumpStr);
            return mapping.findForward("JumpGdzcjszjyjqk");
        } catch (Exception ex) {
            // 系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }  
    
}
