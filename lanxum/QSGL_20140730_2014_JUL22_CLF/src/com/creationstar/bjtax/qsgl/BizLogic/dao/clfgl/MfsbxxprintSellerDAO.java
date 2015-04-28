package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxprintSeller;
import com.ttsoft.common.util.Debug;

/**
 * 卖方申报信息查询打印DAO
 * @author zhangyj
 * 2013-06-14
 */
public class MfsbxxprintSellerDAO extends BaseDAO {

	
    /**
     * 根据conditions获取卖方申报完税证数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 卖方申报完税证对象的集合
     * @throws SQLException
     */
    public  ArrayList queryMfsbxxWszList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxSellerList = new ArrayList();
        String sql = "select b.jsjdm,b.wszxh,b.ndzb,b.wszh,b.hjsjje from qsdb.qs_jl_htypzdzgxb a,sbdb.sb_jl_lsswszz b where a.mmfbz='1' and a.pzhm=b.wszh and a.pizzldm=b.pzzldm and a.pzndzb=b.ndzb " + conditions + " order by b.wszh";
        
        Debug.out("mfsbxxSellerList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();
            	
            	mfsbxxprintSellerItem.setJsjdm(rs.getString("jsjdm"));
            	mfsbxxprintSellerItem.setWszxh(rs.getString("wszxh"));
            	mfsbxxprintSellerItem.setNdzb(rs.getString("ndzb"));
            	mfsbxxprintSellerItem.setWszh(rs.getString("wszh"));
            	mfsbxxprintSellerItem.setHjsjje(rs.getBigDecimal("hjsjje"));
               
            	mfsbxxSellerList.add(mfsbxxprintSellerItem);
            }
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxSellerList;
    }
    
    /**
     * 根据conditions获取卖方申报缴款书数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 卖方申报完税证对象的集合
     * @throws SQLException
     */
    public  ArrayList queryMfsbxxJksList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxSellerList = new ArrayList();
        String sql = "select b.jsjdm,b.sbbh,sum(b.rkje) rkje from qsdb.qs_jl_htypzdzgxb a,sbdb.sb_jl_sbjkzb b where a.mmfbz='1' and a.sbbh=b.sbbh and a.pzhm=b.jkpzh " + conditions + " group by b.jsjdm,b.sbbh order by b.sbbh";
        
        Debug.out("mfsbxxSellerList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();
            	
            	mfsbxxprintSellerItem.setJsjdm(rs.getString("jsjdm"));
            	mfsbxxprintSellerItem.setSbbh(rs.getString("sbbh"));
            	mfsbxxprintSellerItem.setRkjehj(rs.getBigDecimal("rkje"));
               
            	mfsbxxSellerList.add(mfsbxxprintSellerItem);
            }
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxSellerList;
    }    

    /**
     * 根据conditions获取完税证主表数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 完税证主表数据值对象的集合
     * @throws SQLException
     */
    public  ArrayList queryWszzbList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxSellerList = new ArrayList();
        String sql = "select a.wszh,a.cjrq,a.jsjdm,a.nsrmc,a.dz,a.hjsjje from sbdb.sb_jl_lsswszz a " + conditions +" order by a.wszh desc";
        
        Debug.out("mfsbxxSellerList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) 
            {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();
            	
            	mfsbxxprintSellerItem.setWszh(rs.getString("wszh"));
            	mfsbxxprintSellerItem.setCjrq(rs.getTimestamp("cjrq"));
            	mfsbxxprintSellerItem.setJsjdm(rs.getString("jsjdm"));
            	mfsbxxprintSellerItem.setNsrmc(rs.getString("nsrmc"));            	
                mfsbxxprintSellerItem.setDz(rs.getString("dz"));         	
            	mfsbxxprintSellerItem.setHjsjje(rs.getBigDecimal("hjsjje"));
 
            	mfsbxxSellerList.add(mfsbxxprintSellerItem);
            }
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxSellerList;
    }
    
    
    /**
     * 根据conditions获取完税证明细表数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 完税证主表数据值对象的集合
     * @throws SQLException
     */
    public  ArrayList queryWszmxList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxSellerList = new ArrayList();
        String sql = "select a.kssl,a.jsje,a.sl,a.yjhkc,a.sjse,a.skssksrq,a.skssjsrq,a.szdm,a.szsmdm from sbdb.sb_jl_lsswszmx a " + conditions +" order by wszh desc";
        
        Debug.out("mfsbxxSellerList:"+sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) 
            {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();

                mfsbxxprintSellerItem.setKssl(rs.getBigDecimal("kssl"));
                mfsbxxprintSellerItem.setJsje(rs.getBigDecimal("jsje"));
                mfsbxxprintSellerItem.setSl(rs.getBigDecimal("sl"));
                mfsbxxprintSellerItem.setYjhkc(rs.getBigDecimal("yjhkc"));
                mfsbxxprintSellerItem.setSjse(rs.getBigDecimal("sjse"));              
            	mfsbxxprintSellerItem.setSkssksrq(rs.getTimestamp("skssksrq"));
            	mfsbxxprintSellerItem.setSkssjsrq(rs.getTimestamp("skssjsrq"));
                mfsbxxprintSellerItem.setSzdm(rs.getString("szdm"));
                mfsbxxprintSellerItem.setSzsmdm(rs.getString("szsmdm"));
                
            	mfsbxxSellerList.add(mfsbxxprintSellerItem);
            }
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxSellerList;
    }
    
    /**
     * 根据conditions获取缴款书数据
     * @param conditions 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 缴款书数据值对象的集合
     * @throws SQLException
     */
    public  ArrayList queryJksList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxSellerList = new ArrayList();
        String sql = "SELECT zb.szdm AS szdm,mx.szsmdm AS szsmdm,mx.sjse AS sjse,zb.swjgzzjgdm AS swjgzzjgdm,zb.bz AS bz,zb.sbrq AS sbrq, zb.yhmc AS yhmc,zb.zh AS yhzh,mx.yskmdm AS yskmdm,zb.gkzzjgdm AS gkzzjgdm FROM sbdb.sb_jl_sbjkmx mx,sbdb.sb_jl_sbjkzb zb " + conditions +" AND mx.jkpzh=zb.jkpzh  AND zb.sjly='17' ORDER BY mx.szsmdm,mx.yskmdm";
        
        Debug.out("queryJksList:"+sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) 
            {
            	MfsbxxprintSeller mfsbxxprintSellerItem = new MfsbxxprintSeller();

                mfsbxxprintSellerItem.setSzdm(rs.getString("szdm"));
                mfsbxxprintSellerItem.setSzsmdm(rs.getString("szsmdm"));
                mfsbxxprintSellerItem.setSjse(rs.getBigDecimal("sjse"));
                mfsbxxprintSellerItem.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                mfsbxxprintSellerItem.setBz(rs.getString("bz"));
            	mfsbxxprintSellerItem.setSbrq(rs.getTimestamp("sbrq"));
                mfsbxxprintSellerItem.setYhmc(rs.getString("yhmc"));
                mfsbxxprintSellerItem.setYhzh(rs.getString("yhzh"));
                mfsbxxprintSellerItem.setYskmdm(rs.getString("yskmdm"));
                mfsbxxprintSellerItem.setGkzzjgdm(rs.getString("gkzzjgdm"));
                
            	mfsbxxSellerList.add(mfsbxxprintSellerItem);
            }
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxSellerList;
    }    
    
}
