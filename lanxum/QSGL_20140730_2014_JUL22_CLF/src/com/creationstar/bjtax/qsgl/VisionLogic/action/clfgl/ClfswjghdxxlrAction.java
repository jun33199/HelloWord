package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Szqy;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tdjc;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwcqzbzzflx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qszyxsmx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * ������˰����غ˶���Ϣ¼�� Action
 * @author 
 *
 */
public class ClfswjghdxxlrAction extends BaseAction {
	
	public ActionForward doShow(ActionMapping mapping,
	        ActionForm form,
	        HttpServletRequest request,
	        HttpServletResponse response)
	{
		Debug.out("into ClfswjghdxxlrAction's doShow Method....");
		
		try
        {
			//����Token;
			saveToken(request);
			//��ÿͻ����ύ������
			ClfswjghdxxlrForm clfswjghdxxForm = (ClfswjghdxxlrForm)form;
			//��ʼ��
			initCode(request,clfswjghdxxForm);
			clfswjghdxxForm.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
			request.setAttribute(mapping.getAttribute(), clfswjghdxxForm);
        }catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return new ActionForward(mapping.getInput());
        }
		//ת��showҳ�档
		return mapping.findForward("show");
	}

	private void initCode(HttpServletRequest request,ClfswjghdxxlrForm clfswjghdxxForm) {
		HttpSession session = request.getSession(false);
		ClfxxcjAction cjAction = new ClfxxcjAction();
		//���ӷ��ݲ�Ȩ֤��עס�����ʹ�������ؼ��Σ�
		clfswjghdxxForm.setTdjcList(ActionUtil.getCodeTables(session.getServletContext(), Constants.TDJC));
		//���ӷ��ݲ�Ȩ֤��עס�����ʹ����
		clfswjghdxxForm.setFwcqzbzzflxList(ActionUtil.getCodeTables(session.
				getServletContext(), Constants.FWCQZBZZFLX));
		clfswjghdxxForm.setZjList(cjAction.getZjlxcode(request, clfswjghdxxForm));
		//����������������
		clfswjghdxxForm.setFwszqyList(ActionUtil.getCodeTables(session.
				getServletContext(), Constants.FWSZQY));
		//����Ȩ��ת����ϸ
		ArrayList list=ActionUtil.getCodeTables(session.getServletContext(), Constants.QSZYXSMX);
		ArrayList filterList=new ArrayList();
		int j=0;
		for(int i=0;i<list.size();i++){
			Qszyxsmx qszyxsmx=(Qszyxsmx)list.get(i);
			if(clfswjghdxxForm.getFwqszylx()!=null && clfswjghdxxForm.getFwqszylx().equals(qszyxsmx.getQszyxsdm())){
				filterList.add(j++, qszyxsmx);
			}
		}
		clfswjghdxxForm.setQszyxsmxList(filterList);
		
		
		//���õ�ǰ��¼����Ϣ
		String lrrq;
		try {
			lrrq = DateTimeUtil.getCurrentDate();
			UserData ud = super.getUserData();
			Debug.out("lrr = " + ud.getYhmc()+"    lrrq = " + lrrq);
			
			
			clfswjghdxxForm.setLrr(ud.getYhmc());
			clfswjghdxxForm.setLrrq(lrrq);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @methodName:doToPrint
	 * @function:
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-17 ����02:30:51
	 * @version 1.1
	 * 
	 *
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ClfswjghdxxlrForm cf1 = (ClfswjghdxxlrForm)form;
		ClfswjghdxxlrBo data = new ClfswjghdxxlrBo();
		data.setHtbh(cf1.getHtbh());
		data.setSbbh(cf1.getSbbh());
		String queryHthm = cf1.getHtbh();
		//�ж����htbhΪ�գ�����ס�����ס��ֱ����ת��modified by zhangj
    	initCode(request, cf1);
		cf1.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
		if(queryHthm==null||"".equals(queryHthm)){
            if("1".equals(cf1.getFwhdlxdm())){
            	return mapping.findForward("jumpToFzf");
            }else{
            	return mapping.findForward("save");
            }
		}
		//ִ�б������
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        ClfswjghdxxlrForm cf=null;
        try {
        	ClfswjghdxxlrBo dataRes = (ClfswjghdxxlrBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfswjghdxxlrForm)dataRes.getFromData();
        	cf.setSlrq( DateUtils.getHyphenDate());
        	String fwhdlxdm=cf1.getFwhdlxdm();
        	boolean isQuery=cf1.getIsQuery();
        	cf1.setFormData(cf);
    		// ��ҳ�����������mapping
        	initCode(request, cf);
        	cf.setIsQuery(isQuery);
			cf.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
    		request.setAttribute(mapping.getAttribute(), cf);
    		
    		System.out.println("+++++++����¥��+++++++++++++++++++++"+cf.getSzlc_show());
    		System.out.println("+++++++��¥��+++++++++++++++++++++"+cf.getZlc_show());
    		System.out.println("+++++++�Ƿ����޸�Ȩ��+++++++++++++++++++++  "+cf.isHasMAuthorise()+"  +++++"+cf.getIsSaved());
    		
    		System.out.println("+++++++++++++++"+fwhdlxdm);
    		request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�ɹ�");
    		//����IsSaved��isHasMAuthorise��Fwhdlxdm�ж���ת�ĸ�ҳ�棬modified by zhangj
    		if("false".equals(cf.getIsSaved())){
                if("1".equals(fwhdlxdm)){
                	return mapping.findForward("jumpToFzf");
                }else{
                	return mapping.findForward("save");
                }
    		}else if("true".equals(cf.getIsSaved())){
    			if(isQuery){
                    if("1".equals(cf.getFwhdlxdm())){
                    	return mapping.findForward("jumpToFzf");
                    }else{
                    	return mapping.findForward("save");
                    }
    			}
    			
    			 if(cf.isHasMAuthorise()==true){
        			if(fwhdlxdm!=null&&cf.getFwhdlxdm()!=null&&!fwhdlxdm.equals(cf.getFwhdlxdm())){
        				ClfswjghdxxlrForm cForm=ClfcjxxFilter(cf);
        				initCode(request, cForm);
        				cForm.setIsQuery(false);
        				cForm.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
        				request.setAttribute(mapping.getAttribute(), cForm);
        			}else{
        				cf.setIsQuery(true);
        			}    			
                    if("1".equals(fwhdlxdm)){
                    	return mapping.findForward("jumpToFzf");
                    }else{
                    	return mapping.findForward("save");
                    }
    			}else{
    				
    				cf.setIsQuery(true);
    				
    				 //�ѱ����ֵ
    				 if("1".equals(cf.getFwhdlxdm())){
                     	return mapping.findForward("jumpToFzf");
                     }else{
                     	return mapping.findForward("save");
                     }
    			}
    			

    		}else{
    			return mapping.findForward("save");
    		}
		
        } catch (BaseException te) {
        	
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
    		// ��ҳ�����������mapping
            cf1.reSet();
        	initCode(request, cf1);
        	cf1.setHtbh(queryHthm);
            request.setAttribute(mapping.getAttribute(), cf1);
            return new ActionForward(mapping.getInput());
        }
	
        
	}
	
	
	/**
	 * ִ��ɾ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//��ֹ�ظ��ύ
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
		//����Token;
		saveToken(request);	
		
		
		//��ÿͻ����ύ������
		ClfswjghdxxlrForm cf = (ClfswjghdxxlrForm)form;		
		
		System.out.println("ִ�к˶���Ϣɾ����ɾ���걨���Ϊ#############"+cf.getSbbh());
		
		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo)cf.getData();
		
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.DELETE);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        
        try {
        	QsglProxy.getInstance().process(vo);
        	this.initCode(request, cf);
        	this.doQuery(mapping, cf, request, response);
    		request.setAttribute(mapping.getAttribute(), cf);
    		request.setAttribute(Constants.MESSAGE_KEY, "ɾ���ɹ���");
    		
    		cf.reSet();
    		cf.clear();
    		
    		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute(Constants.MESSAGE_KEY, e.getMessage());
        	this.initCode(request, cf);
        	this.doQuery(mapping, cf, request, response);
    		request.setAttribute(mapping.getAttribute(), cf);	
    		return mapping.findForward("show");
			
		}
		return mapping.findForward("show");
	}
	
	
	
	
	
	/**
	 * ͨ����ͬ��Ų�ѯ�������ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward doSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//��ֹ�ظ��ύ
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
		//����Token;
		saveToken(request);
		
		
		//��ÿͻ����ύ������
		
		ClfswjghdxxlrForm cf = (ClfswjghdxxlrForm)form;
		System.out.println("�걨���========"+cf.getSbbh());
		System.out.println("��ͬ���========"+cf.getHtbh());
		
		System.out.println("��������סַ========"+cf.getSqrxzdz());
		System.out.println("�Ƿ�Ϊ��ͥΨһ�����÷�========"+cf.getJtwyshyhbz());
		System.out.println("��������========"+cf.getFwlxdm());
		System.out.println("�������========"+cf.getJcnd());
		System.out.println("��¥��========"+cf.getZlc());
		System.out.println("ԭ������Ʊ���========"+cf.getYgffpje());
		System.out.println("����֤������========"+cf.getGfzmrq());
		System.out.println("������ֵ˰�걨��ʽ========"+cf.getTdzzssbfs());
		System.out.println("ȡ�÷��ز�ʱ�����ɵ���˰���========"+cf.getQdfcqsje());
		System.out.println("ȡ�÷��ز�ʱ�����ɵ�ӡ��˰���========"+cf.getQdfcyhsje());
		System.out.println("ȡ������ʹ��Ȩ��֧���Ľ��========"+cf.getQdtdsyqzfje());
		System.out.println("�ɷ���������������۸�========"+cf.getJfpgjg());
		System.out.println("�۸���������========"+cf.getJgpgfy());
		System.out.println("��˰���������========"+cf.getFdczjjsjdm());
		System.out.println("��˰˰��ǼǺ���========"+cf.getFdczjswdjzh());
		System.out.println("���ز��н���ϵ�绰========"+cf.getFdczjlxdh());
		System.out.println("���ز�����������========"+cf.getFdczjjjr());
		System.out.println("���ز���������ϵ�绰========"+cf.getFdczjjjrlxdh());
		System.out.println("���ز����������֤����========"+cf.getFdczjjjrzjhm());
		System.out.println("�������ʸ�֤�����========"+cf.getFdczjjjrzgzsh());
		System.out.println("��Ȩ֤��ע�������========"+cf.getCqzbzjzmjfl());
		System.out.println("ÿƽ�׽��׵���========"+cf.getMpmjydj());
		System.out.println("��ͨס������޼�========"+cf.getPtzfzgxj());
		System.out.println("�����ݻ���========"+cf.getFwrjl());
		System.out.println("���ֱ�׼========"+cf.getHfbz());
		System.out.println("ס��ʹ��ʱ��========"+cf.getZfsjsjfl());
		System.out.println("Ӫҵ˰���շ�ʽ========"+cf.getYyszsfs());
		System.out.println("��������˰���շ�ʽ========"+cf.getGrsdszsfs());
		System.out.println("������ֵ˰���շ�ʽ========"+cf.getTdzsszsfs());
		System.out.println("��˰����ȷ�Ϸ�ʽ========"+cf.getJssrqrfs());
		System.out.println("��˰������========"+cf.getJsje());
		System.out.println("ס�������۸�========"+cf.getZfpgjg());
		System.out.println("ס��װ�޷���========"+cf.getZfzxfy());
		System.out.println("ס��������Ϣ========"+cf.getZfdklx());
		System.out.println("������========"+cf.getSxf());
		System.out.println("��֤��========"+cf.getGzf());
		System.out.println("�������========"+cf.getHlfy());
		System.out.println("���ؼ��δ���========"+cf.getTdjcdm());
		System.out.println("���ؼ�������========"+cf.getTdjcmc());
		System.out.println("���ݲ�Ȩ֤��עס�����ʹ���========"+cf.getFwcqzbzzflxdm());
		System.out.println("���ݲ�Ȩ֤��עס����������========"+cf.getFwcqzbzzflxmc());
		System.out.println("��Ȩ֤��ע�������Submit========"+cf.getCqzbzjzmjflSubmit());
		System.out.println("���ֱ�׼Submit========"+cf.getHfbzSubmit());
		System.out.println("��������˰���շ�ʽSubmit========"+cf.getGrsdszsfsSubmit());
		System.out.println("Ӫҵ˰���շ�ʽSubmit========"+cf.getYyszsfsSubmit());
		System.out.println("��˰������Submit========"+cf.getJsjeSubmit());
		System.out.println("������ֳ˰���շ�ʽSubmit========"+cf.getTdzsszsfsSubmit());
		System.out.println("ӡ��˰���շ�ʽSubmit========"+cf.getYhszsfsSubmit());		
		
		System.out.println("ÿƽ�׽��׵��� ========"+cf.getMpmjydj());
		System.out.println("���ݽ������========"+cf.getFwjzmj());
		System.out.println("�������11========"+cf.getHlfy());
		System.out.println("���ݺ˶�����========"+cf.getFwhdlxdm());
		System.out.println("Ȩ��ת����ϸ========"+cf.getQszyxsmxdm());
		System.out.println("ԭ��˰Ʊ���ݼ�˰�۸�========"+cf.getYqspfwjsjg());

    	boolean isQuery=cf.getIsQuery();
    	
		
		

		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo)cf.getData();
		
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.INSERT);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        
        
        
        try {
        	ClfswjghdxxlrBo dataRes = (ClfswjghdxxlrBo)QsglProxy.getInstance().process(vo);
        	this.initCode(request, cf);
        	this.doQuery(mapping, cf, request, response);
    		
    		cf.setIsSaved(dataRes.getIsSaved());
    		cf.setHasMAuthorise(dataRes.hasMAuthorise);
    		cf.setIsQuery(isQuery);
    		cf.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
    		cf.setSzlc_show(dataRes.szlc_show);
    		cf.setZlc_show(dataRes.zlc_show);
    		request.setAttribute(Constants.MESSAGE_KEY, "����ɹ���");
    		request.setAttribute(mapping.getAttribute(), cf);
    		
    		System.out.println("-------------------"+request.getAttribute(Constants.MESSAGE_KEY));
    		
        } catch (Exception te) {
        	te.printStackTrace();
        	
        	
        	this.initCode(request, cf);
        	cf.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
        	//this.doQuery(mapping, cf, request, response);
    		request.setAttribute(mapping.getAttribute(), cf);
    		request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
    		
            if(Constants.FWHDLX_NONHOUSING.equals(cf.getFwhdlxdm())){
            	return mapping.findForward("jumpToFzf");
            }else{
            	return mapping.findForward("show");
            }
    		
        }
        if(Constants.FWHDLX_NONHOUSING.equals(cf.getFwhdlxdm())){
        	return mapping.findForward("jumpToFzf");
        }else{
        	return mapping.findForward("show");
        }
	}
	
	/**
	 * ת������Ʊ�����ӡҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward doToPrint(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		ClfswjghdxxlrForm cf1 = (ClfswjghdxxlrForm)form;
		
		ClfswjghdxxlrBo data = new ClfswjghdxxlrBo();
		data.setHtbh(cf1.getHtbh());
		data.setSbbh(cf1.getSbbh());
		
		//ִ�б������
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        String returnStr="";
        try {
        	ClfswjghdxxlrBo dataRes = (ClfswjghdxxlrBo)QsglProxy.getInstance().process(vo);
        	ClfswjghdxxlrForm cf = (ClfswjghdxxlrForm)dataRes.getFromData();
        	cf.setSlrq( DateUtils.getHyphenDate());
        	cf1.setFormData(cf);
        	/*--modified by huohb,2014-07-22--*/
        	//���ݺ˶����ʹ���0��ס����1����ס��
        	//���ݷ��ݺ˶����ʹ��벻ͬ������ͬ�Ĵ�ӡҳ��
        	String fwhdlxdm = cf.getFwhdlxdm();
        	if("0".equals(fwhdlxdm)){
        		returnStr="toPrintZf";
        	}else if("1".equals(fwhdlxdm)){
        		returnStr="toPrintFzf";
        	}
        	/*--modified end--*/
        	
    		// ��ҳ�����������mapping
        	initCode(request, cf);
        	getMC(cf);
    		request.setAttribute(mapping.getAttribute(), cf);
    		
    		System.out.println("+++++++���ؼ���+++++++++++++++++++++"+cf.getTdjcdm());
    		System.out.println("+++++++����¥��+++++++++++++++++++++"+cf.getSzlc_show());
    		System.out.println("+++++++��¥��+++++++++++++++++++++"+cf.getZlc_show());
        } catch (BaseException te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            request.setAttribute(mapping.getAttribute(), cf1);
            return new ActionForward(mapping.getInput());
        }
		
		return mapping.findForward(returnStr);
	
	}
	
	//��÷������ؼ������ƺͷ��ݲ�Ȩ֤ס����������
	private void getMC(ClfswjghdxxlrForm cf){
		String tdjcdm = cf.getTdjcdm();
		String fwcqzbzzflxdm = cf.getFwcqzbzzflxdm();
		
		System.out.println("���ؼ���++++++++++++++++++"+cf.getTdjcList().size());
		System.out.println("���ݲ�Ȩ֤��עס������++++++++++++++++++"+cf.getFwcqzbzzflxList().size());
		
		//������ؼ�������
		if(tdjcdm != null &&!"".equals(tdjcdm)){
			for(int index =0 ; index <  cf.getTdjcList().size() ; index ++){
				Tdjc vo = new Tdjc();
				vo = (Tdjc)cf.getTdjcList().get(index);
				
				if(vo.getTdjcdm().equals(tdjcdm)){
					cf.setTdjcmc(vo.getTdjcmc());
				}
			}
		}
		
		//��÷��ݲ�Ȩ֤ס����������
		if(fwcqzbzzflxdm != null && !"".equals(fwcqzbzzflxdm)){
			for(int index = 0 ; index < cf.getFwcqzbzzflxList().size(); index ++){
				Fwcqzbzzflx vo = new Fwcqzbzzflx();
				vo = (Fwcqzbzzflx)cf.getFwcqzbzzflxList().get(index);
				
				if(vo.getFwcqzbzzflxdm().equals(fwcqzbzzflxdm)){
					cf.setFwcqzbzzflxmc(vo.getFwcqzbzzflxmc());
					
				}
			}
		}
		//mofify by yugw
		//��÷������څ^������
		String fwszqydm=cf.getFwszqydm();
		if(fwszqydm !=null && ! "".equals(fwszqydm)){
			for(int index = 0;index< cf.getFwszqyList().size();index++){
				Szqy vo=new Szqy();
				vo=(Szqy)cf.getFwszqyList().get(index);
				if(vo.getFwszqydm().equals(fwszqydm)){
					cf.setFwszqymc(vo.getFwszqymc());
				}
			}
		}
	}
	
	
	
	
	
	
	//ת����˰������
	public ActionForward doToSellerQSZS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return mapping.findForward("toSellerQSZS");
	}
