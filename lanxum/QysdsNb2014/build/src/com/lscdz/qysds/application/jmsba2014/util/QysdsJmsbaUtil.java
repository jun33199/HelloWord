package com.lscdz.qysds.application.jmsba2014.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.lscdz.qysds.common.util.QysdsHelperUtil;

import yangjian.frame.util.FrameException;



public class QysdsJmsbaUtil{
	/**
	 * ���ɱ���������
	 * @param qxdm:���ش��룻band���참���
	 * @return basqbh
	 * @throws FrameException 
	 */
	public static HashMap getBasqbh(String qxdm,String band,String yhid,String jmbasxdm,Connection conn) throws FrameException{
		HashMap basq = new HashMap();
		CallableStatement cs = null;
		PreparedStatement pst = null;
		try {
			String prcName1 = "BEGIN SFDB.SF_PKG_QYSDSJMBA.main(?,?,?,?,?,?,?); END;";
			cs = conn.prepareCall(prcName1);
			cs.setString(1, qxdm);//��½�û������ش���
			cs.setString(2, band);//�������
			cs.setString(3, yhid);//�û�ID
			cs.setString(4, jmbasxdm);//���ⱸ���������
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
		    cs.execute();

		    if("0".equals(cs.getObject(5))){
		    	basq.put("basqwsh", cs.getObject(6));
		    	basq.put("basqbh", cs.getObject(7));
		    }else{
		    	throw new FrameException("��ȡ�������������ʧ�ܣ������²�����");
		    }
		}catch (Exception ex) {
			throw new FrameException("��ѯ���ݿ�ʧ��"+ex.getMessage());
		}finally{
			if (pst != null) {try {pst.close();} catch (SQLException e) {e.printStackTrace();}}
			if (cs != null) {try {cs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return basq;
	}
		
	
	/**
	 * 
	 * @param conn
	 * @return
	 * @throws FrameException
	 */
	public static String getSequence(Connection conn) throws FrameException{
		String sequence = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT SFDB.SEQ_SF_QYSDSJMSBAJL.NEXTVAL FROM DUAL ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				sequence = rs.getString(1);
			}
			
		}catch (Exception ex) {
			throw new FrameException("sqlִ��ʧ��"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(pst, rs, null);
		}
		return sequence;
	}
	
	/**
	 * ������������״̬
	 * @param basqwsh����������ţ�sqzt������״̬; czr��������
	 * @return basqbh
	 * @throws FrameException 
	 */
	public  static boolean updateSqzt(String basqwsh, String sqzt,String czr,Connection conn) throws FrameException{
		boolean czzt = false;
		PreparedStatement pst = null;
		try {
			//�����������״̬
			String updateZbSql = "UPDATE sfdb.sf_jl_qysdsjmsbajl t " +
					"SET t.sqzt = '"+sqzt+"',t.SHR = '"+czr+"',t.SHSJ = SYSDATE,t.lrr = '"+czr+"',t.lrrq = SYSDATE " +
					"WHERE t.basqwsh = '"+basqwsh+"'";
			
			pst = conn.prepareStatement(updateZbSql);
			pst.executeUpdate();
			czzt = true;
		}catch (Exception ex) {
			throw new FrameException("������������״̬ʧ��"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(pst, null, null);
		}
		return czzt;
	}
	
	/**
	 * �����������״̬
	 * @param basqwsh�����������
	 * @return basqbh
	 * @throws FrameException 
	 */
	public  static boolean checkSqzt(String basqwsh,Connection conn) throws FrameException{
		boolean czzt = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql=" select sqzt from sfdb.sf_jl_qysdsjmsbajl where basqwsh='"+basqwsh+"'" ;
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			StringBuffer sb=new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1));
			}
			if ("3".equals(sb.toString()) || "2".equals(sb.toString())) {
				czzt = true;
			}

		}catch (Exception ex) {
			throw new FrameException("�����������״̬ʧ��"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, null);
		}
		return czzt;
	}
	
	 /**
     * �ַ���ֵ��֤
     * @param value String ����֤��ֵ
     * @return boolean
     */
    public static boolean strNotNull(String value) {
        if (value == null || value.trim().length() == 0) {
            return false;
        }
        return true;
    }
}