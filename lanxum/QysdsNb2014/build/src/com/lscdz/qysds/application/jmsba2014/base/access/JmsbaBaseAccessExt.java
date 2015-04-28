package com.lscdz.qysds.application.jmsba2014.base.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.util.QysdsHelperUtil;

public class JmsbaBaseAccessExt {
	/**
	 * 保存主表数据和资料清单
	 */
	public boolean doSave(Connection conn, JmsbaBaseVo baseVo, Yh ud)
			throws FrameException {
		boolean jmsjlSaved = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// 备案申请文书号
		String basqwsh = baseVo.getBasqwsh();
		// 备案申请编号
		String basqbh = baseVo.getBasqbh();
		// 计算机代码
		String jsjdm = baseVo.getJsjdm();
		// 备案年度
		String band = baseVo.getBand();
		// 减免税类别代码
		String jmbasxdm = baseVo.getJmbasxdm();
		// 减免税政策执行情况
		// String
		// jmszczxqk=QysdsUtil.strNotNull(reqVo.getJmszczxqk())?reqVo.getJmszczxqk():"";
		String jmszczxqk = "";
		// String sftjsm=reqVo.getJmsbaBaseData().getSftjsm();
		String sftjsm = "";
		// 起始时间
		// String
		// qsrq=QysdsUtil.strNotNull(reqVo.getQsrq())?"to_date('"+reqVo.getQsrq()+"','yyyy-mm-dd')":"null";
		String qsrq = "null";
		// 截止时间
		// String
		// jzrq=QysdsUtil.strNotNull(reqVo.getJzrq())?"to_date('"+reqVo.getJzrq()+"','yyyy-mm-dd')":"null";
		String jzrq = "null";
		// 设置减免税额
		// String bajmse =
		// QysdsUtil.strNotNull(reqVo.getBajmse())?reqVo.getBajmse():"";
		// String bajmbl =
		// QysdsUtil.strNotNull(reqVo.getBajmbl())?reqVo.getBajmbl():"";
		String bajmse = "";
		String bajmbl = "";

		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			String zbsql = "";

			// 插入主表
			sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ,"
					+ "SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"
					+ basqwsh
					+ "','"
					+ basqbh
					+ "','"
					+ jsjdm
					+ "','"
					+ band
					+ "','"
					+ jmbasxdm
					+ "','30','"
					+ ud.getSsdwdm()
					+ "','4','"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,'1','"
					+ bajmse
					+ "','"
					+ bajmbl
					+ "',?,"
					+ qsrq
					+ ","
					+ jzrq
					+ ",'"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',to_timestamp('"
					+ time
					+ "','yyyy-mm-dd hh24:mi:ss.ff'))";

			// 主表操作

			ps = conn.prepareStatement(sql);
			ps.setString(1, jmszczxqk);
			ps.executeQuery();

			// String zl=reqVo.getZl();
			String zl = "清单1;清单2";
			// 更新资料清单表
			if (!"".equals(zl)) {
				// 删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// 插入该备案申请文书号的资料清单
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zlqd = zl.split(";");
				ps2 = conn.prepareStatement(zlqdsql);
				for (int i = 0; i < zlqd.length; i++) {
					String zlnr = zlqd[i].substring(0, zlqd[i].indexOf("|"));
					String zlsfkysc = zlqd[i].substring(zlqd[i].length() - 1,
							zlqd[i].length());

					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, "1");
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlSaved = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FrameException("数据库保存记录失败！" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlSaved;
	}
	/**
	 * 变更主表申请状态
	 */
	public boolean doChangeSqzt(Connection conn, JmsbaBaseVo baseVo)throws FrameException {	
		Statement st=null;
		ResultSet rs=null;
		String sql="select BASQWSH from SFDB.SF_JL_QYSDSJMSBAJL where BASQWSH='"+baseVo.getBasqwsh()+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()){
				sql="update SFDB.SF_JL_QYSDSJMSBAJL   set SQZT='"+baseVo.getSqzt()+"' where BASQWSH='"+baseVo.getBasqwsh()+"'";
				return st.execute(sql);
			}else{
				throw new FrameException("该记录不存在，无法更新申请状态");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("更新申请状态失败！"+e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, null);
		}	
	}
	
	/**
	 * 变更资料清单审核是否通过状态，审核是否通过，审核时有无此资料，0未审核，1审核时有，2审核时无
	 */
	public boolean doChangeSfshtg(Connection conn, JmsbaBaseVo baseVo)throws FrameException {	
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="select Xh,BASQWSH from SFDB.SF_JL_QYSDSJMSBAJLZLQD where BASQWSH='"+baseVo.getBasqwsh()+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				ps=conn.prepareStatement("update SFDB.SF_JL_QYSDSJMSBAJL   set SFSHTG=? where BASQWSH=? and XH=?");
			}else{
				throw new FrameException("该记录不存在，无法更改资料清单审核是否通过状态");
			}
			while(rs.next()){
				ps.setString(1,"0");//通过
				ps.setString(2, rs.getString("BASQWSH"));
				ps.setString(3, rs.getString("XH"));
				ps.addBatch(sql);
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("更新资料清单审核是否通过状态失败！"+e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, null);
			QysdsHelperUtil.dbResClose(ps, null, null);
		}
		return true;	
	}
}
