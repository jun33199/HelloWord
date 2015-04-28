//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsnd\\web\\CzzsndAction.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.util.NumberUtils;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbfpbl;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbqy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzmxsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Czzbsj04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Czzsnb04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Fpblsj04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Nsrxx04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Sbsjlist04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Sbxm04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Sbxx04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Tzfmx04VO;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Tzfsj04VO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author ligr  guzx
 * 
 * 查账征收个人独资和合伙企业年度申报
 */
public class Czzsnd04Action extends DcBaseAction {
	// 转向常量
	public static final String SELF = "Show";

	public static final String FAILING = "Failing";

	public static final String SUCCESS = "Success";

	/**
	 * 权限控制
	 * 
	 * @return int
	 */
	protected int getActionID() {
		return SbzlAccess.CZND;
	}

	/**
	 * 保存申报信息 
	 * 接收到的xml字符串是由多个投资方数据组成，首先把该字符串分割成单个投资方数据，然后进行操作
	 * 对单个投资方数据要分别进行签名，因此由多个电子原件数据
	 * 在保存申报数据时要把多个投资方的数据合并成一条申报数据，一次进行保存
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		Czzsnb04VO xvo = new Czzsnb04VO();
		Czzsnb04VO czzsnbVO = new Czzsnb04VO();
		Map retmap = null;
		List dzyjList = new ArrayList();
		List lshList = new ArrayList();

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		String signData = request.getParameter("strSignData");
		//分割xmldata和signData
		StringTokenizer st = new StringTokenizer(xmldata, "\r\n");
		StringTokenizer st2 = new StringTokenizer(signData, "\r\n");
        int apptype = 0;
        if (ud.getCaflag()) 
        {
            apptype = Integer.parseInt(request.getParameter("appType"));
        }
		try {
			String oneSignData = "";
			while (st.hasMoreTokens()) {
				//取分割得到的一块xmldata数据
				String oneXmlData = st.nextToken();
				DzyjsjVO dzyj = new DzyjsjVO();
				if (ud.getCaflag()) {
					//取分割得到的一块signData数据
					oneSignData = st2.nextToken();
					try {
						dzyj = dh.verifyAndSign(apptype, oneXmlData,
								oneSignData, ud.getCert(), ud.getSsdwdm());
					} catch (ApplicationException e1) {
						throw ExceptionUtil.getBaseException(e1);
					}
				}
				try {
					//XMLParseHelper.parseXMLString(xvo, oneXmlData,XMLParseHelper.XERCES_PARSER, true, true);
					xvo = new Czzsnb04VO();
					XMLParseHelper.parseXMLString(xvo, oneXmlData,
							XMLParseHelper.VTDXML_PARSER, false, true);
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
				//把多个投资方数据合并成一条申报数据
				czzsnbVO = convertToCzzsnbVO(xvo,czzsnbVO);
				dzyj.setYwczlx(xvo.getYwczlx());
				dzyj.setYwdm(xvo.getYwlx());
				dzyjList.add(dzyj);
			}
            
			Map data = convert2VO(czzsnbVO);
			data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyjList);
			// 构造VOPackage
			VOPackage voPackage = new VOPackage();
			voPackage.setAction(CzzsndActionConstant.INT_ACTION_SAVE);
			voPackage.setData(data);
			voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
			voPackage.setUserData(ud);
			// 调用后台保存数据
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			retmap = (Map) voPackage.getData();
			if (ud.getCaflag()) {
				dzyjList = (List) retmap
						.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				for (int i = 0; i < dzyjList.size(); i++) {
					DzyjsjVO dzyj = (DzyjsjVO) dzyjList.get(i);
					lshList.add(Long.toString(dzyj.getLsh()));
				}
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
						lshList);
			} else {
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			}

			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"查账征收年度申报资料保存成功！");
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_CZND_SAVE);
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"查账征收年度申报资料保存成功！");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"查账征收年度申报资料申报", (xvo.getSbxx()).getSbrq(), "成功!");

			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception e) {
			request.setAttribute("done", new Boolean(false));
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 * 把每一个投资方数据合并成一条包括所有投资方数据的申报数据
	 * @param xvo 每一个投资方的值对象
	 * @param czzsnbVO 所有投资方组成的一条申报数据的值对象
	 * @return Czzsnb04VO
	 */
	private Czzsnb04VO convertToCzzsnbVO(Czzsnb04VO xvo,Czzsnb04VO czzsnbVO) {
		List tzzsj = xvo.getTzfsj();
		for (int i = 0; i < tzzsj.size(); i++) {
			Tzfsj04VO tzfsj = (Tzfsj04VO) tzzsj.get(i);
			czzsnbVO.getTzfsj().add(tzfsj);
		}
		czzsnbVO.setNsrxx(xvo.getNsrxx());
		czzsnbVO.setSbxx(xvo.getSbxx());
		czzsnbVO.setSbsjlist(xvo.getSbsjlist());
		czzsnbVO.setCzzbsj(xvo.getCzzbsj());
		return czzsnbVO;
	}

