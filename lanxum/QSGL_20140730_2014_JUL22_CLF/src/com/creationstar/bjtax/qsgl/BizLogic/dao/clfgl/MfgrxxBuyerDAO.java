package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.ttsoft.common.util.Debug;

/**
 * 买方个人信息DAO
 * @author tutu
 * 2013-05-10(1)
 */
public class MfgrxxBuyerDAO extends BaseDAO {
/**
 * 保存信息
 * @methodName:InsertMfgrxxBuyerList
 * @function:
 * 
 * @param conn
 * @param buyerList
 * @throws SQLException
 * @author:唐昌富
 * @create date：2013-5-14 上午10:25:12
 * @version 1.1
 * 
 *
 */
    public  void InsertMfgrxxBuyerList(Connection conn,List buyerList) throws SQLException {
    	System.out.println("+++++++++保存买方个人信息常长度+++++++++"+buyerList.size());
    	String sql = "insert into qsdb.qs_jl_mfgrxx_buyer (sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,swjgzzjgdm,lb)  values(?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?)";
    	Debug.out("InsertfgrxxBuyerList:"+sql);
    	PreparedStatement ps = null;
    	try {
    	ps = conn.prepareStatement(sql);
    	for(int index =0;index <buyerList.size();index ++){
    		MfgrxxBuyer mfgrxxBuyer = (MfgrxxBuyer)buyerList.get(index);
    		int j=1;
    		ps.setString(j++, mfgrxxBuyer.getSbbh());
    		ps.setString(j++, mfgrxxBuyer.getHtbh());
    		ps.setBigDecimal(j++, new BigDecimal(mfgrxxBuyer.getSxh()));
    		ps.setString(j++, mfgrxxBuyer.getNsrmc());
    		ps.setString(j++, mfgrxxBuyer.getZjlxdm());
    		ps.setString(j++, mfgrxxBuyer.getZjlxmc());
    		ps.setString(j++, mfgrxxBuyer.getZjhm());
    		ps.setString(j++, mfgrxxBuyer.getQlrfe());
    		ps.setString(j++, mfgrxxBuyer.getLxdh());
    		ps.setString(j++, mfgrxxBuyer.getCjr());
    		ps.setString(j++, mfgrxxBuyer.getLrr());
    		ps.setString(j++, mfgrxxBuyer.getSwjgzzjgdm());
    		ps.setString(j++, mfgrxxBuyer.getLb());
    		ps.addBatch();
    		
    	}
    	//执行批量操作
    	ps.executeBatch();
    	}catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
    	
    }
	
	/**
     * 根据conditions获取买方个人信息数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 买方个人信息数据值对象的集合
     * @throws SQLException
     */
    public  ArrayList queryMfgrxxBuyerList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfgrxxBuyerList = new ArrayList();
        String sql = "select  sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,lb  from qsdb.qs_jl_mfgrxx_buyer " + conditions+" order by sxh asc";
        
        //Debug.out("queryMfgrxxBuyerList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
            	
            	mfgrxxBuyerItem.setSbbh(rs.getString("sbbh"));
            	mfgrxxBuyerItem.setHtbh(rs.getString("htbh"));
            	mfgrxxBuyerItem.setSxh(rs.getString("sxh"));
            	mfgrxxBuyerItem.setNsrmc(rs.getString("nsrmc"));
            	mfgrxxBuyerItem.setZjlxdm(rs.getString("zjlxdm"));
            	mfgrxxBuyerItem.setZjlxmc(rs.getString("zjlxmc"));
            	mfgrxxBuyerItem.setZjhm(rs.getString("zjhm"));
            	mfgrxxBuyerItem.setQlrfe(rs.getString("qlrfe"));
            	mfgrxxBuyerItem.setLxdh(rs.getString("lxdh"));
            	mfgrxxBuyerItem.setCjr(rs.getString("cjr"));
            	mfgrxxBuyerItem.setCjrq(rs.getTimestamp("cjrq"));
            	mfgrxxBuyerItem.setLrr(rs.getString("lrr"));
            	mfgrxxBuyerItem.setLrrq(rs.getTimestamp("lrrq"));
            	mfgrxxBuyerItem.setLb(rs.getString("lb"));
               
