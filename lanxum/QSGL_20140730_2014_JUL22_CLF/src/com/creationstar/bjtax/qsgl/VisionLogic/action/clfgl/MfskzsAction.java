package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.MfskzsForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.MfskzsBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>
 * Title:����˰������ACTION
 * </p>
 * <p>
 * Description: action
 * </p>
 * 
 * @author ��һ��
 * @version 1.1
 */
public class MfskzsAction extends BaseAction {


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
		   Debug.out("into MfskzsAction's doShow Method....");
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	       
	        //��ȡ�����ServletContext�е�processor-map.properties������
//	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
//	        //
//	        VOPackage vo = new VOPackage();
//	        vo.setAction(ActionType.INITSZSMLIST);
//	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
//	        vo.setUserData(this.getUserData());
//	        vo.setData(data);
	        
            try
	       {
//	    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
//	    	   mfskzsForm = (MfskzsForm)dataRes.getFromData(); 	    	   
	    	   
	    	   mfskzsForm.setTfrq(DateTimeUtil.getCurrentDate());
	    	   mfskzsForm.setZsjg(userData.ssdwmc);
	    	   //mfskzsForm.setScriptStr(dataRes.getScriptStr());
				//���淵��ֵ
//	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
//				
//	           request.setAttribute(Constants.MESSAGE_KEY, "����˰������ҳ����ʾ�ɹ���");
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰������ҳ����ʾʧ�ܣ�");
	       }
	       // ����Token;
	       saveToken(request);
	       return mapping.findForward("show");
	   }	

		/**
	    * doQueryGr ����˰������ҳ���ѯ
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */
	   public ActionForward doQueryGr(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) {
		   Debug.out("into MfskzsAction's doQueryGr Method....");
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       
	       mfskzsForm.setYhid(userData.yhid);
    	   mfskzsForm.setYhxm(userData.yhmc);
    	   
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	       
    	   mfskzsForm.setTfrq(mfskzsForm.getTfrq());
    	   mfskzsForm.setZsjg(mfskzsForm.getZsjg());
	       String fwhdlxdm=mfskzsForm.getFwhdlxdm();
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        //
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.QUERY);
	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	       
	       try
	       {	    	  
	           
	    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
	    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();	 
	    	   mfskzsForm.setFwhdlxdm(fwhdlxdm);
				//���淵��ֵ
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
			   if(mfskzsForm.getSbbh()== null || mfskzsForm.getSbbh().equals(""))				   
			   {
				   request.setAttribute(Constants.MESSAGE_KEY, "δ��ѯ���ú�ͬ��ŵķ�����Ϣ��");
			   }
			   else
			   {
				   request.setAttribute(Constants.MESSAGE_KEY, "����˰���ѯҳ����ʾ�ɹ���");
			   }
	           
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰���ѯҳ����ʾʧ�ܣ�");
	       }
	       // ����Token;
	       saveToken(request);
	       if(mfskzsForm.getSbbh() == null)
	       {
		       return mapping.findForward("show");	    	   
	       }
	       else
	       {
		       return mapping.findForward("querygr");  
	       }
	   }	   
	   
	   
		/**
	    * doQueryDh ������˰��ѯҳ���ѯ
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */
	   public ActionForward doQueryDh(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) {
		   Debug.out("into MfskzsAction's doQueryDh Method....");
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       
	       mfskzsForm.setYhid(userData.yhid);
    	   mfskzsForm.setYhxm(userData.yhmc);
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	        
    	   mfskzsForm.setTfrq(mfskzsForm.getTfrq());
    	   mfskzsForm.setZsjg(mfskzsForm.getZsjg());
	       
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        //
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.Query_SBXXHISBYZJHM);
	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	       
	       try
	       {	    	  
	           
	    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
	    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();    	   
				//���淵��ֵ
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
				
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰���ѯҳ����ʾ�ɹ���");
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰���ѯҳ����ʾʧ�ܣ�");
	       }
	       // ����Token;
	       saveToken(request);
	       return mapping.findForward("querygr");
	   }		   

	    /**
	    * doPrintQrb ת��ӡ����ԭֵ��ѯ���ȷ�ϱ�
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */	
	public ActionForward doPrintQrb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		   Debug.out("into MfskzsAction's doPrintQrb Method....");
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       
	       mfskzsForm.setYhid(userData.yhid);
    	   mfskzsForm.setYhxm(userData.yhmc);
    	   mfskzsForm.setSbbh(mfskzsForm.getSbbh());
	       
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
 
    	   mfskzsForm.setTfrq(mfskzsForm.getTfrq());
    	   mfskzsForm.setZsjg(mfskzsForm.getZsjg());  	 
    	   
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        //
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.Query_SBXXHISBYSBBH);
	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);

		    try
		    {	    	  
				String qrrq=DataConvert.TS2JksDate(DataConvert.String2Timestamp(DateTimeUtil.getCurrentDate()));           
		    	MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	ArrayList list = mfskzsForm.getSbxxHisList();
		    	StringBuffer sb = new StringBuffer();		    	
				for(int index =0; index < list.size();index ++){
					MfskzsBo bo = (MfskzsBo)list.get(index);
					if(index==0)
					{
						sb.append(bo.getNsrmc_his()+"("+bo.getSfzjlxmc_his()+":"+bo.getSfzjhm_his()+")");
						mfskzsForm.setFwsyqzh_his(bo.getFwsyqzh_his());
						mfskzsForm.setTdfwzldz_his(bo.getTdfwzldz_his());
						mfskzsForm.setJsje_his(bo.getJsje_his());
						/*--modified by huohb,2014-07-22--*/
				    	//��ӡ-�걨����
				    	mfskzsForm.setSbrq_his(bo.getSbrq_his());
				    	//��ӡ-Ӧ��˰��
				    	mfskzsForm.setYnse_his(bo.getYnse_his());
				    	/*--modified end--*/
					}
					if(index >0){
						sb.append("<br>");
						sb.append(bo.getNsrmc_his()+"("+bo.getSfzjlxmc_his()+":"+bo.getSfzjhm_his()+")");
					}					
				}
				mfskzsForm.setNsrmc_his(sb.toString());
				mfskzsForm.setTfrq(qrrq);
				//���淵��ֵ
		        request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		        request.setAttribute(Constants.MESSAGE_KEY, "����ԭֵ��ѯ���ȷ�ϱ��ӡҳ����ʾ�ɹ���");
		    }
		    catch (Exception ex)
		    {
		       ActionErrors errors = new ActionErrors();
		       errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		       saveErrors(request, errors);
		       request.setAttribute(Constants.MESSAGE_KEY, "����ԭֵ��ѯ���ȷ�ϱ��ӡҳ����ʾʧ�ܣ�");
		    }
		    // ����Token;
		    saveToken(request);
		    return mapping.findForward("toPrintQrb");
	}
	
	   
	   
		/**
	    * doConfirm
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */
	   public ActionForward doConfirm(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) {
		   Debug.out("into MfskzsAction's doConfirm Method....");
	        // ��ֹҳ��ˢ��
	        ActionForward aForward = doHandleToken(mapping, request);

	        if (aForward != null)
	        {
	            return aForward;
	        }
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
    	   mfskzsForm.setHtbh1(mfskzsForm.getHtbh());
    	   mfskzsForm.setYhid(userData.yhid);
    	   mfskzsForm.setYhxm(userData.yhmc);
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
  
    	   mfskzsForm.setTfrq(mfskzsForm.getTfrq());
    	   mfskzsForm.setZsjg(mfskzsForm.getZsjg());
    	   mfskzsForm.setHtwsqyrq(mfskzsForm.getHtwsqyrq());
    	   mfskzsForm.setRqcs(mfskzsForm.getRqcs());
	       
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        //
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.Query_SBXXHISBYSBBH);
	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	       
	       try
	       {	    	  
	           
	    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
	    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();    
	    	   
				//���淵��ֵ
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
				
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰������ҳ����ʾ�ɹ���");
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰������ҳ����ʾʧ�ܣ�");
	       }
	       // ����Token;
	       saveToken(request);
	       return mapping.findForward("queryjg");
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
		   Debug.out("into MfskzsAction's doSave Method....");
	        // ��ֹҳ��ˢ��
	        ActionForward aForward = doHandleToken(mapping, request);

	        if (aForward != null)
	        {
	            return aForward;
	        }
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       String columns[] = mfskzsForm.getColumns();
	       mfskzsForm.setDataList(getValuesToList(request, columns));
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	       
	       //��ֹrefresh
	        ActionForward forward = doHandleToken(mapping, request);
	        if (forward != null)
	        {
	            return forward;
	        }
