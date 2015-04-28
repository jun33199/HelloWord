/*
 * <p>Title: ������˰��������ϵͳ���� �����걨-- ����ռ��˰</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Description: ��ѯ˰Դ�Ǽ�</p>
 * @author ������ - ��
 * @version 1.0
 */
public class GdzysSydjcxAction extends SmsbAction {
	
    public GdzysSydjcxAction() {
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
                                        "<font color=\"#A4B9C6\">������Դռ��˰ϵͳ</font>&gt;<font color=\"#7C9AAB\">˰Դ�Ǽ�</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "˰Դ�Ǽǲ�ѯ");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");
    }
    
   /*-----------------------------------------ҳ����ת����--3--------------------------------------------------------*/
    /**
     * <1>��ѯҳ��չʾ����
     * 
     */
    public ActionForward doShow(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
				//ת�������������ݱ�
				GdzysSydjcxForm gdzysSydjcxForm = (GdzysSydjcxForm)actionForm;
				gdzysSydjcxForm.setNum(-1);

				//JSPȡ�����̿���
				request.setAttribute("gdzysSydjcxForm", gdzysSydjcxForm);
				return actionMapping.findForward("Show");			
            }
    
    /**
     * <2>�Ǽǲ�ѯ����
     * �������(1)0�������(2)1�����,(3)�����б�
     * @param mapping
     * @param ��ѯ��������
     * @param request
     * @param response
     * @return 1.δ�鵽 --�յĲ�ѯҳ��
     * 		   2.һ����¼--ֱ����ʾ
     * 		   3.������¼--��¼�б�
     * @throws BaseException��SmsbAction����
     */
    public ActionForward doQuery(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
    	        
        		//ת�������������ݱ�
    			GdzysSydjcxForm gdzysSydjcxForm = (GdzysSydjcxForm)actionForm;
    			  			
    			//Ȩ������-����ֻ�ܲ�����
    			UserData ud = getUserData(request);
    			gdzysSydjcxForm.setSwjgdm(ud.getSsdwdm());
    			
    			//���ݴ���
    			gdzysSydjcxForm = sendVO(gdzysSydjcxForm ,GdzysCodeConstant.SMSB_GDZYS_DJQuery,request);
    			
    			//JSPȡ�����̿���
    			request.setAttribute("gdzysSydjcxForm", gdzysSydjcxForm);
    			return actionMapping.findForward("QueryResult");		
            } 
    
    /**
     * <3>���������ʱ����ϸ��ѯ
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public ActionForward doQueryDetail(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
        		//ת�������������ݱ�
    			GdzysSydjcxForm gdzysSydjcxForm = (GdzysSydjcxForm)actionForm;
    			
    			//Ȩ������-����ֻ�ܲ�����
    			UserData ud = getUserData(request);
    			gdzysSydjcxForm.setSwjgdm(ud.getSsdwdm());
        
    			//���ݴ���
    			gdzysSydjcxForm = sendVO(gdzysSydjcxForm ,GdzysCodeConstant.SMSB_GDZYS_DJQuery ,request);
    			
    			//JSPȡ�����̿���
    			request.setAttribute("gdzysSydjcxForm", gdzysSydjcxForm);
    			return actionMapping.findForward("QueryResultManyDetail");

            } 
    

    /*-----------------------------------------���ܴ�����----------------------------------------------------------*/
    /**
     * �Ǽǲ�ѯʱ�Ĵ�����
     * @param data
     * @return
     * @throws BaseException
     */
   protected GdzysSydjcxForm sendVO(Object data ,int actionName ,HttpServletRequest request) throws BaseException
   {
	   //��װ������Ϣ
	   VOPackage vo = new VOPackage();
	   vo.setData(data);
	   vo.setUserData(this.getUserData(request));
	   vo.setAction(actionName);
	   vo.setProcessor("com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.processor.GdzysSydjcxProcessor");
	   
	   //����Ҫ��������
	   try {
		   	return (GdzysSydjcxForm)ZhsbProxy.getInstance().process(vo);
	   		} 
	   catch (Exception e) 
	   {
		   e.printStackTrace();
		   throw ExceptionUtil.getBaseException(e, "���ݴ����쳣");
	   }
   }
    
   
}
