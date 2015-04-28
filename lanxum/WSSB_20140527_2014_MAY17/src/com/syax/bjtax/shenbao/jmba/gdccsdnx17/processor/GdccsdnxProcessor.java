package com.syax.bjtax.shenbao.jmba.gdccsdnx17.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_17VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_18VO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>
 * Title: 北京地税核心征管系统－－网上申报
 * </p>
 *
 * <p>
 * Description: (十七)固定资产缩短折旧年限或加速折旧 processor
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
public class GdccsdnxProcessor implements Processor {

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

			case JmbaActionConstant.INTI_ACTION_SHOW:
				vo.setData(this.doShow((Map) vo.getData()));
				return vo;
			case JmbaActionConstant.INTI_ACTION_SAVE:
				vo.setData(this.doSave((Map) vo.getData()));
				return vo;
			case JmbaActionConstant.INTI_ACTION_DELETE:
				vo.setData(this.doDelete((Map) vo.getData()));
				return vo;
			case JmbaActionConstant.INTI_ACTION_COMMIT:
				vo.setData(this.doCommit((Map) vo.getData()));
				return vo;

				// 没有可调用的方法
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 *
	 * 提交,提交主要是更改主表的状为3..
	 * @param data
	 * @return
	 * @throws BaseException
	 */

