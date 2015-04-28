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
import com.syax.common.web.action.ActionErrors;
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
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuForm;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChengzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChuzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWZiyongVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChengzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChuzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDZiyongVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDJBXX;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

public class FangtuAction extends FangtuBaseAction {

	private static Logger logger = Logger.getLogger(FangtuAction.class);
	protected int cat = 1;
	protected FangtuForm form;
	
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		logger.debug("execute FangtuAction Show.");
		request.setAttribute("PAGE_TITLE", "房屋土地情况登记");
		request.setAttribute("PAGE_POSITION", "综合服务管理信息系统>房屋土地情况登记");
		
		try {

			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			UserData userData = (UserData) getUserData(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			String jsjdm = jbsj.getJsjdm();
			String wzqyFlag=Utils.isWzqy(jbsj.getDjzclxdm());
			request.setAttribute("WZQY_FLAG",wzqyFlag);
			logger.debug("jsjdm: [" + jsjdm + "]");
			logger.debug("Wzqy Flag: [" + wzqyFlag + "]");
			
			/**
			 * TODO 0. 解析xml to vo 1、判断是否要进行处理的数据 2、根据destCat进行查询 3、将结果转换为xml
			 */

			// 0. 解析xml to vo
			String xmldata = request
					.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

			logger.debug("xmldata: " + xmldata);

			FangtuVO fangtuVO = new FangtuVO();
			try {
				if (xmldata != null) {
					// XMLParseHelper.parseXMLString(fangtuVO, xmldata,
					// XMLParseHelper.XERCES_PARSER, true,true);
					XMLParseHelper.parseXMLString(fangtuVO, xmldata,
							XMLParseHelper.VTDXML_PARSER, false, true);
				}
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}

			logger.debug("parsed fangtuVO: " + fangtuVO.toXML());

			// 1、判断是否要进行处理的数据
			List list = fangtuVO.getFwZiyongList();

			// 2、根据 cat 设置Processor, 根据 destCat 进行查询
			int destCat = parseDestCat(fangtuVO);
			cat = parseCat(fangtuVO);

			VOPackage voPackage = new VOPackage();
			switch (cat) {
			case ConstantFangtu.CAT_FW_ZIYONG:
				voPackage.setProcessor(ConstantFangtu.FW_ZIYONG_PROCESSOR);
				break;
			case ConstantFangtu.CAT_FW_CHUZU:
				voPackage.setProcessor(ConstantFangtu.FW_CHUZU_PROCESSOR);
				break;
			case ConstantFangtu.CAT_FW_CHENGZU:
				voPackage.setProcessor(ConstantFangtu.FW_CHENGZU_PROCESSOR);
				break;
			case ConstantFangtu.CAT_TD_ZIYONG:
				voPackage.setProcessor(ConstantFangtu.TD_ZIYONG_PROCESSOR);
				break;
			case ConstantFangtu.CAT_TD_CHUZU:
				voPackage.setProcessor(ConstantFangtu.TD_CHUZU_PROCESSOR);
				break;
			case ConstantFangtu.CAT_TD_CHENGZU:
				voPackage.setProcessor(ConstantFangtu.TD_CHENGZU_PROCESSOR);
				break;
			default:
				voPackage.setProcessor(ConstantFangtu.FW_ZIYONG_PROCESSOR);
				break;
			}

			fangtuVO.setJsjdm(jsjdm);
			fangtuVO.setTaxpayerId(jbsj.getSwdjzh());
			fangtuVO.setTaxpayerName(jbsj.getNsrmc());

			fangtuVO.setDestCat(String.valueOf(destCat));
			fangtuVO.setCat(String.valueOf(cat));

			if (fangtuVO.getInputDate() == null
					|| fangtuVO.getInputDate().trim().equals("")) {
				fangtuVO.setInputDate(DateHelper.sysdate());
			}

			voPackage.setData(fangtuVO);
			voPackage.setUserData(userData);
			voPackage.setAction(destCat);
			form = (FangtuForm) ShenbaoProxy.getInstance().process(
					voPackage);

			// 3、将结果转换为xml

			convert2XMLVOResult(fangtuVO, request, destCat, form);

			fangtuVO.setCat(destCat + "");
			
			String tmpxml = fangtuVO.toXML();
			logger.debug("tmpxml: " + tmpxml );
			
			request.setAttribute("CA_XML_DATA", tmpxml);
			request.setAttribute("CA_XML_XSLT_VERSION", fangtuVO
					.getXsltVersion());
			request.setAttribute("CA_XML_SCHEMA_VERSION", fangtuVO
					.getSchemaVersion());

			request.setAttribute("fwzy_total", form.getFwZiyongTotal());
			request.setAttribute("fwcz_total", form.getFwChuzuTotal());
			request.setAttribute("tdzy_total", form.getTdZiyongTotal());
			request.setAttribute("tdcz_total", form.getTdChuzuTotal());
			
			logger.debug("return fangtu_" + destCat);
			return "fangtu_" + destCat;

		} catch ( com.ttsoft.framework.exception.ApplicationException ae ) {
			logger.error(ae.getMessage(), ae);
			ActionErrors errors = new ActionErrors();
            errors.addError(ae.getMessage());
            
			saveErrors(request, errors);
			logger.debug("return fangtu_" + cat);
			return "fangtu_" + cat;
		} catch ( com.ttsoft.framework.exception.BaseException be ) {
			logger.error(be.getMessage(), be);
			ActionErrors errors = new ActionErrors();
            errors.addError(be.getMessage());
			saveErrors(request, errors);
			logger.debug("return fangtu_" + cat);
			return "fangtu_" + cat;
		}
		catch (Exception ex) {
		
			logger.error(ex.getMessage(), ex);
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
			
		} finally {
			//取得证件类型代码表
			List zjlxList = CodeTableUtil.getCodeTableList(request,CodeTable.ZJLX_LIST );
			request.setAttribute("zjlx_list",zjlxList);
			logger.debug("zjlx_list: " + zjlxList.size() );
			
			//取得坐落区县
			request.setAttribute("zlqx_list", Utils.getRegionZlqxList());
			
			if ( form.getFwZiyongTotal() == null ) 
				request.setAttribute("fwzy_total", new FwZiyongTotal());
			if ( form.getFwChuzuTotal() == null )
				request.setAttribute("fwcz_total", new FwChuzuTotal());
			if ( form.getFwZiyongTotal() == null )
				request.setAttribute("tdzy_total", new TdZiyongTotal());
			if ( form.getFwChuzuTotal() == null )
				request.setAttribute("tdcz_total", new TdChuzuTotal());
			
		}
	}

