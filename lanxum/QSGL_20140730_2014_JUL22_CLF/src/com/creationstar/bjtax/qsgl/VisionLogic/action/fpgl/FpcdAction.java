package com.creationstar.bjtax.qsgl.VisionLogic.action.fpgl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpcdForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpcdBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ�ش���Action��:FpcdAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpcdAction extends AddBaseAction {

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
		Debug.out("into FpcdAction's doShow Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpcdForm fpcdForm = (FpcdForm)form;
		session.setAttribute(mapping.getName(), fpcdForm);
		try
		{
			//���ҳ��
			fpcdForm.resetPage();
			
			fpcdForm.setYhid(userData.yhid);
			fpcdForm.setYhmc(userData.yhmc);
			fpcdForm.setZhdm(userData.xtsbm1);
			fpcdForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpcdForm.getYhid();
			String zhdm =fpcdForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpcdForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpcdForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpcdForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//��÷�Ʊ�����б�fpzlList
			fpcdForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//Swjjzzlgmc
			fpcdForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpcdForm.getSwjgzzjgdm()));
	           
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�ش�ҳ����ʾ�ɹ���");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�ش�ҳ����ʾʧ�ܣ�");
		}
		// ����Token;
		saveToken(request);
		return mapping.findForward("show");
	}
	
	/**
	 * doShow
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doQuery(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpcdAction's doQuery Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpcdForm fpcdForm = (FpcdForm)form;
		session.setAttribute(mapping.getName(), fpcdForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//���ҳ��
			fpcdForm.clear();
			
			String swjgzzjgdm = fpcdForm.getSwjgzzjgdm(); //˰�������֯��������
			String fpkfdm = fpcdForm.getFpkfdm();  //��Ʊ�ⷿ����
			String htbh = fpcdForm.getHtbh();  //��ͬ���
			String fpzldm = fpcdForm.getFpzldm(); //��Ʊ�������
			String qshm = fpcdForm.getQshm();  //��ʼ����
			
			FpcdBO bo = new FpcdBO();
			
			//�жϺ�ͬ����Ƿ����
			if(htbh != null && !htbh.equals(""))
			{
				fpcdForm.setFpbcbc(ActionUtil.queryHtbh2(htbh));
				if(fpcdForm.getFpbcbc() != null && !fpcdForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "�ú�ͬ���δ���������Ʊҵ�񣬻���ط�Ʊ�Ѱ�����Ʊҵ��"+ fpcdForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ�������޶�Ӧ��Ʊ��Ϣ!");
					return mapping.findForward("show");
				}
			}
			
			//�жϷ�Ʊ�����Ƿ��Ѱ�����Ʊ
			if(qshm != null && !qshm.equals(""))
			{
				fpcdForm.setFpbcbc(ActionUtil.queryFphm2(fpzldm,qshm));
				if(fpcdForm.getFpbcbc() != null && !fpcdForm.getFpbcbc().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
					return mapping.findForward("show");
				}
			}
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			bo.setSwjgzzjgdm(swjgzzjgdm);
			
			Debug.out("FpcdAction doQuery form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpcdBO)QsglProxy.getInstance().process(vo);
			
			ArrayList itemList = new ArrayList();
			
			//���ز�ѯ��Ϣֵ
			fpcdForm.setFpbcbc(bo.getMessagefwx());
			if(fpcdForm.getFpbcbc() != null && !fpcdForm.getFpbcbc().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fpcdForm.getFpbcbc());
				return mapping.findForward("show");
			}
			
			ArrayList cxList = bo.getCxList();
			if(cxList != null && cxList.size() >0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
				for(int i =0 ;i<cxList.size(); i++)
				{
					FpcdForm fpcdItemForm = new FpcdForm();
					FpcdBO cxItem = (FpcdBO)cxList.get(i);
					
					fpcdItemForm.setFpzldm(cxItem.getFpczz().getFpzldm());
					fpcdItemForm.setFphm(cxItem.getFpczz().getFphm());
					fpcdItemForm.setHtbh(cxItem.getHtypzdzgxb().getHtbh());
					fpcdItemForm.setNsrmc_buyer(cxItem.getFpczz().getFkdw());
					fpcdItemForm.setNsrmc_seller(cxItem.getFpczz().getSkdw());
					fpcdItemForm.setFwcqzh(cxItem.getFpczz().getFwcqzh());
					fpcdItemForm.setFwzldz(cxItem.getFpczz().getFwzldz());
					fpcdItemForm.setJe(String.valueOf(deFormat.format(cxItem.getFpczmx().getJe())));
					
					itemList.add(fpcdItemForm);
				}
				
				// �Żص�form
				fpcdForm.setCxList(itemList);
				fpcdForm.setFpbcbc("");
			}
			else
			{
				request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ����������ѯ�����Ƿ���Ч��");
				fpcdForm.setFpbcbc("test");
				return mapping.findForward("show");
			}
			
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�ش�ҳ���ѯʧ�ܣ�");
		}
		// ����Token;
		saveToken(request);
		return mapping.findForward("query");
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
		Debug.out("into FpcdAction's doSave Method....");
        // ��ֹҳ��ˢ��
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpcdForm fpcdForm = (FpcdForm)form;
		session.setAttribute(mapping.getName(), fpcdForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			FpcdBO bo = new FpcdBO();
			
			//ȡ��Ʊ����������롢��Ʊ����ֵ
			//System.out.println(" >>>fpcdForm.getFphms():"+fpcdForm.getFphms());
			String[] fphms = fpcdForm.getFphms().split(",");
			for(int i =0; i<fphms.length;i++)
			{
				String innerFphms = fphms[i];
				String[] fphmArr = innerFphms.split(":");
				String cxfpzldm = fphmArr[0];
				String cxfphm = fphmArr[1];
				
				//System.out.println(" >>>cxfpzldm:"+cxfpzldm+" >>>cxfphm:"+cxfphm);
				bo.setCxfpzldm(cxfpzldm);  //��ѡֵ��Ʊ��������
				bo.setCxqshm(cxfphm);  //��ѡֵ��Ʊ����
			}
			
			//fpdkForm �е� ��Ʊ�����Ϣ
			bo.setYhid(fpcdForm.getYhid());  //�û�ID
			bo.setYhmc(fpcdForm.getYhmc());  //�û�����
			bo.setFpkfdm(fpcdForm.getFpkfdm());  //��Ʊ�ⷿ����
			bo.setSwjgzzjgdm(fpcdForm.getSwjgzzjgdm());  //˰�������֯�ṹ����
			bo.setCxList(ActionUtil.queryFpcd(bo.getFpkfdm(), bo.getCxfpzldm(), bo.getCxqshm()));
			
			//�ж��ύ��Ʊ���Ƿ��н��
			if(bo.getCxList() == null || bo.getCxList().size() == 0)
			{
				request.setAttribute(mapping.getAttribute(), fpcdForm);
				request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�ش�ʧ�ܣ�������������Ʊ��������:"+ ActionUtil.getFpzlmc(fpcdForm.getFpzldm())+"��ѡ���Ƿ���ȷ��");
				return mapping.findForward("show");
			}
			
			//����ѯ���д��VO��
			for(int i =0; i<bo.getCxList().size(); i++)
			{
				FpcdBO cxItem = (FpcdBO)bo.getCxList().get(i);
				bo.setFpczz(cxItem.getFpczz());
				bo.setFpczmx(cxItem.getFpczmx());
				bo.setHtypzdzgxb(cxItem.getHtypzdzgxb());
			}
			
			Debug.out("FpcdAction doSave form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpcdBO)QsglProxy.getInstance().process(vo);
			
			ArrayList fpkpList =bo.getFpkpList();
			
			if(fpkpList !=null && fpkpList.size() > 0)
			{
				fpcdForm.setBccgbz("1"); //���ñ���ɹ�
				
				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
				for(int i =0 ;i < fpkpList.size(); i++)
				{
					
					FpcdBO fpkcNewItem = (FpcdBO) fpkpList.get(i);

					fpcdForm.setSwjgzzjgmc(fpkcNewItem.getFpczz().getDkdwmc());
					fpcdForm.setFpkfdm(fpkcNewItem.getFpczz().getFpkfdm());
					fpcdForm.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					fpcdForm.setFphm(fpkcNewItem.getFpczz().getFphm());
					fpcdForm.setKprq(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(fpkcNewItem.getFpczz().getKprq())));
					fpcdForm.setHyfl(fpkcNewItem.getFpczz().getHyfl());
					fpcdForm.setNsrmc_buyer(fpkcNewItem.getFpczz().getFkdw());
					fpcdForm.setNsrmc_seller(fpkcNewItem.getFpczz().getSkdw());
					fpcdForm.setFwcqzh(fpkcNewItem.getFpczz().getFwcqzh());
					fpcdForm.setFwzldz(fpkcNewItem.getFpczz().getFwzldz());
					fpcdForm.setPm(fpkcNewItem.getFpczmx().getPm());
					fpcdForm.setDj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getDj())));
					fpcdForm.setSl(String.valueOf(fpkcNewItem.getFpczmx().getSl()));
					fpcdForm.setJe(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fpcdForm.setBz(fpkcNewItem.getFpczz().getBz());
					fpcdForm.setXxhj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fpcdForm.setDxhj(Currency.convert((fpkcNewItem.getFpczmx().getJe().doubleValue())).substring(1));
					fpcdForm.setWszh(fpkcNewItem.getFpczz().getSphm());
					fpcdForm.setJdhm(fpkcNewItem.getFpczz().getFpzldm()+fpkcNewItem.getFpczz().getFphm());
					fpcdForm.setKpr(fpkcNewItem.getFpczz().getKpr());
				}
			}
			
			//request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�ش���Ϣ����ɹ���");
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ�ش���Ϣ����ʧ�ܣ�");
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
    	Debug.out("into FpcdAction's doBack Method....");
    	
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
    	Debug.out("into FpcdAction's doClear Method....");
    	
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
    	Debug.out("into FpcdAction's doFpdk Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("fpdk");
    }
}
