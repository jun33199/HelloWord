/**
 * @Title:       IsInQsqObserver.java
 * @Description: TODO
 * @date:        2014-4-15上午11:03:15
 * @version:     V1.0
 */
package com.lscdz.qysds.common.service.qysdsCheck.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;

/**
 * 清算期校验器
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-19 上午11:04:40
 */
public class IsInQsqObserver{
	/**
	 *是否进入清算期，清算期结束不能申报，进入清算期仍然可以申报（需求改动2015.02.02）
	 * @throws ApplicationException 
	 */
	public void update(Connection conn,CheckBean checkBean) throws FrameException {
		
		try {
			checkBean = this.getInQsq(conn,checkBean);
		} catch (Exception e) {
			throw new FrameException("无法查看该企业是否进入清算期");
		}
		
		if(checkBean == null || checkBean.getJsjdm()==null || "".equals(checkBean.getJsjdm()))
		{
			throw new FrameException("无法查看该企业是否进入清算期");
		}
		
//		if(checkBean.isInQsq())
//		{
//			throw new FrameException("该企业进入清算期，无法进行当前操作");
//		}
		
		if(checkBean.isFinishQs()){
			throw new FrameException("该企业清算期已结束（已完成清算申报），无法进行当前操作");
		}
		
		return;
	}
	/**
	 * 根据计算机代码和清算备案审核状态标识（  1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销）
	 * 确定是否处于清算期
	 * @param checkBean
	 * @return
	 * @throws FrameException
	 */
	public CheckBean getInQsq(Connection conn,CheckBean checkBean) throws FrameException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String jsjdmString = checkBean.getJsjdm();
		
		String sqlString = "select t.nsrjsjdm, t.SBSHZTBS from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t where nsrjsjdm= ? and bashztbs='2' ";
		
		try {			
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
			e.printStackTrace();
			throw new FrameException(e.getMessage());
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

	    } 
        
		return checkBean;
	}
}
