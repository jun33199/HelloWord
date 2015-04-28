package com.syax.bjtax.shenbao.jmba.gdccsdnx17.web;

import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_17VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_19VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;

/**
 * <p>
 * Title: 北京地税核心征管系统－－网上申报
 * </p>
 * 
 * <p>
 * Description: (十七)固定资产缩短折旧年限或加速折旧 Action
 * </p>
 * 
 * <p>
 * Copyright: 四一安信
 * </p>
 * 
 * <p>
 * Company: 四一安信
 * </p>
 * 
 * @author 米军
 * @version 1.0
 */
public class GdccsdnxAction extends DcBaseAction {
	public GdccsdnxAction() {
	}
	
	
//	 提交操作。
	public String doCommit(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
		// @todo 从第二个跳转页面的session中获取
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		voPackage.setData(map);
		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
		return CAConstants.RETURN;
		//return doShow(request, response);
		// return null;
	}
	
	
	
//	 查看页面,展示的是多条记录。
	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

//		 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);

		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
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
		map.put("type", "SHOW");
		voPackage.setData(map);
		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH, "");
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession().setAttribute("TBBLX",
				((Map) voPackage.getData()).get("TBBLX"));
		request.getSession().setAttribute("XSLLX17", "VIEW");

		// 转向到显示页面
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
		if("VIEW".equals(request.getSession().getAttribute("XSLLX17"))){
			request.getSession().setAttribute("XSLLX17", null);
		}
		
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);

		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
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
		map.put("type", "SHOW");
		voPackage.setData(map);
		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH, "");
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession().setAttribute("TBBLX",
				((Map) voPackage.getData()).get("TBBLX"));
//		 if("2".equals(((Map) voPackage.getData()).get("CKZT"))||"3".equals(((Map) voPackage.getData()).get("CKZT"))||"4".equals(((Map) voPackage.getData()).get("CKZT"))){
//			 return doView(request, response); 
//		 }else{
//			 if("VIEW".equals(request.getSession().getAttribute("XSLLX17"))){
//					request.getSession().setAttribute("XSLLX17", null);
//				}
//				Debug.out("转向到显示页面");
//				return CAConstants.SHOW; 
//		 }

		// 转向到显示页面
		return CAConstants.SHOW;
	}

	public String doAdd_Sd_Show(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
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
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jbsj.getJsjdm());
		map.put("type", "ADD");

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH, "ADD");
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession(false).setAttribute("buss", "Save");
		request.getSession(false).setAttribute("TBLX",
				request.getParameter("tbblx"));
		// 转向到显示页面
		return CAConstants.ADD_SD_SHOW;
	}

	public String doAdd_Js_Show(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
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
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jbsj.getJsjdm());
		map.put("type", "ADD");

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH, "ADD");
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession(false).setAttribute("buss", "Save");
		request.getSession(false).setAttribute("TBLX",
				request.getParameter("tbblx"));
		// 转向到显示页面
		return CAConstants.ADD_JS_SHOW;
	}

	private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj,
			String BASQWSH, String type) {
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
		zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM15);
		zbvo.setBasqwsh(BASQWSH);
		if (zbvo.getQysdsjmba().isEmpty() && type.equals("ADD")) {
			Jmba_17VO mxvo = new Jmba_17VO();
			mxvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
					+ "");
			// mxvo.setCjr(jbsj.getJsjdm());
			// mxvo.setCjrq(DateUtilPro.getCurYearStr4());
			mxvo.setLrr(jbsj.getJsjdm());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			mxvo.setLrrq(format.format(new Date(System.currentTimeMillis())));
			mxvo.setTbblx("1");
			zbvo.getQysdsjmba().add(mxvo);
		}
		// db query result,set zbvo data
		// zbvo.setJmbasxdm

		// 4 明细数据VO 1-ns 3.list.add 4s
		// Jmba_19VO vo19 = (Jmba_19VO) map.get("Jmba_19VO");
		// // 3.list.add 4s
		// zbvo.getQysdsjmba().add(vo19);
		// 资料清单查询VO处理,功能页面暂时不调用
		// zbvo.getBajlzlqd().add(zlqdVOs);
		// 1.list.add 3s
		vo.getJmsbajl().add(zbvo);
		vo.setXsltVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA15);
		return vo;
	}

	/**
	 * 录入备案申请 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doDel(HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		String selIndex = request.getParameter("selIndex");
		System.out.println("selIndex = " + selIndex);

		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("selIndex", selIndex);
		map.put("type", "DEL");

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		// 转向到显示页面
		return doShow(request, response);
	}

	/**
	 * 录入备案申请 编辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doEditor(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		String selIndex = request.getParameter("selIndex");
		// System.out.println("selIndex = "+selIndex);

		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
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
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("selIndex", selIndex);
		map.put("type", "EDITOR");

		voPackage.setData(map);

		// 调用后台操作，取得返回值
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH, "");
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession(false).setAttribute("buss", "Update");
		request.getSession(false).setAttribute("selIndex", selIndex);

		// 转向到显示页面
		if ("0".equals(request.getSession(false).getAttribute("TBBLX"))) {
			return CAConstants.ADD_SD_SHOW;
		} else {
			return CAConstants.ADD_JS_SHOW;
		}

	}

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		System.out.println("jmba xmldata" + xmldata);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO jmbavo = new JmbaVO();
		String tbblx = request.getParameter("tbblx");
		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
			}
			try {
				// XMLParseHelper.parseXMLString(wsksbvo, xmldata,
				// XMLParseHelper.XERCES_PARSER, true,true);
				// wsksbvo = new WsksbVO();
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

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			hm.put("type", "Save");
			hm.put("tbblx", tbblx);
			System.out
					.println("填报表类型-ACTION:00000000000000000000000000000 ===== "
							+ request.getSession(false).getAttribute("TBLX"));
			hm.put("TBLX", request.getSession(false).getAttribute("TBLX"));
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"外购软件缩短折旧或摊销年限备案保存成功！");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"外购软件缩短折旧或摊销年限备案保存",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");

			return doShow(request, response);

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

	public String doUpdate(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		String selIndex = (String) request.getSession(false).getAttribute(
				"selIndex");
		System.out.println("selIndex !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!= "
				+ selIndex);

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		// 生成VOPackage
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
			try {
				// XMLParseHelper.parseXMLString(wsksbvo, xmldata,
				// XMLParseHelper.XERCES_PARSER, true,true);
				// wsksbvo = new WsksbVO();
				System.out.println(jmbavo.toString());
				System.out.println("-------------------");
				System.out.println(xmldata);
				XMLParseHelper.parseXMLString(jmbavo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}

			dzyj.setYwczlx(jmbavo.getYwczlx());
			dzyj.setYwdm(jmbavo.getYwlx());
			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			hm.put("type", "Update");
			hm.put("selIndex", selIndex);
			System.out.println("public String doUpdate(HttpServletRequest request"+request.getSession(false).getAttribute("TBBLX"));
			hm.put("TBLX", request.getSession(false).getAttribute("TBBLX"));
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);

			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"xxxxxxxxxxxxxxxxx保存成功！");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"xxxxxxxxxxxxxxxxxxxxxxx保存",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
			System.out
					.println("执行操作之后要+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			return doShow(request, response);
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