	/**
	 * 取业务信息进行显示
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 取得计算机代码
		String jsjdm = ((UserData) request.getSession(false).getAttribute(
				SessionKey.USER_DATA)).yhid;
		// 取得CzzsndForm
		CzzsndForm czzsndForm = new CzzsndForm();

		try {
			// 通过税费的接口取投资方数据
			com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			// 取上一年。
			Calendar now = Calendar.getInstance();
			now.add(Calendar.YEAR, -1);
			now.set(Calendar.MONTH, Calendar.DECEMBER);
			now.set(Calendar.DAY_OF_MONTH, 31);
			List tzfList = sfProxy.getTzfInfo(jsjdm, now.getTime());
			if (tzfList == null || tzfList.size() == 0) {
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						"您的投资方数据为空！");
				return CAConstants.SUCCESS;
			} else {
				setCodeTable(request, czzsndForm);

				// 构造VOPackage
				VOPackage voPackage = new VOPackage();
				voPackage.setAction(CzzsndActionConstant.INT_ACTION_QUERY);
				voPackage.setUserData((UserData) request.getSession(false)
						.getAttribute(SessionKey.USER_DATA));
				voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
				voPackage.setData(czzsndForm.beforeQuery(jsjdm, tzfList));
				voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
						voPackage);

				// 因为Czzsnbtzzsj在数据库中的对应表不再提供保存投资者姓名的地方
				// 所以这里把投资者姓名填上以便页面显示
				Map data = (Map) voPackage.getData();
				// 从返回值取得投资者申报数据
				List tzzsbsjList = (List) data
						.get(CzzsndMapConstant.LIST_TZZSBSJ);

				for (int i = 0, size = tzzsbsjList.size(); i < size; i++) {
					// 取出每条投资者申报数据
					Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj) tzzsbsjList.get(i);
					// 循环从税费取到的投资者数据
					for (int j = 0, jSize = tzfList.size(); j < jSize; j++) {
						// 取出投资者数据
						Tzf tzf = (Tzf) tzfList.get(j);
						// 判断是否证件类型代码和证件号码都相等
						if (czzsnbtzzsj.getZjhm().equalsIgnoreCase(
								tzf.getZjhm())
								&& czzsnbtzzsj.getZjlxdm().equalsIgnoreCase(
										tzf.getZjlxdm())) {
							// 相等时设置投资者申报数据的投资者姓名字段
							czzsnbtzzsj.setTzzxm(tzf.getTzfmc());
							// 结束查找当前投资者申报数据中的投资者姓名循环
							break;
						}
					}
				}

				request.setAttribute("done", new Boolean(true));

				Czzsnb04VO xvo = new Czzsnb04VO();
				xvo = convert2XMLVO(request, data, now.getTime());
				String tmpxml = xvo.toXML();

				request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
						xvo.getXsltVersion());
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
						xvo.getSchemaVersion());

				return CAConstants.SHOW;
			}
		} catch (Exception e) {
			request.setAttribute("done", new Boolean(false));
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 * 将XML-VO对象转换为旧的VO对象
	 * @param vo XML-VO对象
	 * @return Map
	 * @throws BaseException
	 */
	private Map convert2VO(Czzsnb04VO vo) throws BaseException {
		Date today = new Date();
		Map sksjrqMap = Skssrq.yearSkssrq(today);
		String nd = (String) sksjrqMap.get(Skssrq.SKSSRQ_ND);
//		CzzsndForm form = new CzzsndForm();
		String jsjdm = vo.getNsrxx().getJsjdm();
		String swjgzzjgdm = vo.getNsrxx().getSwjgzzjgdm();

		// 减免数据List

		Timestamp now = new Timestamp(System.currentTimeMillis());

		// 更新年度主表数据
		Czzsnbzb czzsnbzb = newInstanceCzzsnbzb(jsjdm, nd, CodeConstant.FSDM_WSSB,
						swjgzzjgdm, vo.getCzzbsj().getLrr(), vo.getCzzbsj()
								.getLrrq(), vo.getSbxx().getSkssjsrq(), vo
								.getSbxx().getSkssksrq(), vo.getCzzbsj()
								.getCjsj(), now);

		// 更新投资者数据
		int tzzSize = vo.getTzfsj().size();
		List tzzsbsjList = new ArrayList(tzzSize);
		for (int i = 0; i < tzzSize; i++) {

			Tzfsj04VO atzfsj = (Tzfsj04VO) vo.getTzfsj().get(i);
			Czzsnbtzzsj czzsnbtzzsj = newInstanceTzzsbsj(jsjdm, nd, atzfsj
					.getZjlxdm(), atzfsj.getZjhm(), atzfsj.getCwfzr(),
					swjgzzjgdm, now);
			tzzsbsjList.add(czzsnbtzzsj);
		}

		// 更新企业数据List
		// 1-38，49-50
		List qysbsjList = new ArrayList(40);
		for (int i = 0; i < 40; i++) {
			Sbxm04VO sbxmvo = (Sbxm04VO) vo.getSbsjlist().getSbxm().get(i);
			Czzsnbqy czzsnbqy = newInstanceQysbsj(jsjdm, nd, sbxmvo
					.getHc(), sbxmvo.getBqljs(), swjgzzjgdm, now);
			qysbsjList.add(czzsnbqy);
		}

		// 更新投资者明细数据
		// 39-48

		List tzzsbsjmxList = new ArrayList(tzzSize);

		for (int i = 0; i < tzzSize; i++) {
			List tzzList = new ArrayList(10);
			Tzfsj04VO atzfsj = (Tzfsj04VO) vo.getTzfsj().get(i);
			for (int j = 0; j < 10; j++) {
				Tzfmx04VO mxvo = (Tzfmx04VO) atzfsj.getTzfmx().get(j);
				Czzsnbtzzmxsj czzsnbtzzmxsj = newCzzsnbtzzmxsj(jsjdm, nd,
						atzfsj.getZjlxdm(), atzfsj.getZjhm(), mxvo.getHc(),
						mxvo.getBqljs(), swjgzzjgdm, now);
				tzzList.add(czzsnbtzzmxsj);
			}
			tzzsbsjmxList.add(tzzList);
		}

		// 更新分配比率数据
		// 51-54
		List fpblsjList = new ArrayList(4);
		for (int i = 0; i < 4; i++) {
			Fpblsj04VO fpblvo = (Fpblsj04VO) vo.getSbsjlist().getFpblsj()
					.get(i);
			String bl = fpblvo.getFpbl();
			String ljs = fpblvo.getBqljs();
			if (fpblvo.getFpbl() == null) {
				bl = "0.00";
			}
			if (fpblvo.getBqljs() == null) {
				ljs = "0.00";
			}

			Czzsnbfpbl czzsnbfpbl = newInstanceFpbl(jsjdm, nd, String
					.valueOf(i + 51), bl, ljs, swjgzzjgdm, now);
			fpblsjList.add(czzsnbfpbl);
		}

		Map retMap = new HashMap();
		retMap.put(CzzsndMapConstant.CZZSNBZB, czzsnbzb);
		retMap.put(CzzsndMapConstant.LIST_QYSBSJ, qysbsjList);
		retMap.put(CzzsndMapConstant.LIST_TZZSBSJ, tzzsbsjList);
		retMap.put(CzzsndMapConstant.LIST_FPBLSJ, fpblsjList);
		retMap.put(CzzsndMapConstant.LIST_TZZMX, tzzsbsjmxList);
		return retMap;
	}
	/**
	 * 转换成XML-VO对象
	 * @param request
	 * @param data
	 * @param now
	 * @return
	 * @throws BaseException
	 * @throws ApplicationException
	 */
	private Czzsnb04VO convert2XMLVO(HttpServletRequest request, Map data,
			Date now) throws BaseException, ApplicationException {
		Czzsnb04VO vo = new Czzsnb04VO();
		Nsrxx04VO nsrxx = new Nsrxx04VO();
		Sbxx04VO sbxx = new Sbxx04VO();
		Czzbsj04VO czzbsj = new Czzbsj04VO();
		Sbsjlist04VO sbsjlist = new Sbsjlist04VO();
		List sbxm = new ArrayList();
		List fpblsj = new ArrayList();

		List tzfsj = new ArrayList();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Czzsnbzb czzsnbzb = (Czzsnbzb) data.get(CzzsndMapConstant.CZZSNBZB);
		List qysbsjList = (List) data.get(CzzsndMapConstant.LIST_QYSBSJ);
		List tzzsbsjList = (List) data.get(CzzsndMapConstant.LIST_TZZSBSJ);
		List fpblsjList = (List) data.get(CzzsndMapConstant.LIST_FPBLSJ);
		List tzzsbsjmxList = (List) data.get(CzzsndMapConstant.LIST_TZZMX);
		SWDJJBSJ swdjjbsj = (SWDJJBSJ) data.get(CzzsndMapConstant.JBSJ);
		String exist = (String) data.get(CzzsndMapConstant.CZZSNBEXISTED);
		vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		if (exist != null && exist.equals("1")) {
			vo.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		}

		String nsrmc = swdjjbsj.getNsrmc();

		nsrxx.setJsjdm(swdjjbsj.getJsjdm());
		nsrxx.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
		nsrxx.setNsrmc(nsrmc);
		nsrxx.setQylxdh(swdjjbsj.getJydzlxdm());

		sbxx.setNd(czzsnbzb.getNd());
		sbxx.setSbrq(sdf.format(czzsnbzb.getSbrq()));
		sbxx.setSkssksrq(sdf.format(czzsnbzb.getSkssksrq()));
		sbxx.setSkssjsrq(sdf.format(czzsnbzb.getSkssjsrq()));
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);

