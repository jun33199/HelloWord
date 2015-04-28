package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.ClfjyxxCX;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxzbSeller;

public class ClfjyxxCXDAO extends BaseDAO{
	
	/**
	 * 通过输入条件查询出所有满足的合同编号和申报编号
	 * @param conn
	 * @param conditions
	 * @return
	 * @throws SQLException
	 */
    public  ArrayList query(Connection conn,String conditions) throws
    SQLException {
    	
    	ArrayList list = new ArrayList();
    	 PreparedStatement ps = null;
    	 ResultSet rs=null;
    	String querySQL = "SELECT  distinct a.SBBH,a.HTBH FROM QSDB.QS_JL_MFSBXXZB_SELLER a,qsdb.qs_jl_mfgrxx_buyer b,qsdb.qs_jl_fwxxb c "+conditions;
    	
    	System.out.println("querySQL ::"+querySQL);
    	
    	
        try {
            ps = conn.prepareStatement(querySQL);
            
            rs= ps.executeQuery();
            
            while (rs.next()) 
            {
            	MfsbxxzbSeller mfsbxxzbSeller = new MfsbxxzbSeller();
            	
            	mfsbxxzbSeller.setSbbh(rs.getString("sbbh"));
            	mfsbxxzbSeller.setHtbh(rs.getString("htbh"));
            	
            	list.add(mfsbxxzbSeller);
            }
            
        } catch (SQLException e) {
            throw e;
        } finally {
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
    	return list;
    }
    
	
	
	
	/**
	 * 根据输入条件，查询出卖方征收信息主表中满足条件的sbbh和htbh ，结果为一个LIST
	 * @param conn
	 * @param conditions
	 * @return
	 * @throws SQLException
	 */
    public  ArrayList queryJyxxList(Connection conn,String sbbh,String htbh) throws
    Exception {
    	
    	String querySQL ="select a.SBBH,a.yes_JSJE_hj_02,a.yes_YNSE_hj_02,a.yes_JMSE_hj_02,a.yes_SJJE_hj_02," +
    			"b.grsds_JSJE_hj_05,b.grsds_YNSE_hj_05,b.grsds_JMSE_hj_05,b.grsds_SJJE_hj_05,b.grsds_sl_05," +
    			"a.ycjd_YNSE_hj_02_10_51_54,a.ycjd_JMSE_hj_02_10_51_54,a.ycjd_SJJE_hj_02_10_51_54," +
    			"a.yhs_YNSE_hj_16,a.yhs_JMSE_hj_16,a.yhs_SJJE_hj_16," +
    			"c.tdzzs_JSJE_hj_08,c.tdzzs_YNSE_hj_08,c.tdzzs_JMSE_hj_08,c.tdzzs_SJJE_hj_08,tdzzs_sl_08," +
    			"a.all_jsje_hj,a.all_ynse_hj,a.all_jmse_hj,a.all_sjje_hj  From ("+
"SELECT A.SBBH,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '02', JSJE)) yes_JSJE_hj_02,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '02', YNSE)) yes_YNSE_hj_02,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '02', JMSE)) yes_JMSE_hj_02,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '02', SJJE)) yes_SJJE_hj_02,"+
       "SUM(CASE WHEN SUBSTR(SZSMDM, 1, 2) IN ('02', '10', '51', '54') THEN YNSE END) ycjd_YNSE_hj_02_10_51_54,"+
       "SUM(CASE WHEN SUBSTR(SZSMDM, 1, 2) IN ('02', '10', '51', '54') THEN JMSE END) ycjd_JMSE_hj_02_10_51_54,"+
       "SUM(CASE WHEN SUBSTR(SZSMDM, 1, 2) IN ('02', '10', '51', '54') THEN SJJE END) ycjd_SJJE_hj_02_10_51_54,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '16', YNSE)) yhs_YNSE_hj_16,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '16', JMSE)) yhs_JMSE_hj_16,"+
       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '16', SJJE)) yhs_SJJE_hj_16,"+
       "SUM(JSJE) all_jsje_hj,"+
       "SUM(YNSE) all_ynse_hj,"+
       "SUM(JMSE) all_jmse_hj,"+
       "SUM(SJJE) all_sjje_hj "+
  "FROM QSDB.QS_JL_MFSBXXMX_SELLER A "+
 "WHERE A.SBBH   ='"+sbbh+"' "+
 "GROUP BY A.SBBH) a, (SELECT SBBH,SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '05', JSJE)) grsds_JSJE_hj_05,"+
                             "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '05', YNSE)) grsds_YNSE_hj_05,"+                            
                             "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '05', JMSE)) grsds_JMSE_hj_05,"+
                             "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '05', SJJE)) grsds_SJJE_hj_05,"+
                             "sl grsds_sl_05 "+
                        "FROM QSDB.QS_JL_MFSBXXMX_SELLER A "+
                       "WHERE A.SBBH ='"+sbbh+"' "+
                         "and SUBSTR(SZSMDM, 1, 2) = '05' "+
                       "GROUP BY A.SBBH, sl) b, "+
                       "(SELECT SBBH,SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '08', JSJE)) tdzzs_JSJE_hj_08,"+
                       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '08', YNSE)) tdzzs_YNSE_hj_08,"+
                       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '08', JMSE)) tdzzs_JMSE_hj_08,"+
                       "SUM(DECODE(SUBSTR(SZSMDM, 1, 2), '08', SJJE)) tdzzs_SJJE_hj_08,"+               
                       "sl tdzzs_sl_08 FROM QSDB.QS_JL_MFSBXXMX_SELLER A WHERE A.SBBH = '"+sbbh+"'  and SUBSTR(SZSMDM, 1, 2) = '08' GROUP BY A.SBBH, sl) c "+
                       "where a.sbbh = b.sbbh(+) and b.sbbh=c.sbbh(+)";
    	
    	
    	//System.out.println("querySQL------"+querySQL);
    	PreparedStatement ps = null;
    	ResultSet rs=null;
    	ArrayList resList = new ArrayList();
        try {
            ps = conn.prepareStatement(querySQL);
            
            rs = ps.executeQuery();
            
            while (rs.next()) 
            {
            	ClfjyxxCX clfjyxxCX = new ClfjyxxCX();
            	clfjyxxCX.setSbbh(rs.getString("SBBH"));
//            	clfjyxxCX.setSl(rs.getBigDecimal("SL"));
            	clfjyxxCX.setYys_JSJE_hj_02(rs.getBigDecimal("yes_JSJE_hj_02"));
            	clfjyxxCX.setYys_YNSE_hj_02(rs.getBigDecimal("yes_YNSE_hj_02"));
            	clfjyxxCX.setYys_JMSE_hj_02(rs.getBigDecimal("yes_JMSE_hj_02"));
            	clfjyxxCX.setYys_SJJE_hj_02(rs.getBigDecimal("yes_SJJE_hj_02"));            	
            	clfjyxxCX.setGrsds_JSJE_hj_05(rs.getBigDecimal("grsds_JSJE_hj_05"));
            	clfjyxxCX.setGrsds_YNSE_hj_05(rs.getBigDecimal("grsds_YNSE_hj_05"));
            	clfjyxxCX.setGrsds_JMSE_hj_05(rs.getBigDecimal("grsds_JMSE_hj_05"));
            	clfjyxxCX.setGrsds_SJJE_hj_05(rs.getBigDecimal("grsds_SJJE_hj_05"));
            	clfjyxxCX.setGrsds_sl_05(rs.getBigDecimal("grsds_sl_05"));
            	clfjyxxCX.setYcjd_YNSE_hj_02_10_51_54(rs.getBigDecimal("ycjd_YNSE_hj_02_10_51_54"));
            	clfjyxxCX.setYcjd_JMSE_hj_02_10_51_54(rs.getBigDecimal("ycjd_JMSE_hj_02_10_51_54"));
            	clfjyxxCX.setYcjd_SJJE_hj_02_10_51_54(rs.getBigDecimal("ycjd_SJJE_hj_02_10_51_54"));
            	clfjyxxCX.setYhs_YNSE_hj_16(rs.getBigDecimal("yhs_YNSE_hj_16"));
            	clfjyxxCX.setYhs_JMSE_hj_16(rs.getBigDecimal("yhs_JMSE_hj_16"));
            	clfjyxxCX.setYhs_SJJE_hj_16(rs.getBigDecimal("yhs_SJJE_hj_16"));
            	clfjyxxCX.setTdzzs_JSJE_hj_08(rs.getBigDecimal("tdzzs_JSJE_hj_08"));
            	clfjyxxCX.setTdzzs_YNSE_hj_08(rs.getBigDecimal("tdzzs_YNSE_hj_08"));
            	clfjyxxCX.setTdzzs_JMSE_hj_08(rs.getBigDecimal("tdzzs_JMSE_hj_08"));
            	clfjyxxCX.setTdzzs_SJJE_hj_08(rs.getBigDecimal("tdzzs_SJJE_hj_08"));
            	clfjyxxCX.setTdzzs_sl_08(rs.getBigDecimal("tdzzs_sl_08"));
            	clfjyxxCX.setAll_jsje_hj(rs.getBigDecimal("all_jsje_hj"));
            	clfjyxxCX.setAll_ynse_hj(rs.getBigDecimal("all_ynse_hj"));
            	clfjyxxCX.setAll_jmse_hj(rs.getBigDecimal("all_jmse_hj"));
            	clfjyxxCX.setAll_sjje_hj(rs.getBigDecimal("all_sjje_hj"));
            	
            	//获得契税申报信息（add by tangchangfu 2014-06-12）
            	this.getQSSBXXbyHtbh(conn, clfjyxxCX, htbh);//add end
            	resList.add(clfjyxxCX);
            }
            
            
            //System.out.println("sbbh =="+sbbh + "查询交易信息长度为+++"+resList.size());
            
        } catch (Exception e) {
            throw e;
        } finally {
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
    	return resList;
    }
    
    /**
     * 通过合同编号获取契税申报信息（add by tangchangfu 2014-06-12）
     * @param htbh
     */
    private void getQSSBXXbyHtbh(Connection conn,ClfjyxxCX clfjyxxCX,String htbh)throws Exception{
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try{
    		String querySQL ="select MAX(t.sl) sl, sum(t.ynse) ynse from qsdb.qs_jl_sbzb t where t.htbh='"+htbh+"'";
    		ps = conn.prepareStatement(querySQL);
    		rs = ps.executeQuery();
    		
    		while(rs.next()){
    			clfjyxxCX.setQs_sl(rs.getBigDecimal("sl"));
    			clfjyxxCX.setQs_jsje_hj(rs.getBigDecimal("ynse"));
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}finally{
//    		modified by zhangj for the bug that ORA-01000: maximum open cursors exceeded
//    		rs = null;
//    		ps = null;
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
    	
    	
    	
    	
    	
    	
    }
    

}
