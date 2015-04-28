package com.lscdz.qysds.application.jmsba2014.main.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.Jmsba2014Contant;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaZlqdVo;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaBasxVo;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaZgswjg;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.qysds.common.util.SbzlAccess;
import com.lscdz.qysds.common.vo.sf_jl_qysdsjmsbajl_2014;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

public class JmsbaMainAccessExt {

	private static String deploy_environment=ResourceManager.getTokenByName("DEPLOY_ENVIRONMENT");
	
	/**
	 * 获取减免税备案列表
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List<JmsbaBasxVo> doQueryJmsBasxList(String sqlWhere)throws FrameException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy");
		int curBand = Integer.parseInt(sf.format(new Date()))-1;
		List<JmsbaBasxVo> list = new ArrayList<JmsbaBasxVo>();
		try {
			conn = ResourceManager.getConnection();
			ps = conn.prepareStatement(sqlWhere);

			rs = ps.executeQuery();
			while (rs.next()) {
				JmsbaBasxVo bajlVo = new JmsbaBasxVo();
				bajlVo.setJsjdm(rs.getString("jsjdm"));
				bajlVo.setNsrmc(rs.getString("nsrmc"));
				bajlVo.setBand(rs.getString("band"));
				bajlVo.setBasqbh(rs.getString("basqbh"));
				bajlVo.setBasqwsh(rs.getString("basqwsh"));
				bajlVo.setJmbasxdm(rs.getString("jmbasxdm"));
				bajlVo.setJmbasxmc(rs.getString("jmbasxmc"));
				bajlVo.setSqlxdm(rs.getString("sqlxdm"));
				if (rs.getString("sqlxdm").equals(QysdsNbConstant.QYSDS_SQLX_WS_CODE)) {
					bajlVo.setSqlxmc(QysdsNbConstant.QYSDS_SQLX_WS_NAME);
				} else {
					bajlVo.setSqlxmc(QysdsNbConstant.QYSDS_SQLX_SM_NAME);
				}
				bajlVo.setSqzt(rs.getString("sqzt"));
				int theBand = Integer.parseInt(rs.getString("band"));
				if("EXTERIOR".equals(deploy_environment)){
					String zqstr = SbzlAccess.getProperty("WSSB_JMSBAHSQJ2014_ZQRL_MONTH_"+ (new SimpleDateFormat("MM")).format(new Date()));
					if (!SbzlAccess.withinZq(zqstr, new Date()) || curBand != theBand) {
						bajlVo.setSqzt("9");
					}
				}
				
				bajlVo.setSqztmc(getSqztMc(rs.getString("sqzt")));
				bajlVo.setSwjgzzjgmc(rs.getString("swjgzzjgmc"));
				bajlVo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				list.add(bajlVo);
			}

		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			// 抛出异常
			ex.printStackTrace();
			throw new FrameException("查询备案数据失败" + ex.getMessage());
		} finally {
			if(ps!=null){
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return list;
	}

	/**
	 * 获取资料清单列表
	 * @param conn
	 * @param baseVo
	 * @return
	 * @throws FrameException
	 */
	public static List<JmsbaZlqdVo> getAddJmsbaZlqdList(Connection conn,JmsbaBaseVo baseVo)throws FrameException {
	
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<JmsbaZlqdVo> list = new ArrayList<JmsbaZlqdVo>();
		try {
			String sqlWhere=" select t.zlqdmc,t.sfkysc,t.zlqddm from dmdb.sf_dm_bazlqddm_2014 t WHERE t.jmbasxdm = '"+baseVo.getJmbasxdm()+"'  and t.zfbs = '0' ORDER BY T.zlqddm";
			ps = conn.prepareStatement(sqlWhere);
			rs = ps.executeQuery();
			while (rs.next()) {
				JmsbaZlqdVo zlqdVo = new JmsbaZlqdVo();
				
				zlqdVo.setZlqddm(rs.getString("zlqddm"));
//				if("01".equals(rs.getString("zlqddm"))){
//					zlqdVo.setZlqdmc(getZlqddm01mc(baseVo.getSwjgzzjgdm().substring(0,2)));
//				}else{
//					zlqdVo.setZlqdmc(rs.getString("zlqdmc"));
//				}
				zlqdVo.setZlqdmc(rs.getString("zlqdmc"));
				zlqdVo.setSfkysc(rs.getString("sfkysc"));
				list.add(zlqdVo);
			}

		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			// 抛出异常
			ex.printStackTrace();
			throw new FrameException("查询备案数据失败" + ex.getMessage());
		} finally {
			if(ps!=null){
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return list;
	}
  
	public static List<JmsbaZgswjg> getZgswjgList(Yh userData)throws FrameException {
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<JmsbaZgswjg> list = new ArrayList<JmsbaZgswjg>();
		try {
			
			conn=ResourceManager.getConnection();
			String sqlWhere="select swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' ";
			String ssdwdm=userData.getSsdwdm();		
			String yhjb=userData.getJbdm();
			if(yhjb.equals("50")){
				sqlWhere+=" and ccbs='1' ";
			}
			if(yhjb.equals("40")){
				sqlWhere+=" and ccbs='2'  and jgznlx='1'  and swjgzzjgdm like '"+ssdwdm.substring(0,2)+"%'" ; 
			}
			if(yhjb.equals("30")){
				sqlWhere+=" and swjgzzjgdm ='"+ssdwdm+"'";
			}
			
			sqlWhere+=" order by swjgzzjgdm";
			ps = conn.prepareStatement(sqlWhere);
			rs = ps.executeQuery();
			while (rs.next()) {
				JmsbaZgswjg zgswjg = new JmsbaZgswjg();
				zgswjg.setZgswjgDm(rs.getString("swjgzzjgdm"));
				zgswjg.setZgswjgMc(rs.getString("swjgzzjgmc"));
				list.add(zgswjg);
			}

		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			// 抛出异常
			ex.printStackTrace();
			throw new FrameException("获取主管税务机关列表失败" + ex.getMessage());
		} finally {
			if(ps!=null){
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return list;
	}
	
	/**
	 * 获取资料清单代码为01的资料名称
	 * @description 根据企业所得税处要求，根据纳税人所在税务机关，资料清单中输出：北京市XXX区县地方税务局减税免税申报资料
	 * @param qxdm
	 * @return
	 */
    public static String getZlqddm01mc(String qxdm){
    	
    	System.out.println("=====进入getZlqddm01mc方法========");
    	
    	String zlqddm01mc="企业所得税优惠事项备案表";
//    	if("01".equals(qxdm)||qxdm.equals("01")){							
//    		zlqddm01mc = "北京市东城区地方税务局减税免税备案表";
//		}
//		else if("02".equals(qxdm)||qxdm.equals("02")){							
//			zlqddm01mc = "北京市西城区地方税务局减税免税备案表";
//		}
//		else if("03".equals(qxdm)||qxdm.equals("03")){							
//			zlqddm01mc = "北京市崇文区地方税务局减税免税备案表";
//		}
//		else if("04".equals(qxdm)||qxdm.equals("04")){							
//			zlqddm01mc = "北京市宣武区地方税务局减税免税备案表";
//		}
//		else if("05".equals(qxdm)||qxdm.equals("05")){							
//			zlqddm01mc = "北京市朝阳区地方税务局减税免税备案表";
//		}
//		else if("06".equals(qxdm)||qxdm.equals("06")){							
//			zlqddm01mc = "北京市海淀区地方税务局减税免税备案表";
//		}
//		else if("07".equals(qxdm)||qxdm.equals("07")){							
//			zlqddm01mc = "北京市丰台区地方税务局减税免税备案表";
//		}
//		else if("08".equals(qxdm)||qxdm.equals("08")){							
//			zlqddm01mc = "北京市石景山区地方税务局减税免税备案表";
//		}
//		else if("09".equals(qxdm)||qxdm.equals("09")){							
//			zlqddm01mc = "北京市门头沟区地方税务局减税免税备案表";
//		}
//		else if("10".equals(qxdm)||qxdm.equals("10")){							
//			zlqddm01mc = "北京市燕山区地方税务局减税免税备案表";
//		}
//		else if("11".equals(qxdm)||qxdm.equals("11")){							
//			zlqddm01mc = "北京市昌平区地方税务局减税免税备案表";
//		}
//		else if("12".equals(qxdm)||qxdm.equals("12")){							
//			zlqddm01mc = "北京市通州区地方税务局减税免税备案表";
//		}
//		else if("13".equals(qxdm)||qxdm.equals("13")){							
//			zlqddm01mc = "北京市顺义区地方税务局减税免税备案表";
//		}
//		else if("14".equals(qxdm)||qxdm.equals("14")){							
//			zlqddm01mc = "北京市大兴区地方税务局减税免税备案表";
//		}
//		else if("15".equals(qxdm)||qxdm.equals("15")){							
//			zlqddm01mc = "北京市房山区地方税务局减税免税备案表";
//		}
//		else if("16".equals(qxdm)||qxdm.equals("16")){							
//			zlqddm01mc = "北京市怀柔区地方税务局减税免税备案表";
//		}
//		else if("17".equals(qxdm)||qxdm.equals("17")){							
//			zlqddm01mc = "北京市密云县地方税务局减税免税备案表";
//		}
//		else if("18".equals(qxdm)||qxdm.equals("18")){							
//			zlqddm01mc = "北京市平谷区地方税务局减税免税备案表";
//		}
//		else if("19".equals(qxdm)||qxdm.equals("19")){							
//			zlqddm01mc = "北京市延庆县地方税务局减税免税备案表";
//		}
//		else if("20".equals(qxdm)||qxdm.equals("20")){							
//			zlqddm01mc = "北京市开发区分局地方税务局减税免税备案表";
//		}
//		else if("21".equals(qxdm)||qxdm.equals("21")){							
//			zlqddm01mc = "北京市西站分局地方税务局减税免税备案表";
//		}
//		else if("22".equals(qxdm)||qxdm.equals("22")){							
//			zlqddm01mc = "北京市涉外分局地方税务局减税免税备案表";
//		}
//		else if("25".equals(qxdm)||qxdm.equals("25")){							
//			zlqddm01mc = "北京市第二稽查局减税免税备案表";
//		}
//		else if("41".equals(qxdm)||qxdm.equals("41")){							
//			zlqddm01mc = "北京市第一稽查局减税免税备案表";
//		}
//		else if("90".equals(qxdm)||qxdm.equals("90")){							
//			zlqddm01mc = "北京市地方税务局减税免税备案表";
//		}
//		else {							
//			zlqddm01mc = "北京市其它地方税务局减税免税备案表";
//		}
    	return zlqddm01mc;
    }
	
	/**
	 * 获取申请状态名称 申请状态代码，1：保存未提交，2：保存未审核，3：提交未审核，4：审核已通过，5：审核未通过
	 * 
	 * @param sqztDm
	 * @return
	 */
	public static String getSqztMc(String sqztDm) {
		String sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWTJ_NAME;
		if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWTJ_CODE)) {
			// 保存未提交
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWTJ_NAME;
		} else if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWSH_CODE)) {
			// 保存未审核
			String deploy_environment=ResourceManager.getTokenByName("DEPLOY_ENVIRONMENT");
			if("INNER".equals(deploy_environment)){
				sqztMc=Jmsba2014Contant.QYSDS_JMSBA_SQZT_DBG_NAME;
			}else{
				sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWSH_NAME;
			}
		} else if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_TJWSH_CODE)) {
			// 提交未审核
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_TJWSH_NAME;
		} else if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE)) {
			// 审核已通过
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_NAME;
		} else if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHWTG_CODE)) {
			// 审核未通过
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHWTG_NAME;
		}else if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_YZF_CODE)) {
			// 已作废
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_YZF_NAME;
		}else if (sqztDm.equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_TSZD_CODE)) {
			// 已接受申请
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_TSZD_NAME;
		} else {
			sqztMc = Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWTJ_NAME;
		}
		return sqztMc;
	}

	/**
	 * 获取总页数
	 * 
	 * @param string
	 * @return
	 * @throws FrameException
	 */
	public static int getCountPage(String string) throws FrameException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		if (ResourceManager.isDebug()) {
			System.out.println("=====sql=====:" + string);
		}
		try {
			con = ResourceManager.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(string);

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			throw e;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			throw new FrameException("内部错误，请与管理员联系，获取总页数");
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception exx) {
			}
			try {
				if (st != null)
					st.close();
			} catch (Exception exx) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception exx) {
			}
		}
	}
	
	/**
	 * 删除备案事项主表数据
	 * @param conn
	 * @param basqwsh
	 * @throws FrameException
	 */
	public static void deleteJmsbaZlqd(Connection conn, String basqwsh) throws FrameException
	{
		Statement st = null;
		String buf = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 where basqwsh='"+ basqwsh + "'";
		try
		{
			st = conn.createStatement();
			st.executeUpdate(buf);
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SF_JL_QYSDSJMSBAJL_2014");
		}
		finally
		{
			try{if (st != null) st.close();}catch(Exception exx){}
		}
	}
	/**
	 * 删除备案事项主表数据
	 * @param conn
	 * @param basqwsh
	 * @throws FrameException
	 */
	public static void deleteJmsbaZb(Connection conn, String basqwsh) throws FrameException
	{
		Statement st = null;
		String buf = "DELETE  SFDB.SF_JL_QYSDSJMSBAJL_2014 where basqwsh='"+ basqwsh + "'";
		try
		{
			st = conn.createStatement();
			st.executeUpdate(buf);
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SF_JL_QYSDSJMSBAJL_2014");
		}
		finally
		{
			try{if (st != null) st.close();}catch(Exception exx){}
		}
	}
	
	/**
	 * 网上对已提交未审核的执行撤回操作
	 * @param conn
	 * @param baseVo
	 * @return
	 * @throws FrameException
	 */
	public static void doRollbackBasx(Connection conn, JmsbaBaseVo baseVo)throws FrameException {
		PreparedStatement ps = null;
		try {
			
			String sql = "update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='1' where basqwsh='"+ baseVo.getBasqwsh() + "'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("更新申请状态失败！" + e.getMessage());
		} 
	}
