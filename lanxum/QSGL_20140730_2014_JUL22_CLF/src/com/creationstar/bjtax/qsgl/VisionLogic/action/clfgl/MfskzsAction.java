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
 * Title:卖方税款征收ACTION
 * </p>
 * <p>
 * Description: action
 * </p>
 * 
 * @author 张一军
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
		   // 获取用户登录数据。
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	       
	        //获取存放在ServletContext中的processor-map.properties的数据
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
				//保存返回值
//	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
//				
//	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款征收页面显示成功！");
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款征收页面显示失败！");
	       }
	       // 保存Token;
	       saveToken(request);
	       return mapping.findForward("show");
	   }	

		/**
	    * doQueryGr 卖方税款征收页面查询
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
		   // 获取用户登录数据。
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
	        //获取存放在ServletContext中的processor-map.properties的数据
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
				//保存返回值
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
			   if(mfskzsForm.getSbbh()== null || mfskzsForm.getSbbh().equals(""))				   
			   {
				   request.setAttribute(Constants.MESSAGE_KEY, "未查询到该合同编号的房屋信息！");
			   }
			   else
			   {
				   request.setAttribute(Constants.MESSAGE_KEY, "卖方税款查询页面显示成功！");
			   }
	           
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款查询页面显示失败！");
	       }
	       // 保存Token;
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
	    * doQueryDh 卖方契税查询页面查询
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
		   // 获取用户登录数据。
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       
	       mfskzsForm.setYhid(userData.yhid);
    	   mfskzsForm.setYhxm(userData.yhmc);
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	        
    	   mfskzsForm.setTfrq(mfskzsForm.getTfrq());
    	   mfskzsForm.setZsjg(mfskzsForm.getZsjg());
	       
	        //获取存放在ServletContext中的processor-map.properties的数据
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
				//保存返回值
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
				
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款查询页面显示成功！");
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款查询页面显示失败！");
	       }
	       // 保存Token;
	       saveToken(request);
	       return mapping.findForward("querygr");
	   }		   

	    /**
	    * doPrintQrb 转打印房产原值查询结果确认表
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */	
	public ActionForward doPrintQrb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		   Debug.out("into MfskzsAction's doPrintQrb Method....");
		   // 获取用户登录数据。
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
    	   
	        //获取存放在ServletContext中的processor-map.properties的数据
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
				    	//打印-申报日期
				    	mfskzsForm.setSbrq_his(bo.getSbrq_his());
				    	//打印-应纳税额
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
				//保存返回值
		        request.setAttribute(mapping.getAttribute(), mfskzsForm);
					
		        request.setAttribute(Constants.MESSAGE_KEY, "房产原值查询结果确认表打印页面显示成功！");
		    }
		    catch (Exception ex)
		    {
		       ActionErrors errors = new ActionErrors();
		       errors.add(ActionErrors.GLOBAL_ERROR,
		                      new ActionError("err.system"));
		       saveErrors(request, errors);
		       request.setAttribute(Constants.MESSAGE_KEY, "房产原值查询结果确认表打印页面显示失败！");
		    }
		    // 保存Token;
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
	        // 禁止页面刷新
	        ActionForward aForward = doHandleToken(mapping, request);

	        if (aForward != null)
	        {
	            return aForward;
	        }
		   // 获取用户登录数据。
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
	       
	        //获取存放在ServletContext中的processor-map.properties的数据
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
	    	   
				//保存返回值
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
				
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款征收页面显示成功！");
	       }
	       catch (Exception ex)
	       {
	           ActionErrors errors = new ActionErrors();
	           errors.add(ActionErrors.GLOBAL_ERROR,
	                      new ActionError("err.system"));
	           saveErrors(request, errors);
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款征收页面显示失败！");
	       }
	       // 保存Token;
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
	        // 禁止页面刷新
	        ActionForward aForward = doHandleToken(mapping, request);

	        if (aForward != null)
	        {
	            return aForward;
	        }
		   // 获取用户登录数据。
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       String columns[] = mfskzsForm.getColumns();
	       mfskzsForm.setDataList(getValuesToList(request, columns));
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm);
	       
	       //防止refresh
	        ActionForward forward = doHandleToken(mapping, request);
	        if (forward != null)
	        {
	            return forward;
	        }
