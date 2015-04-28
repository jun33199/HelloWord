package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.sql.Connection;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.dm.GJBZHY;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;

import java.util.Date;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.vo.DmVo;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.CzzssdsjbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.HdzssdsjbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateTimeUtil;

public class QysdsUtil2014 {
	public QysdsUtil2014() {
	}

	/**
	 * 获取征收方式
	 * 
	 * @param jsjdm
	 *            String
	 * @param rq
	 *            Date
	 * @return Zsfs
	 * @throws BaseException
	 */
	public Zsfs getZsfsInfo(String jsjdm, Date rq) throws BaseException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Zsfs ret = new Zsfs();
		QysdsZsfs q = new QysdsZsfs();
		StringBuffer sql = new StringBuffer();
		int rdnd = getYear(rq);
		try {
			con = SfDBResource.getConnection();
			// SfDBAccess db = new SfDBAccess(con);
			// Vector v = new Vector();
			sql.append("select * from sfdb.sf_jl_qysdszsfs where ");
			sql.append("jsjdm = '").append(jsjdm).append("' ");
			sql.append("and to_number(rdnd) <= ").append(rdnd);
			sql.append(" order by rdnd desc, cjrq desc");
			System.out.println("zsfs query sql:\n" + sql.toString());

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());
			// 取最后核定的一条记录
			if (rs.next()) {
				q.setZsfsdm(rs.getString("ZSFSDM"));
				q.setDl(new BigDecimal(rs.getDouble("DL")));
				q.setCyl(new BigDecimal(rs.getDouble("CYL")));
				q.setDe(new BigDecimal(rs.getDouble("DE")));
				// ret.setZsfsdm(rs.getString("ZSFSDM"));
				// ret.setSl(String.valueOf(rs.getDouble("DL")));
				// ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
			} else {
				return null;
			}

