package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��-��ҵ����˰��֧�����걨2014��-������˰��֧���������</p>
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsCzzsNb2014Util;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;

public class ZfjgfzjgNbAction extends SmsbAction
{
    public ZfjgfzjgNbAction()
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
     *            ZfjgfzjgNbForm
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
    	
        // ��ȡZfjgfzjgNbForm����
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
       
        
        CzzsfzjgNbForm czzsfzjgNbForm=(CzzsfzjgNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSFZJGNBFORM");
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ���ñ���������-�걨
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // ����˰����������
        // ZfjgfzjgNbForm.setSwjsjdm(userData.getXtsbm1());
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());
        
        Map pDataMap = new HashMap();
        pDataMap.put("CzzsfzjgNbForm", czzsfzjgNbForm);
		pDataMap.put("ZfjgfzjgNbForm", zfjgForm);
		
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ���ú�̨����Actionֵ---QUERYACTION
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        // �����������Data����
        vo.setData(pDataMap);
        // ����ProxyҪ���õ�processor����---ZfjgqysdsNbProcessor
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);
        // ����ϵͳ�û���Ϣ
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
            // ��ZfjgfzjgNbForm����request,��Ϊ����������
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
     *            ZfjgfzjgNbForm
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
    	
        // ��ȡZfjgfzjgNbForm����
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
        CzzsfzjgNbForm czzsfzjgNbForm=(CzzsfzjgNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSFZJGNBFORM");        
       
        
        System.out.println("jsjdm"+zfjgForm.getJsjdm());
        // ��ȡ���ɷ�ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ���ñ���������-�걨
        zfjgForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        // ����˰����������
        // ZfjgfzjgNbForm.setSwjsjdm(userData.getXtsbm1());
        // ����¼��������
        zfjgForm.setLrr(userData.getYhid());

        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ���ú�̨����Actionֵ---QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        // �����������Data����,������ZfjgfzjgNbForm
        Map pDataMap = new HashMap();
        pDataMap.put("CzzsfzjgNbForm", czzsfzjgNbForm);
		pDataMap.put("ZfjgfzjgNbForm", zfjgForm);
        vo.setData(pDataMap);
        // ����ProxyҪ���õ�processor����---ZfjgqysdsNbProcessor
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);
        // ����ϵͳ�û���Ϣ
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
            // ��ZfjgfzjgNbForm����request,��Ϊ����������
            
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
     *            ZfjgfzjgNbForm
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
        // ��ȡZfjgfzjgNbForm����
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
        
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

        // ��ZfjgfzjgNbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
        request.setAttribute(mapping.getAttribute(), zfjgForm);
        // ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        // ������������
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(zfjgForm);
        vo.setUserData(userData);
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);

        try {
            // ����Proxy��ִ��processor,��ȡZfjgfzjgNbForm����ֵ
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
            // ��ZfjgfzjgNbForm����request,��Ϊ����������
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
     *            ZfjgfzjgNbForm
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

            // ��ȡZfjgqysdsjbForm����
            ZfjgfzjgNbForm zfjgform = (ZfjgfzjgNbForm) form;
            String jsjdm = zfjgform.getJsjdm();
            String jumpStr = null;
            // ��CzzssdsjbForm ����request��
            CzzsfzjgNbForm czzsForm=new CzzsfzjgNbForm();
            if(jsjdm != null && !jsjdm.equals(""))
            {
                czzsForm.setJsjdm(jsjdm);
                jumpStr = "Query";
            }
            else
            {
                jumpStr = "Jump";
            }
            request.setAttribute("czzsfzjgNbForm2014", czzsForm);
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
     *            ZfjgfzjgNbForm
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
        // ��ȡZfjgfzjgNbForm����
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) form;
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
        vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_ZFJG_PROCESSOR);
        vo.setUserData(userData);
        try {
            // ����Proxy��ִ��processor,��ȡzfjgForm����ֵ
            zfjgForm = (ZfjgfzjgNbForm) SbzlProxy.getInstance().process(vo);
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
	 *            czzsfzjgNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 * @throws ApplicationException 
	 */
	public void doSaveCzzsZb(HttpServletRequest request, ZfjgfzjgNbForm zfjgForm)throws BaseException, ApplicationException {
		
		// ��ǰ��ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm)request.getSession(false).getAttribute("REQ_KEY_CZZSFZJGNBFORM");
		QysdsCzzsNb2014Util qysdsUtil = new QysdsCzzsNb2014Util();
		// ��ȡ��ϵͳ�û���Ϣ
		UserData userData = this.getUserData(request);
		

		Map pData = new HashMap();
		
		String nsfsNow = czzsfzjgNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzsfzjgNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzsfzjgNbForm", czzsfzjgNbForm);
		pData.put("fpb_fzjgftse", zfjgForm.getFzjgftse());
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//��ѯA�������˰�������ֻܷ���
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzsfzjgNbForm.getNsfs_fsjg().get("nsfs"))) || czzsfzjgNbForm.getNsfs_fsjg().get("nsfs")==null){
			czzsfzjgNbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzsfzjgNbForm.getNsfs_fsjg().get("zfjg"))) || czzsfzjgNbForm.getNsfs_fsjg().get("zfjg")==null){
			czzsfzjgNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzsfzjgNbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzsfzjgNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzsfzjgNbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzsfzjgNbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzsfzjgNbForm);
		vo.setUserData(userData);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡczzsfzjgNbForm����ֵ
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			if(!("1".equals(czzsfzjgNbForm.getNsfs()))){
				czzsfzjgNbForm.setQysdsnbList(new ArrayList());
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
			request.getSession(false).setAttribute("REQ_KEY_CZZSFZJGNBFORM",czzsfzjgNbForm);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex,"��ҵ����˰��֧���������˰�걨����ʧ�ܣ�");
		}
	}
}
