package com.ttsoft.bjtax.smsb.wszm.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
*完税证明的Action，所有与完税证明有关的数据传递操作，都在此Action中进行
* <p>Title: </p>
*
* <p>Description: </p>
*
* <p>Copyright: Copyright (c) 2013</p>
*
* <p>Company: </p>
*
* @author tujb
* @version 1.0
*/
public class WszmAction extends SmsbAction
{
	public WszmAction() 
	{
    }
	
	/**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
	
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) 
    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
                             "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
                             "<font color=\"#7C9AAB\">税收完税证明</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "开具税收完税证明");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/gtgsh/Gwszhz-000.htm");
        
    }
    
    
    /**
     * 页面初始化
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
    	Debug.out("into WszmAction's doShow  Method....");
    	
    	WszmForm pf = (WszmForm) form;
        pf.reset(mapping, request);

        try
        {
            //获得预装载的信息
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            if (ud != null)
            {
                pf.setLrr(ud.getYhmc()); //获得单前登陆的用户id，作为录入人id
            }
            pf.setZhdm(ud.getXtsbm1()); //设置帐户代码
            pf.setPzzldm(CodeConstant.SMSB_SWWSZM_PZZLDM); //票证种类代码
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //获得本级的计算机代码

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
            //调用processor
            pf = (WszmForm) ZhsbProxy.getInstance().process(vo);
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
     * doQuery
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws BaseException
     */
    public ActionForward doQuery(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) 
    	throws BaseException 
    {
    	Debug.out("into WszmAction's doQuery Method....");
    	super.doInit(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	//获得当前的userData对象
    	UserData ud = null;
    	WszmForm pf = null;
    	//生成数据包
    	VOPackage vo = new VOPackage();
    	
    	try 
    	{
    		//初始化
    		ud = this.getUserData(httpServletRequest);
    		pf = (WszmForm) actionForm;
    	    if (pf == null) 
    	    {
    	        pf = new WszmForm();
    	    }
    		//调用EJB
    		vo.setAction(CodeConstant.SMSB_QUERYACTION);
    		//调用processor
    		vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
    		//设置actionForm
    		vo.setData(pf);
    		//设置userDate
    		vo.setUserData(ud);
    	
    		pf = (WszmForm) ZhsbProxy.getInstance().process(vo);
    		
    		if (pf.getDataList().size() == 0) 
    		{
    			httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, getPrintMessage("没有符合条件的信息", true));
    		}
    		httpServletRequest.setAttribute("wszmForm", pf);
    	} 
    	catch (Exception ex) 
    	{
    		ex.printStackTrace();
    		httpServletRequest.setAttribute("wszmForm", pf);
    		throw ExceptionUtil.getBaseException(ex);
    	}
    	return actionMapping.findForward("Query");
  	}
    
    
    
