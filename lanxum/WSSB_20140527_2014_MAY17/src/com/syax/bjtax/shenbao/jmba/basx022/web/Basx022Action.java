/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.basx022.web;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;

import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba022VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba022WnVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Jnjpjsgzlx;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * 项目名称：wssb 类名称：Basx022Action 类描述： 增加节能服务公司实施合同能源管理项目的所得备案事项 创建人：wangcy
 * 创建时间：2014-2-13 下午4:52:08 修改人：wangcy 修改时间：2014-2-13 下午4:52:08 修改备注：
 * 
 * @version 1.0
 */
public class Basx022Action extends DcBaseAction {

	/**
	 * 对请求的权限进行检查
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限 暂设置没有检查条件
		// System.out.println("beforePerform");
		// if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
		// {
		// return CAConstants.NOPERMISSION;
		// }
		return null;
	}

	private JmbaZbVO completeDm(JmbaZbVO vo, Map dmMap) {

		List ba022List = vo.getQysdsjmba();
		if (ba022List != null) {
			for (int i = 0; i < ba022List.size(); i++) {
				Jmba022VO vo022 = (Jmba022VO) ba022List.get(i);
				vo022.setJnjpjsgzxmmc(((Jnjpjsgzlx) dmMap.get(vo022
						.getJnjpjsgzxmdm())).getJnjpjsgzxmmc());
			}
		}
		ba022List = vo.getWnqysdsjmba();
		if (ba022List != null) {
			for (int i = 0; i < ba022List.size(); i++) {
				Jmba022WnVO vo022 = (Jmba022WnVO) ba022List.get(i);
				vo022.setJnjpjsgzxmmc(((Jnjpjsgzlx) dmMap.get(vo022
						.getJnjpjsgzxmdm())).getJnjpjsgzxmmc());
			}
		}
		return vo;
	}

	public String doAdd(HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// 取得userdata
		List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJPJSGZXM_LIST);
		System.out.println(jmflList.size());

		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String basqwsh = (String) request.getSession().getAttribute(
				JmbazForm.BASQWSH_ATTRIBUTE_NAME);

		voPackage.setData(basqwsh);

		// 调用后台操作，取得返回值
		List l = (List) ShenbaoProxy.getInstance().process(voPackage);
		JmbaZbVO zbvo = (JmbaZbVO) l.get(0);
		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
				vo.getXsltVersion());
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
				vo.getSchemaVersion());
		request.setAttribute("CS", jmflList);
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.EDITSHOW;
		// return null;
	}

	public String doDelete(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("into delete action");
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO vo = new JmbaVO();
		JmbaVO oldVo = (JmbaVO) request.getSession().getAttribute(
				SessionKey.JMBA_DATA_KEY);

		// 验证电子元件签名
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		DzyjHelper dh = new DzyjHelper();
		if (userdata.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userdata.getCert(),
						userdata.getSsdwdm());
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
		}
		try {
			XMLParseHelper.parseXMLString(vo, xmldata,
					XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}

		List oldList = ((JmbaZbVO) oldVo.getJmsbajl().get(0)).getQysdsjmba();
		List newList = ((JmbaZbVO) vo.getJmsbajl().get(0)).getQysdsjmba();
		boolean find = false;
		String mxxh = "";
		for (int i = 0; i < oldList.size(); i++) {
			Jmba022VO oldmxvo = (Jmba022VO) oldList.get(i);
			mxxh = oldmxvo.getXh();
			find = false;
			for (int j = 0; j < newList.size(); j++) {
				Jmba022VO mxvo = (Jmba022VO) newList.get(j);
				if (mxxh.equals(mxvo.getXh())) {
					find = true;
				}
			}

			if (!find) {
				break;
			}
		}

		dzyj.setYwczlx(vo.getYwczlx());
		dzyj.setYwdm(vo.getYwlx());

		JmbamxBo bo = new JmbamxBo();
		bo.setXh(mxxh);
		HashMap hm = new HashMap();
		hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);
		hm.put(VOConstants.KEY_JMBA_MX_BO, bo);

		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);

		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案删除成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(FriendHelper.getUserData(request),
						"企业所得税减免备案删除",
						(new SimpleDateFormat("yyyyMMdd"))
								.format(new java.util.Date()), "成功!");

		return doShow(request, response);
	}

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("into save action");
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO vo = new JmbaVO();

		// 验证电子元件签名
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		DzyjHelper dh = new DzyjHelper();
		if (userdata.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userdata.getCert(),
						userdata.getSsdwdm());
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
		}
		try {
			XMLParseHelper.parseXMLString(vo, xmldata,
					XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}

		dzyj.setYwczlx(vo.getYwczlx());
		dzyj.setYwdm(vo.getYwlx());

		HashMap hm = new HashMap();
		hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);
		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(FriendHelper.getUserData(request),
						"企业所得税减免备案保存",
						(new SimpleDateFormat("yyyyMMdd"))
								.format(new java.util.Date()), "成功!");

		return doShow(request, response);
	}

	public String doEditZb(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("into save action");
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO vo = new JmbaVO();

		// 验证电子元件签名
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		DzyjHelper dh = new DzyjHelper();
		if (userdata.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userdata.getCert(),
						userdata.getSsdwdm());
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
		}
		try {
			XMLParseHelper.parseXMLString(vo, xmldata,
					XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}

		dzyj.setYwczlx(vo.getYwczlx());
		dzyj.setYwdm(vo.getYwlx());

		HashMap hm = new HashMap();
		hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);
		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(FriendHelper.getUserData(request),
						"企业所得税减免备案保存",
						(new SimpleDateFormat("yyyyMMdd"))
								.format(new java.util.Date()), "成功!");

		return CAConstants.EDITZB;
	}

	public String doCommit(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("into commit action");
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO vo = new JmbaVO();

		// 验证电子元件签名
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		DzyjHelper dh = new DzyjHelper();
		if (userdata.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userdata.getCert(),
						userdata.getSsdwdm());
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
		}
		try {
			XMLParseHelper.parseXMLString(vo, xmldata,
					XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}

		dzyj.setYwczlx(vo.getYwczlx());
		dzyj.setYwdm(vo.getYwlx());

		HashMap hm = new HashMap();
		hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);
		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案提交成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(FriendHelper.getUserData(request),
						"企业所得税减免备案提交",
						(new SimpleDateFormat("yyyyMMdd"))
								.format(new java.util.Date()), "成功!");

		return CAConstants.RETURN;
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 取得userdata
		List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJPJSGZXM_LIST);

		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		JmbamxBo bo = (JmbamxBo) request.getSession().getAttribute(
				JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
		bo.setZtdm("1");
		// String basqwsh =
		// (String)request.getSession().getAttribute("basqwsh");
		// JmbamxBo bo = new JmbamxBo();
		// bo.setBasqwsh(basqwsh);

		voPackage.setData(bo);
		// bo.setXh(mxxh);

		// 调用后台操作，取得返回值
		JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance()
				.process(voPackage);

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
				vo.getXsltVersion());
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
				vo.getSchemaVersion());
		request.setAttribute("CS", jmflList);
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.EDITSHOW;
		// return null;
	}

	public String doZcba(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 取得userdata
		List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJPJSGZXM_LIST);

		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String basqwsh = (String) request.getSession().getAttribute(
				JmbazForm.BASQWSH_ATTRIBUTE_NAME);
		String mxxh = request.getParameter(JmbamxBo.BAMXXH_ATTRIBUTE_NAME);
		JmbamxBo bo = new JmbamxBo();
		bo.setZtdm("2");
		bo.setBasqwsh(basqwsh);
		bo.setXh(mxxh);

		voPackage.setData(bo);

		// 调用后台操作，取得返回值
		JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance()
				.process(voPackage);

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
				vo.getXsltVersion());
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
				vo.getSchemaVersion());
		request.setAttribute("CS", jmflList);
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.EDITSHOW;
		// return null;
	}

	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 取得userdata
		List jmflList = CodeTableUtil.getCodeTableList(request,
						CodeTable.JMBA_JNJPJSGZXM_LIST);
		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		JmbamxBo bo = (JmbamxBo) request.getSession().getAttribute(
				JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
		bo.setZtdm("9");

		voPackage.setData(bo);

		// 调用后台操作，取得返回值
		JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance()
				.process(voPackage);

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
				vo.getXsltVersion());
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
				vo.getSchemaVersion());
		request.setAttribute("CS", jmflList);
		Debug.out("转向到显示页面");
		return CAConstants.VIEWDETAIL;
	}

	public String doViewBak(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		UserData userdata = (UserData) this.getUserData(request);
		List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJPJSGZXM_LIST);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMSBA_BASX022_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		String basqwsh = (String) request.getSession().getAttribute(
				JmbazForm.BASQWSH_ATTRIBUTE_NAME);
		// String mxxh=request.getParameter(JmbamxBo.BAMXXH_ATTRIBUTE_NAME);
		JmbamxBo bo = new JmbamxBo();
		bo.setBasqwsh(basqwsh);
		bo.setZtdm("9");

		voPackage.setData(bo);

		// 调用后台操作，取得返回值
		JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance()
				.process(voPackage);

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
				vo.getXsltVersion());
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
				vo.getSchemaVersion());
		request.setAttribute("CS", jmflList);
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.VIEWSHOW;
	}

	private JmbaVO convertToXmlVO(JmbaZbVO zb, UserData ud, SWDJJBSJ jbsj) {
		// 1 最上层VO
		JmbaVO vo = new JmbaVO();
		// 2 纳税人VO 1.set 2
		NsrxxVO nsrxx = new NsrxxVO();
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		// 1.set 2
		vo.setNsrxx(nsrxx);
		List zbl = new ArrayList();
		zbl.add(zb);
		vo.setJmsbajl(zbl);

		vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA22);
		vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		return vo;
	}

	public static void main(String[] args) {
		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030022]]></ywlx><ywczlx><![CDATA[2]]></ywczlx><nsrxx><jsjdm><![CDATA[01002070]]></jsjdm><nsrmc><![CDATA[北京XXXX公司]]></nsrmc><swjgzzjgdm><![CDATA[0504]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CDATA[京地税东减免备企字[2013]000057]]></basqbh><basqwsh><![CDATA[012013000057]]></basqwsh><band><![CDATA[2013]]></band><jmbasxdm><![CDATA[0220]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><lrrq><![CDATA[2014-02-14]]></lrrq><qysdsjmba><xh><![CDATA[]]></xh><jnjpjsgzxmdm><![CDATA[07]]></jnjpjsgzxmdm><dybzlmc><![CDATA[2012]]></dybzlmc><dybrq><![CDATA[2012]]></dybrq><jbzsqsnd><![CDATA[2015]]></jbzsqsnd><jbzszznd><![CDATA[2017]]></jbzszznd><mzqsnd><![CDATA[2012]]></mzqsnd><mzzznd><![CDATA[2014]]></mzzznd><jnjpjsgzxmmc><![CDATA[钢铁行业干式除尘技术改造项目]]></jnjpjsgzxmmc></qysdsjmba></jmsbajl></taxdoc>";

		JmbaVO vo = new JmbaVO();
		try {
			XMLParseHelper.parseXMLString(vo, xmldata,
					XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			System.out.println("异常");
		}
		System.out.println(vo.getNsrxx().getJsjdm());

	}
}