	/**
	 * @param fangtuVO
	 * @return
	 */
	private int parseCat(FangtuVO fangtuVO) {
		String catString = fangtuVO.getCat();
		int cat = 1;
		if (catString == null) {
			cat = 1;
		} else {
			try {
				cat = Integer.parseInt(catString);
			} catch (Exception e) {
				logger.warn("cat 域不合法，缺省设置为1");
				cat = 1;
			}
		}
		return cat;
	}

	/**
	 * @param fangtuVO
	 * @return
	 */
	private int parseDestCat(FangtuVO fangtuVO) {
		String destCatString = fangtuVO.getDestCat();
		int destCat = 1;
		if (destCatString == null) {
			destCat = 1;
		} else {
			try {
				destCat = Integer.parseInt(destCatString);
			} catch (Exception e) {
				logger.warn("destCat 目标域不合法，缺省设置为1");
				destCat = 1;
			}
		}
		return destCat;
	}

	/**
	 * 转换 XML 到 VO
	 * 
	 * @param fangtuVO
	 * @param request
	 * @param destCat
	 * @param fangtuForm
	 * @return
	 * @throws ApplicationException
	 */
	private FangtuVO convert2XMLVOResult(FangtuVO fangtuVO,
			HttpServletRequest request, int destCat, FangtuForm fangtuForm)
			throws ApplicationException {

		logger.debug("convert2XMLVOResult: destCat:[" + destCat + "]");
		// TODO set xml header
		fangtuVO.setSchemaVersion("20000101");
		fangtuVO.setXsltVersion("20000101");
		fangtuVO.setYwlx("1");
		fangtuVO.setYwczlx("1");
		
		logger.debug("fangtuForm: " + fangtuForm.toString() );
		
		fangtuVO.setTotalItemsNum(fangtuForm.getTotalItemsNum());
		fangtuVO.setTotalPageNum(fangtuForm.getTotalPageNum());
		fangtuVO.setPageSize(fangtuForm.getPageSize());
		fangtuVO.setCurrentPageNum(fangtuForm.getCurrentPageNum());

		switch (destCat) {
		case ConstantFangtu.CAT_FW_ZIYONG:
			fangtuVO.setFwZiyongList(parseList(fangtuForm, ZYFWJBXX.class,
					FWZiyongVO.class));
			break;
		case ConstantFangtu.CAT_FW_CHUZU:
			fangtuVO.setFwChuzuList(parseList(fangtuForm, CZFWJBXX.class,
					FWChuzuVO.class));
			break;
		case ConstantFangtu.CAT_FW_CHENGZU:
			fangtuVO.setFwChengzuList(parseList(fangtuForm, CZUFWJBXX.class,
					FWChengzuVO.class));
			break;
		case ConstantFangtu.CAT_TD_ZIYONG:
			fangtuVO.setTdZiyongList(parseList(fangtuForm, ZYTDJBXX.class,
					TDZiyongVO.class));
			break;
		case ConstantFangtu.CAT_TD_CHUZU:
			fangtuVO.setTdChuzuList(parseList(fangtuForm, CZTDJBXX.class,
					TDChuzuVO.class));
			break;
		case ConstantFangtu.CAT_TD_CHENGZU:
			fangtuVO.setTdChengzuList(parseList(fangtuForm, CZUTDJBXX.class,
					TDChengzuVO.class));
			break;
		default:
			break;
		}
		
		logger.debug("fantuVO: " + fangtuVO.toXML());

		return fangtuVO;
	}

	/**
	 * @param fangtuForm
	 * @param clazz
	 *            TODO
	 * @param voClass
	 *            TODO
	 * @return
	 * @throws ApplicationException
	 */
	private List parseList(FangtuForm fangtuForm, Class clazz, Class voClass)
			throws ApplicationException {

		List vos = new ArrayList();
		if (fangtuForm == null)
			return vos;

		List list = fangtuForm.getList();
		if (list != null) {
			logger.debug("list size: " + list.size());
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object vo = iter.next();
				

				XMLVOInterface xmlvo;

				try {
					xmlvo = (XMLVOInterface) voClass.newInstance();
				} catch (Exception e1) {
					logger.error("实例化房土时出错", e1);
					e1.printStackTrace();
					throw new ApplicationException("实例化房土时出错", e1);
				}
				try {
					BeanUtils.copyProperties(xmlvo, vo);
					logger.debug("-----------------xmlvo-------------------");
					logger.debug(xmlvo.toXML());
				} catch (Exception e) {
					logger.error("房土属性复制时出错", e);
					e.printStackTrace();
					throw new ApplicationException("房土属性复制时出错", e);
				}
				vos.add(xmlvo);
			}
		}
		logger.debug("return vos size: " + vos.size() );
		return vos;
	}

}
