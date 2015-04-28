/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������Դռ��˰-���ȷ��</p>
 * <p>Description: ���˰Դ��Ϣ</p>
 * @author ������ - ��
 * @version 1.0
 */
public class GdzysShqrAction extends SmsbAction {
	
    public GdzysShqrAction() {
    }
    
    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)
    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">������Դռ��˰ϵͳ</font>&gt;<font color=\"#7C9AAB\">˰Դ���ȷ��</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "˰Դ���ȷ��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");
    }
    
   /*-----------------------------------------ҳ����ת����--3--------------------------------------------------------*/
    /**
     * <1>���ҳ���ѯչʾ����
     * 
     */
    public ActionForward doShow(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
				//ת�������������ݱ�
				GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)actionForm;
			//  gdzysShqrForm.setNum(-1);

				//��ʼ��ѯ�Լ�������
				gdzysShqrForm = sendVO(gdzysShqrForm ,GdzysCodeConstant.SMSB_GDZYS_DJQuery,request);
				
				//JSPȡ�����̿���
				request.setAttribute("gdzysShqrForm", gdzysShqrForm);
				return actionMapping.findForward("Show");			
            }
    
    /**
     * <2>�Ǽǲ�ѯ����
     * �������(1)0�������(2)1�����,(3)�����б�
     * @param mapping
     * @param ��ѯ��������
     * @param request
     * @param response
     * @return 1.δ�鵽--�յĲ�ѯҳ��
     * 		   2.�м�¼--��¼�б�
     * @throws BaseException��SmsbAction����
     */
    public ActionForward doQuery(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
    	        
        		//ת�������������ݱ�
    			GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)actionForm;
    			
    			//���ݴ���
    			gdzysShqrForm = sendVO(gdzysShqrForm ,GdzysCodeConstant.SMSB_GDZYS_DJQuery,request);
    			
    			//JSPȡ�����̿���
    			request.setAttribute("gdzysShqrForm", gdzysShqrForm);
    			return actionMapping.findForward("QueryResult");		
            } 
    
//    /**
//     * <3>���������ʱ����ϸ��ѯ
//     * @param actionMapping
//     * @param actionForm
//     * @param request
//     * @param response
//     * @return
//     * @throws BaseException
//     */
//    public ActionForward doQueryDetail(ActionMapping actionMapping,
//            ActionForm actionForm,
//            HttpServletRequest request,
//            HttpServletResponse response) throws BaseException 
//            {
//        		//ת�������������ݱ�
//    			GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)actionForm;
//        
//    			//���ݴ���
//    			gdzysShqrForm = sendVO(gdzysShqrForm ,GdzysCodeConstant.SMSB_GDZYS_DJQueryDetail ,request);
//    			
//    			//JSPȡ�����̿���
//    			request.setAttribute("gdzysShqrForm", gdzysShqrForm);
//    			return actionMapping.findForward("QueryResultManyDetail");
//            }
//    
//  /**
//  * <4>���ȷ��ҳ��չʾ����
//  * 
//  */
// public ActionForward doShowShqr(ActionMapping actionMapping,
//         ActionForm actionForm,
//         HttpServletRequest request,
//         HttpServletResponse response) throws BaseException 
//         {
//				//ת�������������ݱ�
//				GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)actionForm;
//
//				//JSPȡ�����̿���
//				request.setAttribute("gdzysShqrForm", gdzysShqrForm);
//				return actionMapping.findForward("ShowShqr");			
//         }
//    
//    /**
//     * <5>ȷ����˵����ݿ����
//     * @param actionMapping
//     * @param actionForm
//     * @param request
//     * @param response
//     * @return
//     * @throws BaseException
//     */
//    public ActionForward doShqrUpdate (ActionMapping actionMapping,
//            ActionForm actionForm,
//            HttpServletRequest request,
//            HttpServletResponse response) throws BaseException 
//            {
//        		//ת�������������ݱ�
//    			GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)actionForm;
//        
//    			//���ݴ���
//    			gdzysShqrForm = sendVO(gdzysShqrForm ,GdzysCodeConstant.SMSB_GDZYS_UPDATESH,request);
//    			gdzysShqrForm.setNum(-1);
//    			
//    			//JSPȡ�����̿���
//    			request.setAttribute("gdzysShqrForm", gdzysShqrForm);
//    			return actionMapping.findForward("Show");
//            }
//    

    /*-----------------------------------------���ܴ�����----------------------------------------------------------*/
    /**
     * �Ǽǲ�ѯʱ�Ĵ�����
     * @param data
     * @return
     * @throws BaseException
     */
   protected GdzysShqrForm sendVO(Object data ,int actionName ,HttpServletRequest request) throws BaseException
   {
	   //��װ������Ϣ
	   VOPackage vo = new VOPackage();
	   vo.setData(data);
	   vo.setUserData(this.getUserData(request));
	   vo.setAction(actionName);
	   vo.setProcessor("com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.processor.GdzysShProcessor");
	   
	   //����Ҫ��������
	   try {
		   	return (GdzysShqrForm)ZhsbProxy.getInstance().process(vo);
	   		} 
	   catch (Exception e) 
	   {
		   e.printStackTrace();
		   throw ExceptionUtil.getBaseException(e, "���ݴ����쳣");
	   }
   }
    
   
}
