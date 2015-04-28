package com.ttsoft.bjtax.smsb.jccswh.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.gzsxexcel.DateTimeUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * 总局减免税代码维护 该功能是对总局减免税项目进行维护
 * 
 * @author tangchangfu 2014-04-22
 * 
 *         注：此功能的菜单在“会计核算》账务处理》基础参数维护》总局减免税代码维护”
 * 
 */
public class ZjjmsdmwhAction extends SmsbAction {
	/**
	 * 设置页面导航路径
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#7C9AAB\">会计核算系统&gt;账务处理&gt;基础参数维护&gt;</font>总局减免税代码维护 ");
		request.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	
	/**
	 * 初始化:
	 * @param cForm
	 * @param userData
	 */
	private void init(ZjjmsdmwhForm cForm,UserData userData){
		
		VOPackage vo = new VOPackage();
		ArrayList szList = new ArrayList();
		ArrayList jmxzdlList = new ArrayList();
		ArrayList jmxzxlList = new ArrayList();
		
		// 执行Processor
		try {
			// 初始化
			vo.setAction(CodeConstant.SMSB_INIT);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
			
			// 调用EJB
			HashMap resMap = (HashMap)ZhsbProxy.getInstance().process(vo);
			
			if(resMap !=null && !resMap.isEmpty()){
				szList = (ArrayList)resMap.get(CodeConstant.SMSB_MAP_KEY_SZ);
				jmxzdlList = (ArrayList)resMap.get(CodeConstant.SMSB_MAP_KEY_JMXZDL);
				jmxzxlList = (ArrayList)resMap.get(CodeConstant.SMSB_MAP_KEY_JMXZXL);
			}
			
			cForm.setSmsmList(szList);
			cForm.setJmxzdlList(jmxzdlList);
			cForm.setJmxzxlList(jmxzxlList);
			
			cForm.setLrrmc(userData.getYhmc());
			cForm.setLrrq(DateTimeUtil.getCurrentDate());
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
		}
	}
	
	
	
	/**
	 * 初始化
	 */
	public ActionForward doShow(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
		
		//通过request.getSession(false).getAttribute("UserData");获得UserDate
        UserData userData = this.getUserData(httpServletRequest);
        
		this.init(form, userData);
		form.setModifyKey_jmslxdm(null);
		
		return actionMapping.findForward("Show");
	}
	
	
	/**
	 * 获得单条
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doShowOne(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
		this.init(form, userData);
		
        /*初始化VO包*/
        VOPackage vo = new VOPackage();//初始化数据传递容器
        vo.setUserData(userData);//设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);//设置Proxy要调用的processor的类名
        vo.setAction(CodeConstant.QUERYONE);//设置后台调用Action值
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (ZjjmsdmwhForm) SbzlProxy.getInstance().process(vo);
            //流转
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
        	this.init(form, userData);
            //系统捕捉异常，根据异常类型抛出
        	ex.printStackTrace();
			//错误消息
        	form.setMessage(ex.getMessage());
        	this.doQuery(actionMapping, form, httpServletRequest, httpServletResponse);
        }
        return actionMapping.findForward("Query");
	}
	
	
	
	/**
	 * 转维护
	 */
	public ActionForward doToWeiHu(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		System.out.println("+++++++++++++++++++转维护++++++++++++++++++++++++++++++++");
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
		this.init(form, userData);
		try{
			
			form.setQuery_lrrqKS(DateTimeUtil.getCurrentDate());
			form.setQuery_lrrqJS(DateTimeUtil.getCurrentDate());
			
			this.doQuery(actionMapping, form, httpServletRequest, httpServletResponse);
			
			
			return actionMapping.findForward("Query");
		}catch(Exception e){
			e.printStackTrace();
			form.setMessage("跳转到维护界面失败");
		}
		return actionMapping.findForward("Show");
	}
	
	
	/**
	 * 执行查询
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doQuery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
        //当前的ActionForm
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
        /*初始化VO包*/
        VOPackage vo = new VOPackage();//初始化数据传递容器
        vo.setUserData(userData);//设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);//设置Proxy要调用的processor的类名
        vo.setAction(CodeConstant.SMSB_QUERYACTION);//设置后台调用Action值
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (ZjjmsdmwhForm) SbzlProxy.getInstance().process(vo);
            System.out.println("+++++++++++++++++（1）"+form.getQueryList_onePage().size());
            //流转
            this.init(form, userData);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            System.out.println("+++++++++++++++++（2）"+form.getQueryList_onePage().size());
            if((form.getQueryList_onePage() == null || form.getQueryList_onePage().size() == 0) &&
            	(form.getActionType() != null && "Query".equals(form.getActionType()))){
            	form.setMessage("没有满足查询条件的结果，请检查查询条件是否正确！");
            }
            //return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
        	ex.printStackTrace();
			//错误消息
        	form.setMessage(ex.getMessage());
        	this.init(form, userData);
        }
        
        return actionMapping.findForward("Query");
	}
	
	
	/**
	 * 执行更新
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doUpdate(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		String returnStr="Show";
        //当前的ActionForm
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
        
        String updateType = form.getUpdateType();
        if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
        	returnStr="Show";
        }else if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType)){
        	returnStr="Query";
        }
        
        
        /*初始化VO包*/
        VOPackage vo = new VOPackage();//初始化数据传递容器
        vo.setUserData(userData);//设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);//设置Proxy要调用的processor的类名
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);//设置后台调用Action值
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            
            //
            form.setMessage("修改信息保存成功!");
            //流转
            this.init(form, userData);
            System.out.println("actionMapping.getAttribute()+++++++++++++++++"+actionMapping.getAttribute());
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            
            //修改有效标志完成后执行查询操作，查询出修改后最新信息
            if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType)){
            	this.doQuery(actionMapping, form, httpServletRequest, httpServletResponse);
            }
            return actionMapping.findForward(returnStr);
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
        	ex.printStackTrace();
			//错误消息
        	form.setMessage(ex.getMessage());
        	this.init(form, userData);

        }
        
        return actionMapping.findForward(returnStr);
	}
	
	/**
	 * 执行增加
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doSave(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		System.out.println("+++++++++++++++++++开始保存数据++++++++++++++++++++++++++++++++");
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) actionForm;
        UserData userData = this.getUserData(httpServletRequest);
        /*初始化VO包*/
        VOPackage vo = new VOPackage();//初始化数据传递容器
        vo.setUserData(userData);//设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);//设置Proxy要调用的processor的类名
        vo.setAction(CodeConstant.SMSB_SAVEACTION);//设置后台调用Action值
        vo.setProcessor(CodeConstant.SMSB_ZJJMSDMWH_PROCESSOR);
        try {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            
            //初始化
    		//this.init(form, userData);
            form.setMessage("保存成功!");
            //流转
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            
            this.doToWeiHu(actionMapping, form, httpServletRequest, httpServletResponse);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
        	//系统捕捉异常，根据异常类型抛出
        	ex.printStackTrace();
            //初始化
    		this.init(form, userData);
			//错误消息
        	form.setMessage(ex.getMessage());
        	form.setModifyKey_jmslxdm(null);
        }
        return actionMapping.findForward("Show");
	}
	
	
	
	
	
	

}
