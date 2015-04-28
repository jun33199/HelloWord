package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.vo.QyqssdsQueryFilterVo;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsWxBaglAction extends SmsbAction {
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">税费管理</font>&gt;企业所得税不需清算备案管理</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业所得税不需清算备案管理");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssdsbagl-000.htm");

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

		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsWxBaglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.processor.QyqssdsWxBaglProcessor");
		vo.setUserData(userData);

		try {
			qyqssdsWxBaglForm = (QyqssdsWxBaglForm) SbzlProxy.getInstance()
					.process(vo);

			// 设置下拉菜单
			QyqssdsActionHelper.initialPageSelectItem(request,
					qyqssdsWxBaglForm.getFilter_zgswjgList());
			String jmsbajlHtml = QyqssdsActionHelper.creatHtml(QyqssdsActionHelper.QYQSSDSWXBA_QUERY_HTML_HEAD,null);
			// 设置查询结果
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
			// 设置页面查询翻页默认值
			qyqssdsWxBaglForm.setCurrentPage("1");
			qyqssdsWxBaglForm.setRowsPerPage("10");
			request.setAttribute("ROWS_COUNT", "0");
			request.setAttribute("TOTAL_PAGE", "0");
			request.setAttribute(mapping.getAttribute(), qyqssdsWxBaglForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {

			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 查询已申报数据
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

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------

		UserData userData = null;
		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) form;

		// 返回时保留查询结果（重新查询）
		String returnFlag = request.getParameter("returnFlag");
		if (returnFlag != null && returnFlag.trim().length() > 0 && returnFlag.equals("operateReturn")) {
			this.getFilter(request, qyqssdsWxBaglForm);
		}
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsWxBaglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.processor.QyqssdsWxBaglProcessor");
		vo.setUserData(userData);
		try {
			List list = (List) SbzlProxy.getInstance().process(vo);

			int rowsCount = list.size();// 总记录数
			String rowsPerPage = qyqssdsWxBaglForm.getRowsPerPage();// 每页记录数

			int totalPage = rowsCount / Integer.parseInt(rowsPerPage)
					+ (rowsCount % Integer.parseInt(rowsPerPage) > 0 ? 1 : 0);
			// 当前页 如果传入的当前页小于总页数时取总页数。
			int currentPage = (Integer.parseInt(qyqssdsWxBaglForm
					.getCurrentPage()) > totalPage && totalPage > 0) ? totalPage
					: Integer.parseInt(qyqssdsWxBaglForm.getCurrentPage());
			qyqssdsWxBaglForm.setCurrentPage(String.valueOf(currentPage));

			request.setAttribute("ROWS_COUNT", String.valueOf(rowsCount));
			request.setAttribute("TOTAL_PAGE", String.valueOf(totalPage));

			String jmsbajlHtml = QyqssdsActionHelper.creatHtml(QyqssdsActionHelper.QYQSSDSWXBA_QUERY_HTML_HEAD,getSubList(list,currentPage, Integer.parseInt(rowsPerPage)));
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);

			request.setAttribute(CodeConstant.SESSIONKEY_QYQSSDSWXBAGLFORM,qyqssdsWxBaglForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Query");
	}
	
	/**
	 * 审核.查看页面操作
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsWxBaglForm
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
		
		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) form;
		this.setFilter(request, qyqssdsWxBaglForm);
		int czlx=Integer.parseInt(qyqssdsWxBaglForm.getCzlx());	
		userData = this.getUserData(request);
		String jsjdm = qyqssdsWxBaglForm.getJsjdm();
		return new ActionForward(QyqssdsActionHelper.getForwardPath(jsjdm,String.valueOf(czlx)));
	}
	/**
	 * 设置分页
	 * @param list
	 * @param pageNumber
	 * @param rowsPerPage
	 * @return
	 */
	private  List getSubList(List list,int pageNumber,int rowsPerPage){
		   	 int beginIndex=rowsPerPage*(pageNumber-1);
		   	 int endIndex=rowsPerPage*pageNumber;   
		   	 beginIndex=(beginIndex>list.size())?1:beginIndex;
		   	 endIndex=(endIndex>list.size())?list.size():endIndex;
		   	 return list.subList(beginIndex,endIndex);    	 
	}
	//保存查询条件
    private void setFilter(HttpServletRequest request,QyqssdsWxBaglForm  form){
    	QyqssdsQueryFilterVo vo=new QyqssdsQueryFilterVo();
    	vo.setFilter_jsjdm(form.getFilter_jsjdm());
    	vo.setFilter_nsrmc(form.getFilter_nsrmc());
    	vo.setFilter_band(form.getFilter_band());
    	vo.setFilter_sqlx(form.getFilter_sqlx());
    	vo.setFilter_sqzt(form.getFilter_sqzt());
    	vo.setFilter_zgswjg(form.getFilter_zgswjg());
    	vo.setCurrentPage(form.getCurrentPage());
    	vo.setRowsPerPage(form.getRowsPerPage());
    	request.getSession().setAttribute("QYQSSDS_RETURN_FILTER", vo);
    	
    }
	 //重置查询条件
    private void getFilter(HttpServletRequest request,QyqssdsWxBaglForm form){
    	QyqssdsQueryFilterVo vo=(QyqssdsQueryFilterVo)request.getSession().getAttribute("QYQSSDS_RETURN_FILTER");
    	form.setFilter_jsjdm(vo.getFilter_jsjdm());
    	form.setFilter_nsrmc(vo.getFilter_nsrmc());
    	form.setFilter_band(vo.getFilter_band());
    	form.setFilter_sqlx(vo.getFilter_sqlx());
    	form.setFilter_sqzt(vo.getFilter_sqzt());
    	form.setFilter_zgswjg(vo.getFilter_zgswjg());
    	form.setCurrentPage(vo.getCurrentPage());
    	form.setRowsPerPage(vo.getRowsPerPage());
    }
    
    
    
    public ActionForward doAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = this.doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		UserData userData = null;
		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) form;
		this.setFilter(request, qyqssdsWxBaglForm);
		userData = this.getUserData(request);
	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_ADDACTION);
		vo.setData(qyqssdsWxBaglForm);
	
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.processor.QyqssdsWxBaglProcessor");	
		vo.setUserData(userData);
		
		// 获得企业登记基本信息
		SWDJJBSJ djsj = null;
		try {
			QysdsUtil.getAlertStrWhenAdd(qyqssdsWxBaglForm.getFilter_jsjdm(),userData);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		
		
		CheckBean checkBean = new CheckBean();	
        checkBean.setJsjdm(qyqssdsWxBaglForm.getFilter_jsjdm());
    	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	checkBean.setSksj(sdf.format(sbrq));
    	//checkBean.setSkssrqq(skssrqq)
    	//checkBean.setSkssrqz(skssrqz)
		if(!QyqssdsUtil2014.checkJd(checkBean,"6"))
		{
			throw new ApplicationException("该企业不是企业所得税应征户，不需要做企业所得税清算备案！");
		}
   	
		try {
			
			qyqssdsWxBaglForm = (QyqssdsWxBaglForm) SbzlProxy.getInstance().process(vo);	
			if(qyqssdsWxBaglForm.getIsExistedBa()==true){
				//throw ExceptionUtil.getBaseException(new Exception(),"备案信息已经存在");
				throw new ApplicationException("企业所得税不需清算备案信息已经存在，不能重复添加！");
			}
			
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			
			throw ExceptionUtil.getBaseException(ex);
		}		
		//int czlx=Integer.parseInt(qyqssdsWxBaglForm.getCzlx());
		
		return new ActionForward(QyqssdsActionHelper.getForward(QyqssdsActionHelper.PAGE_QYQSSDS_WXBA_SHOW,qyqssdsWxBaglForm.getFilter_jsjdm()));
	}
    
    public ActionForward doView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) form;
		this.setFilter(request, qyqssdsWxBaglForm);
		return new ActionForward(QyqssdsActionHelper.getForward(QyqssdsActionHelper.PAGE_QYQSSDS_WXBA_SHOW,qyqssdsWxBaglForm.getJsjdm()));
    
    }  
    
    

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		
		UserData userData = null;
		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) form;
		
		// 返回时保留查询结果（重新查询）
		String returnFlag = request.getParameter("returnFlag");
		if (returnFlag != null && returnFlag.trim().length() > 0 && returnFlag.equals("operateReturn")) {
			this.getFilter(request, qyqssdsWxBaglForm);
		}
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(qyqssdsWxBaglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.processor.QyqssdsWxBaglProcessor");
		vo.setUserData(userData);
		try {
			
			qyqssdsWxBaglForm= (QyqssdsWxBaglForm) SbzlProxy.getInstance().process(vo);
			
			//request.setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBAGLFORM,qyqssdsBaglForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return doQuery(mapping, form,request, response);
	}
    
    
}
