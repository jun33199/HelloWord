package com.ttsoft.bjtax.smsb.lwpk.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.SJJHWHCommon;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: ������˰��������ϵͳ--˰����</p>
 * <p>Description: ��ʱ�ƻ���Action</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author �����з����ţ���������
 * @version 1.0
 */
public class PKTaskAction extends SmsbAction{

	
	 public PKTaskAction() {
		super();
	}
	 
	 UserData userData = null;

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
	                                    "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">ί�����пۿ�����</font>&gt;��ʱ�ƻ���ά��&gt;</td>");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                                    "��ʱ�ƻ���ά��");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                                    "help/smsb/gzwh/gzwh-000.htm");
	  }
	  /**
	   * ���ܣ���ʼ����ʱ�ƻ���
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doInit(
			ActionMapping mapping,
            ActionForm aForm, 
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
		
		    VOPackage vo = new VOPackage();
		    PKTaskForm yForm = (PKTaskForm) aForm;
		    userData = this.getUserData(request);
		    vo.setAction(CodeConstant.SMSB_INIT);
		    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		    vo.setData(yForm);
		    vo.setUserData(userData);
		    //��ʼ������Ƿ��Ѿ���ʼ��
		    String isinit = null;
		  //ʵ����ʱ��ƻ�ά��ͨ�ö���
			SJJHWHCommon sjjhwh = new SJJHWHCommon();
		    try {
				isinit = (String)ZhsbProxy.getInstance().process(vo);
				yForm.setIsinit(isinit);
				//���ò�ѯ�¶��б�
				yForm.setCxydList(sjjhwh.getCxydList());
				//���ò�ѯʱ���б�
				yForm.setZxsjList(sjjhwh.getZxsjList());
				request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return mapping.findForward("Init");
		
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
		//�Ƿ��ǳ�ʼ��
		request.getSession().setAttribute("is_plkksjjh_show", "true");
		 	//ʵ����ʱ��ƻ�ά��ͨ�ö���
			SJJHWHCommon sjjhwh = new SJJHWHCommon();
			PKTaskForm yForm = (PKTaskForm) aForm;
			yForm.setYd("00");
			yForm.setCxrwlx("00");
			//��������list
			yForm.setCxlxList(sjjhwh.getCxlxList());
			//������������list
			yForm.setCxlxmcList(sjjhwh.getCxlxmcList());
			//���ò�ѯ�¶��б�
			yForm.setCxydList(sjjhwh.getCxydList());
			//���ò�ѯʱ���б�
			yForm.setZxsjList(sjjhwh.getZxsjList());
			//��ʼ������
			yForm.setIsinit("true");
			request.setAttribute("pkTaskForm", yForm);
			return mapping.findForward("Show");
		
	}
	
	
	 /**
	   * ���ܣ�����������ѯ��ʱ�ƻ���
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
		
		   request.getSession().setAttribute("is_plkksjjh_show", "false");
		   VOPackage vo = new VOPackage();
		   PKTaskForm yForm = (PKTaskForm) aForm;
		    userData = this.getUserData(request);
		    vo.setAction(CodeConstant.SMSB_QUERYACTION);
		    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		    vo.setData(yForm);
		    vo.setUserData(userData);
		  //ʵ����ʱ��ƻ�ά��ͨ�ö���
			SJJHWHCommon sjjhwh = new SJJHWHCommon();
		    try {
				List pkTaskList = (List)ZhsbProxy.getInstance().process(vo);
			    //ʱ��ƻ��б�
				//��������list
				yForm.setCxlxList(sjjhwh.getCxlxList());
				//������������list
				yForm.setCxlxmcList(sjjhwh.getCxlxmcList());
			  //���ò�ѯ�¶��б�
			    yForm.setCxydList(sjjhwh.getCxydList());
			  //���ò�ѯʱ���б�
			    yForm.setZxsjList(sjjhwh.getZxsjList());
			    yForm.setPkTaskList(pkTaskList);
			    //�ж��Ƿ��ܹ���ʼ��
			    if(pkTaskList.size()>0){
			    	yForm.setIsinit("false");
			    }else{
			    	yForm.setIsinit("true");
			    }

			    request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		return mapping.findForward("Query");
	}
	
	
	 /**
	   * ���ܣ��޸Ķ�ʱ�ƻ���
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doSaveModify(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {
		
		VOPackage vo = new VOPackage();
		PKTaskForm yForm = (PKTaskForm) aForm;
		userData = this.getUserData(request);
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		vo.setData(yForm);
		vo.setUserData(userData);
			try {
				
				ZhsbProxy.getInstance().process(vo);
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return mapping.findForward("SaveModify");
	}
	
	
	 /**
	   * ���ܣ�ɾ����ʱ�ƻ���
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doDelete(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {
		
		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 
		 System.out.println(yForm.getXh());
		 
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_DELETEACTION);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 //����ȵ�ʱ��ƻ��б�
			 List pkTaskList = (List)ZhsbProxy.getInstance().process(vo);
			 //��������õ�ǰ̨ҳ��
			 yForm.setPkTaskList(pkTaskList);
			 request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return mapping.findForward("Delete");
	}
	
	
	 /**
	   * ���ܣ�����µĶ�ʱ�ƻ���
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doSaveAdd(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {
		
		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_SAVEACTION);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 ZhsbProxy.getInstance().process(vo);
			
			} catch (Exception e) {
				e.printStackTrace();
			}		
		return mapping.findForward("SaveAdd");
	}
	
	 /**
	   * ���ܣ���ӡ��ʱ�ƻ���
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doPrint(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {

		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_PRINTDATA);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 
			 List pkTaskList = (List) ZhsbProxy.getInstance().process(vo);
			 yForm.setPkTaskList(pkTaskList);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		
		request.setAttribute("pkTaskForm", yForm);
		return mapping.findForward("Print");
	}
	
	/**
	   * ���ܣ��������ɴ�����Ϣ���ܣ�
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doGenDKXX(ActionMapping mapping,
								 ActionForm aForm, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws BaseException {
		 
		System.out.println("��������action");
		
		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_SAVEACTION);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 ZhsbProxy.getInstance().process(vo);
			 
			 System.out.println("���ɴ��۷���action");
//			 yForm.setPkTaskList(pkTaskList);
//			 request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		return mapping.findForward("Test1");
	}
	
	
	
}