    /**
	   * doChangePage
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @exception BaseException
	   */
	  public ActionForward doChangePage(ActionMapping actionMapping,
	                                    ActionForm actionForm,
	                                    HttpServletRequest httpServletRequest,
	                                    HttpServletResponse httpServletResponse) 
	  throws BaseException 
	{
		  super.doInit(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		  //获得当前的userData对象
		  UserData ud = null;
		  WszmForm pf = null;
		  VOPackage vo = new VOPackage();
		  //执行Processor
		  try 
		  {
			  //初始化
			  ud = this.getUserData(httpServletRequest);
			  pf = (WszmForm) actionForm;
			  if (pf == null)
			  {
				  pf = new WszmForm();
			  }
			  //调用EJB
			  vo.setAction(CodeConstant.SMSB_QUERYACTION);
			  vo.setUserData(ud);
			  vo.setData(pf);
			  vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
			  pf = (WszmForm) ZhsbProxy.getInstance().process(vo);
			  
			  //回写session数据
			  httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
		  }
		  catch (Exception ex) 
		  {
			  ex.printStackTrace();
			  throw ExceptionUtil.getBaseException(ex);
		  }
		  return actionMapping.findForward("Show");
	  }
	  
	  
	  	/**
	  	 * 显示完税证明
	  	 * 
	  	 * @param actionMapping
	  	 * @param actionForm
	  	 * @param httpServletRequest
	  	 * @param httpServletResponse
	  	 * @return
	  	 * @throws BaseException
	  	 */
	    public ActionForward doDetail(ActionMapping actionMapping,
	            					  ActionForm actionForm,
	            					  HttpServletRequest httpServletRequest,
	            					  HttpServletResponse httpServletResponse) 
	    	throws BaseException 
	    {
	    	Debug.out("into WszmAction's doDetail Method....");
	    	UserData ud = this.getUserData(httpServletRequest);
	    	
	    	//System.out.println("##########ud.getXtsbm1()="+ud.getXtsbm1());
	    	
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	//生成数据包
	    	VOPackage vo = new VOPackage();
	    	//保存
	    	vo.setAction(CodeConstant.SMSB_READERACTION);
	    	//调用processor
	    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
	    	//设置actionForm
	    	HashMap dataMap = new HashMap();
	    	
	    	dataMap.put("nsrsbh",form.getNsrsbh());
	    	dataMap.put("nsrmc",form.getNsrmc());
	    	dataMap.put("ysphm",form.getYsphm());
	    	dataMap.put("ypzh",form.getYpzh());
	    	dataMap.put("ywszh",form.getYwszh());
	    	dataMap.put("ypzzldm",form.getYpzzldm());
	    	dataMap.put("yndzb",form.getYndzb());
	    	dataMap.put("sspzlxdm",form.getSspzlxdm());
	    	dataMap.put("hjje",form.getHjje());
	    	dataMap.put("dycs",form.getDycs());
	    	dataMap.put("swjgdm",form.getSsswjgzzjgdm());
	    	dataMap.put("yhtpbz",form.getYhtpbz());
	    	
	    	vo.setData(dataMap);
	    	//设置userDate
	    	vo.setUserData(ud);
	    	try 
	    	{
	    		form = (WszmForm) ZhsbProxy.getInstance().process(vo);
	    		
                if(form.getSspzlxdm().endsWith(CodeConstant.WSZM_JKS)){
                	httpServletRequest.getSession().setAttribute("SESSION_KEY_JKS_WSZM",
                			form.getDyDataList());
                }
	    		
	    		//form.setResMxList(resMxList);
	    		httpServletRequest.setAttribute("wszmForm", form);
	    	}
	    	catch (Exception ex) 
	    	{
	    		ex.printStackTrace();
	    		httpServletRequest.setAttribute("wszmForm", form);
	    		throw ExceptionUtil.getBaseException(ex);
	    	}
	    	return actionMapping.findForward("Detail");
	    }
	    
	    
	    /**
	     * 打印完税证明
	     * 
	     * @param actionMapping
	     * @param actionForm
	     * @param httpServletRequest
	     * @param httpServletResponse
	     * @return
	     * @throws BaseException
	     */
	    public ActionForward doSavePrint(ActionMapping actionMapping,
	            						 ActionForm actionForm,
	            						 HttpServletRequest httpServletRequest,
	            						 HttpServletResponse httpServletResponse) 
	    	throws BaseException 
	    {
	    	Debug.out("into WszmAction's doPrint Method....");
	    	
	    	//防止refresh
	        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
	        if (forward != null)
	        {
	            return forward;
	        }
	    	
	    	UserData ud = this.getUserData(httpServletRequest);
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	try 
		    {
		    	//生成数据包
		    	VOPackage vo = new VOPackage();
		    	//保存
		    	vo.setAction(CodeConstant.SMSB_SAVEACTION);
		    	//调用processor
		    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
		    	vo.setData(form);
		    	//设置userDate
		    	vo.setUserData(ud);
		    	form = (WszmForm) ZhsbProxy.getInstance().process(vo);
		    	
		    	if(form.getSspzlxdm().endsWith(CodeConstant.WSZM_JKS)){
		    		ArrayList jksList=(ArrayList)httpServletRequest.getSession().getAttribute("SESSION_KEY_JKS_WSZM");
		    		form.setDyDataList(jksList);
	            }
		    	
		    	httpServletRequest.setAttribute("wszmForm", form);
		    }
		    catch (Exception ex) 
	    	{
	    		ex.printStackTrace();
	    		httpServletRequest.setAttribute("wszmForm", form);
	    		throw ExceptionUtil.getBaseException(ex);
	    	}
		    
	    	return actionMapping.findForward("SavePrint");
	    }
	    
	    
	    /**
	     * 打印完税证明成功后设置打印标记
	     * 
	     * @param actionMapping
	     * @param actionForm
	     * @param httpServletRequest
	     * @param httpServletResponse
	     * @return
	     * @throws BaseException
	     */
	    public ActionForward doSuccess(ActionMapping actionMapping,
	    							   ActionForm actionForm,
	    							   HttpServletRequest httpServletRequest,
	    							   HttpServletResponse httpServletResponse) 
	    	throws BaseException 
	    {
	    	Debug.out("into WszmAction's doSuccess Method....");
	    	
	    	//防止refresh
	        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
	        if (forward != null)
	        {
	            return forward;
	        }
	    	
	    	UserData ud = this.getUserData(httpServletRequest);
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	try 
		    {
		    	//生成数据包
		    	VOPackage vo = new VOPackage();
		    	//保存
		    	vo.setAction(CodeConstant.SMSB_PRINTACTION);
		    	//调用processor
		    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
		    	vo.setData(form);
		    	//设置userDate
		    	vo.setUserData(ud);
		    	form = (WszmForm) ZhsbProxy.getInstance().process(vo);
		    	if(form.getSspzlxdm().endsWith(CodeConstant.WSZM_JKS)){
		    		ArrayList jksList=(ArrayList)httpServletRequest.getSession().getAttribute("SESSION_KEY_JKS_WSZM");
		    		form.setDyDataList(jksList);
	            }
		    	httpServletRequest.setAttribute("wszmForm", form);
		    }
		    catch (Exception ex) 
	    	{
	    		ex.printStackTrace();
	    		httpServletRequest.setAttribute("wszmForm", form);
	    		throw ExceptionUtil.getBaseException(ex);
	    	}
		    
	    	return actionMapping.findForward("Success");
	    }
	    
	    
	    
	    /**
	     * 打印完税证明成功后设置打印标记
	     * 
	     * @param actionMapping
	     * @param actionForm
	     * @param httpServletRequest
	     * @param httpServletResponse
	     * @return
	     * @throws BaseException
	     */
	    public ActionForward doReprint(ActionMapping actionMapping,
	    							   ActionForm actionForm,
	    							   HttpServletRequest httpServletRequest,
	    							   HttpServletResponse httpServletResponse) 
	    	throws BaseException 
	    {
	    	Debug.out("into WszmAction's doReprint Method....");
	    	
	    	//防止refresh
	        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
	        if (forward != null)
	        {
	            return forward;
	        }
	    	
	    	UserData ud = this.getUserData(httpServletRequest);
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	try 
		    {
		    	//生成数据包
		    	VOPackage vo = new VOPackage();
		    	//保存
		    	vo.setAction(CodeConstant.SMSB_REPRINTACTION);
		    	//调用processor
		    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
		    	vo.setData(form);
		    	//设置userDate
		    	vo.setUserData(ud);
		    	form = (WszmForm) ZhsbProxy.getInstance().process(vo);
		    	if(form.getSspzlxdm().endsWith(CodeConstant.WSZM_JKS)){
		    		ArrayList jksList=(ArrayList)httpServletRequest.getSession().getAttribute("SESSION_KEY_JKS_WSZM");
		    		form.setDyDataList(jksList);
	            }
		    	httpServletRequest.setAttribute("wszmForm", form);
		    }
		    catch (Exception ex) 
	    	{
	    		ex.printStackTrace();
	    		httpServletRequest.setAttribute("wszmForm", form);
	    		throw ExceptionUtil.getBaseException(ex);
	    	}
		    
	    	return actionMapping.findForward("Reprint");
	    }
	    
    
    
	    /**
	     * 返回完税证
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
	        try
	        {
	            //把预装载的信息传递给下一个页面
	        	WszmCxForm pf = new WszmCxForm();
	        	
	            pf.setActionType("Show");
	            request.setAttribute("wszmCxForm", pf);
	        }
	        catch (Exception ex)
	        {
	        }
	        return mapping.findForward("Dismiss");
	    }
	    
	    
	    /**
	   * 转换输出信息格式
	   * @param message 输出内容
	   * @param successFlag 成功|失败
	   * @return
	   */
	  private String getPrintMessage(String message, boolean successFlag) {
	    StringBuffer printMessage = new StringBuffer();
	    if (successFlag) { //成功信息
	      printMessage.append("<br><strong><font color=\"#0000FF\">&nbsp;&nbsp;")
	          .append(message).append("！</font></strong>");
	    }
	    else { //失败信息
	      printMessage.append("<br><strong><font color=\"#FF0000\">&nbsp;&nbsp;")
	          .append(message).append("！</font></strong>");
	    }
	    return printMessage.toString();
	  }

}
