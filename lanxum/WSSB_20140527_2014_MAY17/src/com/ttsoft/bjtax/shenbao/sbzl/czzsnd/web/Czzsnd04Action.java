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
 * �������ո��˶��ʺͺϻ���ҵ����걨
 */
public class Czzsnd04Action extends DcBaseAction {
	// ת����
	public static final String SELF = "Show";

	public static final String FAILING = "Failing";

	public static final String SUCCESS = "Success";

	/**
	 * Ȩ�޿���
	 * 
	 * @return int
	 */
	protected int getActionID() {
		return SbzlAccess.CZND;
	}

	/**
	 * �����걨��Ϣ 
	 * ���յ���xml�ַ������ɶ��Ͷ�ʷ�������ɣ����ȰѸ��ַ����ָ�ɵ���Ͷ�ʷ����ݣ�Ȼ����в���
	 * �Ե���Ͷ�ʷ�����Ҫ�ֱ����ǩ��������ɶ������ԭ������
	 * �ڱ����걨����ʱҪ�Ѷ��Ͷ�ʷ������ݺϲ���һ���걨���ݣ�һ�ν��б���
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
		//�ָ�xmldata��signData
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
				//ȡ�ָ�õ���һ��xmldata����
				String oneXmlData = st.nextToken();
				DzyjsjVO dzyj = new DzyjsjVO();
				if (ud.getCaflag()) {
					//ȡ�ָ�õ���һ��signData����
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
				//�Ѷ��Ͷ�ʷ����ݺϲ���һ���걨����
				czzsnbVO = convertToCzzsnbVO(xvo,czzsnbVO);
				dzyj.setYwczlx(xvo.getYwczlx());
				dzyj.setYwdm(xvo.getYwlx());
				dzyjList.add(dzyj);
			}
            
			Map data = convert2VO(czzsnbVO);
			data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyjList);
			// ����VOPackage
			VOPackage voPackage = new VOPackage();
			voPackage.setAction(CzzsndActionConstant.INT_ACTION_SAVE);
			voPackage.setData(data);
			voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
			voPackage.setUserData(ud);
			// ���ú�̨��������
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
					"������������걨���ϱ���ɹ���");
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_CZND_SAVE);
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"������������걨���ϱ���ɹ���");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"������������걨�����걨", (xvo.getSbxx()).getSbrq(), "�ɹ�!");

			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception e) {
			request.setAttribute("done", new Boolean(false));
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 * ��ÿһ��Ͷ�ʷ����ݺϲ���һ����������Ͷ�ʷ����ݵ��걨����
	 * @param xvo ÿһ��Ͷ�ʷ���ֵ����
	 * @param czzsnbVO ����Ͷ�ʷ���ɵ�һ���걨���ݵ�ֵ����
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
	 * ȡҵ����Ϣ������ʾ
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// ȡ�ü��������
		String jsjdm = ((UserData) request.getSession(false).getAttribute(
				SessionKey.USER_DATA)).yhid;
		// ȡ��CzzsndForm
		CzzsndForm czzsndForm = new CzzsndForm();

		try {
			// ͨ��˰�ѵĽӿ�ȡͶ�ʷ�����
			com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			// ȡ��һ�ꡣ
			Calendar now = Calendar.getInstance();
			now.add(Calendar.YEAR, -1);
			now.set(Calendar.MONTH, Calendar.DECEMBER);
			now.set(Calendar.DAY_OF_MONTH, 31);
			List tzfList = sfProxy.getTzfInfo(jsjdm, now.getTime());
			if (tzfList == null || tzfList.size() == 0) {
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						"����Ͷ�ʷ�����Ϊ�գ�");
				return CAConstants.SUCCESS;
			} else {
				setCodeTable(request, czzsndForm);

				// ����VOPackage
				VOPackage voPackage = new VOPackage();
				voPackage.setAction(CzzsndActionConstant.INT_ACTION_QUERY);
				voPackage.setUserData((UserData) request.getSession(false)
						.getAttribute(SessionKey.USER_DATA));
				voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
				voPackage.setData(czzsndForm.beforeQuery(jsjdm, tzfList));
				voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
						voPackage);

				// ��ΪCzzsnbtzzsj�����ݿ��еĶ�Ӧ�����ṩ����Ͷ���������ĵط�
				// ���������Ͷ�������������Ա�ҳ����ʾ
				Map data = (Map) voPackage.getData();
				// �ӷ���ֵȡ��Ͷ�����걨����
				List tzzsbsjList = (List) data
						.get(CzzsndMapConstant.LIST_TZZSBSJ);

				for (int i = 0, size = tzzsbsjList.size(); i < size; i++) {
					// ȡ��ÿ��Ͷ�����걨����
					Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj) tzzsbsjList.get(i);
					// ѭ����˰��ȡ����Ͷ��������
					for (int j = 0, jSize = tzfList.size(); j < jSize; j++) {
						// ȡ��Ͷ��������
						Tzf tzf = (Tzf) tzfList.get(j);
						// �ж��Ƿ�֤�����ʹ����֤�����붼���
						if (czzsnbtzzsj.getZjhm().equalsIgnoreCase(
								tzf.getZjhm())
								&& czzsnbtzzsj.getZjlxdm().equalsIgnoreCase(
										tzf.getZjlxdm())) {
							// ���ʱ����Ͷ�����걨���ݵ�Ͷ���������ֶ�
							czzsnbtzzsj.setTzzxm(tzf.getTzfmc());
							// �������ҵ�ǰͶ�����걨�����е�Ͷ��������ѭ��
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
	 * ��XML-VO����ת��Ϊ�ɵ�VO����
	 * @param vo XML-VO����
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

		// ��������List

		Timestamp now = new Timestamp(System.currentTimeMillis());

		// ���������������
		Czzsnbzb czzsnbzb = newInstanceCzzsnbzb(jsjdm, nd, CodeConstant.FSDM_WSSB,
						swjgzzjgdm, vo.getCzzbsj().getLrr(), vo.getCzzbsj()
								.getLrrq(), vo.getSbxx().getSkssjsrq(), vo
								.getSbxx().getSkssksrq(), vo.getCzzbsj()
								.getCjsj(), now);

		// ����Ͷ��������
		int tzzSize = vo.getTzfsj().size();
		List tzzsbsjList = new ArrayList(tzzSize);
		for (int i = 0; i < tzzSize; i++) {

			Tzfsj04VO atzfsj = (Tzfsj04VO) vo.getTzfsj().get(i);
			Czzsnbtzzsj czzsnbtzzsj = newInstanceTzzsbsj(jsjdm, nd, atzfsj
					.getZjlxdm(), atzfsj.getZjhm(), atzfsj.getCwfzr(),
					swjgzzjgdm, now);
			tzzsbsjList.add(czzsnbtzzsj);
		}

		// ������ҵ����List
		// 1-38��49-50
		List qysbsjList = new ArrayList(40);
		for (int i = 0; i < 40; i++) {
			Sbxm04VO sbxmvo = (Sbxm04VO) vo.getSbsjlist().getSbxm().get(i);
			Czzsnbqy czzsnbqy = newInstanceQysbsj(jsjdm, nd, sbxmvo
					.getHc(), sbxmvo.getBqljs(), swjgzzjgdm, now);
			qysbsjList.add(czzsnbqy);
		}

		// ����Ͷ������ϸ����
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

		// ���·����������
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
	 * ת����XML-VO����
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

		// ����˰�ѹ���ӿ�ȡ�ü�������
		List jmsjList = new ArrayList(tzzsbsjList.size());
		com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
		//����Ͷ�ʷ�����tzfsj
		for (int i = 0, size = tzzsbsjList.size(); i < size; i++) {
			Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj) tzzsbsjList.get(i);
			List mxList = (List) tzzsbsjmxList.get(i);
			Tzfsj04VO atzfsj = new Tzfsj04VO();
			atzfsj.setCwfzr(czzsnbtzzsj.getCwfzr());
			atzfsj.setTzzxm(czzsnbtzzsj.getTzzxm());
			atzfsj.setZjhm(czzsnbtzzsj.getZjhm());
			atzfsj.setZjlxdm(czzsnbtzzsj.getZjlxdm());

			// ����ĵ�����Ϊʹ��ȱʡ�Ĺ������룬δ��ĳ��ʱ�����Ĺ�����
			// ���Ծ�ûд��CodeTableȥ��δ��ĳ��ʱ��������Ͷ����������ȡ��
			// ��һ�ֿ��ܣ���Czzsnbtzzsj��Ӧ�����ݿ��ṹ�Ķ���
			// ���ӹ��������ֶΣ����ԣ�������·��
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
		//�����걨�걨�����б�
		for (int i = 0, size = qysbsjList.size(); i < size; i++) {
			Czzsnbqy aqysj = (Czzsnbqy) qysbsjList.get(i);
			Sbxm04VO sbxmvo = new Sbxm04VO();
			sbxmvo.setBqljs(StringUtils.bigDeciaml2String(aqysj.getBqljs()));
			sbxmvo.setHc(aqysj.getHc());
			sbxmvo.setXmmc(CzzsndMapConstant.getInst().getCzmc(aqysj.getHc()));
			sbxm.add(sbxmvo);
		}
		//���ɷ����������
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
		// XML�ĵ���Ϣ
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
	 * ɾ���걨��Ϣ 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 * @throws BaseException
	 */
	public String doDelete(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// ���token
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		// �õ�xml
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

			// ȡ��Form
//			CzzsndForm czzsndForm = new CzzsndForm();
			// ��Form ȡ����ɾ����������
			Map data = convert2VO(xvo);
			// ����ǩ��Ԫ��
			dzyj.setYwczlx(xvo.getYwczlx());
			dzyj.setYwdm(xvo.getYwlx());
			data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			// ����VOPackage
			VOPackage voPackage = new VOPackage();
			voPackage.setAction(CzzsndActionConstant.INT_ACTION_DELETE);
			voPackage.setData(data);
			voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
			voPackage.setUserData((UserData) request.getSession(false)
					.getAttribute(SessionKey.USER_DATA));
			// ���ú�̨ɾ������
			ShenbaoProxy.getInstance().process(voPackage);

			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"������������걨��ɾ���ɹ���");
			request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"������������걨��ɾ���ɹ���");
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_CZND_DELETE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"������������걨���걨", xvo.getSbxx().getSbrq(), "�ɹ�!");

			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception e) {
			request.setAttribute("done", new Boolean(false));
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * ���ô����form��
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param czzsndForm
	 *            CzzsndForm
	 */
	private void setCodeTable(HttpServletRequest request, CzzsndForm czzsndForm) {
		// ȡ�ô����
		List szsmList = CodeTableUtil.getCodeTableList(request,
				CodeTable.SZSM_LIST_AVAILABLE);

		// ��Ϊ����������ֻ�õ�����һ���֣�Ϊ�˼���ҳ������������˳����貿��
		List czzsndSzsmList = new ArrayList();
		for (int i = 0, size = szsmList.size(); i < size; i++) {
			// ȡÿһ��˰��˰Ŀ
			Szsm szsm = (Szsm) szsmList.get(i);
			if (!szsm.getSzsmdm().startsWith(SzsmdmConstant.GTGSH)) {
				// �Ǹ�������˰��˰��˰Ŀ����ѭ��
				continue;
			} else if (szsm.getSzsmdm().length() == 4) {
				// �Ǹ�������˰��˰�ּ���ѭ��
				continue;
			} else if (szsm.getSl() == null) {
				// �Ǹ�������˰��û��˰�ʵ�˰��˰Ŀ����ѭ��
				continue;
			}  else if (szsm.getSzsmdm().startsWith("05120")) {
				// �Ǹ�������˰��2011���������˰���幤�̻���Ӫ���õ���ǰ��˰��˰Ŀ����ѭ��
				//added by zhangyj 20111226
				continue;
			} else {
				// ��������˰��˰��˰Ŀ����

				czzsndSzsmList.add(szsm);
			}
		}

		// ���õ�formȥ
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
     * �Ӵ������Ĳ�������һ�� ���������걨Ͷ�������� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param zjlxdm ֤�����ʹ���
     * @param zjhm ֤������
     * @param hc �д�
     * @param bqljs �����ۼ���
     * @param swjgzzjgdm ���ش���
     * @return List Ͷ��������
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
     * �Ӵ������Ĳ�������һ�� ���������걨������ ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param fsdm ��ʽ����
     * @param swjgzzjgdm ���ش���
     * @param lrr ¼����
     * @param lrrq ¼������
     * @param ssrqz ��������ֹ
     * @param ssrqq ����������
     * @param strCjsj ����ʱ��
     * @return Czzsnbzb �걨����
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
     * �Ӵ������Ĳ�������һ�� ���������걨��ҵ���� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param hc �д�
     * @param bqljs �����ۼ���
     * @param swjgzzjgdm ���ش���
     * @return Czzsnbqy �걨��ҵ
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
     * �Ӵ������Ĳ�������һ�� ���������걨Ͷ�������� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param zjlxdm ֤�����ʹ���
     * @param zjhm ֤������
     * @param cwfzr ��������
     * @param swjgzzjgdm ���ش���
     * @return List Ͷ��������
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
     * �Ӵ������Ĳ�������һ�� ����������ȷ���������� ֵ���󷵻�
     * ����������ֵ�������Ը�����ͬ���������Ͷ�ΪString�͡�
     * @param jsjdm ���������
     * @param nd ���
     * @param hc �д�
     * @param bl ����
     * @param bqljs �����ۼ���
     * @param swjgzzjgdm ���ش���
     * @return Czzsnbfpbl �������
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