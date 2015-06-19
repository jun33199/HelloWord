package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012;

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
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.xmlvo.HdzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.xmlvo.NsrxxVO_HDZS;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.Constant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
//import com.ttsoft.framework.exception.BaseException;
import com.syax.frame.exception.BaseException;
import com.ttsoft.framework.util.DateTimeUtil;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.sql.PreparedStatement;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.bjtax.shenbao.util.DBResource;




public class QysdsUtil2012 {
	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.25";

	public QysdsUtil2012() {
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
	 * @throws com.syax.frame.exception.BaseException 
	 */

	public void getHdxx(String jsjdm, HdxxVO hdvo, HdzsSbsjVO sbsj,
			SWDJJBSJ swdjjbsj, String bblx) throws BaseException {
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
			if (bblx.equals(Constant.NB)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// ȡ��˰��������ʼ�ͽ�������
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// �걨
			} else {

				if ("4".equals(jd)) {

					skssrq = Skssrq.yearSkssrq(sbrq);

					// ȡ��˰��������ʼ�ͽ�������
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// ���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "01");// �걨
				} else {

					skssrq = Skssrq.otherSkssrq(sbrq);

					// ȡ��˰��������ʼ�ͽ�������
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// �����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "00");// ����
				}

				// qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
				// skssjsrq, "00");//����
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// ��ֵ
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		System.out.println("��ǰ�걨�ļ���jd:" + jd);

		System.out.println("��ǰ�걨���걨����sbrq:" + sbrq);

		System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:" + skssksrq);

		System.out.println("��ǰ�걨��˰��������������skssjsrq:" + skssjsrq);
        System.out.print("��ȡ���շ�ʽ���롷��������������������������������ʼ��������������������");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
                        System.out.print("���շ�ʽ���룺 ========= " + zsfsdm);

			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					hdvo.setJmzg("1"); // �м����ʸ�
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
			hdvo.setJmzg("1"); // �м����ʸ�
		}

		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				hdvo.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			hdvo.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		// sbsj.setSl(qyzssl);

	}
	/**
	 * �˶�������ҵ ���ɵ�VO����ת��ΪXML-VO����
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsnbVO Hdzsconvert2XMLVO(HdzssdsBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws BaseException {
		SbxxVO sbxx = new SbxxVO();
		HdzsSbsjVO sbsj = new HdzsSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO_HDZS nsrxx = new NsrxxVO_HDZS();
		HdzssdsnbVO qysdsnb = new HdzssdsnbVO();
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
		
		sbsj.setZczb(qysdsndbo.getZczb());
		sbsj.setZcze(qysdsndbo.getZcze());
		// ��ҵ����˰�걨
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML�ĵ���Ϣ
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSNB_2012);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_HDZSSDSNB_2012);
		if (bblx.equals(Constant.NB)) {
			qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSNB);
		} else {
			qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSJB);
		}

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}

	/**
	 * �˶�������ҵ ��XML-VO����ת��Ϊ�ɵ�VO����
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsBO Hdzsconvert2VO(HdzssdsnbVO qysdsnbvo) throws BaseException {
		HdzssdsBO qysdsnbbo = new HdzssdsBO();
		// ��˰����Ϣ
		qysdsnbbo.setJsjdm(qysdsnbvo.getNsrxx().getJsjdm());
		qysdsnbbo.setNsrmc(qysdsnbvo.getNsrxx().getNsrmc());
		qysdsnbbo.setSwjgzzjgdm(qysdsnbvo.getNsrxx().getSwjgzzjgdm());
		// �˶���Ϣ
		qysdsnbbo.setCyl(qysdsnbvo.getHdxx().getCyl());
		qysdsnbbo.setDezsse(qysdsnbvo.getHdxx().getDezsse());
		qysdsnbbo.setJmzg(qysdsnbvo.getHdxx().getJmzg());
		qysdsnbbo.setQyzslx(qysdsnbvo.getHdxx().getQyzslx());
		qysdsnbbo.setXzqy(qysdsnbvo.getHdxx().getXzqy());
		qysdsnbbo.setYbjmsl(qysdsnbvo.getHdxx().getYbjmsl());
		// �걨��Ϣ
		qysdsnbbo.setFsdm(qysdsnbvo.getSbxx().getFsdm());
		qysdsnbbo.setJd(qysdsnbvo.getSbxx().getJd());
		qysdsnbbo.setNd(qysdsnbvo.getSbxx().getNd());
		try {
			qysdsnbbo.setSbrq(DateTimeUtil
					.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
							.getSbrq())));
			qysdsnbbo.setSkssjsrq(DateTimeUtil
					.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
							.getSkssjsrq())));
			qysdsnbbo.setSkssksrq(DateTimeUtil
					.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
							.getSkssksrq())));
		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
		}
		

		// �걨���ݡ�
		qysdsnbbo.setSbrqshow(qysdsnbvo.getSbsj().getSbrqshow());
		qysdsnbbo.setSyze(qysdsnbvo.getSbsj().getSyze());
		qysdsnbbo.setBzssr(qysdsnbvo.getSbsj().getBzssr());
		qysdsnbbo.setMssr(qysdsnbvo.getSbsj().getMssr());
		qysdsnbbo.setYssre(qysdsnbvo.getSbsj().getYssre());
		qysdsnbbo.setYssdl(qysdsnbvo.getSbsj().getYssdl());
		qysdsnbbo.setYnssde(qysdsnbvo.getSbsj().getYnssde());
		qysdsnbbo.setSl(qysdsnbvo.getSbsj().getSl());
		qysdsnbbo.setYnsdse(qysdsnbvo.getSbsj().getYnsdse());
		qysdsnbbo.setYyjsdse(qysdsnbvo.getSbsj().getYyjsdse());
		qysdsnbbo.setYbsdse(qysdsnbvo.getSbsj().getYbsdse());
		
		qysdsnbbo.setZczb(qysdsnbvo.getSbsj().getZczb());
		qysdsnbbo.setZcze(qysdsnbvo.getSbsj().getZcze());

		return qysdsnbbo;
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
    
	public static void main(String[] args) throws Exception{
		
		java.sql.Date d = (java.sql.Date) (new java.sql.Date(TinyTools.stringToDate("2011-11-11", "yyyy-MM-dd").getTime()));
		System.out.println(d);
		
		
	}

}
