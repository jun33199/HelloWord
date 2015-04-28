package com.lscdz.qysds.application.qysdsnb2014.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lscdz.qysds.application.jmsba2014.Jmsba2014Contant;
import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsJmsbajl;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

/**
 * 处理与年报中与减免税相关的业务
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-3-3 上午10:14:48
 */
public class JmsbaHelper {
	/**
	 * 根据计算机代码和备案年度，判断数据库中是否有审核已经通过的农林牧渔的备案事项
	 * @param jsjdm
	 * @param band
	 * @return
	 * @throws FrameException 
	 */
	public static boolean nlmyBasxChecked(String jsjdm,String band) throws FrameException{
		
		String sql="select * from  SFDB.SF_JL_QYSDSJMSBAJL_2014  where jsjdm=? and band=? and JMBASXDM=? and SQZT=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			conn=ResourceManager.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, jsjdm);
			ps.setString(2, band);
			ps.setString(3, Jmsba2014Contant.QYSDS_JMSBA_BASX_0120);
			ps.setString(4, Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE);
			rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询备案事项‘从事农、林、牧、渔业项目的所得减免征收企业所得税’失败！");
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, conn);
		}
		return false;		
	}
	/**
	 * 根据计算机代码和备案年度查询数据库中所有的备案事项
	 * @param jsjdm
	 * @param band
	 * @return
	 * @throws FrameException 
	 */
	public static ArrayList<QysdsJmsbajl> basxQuery(String jsjdm,String band) throws FrameException{
		
		String sql="select JMBASXDM,SQZT from  SFDB.SF_JL_QYSDSJMSBAJL_2014  where jsjdm=? and band=? and sqzt in(2,3,4) ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		ArrayList<QysdsJmsbajl> jmsbajlList=null;
		try {
			conn=ResourceManager.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, jsjdm);
			ps.setString(2, band);
			rs=ps.executeQuery();
			jmsbajlList=new ArrayList<QysdsJmsbajl>();
			while(rs.next()){				
				QysdsJmsbajl jmsbajl=new QysdsJmsbajl();
				jmsbajl.setJmbasxdm(rs.getString("JMBASXDM"));
				jmsbajl.setSqzt(rs.getString("SQZT")==null?"":rs.getString("SQZT"));
				jmsbajlList.add(jmsbajl);				
			}
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询备案事项失败！");
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, conn);
		}
		return jmsbajlList;	
	}	
}
