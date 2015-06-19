package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.web;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��-��ҵ����˰��֧�����걨2012��-������˰��֧���������</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 *
 * @author wangcy
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.QysdsNb2012Util;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class ZfjgqysdsNbAction extends SmsbAction
{
    public ZfjgqysdsNbAction()
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
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;<font color=\"#7C9AAB\">��ҵ����˰������˰��֧���������</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                "��ҵ����˰������˰��֧���������");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }

    /**
     * ��һ��¼���session��ȥ����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            ZfjgqysdsNbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
    	
        // ��ȡZfjgqysdsNbForm����
        ZfjgqysdsNbForm zfjgForm = (ZfjgqysdsNbForm) form;
        System.out.println("jsjdm"+zfjgForm.getJsjdm());
        
        CzzssdsNbForm czzssdsNbForm=(CzzssdsNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSSDSNBFORM");
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ���ñ���������-�걨
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // ����˰����������
        // ZfjgqysdsNbForm.setSwjsjdm(userData.getXtsbm1());
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());

        Map pDataMap = new HashMap();
        pDataMap.put("CzzssdsNbForm", czzssdsNbForm);
		pDataMap.put("ZfjgqysdsNbForm", zfjgForm);
		
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ���ú�̨����Actionֵ---QUERYACTION
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        // �����������Data����
        vo.setData(pDataMap);
        // ����ProxyҪ���õ�processor����---ZfjgqysdsNbProcessor
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_ZFJG_PROCESSOR);
        // ����ϵͳ�û���Ϣ
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgqysdsNbForm) SbzlProxy.getInstance().process(vo);
            // ��ZfjgqysdsNbForm����request,��Ϊ����������
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            
            // �����ɹ���ת
            return mapping.findForward("Show");

        } catch (Exception ex) {

            zfjgForm.reset(mapping,request);
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * ��ѯ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            ZfjgqysdsNbForm
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
    	
        // ��ȡZfjgqysdsNbForm����
        ZfjgqysdsNbForm zfjgForm = (ZfjgqysdsNbForm) form;
        CzzssdsNbForm czzssdsNbForm=(CzzssdsNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSSDSNBFORM");        
       
        
        System.out.println("jsjdm"+zfjgForm.getJsjdm());
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ���ñ���������-�걨
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // ����˰����������
        // ZfjgqysdsNbForm.setSwjsjdm(userData.getXtsbm1());
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());

        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ���ú�̨����Actionֵ---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // �����������Data����,������ZfjgqysdsNbForm
        Map pDataMap = new HashMap();
        pDataMap.put("CzzssdsNbForm", czzssdsNbForm);
		pDataMap.put("ZfjgqysdsNbForm", zfjgForm);
        vo.setData(pDataMap);
        // ����ProxyҪ���õ�processor����---ZfjgqysdsNbProcessor
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_ZFJG_PROCESSOR);
        // ����ϵͳ�û���Ϣ
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgqysdsNbForm) SbzlProxy.getInstance().process(vo);
            // ��ZfjgqysdsNbForm����request,��Ϊ����������
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            
            // �����ɹ���ת
            return mapping.findForward("Show");

        } catch (Exception ex) {

            zfjgForm.reset(mapping,request);
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
     *            ZfjgqysdsNbForm
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
        // ��ȡZfjgqysdsNbForm����
        ZfjgqysdsNbForm zfjgForm = (ZfjgqysdsNbForm) form;
        
        //������������
        this.doSaveCzzsZb(request, zfjgForm);

        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());
        // ���ñ���������
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
        zfjgForm.setQysdsNbList(SfRequestUtil.getValuesToList(request,zfjgForm.getFzjgColumns()));
      
        System.out.println("nsrmc = " + zfjgForm.getNsrmc());

        // ��ZfjgqysdsNbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ������������
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(zfjgForm);
        vo.setUserData(userData);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_ZFJG_PROCESSOR);

        try {
            // ����Proxy��ִ��processor,��ȡZfjgqysdsNbForm����ֵ
            zfjgForm = (ZfjgqysdsNbForm) SbzlProxy.getInstance().process(vo);
            // ��ZfjgqysdsNbForm����request,��Ϊ����������
            request.setAttribute(mapping.getAttribute(), zfjgForm);
            // �����ɹ���ת
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            return mapping.findForward("Show");
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    /**
     * ��תҳ������
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            ZfjgqysdsNbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doJump(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws BaseException
    {
        try {
            System.out.println("I am jumping....");
            // ��ȡZfjgqysdsjbForm����
            ZfjgqysdsNbForm zfjgform = (ZfjgqysdsNbForm) form;
            String jsjdm = zfjgform.getJsjdm();
            String jumpStr = null;
            // ��CzzssdsjbForm ����request��
            CzzssdsNbForm czzsForm=new CzzssdsNbForm();
            if(jsjdm != null && !jsjdm.equals(""))
            {
                czzsForm.setJsjdm(jsjdm);
                jumpStr = "Query";
            }
            else
            {
                jumpStr = "Jump";
            }
            request.setAttribute("czzssdsnb2012Form", czzsForm);
            System.out.println("jumpStr = " + jumpStr);
            return mapping.findForward(jumpStr);
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * ɾ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            ZfjgqysdsNbForm
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
    	 System.out.println("doDelete ============Action ");
    	
        // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡZfjgqysdsNbForm����
        ZfjgqysdsNbForm zfjgForm = (ZfjgqysdsNbForm) form;
        // ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����zfjgForm��DataList�У�
        zfjgForm.setQysdsNbList(SfRequestUtil.getValuesToList(request,zfjgForm.getFzjgColumns()));
        // ��zfjgForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());
        // ���ñ���������
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ������������
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(zfjgForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_ZFJG_PROCESSOR);
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgqysdsNbForm) SbzlProxy.getInstance().process(vo);
			System.out.println("I am jumping to Czzssdsjb....");
			// ��ȡCzzssdsjbForm����
	
			return mapping.findForward("Jump");
            
        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
	 * �����걨��������
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            czzssdsNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 * @throws ApplicationException 
	 */
	public void doSaveCzzsZb(HttpServletRequest request, ZfjgqysdsNbForm zfjgForm)throws BaseException, ApplicationException {
		
		// ��ǰ��ActionForm---CzzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSSDSNBFORM");
		QysdsNb2012Util qysdsUtil = new QysdsNb2012Util();
		// ��ȡ��ϵͳ�û���Ϣ
		UserData userData = this.getUserData(request);
		

		Map pData = new HashMap();
		
		String nsfsNow = czzssdsNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzssdsNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzssdsNbForm", czzssdsNbForm);
		pData.put("fpb_fzjgftse", zfjgForm.getFzjgftse());
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//��ѯA�������˰�������ֻܷ���
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzssdsNbForm.getNsfs_fsjg().get("nsfs"))) || czzssdsNbForm.getNsfs_fsjg().get("nsfs")==null){
			czzssdsNbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzssdsNbForm.getNsfs_fsjg().get("zfjg"))) || czzssdsNbForm.getNsfs_fsjg().get("zfjg")==null){
			czzssdsNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzssdsNbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzssdsNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzssdsNbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzssdsNbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzssdsNbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡCzzssdsNbForm����ֵ
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			if(!("1".equals(czzssdsNbForm.getNsfs()))){
				czzssdsNbForm.setQysdsnbList(new ArrayList());
			}
			String nsfsOld = (String)nsfs_zfjg.get("nsfs_old");
			String zfjgOld = (String)nsfs_zfjg.get("zfjg_old");
			
			if(nsfsNow.equals("1")){
				if(!zfjgOld.equals(zfjgNow)){
					qysdsUtil.deleteCascadeZfjgData(pData);
				}
				qysdsUtil.saveCascadeZfjgData(pData);
			}else{
				qysdsUtil.deleteCascadeZfjgData(pData);
			}
			request.getSession(false).setAttribute("REQ_KEY_CZZSSDSNBFORM",czzssdsNbForm);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex,"��ҵ����˰��֧���������˰�걨����ʧ�ܣ�");
		}
	}
}
