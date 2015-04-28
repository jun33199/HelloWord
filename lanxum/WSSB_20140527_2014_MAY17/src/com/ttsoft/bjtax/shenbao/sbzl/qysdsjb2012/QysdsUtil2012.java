package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012;

/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- 08��ҵ����˰�걨������</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.CzzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.FzjgList2012VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.Fzjghj2012VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.Fzjgxx2012VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.HdzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.HdzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.NsrxxVO_HDZS;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.Sbxx2012VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.Zjgxx2012VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.DateTimeUtil;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.sql.PreparedStatement;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.*;;

public class QysdsUtil2012 {
	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.25";

	public QysdsUtil2012() {
	}

	/**
	 * 08��ҵ����˰������˰��֧��������� ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 * @author Guoxh,2012-03-06,�޸�����˼·�����ı�ԭ���Ĵ���ܹ���ֻ�޸�������
	 */
	public ZfjgqysdsjbVO Zfjgconvert2XMLVO(ZfjgqysdsjbBO qysdsbo,
			SWDJJBSJ djJbsj) throws BaseException {
		ZfjgqysdsjbVO qysdsvo = new ZfjgqysdsjbVO();
		Sbxx2012VO sbxx = new Sbxx2012VO();
		Zjgxx2012VO zjgxx = new Zjgxx2012VO();
		FzjgList2012VO fzjgList = new FzjgList2012VO();
		Fzjghj2012VO fzjghj = new Fzjghj2012VO();

		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ///////////////////////////////////
		HashMap sbsj = (HashMap) qysdsbo.getSbsj();
		System.out.println("==========2012========");
		System.out.println(sbsj.toString());
		// sbxx
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSbrqShow(qysdsbo.getSbrqshow());
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
//		sbxx.setFpblyxqq((String) sbsj.get("1"));
//		sbxx.setFpblyxqz((String) sbsj.get("2"));

		// zjgxx
		zjgxx.setJsjdm(djJbsj.getJsjdm());
		zjgxx.setNsrsbh(qysdsbo.getNsrsbh());
		zjgxx.setNsrmc(qysdsbo.getNsrmc());
		zjgxx.setNsrsbh((String) sbsj.get("2"));
		zjgxx.setNsrmc((String) sbsj.get("1"));
		zjgxx.setYnsdse(((String) sbsj.get("3")) == null ? "0.00" : (String) sbsj
				.get("3"));
		zjgxx.setFtsdse(((String) sbsj.get("4")) == null ? "0.00" : (String) sbsj
				.get("4"));
		zjgxx.setFpsdse(((String) sbsj.get("5")) == null ? "0.00" : (String) sbsj
				.get("5"));
		zjgxx.setFzjgftse(((String) sbsj.get("6")) == null ? "0.00" : (String) sbsj
				.get("6"));

		// fzjgxx
		ArrayList fzjgxxList = new ArrayList();
		int rows = Integer.parseInt((String) sbsj
				.get(Constant.ZFJGSDSJB_2008_MAX_ROW));
		for (int i = 1; i <= rows; i++) {
			Fzjgxx2012VO fzjg = new Fzjgxx2012VO();
			fzjg.setIndex(String.valueOf(i));
			for (int j = 12; j < 19; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i);
				// System.out.println("util id = " + id);
				// System.out.println("util value = " + (String) sbsj.get(id));
				switch (j) {
				case 12:
					// ��֧������˰��ʶ���
					fzjg.setNsrsbh((String) sbsj.get(id));
					break;
				case 13:
					// ��֧��������
					fzjg.setNsrmc((String) sbsj.get(id));
					break;
				case 14:
					// ��֧���������ܶ�
					fzjg.setSrze((String) sbsj.get(id));
					break;
				case 15:
					// ��֧���������ܶ�
					fzjg.setGzze((String) sbsj.get(id));
					break;
				case 16:
					// ��֧�����ʲ��ܶ�
					fzjg.setZcze((String) sbsj.get(id));
					break;
				case 17:
					// ��֧�����������
					fzjg.setFpbl((String) sbsj.get(id));
					break;
				case 18:
					// ��֧��������˰��
					fzjg.setFpse((String) sbsj.get(id));
					break;
				}
			}
			fzjgxxList.add(fzjg);
		}
		fzjgList.setMxxx(fzjgxxList);
		
		//2012��֧�����ϼ���
		fzjghj.setSrehj((String)sbsj.get("7"));
		fzjghj.setGzehj((String)sbsj.get("8"));
		fzjghj.setZcehj((String)sbsj.get("9"));
		fzjghj.setFpblhj((String)sbsj.get("10"));
		fzjghj.setFpsehj((String)sbsj.get("11"));