	private Map doCommit(Map data) throws BaseException {
		Connection con = null;
		PreparedStatement st = null;
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		con = DBResource.getConnection();
		String sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, BASQWSH);
			st.executeUpdate();
            st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}
		return null;
	}

	/**
	 */
	private Map doQuery(Map data) throws BaseException {
		Map map = null;

		return map;
	}

	/**
	 *
	 * 查询显示全部数据
	 *
	 * @param data
	 * @return
	 * @throws BaseException
	 */
	private Map doShow(Map data) throws BaseException {
		Map map = new HashMap();
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		String type = (String) data.get("type");
		String selIndex = (String) data.get("selIndex");
		Jmba_17VO vo = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		String tbblx = null;
		try {
			String sql = "select * from sfdb.sf_jl_qysdsjmsba_15 t where t.basqwsh = '"
					+ BASQWSH + "'";
			if (type != null && type.equals("EDITOR")) {
				sql += " and t.xh = '" + selIndex + "'";
				type = "SHOW";
			}
			sql += " order by t.xh";
			System.out.println("sql =================== " + sql);
			con = DBResource.getConnection();
			JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(con, BASQWSH);
			System.out.println("zbvo is " + zbvo);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			//如果有数据,满足条件就进入赋值
			while (rs.next() && type != null && type.equals("SHOW")) {
				vo = new Jmba_17VO();
				vo.setXh(rs.getString("xh"));
				vo.setBasqwsh(rs.getString("basqwsh"));
				vo.setJsjdm(rs.getString("jsjdm"));
				vo.setBand(rs.getString("band"));
				vo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				vo.setGdzcmc_sd(rs.getString("gdzcmc_sd"));
				vo.setSfnxdyzd_sd(rs.getString("sfnxdyzd_sd"));
				vo.setSftgczqksm_sd(rs.getString("sftgczqksm_sd"));
				vo.setGdzcyz_sd(rs.getString("gdzcyz_sd"));
				vo.setGdzcjsjc_sd(rs.getString("gdzcjsjc_sd"));
				vo.setSfgdzdnx_sd(rs.getString("sfgdzdnx_sd"));
				vo.setJszjzdnx_sd(rs.getString("jszjzdnx_sd"));
				vo.setZjqsnd_sd(rs.getString("zjqsnd_sd"));
				vo.setZjzznd_sd(rs.getString("zjzznd_sd"));
				vo.setZje_sd(rs.getString("zje_sd"));
				vo.setYjtzjnx_sd(rs.getString("yjtzjnx_sd"));
				vo.setYjtzje_sd(rs.getString("yjtzje_sd"));
				vo.setGdzcmc_js(rs.getString("gdzcmc_js"));
				vo.setSftgffsm_js(rs.getString("sftgffsm_js"));
				vo.setGdzcyz_js(rs.getString("gdzcyz_js"));
				vo.setGdzcjsjc_js(rs.getString("gdzcjsjc_js"));
				vo.setJszjffdm_js(rs.getString("jszjffdm_js"));
				vo.setZje_js(rs.getString("zje_js"));
				vo.setShbj(rs.getString("shbj"));
				vo.setCjr(rs.getString("cjr"));
				vo.setCjrq(rs.getString("cjrq"));
				vo.setLrr(rs.getString("lrr"));
				vo.setLrrq(rs.getString("lrrq"));
				vo.setTbblx(rs.getString("tbblx"));
				if ("0".equals(rs.getString("tbblx"))) {
					tbblx = "0";
				} else {
					tbblx = "1";
				}
				zbvo.getQysdsjmba().add(vo);
				System.out.println("vo to xml " + vo.toXML());
			}
			//把金额进行合计
			sql = " select sum(NVL(t.gdzcyz_sd,0))gdzcyz_sd,sum(NVL(t.gdzcjsjc_sd,0))gdzcjsjc_sd,"
					+ "sum(NVL(t.zje_sd,0))zje_sd,sum(NVL(t.yjtzje_sd,0))yjtzje_sd,"
					+ "sum(NVL(t.gdzcyz_js,0))gdzcyz_js,sum(NVL(t.gdzcjsjc_js,0))gdzcjsjc_js,"
					+ "sum(NVL(t.zje_js,0))zje_js from sfdb.sf_jl_qysdsjmsba_15 t where t.basqwsh = '"
					+ BASQWSH + "'";
			rs = st.executeQuery(sql);
			while (rs.next() && type != null && type.equals("SHOW")) {
				vo = new Jmba_17VO();
				vo.setXh("合计");
				vo.setBasqwsh("/");
				vo.setJsjdm("/");
				vo.setBand("/");
				vo.setSwjgzzjgdm("/");
				vo.setGdzcmc_sd("/");
				vo.setSfnxdyzd_sd("/");
				vo.setSftgczqksm_sd("/");
				vo.setGdzcyz_sd(rs.getString("gdzcyz_sd"));
				vo.setGdzcjsjc_sd(rs.getString("gdzcjsjc_sd"));
				vo.setSfgdzdnx_sd("/");
				vo.setJszjzdnx_sd("/");
				vo.setZjqsnd_sd("/");
				vo.setZjzznd_sd("/");
				vo.setZje_sd(rs.getString("zje_sd"));
				vo.setYjtzjnx_sd("/");
				vo.setYjtzje_sd(rs.getString("yjtzje_sd"));
				vo.setGdzcmc_js("/");
				vo.setSftgffsm_js("/");
				vo.setGdzcyz_js(rs.getString("gdzcyz_js"));
				vo.setGdzcjsjc_js(rs.getString("gdzcjsjc_js"));
				vo.setJszjffdm_js("/");
				vo.setZje_js(rs.getString("zje_js"));
				vo.setShbj("/");
				vo.setCjr("/");
				vo.setCjrq("/");
				vo.setLrr("/");
				vo.setLrrq("/");
				vo.setTbblx("/");
				zbvo.getQysdsjmba().add(vo);
				System.out.println("vo to xml " + vo.toXML());
			}
			map.put("JmbaZbVO", zbvo);
			map.put("TBBLX", tbblx);
			//map.put("CKZT", zbvo.getZtdm());
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
	/**
	 *
	 * 保存方法
	 * @param data
	 * @return
	 * @throws BaseException
	 */

	private Map doSave(Map data) throws BaseException {
		System.out
				.println("into Processor save==============================================");
		Map map = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		// String tbblx=data.get(key)
		try {
			JmbaVO jmbavo = (JmbaVO) data
					.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
			String type = (String) data.get("type");
			String selIndex = (String) data.get("selIndex");

			String sql = "insert into sfdb.sf_jl_qysdsjmsba_15 "
					+ "(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GDZCMC_SD,SFNXDYZD_SD,SFTGCZQKSM_SD,"
					+ "GDZCYZ_SD,GDZCJSJC_SD,SFGDZDNX_SD,JSZJZDNX_SD,ZJQSND_SD,ZJZZND_SD,ZJE_SD,YJTZJNX_SD,"
					+ "YJTZJE_SD,GDZCMC_JS,SFTGFFSM_JS,GDZCYZ_JS,GDZCJSJC_JS,JSZJFFDM_JS,ZJE_JS,SHBJ,"
					+ "CJR,CJRQ,LRR,LRRQ,TBBLX)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,sysdate,?,sysdate,?)";

			Debug
					.out("save方法执行是sql语句-----------------------------------"
							+ sql);

			con = DBResource.getConnection();
			String xh = "";
			System.out.println("type = ======" + type);
			System.out.println("selIndex--------------------------- = "
					+ selIndex);
			//判断
			if (type != null && selIndex != null && type.equals("Update")) {
				java.sql.Statement delSt = con.createStatement();

				String del = "delete from sfdb.sf_jl_qysdsjmsba_15 t where t.basqwsh = '"
						+ ((JmbaZbVO) jmbavo.getJmsbajl().get(0)).getBasqwsh()
						+ "'" + " and t.xh = '" + selIndex + "'";
				System.out.println("del sql =  " + del);
				delSt.execute(del);
				xh = selIndex;
			} else {
				xh = new QysdsUtil().getSequence();
			}

			st = con.prepareStatement(sql);

			// 赋值
			st.setString(1, xh);
			st.setString(2, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
					.getBasqwsh());
			st.setString(3, jmbavo.getNsrxx().getJsjdm());
			st.setString(4, ((JmbaZbVO) jmbavo.getJmsbajl().get(0)).getBand());
			st.setString(5,
					((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getQysdsjmba().get(0)).getSwjgzzjgdm());
			st.setString(6,
					((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getQysdsjmba().get(0)).getGdzcmc_sd());
			st.setString(7,
					((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getQysdsjmba().get(0)).getSfnxdyzd_sd());
			st.setString(8,
					((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getQysdsjmba().get(0)).getSftgczqksm_sd());
			st.setString(9,
					((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
							.getQysdsjmba().get(0)).getGdzcyz_sd());
			st.setString(10, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getGdzcjsjc_sd());
			st.setString(11, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getSfgdzdnx_sd());
			st.setString(12, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getJszjzdnx_sd());
			st.setString(13, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getZjqsnd_sd());
			st.setString(14, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getZjzznd_sd());
			st.setString(15, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getZje_sd());
			st.setString(16, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getYjtzjnx_sd());
			st.setString(17, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getYjtzje_sd());
			st.setString(18, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getGdzcmc_js());
			st.setString(19, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getSftgffsm_js());
			st.setString(20, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getGdzcyz_js());
			st.setString(21, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getGdzcjsjc_js());
			st.setString(22, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getJszjffdm_js());
			st.setString(23, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getZje_js());
			st.setString(24, ((Jmba_17VO) ((JmbaZbVO) jmbavo.getJmsbajl()
					.get(0)).getQysdsjmba().get(0)).getShbj());
			st.setString(25, jmbavo.getNsrxx().getJsjdm());
			st.setString(26, jmbavo.getNsrxx().getJsjdm());
			System.out
					.println("填报表类型-PROCESSOR:!!!!!!!!!!!!!!!!!!!!!!!!! ===== "
							+ (String) data.get("TBLX"));
			st.setString(27, (String) data.get("TBLX"));
			st.executeUpdate();

			st.clearParameters();
			// 保存操作sqzt=2,提交操作sqzt=3
			sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=1 where BASQWSH=?";
			st = con.prepareStatement(sql);
			st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
					.getBasqwsh());
			st.executeUpdate();

			st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}

		return map;
	}
	/**
	 *
	 * 删除
	 * @param data
	 * @return
	 * @throws BaseException
	 */

	private Map doDelete(Map data) throws BaseException {
		System.out.println("into Processor Delete");

		Connection con = null;
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		String type = (String) data.get("type");
		String selIndex = (String) data.get("selIndex");
		try {
			con = DBResource.getConnection();
			java.sql.Statement delSt = con.createStatement();

			String del = "delete from sfdb.sf_jl_qysdsjmsba_15 t where t.basqwsh = '"
					+ BASQWSH + "' and t.xh = '" + selIndex + "'";
			delSt.execute(del);

			delSt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}
		return null;
	}
}

// /**
// *
// * 查询显示全部数据
// * @param data
// * @return
// * @throws BaseException
// */
// private Map doShow(Map data) throws BaseException {
// Map map = new HashMap();
// String BASQWSH = (String) data.get("BASQWSH");
// System.out.println("BASQWSH = " + BASQWSH);
// String type = (String) data.get("type");
// String selIndex = (String) data.get("selIndex");
// Jmba_17VO vo = null;
// Connection con = null;
// ResultSet rs = null;
// Statement st = null;
// String tbblx = null;
// BigDecimal gdzcyz_sdHje = new BigDecimal("0");
// BigDecimal gdzcjsjc_sdHje = new BigDecimal("0");
// BigDecimal zje_sdHje = new BigDecimal("0");
// BigDecimal yjtzje_sdHje = new BigDecimal("0");
// BigDecimal gdzcyz_jsHje = new BigDecimal("0");
// BigDecimal gdzcjsjc_jsHje = new BigDecimal("0");
// BigDecimal zje_jsHje = new BigDecimal("0");
// try {
// String sql = "select * from sfdb.sf_jl_qysdsjmsba_15 t where t.basqwsh =
// '"
// + BASQWSH + "'";
// if (type != null && type.equals("EDITOR")) {
// sql += " and t.xh = '" + selIndex + "'";
// type = "SHOW";
// }
// sql += " order by t.xh";
// System.out.println("sql =================== " + sql);
// con = DBResource.getConnection();
// JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(con, BASQWSH);
// System.out.println("zbvo is " + zbvo);
// st = con.createStatement();
// rs = st.executeQuery(sql);
// while (rs.next()) {
// vo = new Jmba_17VO();
// vo.setXh(rs.getString("xh"));
// vo.setBasqwsh(rs.getString("basqwsh"));
// vo.setJsjdm(rs.getString("jsjdm"));
// vo.setBand(rs.getString("band"));
// vo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
// vo.setGdzcmc_sd(rs.getString("gdzcmc_sd"));
// vo.setSfnxdyzd_sd(rs.getString("sfnxdyzd_sd"));
// vo.setSftgczqksm_sd(rs.getString("sftgczqksm_sd"));
// vo.setGdzcyz_sd(rs.getString("gdzcyz_sd"));
// vo.setGdzcjsjc_sd(rs.getString("gdzcjsjc_sd"));
// vo.setSfgdzdnx_sd(rs.getString("sfgdzdnx_sd"));
// vo.setJszjzdnx_sd(rs.getString("jszjzdnx_sd"));
// vo.setZjqsnd_sd(rs.getString("zjqsnd_sd"));
// vo.setZjzznd_sd(rs.getString("zjzznd_sd"));
// vo.setZje_sd(rs.getString("zje_sd"));
// vo.setYjtzjnx_sd(rs.getString("yjtzjnx_sd"));
// vo.setYjtzje_sd(rs.getString("yjtzje_sd"));
// vo.setGdzcmc_js(rs.getString("gdzcmc_js"));
// vo.setSftgffsm_js(rs.getString("sftgffsm_js"));
// vo.setGdzcyz_js(rs.getString("gdzcyz_js"));
// vo.setGdzcjsjc_js(rs.getString("gdzcjsjc_js"));
// vo.setJszjffdm_js(rs.getString("jszjffdm_js"));
// vo.setZje_js(rs.getString("zje_js"));
// vo.setShbj(rs.getString("shbj"));
// vo.setCjr(rs.getString("cjr"));
// vo.setCjrq(rs.getString("cjrq"));
// vo.setLrr(rs.getString("lrr"));
// vo.setLrrq(rs.getString("lrrq"));
// vo.setTbblx(rs.getString("tbblx"));
// if ("0".equals(rs.getString("tbblx") )) {
// tbblx = "0";
// } else {
// tbblx = "1";
// }
//
// // gdzcyz_sdHje=gdzcyz_sdHje.add(new
// BigDecimal(rs.getString("gdzcyz_sd")));
// // gdzcjsjc_sdHje=gdzcjsjc_sdHje.add(new
// BigDecimal(rs.getString("gdzcjsjc_sd")));
// // zje_sdHje=zje_sdHje.add(new BigDecimal(rs.getString("zje_sd")));
// // yjtzje_sdHje=yjtzje_sdHje.add(new
// BigDecimal(rs.getString("yjtzje_sd")));
//
// //gdzcyz_jsHje=gdzcyz_jsHje.add(new
// BigDecimal(rs.getString("gdzcyz_js")));
// // gdzcjsjc_jsHje=gdzcjsjc_jsHje.add(new
// BigDecimal(rs.getString("gdzcjsjc_js")));
// // zje_jsHje=zje_jsHje.add(new BigDecimal(rs.getString("zje_js")));
// zbvo.getQysdsjmba().add(vo);
// System.out.println("vo to xml " + vo.toXML());
// }
// gdzcyz_sdHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// gdzcjsjc_sdHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// zje_sdHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// yjtzje_sdHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// gdzcyz_jsHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// gdzcjsjc_jsHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// zje_jsHje.setScale(2,BigDecimal.ROUND_HALF_UP);
// map.put("gdzcyz_sdHje", gdzcyz_sdHje.toString());
// map.put("gdzcjsjc_sdHje", gdzcjsjc_sdHje.toString());
// map.put("zje_sdHje", zje_sdHje.toString());
// map.put("yjtzje_sdHje", yjtzje_sdHje.toString());
// map.put("gdzcyz_jsHje", gdzcyz_jsHje.toString());
// map.put("gdzcjsjc_jsHje", gdzcjsjc_jsHje.toString());
// map.put("zje_jsHje", zje_jsHje.toString());
//
// map.put("JmbaZbVO", zbvo);
// map.put("TBBLX", tbblx);
// } catch (Exception ex) {
// ex.printStackTrace();
// } finally {
// try {
// if (rs != null) {
// rs.close();
// }
// } catch (Exception exx) {
// }
// try {
// if (st != null) {
// st.close();
// }
// } catch (Exception exx) {
// }
// try {
// if (con != null) {
// con.close();
// }
// } catch (Exception exx) {
// }
// }
// return map;
// }

// private Map doCommit(Map data) throws BaseException {
// Map map = null;
// String BASQWSH = "";
// Jmba_17VO vo = null;
// NsrxxVO nsrvo = null;
// String BAND = "";
// Connection con = null;
// ResultSet rs = null;
// PreparedStatement st = null;
// JmbaVO jmbavo = null;
// try {
// // CA
// jmbavo = (JmbaVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
//
// // 取得sequence
// String xh = new QysdsUtil().getSequence();
// jmbavo = (JmbaVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
// JmbaZbVO zbvo = (JmbaZbVO) jmbavo.getJmsbajl().get(0);
// BASQWSH = zbvo.getBasqwsh();
// String jsjdm = jmbavo.getNsrxx().getJsjdm();
//
// // SQL
// String sql = "insert into sfdb.sf_jl_qysdsjmsba_15 "
// +
// "(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GDZCMC_SD,SFNXDYZD_SD,SFTGCZQKSM_SD,"
// +
// "GDZCYZ_SD,GDZCJSJC_SD,SFGDZDNX_SD,JSZJZDNX_SD,ZJQSND_SD,ZJZZND_SD,ZJE_SD,YJTZJNX_SD,"
// +
// "YJTZJE_SD,GDZCMC_JS,SFTGFFSM_JS,GDZCYZ_JS,GDZCJSJC_JS,JSZJFFDM_JS,ZJE_JS,SHBJ,"
// + "CJR,CJRQ,LRR,LRRQ,TBBLX)" + " values ('" + xh + "','"
// + BASQWSH + "','" + jsjdm + "','" + vo.getBand() + "','"
// + vo.getSwjgzzjgdm() + "','" + vo.getGdzcmc_sd() + "',"
// + "'" + vo.getSfnxdyzd_sd() + "','" + vo.getSftgczqksm_sd()
// + "'," + "'" + vo.getGdzcyz_sd() + "','"
// + vo.getGdzcjsjc_sd() + "','" + vo.getSfgdzdnx_sd() + "','"
// + vo.getJszjzdnx_sd() + "','" + vo.getZjqsnd_sd() + "',"
// + "'" + vo.getZjzznd_sd() + "','" + vo.getZje_sd() + "','"
// + vo.getYjtzjnx_sd() + "'," + "'" + vo.getYjtzje_sd()
// + "','" + vo.getGdzcmc_js() + "','" + vo.getSftgffsm_js()
// + "','" + vo.getGdzcyz_js() + "','" + vo.getGdzcjsjc_js()
// + "'," + "'" + vo.getJszjffdm_js() + "','" + vo.getZje_js()
// + "','" + vo.getShbj() + "'," + "'" + jsjdm + "',sysdate,'"
// + jsjdm + "',sysdate,'" + vo.getTbblx() + "')";
//
// st.executeUpdate();
//
// st.clearParameters();
// // 保存操作sqzt=2,提交操作sqzt=3
// sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";//
// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
// st = con.prepareStatement(sql);
// st.setString(1, ((JmbaZbVO) jmbavo.getJmsbajl().get(0))
// .getBasqwsh());
// st.executeUpdate();
//
// st.close();
//
// } catch (Exception ex) {
// ex.printStackTrace();
// throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
// } finally {
// DBResource.destroyConnection(con);
// }
//
// return map;
// }

