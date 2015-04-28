package com.syax.bjtax.shenbao.jmba.basx014b.web;

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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13BVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14AVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14BVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba18VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba20VO;
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
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Basx014bAction extends DcBaseAction {

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
	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		
		// 获取代码表的值。
//		List zysbList = CodeTableUtil.getCodeTableList(request,
//				CodeTable.JMBA_ZYSBLX_LIST);

		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
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
		HashMap map=new HashMap();
		map = (HashMap)(ShenbaoProxy.getInstance()
				.process(voPackage));
		JmbaZbVO zbvo = (JmbaZbVO)map.get("jmbavo");
		List zysbList=(ArrayList)map.get("sblx");

		zbvo = completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,
				CodeTable.JMBA_BASX_MAP));

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);

		if (zbvo.getQysdsjmba() == null || zbvo.getQysdsjmba().size() == 0) {
			vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			zbvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
					+ "");
			getNewBandVo(userdata, jbsj, zbvo.getQysdsjmba());
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

		request.getSession().setAttribute("ZYSBLIST", zysbList);

		

			request.getSession().setAttribute("ZYSBLXDM14B",
					((Jmba14BVO) (zbvo.getQysdsjmba().get(0))).getZysblxdm());
	

		int size = zbvo.getQysdsjmba().size();
		System.out.println("mxvo size = " + size);
		request.getSession(false).setAttribute("size", size + "");
		request.getSession().setAttribute("XSLLX14b", "VIEW");
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.SHOW;

	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if("VIEW".equals(request.getSession().getAttribute("XSLLX14b"))){
			request.getSession().setAttribute("XSLLX14b", null);
		}
		// 获取代码表的值。
//		List zysbList = CodeTableUtil.getCodeTableList(request,
//				CodeTable.JMBA_ZYSBLX_LIST);

		UserData userdata = (UserData) this.getUserData(request);
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
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
		HashMap map=new HashMap();
		map = (HashMap)(ShenbaoProxy.getInstance()
				.process(voPackage));
		JmbaZbVO zbvo = (JmbaZbVO)map.get("jmbavo");
		List zysbList=(ArrayList)map.get("sblx");

		zbvo = completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,
				CodeTable.JMBA_BASX_MAP));

		JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);

		if (zbvo.getQysdsjmba() == null || zbvo.getQysdsjmba().size() == 0) {
			vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			zbvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
					+ "");
			getNewBandVo(userdata, jbsj, zbvo.getQysdsjmba());
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

		request.getSession().setAttribute("ZYSBLIST", zysbList);

		if (vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)) {

			request.getSession().setAttribute("ZYSBLXDM14B",
					((Jmba14BVO) (zbvo.getQysdsjmba().get(0))).getZysblxdm());
		}

		int size = zbvo.getQysdsjmba().size();
		System.out.println("mxvo size = " + size);
		request.getSession(false).setAttribute("size", size + "");

		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.SHOW;

	}


    public String doAjaxShow(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
    // 获取代码表的值。
//        List zysbList = CodeTableUtil.getCodeTableList(request,
//                CodeTable.JMBA_ZYSBLX_LIST);

    String wnbasqwsh = request.getParameter("wnbasqwsh");
     String  querydmnd = request.getParameter("querydmnd");
        UserData userdata = (UserData) this.getUserData(request);
        Map djMap = (Map) FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
        // 生成VOPackage
        VOPackage voPackage = new VOPackage();
        // 设定vopackage参数
        voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYDETAIL);
        voPackage.setUserData(userdata);
        // @todo 从第二个跳转页面的request中获取
        JmbamxBo bo = (JmbamxBo) request.getSession().getAttribute(
                JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
        // String basqwsh =
        // (String)request.getSession().getAttribute("basqwsh");
        // JmbamxBo bo = new JmbamxBo();
        // bo.setBasqwsh(basqwsh);
        Map map = new HashMap();
        map.put("wnbasqwsh",wnbasqwsh);

        map.put("querydmnd",querydmnd);
        map.put("bo",bo);
        voPackage.setData(map);

        // 调用后台操作，取得返回值
        HashMap map1=new HashMap();
		map = (HashMap)(ShenbaoProxy.getInstance()
				.process(voPackage));
		JmbaZbVO zbvo = (JmbaZbVO)map.get("jmbavo");
		List zysbList=(ArrayList)map.get("sblx");


        zbvo = completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,
                CodeTable.JMBA_BASX_MAP));

        JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
        StringBuffer buff = new StringBuffer();
        if (zbvo.getQysdsjmba() == null || zbvo.getQysdsjmba().size() == 0) {
            vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
            zbvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
                    + "");
            getNewBandVo(userdata, jbsj, zbvo.getQysdsjmba());
            buff.append("0");
        }else{
            //buff.append("1");
        	//edit at 20100222
            buff.append(""+zbvo.getQysdsjmba().size());
        }
        // 构造数据
        String strXml = vo.toXML();
//        Debug.out(strXml);
//
//        request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
//                .getXsltVersion());
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo
//                .getSchemaVersion());
//
        request.getSession().setAttribute("ZYSBLIST", zysbList);

        if (vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)) {

            request.getSession().setAttribute("ZYSBLXDM14B",
                    ((Jmba14BVO) (zbvo.getQysdsjmba().get(0))).getZysblxdm());
        }

        int size = zbvo.getQysdsjmba().size();

        request.getSession(false).setAttribute("size", size + "");
