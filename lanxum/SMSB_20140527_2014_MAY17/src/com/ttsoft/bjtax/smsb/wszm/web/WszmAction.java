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
*��˰֤����Action����������˰֤���йص����ݴ��ݲ��������ڴ�Action�н���
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
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
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
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">˰����˰֤��</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "����˰����˰֤��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/gtgsh/Gwszhz-000.htm");
        
    }
    
    
    /**
     * ҳ���ʼ��
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
            //���Ԥװ�ص���Ϣ
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            if (ud != null)
            {
                pf.setLrr(ud.getYhmc()); //��õ�ǰ��½���û�id����Ϊ¼����id
            }
            pf.setZhdm(ud.getXtsbm1()); //�����ʻ�����
            pf.setPzzldm(CodeConstant.SMSB_SWWSZM_PZZLDM); //Ʊ֤�������
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //��ñ����ļ��������

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
            //����processor
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
    	//��õ�ǰ��userData����
    	UserData ud = null;
    	WszmForm pf = null;
    	//�������ݰ�
    	VOPackage vo = new VOPackage();
    	
    	try 
    	{
    		//��ʼ��
    		ud = this.getUserData(httpServletRequest);
    		pf = (WszmForm) actionForm;
    	    if (pf == null) 
    	    {
    	        pf = new WszmForm();
    	    }
    		//����EJB
    		vo.setAction(CodeConstant.SMSB_QUERYACTION);
    		//����processor
    		vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
    		//����actionForm
    		vo.setData(pf);
    		//����userDate
    		vo.setUserData(ud);
    	
    		pf = (WszmForm) ZhsbProxy.getInstance().process(vo);
    		
    		if (pf.getDataList().size() == 0) 
    		{
    			httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, getPrintMessage("û�з�����������Ϣ", true));
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
		  //��õ�ǰ��userData����
		  UserData ud = null;
		  WszmForm pf = null;
		  VOPackage vo = new VOPackage();
		  //ִ��Processor
		  try 
		  {
			  //��ʼ��
			  ud = this.getUserData(httpServletRequest);
			  pf = (WszmForm) actionForm;
			  if (pf == null)
			  {
				  pf = new WszmForm();
			  }
			  //����EJB
			  vo.setAction(CodeConstant.SMSB_QUERYACTION);
			  vo.setUserData(ud);
			  vo.setData(pf);
			  vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
			  pf = (WszmForm) ZhsbProxy.getInstance().process(vo);
			  
			  //��дsession����
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
	  	 * ��ʾ��˰֤��
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
	    	
	    	//�������ݰ�
	    	VOPackage vo = new VOPackage();
	    	//����
	    	vo.setAction(CodeConstant.SMSB_READERACTION);
	    	//����processor
	    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
	    	//����actionForm
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
	    	//����userDate
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
	     * ��ӡ��˰֤��
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
	    	
	    	//��ֹrefresh
	        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
	        if (forward != null)
	        {
	            return forward;
	        }
	    	
	    	UserData ud = this.getUserData(httpServletRequest);
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	try 
		    {
		    	//�������ݰ�
		    	VOPackage vo = new VOPackage();
		    	//����
		    	vo.setAction(CodeConstant.SMSB_SAVEACTION);
		    	//����processor
		    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
		    	vo.setData(form);
		    	//����userDate
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
	     * ��ӡ��˰֤���ɹ������ô�ӡ���
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
	    	
	    	//��ֹrefresh
	        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
	        if (forward != null)
	        {
	            return forward;
	        }
	    	
	    	UserData ud = this.getUserData(httpServletRequest);
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	try 
		    {
		    	//�������ݰ�
		    	VOPackage vo = new VOPackage();
		    	//����
		    	vo.setAction(CodeConstant.SMSB_PRINTACTION);
		    	//����processor
		    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
		    	vo.setData(form);
		    	//����userDate
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
	     * ��ӡ��˰֤���ɹ������ô�ӡ���
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
	    	
	    	//��ֹrefresh
	        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
	        if (forward != null)
	        {
	            return forward;
	        }
	    	
	    	UserData ud = this.getUserData(httpServletRequest);
	    	WszmForm form = (WszmForm) httpServletRequest.getAttribute("wszmForm");
	    	
	    	try 
		    {
		    	//�������ݰ�
		    	VOPackage vo = new VOPackage();
		    	//����
		    	vo.setAction(CodeConstant.SMSB_REPRINTACTION);
		    	//����processor
		    	vo.setProcessor(CodeConstant.WSZM_PROCESSOR);
		    	vo.setData(form);
		    	//����userDate
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
	     * ������˰֤
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
	            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
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
	   * ת�������Ϣ��ʽ
	   * @param message �������
	   * @param successFlag �ɹ�|ʧ��
	   * @return
	   */
	  private String getPrintMessage(String message, boolean successFlag) {
	    StringBuffer printMessage = new StringBuffer();
	    if (successFlag) { //�ɹ���Ϣ
	      printMessage.append("<br><strong><font color=\"#0000FF\">&nbsp;&nbsp;")
	          .append(message).append("��</font></strong>");
	    }
	    else { //ʧ����Ϣ
	      printMessage.append("<br><strong><font color=\"#FF0000\">&nbsp;&nbsp;")
	          .append(message).append("��</font></strong>");
	    }
	    return printMessage.toString();
	  }

}
