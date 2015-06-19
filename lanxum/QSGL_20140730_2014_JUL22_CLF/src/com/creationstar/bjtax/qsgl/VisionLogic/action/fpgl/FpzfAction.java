package com.creationstar.bjtax.qsgl.VisionLogic.action.fpgl;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpzfForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpzfBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ���Ϲ���Action��:FpzfAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpzfAction extends AddBaseAction {
	/**
	 * doShow
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doShow(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpzfAction's doShow Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpzfForm fpzfForm = (FpzfForm)form;
		session.setAttribute(mapping.getName(), fpzfForm);
		try
		{
			//���ҳ��
			fpzfForm.resetPage();
			
			fpzfForm.setYhid(userData.yhid);
			fpzfForm.setYhmc(userData.yhmc);
			fpzfForm.setZhdm(userData.xtsbm1);
			fpzfForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpzfForm.getYhid();
			String zhdm =fpzfForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpzfForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpzfForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpzfForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//��÷�Ʊ�����б�fpzlList
			fpzfForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//��÷�Ʊ�����б�cxfpzlList
			ArrayList tempList = new ArrayList();
			
			if (fpzfForm.getFpzlList() != null && fpzfForm.getFpzlList().size() > 0)
			{
				for(int i =0 ;i<fpzfForm.getFpzlList().size(); i++)
				{
					FpzfForm tempForm = new FpzfForm();
					Fpzl tempfpzl = (Fpzl)fpzfForm.getFpzlList().get(i);
					tempForm.setCxfpzldm(tempfpzl.getFpzldm());
					tempForm.setCxfpzlmc(tempfpzl.getFpzlmc());
					
					tempList.add(tempForm);
					
				}
				fpzfForm.setCxfpzlList(tempList);
			}
			
			//���¼���˺�¼��ʱ��
			fpzfForm.setLrr(userData.getYhmc());
			fpzfForm.setLrrq(DateTimeUtil.getCurrentDate());
			//Swjjzzlgmc
			fpzfForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpzfForm.getSwjgzzjgdm()));
	           
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ʊҳ����ʾ�ɹ���");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����ҳ����ʾʧ�ܣ�");
		}
		// ����Token;
		saveToken(request);
		return mapping.findForward("show");
	}
	
	/**
	 * doSave
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doSave(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpzfAction's doSave Method....");
        // ��ֹҳ��ˢ��
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpzfForm fpzfForm = (FpzfForm)form;
		session.setAttribute(mapping.getName(), fpzfForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			
			String swjgzzjgdm = fpzfForm.getSwjgzzjgdm(); //˰�������֯��������
			String fpkfdm = fpzfForm.getFpkfdm();  //��Ʊ�ⷿ����
			String fpzldm = fpzfForm.getFpzldm(); //��Ʊ�������
			String qshm = fpzfForm.getQshm();  //��ʼ����
			
			FpzfBO bo = new FpzfBO();
			
			//�жϷ�Ʊ�����Ƿ��п��
			if(qshm != null && !qshm.equals(""))
			{
				//�ж��Ƿ��п��
				fpzfForm.setFpbcxx(ActionUtil.queryMaxFpkcxx(fpzfForm.getFpkfdm(), fpzfForm.getFpzldm(), fpzfForm.getCxqshm()));
				if(fpzfForm.getFpbcxx() != null && !fpzfForm.getFpbcxx().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpzfForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ���벻���ڣ�"+ fpzfForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}

			bo.setFpkfdm(fpkfdm);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			bo.setYhid(fpzfForm.getYhid());  //�û�ID
			bo.setYhmc(fpzfForm.getYhmc());  //�û�����
			bo.setSwjgzzjgdm(fpzfForm.getSwjgzzjgdm()); //˰�������֯��������
			bo.setSwjgzzjgmc((ActionUtil.getSwjjzzlgmc(bo.getSwjgzzjgdm()))); //˰�������֯��������
			
			Debug.out("FpzfAction doSave form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpzfBO)QsglProxy.getInstance().process(vo);
			fpzfForm.resetPage();
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ������Ϣ����ɹ���");
			
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ������Ϣ����ʧ�ܣ�");
		}
		// ����Token;
		saveToken(request);
		return mapping.findForward("save");
	}
	
	/**
	 * doBack
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doBack(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("back");
    }
    
    /**
	 * doClear
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doClear(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("clear");
    }
    
    /**
	 * doFpdk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doFpdk(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("fpdk");
    }
}
