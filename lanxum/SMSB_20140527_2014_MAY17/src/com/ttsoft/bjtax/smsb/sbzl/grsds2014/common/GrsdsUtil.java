/**
 * @Title:       GrsdsUtil.java
 * @Description: TODO
 * @date:        2014-11-28下午06:04:42
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM.DjzclxDM;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM.GjbzhyDM;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM.GjdqDM;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM.SfzjlxDM;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM.SwjgzzjgDM;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-28
 */
public class GrsdsUtil {

	/**
	 * @Description: TODO 获取身份证件类型代码表
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
				conn = SfDBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					SfzjlxDM sfzjlxDM = new SfzjlxDM();
					sfzjlxDM.setText(rs.getString("ZJLXMC"));
					sfzjlxDM.setValue(rs.getString("ZJLXDM"));
					ls.add(sfzjlxDM);
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
	 * @Description: TODO 获取国籍
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
				conn = SfDBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					GjdqDM gjdqDM = new GjdqDM();
					gjdqDM.setText(rs.getString("GJDQMC"));
					gjdqDM.setValue(rs.getString("GJDQDM"));
					ls.add(gjdqDM);
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
	 * @Description: TODO税务机关组织机构
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List getSwjgzzjgList(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_SWJGZZJG)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_SWJGZZJG) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_SWJGZZJG);
		}else{
			List ls = new ArrayList();
			String sql = "select * from dmdb.gy_dm_swjgzzjg";
			Connection conn =null;
			try {
				conn = SfDBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					SwjgzzjgDM swjgzzjgDM = new SwjgzzjgDM();
					swjgzzjgDM.setText(rs.getString("SWJGZZJGMC"));
					swjgzzjgDM.setValue(rs.getString("SWJGZZJGDM"));
					ls.add(swjgzzjgDM);
				}
				session.setAttribute(Grsds2014Constant.SESSION_DM_SWJGZZJG ,ls);
			} finally {
				if(conn!=null)
					conn.close();
			}
			return ls;
		}
	}
	
	/**
	 * @Description: TODO 国家标准行业
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List getHyList(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute(Grsds2014Constant.SESSION_DM_GJBZHY)!=null && session.getAttribute(Grsds2014Constant.SESSION_DM_GJBZHY) instanceof List)
		{
			return (List)session.getAttribute(Grsds2014Constant.SESSION_DM_GJBZHY);
		}else{
			List ls = new ArrayList();
			String sql = "select * from dmdb.gy_dm_gjbzhy";
			Connection conn =null;
			try {
				conn = SfDBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					GjbzhyDM gjbzhyDM = new GjbzhyDM();
					gjbzhyDM.setText(rs.getString("GJBZHYMC"));
					gjbzhyDM.setValue(rs.getString("GJBZHYDM"));
					ls.add(gjbzhyDM);
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
	 * @Description: TODO 登记注册类型
	 * @param request
	 * @return
	 * @throws Exception
	 */
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
				conn = SfDBResource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					DjzclxDM djzclxDM = new DjzclxDM();
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
	 * @Description: TODO 是否存在该年度投资者基础信息 
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
			throw new Exception("no connection");
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
		Connection conn = SfDBResource.getConnection();
		try{
			return hasItemJcxxTzz(nd,jsjdm,sfzjlx,sfzjhm,conn);
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}


