package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;

/**
 * @author tutu
 * 合同与凭证对照关系表DAO
 * 2013-05-15(1)
 */
public class HtypzdzgxbDAO extends BaseDAO {

	/**
     * 插入一条合同与凭证对照关系表数据记录
     * @param htypzdzgxb 合同与凭证对照关系表操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Htypzdzgxb htypzdzgxb, Connection conn) throws
            SQLException {
        String sql = "insert into qsdb.qs_jl_htypzdzgxb (xh,htbh,mmfbz,pzfldm,pizzldm,pzhm,pzndzb,cjr,cjrq,lrr,lrrq,sbbh,swjgzzjgdm) values (QSDB.SEQ_QS_CLFJYXH.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, htypzdzgxb.htbh);
            ps.setString(2, htypzdzgxb.mmfbz);
            ps.setString(3, htypzdzgxb.pzfldm);
            ps.setString(4, htypzdzgxb.pizzldm);
            ps.setString(5, htypzdzgxb.pzhm);
            ps.setString(6, htypzdzgxb.pzndzb);
            ps.setString(7, htypzdzgxb.cjr);
            ps.setTimestamp(8, htypzdzgxb.cjrq);
            ps.setString(9, htypzdzgxb.lrr);
            ps.setTimestamp(10, htypzdzgxb.lrrq);
            ps.setString(11, htypzdzgxb.sbbh);
            ps.setString(12, htypzdzgxb.swjgzzjgdm);
            
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    
    /**
     * 根据主键获取合同与凭证对照关系表数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws SQLException
     */
    public static String queryPzfldm(String condition, Connection conn) throws
            SQLException {
    	String pzdm ="";
    	String pzdmItem ="";
    	ArrayList pzdmList = new ArrayList();
        String sql = "select xh,htbh,mmfbz,pzfldm,pizzldm,pzhm,pzndzb,cjr,cjrq,lrr,lrrq,sbbh,swjgzzjgdm from qsdb.qs_jl_htypzdzgxb " + condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
            	
            	htypzdzgxb.setHtbh(rs.getString("htbh"));
            	htypzdzgxb.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	
            	pzdmList.add(htypzdzgxb);
            }
            
            if ((pzdmList != null) && (pzdmList.size() != 0))
            {
            	for(int i=0;i<pzdmList.size();i++)
            	{
            		Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
           		  	Htypzdzgxb htypzdzgxbItem = (Htypzdzgxb) pzdmList.get(i);
           		  	htypzdzgxb.setPzhm(htypzdzgxbItem.getPzhm());
           		  	htypzdzgxb.setPizzldm(htypzdzgxbItem.getPizzldm());
           		
           		  	if(i <  pzdmList.size()-1)
           		  	{
           		  		pzdmItem = pzdmItem +  htypzdzgxb.getPizzldm()+ htypzdzgxb.getPzhm() +" ";
           		  	}
           		  	else
           		  	{
           		  		pzdmItem = pzdmItem + htypzdzgxb.getPizzldm()+ htypzdzgxb.getPzhm();
           		  	}
           	  	}
           	  	pzdm = pzdmItem;
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return pzdm;
    }
    
    
    /**
     * 根据主键获取合同与凭证对照关系主数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证主数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList HtypzdzList = new ArrayList();
        String sql = "select xh,htbh,mmfbz,pzfldm,pizzldm,pzhm,pzndzb,cjr,cjrq,lrr,lrrq,sbbh,swjgzzjgdm from qsdb.qs_jl_htypzdzgxb " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
            	
            	htypzdzgxb.setXh(rs.getString("xh"));
            	htypzdzgxb.setHtbh(rs.getString("htbh"));
            	htypzdzgxb.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb.setCjr(rs.getString("cjr"));
            	htypzdzgxb.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb.setLrr(rs.getString("lrr"));
            	htypzdzgxb.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb.setSbbh(rs.getString("sbbh"));
            	htypzdzgxb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	
            	HtypzdzList.add(htypzdzgxb);

            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return HtypzdzList;
    }
    
    
    /**
     * 删除合同与凭证对照关系数据记录
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(String condition, Connection conn) throws
            SQLException {
    	String sql = "delete from  qsdb.qs_jl_htypzdzgxb "+ condition;
        PreparedStatement ps = null;
        try {
        	ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    
}
