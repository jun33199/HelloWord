package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008;

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
import java.sql.Statement;
import java.sql.Timestamp;
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
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.CzzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.FzjgList2008VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.Fzjgxx2008VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.HdzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.HdzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.NsrxxVO_HDZS;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.Sbxx2008VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.Zjgxx2008VO;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DateTimeUtil;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.sql.PreparedStatement;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.BaseException;

public class QysdsUtil2008 {
	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.25";

	public QysdsUtil2008() {
	}

	/**
	 * 08��ҵ����˰������˰��֧��������� ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public ZfjgqysdsjbVO Zfjgconvert2XMLVO(ZfjgqysdsjbBO qysdsbo,
			SWDJJBSJ djJbsj) throws BaseException {
		ZfjgqysdsjbVO qysdsvo = new ZfjgqysdsjbVO();
		Sbxx2008VO sbxx = new Sbxx2008VO();
		Zjgxx2008VO zjgxx = new Zjgxx2008VO();
		FzjgList2008VO fzjgList = new FzjgList2008VO();

		// ���ϵͳ��ǰ����
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// ���VO��
		// ///////////////////////////////////
		HashMap sbsj = (HashMap) qysdsbo.getSbsj();
		// sbxx
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSbrqShow(qysdsbo.getSbrqshow());
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
		sbxx.setFpblyxqq((String) sbsj.get("1"));
		sbxx.setFpblyxqz((String) sbsj.get("2"));

		// zjgxx
		zjgxx.setJsjdm(djJbsj.getJsjdm());
		zjgxx.setNsrsbh(qysdsbo.getNsrsbh());
		zjgxx.setNsrmc(qysdsbo.getNsrmc());
		// zjgxx.setNsrsbh((String) sbsj.get("3"));
		// zjgxx.setNsrmc((String) sbsj.get("4"));
		zjgxx.setSrze(((String) sbsj.get("5")) == null ? "0.00" : (String) sbsj
				.get("5"));
		zjgxx.setGzze(((String) sbsj.get("6")) == null ? "0.00" : (String) sbsj
				.get("6"));
		zjgxx.setZcze(((String) sbsj.get("7")) == null ? "0.00" : (String) sbsj
				.get("7"));
		zjgxx.setHj(((String) sbsj.get("8")) == null ? "0.00" : (String) sbsj
				.get("8"));
		zjgxx.setFtse(((String) sbsj.get("9")) == null ? "0.00" : (String) sbsj
				.get("9"));

		// fzjgxx
		ArrayList fzjgxxList = new ArrayList();
		int rows = Integer.parseInt((String) sbsj
				.get(Constant.ZFJGSDSJB_2008_MAX_ROW));
		for (int i = 1; i <= rows; i++) {
			Fzjgxx2008VO fzjg = new Fzjgxx2008VO();
			fzjg.setIndex(String.valueOf(i));
			for (int j = 10; j < 18; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i);
				// System.out.println("util id = " + id);
				// System.out.println("util value = " + (String) sbsj.get(id));
				switch (j) {
				case 10:
					// ��֧������˰��ʶ���
					fzjg.setNsrsbh((String) sbsj.get(id));
					break;
				case 11:
					// ��֧��������
					fzjg.setNsrmc((String) sbsj.get(id));
					break;
				case 12:
					// ��֧���������ܶ�
					fzjg.setSrze((String) sbsj.get(id));
					break;
				case 13:
					// ��֧���������ܶ�
					fzjg.setGzze((String) sbsj.get(id));
					break;
				case 14:
					// ��֧�����ʲ��ܶ�
					fzjg.setZcze((String) sbsj.get(id));
					break;
				case 15:
					// ��֧�����ϼ�
					fzjg.setHj((String) sbsj.get(id));
					break;
				case 16:
					// ��֧�����������
					fzjg.setFpbl((String) sbsj.get(id));
					break;
				case 17:
					// ��֧��������˰��
					fzjg.setFpse((String) sbsj.get(id));
					break;
				}
			}
			fzjgxxList.add(fzjg);
		}
		fzjgList.setMxxx(fzjgxxList);

		// ��ҵ����˰�걨
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);

		// XML�ĵ���Ϣ
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_ZFJGSDSJB_2008);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_ZFJGSDSJB_2008);
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
		// qysdsbo.setFpblyxqq(qysdsvo.getSbxx().getFpblyxqq());
		// qysdsbo.setFpblyxqq(qysdsvo.getSbxx().getFpblyxqz());
		// fpblyxqq
		sbsj.put("1", qysdsvo.getSbxx().getFpblyxqq());
		// fpblyxqz
		sbsj.put("2", qysdsvo.getSbxx().getFpblyxqz());

		/**
		 * �ܻ�����Ϣ
		 */
		// jsjdm
		qysdsbo.setJsjdm(qysdsvo.getZjgxx().getJsjdm());
		// nsrsbh
		qysdsbo.setNsrsbh(qysdsvo.getZjgxx().getNsrsbh());
		sbsj.put("3", qysdsvo.getZjgxx().getNsrsbh());
		// nsrmc
		qysdsbo.setNsrmc(qysdsvo.getZjgxx().getNsrmc());
		sbsj.put("4", qysdsvo.getZjgxx().getNsrmc());
		// srze
		sbsj.put("5", qysdsvo.getZjgxx().getSrze());
		// gzze
		sbsj.put("6", qysdsvo.getZjgxx().getGzze());
		// zcze
		sbsj.put("7", qysdsvo.getZjgxx().getZcze());
		// hj
		sbsj.put("8", qysdsvo.getZjgxx().getHj());
		// ftse
		sbsj.put("9", qysdsvo.getZjgxx().getFtse());

