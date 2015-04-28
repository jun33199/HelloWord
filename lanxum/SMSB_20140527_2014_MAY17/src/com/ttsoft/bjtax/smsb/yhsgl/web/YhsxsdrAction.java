/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.xml.sax.InputSource;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.yhsgl.processor.xml4YHS;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 导入代售单位印花税销售情况 Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsxsdrAction
    extends SmsbAction
{
    UserData userData = null;

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>导入代售单位印花税销售情况");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsxsdr-000.htm");
    }

    /**
     * 显示处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsxsdrForm yForm = (YhsxsdrForm) aForm;
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 导入处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doLoad (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsxsdrForm yForm = (YhsxsdrForm) aForm;

        vo.setAction(CodeConstant.SMSB_LOADACTION);
        vo.setProcessor(CodeConstant.YHSGL_XSDR_PROCESSOR);
        vo.setData(yForm);
        yForm.reset(mapping, request);

        //重新得时间等数据
        userData = this.getUserData(request);
        yForm.setLrr(String.valueOf(userData.getYhid()));
        //解析导入文件得到数据
        try
        {
            yForm = (YhsxsdrForm) doHandleXMLFile(yForm);
            request.getSession().setAttribute(CodeConstant.
                                              SESSIONKEY_YHSDATALIST,
                                              yForm.getDataList());
            request.getSession().setAttribute(CodeConstant.SESSIONKEY_XSLIST,
                                              yForm.getXsList());

            //分页
            yForm.setPgNum(1);
            List pgList = yForm.getDataList();
            yForm.setLength(CodeConstant.YHS_PG_LENGTH);
            yForm.setPgSum(pgList.size() % yForm.getLength() == 0 ?
                           pgList.size() / yForm.getLength() :
                           pgList.size() / yForm.getLength() + 1);

            //得到合计项
            int gpslhj = 0;
            float jehj = 0;
            for (int i = 0; i < yForm.getDataList().size(); i++)
            {
                HashMap map = (HashMap) yForm.getDataList().get(i);
                jehj += Float.parseFloat( (String) map.get("je"));
                gpslhj += Integer.parseInt( (String) map.get("gpsl"));
            }
            int loc = String.valueOf(jehj).indexOf(".");
            yForm.setGpslhj(String.valueOf(gpslhj));
            yForm.setHjje(String.valueOf(jehj).substring(0, loc + 2));

        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "请检查导入的销售数据！");
        }

        //调登记接口,获得代售单位信息
        try
        {
            com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.
                bjtax.
                dj.proxy.ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(yForm.getDsjsjdm(),
                                                this.getUserData(request));
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            yForm.setDsdwmc(swdjjbsj.getNsrmc());
            yForm.setDsdwlxdh(swdjjbsj.getJydzlxdm());
            yForm.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Load");
        }
        catch (Exception e2)
        {
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            throw ExceptionUtil.getBaseException(e2, "请检查导入文件中的代售单位计算机代码是否正确！");
        }
    }

    /**
     * 汇总处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doHzjks (ActionMapping mapping,
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        YhsxsdrForm yForm = (YhsxsdrForm) aForm;
        yForm.setDataList( (ArrayList) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_YHSDATALIST));
        yForm.setXsList( (ArrayList) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_XSLIST));
        VOPackage vo = new VOPackage();
        vo.setProcessor(CodeConstant.YHSGL_XSDR_PROCESSOR);
        vo.setData(yForm);
        vo.setAction(CodeConstant.SMSB_HZJKSACTION);
        vo.setUserData(this.getUserData(request));

        try
        {//跳至汇总缴款书
            HashMap map = new HashMap();
            map = (HashMap) ZhsbProxy.getInstance().process(vo);
            YhsxshzForm hzForm = new YhsxshzForm();
            hzForm.setDsjsjdm(yForm.getDsjsjdm());
            hzForm.setDsdwmc(String.valueOf(map.get("dsdwmc")));
            hzForm.setJkpzh(String.valueOf(map.get("jkpzh")));
            hzForm.setSjse(String.valueOf(map.get("sjse")));
            request.setAttribute("yhsxshzForm", hzForm);
            return mapping.findForward("Hzjks");
        }
        catch (Exception e)
        {
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 撤销处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks (ActionMapping mapping,
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        try
        {//跳转到撤销缴款书
            YhsxsdrForm form = (YhsxsdrForm) aForm;
            YhsxshzcxForm yForm = new YhsxshzcxForm();
            yForm.reset(mapping, request);
            yForm.setDsjsjdm(form.getDsjsjdm());
            request.setAttribute("yhsxshzcxForm", yForm);
            return mapping.findForward("Cxjks");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 跳至选择的页
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doGotopage (ActionMapping mapping,
                                     ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsxsdrForm yForm = (YhsxsdrForm) aForm;
            yForm.setDataList( (ArrayList) request.getSession().getAttribute(
                CodeConstant.SESSIONKEY_YHSDATALIST));
            return mapping.findForward("Gotopage");//跳至选择的页
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 处理XML文件
     * @param form The optional ActionForm bean for this request (if any)
     * @return The optional ActionForm bean for this request (if any)
     * @throws BaseException
     */
    private ActionForm doHandleXMLFile (YhsxsdrForm form)
        throws BaseException
    {
        FormFile formFile = form.getTheFile();
        try
        {
            //获得导入数据
            InputSource theFile = new InputSource(formFile.getInputStream());
            //解析数据得到导入记录
            xml4YHS parser = new xml4YHS(theFile);
            form.setDataList(parser.yhsList); //销售数据
            form.setDsjsjdm(parser.dsjsjdm); //代售单位计算机代码
            form.setXsList(parser.xsList); //销售凭证号与相应的合计金额
            return form;
        }
        catch (ApplicationException aEx)
        {
            throw ExceptionUtil.getBaseException(aEx);
        }
        catch (Exception ex)
        {
            throw new ApplicationException("当前导入的xml文件格式或数据错误!");
        }
    }
}