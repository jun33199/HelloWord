package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxzbSeller;
import com.ttsoft.common.util.Debug;

/**
 * �����걨��Ϣ������VO
 * @author zhangyj
 * 2013-06-08
 */
public class MfsbxxzbSellerDAO extends BaseDAO {

	/**
     * ����һ�������걨��Ϣ���������ݼ�¼
     * @param MfsbxxzbSeller �����걨��Ϣ���������
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public  void insert(MfsbxxzbSeller ms, Connection conn) throws
            SQLException {
        String sql = "insert into qsdb.qs_jl_mfsbxxzb_seller (sbbh,htbh,nsrmc,zjlxdm,zjlxmc,zjhm,jsjdm,djzclxdm,gjbzhydm,lsgxdm,swjgzzjgdm,skxjrq,ynhjje,jmhjje,sjhjje,cjr,cjrq,lrr,lrrq,run_bz,qs_sbbh) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);            
            
            ps.setString(1, ms.sbbh);
            ps.setString(2, ms.htbh);
            ps.setString(3, ms.nsrmc);
            ps.setString(4, ms.zjlxdm);
            ps.setString(5, ms.zjlxmc);
            ps.setString(6, ms.zjhm);
            ps.setString(7, ms.jsjdm);
            ps.setString(8, ms.djzclxdm);
            ps.setString(9, ms.gjbzhydm);
            ps.setString(10, ms.lsgxdm);
            ps.setString(11, ms.swjgzzjgdm);
            ps.setTimestamp(12, ms.skxjrq);
            ps.setBigDecimal(13, ms.ynhjje);
            ps.setBigDecimal(14, ms.jmhjje);
            ps.setBigDecimal(15, ms.sjhjje);
            ps.setString(16, ms.cjr);
            ps.setString(17, ms.lrr);
            ps.setString(18, ms.run_bz);
            ps.setString(19, ms.qs_sbbh);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * ����conditions��ȡ�����걨��Ϣ��������
     * @param conditions ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �����걨��Ϣ��������ֵ����ļ���
     * @throws SQLException
     */
    public  ArrayList queryMfsbxxzbList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxzbSellerList = new ArrayList();
        //
        String sql = "select sbbh,htbh,sjhjje,nsrmc from qsdb.qs_jl_mfsbxxzb_seller a " + conditions + " not exists (select a.htbh from qsdb.qs_jl_htypzdzgxb b where b.mmfbz='1' and a.htbh=b.htbh) order by cjrq";
        
        Debug.out("queryMfsbxxzbList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfsbxxzbSeller mfsbxxzbSellerItem = new MfsbxxzbSeller();
            	
            	mfsbxxzbSellerItem.setSbbh(rs.getString("sbbh"));
            	mfsbxxzbSellerItem.setHtbh(rs.getString("htbh"));
            	mfsbxxzbSellerItem.setSjhjje(rs.getBigDecimal("sjhjje"));
            	mfsbxxzbSellerItem.setNsrmc(rs.getString("nsrmc"));
               
            	mfsbxxzbSellerList.add(mfsbxxzbSellerItem);
            }
//            System.out.println(" mfsbxxzbSellerList size:"+mfsbxxzbSellerList.size());
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxzbSellerList;
    } 
    
    /**
     * ����conditions��ȡ�����걨��Ϣ��������
     * @param conditions ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �����걨��Ϣ��������ֵ����ļ���
     * @throws SQLException
     */
    public  ArrayList queryMfsbxxzbAllList(Connection conn,String conditions) throws
            SQLException {
        ArrayList mfsbxxzbSellerList = new ArrayList();
        //
        String sql = "select sbbh,htbh,sjhjje,nsrmc from qsdb.qs_jl_mfsbxxzb_seller " + conditions + " order by cjrq";
        
        Debug.out("queryMfsbxxzbAllList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	MfsbxxzbSeller mfsbxxzbSellerItem = new MfsbxxzbSeller();
            	
            	mfsbxxzbSellerItem.setSbbh(rs.getString("sbbh"));
            	mfsbxxzbSellerItem.setHtbh(rs.getString("htbh"));
            	mfsbxxzbSellerItem.setSjhjje(rs.getBigDecimal("sjhjje"));
            	mfsbxxzbSellerItem.setNsrmc(rs.getString("nsrmc"));
               
            	mfsbxxzbSellerList.add(mfsbxxzbSellerItem);
            }
//            System.out.println(" mfsbxxzbSellerList size:"+mfsbxxzbSellerList.size());
            
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return mfsbxxzbSellerList;
    } 
    
}
