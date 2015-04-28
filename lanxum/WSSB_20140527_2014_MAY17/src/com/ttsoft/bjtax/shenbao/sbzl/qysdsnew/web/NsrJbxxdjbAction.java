package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.QysdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 纳税人基本信息表Action
 */
public class NsrJbxxdjbAction extends DcBaseAction {

	private String errorMessage = "";

	public NsrJbxxdjbAction() {
	}

	protected boolean validate(NsrJbxxdjbVO nsrjbxx) {
		if (!nsrjbxx.getYwlx().equals(Constant.CA_YWLXDM_NSRJBXXB)) {
			errorMessage = "业务类型错误，不能执行业务操作。";
			return false;
		}
		if (!(nsrjbxx.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || nsrjbxx
				.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
			System.out.println("业务操作类型错误" + nsrjbxx.getYwczlx());
			errorMessage = "业务操作类型错误，不能执行业务操作。";
			return false;
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!nsrjbxx.getNsrjbxx().getSbrq().startsWith(df.format(now))) {
			errorMessage = "申报日期错误。";
			return false;
		}
		return true;
	}

	protected int getActionID() {
		return com.ttsoft.bjtax.shenbao.util.SbzlAccess.NSRJBXXB;
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


		NsrJbxxBO nsrJbxxBO = null;
		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		
		QysdsUtil qysdsUtil = new QysdsUtil();
		

		try {

			//调用校验
			CheckBean checkBean = new CheckBean();	
			checkBean.setJsjdm(ud.getYhid());
			checkBean.createZgrqByCurrenttimeY();
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// 取得登记基本数据
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
						
			//String sybs=FriendHelper.getNsrSybs(djJbsj);
			String sybs=checkBean.getJdlx();  					//modified by lijn 20140617
			if(CodeConstant.CODE_QYSDS_ZGFWJD_OTHER.equals(sybs)){
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"您企业不是地税管辖的企业所得税应征户，不能保存基本信息登记表");
				request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"您企业不是地税管辖的企业所得税应征户，不能保存基本信息登记表");
				request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
				LogUtil.getInstance().log(FriendHelper.getUserData(request),"企业所得税纳税人基本信息表", nsrJbxxdjbVO.getNsrjbxx().getSbrq(), "成功!");
				return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
			}
			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
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
			
			/*-----addby lijn20140617--------*/
			nsrJbxxdjbVO.getNsrjbxx().setSybs(sybs);
			
			// 取得Form

			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBO);//BO
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);//登记数据
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);//电子原件
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);//VO

			// 构造VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.NSRJBXXDJB_PROCESSOR);
			vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// 调用后台Procxy

			// 设置回执下载页面的后续操作。
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			if (ud.getCaflag()) {
				dzyj = (DzyjsjVO) retmap
						.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				ArrayList hzlist = new ArrayList();
				hzlist.add(Long.toString(dzyj.getLsh()));
				// request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
				request
						.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
								hzlist);
			} else {
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			}
			NsrJbxxdjbVO jbxxdj=(NsrJbxxdjbVO)retmap.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
			String QueryFlag=jbxxdj.getNsrjbxx().getQueryFlag();
			if(QueryFlag !=null && QueryFlag.equals("999")){
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						"当期申报数据被删除，如有需要请重新录入数据进行保存!");
				request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
						"当期申报数据被删除，如有需要请重新录入数据进行保存!");
			}else{
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						"企业所得税纳税人基本信息表" + CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
				request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
						"企业所得税纳税人基本信息表" + CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
			}
			
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"企业所得税纳税人基本信息表", nsrJbxxdjbVO.getNsrjbxx().getSbrq(), "成功!");
//			return CAConstants.SUCCESSSB;
			return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, nsrJbxxdjbVO
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					nsrJbxxdjbVO.getSchemaVersion());
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"企业所得税纳税人基本信息表",nsrJbxxdjbVO.getNsrjbxx().getSbrq(), "失败!");
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
		UserData ud = (UserData) request.getSession(false).getAttribute(
				"UserData");
		if (ud == null) {
			System.out.println("session is null");
		}

		String jsjdm = ud.yhid;

		Timestamp curDate = new Timestamp(System.currentTimeMillis());
        //mass 增加 2013-12-6
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		NsrJbxxBO nsrJbxxBO = null;
		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		
		QysdsUtil qysdsUtil = new QysdsUtil();
		try {
			
			 CheckBean checkBean = new CheckBean();	
	         checkBean.setJsjdm(ud.getYhid());
	         checkBean.createZgrqByCurrenttimeY();
	         QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			//检查是否是试点区县，适用时间20090201-20090215
//			checkIsTestTaxpayer(request,response);
			
			// 取得登记基本数据
			djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
			pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			
			vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
			vo.setProcessor(ProcessorNames.NSRJBXXDJB_PROCESSOR);
			vo.setData(pDataMap);
			vo.setUserData(ud);
			
			// 调用后台查询数据构造nsrJbxxBO
			nsrJbxxBO = (NsrJbxxBO) ShenbaoProxy.getInstance().process(vo);
			//mass 增加 2013-12-6
			nsrJbxxBO.setSbrq(sdf.format(curDate));
			nsrJbxxdjbVO = qysdsUtil.NsrJbxxconvert2XMLVO(nsrJbxxBO, djJbsj,
					Constant.NSRJBXXB);
			
			/*---add by lijn20140617 页面上也没有对应的名称目的是传过去不报错 (增加征管范围鉴定后代替税源标识)*/
			if(CodeConstant.CODE_QYSDS_ZGFWJD_ZFJGJZBSZJG.equals(checkBean.getJdlx()))
			{
				checkBean.setJdlx(CodeConstant.CODE_QYSDS_ZGFWJD_DLNSR);
			}
			nsrJbxxdjbVO.getNsrjbxx().setSybs(checkBean.getJdlx());
			
			
			
			String tmpxml = nsrJbxxdjbVO.toXML();
			System.out.println("show xml:" + tmpxml);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					nsrJbxxdjbVO.getSchemaVersion());

			
			String link = TinyTools.getProperty("WSSB_QYSDS_DOWN_LINK");
			
			
			String[] link_array = TinyTools.divideString(link, ";");
			
			request.setAttribute(Constant.REQUEST_LINK_QYSDS,link_array);
			
			
			// if (true) throw new Exception("test");

			// 转向企业所得税亏损申报页面
			return CAConstants.SHOW;
		} catch (Exception ex) {
			// /3.9.设置空xml，转向失败页面
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, nsrJbxxdjbVO
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					nsrJbxxdjbVO.getSchemaVersion());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限
		if (!SbzlAccess.getAuthority(SbzlAccess.NSRJBXXB, request)) {
			return CAConstants.NOPERMISSION;
		}

		return null;
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return "Return";
	}

	
	/**检查是否是试点区县，适用时间20090201-20090215
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void checkIsTestTaxpayer(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// 检查是否是试点区县，适用时间20090201-20090215

		

    	Date now = new Date();

        int fromDate = Integer.parseInt("20090201");
        int toDate = Integer.parseInt("20090215");
        
        int nowDate = Integer.parseInt((new SimpleDateFormat("yyyyMMdd")).format(now));

        if (nowDate <= toDate && nowDate >= fromDate)
        {
    		request.setAttribute("_REQ_KEY_TEST_ORG_USE", "1");
    		

    		// 登记基本数据
    		SWDJJBSJ djJbsj = null;
    		
    		//试点区县 崇文 海淀		
    		HashMap testOrg = new HashMap();
    		testOrg.put("03", "03");
    		testOrg.put("06", "06");
    		
    		// 取得登记基本数据
    		djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
    		
    		String swjgzzjgdm = djJbsj.getSwjgzzjgdm();
    		
    		String swjgzzjgdmSub = swjgzzjgdm.substring(0,2);
    		
    		String testOrgMessage = "";
    		
    		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%\n:getQxdm:"+djJbsj.getQxdm() 
    				+ ":getSwjgzzjgdm:" +djJbsj.getSwjgzzjgdm()
    				+ ":swjgzzjgdm:" + swjgzzjgdm
    				+":swjgzzjgdmSub:" + swjgzzjgdmSub
    				+"\n");
    		
    		if(testOrg.containsKey(swjgzzjgdmSub)){

        		request.setAttribute("_REQ_KEY_TEST_ORG_TEST", "1");
    			
    			testOrgMessage = "尊敬的纳税人：2008年度企业所得税汇算清缴申报系统于2009-2-1至2009-2-15试运行，此阶段您填报的申报数据可能在系统正式运行后需要调整。由此给您带来的不便，请您谅解！";
    			
    		}else{

        		request.setAttribute("_REQ_KEY_TEST_ORG_TEST", "0");

    			testOrgMessage = "尊敬的纳税人：2008年度企业所得税汇算清缴申报系统目前处于试运行阶段。由于您是非试点用户，系统暂时无法受理您的申报。请您近期关注北京市地方税务局官方网站（http://www.tax861.gov.cn），我们将及时发布最新的相关信息。由此给您带来的不便，请您谅解！";
    			
    		}
    		
    		request.setAttribute("_REQ_KEY_TEST_ORG_MSG", testOrgMessage);
    		
        }

		
		
	}

}