/**
 * @Title:       GrsdsUtil.java
 * @Description: TODO
 * @date:        2014-11-28����06:04:42
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Djzclx;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Gjbzhy;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Gjdq;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Sfzjlx;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.util.DBResource;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-28
 */
public class GrsdsUtil {

	/**
	 * @Description: TODO ��ȡ���֤�����ʹ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List getSfzjlxList(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_SFZJLXS)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_SFZJLXS) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_SFZJLXS);
		}else{
			List ls = new ArrayList();
			String sql = "select * from DMDB.Gy_Dm_Zjlx";
			Connection conn = null;
			try{
				conn = DBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Sfzjlx sfzjlxVO = new Sfzjlx();
					sfzjlxVO.setText(rs.getString("ZJLXMC"));
					sfzjlxVO.setValue(rs.getString("ZJLXDM"));
					ls.add(sfzjlxVO);
				}
				session.setAttribute(Grsds2014Constant.SESSION_DM_SFZJLXS ,ls);
			}finally{
				if(conn!=null)
				conn.close();
			}
			
			return ls;
		}
	}
	/**
	 * @Description: TODO ��ȡ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List getGjDqList(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_GJ)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_GJ) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_GJ);
		}else{
			List ls = new ArrayList();
			String sql = "select * from DMDB.Gy_Dm_GJDQ";
			Connection conn =null;
			try {
				conn = DBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Gjdq gjdq = new Gjdq();
					gjdq.setText(rs.getString("GJDQMC"));
					gjdq.setValue(rs.getString("GJDQDM"));
					ls.add(gjdq);
				}
				session.setAttribute(Grsds2014Constant.SESSION_DM_GJ ,ls);
			} finally {
				if(conn!=null)
					conn.close();
			}
			
			return ls;
		}
	}
	
	/**
	 * @Description: ���ұ�׼��ҵ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List getGjBzhyList(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_GJBZHY)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_GJBZHY) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_GJBZHY);
		}else{
			List ls = new ArrayList();
			String sql = "select * from dmdb.gy_dm_gjbzhy";
			Connection conn =null;
			try {
				conn = DBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Gjbzhy gjbzhy = new Gjbzhy();
					gjbzhy.setText(rs.getString("GJBZHYMC"));
					gjbzhy.setValue(rs.getString("GJBZHYDM"));
					ls.add(gjbzhy);
				}
				session.setAttribute(Grsds2014Constant.SESSION_DM_GJBZHY ,ls);
			} finally {
				if(conn!=null)
					conn.close();
			}
			
			return ls;
		}
	}
	
	/**
	 * @Description: ˰�������֯����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List getSwjgzzjgList(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_SSJGZZJG)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_SSJGZZJG) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_SSJGZZJG);
		}else{
			List ls = new ArrayList();
			String sql = "select * from dmdb.gy_dm_swjgzzjg";
			Connection conn =null;
			try {
				conn = DBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Swjgzzjg swjgzzjg = new Swjgzzjg();
					swjgzzjg.setText(rs.getString("SWJGZZJGMC"));
					swjgzzjg.setValue(rs.getString("SWJGZZJGDM"));
					ls.add(swjgzzjg);
				}
				session.setAttribute(Grsds2014Constant.SESSION_DM_SSJGZZJG ,ls);
			} finally {
				if(conn!=null)
					conn.close();
			}
			
			return ls;
		}
	}
	
	public static List getDjzclxList(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_DJZCLX)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_DJZCLX) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_DJZCLX);
		}else{
			List ls = new ArrayList();
			String sql = "select * from dmdb.dj_dm_djzclx";
			Connection conn =null;
			try {
				conn = DBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Djzclx djzclxDM = new Djzclx();
					djzclxDM.setText(rs.getString("DJZCLXMC"));
					djzclxDM.setValue(rs.getString("DJZCLXDM"));
					ls.add(djzclxDM);
				}
				session.setAttribute(Grsds2014Constant.SESSION_DM_DJZCLX ,ls);
			} finally {
				if(conn!=null)
					conn.close();
			}
			
			return ls;
		}
	}
	
	/**
	 * @Description: TODO �Ƿ���ڸ����Ͷ���߻�����Ϣ 
	 * @param nd
	 * @param jsjdm
	 * @param sfzjlx
	 * @param sfzjhm
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemJcxxTzz(String nd ,String jsjdm ,String sfzjlx ,String sfzjhm ,Connection conn) throws Exception
	{
		if(conn==null ){
			throw new Exception("no DBConnection");
		}
		boolean result = false;
		String sql = "select jsjdm from sbdb.SB_JL_GRSDS_JCXXB_TZF where jsjdm=? and sfzjlxdm=? and sfzjhm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jsjdm);
		ps.setString(2, sfzjlx);
		ps.setString(3, sfzjhm);
		ps.setString(4, nd);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			result = true;
		}
		return result;
	}
	
	public static boolean hasItemJcxxTzz(String nd ,String jsjdm ,String sfzjlx ,String sfzjhm ) throws Exception
	{
		Connection conn = DBResource.getConnection();
		try{
			return hasItemJcxxTzz(nd,jsjdm,sfzjlx,sfzjhm,conn);
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}

	/**
	 * @Description: TODO �Ƿ���ڸ������ҵ������Ϣ
	 * @param nd
	 * @param jsjdm
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemJcxxQy(String nd ,String jsjdm ,Connection conn) throws Exception{
		if(conn==null ){
			throw new Exception("no DBConnection");
		}
		boolean result = false;
		String sql = "select jsjdm from sbdb.SB_JL_GRSDS_JCXXB_QY where jsjdm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jsjdm);
		ps.setString(2, nd);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			result = true;
		}
		return result;
	}
	
	
	/**
	 * @Description: TODO ��ѯ�Ƿ�����걨��ҵ
	 * @param nd
	 * @param jsjdm
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemNdsbbQy(String nd ,String jsjdm ,Connection conn) throws Exception{
		if(conn==null ){
			throw new Exception("no DBConnection");
		}
		boolean result = false;
		String sql = "select jsjdm from SBDB.SB_JL_GRSDS_NDSBB_QY where jsjdm=? and nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jsjdm);
		ps.setString(2, nd);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * @Description: TODO ����걨��Ͷ�ʷ�
	 * @param nd
	 * @param jsjdm
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemNdsbbTzz(String nd ,String jsjdm ,String sfzjlxdm ,String sfzjhm ,Connection conn) throws Exception{
		if(conn==null ){
			throw new Exception("no DBConnection");
		}
		boolean result = false;
		String sql = "select jsjdm from SBDB.SB_JL_GRSDS_NDSBB_TZZ where jsjdm=? and nd=? and sfzjlxdm=? and sfzjhm=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, jsjdm);
		ps.setString(2, nd);
		ps.setString(3, sfzjlxdm);
		ps.setString(4, sfzjhm);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			result = true;
		}
		return result;
	}

	/**
	 * @Description: TODO ��post��ʽ���ݵı��Ļ���utf8�����ֽ������ڷ�����������GBK���룬�����轫ԭ�ֽ�����GBK��������utf8����
	 * @param material
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String GBKTOUTF(String material) throws UnsupportedEncodingException{
		return new String(material.getBytes("GBK"), "utf-8");
 	}

	/**
	 * @Description: TODO ȡ��ҳ��Ϣ
	 * @param allList
	 * @param neededPage
	 * @param pageNumber
	 * @return
	 */
	public static List getPageList(List allList ,int neededPage ,int pageNumber){
		List result=null;
		int size = allList.size();
		//�������0ҳ��
		if(size <= (neededPage-1)*pageNumber){
			result=new ArrayList();
		
		//����һҳ
		}else if(size>(neededPage-1)*pageNumber && size<=neededPage*pageNumber){
			int start = (neededPage-1)*pageNumber;
			int end = size;
			result = allList.subList(start, end);
		//��ҳ
		}else{
			int start = (neededPage-1)*pageNumber;
			int end = neededPage*pageNumber;
			result = allList.subList(start, end);
		}
		
		return result;
	}
}
