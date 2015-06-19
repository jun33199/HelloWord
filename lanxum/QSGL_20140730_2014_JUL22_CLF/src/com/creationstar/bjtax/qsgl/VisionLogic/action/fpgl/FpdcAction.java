package com.creationstar.bjtax.qsgl.VisionLogic.action.fpgl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpdcForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.ExcelUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ��������Action��:FpdcAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpdcAction extends AddBaseAction {
	
	static final String[] TITLEHZ = {"���������", "��Ʊ��ʼʱ��", "��Ʊ��ֹʱ��", "������Ʊ����", "��Ʊ����", "��Ʊ����", 
									 "������Ʊ���", "��Ʊ���","����ʱ�䣨ϵͳ���ڣ�","����Ա����������Ա��"};
	
	static final String[] COLUMSHZ = {"jsjdm", "qsrq", "jzrq", "kpsl", "tpsl", "fpsl", "kpje", "tpje", "czrq", "czr"};
	
	static final String[] TITLE = {"��Ʊ����", "��Ʊ����", "���������", "��Ʊ����", "���λ", "�տλ", "������λ",
								   "��Ʊ���ʹ���","��Ʊ��������", "��Ʊ�ϼƽ��", "��Ʊ��Ʊ����", "��Ʊ��Ʊ����", "��ע", 
								   "˰Ʊ����", "��Ʊ��","����ʱ�䣨ϵͳ���ڣ�","����Ա���������ݲ���Ա��"};
	
	static final String[] COLUMS = {"fpzldm", "fphm", "jsjdm", "kprq", "fkdw","skdw","dkdwmc", "kplxdm", "kplxmc",
									"je", "tpfpzldm","tpfphm","bz","sphm","kpr","czrq","czr"};
	
	static final String[] TITLEMX = {"��Ʊ����", "��Ʊ����", "��Ŀ", "����", "����", "���", "��Ʊ����"};
	
	static final String[] COLUMSMX = {"fpzldm", "fphm", "pm", "dj", "sl", "je", "kprq"};

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
		Debug.out("into FpdcAction's doShow Method....");
		
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdcForm fpdcForm = (FpdcForm)form;
		session.setAttribute(mapping.getName(), fpdcForm);
		try
		{
			//���ҳ��
			fpdcForm.resetPage();
			
			fpdcForm.setYhid(userData.yhid);
			fpdcForm.setYhmc(userData.yhmc);
			fpdcForm.setZhdm(userData.xtsbm1);
			fpdcForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpdcForm.getYhid();
			String zhdm =fpdcForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpdcForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpdcForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpdcForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//��÷�Ʊ�����б�fpzlList
			fpdcForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//Swjjzzlgmc
			fpdcForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpdcForm.getSwjgzzjgdm()));
			
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����ҳ����ʾ�ɹ���");
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
		FpdcForm fpdcForm = (FpdcForm)form;
		session.setAttribute(mapping.getName(), fpdcForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//���ҳ��
			fpdcForm.clear();
			
			String swjgzzjgdm = fpdcForm.getSwjgzzjgdm(); //˰�������֯��������
			String fpkfdm = fpdcForm.getFpkfdm();  //��Ʊ�ⷿ����
			String htbh = fpdcForm.getHtbh();  //��ͬ���
			String fpzldm = fpdcForm.getFpzldm(); //��Ʊ�������
			String qshm = fpdcForm.getQshm();  //��ʼ����
			String qsrq = fpdcForm.getQsrq();  //��ʼ����
			String jzrq = fpdcForm.getJzrq();  //��ֹ����
			String dczt = fpdcForm.getDczt();  //����״̬
			
			//System.out.println("fpkfdm:"+fpkfdm+" htbh:"+htbh+" fpzldm:"+fpzldm+" qshm:"+qshm+" qsrq:"+qsrq+" jzrq:"+jzrq+" dczt:"+dczt);
			
			FpdcBO bo = new FpdcBO();
			
			//�жϺ�ͬ����Ƿ����
			if(htbh != null && !htbh.equals(""))
			{
				fpdcForm.setFpbcbc(ActionUtil.queryHtbh2(htbh));
				if(fpdcForm.getFpbcbc() != null && !fpdcForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "�ú�ͬ���δ���������Ʊҵ�񣬻���ط�Ʊ�Ѱ�����Ʊҵ��"+ fpdcForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ�������޶�Ӧ��Ʊ��Ϣ!");
					return mapping.findForward("show");
				}
			}
			
			/*//�жϷ�Ʊ�����Ƿ��Ѱ�����Ʊ
			if(qshm != null && !qshm.equals(""))
			{
				fpdcForm.setFpbcbc(ActionUtil.queryFphm2(fpzldm,qshm));
				if(fpdcForm.getFpbcbc() != null && !fpdcForm.getFpbcbc().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
					return mapping.findForward("show");
				}
			}*/
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			bo.setQsrq(qsrq);
			bo.setJzrq(jzrq);
			bo.setDczt(dczt);
			bo.setSwjgzzjgdm(swjgzzjgdm);
			
			Debug.out("FpdcAction doQuery form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdcBO)QsglProxy.getInstance().process(vo);
			
			ArrayList itemList = new ArrayList();
			
			//���ز�ѯ��Ϣֵ
			fpdcForm.setFpbcbc(bo.getMessagefwx());
			if(fpdcForm.getFpbcbc() != null && !fpdcForm.getFpbcbc().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fpdcForm.getFpbcbc());
				return mapping.findForward("show");
			}
			
			ArrayList cxList = bo.getCxList();
			if(cxList != null && cxList.size() >0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
				for(int i =0 ;i<cxList.size(); i++)
				{
					FpdcForm fpdcItemForm = new FpdcForm();
					FpdcBO cxItem = (FpdcBO)cxList.get(i);
					
					fpdcItemForm.setFpzldm(cxItem.getFpczz().getFpzldm());
					fpdcItemForm.setFphm(cxItem.getFpczz().getFphm());
					fpdcItemForm.setHtbh(cxItem.getHtypzdzgxb().getHtbh());
					fpdcItemForm.setNsrmc_buyer(cxItem.getFpczz().getFkdw());
					fpdcItemForm.setNsrmc_seller(cxItem.getFpczz().getSkdw());
					fpdcItemForm.setFwcqzh(cxItem.getFpczz().getFwcqzh());
					fpdcItemForm.setFwzldz(cxItem.getFpczz().getFwzldz());
					
					if(cxItem.getFpczmx().getJe()!=null)
					{
						fpdcItemForm.setJe(String.valueOf(deFormat.format(cxItem.getFpczmx().getJe())));
					}
					
					if(cxItem.getFpczz().getDcbz()!=null && cxItem.getFpczz().getDcbz().equals(Constants.FP_DCBZ_WTP))
					{
						fpdcItemForm.setDczt("δ����");
					}
					if(cxItem.getFpczz().getDcbz()!=null && cxItem.getFpczz().getDcbz().equals(Constants.FP_DCBZ_YTP))
					{
						fpdcItemForm.setDczt("�ѵ���");
					}
					
					itemList.add(fpdcItemForm);
				}
				
				// �Żص�form
				fpdcForm.setCxList(itemList);
				fpdcForm.setFpbcbc("");
			}
			else
			{
				request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ����������ѯ�����Ƿ���Ч��");
				fpdcForm.setFpbcbc("test");
				return mapping.findForward("show");
			}
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
		Debug.out("into FpdcAction's doSave Method....");
        // ��ֹҳ��ˢ��
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// ��ȡ�û���¼���ݡ�
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdcForm fpdcForm = (FpdcForm)form;
		session.setAttribute(mapping.getName(), fpdcForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			FpdcBO bo = new FpdcBO();
			
			//fpdkForm �е� ��Ʊ�����Ϣ
			bo.setYhid(fpdcForm.getYhid());  //�û�ID
			bo.setYhmc(fpdcForm.getYhmc());  //�û�����
			bo.setFpkfdm(fpdcForm.getFpkfdm());  //��Ʊ�ⷿ����
			bo.setSwjgzzjgdm(fpdcForm.getSwjgzzjgdm());
			
			//ȡ��Ʊ����������롢��Ʊ����ֵ
			String[] fphmsArr = fpdcForm.getFphms().split(",");
			ArrayList resList = new ArrayList();
			StringBuffer selectedSpxxBuff = new StringBuffer();
			for(int i=0; i<fphmsArr.length; i++)
			{
				Fpczz fpczz = new Fpczz();
				String innerFphms = fphmsArr[i];
				String[] fphmArr = innerFphms.split(":");
				String cxfpzldm = fphmArr[0];
				String cxfphm = fphmArr[1];
				
				fpczz.setFpzldm(cxfpzldm);  //��ѡֵ��Ʊ��������
				fpczz.setFphm(cxfphm);  //��ѡֵ��Ʊ����
				
				//����ͳ����
				if(i == 0){
					selectedSpxxBuff.append("(");
					selectedSpxxBuff.append(" ( b.fphm ='"+cxfphm+"' and b.fpzldm ='"+cxfpzldm+"')");
				}else{
					selectedSpxxBuff.append("or ( b.fphm ='"+cxfphm+"' and b.fpzldm ='"+cxfpzldm+"')");
				}
				
				if(i == fphmsArr.length -1)
				{
					selectedSpxxBuff.append(")");
				}
				
				resList.add(fpczz);
			}
			
			bo.setCxList(resList);
			
			//�ж��ύ��Ʊ���Ƿ��н��
			if(bo.getCxList() == null || bo.getCxList().size() == 0)
			{
				request.setAttribute(mapping.getAttribute(), fpdcForm);
				request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ����ʧ�ܣ�������������Ʊ��������:"+ ActionUtil.getFpzlmc(fpdcForm.getFpzldm())+"��ѡ���Ƿ���ȷ��");
				return mapping.findForward("show");
			}
			
			Debug.out("FpdcAction doSave form.getData() bo.class is��" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//�����̨��Ҫ���������ݶ���
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdcBO)QsglProxy.getInstance().process(vo);
			
			ArrayList mxList =bo.getFpkpList();
			
			ArrayList fpdchztList = new ArrayList();
			ArrayList fpdcmxtList = new ArrayList();
			try{
			if(mxList !=null && mxList.size() > 0)
			{
				//����LIST
				
				ArrayList hzList = ActionUtil.queryFpdchzData(bo.getFpkfdm(),selectedSpxxBuff.toString(),bo.getSwjgzzjgdm());
				
				for(int i =0 ;i < hzList.size(); i++)
				{
					FpdcBO2 fpdcBO2 = new FpdcBO2();
					FpdcBO2 fphzItem = (FpdcBO2) hzList.get(i);
					
					fpdcBO2.setJsjdm(fphzItem.getJsjdm());
					if(fphzItem.getQsrq() != null && fphzItem.getQsrq() != "")
					{
						fpdcBO2.setQsrq(fphzItem.getQsrq().substring(0, fphzItem.getQsrq().length()-2));
					}
					if(fphzItem.getJzrq() != null && fphzItem.getJzrq() != "")
					{
						fpdcBO2.setJzrq(fphzItem.getJzrq().substring(0, fphzItem.getJzrq().length()-2));
					}
					fpdcBO2.setKpsl(fphzItem.getKpsl());
					fpdcBO2.setTpsl(fphzItem.getTpsl());
					fpdcBO2.setFpsl(fphzItem.getFpsl());
					fpdcBO2.setKpje(fphzItem.getKpje());
					fpdcBO2.setTpje(fphzItem.getTpje());
					fpdcBO2.setCzrq(DateUtils.displayValue(new Date(),"yyyy-MM-dd"));
					fpdcBO2.setCzr(userData.yhmc);
					
					fpdchztList.add(fpdcBO2);
				}
				
				//��ϸLIST
				for(int i =0 ;i < mxList.size(); i++)
				{
					FpdcBO2 fpdcBO2 = new FpdcBO2();
					FpdcBO fpkcNewItem = (FpdcBO) mxList.get(i);
					
					fpdcBO2.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					fpdcBO2.setFphm(fpkcNewItem.getFpczz().getFphm());
					fpdcBO2.setJsjdm(fpkcNewItem.getSwjgzzjg().getJsjdm());
					
					//fpdcBO2.setKprq(fpkcNewItem.getFpczz().getKprq().toString());
					
					if(fpkcNewItem.getFpczz().getKprq().toString() != null && fpkcNewItem.getFpczz().getKprq().toString() != "")
					{
						fpdcBO2.setKprq(fpkcNewItem.getFpczz().getKprq().toString().substring(0, fpkcNewItem.getFpczz().getKprq().toString().length()-2));
					}
					
					fpdcBO2.setFkdw(fpkcNewItem.getFpczz().getFkdw());
					fpdcBO2.setSkdw(fpkcNewItem.getFpczz().getSkdw());
					fpdcBO2.setDkdwmc(fpkcNewItem.getFpczz().getDkdwmc());
					fpdcBO2.setKplxdm(fpkcNewItem.getFpczz().getKplxdm());
					fpdcBO2.setKplxmc(fpkcNewItem.getKplx().getKplxmc());
					fpdcBO2.setTpfpzldm(fpkcNewItem.getFpczz().getTpfpzldm());
					fpdcBO2.setTpfphm(fpkcNewItem.getFpczz().getTpfphm());
					fpdcBO2.setBz(fpkcNewItem.getFpczz().getBz());
					fpdcBO2.setSphm(fpkcNewItem.getFpczz().getSphm());
					fpdcBO2.setKpr(fpkcNewItem.getFpczz().getKpr());
					fpdcBO2.setCzrq(DateUtils.displayValue(new Date(),"yyyy-MM-dd"));
					fpdcBO2.setCzr(userData.yhmc);
					fpdcBO2.setPm(fpkcNewItem.getFpczmx().getPm());
					fpdcBO2.setSl(fpkcNewItem.getFpczmx().getSl());
					fpdcBO2.setDj(fpkcNewItem.getFpczmx().getDj());
					fpdcBO2.setJe(fpkcNewItem.getFpczmx().getJe());
					
					fpdcmxtList.add(fpdcBO2);
				}
				fpdcForm.setDczbList(fpdchztList);
				fpdcForm.setDcmxList(fpdcmxtList);
			
			
				System.out.println("���Excel���ܱ��ļ���¼����" + fpdcForm.getDczbList().size());
				System.out.println("���Excel��ϸ���ļ���¼����" + fpdcForm.getDcmxList().size());
			
				if (fpdcForm.getDcmxList() == null || fpdcForm.getDcmxList().size() <= 0) {
					request.setAttribute(Constants.MESSAGE_KEY,"û���ύ��ѯ���ݣ��޷�����Excel�ļ�");
					return mapping.findForward("query");
				}
				
				String currDate = DataConvert.Date2String(new Date(System.currentTimeMillis()));
				String zbFileName = "������Ʊ�������-".concat(currDate).concat(".xls");
				String zbEncodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(zbFileName);
				
				response.resetBuffer();
				response.setHeader("Content-disposition","attachment; filename=" + zbEncodeFileName);
				response.setContentType("application/vnd.ms-excel");
            
				HSSFWorkbook workbook = new HSSFWorkbook();
				
				String[] sheetName0 = {"DKFPHZB","������Ʊ���ܱ�"};
				String[] sheetName1 = {"DKFPMXB","������Ʊ��ϸ��"};
				String[] sheetName2 = {"DKFPMXZB","������Ʊ��ϸ�ӱ�"};
				
				ExcelUtil.generateExcel(workbook, 0, sheetName0[0], sheetName0[1], TITLEHZ, COLUMSHZ, fpdcForm.getDczbList(),response.getOutputStream());
				ExcelUtil.generateExcel(workbook, 1, sheetName1[0], sheetName1[1], TITLE, COLUMS, fpdcForm.getDcmxList(),response.getOutputStream());
				ExcelUtil.generateExcel(workbook, 2, sheetName2[0], sheetName2[1], TITLEMX, COLUMSMX, fpdcForm.getDcmxList(),response.getOutputStream());
				
				workbook.write(response.getOutputStream());  
				response.getOutputStream().close(); 
            
				request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ϣ�����ɹ���");
				return null;
			}
			}catch (Exception e) {  
		          e.printStackTrace();
		    }
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "��Ʊ��Ϣ����ʧ�ܣ�");
		}
		// ����Token;
		saveToken(request);
		return mapping.findForward("save");
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
    	Debug.out("into FpdcAction's doClear Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("clear");
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
    	Debug.out("into FpdcAction's doBack Method....");
    	
    	// ɾ��session�е�form
    	removeForm(mapping, request);
    	
    	// ����Token
    	saveToken(request);
    	return mapping.findForward("back");
    }
}
