package com.syax.bjtax.shenbao.jmba.jmbaz.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class JmbazProcessor implements Processor {
	/**
	 * 实现Processor接口
	 *
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
	 */

	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		//查询代码表
		case JmbaActionConstant.INTI_ACTION_SHOW:
			result = doShow(vo);
			break;

		case JmbaActionConstant.INTI_ACTION_QUERY:
			result = doQuery(vo);
			break;

		case JmbaActionConstant.INTI_ACTION_SAVE:
			result = doSave(vo, "1");////保存为1
			break;
		case JmbaActionConstant.INTI_ACTION_DELETE:
			result = doDelete(vo);
			break;
		case JmbaActionConstant.INTI_ACTION_QUERYZB:
			result = doQueryZB(vo);
			break;
		case JmbaActionConstant.INTI_ACTION_ROLLBACK:
			result = doRollback(vo);
			break;
		case JmbaActionConstant.INTI_ACTION_COMMIT:
			result = doSave(vo, "3");//提交为3
			break;

		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		Connection conn = null;
		UserData ud = vo.getUserData();
		JmbaZbVO vo1 = (JmbaZbVO) vo.getData();
		JmbamxBo mxbo = new JmbamxBo();
		try {
			conn = DBResource.getConnection();
			String querysql = "BEGIN SFDB.SF_PKG_QYSDSJMBA.main(?,?,?,?,?,?,?); END;";

			CallableStatement cs = conn.prepareCall(querysql); // 调用存储过程

			cs.setString(1, ud.getSsdwdm().substring(0, 2));
			cs.setString(2, vo1.getBand());
			cs.setString(3, ud.getYhid());
			cs.setString(4, vo1.getJmbasxdm());

			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);

			cs.execute();
			String outbs = cs.getString(5);
			System.out.println("===doadd check sql==" + querysql);
			mxbo.setZtdm(outbs);
			mxbo.setJmbasxdm(vo1.getJmbasxdm());
			mxbo.setBand(vo1.getBand());

			if ("0".equals(outbs)) {
				mxbo.setBasqwsh(cs.getString(6));
				mxbo.setBasqbh(cs.getString(7));

			}
			if (cs != null) {
				cs.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}

		return mxbo;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 *
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		JmbazForm zb = (JmbazForm) vo.getData();
		String zq = zb.getCheckZq();
		JmbaVO s = null;
		int curBand = Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1;
		UserData user = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBResource.getConnection();
			//and a.sqzt<>'1'
			StringBuffer sb = new StringBuffer();
			sb.append(" select a.jsjdm jsjdm,b.nsrmc nsrmc,a.band band, a.BAJMbl,");
			sb.append(" '普通用户' yhlb,a.jmbasxdm,a.sqzt,a.basqwsh,a.basqbh,  ");
			sb.append(" (select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=a.jmbasxdm) jmbasxmc, ");
			sb.append(" decode(a.sqlxdm,'0','网上申请','1','上门申请') sqlx, ");
			sb.append(" decode(a.sqzt,'1','已保存未提交','2','已保存未审核','3','已提交未审核','4','审核已通过','5','审核未通过','6','已作废') sqztmc ");
			//sb.append(" from sfdb.sf_jl_qysdsjmsbajl a ,djdb.dj_jl_jbsj b
			// where a.jsjdm=b.jsjdm and a.sqlxdm='0'");
			sb.append(" from sfdb.sf_jl_qysdsjmsbajl a ,djdb.dj_jl_jbsj b where a.jsjdm=b.jsjdm  ");
			String jmsbasx = zb.getJmbasxdm();
			String sqzt = zb.getZtdm();
			String band = zb.getBand();

			sb.append(" and a.jsjdm='" + user.getYhid() + "' ");

			if (jmsbasx != null && jmsbasx.trim().length() > 0) {
				sb.append(" and a.jmbasxdm='" + jmsbasx + "' ");
			}

			if (sqzt != null && sqzt.trim().length() > 0) {
				sb.append(" and a.sqzt='" + sqzt + "' ");
			}
			if (band != null && band.trim().length() > 0) {
				sb.append(" and a.band='" + band + "' ");
			}
			Debug.out("+++++++++++++++++++++++++++++++++查看++++++++++++++++"
					+ sb.toString());
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				JmbaZbVO vo1 = new JmbaZbVO();
				vo1.setJmbasxdm(rs.getString("JMBASXDM"));
				vo1.setJmbasxmc(rs.getString("JMBASXMC"));
				vo1.setBand(rs.getString("band"));
				vo1.setZtdm(rs.getString("SQZT"));
				int theBand = Integer.parseInt(rs.getString("band"));

				if ("1".equals(zq) || curBand != theBand) {
					vo1.setZtdm("9");
				}

				vo1.setZtmc(rs.getString("SQZTMC"));
				vo1.setBasqwsh(rs.getString("basqwsh"));
				vo1.setBasqbh(rs.getString("basqbh"));

				list.add(vo1);
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}
		return list;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 *
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		DzyjHelper dh = new DzyjHelper();
		Map hm = (Map) vo.getData();
		JmbaVO bavo = (JmbaVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO) bavo.getJmsbajl().get(0);
		String basqwsh = vo1.getBasqwsh();
		String jmbasxdm = vo1.getJmbasxdm();
		System.out.println("basqwsh=" + basqwsh + "   basxdm=" + jmbasxdm);

		UserData user = vo.getUserData();
		Connection conn = null;
		Statement ps = null;
		try {
			//			电子原件暂不实现
			/*
			 * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
			 * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
			 * ex) { ex.printStackTrace(); throw
			 * ExceptionUtil.getBaseException(ex); }
			 * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			 */
			conn = DBResource.getConnection();
			String sql = "delete sfdb.sf_jl_qysdsjmsbajlzlqd where basqwsh='"
					+ basqwsh + "'";
			//String sql="delete from SFDB.SF_JL_QYSDSJMSBAJL where
			// basqwsh='"+basqwsh+"'";
			System.out.println(sql);

			ps = conn.createStatement();
			ps.addBatch(sql);
			ps.addBatch("delete " + QysdsUtil.getTableNameByJmbasxdm(jmbasxdm)
					+ " where basqwsh='" + basqwsh + "'");
			ps.addBatch("delete sfdb.sf_jl_qysdsjmsbajl where basqwsh='"
					+ basqwsh + "'");
			ps.executeBatch();

			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}
		return null;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 *
	 */

	private Object doRollback(VOPackage vo) throws BaseException {

		DzyjHelper dh = new DzyjHelper();
		Map hm = (Map) vo.getData();
		JmbaVO bavo = (JmbaVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO) bavo.getJmsbajl().get(0);
		String basqwsh = vo1.getBasqwsh();
		String jmbasxdm = vo1.getJmbasxdm();

		UserData user = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//			电子原件暂不实现
			/*
			 * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
			 * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
			 * ex) { ex.printStackTrace(); throw
			 * ExceptionUtil.getBaseException(ex); }
			 * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			 */
			conn = DBResource.getConnection();
			//提交转保存
			String sql = "update SFDB.SF_JL_QYSDSJMSBAJL set sqzt='1' where basqwsh='"
					+ basqwsh + "'";
			System.out.println(sql);

			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}
		return null;
	}


	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 *
	 */

	private Object doQueryZB(VOPackage vo) throws BaseException {

		String basqwsh = (String) vo.getData();

		UserData user = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBResource.getConnection();
			
			String SQL = "SELECT T.basqwsh,T.BAsqbh,T.JSJDM,T.BAND,A.NSRMC,S.JMBASXMC," +
					" T.ZFSM,to_char(T.ZFRQ,'YYYY-MM-DD') zfrq,to_char(T.QSRQ, 'yyyy-mm-dd') QSRQ,to_char(T.JZRQ, 'yyyy-mm-dd') JZRQ,to_char(T.LRRQ, 'yyyy-mm-dd') LRRQ,T.Bajmse,T.LRRQ, T.sqzt,T.szDM,"
					+ "t.fhwjmc,T.LRR,T.JMBASXDM,T.BAJMbl, decode(t.sqzt,'1','已保存未提交','2','已保存未审核','3','已提交未审核','4','审核已通过','5','审核未通过','6','已作废') sqztmc FROM SFDB.SF_JL_QYSDSJMSBAJL T, DJDB.DJ_JL_JBSJ A, DMDB.SF_DM_JMBASXDM S "
					+ "WHERE T.JSJDM = A.JSJDM AND T.JMBASXDM = S.JMBASXDM  AND T.BASQWSH = '"
					+ basqwsh + "'";
			
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			JmbaZbVO vo1 = new JmbaZbVO();
			if (rs.next()) {

				vo1.setXh(rs.getString("BASQWSH"));
				vo1.setBasqwsh(rs.getString("BASQWSH"));
				vo1.setBand(rs.getString("BAND"));
				vo1.setBasqbh(rs.getString("BASQBH"));
				vo1.setSzmc("企业所得税");
				vo1.setZtdm(rs.getString("SQZT"));
				vo1.setZtmc(rs.getString("SQZTMC"));
				vo1.setSzdm(rs.getString("SZDM"));

				vo1.setJmbasxmc(rs.getString("JMBASXMC"));
				vo1.setJmbasxdm(rs.getString("JMBASXDM"));
				vo1.setQsrq(rs.getString("QSRQ"));
				vo1.setJzrq(rs.getString("JZRQ"));
				vo1.setBajmse(rs.getString("Bajmse"));
				vo1.setBajmbl(rs.getString("Bajmbl"));
				vo1.setFhwjmc(rs.getString("FHWJMC"));
				vo1.setLrrq(rs.getString("LRRQ"));
				vo1.setZfsm(rs.getString("ZFSM"));
				vo1.setZfrq(rs.getString("ZFRQ"));
			}

			List l = PublicAccess.getJmbaZlqdVO(conn, basqwsh);
			vo1.setBajlzlqd(l);
			list.add(vo1);

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}
		return list;
	}

	private Object doSave(VOPackage vo, String sqzt) throws BaseException {
		System.out.println("进入到保存processor");
		UserData ud = vo.getUserData();
		Map m = (Map) vo.getData();
		JmbaVO form = (JmbaVO) m.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst;
		QysdsUtil qysdsUtil = new QysdsUtil();
		HashMap map = new HashMap();
		// 获取简称
		try {
			//			电子原件暂不实现
			/*
			 * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
			 * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
			 * ex) { ex.printStackTrace(); throw
			 * ExceptionUtil.getBaseException(ex); }
			 * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			 */

			String qxdm = ud.getSsdwdm().substring(0, 2);
			conn = DBResource.getConnection();
			String basqwsh = ((JmbaZbVO) form.getJmsbajl().get(0)).getBasqwsh();
			System.out.println("basqwsh====" + basqwsh);
			String basqbh = ((JmbaZbVO) form.getJmsbajl().get(0)).getBasqbh();
			boolean modify = true; //默认为修改状态，如果备案申请编号和文书号为空，设置为新增状态
			if (basqwsh == null || basqwsh.trim().length() == 0) {//判断如果备案申请文书号为空，则认为是新增状态

				modify = false;
				//调用公用方法，取得备案申请文书号和备案编号
				map = qysdsUtil.getBasqbh(qxdm, ""
						+ ((JmbaZbVO) form.getJmsbajl().get(0)).getBand());
				basqwsh = (String) map.get("basqwsh");
				basqbh = (String) map.get("basqbh");
				((JmbaZbVO) form.getJmsbajl().get(0)).setBasqwsh(basqwsh);
				((JmbaZbVO) form.getJmsbajl().get(0)).setBasqbh(basqbh);
			}

			System.out.println("====basqwsh===="
					+ ((JmbaZbVO) form.getJmsbajl().get(0)).getBasqwsh());
			String bajmse = "";
			String sql = "";
			if (modify) {
				// 主表sql
				sql = " update SFDB.SF_JL_QYSDSJMSBAJL set bajmse='"
						+ ((JmbaZbVO) form.getJmsbajl().get(0)).getBajmse()
						+ "' , bajmbl='"
						+ ((JmbaZbVO) form.getJmsbajl().get(0)).getBajmbl()
						+ "' , fhwjmc='"
						+ ((JmbaZbVO) form.getJmsbajl().get(0)).getFhwjmc()
						+ "' , sqzt='"+sqzt+"' , ";

				if (((JmbaZbVO) form.getJmsbajl().get(0)).getQsrq() != null
						&& ((JmbaZbVO) form.getJmsbajl().get(0)).getQsrq()
								.trim().length() > 0) {
					sql += " qsrq= to_date('"
							+ ((JmbaZbVO) form.getJmsbajl().get(0)).getQsrq()
							+ "', 'yyyy-mm-dd'),";
				} else {
					sql += " qsrq=null, ";
				}
				if (((JmbaZbVO) form.getJmsbajl().get(0)).getJzrq() != null
						&& ((JmbaZbVO) form.getJmsbajl().get(0)).getJzrq()
								.trim().length() > 0) {
					sql += " jzrq= to_date('"
							+ ((JmbaZbVO) form.getJmsbajl().get(0)).getJzrq()
							+ "', 'yyyy-mm-dd'),";
				} else {
					sql += " jzrq=null, ";
				}
				sql += " lrr='" + ud.getYhid() + "',lrrq=sysdate "
						+ " where basqwsh='" + basqwsh + "'";
				pst = conn.prepareStatement(sql);

			} else {
				sql = "insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,basqbh,JSJDM,jmbasxdm,szdm,sqzt,sqlxdm,BAND,bajmse,BAJMbl,fhwjmc,qsrq,jzrq,tjr,tjsj,CJR,CJRQ,LRR,LRRQ,swjgzzjgdm) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,sysdate,?)";
				Timestamp qsrq = null;
				if (((JmbaZbVO) form.getJmsbajl().get(0)).getQsrq() != null
						&& ((JmbaZbVO) form.getJmsbajl().get(0)).getQsrq()
								.trim().length() > 0) {
					//String qsrq=
					// to_date('"+((JmbaZbVO)form.getJmsbajl().get(0)).getQsrq()+"',
					// 'yyyymmdd'),";
					qsrq = DateUtilPro.string2Date(((JmbaZbVO) form
							.getJmsbajl().get(0)).getQsrq());

				}
				Timestamp jzrq = null;
				if (((JmbaZbVO) form.getJmsbajl().get(0)).getJzrq() != null
						&& ((JmbaZbVO) form.getJmsbajl().get(0)).getJzrq()
								.trim().length() > 0) {
					//sql+=" jzrq=
					// to_date('"+((JmbaZbVO)form.getJmsbajl().get(0)).getJzrq()+"',
					// 'yyyymmdd'),";
					jzrq = DateUtilPro.string2Date(((JmbaZbVO) form
							.getJmsbajl().get(0)).getJzrq());
				}

				pst = conn.prepareStatement(sql);
				pst.setString(1, basqwsh);
				pst.setString(2, basqbh);
				pst.setString(3, form.getNsrxx().getJsjdm());
				pst.setString(4, ((JmbaZbVO) form.getJmsbajl().get(0))
						.getJmbasxdm());
				//技术转让类型代码，名称？
				pst.setString(5, ((JmbaZbVO) form.getJmsbajl().get(0))
						.getSzdm());
				////保存操作sqzt=1,提交操作sqzt=3
				pst.setString(6, sqzt);
				pst.setString(7, "0");
				pst.setString(8, ((JmbaZbVO) form.getJmsbajl().get(0))
						.getBand());
				pst.setString(9, ((JmbaZbVO) form.getJmsbajl().get(0))
						.getBajmse());
				pst.setString(10, ((JmbaZbVO) form.getJmsbajl().get(0))
						.getBajmbl());

				pst.setString(11, ((JmbaZbVO) form.getJmsbajl().get(0))
						.getFhwjmc());

				pst.setTimestamp(12, qsrq);
				pst.setTimestamp(13, jzrq);

				pst.setString(14, form.getNsrxx().getJsjdm());
				pst.setString(15, form.getNsrxx().getJsjdm());
				//st.setString(13,((Jmba07Vo)((JmbaZbVO)form.getJmsbajl().get(0)).getQysdsjmba().get(0)).getCjrq());
				pst.setString(16, form.getNsrxx().getJsjdm());
				//st.setString(15,((Jmba07Vo)((JmbaZbVO)form.getJmsbajl().get(0)).getQysdsjmba().get(0)).getLrrq());
				pst.setString(17, ud.getSsdwdm());

			}
			System.out.println("MainProcessor==doSave==sql===" + sql);

			pst.execute();
			pst.clearParameters();

			pst = conn
					.prepareStatement("delete sfdb.sf_jl_qysdsjmsbajlzlqd where basqwsh='"
							+ basqwsh + "'");
			pst.execute();

			//		// 资料子表sql
			List zlqd = ((JmbaZbVO) form.getJmsbajl().get(0)).getBajlzlqd();
			String zlzbsql = "insert into sfdb.sf_jl_qysdsjmsbajlzlqd(BASQWSH,CJR,CJRQ,LRR,LRRQ,XH,ZLQD,swjgzzjgdm,sfkysc,sfshtg) VALUES('"
					+ basqwsh
					+ "','"
					+ vo.getUserData().yhid
					+ "',sysdate,'"
					+ vo.getUserData().yhid + "',sysdate,?,?,?,?,0)";
			pst = conn.prepareStatement(zlzbsql);
			if (zlqd != null && zlqd.size() > 0) {
				for (int i = 0; i < zlqd.size(); i++) {
					//pst.clearParameters();
					String qdxh = qysdsUtil.getSequence();
					((JmbaZlqdVO) zlqd.get(i)).setXh(qdxh);
					pst.setString(1, qdxh);
					pst.setString(2, ((JmbaZlqdVO) zlqd.get(i)).getZlqd());
					pst.setString(3, ud.getSsdwdm());
					pst.setString(4, ((JmbaZlqdVO) zlqd.get(i)).getSfkysc());
					pst.addBatch();
				}
				pst.executeBatch();

			}
            if(pst != null){
                pst.close();
            }
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		} finally {
			DBResource.destroyConnection(conn);
		}
		return form;
	}

}
