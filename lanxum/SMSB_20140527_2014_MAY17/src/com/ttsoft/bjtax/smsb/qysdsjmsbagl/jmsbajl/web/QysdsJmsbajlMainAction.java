package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.web.Basx01Form;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.QueryFilterVo;


import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税减免税备案管理
 * </p>
 * 
 * @author 开发二部：刘超
 * @version 1.1
 */
public class QysdsJmsbajlMainAction extends SmsbAction {
	/**
	 * 操作员数据
	 */
	private UserData userData = null;
	
	/**
	 * 公共的前置处理方法，每次进入页面都会被调用<BR>
	 * 设置页面显示时使用的导航信息和标题
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QysdsnbForm
	 * @param httpServletRequest
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	
	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
	
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
		.setAttribute(
				CodeConstant.SMSB_HEADER_POSITION,
		"<font color=\"#A4B9C6\">综合服务管理信息系统</font>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"企业所得税年度纳税申报");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
	}
	
	
	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qysdsJmsbajlMainForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
		vo.setUserData(userData);
	
	
		try {
			qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) SbzlProxy.getInstance().process(vo);
			//设置默认新增域备案年度为去年
			SimpleDateFormat sf=new SimpleDateFormat("yyyy");
			int band = Integer.parseInt(sf.format(new Date()))-1;
			qysdsJmsbajlMainForm.setAdd_band(String.valueOf(band));
			//设置下拉菜单
			List zgswjgList=qysdsJmsbajlMainForm.getFilter_zgswjgList();
			
			ActionHelper.initialPageSelectItem(request,qysdsJmsbajlMainForm.getFilter_jmsbasxList(),qysdsJmsbajlMainForm.getFilter_zgswjgList());
			String jmsbajlHtml=ActionHelper.boToHtml(null);
			//设置查询结果
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
			//设置页面查询翻页默认值
			qysdsJmsbajlMainForm.setCurrentPage("1");
			qysdsJmsbajlMainForm.setRowsPerPage("10");
			request.setAttribute("ROWS_COUNT","0");
			request.setAttribute("TOTAL_PAGE","0");
			request.setAttribute(mapping.getAttribute(), qysdsJmsbajlMainForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}	
	
	
	
	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	
	public ActionForward doShowZfbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qysdsJmsbajlMainForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
		vo.setUserData(userData);
	
	
		try {
			qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) SbzlProxy.getInstance().process(vo);
			//设置默认新增域备案年度为去年
			SimpleDateFormat sf=new SimpleDateFormat("yyyy");
			int band = Integer.parseInt(sf.format(new Date()))-1;
			qysdsJmsbajlMainForm.setAdd_band(String.valueOf(band));
			//设置下拉菜单
			List zgswjgList=qysdsJmsbajlMainForm.getFilter_zgswjgList();
			
			ActionHelper.initialPageSelectItem(request,qysdsJmsbajlMainForm.getFilter_jmsbasxList(),qysdsJmsbajlMainForm.getFilter_zgswjgList());
			String jmsbajlHtml=ActionHelper.boToHtmlZfbg(null);
			//设置查询结果
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
			//设置页面查询翻页默认值
			qysdsJmsbajlMainForm.setCurrentPage("1");
			qysdsJmsbajlMainForm.setRowsPerPage("10");
			request.setAttribute("ROWS_COUNT","0");
			request.setAttribute("TOTAL_PAGE","0");
			request.setAttribute(mapping.getAttribute(), qysdsJmsbajlMainForm);
			return mapping.findForward("ShowZfbg");
		} catch (Exception ex) {
			
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}	
	
	
	
	/**
	 * 新增备案事项
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		try {
			QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
			//设置返回查询条件
			this.setFilterByAdd(request, qysdsJmsbajlMainForm);
			
			userData = this.getUserData(request);
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_SAVEACTION);
			vo.setData(qysdsJmsbajlMainForm);
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
			vo.setUserData(userData);
			qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm)SbzlProxy.getInstance().process(vo);
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSJMSBAJLFORM,qysdsJmsbajlMainForm);
			String jmbasxdm = qysdsJmsbajlMainForm.getAdd_jmsbasx();
			String basqwsh = qysdsJmsbajlMainForm.getBasqwsh();
			String basqbh = qysdsJmsbajlMainForm.getBasqbh();
			String czlx = String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_ADD);
			return new ActionForward(ActionHelper.getForwardPath(jmbasxdm,basqwsh,czlx,basqbh));
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		
	}
	
	
	
	
	
	/**
     * 查询已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
//    	-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        
    	UserData userData = null;
    	QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
    	
    	//返回时保留查询结果（重新查询）
    	String returnFlag=request.getParameter("returnFlag");
    	if(returnFlag!=null&&returnFlag.trim().length()>0&&returnFlag.equals("operateReturn")){
			this.getFilter(request, qysdsJmsbajlMainForm);
		}
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(qysdsJmsbajlMainForm);
        vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
        vo.setUserData(userData);
       
        try
        {
        	List list= (List) SbzlProxy.getInstance().process(vo);
        	
        	
        	int rowsCount=list.size();//总记录数
    		String rowsPerPage=qysdsJmsbajlMainForm.getRowsPerPage();//每页记录数		
    		
    		int totalPage=rowsCount/Integer.parseInt(rowsPerPage)+(rowsCount%Integer.parseInt(rowsPerPage)>0?1:0);
    		//当前页 如果传入的当前页小于总页数时取总页数。
    		int currentPage=(Integer.parseInt(qysdsJmsbajlMainForm.getCurrentPage())>totalPage && totalPage>0)?totalPage:Integer.parseInt(qysdsJmsbajlMainForm.getCurrentPage());
    		qysdsJmsbajlMainForm.setCurrentPage(String.valueOf(currentPage));
    		
    		request.setAttribute("ROWS_COUNT",String.valueOf(rowsCount));
    		request.setAttribute("TOTAL_PAGE",String.valueOf(totalPage));
        	
        	String jmsbajlHtml=ActionHelper.boToHtml(getSubList(list,currentPage,Integer.parseInt(rowsPerPage)));
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
            
            request.setAttribute(CodeConstant.SESSIONKEY_QYSDSJMSBAJLFORM,qysdsJmsbajlMainForm);
        }
        catch (Exception ex)
        {	
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }
    
  
	/**
     * 查询已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doQueryZfbg (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
//    	-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        
    	UserData userData = null;
    	QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
    	
    	//返回时保留查询结果（重新查询）
    	String returnFlag=request.getParameter("returnFlag");
    	if(returnFlag!=null&&returnFlag.trim().length()>0&&returnFlag.equals("operateReturn")){
			this.getFilter(request, qysdsJmsbajlMainForm);
		}
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYZFBG);
        vo.setData(qysdsJmsbajlMainForm);
        vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
        vo.setUserData(userData);
       
        try
        {
        	List list= (List) SbzlProxy.getInstance().process(vo);
        	
        	//处理查询结果分页////////////////////////////////////////////////////////////////
        	int rowsCount=list.size();//总记录数
    		String rowsPerPage=qysdsJmsbajlMainForm.getRowsPerPage();//每页记录数		    		
    		int totalPage=rowsCount/Integer.parseInt(rowsPerPage)+(rowsCount%Integer.parseInt(rowsPerPage)>0?1:0);
    		//当前页 如果传入的当前页小于总页数时取总页数。
    		int currentPage=(Integer.parseInt(qysdsJmsbajlMainForm.getCurrentPage())>totalPage && totalPage>0)?totalPage:Integer.parseInt(qysdsJmsbajlMainForm.getCurrentPage());
    		qysdsJmsbajlMainForm.setCurrentPage(String.valueOf(currentPage));    		
    		request.setAttribute("ROWS_COUNT",String.valueOf(rowsCount));
    		request.setAttribute("TOTAL_PAGE",String.valueOf(totalPage));
        	///////////////////////////////////////////////////////////////////////////////    
    		
    		
        	String jmsbajlHtml=ActionHelper.boToHtmlZfbg(getSubList(list,currentPage,Integer.parseInt(rowsPerPage)));
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
            
            request.setAttribute(CodeConstant.SESSIONKEY_QYSDSJMSBAJLFORM,qysdsJmsbajlMainForm);
        }
        catch (Exception ex)
        {	
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("QueryZfbg");
    }    
    
	/**
	 * 修改.审核.查看页面操作
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doOperate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
		this.setFilter(request, qysdsJmsbajlMainForm);
		int czlx=Integer.parseInt(qysdsJmsbajlMainForm.getCzlx());	
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setData(qysdsJmsbajlMainForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
		//如果操作类型为审核，则需要对申请状态进行校验
		if(czlx==CodeConstant.QYSDSJMSBAJL_CZLX_CHECK){
			vo.setAction(CodeConstant.QYSDSJMSBAJL_BEFORCHECK);
			try {
				SbzlProxy.getInstance().process(vo);
				request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSJMSBAJLFORM,qysdsJmsbajlMainForm);	
			} catch (Exception ex) {
				// 系统捕捉异常，根据异常类型抛出
				throw ExceptionUtil.getBaseException(ex);
			}
		}
		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
		try {
			qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm)SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		
		String jmbasxdm = qysdsJmsbajlMainForm.getJmbasxdm();
		String basqwsh = qysdsJmsbajlMainForm.getBasqwsh();
		String basqbh = qysdsJmsbajlMainForm.getBasqbh();
		return new ActionForward(ActionHelper.getForwardPath(jmbasxdm,basqwsh,String.valueOf(czlx),basqbh));
	}	
	
	
    private  List getSubList(List list,int pageNumber,int rowsPerPage){
	   	 int beginIndex=rowsPerPage*(pageNumber-1);
	   	 int endIndex=rowsPerPage*pageNumber;   
	   	 beginIndex=(beginIndex>list.size())?1:beginIndex;
	   	 endIndex=(endIndex>list.size())?list.size():endIndex;
	   	 return list.subList(beginIndex,endIndex);    	 
	}
    //设置新增
    private void setFilterByAdd(HttpServletRequest request,QysdsJmsbajlMainForm form){
    	QueryFilterVo vo=new QueryFilterVo();
    	vo.setFilter_jsjdm(form.getAdd_jsjdm());    	
    	vo.setFilter_band(form.getAdd_band());
    	vo.setFilter_sqlx("1");
    	vo.setFilter_sqzt("'4'");
    	vo.setFilter_jmsbasx(form.getAdd_jmsbasx());
    	vo.setBand(form.getAdd_band());
    	vo.setCurrentPage("1");
    	vo.setRowsPerPage("10");
    	
    	request.getSession().setAttribute("RETURN_FILTER", vo);
    	
    }
    //保存查询条件
    private void setFilter(HttpServletRequest request,QysdsJmsbajlMainForm form){
    	QueryFilterVo vo=new QueryFilterVo();
    	vo.setFilter_jsjdm(form.getFilter_jsjdm());
    	vo.setFilter_nsrmc(form.getFilter_nsrmc());
    	vo.setFilter_band(form.getFilter_band());
    	vo.setFilter_sqlx(form.getFilter_sqlx());
    	vo.setFilter_sqzt(form.getFilter_sqzt());
    	vo.setFilter_zgswjg(form.getFilter_zgswjg());
    	vo.setFilter_jmsbasx(form.getFilter_jmsbasx());
    	vo.setFilter_basqbh(form.getFilter_basqbh());
    	vo.setBand(form.getAdd_band());
    	vo.setCurrentPage(form.getCurrentPage());
    	vo.setRowsPerPage(form.getRowsPerPage());
    	request.getSession().setAttribute("RETURN_FILTER", vo);
    	
    }
    //重置查询条件
    private void getFilter(HttpServletRequest request,QysdsJmsbajlMainForm form){
    	QueryFilterVo vo=(QueryFilterVo)request.getSession().getAttribute("RETURN_FILTER");
    	form.setFilter_jsjdm(vo.getFilter_jsjdm());
    	form.setFilter_nsrmc(vo.getFilter_nsrmc());
    	form.setFilter_band(vo.getFilter_band());
    	form.setFilter_sqlx(vo.getFilter_sqlx());
    	form.setFilter_sqzt(vo.getFilter_sqzt());
    	form.setFilter_zgswjg(vo.getFilter_zgswjg());
    	form.setFilter_jmsbasx(vo.getFilter_jmsbasx());
    	form.setFilter_basqbh(vo.getFilter_basqbh());
    	form.setAdd_band(vo.getBand());
    	form.setCurrentPage(vo.getCurrentPage());
    	form.setRowsPerPage(vo.getRowsPerPage());
    }
}
