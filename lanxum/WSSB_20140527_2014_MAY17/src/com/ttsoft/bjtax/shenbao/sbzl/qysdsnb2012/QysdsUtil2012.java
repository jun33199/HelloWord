package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 08企业所得税申报工具类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
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
	// 企业所得税税率
	private static final String QYSDS_SL = "0.25";

	public QysdsUtil2012() {
	}
	
	/**
	 * 获取季度
	 *
	 * @return String
	 */
	public String getJbDM() {
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		String jd = Skssrq.preQuarter(curDate);
		return jd;
	}

	/**
	 * 将传入Map的key值进行排序
	 *
	 * @param declareMap
	 *            传入的Map
	 * @return 排序后的List
	 */
	public ArrayList arrangeMapKey(HashMap declareMap) {
		// 定义keyList
		ArrayList keyList = new ArrayList();

		Iterator it = declareMap.keySet().iterator();
		while (it.hasNext()) {
			// 获取当前key
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
	 * 将传入的double值转换成字符串输出 如果double为*.0，则只要整数位
	 *
	 * @param d
	 *            需要转换的double值
	 * @return 转换后的字符串
	 */
	public String convertStr(double d) {
		String str = String.valueOf(d);
		if (str.endsWith(".0")) {
			str = str.substring(0, (str.indexOf(".")));
		}
		return str;
	}

	/**
	 * 格式化日期
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
			// 先将日期部分格式化
			if (retStr.substring(6, 7).equals("-")) {
				retStr = retStr.substring(0, 5) + "0" + retStr.substring(5);
			}
			if (retStr.length() < 10) {
				retStr = retStr.substring(0, 8) + "0" + retStr.substring(8);
				// 处理时间部分
			}
			if (st.countTokens() < 1) {
				return retStr + " 00:00:00.000"; // 仅有日期
			}
			String s = new String();
			s = st.nextToken();
			st = new StringTokenizer(s, ":");
			int count = st.countTokens();
			if (count == 1) {
				retStr += " " + s + ":00:00.000"; // 时间中只有小时
			}
			if (count == 2) {
				retStr += " " + s + ":00.000"; // 时间中只有小时、分钟
			}
			if (count == 3) {
				retStr += " " + s + ".000"; // 时间中无毫秒
			}
			return retStr;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得核定信息
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
		String qyzssllx = "3"; // 缺省为正常申报

		// 企业征税的税率 相对于企业征税的税率类型
		String qyzssl = QYSDS_SL;

		// 应纳所得税额
		String ynsdse = "0.00";
		// 定额征收税额
		String dezsse = "0.00";

		// 当前时间
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

		// 征期
		Map skssrq = new HashMap();

		// 税款所属开始和结束日期
		Timestamp skssksrq;
		Timestamp skssjsrq;

		// 取得所在的季度
		String jd = Skssrq.preQuarter(sbrq);

		ServiceProxy proxy = new ServiceProxy();

		// 查询税费接口
		QysdsSet qysdsSet = null;

		// 数据库连接对象
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// 减免资格标识
		boolean jm_type = false;

		try {
			if (bblx.equals(Constant.NB)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// 取得税款所属开始和结束日期
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// 年报
			} else {

				if ("4".equals(jd)) {

					skssrq = Skssrq.yearSkssrq(sbrq);

					// 取得税款所属开始和结束日期
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// 如果为第4季度的时候调用年报的接口
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "01");// 年报
				} else {

					skssrq = Skssrq.otherSkssrq(sbrq);

					// 取得税款所属开始和结束日期
					skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
					skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

					// 如果不为第4季度的时候调用季报的接口
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, "00");// 季报
				}

				// qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
				// skssjsrq, "00");//季报
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1、查询企业征收方式
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// 初值
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		System.out.println("当前申报的季度jd:" + jd);

		System.out.println("当前申报的申报日期sbrq:" + sbrq);

		System.out.println("当前申报的税款所属开始日期skssksrq:" + skssksrq);

		System.out.println("当前申报的税款所属结束日期skssjsrq:" + skssjsrq);
        System.out.print("获取征收方式代码》》》》》》》》》》》》》》》》开始！！！！！！！！！！");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
                        System.out.print("征收方式代码： ========= " + zsfsdm);

			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// 纯益率征收
					qyzssllx = "2";
				} else {
					// 高新技术和纯益率企业
					qyzssllx = "5";
					qyzssl = "0.15";
					hdvo.setJmzg("1"); // 有减免资格
				}
				hdvo.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// 定额征收
				qyzssllx = "4";
				// 此时本字段代表企业核定税额
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				hdvo.setDezsse(dezsse);
			}
		}

		// 2、查询是否是高新技术企业
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// 高新技术和纯益率企业
				qyzssllx = "5";
			} else {
				// 类型为高新技术企业
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			hdvo.setJmzg("1"); // 有减免资格
		}

		// 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				hdvo.setJmzg("1"); // 有减免资格
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// 非乡镇企业的减免情况
			hdvo.setJmzg("1"); // 有减免资格
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		// sbsj.setSl(qyzssl);

	}
	/**
	 * 核定征收企业 将旧的VO对象转换为XML-VO对象。
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
		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// 填充VO。
		// 纳税人信息。
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
		nsrxx.setNsrsbh(djJbsj.getSwdjzh());

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// 核定信息，包含一部分申报信息
		getHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// 信息
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsndbo.getJd());
		sbxx.setNd(qysdsndbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

		// 申报数据
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
		// 企业所得税年报
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML文档信息
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
	 * 核定征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsBO Hdzsconvert2VO(HdzssdsnbVO qysdsnbvo) throws BaseException {
		HdzssdsBO qysdsnbbo = new HdzssdsBO();
		// 纳税人信息
		qysdsnbbo.setJsjdm(qysdsnbvo.getNsrxx().getJsjdm());
		qysdsnbbo.setNsrmc(qysdsnbvo.getNsrxx().getNsrmc());
		qysdsnbbo.setSwjgzzjgdm(qysdsnbvo.getNsrxx().getSwjgzzjgdm());
		// 核定信息
		qysdsnbbo.setCyl(qysdsnbvo.getHdxx().getCyl());
		qysdsnbbo.setDezsse(qysdsnbvo.getHdxx().getDezsse());
		qysdsnbbo.setJmzg(qysdsnbvo.getHdxx().getJmzg());
		qysdsnbbo.setQyzslx(qysdsnbvo.getHdxx().getQyzslx());
		qysdsnbbo.setXzqy(qysdsnbvo.getHdxx().getXzqy());
		qysdsnbbo.setYbjmsl(qysdsnbvo.getHdxx().getYbjmsl());
		// 申报信息
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
		

		// 申报数据。
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
     * 获取征收方式
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
            //取最后核定的一条记录
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

            //关闭数据库对象
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
     * 得到给定日期的年份 为int型
     * @param date 给定的日期
     * @return int 年份值
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
