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
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰����˰��������
 * </p>
 * 
 * @author ��������������
 * @version 1.1
 */
public class QysdsJmsbajlMainAction extends SmsbAction {
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
		"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"��ҵ����˰�����˰�걨");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
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
		
		QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qysdsJmsbajlMainForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
		vo.setUserData(userData);
	
	
		try {
			qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) SbzlProxy.getInstance().process(vo);
			//����Ĭ�������򱸰����Ϊȥ��
			SimpleDateFormat sf=new SimpleDateFormat("yyyy");
			int band = Integer.parseInt(sf.format(new Date()))-1;
			qysdsJmsbajlMainForm.setAdd_band(String.valueOf(band));
			//���������˵�
			List zgswjgList=qysdsJmsbajlMainForm.getFilter_zgswjgList();
			
			ActionHelper.initialPageSelectItem(request,qysdsJmsbajlMainForm.getFilter_jmsbasxList(),qysdsJmsbajlMainForm.getFilter_zgswjgList());
			String jmsbajlHtml=ActionHelper.boToHtml(null);
			//���ò�ѯ���
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
			//����ҳ���ѯ��ҳĬ��ֵ
			qysdsJmsbajlMainForm.setCurrentPage("1");
			qysdsJmsbajlMainForm.setRowsPerPage("10");
			request.setAttribute("ROWS_COUNT","0");
			request.setAttribute("TOTAL_PAGE","0");
			request.setAttribute(mapping.getAttribute(), qysdsJmsbajlMainForm);
			return mapping.findForward("Show");
		} catch (Exception ex) {
			
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
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
			//����Ĭ�������򱸰����Ϊȥ��
			SimpleDateFormat sf=new SimpleDateFormat("yyyy");
			int band = Integer.parseInt(sf.format(new Date()))-1;
			qysdsJmsbajlMainForm.setAdd_band(String.valueOf(band));
			//���������˵�
			List zgswjgList=qysdsJmsbajlMainForm.getFilter_zgswjgList();
			
			ActionHelper.initialPageSelectItem(request,qysdsJmsbajlMainForm.getFilter_jmsbasxList(),qysdsJmsbajlMainForm.getFilter_zgswjgList());
			String jmsbajlHtml=ActionHelper.boToHtmlZfbg(null);
			//���ò�ѯ���
			request.getSession(false).setAttribute("QUERY_HTML", jmsbajlHtml);
			//����ҳ���ѯ��ҳĬ��ֵ
			qysdsJmsbajlMainForm.setCurrentPage("1");
			qysdsJmsbajlMainForm.setRowsPerPage("10");
			request.setAttribute("ROWS_COUNT","0");
			request.setAttribute("TOTAL_PAGE","0");
			request.setAttribute(mapping.getAttribute(), qysdsJmsbajlMainForm);
			return mapping.findForward("ShowZfbg");
		} catch (Exception ex) {
			
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}	
	
	
	
	/**
	 * ������������
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
			//���÷��ز�ѯ����
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
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		
	}
	
	
	
	
	
	/**
     * ��ѯ���걨����
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
//    	-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        
    	UserData userData = null;
    	QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
    	
    	//����ʱ������ѯ��������²�ѯ��
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
        	
        	
        	int rowsCount=list.size();//�ܼ�¼��
    		String rowsPerPage=qysdsJmsbajlMainForm.getRowsPerPage();//ÿҳ��¼��		
    		
    		int totalPage=rowsCount/Integer.parseInt(rowsPerPage)+(rowsCount%Integer.parseInt(rowsPerPage)>0?1:0);
    		//��ǰҳ �������ĵ�ǰҳС����ҳ��ʱȡ��ҳ����
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
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }
    
  
	/**
     * ��ѯ���걨����
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQueryZfbg (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
//    	-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        
    	UserData userData = null;
    	QysdsJmsbajlMainForm qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm) form;
    	
    	//����ʱ������ѯ��������²�ѯ��
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
        	
        	//�����ѯ�����ҳ////////////////////////////////////////////////////////////////
        	int rowsCount=list.size();//�ܼ�¼��
    		String rowsPerPage=qysdsJmsbajlMainForm.getRowsPerPage();//ÿҳ��¼��		    		
    		int totalPage=rowsCount/Integer.parseInt(rowsPerPage)+(rowsCount%Integer.parseInt(rowsPerPage)>0?1:0);
    		//��ǰҳ �������ĵ�ǰҳС����ҳ��ʱȡ��ҳ����
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
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("QueryZfbg");
    }    
    
	/**
	 * �޸�.���.�鿴ҳ�����
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
		//�����������Ϊ��ˣ�����Ҫ������״̬����У��
		if(czlx==CodeConstant.QYSDSJMSBAJL_CZLX_CHECK){
			vo.setAction(CodeConstant.QYSDSJMSBAJL_BEFORCHECK);
			try {
				SbzlProxy.getInstance().process(vo);
				request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSJMSBAJLFORM,qysdsJmsbajlMainForm);	
			} catch (Exception ex) {
				// ϵͳ��׽�쳣�������쳣�����׳�
				throw ExceptionUtil.getBaseException(ex);
			}
		}
		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
		try {
			qysdsJmsbajlMainForm = (QysdsJmsbajlMainForm)SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
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
    //��������
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
    //�����ѯ����
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
    //���ò�ѯ����
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