//


//        buff.append("<?xml version='1.0' encoding='GB2312' ?>");
//        buff.append("<btznsrxx>");
//
//        buff.append("<"+CAConstants.REQ_KEY_CA_XML_DATA+"><![CDATA[1111]]></"+CAConstants.REQ_KEY_CA_XML_DATA+">");
//
//        buff.append("</btznsrxx>");
        buff.append(strXml);
        System.out.println(buff.toString());
        response.setContentType("text/xml;charset=GB2312");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter responseOut = null;
        try {
            responseOut = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        responseOut.write(buff.toString());
        responseOut.flush();

        //return CAConstants.SHOW;
        return null;

    }


    public String doAjaxDmnd(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
    // 获取代码表的值。
        List zysbList = CodeTableUtil.getCodeTableList(request,
                CodeTable.JMBA_ZYSBLX_LIST);

     String  querydmnd = request.getParameter("querydmnd");
        UserData userdata = (UserData) this.getUserData(request);
        Map djMap = (Map) FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
        // 生成VOPackage
        VOPackage voPackage = new VOPackage();
        // 设定vopackage参数
        voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYDETAIL);
        voPackage.setUserData(userdata);
        // @todo 从第二个跳转页面的request中获取
        JmbamxBo bo = (JmbamxBo) request.getSession().getAttribute(
                JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
        // String basqwsh =
        // (String)request.getSession().getAttribute("basqwsh");
        // JmbamxBo bo = new JmbamxBo();
        // bo.setBasqwsh(basqwsh);
        JmbaZbVO zbvo = new JmbaZbVO();

        zbvo.setBand(bo.getBand());
        zbvo.setBasqbh(bo.getBasqbh());
        zbvo.setBasqwsh(bo.getBasqwsh());
        zbvo.setJmbasxdm(bo.getJmbasxdm());
        zbvo.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
                     .format(new Date()));

        zbvo = completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,
            CodeTable.JMBA_BASX_MAP));

        JmbaVO vo = this.convertToXmlVO(zbvo, userdata, jbsj);
        StringBuffer buff = new StringBuffer();

        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
        zbvo.setBand( (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
                     + "");
        int count = getBandsVo(zbvo.getQysdsjmba(), querydmnd);
        buff.append(count+"");

        // 构造数据
        String strXml = vo.toXML();
//        Debug.out(strXml);
//
//        request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
//                .getXsltVersion());
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo
//                .getSchemaVersion());
//
        request.getSession().setAttribute("ZYSBLIST", zysbList);

        if (vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)) {

            request.getSession().setAttribute("ZYSBLXDM14B",
                    ((Jmba14BVO) (zbvo.getQysdsjmba().get(0))).getZysblxdm());
        }

        int size = zbvo.getQysdsjmba().size();

        request.getSession(false).setAttribute("size", size + "");

        buff.append(strXml);
        System.out.println(buff.toString());
        response.setContentType("text/xml;charset=GB2312");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter responseOut = null;
        try {
            responseOut = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        responseOut.write(buff.toString());
        responseOut.flush();

        //return CAConstants.SHOW;
        return null;

    }



	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("into save action");

        String tmpdmnd = request.getParameter("tmpdmnd");
        System.out.println("tmpdmnd = "+tmpdmnd);
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO vo = new JmbaVO();

		// 验证电子元件签名
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		System.out.println("--------------------------" + xmldata);
		DzyjHelper dh = new DzyjHelper();
		// String tbblx = request.getParameter("tbblx");
		// System.out.println("============tbblx========"+tbblx);
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

		// hm.put("tbblx", tbblx);

		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
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
        request.getSession(false).setAttribute("tmpdmnd",tmpdmnd);
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
		voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
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
		voPackage.setProcessor(ProcessorNames.BASX014B_PROCESSOR);
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
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA14);
		vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);

		return vo;
	}

	private void getNewBandVo(UserData ud, SWDJJBSJ jbsj, List zbList) {

		int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
		/**
		 * @todo
		 */
		Jmba14BVO mxvo = null;


		for (int i = 2008; i <= band; i++) {
			mxvo = new Jmba14BVO();
			mxvo.setDmnd(i + "");
			System.out.println("mxvo================= " + i + "年 "
					+ mxvo.toXML());
			zbList.add(mxvo);
		}
		System.out.println("list size = " + zbList.size());
	}

    private int getBandsVo( List zbList,String qsnd) {

        int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
        /**
         * @todo
         */
        Jmba14BVO mxvo = null;

        int qnd = Integer.parseInt(qsnd);
        int size = 0,tmp=qnd+5-1;
        for (int i = qnd; i <= band; i++,size++) {
            //2009
            //2010
            //2011
            //2012
            //2013

            //2014
            //2015
            System.out.println("i = "+i);
            System.out.println("tmp = "+tmp);
            if(i>tmp){
                break;
            }
            mxvo = new Jmba14BVO();
            mxvo.setDmnd(i + "");
            System.out.println("mxvo================= " + i + "年 "
                    + mxvo.toXML());
            zbList.add(mxvo);
        }
        System.out.println("list size = " + zbList.size());
        return size;
    }


}
