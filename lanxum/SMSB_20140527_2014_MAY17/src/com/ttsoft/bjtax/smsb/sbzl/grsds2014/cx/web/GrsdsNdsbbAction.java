package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.GrsdsUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class GrsdsNdsbbAction extends SmsbAction  {

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��������˰������Ϣ��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��������˰������Ϣ��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}
	
	/**
	 * @Description: TODO ��ʾ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
		GrsdsNdsbbActionForm ndsbbForm = (GrsdsNdsbbActionForm)actionForm;
		String query_jsjdm=ndsbbForm.getBtzzxx_jsjdm();
		String query_sfzjhm=ndsbbForm.getTzzxx_sfzjhm();
		String query_sfzjlx=ndsbbForm.getTzzxx_sfzjlx();
	
		try {
			if(query_jsjdm==null || "".equals(query_jsjdm) || query_sfzjlx==null || "".equals(query_sfzjlx) || query_sfzjhm==null || "".equals(query_sfzjhm)){
				throw ExceptionUtil.getBaseException(new Exception("��ѯ��Ϣ������"));
			}
			
			//�����Ƿ���д����걨�� ��δ��д��ת��Ͷ�����б�ҳ 
			if (!GrsdsUtil.hasItemNdsbbTzz(ndsbbForm.getNd(), query_jsjdm, query_sfzjlx, query_sfzjhm)) {	
				return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014cx.do?actionType=Show&jsjdm="+query_jsjdm+"&msg=����ҵ��δ�������걨��");//+"&sfzjlx="+query_sfzjlx+"&sfzjhm="+query_sfzjhm);
			}
			
			ndsbbForm.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));		//���֤������
			ndsbbForm.setGjList(GrsdsUtil.getGjDqList(request));			//�������������
			
			//��ѯ�걨��Ϣ
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_NDSBB_CX);
			vo.setData(ndsbbForm);
			ndsbbForm = (GrsdsNdsbbActionForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), ndsbbForm);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
			
		return mapping.findForward("Show");
	}

	/**
	 * @Description: TODO ��һҳ
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doPrevious(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsNdsbbActionForm ndsbbForm =(GrsdsNdsbbActionForm)actionForm;
			
		ActionForward result = new ActionForward("/webapp/smsb/grsds/grsdsJbxxAction2014cx.do?actionType=Show&qyxx_jsjdm="+ndsbbForm.getBtzzxx_jsjdm()+"&grxx_sfzjlx="+ndsbbForm.getTzzxx_sfzjlx()+"&grxx_sfzjhm="+ndsbbForm.getTzzxx_sfzjhm()+"&nd="+ndsbbForm.getNd());
		return result;
	}
	
	/**
	 * @Description: TODO ����
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doBack(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		GrsdsNdsbbActionForm ndsbbForm =(GrsdsNdsbbActionForm)actionForm;
		return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014cx.do?actionType=Show&jsjdm="+ndsbbForm.getBtzzxx_jsjdm());//+"&sfzjlx="+ndsbbForm.getTzzxx_sfzjlx()+"&sfzjhm="+ndsbbForm.getTzzxx_sfzjhm());
	}
	
}
