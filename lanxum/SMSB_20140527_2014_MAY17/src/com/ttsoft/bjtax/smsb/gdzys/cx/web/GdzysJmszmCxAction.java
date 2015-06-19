package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.web.PlkscxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>Title:������˰��������ϵͳ--����ռ��˰����˰֤����ѯ </p>
 * <p>Description: ����˰֤����ѯAction</p>
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmszmCxAction extends SmsbAction{

	public GdzysJmszmCxAction(){
		
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
	                        // "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
	                         "<font color=\"#7C9AAB\">����ҵ���ѯ</font>&gt;"+
	                         "<font color=\"#7C9AAB\">����˰֤����ѯ</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "����˰֤����ѯ");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	  /**
	   * ��ʾ��ʼҳ�淽��
	   */
	  public ActionForward doShow(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws BaseException{
			try {
				GdzysJmszmCxForm pf = (GdzysJmszmCxForm) form;
				pf.setQueryFlag(false);
				VOPackage vo = new VOPackage();
				UserData userData = this.getUserData(request);
				String yhjb=userData.yhjb;
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
				vo.setProcessor(GdzysCodeConstant.GDZYS_CX_JMSZMCX_PROCESSOR);
				vo.setData(pf);
				vo.setUserData(userData);
				pf = (GdzysJmszmCxForm) ZhsbProxy.getInstance().process(vo);
				request.setAttribute(mapping.getAttribute(), pf);
				return mapping.findForward("Show");
				} catch (Exception e) {
					e.printStackTrace();
					throw ExceptionUtil.getBaseException(e);
				}
		}
	  

		//��ȡ˰��������
		public ActionForward doGetsws(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			GdzysJmszmCxForm gf = (GdzysJmszmCxForm) form;
			VOPackage vo = new VOPackage();
			vo.setData(gf);
	                vo.setUserData(this.getUserData(request));
			vo.setProcessor(GdzysCodeConstant.GDZYS_CX_JMSZMCX_PROCESSOR);

			vo.setAction(CodeConstant.SMSB_CXDQJSLIST);
			gf = (GdzysJmszmCxForm) ZhsbProxy.getInstance().process(vo);
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
	//���в�ѯAction	
		public ActionForward doQuery(
				ActionMapping mapping,
				ActionForm aForm, 
				HttpServletRequest request,
				HttpServletResponse response) throws BaseException {
				
				   VOPackage vo = new VOPackage(); 
					  try {
						  UserData userData = this.getUserData(request);
					      //�������
						  GdzysJmszmCxForm form = (GdzysJmszmCxForm)aForm;
						  form.setYhjb(userData.yhjb);
						  form.setQueryFlag(true);
						 // form.setPageSize(1);
						//��ȡ�־�����
							vo.setAction(CodeConstant.SMSB_DQJSLIST);
							vo.setProcessor(GdzysCodeConstant.GDZYS_CX_JMSZMCX_PROCESSOR);
							vo.setData(form);
							vo.setUserData(userData);
							form = (GdzysJmszmCxForm) ZhsbProxy.getInstance().process(vo);
							form.setPageSize(10);
					        vo.setData(form);
					        vo.setUserData(this.getUserData(request));
					      //action����
					      vo.setAction(CodeConstant.SMSB_QUERYACTION);
					      //processor����
					      vo.setProcessor(GdzysCodeConstant.GDZYS_CX_JMSZMCX_PROCESSOR);
					      //����form
					      form = (GdzysJmszmCxForm) ZhsbProxy.getInstance().process(vo);
					      
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
}
