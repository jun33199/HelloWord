package com.ttsoft.bjtax.smsb.zjyjmsb.cx.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zjyjmsb.cx.ZjycxExcelUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zjyjmsb.cx.web.ZjyjmcxForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ--�����걨</p>
 * <p>Description: �پ�ҵ����˰�걨��ѯAction</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: ��һ����</p>
 * @author qinwei
 * @version 1.0
 */
public class ZjyjmcxAction 
        extends SmsbAction{
	 
	   /**
	   * ���ݼ����󣨰���Form��UserData����
	   */
	  private VOPackage vo = new VOPackage();
	   int rowNum = 0;
	   
	  public ZjyjmcxAction (){
		  
	  }
	  /**
	   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����
	   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	   * @param mapping The ActionMapping used to select this instance
	   * @param actionForm The optional ActionForm bean for this request (if any)
	   * @param httpServletRequest The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   */

	  protected void initialRequest(ActionMapping mapping,
	                                ActionForm actionForm,
	                                HttpServletRequest  request,
	                                HttpServletResponse response)

	  {  
		request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	           "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>�پ�ҵ����˰�걨���ѯ ");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "");
	  }
	  /**
	   * doShow
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @exception BaseException
	   */
	  public ActionForward doShow(ActionMapping mapping,
	                              ActionForm form,
	                              HttpServletRequest request,
	                              HttpServletResponse response) throws
	                              BaseException {
        //�������
		removeForm(mapping, request);
	    ZjyjmcxForm zjycxForm = null;
	    VOPackage vo = new VOPackage(); 
	  try {
	      //�������
		  zjycxForm = (ZjyjmcxForm)form;
		  zjycxForm.setDataList(new ArrayList());
	      vo.setData(zjycxForm);
	      vo.setUserData(this.getUserData(request));
	      vo.setAction(CodeConstant.SMSB_SHOWACTION);
	      vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	      zjycxForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      request.setAttribute(mapping.getAttribute(), zjycxForm);
	      return mapping.findForward("Show");
	    }
	    catch (Exception e) {
	      Debug.printException(e);
	      throw ExceptionUtil.getBaseException(e);
	    }

	  }
	  /**
	   * ��ѯ����
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request 
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @throws BaseException
	   */
	  public ActionForward doQuery(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) throws
	      BaseException {
		  ZjyjmcxForm zjycxForm = (ZjyjmcxForm)form;
		  //ÿ�β�ѯҳ���ʼ���ɵ�һҳ
		  zjycxForm.setNextPage("1");
	    try {
	      vo.setAction(CodeConstant.SMSB_QUERYACTION);
	      vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	      vo.setData(zjycxForm);
	      vo.setUserData(this.getUserData(request));
	      zjycxForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      System.out.println("zjycxForm.getDataList().size():" + zjycxForm.getDataList().size());
	      if (zjycxForm.getDataList().size() == 0) {
	        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
	                             , getPrintMessage("û�з�����������Ϣ", true));
	      }

	      //����form
	      request.setAttribute(mapping.getAttribute(), zjycxForm);
	      return mapping.findForward("Query");
	    }
	    catch (Exception e) {
	    	zjycxForm.setNextPage("0");
	    	zjycxForm.setTotalpage("0");
	      try {
	        vo.setAction(CodeConstant.SMSB_SHOWACTION);
	        vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	        vo.setData(zjycxForm);
	        vo.setUserData(this.getUserData(request));
	        zjycxForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      }
	      catch (Exception ex) {
	      }
	      //����form
	      request.setAttribute(mapping.getAttribute(), zjycxForm);
	      Debug.printException(e);
	      throw ExceptionUtil.getBaseException(e);
	    }

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
	  public ActionForward doChangePage(ActionMapping mapping,
	                                    ActionForm form,
	                                    HttpServletRequest request,
	                                    HttpServletResponse response) throws
	      BaseException {
	    super.doInit(mapping, form, request, response);
	    //��õ�ǰ��userData����
	    UserData ud = null;
	    ZjyjmcxForm zf = null;
	    VOPackage vo = new VOPackage();
	    //ִ��Processor
	    try {
	      //��ʼ��
	      ud = this.getUserData(request);
	      zf = (ZjyjmcxForm) form;
	      if (zf == null) {
	    	  zf = new ZjyjmcxForm();
	      }
	      //����EJB
	      vo.setAction(CodeConstant.SMSB_QUERYACTION);
	      vo.setUserData(ud);
	      vo.setData(zf);
	      vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	      zf = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      //��дsession����
	      request.setAttribute(mapping.getAttribute(), zf);


	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	      throw ExceptionUtil.getBaseException(ex);
	    }
	    return mapping.findForward("Show");
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
		  ZjyjmcxForm zjyForm = null;
		  VOPackage vo = new VOPackage();
		  //ִ��Processor
	      try {
	    	  //��ʼ��
	          ud = this.getUserData(request);
	          zjyForm = (ZjyjmcxForm) form;
	          //resetҳ�����
	          zjyForm.reset(mapping, request);
	          if (zjyForm == null) {
	        	  zjyForm = new ZjyjmcxForm();
	          }
	          //����EJB
	          vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
	          vo.setUserData(ud);
	          vo.setData(zjyForm);
	          vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	          zjyForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);        	         
	          if (zjyForm.getDataList().size() > 0) {
	            String currDate = TinyTools.Date2String(new Date(System.
	                currentTimeMillis()));
	            String fileName = "�پ�ҵ����˰�걨��ѯ���".concat(currDate).concat(".xls");
	            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
	                fileName);
	            response.resetBuffer();
	            response.setHeader("Content-disposition",
	                               "attachment; filename=" + encodeFileName);
	            response.setContentType("application/vnd.ms-excel");
	            ZjycxExcelUtil zjyExcel = new ZjycxExcelUtil();
	            zjyExcel.getZjycxExcel(response.getOutputStream(), zjyForm.getDataList());
	          }
	          else {
	        	  zjyForm.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
	          }
	          //��������
	          zjyForm.setDataList(new ArrayList());
	          //��дsession����
	          request.setAttribute(mapping.getAttribute(), zjyForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }
	        /**
	        if (zjyForm.getDataList().size() > 0) {
	          return null;
	        }
	        else {
	        	System.out.println("SaveExcel************");
	          return mapping.findForward("SaveExcel");
	        }
	        **/
	        return null;
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
