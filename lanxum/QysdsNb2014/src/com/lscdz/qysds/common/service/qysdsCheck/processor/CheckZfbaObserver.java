
package com.lscdz.qysds.common.service.qysdsCheck.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.lscdz.qysds.common.service.qysdsCheck.JdlxContant;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.service.qysdsCheck.bo.ZfjgInf;
import yangjian.frame.util.FrameException;

/**
 * 企业所得税申报表校验器--根据时间点
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-19 上午11:18:33
 */

public class CheckZfbaObserver{
	
	/**
	 *检查企业所得税申报表对应类型
	 * @throws BaseException 
	 */
	 
	public void update(Connection conn,CheckBean checkBean) throws FrameException {

		ZfjgInf zfjgInf = null;
		// 鉴定类型为 跨省市总机构纳税人 02 或跨省市分支机构纳税人 03 时
		if (JdlxContant.QYSDSZGFWJDLX_KSSZJGNSR.equals(checkBean.getJdlx())
				|| JdlxContant.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())) {
			zfjgInf = this.doQueryZjgba(conn,checkBean);
			if (zfjgInf != null) {
				// 总机构
				if ("0".equals(zfjgInf.getBaqylx())) {
					if (!zfjgInf.isIsftsk()) {
						checkBean.setJdlx(JdlxContant.QYSDSZGFWJDLX_DLNSR);// 设置鉴定类型为独立纳税人
						System.out.println("---------CheckZfbaObserver  总机构已设置为独立纳税人");
					}
				} 
                // 分支机构
				if ("1".equals(zfjgInf.getBaqylx())){
					// 是否参与分摊企业所得税项目为否且分支机构类型为二级分支机构
					if (!zfjgInf.isCyftsk() && "02".equals(zfjgInf.getFzlx())) {
						checkBean.setJdlx(JdlxContant.QYSDSZGFWJDLX_DLNSR);// 设置鉴定类型为独立纳税人
						System.out.println("---------CheckZfbaObserver  分支机构已设置为独立纳税人");
					}
				}
			}
			checkBean.setZfjgInf(zfjgInf);
		}
	}


	/**
	 * @Description: 外部工程获取总分机构备案信息接口的实现方法
	 * @param vo
	 * @return
	 */
	public ZfjgInf doQueryZjgba(Connection conn,CheckBean checkBean) throws FrameException{
//		Connection conn = null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
//	    ZfjgbaModel model = null ;
//	    List zfjgList= new ArrayList(); 
	    ZfjgInf zfjgInf = null;
		try {
			//获取链接
//			conn = ResourceManager.getConnection();
			//获取用户数据
			String jsjdm = checkBean.getJsjdm();
			//String ksrq = (String) map.get("ksrq");
			String jzrq = checkBean.getSkssrqz();
			String sql = "SELECT id,baqylx,jsjdm,baqssj,sfftsk,sndsfxwqy,fzjglxdm,BAQSSJ FROM sfdb.SF_JL_ZFJGBA_QYXX t " +
					     " WHERE jsjdm=? AND ZXBZ='0' AND BAQSSJ<to_date(?,'yyyymmdd') ORDER BY t.baqssj DESC";
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, jsjdm);
		    ps.setString(2, jzrq);
		    rs = ps.executeQuery();
		    zfjgInf = new ZfjgInf();
		    if(rs.next()){
		    	
		    	//取列表中第一条数据
		    	if("0".equals(rs.getString("baqylx"))){//总机构
		    		//设置是否分摊所得税
		    		zfjgInf.setIsftsk(("1".equals(rs.getString("sfftsk")))?false:true);
		    		//设置总机构小微企业类型
		    		String xwqy = rs.getString("sndsfxwqy")==null?"":rs.getString("sndsfxwqy");
		    		zfjgInf.setXWQY(("1".equals(xwqy))?false:true);
		    	}else if("1".equals(rs.getString("baqylx"))){//分支机构
		    		//设置是否参与分摊企业所得税
		    		zfjgInf.setCyftsk(("1".equals(rs.getString("sfftsk")))?false:true);
		    		//获取分支机构小微企业类型
		    		String isxwqy = getXwqyById(conn,rs.getString("id"));
		    		zfjgInf.setFzjg_zjginf_XWQY(("1".equals(isxwqy))?false:true);
		    		//设置分支机构类型
		    		String fzjglxdm = rs.getString("fzjglxdm")==null?"":rs.getString("fzjglxdm");
		    		zfjgInf.setFzlx(fzjglxdm);
		    	    
		    	}else{//非总分机构
//		    		throw new ApplicationException("非总分机构不做备案");
		    		System.out.println("非总分机构不做备案");
		    	}
		    	//设置备案企业类型
		    	zfjgInf.setBaqylx(rs.getString("baqylx"));
		    	//设置备案起始时间
		    	//zfjgInf.setBaqssj(null==rs.getDate("BAQSSJ")?"":new SimpleDateFormat("yyyyMMdd").format(rs.getDate("BAQSSJ")));
		    	zfjgInf.setBaqssj(rs.getDate("BAQSSJ"));
		    	zfjgInf.setHasBasj(true);
		    	
		    }else{
		    	zfjgInf.setHasBasj(false);
		    }
		    System.out.println("----------------------zfjgInf="+zfjgInf);
		    
		  
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("查询总机构备案信息失败！"+e.getMessage());
		} finally{
			//关闭资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		return zfjgInf;
	}
	/*
	 * 为分支机构时获取小微企业类型
	 */
	public String getXwqyById(Connection conn,String id) throws FrameException{
		String xwqy = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = " SELECT SFXWQY FROM sfdb.SF_JL_FZJGBA_ZJGXX WHERE ID=?";
	    try{
	    	pst = conn.prepareStatement(sql);
	    	pst.setString(1, id);
	    	rs = pst.executeQuery();
	    	if(rs.next()){
	    		xwqy = rs.getString("SFXWQY");
	    	}
	    	
	    }catch(SQLException e){
	    	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    	e.printStackTrace();
			throw new FrameException("获取小微企业类型出错！");	    	
	    }finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
	    }
		
		return xwqy;
		
	}
	
	

}