            	mfgrxxBuyerList.add(mfgrxxBuyerItem);
            }
           // System.out.println(" mfgrxxBuyerList size:"+mfgrxxBuyerList.size());
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfgrxxBuyerList;
    }
    
	 /**
	  * 保存买方信息到历史表
	  * @return
	  */
	public boolean saveBuyyerInfo2his (String sbbh,String scrydm, Connection conn)throws SQLException{
		 boolean isSuccess = false;
		 PreparedStatement ps = null;
		 
		 //删除时拼接删除人员和删除时间SQL
		 String scrySQL=",'' SCCZRY,null SCRQ";
		 if(scrydm != null && !"".equals(scrydm)){
			 scrySQL=",'"+scrydm+"' SCCZRY,sysdate SCRQ";
		 }
		 
		 try {
		 //1.拼接插入买卖方历史表SQL
		 StringBuffer MMF_HisSQLBuff = new StringBuffer();
		 MMF_HisSQLBuff.append(" insert into QSDB.QS_JL_MMFGRXX_LS (XH,MMFBS,SBBH, HTBH, SXH, NSRMC, ZJLXDM, ZJLXMC, ZJHM, QLRFE, LXDH, SWJGZZJGDM, CJR, CJRQ, LRR, LRRQ, LB,SCCZRY,SCRQ)"
				 			  +" select QSDB.SEQ_QS_CLFJYXH.nextval xh,'0' MMFBS,SBBH, HTBH, SXH, NSRMC, ZJLXDM, ZJLXMC, ZJHM, QLRFE, LXDH, SWJGZZJGDM, CJR, CJRQ, LRR, LRRQ, LB");
		 MMF_HisSQLBuff.append(scrySQL);
		 MMF_HisSQLBuff.append(" from qsdb.qs_jl_mfgrxx_buyer where sbbh = ? ");
		 
		 System.out.println("MMF_HisSQLBuff##########"+MMF_HisSQLBuff.toString());
		 
		 ps = conn.prepareStatement(MMF_HisSQLBuff.toString()); 
		 
		 ps.setString(1, sbbh);
		 
		 isSuccess = ps.execute();
		 
		 } catch (SQLException e) {
	            throw e;
	        } finally {
	            ps.close();
	        }
		 return isSuccess;		
	}
	
	 /**
	  * 删除买方信息
	  * @param htbh
	  * @param sbbh
	  * @param conn
	  * @return
	  */
	 public boolean delBuyyerInfo(String sbbh, Connection conn)throws SQLException{
		 boolean isSuccess = false;
		 PreparedStatement ps = null;
		 try {
			 String delSQL = "delete from qsdb.qs_jl_mfgrxx_buyer where sbbh=?";
			 ps = conn.prepareStatement(delSQL.toString()); 

			 ps.setString(1, sbbh);
			 
			 isSuccess = ps.execute();			 
		 } catch (SQLException e) {
	            throw e;
	        } finally {
	            ps.close();
	        }
		 
		 return isSuccess;
	 }
    
	 
		/**
	     * 契税根据conditions获取买方个人信息数据
	     * @param conditions 含有where 的条件字句
	     * @param conn 数据库连接对象
	     * @return ArrayList 契税查询买方个人信息数据值对象的集合
	     * @throws SQLException
	     */
	    public  ArrayList queryMfgrxxBuyerListForQs(Connection conn,String conditions) throws
	            SQLException {
	        ArrayList mfgrxxBuyerList = new ArrayList();
	        String sql = "select  sbbh,htbh,sxh,nsrmc,zjlxdm,zjlxmc,zjhm,qlrfe,lxdh,cjr,cjrq,lrr,lrrq,lb  from qsdb.qs_jl_mfgrxx_buyer " + conditions+" order by sxh asc";
	        
	        Debug.out("queryMfgrxxBuyerList:"+sql);
	        
	        PreparedStatement ps = null;
	        try {
	            ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) 
	            {
	            	MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
	            	
	            	mfgrxxBuyerItem.setSbbh(rs.getString("sbbh"));
	            	mfgrxxBuyerItem.setHtbh(rs.getString("htbh"));
	            	mfgrxxBuyerItem.setSxh(rs.getString("sxh"));
	            	mfgrxxBuyerItem.setNsrmc(rs.getString("nsrmc"));
	            	mfgrxxBuyerItem.setZjlxdm(rs.getString("zjlxdm"));
	            	mfgrxxBuyerItem.setZjlxmc(rs.getString("zjlxmc"));
	            	mfgrxxBuyerItem.setZjhm(rs.getString("zjhm"));
	            	mfgrxxBuyerItem.setQlrfe(rs.getString("qlrfe"));
	            	mfgrxxBuyerItem.setLxdh(rs.getString("lxdh"));
	            	mfgrxxBuyerItem.setCjr(rs.getString("cjr"));
	            	mfgrxxBuyerItem.setCjrq(rs.getTimestamp("cjrq"));
	            	mfgrxxBuyerItem.setLrr(rs.getString("lrr"));
	            	mfgrxxBuyerItem.setLrrq(rs.getTimestamp("lrrq"));
	            	mfgrxxBuyerItem.setLb(rs.getString("lb"));
	               
	            	mfgrxxBuyerList.add(mfgrxxBuyerItem);
	            }
	            System.out.println(" mfgrxxBuyerList size:"+mfgrxxBuyerList.size());
	            
	            rs.close();
	        } catch (SQLException e) {
	        	throw e;
	        } finally {
	            ps.close();
	        }
	        return mfgrxxBuyerList;
	    }
    
}
