package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QyqssdsSaveAndCheck {
	/**
	 * ���±��еı�ʶΪ���ύ
	 * @throws BaseException 
	 */
	public static void updateFlag(Connection conn,QyqssdsBaseForm form) throws BaseException{
		//�걨���״̬��ʶ
		String flag = form.getSbShztbs();
		if(!flag.equals("")&&flag!=null){
			return ;
		}
		String sql = "update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set sbshztbs='1',QSSBKSRQ=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm='"+form.getJsjdm()+"'";
		PreparedStatement stmt =null;
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, form.getQssbksrq().substring(0, 10));//�����걨��ʼ����
			stmt.setString(2, form.getQssbjsrq().substring(0,10));//�����걨��������
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
			
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
