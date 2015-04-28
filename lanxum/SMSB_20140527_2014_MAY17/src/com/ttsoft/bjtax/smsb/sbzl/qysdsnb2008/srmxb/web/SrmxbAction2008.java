/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srmxb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.syax.creports.Constants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author zhangyj
 * @version 1.1
 */

public class SrmxbAction2008 extends 
   SmsbAction 
   {
	/**
     * ����Ա����
     */

    private UserData userData = null;
    
    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm QysdsnbForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��ҵ����˰�����˰�걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }
    
    /**
     * ��ʼ��ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
    	SrmxbForm2008 srmxbForm = (SrmxbForm2008)form;
        userData = this.getUserData(request);
        this.getBaseForm(request, srmxbForm); //������˰�˻�������
        VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION); //���ò�������
		vo.setUserData(userData);
		vo.setData(srmxbForm); //���ò�������
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_SRMXB_PROCESSOR2008); //����PROCESSOR
		try
		{
			srmxbForm =(SrmxbForm2008)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), srmxbForm); //�Ѳ�ѯ�������REQUEST
		}catch(Exception ex)
		{
			throw ExceptionUtil.getBaseException(ex);
		}

        return mapping.findForward("Show");
        
    }  
    
    /**
     * ����ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    
    public ActionForward doSave (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
    	
		SrmxbForm2008 srmxbForm = (SrmxbForm2008)form;
     userData = this.getUserData(request);
	  String[] sb_columns = srmxbForm.getSb_columns();
	  srmxbForm.setSrmxb(SfRequestUtil.getValuesToList(request, sb_columns)); //����ҳ������
	  this.getBaseForm(request, srmxbForm); //������˰�˻�����Ϣ
	  request.setAttribute(mapping.getAttribute(), srmxbForm);
	  
	  VOPackage vo = new VOPackage();
	  vo.setAction(CodeConstant.SMSB_SAVEACTION); //���ò�������
	  vo.setData(srmxbForm); //���ò�������
	  vo.setUserData(userData); 
	  vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_SRMXB_PROCESSOR2008); //����PROCESSOR
	  try
	  {
		  srmxbForm = (SrmxbForm2008)SbzlProxy.getInstance().process(vo);
		  request.setAttribute(mapping.getAttribute(), srmxbForm);
	  }catch(Exception ex)
	  {
//		ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
	  }
	  request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���"); //���ر�����
	  return mapping.findForward("Show");
	  
	}   
    
    /**
     * ɾ��ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doDelete (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
    	
		SrmxbForm2008 srmxbForm = (SrmxbForm2008)form;
        userData = this.getUserData(request);
        this.getBaseForm(request, srmxbForm); //������˰�˻�����Ϣ
        
   	  VOPackage vo = new VOPackage();
   	  vo.setAction(CodeConstant.SMSB_DELETEACTION); //���ò�������
   	  vo.setData(srmxbForm); //���ò�������
   	  vo.setUserData(userData);
   	  vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_SRMXB_PROCESSOR2008); //����PROCESSOR
   	  try
   	  {
   		srmxbForm= (SrmxbForm2008)SbzlProxy.getInstance().process(vo);
   		  request.setAttribute(mapping.getAttribute(), srmxbForm);
   	  }catch(Exception ex)
   	  {
//   		ϵͳ��׽�쳣�������쳣�����׳�
   			throw ExceptionUtil.getBaseException(ex);
   	  }
   	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���"); //����ɾ�����
   	  return mapping.findForward("Show");
	
	}   
    
    /**
     * ���ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doCheck (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
    	
		SrmxbForm2008 srmxbForm = (SrmxbForm2008)form;
        userData = this.getUserData(request);
   	  String[] sb_columns = srmxbForm.getSb_columns();
   	  srmxbForm.setSrmxb(SfRequestUtil.getValuesToList(request, sb_columns)); //����ҳ������
   	  this.getBaseForm(request, srmxbForm); //������˰�˻�����Ϣ
   	  request.setAttribute(mapping.getAttribute(), srmxbForm);
   	  
   	  VOPackage vo = new VOPackage();
   	  vo.setAction(CodeConstant.SMSB_CHECKACTION); //���ò�������
   	  vo.setData(srmxbForm); //���ò�������
   	  vo.setUserData(userData);
   	  vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srmxb.processor.SrmxbProcessor2008"); //����PROCESSOR
   	  try
   	  {
   		srmxbForm = (SrmxbForm2008)SbzlProxy.getInstance().process(vo);
   		request.setAttribute(mapping.getAttribute(), srmxbForm);
		
		if(srmxbForm.getCheckList()==null){
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
		}else if(srmxbForm.getCheckList().size()==0){
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
		}else if(srmxbForm.getCheckList().size()>0){
			request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2008.getCheckResults(srmxbForm.getCheckList()));
		}
		return mapping.findForward("Show");
   	  }catch(Exception ex)
   	  {
//   		ϵͳ��׽�쳣�������쳣�����׳�
   			throw ExceptionUtil.getBaseException(ex);
   	  }
	}   
    /**
     * �˳�ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doExit (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
	
	return mapping.findForward("Return");
	} 
    /**
	 * ��ȡ��˰�˻�����Ϣ
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request,SrmxbForm2008 form)
	{
		
		userData = this.getUserData(request);
		
		QysdsNewForm baseForm =(QysdsNewForm)request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		if(baseForm!=null)
		{
			form.setJsjdm(baseForm.getJsjdm());
			form.setSbrq(baseForm.getSbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			form.setSkssksrq(baseForm.getSkssksrq());
			form.setSkssjsrq(baseForm.getSkssjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setCkzd(baseForm.getCkzd());
			form.setZsfs(baseForm.getZsfs());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setGzglxs(baseForm.getGzglxs());
			form.setJmlx(baseForm.getJmlx());
			form.setNextTableURL(QysdsUtil2008.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_2008_1_1));
			form.setPreviousTableURL(QysdsUtil2008.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_2008_1_1));
		}
	}
    

}