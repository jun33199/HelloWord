package com.ttsoft.bjtax.smsb.lwpk.web;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.DhkscxExcelUtil;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 *��ѯ�����ۿ�action
 *kanght 
 * 201307
 */
public class DhkscxAction extends SmsbAction{

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
	                                    "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�ۺϲ�ѯ</font>&gt;����������˰��ѯ&gt;</td>");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                                    "����������˰��ѯ");
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
		//�ж��Ƿ��ǳ�ʼ��
		request.getSession().setAttribute("is_show", "true");
		return mapping.findForward("Show");
		
	}
	 /**
	   * ���ܣ���ѯ������Ϣ��
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
		//�ж��Ƿ��ǳ�ʼ��
		request.getSession().setAttribute("is_show", "false");
		   VOPackage vo = new VOPackage(); 
			  try {
			      //�������
				  DhkscxForm form = (DhkscxForm)aForm;
			      vo.setData(form);
			      vo.setUserData(this.getUserData(request));
			      //action����
			      vo.setAction(CodeConstant.SMSB_QUERYACTION);
			      //processor����
			      vo.setProcessor(CodeConstant.DHKSCX_Processor);
			      //����form
			      DhkscxForm theForm = (DhkscxForm) ZhsbProxy.getInstance().process(vo);
			      //����request
			      request.setAttribute(mapping.getAttribute(), theForm);
			      return mapping.findForward("Query");
			    }
			    catch (Exception ex) {
			    	ex.printStackTrace();
			      throw ExceptionUtil.getBaseException(ex);
			    }
		
	}
	
	/**
	 * ��ҳ��ѯ
	 * 
	 * 
	 */
	public ActionForward doChangePage(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
			BaseException {
			super.doInit(mapping, form, request, response);
			//��õ�ǰ��userData����
			UserData ud = null;
			DhkscxForm zf = null;
			VOPackage vo = new VOPackage();
			//ִ��Processor
			try {
			//��ʼ��
			ud = this.getUserData(request);
			zf = (DhkscxForm) form;
			if (zf == null) {
			zf = new DhkscxForm();
			}
			//����EJB
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(ud);
			vo.setData(zf);
			vo.setProcessor(CodeConstant.DHKSCX_Processor);
			zf = (DhkscxForm) ZhsbProxy.getInstance().process(vo);
			//��дsession����
			request.setAttribute(mapping.getAttribute(), zf);
			}
			catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
			}
			return mapping.findForward("Query");
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

	  public ActionForward doSaveExcel(ActionMapping mapping, ActionForm form,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) throws
	         BaseException {
		  super.doInit(mapping, form, request, response);
        //��õ�ǰ��userData����
		  UserData ud = null;
		  DhkscxForm theForm = null;
		  VOPackage vo = new VOPackage();
		  //ִ��Processor
	      try {
	    	  //��ʼ��
	          ud = this.getUserData(request);
	          theForm = (DhkscxForm) form;
	          //resetҳ�����
	          theForm.reset(mapping, request);
	          //����EJB
	          vo.setAction(CodeConstant.SMSB_QUERYACTION);
	          vo.setUserData(ud);
	          vo.setData(theForm);
	          vo.setProcessor(CodeConstant.DHKSCX_Processor);
	          theForm = (DhkscxForm) ZhsbProxy.getInstance().process(vo); 
	          if (theForm.getPkjbsjModel() != null && theForm.getPlkkdhcxlist().size()>0 ) {
	        	  //��ǰʱ��
	            String currDate = TinyTools.Date2String(new Date(System.
	                currentTimeMillis()));
	            //�ļ�����
	            String fileName = "���������ۿ�״̬��ѯ���".concat(currDate).concat(".xls");
	            //�ַ���ת��
	            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
	                fileName);
	            response.resetBuffer();
	            response.setHeader("Content-disposition",
	                               "attachment; filename=" + encodeFileName);
	            response.setContentType("application/vnd.ms-excel");
	            DhkscxExcelUtil dhkscxExcel = new DhkscxExcelUtil();
	            dhkscxExcel.getDhkscxExcel(response.getOutputStream(), theForm.getPlkkdhcxlist(),theForm.getPkjbsjModel());
	          }
	          else {
	        	  theForm.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
	          }
	          //��������
	          theForm.setPlkkdhcxlist(new ArrayList());
	          //��дsession����
	          request.setAttribute(mapping.getAttribute(), theForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }
	        
	        return null;
	      }
	  
	  
	  
	  
	  
	  
}