		czzbsj.setCjsj(sdf.format(czzsnbzb.getSbrq()));
		czzbsj.setLrr(czzsnbzb.getLrr());
		czzbsj.setLrrq(sdf.format(czzsnbzb.getSbrq()));

		vo.setNsrxx(nsrxx);
		vo.setSbxx(sbxx);
		vo.setCzzbsj(czzbsj);

		// 调用税费管理接口取得减免数据
		List jmsjList = new ArrayList(tzzsbsjList.size());
		com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
		//生成投资方数据tzfsj
		for (int i = 0, size = tzzsbsjList.size(); i < size; i++) {
			Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj) tzzsbsjList.get(i);
			List mxList = (List) tzzsbsjmxList.get(i);
			Tzfsj04VO atzfsj = new Tzfsj04VO();
			atzfsj.setCwfzr(czzsnbtzzsj.getCwfzr());
			atzfsj.setTzzxm(czzsnbtzzsj.getTzzxm());
			atzfsj.setZjhm(czzsnbtzzsj.getZjhm());
			atzfsj.setZjlxdm(czzsnbtzzsj.getZjlxdm());

			// 下面的调用因为使用缺省的国籍代码，未来某个时候会更改过来，
			// 所以就没写到CodeTable去。未来某个时候国籍会从投资者数据中取。
			// 有一种可能，是Czzsnbtzzsj对应的数据库表结构改动，
			// 增加国籍代码字段，所以，漫漫长路。
			boolean result = false;
			try {
				Calendar cc = new GregorianCalendar();
				int year = cc.get(Calendar.YEAR) - 1;
				cc.set(year, Calendar.DECEMBER, 31);
				result = sfProxy.getZRRJm(czzsnbtzzsj.getZjlxdm(), czzsnbtzzsj
						.getZjhm(), "CHN", SzsmdmConstant.GRSDS, cc.getTime());
			} catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}
			atzfsj.setJmsjcontrol(CAUtils.bool2Str(result));
			List mxvol = new ArrayList();
			for (int j = 0; j < mxList.size(); j++) {
				Czzsnbtzzmxsj mxtzfsj = (Czzsnbtzzmxsj) mxList.get(j);
				Tzfmx04VO mxvo = new Tzfmx04VO();
				mxvo
						.setBqljs(StringUtils.bigDeciaml2String(mxtzfsj
								.getBqljs()));
				mxvo.setHc(mxtzfsj.getHc());
				mxvo.setXmmc(CzzsndMapConstant.getInst().getCzmc(
						mxtzfsj.getHc()));
				mxvol.add(mxvo);
			}
			atzfsj.setTzfmx(mxvol);

			tzfsj.add(atzfsj);
			jmsjList.add(result ? Boolean.TRUE : Boolean.FALSE);

		}
		//生成申报申报数据列表
		for (int i = 0, size = qysbsjList.size(); i < size; i++) {
			Czzsnbqy aqysj = (Czzsnbqy) qysbsjList.get(i);
			Sbxm04VO sbxmvo = new Sbxm04VO();
			sbxmvo.setBqljs(StringUtils.bigDeciaml2String(aqysj.getBqljs()));
			sbxmvo.setHc(aqysj.getHc());
			sbxmvo.setXmmc(CzzsndMapConstant.getInst().getCzmc(aqysj.getHc()));
			sbxm.add(sbxmvo);
		}
		//生成分配比例数据
		for (int i = 0, size = fpblsjList.size(); i < size; i++) {
			Czzsnbfpbl afpbl = (Czzsnbfpbl) fpblsjList.get(i);
			Fpblsj04VO fpblvo = new Fpblsj04VO();
			fpblvo.setBqljs(StringUtils.bigDeciaml2String(afpbl.getBqljs()));
			fpblvo.setFpbl(StringUtils.bigDeciaml2String(afpbl.getBl()));
			fpblvo.setHc(afpbl.getHc());
			fpblvo.setXmmc(CzzsndMapConstant.getInst().getCzmc(afpbl.getHc()));
			fpblsj.add(fpblvo);
		}

		sbsjlist.setFpblsj(fpblsj);
		sbsjlist.setSbxm(sbxm);

		vo.setTzfsj(tzfsj);
		vo.setSbsjlist(sbsjlist);
		// XML文档信息
		vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_CZZSGRDZ_NB).substring(8));
		vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_CZZSGRDZ_NB).substring(0, 8));
		vo.setYwlx(CAcodeConstants.YWDM_SB_WS_CZZSGRDZ_NB);

		return vo;
	}
	
	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return CAConstants.RETURN;
	}

	/**
	 * 删除申报信息 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 * @throws BaseException
	 */
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
//		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		Czzsnb04VO xvo = new Czzsnb04VO();

		try {
			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			try {
				//XMLParseHelper.parseXMLString(xvo, xmldata,XMLParseHelper.XERCES_PARSER, true, true);
				//xvo = new Czzsnb04VO();
				XMLParseHelper.parseXMLString(xvo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}

			// 取得Form
//			CzzsndForm czzsndForm = new CzzsndForm();
			// 从Form 取得需删除数据数据
			Map data = convert2VO(xvo);
			// 保存签名元件
			dzyj.setYwczlx(xvo.getYwczlx());
			dzyj.setYwdm(xvo.getYwlx());
			data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			// 构造VOPackage
			VOPackage voPackage = new VOPackage();
			voPackage.setAction(CzzsndActionConstant.INT_ACTION_DELETE);
			voPackage.setData(data);
			voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
			voPackage.setUserData((UserData) request.getSession(false)
					.getAttribute(SessionKey.USER_DATA));
			// 调用后台删除数据
			ShenbaoProxy.getInstance().process(voPackage);

			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"查账征收年度申报表删除成功！");
			request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"查账征收年度申报表删除成功！");
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_CZND_DELETE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"查账征收年度申报表申报", xvo.getSbxx().getSbrq(), "成功!");

			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception e) {
			request.setAttribute("done", new Boolean(false));
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 设置代码表到form。
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param czzsndForm
	 *            CzzsndForm
	 */
	private void setCodeTable(HttpServletRequest request, CzzsndForm czzsndForm) {
		// 取得代码表
		List szsmList = CodeTableUtil.getCodeTableList(request,
				CodeTable.SZSM_LIST_AVAILABLE);

		// 因为代码表的数据只用到其中一部分，为了减少页面代码量，过滤出所需部分
		List czzsndSzsmList = new ArrayList();
		for (int i = 0, size = szsmList.size(); i < size; i++) {
			// 取每一条税种税目
			Szsm szsm = (Szsm) szsmList.get(i);
			if (!szsm.getSzsmdm().startsWith(SzsmdmConstant.GTGSH)) {
				// 非个人所得税的税种税目继续循环
				continue;
			} else if (szsm.getSzsmdm().length() == 4) {
				// 是个人所得税的税种继续循环
				continue;
			} else if (szsm.getSl() == null) {
				// 是个人所得税的没有税率的税种税目继续循环
				continue;
			}  else if (szsm.getSzsmdm().startsWith("05120")) {
				// 是个人所得税的2011年个人所得税个体工商户经营所得调整前的税种税目继续循环
				//added by zhangyj 20111226
				continue;
			} else {
				// 个人所得税的税种税目保存

				czzsndSzsmList.add(szsm);
			}
		}

		// 设置到form去
		request.getSession().setAttribute("slbsjList", czzsndSzsmList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syax.common.web.action.DcBaseAction#beforePerform(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse arg1) {
		if (!SbzlAccess.getAuthority(SbzlAccess.CZND, request)) {

			return CAConstants.NOPERMISSION;
		}
		return null;
	}
    
    
    
    /**
     * 从传过来的参数构造一个 查帐征收年报投资者数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param zjlxdm 证件类型代码
     * @param zjhm 证件号码
     * @param hc 行次
     * @param bqljs 本期累计数
     * @param swjgzzjgdm 机关代码
     * @return List 投资者数据
     */
    private Czzsnbtzzmxsj newCzzsnbtzzmxsj(String jsjdm, String nd,
                                           String zjlxdm, String zjhm,
                                           String hc, String bqljs,
                                           String swjgzzjgdm, Timestamp now)
    {
        Czzsnbtzzmxsj czzsnbtzzmxsj = new Czzsnbtzzmxsj();
        czzsnbtzzmxsj.setBqljs(new BigDecimal(bqljs));
        czzsnbtzzmxsj.setHc(hc);
        czzsnbtzzmxsj.setJsjdm(jsjdm);
        czzsnbtzzmxsj.setNd(nd);
        czzsnbtzzmxsj.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbtzzmxsj.setZjhm(zjhm);
        czzsnbtzzmxsj.setZjlxdm(zjlxdm);
        czzsnbtzzmxsj.setCjrq(now);
        czzsnbtzzmxsj.setLrrq(now);
        czzsnbtzzmxsj.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsnbtzzmxsj;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报主数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param fsdm 方式代码
     * @param swjgzzjgdm 机关代码
     * @param lrr 录入人
     * @param lrrq 录入日期
     * @param ssrqz 所属日期止
     * @param ssrqq 所属日期起
     * @param strCjsj 创建时间
     * @return Czzsnbzb 年报主表
     */
    private Czzsnbzb newInstanceCzzsnbzb(String jsjdm, String nd, String fsdm,
                                         String swjgzzjgdm, String lrr,
                                         String lrrq,
                                         String ssrqz, String ssrqq,
                                         String strCjsj,
                                         Timestamp now)
    {
        Czzsnbzb czzsnbzb = new Czzsnbzb();
        czzsnbzb.setJsjdm(jsjdm);
        czzsnbzb.setNd(nd);
        czzsnbzb.setFsdm(fsdm);
        czzsnbzb.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbzb.setLrr(lrr);
        czzsnbzb.setLrrq(now);
        czzsnbzb.setSbrq(TinyTools.second2Day(now));
        Map skssrqMap = Skssrq.yearSkssrq(new Date());
        czzsnbzb.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        czzsnbzb.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
//        Date cjsj = TinyTools.stringToDate(strCjsj);
        czzsnbzb.setCjrq(now);
        czzsnbzb.setQxdm(swjgzzjgdm.substring(0, 2));
        return czzsnbzb;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报企业数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param hc 行次
     * @param bqljs 本期累计数
     * @param swjgzzjgdm 机关代码
     * @return Czzsnbqy 年报企业
     */
    private Czzsnbqy newInstanceQysbsj(String jsjdm, String nd, String hc,
                                       String bqljs, String swjgzzjgdm, Timestamp now)
    {
        Czzsnbqy czzsnbqy = new Czzsnbqy();
        czzsnbqy.setJsjdm(jsjdm);
        czzsnbqy.setNd(nd);
        czzsnbqy.setHc(hc);
        czzsnbqy.setBqljs(new BigDecimal(bqljs));
        czzsnbqy.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbqy.setLrrq(now);
        czzsnbqy.setCjrq(now);
        czzsnbqy.setQxdm(swjgzzjgdm.substring(0, 2));

        return czzsnbqy;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年报投资者数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param zjlxdm 证件类型代码
     * @param zjhm 证件号码
     * @param cwfzr 财务负责人
     * @param swjgzzjgdm 机关代码
     * @return List 投资者数据
     */
    private Czzsnbtzzsj newInstanceTzzsbsj(String jsjdm, String nd,
                                           String zjlxdm, String zjhm,
                                           String cwfzr, String swjgzzjgdm,
                                           Timestamp now)
    {
        Czzsnbtzzsj czzsnbtzzsj = new Czzsnbtzzsj();
        czzsnbtzzsj.setJsjdm(jsjdm);
        czzsnbtzzsj.setNd(nd);
        czzsnbtzzsj.setZjlxdm(zjlxdm);
        czzsnbtzzsj.setZjhm(zjhm);
        czzsnbtzzsj.setCwfzr(cwfzr);
        czzsnbtzzsj.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbtzzsj.setQxdm(swjgzzjgdm.substring(0, 2));
        czzsnbtzzsj.setCjrq(now);
        czzsnbtzzsj.setLrrq(now);
        return czzsnbtzzsj;
    }

    /**
     * 从传过来的参数构造一个 查帐征收年度分配比例数据 值对象返回
     * 参数个数和值对象属性个数相同，参数类型都为String型。
     * @param jsjdm 计算机代码
     * @param nd 年度
     * @param hc 行次
     * @param bl 比例
     * @param bqljs 本期累计数
     * @param swjgzzjgdm 机关代码
     * @return Czzsnbfpbl 分配比例
     */
    private Czzsnbfpbl newInstanceFpbl(String jsjdm, String nd, String hc,
                                       String bl, String bqljs, String swjgzzjgdm,
                                       Timestamp now)
    {
        Czzsnbfpbl czzsnbfpbl = new Czzsnbfpbl();
        czzsnbfpbl.setJsjdm(jsjdm);
        czzsnbfpbl.setNd(nd);
        czzsnbfpbl.setHc(hc);
        czzsnbfpbl.setBl(new BigDecimal(bl));
        czzsnbfpbl.setBqljs(new BigDecimal(bqljs));
        czzsnbfpbl.setSwjgzzjgdm(swjgzzjgdm);
        czzsnbfpbl.setLrrq(now);
        czzsnbfpbl.setCjrq(now);
        czzsnbfpbl.setQxdm(swjgzzjgdm.substring(0, 2));
        return czzsnbfpbl;
    }
    
}