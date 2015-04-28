package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.exolab.castor.types.DateTime;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.vo.QyqssdsQueryFilterVo;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsSbglAction extends SmsbAction {
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">税费管理</font>&gt;企业清算所得税申报管理</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业清算所得税申报管理");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssdssbgl-000.htm");

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

		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsSbglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.processor.QyqssdsSbglProcessor");
		vo.setUserData(userData);

		try {
			qyqssdsSbglForm = (QyqssdsSbglForm) SbzlProxy.getInstance()
					.process(vo);

			// 设置下拉菜单
			QyqssdsActionHelper.initialPageSelectItem(request,
					qyqssdsSbglForm.getFilter_zgswjgList());
			String jmsbajlHtml = QyqssdsActionHelper.boToHtml(null);
			// 设置查询结果
			request.getSession(false).setAttribute("QYQSSB_QUERY_HTML", jmsbajlHtml);
			// 设置页面查询翻页默认值
			qyqssdsSbglForm.setCurrentPage("1");
			qyqssdsSbglForm.setRowsPerPage("10");
			request.setAttribute("QYQSSB_ROWS_COUNT", "0");
			request.setAttribute("QYQSSB_TOTAL_PAGE", "0");
			request.setAttribute(mapping.getAttribute(), qyqssdsSbglForm);
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
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		// 返回时保留查询结果（重新查询）
		String returnFlag = request.getParameter("returnFlag");
		if (returnFlag != null && returnFlag.trim().length() > 0 && returnFlag.equals("operateReturn")) {
			this.getFilter(request, qyqssdsSbglForm);
		}
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsSbglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.processor.QyqssdsSbglProcessor");
		vo.setUserData(userData);
		try {
			List list = (List) SbzlProxy.getInstance().process(vo);
			int rowsCount = list.size();// 总记录数
			String rowsPerPage = qyqssdsSbglForm.getRowsPerPage();// 每页记录数
			int totalPage = rowsCount / Integer.parseInt(rowsPerPage)
					+ (rowsCount % Integer.parseInt(rowsPerPage) > 0 ? 1 : 0);
			// 当前页 如果传入的当前页小于总页数时取总页数。
			int currentPage = (Integer.parseInt(qyqssdsSbglForm
					.getCurrentPage()) > totalPage && totalPage > 0) ? totalPage
					: Integer.parseInt(qyqssdsSbglForm.getCurrentPage());
			qyqssdsSbglForm.setCurrentPage(String.valueOf(currentPage));
			request.setAttribute("QYQSSB_ROWS_COUNT", String.valueOf(rowsCount));
			request.setAttribute("QYQSSB_TOTAL_PAGE", String.valueOf(totalPage));

			String jmssbjlHtml = QyqssdsActionHelper.sbboToHtml(getSubList(list,currentPage, Integer.parseInt(rowsPerPage)));
			request.getSession(false).setAttribute("QYQSSB_QUERY_HTML", jmssbjlHtml);

			request.setAttribute(CodeConstant.SESSIONKEY_QYQSSDSSBGLFORM,qyqssdsSbglForm);
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
	 *            QyqssdsBaglForm
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
		
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		this.setFilter(request, qyqssdsSbglForm);
		int czlx=Integer.parseInt(qyqssdsSbglForm.getCzlx());	
		userData = this.getUserData(request);
//		VOPackage vo = new VOPackage();
//		vo.setData(qyqssdsBaglForm);
//		vo.setUserData(userData);
//		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.processor.QyqssdsBaglProcessor");
//		//如果操作类型为审核，则需要对申请状态进行校验
//		if(czlx==CodeConstant.QYQSSDSBAGL_CZLX_CHECK){
//			vo.setAction(CodeConstant.QYQSSDSBAGL_BEFORCHECK);
//			try {
//				SbzlProxy.getInstance().process(vo);
//				request.getSession(false).setAttribute(CodeConstant.SESSION_KEY_QYQSSDSSBGLFORM,qyqssdsBaglForm);	
//			} catch (Exception ex) {
//				// 系统捕捉异常，根据异常类型抛出
//				throw ExceptionUtil.getBaseException(ex);
//			}
//		}
//		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
//		try {
//			qyqssdsBaglForm = (QyqssdsBaglForm)SbzlProxy.getInstance().process(vo);
//		} catch (Exception ex) {
//			// 系统捕捉异常，根据异常类型抛出
//			throw ExceptionUtil.getBaseException(ex);
//		}
		
		String jsjdm = qyqssdsSbglForm.getJsjdm();
		return new ActionForward(QyqssdsActionHelper.getSbForwardPath(jsjdm,String.valueOf(czlx)));
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
    private void setFilter(HttpServletRequest request,QyqssdsSbglForm  form){
    	QyqssdsQueryFilterVo vo=new QyqssdsQueryFilterVo();
    	vo.setFilter_jsjdm(form.getFilter_jsjdm());
    	vo.setFilter_nsrmc(form.getFilter_nsrmc());
    	vo.setFilter_band(form.getFilter_band());
    	vo.setFilter_sqlx(form.getFilter_sqlx());
    	vo.setFilter_sqzt(form.getFilter_sqzt());
    	vo.setFilter_zgswjg(form.getFilter_zgswjg());
    	vo.setCurrentPage(form.getCurrentPage());
    	vo.setRowsPerPage(form.getRowsPerPage());
    	request.getSession().setAttribute("QYQSSDSSBGL_RETURN_FILTER", vo);
    	
    }
	 //重置查询条件
    private void getFilter(HttpServletRequest request,QyqssdsSbglForm form){
    	QyqssdsQueryFilterVo vo=(QyqssdsQueryFilterVo)request.getSession().getAttribute("QYQSSDSSBGL_RETURN_FILTER");
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
		
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		String jsjdm=qyqssdsSbglForm.getFilter_jsjdm();
		qyqssdsSbglForm.setFilter_jsjdm("");
		this.setFilter(request, qyqssdsSbglForm);
		qyqssdsSbglForm.setFilter_jsjdm(jsjdm);
		Map map=QyqssdsActionHelper.getShztbs(qyqssdsSbglForm.getFilter_jsjdm());
		if(map.isEmpty()){
			//throw  ExceptionUtil.getBaseException(new Exception(),"备案不存在！");
			throw new ApplicationException("该企业的企业清算所得税备案信息不存在，不能进行申报");
		}
			
//		userData = this.getUserData(request);
//		CheckBean checkBean = new CheckBean();	
//        checkBean.setJsjdm(qyqssdsSbglForm.getFilter_jsjdm());
//    	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String bashtgrq=map.get(CodeConstant.SMSB_QYQSSDS2014_BASHTGRQ).toString().substring(0,10);
//		bashtgrq=bashtgrq.replaceAll("-", "");
//    	//checkBean.setSksj(sdf.format(sbrq));
//    	checkBean.setSkssrqq(bashtgrq);
//    	checkBean.setSkssrqz(sdf.format(sbrq));
//    	//System.out.println(checkBean.getJsjdm()+checkBean.getSkssrqq()+checkBean.getSkssrqz());
//    	
//		if(!QyqssdsUtil2014.checkJd(checkBean,"5"))
//		{
//			throw new ApplicationException("该企业不是企业所得税应征户，不需要做企业清算所得税申报！");
//		}
		
		String bashztbs=map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString();
		if(!"2".equals(bashztbs)){//清算申报审核状态标示，审核已通过
			throw new ApplicationException("该企业的企业清算所得税备案信息审核未通过,不能进行企业清算所得税申报！");
			//throw ExceptionUtil.getBaseException(new Exception(),"备案审核未通过！");
		}

		String shshztbs=map.get(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS).toString();	
		if(shshztbs==null){
			return new ActionForward(QyqssdsActionHelper.getSbForwardPath(qyqssdsSbglForm.getFilter_jsjdm(),new Integer(CodeConstant.QYQSSDSBAGL_CZLX_ADD).toString()));
		}else if(shshztbs.length()>0){
			//throw ExceptionUtil.getBaseException(new Exception(),"申请已经存在！");
			throw new ApplicationException("该企业的企业清算所得税申请已经存在，不能进行添加！");
		}	
		
		return new ActionForward(QyqssdsActionHelper.getSbForwardPath(qyqssdsSbglForm.getFilter_jsjdm(),new Integer(CodeConstant.QYQSSDSBAGL_CZLX_ADD).toString()));
	}
    
    public ActionForward doView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
    	
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		this.setFilter(request, qyqssdsSbglForm);
		return new ActionForward(QyqssdsActionHelper.getSbForwardPath(qyqssdsSbglForm.getJsjdm(),qyqssdsSbglForm.getCzlx()));
    
    }  
    
    

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		
		// 返回时保留查询结果（重新查询）
		String returnFlag = request.getParameter("returnFlag");
		if (returnFlag != null && returnFlag.trim().length() > 0 && returnFlag.equals("operateReturn")) {
			this.getFilter(request, qyqssdsSbglForm);
		}
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(qyqssdsSbglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.processor.QyqssdsSbglProcessor");
		vo.setUserData(userData);
		try {
			qyqssdsSbglForm= (QyqssdsSbglForm) SbzlProxy.getInstance().process(vo);
			//request.setAttribute(CodeConstant.SESSION_KEY_QYQSSDSSBGLFORM,qyqssdsBaglForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return doQuery(mapping, form,request, response);
	}
    
	/**
     * 返回页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDSSB_QUERY);
	}
}
