/**
 * @Title:       IsInQsqObserver.java
 * @Description: TODO
 * @date:        2014-4-15����11:03:15
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
 * ������У����
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-19 ����11:04:40
 */
public class IsInQsqObserver{
	/**
	 *�Ƿ���������ڣ������ڽ��������걨��������������Ȼ�����걨������Ķ�2015.02.02��
	 * @throws ApplicationException 
	 */
	public void update(Connection conn,CheckBean checkBean) throws FrameException {
		
		try {
			checkBean = this.getInQsq(conn,checkBean);
		} catch (Exception e) {
			throw new FrameException("�޷��鿴����ҵ�Ƿ����������");
		}
		
		if(checkBean == null || checkBean.getJsjdm()==null || "".equals(checkBean.getJsjdm()))
		{
			throw new FrameException("�޷��鿴����ҵ�Ƿ����������");
		}
		
//		if(checkBean.isInQsq())
//		{
//			throw new FrameException("����ҵ���������ڣ��޷����е�ǰ����");
//		}
		
		if(checkBean.isFinishQs()){
			throw new FrameException("����ҵ�������ѽ���������������걨�����޷����е�ǰ����");
		}
		
		return;
	}
	/**
	 * ���ݼ������������㱸�����״̬��ʶ��  1�����ύδ��ˣ�2�������ͨ����3����˱����أ�4��������
	 * ȷ���Ƿ���������
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
