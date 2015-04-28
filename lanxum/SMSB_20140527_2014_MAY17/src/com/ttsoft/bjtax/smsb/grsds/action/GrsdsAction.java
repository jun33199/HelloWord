package com.ttsoft.bjtax.smsb.grsds.action;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.grsds.form.GrsdsForm;
import com.ttsoft.bjtax.smsb.grsds.model.GrVO;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

public class GrsdsAction extends SmsbAction {

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response) {
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">耕地资源占用税系统</font>&gt;<font color=\"#7C9AAB\">税源查询 </font>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "税源查询");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/zhsb/zhsb-000.htm");
	}

	public ActionForward doShow(ActionMapping actionMapping,
			ActionForm actionFormForm, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 封装数据信息
		GrsdsForm grsdsForm = (GrsdsForm) actionFormForm;
		VOPackage vo = new VOPackage();
		vo.setUserData(this.getUserData(request));
		vo.setAction(CodeConstant.SMSB_GRSDS_CXTZF);
		vo.setProcessor(CodeConstant.SMSB_GRSDS_PROCESSOR);
		List tzfList = null;
		List zjlxList = null;
		try {
			tzfList = (List) ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		vo.setAction(CodeConstant.SMSB_GRSDS_CXZJLX);
		try {
			zjlxList = (List) ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		grsdsForm.setTzfList(tzfList);
		grsdsForm.setZjlxList(zjlxList);

		return actionMapping.findForward("show");
	}
	
	/**
	 * 初始化基本信息页面
	 * @param actionMapping
	 * @param actionFormForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doInitJbxx(ActionMapping actionMapping,
			ActionForm actionFormForm, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		return actionMapping.findForward("initJbxx");
	}

	public ActionForward doAddSave(ActionMapping actionMapping,
			ActionForm actionFormForm, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		UserData ud = (UserData) request.getSession(false).getAttribute(
				"UserData");
		// ------------
		GrVO grVO = new GrVO();
		grVO.setJsjdm(request.getParameter("jsjdm"));
		grVO.setGr_tzzxm(request.getParameter("gr_tzzxm"));
		grVO.setGr_sfzjlx(request.getParameter("gr_sfzjlx"));
		grVO.setGr_sfzjhm(request.getParameter("gr_sfzjhm"));
		grVO.setGr_fpbl(request.getParameter("gr_fpbl"));

		// ----------------
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_GRSDS_ADD_TZF);
		vo.setProcessor(CodeConstant.SMSB_GRSDS_PROCESSOR);
		vo.setData(grVO);
		vo.setUserData(ud);

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?>");
		sb.append("<re>");
		try {
			ZhsbProxy.getInstance().process(vo);
			sb.append("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("FALSE");
		}
		sb.append("</re>");

		response.setContentType("text/xml;charset=GB2312");
		response.setHeader("Cache-Control", "no-cache");
		try {
			BufferedWriter bw = new BufferedWriter(response.getWriter());
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @Description: 删除一条信息
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping actionMapping,
			ActionForm actionFormForm, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		GrVO grVO = new GrVO();
		grVO.setJsjdm(request.getParameter("jsjdm"));
		grVO.setGr_sfzjlx(request.getParameter("gr_sfzjlx"));
		grVO.setGr_sfzjhm(request.getParameter("gr_sfzjhm"));

		// ----------------
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_GRSDS_DELETE_TZF);
		vo.setProcessor(CodeConstant.SMSB_GRSDS_PROCESSOR);
		vo.setData(grVO);

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?>");
		sb.append("<re>");
		try {
			ZhsbProxy.getInstance().process(vo);
			sb.append("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("FALSE");
		}
		sb.append("</re>");

		response.setContentType("text/xml;charset=GB2312");
		response.setHeader("Cache-Control", "no-cache");
		try {
			BufferedWriter bw = new BufferedWriter(response.getWriter());
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
