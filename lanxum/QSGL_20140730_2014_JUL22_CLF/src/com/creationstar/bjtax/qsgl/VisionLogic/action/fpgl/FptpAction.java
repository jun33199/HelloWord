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

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FptpForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FptpBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ��Ʊ����Action��:FptpAction </p>
 * @author tutu
 * @version 1.1
 */
public class FptpAction extends AddBaseAction {

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
		Debug.out("into FptpAction's doShow Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FptpForm fptpForm = (FptpForm)form;
		session.setAttribute(mapping.getName(), fptpForm);
		try
		{
			//���ҳ��
			fptpForm.resetPage();
			
			fptpForm.setYhid(userData.yhid);
			fptpForm.setYhmc(userData.yhmc);
			fptpForm.setZhdm(userData.xtsbm1);
			fptpForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fptpForm.getYhid();
			String zhdm =fptpForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fptpForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fptpForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fptpForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//��÷�Ʊ�����б�fpzlList
			fptpForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//�����Ʊ��Ʊ�����б�cxfpzlList
			ArrayList tempList = new ArrayList();
			for(int i =0 ;i<fptpForm.getFpzlList().size(); i++)
			{
				FptpForm tempForm = new FptpForm();
				Fpzl tempfpzl = (Fpzl)fptpForm.getFpzlList().get(i);
				tempForm.setTpfpzldm(tempfpzl.getFpzldm());
				tempForm.setTpfpzlmc(tempfpzl.getFpzlmc());
				
				tempList.add(tempForm);
				
			}
			fptpForm.setTpfpzlList(tempList);
			
			//Swjjzzlgmc
			fptpForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fptpForm.getSwjgzzjgdm()));
	           
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ʊҳ����ʾ�ɹ���");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ʊҳ����ʾʧ�ܣ�");
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
		Debug.out("into FptpAction's doQuery Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FptpForm fptpForm = (FptpForm)form;
		session.setAttribute(mapping.getName(), fptpForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//���ҳ��
			fptpForm.clear();
			
			String swjgzzjgdm = fptpForm.getSwjgzzjgdm(); //˰�������֯��������
			String fpkfdm = fptpForm.getFpkfdm();  //��Ʊ�ⷿ����
			String htbh = fptpForm.getHtbh();  //��ͬ���
			String fpzldm = fptpForm.getFpzldm(); //��Ʊ�������
			String qshm = fptpForm.getQshm();  //��ʼ����
			String tpfpzldm = fptpForm.getTpfpzldm();  //��Ʊ��Ʊ�������
			String tpqshm = fptpForm.getTpqshm();  //��Ʊ��Ʊ���루��ʼ���룩
			
			Debug.out("doQuery >>"+fptpForm.getFpkfdm()+":"+fptpForm.getTpfpzldm()+":"+fptpForm.getTpqshm());
			
			FptpBO bo = new FptpBO();
			
			//�жϷ�Ʊ�����Ƿ��Ѱ�����Ʊ
			if(qshm != null && !qshm.equals(""))
			{
				fptpForm.setFpbcbc(ActionUtil.queryFphm(fpzldm,qshm));
				if(fptpForm.getFpbcbc() != null && !fptpForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "�÷�Ʊ���벻���ڻ��Ѱ�����Ʊ��"+ fptpForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
					return mapping.findForward("show");
				}
			}
			
			//�ж���Ʊ��Ʊ�����Ƿ����
			if(tpqshm != null && !tpqshm.equals(""))
			{
				fptpForm.setFpbcbc(ActionUtil.queryMaxFpkcxx(fpkfdm, tpfpzldm, tpqshm));
				if(fptpForm.getFpbcbc() != null && !fptpForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ���벻���ڣ�"+ fptpForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
					return mapping.findForward("show");
				}
			}
			else
			{
				//����ҳ��
				fptpForm.reset();
				
				//��ȡ��ѯ����ķ�Ʊ�������
				ArrayList tempList = new ArrayList();
				for(int i =0 ;i<ActionUtil.queryMaxFpzl(fpkfdm).size(); i++)
				{
					FptpForm tempForm = new FptpForm();
					Fpzl tempfpzl = (Fpzl)ActionUtil.queryMaxFpzl(fpkfdm).get(i);
					tempForm.setTpfpzldm(tempfpzl.getFpzldm());
					tempForm.setTpfpzlmc(tempfpzl.getFpzlmc());
					tempList.add(tempForm);
				}
				fptpForm.setTpfpzlList(tempList);
				
				if ((fptpForm.getTpfpzlList() == null) || (fptpForm.getTpfpzlList().size() == 0))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����δ���û�÷�Ʊ�����޿��������");
					return mapping.findForward("show");
				}
				else
				{
					//���÷�Ʊ����
					FptpForm tempTpForm = (FptpForm)fptpForm.getTpfpzlList().get(0);
					fptpForm.setTpfpzldm(tempTpForm.getTpfpzldm());
					
					//�ж��Ƿ��п�棬ȡ��С��Ʊ����
					ArrayList kcList  = ActionUtil.queryMaxFpkc(fpkfdm,fptpForm.getTpfpzldm());
					if(kcList != null && kcList.size() != 0)
					{
						Fpkc qshmItem = (Fpkc) kcList.get(0);
						fptpForm.setTpqshm(qshmItem.getQshm());
					}
				}
			}
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			
			Debug.out("FptpAction doQuery form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FptpBO)QsglProxy.getInstance().process(vo);
			
			ArrayList itemList = new ArrayList();
			
			//���ز�ѯ��Ϣֵ
			fptpForm.setFpbcbc(bo.getMessagefwx());
			if(fptpForm.getFpbcbc() != null && !fptpForm.getFpbcbc().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fptpForm.getFpbcbc());
				return mapping.findForward("show");
			}
			
			ArrayList cxList = bo.getCxList();
			
			if(cxList != null && cxList.size() >0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
				for(int i =0 ;i<cxList.size(); i++)
				{
					FptpForm fptpItemForm = new FptpForm();
					FptpBO cxItem = (FptpBO)cxList.get(i);
					
					fptpItemForm.setFpzldm(cxItem.getHtypzdzgxb().getPizzldm());
					fptpItemForm.setFphm(cxItem.getHtypzdzgxb().getPzhm());
					fptpItemForm.setHtbh(cxItem.getHtypzdzgxb().getHtbh());
					fptpItemForm.setNsrmc_buyer(cxItem.getFpczz().getFkdw());
					fptpItemForm.setNsrmc_seller(cxItem.getFpczz().getSkdw());
					fptpItemForm.setFwcqzh(cxItem.getFpczz().getFwcqzh());
					fptpItemForm.setFwzldz(cxItem.getFpczz().getFwzldz());
					fptpItemForm.setJe(String.valueOf(deFormat.format(cxItem.getFpczmx().getJe())));
					
					itemList.add(fptpItemForm);
				}
				// �Żص�form
				fptpForm.setCxList(itemList);
				fptpForm.setFpbcbc("");
			}
			else
			{
				request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ����������ѯ�����Ƿ���Ч��");
				fptpForm.setFpbcbc("test");
				return mapping.findForward("show");
			}
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ʊҳ���ѯʧ�ܣ�");
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
		Debug.out("into FptpAction's doSave Method....");
        // ��ֹҳ��ˢ��
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FptpForm fptpForm = (FptpForm)form;
		session.setAttribute(mapping.getName(), fptpForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			Debug.out("doSave >>"+fptpForm.getFpkfdm()+":"+fptpForm.getTpfpzldm()+":"+fptpForm.getTpqshm());
			
			//�ж�ҳ�洫�ݵķ�Ʊ�����Ƿ����
			if(fptpForm.getTpqshm() != null && !fptpForm.getTpqshm().equals(""))
			{
				//�ж�ҳ�洫�ݵķ�Ʊ�����ڷ�Ʊ������Ƿ����
				fptpForm.setFpbcxx(ActionUtil.queryMaxFpkcxx(fptpForm.getFpkfdm(), fptpForm.getTpfpzldm(), fptpForm.getTpqshm()));
				if(fptpForm.getFpbcxx() != null && !fptpForm.getFpbcxx().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fptpForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ���벻���ڣ�"+ fptpForm.getFpbcxx());
					return mapping.findForward("show");
				}
				
				//�ж�ҳ�洫�ݵķ�Ʊ�����Ƿ���ʹ��
				fptpForm.setFpbcxx(ActionUtil.checkFphm(fptpForm.getFpkfdm(), fptpForm.getTpfpzldm(), fptpForm.getTpqshm()));
				if(fptpForm.getFpbcxx() != null && !fptpForm.getFpbcxx().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fptpForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ������ʹ�ã�"+ fptpForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}
			
			FptpBO bo = new FptpBO();
			
			//fpdkForm �е� ��Ʊ�����Ϣ
			bo.setYhid(fptpForm.getYhid());  //�û�ID
			bo.setYhmc(fptpForm.getYhmc());  //�û�����
			bo.setSwjgzzjgdm(fptpForm.getSwjgzzjgdm());  //˰�������֯��������
			bo.setSwjgzzjgmc((ActionUtil.getSwjjzzlgmc(bo.getSwjgzzjgdm()))); //˰�������֯��������
			bo.setFpkfdm(fptpForm.getFpkfdm());  //��Ʊ�ⷿ����
			bo.setTpfpzldm(fptpForm.getTpfpzldm()); //��Ʊ��Ʊ�������
			bo.setTpqshm(fptpForm.getTpqshm());  //��Ʊ��Ʊ����
			bo.setKpr(fptpForm.getKpr());  //��Ʊ��
			
			//ȡ��Ʊ����������롢��Ʊ����ֵ
			String[] fphms = fptpForm.getFphms().split(",");
			for(int i =0; i<fphms.length;i++)
			{
				String innerFphms = fphms[i];
				String[] fphmArr = innerFphms.split(":");
				String cxfpzldm = fphmArr[0];
				String cxfphm = fphmArr[1];
				
				bo.setCxfpzldm(cxfpzldm);  //��ѡֵ��Ʊ��������
				bo.setCxqshm(cxfphm);  //��ѡֵ��Ʊ����
				bo.setCxList(ActionUtil.queryFptp(bo.getFpkfdm(),cxfpzldm,cxfphm));  //��Ʊ��Ϣ
			
				//�ж��ύ��Ʊ���Ƿ��н��
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					request.setAttribute(mapping.getAttribute(), fptpForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊʧ�ܣ�������������Ʊ��������:"+ ActionUtil.getFpzlmc(fptpForm.getFpzldm())+"��ѡ���Ƿ���ȷ��");
					return mapping.findForward("show");
				}
				
				//����ѯ���д��VO��
				for(int j =0; j<bo.getCxList().size(); j++)
				{
					FptpBO cxItem = (FptpBO)bo.getCxList().get(j);
					bo.setFpczz(cxItem.getFpczz());
					bo.setFpczmx(cxItem.getFpczmx());
					bo.setHtypzdzgxb(cxItem.getHtypzdzgxb());
				}
			}
			
			Debug.out("FptpAction doSave form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FptpBO)QsglProxy.getInstance().process(vo);
			
			ArrayList fpkpList =bo.getFpkpList();
			
			if(fpkpList !=null && fpkpList.size() > 0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
				for(int i =0 ;i < fpkpList.size(); i++)
				{
					
					FptpBO fpkcNewItem = (FptpBO) fpkpList.get(i);

					fptpForm.setSwjgzzjgmc(fpkcNewItem.getFpczz().getDkdwmc());
					fptpForm.setFpkfdm(fpkcNewItem.getFpczz().getFpkfdm());
					fptpForm.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					fptpForm.setFphm(fpkcNewItem.getFpczz().getFphm());
					fptpForm.setKprq(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(fpkcNewItem.getFpczz().getKprq())));
					fptpForm.setHyfl(fpkcNewItem.getFpczz().getHyfl());
					fptpForm.setNsrmc_buyer(fpkcNewItem.getFpczz().getFkdw());
					fptpForm.setNsrmc_seller(fpkcNewItem.getFpczz().getSkdw());
					fptpForm.setFwcqzh(fpkcNewItem.getFpczz().getFwcqzh());
					fptpForm.setFwzldz(fpkcNewItem.getFpczz().getFwzldz());
					fptpForm.setPm(fpkcNewItem.getFpczmx().getPm());
					fptpForm.setDj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getDj())));
					fptpForm.setSl(String.valueOf(fpkcNewItem.getFpczmx().getSl()));
					fptpForm.setJe(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fptpForm.setBz(fpkcNewItem.getFpczz().getBz());
					fptpForm.setXxhj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fptpForm.setDxhj(String.valueOf(ActionUtil.toChineseCharacter(fpkcNewItem.getFpczmx().getJe().doubleValue())));
					fptpForm.setWszh(fpkcNewItem.getFpczz().getSphm());
					fptpForm.setJdhm(fpkcNewItem.getFpczz().getFpzldm()+fpkcNewItem.getFpczz().getFphm());
					fptpForm.setKpr(fpkcNewItem.getFpczz().getKpr());
				}
				fptpForm.resetforSave();
			}
			
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ʊ��Ϣ����ɹ���");
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ʊ��Ϣ����ʧ�ܣ�");
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
            HttpServletResponse response) {
    	
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
            HttpServletResponse response) {
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("fpdk");
    }
}
