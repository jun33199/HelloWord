package com.lscdz.qysds.application.jmsba2014.base.access;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.Jmsba2014Contant;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaZlqdVo;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class JmsbaBaseAccessExt {
	private  final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 用于上门直接保存主表数据和资料清单
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doSave(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
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
		// 报表期类型,0-月度,1-季度,2-年度
		String bbqlx = baseVo.getBbqlx();
		// 备案方式,1-预缴备案,2-汇缴备案
		String bafs = baseVo.getBafs();
		// 期号 根据BBLX区分不同期的申报,例如,BBQLX=2则期号开始为1
		String qh = baseVo.getQh();
		// 享受优惠期间起
		//Timestamp xsyhqjq = baseVo.getXsyhqjq();
		String xsyhqjq = baseVo.getXsyhqjq();
		// 享受优惠期间止
		//Timestamp xsyhqjz = baseVo.getXsyhqjz();
		String xsyhqjz = baseVo.getXsyhqjz();
		// 税款所属时期起
		String sksssqq = baseVo.getSksssqq();
		// 税款所属时期止
		String sksssqz = baseVo.getSksssqz();		
		// 减免税类别代码		
		String jmbasxdm = baseVo.getJmbasxdm();
		// 主要政策依据文件及文号
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// 具有相关资格的批准文件（证书）及文号（编号）
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//有关情况说明
		String ygqksm=baseVo.getYgqksm();
		/*//文件（证书）有效期起始日期
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//文件（证书）有效期截止日期
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//备案减免税额
		BigDecimal bajmse = baseVo.getBajmse();
		//备案减免比例
		BigDecimal bajmbl = baseVo.getBajmbl();

		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			String sqzt="";
			//针对农、林、牧、渔业 设置特殊状态
			if(baseVo.getJmbasxdm().equals(Jmsba2014Contant.QYSDS_JMSBA_BASX_0120)){
				sqzt=Jmsba2014Contant.QYSDS_JMSBA_SQZT_TSZD_CODE;
			}else{
				sqzt=Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE;
			}
			// 插入主表
			sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL_2014(BASQWSH,BASQBH,JSJDM,BAND,BBQLX,BAFS,QH,XSYHQJQ,XSYHQJZ,SKSSSQQ,SKSSSQZ,JMBASXDM,SZDM,SWJGZZJGDM,ZYZCYJWJJWH,JYXGZGDPZWJJWH,"
					+"SQZT,TJR,TJSJ,SHR,SHSJ,SQLXDM,YGQKSM,WJYXQQSRQ,WJYXQJZRQ,CJR,CJRQ,LRR,LRRQ,BAJMSE,BAJMBL)values('"
					+ basqwsh+ "','"
					+ basqbh+ "','"
					+ jsjdm+ "','"
					+ band+ "','"
					+ bbqlx+ "','"
					+ bafs+ "','"
					+ qh+ "',"
					+ "?,"    
					+ "?"
					+ ",to_timestamp('"+ sksssqq+ "','yyyy-mm-dd')"
					+ ",to_timestamp('"+ sksssqz+ "','yyyy-mm-dd')"+ ",'"					
					+ jmbasxdm
					+ "','30','"
					+ ud.getSsdwdm()+"','"
					+ zyzcyjwjjwh+"','"
					+ jyxgzgdpzwjjwh
					+ "','"+sqzt+"','"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,'1','"
					+ ygqksm+"',"
					+ "?,"
					+ "?,'"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,"
					+ "?,"
					+ "?)";

			// 主表操作

			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
			if(wjyxqqsrq==null){
				ps.setTimestamp(3,null);
			}else{
				ps.setTimestamp(3, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(4,null);
			}else{
				ps.setTimestamp(4, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setBigDecimal(5, bajmse);
			ps.setBigDecimal(6, bajmbl);
			ps.executeQuery();

			// 更新资料清单表
			if (baseVo.getZlqdList().size()!=0) {
				// 删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// 插入该备案申请文书号的资料清单
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, zlqdVo.getSfshtg());
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlSaved = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
	 * 更新主表数据和资料清单
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doUpdate(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
		boolean jmsjlUpdateed = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// 备案申请文书号
		String basqwsh = baseVo.getBasqwsh();
		// 计算机代码
		String jsjdm = baseVo.getJsjdm();
		// 享受优惠期间起
		String xsyhqjq = baseVo.getXsyhqjq();
		// 享受优惠期间止
		String xsyhqjz = baseVo.getXsyhqjz();
		// 主要政策依据文件及文号
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// 具有相关资格的批准文件（证书）及文号（编号）
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//有关情况说明
		String ygqksm=baseVo.getYgqksm();
		/*//文件（证书）有效期起始日期
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//文件（证书）有效期截止日期
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//备案减免税额
		BigDecimal bajmse = baseVo.getBajmse();
		//备案减免比例
		BigDecimal bajmbl = baseVo.getBajmbl();
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			//更新主表数据
			sql="update SFDB.SF_JL_QYSDSJMSBAJL_2014 set XSYHQJQ=?,XSYHQJZ=?,ZYZCYJWJJWH=?,JYXGZGDPZWJJWH=?,YGQKSM=?,WJYXQQSRQ=?,WJYXQJZRQ=?,LRR=?,LRRQ=sysdate,BAJMSE=?,BAJMBL=?,sqzt=?,shr=?,shsj=? where BASQWSH=?";
			// 主表操作

			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
			
			ps.setString(3, zyzcyjwjjwh);
			ps.setString(4, jyxgzgdpzwjjwh);
			ps.setString(5, ygqksm);
			if(wjyxqqsrq==null){
				ps.setTimestamp(6,null);
			}else{
				ps.setTimestamp(6, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(7,null);
			}else{
				ps.setTimestamp(7, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setString(8, ud.getYhid());
			ps.setBigDecimal(9, bajmse);
			ps.setBigDecimal(10, bajmbl);
			ps.setString(11, baseVo.getSqzt());
			if(baseVo.getSqzt().equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE)){
				ps.setString(12, ud.getYhid());//审核人
				ps.setTimestamp(13, FrameCommonAccess.getDBDate());//审核日期
			}else{
				ps.setString(12, "");
				ps.setTimestamp(13, null);
			}
			ps.setString(14, basqwsh);
			ps.executeUpdate();

			// 更新资料清单表
			if (baseVo.getZlqdList().size()!=0) {
				// 删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// 插入该备案申请文书号的资料清单
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, zlqdVo.getSfshtg());
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlUpdateed = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("数据库更新记录失败！" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlUpdateed;
	}
	
	
	/**
	 * 用于网上临时保存主表数据和资料清单
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doSaveWs(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
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
		// 报表期类型,0-月度,1-季度,2-年度
		String bbqlx = baseVo.getBbqlx();
		// 备案方式,1-预缴备案,2-汇缴备案
		String bafs = baseVo.getBafs();
		// 期号 根据BBLX区分不同期的申报,例如,BBQLX=2则期号开始为1
		String qh = baseVo.getQh();
		// 享受优惠期间起
		String xsyhqjq = baseVo.getXsyhqjq();
		// 享受优惠期间止
		String xsyhqjz = baseVo.getXsyhqjz();
		// 税款所属时期起
		String sksssqq = baseVo.getSksssqq();
		// 税款所属时期止
		String sksssqz = baseVo.getSksssqz();		
		// 减免税类别代码		
		String jmbasxdm = baseVo.getJmbasxdm();
		// 主要政策依据文件及文号
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// 具有相关资格的批准文件（证书）及文号（编号）
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//有关情况说明
		String ygqksm=baseVo.getYgqksm();
		/*//文件（证书）有效期起始日期
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//文件（证书）有效期截止日期
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//备案减免税额
		BigDecimal bajmse = baseVo.getBajmse();
		//备案减免比例
		BigDecimal bajmbl = baseVo.getBajmbl();

		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";

			// 插入主表
			sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL_2014(BASQWSH,BASQBH,JSJDM,BAND,BBQLX,BAFS,QH,XSYHQJQ,XSYHQJZ,SKSSSQQ,SKSSSQZ,JMBASXDM,SZDM,SWJGZZJGDM,ZYZCYJWJJWH,JYXGZGDPZWJJWH,"
					+"SQZT,TJR,TJSJ,SHR,SHSJ,SQLXDM,YGQKSM,WJYXQQSRQ,WJYXQJZRQ,CJR,CJRQ,LRR,LRRQ,BAJMSE,BAJMBL)values('"
					+ basqwsh+ "','"
					+ basqbh+ "','"
					+ jsjdm+ "','"
					+ band+ "','"
					+ bbqlx+ "','"
					+ bafs+ "','"
					+ qh+ "',"
					+ "?,"    
					+ "?"
					+ ",to_timestamp('"+ sksssqq+ "','yyyy-mm-dd')"
					+ ",to_timestamp('"+ sksssqz+ "','yyyy-mm-dd')"+ ",'"					
					+ jmbasxdm
					+ "','30','"
					+ ud.getSsdwdm()+"','"
					+ zyzcyjwjjwh+"','"
					+ jyxgzgdpzwjjwh
					+ "','"+baseVo.getSqzt()+"','"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,'"+baseVo.getSqlxdm()+"','"
					+ ygqksm+"',"
					+ "?,"
					+ "?,'"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,"
					+ "?,"
					+ "?)";

			// 主表操作

			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
			if(wjyxqqsrq==null){
				ps.setTimestamp(3,null);
			}else{
				ps.setTimestamp(3, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(4,null);
			}else{
				ps.setTimestamp(4, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setBigDecimal(5, bajmse);
			ps.setBigDecimal(6, bajmbl);
			ps.executeQuery();

			// 更新资料清单表
			if (baseVo.getZlqdList().size()!=0) {
				// 删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// 插入该备案申请文书号的资料清单
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, "0");
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlSaved = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
	 * 用于网上修改已保存为提交的主表数据和资料清单
	 * 上门修改网上已提交未审核的数据
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doUpdateWs(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
		boolean jmsjlUpdateed = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// 备案申请文书号
		String basqwsh = baseVo.getBasqwsh();
		// 计算机代码
		String jsjdm = baseVo.getJsjdm();
		// 享受优惠期间起
		String xsyhqjq = baseVo.getXsyhqjq();
		// 享受优惠期间止
		String xsyhqjz = baseVo.getXsyhqjz();
		// 主要政策依据文件及文号
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// 具有相关资格的批准文件（证书）及文号（编号）
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//有关情况说明
		String ygqksm=baseVo.getYgqksm();
		/*//文件（证书）有效期起始日期
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//文件（证书）有效期截止日期
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//备案减免税额
		BigDecimal bajmse = baseVo.getBajmse();
		//备案减免比例
		BigDecimal bajmbl = baseVo.getBajmbl();
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			//更新主表数据
			sql="update SFDB.SF_JL_QYSDSJMSBAJL_2014 set XSYHQJQ=?,XSYHQJZ=?,ZYZCYJWJJWH=?,JYXGZGDPZWJJWH=?,YGQKSM=?,WJYXQQSRQ=?,WJYXQJZRQ=?,LRR=?,LRRQ=sysdate,BAJMSE=?,BAJMBL=?,sqzt=?,shr=?,shsj=?,SQLXDM=? where BASQWSH=?";
			// 主表操作
			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
		
			ps.setString(3, zyzcyjwjjwh);
			ps.setString(4, jyxgzgdpzwjjwh);
			ps.setString(5, ygqksm);
			if(wjyxqqsrq==null){
				ps.setTimestamp(6,null);
			}else{
				ps.setTimestamp(6, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(7,null);
			}else{
				ps.setTimestamp(7, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setString(8, ud.getYhid());
			ps.setBigDecimal(9, bajmse);
			ps.setBigDecimal(10, bajmbl);
			ps.setString(11, baseVo.getSqzt());
			
			if(baseVo.getSqzt().equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE)){
				ps.setString(12, ud.getYhid());//审核人
				ps.setTimestamp(13, FrameCommonAccess.getDBDate());//审核日期
			}else{
				ps.setString(12, "");
				ps.setTimestamp(13, null);
			}
			ps.setString(14, baseVo.getSqlxdm());
			ps.setString(15, basqwsh);
			ps.executeUpdate();

			// 更新资料清单表
			if (baseVo.getZlqdList().size()!=0) {
				// 删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// 插入该备案申请文书号的资料清单
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, "0");
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlUpdateed = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("数据库更新记录失败！" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlUpdateed;
	}
	
	/**
	 * 变更主表申请状态
	 * @param conn
	 * @param baseVo
	 * @return
	 * @throws FrameException
	 */
	public boolean doChangeSqzt(Connection conn, JmsbaBaseVo baseVo)
			throws FrameException {
		Statement st = null;
		ResultSet rs = null;
		String sql = "select BASQWSH from SFDB.SF_JL_QYSDSJMSBAJL_2014 where BASQWSH='"
				+ baseVo.getBasqwsh() + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL_2014   set SQZT='"
						+ baseVo.getSqzt() + "' where BASQWSH='"
						+ baseVo.getBasqwsh() + "'";
				return st.execute(sql);
			} else {
				throw new FrameException("该记录不存在，无法更新申请状态");
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("更新申请状态失败！" + e.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(st, rs, null);
		}
	}

	/**
	 * 变更资料清单审核是否通过状态，审核是否通过，审核时有无此资料，0未审核，1审核时有，2审核时无
	 */
	public boolean doChangeSfshtg(Connection conn, JmsbaBaseVo baseVo)
			throws FrameException {
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select Xh,BASQWSH from SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 where BASQWSH='"
				+ baseVo.getBasqwsh() + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				ps = conn.prepareStatement("update SFDB.SF_JL_QYSDSJMSBAJL_2014   set SFSHTG=? where BASQWSH=? and XH=?");
			} else {
				throw new FrameException("该记录不存在，无法更改资料清单审核是否通过状态");
			}
			while (rs.next()) {
				ps.setString(1, "0");// 通过
				ps.setString(2, rs.getString("BASQWSH"));
				ps.setString(3, rs.getString("XH"));
				ps.addBatch(sql);
			}
			ps.executeBatch();
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("更新资料清单审核是否通过状态失败！" + e.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(st, rs, null);
			QysdsHelperUtil.dbResClose(ps, null, null);
		}
		return true;
	}
	/**
	 * 根据备案申请文书号查询主表和资料清单中的所有全部数据
	 * @param conn
	 * @param basqwsh
	 * @return
	 * @throws FrameException 
	 */
	public JmsbaBaseVo doQueryJmsbaxx(Connection conn,String basqwsh) throws FrameException{
		JmsbaBaseVo jmsbaBaseVo=null;
		//查询主表数据
		jmsbaBaseVo=doQueryJmsbajl(conn,basqwsh);
		if(jmsbaBaseVo!=null){
			//查询资料清单中数据
			jmsbaBaseVo.setZlqdList(doQueryZlqd(conn,basqwsh));
		}
		return jmsbaBaseVo;
	}
	/**
	 * 根据备案申请文书号查询主表数据
	 * @param conn
	 * @param basqwsh
	 * @return
	 * @throws FrameException 
	 */
	private JmsbaBaseVo doQueryJmsbajl(Connection conn,String basqwsh) throws FrameException{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from SFDB.SF_JL_QYSDSJMSBAJL_2014 where basqwsh=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, basqwsh);
			rs=ps.executeQuery();
			if(rs.next()){
				JmsbaBaseVo jmsbaBaseVo=new JmsbaBaseVo();
				jmsbaBaseVo.setBafs(rs.getString("BAFS"));
				jmsbaBaseVo.setBand(rs.getString("BAND"));
				jmsbaBaseVo.setBasqbh(rs.getString("BASQBH"));
				jmsbaBaseVo.setBasqwsh(rs.getString("BASQWSH"));
				jmsbaBaseVo.setBbqlx(rs.getString("BBQLX"));
				jmsbaBaseVo.setCjr(rs.getString("CJR"));
				jmsbaBaseVo.setCjrq(rs.getTimestamp("CJRQ"));
				jmsbaBaseVo.setJmbasxdm(rs.getString("JMBASXDM"));
				jmsbaBaseVo.setJmbasxmc(CodeTableManager.getNameByCode(CodeTableKey.SF_DM_JMBASXDM_2014, rs.getString("JMBASXDM")));
				jmsbaBaseVo.setJsjdm(rs.getString("JSJDM"));
				jmsbaBaseVo.setJyxgzgdpzwjjwh(rs.getString("JYXGZGDPZWJJWH"));
				jmsbaBaseVo.setLrr(rs.getString("LRR"));
				jmsbaBaseVo.setLrrq(rs.getTimestamp("LRRQ"));
				jmsbaBaseVo.setQh(rs.getString("QH"));
				jmsbaBaseVo.setSksssqq(rs.getTimestamp("SKSSSQQ")==null?"":timeFormat.format(rs.getTimestamp("SKSSSQQ")));
				jmsbaBaseVo.setSksssqz(rs.getTimestamp("SKSSSQZ")==null?"":timeFormat.format(rs.getTimestamp("SKSSSQZ")));
				jmsbaBaseVo.setSqlxdm(rs.getString("SQLXDM"));
				jmsbaBaseVo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				jmsbaBaseVo.setSzdm(rs.getString("SZDM"));
				
				//jmsbaBaseVo.setXsyhqjq(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("XSYHQJQ")));
				jmsbaBaseVo.setXsyhqjq(rs.getTimestamp("XSYHQJQ")==null?"":timeFormat.format(rs.getTimestamp("XSYHQJQ")));
				//jmsbaBaseVo.setXsyhqjz(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("XSYHQJZ")));
				jmsbaBaseVo.setXsyhqjz(rs.getTimestamp("XSYHQJZ")==null?"":timeFormat.format(rs.getTimestamp("XSYHQJZ")));
				jmsbaBaseVo.setZyzcyjwjjwh(rs.getString("ZYZCYJWJJWH"));
				jmsbaBaseVo.setBajmbl(rs.getBigDecimal("BAJMBL"));
				jmsbaBaseVo.setBajmse(rs.getBigDecimal("BAJMSE"));
				jmsbaBaseVo.setHtr(rs.getString("HTR"));
				jmsbaBaseVo.setHtrq(rs.getTimestamp("HTRQ"));
				jmsbaBaseVo.setShr(rs.getString("SHR"));
				jmsbaBaseVo.setShsj(rs.getTimestamp("SHSJ"));
				jmsbaBaseVo.setSqzt(rs.getString("SQZT"));
				jmsbaBaseVo.setTjr(rs.getString("TJR"));
				jmsbaBaseVo.setTjsj(rs.getTimestamp("TJSJ"));

				//jmsbaBaseVo.setWjyxqjzrq(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("WJYXQJZRQ")));
				//jmsbaBaseVo.setWjyxqqsrq(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("WJYXQQSRQ")));
				jmsbaBaseVo.setWjyxqjzrq(rs.getTimestamp("WJYXQJZRQ")==null?"":timeFormat.format(rs.getTimestamp("WJYXQJZRQ")));
				jmsbaBaseVo.setWjyxqqsrq(rs.getTimestamp("WJYXQQSRQ")==null?"":timeFormat.format(rs.getTimestamp("WJYXQQSRQ")));
				jmsbaBaseVo.setYgqksm(rs.getString("YGQKSM"));
				jmsbaBaseVo.setZfr(rs.getString("ZFR"));
				jmsbaBaseVo.setZfrq(rs.getTimestamp("ZFRQ"));
				jmsbaBaseVo.setZfsm(rs.getString("ZFSM"));
				return jmsbaBaseVo;
				
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询减免税备案主表数据失败！" + e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, null);
		}		
		return null;
	}
	/**
	 * 根据备案申请文书号查询资料清单中数据
	 * @param conn
	 * @param basqwsh
	 * @return
	 * @throws FrameException 
	 */
	private List<JmsbaZlqdVo> doQueryZlqd(Connection conn,String basqwsh) throws FrameException{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 where basqwsh=?";
		List<JmsbaZlqdVo> zlqdList=new ArrayList<JmsbaZlqdVo>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, basqwsh);
			rs=ps.executeQuery();
			while(rs.next()){
				JmsbaZlqdVo jmsbaZlqdVo=new JmsbaZlqdVo();
				jmsbaZlqdVo.setXh(rs.getString("xh"));
				jmsbaZlqdVo.setZlqdmc(rs.getString("ZLQD"));
				jmsbaZlqdVo.setSfkysc(rs.getString("SFKYSC"));
				jmsbaZlqdVo.setSfshtg(rs.getString("SFSHTG"));
				zlqdList.add(jmsbaZlqdVo);				
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询减免税备案资料清单数据失败！" + e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, null);
		}		
		return zlqdList;
	}
	
	/**
	 * 作废备案事项
	 * @param conn
	 * @param baseVo
	 * @return
	 * @throws FrameException
	 */
	public boolean doDestory(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			String zfsm="";//作废说明
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='6',zfr='"+ud.getYhid()+"',zfrq=sysdate,zfsm='"+zfsm+"' ,htr=null,htrq=null where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("作废减免税备案项目（"+baseVo.getJmbasxmc()+"）失败！");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
	
	/**
	 * 将状态改为带变更状态
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doModify(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='2',zfr=null,zfrq=null,zfsm=null,htr='"+ud.getYhid()+"',htrq=sysdate where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("变更减免税备案项目（"+baseVo.getJmbasxmc()+"）失败！");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
	
	/**
	 * 将状态改为审核通过
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doAudit(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='4',zfr=null,zfrq=null,zfsm=null,htr=null,htrq=null,shr='"+ud.getYhid()+"',shsj=sysdate where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("变更减免税备案项目（"+baseVo.getJmbasxmc()+"）失败！");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
	
	/**
	 * 将状态改为审核通过
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doAuditReject(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='5',zfr=null,zfrq=null,zfsm=null,htr=null,htrq=null,shr='"+ud.getYhid()+"',shsj=sysdate where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("变更减免税备案项目（"+baseVo.getJmbasxmc()+"）失败！");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
}
