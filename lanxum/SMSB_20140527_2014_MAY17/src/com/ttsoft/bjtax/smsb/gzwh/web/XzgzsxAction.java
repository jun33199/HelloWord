/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gzwh.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.SmExcelParser;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 新增告知事项</p>
 * @author 开发六组－－石岩峰
 * @version 1.1
 */
public class XzgzsxAction
    extends SmsbAction
{
    /**
     * 数据集对象（包括Form和UserData对象）
     */
    private VOPackage vo = new VOPackage();

    /**
     * 用户信息对象
     */
    UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        
        System.out.println("XzgzsxAction.initialRequest()");
        
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报征收</font>&gt;<font color=\"#6F8EA2\">申报控制</font>&gt;告知事项维护&gt;</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "告知事项维护");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/gzwh/gzwh-000.htm");

    }

    /**
     * 显示处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
    	System.out.println("XzgzsxAction.doShow()");
    	
        try
        {
            GzsxwhForm gf = (GzsxwhForm) form;
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 更新处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        GzsxwhForm gf = (GzsxwhForm) form;

        userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(CodeConstant.GZWH_XZGZSX_PROCESSOR);
        vo.setData(gf);
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            gf.setMxChooseTypeRadio(gf.getMxChooseTypeRadioHidden());
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            //显示成功信息
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "更新成功！");
            return mapping.findForward("Update");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 保存处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
    	System.out.println("XzgzsxAction.doSave()");
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        GzsxwhForm gf = (GzsxwhForm) form;

        try
        {

        	//System.out.println(gf.getMxJsjdm()+"----"+gf.getMxJsjdm().equals("*")+"---"+gf.getMxJsjdm().indexOf(","));
            if (gf.getMxJsjdm() != null && !gf.getMxJsjdm().equals("*")
                && gf.getMxJsjdm().indexOf(",") < 0)
            {
                //调登记接口
                try
                {
                    if (gf.getMxJsjdm() != null
                        && (gf.getMxJsjdm().trim()).length() != 0)
                    {
                        com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new
                            com.ttsoft.
                            bjtax.
                            dj.proxy.ServiceProxy();
                        HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm());
                        SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                        gf.setMxNsrmc(swdjjbsj.getNsrmc());
                    }
                }
                catch (Exception e)
                { 
                	e.printStackTrace();
                    throw new ApplicationException("没有该纳税人的登记信息!");
                }
                //判断是非有操作权限
                try
                {
                    if (gf.getMxJsjdm() != null
                        && (gf.getMxJsjdm().trim()).length() != 0)
                    {
                        com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new
                            com.ttsoft.
                            bjtax.
                            dj.proxy.ServiceProxy();
                        HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm(),
                            this.getUserData(request));
                    }
                }
                catch (Exception e)
                {
                    gf.setNsrmc("");
                    e.printStackTrace();
                    throw new ApplicationException("您没有足够的权限处理此计算机代码！");
                }
            }
        }
        catch (Exception e)
        {
            gf.setMxNsrmc("");
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        try
        {
            userData = this.getUserData(request);
            vo.setUserData(this.getUserData(request));

            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setProcessor(CodeConstant.GZWH_XZGZSX_PROCESSOR);
            vo.setData(gf);

            if (gf.getMxChooseTypeRadioHidden().equals("3"))
            {
                //计算机代码导入
                List list = this.getJsjdmFromExcel(gf);
                gf.setJsjdmList( (ArrayList) list);
                gf.setExcelFile(null);
            }

            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //数据初始化（保存成功）
            //告知起始日期==服务器系统日期
            SfDateUtil sfdateUtil = new SfDateUtil();
            gf.setMxGzqsrq(sfdateUtil.getDate());
            gf.setMxJsjdm(null);
            gf.setMxNsrmc(null);
            gf.setMxQylx(null);
            gf.setMxGzlx(null);
            gf.setMxGzjzrq(null);
            gf.setMxGzsxxxxx(null);
            gf.setMxChooseTypeRadio("1");

            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
            return mapping.findForward("Save");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 取名称处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doGetNsrmc (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;

        //调登记接口
        try
        {
            try
            {
                if (gf.getMxJsjdm() != null
                    && (gf.getMxJsjdm().trim()).length() != 0)
                {
                    com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.
                        ttsoft.bjtax.
                        dj.proxy.ServiceProxy();
                    HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm());
                    SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                    gf.setMxNsrmc(swdjjbsj.getNsrmc());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("没有该纳税人的登记信息!");
            }
            //判断是非有操作权限
            try
            {
                if (gf.getMxJsjdm() != null
                    && (gf.getMxJsjdm().trim()).length() != 0)
                {
                    com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.
                        ttsoft.bjtax.
                        dj.proxy.ServiceProxy();
                    HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm(),
                        this.getUserData(request));
                }
            }
            catch (Exception e)
            {
                gf.setNsrmc("");
                e.printStackTrace();
                throw new ApplicationException("您没有足够的权限处理此计算机代码！");
            }
        }
        catch (Exception e)
        {
            gf.setMxNsrmc("");
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        return mapping.findForward("GetNsrmc");

    }

    /**
     * 返回处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doReturn (ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GzsxwhForm gf = (GzsxwhForm) form;
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            if (gf.getSaveType().equals("Update"))
            {
                return mapping.findForward("ReturnSelect");
            }
            else
            {
                return mapping.findForward("Return");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 根据指定的excel文件得到相应的计算机代码
     * excel文件中只包含一列计算机代码
     * @param form The optional ActionForm bean for this request (if any)
     * @return 导入文件的List
     * @throws BaseException
     */
    private List getJsjdmFromExcel (ActionForm form)
        throws
        BaseException
    {
        SmExcelParser ep = new SmExcelParser();
        GzsxwhForm gf = (GzsxwhForm) form;
        try
        {
            return ep.getColAt(gf.getExcelFile().getInputStream(), 0, 0);
        }
        catch (IOException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}