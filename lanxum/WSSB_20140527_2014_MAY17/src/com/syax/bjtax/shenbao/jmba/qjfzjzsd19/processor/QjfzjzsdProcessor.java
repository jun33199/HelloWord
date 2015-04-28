package com.syax.bjtax.shenbao.jmba.qjfzjzsd19.processor;

import com.ttsoft.bjtax.sfgl.model.orobj.Qysds;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;

import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba07Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_17VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_19VO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;

/**
 *
 * <p>
 * Title: 北京地税核心征管系统－－网上申报
 * </p>
 *
 * <p>
 * Description: （十九）清洁发展机制项目所得 processor
 * </p>
 *
 * <p>
 * Copyright: 四一安信
 * </p>
 *
 * <p>
 * Company: 四一安信
 * </p>
 *
 * @author 米军
 * @version 1.0
 */
public class QjfzjzsdProcessor implements Processor {

	/**
	 * 总控数据
	 */
	private UserData userData;

	/**
	 * 处理请求
	 */
	public Object process(VOPackage vo) throws BaseException {
		this.userData = vo.getUserData();

		// 根据业务操作类型值来做业务操作
		try {
			switch (vo.getAction()) {
			// 查询
			case JmbaActionConstant.INTI_ACTION_QUERY:
				vo.setData(this.doQuery((Map) vo.getData()));
				// show方法
			case JmbaActionConstant.INTI_ACTION_SHOW:
				Debug.out("==================19show");
				vo.setData(this.doShow((Map) vo.getData()));

				return vo;
			case JmbaActionConstant.INTI_ACTION_SAVE:
				Debug.out("=====================19保存");
				vo.setData(this.doSave((Map) vo.getData()));
				// 保存
				return vo;
			case JmbaActionConstant.INTI_ACTION_COMMIT:
				// 提交
				Debug.out("=====================19提交");
				//vo.setData(this.doCommit((Map) vo.getData()));
				return vo;

			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 */
	private Map doQuery(Map data) throws BaseException {
		Map map = null;

		return map;
	}

	/**
	 */
	private Map doShow(Map data) throws BaseException {
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		String jsjdm = (String) data.get("jsjdm");
		Map map = new HashMap();
		Jmba_19VO vo = new Jmba_19VO();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		//存储过程返回类型
		String czlx19=null;
		try {
			String sql = "BEGIN SFDB.PRO_JMBA_QYSDSJMSBA_17(?,?,?,?); END;";
			con = DBResource.getConnection();
			CallableStatement cs = con.prepareCall(sql); // 调用存储过程
			cs.setString(1, jsjdm);
			cs.setString(2,BASQWSH);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();

			int czlx=Integer.parseInt(cs.getString(3));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(czlx+"------------------从存储过程取道的操作状态");
			switch (czlx) {
			case 0:
				vo.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1
						+ "");
				vo.setLrr(jsjdm);
				vo.setLrrq(format.format(new Date(System.currentTimeMillis())));
				czlx19="0";
				break;
			case 1:
				rs = (ResultSet) cs.getObject(4);
				while(rs.next()){
					vo.setXh(rs.getString("xh"));
					vo.setBasqwsh(rs.getString("basqwsh"));
					vo.setJsjdm(rs.getString("jsjdm"));
					vo.setBand(rs.getString("band"));
					vo.setZrsrje(rs.getString("zrsrje"));
					vo.setSjje1(rs.getString("sjje1"));
					vo.setSjje2(rs.getString("sjje2"));
					vo.setSfyzrzmcl(rs.getString("sfyzrzmcl"));
					vo.setSfysjzmcl(rs.getString("sfysjzmcl"));
					vo.setSfysrzmcl(rs.getString("sfysrzmcl"));
					vo.setSfyhsqksm(rs.getString("sfyhsqksm"));
					vo.setHlnd(rs.getString("hlnd"));
					vo.setQtzl(rs.getString("qtzl"));
					vo.setMzqsnd(rs.getString("mzqsnd"));
					vo.setMzzznd(rs.getString("mzzznd"));
					vo.setJzqsnd(rs.getString("jzqsnd"));
					vo.setJzzznd(rs.getString("jzzznd"));
					vo.setCjr(rs.getString("CJR"));
					vo.setCjrq(rs.getString("CJRQ"));
					vo.setLrr(rs.getString("LRR"));
					vo.setLrrq(rs.getString("LRRQ"));
				}
				czlx19="1";
				break;
			case 2:
				rs = (ResultSet) cs.getObject(4);
				while(rs.next()){
					vo.setXh(rs.getString("xh"));
					vo.setBasqwsh(rs.getString("basqwsh"));
					vo.setJsjdm(rs.getString("jsjdm"));
					vo.setBand(rs.getString("band"));
					vo.setZrsrje(rs.getString("zrsrje"));
					vo.setSjje1(rs.getString("sjje1"));
					vo.setSjje2(rs.getString("sjje2"));
					vo.setSfyzrzmcl(rs.getString("sfyzrzmcl"));
					vo.setSfysjzmcl(rs.getString("sfysjzmcl"));
					vo.setSfysrzmcl(rs.getString("sfysrzmcl"));
					vo.setSfyhsqksm(rs.getString("sfyhsqksm"));
					vo.setHlnd(rs.getString("hlnd"));
					vo.setQtzl(rs.getString("qtzl"));
					vo.setMzqsnd(rs.getString("mzqsnd"));
					vo.setMzzznd(rs.getString("mzzznd"));
					vo.setJzqsnd(rs.getString("jzqsnd"));
					vo.setJzzznd(rs.getString("jzzznd"));
					vo.setCjr(rs.getString("CJR"));
					vo.setCjrq(rs.getString("CJRQ"));
					vo.setLrr(rs.getString("LRR"));
					vo.setLrrq(rs.getString("LRRQ"));
				}
				czlx19="2";
				break;
			case 3:
				vo.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1
						+ "");
				vo.setLrr(jsjdm);
				vo.setLrrq(format.format(new Date(System.currentTimeMillis())));
				czlx19="3";
				break;
			default:
				czlx19="4";
				break;
			}
			map.put("Jmba_19VO", vo);
			map.put("czlx19", czlx19);
			map.put("JmbaZbVO", PublicAccess.getJmbaZbVO(con, BASQWSH));
			map.put("JmbaZlqdVO", PublicAccess.getJmbaZlqdVO(con, BASQWSH));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

		return map;
	}

//	/**
//	 */
//	private Map doShow(Map data) throws BaseException {
//		String BASQWSH = (String) data.get("BASQWSH");
//		System.out.println("BASQWSH = " + BASQWSH);
//		String jsjdm = (String) data.get("jsjdm");
//		Map map = new HashMap();
//		Jmba_19VO vo = new Jmba_19VO();
//		Connection con = null;
//		ResultSet rs = null;
//		Statement st = null;
//		try {
//			String sql = "select * from sfdb.sf_jl_qysdsjmsba_17 t where t.basqwsh = '"
//					+ BASQWSH + "' order by t.xh";
//			System.out.println(sql+"========");
//			con = DBResource.getConnection();
//			st = con.createStatement();
//			rs = st.executeQuery(sql);
//			if (rs.next()) {
//				vo.setXh(rs.getString("xh"));
//				vo.setBasqwsh(rs.getString("basqwsh"));
//				vo.setJsjdm(rs.getString("jsjdm"));
//				vo.setBand(rs.getString("band"));
//				vo.setZrsrje(rs.getString("zrsrje"));
//				vo.setSjje1(rs.getString("sjje1"));
//				vo.setSjje2(rs.getString("sjje2"));
//				vo.setSfyzrzmcl(rs.getString("sfyzrzmcl"));
//				vo.setSfysjzmcl(rs.getString("sfysjzmcl"));
//				vo.setSfysrzmcl(rs.getString("sfysrzmcl"));
//				vo.setSfyhsqksm(rs.getString("sfyhsqksm"));
//				vo.setHlnd(rs.getString("hlnd"));
//				vo.setQtzl(rs.getString("qtzl"));
//				vo.setMzqsnd(rs.getString("mzqsnd"));
//				vo.setMzzznd(rs.getString("mzzznd"));
//				vo.setJzqsnd(rs.getString("jzqsnd"));
//				vo.setJzzznd(rs.getString("jzzznd"));
//				vo.setCjr(rs.getString("CJR"));
//				vo.setCjrq(rs.getString("CJRQ"));
//				vo.setLrr(rs.getString("LRR"));
//				vo.setLrrq(rs.getString("LRRQ"));
//			} else {
//				vo.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1
//						+ "");
//				vo.setLrr(jsjdm);
//				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//				vo.setLrrq(format.format(new Date(System.currentTimeMillis())));
//			}
//			map.put("Jmba_19VO", vo);
//			map.put("JmbaZbVO", PublicAccess.getJmbaZbVO(con, BASQWSH));
//			map.put("JmbaZlqdVO", PublicAccess.getJmbaZlqdVO(con, BASQWSH));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (Exception exx) {
//			}
//			try {
//				if (st != null) {
//					st.close();
//				}
//			} catch (Exception exx) {
//			}
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (Exception exx) {
//			}
//		}
//
//		return map;
//	}

	private Map doSave(Map date) throws BaseException {
		Map map = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = "";
		try {
			// CA
			JmbaVO jmbavo = (JmbaVO) date
					.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);

			if ("3".equals((jmbavo.getYwczlx()))) {
				sql = "select * from sfdb.sf_jl_qysdsjmsba_17 t where t.basqwsh = ?"
						+ " order by t.xh";
				con = DBResource.getConnection();
				st = con.prepareStatement(sql);
				st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
						.getBasqwsh());
				rs = st.executeQuery();

				if (rs.next()) {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
                        con = null;
					}
					// SQL
					sql = "update sfdb.sf_jl_qysdsjmsba_17 set ZRSRJE=?,SJJE1=?,SJJE2=?,SFYZRZMCL=?,SFYSJZMCL=?,SFYSRZMCL=?,"
							+ "SFYHSQKSM=?,HLND=?,QTZL=?,MZQSND=?,MZZZND=?,JZQSND=?,JZZZND=? where BASQWSH=?";
					System.out.println("=================sql-----------" + sql);
					// 得到连接
					con = DBResource.getConnection();
					st = con.prepareStatement(sql);
					// 赋值
					st.setString(1, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getZrsrje());
					st.setString(2, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje1());
					st.setString(3, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje2());
					st.setString(4, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyzrzmcl());
					st.setString(5, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysjzmcl());
					st.setString(6, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysrzmcl());
					st.setString(7, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyhsqksm());
					st.setString(8, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getHlnd());
					st.setString(9, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getQtzl());
					st.setString(10, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzqsnd());
					st.setString(11, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzzznd());
					st.setString(12, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzqsnd());
					st.setString(13, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzzznd());
					st.setString(14, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBasqwsh());
					st.executeUpdate();
					st.clearParameters();
					sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
					Debug
							.out("save方法执行是更新主表sql语句-----------------------------------"
									+ sql);
					st = con.prepareStatement(sql);
					st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBasqwsh());
					st.executeUpdate();
					st.close();
                    if (con != null) {
                        con.close();
                        con = null;
                    }

				} else {
					// 如果没数据就新增
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
					// SQL
					sql = "insert into sfdb.sf_jl_qysdsjmsba_17 "
							+ "(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,ZRSRJE,SJJE1,SJJE2,"
							+ "SFYZRZMCL,SFYSJZMCL,SFYSRZMCL,SFYHSQKSM,HLND,QTZL,MZQSND," +
									"MZZZND,JZQSND,JZZZND,CJR,CJRQ,LRR,LRRQ)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?)";
					System.out.println("=================sql-----------" + sql);

					// 取得sequence
					String xh = new QysdsUtil().getSequence();
					// 得到连接
					con = DBResource.getConnection();
					st = con.prepareStatement(sql);
					// 赋值
					st.setString(1, xh);
					st.setString(2, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBasqwsh());
					st.setString(3, jmbavo.getNsrxx().getJsjdm());
					st.setString(4, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBand());
					st.setString(5, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSwjgzzjgdm());
					st.setString(6, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getZrsrje());
					st.setString(7, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje1());
					st.setString(8, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje2());
					st.setString(9, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyzrzmcl());
					st.setString(10, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysjzmcl());
					st.setString(11, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysrzmcl());
					st.setString(12, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyhsqksm());
					st.setString(13, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getHlnd());
					st.setString(14, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getQtzl());
					st.setString(15, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzqsnd());
					st.setString(16, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzzznd());
					st.setString(17, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzqsnd());
					st.setString(18, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzzznd());
					st.setString(19, jmbavo.getNsrxx().getJsjdm());
					st.setString(20, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getLrr());
					String str1 = ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
							.get(0)).getQysdsjmba().get(0)).getLrrq();

					st.setDate(21, new java.sql.Date(DateUtilPro.getDate(str1,
							"yyyy-MM-dd").getTime()));
					st.executeUpdate();
					st.clearParameters();
					sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
					Debug
							.out("save方法执行是更新主表sql语句-----------------------------------"
									+ sql);
					st = con.prepareStatement(sql);
					st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBasqwsh());
					st.executeUpdate();
					st.close();
				}

			} else {
				sql = "select * from sfdb.sf_jl_qysdsjmsba_17 t where t.basqwsh = ?"
						+ " order by t.xh";
				con = DBResource.getConnection();
				st = con.prepareStatement(sql);
				st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
						.getBasqwsh());
				rs = st.executeQuery();

				if (rs.next()) {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
					// SQL
					sql = "update sfdb.sf_jl_qysdsjmsba_17 set ZRSRJE=?,SJJE1=?,SJJE2=?,SFYZRZMCL=?,SFYSJZMCL=?,SFYSRZMCL=?,"
							+ "SFYHSQKSM=?,HLND=?,QTZL=?,MZQSND=?,MZZZND=?,JZQSND=?,JZZZND=? where BASQWSH=?";
					System.out.println("=================sql-----------" + sql);
					// 得到连接
					con = DBResource.getConnection();
					st = con.prepareStatement(sql);
					// 赋值
					st.setString(1, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getZrsrje());
					st.setString(2, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje1());
					st.setString(3, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje2());
					st.setString(4, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyzrzmcl());
					st.setString(5, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysjzmcl());
					st.setString(6, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysrzmcl());
					st.setString(7, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyhsqksm());
					st.setString(8, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getHlnd());
					st.setString(9, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getQtzl());
					st.setString(10, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzqsnd());
					st.setString(11, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzzznd());
					st.setString(12, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzqsnd());
					st.setString(13, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzzznd());
					st.setString(14, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBasqwsh());
					st.executeUpdate();

				} else {
					// 如果没数据就新增
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
					// SQL
					sql = "insert into sfdb.sf_jl_qysdsjmsba_17 "
							+ "(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,ZRSRJE,SJJE1,SJJE2,"
							+ "SFYZRZMCL,SFYSJZMCL,SFYSRZMCL,SFYHSQKSM,HLND,QTZL,MZQSND,MZZZND,JZQSND,JZZZND,CJR,CJRQ,LRR,LRRQ)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?)";
					System.out.println("=================sql-----------" + sql);

					// 取得sequence
					String xh = new QysdsUtil().getSequence();
					// 得到连接
					con = DBResource.getConnection();
					st = con.prepareStatement(sql);
					// 赋值
					st.setString(1, xh);
					st.setString(2, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBasqwsh());
					st.setString(3, jmbavo.getNsrxx().getJsjdm());
					st.setString(4, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getBand());
					st.setString(5, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSwjgzzjgdm());
					st.setString(6, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getZrsrje());
					st.setString(7, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje1());
					st.setString(8, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSjje2());
					st.setString(9, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyzrzmcl());
					st.setString(10, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysjzmcl());
					st.setString(11, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfysrzmcl());
					st.setString(12, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getSfyhsqksm());
					st.setString(13, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getHlnd());
					st.setString(14, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getQtzl());
					st.setString(15, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzqsnd());
					st.setString(16, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getMzzznd());
					st.setString(17, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzqsnd());
					st.setString(18, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getJzzznd());
					st.setString(19, jmbavo.getNsrxx().getJsjdm());
					st.setString(20, ((Jmba_19VO) ((JmbaZbVO) jmbavo
							.getJmsbajl().get(0)).getQysdsjmba().get(0))
							.getLrr());
					String str1 = ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
							.get(0)).getQysdsjmba().get(0)).getLrrq();

					st.setDate(21, new java.sql.Date(DateUtilPro.getDate(str1,
							"yyyy-MM-dd").getTime()));
					st.executeUpdate();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}

		return map;
	}
}
	//
	// // SQL
	// sql = "insert into sfdb.sf_jl_qysdsjmsba_17 "
	// + "(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,ZRSRJE,SJJE1,SJJE2,"
	// +
	// "SFYZRZMCL,SFYSJZMCL,SFYSRZMCL,SFYHSQKSM,HLND,QTZL,MZQSND,MZZZND,JZQSND,JZZZND,CJR,CJRQ,LRR,LRRQ)"
	// + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate)";
	// System.out.println("=================sql-----------" + sql);
	//
	// // 取得sequence
	// String xh = new QysdsUtil().getSequence();
	// // 得到连接
	// con = DBResource.getConnection();
	// st = con.prepareStatement(sql);
	// // 赋值
	// st.setString(1, xh);
	// st.setString(2, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getBasqwsh());
	// st.setString(3, jmbavo.getNsrxx().getJsjdm());
	// st.setString(4, ((JmbaZbVO) jmbavo.getJmsbajl().get(0)).getBand());
	// st.setString(5,
	// ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getQysdsjmba().get(0)).getSwjgzzjgdm());
	// st.setString(6,
	// ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getQysdsjmba().get(0)).getZrsrje());
	// st.setString(7,
	// ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getQysdsjmba().get(0)).getSjje1());
	// st.setString(8,
	// ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getQysdsjmba().get(0)).getSjje2());
	// st.setString(9,
	// ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getQysdsjmba().get(0)).getSfyzrzmcl());
	// st.setString(10, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getSfysjzmcl());
	// st.setString(11, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getSfysrzmcl());
	// st.setString(12, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getSfyhsqksm());
	// st.setString(13, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getHlnd());
	// st.setString(14, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getQtzl());
	// st.setString(15, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getMzqsnd());
	// st.setString(16, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getMzzznd());
	// st.setString(17, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getJzqsnd());
	// st.setString(18, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
	// .get(0)).getQysdsjmba().get(0)).getJzzznd());
	// st.setString(19, jmbavo.getNsrxx().getJsjdm());
	// st.setString(20, jmbavo.getNsrxx().getJsjdm());
	//
	// st.executeUpdate();
	//
	// st.clearParameters();
	// // 保存操作sqzt=2,提交操作sqzt=3
	// sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=2 where BASQWSH=?";//
	// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
	// st = con.prepareStatement(sql);
	// st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
	// .getBasqwsh());
	// st.executeUpdate();
	// st.close();
	//
	// }
