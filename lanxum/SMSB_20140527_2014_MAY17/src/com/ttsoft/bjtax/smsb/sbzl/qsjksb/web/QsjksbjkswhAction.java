/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypysActionForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现欠税缴款申报功能：包括缴款书录入，维护。</p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbjkswhAction
    extends SmsbAction
{
    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报缴款</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "综合申报");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsbjkswh-000.htm");
    }

    /**
     * 查询计算机代码对应的未入库缴款书
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;


        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbjkswhProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        
         form = (QsjksbjkswhActionForm) vo.getData();
        try
        {
            form = (QsjksbjkswhActionForm) ZhsbProxy.getInstance().process(vo);
            //System.out.println("========222======="+form.getDataList().size());

            List ar = TranslateHelper.splitMAP(form.getDataList(),httpServletRequest);
            
            //Debug.out("((List)ar.get(0)).size()=" + ((List)ar.get(0)).size());
            //Debug.out("((List)ar.get(1)).size()=" + ((List)ar.get(1)).size());
            
            form.setDataList((List)ar.get(0));
            form.setNlwDataList((List)ar.get(1));

            //查询缴款书撤销条件
            vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION1);
            vo.setProcessor(
                "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbjkswhProcessor");
            vo.setData(form);
            vo.setUserData(ud);           
            try{
            	form = (QsjksbjkswhActionForm) ZhsbProxy.getInstance().process(vo);
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex, "查询缴款书撤销条件失败!");
            }
            
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
    }

    /**
     * 察看一票一税缴款书信息
     * modified by qianchao 2005.11.2
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpys (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;
        QsjksbjksypysActionForm form1 = new QsjksbjksypysActionForm();
        form1.setJkpzh(form.getJkpzhStr());
        form1.setJsjdm(form.getJsjdm());
        form1.setForward("Back");
        //设置申报编号标志
        httpServletRequest.setAttribute("qsjksbjksypysActionForm", form1);
        return actionMapping.findForward("Ypys");
    }

    /**
     * 察看一票多税缴款书信息
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpds (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	QsjksbjkswhActionForm pf1 = (QsjksbjkswhActionForm) actionForm;
      JksqdPrintForm pf = new JksqdPrintForm();

      pf.setH_jsjdm(pf1.getJsjdm());
      pf.setH_sbbh(pf1.getSbbh());
      pf.setJsjdm(pf1.getJsjdm());
      pf.setSbbh(pf1.getSbbh());
      pf.setHeadSjly(CodeConstant.SMSB_SJLY_BJQS); 
      pf.setActionType("Show");
      httpServletRequest.setAttribute("jksqdPrintForm", pf);
      return actionMapping.findForward("PrintSQD");

    }

    /**
     * 撤销缴款书
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doCx (ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //防止refresh
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;

        form.setCxStr("");
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbjkswhProcessor");
        vo.setData(form);
        //总控信息
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            form = (QsjksbjkswhActionForm) ZhsbProxy.getInstance().process(vo);

            form.setSbbh("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");
 
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Cx");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
        }
    }

    /**
     * 返回综合申报主页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;


        return actionMapping.findForward("Back");
    }

}
