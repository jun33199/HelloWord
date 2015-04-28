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
 * @version 1.0 ��˰�˻�����Ϣ��Action
 */
public class NsrJbxxdjbAction extends DcBaseAction {

	private String errorMessage = "";

	public NsrJbxxdjbAction() {
	}

	protected boolean validate(NsrJbxxdjbVO nsrjbxx) {
		if (!nsrjbxx.getYwlx().equals(Constant.CA_YWLXDM_NSRJBXXB)) {
			errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
			return false;
		}
		if (!(nsrjbxx.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || nsrjbxx
				.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
			System.out.println("ҵ��������ʹ���" + nsrjbxx.getYwczlx());
			errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
			return false;
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!nsrjbxx.getNsrjbxx().getSbrq().startsWith(df.format(now))) {
			errorMessage = "�걨���ڴ���";
			return false;
		}
		return true;
	}

	protected int getActionID() {
		return com.ttsoft.bjtax.shenbao.util.SbzlAccess.NSRJBXXB;
	}

	/**
	 * ������˰�˻�����Ϣ��
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

			//����У��
			CheckBean checkBean = new CheckBean();	
			checkBean.setJsjdm(ud.getYhid());
			checkBean.createZgrqByCurrenttimeY();
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
						
			//String sybs=FriendHelper.getNsrSybs(djJbsj);
			String sybs=checkBean.getJdlx();  					//modified by lijn 20140617
			if(CodeConstant.CODE_QYSDS_ZGFWJD_OTHER.equals(sybs)){
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"����ҵ���ǵ�˰��Ͻ����ҵ����˰Ӧ���������ܱ��������Ϣ�ǼǱ�");
				request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"����ҵ���ǵ�˰��Ͻ����ҵ����˰Ӧ���������ܱ��������Ϣ�ǼǱ�");
				request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
				LogUtil.getInstance().log(FriendHelper.getUserData(request),"��ҵ����˰��˰�˻�����Ϣ��", nsrJbxxdjbVO.getNsrjbxx().getSbrq(), "�ɹ�!");
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
			
			// ȡ��Form

			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, nsrJbxxBO);//BO
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);//�Ǽ�����
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);//����ԭ��
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, nsrJbxxdjbVO);//VO

			// ����VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.NSRJBXXDJB_PROCESSOR);
			vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// ���ú�̨Procxy

			// ���û�ִ����ҳ��ĺ���������
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
						"�����걨���ݱ�ɾ����������Ҫ������¼�����ݽ��б���!");
				request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
						"�����걨���ݱ�ɾ����������Ҫ������¼�����ݽ��б���!");
			}else{
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						"��ҵ����˰��˰�˻�����Ϣ��" + CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
				request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
						"��ҵ����˰��˰�˻�����Ϣ��" + CAUtils.getTsxx(nsrJbxxdjbVO.getYwczlx()));
			}
			
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰��˰�˻�����Ϣ��", nsrJbxxdjbVO.getNsrjbxx().getSbrq(), "�ɹ�!");
//			return CAConstants.SUCCESSSB;
			return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, nsrJbxxdjbVO
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					nsrJbxxdjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					nsrJbxxdjbVO.getSchemaVersion());
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰��˰�˻�����Ϣ��",nsrJbxxdjbVO.getNsrjbxx().getSbrq(), "ʧ��!");
			throw ExceptionUtil.getBaseException(ex);
		}
	}


	/**
	 * ��ʼ����ҵ����˰�걨����
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// �Ǽǻ�������
		SWDJJBSJ djJbsj = null;
		// ����VOPackage
		VOPackage vo = new VOPackage();
		Map pDataMap = new HashMap();
		UserData ud = (UserData) request.getSession(false).getAttribute(
				"UserData");
		if (ud == null) {
			System.out.println("session is null");
		}

		String jsjdm = ud.yhid;

		Timestamp curDate = new Timestamp(System.currentTimeMillis());
        //mass ���� 2013-12-6
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		NsrJbxxBO nsrJbxxBO = null;
		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		
		QysdsUtil qysdsUtil = new QysdsUtil();
		try {
			
			 CheckBean checkBean = new CheckBean();	
	         checkBean.setJsjdm(ud.getYhid());
	         checkBean.createZgrqByCurrenttimeY();
	         QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			//����Ƿ����Ե����أ�����ʱ��20090201-20090215
//			checkIsTestTaxpayer(request,response);
			
			// ȡ�õǼǻ�������
			djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
			pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			
			vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
			vo.setProcessor(ProcessorNames.NSRJBXXDJB_PROCESSOR);
			vo.setData(pDataMap);
			vo.setUserData(ud);
			
			// ���ú�̨��ѯ���ݹ���nsrJbxxBO
			nsrJbxxBO = (NsrJbxxBO) ShenbaoProxy.getInstance().process(vo);
			//mass ���� 2013-12-6
			nsrJbxxBO.setSbrq(sdf.format(curDate));
			nsrJbxxdjbVO = qysdsUtil.NsrJbxxconvert2XMLVO(nsrJbxxBO, djJbsj,
					Constant.NSRJBXXB);
			
			/*---add by lijn20140617 ҳ����Ҳû�ж�Ӧ������Ŀ���Ǵ���ȥ������ (�������ܷ�Χ���������˰Դ��ʶ)*/
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

			// ת����ҵ����˰�����걨ҳ��
			return CAConstants.SHOW;
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
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
		// ���Ȩ��
		if (!SbzlAccess.getAuthority(SbzlAccess.NSRJBXXB, request)) {
			return CAConstants.NOPERMISSION;
		}

		return null;
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return "Return";
	}

	
	/**����Ƿ����Ե����أ�����ʱ��20090201-20090215
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void checkIsTestTaxpayer(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// ����Ƿ����Ե����أ�����ʱ��20090201-20090215

		

    	Date now = new Date();

        int fromDate = Integer.parseInt("20090201");
        int toDate = Integer.parseInt("20090215");
        
        int nowDate = Integer.parseInt((new SimpleDateFormat("yyyyMMdd")).format(now));

        if (nowDate <= toDate && nowDate >= fromDate)
        {
    		request.setAttribute("_REQ_KEY_TEST_ORG_USE", "1");
    		

    		// �Ǽǻ�������
    		SWDJJBSJ djJbsj = null;
    		
    		//�Ե����� ���� ����		
    		HashMap testOrg = new HashMap();
    		testOrg.put("03", "03");
    		testOrg.put("06", "06");
    		
    		// ȡ�õǼǻ�������
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
    			
    			testOrgMessage = "�𾴵���˰�ˣ�2008�����ҵ����˰��������걨ϵͳ��2009-2-1��2009-2-15�����У��˽׶�������걨���ݿ�����ϵͳ��ʽ���к���Ҫ�������ɴ˸��������Ĳ��㣬�����½⣡";
    			
    		}else{

        		request.setAttribute("_REQ_KEY_TEST_ORG_TEST", "0");

    			testOrgMessage = "�𾴵���˰�ˣ�2008�����ҵ����˰��������걨ϵͳĿǰ���������н׶Ρ��������Ƿ��Ե��û���ϵͳ��ʱ�޷����������걨���������ڹ�ע�����еط�˰��ֹٷ���վ��http://www.tax861.gov.cn�������ǽ���ʱ�������µ������Ϣ���ɴ˸��������Ĳ��㣬�����½⣡";
    			
    		}
    		
    		request.setAttribute("_REQ_KEY_TEST_ORG_MSG", testOrgMessage);
    		
        }

		
		
	}

}