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
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpdkForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdkBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ��������Action��:FpdkAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpdkAction extends AddBaseAction {
	
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
		Debug.out("into FpdkAction's doShow Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdkForm fpdkForm = (FpdkForm)form;
		session.setAttribute(mapping.getName(), fpdkForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//���ҳ��
			fpdkForm.resetPage();
			
			fpdkForm.setYhid(userData.yhid);
			fpdkForm.setYhmc(userData.yhmc);
			fpdkForm.setZhdm(userData.xtsbm1);
			fpdkForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpdkForm.getYhid();
			String zhdm =fpdkForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpdkForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpdkForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpdkForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//��÷�Ʊ�����б�fpzlList
			fpdkForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//Swjjzzlgmc
			fpdkForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpdkForm.getSwjgzzjgdm()));
			
			//���淵��ֵ
            request.setAttribute(mapping.getAttribute(), fpdkForm);
            request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����ҳ����ʾ�ɹ���");
		}
		catch (Exception ex)
		{
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
		Debug.out("into FpdkAction's doQuery Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdkForm fpdkForm = (FpdkForm)form;
		session.setAttribute(mapping.getName(), fpdkForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//���ҳ��
			fpdkForm.clear();
			
			String swjgzzjgdm = fpdkForm.getSwjgzzjgdm(); //˰�������֯��������
			
			String yhid = userData.yhid;
			String zhdm = userData.xtsbm1;
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpdkForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpdkForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			String fpkfdm = fpdkForm.getFpkfdm();  //��Ʊ�ⷿ����
			String htbh = fpdkForm.getHtbh();  //��ͬ���
			String fpzldm = fpdkForm.getFpzldm(); //��Ʊ�������
			String qshm = fpdkForm.getQshm();  //��ʼ����
			
			FpdkBO bo = new FpdkBO();
			
			//�жϺ�ͬ����Ƿ����
			if(htbh != null && !htbh.equals(""))
			{
				fpdkForm.setFpbcxx(ActionUtil.queryHtbh(htbh));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ������û��Ȩ�޲鿴�òɼ���Ϣ!");
					return mapping.findForward("show");
				}
				
				fpdkForm.setFpbcxx(ActionUtil.queryHtbh4(htbh));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "���ʵ�Ƿ����˰����غ˶���Ϣ��"+ fpdkForm.getFpbcxx());
					return mapping.findForward("show");
				}
				
				fpdkForm.setFpbcxx(ActionUtil.queryHtbh3(htbh));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "�ú�ͬ����Ѱ��������Ʊҵ��"+ fpdkForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}
			
			//�ж�ҳ�洫�ݵķ�Ʊ�����ڷ�Ʊ������Ƿ����
			if(qshm != null && !qshm.equals(""))
			{
				fpdkForm.setFpbcxx(ActionUtil.queryMaxFpkcxx(fpkfdm, fpzldm, qshm));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ���벻���ڣ�"+ fpdkForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}
			else
			{
				//����ҳ��
				fpdkForm.reset();
				//��÷�Ʊ�����б�fpzlList
				fpdkForm.setFpzlList(ActionUtil.queryMaxFpzl(fpkfdm));
				fpdkForm.setFpbcxx(fpdkForm.getFpzlList().size()+"");
				if (fpdkForm.getFpbcxx() == null || fpdkForm.getFpbcxx().equals("") || fpdkForm.getFpbcxx().equals("0"))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����δ���û�÷�Ʊ�����޿��������");
					return mapping.findForward("show");
				}else{
					fpdkForm.setFpbcxx("");
				}
				
				bo.setFpzlList(fpdkForm.getFpzlList());
			} 
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setSwjgzzjgdm(swjgzzjgdm);
			
			Debug.out("FpdkAction doQuery form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdkBO)QsglProxy.getInstance().process(vo);
			
			//������˰ƾ֤��ѯ��Ϣ
			String message = bo.getMessage();
			if(message != null && !message.equals(""))
			{
				fpdkForm.setMessage(message);
				request.setAttribute(Constants.MESSAGE_KEY, fpdkForm.getMessage().substring(3));
				return mapping.findForward("show");
			}
			//���ز�ѯ��Ϣֵ
			fpdkForm.setFpbcxx(bo.getMessagefwx());
			if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fpdkForm.getFpbcxx());
				return mapping.findForward("show");
			}
			
			//�����һ����Ʊ����
			ArrayList  fpkcList = bo.getFpkcList();
			
			if ((fpkcList != null) && (fpkcList.size() > 0))
			{
				Fpkc fpkc= new Fpkc();
				Fpkc qshmItem = (Fpkc) fpkcList.get(0);
				fpkc.setQshm(qshmItem.getQshm());
				fpdkForm.setQshm(fpkc.getQshm());
			}
			
			fpdkForm.setWszh(bo.getWszh());  //��˰ƾ֤��
			fpdkForm.setSbbh(bo.getSbbh()); //�걨���
			fpdkForm.setFwcqzh(bo.getFwcqzh());  //��÷��ݲ�Ȩ֤��
			fpdkForm.setFwzldz(bo.getFwzldz());  //���������ַ
			fpdkForm.setDj(bo.getHtzj());  //����
			fpdkForm.setJe(bo.getHtzj());  //�ܼ�
			fpdkForm.setXxhj(bo.getXxhj());  //Сд���ϼ�
			fpdkForm.setDxhj(bo.getDxhj());  //��д���ϼ�
			fpdkForm.setNsrmc_buyer(bo.getNsrmc_buyer()); //������
			fpdkForm.setNsrmc_seller(bo.getNsrmc_seller());  //�տ���
			fpdkForm.setHdjg(bo.getHdjg()); //�˶��۸�
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����ҳ���ѯʧ�ܣ�");
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
		Debug.out("into FpdkAction's doSave Method....");
        // ��ֹҳ��ˢ��
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdkForm fpdkForm = (FpdkForm)form;
		session.setAttribute(mapping.getName(), fpdkForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//���ҳ��
			fpdkForm.resetforBeSave();
			
			if(fpdkForm.getJe() == null || fpdkForm.getJe().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, "����ȷ��"+ fpdkForm.getJe());
				return mapping.findForward("show");
			}
			
			//��ȡ��Ʊ��ӡ����
			double je = Double.valueOf(fpdkForm.getJe()).doubleValue();
			int s_ = 99999999;
			int pageNum = 0;
			
			pageNum   = (int)je/s_;
			
			double temp = pageNum;
			
			if( je/s_ >temp)
			{
				pageNum +=1;
			}
			
			//�ж�ҳ�洫�ݵķ�Ʊ�����Ƿ����
			if(fpdkForm.getQshm() != null && !fpdkForm.getQshm().equals(""))
			{
				//�ж�ҳ�洫�ݵķ�Ʊ�����ڷ�Ʊ������Ƿ����
				fpdkForm.setFpbcbc(ActionUtil.queryMaxFpkcxx(fpdkForm.getFpkfdm(), fpdkForm.getFpzldm(), fpdkForm.getQshm()));
				if(fpdkForm.getFpbcbc() != null && !fpdkForm.getFpbcbc().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpdkForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ���벻���ڣ�"+ fpdkForm.getFpbcbc());
					return mapping.findForward("show");
				}
				//�ж�ҳ�洫�ݵķ�Ʊ�����Ƿ���ʹ��
				fpdkForm.setFpbcbc(ActionUtil.checkFphm(fpdkForm.getFpkfdm(), fpdkForm.getFpzldm(), fpdkForm.getQshm()));
				if(fpdkForm.getFpbcbc() != null && !fpdkForm.getFpbcbc().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpdkForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ������ʹ�ã�"+ fpdkForm.getFpbcbc());
					return mapping.findForward("show");
				}
				//�жϴ�ӡʹ�÷�Ʊ�����治��
				fpdkForm.setFpbcbc(ActionUtil.checkMoreFphm(fpdkForm.getFpkfdm(), fpdkForm.getFpzldm(), fpdkForm.getQshm(),pageNum));
				if(fpdkForm.getFpbcbc() != null && !fpdkForm.getFpbcbc().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpdkForm);
					request.setAttribute(Constants.MESSAGE_KEY, "��ӡʹ�÷�Ʊ�����治�㣺"+ fpdkForm.getFpbcbc());
					return mapping.findForward("show");
				}
			}
			
			FpdkBO bo = new FpdkBO();
			
			//fpdkForm �е� ��Ʊ�����Ϣ
			bo.setYhid(fpdkForm.getYhid());  //�û�ID
			bo.setYhmc(fpdkForm.getYhmc());  //�û�����
			bo.setSwjgzzjgdm(fpdkForm.getSwjgzzjgdm()); //˰�������֯��������
			bo.setSwjgzzjgmc((ActionUtil.getSwjjzzlgmc(bo.getSwjgzzjgdm()))); //˰�������֯��������
			bo.setFpkfdm(fpdkForm.getFpkfdm());  //��Ʊ�ⷿ����
			bo.setFpzldm(fpdkForm.getFpzldm()); //��Ʊ�������
			bo.setQshm(fpdkForm.getQshm());  //��ʼ����
			bo.setKpr(fpdkForm.getKpr());  //��Ʊ��
			bo.setPageNum(pageNum);
			
			//fpdkForm �е� �����������Ϣ
			bo.setSbbh(fpdkForm.getSbbh());  //�걨���
			bo.setHtbh(fpdkForm.getHtbh());  //��ͬ���
			bo.setHyfl(fpdkForm.getHyfl());  //��ҵ����
			bo.setNsrmc_buyer(fpdkForm.getNsrmc_buyer()); //���λ
			bo.setNsrmc_seller(fpdkForm.getNsrmc_seller()); //�տλ
			bo.setPm(fpdkForm.getPm());  //ƷĿ 
			bo.setDj(fpdkForm.getDj());  //����
			bo.setSl(fpdkForm.getSl());  //����
			bo.setJe(fpdkForm.getJe());  //���
			bo.setFwcqzh(fpdkForm.getFwcqzh());  //���ݲ�Ȩ֤��
			bo.setFwzldz(fpdkForm.getFwzldz());  //���������ַ
			bo.setBz(fpdkForm.getBzValueSubm()+fpdkForm.getBz());  //��ע
			bo.setXxhj(fpdkForm.getXxhj());  //Сд�ϼ�
			bo.setDxhj(fpdkForm.getDxhj());  //��д�ϼ�
			bo.setWszh(fpdkForm.getWszh());  //��˰֤����
			
			Debug.out("FpdkAction doSave form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdkBO)QsglProxy.getInstance().process(vo);
			
			ArrayList fpkpList =bo.getFpkpList();
			
			if(fpkpList == null || fpkpList.size() == 0)
			{
				bo.setMessagefwx("�޴�ӡ��������ʵ�Ƿ������Ʊ�ɹ�!");
			}
			ArrayList printList = new ArrayList();
			
			if(fpkpList !=null && fpkpList.size() > 0)
			{
				//fpdkForm.setBccgbz("1"); //���ñ���ɹ�
				
				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
				for(int i =0 ;i < fpkpList.size(); i++)
				{
					FpdkForm tempForm = new FpdkForm();
					
					FpdkBO fpkcNewItem = (FpdkBO) fpkpList.get(i);

					tempForm.setSwjgzzjgmc(fpkcNewItem.getFpczz().getDkdwmc());
					tempForm.setFpkfdm(fpkcNewItem.getFpczz().getFpkfdm());
					tempForm.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					tempForm.setFphm(fpkcNewItem.getFpczz().getFphm());
					tempForm.setKprq(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(fpkcNewItem.getFpczz().getKprq())));
					tempForm.setHyfl(fpkcNewItem.getFpczz().getHyfl());
					tempForm.setNsrmc_buyer(fpkcNewItem.getFpczz().getFkdw());
					tempForm.setNsrmc_seller(fpkcNewItem.getFpczz().getSkdw());
					tempForm.setFwcqzh(fpkcNewItem.getFpczz().getFwcqzh());
					tempForm.setFwzldz(fpkcNewItem.getFpczz().getFwzldz());
					tempForm.setPm(fpkcNewItem.getFpczmx().getPm());
					tempForm.setDj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getDj())));
					tempForm.setSl(String.valueOf(fpkcNewItem.getFpczmx().getSl()));
					tempForm.setJe(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					tempForm.setBz(fpkcNewItem.getFpczz().getBz());
					tempForm.setXxhj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					tempForm.setDxhj(Currency.convert((fpkcNewItem.getFpczmx().getJe().doubleValue())).substring(1));
					tempForm.setWszh(fpkcNewItem.getFpczz().getSphm());
					tempForm.setJdhm(fpkcNewItem.getFpczz().getFpzldm()+fpkcNewItem.getFpczz().getFphm());
					tempForm.setKpr(fpkcNewItem.getFpczz().getKpr());
					
					printList.add(tempForm);
				}
				
				fpdkForm.setPrintList(printList);
				
				fpdkForm.resetforSave();
			}
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
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doBack Method....");
    	
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
    	Debug.out("into FpdkAction's doClear Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("clear");
    }
    
    /**
	 * doFpzf
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doFpzf(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doFpzf Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("fpzf");
    }
    
    /**
	 * doAddSbGr
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doAddSbGr(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doAddSbGr Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("addSbGr");
    }
    
    
    /**
	 * doClfxxcj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doClfxxcj(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doClfxxcj Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("clfxxcj");
    }
    
    /**
	 * doMfskzs
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doMfskzs(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doMfskzs Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("mfskzs");
    }
    
    
    /**
     * ҳ���ӡ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws BaseException
    {
        // ����ǡ����ҳ�档
        return mapping.findForward("print");
    }
}