/*	*//**
	 * 查询减免税备案记录
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 *//*
	public static List<sf_jl_qysdsjmsbajl_2014> getRecords(String sqlWhere,Connection con) throws FrameException
	{
		//Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sf_jl_qysdsjmsbajl_2014 vo = null;
		String buf = null;
		List<sf_jl_qysdsjmsbajl_2014> ar = new ArrayList<sf_jl_qysdsjmsbajl_2014>();

		try
		{
			//con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SFDB.SF_JL_QYSDSJMSBAJL_2014 " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new sf_jl_qysdsjmsbajl_2014();
				vo.setBafs(rs.getString("BAFS"));
				vo.setBand(rs.getString("BAND"));
				vo.setBasqbh(rs.getString("BASQBH"));
				vo.setBasqwsh(rs.getString("BASQWSH"));
				vo.setBbqlx(rs.getString("BBQLX"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setJmbasxdm(rs.getString("JMBASXDM"));
				vo.setJsjdm(rs.getString("JSJDM"));
				vo.setJyxgzgdpzwjjwh(rs.getString("JYXGZGDPZWJJWH"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setQh(rs.getString("QH"));
				vo.setSksssqq(rs.getTimestamp("SKSSSQQ"));
				vo.setSksssqz(rs.getTimestamp("SKSSSQZ"));
				vo.setSqlxdm(rs.getString("SQLXDM"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSzdm(rs.getString("SZDM"));
				vo.setXsyhqjq(rs.getTimestamp("XSYHQJQ"));
				vo.setXsyhqjz(rs.getTimestamp("XSYHQJZ"));
				vo.setZyzcyjwjjwh(rs.getString("ZYZCYJWJJWH"));
				vo.setBajmbl(rs.getBigDecimal("BAJMBL"));
				vo.setBajmse(rs.getBigDecimal("BAJMSE"));
				vo.setHtr(rs.getString("HTR"));
				vo.setHtrq(rs.getTimestamp("HTRQ"));
				vo.setShr(rs.getString("SHR"));
				vo.setShsj(rs.getTimestamp("SHSJ"));
				vo.setSqzt(rs.getString("SQZT"));
				vo.setTjr(rs.getString("TJR"));
				vo.setTjsj(rs.getTimestamp("TJSJ"));
				vo.setWjyxqjzrq(rs.getTimestamp("WJYXQJZRQ"));
				vo.setWjyxqqsrq(rs.getTimestamp("WJYXQQSRQ"));
				vo.setYgqksm(rs.getString("YGQKSM"));
				vo.setZfr(rs.getString("ZFR"));
				vo.setZfrq(rs.getTimestamp("ZFRQ"));
				vo.setZfsm(rs.getString("ZFSM"));

				ar.add(vo);
			}
			return ar;
		}
	
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SFDB.SF_JL_QYSDSJMSBAJL_2014");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			//try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}*/
}
