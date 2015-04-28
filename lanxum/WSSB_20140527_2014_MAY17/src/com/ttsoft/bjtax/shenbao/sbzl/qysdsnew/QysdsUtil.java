package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.CzzsJbSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsFbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsnbFbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsFbSbsjVO;

public class QysdsUtil {
	// 企业所得税税率
	private static final String QYSDS_SL = "0.33";

	public QysdsUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		// /**
		// * 判断在减免审批结果表中是否有减免资格。
		// */
		//
		// try {
		//
		// conn = DBResource.getConnection();
		// StringBuffer bf = new StringBuffer();
		// bf.delete(0, bf.length());
		//
		// bf
		// .append(
		// " select t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp
		// t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
		// .append(" where t1.jsjdm= ").append(
		// StringUtils.getSQLStr(jsjdm)).append(
		// " and t2.sqspbh=t1.sqspbh ").append(
		// " and t2.jmsqsrq<= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.jmszzrq>= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.szsmdm='30' ").append(
		// " order by t1.cjrq desc ");
		//
		// System.out.println("企业所得税-查询减免类型，是否有减免资格SQL");
		// System.out.println(bf.toString());
		// st = conn.createStatement();
		// rs = st.executeQuery(bf.toString());
		//
		// if (rs.next()) {
		// jm_type = true;
		// }
		//
		// if (!jm_type) {
		// jm_type = false;
		// }
		// if (jm_type) {
		// hdvo.setJmzg("1"); // 有减免资格
		// }
		//
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// throw ExceptionUtil.getBaseException(ex, "查询减免类型，是否有减免资格失败");
		// } finally {
		// // 关闭数据库连接
		// DBResource.destroyConnection(conn);
		// }

	}

        public void getFbHdxx(String jsjdm, HdxxVO hdvo, HdzsFbSbsjVO sbsj,
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

                if (zsfs != null) {
                        String zsfsdm = zsfs.getZsfsdm();
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

                // /**
                // * 判断在减免审批结果表中是否有减免资格。
                // */
                //
                // try {
                //
                // conn = DBResource.getConnection();
                // StringBuffer bf = new StringBuffer();
                // bf.delete(0, bf.length());
                //
                // bf
                // .append(
                // " select t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp
                // t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
                // .append(" where t1.jsjdm= ").append(
                // StringUtils.getSQLStr(jsjdm)).append(
                // " and t2.sqspbh=t1.sqspbh ").append(
                // " and t2.jmsqsrq<= ").append(
                // StringUtils.getSQLFromTimestamp(skssjsrq)).append(
                // " and t2.jmszzrq>= ").append(
                // StringUtils.getSQLFromTimestamp(skssjsrq)).append(
                // " and t2.szsmdm='30' ").append(
                // " order by t1.cjrq desc ");
                //
                // System.out.println("企业所得税-查询减免类型，是否有减免资格SQL");
                // System.out.println(bf.toString());
                // st = conn.createStatement();
                // rs = st.executeQuery(bf.toString());
                //
                // if (rs.next()) {
                // jm_type = true;
                // }
                //
                // if (!jm_type) {
                // jm_type = false;
                // }
                // if (jm_type) {
                // hdvo.setJmzg("1"); // 有减免资格
                // }
                //
                // } catch (Exception ex) {
                // ex.printStackTrace();
                // throw ExceptionUtil.getBaseException(ex, "查询减免类型，是否有减免资格失败");
                // } finally {
                // // 关闭数据库连接
                // DBResource.destroyConnection(conn);
                // }

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
		NsrxxVO nsrxx = new NsrxxVO();
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
		sbsj.setSrze_bqs(qysdsndbo.getSrze_bqs());
		sbsj.setSrze_ljs(qysdsndbo.getSrze_ljs());
		sbsj.setYssdl_bqs(qysdsndbo.getYssdl_bqs());
		sbsj.setYssdl_ljs(qysdsndbo.getYssdl_ljs());
		sbsj.setYnssde_bqs(qysdsndbo.getYnssde_bqs());
		sbsj.setYnssde_ljs(qysdsndbo.getYnssde_ljs());
		sbsj.setYbdsdse_bqs(qysdsndbo.getYbdsdse_bqs());
		sbsj.setYbdsdse_ljs(qysdsndbo.getYbdsdse_ljs());
		sbsj.setSysl_bqs(qysdsndbo.getSysl_bqs());
		sbsj.setSysl_ljs(qysdsndbo.getSysl_ljs());
		sbsj.setYjsdse_bqs(qysdsndbo.getYjsdse_bqs());
		sbsj.setYjsdse_ljs(qysdsndbo.getYjsdse_ljs());
		sbsj.setSjyyjdsdse_bqs(qysdsndbo.getSjyyjdsdse_bqs());
		sbsj.setSjyyjdsdse_ljs(qysdsndbo.getSjyyjdsdse_ljs());
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		
		sbsj.setZczbje(qysdsndbo.getZczbje());
		sbsj.setZcze_ljs(qysdsndbo.getZcze_ljs());
		
		// 企业所得税年报
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML文档信息
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSNB);
		qysdsnb.setSchemaVersion(Constant.CA_XSLTDM_HDZSSDSNB);
		if (bblx.equals(Constant.NB)) {
			qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSNB);
		} else {
			qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSJB);
		}

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}
        /**
         * 核定征收企业 将旧的VO对象转换为XML-VO对象。
         *
         * @param qysdsjd
         * @param djJbsj
         * @return
         * @throws BaseException
         */
        public HdzsnbFbVO HdzsFbconvert2XMLVO(HdzssdsFbBO qysdsndbo, SWDJJBSJ djJbsj,
                        String bblx) throws BaseException {
                SbxxVO sbxx = new SbxxVO();
                HdzsFbSbsjVO sbsj = new HdzsFbSbsjVO();
                HdxxVO hdxx = new HdxxVO();
                NsrxxVO nsrxx = new NsrxxVO();
                HdzsnbFbVO qysdsnb = new HdzsnbFbVO();
                // 获得系统当前日期
                Timestamp curDate = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                // ////////////////////////////////////
                // 填充VO。
                // 纳税人信息。
                nsrxx.setJsjdm(djJbsj.getJsjdm());
                nsrxx.setNsrmc(djJbsj.getNsrmc());
                nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

                qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
                // 核定信息，包含一部分申报信息
                getFbHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

                // 信息
                sbxx.setFsdm(CodeConstant.FSDM_WSSB);
                sbxx.setJd(qysdsndbo.getJd());
                sbxx.setNd(qysdsndbo.getNd());
                sbxx.setSbrq(sdf.format(curDate));
                sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
                sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

                // 申报数据
                sbsj.setJmshj_je(qysdsndbo.getJmshj_je());
                sbsj.setGdqyh_je(qysdsndbo.getGdqyh_je());
                sbsj.setGxqy_je(qysdsndbo.getGxqy_je());
                sbsj.setMzzz_je(qysdsndbo.getMzzz_je());
                sbsj.setQt_je(qysdsndbo.getQt_je());
                sbsj.setQyrs(qysdsndbo.getQyrs());
                sbsj.setSshy(qysdsndbo.getSshy());
                sbsj.setXxwl_je(qysdsndbo.getXxwl_je());
                sbsj.setZcze(qysdsndbo.getZcze());
                sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
                // 企业所得税年报
                qysdsnb.setNsrxx(nsrxx);
                qysdsnb.setSbsj(sbsj);
                qysdsnb.setSbxx(sbxx);
                qysdsnb.setHdxx(hdxx);

                // XML文档信息
                qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSFBNB);
                qysdsnb.setSchemaVersion(Constant.CA_XSLTDM_HDZSSDSFBNB);
                if (bblx.equals(Constant.NB)) {
                        qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSFBNB);
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
        public HdzssdsFbBO HdzsFbconvert2VO(HdzsnbFbVO qysdsnbvo) throws BaseException {
                HdzssdsFbBO qysdsnbbo = new HdzssdsFbBO();
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
                qysdsnbbo.setSbrq(DateTimeUtil
                                .stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
                                                .getSbrq())));
                qysdsnbbo.setSkssjsrq(DateTimeUtil
                                .stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
                                                .getSkssjsrq())));
                qysdsnbbo.setSkssksrq(DateTimeUtil
                                .stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
                                                .getSkssksrq())));

                // 申报数据。
                qysdsnbbo.setSbrqshow(qysdsnbvo.getSbsj().getSbrqshow());
                qysdsnbbo.setJmshj_je(qysdsnbvo.getSbsj().getJmshj_je());
                qysdsnbbo.setGdqyh_je(qysdsnbvo.getSbsj().getGdqyh_je());
                qysdsnbbo.setGxqy_je(qysdsnbvo.getSbsj().getGxqy_je());
                qysdsnbbo.setMzzz_je(qysdsnbvo.getSbsj().getMzzz_je());
                qysdsnbbo.setQt_je(qysdsnbvo.getSbsj().getQt_je());
                qysdsnbbo.setQyrs(qysdsnbvo.getSbsj().getQyrs());
                qysdsnbbo.setSshy(qysdsnbvo.getSbsj().getSshy());
                qysdsnbbo.setXxwl_je(qysdsnbvo.getSbsj().getXxwl_je());
                qysdsnbbo.setZcze(qysdsnbvo.getSbsj().getZcze());
                return qysdsnbbo;
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
		qysdsnbbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
						.getSbrq())));
		qysdsnbbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
						.getSkssjsrq())));
		qysdsnbbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx()
						.getSkssksrq())));

		// 申报数据。
		qysdsnbbo.setSbrqshow(qysdsnbvo.getSbsj().getSbrqshow());
		qysdsnbbo.setSjyyjdsdse_bqs(qysdsnbvo.getSbsj().getSjyyjdsdse_bqs());
		qysdsnbbo.setSjyyjdsdse_ljs(qysdsnbvo.getSbsj().getSjyyjdsdse_ljs());
		qysdsnbbo.setSrze_bqs(qysdsnbvo.getSbsj().getSrze_bqs());
		qysdsnbbo.setSrze_ljs(qysdsnbvo.getSbsj().getSrze_ljs());
		qysdsnbbo.setSysl_bqs(qysdsnbvo.getSbsj().getSysl_bqs());
		qysdsnbbo.setSysl_ljs(qysdsnbvo.getSbsj().getSysl_ljs());
		qysdsnbbo.setYbdsdse_bqs(qysdsnbvo.getSbsj().getYbdsdse_bqs());
		qysdsnbbo.setYbdsdse_ljs(qysdsnbvo.getSbsj().getYbdsdse_ljs());
		qysdsnbbo.setYjsdse_bqs(qysdsnbvo.getSbsj().getYjsdse_bqs());
		qysdsnbbo.setYjsdse_ljs(qysdsnbvo.getSbsj().getYjsdse_ljs());
		qysdsnbbo.setYnssde_bqs(qysdsnbvo.getSbsj().getYnssde_bqs());
		qysdsnbbo.setYnssde_ljs(qysdsnbvo.getSbsj().getYnssde_ljs());
		qysdsnbbo.setYssdl_bqs(qysdsnbvo.getSbsj().getYssdl_bqs());
		qysdsnbbo.setYssdl_ljs(qysdsnbvo.getSbsj().getYssdl_ljs());
		
		qysdsnbbo.setZczbje(qysdsnbvo.getSbsj().getZczbje());
		qysdsnbbo.setZcze_ljs(qysdsnbvo.getSbsj().getZcze_ljs());

		return qysdsnbbo;
	}

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

	public String getJbDM() {
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		String jd = Skssrq.preQuarter(curDate);
		return jd;
	}

	public void getCzxx(String jsjdm, HdxxVO hdvo, CzzsJbSbsjVO sbsj,
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
			if (bblx.equals(Constant.CZNB)) {
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

		if (zsfs != null) {

			System.out.println("征收方式代码:" + zsfs.getZsfsdm());

			String zsfsdm = zsfs.getZsfsdm();
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

		// /**
		// * 判断在减免审批结果表中是否有减免资格。
		// */
		//
		// try {
		//
		// conn = DBResource.getConnection();
		// StringBuffer bf = new StringBuffer();
		// bf.delete(0, bf.length());
		//
		// bf
		// .append(
		// " select t1.jmslbdm as JMSLBDM from spdb.sp_jl_ybnsdwjmsp
		// t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
		// .append(" where t1.jsjdm= ").append(
		// StringUtils.getSQLStr(jsjdm)).append(
		// " and t2.sqspbh=t1.sqspbh ").append(
		// " and t2.jmsqsrq<= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.jmszzrq>= ").append(
		// StringUtils.getSQLFromTimestamp(skssjsrq)).append(
		// " and t2.szsmdm='30' ").append(
		// " order by t1.cjrq desc ");
		//
		// System.out.println("企业所得税-查询减免类型，是否有减免资格SQL");
		// System.out.println(bf.toString());
		// st = conn.createStatement();
		// rs = st.executeQuery(bf.toString());
		//
		// if (rs.next()) {
		// jm_type = true;
		// }
		//
		// if (!jm_type) {
		// jm_type = false;
		// }
		// if (jm_type) {
		// hdvo.setJmzg("1"); // 有减免资格
		// }
		//
		//
		// // 2007－4－4增加判断高新技术企业9010只判断审批件，不判断是否过期
		//
		// bf.delete(0, bf.length());
		//
		// bf
		// .append(
		// " select t1.sqspbh as sqspbh,t1.jmslbdm as JMSLBDM from
		// spdb.sp_jl_ybnsdwjmsp t1,spdb.sp_jl_ybnsdwjmspmx t2 ")
		// .append(" where t1.jsjdm= ").append(
		// StringUtils.getSQLStr(jsjdm)).append(
		// " and t2.sqspbh=t1.sqspbh ").append(
		// " and t2.szsmdm='30' ")
		// .append(" and t1.jmslbdm in ('9010')")
		// .append(
		// " order by t1.cjrq desc ");
		//
		// System.out.println("企业所得税-查询减免类型，是否有减免资格SQL，高新技术企业9010");
		// System.out.println(bf.toString());
		// st = conn.createStatement();
		// rs = st.executeQuery(bf.toString());
		//
		//
		// if (rs.next()) {
		// jm_type = true;
		// }
		//
		// if (!jm_type) {
		// jm_type = false;
		// }
		// if (jm_type) {
		// hdvo.setJmzg("1"); // 有减免资格
		// }
		//
		//
		// // 2007－4－4增加判断高新技术企业9010只判断审批件，不判断是否过期 end
		//
		//
		// } catch (Exception ex) {
		//
		// ex.printStackTrace();
		//
		// throw ExceptionUtil.getBaseException(ex, "查询减免类型，是否有减免资格失败");
		// } finally {
		// // 关闭数据库连接
		// DBResource.destroyConnection(conn);
		// }

	}

	/**
	 * 查账征收企业 将旧的VO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsjbVO Czzsconvert2XMLVO(CzzssdsBO qysdsbo, SWDJJBSJ djJbsj,
			String bblx) throws BaseException {
		SbxxVO sbxx = new SbxxVO();
		CzzsJbSbsjVO sbsj = new CzzsJbSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO nsrxx = new NsrxxVO();
		CzzssdsjbVO qysdsvo = new CzzssdsjbVO();
		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// 填充VO。
		// 纳税人信息。
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// 核定信息，包含一部分申报信息
		getCzxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// 信息
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));

		// 申报数据
		sbsj.setHznscyqyjdyjbl(qysdsbo.getHznscyqyjdyjbl());
		sbsj.setJmsdse(qysdsbo.getJmsdse());
		sbsj.setLrze(qysdsbo.getLrze());
		sbsj.setMbyqndks(qysdsbo.getMbyqndks());
		sbsj.setNstzjse(qysdsbo.getNstzjse());
		sbsj.setNstzzje(qysdsbo.getNstzzje());
		sbsj.setSbrqshow(qysdsbo.getSbrqshow());
		sbsj.setSjyyjdsdse(qysdsbo.getSjyyjdsdse());
		sbsj.setSysl(qysdsbo.getSysl());
		sbsj.setYbdsdse(qysdsbo.getYbdsdse());
		sbsj.setYnsdse(qysdsbo.getYnsdse());
		sbsj.setYnssde(qysdsbo.getYnssde());

		// 企业所得税年报
		qysdsvo.setNsrxx(nsrxx);
		qysdsvo.setSbsj(sbsj);
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setHdxx(hdxx);

		// XML文档信息
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_CZSSDSJB);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_CZZSSDSJB);

		qysdsvo.setYwlx(Constant.CA_YWLXDM_CZZSSDSJB);

		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsvo;
	}

	/**
	 * 查账征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsBO Czzsconvert2VO(CzzssdsjbVO qysdsvo) throws BaseException {
		CzzssdsBO qysdsbo = new CzzssdsBO();
		// 纳税人信息
		qysdsbo.setJsjdm(qysdsvo.getNsrxx().getJsjdm());
		qysdsbo.setNsrmc(qysdsvo.getNsrxx().getNsrmc());
		qysdsbo.setSwjgzzjgdm(qysdsvo.getNsrxx().getSwjgzzjgdm());
		// 核定信息
		qysdsbo.setCyl(qysdsvo.getHdxx().getCyl());
		qysdsbo.setDezsse(qysdsvo.getHdxx().getDezsse());
		qysdsbo.setJmzg(qysdsvo.getHdxx().getJmzg());
		qysdsbo.setQyzslx(qysdsvo.getHdxx().getQyzslx());
		qysdsbo.setXzqy(qysdsvo.getHdxx().getXzqy());
		qysdsbo.setYbjmsl(qysdsvo.getHdxx().getYbjmsl());
		// 申报信息
		qysdsbo.setFsdm(qysdsvo.getSbxx().getFsdm());
		qysdsbo.setJd(qysdsvo.getSbxx().getJd());
		qysdsbo.setNd(qysdsvo.getSbxx().getNd());
		qysdsbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSbrq())));
		qysdsbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssjsrq())));
		qysdsbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssksrq())));

		// 申报数据。
		qysdsbo.setHznscyqyjdyjbl(qysdsvo.getSbsj().getHznscyqyjdyjbl());
		qysdsbo.setJmsdse(qysdsvo.getSbsj().getJmsdse());
		qysdsbo.setLrze(qysdsvo.getSbsj().getLrze());
		qysdsbo.setMbyqndks(qysdsvo.getSbsj().getMbyqndks());
		qysdsbo.setNstzjse(qysdsvo.getSbsj().getNstzjse());
		qysdsbo.setNstzzje(qysdsvo.getSbsj().getNstzzje());
		qysdsbo.setSbrqshow(qysdsvo.getSbsj().getSbrqshow());
		qysdsbo.setSjyyjdsdse(qysdsvo.getSbsj().getSjyyjdsdse());
		qysdsbo.setSysl(qysdsvo.getSbsj().getSysl());
		qysdsbo.setYbdsdse(qysdsvo.getSbsj().getYbdsdse());
		qysdsbo.setYnsdse(qysdsvo.getSbsj().getYnsdse());
		qysdsbo.setYnssde(qysdsvo.getSbsj().getYnssde());

		return qysdsbo;
	}

	/**
	 * 基本信息表 将BO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public NsrJbxxdjbVO NsrJbxxconvert2XMLVO(NsrJbxxBO nsrJbxxBO,
			SWDJJBSJ djJbsj, String bblx) throws BaseException {

		NsrJbxxdjbVO nsrJbxxdjbVO = new NsrJbxxdjbVO();
		NsrJbxxVO nsrjbxx = new NsrJbxxVO();

		// //////////////////////////////////
		// 填充VO。

		nsrjbxx.setBbmsf(nsrJbxxBO.getBbmsf());
		nsrjbxx.setCjr(nsrJbxxBO.getCjr());
		nsrjbxx.setCjrq(nsrJbxxBO.getCjrq());
		nsrjbxx.setCwkjzddm(nsrJbxxBO.getCwkjzddm());
		nsrjbxx.setCwkjzddm_old(nsrJbxxBO.getCwkjzddm_old());
		nsrjbxx.setCwkjzdmc(nsrJbxxBO.getCwkjzddm_old());
		nsrjbxx.setGzglxsdm(nsrJbxxBO.getGzglxsdm());
		nsrjbxx.setGzglxsdm_old(nsrJbxxBO.getGzglxsdm_old());
		nsrjbxx.setGzglxsmc(nsrJbxxBO.getGzglxsmc());
		nsrjbxx.setJmlxdm(nsrJbxxBO.getJmlxdm());
		nsrjbxx.setJmlxdm_old(nsrJbxxBO.getJmlxdm_old());
		nsrjbxx.setJmlxmc(nsrJbxxBO.getJmlxmc());
		nsrjbxx.setJsjdm(nsrJbxxBO.getJsjdm());
		nsrjbxx.setJydz(nsrJbxxBO.getJydz());
		nsrjbxx.setLrr(nsrJbxxBO.getLrr());
		nsrjbxx.setLrrq(nsrJbxxBO.getLrrq());
		nsrjbxx.setLxdh(nsrJbxxBO.getLxdh());
		nsrjbxx.setNsrmc(nsrJbxxBO.getNsrmc());
		nsrjbxx.setNsrsbh(nsrJbxxBO.getNsrsbh());
		nsrjbxx.setSbnd(nsrJbxxBO.getSbnd());
		nsrjbxx.setSknd(nsrJbxxBO.getSknd());
		nsrjbxx.setSbrq(nsrJbxxBO.getSbrq());
		nsrjbxx.setSkssjsrq(nsrJbxxBO.getSkssjsrq());
		nsrjbxx.setSkssksrq(nsrJbxxBO.getSkssksrq());
		nsrjbxx.setSqspbh(nsrJbxxBO.getSqspbh());
		nsrjbxx.setSshydm(nsrJbxxBO.getSshydm());
		nsrjbxx.setSshymc(nsrJbxxBO.getSshymc());
		nsrjbxx.setSsjjlxdm(nsrJbxxBO.getSsjjlxdm());
		nsrjbxx.setSsjjlxmc(nsrJbxxBO.getSsjjlxmc());
		nsrjbxx.setSwjgzzjgdm(nsrJbxxBO.getSwjgzzjgdm());
		nsrjbxx.setSwjgzzjgmc(nsrJbxxBO.getSwjgzzjgmc());
		nsrjbxx.setVersion(nsrJbxxBO.getVersion());
		nsrjbxx.setXtjb(nsrJbxxBO.getXtjb());
		nsrjbxx.setZsfsdm(nsrJbxxBO.getZsfsdm());
		nsrjbxx.setZsfsmc(nsrJbxxBO.getZsfsmc());
		nsrjbxx.setZczbje(nsrJbxxBO.getZczbje());
		nsrjbxx.setZcze(nsrJbxxBO.getZcze());
		// 获取税源标识
		nsrjbxx.setSybs(FriendHelper.getNsrSybs(djJbsj));

		//获取是否填写过汇算清缴数据
		nsrjbxx.setQueryFlag(DoQueryData(nsrJbxxBO));
		
		// 纳税人基本信息
		nsrJbxxdjbVO.setNsrjbxx(nsrjbxx);

		// XML文档信息
		nsrJbxxdjbVO.setXsltVersion(Constant.CA_XSLTDM_NSRJBXXB);
		nsrJbxxdjbVO.setSchemaVersion(Constant.CA_SCHEMADM_NSRJBXXB);

		nsrJbxxdjbVO.setYwlx(Constant.CA_YWLXDM_NSRJBXXB);

		nsrJbxxdjbVO.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return nsrJbxxdjbVO;
	}

	/**
	 * 基本信息表 将BO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public NsrJbxxBO NsrJbxxconvert2VO(NsrJbxxdjbVO nsrJbxxdjbVO)
			throws BaseException {

		NsrJbxxBO nsrJbxxBO = new NsrJbxxBO();
		NsrJbxxVO nsrjbxx = new NsrJbxxVO();
		nsrjbxx = nsrJbxxdjbVO.getNsrjbxx();

		// //////////////////////////////////
		// 填充VO。

		nsrJbxxBO.setBbmsf(nsrjbxx.getBbmsf());
		nsrJbxxBO.setCjr(nsrjbxx.getCjr());
		nsrJbxxBO.setCjrq(nsrjbxx.getCjrq());
		nsrJbxxBO.setCwkjzddm(nsrjbxx.getCwkjzddm());
		nsrJbxxBO.setCwkjzddm_old(nsrjbxx.getCwkjzddm_old());
		nsrJbxxBO.setCwkjzdmc(nsrjbxx.getCwkjzddm_old());
		nsrJbxxBO.setGzglxsdm(nsrjbxx.getGzglxsdm());
		nsrJbxxBO.setGzglxsdm_old(nsrjbxx.getGzglxsdm_old());
		nsrJbxxBO.setGzglxsmc(nsrjbxx.getGzglxsmc());
		nsrJbxxBO.setJmlxdm(nsrjbxx.getJmlxdm());
		nsrJbxxBO.setJmlxdm_old(nsrjbxx.getJmlxdm_old());
		nsrJbxxBO.setJmlxmc(nsrjbxx.getJmlxmc());
		nsrJbxxBO.setJsjdm(nsrjbxx.getJsjdm());
		nsrJbxxBO.setJydz(nsrjbxx.getJydz());
		nsrJbxxBO.setLrr(nsrjbxx.getLrr());
		nsrJbxxBO.setLrrq(nsrjbxx.getLrrq());
		nsrJbxxBO.setLxdh(nsrjbxx.getLxdh());
		nsrJbxxBO.setNsrmc(nsrjbxx.getNsrmc());
		nsrJbxxBO.setNsrsbh(nsrjbxx.getNsrsbh());
		nsrJbxxBO.setSbnd(nsrjbxx.getSbnd());
		nsrJbxxBO.setSknd(nsrjbxx.getSknd());
		nsrJbxxBO.setSbrq(nsrjbxx.getSbrq());
		nsrJbxxBO.setSkssjsrq(nsrjbxx.getSkssjsrq());
		nsrJbxxBO.setSkssksrq(nsrjbxx.getSkssksrq());
		nsrJbxxBO.setSqspbh(nsrjbxx.getSqspbh());
		nsrJbxxBO.setSshydm(nsrjbxx.getSshydm());
		nsrJbxxBO.setSshymc(nsrjbxx.getSshymc());
		nsrJbxxBO.setSsjjlxdm(nsrjbxx.getSsjjlxdm());
		nsrJbxxBO.setSsjjlxmc(nsrjbxx.getSsjjlxmc());
		nsrJbxxBO.setSwjgzzjgdm(nsrjbxx.getSwjgzzjgdm());
		nsrJbxxBO.setSwjgzzjgmc(nsrjbxx.getSwjgzzjgmc());
		nsrJbxxBO.setVersion(nsrjbxx.getVersion());
		nsrJbxxBO.setXtjb(nsrjbxx.getXtjb());
		nsrJbxxBO.setZsfsdm(nsrjbxx.getZsfsdm());
		nsrJbxxBO.setZsfsmc(nsrjbxx.getZsfsmc());
		nsrJbxxBO.setZczbje(nsrjbxx.getZczbje());
		nsrJbxxBO.setZcze(nsrjbxx.getZcze());

		return nsrJbxxBO;
	}
	
    /**
	 * @decription 级联查询是否填写过汇算清缴数据
	 * @author wangcy
	 * @modify_date 2013-12-10
	 * @param pData
	 * @throws BaseException
     * @throws com.ttsoft.framework.exception.BaseException 
	 */
	public String DoQueryData(NsrJbxxBO nsrJbxxBO)  
	{
		Connection conn = null;
		PreparedStatement queryPstmtZb = null;
		PreparedStatement queryPstmtCb = null;
		ResultSet queryRsZb = null;
		ResultSet queryRsCb = null;
		String  queryFlag = "";

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();
		try {
			// 创建数据库连接
			conn = DBResource.getConnection();
			// 获取税款所属季度、年度   因为是年报  所以期号  为1
			String qh = "1";
			String nd = nsrJbxxBO.getSkssksrq().substring(0, 4);
			querySqlZb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM= '");

			querySqlZb.append(nsrJbxxBO.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(Constants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append(qh).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append(nd).append("' ");


			System.out.println(querySqlZb.toString());
			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM= '");
			querySqlCb.append(nsrJbxxBO.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append(qh).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append(nd).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());
			System.out.println(querySqlCb.toString());
			String queryFlagZb = "0";
			String queryFlagCb = "0";
			
			if(queryRsZb.next()){ queryFlagZb = "1"; }
			if(queryRsCb.next()){ queryFlagCb = "1"; }

			if(queryFlagZb.equals("1") || queryFlagCb.equals("1")){
				queryFlag="1";
			}else{
				queryFlag="0";
			}
			
		} catch (Exception localException) {
			localException.printStackTrace();
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
		return queryFlag;
	}
	
    /**
	 * @description 释放数据库资源
	 * @author wangcy
	 * @modify_date 2013-12-08
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
}
