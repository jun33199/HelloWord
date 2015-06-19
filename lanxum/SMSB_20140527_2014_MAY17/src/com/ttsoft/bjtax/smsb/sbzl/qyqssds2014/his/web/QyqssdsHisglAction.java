package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.his.web;

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

public class QyqssdsHisglAction extends SmsbAction {
	
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
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ��������˰��ʷ����</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ��������˰��ʷ����");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qyqssds/qyqssdshisgl-000.htm");

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
		System.out.println("----��ʷ��doShow----");

		QyqssdsHisglForm qyqssdsHisglForm = (QyqssdsHisglForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsHisglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.his.processor.QyqssdsHisglProcessor");
		vo.setUserData(userData);

		try {
			qyqssdsHisglForm = (QyqssdsHisglForm) SbzlProxy.getInstance()
					.process(vo);

			// ���������˵�
			QyqssdsActionHelper.initialPageSelectItem(request,
					qyqssdsHisglForm.getFilter_zgswjgList());
			String jmsbajlHtml = QyqssdsActionHelper.hisboToHtml(null);
			// ���ò�ѯ���
			request.getSession(false).setAttribute("QYQSSDSHIS_QUERY_HTML_HEAD", jmsbajlHtml);
			// ����ҳ���ѯ��ҳĬ��ֵ
			qyqssdsHisglForm.setCurrentPage("1");
			qyqssdsHisglForm.setRowsPerPage("10");
			request.setAttribute("QYQSHIS_ROWS_COUNT", "0");
			request.setAttribute("QYQSHIS_TOTAL_PAGE", "0");
			System.out.println("��ʷ��-doform����--------"+qyqssdsHisglForm.getFilter_sqlx());
			request.setAttribute(mapping.getAttribute(), qyqssdsHisglForm);
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
		QyqssdsHisglForm qyqssdsHisglForm = (QyqssdsHisglForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsHisglForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.his.processor.QyqssdsHisglProcessor");
		vo.setUserData(userData);
		try {
			List list = (List) SbzlProxy.getInstance().process(vo);
			int rowsCount = list.size();// �ܼ�¼��
			String rowsPerPage = qyqssdsHisglForm.getRowsPerPage();// ÿҳ��¼��
			int totalPage = rowsCount / Integer.parseInt(rowsPerPage)
					+ (rowsCount % Integer.parseInt(rowsPerPage) > 0 ? 1 : 0);
			// ��ǰҳ �������ĵ�ǰҳС����ҳ��ʱȡ��ҳ����
			int currentPage = (Integer.parseInt(qyqssdsHisglForm
					.getCurrentPage()) > totalPage && totalPage > 0) ? totalPage
					: Integer.parseInt(qyqssdsHisglForm.getCurrentPage());
			qyqssdsHisglForm.setCurrentPage(String.valueOf(currentPage));
			request.setAttribute("QYQSHIS_ROWS_COUNT", String.valueOf(rowsCount));
			request.setAttribute("QYQSHIS_TOTAL_PAGE", String.valueOf(totalPage));

			String jmssbjlHtml = QyqssdsActionHelper.hisboToHtml(getSubList(list,currentPage, Integer.parseInt(rowsPerPage)));
			request.getSession(false).setAttribute("QYQSSDSHIS_QUERY_HTML_HEAD", jmssbjlHtml);

			request.setAttribute("SESSION_KEY_QYQSSDSHISGLFORM",qyqssdsHisglForm);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Query");		
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
    private void setFilter(HttpServletRequest request,QyqssdsHisglForm  form){
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
    private void getFilter(HttpServletRequest request,QyqssdsHisglForm form){
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
    
}
