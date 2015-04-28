/**
 * @Title:       CheckQsqprocessor.java
 * @Description: TODO
 * @date:        2014-5-30上午11:51:37
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO 查看是否进入清算期
 * @author: 	 Lijn
 * @time:        2014-5-30
 */
public class CheckQsqprocessor  implements Processor {

//	public Object process(VOPackage vo) throws BaseException {
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		CheckBean checkBean = (CheckBean)vo.getData();
//		String jsjdmString = checkBean.getJsjdm();
//		
//		String sqlString = " select t.nsrjsjdm from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t where nsrjsjdm= ? and bashztbs='2' ";
//		
//		try {
//			// 创建数据库连接
//			conn = DBResource.getConnection();
//			
//			ps = conn.prepareStatement(sqlString);
//			ps.setString(1, jsjdmString);
//			rs = ps.executeQuery();
//			if(rs!=null && rs.next())
//			{
//				checkBean.setInQsq(true);
//			}else{
//				checkBean.setInQsq(false);
//			}
//			
//		} catch (SQLException e) {
//			throw ExceptionUtil.getBaseException(e);
//		}
//        
//		return checkBean;
//	}
	/**
	 * 根据计算机代码和清算备案审核状态标识（  1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销）
	 * 确定是否处于清算期和清算期是否结束，zhangj,2015.02.02修改
	 */
	public Object process(VOPackage vo) throws BaseException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CheckBean checkBean = (CheckBean)vo.getData();
		String jsjdmString = checkBean.getJsjdm();
		
		String sqlString = "select t.nsrjsjdm, t.SBSHZTBS from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t where nsrjsjdm= ? and bashztbs='2' ";
		try {
			// 创建数据库连接
			conn = DBResource.getConnection();
			
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, jsjdmString);
			rs = ps.executeQuery();
			if(rs!=null && rs.next())
			{
				checkBean.setInQsq(true);
				if("2".equals(rs.getString("SBSHZTBS"))){
					checkBean.setFinishQs(true);
				}else {
					checkBean.setFinishQs(false);
				}				
			}else{
				checkBean.setInQsq(false);
				checkBean.setFinishQs(false);
			}
			
		} catch (SQLException e) {
			throw ExceptionUtil.getBaseException(e);
		}finally
        {
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
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    }
        
		return checkBean;
	}
}