			ret.setZsfsdm(q.getZsfsdm());
			ret.setSl(String.valueOf(q.getDl()));
			ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));

			if (q.getCyl() != null) {
				ret.setCyl(String.valueOf(q.getCyl().divide(
						new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)));
			} else {
				ret.setCyl(String.valueOf(q.getCyl()));
			}

			ret.setDe(String.valueOf(q.getDe()));

			// 关闭数据库对象
			rs.close();
			pstmt.close();
			return ret;
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(con);
		}

	}

	/**
	 * 得到给定日期的年份 为int型
	 * 
	 * @param date
	 *            给定的日期
	 * @return int 年份值
	 */
	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return (calendar.get(Calendar.YEAR));
	}

	public java.lang.String getZsfsmc(String zsfsdm, Connection con) {
		PreparedStatement pstmt = null;
		// ResultSet rs = null;
		String ret = "";
		try {
			String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
					+ zsfsdm + "'";
			SfDBAccess db = new SfDBAccess(con);
			ResultSet rs = db.querySQL(sql);
			if (rs.next()) {
				// rs.first();
				ret = rs.getString("zsfsmc");
			}
			rs.close();
			return ret;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * @decription 将A类表中页面数据转换为可视数据
	 * @author gaoyh
	 * @modify_date 2014-03-20
	 * @param czzssdsjbForm
	 * @throws ApplicationException
	 */
	public HashMap translateDataToMap(CzzssdsjbForm czzssdsjbForm)
			throws ApplicationException {
		List formList = czzssdsjbForm.getQysdsjbList();
		HashMap formData = new HashMap();

		for (int i = 0; i < formList.size(); i++) {
			HashMap map = (HashMap) formList.get(i);
			String hc = (String) map.get("hc");

			System.out.println("hc===" + hc + "......." + "lje==="
					+ map.get("lje"));
			System.out.println("list.size()===" + formList.size());

			if (i < 30) {
				if (hc.equals("1")) {
					formData.put("nsfs", map.get("lje"));
				}
				if (hc.equals("2")) {
					formData.put("zfjg", map.get("lje"));
				}
				if (hc.equals("3")) {
					formData.put("yysr", map.get("lje"));
				}
				if (hc.equals("4")) {
					formData.put("yycb", map.get("lje"));
				}
				if (hc.equals("5")) {
					formData.put("lrze", map.get("lje"));
				}
				if (hc.equals("6")) {
					formData.put("tdjsynssde", map.get("lje"));
				}
				if (hc.equals("7")) {
					formData.put("bzsr", map.get("lje"));
				}
				if (hc.equals("8")) {
					formData.put("mssr", map.get("lje"));
				}
				if (hc.equals("9")) {
					formData.put("jmzynssde", map.get("lje"));
				}

				if (hc.equals("10")) {
					formData.put("mbyqndks", map.get("lje"));
				}
				if (hc.equals("11")) {
					formData.put("sjlre", map.get("lje"));
				}
				if (hc.equals("12")) {
					formData.put("sl", map.get("lje"));
				}
				if (hc.equals("13")) {
					formData.put("ynsdse", map.get("lje"));
				}
				if (hc.equals("14")) {
					formData.put("jmsdse", map.get("lje"));
				}
				if (hc.equals("15")) {
					formData.put("xwqyjmsdse", map.get("lje"));
				}
				if (hc.equals("16")) {
					formData.put("sjyyjsdse", map.get("lje"));
				}
				if (hc.equals("17")) {
					formData.put("tdywyjsdse", map.get("lje"));
				}
				if (hc.equals("18")) {
					formData.put("ybtsdse", map.get("lje"));
				}
				if (hc.equals("19")) {
					formData.put("yqnddjsdse", map.get("lje"));
				}
				if (hc.equals("20")) {
					formData.put("bqsjybtsdse", map.get("lje"));
				}
				if (hc.equals("30")) {
					formData.put("zjgyftsdse", map.get("lje"));
				}
				if (hc.equals("31")) {
					formData.put("czjzfpsdse", map.get("lje"));
				}
				if (hc.equals("32")) {
					formData.put("fzjgyftsdse", map.get("lje"));
				}
				if (hc.equals("33")) {
					formData.put("zjgdlscjybmyftsdse", map.get("lje"));
				}
				// if (hc.equals("28")) {
				// formData.put("zjgycxfzjgyftsdse", map.get("lje"));
				// }
				if (hc.equals("34")) {
					formData.put("fpbl", map.get("lje"));
				}
				if (hc.equals("35")) {
					formData.put("fpsdse", map.get("lje"));
				}
			} else {
				throw new ApplicationException("错误的行次数据!!!");
			}
		}
		return formData;
	}

	/**
	 * @decription 查询A类表中纳税方法与总分机构
	 * @author gaoyh
	 * @modify_date 2014-03-20
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 * 
	 */
	public HashMap getNsfsAndZfjg(Map pData) throws BaseException,
			ApplicationException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		HashMap nszf = new HashMap();

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			CzzssdsjbForm czzssdsjbForm = (CzzssdsjbForm) pData
					.get("czzssdsjbForm");
			// 获取税款所属季度
			String jd = QysdsNewUtil.preQuarter(SfDateUtil
					.getDate(czzssdsjbForm.getSkssjsrq()));
			String nd = czzssdsjbForm.getSkssksrq().substring(0, 4);

			sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
			sql.append("nsrjsjdm = '").append(czzssdsjbForm.getJsjdm())
					.append("' ");
			sql.append("and bbqlx = '")
					.append(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)
					.append("' ");
			sql.append("and qh = '").append(jd).append("' ");
			sql.append("and sknd = '").append(nd).append("' ");
			sql.append("and sbdm = '")
					.append(CodeConstant.TABLE_ID_CZQYSDSJB_2008).append("' ");
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

			czzssdsjbForm.setNsfs_fsjg(nszf);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 关闭数据库对象
			this.releaseAll(rs, pstmt, conn);
		}
		return nszf;
	}

	/**
	 * @decription 级联查询分配表中所有数据
	 * @author gaoyh
	 * @modify_date 2014-03-20
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
	public HashMap queryCascadeZfjgData(Map pData) throws BaseException,
			ApplicationException {

		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法查询申报主数据");
		}

		Connection conn = null;
		PreparedStatement queryPstmtZb = null;
		PreparedStatement queryPstmtCb = null;
		ResultSet queryRsZb = null;
		ResultSet queryRsCb = null;
		HashMap rsMap = new HashMap();

		// SWDJJBSJ djJbsj = (SWDJJBSJ)
		// pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			CzzssdsjbForm czzssdsjbForm = (CzzssdsjbForm) pData
					.get("czzssdsjbForm");
			// SWDJJBSJ djxx = InterfaceDj.getJBSJ_New(czzssdsjbForm.getJsjdm(),
			// (UserData)pData.get("userData"));
			// 获取税款所属季度、年度
			String jd = QysdsNewUtil.preQuarter(SfDateUtil
					.getDate(czzssdsjbForm.getSkssjsrq()));
			String nd = czzssdsjbForm.getSkssksrq().substring(0, 4);

			querySqlZb
					.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM= '");

			querySqlZb.append(czzssdsjbForm.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR).append(
					"' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append(jd).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append(nd).append("' ");
			querySqlZb.append(" AND SBDM = '");
			querySqlZb.append(CodeConstant.TABLE_ID_HZJNFZJG_2008).append("' ");

			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb
					.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM= '");
			querySqlCb.append(czzssdsjbForm.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(
					QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append(
					"' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append(jd).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append(nd).append("' ");
			querySqlCb.append(" AND SBDM = '");
			querySqlCb.append(CodeConstant.TABLE_ID_HZJNFZJG_2008).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());

			String queryFlagZb = "0";
			String queryFlagCb = "0";

			if (queryRsZb.next()) {
				queryFlagZb = "1";
			}
			if (queryRsCb.next()) {
				queryFlagCb = "1";
			}

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
				if (queryRsCb.getString("hc").equals("17")
						&& queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpbl", queryRsCb.getString("yz"));
				}
				if (queryRsCb.getString("hc").equals("18")
						&& queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpsdse", queryRsCb.getString("yz"));
				}
			}

			rsMap.put("sbsjZbMap", sbsjZbMap);
			rsMap.put("sbsjCbMap", sbsjCbMap);

			System.out.println("queryCascadeZfjgData()方法，级联处理查询数据完毕。。。");

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
	 * @description 级联保存分配表中不可修改部分数据
	 * @author gaoyh
	 * @modify_date 2014-03-20
	 * @param pData
	 * @param nsfs_zfjg
	 * @throws BaseException
	 * @throws ApplicationException
	 */
	public void saveCascadeZfjgData(Map pData) throws BaseException,
			ApplicationException {

		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法保存数据");
		}

		System.out.println("进入saveCascadeZfjgData()处理数据。。。。。。。。。。。。。。");

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 取得税款所属日期Map
			Timestamp curDate = new Timestamp(System.currentTimeMillis());

			BigDecimal divisor = new BigDecimal(100);
			DecimalFormat percentFormat = new DecimalFormat("#########.00%");

			CzzssdsjbForm czzssdsjbForm = (CzzssdsjbForm) pData
					.get("czzssdsjbForm");
			SWDJJBSJ djxx = InterfaceDj.getJBSJ_New(czzssdsjbForm.getJsjdm(),
					(UserData) pData.get("userData"));
			// 获取税款所属季度、年度
			String jd = QysdsNewUtil.preQuarter(SfDateUtil
					.getDate(czzssdsjbForm.getSkssjsrq()));
			String nd = czzssdsjbForm.getSkssksrq().substring(0, 4);

			HashMap queryResult = this.queryCascadeZfjgData(pData);
			String queryFlagZb = (String) queryResult.get("queryFlagZb");
			String queryFlagCb = (String) queryResult.get("queryFlagCb");

			String nsfsNow = (String) pData.get("nsfsNow");
			String zfjgNow = (String) pData.get("zfjgNow");

			HashMap formData = this.translateDataToMap(czzssdsjbForm);

			// 获取申报数据
			String[] hcZb = { "1", "2", "3", "4", "5", "6", "10", "11" };
			String[] hcCb = { "12", "13", "17", "18", "14", "15", "16" };
			String[] yzZb = new String[8];
			String[] yzCb = new String[7];

			// System.out.println("djJbsj.getNsrmc()==="+ djJbsj.getNsrmc());
			// System.out.println("djJbsj.getNsrsbh()==="+ djJbsj.getSwdjzh());

			if (zfjgNow.equals("1")) {
				for (int i = 0; i < yzZb.length; i++) {
					switch (i) {
					case 0:
						yzZb[i] = czzssdsjbForm.getNsrmc();
						break;
					case 1:
						yzZb[i] = czzssdsjbForm.getNsrsbh();
						break;
					case 2:
						yzZb[i] = (String) formData.get("ynsdse");
						break;
					case 3:
						yzZb[i] = (String) formData.get("zjgyftsdse");
						break;
					case 4:
						yzZb[i] = (String) formData.get("czjzfpsdse");
						break;
					case 5:
						yzZb[i] = (String) formData.get("fzjgyftsdse");
						break;
					case 6:
						yzZb[i] = "0";
						break;
					case 7:
						yzZb[i] = "0.00";
						break;
					default:
						throw new SystemException("不在赋值范围！！");
					}
				}
			}

			if (zfjgNow.equals("2")) {
				if (queryFlagZb.equals("0")) {
					BigDecimal fpblZb = new BigDecimal(
							(String) formData.get("fpbl"));
					System.out.println("fpblCbResult======"
							+ fpblZb.divide(divisor, 4, 2));
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
							yzZb[i] = (String) formData.get("fzjgyftsdse");
							break;
						case 6:
							yzZb[i] = percentFormat.format(
									fpblZb.divide(divisor, 4, 2)).toString();
							break;
						case 7:
							yzZb[i] = (String) formData.get("fpsdse");
							break;
						default:
							throw new SystemException("不在赋值范围！！");
						}
						System.out.println("hcZb[" + i + "]" + "========"
								+ hcZb[i] + "======" + "yzZb[" + i + "]"
								+ "======" + yzZb[i]);
					}
				}

				if (queryFlagCb.equals("0")) {
					BigDecimal fpblCb = new BigDecimal(
							(String) formData.get("fpbl"));
					System.out.println("fpblCbResult======"
							+ fpblCb.divide(divisor, 4, 2));
					for (int i = 0; i < yzCb.length; i++) {
						switch (i) {
						case 0:
							yzCb[i] = czzssdsjbForm.getNsrsbh();
							break;
						case 1:
							yzCb[i] = czzssdsjbForm.getNsrmc();
							break;
						case 2:
							yzCb[i] = fpblCb.divide(divisor, 4, 2).toString();
							break;
						case 3:
							yzCb[i] = (String) formData.get("fpsdse");
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
							throw new SystemException("不在赋值范围！！");
						}
					}
				}

				if (queryFlagZb.equals("1")) {
					HashMap sbsjZbMap = (HashMap) queryResult.get("sbsjZbMap");
					HashMap sbsjCbMap = (HashMap) queryResult.get("sbsjCbMap");

					String fpblHjOld = (String) sbsjZbMap.get("fpbl");
					String fpblOld = (String) sbsjCbMap.get("fpbl");
					double tmpFpblHj = 0.00;
					if (fpblHjOld.endsWith("%")) {
						tmpFpblHj = Double.parseDouble(fpblHjOld.substring(0,
								fpblHjOld.indexOf("%")));
					} else {
						tmpFpblHj = Double.parseDouble(fpblHjOld);
					}
					// double tmpFpblHj =
					// Double.parseDouble(fpblHjOld.substring(0,fpblHjOld.indexOf("%")));
					double tmpFpblOld = Double.parseDouble(fpblOld) * 100;
					double tmpFpblNew = Double.parseDouble((String) formData
							.get("fpbl"));

					double tmpFpsdseHj = Double.parseDouble((String) sbsjZbMap
							.get("fpsdse"));
					double tmpFpsdseOld = Double.parseDouble((String) sbsjCbMap
							.get("fpsdse"));
					double tmpFpsdseNew = Double.parseDouble((String) formData
							.get("fpsdse"));

					double fpblHjZb = tmpFpblHj + tmpFpblNew - tmpFpblOld;
					double fpsdseZb = tmpFpsdseHj + tmpFpsdseNew - tmpFpsdseOld;

					BigDecimal updateFpblHj = new BigDecimal(fpblHjZb);
					String updateFpsdseHj = "" + fpsdseZb;

					System.out.println("updateFpblHj============="
							+ updateFpblHj);
					System.out.println("updateFpsdseHj============="
							+ updateFpsdseHj);

					for (int i = 5; i < yzZb.length; i++) {
						switch (i) {
						case 5:
							yzZb[i] = (String) formData.get("fzjgyftsdse");
							break;
						case 6:
							yzZb[i] = percentFormat.format(
									updateFpblHj.divide(divisor, 4, 2))
									.toString();
							break;
						case 7:
							yzZb[i] = updateFpsdseHj;
							break;
						default:
							throw new SystemException("不在赋值范围！！");
						}
					}
				}

				if (queryFlagCb.equals("1")) {
					BigDecimal fpblCb = new BigDecimal(
							(String) formData.get("fpbl"));
					for (int i = 0; i < yzCb.length - 3; i++) {
						switch (i) {
						case 0:
							yzCb[i] = czzssdsjbForm.getNsrsbh();
							break;
						case 1:
							yzCb[i] = czzssdsjbForm.getNsrmc();
							break;
						case 2:
							yzCb[i] = fpblCb.divide(divisor, 4, 2).toString();
							break;
						case 3:
							yzCb[i] = (String) formData.get("fpsdse");
							break;
						default:
							throw new SystemException("不在赋值范围！！");
						}
					}
				}
			}

			PreparedStatement insertPstmtZb = conn
					.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement insertPstmtCb = conn
					.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			PreparedStatement updatePstmtZb = conn
					.prepareStatement("UPDATE SBDB.SB_JL_QYSDSSBB_ZB_JD SET YZ=?, LRSJ=? WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=? AND HC=?");
			PreparedStatement updatePstmtCb = conn
					.prepareStatement("UPDATE SBDB.SB_JL_QYSDSSBB_CB_JD SET YZ=?, LRSJ=? WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=? AND HC=? AND ZHS=?");

			if (zfjgNow.equals("1")) {
				if (queryFlagZb.equals("0")) {
					System.out
							.println("111111111111111111111111111111111111111111111");
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, czzssdsjbForm.getJsjdm());
						insertPstmtZb.setString(2, czzssdsjbForm.getNsrmc());
						insertPstmtZb.setString(3,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(4, jd);
						insertPstmtZb.setString(5, nd);
						insertPstmtZb.setDate(
								6,
								new java.sql.Date(TinyTools.stringToDate(
										czzssdsjbForm.getSbrq(), "yyyyMMdd")
										.getTime()));
						insertPstmtZb.setString(7,
								CodeConstant.SBZL_QYSDSJB_VERSION_2014);
						insertPstmtZb.setString(8,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(9,
								CodeConstant.TABLE_ID_HZJNFZJG_2008);
						insertPstmtZb.setString(10,
								CodeConstant.TABLE_NAME_HZJNFZJG_2008);
						insertPstmtZb.setString(11, hcZb[i]);
						insertPstmtZb.setDate(
								12,
								new java.sql.Date(TinyTools
										.stringToDate(
												czzssdsjbForm.getSkssksrq(),
												"yyyyMMdd").getTime()));
						insertPstmtZb.setDate(
								13,
								new java.sql.Date(TinyTools
										.stringToDate(
												czzssdsjbForm.getSkssjsrq(),
												"yyyyMMdd").getTime()));
						insertPstmtZb.setString(14,
								Constants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE);
						insertPstmtZb.setString(15, yzZb[i]);
						insertPstmtZb.setString(16, czzssdsjbForm.getSwjsjdm());
						insertPstmtZb.setString(17,
								czzssdsjbForm.getSwjgzzjgdm());
						insertPstmtZb.setString(18, czzssdsjbForm.getLrr());
						insertPstmtZb.setDate(19, new java.sql.Date(
								new java.util.Date().getTime()));
						insertPstmtZb.setString(20, czzssdsjbForm.getLrr());
						insertPstmtZb.setDate(21, new java.sql.Date(
								new java.util.Date().getTime()));
						insertPstmtZb.setString(22, czzssdsjbForm.getQxdm());
						insertPstmtZb.addBatch();
					}
					insertPstmtZb.executeBatch();
				}
				if (queryFlagZb.equals("1")) {
					System.out
							.println("2222222222222222222222222222222222222222222222222222222");
					for (int i = 0; i < hcZb.length - 2; i++) {
						updatePstmtZb.setString(1, yzZb[i]);
						updatePstmtZb.setDate(2, new java.sql.Date(
								new java.util.Date().getTime()));
						updatePstmtZb.setString(3, czzssdsjbForm.getJsjdm());
						updatePstmtZb.setString(4, nd);
						updatePstmtZb.setString(5,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtZb.setString(6, jd);
						updatePstmtZb.setString(7,
								CodeConstant.TABLE_ID_HZJNFZJG_2008);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}
			}

			if (zfjgNow.equals("2")) {
				if (queryFlagZb.equals("0")) {
					System.out
							.println("3333333333333333333333333333333333333333333333333333333333333333");
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, czzssdsjbForm.getJsjdm());
						insertPstmtZb.setString(2, czzssdsjbForm.getNsrmc());
						insertPstmtZb.setString(3,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(4, jd);
						insertPstmtZb.setString(5, nd);
						insertPstmtZb.setDate(
								6,
								new java.sql.Date(TinyTools.stringToDate(
										czzssdsjbForm.getSbrq(), "yyyyMMdd")
										.getTime()));
						insertPstmtZb.setString(7,
								CodeConstant.SBZL_QYSDSJB_VERSION_2014);
						insertPstmtZb.setString(8,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(9,
								CodeConstant.TABLE_ID_HZJNFZJG_2008);
						insertPstmtZb.setString(10,
								CodeConstant.TABLE_NAME_HZJNFZJG_2008);
						insertPstmtZb.setString(11, hcZb[i]);
						insertPstmtZb.setDate(
								12,
								new java.sql.Date(TinyTools
										.stringToDate(
												czzssdsjbForm.getSkssksrq(),
												"yyyyMMdd").getTime()));
						insertPstmtZb.setDate(
								13,
								new java.sql.Date(TinyTools
										.stringToDate(
												czzssdsjbForm.getSkssjsrq(),
												"yyyyMMdd").getTime()));
						insertPstmtZb.setString(14,
								Constants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE);
						insertPstmtZb.setString(15, yzZb[i]);
						insertPstmtZb.setString(16, czzssdsjbForm.getSwjsjdm());
						insertPstmtZb.setString(17,
								czzssdsjbForm.getSwjgzzjgdm());
						insertPstmtZb.setString(18, czzssdsjbForm.getLrr());
						insertPstmtZb.setDate(19, new java.sql.Date(
								new java.util.Date().getTime()));
						insertPstmtZb.setString(20, czzssdsjbForm.getLrr());
						insertPstmtZb.setDate(21, new java.sql.Date(
								new java.util.Date().getTime()));
						insertPstmtZb.setString(22, czzssdsjbForm.getQxdm());
						insertPstmtZb.addBatch();
					}
					insertPstmtZb.executeBatch();
				}
				if (queryFlagCb.equals("0")) {
					System.out
							.println("444444444444444444444444444444444444444444444444444444");
					for (int i = 0; i < 7; i++) {
						insertPstmtCb.setString(1, czzssdsjbForm.getJsjdm());
						insertPstmtCb.setString(2, czzssdsjbForm.getNsrmc());
						insertPstmtCb.setString(3,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtCb.setString(4, jd);
						insertPstmtCb.setString(5, nd);
						insertPstmtCb.setString(6,
								CodeConstant.SBZL_QYSDSJB_VERSION_2014);
						insertPstmtCb.setString(7,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtCb.setString(8,
								CodeConstant.TABLE_ID_HZJNFZJG_2008);
						insertPstmtCb.setString(9,
								CodeConstant.TABLE_NAME_HZJNFZJG_2008);
						insertPstmtCb.setString(10, hcCb[i]);
						insertPstmtCb.setString(11, "1");
						insertPstmtCb.setString(12, "");
						insertPstmtCb.setString(13, yzCb[i]);
						insertPstmtCb.setString(14,
								czzssdsjbForm.getSwjgzzjgdm());
						insertPstmtCb.setString(15, czzssdsjbForm.getLrr());
						insertPstmtCb.setDate(16, new java.sql.Date(
								new java.util.Date().getTime()));
						insertPstmtCb.setString(17, czzssdsjbForm.getLrr());
						insertPstmtCb.setDate(18, new java.sql.Date(
								new java.util.Date().getTime()));
						insertPstmtCb.addBatch();
					}
					insertPstmtCb.executeBatch();
				}

				if (queryFlagZb.equals("1")) {
					System.out
							.println("555555555555555555555555555555555555555555555555555555555");
					for (int i = 5; i < hcZb.length; i++) {
						updatePstmtZb.setString(1, yzZb[i]);
						updatePstmtZb.setDate(2, new java.sql.Date(
								new java.util.Date().getTime()));
						updatePstmtZb.setString(3, czzssdsjbForm.getJsjdm());
						updatePstmtZb.setString(4, nd);
						updatePstmtZb.setString(5,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtZb.setString(6, jd);
						updatePstmtZb.setString(7,
								CodeConstant.TABLE_ID_HZJNFZJG_2008);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}

				if (queryFlagCb.equals("1")) {
					System.out
							.println("6666666666666666666666666666666666666666666666666666666666666");
					for (int i = 0; i < hcCb.length - 3; i++) {
						updatePstmtCb.setString(1, yzCb[i]);
						updatePstmtCb.setDate(2, new java.sql.Date(
								new java.util.Date().getTime()));
						updatePstmtCb.setString(3, czzssdsjbForm.getJsjdm());
						updatePstmtCb.setString(4, nd);
						updatePstmtCb.setString(5,
								Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtCb.setString(6, jd);
						updatePstmtCb.setString(7,
								CodeConstant.TABLE_ID_HZJNFZJG_2008);
						updatePstmtCb.setString(8, hcCb[i]);
						updatePstmtCb.setString(9, "1");
						updatePstmtCb.addBatch();
					}
					updatePstmtCb.executeBatch();
				}
			}

			// insertPstmtZb.executeBatch();
			// insertPstmtCb.executeBatch();
			// updatePstmtZb.executeBatch();
			// updatePstmtCb.executeBatch();

			System.out.println("saveCascadeZfjgData()方法，级联处理保存数据完毕。。。");

			insertPstmtZb.close();
			insertPstmtCb.close();
			updatePstmtZb.close();
			updatePstmtCb.close();
			conn.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil
					.getBaseException(localException);
		}
	}

	/**
	 * @decription 级联删除分配表数据
	 * @author gaoyh
	 * @modify_date 2014-03-20
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
	public void deleteCascadeZfjgData(Map pData) throws BaseException,
			ApplicationException {
		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法保存申报主数据");
		}

		System.out.println("进入deleteCascadeZfjgData()方法，级联处理删除数据完毕。。。");

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			CzzssdsjbForm czzssdsjbForm = (CzzssdsjbForm) pData
					.get("czzssdsjbForm");
			// 获取税款所属季度
			String jd = QysdsNewUtil.preQuarter(SfDateUtil
					.getDate(czzssdsjbForm.getSkssjsrq()));
			String nd = czzssdsjbForm.getSkssksrq().substring(0, 4);
			System.out.println("czzssdsjbForm.getJsjdm()========="
					+ czzssdsjbForm.getJsjdm());
			System.out.println("jd=========" + jd);
			System.out.println("jd=========" + nd);

			PreparedStatement detelePstmtZb = conn
					.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			PreparedStatement detelePstmtCb = conn
					.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");

			detelePstmtZb.setString(1, czzssdsjbForm.getJsjdm());
			detelePstmtZb.setString(2, nd);
			detelePstmtZb.setString(3, Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			detelePstmtZb.setString(4, jd);
			detelePstmtZb.setString(5, CodeConstant.TABLE_ID_HZJNFZJG_2008);
			detelePstmtZb.addBatch();

			detelePstmtCb.setString(1, czzssdsjbForm.getJsjdm());
			detelePstmtCb.setString(2, nd);
			detelePstmtCb.setString(3, Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			detelePstmtCb.setString(4, jd);
			detelePstmtCb.setString(5, CodeConstant.TABLE_ID_HZJNFZJG_2008);
			detelePstmtCb.addBatch();

			detelePstmtZb.executeBatch();
			detelePstmtCb.executeBatch();

			System.out.println("deleteCascadeZfjgData()方法，级联处理删除数据完毕。。。");

			detelePstmtZb.close();
			detelePstmtCb.close();
			conn.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil
					.getBaseException(localException);
		}
	}

	/**
	 * @description 释放数据库资源
	 * @author gaoyh
	 * @modify_date 2014-03-15
	 * @param rs
	 * @param stmt
	 * @param con
	 */
	public void release(ResultSet rs, Statement stmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void releaseAll(ResultSet rs, Statement stmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否是新开户
	 * 
	 * @param skssksrq
	 * @param djJbsj
	 * @return
	 */
	public String getSfxkh(String skssnd, SWDJJBSJ djJbsj,String qh) {
		String sfxkh = "N";
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(djJbsj.getKydjrq());
		// 开业登记年度
		int kydjnd = calendar.get(calendar.YEAR);
		if (kydjnd == Integer.parseInt(skssnd)) {
			sfxkh = "Y";
			;
		} else {
			sfxkh = "N";
		}
		//2014版改动，zhangj，20150212 start
		if("2015".equals(skssnd)&&"1".equals(qh)){
			if ((kydjnd == Integer.parseInt(skssnd))||(kydjnd == (Integer.parseInt(skssnd)-1))) {
				sfxkh = "Y";
			}
		}
		//zhangj，20150212 end
		return sfxkh;
	}

	/**
	 * 获取税款所属期所在年度的上一年度的征收方式代码
	 * 
	 * @param jsjdm
	 *            String
	 * @param skssrqq
	 *            Date
	 * @return String
	 * @throws BaseException
	 */
	public String getSyndZsfsDm(String jsjdm, String skssnd,String qh)
			throws BaseException, ApplicationException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String zsfsdm = CodeConstant.ZSFSDM_CZZS;
		int rdnd = Integer.parseInt(skssnd);
		
        //2014版需求改动，zhangj，20150212 start
        if(2015==rdnd && "1".equals(qh)){
        	rdnd=2014;
        }
        //20150212 end
		
		try {
			con = SfDBResource.getConnection();
			sql.append("select * from sfdb.sf_jl_qysdszsfs_his where ");
			sql.append("jsjdm = '").append(jsjdm).append("' ");
			sql.append("and to_number(rdnd) < ").append(rdnd);
			sql.append(" order by rdnd desc, cjrq desc");

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());

			if (rs.next()) {
				zsfsdm = rs.getString("ZSFSDM") == null ? CodeConstant.ZSFSDM_CZZS
						: rs.getString("ZSFSDM");
			}
			// 关闭数据库对象
			rs.close();
			pstmt.close();
			return zsfsdm;
		} catch (Exception ex) {
			throw com.ttsoft.framework.exception.ExceptionUtil
					.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(con);
		}
	}

	/**
	 * 查询查帐征收表关联分配表数据
	 * 
	 * @param conn
	 *            Connection
	 * @param form
	 *            ZfjgqysdsjbForm
	 * @throws BaseException
	 */
	public Map getSyndHsqjSbxx(String zsfsdm, String jsjdm, String skssnd,String qh)
			throws BaseException, ApplicationException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		int sknd = Integer.parseInt(skssnd);

        //2014版需求改动，zhangj，20150212 start
        if(2015==sknd&&"1".endsWith(qh)){
        	sknd=2014;
        }
        //20150212 end

        
		// 获取上一年度核定征收行6数据
		String syndZbh6 = "0.00";
		// 获取上一年度汇算清缴主表行9数据
		String syndZbh25 = "0.00";
		// 获取上一年度汇算清缴附表5行45、46、47的校验结果
		String syndFb5jyjg = "N";
		try {
			conn = SfDBResource.getConnection();
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfsdm)) {
				sql.append("select * from sbdb.sb_jl_qysdssbb_zb_nd where ");
				sql.append("nsrjsjdm = '").append(jsjdm).append("' ");
				sql.append("and bbqlx = '2' ");
				sql.append("and qh = '1' ");
				sql.append("and sknd = '").append(sknd - 1).append("' ");
				sql.append("and ((sbdm='1' and hc in ('9','25')) or (sbdm='10' and hc in ('45','46','47'))) ");
				System.out.println("sql:\n" + sql.toString());

				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery(sql.toString());

				String zbh25 = "0.00";
				String fb5h45 = "0.00";
				String fb5h46 = "0.00";
				String fb5h47 = "";

				while (rs.next()) {
					if (rs.getString("hc").equals("25")) {
						zbh25 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("45")) {
						fb5h45 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("46")) {
						fb5h46 = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
					}
					if (rs.getString("hc").equals("47")) {
						fb5h47 = rs.getString("yz") == null ? "" : rs.getString("yz");
					}
				}

				syndZbh25 = zbh25;
				if (Double.parseDouble(zbh25) <= 30 * 10000) {
					if (fb5h47.equals("01")) {// 工业企业
						if (Double.parseDouble(fb5h45) <= 100 && Double.parseDouble(fb5h46) <= 3000*10000) {
							syndFb5jyjg = "Y";
						} else {
							syndFb5jyjg = "N";
						}
					} else if (fb5h47.equals("02")) {// 其他企业
						if (Double.parseDouble(fb5h45) <= 80 && Double.parseDouble(fb5h46) <= 1000*10000) {
							syndFb5jyjg = "Y";
						} else {
							syndFb5jyjg = "N";
						}
					} else {
						syndFb5jyjg = "N";
					}
				} else {
					syndFb5jyjg = "N";
				}
				// 关闭数据库对象
				rs.close();
				pstmt.close();
			} else {
				sql1.append(
						"select * from sbdb.sb_jl_qysdssbb_zb_jd t where nsrjsjdm='")
						.append(jsjdm).append("' and  sbdm='24' and hc='6'")
						.append("and sknd = '").append(sknd - 1).append("' ");
				pstmt1 = conn.prepareStatement(sql1.toString());
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					if (rs1.getString("hc").equals("6")) {
						syndZbh6 = rs1.getString("yz") == null ? "0.00" : rs1.getString("yz");
					}
				}
				// 关闭数据库对象
				rs1.close();
				pstmt1.close();
			}
			Map map = new HashMap();
			map.put("syndZbh6", syndZbh6);
			map.put("syndZbh25", syndZbh25);
			map.put("syndFb5jyjg", syndFb5jyjg);
			
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
	}
	/**
	 * 将指定日期转换为 Timestamp
	 * @param date  指定日期格式为 "20030908"
	 * @return  Timestamp
	 */
	public static Timestamp getTimestamp(String dateStr) {
		
		java.util.Date date = null; 
		String tmp = SBStringUtils.trim(dateStr);
		tmp = SBStringUtils.killNull(tmp);
		tmp = replaceAll(tmp,"-","");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			date = sdf.parse(tmp);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return second2Day(date);
	}

	/**
	 * 替换字符串
	 * @param original 源字符串
	 * @param find 查找字符串
	 * @param replacement 替换字符串
	 * @return 替换后的字符串
	 */
	public final static String replaceAll(String original, String find,
			String replacement) {
		StringBuffer buffer = new StringBuffer(original);
		
		int idx = original.length();
		int offset = find.length();
		
		while ( (idx = original.lastIndexOf(find, idx - 1)) > -1) {
			buffer.replace(idx, idx + offset, replacement);
		}
		
		return buffer.toString();
	}
	/**将java.util.Date转换为的java.sql.Timestamp
	 * @param java.util.Date
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp second2Day(java.util.Date date)
	{
		if (date==null)
			return new Timestamp(System.currentTimeMillis());
		java.sql.Timestamp tempStamp = null;
		
		try
		{
			String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
		}
		catch (Exception ex) {}
		
		return tempStamp;
	}
	/**
	 * 核定征收季报初始化代码表
	 * @param czzssdsjbForm
	 */
	public static void initCodeTable(HdzssdsjbForm hdzssdsjbForm){
        //国家标准行业
        List gjbzhy = CodeTableUtil.getCodeTableList(CodeTableKey.GJBZHY);
        ArrayList gjbzhyList=new ArrayList();
        for(int i=0;i<gjbzhy.size();i++){
        	GJBZHY gjbzhyObject=(GJBZHY)gjbzhy.get(i);
        	String gjbzhydm=gjbzhyObject.getGjbzhydm();
        	if(gjbzhydm!=null && !gjbzhydm.equals("")&&gjbzhydm.length()==4){
        		gjbzhyList.add(gjbzhyObject);
//        		System.out.println("gjbzhydm: "+gjbzhyObject.getGjbzhydm()+" mc:"+gjbzhyObject.getGjbzhymc());
        	}
        	
        }
        hdzssdsjbForm.setGjbzhy(gjbzhyList);
	}
	/**
	 * 查账征收季报A类初始化代码表
	 * @param czzssdsjbForm
	 */
	public static void initCodeTable(CzzssdsjbForm czzssdsjbForm){
		//初始化免税收入项目代码表
		initMssrxmCodeList(czzssdsjbForm);
		//初始化减征、免征项目代码表
		initJzmzxmCodeList(czzssdsjbForm);
		//初始化减征、免征项目代码表
		initJmxmCodeList(czzssdsjbForm);
	}   
	/**
	 * 初始化免税收入项目代码表
	 * @param czzssdsjbForm
	 */
    public static void initMssrxmCodeList(CzzssdsjbForm czzssdsjbForm){
    	String sql="SELECT MSSRXMDM,LPAD('  ',2*(LEVEL - 1)) || MSSRXMMC MSSRXMMC, level "+
      "FROM DMDB.SB_DM_QYSDS_MSSRXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR MSSRXMDM = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List mssrxmList=new ArrayList();
    	try {
			conn=SfDBResource.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("MSSRXMDM");
				String mc=rs.getString("MSSRXMMC");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				mssrxmList.add(dmvo);	
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				  try{
				      rs.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(st!=null){
				  try{
					  st.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(conn!=null){
			  try{
				  conn.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		}
		czzssdsjbForm.setMssrxmList(mssrxmList);
    }
    /**
     * 初始化减征、免征项目代码表
     * @param czzssdsjbForm
     */
    public static void initJzmzxmCodeList(CzzssdsjbForm czzssdsjbForm){
    	String sql="SELECT JZMZXMDM,LPAD('  ',2*(LEVEL - 1)) || JZMZXMMC JZMZXMMC, level "+
      "FROM DMDB.SB_DM_QYSDS_JZMZXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR JZMZXMDM = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List jzmzxmList=new ArrayList();
    	try {
			conn=SfDBResource.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("JZMZXMDM");
				String mc=rs.getString("JZMZXMMC");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				jzmzxmList.add(dmvo);	
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				  try{
				      rs.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(st!=null){
				  try{
					  st.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(conn!=null){
			  try{
				  conn.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		}
		czzssdsjbForm.setJzmzxmList(jzmzxmList);
    }
    /**
     * 初始化减免项目代码表
     * @param czzssdsjbForm
     */
    public static void initJmxmCodeList(CzzssdsjbForm czzssdsjbForm){
    	String sql="SELECT jmxmdm,LPAD('  ',2*(LEVEL - 1)) || jmxmmc jmxmmc, level "+
      "FROM DMDB.SB_DM_QYSDS_JMXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR jmxmdm = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List jmxmList=new ArrayList();
    	try {
			conn=SfDBResource.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("jmxmdm");
				String mc=rs.getString("jmxmmc");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				jmxmList.add(dmvo);	
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				  try{
				      rs.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(st!=null){
				  try{
					  st.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(conn!=null){
			  try{
				  conn.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		}
		czzssdsjbForm.setJmxmList(jmxmList);
    }
}
