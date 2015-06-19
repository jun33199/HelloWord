package com.lscdz.util.seq.extdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

/**
 * 
 * ��Ŀ���ƣ�BjgsFjmServer   
 * �����ƣ�SeqAccessExt
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-24 ����4:26:49   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-24 ����4:26:49   
 * �޸ı�ע��   
 * @version  1.0
 */
public class SeqAccessExt {
	/**
	 * ��ȡ����ֵ
	 * @param seqName
	 * @return
	 * @throws FrameException
	 */
	public static synchronized String getSequenceVal(String seqName) throws FrameException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String buf = null;
		try {
			con = ResourceManager.getConnection();
			st = con.createStatement();
			buf = "SELECT "+seqName+".NEXTVAL AS VAL FROM DUAL ";
			rs = st.executeQuery(buf);
			if (!rs.next())
				return "0";
			String seqValue = rs.getString("VAL");
			return seqValue;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("�������кŷ����쳣���������Ա��ϵ��");
		} finally {
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception localException3){
			}
		}
	}
}
