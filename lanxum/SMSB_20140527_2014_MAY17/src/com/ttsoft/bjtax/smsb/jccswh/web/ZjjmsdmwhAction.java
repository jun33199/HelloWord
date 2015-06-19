package com.ttsoft.bjtax.smsb.jccswh.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.gzsxexcel.DateTimeUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * �ּܾ���˰����ά�� �ù����Ƕ��ּܾ���˰��Ŀ����ά��
 * 
 * @author tangchangfu 2014-04-22
 * 
 *         ע���˹��ܵĲ˵��ڡ���ƺ��㡷��������������ά�����ּܾ���˰����ά����
 * 
 */
public class ZjjmsdmwhAction extends SmsbAction {
	/**
	 * ����ҳ�浼��·��
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#7C9AAB\">��ƺ���ϵͳ&gt;������&gt;��������ά��&gt;</font>�ּܾ���˰����ά�� ");
		request.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	
	/**
	 * ��ʼ��:
	 * @param cForm
	 * @param userData
	 */
	private void init(ZjjmsdmwhForm cForm,UserData userData){
		
		VOPackage vo = new VOPackage();
		ArrayList szList = new ArrayList();
		ArrayList jmxzdlList = new ArrayList();
		ArrayList jmxzxlList = new ArrayList();
		
		// ִ��Processor
		try {
			// ��ʼ��
			vo.setAction(CodeConstant.SMSB_INIT);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
			
			// ����EJB
			HashMap resMap = (HashMap)ZhsbProxy.getInstance().process(vo);
			
			if(resMap !=null && !resMap.isEmpty()){
				szList = (ArrayList)resMap.get(CodeConstant.SMSB_MAP_KEY_SZ);
				jmxzdlList = (ArrayList)resMap.get(CodeConstant.SMSB_MAP_KEY_JMXZDL);
				jmxzxlList = (ArrayList)resMap.get(CodeConstant.SMSB_MAP_KEY_JMXZXL);
			}
			
			cForm.setSmsmList(szList);
			cForm.setJmxzdlList(jmxzdlList);
			cForm.setJmxzxlList(jmxzxlList);
			
			cForm.setLrrmc(userData.getYhmc());
			cForm.setLrrq(DateTimeUtil.getCurrentDate());
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
		}
	}
	
	
	
