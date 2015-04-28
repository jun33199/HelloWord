package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.QyqssdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.QyqssdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.bo.NsrJbxxBo;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.xmlvo.QyqssdsNsrJbxxVo;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.QysdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsBaAction extends DcBaseAction {
	private String errorMessage = "";

	protected boolean validate(QyqssdsNsrJbxxVo nsrjbxx) {
		if (!nsrjbxx.getYwlx().equals(QyqssdsConstant.CA_YWLXDM_QYQSSDS)) {
			errorMessage = "业务类型错误，不能执行业务操作。";
		}

		if (!(nsrjbxx.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || nsrjbxx
				.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
			System.out.println("业务操作类型错误" + nsrjbxx.getYwczlx());
			errorMessage = "业务操作类型错误，不能执行业务操作。";
			return false;
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!nsrjbxx.getNsrjbxx().getTbrq().startsWith(df.format(now))) {
			errorMessage = "申报日期错误。";
			return false;
		}
		return true;
	}

	public String doDelete(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 检查token
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		// 得到xml
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		NsrJbxxBo nsrJbxxBO = null;
		QyqssdsNsrJbxxVo nsrJbxxdjbVO = new QyqssdsNsrJbxxVo();
		QyqssdsUtil qyqssdsUtil = new QyqssdsUtil();
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		try {
			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(),
							ud.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			System.out.println("save xml:" + xmldata);

			try {
				// XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
				// qyjb = new QysdsjbVO();
				XMLParseHelper.parseXMLString(nsrJbxxdjbVO, xmldata,XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			nsrJbxxBO = qyqssdsUtil.NsrJbxxconvert2VO(nsrJbxxdjbVO);
			dzyj.setYwczlx(nsrJbxxdjbVO.getYwczlx());
			dzyj.setYwdm(nsrJbxxdjbVO.getYwlx());
			// 取得登记基本数据
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
			qyqssdsUtil.getBaShztBs(nsrJbxxBO);
			if(nsrJbxxBO.getBaShztbs()!=null && nsrJbxxBO.getBaShztbs().equals("2")){
				throw ExceptionUtil.getBaseException(new Exception(), "您企业的企业清算所得税备案已经审核通过，不能进行删除动作！");
			}
			if(nsrJbxxBO.getBaShztbs()!=null && nsrJbxxBO.getBaShztbs().equals("1")){
				throw ExceptionUtil.getBaseException(new Exception(), "您企业的企业清算所得税备案已经提交，不能进行删除动作！");
			}
			// 更新form中的数据
			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBO);
			
			// 构造VOPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(QyqssdsConstant.QYQSSDS_PROCESSOR);
			vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
			vo.setUserData(ud);
			// 调用后台Proxy
			ShenbaoProxy.getInstance().process(vo);

			request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"企业清算所得税备案删除"+ CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,QyqssdsConstant.REQ_KEY_RETURN_QYQSSDSBA_SAVE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业清算所得税备案删除", nsrJbxxdjbVO.getNsrjbxx().getTbrq(),"成功!");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"企业清算所得税备案删除"+ CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
			// return CAConstants.SUCCESSSB;
			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE,CAConstants.SUCCESSSB);

		} catch (Exception ex) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA,nsrJbxxdjbVO.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,nsrJbxxdjbVO.getSchemaVersion());
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"删除企业清算所得税备案",(new SimpleDateFormat("yyyyMMdd")).format(curDate), "失败!");
			throw ExceptionUtil.getBaseException(ex);
		}

	}

	/**
	 * 初始化企业所得税申报数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 登记基本数据
		SWDJJBSJ djJbsj = null;
		// 构造VOPackage
		VOPackage vo = new VOPackage();
		Map pDataMap = new HashMap();
		UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
		if (ud == null) {
			System.out.println("session is null");
		}

		String jsjdm = ud.yhid;

		Timestamp curDate = new Timestamp(System.currentTimeMillis());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		NsrJbxxBo nsrJbxxBO = new NsrJbxxBo();
		QyqssdsNsrJbxxVo nsrJbxxdjbVO = new QyqssdsNsrJbxxVo();

		QyqssdsUtil qyqssdsUtil = new QyqssdsUtil();
		try {

			// 检查是否是试点区县，适用时间20090201-20090215
			// checkIsTestTaxpayer(request,response);

			// 取得登记基本数据
			djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			pDataMap.put(QyqssdsConstant.STRING_KEY_JSJDM, jsjdm);
			pDataMap.put(QyqssdsConstant.STRING_KEY_DATE, curDate);
			pDataMap.put(QyqssdsConstant.OBJECT_KEY_DJSJ, djJbsj);

			vo.setAction(QyqssdsConstant.INT_ACTION_QUERY);
			vo.setProcessor(QyqssdsConstant.QYQSSDS_PROCESSOR);
			vo.setData(pDataMap);
			vo.setUserData(ud);

			// 调用后台查询数据构造NsrJbxxBo
			nsrJbxxBO = (NsrJbxxBo) ShenbaoProxy.getInstance().process(vo);
			// mass 增加 2013-12-6
			nsrJbxxBO.setTbrq(sdf.format(curDate));
			nsrJbxxdjbVO = qyqssdsUtil.NsrJbxxconvert2XMLVO(nsrJbxxBO, djJbsj);

			String tmpxml = nsrJbxxdjbVO.toXML();
			System.out.println("show xml:" + tmpxml);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,nsrJbxxdjbVO.getSchemaVersion());

			// String link = TinyTools.getProperty("WSSB_QYSDS_DOWN_LINK");

			return CAConstants.SHOW;
		} catch (Exception ex) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA,
					nsrJbxxdjbVO.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					nsrJbxxdjbVO.getSchemaVersion());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 保存纳税人基本信息表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		Map retmap = null;

		NsrJbxxBo nsrJbxxBO = null;
		QyqssdsNsrJbxxVo nsrJbxxdjbVO = new QyqssdsNsrJbxxVo();

		QyqssdsUtil qysdsUtil = new QyqssdsUtil();

		try {

			// 取得登记基本数据
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(),
							ud.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			System.out.println("save xml:" + xmldata);
			try {
				// XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
				// qyjb = new QysdsjbVO();
				XMLParseHelper.parseXMLString(nsrJbxxdjbVO, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}

			nsrJbxxBO = qysdsUtil.NsrJbxxconvert2VO(nsrJbxxdjbVO);
			dzyj.setYwdm(nsrJbxxdjbVO.getYwlx());
			dzyj.setYwczlx(nsrJbxxdjbVO.getYwczlx());
			// 取得Form
			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBO);// BO
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);// 登记数据
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);// 电子原件
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);// VO
			qysdsUtil.getBaShztBs(nsrJbxxBO);
			if(nsrJbxxBO.getBaShztbs()!=null && nsrJbxxBO.getBaShztbs().equals("2")){
				throw ExceptionUtil.getBaseException(new Exception(), "您企业的企业清算所得税备案已经审核通过，不能进行修改！");
			}
			if(nsrJbxxBO.getBaShztbs()!=null && nsrJbxxBO.getBaShztbs().equals("1")){
				throw ExceptionUtil.getBaseException(new Exception(), "您企业的企业清算所得税备案已经提交，不能进行重复提交操作！");
			}
			// 构造VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(QyqssdsConstant.QYQSSDS_PROCESSOR);
			vo.setAction(QyqssdsConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// 调用后台Procxy

			// 设置回执下载页面的后续操作。
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			if (ud.getCaflag()) {
				dzyj = (DzyjsjVO) retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				ArrayList hzlist = new ArrayList();
				hzlist.add(Long.toString(dzyj.getLsh()));
				// request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, hzlist);
			} else {
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			}

			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"企业清算所得税备案信息" + CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"企业清算所得税备案信息" + CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));

			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,QyqssdsConstant.REQ_KEY_RETURN_QYQSSDSBA_SAVE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业清算所得税备案", nsrJbxxdjbVO.getNsrjbxx().getTbrq(),"成功!");
			// return CAConstants.SUCCESSSB;
			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA,nsrJbxxdjbVO.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,nsrJbxxdjbVO.getSchemaVersion());
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业清算所得税备案", nsrJbxxdjbVO.getNsrjbxx().getTbrq(),"失败!");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * XML文档信息
	 * 
	 * @param nsrJbxxVO
	 */
	private void Czzsconvert2VO(QyqssdsNsrJbxxVo nsrJbxxVO) {
		// XML文档信息
		nsrJbxxVO.setXsltVersion(QyqssdsConstant.CA_XSLTDM_QYQSSDS);
		nsrJbxxVO.setSchemaVersion(QyqssdsConstant.CA_SCHEMADM_QYQSSDS);
		nsrJbxxVO.setYwlx(QyqssdsConstant.CA_YWLXDM_QYQSSDS);
		nsrJbxxVO.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
	}

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限
		if (!SbzlAccess.getAuthority(SbzlAccess.QYQSSDSBA2014, request)) {
			return CAConstants.NOPERMISSION;
		}
		return null;
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return "Return";
	}

}
