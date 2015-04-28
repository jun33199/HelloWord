package com.ttsoft.bjtax.shenbao.fangtu.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.xml.XMLVOInterface;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.DateHelper;
import com.ttsoft.bjtax.shenbao.fangtu.FangtuAlterBVO;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuForm;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChengzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChuzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWZiyongVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.LabelValueXMLVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChengzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChuzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDZiyongVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.AlterFangtuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.FWChengzuAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.FWChengzuDataVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.FWChuzuAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.FWChuzuDataVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.FWZiyongAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.FWZiyongDataVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDChengzuAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDChengzuDataVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDChuzuAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDChuzuDataVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDZiyongAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDZiyongDataVO;
import com.ttsoft.bjtax.shenbao.model.domain.JMZC;
import com.ttsoft.bjtax.shenbao.model.domain.Zjlx;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.util.VOPackage;

public class FangtuAlterAction extends FangtuBaseAction {

	private static Logger logger = Logger.getLogger(FangtuAlterAction.class);

	private String zc_options = "";
	private List zjlxList = new ArrayList();
	
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		logger.debug("execute FangtuAlterAction Show.");

		request.setAttribute("PAGE_TITLE", "���������������Ǽ�");
		request.setAttribute("PAGE_POSITION", "�ۺϷ��������Ϣϵͳ>���������������Ǽ�");
		try {
			UserData userData = (UserData) getUserData(request);
			Map djMap;
			String jsjdm="";
			SWDJJBSJ jbsj=new SWDJJBSJ();
			if(userData.yhlx.equals(CodeConstants.YHLX_WSYH)){
				/**
				 * �����û�
				 */
				// ȡ�õǼ�����
				djMap = (Map) FriendHelper.getDjInfo(request);
				jbsj = (SWDJJBSJ) djMap.get("JBSJ");
				jsjdm = jbsj.getJsjdm();
			}else if(userData.yhlx.equals(CodeConstants.YHLX_XTYH)){
				/**
				 * �ڲ��û�
				 */
				jsjdm=request.getParameter("jsjdm")==null?"":request.getParameter("jsjdm");
				if(!jsjdm.equals("")){
					request.getSession(false).setAttribute("SESSION_ID_FT_JSJDM",jsjdm);
				}else{
					String session_jsjdm=(String)request.getSession(false).getAttribute("SESSION_ID_FT_JSJDM");
					if(session_jsjdm!=null && !session_jsjdm.equals("")){
						jsjdm=session_jsjdm;
					}
				}
				
				if (jsjdm!=null && !jsjdm.equals("")) {
					djMap = (Map) FriendHelper.getDjInfo(jsjdm);
					jbsj = (SWDJJBSJ) djMap.get("JBSJ");
				}
			}else{
				throw new ApplicationException("�Ƿ����û����ͣ�����ϵͳ����Ա��ϵ��");
			}
			logger.debug("jsjdm: [" + jsjdm + "]");

			/**
			 * TODO 0. ����xml to vo 1���ж��Ƿ�Ҫ���д�������� 2������destCat���в�ѯ 3�������ת��Ϊxml
			 */

			// 0. ����xml to vo
			String xmldata = request
					.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

			logger.debug("xmldata: " + xmldata);

			AlterFangtuVO fangtuAlterVO = new AlterFangtuVO();
			try {
				if (xmldata != null) {
					// XMLParseHelper.parseXMLString(fangtuVO, xmldata,
					// XMLParseHelper.XERCES_PARSER, true,true);
					XMLParseHelper.parseXMLString(fangtuAlterVO, xmldata,
							XMLParseHelper.VTDXML_PARSER, false, true);
				}
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}

			logger.debug("parsed fangtuAlterVO: " + fangtuAlterVO.toXML());

			// 1���ж��Ƿ�Ҫ���д��������
			List list = fangtuAlterVO.getFwZiyongList();
			if (list != null) {
				logger.debug("fw ziyong list: " + list.size());
			}

			// 2������ cat ����Processor, ���� destCat ���в�ѯ
			int destCat = parseDestCat(fangtuAlterVO);
			int cat = parseCat(fangtuAlterVO);

			VOPackage voPackage = new VOPackage();
			switch (cat) {
			case ConstantFangtu.CAT_FW_ZIYONG:
				voPackage
						.setProcessor(ConstantFangtu.ALTER_FW_ZIYONG_PROCESSOR);
				break;
			case ConstantFangtu.CAT_FW_CHUZU:
				voPackage.setProcessor(ConstantFangtu.ALTER_FW_CHUZU_PROCESSOR);
				break;
			case ConstantFangtu.CAT_FW_CHENGZU:
				voPackage
						.setProcessor(ConstantFangtu.ALTER_FW_CHENGZU_PROCESSOR);
				break;
			case ConstantFangtu.CAT_TD_ZIYONG:
				voPackage
						.setProcessor(ConstantFangtu.ALTER_TD_ZIYONG_PROCESSOR);
				break;
			case ConstantFangtu.CAT_TD_CHUZU:
				voPackage.setProcessor(ConstantFangtu.ALTER_TD_CHUZU_PROCESSOR);
				break;
			case ConstantFangtu.CAT_TD_CHENGZU:
				voPackage
						.setProcessor(ConstantFangtu.ALTER_TD_CHENGZU_PROCESSOR);
				break;
			default:
				voPackage
						.setProcessor(ConstantFangtu.ALTER_FW_ZIYONG_PROCESSOR);
				break;
			}

			fangtuAlterVO.setJsjdm(jsjdm);
			fangtuAlterVO.setTaxpayerId(jbsj.getSwdjzh());
			fangtuAlterVO.setTaxpayerName(jbsj.getNsrmc());

			fangtuAlterVO.setDestCat(String.valueOf(destCat));
			fangtuAlterVO.setCat(String.valueOf(cat));

			if (fangtuAlterVO.getInputDate() == null
					|| fangtuAlterVO.getInputDate().trim().equals("")) {
				fangtuAlterVO.setInputDate(DateHelper.sysdate());
			}

			voPackage.setData(fangtuAlterVO);
			voPackage.setUserData(userData);
			voPackage.setAction(destCat);
			List vos = fangtuAlterVO.getTdZiyongList();

			FangtuForm form = (FangtuForm) ShenbaoProxy.getInstance().process(
					voPackage);

			// 3�������ת��Ϊxml
			convert2XMLVOResult(fangtuAlterVO, request, destCat, form);

			fangtuAlterVO.setCat(destCat + "");

			//�ֵ������
			List zhengceList = form.getZhengceList();
			List zcList = new ArrayList();
			
			if ( zhengceList != null ) {
				for (Iterator iter = zhengceList.iterator(); iter.hasNext();) {
					JMZC jmzc = (JMZC) iter.next();
					zcList.add( new LabelValueXMLVO(jmzc.getJmzcwh(),jmzc.getJmzcdm()));
					
					// ����option javascript
					zc_options += "<option value='" + jmzc.getJmzcdm()
							+ "'>" + jmzc.getJmzcwh() + "</option>";
				}
			}
			
			
			
			// ȡ��֤�����ʹ����
			zjlxList = CodeTableUtil.getCodeTableList(request,
					CodeTable.ZJLX_LIST);
			List zhengjianList = new ArrayList();
			logger.debug("zjlx_list: " + zjlxList.size());
			for (Iterator iter = zjlxList.iterator(); iter.hasNext();) {
				Zjlx zjlx = (Zjlx) iter.next();
				zhengjianList.add( new LabelValueXMLVO(zjlx.getZjlxmc(),zjlx.getZjlxdm()));
			}
			
			fangtuAlterVO.setZhengjianList(zhengjianList);
			fangtuAlterVO.setZhengceList(zcList);
			fangtuAlterVO.setJianMianList(Utils.getJianMianList());
			fangtuAlterVO.setYingShuiList(Utils.getYingShuiList());
			fangtuAlterVO.setJianMianMJList(Utils.getJianMianMJList());
			fangtuAlterVO.setYingShuiMJList(Utils.getYingShuiMJList());
			fangtuAlterVO.setDaiJiaoList(Utils.getDaiJiaoList());
			
			fangtuAlterVO.setRegionList(Utils.getRegionList());
			fangtuAlterVO.setSlList(Utils.getSlList());
			
			String tmpxml = fangtuAlterVO.toXML();

			request.setAttribute("CA_XML_DATA", tmpxml);
			request.setAttribute("CA_XML_XSLT_VERSION", fangtuAlterVO
					.getXsltVersion());
			request.setAttribute("CA_XML_SCHEMA_VERSION", fangtuAlterVO
					.getSchemaVersion());
			
			logger.debug("return fangtu_" + destCat);
			return "fangtu_" + destCat;

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			request.setAttribute("zc_options", zc_options);
			
			request.setAttribute("zjlx_list", zjlxList);
			
//			ȡ����������
			request.setAttribute("zlqx_list", Utils.getRegionZlqxList());
		}
	}
	
	/**
	 * @param fangtuVO
	 * @return
	 */
	private int parseCat(AlterFangtuVO fangtuVO) {
		String catString = fangtuVO.getCat();
		int cat = 1;
		if (catString == null) {
			cat = 1;
		} else {
			try {
				cat = Integer.parseInt(catString);
			} catch (Exception e) {
				logger.warn("cat �򲻺Ϸ���ȱʡ����Ϊ1");
				cat = 1;
			}
		}
		return cat;
	}

	/**
	 * @param fangtuVO
	 * @return
	 */
	private int parseDestCat(AlterFangtuVO fangtuVO) {
		String destCatString = fangtuVO.getDestCat();
		int destCat = 1;
		if (destCatString == null) {
			destCat = 1;
		} else {
			try {
				destCat = Integer.parseInt(destCatString);
			} catch (Exception e) {
				logger.warn("destCat Ŀ���򲻺Ϸ���ȱʡ����Ϊ1");
				destCat = 1;
			}
		}
		return destCat;
	}

	/**
	 * ת�� XML �� VO
	 * 
	 * @param fangtuAlterVO
	 * @param request
	 * @param destCat
	 * @param fangtuForm
	 * @return
	 * @throws ApplicationException
	 */
	private AlterFangtuVO convert2XMLVOResult(AlterFangtuVO fangtuAlterVO,
			HttpServletRequest request, int destCat, FangtuForm fangtuForm)
			throws ApplicationException {

		logger.debug("convert2XMLVOResult: destCat:[" + destCat + "]");
		// TODO set xml header
		fangtuAlterVO.setSchemaVersion("20000101");
		fangtuAlterVO.setXsltVersion("20000101");
		fangtuAlterVO.setYwlx("1");
		fangtuAlterVO.setYwczlx("1");

		fangtuAlterVO.setTotalItemsNum(fangtuForm.getTotalItemsNum());
		fangtuAlterVO.setTotalPageNum(fangtuForm.getTotalPageNum());
		fangtuAlterVO.setPageSize(fangtuForm.getPageSize());
		fangtuAlterVO.setCurrentPageNum(fangtuForm.getCurrentPageNum());
		
		switch (destCat) {
		case ConstantFangtu.CAT_FW_ZIYONG:
			fangtuAlterVO.setFwZiyongList(parseList(fangtuForm, FWZiyongVO.class,
					FWZiyongAlterVO.class, FWZiyongDataVO.class));
			break;
		case ConstantFangtu.CAT_FW_CHUZU:
			fangtuAlterVO.setFwChuzuList(parseList(fangtuForm, FWChuzuVO.class,
					FWChuzuAlterVO.class, FWChuzuDataVO.class));
			break;
		case ConstantFangtu.CAT_FW_CHENGZU:
			fangtuAlterVO.setFwChengzuList(parseList(fangtuForm,
					FWChengzuVO.class, FWChengzuAlterVO.class, FWChengzuDataVO.class));
			break;
		case ConstantFangtu.CAT_TD_ZIYONG:
			fangtuAlterVO.setTdZiyongList(parseList(fangtuForm, TDZiyongVO.class,
					TDZiyongAlterVO.class, TDZiyongDataVO.class));
			break;
		case ConstantFangtu.CAT_TD_CHUZU:
			fangtuAlterVO.setTdChuzuList(parseList(fangtuForm, TDChuzuVO.class,
					TDChuzuAlterVO.class, TDChuzuDataVO.class));
			break;
		case ConstantFangtu.CAT_TD_CHENGZU:
			fangtuAlterVO.setTdChengzuList(parseList(fangtuForm,
					TDChengzuVO.class, TDChengzuAlterVO.class, TDChengzuDataVO.class));
			break;
		default:
			break;
		}

		return fangtuAlterVO;
	}

	/**
	 * ��form�е�list��ֵ���з�װ, ���xmlVO��ʽ
	 * �������İ�������������: �Ǽ���ϢVO, �����ϢVO, ������ʶ
	 * 
	 * @param fangtuForm
	 * @param regClass �Ǽ�����xmlvo
	 * @param alterClass �������xmlvo
	 * @param dataClass ������xmlvo
	 * @return
	 * @throws ApplicationException
	 */
	private List parseList(FangtuForm fangtuForm, Class regClass,
			Class alterClass, Class dataClass) throws ApplicationException {

		List vos = new ArrayList();
		if (fangtuForm == null)
			return vos;

		List list = fangtuForm.getList();
		if (list != null) {
			logger.debug("list size: " + list.size());
			for (Iterator iter = list.iterator(); iter.hasNext();) {
//				Map map = (Map) iter.next();
//				Object regVO = map.get("regVO");
//				Object alterVO = map.get("alterVO");
//				String opFlag = (String) map.get("opFlag");
				FangtuAlterBVO bvo = (FangtuAlterBVO) iter.next();
				Object regVO = bvo.getRegData();
				Object alterVO = bvo.getAlterData();
				String opFlag = bvo.getOpFlag();

				XMLVOInterface regXmlvo;
				XMLVOInterface alterXmlvo;
				XMLVOInterface xmlvo;

				try {

					regXmlvo = (XMLVOInterface) regClass.newInstance();
					alterXmlvo = (XMLVOInterface) alterClass.newInstance();
					xmlvo = (XMLVOInterface) dataClass.newInstance();
					
					
				} catch (Exception e1) {
					logger.error("ʵ��������ʱ����", e1);
					e1.printStackTrace();
					throw new ApplicationException("ʵ��������ʱ����", e1);
				}
				
				try {
					BeanUtils.copyProperties(regXmlvo, regVO);
					BeanUtils.copyProperties(alterXmlvo, alterVO);
					
					BeanUtils.copyProperty(xmlvo, "regVO", regXmlvo);
					BeanUtils.copyProperty(xmlvo, "alterVO", alterXmlvo);
					BeanUtils.copyProperty(xmlvo, "opFlag", opFlag);
					
				} catch (Exception e) {
					logger.error("�������Ը���ʱ����", e);
					e.printStackTrace();
					throw new ApplicationException("�������Ը���ʱ����", e);
				}
				vos.add(xmlvo);
			}
		}

		return vos;
	}

}