	/**
	 * ��ʼ��
	 */
	public ActionForward doShow(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
		
		//ͨ��request.getSession(false).getAttribute("UserData");���UserDate
        UserData userData = this.getUserData(httpServletRequest);
        
		this.init(form, userData);
		form.setModifyKey_jmslxdm(null);
		
		return actionMapping.findForward("Show");
	}
	
	
	/**
	 * ��õ���
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doShowOne(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
		this.init(form, userData);
		
        /*��ʼ��VO��*/
        VOPackage vo = new VOPackage();//��ʼ�����ݴ�������
        vo.setUserData(userData);//�����������Data����,�������������ActionForm
        vo.setData(form);//����ProxyҪ���õ�processor������
        vo.setAction(CodeConstant.QUERYONE);//���ú�̨����Actionֵ
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (ZjjmsdmwhForm) SbzlProxy.getInstance().process(vo);
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
        	this.init(form, userData);
            //ϵͳ��׽�쳣�������쳣�����׳�
        	ex.printStackTrace();
			//������Ϣ
        	form.setMessage(ex.getMessage());
        	this.doQuery(actionMapping, form, httpServletRequest, httpServletResponse);
        }
        return actionMapping.findForward("Query");
	}
	
	
	
	/**
	 * תά��
	 */
	public ActionForward doToWeiHu(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		System.out.println("+++++++++++++++++++תά��++++++++++++++++++++++++++++++++");
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
		this.init(form, userData);
		try{
			
			form.setQuery_lrrqKS(DateTimeUtil.getCurrentDate());
			form.setQuery_lrrqJS(DateTimeUtil.getCurrentDate());
			
			this.doQuery(actionMapping, form, httpServletRequest, httpServletResponse);
			
			
			return actionMapping.findForward("Query");
		}catch(Exception e){
			e.printStackTrace();
			form.setMessage("��ת��ά������ʧ��");
		}
		return actionMapping.findForward("Show");
	}
	
	
	/**
	 * ִ�в�ѯ
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doQuery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
        //��ǰ��ActionForm
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
        /*��ʼ��VO��*/
        VOPackage vo = new VOPackage();//��ʼ�����ݴ�������
        vo.setUserData(userData);//�����������Data����,�������������ActionForm
        vo.setData(form);//����ProxyҪ���õ�processor������
        vo.setAction(CodeConstant.SMSB_QUERYACTION);//���ú�̨����Actionֵ
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (ZjjmsdmwhForm) SbzlProxy.getInstance().process(vo);
            System.out.println("+++++++++++++++++��1��"+form.getQueryList_onePage().size());
            //��ת
            this.init(form, userData);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            System.out.println("+++++++++++++++++��2��"+form.getQueryList_onePage().size());
            if((form.getQueryList_onePage() == null || form.getQueryList_onePage().size() == 0) &&
            	(form.getActionType() != null && "Query".equals(form.getActionType()))){
            	form.setMessage("û�������ѯ�����Ľ���������ѯ�����Ƿ���ȷ��");
            }
            //return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
        	ex.printStackTrace();
			//������Ϣ
        	form.setMessage(ex.getMessage());
        	this.init(form, userData);
        }
        
        return actionMapping.findForward("Query");
	}
	
	
	/**
	 * ִ�и���
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doUpdate(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		String returnStr="Show";
        //��ǰ��ActionForm
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
        
        String updateType = form.getUpdateType();
        if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
        	returnStr="Show";
        }else if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType)){
        	returnStr="Query";
        }
        
        
        /*��ʼ��VO��*/
        VOPackage vo = new VOPackage();//��ʼ�����ݴ�������
        vo.setUserData(userData);//�����������Data����,�������������ActionForm
        vo.setData(form);//����ProxyҪ���õ�processor������
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);//���ú�̨����Actionֵ
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            
            //
            form.setMessage("�޸���Ϣ����ɹ�!");
            //��ת
            this.init(form, userData);
            System.out.println("actionMapping.getAttribute()+++++++++++++++++"+actionMapping.getAttribute());
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            
            //�޸���Ч��־��ɺ�ִ�в�ѯ��������ѯ���޸ĺ�������Ϣ
            if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType)){
            	this.doQuery(actionMapping, form, httpServletRequest, httpServletResponse);
            }
            return actionMapping.findForward(returnStr);
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
        	ex.printStackTrace();
			//������Ϣ
        	form.setMessage(ex.getMessage());
        	this.init(form, userData);

        }
        
        return actionMapping.findForward(returnStr);
	}
	
	/**
	 * ִ������
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doSave(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		System.out.println("+++++++++++++++++++��ʼ��������++++++++++++++++++++++++++++++++");
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
        /*��ʼ��VO��*/
        VOPackage vo = new VOPackage();//��ʼ�����ݴ�������
        vo.setUserData(userData);//�����������Data����,�������������ActionForm
        vo.setData(form);//����ProxyҪ���õ�processor������
        vo.setAction(CodeConstant.SMSB_SAVEACTION);//���ú�̨����Actionֵ
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            
            //��ʼ��
    		//this.init(form, userData);
            form.setMessage("����ɹ�!");
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            
            this.doToWeiHu(actionMapping, form, httpServletRequest, httpServletResponse);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
        	//ϵͳ��׽�쳣�������쳣�����׳�
        	ex.printStackTrace();
            //��ʼ��
    		this.init(form, userData);
			//������Ϣ
        	form.setMessage(ex.getMessage());
        	form.setModifyKey_jmslxdm(null);
        }
        return actionMapping.findForward("Show");
	}
	
	
	
	
	
	

}
