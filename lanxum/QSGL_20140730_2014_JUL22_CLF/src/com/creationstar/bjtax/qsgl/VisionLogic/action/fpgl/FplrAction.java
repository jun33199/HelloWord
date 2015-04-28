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

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FplrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FplrBO;
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
 * <p>Description: ��Ʊģ��ķ�Ʊ�Ŷ�¼��Action��:FplrAction </p>
 * @author tutu
 * @version 1.1
 */
public class FplrAction extends AddBaseAction {
	
	
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
	   Debug.out("into FplrAction's doShow Method....");
	   // ��ȡ�û���¼���ݡ�
       HttpSession session = request.getSession(false);
       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
       FplrForm fplrForm = (FplrForm)form;
       session.setAttribute(mapping.getName(), fplrForm);
       try
       {
    	   fplrForm.setYhid(userData.yhid);
    	   fplrForm.setZhdm(userData.xtsbm1);
    	   fplrForm.setSwjgzzjgdm(userData.ssdwdm);
    	   
    	   String yhid =fplrForm.getYhid();
    	   String zhdm =fplrForm.getZhdm();
    	   
    	   if(yhid != null && !yhid.equals(""))
    	   {
    		   if(yhid == zhdm)
    		   {
    			   fplrForm.setFpkfdm(Constants.FP_WWYH+yhid);
    		   }
    		   else
    		   {
    			   fplrForm.setFpkfdm(Constants.FP_NWYH+yhid);
    		   }
    	   }
    	   
    	   //��÷�Ʊ�����б�fpzlList
           fplrForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
           
           //���¼���˺�¼��ʱ��
           fplrForm.setLrr(userData.getYhmc());
           fplrForm.setLrrq(DateTimeUtil.getCurrentDate());
           
			//���淵��ֵ
           request.setAttribute(mapping.getAttribute(), fplrForm);
			
           request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�Ŷ�¼��ҳ����ʾ�ɹ���");
       }
       catch (Exception ex)
       {
           ActionErrors errors = new ActionErrors();
           errors.add(ActionErrors.GLOBAL_ERROR,
                      new ActionError("err.system"));
           saveErrors(request, errors);
           request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�Ŷ�¼��ҳ����ʾʧ�ܣ�");
       }
       // ����Token;
       saveToken(request);
       return mapping.findForward("show");
   }
   
	/**
	 * ����
	 * @param mapping
	 * @param form    FplrForm
	 * @param request
	 * @param request
	 * @author tutu
	 * @Time 2013-05-03
	 */
	public ActionForward doSave(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FplrAction's doSave Method....");
        // ��ֹҳ��ˢ��
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FplrForm fplrForm =(FplrForm) form;
		session.setAttribute(mapping.getName(), fplrForm);
		ActionErrors errors = new ActionErrors();
		try {
			FplrBO bo = new FplrBO();
			
			String fpkfdm = fplrForm.getFpkfdm();
			String swjgzzjgdm = fplrForm.getSwjgzzjgdm(); //˰�������֯��������
			ArrayList fplrList = fplrForm.getFplrList();
			if(fplrList != null && fplrList.size()>0)
			{
				for(int i=0; i<fplrList.size(); i++)
				{
					Fpkc fpkcNewItem = (Fpkc)fplrList.get(i);
					String fpzldm = fpkcNewItem.getFpzldm();
					String qshm = fpkcNewItem.getQshm();
					String jzhm = fpkcNewItem.getJzhm();
					
					String chekcFphm = ActionUtil.queryFpkclr(fpzldm, qshm, jzhm);
					
					if(chekcFphm != null && !chekcFphm.equals("0"))
					{
						request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�����Ѵ���!");
						return mapping.findForward("show");
					}
				}
			}
			bo.setFpkfdm(fpkfdm);  //��Ʊ�ⷿ����
			bo.setSwjgzzjgdm(fplrForm.getSwjgzzjgdm());  //˰�������֯��������
			bo.setFplrList(fplrList);  //��Ʊ�Ŷ�¼��List��fpzldm��qshm��jzhm��sl��
			bo.setYhmc(userData.yhmc);  //�û�����
			bo.setYhid(userData.yhid);  //�û�ID
			
			Debug.out("FplrAction doSave form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FplrBO)QsglProxy.getInstance().process(vo);
			
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�Ŷ�¼��ɹ���");
        } 
		catch (Exception ex) 
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�Ŷ�¼��ʧ�ܣ�");
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
            HttpServletResponse response) 
    {
    	Debug.out("into FplrAction's doBack Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("back");
    }
}
