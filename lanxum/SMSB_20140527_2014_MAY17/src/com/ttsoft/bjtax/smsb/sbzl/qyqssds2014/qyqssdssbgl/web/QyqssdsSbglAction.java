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
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ��������˰�걨����</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ��������˰�걨����");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssdssbgl-000.htm");

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

			// ���������˵�
			QyqssdsActionHelper.initialPageSelectItem(request,
					qyqssdsSbglForm.getFilter_zgswjgList());
			String jmsbajlHtml = QyqssdsActionHelper.boToHtml(null);
			// ���ò�ѯ���
			request.getSession(false).setAttribute("QYQSSB_QUERY_HTML", jmsbajlHtml);
			// ����ҳ���ѯ��ҳĬ��ֵ
			qyqssdsSbglForm.setCurrentPage("1");
			qyqssdsSbglForm.setRowsPerPage("10");
			request.setAttribute("QYQSSB_ROWS_COUNT", "0");
			request.setAttribute("QYQSSB_TOTAL_PAGE", "0");
			request.setAttribute(mapping.getAttribute(), qyqssdsSbglForm);
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
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		// ����ʱ������ѯ��������²�ѯ��
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
			int rowsCount = list.size();// �ܼ�¼��
			String rowsPerPage = qyqssdsSbglForm.getRowsPerPage();// ÿҳ��¼��
			int totalPage = rowsCount / Integer.parseInt(rowsPerPage)
					+ (rowsCount % Integer.parseInt(rowsPerPage) > 0 ? 1 : 0);
			// ��ǰҳ �������ĵ�ǰҳС����ҳ��ʱȡ��ҳ����
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
		
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		this.setFilter(request, qyqssdsSbglForm);
		int czlx=Integer.parseInt(qyqssdsSbglForm.getCzlx());	
		userData = this.getUserData(request);
//		VOPackage vo = new VOPackage();
//		vo.setData(qyqssdsBaglForm);
//		vo.setUserData(userData);
//		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.processor.QyqssdsBaglProcessor");
//		//�����������Ϊ��ˣ�����Ҫ������״̬����У��
//		if(czlx==CodeConstant.QYQSSDSBAGL_CZLX_CHECK){
//			vo.setAction(CodeConstant.QYQSSDSBAGL_BEFORCHECK);
//			try {
//				SbzlProxy.getInstance().process(vo);
//				request.getSession(false).setAttribute(CodeConstant.SESSION_KEY_QYQSSDSSBGLFORM,qyqssdsBaglForm);	
//			} catch (Exception ex) {
//				// ϵͳ��׽�쳣�������쳣�����׳�
//				throw ExceptionUtil.getBaseException(ex);
//			}
//		}
//		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
//		try {
//			qyqssdsBaglForm = (QyqssdsBaglForm)SbzlProxy.getInstance().process(vo);
//		} catch (Exception ex) {
//			// ϵͳ��׽�쳣�������쳣�����׳�
//			throw ExceptionUtil.getBaseException(ex);
//		}
		
		String jsjdm = qyqssdsSbglForm.getJsjdm();
		return new ActionForward(QyqssdsActionHelper.getSbForwardPath(jsjdm,String.valueOf(czlx)));
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
	 //���ò�ѯ����
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
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
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
			//throw  ExceptionUtil.getBaseException(new Exception(),"���������ڣ�");
			throw new ApplicationException("����ҵ����ҵ��������˰������Ϣ�����ڣ����ܽ����걨");
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
//			throw new ApplicationException("����ҵ������ҵ����˰Ӧ����������Ҫ����ҵ��������˰�걨��");
//		}
		
		String bashztbs=map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString();
		if(!"2".equals(bashztbs)){//�����걨���״̬��ʾ�������ͨ��
			throw new ApplicationException("����ҵ����ҵ��������˰������Ϣ���δͨ��,���ܽ�����ҵ��������˰�걨��");
			//throw ExceptionUtil.getBaseException(new Exception(),"�������δͨ����");
		}

		String shshztbs=map.get(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS).toString();	
		if(shshztbs==null){
			return new ActionForward(QyqssdsActionHelper.getSbForwardPath(qyqssdsSbglForm.getFilter_jsjdm(),new Integer(CodeConstant.QYQSSDSBAGL_CZLX_ADD).toString()));
		}else if(shshztbs.length()>0){
			//throw ExceptionUtil.getBaseException(new Exception(),"�����Ѿ����ڣ�");
			throw new ApplicationException("����ҵ����ҵ��������˰�����Ѿ����ڣ����ܽ�����ӣ�");
		}	
		
		return new ActionForward(QyqssdsActionHelper.getSbForwardPath(qyqssdsSbglForm.getFilter_jsjdm(),new Integer(CodeConstant.QYQSSDSBAGL_CZLX_ADD).toString()));
	}
    
    public ActionForward doView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
    	
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		this.setFilter(request, qyqssdsSbglForm);
		return new ActionForward(QyqssdsActionHelper.getSbForwardPath(qyqssdsSbglForm.getJsjdm(),qyqssdsSbglForm.getCzlx()));
    
    }  
    
    

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		QyqssdsSbglForm qyqssdsSbglForm = (QyqssdsSbglForm) form;
		
		// ����ʱ������ѯ��������²�ѯ��
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
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return doQuery(mapping, form,request, response);
	}
    
	/**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDSSB_QUERY);
	}
}
