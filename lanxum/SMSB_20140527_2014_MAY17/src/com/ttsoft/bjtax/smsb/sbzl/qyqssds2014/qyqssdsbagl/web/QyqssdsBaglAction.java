package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.web;

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

public class QyqssdsBaglAction extends SmsbAction {
	/**
	 * ����Ա����
	 */
	private UserData userData = null;

	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
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
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ��������˰��������</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ��������˰��������");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssdsbagl-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		QyqssdsBaglForm qyqssdsBaglForm = (QyqssdsBaglForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsBaglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.processor.QyqssdsBaglProcessor");
		vo.setUserData(userData);

		try {
			qyqssdsBaglForm = (QyqssdsBaglForm) SbzlProxy.getInstance()
					.process(vo);

			// ���������˵�
			QyqssdsActionHelper.initialPageSelectItem(request,
					qyqssdsBaglForm.getFilter_zgswjgList());
			String jmsbajlHtml = QyqssdsActionHelper.boToHtml(null);
			// ���ò�ѯ���
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
			// ����ҳ���ѯ��ҳĬ��ֵ
			qyqssdsBaglForm.setCurrentPage("1");
			qyqssdsBaglForm.setRowsPerPage("10");
			request.setAttribute("ROWS_COUNT", "0");
			request.setAttribute("TOTAL_PAGE", "0");
			request.setAttribute(mapping.getAttribute(), qyqssdsBaglForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {

			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ��ѯ���걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------

		UserData userData = null;
		QyqssdsBaglForm qyqssdsBaglForm = (QyqssdsBaglForm) form;

		// ����ʱ������ѯ��������²�ѯ��
		String returnFlag = request.getParameter("returnFlag");
		if (returnFlag != null && returnFlag.trim().length() > 0 && returnFlag.equals("operateReturn")) {
			this.getFilter(request, qyqssdsBaglForm);
		}
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsBaglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.processor.QyqssdsBaglProcessor");
		vo.setUserData(userData);
		try {
			List list = (List) SbzlProxy.getInstance().process(vo);

			int rowsCount = list.size();// �ܼ�¼��
			String rowsPerPage = qyqssdsBaglForm.getRowsPerPage();// ÿҳ��¼��

			int totalPage = rowsCount / Integer.parseInt(rowsPerPage)
					+ (rowsCount % Integer.parseInt(rowsPerPage) > 0 ? 1 : 0);
			// ��ǰҳ �������ĵ�ǰҳС����ҳ��ʱȡ��ҳ����
			int currentPage = (Integer.parseInt(qyqssdsBaglForm
					.getCurrentPage()) > totalPage && totalPage > 0) ? totalPage
					: Integer.parseInt(qyqssdsBaglForm.getCurrentPage());
			qyqssdsBaglForm.setCurrentPage(String.valueOf(currentPage));

			request.setAttribute("ROWS_COUNT", String.valueOf(rowsCount));
			request.setAttribute("TOTAL_PAGE", String.valueOf(totalPage));

			String jmsbajlHtml = QyqssdsActionHelper.boToHtml(getSubList(list,currentPage, Integer.parseInt(rowsPerPage)));
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);

			request.setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBAGLFORM,qyqssdsBaglForm);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Query");
	}
	
	/**
	 * ���.�鿴ҳ�����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaglForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doOperate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QyqssdsBaglForm qyqssdsBaglForm = (QyqssdsBaglForm) form;
		this.setFilter(request, qyqssdsBaglForm);
		int czlx=Integer.parseInt(qyqssdsBaglForm.getCzlx());	
		userData = this.getUserData(request);
		String jsjdm = qyqssdsBaglForm.getJsjdm();
		return new ActionForward(QyqssdsActionHelper.getForwardPath(jsjdm,String.valueOf(czlx)));
	}
	/**
	 * ���÷�ҳ
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
	//�����ѯ����
    private void setFilter(HttpServletRequest request,QyqssdsBaglForm  form){
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
	 //���ò�ѯ����
    private void getFilter(HttpServletRequest request,QyqssdsBaglForm form){
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
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = this.doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		UserData userData = null;
		QyqssdsBaglForm qyqssdsBaglForm = (QyqssdsBaglForm) form;
		this.setFilter(request, qyqssdsBaglForm);
		userData = this.getUserData(request);
	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_ADDACTION);
		vo.setData(qyqssdsBaglForm);
	
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.processor.QyqssdsBaglProcessor");	
		vo.setUserData(userData);
		//�жϸ���ҵ�Ƿ���Ҫ����
		if(CodeConstant.SMSB_QYQSSDS2014_WXBA.equals(QyqssdsActionHelper.getSfwxjsba(qyqssdsBaglForm.getFilter_jsjdm()).get(CodeConstant.SMSB_QYQSSDS2014_SFWXJXBA))){
			throw new ApplicationException("����ҵ����Ҫ����ҵ��������˰������");
		}

		
		
		// �����ҵ�Ǽǻ�����Ϣ
		SWDJJBSJ djsj = null;
		try {
			QysdsUtil.getAlertStrWhenAdd(qyqssdsBaglForm.getFilter_jsjdm(),userData);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
						
		
		CheckBean checkBean = new CheckBean();	
        checkBean.setJsjdm(qyqssdsBaglForm.getFilter_jsjdm());
    	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	checkBean.setSksj(sdf.format(sbrq));
    	//checkBean.setSkssrqq(skssrqq)
    	//checkBean.setSkssrqz(skssrqz)
		if(!QyqssdsUtil2014.checkJd(checkBean,"6"))
		{
			throw new ApplicationException("����ҵ������ҵ����˰Ӧ����������Ҫ����ҵ��������˰������");
		}
   	
		try {
			
			qyqssdsBaglForm = (QyqssdsBaglForm) SbzlProxy.getInstance().process(vo);	
			if(qyqssdsBaglForm.getIsExistedBa()==true){
				//throw ExceptionUtil.getBaseException(new Exception(),"������Ϣ�Ѿ�����");
				throw new ApplicationException("��ҵ��������˰������Ϣ�Ѿ����ڣ������ظ���ӣ�");
			}
			
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			
			throw ExceptionUtil.getBaseException(ex);
		}		
		//int czlx=Integer.parseInt(qyqssdsBaglForm.getCzlx());
		return new ActionForward(QyqssdsActionHelper.getForwardPath(qyqssdsBaglForm.getFilter_jsjdm(),new Integer(CodeConstant.QYQSSDSBAGL_CZLX_ADD).toString()));
	}
    
    public ActionForward doView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		QyqssdsBaglForm qyqssdsBaglForm = (QyqssdsBaglForm) form;
		this.setFilter(request, qyqssdsBaglForm);
		return new ActionForward(QyqssdsActionHelper.getForwardPath(qyqssdsBaglForm.getJsjdm(),qyqssdsBaglForm.getCzlx()));
    
    }  
    
    

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		
		UserData userData = null;
		QyqssdsBaglForm qyqssdsBaglForm = (QyqssdsBaglForm) form;
		
		// ����ʱ������ѯ��������²�ѯ��
		String returnFlag = request.getParameter("returnFlag");
		if (returnFlag != null && returnFlag.trim().length() > 0 && returnFlag.equals("operateReturn")) {
			this.getFilter(request, qyqssdsBaglForm);
		}
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(qyqssdsBaglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.processor.QyqssdsBaglProcessor");
		vo.setUserData(userData);
		try {
			
			qyqssdsBaglForm= (QyqssdsBaglForm) SbzlProxy.getInstance().process(vo);
			
			//request.setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBAGLFORM,qyqssdsBaglForm);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return doQuery(mapping, form,request, response);
	}
    
    
}
