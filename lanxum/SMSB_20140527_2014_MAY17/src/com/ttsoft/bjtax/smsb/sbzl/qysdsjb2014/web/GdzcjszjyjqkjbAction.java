package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: 北京地税综合管理系统 申报征收-上门模块-企业所得税季报2014版-固定资产加速折旧（扣除）预缴情况统计表</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: 北京立思辰电子科技有限公司</p>
 *
 * @author zhangyj
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

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
import com.ttsoft.bjtax.dj.model.dm.GJBZHY;
import com.ttsoft.bjtax.dj.util.*;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ygyjzjlnstzb.web.YgyjzjlnstzbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class GdzcjszjyjqkjbAction extends SmsbAction
{
  
    public GdzcjszjyjqkjbAction()
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
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;<font color=\"#7C9AAB\">固定资产加速折旧（扣除）预缴情况统计表</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                "固定资产加速折旧（扣除）预缴情况统计表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }



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

    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;

        //国家标准行业
        List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
        ArrayList gjbzhyList=new ArrayList();
        for(int i=0;i<gjbzhy.size();i++){
        	GJBZHY gjbzhyObject=(GJBZHY)gjbzhy.get(i);
        	String gjbzhydm=gjbzhyObject.getGjbzhydm();
        	if(gjbzhydm!=null && !gjbzhydm.equals("")&&gjbzhydm.length()==4){
        		gjbzhyList.add(gjbzhyObject);
//        		System.out.println("gjbzhydm: "+gjbzhyObject.getGjbzhydm()+" mc:"+gjbzhyObject.getGjbzhymc());
        	}
        	
        }
        request.getSession().setAttribute("gjbzhy", gjbzhy);
        gdzcjszjyjqkjbForm.setGjbzhy(gjbzhyList);

        String jumpFlag=(String) request.getAttribute("jumpFlag");
        if(jumpFlag!=null && !jumpFlag.equals("") && gdzcjszjyjqkjbForm.getJumpFlag().equals("")){
        	gdzcjszjyjqkjbForm.setJumpFlag(jumpFlag);
        }
    	
        // 获取本缴费系统用户信息
        UserData userData = this.getUserData(request);
        // 设置报表期类型-季报
        gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // 设置税务计算机代码
        // czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
        // 设置录入人名称
        gdzcjszjyjqkjbForm.setLrr(userData.getYhid());

        // 初始化数据传递容器
        VOPackage vo = new VOPackage();
        // 设置后台调用Action值---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // 设置容器里的Data数据,这里存放ZfjgqysdsjbForm
        vo.setData(gdzcjszjyjqkjbForm);
        // 设置Proxy要调用的processor的类---ZfjgqysdsjbProcessor
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR);
        // 设置系统用户信息
        vo.setUserData(userData);
        try {
            // 调用Proxy，执行processor,获取zfjgForm返回值
        	gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) SbzlProxy.getInstance().process(vo);
            // 将czqysdsjbForm置入request,作为回显数据用
            request.setAttribute("gdzcjszjyjqkjb2014Form", gdzcjszjyjqkjbForm);
            // 操作成功流转
            return mapping.findForward("Show");
           
        } catch (Exception ex) {

        	gdzcjszjyjqkjbForm.reset(mapping,request);
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

        GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;
        UserData userData = this.getUserData(request);
        List arrayList=new ArrayList();
        arrayList=SfRequestUtil.getValuesToList(request,gdzcjszjyjqkjbForm.getSb_columns());
        gdzcjszjyjqkjbForm.setGdzcjszjyjqkjbList(arrayList); //设置固定行数据
//        this.getBaseForm(request, gdzcjszjyjqkjbForm); //加入纳税人基本数据
        request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION); //设置操作类型
        vo.setData(gdzcjszjyjqkjbForm); //设置操作数据
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR); //PROCESSOR
        vo.setUserData(userData);
        try {
            //调用processor
            gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);
        } catch (Exception ex) {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！"); //返回保存结果
        
        return this.doQuery(mapping, form, request, response);
    }
    /**
     *跳转到企业所得税汇总纳税分支机构分配表页面
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
    public ActionForward doReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;
    	String jumpFlag=gdzcjszjyjqkjbForm.getJumpFlag();
    	System.out.println("...............return : "+jumpFlag+request.getAttribute("jumpFlag"));
    	if(jumpFlag!=null && jumpFlag.equals("czzssdsjb2014")){
    		return mapping.findForward("JumpCzzs");
    	}else{
    		return mapping.findForward("JumpZfjg");
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

        // -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;
        UserData userData = this.getUserData(request);
        List arrayList=new ArrayList();
        arrayList=SfRequestUtil.getValuesToList(request,gdzcjszjyjqkjbForm.getSb_columns());
        gdzcjszjyjqkjbForm.setGdzcjszjyjqkjbList(arrayList); //设置固定行数据
//        this.getBaseForm(request, gdzcjszjyjqkjbForm); //加入纳税人基本数据
        request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION); //设置操作类型
        vo.setData(gdzcjszjyjqkjbForm); //设置操作数据
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR); //PROCESSOR
        vo.setUserData(userData);
        try {
            //调用processor
            gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);
        } catch (Exception ex) {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！"); //返回删除结果
        
        return this.doQuery(mapping, form, request, response);
    }
}