//	private Map doCommit(Map date) throws BaseException {
//		Map map = null;
//		Connection con = null;
//		ResultSet rs = null;
//		PreparedStatement st = null;
//		try {
//			// CA
//			JmbaVO jmbavo = (JmbaVO) date
//					.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
//
//			// SQL
//			String sql = "insert into sfdb.sf_jl_qysdsjmsba_17 "
//					+ "(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,ZRSRJE,SJJE1,SJJE2,"
//					+ "SFYZRZMCL,SFYSJZMCL,SFYSRZMCL,SFYHSQKSM,QTZL,CJR,CJRQ,LRR,LRRQ)"
//					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate)";
//			// 取得sequence
//			String xh = new QysdsUtil().getSequence();
//			con = DBResource.getConnection();
//			st = con.prepareStatement(sql);
//			st = con.prepareStatement(sql);
//			// 赋值
//			st.setString(1, xh);
//			st.setString(2, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//					.getBasqwsh());
//			st.setString(3, jmbavo.getNsrxx().getJsjdm());
//			st.setString(4, ((JmbaZbVO) jmbavo.getJmsbajl().get(0)).getBand());
//			st.setString(5,
//					((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//							.getQysdsjmba().get(0)).getSwjgzzjgdm());
//			st.setString(6,
//					((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//							.getQysdsjmba().get(0)).getZrsrje());
//			st.setString(7,
//					((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//							.getQysdsjmba().get(0)).getSjje1());
//			st.setString(8,
//					((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//							.getQysdsjmba().get(0)).getSjje2());
//			st.setString(9,
//					((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//							.getQysdsjmba().get(0)).getSfyzrzmcl());
//			st.setString(10, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
//					.get(0)).getQysdsjmba().get(0)).getSfysjzmcl());
//			st.setString(11, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
//					.get(0)).getQysdsjmba().get(0)).getSfysrzmcl());
//			st.setString(12, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
//					.get(0)).getQysdsjmba().get(0)).getSfyhsqksm());
//			st.setString(13, ((Jmba_19VO) ((JmbaZbVO) jmbavo.getJmsbajl()
//					.get(0)).getQysdsjmba().get(0)).getQtzl());
//			st.setString(14, jmbavo.getNsrxx().getJsjdm());
//			st.setString(15, jmbavo.getNsrxx().getJsjdm());
//
//			st.executeUpdate();
//
//			st.clearParameters();
//			// 保存操作sqzt=2,提交操作sqzt=3
//			sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
//			st = con.prepareStatement(sql);
//			st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
//					.getBasqwsh());
//			st.executeUpdate();
//
//			st.close();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
//		} finally {
//			DBResource.destroyConnection(con);
//		}
//
//		return map;
//	}
//}
