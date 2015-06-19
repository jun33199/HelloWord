package com.ttsoft.bjtax.smsb.lwpk.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.PlkscxExcelUtil;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 *��ѯͳ��������˰���action
 *wangxq 
 * 20130718
 */
public class PlkscxAction extends SmsbAction{
	/**
	   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	   * @param mapping The ActionMapping used to select this instance
	   * @param actionForm The optional ActionForm bean for this request (if any)
	   * @param httpServletRequest The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   */

	  protected void initialRequest(ActionMapping mapping,
	                                ActionForm actionForm,
	                                HttpServletRequest httpServletRequest,
	                                HttpServletResponse response)

	  {
	    super.initialRequest(mapping, actionForm, httpServletRequest, response);
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	                                    "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�ۺϲ�ѯ</font>&gt;������˰�ۺϲ�ѯ&gt;</td>");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                                    "ͳ��������˰��ѯ");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                                    "help/smsb/gzwh/gzwh-000.htm");
	  }
	  
		 /**
	   * ���ܣ���ʾҳ�棡
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doShow(
		ActionMapping mapping,
		ActionForm aForm, 
		HttpServletRequest request,
		HttpServletResponse response) throws BaseException {
		try {
		//System.out.println("PlkscxAction################doShow");
		PlkscxForm pf = (PlkscxForm) aForm;
		pf.setQueryFlag(false);
		VOPackage vo = new VOPackage();
		UserData userData = this.getUserData(request);
		String yhjb=userData.yhjb;
		/*
		System.out.println("################userData.jdid="+userData.jdid);
		System.out.println("################userData.ssdwdm="+userData.ssdwdm);
		System.out.println("################userData.ssdwmc="+userData.ssdwmc);
		System.out.println("################userData.yhid="+userData.yhid);
		System.out.println("################userData.yhmc="+userData.yhmc);
		*/
		//System.out.println("################yhjb="+yhjb);	
		pf.setYhjb(yhjb);
		if(!yhjb.equals("50")){
			pf.setDqjs(userData.ssdwdm.substring(0, 2)+"00");	
			//dqjs
		}
		if(!yhjb.equals("50")&&!yhjb.equals("40")){
			pf.setCxdqjs(userData.ssdwdm);
		}
		//��ȡ�־�����
		vo.setAction(CodeConstant.SMSB_DQJSLIST);
		vo.setProcessor(CodeConstant.PLBCX_Processor);
		vo.setData(pf);
		vo.setUserData(userData);
		pf = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
		
		
		//��ȡ��������
		vo.setAction(CodeConstant.SMSB_CXYHLIST);
		vo.setProcessor(CodeConstant.PLBCX_Processor);
		vo.setData(pf);
		vo.setUserData(userData);
		pf = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
		
		pf.setDataList(new ArrayList());
		request.setAttribute(mapping.getAttribute(), pf);
		return mapping.findForward("Show");
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	}
	
	//��ȡ˰��������
	public ActionForward dogetsws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PlkscxForm gf = (PlkscxForm) form;
		VOPackage vo = new VOPackage();
		vo.setData(gf);
                vo.setUserData(this.getUserData(request));
		vo.setProcessor(CodeConstant.PLBCX_Processor);

		vo.setAction(CodeConstant.SMSB_CXDQJSLIST);
		gf = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
		List sws = gf.getCxswdwlist();
		response.setContentType("text/xml;charset=GB2312");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter responseOut = null;
		try {
			responseOut = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
		responseOut.write("<mb>");
		for (int i = 0; i < sws.size(); i++) {
			swdwmodel v = (swdwmodel) sws.get(i);
			responseOut.write("<gV>" + v.getSwdwid() + "</gV>");
			responseOut.write("<gU>" + v.getSwdwmc() + "</gU>");

		}
		
		responseOut.write("</mb>");
		// responseOut.flush();
		responseOut.close();

		return null;
	}

	
	
	
	 /**
	   * ���ܣ�������˰��Ϣ��
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doQuery(
		ActionMapping mapping,
		ActionForm aForm, 
		HttpServletRequest request,
		HttpServletResponse response) throws BaseException {
		
		   VOPackage vo = new VOPackage(); 
			  try {
				  UserData userData = this.getUserData(request);
			      //�������
				  PlkscxForm form = (PlkscxForm)aForm;
				  form.setYhjb(userData.yhjb);
				  form.setQueryFlag(true);
				 // form.setPageSize(1);
				//��ȡ�־�����
					vo.setAction(CodeConstant.SMSB_DQJSLIST);
					vo.setProcessor(CodeConstant.PLBCX_Processor);
					vo.setData(form);
					vo.setUserData(userData);
					form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
					
					
					//��ȡ��������
					vo.setAction(CodeConstant.SMSB_CXYHLIST);
					vo.setProcessor(CodeConstant.PLBCX_Processor);
					vo.setData(form);
					vo.setUserData(userData);
					form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
					form.setPageSize(10);
			      vo.setData(form);
			      vo.setUserData(this.getUserData(request));
			      //action����
			      vo.setAction(CodeConstant.SMSB_QUERYACTION);
			      //processor����
			      vo.setProcessor(CodeConstant.PLBCX_Processor);
			      //����form
			      form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
			      
			      System.out.println("#####form.getDataList().size()="+form.getDataList().size());
			      //����request
			      request.setAttribute(mapping.getAttribute(), form);
			      return mapping.findForward("Query");
			    }
			    catch (Exception e) {
			      Debug.printException(e);
			      throw ExceptionUtil.getBaseException(e);
			    }
	}
	
	 /**
	   * ���ܣ�������˰��ϸ��Ϣ��
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	public ActionForward doQueryKkDetail(
		ActionMapping mapping,
		ActionForm aForm, 
		HttpServletRequest request,
		HttpServletResponse response) throws BaseException {
		
		   VOPackage vo = new VOPackage(); 
			  try {
				  UserData userData = this.getUserData(request);
			      //�������
				  PlkscxForm form = (PlkscxForm)aForm;
				  form.setYhjb(userData.yhjb);
				  vo.setData(form);
			      vo.setAction(CodeConstant.SMSB_QUERYA_DETATILACTION);
			      //processor����
			      vo.setProcessor(CodeConstant.PLBCX_Processor);
			      //����form
			      form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
			      
			      System.out.println("doQueryKkDetail#####form.getDataList().size()="+form.getDataList().size());
			      //����request
			      request.setAttribute(mapping.getAttribute(), form);
			      return mapping.findForward("QueryDetail");
			    }
			    catch (Exception e) {
			      Debug.printException(e);
			      throw ExceptionUtil.getBaseException(e);
			    }
	}
	
	
	/**
	   * ����Excel�ļ�����
	   * @param mapping The ActionMapping used to select this instance
	   * @param aFrom The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @throws BaseException
	   */

	  public ActionForward doExport(ActionMapping mapping, ActionForm form,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) throws
	         Exception {
		  super.doInit(mapping, form, request, response);
      //��õ�ǰ��userData����
		  UserData ud = null;
		  VOPackage vo = new VOPackage();
		  //ִ��Processor
		  OutputStream os = response.getOutputStream();
	      try {
	    	 
	    	  
	    	  //��ʼ��
	          ud = this.getUserData(request);
	          PlkscxForm theForm = (PlkscxForm)form;
	          theForm.setYhjb(ud.yhjb);
	          //resetҳ�����
	         // theForm.reset(mapping, request);
	          //����EJB
	          vo.setAction(CodeConstant.SMSB_EXPORT);
	          vo.setUserData(ud);
	          vo.setData(theForm);
	          vo.setProcessor(CodeConstant.PLBCX_Processor);
	          theForm = (PlkscxForm) ZhsbProxy.getInstance().process(vo); 
	          String currDate = TinyTools.Date2String(new Date(System.
		                currentTimeMillis()));
		            //�ļ�����
		            String fileName = "�����ۿ��ѯ���".concat(currDate).concat(".xls");
		            //�ַ���ת��
		            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
		                fileName);
	        	  response.addHeader("Content-Disposition",
	  			"attachment; filename=\"" + encodeFileName + "\"");
	            PlkscxExcelUtil plkscxExcelUtil = new PlkscxExcelUtil();
	            plkscxExcelUtil.getPlkscxExcel(os, (List)theForm.getDataList());
	          //}
	          //else {
	        	//  theForm.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
	         // }
	          //request.setAttribute(mapping.getAttribute(), theForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }finally{
	        	os.flush();
				os.close();
				os = null ;
	        }
	        
	        return null;
	      }
	  /**
	   * ����Excel�ļ�����
	   * @param mapping The ActionMapping used to select this instance
	   * @param aFrom The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @throws BaseException
	   */

	  public ActionForward doExportDetail(ActionMapping mapping, ActionForm form,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) throws
	         Exception {
		  super.doInit(mapping, form, request, response);
      //��õ�ǰ��userData����
		  UserData ud = null;
		  VOPackage vo = new VOPackage();
		  //ִ��Processor
		  OutputStream os = response.getOutputStream();
	      try {
	    	 
	    	  
	    	  //��ʼ��
	          ud = this.getUserData(request);
	          PlkscxForm theForm = (PlkscxForm)form;
	          theForm.setYhjb(ud.yhjb);
	          //resetҳ�����
	         // theForm.reset(mapping, request);
	          //����EJB
	          vo.setAction(CodeConstant.SMSB_EXPORTDETAIL);
	          vo.setUserData(ud);
	          vo.setData(theForm);
	          vo.setProcessor(CodeConstant.PLBCX_Processor);
	          theForm = (PlkscxForm) ZhsbProxy.getInstance().process(vo); 
	          String currDate = TinyTools.Date2String(new Date(System.
		                currentTimeMillis()));
		            //�ļ�����
		            String fileName = "�����ۿ��ѯ�����ϸ".concat(currDate).concat(".xls");
		            //�ַ���ת��
		            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
		                fileName);
	        	  response.addHeader("Content-Disposition",
	  			"attachment; filename=\"" + encodeFileName + "\"");
	            PlkscxExcelUtil plkscxExcelUtil = new PlkscxExcelUtil();
	            plkscxExcelUtil.getPlkscxDetailExcel(os, (List)theForm.getDataList());
	          //}
	          //else {
	        	//  theForm.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
	         // }
	          //request.setAttribute(mapping.getAttribute(), theForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }finally{
	        	os.flush();
				os.close();
				os = null ;
	        }
	        
	        return null;
	      }
	
}
