package com.syax.bjtax.shenbao.jmba.qjfzjzsd19.web;

import com.ekernel.foundation.xml.XMLHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.framework.util.VOPackage;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import java.util.Map;
import java.util.HashMap;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba05VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_18VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_19VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;

/**
 * <p>
 * Title: 北京地税核心征管系统－－网上申报
 * </p>
 * 
 * <p>
 * Description: （十九）清洁发展机制项目所得 Action
 * </p>
 * <p>
 * Company: 四一安信
 * </p>
 * 
 * @author 米军
 * @version 1.0
 */
public class QjfzjzsdAction extends DcBaseAction {
	public QjfzjzsdAction() {
	}

	// 查看页面
	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 获取纳税人登记基本信息
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的session中获取
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		String jsjdm = userdata.getYhid();
		// 纳税人的主管税务机关
		String zgswjg = userdata.getSsdwdm();
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jsjdm);
		voPackage.setData(map);
		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
		// 拼装jmbaVO
		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH);
		// 构造xml数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		// 在请求中设置xml数据和xsl文件的版本号
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession().setAttribute("XSLLX19", "VIEW");
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.SHOW;
	}

	/**
	 * 录入备案申请 初始请求处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		Debug.out("****************************************");
		if ("VIEW".equals(request.getSession().getAttribute("XSLLX19"))) {
			request.getSession().setAttribute("XSLLX19", null);
		}
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);

		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		String jsjdm = userdata.getYhid();
		// 纳税人的主管税务机关
		String zgswjg = userdata.getSsdwdm();
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jsjdm);

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH);
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession().setAttribute("CZLX19",
				((Map) voPackage.getData()).get("czlx19"));

		// 转向到显示页面
		return CAConstants.SHOW;
	}

	private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj,
			String BASQWSH) {
		// 1 最上层VO
		JmbaVO vo = new JmbaVO();
		// 2 纳税人VO 1.set 2
		NsrxxVO nsrxx = new NsrxxVO();
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		// 1.set 2
		vo.setNsrxx(nsrxx);
		// 3 主表VO 1-n 备案文书号 1.list.add 3s
		JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
		zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM17);
		zbvo.setBasqwsh(BASQWSH);

		Jmba_19VO vo19 = (Jmba_19VO) map.get("Jmba_19VO");
		vo19.setLrr(jbsj.getJsjdm());
		vo19.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1 + "");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		vo19.setLrrq(format.format(new Date(System.currentTimeMillis())));
		zbvo.getQysdsjmba().add(vo19);
		System.out.println(zbvo.getBasqwsh() + "----------" + vo19.getQtzl()
				+ "=======查看==================");
		// 将主表VO设置到主VO中，并设置主VO的xsl版本、schema版本、业务类型信息

		vo.getJmsbajl().add(zbvo);
		vo.setXsltVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA17);
		return vo;
	}

	// if (zbvo.getQysdsjmba().isEmpty()) {
	// Jmba_19VO mxvo = new Jmba_19VO();
	// mxvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
	// + "");
	// // mxvo.setCjr(jbsj.getJsjdm());
	// // mxvo.setCjrq(DateUtilPro.getCurYearStr4());
	// mxvo.setLrr(jbsj.getJsjdm());
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// mxvo.setLrrq(format.format(new Date(System.currentTimeMillis())));
	// zbvo.getQysdsjmba().add(mxvo);
	// }

	// db query result,set zbvo data
	// zbvo.setJmbasxdm

	// 4 明细数据VO 1-ns 3.list.add 4s
	// Jmba_19VO vo19 = (Jmba_19VO) map.get("Jmba_19VO");
	// // 3.list.add 4s
	// zbvo.getQysdsjmba().add(vo19);
	// 资料清单查询VO处理,功能页面暂时不调用
	// zbvo.getBajlzlqd().add(zlqdVOs);
	// 1.list.add 3s

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("save==================================");
		if (!isTokenValid(request)) {

			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		VOPackage voPackage = new VOPackage();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		System.out.println("jmba xmldata" + xmldata);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO jmbavo = new JmbaVO();
		try {
			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++++++++++111111111+++++++++++++++++++++++++++++++++++++++++");
			try {

				XMLParseHelper.parseXMLString(jmbavo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);

			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			System.out
					.println("执行操作之前要+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++");
			dzyj.setYwczlx(jmbavo.getYwczlx());
			dzyj.setYwdm(jmbavo.getYwlx());
			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();
			// hm.put(ZhsbMapConstant.SBSJ, jmbavo);
			// hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
			System.out
					.println("执行操作之前要++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			System.out
					.println("执行操作之后要+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// 签名原件暂不实现 20091227
			// dzyj = (DzyjsjVO) ((HashMap)
			// voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
			// 如果是CA户则进行签名回写

			// jmbavo = convert2XMLVOResult(request, jbsj, wynsksb, now);
			// String tmpxml = jmbavo.toXML();
			// request.setAttribute("CA_XML_DATA", tmpxml);
			// request.setAttribute("CA_XML_XSLT_VERSION",
			// jmbavo.getXsltVersion());
			// request.setAttribute("CA_XML_SCHEMA_VERSION",
			// jmbavo.getSchemaVersion());

			// 设置回执下载页面的后续操作。
			// 签名原件暂不实现 20091227
			/*
			 * if (ud.getCaflag()) {
			 * request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
			 * Long.toString(dzyj.getLsh())); } else {
			 * request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, ""); }
			 */
			System.out
					.println("执行操作之后要++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"清洁发展机制项目所得减免备案保存成功！");
			// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
			// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"清洁发展机制项目所得减免备案保存",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
			System.out
					.println("执行操作之后要+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			return CAConstants.SAVE;
			// return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
			// CAConstants.SUCCESSSB);
		} catch (Exception e) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					jmbavo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					jmbavo.getSchemaVersion());
			System.out.println("减免备案异常信息: ===== " + e.getMessage());

			throw ExceptionUtil.getBaseException(e);
		}

	}

	// /**
	// * 录入备案申请 编辑
	// *
	// * @param request
	// * @param response
	// * @return
	// * @throws BaseException
	// */
	// public String doEditor(HttpServletRequest request,
	// HttpServletResponse response) throws BaseException {
	// String selIndex = request.getParameter("selIndex");
	// // System.out.println("selIndex = "+selIndex);
	//
	// // 取得userdata
	// UserData userdata = (UserData) this.getUserData(request);
	// // 生成VOPackage
	// VOPackage voPackage = new VOPackage();
	// // 设定vopackage参数
	// voPackage.setProcessor(ProcessorNames.WGRJSDNX18_PROCESSOR);
	// voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
	// voPackage.setUserData(userdata);
	// // @todo 从第二个跳转页面的request中获取
	// String BASQWSH = (String) request.getSession(false).getAttribute(
	// "basqwsh");
	// // test if
	// if (BASQWSH == null)
	// BASQWSH = "062008000002";
	// Map djMap = (Map) FriendHelper.getDjInfo(request);
	// SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
	// Map map = new HashMap();
	// map.put("BASQWSH", BASQWSH);
	// map.put("selIndex", selIndex);
	// map.put("type", "EDITOR");
	//
	// voPackage.setData(map);
	//
	// // 调用后台操作，取得返回值
	// voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
	// JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
	// jbsj, BASQWSH,"");
	// // 构造数据
	// String strXml = vo.toXML();
	// Debug.out(strXml);
	// request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	// request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
	// .getXsltVersion());
	// request.getSession(false).setAttribute("buss", "Update");
	// request.getSession(false).setAttribute("selIndex", selIndex);
	//
	// // 转向到显示页面
	// return CAConstants.ADDSHOW;
	// }

	/**
	 * 对请求的权限进行检查
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限 暂设置没有检查条件
		// System.out.println("beforePerform");
		// if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
		// {
		//
		// return CAConstants.NOPERMISSION;
		// }
		return null;
	}

}
