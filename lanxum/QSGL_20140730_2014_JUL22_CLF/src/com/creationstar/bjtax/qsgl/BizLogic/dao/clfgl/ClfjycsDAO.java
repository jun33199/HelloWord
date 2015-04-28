package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Clfjycs;
import com.ttsoft.common.util.Debug;

/**
 * 存量房交易参数表DAO
 * @author tutu
 * 2013-10-10(1)
 */
public class ClfjycsDAO extends BaseDAO {
	/**
     * 根据conditions获取房屋数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 房屋数据值对象的集合
     * @throws SQLException
     */
    public  ArrayList queryCsList(Connection conn,String conditions) throws
            SQLException {
        ArrayList CsList = new ArrayList();
        String sql = "select  cslx,dyz,zxbs,cjr,cjrq,lrr,lrrq,cslxms,swjgzzjgdm from dmdb.qs_dm_clfjycsb " + conditions;
        
        Debug.out("queryCsList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	Clfjycs cs = new Clfjycs();
            	
            	cs.setCslx(rs.getString("cslx"));
            	cs.setDyz(rs.getString("dyz"));
            	cs.setZxbs(rs.getString("zxbs"));
            	cs.setCjr(rs.getString("cjr"));
            	cs.setCjrq(rs.getTimestamp("cjrq"));
            	cs.setLrr(rs.getString("lrr"));
            	cs.setLrrq(rs.getTimestamp("lrrq"));
            	cs.setCslxms(rs.getString("cslxms"));
            	cs.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
               
            	CsList.add(cs);
            }
            System.out.println(" CsList size:"+CsList.size());
            
            rs.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw e;
        } finally {
            ps.close();
        }
        return CsList;
    }
    
    
    /**
     * 保存新增信息
     * @param newAddInfoList
     * @return
     * @throws Exception   
     */
    public boolean saveNewAdd(Connection conn,ArrayList newAddInfoList) throws Exception{
    	boolean isSucc = false;
    	PreparedStatement ps = null;
    	try{
    		String saveSql = "insert into dmdb.qs_dm_clfjycsb (CSLX, DYZ, CSLXMS, ZXBS, SWJGZZJGDM, CJRQ, CJR, LRRQ, LRR)values (?,?,?,?,?,sysdate,?,sysdate,?)";
    		ps = conn.prepareStatement(saveSql);
    		for(int index =0; index < newAddInfoList.size(); index ++){
    			Clfjycs cs = (Clfjycs) newAddInfoList.get(index);
    			int i =1;
    			ps.setString(i++, "02");
    			ps.setString(i++, cs.getDyz());
    			ps.setString(i++, cs.getCslxms());
    			ps.setString(i++, cs.getZxbs());
    			ps.setString(i++, cs.getSwjgzzjgdm());
    			ps.setString(i++, cs.getCjr());
    			ps.setString(i++, cs.getLrr());
    			
    			ps.addBatch();
    		}
    		 ps.executeBatch();
    		 isSucc = true;
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	} finally {
            ps.close();
        }
    	return isSucc;
    }
    
    
    /**
     * 删除
     * @param conn
     * @param dyz
     * @return
     * @throws Exception
     */
    public boolean delete(Connection conn,String dyz) throws Exception{
    	
    	boolean isSucc = false;
    	PreparedStatement ps = null;
    	try{
    		//保存到历史表
    		String toHis = "insert into dmdb.qs_dm_clfjycsb_ls (XH,CSLX, DYZ, CSLXMS, ZXBS, SWJGZZJGDM, CJRQ, CJR, LRRQ, LRR)" +
    				"              select QSDB.SEQ_QS_CLFJYXH.nextval,CSLX,DYZ, CSLXMS, ZXBS, SWJGZZJGDM, CJRQ, CJR, LRRQ, LRR from " +
    				"               dmdb.qs_dm_clfjycsb where dyz=? and cslx='02'";
    		
    		ps = conn.prepareStatement(toHis);
    		ps.setString(1, dyz);
    		isSucc = ps.execute();
    		
    		
    		
    		
    		//执行删除
    		String delete = "delete from dmdb.qs_dm_clfjycsb where dyz=? and cslx='02'";
    		ps = conn.prepareStatement(delete);
    			ps.setString(1, dyz);
    			
    			isSucc = ps.execute();
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	} finally {
            ps.close();
        }
    	return isSucc;   	
    }
    
    
    public boolean isExist(Connection conn,String dyz)throws Exception{
    	boolean isExist = false;
    	PreparedStatement ps = null;
    	try{
    		String sql = "select * from dmdb.qs_dm_clfjycsb where dyz =?";
    		
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, dyz);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()){
    			isExist = true;
    			return isExist;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	} finally {
            ps.close();
        }    	
    	
    	return isExist;
    }
    
    
    
    
    
    
    
    
    
    
    
}