		// ��ҵ����˰�걨
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);
		qysdsvo.setFzjghj(fzjghj);

		// XML�ĵ���Ϣ
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_ZFJGSDSJB_2012);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_ZFJGSDSJB_2012);
		qysdsvo.setYwlx(Constant.CA_YWLXDM_ZFJGSDSJB_2008);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}
	
	/**
	 * 2012��ҵ����˰������˰��֧��������� ���ɵ�VO����ת��ΪXML-VO����
	 * 
	 * @param qysdsjd
	 * @param djJbsj
	 * @param cvo
	 * @return ZfjgqysdsjbVO
	 * @throws BaseException
	 * @author gaoyh,2012-03-16,�޸�����˼·�����ı�ԭ���Ĵ���ܹ���������ȡ��A���
	 */
	public ZfjgqysdsjbVO zfjgxxGetDataFromAconvert2XMLVO(ZfjgqysdsjbBO qysdsbo,
			SWDJJBSJ djJbsj, CzzssdsjbVO cvo, String nszf) throws BaseException {
		
		CzzssdsjbVO czzsjbvo = (CzzssdsjbVO)cvo;
		ZfjgqysdsjbVO qysdsvo = new ZfjgqysdsjbVO();
		Sbxx2012VO sbxx = new Sbxx2012VO();
		Zjgxx2012VO zjgxx = new Zjgxx2012VO();
		FzjgList2012VO fzjgList = new FzjgList2012VO();
		Fzjghj2012VO fzjghj = new Fzjghj2012VO();

		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ///////////////////////////////////
		HashMap sbsj = (HashMap) qysdsbo.getSbsj();
		System.out.println("==========2012========");
		System.out.println(sbsj.toString());
		// sbxx
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSbrqShow(qysdsbo.getSbrqshow());
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
		// sbxx.setFpblyxqq((String) sbsj.get("1"));
		// sbxx.setFpblyxqz((String) sbsj.get("2"));

		// zjgxx
		zjgxx.setJsjdm(djJbsj.getJsjdm());
		zjgxx.setNsrsbh(((String) sbsj.get("2")) == null ? "" : (String) sbsj.get("2"));
		zjgxx.setNsrmc(((String) sbsj.get("1")) == null ? "" : (String) sbsj.get("1"));
		zjgxx.setYnsdse(((String) sbsj.get("3")) == null ? "0.00" : (String) sbsj.get("3"));
		zjgxx.setFtsdse(((String) sbsj.get("4")) == null ? "0.00" : (String) sbsj.get("4"));
		zjgxx.setFpsdse(((String) sbsj.get("5")) == null ? "0.00" : (String) sbsj.get("5"));
		zjgxx.setFzjgftse(((String) sbsj.get("6")) == null ? "0.00" : (String) sbsj.get("6"));
		// fzjgxx
		
		ArrayList fzjgxxList = new ArrayList();
		int rows = Integer.parseInt((String) sbsj
				.get(Constant.ZFJGSDSJB_2008_MAX_ROW));
		for (int i = 1; i <= rows; i++) {
			Fzjgxx2012VO fzjg = new Fzjgxx2012VO();
			fzjg.setIndex(String.valueOf(i));
			for (int j = 12; j < 19; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i);
				// System.out.println("util id = " + id);
				// System.out.println("util value = " + (String) sbsj.get(id));
				switch (j) {
				case 12:
					// ��֧������˰��ʶ���
					fzjg.setNsrsbh(((String) sbsj.get(id)) == null ? "" : (String) sbsj.get(id));
					break;
				case 13:
					// ��֧��������
					fzjg.setNsrmc(((String) sbsj.get(id)) == null ? "" : (String) sbsj.get(id));
					break;
				case 14:
					// ��֧���������ܶ�
					fzjg.setSrze(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 15:
					// ��֧���������ܶ�
					fzjg.setGzze(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 16:
					// ��֧�����ʲ��ܶ�
					fzjg.setZcze(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 17:
					// ��֧�����������
					fzjg.setFpbl(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 18:
					// ��֧��������˰��
					fzjg.setFpse(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				}
			}
			fzjgxxList.add(fzjg);
		}
		fzjgList.setMxxx(fzjgxxList);

		// 2012��֧�����ϼ���
		fzjghj.setSrehj(((String) sbsj.get("7")) == null ? "0.00" : (String) sbsj.get("7"));
		fzjghj.setGzehj(((String) sbsj.get("8")) == null ? "0.00" : (String) sbsj.get("8"));
		fzjghj.setZcehj(((String) sbsj.get("9")) == null ? "0.00" : (String) sbsj.get("9"));
		fzjghj.setFpblhj(((String) sbsj.get("10")) == null ? "0" : (String) sbsj.get("10"));
		fzjghj.setFpsehj(((String) sbsj.get("11")) == null ? "0.00" : (String) sbsj.get("11"));
		
		
		// ��ҵ����˰�걨
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);
		qysdsvo.setFzjghj(fzjghj);

		// XML�ĵ���Ϣ
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_ZFJGSDSJB_2012);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_ZFJGSDSJB_2012);
		qysdsvo.setYwlx(Constant.CA_YWLXDM_ZFJGSDSJB_2008);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}

	/**
	 * ����������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 * @author Guoxh,2012-03-06,�޸�����˼·�����ı�ԭ���Ĵ���ܹ���ֻ�޸�������
	 */
	public ZfjgqysdsjbBO Zfjgconvert2BO(ZfjgqysdsjbVO qysdsvo)
			throws BaseException {
		ZfjgqysdsjbBO qysdsbo = new ZfjgqysdsjbBO();
		HashMap sbsj = new HashMap();

		/**
		 * �걨��Ϣ
		 */
		// fsdm
		qysdsbo.setFsdm(qysdsvo.getSbxx().getFsdm());
		// jd
		qysdsbo.setJd(qysdsvo.getSbxx().getJd());
		// nd
		qysdsbo.setNd(qysdsvo.getSbxx().getNd());
		// sbrq
		qysdsbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSbrq())));
		// skssksrq
		qysdsbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssjsrq())));
		// skssjsrq
		qysdsbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssksrq())));
		//2012��fpblyxq�Ѳ�����,modified by guoxh,2012-03-06
		// qysdsbo.setFpblyxqq(qysdsvo.getSbxx().getFpblyxqq());
		// qysdsbo.setFpblyxqq(qysdsvo.getSbxx().getFpblyxqz());
		// fpblyxqq
		//sbsj.put("1", qysdsvo.getSbxx().getFpblyxqq());
		// fpblyxqz
		//sbsj.put("2", qysdsvo.getSbxx().getFpblyxqz());

		/**
		 * �ܻ�����Ϣ
		 */
		// jsjdm
		qysdsbo.setJsjdm(qysdsvo.getZjgxx().getJsjdm());
		// nsrsbh
		qysdsbo.setNsrsbh(qysdsvo.getZjgxx().getNsrsbh());
		sbsj.put("2", qysdsvo.getZjgxx().getNsrsbh());
		// nsrmc
		qysdsbo.setNsrmc(qysdsvo.getZjgxx().getNsrmc());
		sbsj.put("1", qysdsvo.getZjgxx().getNsrmc());
		// srze
		sbsj.put("3", qysdsvo.getZjgxx().getYnsdse());
		// gzze
		sbsj.put("4", qysdsvo.getZjgxx().getFtsdse());
		// zcze
		sbsj.put("5", qysdsvo.getZjgxx().getFpsdse());
		// ftse
		sbsj.put("6", qysdsvo.getZjgxx().getFzjgftse());

		/**
		 * ��֧������ϸ��Ϣ
		 */
		ArrayList fzjgmxList = (ArrayList) qysdsvo.getFzjgxx().getMxxx();
		// System.out.println("fzjgmxList size = " + fzjgmxList.size());
		for (int i = 0; i < fzjgmxList.size(); i++) {
			Fzjgxx2012VO fzjgxx = (Fzjgxx2012VO) fzjgmxList.get(i);
			// System.out.println("index = " + fzjgxx.getIndex());
			// ��id��10~17��Ԫ���ֵ����Map��
			for (int j = 12; j < 19; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i + 1);
				switch (j) {
				case 12:
					// ��֧������˰��ʶ���
					sbsj.put(id, fzjgxx.getNsrsbh());
					break;
				case 13:
					// ��֧��������
					sbsj.put(id, fzjgxx.getNsrmc());
					break;
				case 14:
					// ��֧���������ܶ�
					sbsj.put(id, fzjgxx.getSrze());
					break;
				case 15:
					// ��֧���������ܶ�
					sbsj.put(id, fzjgxx.getGzze());
					break;
				case 16:
					// ��֧�����ʲ��ܶ�
					sbsj.put(id, fzjgxx.getZcze());
					break;
//				case 15:
//					// ��֧�����ϼ�
//					sbsj.put(id, fzjgxx.getHj());
//					break;
				case 17:
					// ��֧�����������
					sbsj.put(id, fzjgxx.getFpbl());
					break;
				case 18:
					// ��֧��������˰��
					sbsj.put(id, fzjgxx.getFpse());
					break;
				}
			}
		}
		
		//2012���֧�����ϼ���
		Fzjghj2012VO fzjghj = qysdsvo.getFzjghj();
		sbsj.put("7", fzjghj.getSrehj());
		sbsj.put("8", fzjghj.getGzehj());
		sbsj.put("9", fzjghj.getZcehj());
		sbsj.put("10", fzjghj.getFpblhj());
		sbsj.put("11", fzjghj.getFpsehj());

		qysdsbo.setSbsj(sbsj);

		return qysdsbo;
	}

	/**
	 * ��ȡ����
	 *
	 * @return String
	 */
	public String getJbDM() {
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		String jd = Skssrq.preQuarter(curDate);
		return jd;
	}

	/**
	 * ������Map��keyֵ��������
	 *
	 * @param declareMap
	 *            �����Map
	 * @return ������List
	 */
	public ArrayList arrangeMapKey(HashMap declareMap) {
		// ����keyList
		ArrayList keyList = new ArrayList();

		Iterator it = declareMap.keySet().iterator();
		while (it.hasNext()) {
			// ��ȡ��ǰkey
			String key = (String) it.next();
			keyList.add(key);
		}

		int size = keyList.size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				double curKey = Double.parseDouble((String) keyList.get(j));
				double nextKey = Double
						.parseDouble((String) keyList.get(j + 1));
				if (curKey > nextKey) {
					double tmp = curKey;
					keyList.set(j, convertStr(nextKey));
					keyList.set((j + 1), convertStr(tmp));
				}
			}
		}
		return keyList;
	}

	/**
	 * �������doubleֵת�����ַ������ ���doubleΪ*.0����ֻҪ����λ
	 *
	 * @param d
	 *            ��Ҫת����doubleֵ
	 * @return ת������ַ���
	 */
	public String convertStr(double d) {
		String str = String.valueOf(d);
		if (str.endsWith(".0")) {
			str = str.substring(0, (str.indexOf(".")));
		}
		return str;
	}

	/**
	 * ��ʽ������
	 *
	 * @param datetime
	 *            String
	 * @return String
	 */
	private String getFormatDatetime(String datetime) {
		try {
			String retStr = new String();
			StringTokenizer st = new StringTokenizer(datetime.trim());
			retStr = st.nextToken();
			// �Ƚ����ڲ��ָ�ʽ��
			if (retStr.substring(6, 7).equals("-")) {
				retStr = retStr.substring(0, 5) + "0" + retStr.substring(5);
			}
			if (retStr.length() < 10) {
				retStr = retStr.substring(0, 8) + "0" + retStr.substring(8);
				// ����ʱ�䲿��
			}
			if (st.countTokens() < 1) {
				return retStr + " 00:00:00.000"; // ��������
			}
			String s = new String();
			s = st.nextToken();
			st = new StringTokenizer(s, ":");
			int count = st.countTokens();
			if (count == 1) {
				retStr += " " + s + ":00:00.000"; // ʱ����ֻ��Сʱ
			}
			if (count == 2) {
				retStr += " " + s + ":00.000"; // ʱ����ֻ��Сʱ������
			}
			if (count == 3) {
				retStr += " " + s + ".000"; // ʱ�����޺���
			}
			return retStr;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ȡ�ú˶���Ϣ
	 *
	 * @param jsjdm
	 * @param hdvo
	 * @param sbsj
	 * @param swdjjbsj
	 * @param bblx
	 * @throws com.syax.frame.exception.BaseException
	 */
	public void getHdxx(String jsjdm, HdxxVO hdvo, HdzsSbsjVO sbsj,
			SWDJJBSJ swdjjbsj, String bblx)
			throws com.syax.frame.exception.BaseException {
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		// Ӧ������˰��
		String ynsdse = "0.00";
		// ��������˰��
		String dezsse = "0.00";

		// ��ǰʱ��
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

		// ����
		Map skssrq = new HashMap();

		// ˰��������ʼ�ͽ�������
		Timestamp skssksrq;
		Timestamp skssjsrq;

		// ȡ�����ڵļ���
		String jd = Skssrq.preQuarter(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;

		// ���ݿ����Ӷ���
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// �����ʸ��ʶ
		boolean jm_type = false;

		try {

			if ("4".equals(jd)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// ���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// �걨
			} else {

				skssrq = Skssrq.otherSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// �����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"00");// ����
                //����Zsfs
                Zsfs zsfs = getZsfsInfo(jsjdm, skssjsrq);
                qysdsSet.setZsfs(zsfs);

			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();
		System.out.println("���¼�����ҵ:" + gxqyrdrq);

		// ��ֵ
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		System.out.println("��ǰ�걨�ļ���jd:" + jd);

		System.out.println("��ǰ�걨���걨����sbrq:" + sbrq);

		System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:" + skssksrq);

		System.out.println("��ǰ�걨��˰��������������skssjsrq:" + skssjsrq);

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					// hdvo.setJmzg("1"); // �м����ʸ�
				}
				hdvo.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				hdvo.setDezsse(dezsse);
			}
		}

		// 2����ѯ�Ƿ��Ǹ��¼�����ҵ
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// ���¼����ʹ�������ҵ
				qyzssllx = "5";
			} else {
				// ����Ϊ���¼�����ҵ
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			// hdvo.setJmzg("1"); // �м����ʸ�
		}

		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				// hdvo.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			// hdvo.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		hdvo.setJmzg("1");// Ĭ������Ϊ�м����ʸ�
	}

	/**
	 * �˶�������ҵ ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsjbVO Hdzsconvert2XMLVO(HdzssdsBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws com.syax.frame.exception.BaseException {
		SbxxVO sbxx = new SbxxVO();
		HdzsSbsjVO sbsj = new HdzsSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO_HDZS nsrxx = new NsrxxVO_HDZS();
		HdzssdsjbVO qysdsnb = new HdzssdsjbVO();
		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ��˰����Ϣ��
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
		nsrxx.setNsrsbh(djJbsj.getSwdjzh());

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// �˶���Ϣ������һ�����걨��Ϣ
		getHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// ��Ϣ
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsndbo.getJd());
		sbxx.setNd(qysdsndbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

		// �걨����
		sbsj.setSyze(qysdsndbo.getSyze());
		sbsj.setBzssr(qysdsndbo.getBzssr());
		sbsj.setMssr(qysdsndbo.getMssr());
		sbsj.setYssre(qysdsndbo.getYssre());
		sbsj.setYssdl(qysdsndbo.getYssdl());
		sbsj.setYnssde(qysdsndbo.getYnssde());
		sbsj.setSl(qysdsndbo.getSl());
		sbsj.setYnsdse(qysdsndbo.getYnsdse());
		sbsj.setYyjsdse(qysdsndbo.getYyjsdse());
		sbsj.setYbsdse(qysdsndbo.getYbsdse());
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		// ��ҵ����˰�걨
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSJB_2012);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_HDZSSDSJB_2012);
		qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSJB_2008);

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}

	/**
	 * ����������ҵ ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsjbVO Czzsconvert2XMLVO(CzzssdsBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws com.syax.frame.exception.BaseException {
		SbxxVO sbxx = new SbxxVO();
		CzzsSbsjVO sbsj = new CzzsSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO_HDZS nsrxx = new NsrxxVO_HDZS();
		CzzssdsjbVO qysdsnb = new CzzssdsjbVO();
		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ��˰����Ϣ��
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
		nsrxx.setNsrsbh(djJbsj.getSwdjzh());

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// �˶���Ϣ������һ�����걨��Ϣ
		getHdxx_cz(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// ��Ϣ
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsndbo.getJd());
		sbxx.setNd(qysdsndbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

		// �걨����
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		sbsj.setNsfs(qysdsndbo.getNsfs());
		sbsj.setZfjg(qysdsndbo.getZfjg());
		sbsj.setYysr(qysdsndbo.getYysr());
		sbsj.setYycb(qysdsndbo.getYycb());
		sbsj.setLrze(qysdsndbo.getLrze());
		sbsj.setTdjsynssde(qysdsndbo.getTdjsynssde());
		sbsj.setBzsr(qysdsndbo.getBzsr());
		sbsj.setMssr(qysdsndbo.getMssr());
		sbsj.setMbyqndks(qysdsndbo.getMbyqndks());
		sbsj.setSjlre(qysdsndbo.getSjlre());
		sbsj.setSl(qysdsndbo.getSl());
		sbsj.setYnsdse(qysdsndbo.getYnsdse());
		sbsj.setJmsdse(qysdsndbo.getJmsdse());
		sbsj.setSjyyjsdse(qysdsndbo.getSjyyjsdse());
		sbsj.setTdywyjsdse(qysdsndbo.getTdywyjsdse());
		sbsj.setYbtsdse(qysdsndbo.getYbtsdse());
		sbsj.setYqnddjsdse(qysdsndbo.getYqnddjsdse());
		sbsj.setBqsjybtsdse(qysdsndbo.getBqsjybtsdse());
		sbsj.setZjgyftsdse(qysdsndbo.getZjgyftsdse());
		sbsj.setCzjzfpsdse(qysdsndbo.getCzjzfpsdse());
		sbsj.setFzjgyftsdse(qysdsndbo.getFzjgyftsdse());
		sbsj.setZjgdlscjybmyftsdse(qysdsndbo.getZjgdlscjybmyftsdse());
		sbsj.setZjgycxfzjgyftsdse(qysdsndbo.getZjgycxfzjgyftsdse());
		sbsj.setFpbl(qysdsndbo.getFpbl());
		sbsj.setFpsdse(qysdsndbo.getFpsdse());
		
		System.out.println("qysdsndbo.getNsfs()========="+qysdsndbo.getNsfs());
		System.out.println("qysdsndbo.getZfjg()========="+qysdsndbo.getZfjg());
		// ��ҵ����˰�걨
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_CZZSSDSJB_2012);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_CZZSSDSJB_2012);
		qysdsnb.setYwlx(Constant.CA_YWLXDM_CZZSSDSJB_2012);

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}

	/**
	 * ȡ�ú˶���Ϣ
	 *
	 * @param jsjdm
	 * @param hdvo
	 * @param sbsj
	 * @param swdjjbsj
	 * @param bblx
	 * @throws com.syax.frame.exception.BaseException
	 */
	public void getHdxx_cz(String jsjdm, HdxxVO hdvo, CzzsSbsjVO sbsj,
			SWDJJBSJ swdjjbsj, String bblx)
			throws com.syax.frame.exception.BaseException {
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		// Ӧ������˰��
		String ynsdse = "0.00";
		// ��������˰��
		String dezsse = "0.00";

		// ��ǰʱ��
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

		// ����
		Map skssrq = new HashMap();

		// ˰��������ʼ�ͽ�������
		Timestamp skssksrq;
		Timestamp skssjsrq;

		// ȡ�����ڵļ���
		String jd = Skssrq.preQuarter(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;

		// ���ݿ����Ӷ���
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// �����ʸ��ʶ
		boolean jm_type = false;

		try {

			if ("4".equals(jd)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// ���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// �걨
			} else {

				skssrq = Skssrq.otherSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// �����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"00");// ����
                //����Zsfs
                Zsfs zsfs = getZsfsInfo(jsjdm, skssjsrq);
                qysdsSet.setZsfs(zsfs);
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();
		System.out.println("���¼�����ҵ:" + gxqyrdrq);

		// ��ֵ
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		System.out.println("��ǰ�걨�ļ���jd:" + jd);

		System.out.println("��ǰ�걨���걨����sbrq:" + sbrq);

		System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:" + skssksrq);

		System.out.println("��ǰ�걨��˰��������������skssjsrq:" + skssjsrq);

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					// hdvo.setJmzg("1"); // �м����ʸ�
				}
				hdvo.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				hdvo.setDezsse(dezsse);
			}
		}

		// 2����ѯ�Ƿ��Ǹ��¼�����ҵ
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// ���¼����ʹ�������ҵ
				qyzssllx = "5";
			} else {
				// ����Ϊ���¼�����ҵ
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			// hdvo.setJmzg("1"); // �м����ʸ�
		}

		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				// hdvo.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			// hdvo.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		hdvo.setJmzg("1");// Ĭ������Ϊ�м����ʸ�
	}

	/**
	 * �˶�������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsBO Hdzsconvert2VO(HdzssdsjbVO qysdsjbvo) throws BaseException {
		HdzssdsBO qysdsjbbo = new HdzssdsBO();
		// ��˰����Ϣ
		qysdsjbbo.setJsjdm(qysdsjbvo.getNsrxx().getJsjdm());
		qysdsjbbo.setNsrmc(qysdsjbvo.getNsrxx().getNsrmc());
		qysdsjbbo.setSwjgzzjgdm(qysdsjbvo.getNsrxx().getSwjgzzjgdm());
		// �˶���Ϣ
		qysdsjbbo.setCyl(qysdsjbvo.getHdxx().getCyl());
		qysdsjbbo.setDezsse(qysdsjbvo.getHdxx().getDezsse());
		qysdsjbbo.setJmzg(qysdsjbvo.getHdxx().getJmzg());
		qysdsjbbo.setQyzslx(qysdsjbvo.getHdxx().getQyzslx());
		qysdsjbbo.setXzqy(qysdsjbvo.getHdxx().getXzqy());
		qysdsjbbo.setYbjmsl(qysdsjbvo.getHdxx().getYbjmsl());
		// �걨��Ϣ
		qysdsjbbo.setFsdm(qysdsjbvo.getSbxx().getFsdm());
		qysdsjbbo.setJd(qysdsjbvo.getSbxx().getJd());
		qysdsjbbo.setNd(qysdsjbvo.getSbxx().getNd());
		qysdsjbbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx()
						.getSbrq())));
		qysdsjbbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx()
						.getSkssjsrq())));
		qysdsjbbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx()
						.getSkssksrq())));

		// �걨���ݡ�
		qysdsjbbo.setSbrqshow(qysdsjbvo.getSbsj().getSbrqshow());
		qysdsjbbo.setSyze(qysdsjbvo.getSbsj().getSyze());
		qysdsjbbo.setBzssr(qysdsjbvo.getSbsj().getBzssr());
		qysdsjbbo.setMssr(qysdsjbvo.getSbsj().getMssr());
		qysdsjbbo.setYssre(qysdsjbvo.getSbsj().getYssre());
		qysdsjbbo.setYssdl(qysdsjbvo.getSbsj().getYssdl());
		qysdsjbbo.setYnssde(qysdsjbvo.getSbsj().getYnssde());
		qysdsjbbo.setSl(qysdsjbvo.getSbsj().getSl());
		qysdsjbbo.setYnsdse(qysdsjbvo.getSbsj().getYnsdse());
		qysdsjbbo.setYyjsdse(qysdsjbvo.getSbsj().getYyjsdse());
		qysdsjbbo.setYbsdse(qysdsjbvo.getSbsj().getYbsdse());

		return qysdsjbbo;
	}

	/**
	 * ����������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsBO Czzsconvert2VO(CzzssdsjbVO qysdsjbvo) throws BaseException {
		CzzssdsBO qysdsjbbo = new CzzssdsBO();
		// ��˰����Ϣ
		qysdsjbbo.setJsjdm(qysdsjbvo.getNsrxx().getJsjdm());
		qysdsjbbo.setNsrmc(qysdsjbvo.getNsrxx().getNsrmc());
		qysdsjbbo.setSwjgzzjgdm(qysdsjbvo.getNsrxx().getSwjgzzjgdm());
		// �˶���Ϣ
		qysdsjbbo.setCyl(qysdsjbvo.getHdxx().getCyl());
		qysdsjbbo.setDezsse(qysdsjbvo.getHdxx().getDezsse());
		qysdsjbbo.setJmzg(qysdsjbvo.getHdxx().getJmzg());
		qysdsjbbo.setQyzslx(qysdsjbvo.getHdxx().getQyzslx());
		qysdsjbbo.setXzqy(qysdsjbvo.getHdxx().getXzqy());
		qysdsjbbo.setYbjmsl(qysdsjbvo.getHdxx().getYbjmsl());
		// �걨��Ϣ
		qysdsjbbo.setFsdm(qysdsjbvo.getSbxx().getFsdm());
		qysdsjbbo.setJd(qysdsjbvo.getSbxx().getJd());
		qysdsjbbo.setNd(qysdsjbvo.getSbxx().getNd());
		qysdsjbbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx()
						.getSbrq())));
		qysdsjbbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx()
						.getSkssjsrq())));
		qysdsjbbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx()
						.getSkssksrq())));

		// �걨���ݡ�
		qysdsjbbo.setSbrqshow(qysdsjbvo.getSbsj().getSbrqshow());
		qysdsjbbo.setNsfs(qysdsjbvo.getSbsj().getNsfs());
		qysdsjbbo.setZfjg(qysdsjbvo.getSbsj().getZfjg());
		qysdsjbbo.setYysr(qysdsjbvo.getSbsj().getYysr());
		qysdsjbbo.setYycb(qysdsjbvo.getSbsj().getYycb());
		qysdsjbbo.setLrze(qysdsjbvo.getSbsj().getLrze());
		qysdsjbbo.setTdjsynssde(qysdsjbvo.getSbsj().getTdjsynssde());
		qysdsjbbo.setBzsr(qysdsjbvo.getSbsj().getBzsr());
		qysdsjbbo.setMssr(qysdsjbvo.getSbsj().getMssr());
		qysdsjbbo.setMbyqndks(qysdsjbvo.getSbsj().getMbyqndks());
		qysdsjbbo.setSjlre(qysdsjbvo.getSbsj().getSjlre());
		qysdsjbbo.setSl(qysdsjbvo.getSbsj().getSl());
		qysdsjbbo.setYnsdse(qysdsjbvo.getSbsj().getYnsdse());
		qysdsjbbo.setJmsdse(qysdsjbvo.getSbsj().getJmsdse());
        qysdsjbbo.setSjyyjsdse(qysdsjbvo.getSbsj().getSjyyjsdse());
        qysdsjbbo.setTdywyjsdse(qysdsjbvo.getSbsj().getTdywyjsdse());
		qysdsjbbo.setYbtsdse(qysdsjbvo.getSbsj().getYbtsdse());
		qysdsjbbo.setYqnddjsdse(qysdsjbvo.getSbsj().getYqnddjsdse());
		qysdsjbbo.setBqsjybtsdse(qysdsjbvo.getSbsj().getBqsjybtsdse());
		qysdsjbbo.setZjgyftsdse(qysdsjbvo.getSbsj().getZjgyftsdse());
		qysdsjbbo.setCzjzfpsdse(qysdsjbvo.getSbsj().getCzjzfpsdse());
		qysdsjbbo.setFzjgyftsdse(qysdsjbvo.getSbsj().getFzjgyftsdse());
		qysdsjbbo.setZjgdlscjybmyftsdse(qysdsjbvo.getSbsj().getZjgdlscjybmyftsdse());
		qysdsjbbo.setZjgycxfzjgyftsdse(qysdsjbvo.getSbsj().getZjgycxfzjgyftsdse());
		qysdsjbbo.setFpbl(qysdsjbvo.getSbsj().getFpbl());
		qysdsjbbo.setFpsdse(qysdsjbvo.getSbsj().getFpsdse());

		return qysdsjbbo;
	}

    /**
     * ��ȡ���շ�ʽ
     * @param jsjdm String
     * @param rq Date
     * @return Zsfs
     * @throws BaseException
     */
    public Zsfs getZsfsInfo(String jsjdm, Date rq) throws BaseException, com.syax.frame.exception.BaseException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Zsfs ret = new Zsfs();
        QysdsZsfs q = new QysdsZsfs();
        StringBuffer sql = new StringBuffer();
        int rdnd = getYear(rq);
        try {
            con = DBResource.getConnection();
//            SfDBAccess db = new SfDBAccess(con);
//            Vector v = new Vector();
            sql.append("select * from sfdb.sf_jl_qysdszsfs where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and to_number(rdnd) <= ").append(rdnd);
            sql.append(" order by rdnd desc, cjrq desc");
            System.out.println("zsfs query sql:\n" + sql.toString());

            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());
            //ȡ���˶���һ����¼
            if(rs.next())
            {
                q.setZsfsdm(rs.getString("ZSFSDM"));
                q.setDl(new BigDecimal(rs.getDouble("DL")));
                q.setCyl(new BigDecimal(rs.getDouble("CYL")));
                q.setDe(new BigDecimal(rs.getDouble("DE")));
                //ret.setZsfsdm(rs.getString("ZSFSDM"));
                //ret.setSl(String.valueOf(rs.getDouble("DL")));
                //ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
            }
            else
            {
                return null;
            }

            ret.setZsfsdm(q.getZsfsdm());
            ret.setSl(String.valueOf(q.getDl()));
            ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));

            if (q.getCyl() != null) {
                ret.setCyl(String.valueOf(q.getCyl().divide(new BigDecimal(100),
                    4,
                    BigDecimal.ROUND_HALF_UP)));
            }
            else {
                ret.setCyl(String.valueOf(q.getCyl()));
            }

            ret.setDe(String.valueOf(q.getDe()));

            //�ر����ݿ����
            rs.close();
            pstmt.close();
            con.close();
            return ret;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }finally{
        	DBResource.destroyConnection(con);
        }
    }

    /**
     * �õ��������ڵ���� Ϊint��
     * @param date ����������
     * @return int ���ֵ
     */
    public static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR));
    }

    public java.lang.String getZsfsmc(String zsfsdm, Connection con)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String ret = "";
        try
        {
            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
                + zsfsdm +
                "'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
//            SfDBAccess db = new SfDBAccess(con);
//            ResultSet rs = db.querySQL(sql);
            if (rs.next())
            {
                //rs.first();
                ret = rs.getString("zsfsmc");
            }
            rs.close();
            return ret;
        }
        catch (Exception ex)
        {
            return "";
        }
    }
    
    /**
     * @decription ��ѯA�������˰�������ֻܷ���
     * @author gaoyh
     * @modify_date 2012-03-16
     * @param pData
     * @throws BaseException
     * @throws ApplicationException 
     * 
     */
    public HashMap getNsfsAndZfjg(Map pData) throws BaseException, ApplicationException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		HashMap nszf = new HashMap();

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			CzzssdsBO czzsbo = (CzzssdsBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

			sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
			sql.append("nsrjsjdm = '").append(czzsbo.getJsjdm()).append("' ");
			sql.append("and bbqlx = '").append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
			sql.append("and qh = '").append((String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
			sql.append("and sknd = '").append(czzsbo.getNd()).append("' ");
			sql.append("and sbdm = '").append(Constant.TABLE_ID_CZZSSDS_2008).append("' ");
			sql.append("and hc in ('1', '2') ");
			
			System.out.println("sql:\n" + sql.toString());

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				// nszf.put("hc", rs.getString("hc"));
				// nszf.put("yz", rs.getString("yz"));

				if (rs.getString("hc").equals("1")) {
					nszf.put("nsfs", rs.getString("yz"));
				}
				if (rs.getString("hc").equals("2")) {
					nszf.put("zfjg", rs.getString("yz"));
				}
			}
	
			System.out.println("nsfs====================" + nszf.get("nsfs"));
			System.out.println("zfjg====================" + nszf.get("zfjg"));
			
			czzsbo.setNsfs_fsjg(nszf);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// �ر����ݿ����
			this.releaseAll(rs, pstmt, conn);
		}
		return nszf;
	}
    
    /**
	 * @decription ������ѯ���������������
	 * @author gaoyh
	 * @modify_date 2012-03-16
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
	public HashMap queryCascadeZfjgData(Map pData) throws BaseException, ApplicationException {
		
		if (pData == null) {
			throw new ApplicationException("���뱣�����Ϊ�գ��޷���ѯ�걨������");
		}

		Connection conn = null;
		PreparedStatement queryPstmtZb = null;
		PreparedStatement queryPstmtCb = null;
		ResultSet queryRsZb = null;
		ResultSet queryRsCb = null;
		HashMap rsMap = new HashMap();

		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);

		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.otherSkssrq(curDate);

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();

			querySqlZb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM= '");

			querySqlZb.append(djJbsj.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append((String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append((String) skssrq.get(Skssrq.SKSSRQ_ND)).append("' ");
			querySqlZb.append(" AND SBDM = '");
			querySqlZb.append(Constant.TABLE_ID_ZFJGSDS_2008).append("' ");

			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM= '");
			querySqlCb.append(djJbsj.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append((String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append((String) skssrq.get(Skssrq.SKSSRQ_ND)).append("' ");
			querySqlCb.append(" AND SBDM = '");
			querySqlCb.append(Constant.TABLE_ID_ZFJGSDS_2008).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());
			
			String queryFlagZb = "0";
			String queryFlagCb = "0";
			
			if(queryRsZb.next()){ queryFlagZb = "1"; }
			if(queryRsCb.next()){ queryFlagCb = "1"; }

			rsMap.put("queryFlagZb", queryFlagZb);
			rsMap.put("queryFlagCb", queryFlagCb);
			
			HashMap sbsjZbMap = new HashMap();
			HashMap sbsjCbMap = new HashMap();
			
			while (queryRsZb.next()) {
				if (queryRsZb.getString("hc").equals("10")) {
					sbsjZbMap.put("fpbl", queryRsZb.getString("yz"));
				}
				if (queryRsZb.getString("hc").equals("11")) {
					sbsjZbMap.put("fpsdse", queryRsZb.getString("yz"));
				}
			}
			while (queryRsCb.next()) {
				if (queryRsCb.getString("hc").equals("17") && queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpbl", queryRsCb.getString("yz"));
				}
				if (queryRsCb.getString("hc").equals("18") && queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpsdse", queryRsCb.getString("yz"));
				}
			}
			
			rsMap.put("sbsjZbMap", sbsjZbMap);
			rsMap.put("sbsjCbMap", sbsjCbMap);
			
			System.out.println("queryCascadeZfjgData()���������������ѯ������ϡ�����");

		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil
					.getBaseException(localException);
		} finally {
			this.release(queryRsZb, queryPstmtZb);
			this.release(queryRsCb, queryPstmtCb);
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return rsMap;
	}
    
    /**
	 * @description �������������в����޸Ĳ�������
	 * @author gaoyh
	 * @modify_date 2012-03-16
	 * @param pData
	 * @param nsfs_zfjg
	 * @throws BaseException
	 * @throws ApplicationException
	 */
    public void saveCascadeZfjgData(Map pData) throws BaseException, ApplicationException {

		if (pData == null) {
			throw new ApplicationException("���뱣�����Ϊ�գ��޷���������");
		}
		
		Connection conn = null;
		CzzssdsBO qysdsndbo = (CzzssdsBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		
		System.out.println("����saveCascadeZfjgData()�������ݡ���������������������������");

		// Date sbrq = new Date(qysdsndbo.getSbrq().getTime());
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date sbrq =
		// format.parse(DateTimeUtil.timestampToString(qysdsndbo.getSbrq()));
		// Date sksssqq =
		// format.parse(DateTimeUtil.timestampToString(qysdsndbo.getSkssksrq()));
		// Date sksssqz =
		// format.parse(DateTimeUtil.timestampToString(qysdsndbo.getSkssjsrq()));
		
		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.otherSkssrq(curDate);

		String sbrq = DateTimeUtil.timestampToString(curDate);
		String skssksrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSKSRQ));
		String skssjsrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSJSRQ));
		
		BigDecimal divisor = new BigDecimal(100);
		DecimalFormat percentFormat = new DecimalFormat("#########.00%");

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			
			HashMap queryResult = this.queryCascadeZfjgData(pData);
			String queryFlagZb = (String) queryResult.get("queryFlagZb");
			String queryFlagCb = (String) queryResult.get("queryFlagCb");

			//String nsfs_old = (String) nsfs_zfjg.get("nsfs_old");
			//String zfjg_old = (String) nsfs_zfjg.get("zfjg_old");

			// ��ȡ�걨����
			String[] hcZb = { "1", "2", "3", "4", "5", "6", "10", "11" };
			String[] hcCb = { "12", "13", "17", "18", "14", "15", "16" };
			String[] yzZb = new String[8];
			String[] yzCb = new String[7];
			
			//System.out.println("djJbsj.getNsrmc()==="+ djJbsj.getNsrmc());
			//System.out.println("djJbsj.getNsrsbh()==="+ djJbsj.getSwdjzh());
			
			if(qysdsndbo.getZfjg().equals("1")){
				for (int i = 0; i < yzZb.length; i++) {
					switch (i) {
						case 0:
							yzZb[i] = djJbsj.getNsrmc();
							break;
						case 1:
							yzZb[i] = djJbsj.getSwdjzh();
							break;
						case 2:
							yzZb[i] = qysdsndbo.getYnsdse();
							break;
						case 3:
							yzZb[i] = qysdsndbo.getZjgyftsdse();
							break;
						case 4:
							yzZb[i] = qysdsndbo.getCzjzfpsdse();
							break;
						case 5:
							yzZb[i] = qysdsndbo.getFzjgyftsdse();
							break;
						case 6:
							yzZb[i] = "0";
							break;
						case 7:
							yzZb[i] = "0.00";
							break;
						default:
							throw new SystemException("���ڸ�ֵ��Χ����");
					}
				}
			}
			
			
			if(qysdsndbo.getZfjg().equals("2")){
				if (queryFlagZb.equals("0")) {
					BigDecimal fpblZb = new BigDecimal(qysdsndbo.getFpbl());
			        System.out.println("fpblCbResult======"+fpblZb.divide(divisor, 4, 2));
					for (int i = 0; i < yzZb.length; i++) {
						switch (i) {
							case 0:
								yzZb[i] = "";
								break;
							case 1:
								yzZb[i] = "";
								break;
							case 2:
								yzZb[i] = "0.00";
								break;
							case 3:
								yzZb[i] = "0.00";
								break;
							case 4:
								yzZb[i] = "0.00";
								break;
							case 5:
								yzZb[i] = qysdsndbo.getFzjgyftsdse();
								break;
							case 6:
								yzZb[i] = percentFormat.format(fpblZb.divide(divisor, 4, 2)).toString();
								break;
							case 7:
								yzZb[i] = qysdsndbo.getFpsdse();
								break;		
							default:
								throw new SystemException("���ڸ�ֵ��Χ����");
						}
						System.out.println("hcZb[" + i + "]" + "========" + hcZb[i] + "======" + "yzZb[" + i + "]" + "======" + yzZb[i]);
					}
				}
				
				if (queryFlagCb.equals("0")) {
					BigDecimal fpblCb = new BigDecimal(qysdsndbo.getFpbl());
			        System.out.println("fpblCbResult======"+fpblCb.divide(divisor, 4, 2));
					for (int i = 0; i < yzCb.length; i++) {
						switch (i) {
							case 0:
								yzCb[i] = djJbsj.getSwdjzh();
								break;
							case 1:
								yzCb[i] = djJbsj.getNsrmc();
								break;
							case 2:
								yzCb[i] = fpblCb.divide(divisor, 4, 2).toString();
								break;
							case 3:
								yzCb[i] = qysdsndbo.getFpsdse();
								break;
							case 4:
								yzCb[i] = "0.00";
								break;
							case 5:
								yzCb[i] = "0.00";
								break;
							case 6:
								yzCb[i] = "0.00";
								break;
							
							default:
								throw new SystemException("���ڸ�ֵ��Χ����");
						}
					}
				}
				
				if (queryFlagZb.equals("1")) {
					HashMap sbsjZbMap = (HashMap) queryResult.get("sbsjZbMap");
					HashMap sbsjCbMap = (HashMap) queryResult.get("sbsjCbMap");
					
					String fpblHjOld = (String)sbsjZbMap.get("fpbl");
					String fpblOld = (String)sbsjCbMap.get("fpbl");			
					
					double tmpFpblHj = Double.parseDouble(fpblHjOld.substring(0,fpblHjOld.indexOf("%")));
					double tmpFpblOld = Double.parseDouble(fpblOld)*100;
					double tmpFpblNew = Double.parseDouble(qysdsndbo.getFpbl());
					
					double tmpFpsdseHj = Double.parseDouble((String)sbsjZbMap.get("fpsdse"));			
					double tmpFpsdseOld = Double.parseDouble((String)sbsjCbMap.get("fpsdse"));			
					double tmpFpsdseNew = Double.parseDouble(qysdsndbo.getFpsdse());
					
					double fpblHjZb = tmpFpblHj+tmpFpblNew-tmpFpblOld;
					double fpsdseZb = tmpFpsdseHj+tmpFpsdseNew-tmpFpsdseOld;
					
					BigDecimal updateFpblHj = new BigDecimal(fpblHjZb);
					String updateFpsdseHj = ""+fpsdseZb;
					
					System.out.println("updateFpblHj============="+updateFpblHj);
					System.out.println("updateFpsdseHj============="+updateFpsdseHj);
					
					for (int i = 5; i < yzZb.length; i++) {
						switch (i) {
							case 5:
								yzZb[i] = qysdsndbo.getFzjgyftsdse();
								break;
							case 6:
								yzZb[i] = percentFormat.format(updateFpblHj.divide(divisor, 4, 2)).toString();
								break;
							case 7:
								yzZb[i] = updateFpsdseHj;
								break;
							default:
								throw new SystemException("���ڸ�ֵ��Χ����");
						}
					}				
				}
				
				if (queryFlagCb.equals("1")) {
					BigDecimal fpblCb = new BigDecimal(qysdsndbo.getFpbl());
					for (int i = 0; i < yzCb.length-3; i++) {
						switch (i) {
							case 0:
								yzCb[i] = djJbsj.getSwdjzh();
								break;
							case 1:
								yzCb[i] = djJbsj.getNsrmc();
								break;
							case 2:
								yzCb[i] = fpblCb.divide(divisor, 4, 2).toString();
								break;
							case 3:
								yzCb[i] = qysdsndbo.getFpsdse();
								break;
							default:
								throw new SystemException("���ڸ�ֵ��Χ����");
						}
					}
				}
			}
			

			PreparedStatement insertPstmtZb = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement insertPstmtCb = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			PreparedStatement updatePstmtZb = conn.prepareStatement("UPDATE SBDB.SB_JL_QYSDSSBB_ZB_JD SET YZ=?, LRSJ=? WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=? AND HC=?");
			PreparedStatement updatePstmtCb = conn.prepareStatement("UPDATE SBDB.SB_JL_QYSDSSBB_CB_JD SET YZ=?, LRSJ=? WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=? AND HC=? AND ZHS=?");

			if(qysdsndbo.getZfjg().equals("1")){
				if (queryFlagZb.equals("0")) {
					System.out.println("111111111111111111111111111111111111111111111");
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, djJbsj.getJsjdm());
						insertPstmtZb.setString(2, djJbsj.getNsrmc());
						insertPstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						insertPstmtZb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtZb.setDate(6, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(7, Constant.QYSDSJB_VERSION_2012);
						insertPstmtZb.setString(8, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(9, Constant.TABLE_ID_ZFJGSDS_2008);
						insertPstmtZb.setString(10, Constant.TABLE_NAME_ZFJGSDS_2008);
						insertPstmtZb.setString(11, hcZb[i]);
						insertPstmtZb.setDate(12, new java.sql.Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setDate(13, new java.sql.Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(14, QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE);
						insertPstmtZb.setString(15, yzZb[i]);
						insertPstmtZb.setString(16, "");
						insertPstmtZb.setString(17, djJbsj.getSwjgzzjgdm());
						insertPstmtZb.setString(18, djJbsj.getJsjdm());
						insertPstmtZb.setDate(19, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(20, djJbsj.getJsjdm());
						insertPstmtZb.setDate(21, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(22, djJbsj.getQxdm());
						insertPstmtZb.addBatch();
					}
					insertPstmtZb.executeBatch();
				}
				if (queryFlagZb.equals("1")) {
					System.out.println("2222222222222222222222222222222222222222222222222222222");
					for (int i = 0; i < hcZb.length-2; i++) {
						updatePstmtZb.setString(1, yzZb[i]);
						updatePstmtZb.setDate(2, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						updatePstmtZb.setString(3, djJbsj.getJsjdm());
						updatePstmtZb.setString(4, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						updatePstmtZb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtZb.setString(6, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						updatePstmtZb.setString(7, Constant.TABLE_ID_ZFJGSDS_2008);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}
			}

			if(qysdsndbo.getZfjg().equals("2")){
				if (queryFlagZb.equals("0")) {
					System.out.println("3333333333333333333333333333333333333333333333333333333333333333");
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, djJbsj.getJsjdm());
						insertPstmtZb.setString(2, djJbsj.getNsrmc());
						insertPstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						insertPstmtZb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtZb.setDate(6, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(7, Constant.QYSDSJB_VERSION_2012);
						insertPstmtZb.setString(8, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(9, Constant.TABLE_ID_ZFJGSDS_2008);
						insertPstmtZb.setString(10, Constant.TABLE_NAME_ZFJGSDS_2008);
						insertPstmtZb.setString(11, hcZb[i]);
						insertPstmtZb.setDate(12, new java.sql.Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setDate(13, new java.sql.Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(14, QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE);
						insertPstmtZb.setString(15, yzZb[i]);
						insertPstmtZb.setString(16, "");
						insertPstmtZb.setString(17, djJbsj.getSwjgzzjgdm());
						insertPstmtZb.setString(18, djJbsj.getJsjdm());
						insertPstmtZb.setDate(19, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(20, djJbsj.getJsjdm());
						insertPstmtZb.setDate(21, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(22, djJbsj.getQxdm());
						insertPstmtZb.addBatch();
					}
					insertPstmtZb.executeBatch();
				}
				if (queryFlagCb.equals("0")) {
					System.out.println("444444444444444444444444444444444444444444444444444444");
					for (int i = 0; i < 7; i++) {
						insertPstmtCb.setString(1, djJbsj.getJsjdm());
						insertPstmtCb.setString(2, djJbsj.getNsrmc());
						insertPstmtCb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtCb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						insertPstmtCb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtCb.setString(6, Constant.QYSDSJB_VERSION_2012);
						insertPstmtCb.setString(7, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtCb.setString(8, Constant.TABLE_ID_ZFJGSDS_2008);
						insertPstmtCb.setString(9, Constant.TABLE_NAME_ZFJGSDS_2008);
						insertPstmtCb.setString(10, hcCb[i]);
						insertPstmtCb.setString(11, "1");
						insertPstmtCb.setString(12, "");
						insertPstmtCb.setString(13, yzCb[i]);
						insertPstmtCb.setString(14, djJbsj.getSwjgzzjgdm());
						insertPstmtCb.setString(15, djJbsj.getJsjdm());
						insertPstmtCb.setDate(16, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtCb.setString(17, djJbsj.getJsjdm());
						insertPstmtCb.setDate(18, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtCb.addBatch();
					}
					insertPstmtCb.executeBatch();
				}
				
				
				if (queryFlagZb.equals("1")) {
					System.out.println("555555555555555555555555555555555555555555555555555555555");
					for (int i = 5; i < hcZb.length; i++) {
						updatePstmtZb.setString(1, yzZb[i]);
						updatePstmtZb.setDate(2, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						updatePstmtZb.setString(3, djJbsj.getJsjdm());
						updatePstmtZb.setString(4, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						updatePstmtZb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtZb.setString(6, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						updatePstmtZb.setString(7, Constant.TABLE_ID_ZFJGSDS_2008);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}
				
				if (queryFlagCb.equals("1")) {
					System.out.println("6666666666666666666666666666666666666666666666666666666666666");
					for (int i = 0; i < hcCb.length-3; i++) {
						updatePstmtCb.setString(1, yzCb[i]);
						updatePstmtCb.setDate(2, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						updatePstmtCb.setString(3, djJbsj.getJsjdm());
						updatePstmtCb.setString(4, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						updatePstmtCb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtCb.setString(6, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						updatePstmtCb.setString(7, Constant.TABLE_ID_ZFJGSDS_2008);
						updatePstmtCb.setString(8, hcCb[i]);
						updatePstmtCb.setString(9, "1");
						updatePstmtCb.addBatch();
					}
					updatePstmtCb.executeBatch();
				}
			}
			
			
			
//			insertPstmtZb.executeBatch();
//			insertPstmtCb.executeBatch();			
//			updatePstmtZb.executeBatch();
//			updatePstmtCb.executeBatch();
			
			System.out.println("saveCascadeZfjgData()����������������������ϡ�����");

			insertPstmtZb.close();
			insertPstmtCb.close();
			updatePstmtZb.close();
			updatePstmtCb.close();
			conn.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil.getBaseException(localException);
		}
	}
    
    /**
	 * @decription ����ɾ�����������
	 * @author gaoyh
	 * @modify_date 2012-03-16
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
    public void deleteCascadeZfjgData(Map pData) throws BaseException,
			ApplicationException {
		if (pData == null) {
			throw new ApplicationException("���뱣�����Ϊ�գ��޷������걨������");
		}

		Connection conn = null;

		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		CzzssdsBO czzsbo = (CzzssdsBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.otherSkssrq(curDate);

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();

			PreparedStatement detelePstmtZb = conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			PreparedStatement detelePstmtCb = conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			
			detelePstmtZb.setString(1, czzsbo.getJsjdm());
			detelePstmtZb.setString(2, (String) skssrq.get(Skssrq.SKSSRQ_ND));
			detelePstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			detelePstmtZb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
			detelePstmtZb.setString(5, Constant.TABLE_ID_ZFJGSDS_2008);
			detelePstmtZb.addBatch();

			detelePstmtCb.setString(1, czzsbo.getJsjdm());
			detelePstmtCb.setString(2, (String) skssrq.get(Skssrq.SKSSRQ_ND));
			detelePstmtCb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			detelePstmtCb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
			detelePstmtCb.setString(5, Constant.TABLE_ID_ZFJGSDS_2008);
			detelePstmtCb.addBatch();
			
			detelePstmtZb.executeBatch();
			detelePstmtCb.executeBatch();
			
			System.out.println("deleteCascadeZfjgData()��������������ɾ��������ϡ�����");

			detelePstmtZb.close();
			detelePstmtCb.close();
			conn.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil.getBaseException(localException);
		}
	}
    
    /**
	 * @description �ͷ����ݿ���Դ
	 * @author gaoyh
	 * @modify_date 2012-03-15
	 * @param rs
	 * @param stmt
	 * @param con
	 */
    public void release(ResultSet rs, Statement stmt){
		if(rs!=null){
			  try{
			      rs.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(stmt!=null){
			  try{
			      stmt.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
	}
    public void releaseAll(ResultSet rs, Statement stmt, Connection con){
		if(rs!=null){
			  try{
			      rs.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(stmt!=null){
			  try{
			      stmt.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(con!=null){
		  try{
		      con.close();
		  }catch (Exception ex) {
			  ex.printStackTrace();
		  }
		}
	}
    
	public static void main(String[] args) throws Exception{
		
		java.sql.Date d = (java.sql.Date) (new java.sql.Date(TinyTools.stringToDate("2011-11-11", "yyyy-MM-dd").getTime()));
		System.out.println(d);
		
		
	}

}
