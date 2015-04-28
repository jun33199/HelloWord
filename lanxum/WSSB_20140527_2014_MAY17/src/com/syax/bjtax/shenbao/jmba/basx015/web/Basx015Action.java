package com.syax.bjtax.shenbao.jmba.basx015.web;

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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba15VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.dm.JMBASXDM;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
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
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Basx015Action extends DcBaseAction {

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

	private JmbaZbVO completeDm(JmbaZbVO vo, Map dmMap) {
		vo.setJmbasxmc(((JMBASXDM) dmMap.get(vo.getJmbasxdm())).getJMBASXMC());
		return vo;
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX015_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		voPackage.setUserData(userdata);
		// @todo 从第二个跳转页面的request中获取
		JmbamxBo bo = (JmbamxBo) request.getSession().getAttribute(
				JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
		// String basqwsh =
		// (String)request.getSession().getAttribute("basqwsh");
		// JmbamxBo bo = new JmbamxBo();
		// bo.setBasqwsh(basqwsh);

		voPackage.setData(bo);

		// 调用后台操作，取得返回值
		JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance()
				.process(voPackage);
		zbvo = completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,
				CodeTable.JMBA_BASX_MAP));

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
		if (zbvo.getQysdsjmba() == null || zbvo.getQysdsjmba().size() == 0) {
			vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		}

		// 构造数据
		String strXml = vo.toXML();
		Debug.out(strXml);

		request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo
				.getSchemaVersion());

		if (zbvo.getQysdsjmba().isEmpty()) {
			request.getSession().setAttribute("TBBLX", "2");
		} else {
			request.getSession().setAttribute("TBBLX",
					((Jmba15VO) (zbvo.getQysdsjmba().get(0))).getTbblx());
		}

		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.SHOW;

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
		String tbblx = request.getParameter("tbblx");
		System.out.println("============tbblx========"+tbblx);
		if (userdata.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userdata.getCert(), userdata
						.getSsdwdm());
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
		hm.put("tbblx", tbblx);

		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX015_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);
		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(
						FriendHelper.getUserData(request),
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
				dzyj = dh.verifyAndSign(request, userdata.getCert(), userdata
						.getSsdwdm());
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
		voPackage.setProcessor(ProcessorNames.BASX015_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);
		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(
						FriendHelper.getUserData(request),
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
				dzyj = dh.verifyAndSign(request, userdata.getCert(), userdata
						.getSsdwdm());
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
		voPackage.setProcessor(ProcessorNames.AZCJR03_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
		voPackage.setUserData(userdata);
		voPackage.setData(hm);

		// 调用后台操作，取得返回值
		ShenbaoProxy.getInstance().process(voPackage);
		request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案提交成功！");
		// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
		// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
		LogUtil.getInstance()
				.log(
						FriendHelper.getUserData(request),
						"企业所得税减免备案提交",
						(new SimpleDateFormat("yyyyMMdd"))
								.format(new java.util.Date()), "成功!");

		return CAConstants.SAVE;
	}

	public String doView(HttpServletRequest request,
	        HttpServletResponse response) throws BaseException {

	    UserData userdata = (UserData) this.getUserData(request);
	    Map djMap = (Map) FriendHelper.getDjInfo(request);
	    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.BASX015_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	    voPackage.setUserData(userdata);
	    //@todo 从第二个跳转页面的request中获取
	    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
	    //String basqwsh = (String)request.getSession().getAttribute("basqwsh");
	    //JmbamxBo bo = new JmbamxBo();
	    //bo.setBasqwsh(basqwsh);

	    voPackage.setData(bo);

	    // 调用后台操作，取得返回值
	    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
	            voPackage);

	    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
	    //构造数据
	    String strXml = vo.toXML();
	    Debug.out(strXml);
	    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
	    // 转向到显示页面
		  Debug.out("转向到显示页面");

          if(zbvo.getQysdsjmba().isEmpty()) {
              request.getSession().setAttribute("TBBLX", "2");
          }
          else {
              request.getSession().setAttribute("TBBLX",
                                                ( (Jmba15VO) (zbvo.getQysdsjmba().get(0))).getTbblx());
          }

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
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA15);
		vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		return vo;
	}

}