	/**
	 * @Description: TODO 是否存在该年度企业基础信息
	 * @param nd
	 * @param jsjdm
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemJcxxQy(String nd ,String jsjdm ,Connection conn) throws Exception{
		if(conn==null ){
			throw new Exception("no connection");
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
	 * @Description: TODO 查询是否存在年报企业
	 * @param nd
	 * @param jsjdm
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemNdsbbQy(String nd ,String jsjdm ,Connection conn) throws Exception{
		if(conn==null ){
			throw new Exception("no connection");
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
	 * @Description: TODO 年度申报表投资方
	 * @param nd
	 * @param jsjdm
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static boolean hasItemNdsbbTzz(String nd ,String jsjdm ,String sfzjlxdm ,String sfzjhm ,Connection conn) throws Exception{
		if(conn==null ){
			throw new Exception("no connection");
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

	public static boolean hasItemNdsbbTzz(String nd ,String jsjdm ,String sfzjlx ,String sfzjhm ) throws Exception
	{
		Connection conn = SfDBResource.getConnection();
		try{
			return hasItemNdsbbTzz(nd,jsjdm,sfzjlx,sfzjhm,conn);
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	/**
	 * @Description: TODO 权限判定
	 * @param jsjdm
	 * @param ud
	 * @return
	 * @throws SystemException 
	 * @throws Exception 
	 */
	public static boolean checkAuthority(String jsjdm ,UserData ud) throws Exception{
		boolean result = false;
		Connection conn = null;
		String yhjb = ud.getYhjb();
		
		//对分局，市局，税务所及用户才判断
		if(CodeConstants.JBDM_SJ.equals(yhjb)||CodeConstants.JBDM_FJ.equals(yhjb)||CodeConstants.JBDM_SWSJ.equals(yhjb)){
			try {
				conn = SfDBResource.getConnection();
				
				//数据过滤权限
				String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"DJDB", "DJ_JL_JBSJ");
				String query_sql="select jsjdm from djdb.dj_jl_jbsj jbsj where jbsj.jsjdm=? ";
				if(datafilter!=null && !"".equals(datafilter)){
					query_sql+=" and "+datafilter;
				}
				System.out.println("权限查询："+query_sql);
				PreparedStatement ps = conn.prepareStatement(query_sql);
				ps.setString(1, jsjdm);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					result = true;
				}
			} finally{
				if(conn!=null)
					conn.close();
			}
			
		}
		
		return result;
	}


	/**
	 * @Description: TODO 检查征收方式 :查账或没有才正确
	 * @param jsjdm
	 * @return
	 * @throws Exception
	 */
	public static boolean checkZsfs(String jsjdm ,String nd)throws Exception{
		boolean result = false;
		
        String zsfsDM = null;
 
        Connection conn = SfDBResource.getConnection();
        String querySQL = "select zsfsdm  from  sfdb.SF_JL_GRHHZSFS  where jsjdm=? and rdnd=?";
        try {
			PreparedStatement ps =conn.prepareStatement(querySQL);
			ps.setString(1, jsjdm);
			ps.setString(2, nd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				zsfsDM = rs.getString("zsfsdm");
			}
		} catch (SQLException e) {
			throw ExceptionUtil.getBaseException(e);
		}
       
		
        if(zsfsDM== null || "12".equals(zsfsDM)){
        	result=true;
        }
		return result;
	}
	
	/**
	 * @Description: TODO 检查登记注册类型
	 * @param jsjdm
	 * @return
	 * @throws Exception
	 */
	public static boolean checkDjzclx(String jsjdm )throws Exception{
		boolean result = false;
	
		com.ttsoft.bjtax.dj.proxy.ServiceProxy sp = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
		Map djMap = sp.getDjInfo(jsjdm);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		String djzclxdm = jbsj.getDjzclxdm();
			
		if(Grsds2014Constant.DJZCLXDM_GTGSH.equals(djzclxdm)||Grsds2014Constant.DJZCLXDM_GRDZQY.equals(djzclxdm)||Grsds2014Constant.DJZCLXDM_GRHHQY.equals(djzclxdm)||Grsds2014Constant.DJZCLXDM_SYDZQY.equals(djzclxdm)||Grsds2014Constant.DJZCLXDM_SYHHQY.equals(djzclxdm))
		{
			result=true;
		}
		return result;
	}

	public static List getPageList(List allList ,int neededPage ,int pageNumber){
		List result=null;
		int size = allList.size();
		//如果不足0页：
		if(size <= (neededPage-1)*pageNumber){
			result=new ArrayList();
		
		//不到一页
		}else if(size>(neededPage-1)*pageNumber && size<=neededPage*pageNumber){
			int start = (neededPage-1)*pageNumber;
			int end = size;
			result = allList.subList(start, end);
		//整页
		}else{
			int start = (neededPage-1)*pageNumber;
			int end = neededPage*pageNumber;
			result = allList.subList(start, end);
		}
		
		return result;
	}
}