//	    	mfskzsForm.setTfrq(mfskzsForm.getTfrq());
//	    	mfskzsForm.setZsjg(mfskzsForm.getZsjg());   
            
	       
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        //
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.INSERT);
	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	       
	       try
	       {	    	  
	           
	    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
	    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();    	   
				//���淵��ֵ
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
			   if(mfskzsForm.getSbxxHisList().size()==0)
			   {
		           request.setAttribute(Constants.MESSAGE_KEY, "�걨�����Ѿ����ɣ������ظ�������");
			       // ����Token;
			       saveToken(request);
			       return mapping.findForward("show");
			   }else
			   {
		           request.setAttribute(Constants.MESSAGE_KEY, "����˰���������ݱ���ɹ���");
			       // ����Token;
			       saveToken(request);
			       return mapping.findForward("save");
			   }

	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "����˰���������ݱ���ʧ�ܣ�");
		       // ����Token;
		       saveToken(request);
		       return mapping.findForward("show");
	       }

	   }   
	   	
		  /**
		   * ��ǰ̨�������ݰ����������鸳��List
		   * @param request  ǰ̨�������
		   * @param columns  ��������
		   * @return  �����
		   */
		  public ArrayList getValuesToList(HttpServletRequest request,
		                                   String columns[]) {
		    ArrayList list = new ArrayList();
		    if (columns == null) {
		      return list;
		    }
		    if (request.getParameter(columns[0]) != null) {
		      int rows = request.getParameterValues(columns[0]).length;
		      for (int i = 0; i < rows; i++) {
		        HashMap map = new HashMap();
		        for (int j = 0; j < columns.length; j++) {
		          if (request.getParameter(columns[j]) == null) {
		            continue;
		          }
		          map.put(columns[j],
		                  request.getParameterValues(columns[j])[i]);
		        }
		        list.add(map);
		      }
		    }
		    return list;
		  }
		
		/**
	    * dotoWhsbxx ת�걨��Ϣά��ҳ��
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */	   
	public ActionForward dotoWhsbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		   Debug.out("into MfskzsAction's dotoWhsbxx Method....");
		   // ��ȡ�û���¼���ݡ�
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm); 	 
    	   
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        //
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.CLF_MFSBXXWH);
	        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);

		    try
		    {           
		    	MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	mfskzsForm.setZsjg(userData.ssdwmc);
		    	mfskzsForm.setHtbh(dataRes.getHtbh());
		    	//���淵��ֵ
		        request.setAttribute(mapping.getAttribute(), mfskzsForm);
		    	String hasHdxx = mfskzsForm.getHasHdxx();
		    	   if(hasHdxx !=null)
		    	   {
		    		   if(hasHdxx.equals("O"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ!");
			    		   return mapping.findForward("show");
		    		   }
		    		   if(hasHdxx.equals("N"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "���ʵ�Ƿ�����˰����ض�����к˶���");
			    		   return mapping.findForward("show");
		    		   }
		    	   }
		    }
		    catch (Exception ex)
		    {
		       ActionErrors errors = new ActionErrors();
		       errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		       saveErrors(request, errors);
		       request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣά��ҳ����ʾʧ�ܣ�");
		    }
		    // ����Token;
		    saveToken(request);				
		    return mapping.findForward("toWhsbxx");
	}
	
		//ת��Ʊ����	
		public ActionForward dotoFpdk(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
				
			return mapping.findForward("toFpdk");
		}
		
		//ת��˰�걨
		public ActionForward dotoAddSbGr(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
					
			return mapping.findForward("toAddSbGr");
		}
		
		//ת��������Ϣ�ɼ�
		public ActionForward dotoClfxxcj(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
					
			return mapping.findForward("toClfxxcj");
		}	
		
		//ת����˰������
		public ActionForward doBack(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
					
			return doShow(mapping,form,request,response);
		}
		
		//ת��ӡ�˶���	
		public ActionForward doPrintFwhdxx(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
				
			return mapping.findForward("PrintFwhdxx");
		}
		
		/**
		    * QuerySbxx �����걨��Ϣά��ҳ���ѯ
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doQuerySbxx(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's QuerySbxx Method....");
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.Query_SBXXBYHTBH);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {	    	  
		           
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
		    	   mfskzsForm.setHtbh(dataRes.getHtbh());
		    	   //���淵��ֵ
			       request.setAttribute(mapping.getAttribute(), mfskzsForm);

		    	   String hasHdxx = mfskzsForm.getHasHdxx();
		    	   if(hasHdxx !=null)
		    	   {
		    		   if(hasHdxx.equals("O"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ!");
			    		   return mapping.findForward("show");
		    		   }
		    		   if(hasHdxx.equals("N"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "���ʵ�Ƿ�����˰����ض�����к˶���");
			    		   return mapping.findForward("show");
		    		   }
		    	   }

		    	   
		           
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, "�����걨��Ϣά��ҳ����ʾʧ�ܣ�");
		       }
		       // ����Token;
		       saveToken(request);

		       return mapping.findForward("querysbxx");
		   }
		   
			/**
		    * DataSynchronism
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doDataSynchronism(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's DataSynchronism Method....");
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		       
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.DataSynchronism);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {	    	  
		           
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
		    	   
					//���淵��ֵ
		           request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       // ����Token;
		       saveToken(request);
		       return mapping.findForward("dataSynchronism");
		   }
		   
			/**
		    * SaveWsz
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doSaveWsz(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's SaveWsz Method....");
		        // ��ֹҳ��ˢ��
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.CREATE_WSZ);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {	    	  
		           
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
			       
					//���淵��ֵ
		           request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       // ����Token;
		       saveToken(request);
		      
		       if(mfskzsForm.getBz().equals("1"))
		       {
			       return mapping.findForward("viewwsz");		    	   
		       }
		       else{
		    	   return mapping.findForward("wsz");
		       }
		   }

			/**
		    * SaveJks
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doSaveJks(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's SaveJks Method....");
		        // ��ֹҳ��ˢ��
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.CREATE_JKS);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {	    	  
		           
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
					//���淵��ֵ
		           request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       // ����Token;
 				saveToken(request);		       
 				if(mfskzsForm.getBz().equals("1"))
		       {
			       return mapping.findForward("viewjks");		    	   
		       }
		       else{
		    	   return mapping.findForward("jks");

		       }
		   }
		   
			/**
		    * Delete
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doDelete(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's Delete Method....");
		        // ��ֹҳ��ˢ��
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.DELETE);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {	    	  
		           
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
		    	   
					//���淵��ֵ
		           request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       // ����Token;
		       saveToken(request);
		       return mapping.findForward("delete");
		   }
		   
			/**
		    * PrintWsz
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doPrintWsz(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's doPrintWsz Method....");
		        // ��ֹҳ��ˢ��
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.PRINT_WSZ);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {   
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
		    	   
					//���淵��ֵ
		           request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       // ����Token;
		       saveToken(request);
			   return mapping.findForward("viewwsz");		    	   
		   }
		   
			/**
		    * PrintJks
		    * @param mapping ActionMapping
		    * @param form ActionForm
		    * @param request HttpServletRequest
		    * @param response HttpServletResponse
		    * @return ActionForward
		    */
		   public ActionForward doPrintJks(ActionMapping mapping,
		                               ActionForm form,
		                               HttpServletRequest request,
		                               HttpServletResponse response) {
			   Debug.out("into MfskzsAction's doPrintJks Method....");
		        // ��ֹҳ��ˢ��
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // ��ȡ�û���¼���ݡ�
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //��ȡ�����ServletContext�е�processor-map.properties������
		        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
		        //
		        VOPackage vo = new VOPackage();
		        vo.setAction(ActionType.PRINT_JKS);
		        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
		        vo.setUserData(this.getUserData());
		        vo.setData(data);
		       
		       try
		       {   
		    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
		    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
		    	   mfskzsForm.setZsjg(userData.ssdwmc);
		    	   
					//���淵��ֵ
		           request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       catch (Exception ex)
		       {
		           ActionErrors errors = new ActionErrors();
		           errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		           saveErrors(request, errors);
		           request.setAttribute(Constants.MESSAGE_KEY, mfskzsForm.getMessage());
		       }
		       // ����Token;
		       saveToken(request);
			   return mapping.findForward("viewjks");		    	   
		   }
			/**
		    * ��ת��������Ʊ�����ҳ��
		    * @author zhangj
		    */
			public ActionForward dotoClfswjghdxxlr(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) {
				MfskzsForm mfskzsForm = (MfskzsForm)form;
				return new ActionForward("/clfgl/clfswjghdxxlr.do?operationType=Query&htbh="+mfskzsForm.getHtbh());
			}
			/**
			    * ��ѯ��˰֤
			    * @param mapping ActionMapping
			    * @param form ActionForm
			    * @param request HttpServletRequest
			    * @param response HttpServletResponse
			    * @return ActionForward
			    * @author zhangj
			    */
			   public ActionForward doQueryWsz(ActionMapping mapping,
			                               ActionForm form,
			                               HttpServletRequest request,
			                               HttpServletResponse response) {
				   Debug.out("into MfskzsAction's SaveWsz Method....");
			        // ��ֹҳ��ˢ��
			        ActionForward aForward = doHandleToken(mapping, request);

			        if (aForward != null)
			        {
			            return aForward;
			        }
				   // ��ȡ�û���¼���ݡ�
			       HttpSession session = request.getSession(false);
			       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
			       MfskzsForm mfskzsForm = (MfskzsForm)form;
			       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
			       session.setAttribute(mapping.getName(), mfskzsForm);		        
			       
					
					if("true".equals(mfskzsForm.getIsPrintSuccess())){
				        request.setAttribute(mapping.getAttribute(), mfskzsForm);							
				        request.setAttribute(Constants.MESSAGE_KEY, "��ӡ�ɹ���");
						return mapping.findForward("viewwsz");
					}
			       
			       
			        //��ȡ�����ServletContext�е�processor-map.properties������
			        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
			        //
			        VOPackage vo = new VOPackage();
			        vo.setAction(ActionType.QUERY_WSZ);
			        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
			        vo.setUserData(this.getUserData());
			        vo.setData(data);
			       
			       try
			       {	    	  
			           
			    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
			    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();
			    	   mfskzsForm.setZsjg(userData.ssdwmc);
				       
						//���淵��ֵ
			           request.setAttribute(mapping.getAttribute(), mfskzsForm);
						
			           request.setAttribute(Constants.MESSAGE_KEY, "��ӡʧ�ܣ������´�ӡ��");
			       }
			       catch (Exception ex)
			       {
			           ActionErrors errors = new ActionErrors();
			           errors.add(ActionErrors.GLOBAL_ERROR,
			                      new ActionError("err.system"));
			           saveErrors(request, errors);
			           request.setAttribute(Constants.MESSAGE_KEY, "");
			       }
			       // ����Token;
			       saveToken(request);

				   return mapping.findForward("viewwsz");		    	   

			   }	

				/**
			    * doQueryGr ����˰������ҳ���ѯ
			    * @param mapping ActionMapping
			    * @param form ActionForm
			    * @param request HttpServletRequest
			    * @param response HttpServletResponse
			    * @return ActionForward
			    */
			   public ActionForward doQueryFzfGr(ActionMapping mapping,
			                               ActionForm form,
			                               HttpServletRequest request,
			                               HttpServletResponse response) {
				   Debug.out("into MfskzsAction's doQueryGr Method....");
				   // ��ȡ�û���¼���ݡ�
			       HttpSession session = request.getSession(false);
			       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
			       MfskzsForm mfskzsForm = (MfskzsForm)form;
			       
			       mfskzsForm.setYhid(userData.yhid);
		    	   mfskzsForm.setYhxm(userData.yhmc);
		    	   
			       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
			       session.setAttribute(mapping.getName(), mfskzsForm);
			       
		    	   mfskzsForm.setTfrq(mfskzsForm.getTfrq());
		    	   mfskzsForm.setZsjg(mfskzsForm.getZsjg());
			       String fwhdlxdm=mfskzsForm.getFwhdlxdm();
			        //��ȡ�����ServletContext�е�processor-map.properties������
			        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
			        //
			        VOPackage vo = new VOPackage();
			        vo.setAction(ActionType.QUERY);
			        vo.setProcessor(prop.getProperty(new MfskzsBo().getClass().getName()));
			        vo.setUserData(this.getUserData());
			        vo.setData(data);
			       
			       try
			       {	    	  
			           
			    	   MfskzsBo dataRes = (MfskzsBo)QsglProxy.getInstance().process(vo);
			    	   mfskzsForm = (MfskzsForm)dataRes.getFromData();	 
			    	   mfskzsForm.setFwhdlxdm(fwhdlxdm);
						//���淵��ֵ
			          // request.setAttribute(mapping.getAttribute(), mfskzsForm);
//					   if(mfskzsForm.getSbbh()== null || mfskzsForm.getSbbh().equals(""))				   
//					   {
//						   request.setAttribute(Constants.MESSAGE_KEY, "δ��ѯ���ú�ͬ��ŵķ�����Ϣ��");
//					   }
//					   else
//					   {
//						   request.setAttribute(Constants.MESSAGE_KEY, "����˰���ѯҳ����ʾ�ɹ���");
//					   }
			           
			       }
			       catch (Exception ex)
			       {
			           ActionErrors errors = new ActionErrors();
			           errors.add(ActionErrors.GLOBAL_ERROR,
			                      new ActionError("err.system"));
			           saveErrors(request, errors);
			           request.setAttribute(Constants.MESSAGE_KEY, "����˰���ѯҳ����ʾʧ�ܣ�");
			       }
			       // ����Token;
			       saveToken(request);

				       return doConfirm(mapping, mfskzsForm,request,response);  

			   }	   
			   
}
