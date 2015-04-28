/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.LWHelper;
import com.ttsoft.bjtax.smsb.zhsb.pgbs.web.ZhsbPgbsActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收缴款书录入</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsJkslrAction
    extends SmsbAction
{
    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
                             "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
                             "<font color=\"#7C9AAB\">零散税征收</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "缴款书录入");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/jkslr-000.htm");
        try
        {
            LszsJkslrForm pf = (LszsJkslrForm) form;
            //设置计算机代码
            UserData ud = this.getUserData(request);
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //所属机关计算机代码
            //设置初始化方法的参数
            ZhsbActionForm temp = new ZhsbActionForm();
            //设置计算机代码
            temp.setJsjdm(pf.getJsjdm());
            //设置申报日期
            temp.setSbrq(SfDateUtil.getDate());
            ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
            //设置前台显示用js数组
            pf.setScriptStr(zhsb.getScriptStr());

        }
        catch (Exception ex)
        {

        }

    }

    /**
     * 初始化
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {

        LszsJkslrForm pf = (LszsJkslrForm) form;

        try
        {
            //获得预装载的信息
            //获得当前的userData对象
            //设置计算机代码
            UserData ud = this.getUserData(request);
            pf.reset(mapping, request);
            if (ud != null)
            {
                pf.setLrr(ud.getYhid());
                pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //所属机关计算机代码，暂时写死！
                pf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
            }
            //还需号获得本级的计算机代码
            //pf.se
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_JKSLR_PROCESSOR);
            //调用processor
            pf = (LszsJkslrForm) ZhsbProxy.getInstance().process(vo);
//      //设置初始化方法的参数
//      ZhsbActionForm temp = new ZhsbActionForm();
//      //设置计算机代码
//      temp.setJsjdm(pf.getJsjdm());
//      ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
//      //设置前台显示用js数组
//      pf.setScriptStr(zhsb.getScriptStr());
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Show");
    }

    /**
     * 查询
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {

        LszsJkslrForm pf = (LszsJkslrForm) form;
        try
        {
            //UserData对象
            UserData ud = this.getUserData(request);
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_JKSLR_PROCESSOR);
            //调用processor
            pf = (LszsJkslrForm) ZhsbProxy.getInstance().process(vo);
            //设置初始化的js数组
            //设置初始化方法的参数
            ZhsbActionForm temp = new ZhsbActionForm();
            //设置计算机代码
            temp.setJsjdm(pf.getJsjdm());
            //设置申报日期
            temp.setSbrq(SfDateUtil.getDate());
            ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
            //设置前台显示用js数组
            pf.setScriptStr(zhsb.getScriptStr());
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Query");
    }

    /**
     * 保存
     * modified by qianchao 2005.10.26
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        String forwardFlag = "Save";
        LszsJkslrForm pf = (LszsJkslrForm) form;

        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        //UserData对象
        UserData ud = this.getUserData(request);

        String columns[] = pf.getColumns();
        pf.setDataList(this.getValuesToList(request, columns));
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.LSZS_JKSLR_PROCESSOR);


        //start add by qianchao 2005.10.25
        System.out.println("检测联网状态，设定税票类型。");
        System.out.println("pf.getJsjdm()=" + pf.getJsjdm());
        HashMap datamap = new HashMap();
        boolean lwstate = LWHelper.getLWState(request,pf.getJsjdm());
        if (lwstate)
        {
          datamap.put(CodeConstant.MAP_VO_YPYSorYPDS, new Integer(CodeConstant.PRINT_YPDS_KM));
        }
        else
        {
          datamap.put(CodeConstant.MAP_VO_YPYSorYPDS, new Integer(CodeConstant.PRINT_YPYS));
        }
        datamap.put(CodeConstant.MAP_VO_FORM,pf);
        vo.setData(datamap);
        //end add by qianchao 2005.10.25


        //设置通过总控得到的用户信息
        vo.setUserData(ud);
        try
        {
            Object tt = ZhsbProxy.getInstance().process(vo);
            //end modifying by qianchao 2005.11.2
            
            LszsJkslrForm rtForm = (LszsJkslrForm)tt;
            
            List dataList = rtForm.getDataList();
            List delList = new ArrayList();
            List okList = new ArrayList();
            
            if(dataList.size()>0){
            	if(lwstate){
            		//一票多税
                	BigDecimal total = new BigDecimal(0.00);
               		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
            			List infoList = (List) iterator.next();
       	
	                    for (int i = 0; i < infoList.size(); i++) {
	
	                    	DeclareInfor declareInfor = (DeclareInfor)infoList.get(i);
	                    	
	                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
	                    	List jkmxList = declareInfor.getSbjkmxInfo();
	                    	
	                    	delList.add(sbjkzb.getJkpzh());
	                    	
	                    	for (int j = 0; j < jkmxList.size(); j++) {
	                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
	                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
	                    		total = total.add(sjse);
	                    	}
	                    }
	                    okList.add(infoList);
            		}
                    
                    if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                    	okList =  new ArrayList();
                    }else{
                    	delList = new ArrayList();
                    }
            	}else{
            		//一票一税        		
                    for (int i = 0; i < dataList.size(); i++) {
                    	
                    	BigDecimal total = new BigDecimal(0.00);
                    	
                    	DeclareInfor declareInfor = (DeclareInfor)dataList.get(i);
                    	
                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
                    	List jkmxList = declareInfor.getSbjkmxInfo();
                    	
                    	for (int j = 0; j < jkmxList.size(); j++) {
                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
                    		total = total.add(sjse);
                    	}
                        if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                        	delList.add(sbjkzb.getJkpzh());
                        }else{
                        	okList.add(declareInfor);
                        }
                    }
        		}
            }
            
            if(delList.size()>0){
	            vo.setAction(CodeConstant.SMSB_DELETEACTION);
	            Map map = new HashMap();
	            map.put("jsjdm", rtForm.getJsjdm());
	            map.put("jkpzhList", delList);
	            vo.setData(map);
	            ZhsbProxy.getInstance().process(vo);
	            //重新设置剩余数据
	            rtForm.setDataList(okList);
            }
            
            if(okList.size()==0){
            	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!");
            	return this.doQuery(mapping,form,request,response);
            }
            
            if(delList.size()>0){
            	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"部分缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!");
            }else{
            	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"保存成功！");
            }
            
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            
            /********************Modified by lufeng 2003-11-11********************/
            //保存成功后打开列表打印页面
            LszsCxjksForm wszPF = new LszsCxjksForm();
            wszPF.setActionType("Show");
            request.setAttribute(mapping.getAttribute(), wszPF);

            forwardFlag = "Dismiss";
            //pf.reset(mapping, request);
        }
        catch (Exception ex)
        {
            //return (new ActionForward(mapping.getInput()));
            forwardFlag = "Save";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * 转到撤销页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDismiss (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        LszsCxjksForm pf = new LszsCxjksForm();
        try
        {
            //把预装载的信息传递给下一个页面
            pf.setActionType("Show");
            request.setAttribute("lszsCxjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Dismiss");
    }

    /**
     * 取得包含税种税目list和营业税附加税list的list
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @return the element previously at the specified position.
     * @exception Exception
     */
    private ActionForm getInitList (ActionForm actionForm)
        throws Exception
    {
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
        //调用综合申报的processor取得前台显示用的js数组
        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
        vo.setData(actionForm);
        try
        {
            return (ActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        }
    }

}
