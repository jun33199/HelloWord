package com.ttsoft.bjtax.shenbao.nsrjcxxhd.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.frame.util.JspUtil;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.dj.util.DjDBUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.NsrjcxxhdConstant;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Add;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Djjbsj;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Dkdj;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Fzjg;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Qyry;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Swdl;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Tzf;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Zjg;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class NsrjcxxhdProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
		if(NsrjcxxhdConstant.ACTION_QUERY_INIT == vo.getAction()) {
			return this.queryInit(vo);
		} else if(NsrjcxxhdConstant.ACTION_SAVE == vo.getAction()) {
			return this.save(vo);
		}
		return null;
	}
	
	/**
	 * 保存修改数据
	 * @param vo
	 * @return
	 */
	private Object save(VOPackage vo) {
		Map data = (Map) vo.getData();
		Djjbsj djjbsj = (Djjbsj) data.get("djjbsj");
		Qyry cwfzr = (Qyry) data.get("cwfzr");
		Qyry bsr = (Qyry) data.get("bsr");
		Qyry fr = (Qyry) data.get("fr");
		Swdl swdl = (Swdl) data.get("swdl");
		
		boolean deleteSwdlFlag = false;
		if((swdl.getMc() == null || "".equals(swdl.getMc())) && (swdl.getSwdjzh() == null || "".equals(swdl.getSwdjzh())) ) {
			deleteSwdlFlag = true;
		}
		
		String updateJbsj = "update djdb.dj_jl_jbsj set zcdzyb = '" + djjbsj.getZcdzyb() + "', zcdzlxdh = '" + djjbsj.getZcdzlxdh() + "', jydz = '" + djjbsj.getJydz() + "', jydzyb = '" + djjbsj.getJydzyb() + "', jydzlxdm = '" + djjbsj.getJydzlxdm() + "' where jsjdm = '" + djjbsj.getJsjdm() + "'";
		String updateCwfzr = "update djdb.dj_jl_qyry set xm = '" + cwfzr.getXm() + "', zjlxdm = '" + cwfzr.getZjlxdm() + "', zjhm = '" + cwfzr.getZjhm() + "', gddh = '" + cwfzr.getGddh() + "', yddh = '" + cwfzr.getYddh() + "', dzyx = '" + cwfzr.getDzyx() + "' where jsjdm = '" + cwfzr.getJsjdm() + "' and zwdm = '04'";
		String updateBsr = "update djdb.dj_jl_qyry set xm = '" + bsr.getXm() + "', zjlxdm = '" + bsr.getZjlxdm() + "', zjhm = '" + bsr.getZjhm() + "', gddh = '" + bsr.getGddh() + "', yddh = '" + bsr.getYddh() + "', dzyx = '" + bsr.getDzyx() + "' where jsjdm = '" + bsr.getJsjdm() + "' and zwdm = '05'";
		String updateFr = "update djdb.dj_jl_qyry set gddh = '" + fr.getGddh() + "', yddh = '" + fr.getYddh() + "', dzyx = '" + fr.getDzyx() + "' where jsjdm = '" + fr.getJsjdm() + "' and zwdm = '01'";
		String updateSwdl = "update djdb.dj_jl_swdl set mc = '"+ swdl.getMc() +"', swdjzh = '" + swdl.getSwdjzh() + "', dzyx = '" + swdl.getDzyx() + "', gddh = '" + swdl.getGddh() + "' where jsjdm = '" + swdl.getJsjdm() + "'";
		String deleteSwdl = "delete from djdb.dj_jl_swdl where jsjdm = '" + swdl.getJsjdm() + "'";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String msg = "success";
		try {
			conn = DjDBUtil.getConnection();
			pstmt = conn.prepareStatement(updateJbsj);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(updateCwfzr);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(updateBsr);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(updateFr);
			pstmt.executeUpdate();
			if(deleteSwdlFlag) {
				pstmt = conn.prepareStatement(deleteSwdl);
				pstmt.execute();
			} else {
				pstmt = conn.prepareStatement(updateSwdl);
				int updateRow = pstmt.executeUpdate();
				if(updateRow == 0) {
					String sql = "insert into djdb.dj_jl_swdl(mc, swdjzh, dzyx, gddh, jsjdm, swjgzzjgdm, cjrq, lrrq) "
							+ "values('"+swdl.getMc()+"', '" + swdl.getSwdjzh() + "', '" + swdl.getDzyx() + "', '" + swdl.getGddh() + "', '" + swdl.getJsjdm() + "', '" + swdl.getSwjgzzjgdm() + "', sysdate, sysdate)";
					pstmt = conn.prepareStatement(sql);
					pstmt.execute();
				}
			}
		} catch (BaseException e) {
			msg = "failed";
			e.printStackTrace();
		} catch (SQLException e) {
			msg = "failed";
			e.printStackTrace();
		} finally {
			this.freeDB(conn, pstmt, null);
		}
		return msg;
	}
	
	/**
	 * 查询初始化数据
	 * @param vo
	 * @return
	 */
	private Map queryInit(VOPackage vo) {
		Map rtnMap = new HashMap();
		Connection conn = null;
		try {
			conn = DjDBUtil.getConnection();
			Add add = this.queryAdd(vo, conn);
			rtnMap.put("add", add);
			Djjbsj djjbsj = this.queryDjjbsj(vo, conn);
			rtnMap.put("djjbsj", djjbsj);
			List tzfList = this.queryTzf(vo, conn);
			rtnMap.put("tzfList", tzfList);
			List fzjgList = this.queryFzjg(vo, conn);
			rtnMap.put("fzjgList", fzjgList);
			Zjg zjg = this.queryZjg(vo, conn);
			rtnMap.put("zjg", zjg);
			List qyryList = this.queryQyry(vo, conn);
			rtnMap.put("qyryList", qyryList);
			Swdl swdl = this.querySwdl(vo, conn);
			rtnMap.put("swdl", swdl);
			List dkdjList = this.queryDkdj(vo, conn);
			rtnMap.put("dkdjList", dkdjList);
		} catch (BaseException e) {
			e.printStackTrace();
		} finally {
			this.freeDB(conn, null, null);
		}
		return rtnMap;
	}
	
	/**
	 * 根据计算机代码查询ADD
	 * @param vo
	 * @return
	 */
	private Add queryAdd(VOPackage vo, Connection conn) {
		String jsjdm = (String) vo.getData();
		Add add = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from djdb.dj_jl_add where jsjdm='"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					add = new Add();
					add.setCjrq(rs.getDate("cjrq"));
					add.setCyrs(rs.getString("cyrs"));
					add.setDwxz(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.DWXZ,rs.getString("dwxz"))));
					add.setFzrq(rs.getDate("fzrq"));
					add.setGdgrs(rs.getString("gdgrs"));
					add.setGgrs(rs.getString("ggrs"));
					add.setGjbzhydm_one(rs.getString("gjbzhydm_one"));
					add.setGjbzhydm_three(rs.getString("gjbzhydm_three"));
					add.setGjbzhydm_two(rs.getString("gjbzhydm_two"));
					add.setGjhdq(rs.getString("gjhdq"));
					add.setGytzbl(rs.getDouble("gytzbl"));
					add.setGytzbl(rs.getDouble("gytzbl"));
					add.setHhr(rs.getString("hhr"));
					add.setJsjdm(rs.getString("jsjdm"));
					add.setKyslrq(rs.getDate("kyslrq"));
					add.setLsswdjyxq(rs.getString("lsswdjyxq"));
					add.setLsswdjyxq(rs.getString("lsswdjyxq"));
					add.setNsrlx(rs.getString("nsrlx"));
					add.setPzcljgdm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.PZCLJG,rs.getString("pzcljgdm"))));
					add.setWjrs(rs.getString("wjrs"));
					add.setXcjyqx_begin(rs.getDate("xcjyqx_begin"));
					add.setXcjyqx_end(rs.getDate("xcjyqx_end"));
					add.setYhkhdjzh(rs.getString("yhkhdjzh"));
					add.setZcbbzdm_three(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.BZ,rs.getString("zcbbzdm_three"))));
					add.setZcbbzdm_two(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.BZ,rs.getString("zcbbzdm_two"))));
					add.setZczbbzdm_one(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.BZ,rs.getString("zczbbzdm_one"))));
					add.setZczbje_one(rs.getDouble("zczbje_one"));
					add.setZczbje_two(rs.getDouble("zczbje_two"));
					add.setZczbje_three(rs.getDouble("zczbje_three"));
					add.setZjhm(rs.getString("zjhm"));
					add.setZjmc(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,rs.getString("zjmc"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.freeDB(null, stmt, rs);
			}
		return add;
	}

	/**
	 * 根据计算机代码查询登记基本信息
	 * @param vo
	 * @return
	 */
	private Djjbsj queryDjjbsj(VOPackage vo, Connection conn) {
		String jsjdm = (String) vo.getData();
		Djjbsj djjbsj = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from djdb.dj_jl_jbsj where jsjdm='"+jsjdm+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				djjbsj = new Djjbsj();
				djjbsj.setCjsj(rs.getDate("cjsj"));
				djjbsj.setDjsllx(rs.getString("djsllx"));
				
				djjbsj.setDjzclxdm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.DJZCLX,rs.getString("djzclxdm"))));
				//djjbsj.setDjzclxmc(rs.getString("djzclxmc"));
				djjbsj.setDjzzldm(rs.getString("djzzldm"));
				djjbsj.setGdsgghbs(rs.getLong("gdsgghbs"));
				djjbsj.setGjbzhydm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.GJBZHY,rs.getString("gjbzhydm"))));
				//djjbsj.setGjbzhymc(rs.getString("gjbzhymc"));
				djjbsj.setHsxsdm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.HSYS,rs.getString("hsxsdm"))));
				djjbsj.setHzfhrq(rs.getDate("hzfhrq"));
				djjbsj.setHzfhry(rs.getString("hzfhry"));
				djjbsj.setJsjdm(rs.getString("jsjdm"));
				djjbsj.setJydz(rs.getString("jydz"));
				djjbsj.setJydzlxdm(rs.getString("jydzlxdm"));
				djjbsj.setJydzyb(rs.getString("jydzyb"));
				djjbsj.setJyfw(rs.getString("jyfw"));
				djjbsj.setKjzddm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.KJZD,rs.getString("kjzddm"))));
				djjbsj.setKydjrq(rs.getDate("kydjrq"));
				djjbsj.setLsgxdm(rs.getString("lsgxdm"));
