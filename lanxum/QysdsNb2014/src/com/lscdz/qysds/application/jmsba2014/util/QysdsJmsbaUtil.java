package com.lscdz.qysds.application.jmsba2014.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaZlqdVo;
import com.lscdz.qysds.common.util.QysdsHelperUtil;

import yangjian.frame.util.FrameException;



public class QysdsJmsbaUtil{
	/**
	 * 生成备案申请编号
	 * @param qxdm:区县代码；band：办案年度
	 * @return basqbh
	 * @throws FrameException 
	 */
	public static HashMap getBasqbh(String ssdwdm,String band,String jsjdm,String jmbasxdm,Connection conn) throws FrameException{
		HashMap basq = new HashMap();
		CallableStatement cs = null;
		PreparedStatement pst = null;
		try {
			String prcName1 = "BEGIN SFDB.SF_PKG_QYSDSJMBA.main(?,?,?,?,?,?,?); END;";
			cs = conn.prepareCall(prcName1);
			cs.setString(1, ssdwdm.substring(0,2));//登陆用户的区县代码
			cs.setString(2, band);//备案年度
			cs.setString(3, jsjdm);//计算机代码
			cs.setString(4, jmbasxdm);//减免备案事项代码
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
		    cs.execute();

		    if("0".equals(cs.getObject(5))){
		    	basq.put("basqwsh", cs.getObject(6));
		    	basq.put("basqbh", cs.getObject(7));
		    }else{
		    	throw new FrameException("获取备案申请文书号失败，请重新操作！");
		    }
		}catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			throw new FrameException(ex.getMessage());
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			throw new FrameException("sql执行失败"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(pst, rs, null);
		}
		return sequence;
	}
	
	/**
	 * 更新主表申请状态
	 * @param basqwsh：备案文书号；sqzt：申请状态; czr：操作人
	 * @return basqbh
	 * @throws FrameException 
	 */
	public  static boolean updateSqzt(String basqwsh, String sqzt,String czr,Connection conn) throws FrameException{
		boolean czzt = false;
		PreparedStatement pst = null;
		try {
			//更新主表审核状态
			String updateZbSql = "UPDATE SFDB.SF_JL_QYSDSJMSBAJL_2014 t " +
					"SET t.sqzt = '"+sqzt+"',t.SHR = '"+czr+"',t.SHSJ = SYSDATE,t.lrr = '"+czr+"',t.lrrq = SYSDATE " +
					"WHERE t.basqwsh = '"+basqwsh+"'";
			
			pst = conn.prepareStatement(updateZbSql);
			pst.executeUpdate();
			czzt = true;
		}catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("更新主表申请状态失败"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(pst, null, null);
		}
		return czzt;
	}
	
	
	/**
	 *  更新审核资料审核通过标识
	 * @param baseVo
	 * @param userData
	 * @param conn
	 * @return
	 * @throws FrameException
	 */
	public  static boolean updateZlqd(JmsbaBaseVo baseVo, Yh userData,Connection conn) throws FrameException{
		boolean czzt = false;
		PreparedStatement pst = null;
		try {
			String SQL = "";
			String sql = "";
			String SELECT = " SELECT ";
			String XH = " XH, ";
			String SHBJ = " SFSHTG FROM DUAL ";
			String UNION = " UNION ALL";
			for(int i = 0;i<baseVo.getZlqdList().size();i++){
				JmsbaZlqdVo zlqdVo=baseVo.getZlqdList().get(i);
				sql=SELECT+zlqdVo.getXh()+XH +zlqdVo.getSfshtg()+SHBJ;
				if(i!=(baseVo.getZlqdList().size()-1)){
					sql = sql + UNION;
				}
				SQL = SQL+sql;
			}
			//更新资料清单表中资料是否审核通过
			SQL = "UPDATE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 A SET A.SFSHTG = (SELECT B.SFSHTG FROM (" +SQL+
					") B WHERE A.XH = B.XH)WHERE A.BASQWSH = '"+baseVo.getBasqwsh()+"'";
			
			System.out.println(SQL);
			pst = conn.prepareStatement(SQL);
			pst.executeUpdate();
			czzt = true;
		}catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("更新资料清单申请状态失败"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(pst, null, null);
		}
		return czzt;
	}
	
	/**
	 * 检查主表申请状态
	 * @param basqwsh：备案文书号
	 * @return basqbh
	 * @throws FrameException 
	 */
	public  static boolean checkSqzt(String basqwsh,Connection conn) throws FrameException{
		boolean czzt = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql=" select sqzt from SFDB.SF_JL_QYSDSJMSBAJL_2014 where basqwsh='"+basqwsh+"'" ;
			
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			throw new FrameException("检查主表申请状态失败"+ex.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, null);
		}
		return czzt;
	}
	 /**
     * 字符空值验证
     * @param value String 待验证数值
     * @return boolean
     */
    public static boolean strNotNull(String value) {
        if (value == null || value.trim().length() == 0) {
            return false;
        }
        return true;
    }
}