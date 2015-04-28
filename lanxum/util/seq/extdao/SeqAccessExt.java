package com.lscdz.util.seq.extdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

/**
 * 
 * 项目名称：BjgsFjmServer   
 * 类名称：SeqAccessExt
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-2-24 下午4:26:49   
 * 修改人：wangcy   
 * 修改时间：2014-2-24 下午4:26:49   
 * 修改备注：   
 * @version  1.0
 */
public class SeqAccessExt {
	/**
	 * 获取序列值
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
			throw new FrameException("访问序列号发生异常，请与管理员联系！");
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