//				djjbsj.setLsgxmc(rs.getString("lsgxmc"));
				djjbsj.setNsrmc(rs.getString("nsrmc"));
				djjbsj.setNsrzt(rs.getString("nsrzt"));
				djjbsj.setQrrq(rs.getDate("qrrq"));
				djjbsj.setQrry(rs.getString("qrry"));
				djjbsj.setQyzy(rs.getString("qyzy"));
				djjbsj.setScbs(rs.getString("scbs"));
				djjbsj.setScjxdm(rs.getString("scjxdm"));
				djjbsj.setSfzjhm(rs.getString("sfzjhm"));
				djjbsj.setSlrq(rs.getDate("slrq"));
				djjbsj.setSlry(rs.getString("slry"));
				djjbsj.setSwdjlx(rs.getString("swdjlx"));
				djjbsj.setSwdjzh(rs.getString("swdjzh"));
				djjbsj.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
//				djjbsj.setSwjgzzjgmc(rs.getString("swjgzzjgmc"));
				djjbsj.setWzztzblhj(rs.getDouble("wzztzblhj"));
				djjbsj.setXgsj(rs.getDate("xgsj"));
				djjbsj.setYhzrq(rs.getDate("yhzrq"));
				djjbsj.setYhzry(rs.getString("yhzry"));
				djjbsj.setYyzzh(rs.getString("yyzzh"));
				djjbsj.setZcdz(rs.getString("zcdz"));
				djjbsj.setZcdzlxdh(rs.getString("zcdzlxdh"));
				djjbsj.setZcdzyb(rs.getString("zcdzyb"));
				djjbsj.setZczbbzdm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.BZ,rs.getString("zczbbzdm"))));
				djjbsj.setZczbje(rs.getLong("zczbje"));
				djjbsj.setZgbmdm(rs.getString("zgbmdm"));
				djjbsj.setZrrtzblhj(rs.getDouble("zrrtzblhj"));
				djjbsj.setZzjgdm(rs.getString("zzjgdm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			freeDB(null, stmt, rs);
		}
		
		return djjbsj;
	}
	
	/**
	 * 根据计算机代码查询投资方
	 * @param vo
	 * @return
	 */
	private List queryTzf(VOPackage vo, Connection conn) {
		List tzfList = new ArrayList();
		String jsjdm = (String) vo.getData();
		Tzf tzf = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select t.*, t.rowid from djdb.dj_jl_tzf t where jsjdm = '"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					tzf = new Tzf();
					tzf.setCjrq(rs.getDate("cjrq"));
					tzf.setFpbl(rs.getDouble("fpbl"));
					tzf.setGjdz(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.GJ,rs.getString("gjdz"))));
					tzf.setJsjdm(rs.getString("jsjdm"));
					tzf.setLrrq(rs.getDate("lrrq"));
					tzf.setQxdm(rs.getString("qxdm"));
					tzf.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
					tzf.setTzbl(rs.getDouble("tzbl"));
					tzf.setTzfmc(rs.getString("tzfmc"));
					tzf.setTzje(rs.getDouble("tzje"));
					tzf.setXh(rs.getInt("xh"));
					tzf.setZjhm(rs.getString("zjhm"));
					tzf.setZjlxdm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,rs.getString("zjlxdm"))));
					
					String tzfjjxz = rs.getString("tzfjjxz");
					if(tzfjjxz != null && !"".equals(tzfjjxz)){
						if(tzfjjxz.equals("510")){
							tzf.setTzfjjxz("自然人");
						}else{
							tzf.setTzfjjxz(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.DJZCLX,tzfjjxz)));
						}
					} else {
						tzf.setTzfjjxz("");
					}
					
					tzfList.add(tzf);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				freeDB(null, stmt, rs);
			}
		return tzfList;
	}
	
	/**
	 * 根据计算机代码查询代扣代缴
	 * @param vo
	 * @return
	 */
	private List queryDkdj(VOPackage vo, Connection conn) {
		List dkdjList = new ArrayList();
		String jsjdm = (String) vo.getData();
		Dkdj dkdj = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select t.*, t.rowid from djdb.dj_jl_dkdj t where jsjdm = '"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					dkdj = new Dkdj();
					dkdj.setCjrq(rs.getDate("cjrq"));
					dkdj.setDkdjsz(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.SZ,rs.getString("dkdjsz"))));
					dkdj.setDkdjyw(rs.getString("dkdjyw"));
					dkdj.setJsjdm(rs.getString("jsjdm"));
					dkdjList.add(dkdj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				freeDB(null, stmt, rs);
			}
		return dkdjList;
	}
	
	/**
	 * 根据计算机代码查询企业人员
	 * @param vo
	 * @return
	 */
	private List queryQyry(VOPackage vo, Connection conn) {
		List qyryList = new ArrayList();
		String jsjdm = (String) vo.getData();
		Qyry qyry = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select t.*, t.rowid from djdb.dj_jl_qyry t where jsjdm = '"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					qyry = new Qyry();
					qyry.setDzyx(rs.getString("dzyx"));
					qyry.setGddh(rs.getString("gddh"));
					qyry.setGjdz(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.GJ,rs.getString("gjdz"))));
					qyry.setJsjdm(rs.getString("jsjdm"));
					qyry.setXm(rs.getString("xm"));
					qyry.setYddh(rs.getString("yddh"));
					qyry.setZjhm(rs.getString("zjhm"));
					qyry.setZjlxdm(JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,rs.getString("zjlxdm"))));
					qyry.setZwdm(rs.getString("zwdm"));
					
					qyryList.add(qyry);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				freeDB(null, stmt, rs);
			}
		return qyryList;
	}
	
	/**
	 * 根据计算机代码查询总机构
	 * @param vo
	 * @return
	 */
	private Zjg queryZjg(VOPackage vo, Connection conn) {
		String jsjdm = (String) vo.getData();
		Zjg zjg = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from djdb.dj_jl_zjg where jsjdm='"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					zjg = new Zjg();
					zjg.setCjrq(rs.getDate("cjrq"));
					zjg.setFrdbxm(rs.getString("frdbxm"));
					zjg.setFrdbzjhm(rs.getString("frdbzjhm"));
					zjg.setFrdbzjlx(rs.getString("frdbzjlx"));
					zjg.setJsjdm(rs.getString("jsjdm"));
					zjg.setLrrq(rs.getDate("lrrq"));
					zjg.setNsrmc(rs.getString("nsrmc"));
					zjg.setScjydz(rs.getString("scjydz"));
					zjg.setScjydzlxdh(rs.getString("scjydzlxdh"));
					zjg.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
					zjg.setZcdzyb(rs.getString("zcdzyb"));
					zjg.setZjgjsjdm(rs.getString("zjgjsjdm"));
					zjg.setZjgjyfw(rs.getString("zjgjyfw"));
					zjg.setZjgswdjzh(rs.getString("zjgswdjzh"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				freeDB(null, stmt, rs);
			}
		return zjg;
	}
	
	/**
	 * 根据计算机代码查询税务代理
	 * @param vo
	 * @return
	 */
	private Swdl querySwdl(VOPackage vo, Connection conn) {
		String jsjdm = (String) vo.getData();
		Swdl swdl = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from djdb.dj_jl_swdl where jsjdm='"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					swdl = new Swdl();
					swdl.setDzyx(rs.getString("dzyx"));
					swdl.setGddh(rs.getString("gddh"));
					swdl.setJsjdm(rs.getString("jsjdm"));
					swdl.setMc(rs.getString("mc"));
					swdl.setSwdjzh(rs.getString("swdjzh"));
					swdl.setSwdlyddh(rs.getString("swdlyddh"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				freeDB(null, stmt, rs);
			}
		return swdl;
	}
	
	/**
	 * 根据计算机代码查询分支机构
	 * @param vo
	 * @return
	 */
	private List queryFzjg(VOPackage vo, Connection conn) {
		List fzjgList = new ArrayList();
		String jsjdm = (String) vo.getData();
		Fzjg fzjg = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from djdb.dj_jl_fzjg where jsjdm='"+jsjdm+"'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					fzjg = new Fzjg();
					fzjg.setCjrq(rs.getDate("cjrq"));
					fzjg.setFzdh(rs.getString("fzdh"));
					fzjg.setFzjgswdjzh(rs.getString("fzjgswdjzh"));
					fzjg.setJsjdm(rs.getString("jsjdm"));
					fzjg.setLrrq(rs.getDate("lrrq"));
					fzjg.setNsrmc(rs.getString("nsrmc"));
					fzjg.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
					fzjg.setZcdz(rs.getString("zcdz"));
					
					fzjgList.add(fzjg);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				freeDB(null, stmt, rs);
			}
		return fzjgList;
	}

	/**
	 * 关闭数据库相关资源
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	private void freeDB(Connection conn, Statement stmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