//ת��Ʊ����	
	public ActionForward doToFPDK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return mapping.findForward("toFPDK");
	}
	//ת��˰�걨
	public ActionForward doToQSSB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return mapping.findForward("toQSSB");
	}
	private ClfswjghdxxlrForm ClfcjxxFilter(ClfswjghdxxlrForm cf){
		ClfswjghdxxlrForm form= new ClfswjghdxxlrForm();
		//==============�������ɼ���Ϣ=================
		form.setHasMAuthorise(cf.isHasMAuthorise());
		form.setSaveIsSuccess(cf.getSaveIsSuccess());
		form.setHasMfSkzsxx(cf.getHasMfSkzsxx());//�ú˶�������˰��������Ϣ
		form.setHasMfFpdkxx(cf.getHasMfFpdkxx());//�ú˶���������Ʊ������Ϣ
		 
		form.setKeyStr(cf.getKeyStr());
		form.setSbbh(cf.getSbbh());
		form.setBbbs(cf.getBbbs());// �汾��ʾ
		form.setHtbh(cf.getHtbh());// ��ͬ���
		form.setHtwsqyrq(cf.getHtwsqyrq());// ��ͬ����ǩԼ����
		form.setFwzlqx(cf.getFwzlqx());// ������������
		form.setFwzldz(cf.getFwzldz());// ���������ַ
		form.setFwqszylx(cf.getFwqszylx());// ����Ȩ��ת������_����
		form.setFwqszylxmc(cf.getFwqszylxmc());// ����Ȩ��ת������_����
		form.setSfwscsssggf(cf.getSfwscsssggf());// �Ƿ�Ϊ�״������ѹ�����_����
		form.setSfwscsssggfmc(cf.getSfwscsssggfmc());// �Ƿ�Ϊ�״������ѹ�����_����
		form.setFwcqzh(cf.getFwcqzh());// ���ݲ�Ȩ֤��
		form.setFwsyqztfrq(cf.getFwsyqztfrq());// ��������Ȩ֤�����
		form.setFwjzmj(cf.getFwjzmj());// ���ݽ������
		form.setJzjgdm(cf.getJzjgdm());// �����ṹ����
		form.setJzjgmc(cf.getJzjgmc());// �����ṹ����
		form.setGhyt(cf.getGhyt());// �滮��;
		form.setHtzj(cf.getHtzj());// ��ͬ�ܼ�
		form.setBzdm(cf.getBzdm());// ���ִ���
		form.setBzmc(cf.getBzmc());// ��������
		form.setHl(cf.getHl());// ����
		form.setWbje(cf.getWbje());// ��ҽ��
		form.setSzlc(cf.getSzlc());// ����¥��
		form.setFdczjjgmc(cf.getFdczjjgmc());// ���ز��н��������
		form.setZlc_show(cf.getZlc_show());//
		form.setSzlc_show(cf.getSzlc_show());
		form.setAll_sellerInfo(cf.getAll_sellerInfo());
		form.setAll_buyerInfo(cf.getAll_buyerInfo());
		form.setUNEpiccode(cf.getUNEpiccode());
		//==============�������˶���Ϣ=================
		form.setHasMAuthorise(cf.isHasMAuthorise());
		form.setIsSaved(cf.getIsSaved());
		form.setIsQuery(cf.getIsQuery());
		form.setGfzmrq(cf.getGfzmrq());
		
		return form;
	}
}
