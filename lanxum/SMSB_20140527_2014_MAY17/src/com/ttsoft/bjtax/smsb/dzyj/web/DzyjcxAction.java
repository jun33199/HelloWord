/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.dzyj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DzyjcxAction
    extends SmsbAction
{
    /**
     *初始时显示页面
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        DzyjcxActionForm form = (DzyjcxActionForm) actionForm;
        
        form.setYwlxList(CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords());
        form.setJsjdm("");
        form.setCzlx("0");
        form.setYwlx("0");
        form.setQssj("");
        form.setJzsj("");
        
        /*
        form.setJsjdm("06003600");
        form.setQssj("20060201");
        form.setJzsj("20060228");
        */
        httpServletRequest.setAttribute("dzyjcxActionForm", form);
        
        return (new ActionForward(actionMapping.getInput()));
    }

    /**
     * 查询页面
     * 
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
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
        DzyjcxActionForm form = (DzyjcxActionForm) actionForm;

        //生成数据包
        VOPackage vo = new VOPackage();
        //保存
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //调用processor
        vo.setProcessor(CodeConstant.DZYJ_PROCESSOR);
        //设置actionForm
        vo.setData(form);
        //设置userDate
        vo.setUserData(ud);
        try
        {
            DzyjcxActionForm retForm = (DzyjcxActionForm) ZhsbProxy.getInstance().process(vo);
            httpServletRequest.getSession(false).setAttribute("CA_DZYJSJ_QUERY_RESULT",retForm.getResultList());
            retForm.setResultList(null);
            
            httpServletRequest.setAttribute("dzyjcxActionForm", retForm);
        }
        catch (Exception ex)
        {

            form.setYwlxList(CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords());
            httpServletRequest.setAttribute("dzyjcxActionForm", form);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Query");
    }

    /**
     * 显示一条明细数据
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doOneItem (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        DzyjcxActionForm form = (DzyjcxActionForm) actionForm;
        
        List mxlist = (List)httpServletRequest.getSession(false).getAttribute("CA_DZYJSJ_QUERY_RESULT");
        if (mxlist == null)
        {
            throw new ApplicationException("系统中无此数据！");
        }
        DzyjsjVO dzvo;
        Map sjmap = new HashMap();
        String tempstr = "";
        List ywlist = CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords();
        int i,j;
        
        for (i = 0; i < mxlist.size(); i++)
        {
            dzvo = (DzyjsjVO)mxlist.get(i);
            if (dzvo.getLsh() == form.getOneItemLsh())
            {
                sjmap.put("lsh",new Long(dzvo.getLsh()));
                sjmap.put("jsjdm",dzvo.getJsjdm());
                
                for (j = 0; j < ywlist.size(); j++)
                {
                    if (((LabelValueBean)ywlist.get(j)).getValue().equals(dzvo.getYwdm()))
                    {
                        tempstr = ((LabelValueBean)ywlist.get(j)).getLabel();
                        break;
                    }
                }

                sjmap.put("ywlx",tempstr);
                if (dzvo.getYwczlx().equals("1"))
                {
                    sjmap.put("czlx","保存");    
                }
                else if (dzvo.getYwczlx().equals("2"))
                {
                    sjmap.put("czlx","修改");    
                }
                else if (dzvo.getYwczlx().equals("3"))
                {
                    sjmap.put("czlx","删除");    
                }
                else 
                {
                    sjmap.put("czlx",dzvo.getYwczlx());    
                }
                
                sjmap.put("ywuid",dzvo.getYwuid());
                sjmap.put("jssj",(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(dzvo.getJssj()));
                sjmap.put("yjsj",dzvo.getDzyj());
                sjmap.put("qmsj",dzvo.getDzyjqm());
                sjmap.put("zsxlh",dzvo.getZsxlh());
                httpServletRequest.setAttribute("CA_DZQMYJSJ",sjmap);
                
                return actionMapping.findForward("ShowOne");
            }
        }
        throw new ApplicationException("系统忙，无此数据！");
    }
    
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报缴款</font>&gt;<font color=\"#7C9AAB\">电子原件</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "电子原件查询");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "");
    }
    
 
}