//	    	mfskzsForm.setTfrq(mfskzsForm.getTfrq());
//	    	mfskzsForm.setZsjg(mfskzsForm.getZsjg());   
            
	       
	        //获取存放在ServletContext中的processor-map.properties的数据
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
				//保存返回值
	           request.setAttribute(mapping.getAttribute(), mfskzsForm);
			   if(mfskzsForm.getSbxxHisList().size()==0)
			   {
		           request.setAttribute(Constants.MESSAGE_KEY, "申报数据已经生成，不能重复操作！");
			       // 保存Token;
			       saveToken(request);
			       return mapping.findForward("show");
			   }else
			   {
		           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款征收数据保存成功！");
			       // 保存Token;
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
	           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款征收数据保存失败！");
		       // 保存Token;
		       saveToken(request);
		       return mapping.findForward("show");
	       }

	   }   
	   	
		  /**
		   * 将前台请求数据按照名称数组赋给List
		   * @param request  前台请求对象
		   * @param columns  请求数组
		   * @return  结果集
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
	    * dotoWhsbxx 转申报信息维护页面
	    * @param mapping ActionMapping
	    * @param form ActionForm
	    * @param request HttpServletRequest
	    * @param response HttpServletResponse
	    * @return ActionForward
	    */	   
	public ActionForward dotoWhsbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		   Debug.out("into MfskzsAction's dotoWhsbxx Method....");
		   // 获取用户登录数据。
	       HttpSession session = request.getSession(false);
	       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
	       MfskzsForm mfskzsForm = (MfskzsForm)form;
	       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
	       session.setAttribute(mapping.getName(), mfskzsForm); 	 
    	   
	        //获取存放在ServletContext中的processor-map.properties的数据
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
		    	//保存返回值
		        request.setAttribute(mapping.getAttribute(), mfskzsForm);
		    	String hasHdxx = mfskzsForm.getHasHdxx();
		    	   if(hasHdxx !=null)
		    	   {
		    		   if(hasHdxx.equals("O"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应房屋采集信息!");
			    		   return mapping.findForward("show");
		    		   }
		    		   if(hasHdxx.equals("N"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "请核实是否已由税务机关对其进行核定！");
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
		       request.setAttribute(Constants.MESSAGE_KEY, "申报信息维护页面显示失败！");
		    }
		    // 保存Token;
		    saveToken(request);				
		    return mapping.findForward("toWhsbxx");
	}
	
		//转发票代开	
		public ActionForward dotoFpdk(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
				
			return mapping.findForward("toFpdk");
		}
		
		//转契税申报
		public ActionForward dotoAddSbGr(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
					
			return mapping.findForward("toAddSbGr");
		}
		
		//转存量房信息采集
		public ActionForward dotoClfxxcj(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
					
			return mapping.findForward("toClfxxcj");
		}	
		
		//转卖方税款征收
		public ActionForward doBack(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
					
			return doShow(mapping,form,request,response);
		}
		
		//转打印核定表	
		public ActionForward doPrintFwhdxx(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
				
			return mapping.findForward("PrintFwhdxx");
		}
		
		/**
		    * QuerySbxx 卖方申报信息维护页面查询
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
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		        //获取存放在ServletContext中的processor-map.properties的数据
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
		    	   //保存返回值
			       request.setAttribute(mapping.getAttribute(), mfskzsForm);

		    	   String hasHdxx = mfskzsForm.getHasHdxx();
		    	   if(hasHdxx !=null)
		    	   {
		    		   if(hasHdxx.equals("O"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应房屋采集信息!");
			    		   return mapping.findForward("show");
		    		   }
		    		   if(hasHdxx.equals("N"))
		    		   {
		    			   request.setAttribute(Constants.MESSAGE_KEY, "请核实是否已由税务机关对其进行核定！");
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
		           request.setAttribute(Constants.MESSAGE_KEY, "卖方申报信息维护页面显示失败！");
		       }
		       // 保存Token;
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
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		       
		        //获取存放在ServletContext中的processor-map.properties的数据
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
		    	   
					//保存返回值
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
		       // 保存Token;
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
		        // 禁止页面刷新
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //获取存放在ServletContext中的processor-map.properties的数据
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
			       
					//保存返回值
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
		       // 保存Token;
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
		        // 禁止页面刷新
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //获取存放在ServletContext中的processor-map.properties的数据
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
					//保存返回值
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
		       // 保存Token;
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
		        // 禁止页面刷新
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //获取存放在ServletContext中的processor-map.properties的数据
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
		    	   
					//保存返回值
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
		       // 保存Token;
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
		        // 禁止页面刷新
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //获取存放在ServletContext中的processor-map.properties的数据
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
		    	   
					//保存返回值
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
		       // 保存Token;
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
		        // 禁止页面刷新
		        ActionForward aForward = doHandleToken(mapping, request);

		        if (aForward != null)
		        {
		            return aForward;
		        }
			   // 获取用户登录数据。
		       HttpSession session = request.getSession(false);
		       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		       MfskzsForm mfskzsForm = (MfskzsForm)form;
		       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
		       session.setAttribute(mapping.getName(), mfskzsForm);		        
		       
		        //获取存放在ServletContext中的processor-map.properties的数据
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
		    	   
					//保存返回值
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
		       // 保存Token;
		       saveToken(request);
			   return mapping.findForward("viewjks");		    	   
		   }
			/**
		    * 跳转到代开发票申请表页面
		    * @author zhangj
		    */
			public ActionForward dotoClfswjghdxxlr(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) {
				MfskzsForm mfskzsForm = (MfskzsForm)form;
				return new ActionForward("/clfgl/clfswjghdxxlr.do?operationType=Query&htbh="+mfskzsForm.getHtbh());
			}
			/**
			    * 查询完税证
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
			        // 禁止页面刷新
			        ActionForward aForward = doHandleToken(mapping, request);

			        if (aForward != null)
			        {
			            return aForward;
			        }
				   // 获取用户登录数据。
			       HttpSession session = request.getSession(false);
			       UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
			       MfskzsForm mfskzsForm = (MfskzsForm)form;
			       MfskzsBo data = (MfskzsBo)mfskzsForm.getData();
			       session.setAttribute(mapping.getName(), mfskzsForm);		        
			       
					
					if("true".equals(mfskzsForm.getIsPrintSuccess())){
				        request.setAttribute(mapping.getAttribute(), mfskzsForm);							
				        request.setAttribute(Constants.MESSAGE_KEY, "打印成功！");
						return mapping.findForward("viewwsz");
					}
			       
			       
			        //获取存放在ServletContext中的processor-map.properties的数据
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
				       
						//保存返回值
			           request.setAttribute(mapping.getAttribute(), mfskzsForm);
						
			           request.setAttribute(Constants.MESSAGE_KEY, "打印失败，请重新打印！");
			       }
			       catch (Exception ex)
			       {
			           ActionErrors errors = new ActionErrors();
			           errors.add(ActionErrors.GLOBAL_ERROR,
			                      new ActionError("err.system"));
			           saveErrors(request, errors);
			           request.setAttribute(Constants.MESSAGE_KEY, "");
			       }
			       // 保存Token;
			       saveToken(request);

				   return mapping.findForward("viewwsz");		    	   

			   }	

				/**
			    * doQueryGr 卖方税款征收页面查询
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
				   // 获取用户登录数据。
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
			        //获取存放在ServletContext中的processor-map.properties的数据
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
						//保存返回值
			          // request.setAttribute(mapping.getAttribute(), mfskzsForm);
//					   if(mfskzsForm.getSbbh()== null || mfskzsForm.getSbbh().equals(""))				   
//					   {
//						   request.setAttribute(Constants.MESSAGE_KEY, "未查询到该合同编号的房屋信息！");
//					   }
//					   else
//					   {
//						   request.setAttribute(Constants.MESSAGE_KEY, "卖方税款查询页面显示成功！");
//					   }
			           
			       }
			       catch (Exception ex)
			       {
			           ActionErrors errors = new ActionErrors();
			           errors.add(ActionErrors.GLOBAL_ERROR,
			                      new ActionError("err.system"));
			           saveErrors(request, errors);
			           request.setAttribute(Constants.MESSAGE_KEY, "卖方税款查询页面显示失败！");
			       }
			       // 保存Token;
			       saveToken(request);

				       return doConfirm(mapping, mfskzsForm,request,response);  

			   }	   
			   
}