		/**
		 * ��֧������ϸ��Ϣ
		 */
		ArrayList fzjgmxList = (ArrayList) qysdsvo.getFzjgxx().getMxxx();
		// System.out.println("fzjgmxList size = " + fzjgmxList.size());
		for (int i = 0; i < fzjgmxList.size(); i++) {
			Fzjgxx2008VO fzjgxx = (Fzjgxx2008VO) fzjgmxList.get(i);
			// System.out.println("index = " + fzjgxx.getIndex());
			// ��id��10~17��Ԫ���ֵ����Map��
			for (int j = 10; j < 18; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i + 1);
				switch (j) {
				case 10:
					// ��֧������˰��ʶ���
					sbsj.put(id, fzjgxx.getNsrsbh());
					break;
				case 11:
					// ��֧��������
					sbsj.put(id, fzjgxx.getNsrmc());
					break;
				case 12:
					// ��֧���������ܶ�
					sbsj.put(id, fzjgxx.getSrze());
					break;
				case 13:
					// ��֧���������ܶ�
					sbsj.put(id, fzjgxx.getGzze());
					break;
				case 14:
					// ��֧�����ʲ��ܶ�
					sbsj.put(id, fzjgxx.getZcze());
					break;
				case 15:
					// ��֧�����ϼ�
					sbsj.put(id, fzjgxx.getHj());
					break;
				case 16:
					// ��֧�����������
					sbsj.put(id, fzjgxx.getFpbl());
					break;
				case 17:
					// ��֧��������˰��
					sbsj.put(id, fzjgxx.getFpse());
					break;
				}
			}
		}

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
		sbsj.setYssdl(qysdsndbo.getYssdl());
		sbsj.setYnssde(qysdsndbo.getYnssde());
		sbsj.setSl(qysdsndbo.getSl());
		sbsj.setYnsdse(qysdsndbo.getYnsdse());
		sbsj.setJmsdse(qysdsndbo.getJmsdse());
		sbsj.setYyjsdse(qysdsndbo.getYyjsdse());
		sbsj.setYbsdse(qysdsndbo.getYbsdse());
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		// ��ҵ����˰�걨
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSJB_2008);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_HDZSSDSJB_2008);
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
		sbsj.setSl(qysdsndbo.getSl());
		sbsj.setYnsdse(qysdsndbo.getYnsdse());
		sbsj.setJmsdse(qysdsndbo.getJmsdse());
		sbsj.setSjyjsdse(qysdsndbo.getSjyjsdse());
		sbsj.setYbtsdse(qysdsndbo.getYbtsdse());
		sbsj.setZjgftsdse(qysdsndbo.getZjgftsdse());
		sbsj.setZyczjzsdse(qysdsndbo.getZyczjzsdse());
		sbsj.setFzjgftsdse(qysdsndbo.getFzjgftsdse());
        sbsj.setJzjnfzjgsdse(qysdsndbo.getJzjnfzjgsdse());
		sbsj.setFpbl(qysdsndbo.getFpbl());
		sbsj.setFpsdse(qysdsndbo.getFpsdse());
		// ��ҵ����˰�걨
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_CZZSSDSJB_2008);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_CZZSSDSJB_2008);
		qysdsnb.setYwlx(Constant.CA_YWLXDM_CZZSSDSJB_2008);

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
		qysdsjbbo.setYssdl(qysdsjbvo.getSbsj().getYssdl());
		qysdsjbbo.setYnssde(qysdsjbvo.getSbsj().getYnssde());
		qysdsjbbo.setSl(qysdsjbvo.getSbsj().getSl());
		qysdsjbbo.setYnsdse(qysdsjbvo.getSbsj().getYnsdse());
		qysdsjbbo.setJmsdse(qysdsjbvo.getSbsj().getJmsdse());
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
		qysdsjbbo.setSl(qysdsjbvo.getSbsj().getSl());
		qysdsjbbo.setYnsdse(qysdsjbvo.getSbsj().getYnsdse());
		qysdsjbbo.setJmsdse(qysdsjbvo.getSbsj().getJmsdse());
		qysdsjbbo.setSjyjsdse(qysdsjbvo.getSbsj().getSjyjsdse());
		qysdsjbbo.setYbtsdse(qysdsjbvo.getSbsj().getYbtsdse());
		qysdsjbbo.setZjgftsdse(qysdsjbvo.getSbsj().getZjgftsdse());
		qysdsjbbo.setZyczjzsdse(qysdsjbvo.getSbsj().getZyczjzsdse());
		qysdsjbbo.setFzjgftsdse(qysdsjbvo.getSbsj().getFzjgftsdse());
        qysdsjbbo.setJzjnfzjgsdse(qysdsjbvo.getSbsj().getJzjnfzjgsdse());
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


}
