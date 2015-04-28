package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��-��ҵ����˰����2014��-�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: ������˼�����ӿƼ����޹�˾</p>
 *
 * @author zhangyj
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.dm.GJBZHY;
import com.ttsoft.bjtax.dj.util.*;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ygyjzjlnstzb.web.YgyjzjlnstzbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class GdzcjszjyjqkjbAction extends SmsbAction
{
  
    public GdzcjszjyjqkjbAction()
    {
    }

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param actionForm
     *            QysdsnbForm
     * @param httpServletRequest
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */

    protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest, HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;<font color=\"#7C9AAB\">�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                "�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }



    /**
     * ��ѯ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doQuery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {

    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;

        //���ұ�׼��ҵ
        List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
        ArrayList gjbzhyList=new ArrayList();
        for(int i=0;i<gjbzhy.size();i++){
        	GJBZHY gjbzhyObject=(GJBZHY)gjbzhy.get(i);
        	String gjbzhydm=gjbzhyObject.getGjbzhydm();
        	if(gjbzhydm!=null && !gjbzhydm.equals("")&&gjbzhydm.length()==4){
        		gjbzhyList.add(gjbzhyObject);
//        		System.out.println("gjbzhydm: "+gjbzhyObject.getGjbzhydm()+" mc:"+gjbzhyObject.getGjbzhymc());
        	}
        	
        }
        request.getSession().setAttribute("gjbzhy", gjbzhy);
        gdzcjszjyjqkjbForm.setGjbzhy(gjbzhyList);

        String jumpFlag=(String) request.getAttribute("jumpFlag");
        if(jumpFlag!=null && !jumpFlag.equals("") && gdzcjszjyjqkjbForm.getJumpFlag().equals("")){
        	gdzcjszjyjqkjbForm.setJumpFlag(jumpFlag);
        }
    	
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ���ñ���������-����
        gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        // ����˰����������
        // czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
        // ����¼��������
        gdzcjszjyjqkjbForm.setLrr(userData.getYhid());

        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ���ú�̨����Actionֵ---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // �����������Data����,������ZfjgqysdsjbForm
        vo.setData(gdzcjszjyjqkjbForm);
        // ����ProxyҪ���õ�processor����---ZfjgqysdsjbProcessor
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR);
        // ����ϵͳ�û���Ϣ
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
        	gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) SbzlProxy.getInstance().process(vo);
            // ��czqysdsjbForm����request,��Ϊ����������
            request.setAttribute("gdzcjszjyjqkjb2014Form", gdzcjszjyjqkjbForm);
            // �����ɹ���ת
            return mapping.findForward("Show");
           
        } catch (Exception ex) {

        	gdzcjszjyjqkjbForm.reset(mapping,request);
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {

        // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;
        UserData userData = this.getUserData(request);
        List arrayList=new ArrayList();
        arrayList=SfRequestUtil.getValuesToList(request,gdzcjszjyjqkjbForm.getSb_columns());
        gdzcjszjyjqkjbForm.setGdzcjszjyjqkjbList(arrayList); //���ù̶�������
//        this.getBaseForm(request, gdzcjszjyjqkjbForm); //������˰�˻�������
        request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION); //���ò�������
        vo.setData(gdzcjszjyjqkjbForm); //���ò�������
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR); //PROCESSOR
        vo.setUserData(userData);
        try {
            //����processor
            gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);
        } catch (Exception ex) {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���"); //���ر�����
        
        return this.doQuery(mapping, form, request, response);
    }
    /**
     *��ת����ҵ����˰������˰��֧���������ҳ��
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;
    	String jumpFlag=gdzcjszjyjqkjbForm.getJumpFlag();
    	System.out.println("...............return : "+jumpFlag+request.getAttribute("jumpFlag"));
    	if(jumpFlag!=null && jumpFlag.equals("czzssdsjb2014")){
    		return mapping.findForward("JumpCzzs");
    	}else{
    		return mapping.findForward("JumpZfjg");
    	}
    	
    }
    
    /**
     * ɾ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            CzqysdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {

        // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) form;
        UserData userData = this.getUserData(request);
        List arrayList=new ArrayList();
        arrayList=SfRequestUtil.getValuesToList(request,gdzcjszjyjqkjbForm.getSb_columns());
        gdzcjszjyjqkjbForm.setGdzcjszjyjqkjbList(arrayList); //���ù̶�������
//        this.getBaseForm(request, gdzcjszjyjqkjbForm); //������˰�˻�������
        request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION); //���ò�������
        vo.setData(gdzcjszjyjqkjbForm); //���ò�������
        vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_GDZCJSZJYJQK_PROCESSOR); //PROCESSOR
        vo.setUserData(userData);
        try {
            //����processor
            gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), gdzcjszjyjqkjbForm);
        } catch (Exception ex) {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���"); //����ɾ�����
        
        return this.doQuery(mapping, form, request, response);
    }
}
