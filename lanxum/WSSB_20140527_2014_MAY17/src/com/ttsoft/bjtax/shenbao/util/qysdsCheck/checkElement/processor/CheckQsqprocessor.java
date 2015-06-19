/**
 * @Title:       CheckQsqprocessor.java
 * @Description: TODO
 * @date:        2014-5-30����11:51:37
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
 * @Description: TODO �鿴�Ƿ����������
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
//			// �������ݿ�����
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
	 * ���ݼ������������㱸�����״̬��ʶ��  1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4��������
	 * ȷ���Ƿ��������ں��������Ƿ������zhangj,2015.02.02�޸�
	 */
	public Object process(VOPackage vo) throws BaseException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CheckBean checkBean = (CheckBean)vo.getData();
		String jsjdmString = checkBean.getJsjdm();
		
		String sqlString = "select t.nsrjsjdm, t.SBSHZTBS from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t where nsrjsjdm= ? and bashztbs='2' ";
		try {
			// �������ݿ�����
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
