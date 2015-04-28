package com.ttsoft.bjtax.smsb.gzsxfkcx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import com.syax.frame.util.ExcelUtil;
//import com.syax.sjsh.common.ExcelParameter;
//import com.syax.adhocquery.vo.MbVo;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant; //import com.ttsoft.bjtax.smsb.gghsb.excel.ExcelUtil;
import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm;
import com.ttsoft.bjtax.smsb.model.client.ExcelParameter;
import com.ttsoft.bjtax.smsb.model.client.nsrztmodel;
import com.ttsoft.bjtax.smsb.model.client.scjxmodel;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.model.client.swjgtolist;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.gzsxexcel.ExcelUtil; //import com.ttsoft.bjtax.smsb.util.ExcelUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class GzsxfkcxAction extends SmsbAction {
	private static final String FILENAME = "告知事项反馈数据";
	private List list1 = new ArrayList();
	private int i = 1;

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报征收</font>&gt;<font color=\"#6F8EA2\">申报控制</font>&gt;告知事项反馈查询&gt;</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"告知事项反馈查询");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/gzwh/gzwh-000.htm");
	}

	/*
	 * 点查询主菜单走doshow这个方法
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			List list = new ArrayList();
			GzsxwhForm gf = (GzsxwhForm) form;
			VOPackage vo = new VOPackage();
			UserData d = this.getUserData(request);
			// 设置区县的数据
			vo.setAction(CodeConstant.SMSB_DQJSLIST);
			vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
			vo.setData(gf);
			vo.setUserData(this.getUserData(request));
			gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
			// 设置行业基本类型数据
			vo.setAction(CodeConstant.SMSB_HYLXLIST);
			vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
			vo.setData(gf);
			gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
			// 设置登记注册类型的数据。
			vo.setAction(CodeConstant.SMSB_DJLXLIST);
			vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
			vo.setData(gf);
			gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
			// 设置纳税人状态类型数据。
			vo.setAction(CodeConstant.SMSB_NSRZTLIST);
			vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
			vo.setData(gf);
			gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
			gf.setNsrztlilst(gf.getNsrztlilst());
			list = list1;
			request.setAttribute(mapping.getAttribute(), gf);
			gf.setTjgzsxlilst(list);
			return mapping.findForward("Show");

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	/*
	 * 点了主查询页面上的查询按钮跑这个doquery方法
	 */
	public ActionForward doquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			// 搜集查询条件，计算机代码时：
			GzsxwhForm gf = (GzsxwhForm) form;
			VOPackage vo = new VOPackage();
			String jsjdm = gf.getJsjdm();
			if (!"".equals(jsjdm)) {
				vo.setAction(CodeConstant.SMSB_GZSXFKJSJSDMQUERY);
				vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
				vo.setData(gf);
				gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
				List list = gf.getJsjdmgzsxlilst();
				request.getSession().setAttribute("RESULT_LIST", list);
				if (list.size() % 20 == 0) {
					gf.setPgSum(list.size() / 20);
				} else {
					gf.setPgSum(list.size() / 20 + 1);
				}
				List list1 = new ArrayList();
				if (gf.getPgNum() == 0) {
					gf.setPgNum(1);
					for (int i = 0; i < 20; i++) {
						if (list.size() <= i)
							break;
						list1.add(list.get(i));
						request.getSession().setAttribute(((swjgtolist)list.get(i)).getGzsx_id()+"-smgzsx", ((swjgtolist)list.get(i)).getGzsxnr());
					
					}
					gf.setJsjdmgzsxlilst(list1);
				} else {
					for (int i = (gf.getPgNum() - 1) * 20; i < 20 + (gf
							.getPgNum() - 1) * 20; i++) {
						if (list.size() <= i)
							break;
						list1.add(list.get(i));
						request.getSession().setAttribute(((swjgtolist)list.get(i)).getGzsx_id()+"-smgzsx", ((swjgtolist)list.get(i)).getGzsxnr());
						
					}
					gf.setJsjdmgzsxlilst(list1);
					
				}
				request.setAttribute(mapping.getAttribute(), gf);
				return mapping.findForward("jsjdmgzsxlist");
			} else {
				// 如果是非计算机代码的形式，就要整理查询的条件，来做查询了。
				vo.setAction(CodeConstant.SMSB_GZSXFKTJQUERY);
				vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
				vo.setData(gf);
				vo.setUserData(this.getUserData(request));
				gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
				List list = gf.getTjgzsxlilst();
				gf.setJlcount(list.size() + "");
				request.getSession().setAttribute("GZSXRESULT_LIST", list);
				if (list.size() % 20 == 0) {
					gf.setPgSum(list.size() / 20);
				} else {
					gf.setPgSum(list.size() / 20 + 1);
				}
				List list2 = new ArrayList();
				if (gf.getPgNum() == 0) {

					gf.setPgNum(1);
					for (int i = 0; i < 20; i++) {
						if (list.size() <= i)
							break;
						list2.add(list.get(i));
						request.getSession().setAttribute(((swjgtolist)list.get(i)).getGzsx_id()+"-smgzsx", ((swjgtolist)list.get(i)).getGzsxnr());
						
					}
				} else {
					for (int i = (gf.getPgNum() - 1) * 20; i < 20 + (gf
							.getPgNum() - 1) * 20; i++) {
						if (list.size() <= i)
							break;
						list2.add(list.get(i));
						request.getSession().setAttribute(((swjgtolist)list.get(i)).getGzsx_id()+"-smgzsx", ((swjgtolist)list.get(i)).getGzsxnr());
						
					}
				}
				if (list1.size() != 0)
					list1 = null;
				list1 = list2;
				request.setAttribute(mapping.getAttribute(), gf);
				return doShow(mapping, gf, request, response);
			}
			// 针对上面的两种情况得到查询的结果

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	// 从后台取出告知事项列表数据
	private List getGzsxList(String jsjdm) throws Exception {
		VOPackage vo = new VOPackage();
		vo.setData(jsjdm);
		vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
		vo.setAction(CodeConstant.SMSB_GZSXFKJSJSDMQUERY);
		return (List) ShenbaoProxy.getInstance().process(vo);
	}

	// 修改的
	public ActionForward dogetsws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzsxwhForm gf = (GzsxwhForm) form;
		VOPackage vo = new VOPackage();
		vo.setData(gf);
                vo.setUserData(this.getUserData(request));
		vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);

		vo.setAction(CodeConstant.SMSB_CXDQJSLIST);
		gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
		List sws = gf.getCxswdwlilst();
		List scjx = gf.getScjxlilst();
		response.setContentType("text/xml;charset=GB2312");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter responseOut = null;
		try {
			responseOut = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
		responseOut.write("<mb>");
		for (int i = 0; i < sws.size(); i++) {
			swdwmodel v = (swdwmodel) sws.get(i);
			responseOut.write("<gV>" + v.getSwdwid() + "</gV>");
			responseOut.write("<gU>" + v.getSwdwmc() + "</gU>");

		}
		for (int i = 0; i < scjx.size(); i++) {
			scjxmodel v = (scjxmodel) scjx.get(i);
			responseOut.write("<sV>" + v.getJxid() + "</sV>");
			responseOut.write("<sU>" + v.getJxmc() + "</sU>");
		}
		responseOut.write("</mb>");
		// responseOut.flush();
		responseOut.close();

		return null;
	}

	/*
	 * 点了告知事项查询结果的已阅读清单
	 */
	public ActionForward dogetydqd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			gf.setGzsxnrbt(gf.getYdcs().split("\\$")[1]);
			VOPackage vo = new VOPackage();
			if (gf.getPgNum() == 0)
				gf.setPgNum(1);
			vo.setAction(CodeConstant.SMSB_GZSXYYDQUERY);
			vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
			vo.setData(gf);
			vo.setUserData(this.getUserData(request));
			// 针对上面的两种情况得到查询的结果
			gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
			List list = gf.getTjgzsxlilst();
			request.getSession().setAttribute("yddqlsit", list);
			request.setAttribute(mapping.getAttribute(), gf);
			return mapping.findForward("ydqd");

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	/*
	 * 点了告知事项查询结果的未阅读清单
	 */
	public ActionForward dogetwydqd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			VOPackage vo = new VOPackage();
			gf.setGzsxnrbt(gf.getYdcs().split("\\$")[1]);
			if (gf.getPgNum() == 0)
				gf.setPgNum(1);
			vo.setAction(CodeConstant.SMSB_GZSXWYDQUERY);
			vo.setProcessor(CodeConstant.GZWH_GZSXFKCX_PROCESSOR);
			vo.setData(gf);
			// 针对上面的两种情况得到查询的结果
			vo.setUserData(this.getUserData(request));
			gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
			List list = gf.getTjgzsxlilst();
			request.getSession().setAttribute("wydqlsit", list);
			request.setAttribute(mapping.getAttribute(), gf);
			return mapping.findForward("wydqd");

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	/*
	 * 点了查看反馈内容连接
	 */
	public ActionForward dogetfknr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			String fknr = gf.getFknr();
			request.setAttribute("FKNR", fknr);
			return mapping.findForward("gzfknr");

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	/*
	 * 点了查看告知事项内容连接
	 */
	public ActionForward dogetgzsxnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			String gzsx_id = gf.getGzsx_id();
			//System.out.println(gzsx_id+"=-------------告知id");
			//request.getSession().getAttribute(gzsx_id);
			request.setAttribute("gzsx_id", gzsx_id+"-smgzsx");
			return mapping.findForward("gzsxnr");

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	/**
	 * 返回处理
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request (if any)
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * @return the element previously at the specified position.
	 * @throws BaseException
	 */
	public ActionForward doReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			// 传递form
			request.setAttribute(mapping.getAttribute(), gf);

			return doShow(mapping, form, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	public ActionForward dofanhui(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			// 传递form
			request.setAttribute(mapping.getAttribute(), gf);

			return doquery(mapping, form, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

	}

	// excel导出

	public ActionForward doExportExcel1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			// 构造导出的数据
			String[] titleStr = { "告知事项ID", "告知事项标题", "主管税务机关", "阅读时间", "反馈内容" };
			String[] colStr = { "gzsx_id", "gzsxnrbt", "swjgzzjg", "ydsj",
					"fknr" };
			List list = (List) (request.getSession())
					.getAttribute("RESULT_LIST");
			exportExcel(titleStr, colStr, list, gf.getNsrmc() + "对告知事项阅读情况",
					response);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			// 转向错误页面
			request.setAttribute("Msg", e.getMessage());
			return mapping.findForward("errors");
		}
	}

	// excel导出

	public ActionForward doExportExcel2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			// 构造导出的数据
			String[] titleStr = { "告知事项编号", "告知事项标题", "主管税务机关", "阅读清单", "未读清单" };
			String[] colStr = { "gzsx_id", "gzsxnrbt", "swjgzzjg", "yydlink",
					"wydlink" };
			List list = (List) (request.getSession())
					.getAttribute("GZSXRESULT_LIST");
			exportExcel(titleStr, colStr, list, gf.getNsrmc(), response);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			// 转向错误页面
			request.setAttribute("Msg", e.getMessage());
			return mapping.findForward("errors");
		}
	}

	// excel已阅读清单导出

	public ActionForward doexportydqdExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		try {
			GzsxwhForm gf = (GzsxwhForm) form;
			// 构造导出的数据
			String[] titleStr = { "计算机代码", "纳税人名称", "阅读时间", "答复" };
			String[] colStr = { "jsjdm", "nsrmc", "ydsj", "fknr" };
			List list = (List) (request.getSession()).getAttribute("yddqlsit");
			exportExcel(titleStr, colStr, list, gf.getGzsxnrbt() + (i++),
					response);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			// 转向错误页面
			request.setAttribute("Msg", e.getMessage());
			return mapping.findForward("errors");
		}
	}

	// excel未阅读清单导出

	public ActionForward doexportwydqdExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		try {

			GzsxwhForm gf = (GzsxwhForm) form;
			// 构造导出的数据
			String[] titleStr = { "计算机代码", "纳税人名称" };
			String[] colStr = { "jsjdm", "nsrmc" };
			List list = (List) (request.getSession()).getAttribute("wydqlsit");
			exportExcel(titleStr, colStr, list, gf.getGzsxnrbt() + (i++),
					response);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			// 转向错误页面
			request.setAttribute("Msg", e.getMessage());
			return mapping.findForward("errors");
		}
	}

	public void exportExcel(String[] title, String[] column, List totalexcel,
			String fileName, HttpServletResponse response) throws Exception {
		// 导出excel开始
		ExcelParameter param = new ExcelParameter();
		param.setDataList(totalexcel);

		param.setEncodeFileName(fileName);

		param.setResponse(response);
		param.setTITLE(title);
		param.setCOLUMS(column);

		ExcelUtil.generateExcel(param);
	}

}
