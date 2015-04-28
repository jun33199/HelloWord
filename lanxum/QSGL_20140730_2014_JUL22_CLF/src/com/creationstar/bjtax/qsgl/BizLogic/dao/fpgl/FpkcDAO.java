package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.util.Constants;


/**
 * 发票库存DAO
 * @author tutu
 * 2013-05-10(1)
 */
public class FpkcDAO extends BaseDAO {
	
	/**
     * 插入一条发票库存数据记录
     * @param fpcz 发票库存数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Fpkc fpkc, Connection conn) throws
            SQLException {
        String sql = "insert into fpdb.fp_jl_fpkcmx (fpkfdm,fpzldm,jcsj,qshm,jzhm,sl,swjgzzjgdm,cjr,cjrq,lrr,lrrq,bz,rkbs) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fpkc.fpkfdm);
            ps.setString(2, fpkc.fpzldm);
            ps.setTimestamp(3, fpkc.jcsj);
            ps.setString(4, fpkc.qshm);
            ps.setString(5, fpkc.jzhm);
            ps.setBigDecimal(6, fpkc.sl);
            ps.setString(7, fpkc.swjgzzjgdm);
            ps.setString(8, fpkc.cjr);
            ps.setTimestamp(9, fpkc.cjrq);
            ps.setString(10, fpkc.lrr);
            ps.setTimestamp(11, fpkc.lrrq);
            ps.setString(12, fpkc.bz);
            ps.setString(13, fpkc.rkbs);

            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 更新一条发票库存数据记录
     * @param fpcz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     * @throws SQLException
     */
    public static void update(Fpkc fpkc, Connection conn) throws
            SQLException {
        String sql = "update  fpdb.fp_jl_fpkcmx  set fpkfdm=?,fpzldm=?,jcsj=?,qshm=?,jzhm=?,sl=?,swjgzzjgdm=?,cjr=?,cjrq=?,lrr=?,lrrq=?,rkbs=?  where fpkfdm=? and fpzldm = ?  and fpqzhm = ?  and jcsj = ? and qshm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpkc.fpkfdm);
            ps.setString(2, fpkc.fpzldm);
            ps.setTimestamp(3, fpkc.jcsj);
            ps.setString(4, fpkc.qshm);
            ps.setString(5, fpkc.jzhm);
            ps.setBigDecimal(6, fpkc.sl);
            ps.setString(7, fpkc.swjgzzjgdm);
            ps.setString(8, fpkc.cjr);
            ps.setTimestamp(9, fpkc.cjrq);
            ps.setString(10, fpkc.lrr);
            ps.setTimestamp(11, fpkc.lrrq);
            ps.setString(12, fpkc.bz);
            ps.setString(13, fpkc.rkbs);
            
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 删除多条发票库存数据记录
     * @param fpczList 发票操作数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList fpkcList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  fpdb.fp_jl_fpkcmx  where fpkfdm = ? and fpzldm = ?   and jcsj = ? and qshm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < fpkcList.size(); i++) {
            	Fpkc fpkc = (Fpkc) fpkcList.get(i);
            	
            	ps.setString(1, fpkc.fpkfdm);
                ps.setString(2, fpkc.fpzldm);
                ps.setTimestamp(3, fpkc.jcsj);
                ps.setString(4, fpkc.qshm);
                
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 撤销发票使用
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "delete from  fpdb.fp_jl_fpkcmx  where ");
        sql.append(condition);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * 根据主键获取发票库存数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList FpczList = new ArrayList();
        String sql = "select fpkfdm,fpzldm,jcsj,qshm,jzhm,sl,swjgzzjgdm,cjr,cjrq,lrr,lrrq,bz,rkbs from fpdb.fp_jl_fpkcmx " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Fpkc fpkc = new Fpkc();
            	
            	fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	fpkc.setFpzldm(rs.getString("fpzldm"));
            	fpkc.setJcsj(rs.getTimestamp("jcsj"));
            	fpkc.setQshm(rs.getString("qshm"));
            	fpkc.setJzhm(rs.getString("jzhm"));
            	fpkc.setSl(rs.getBigDecimal("sl"));
            	fpkc.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpkc.setCjr(rs.getString("cjr"));
            	fpkc.setCjrq(rs.getTimestamp("cjrq"));
            	fpkc.setLrr(rs.getString("lrr"));
            	fpkc.setLrrq(rs.getTimestamp("lrrq"));
            	fpkc.setRkbs(rs.getString("rkbs"));
               
            	FpczList.add(fpkc);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpczList;
    }
    
    /**
     * 根据主键获取发票库存数据最新操作时间
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws SQLException
     */
    public static Timestamp queryMaxTime(Connection conn,String conditions) throws
            SQLException {
    	
    	Timestamp newTime = null;
    	Fpkc fpkc = new Fpkc();
        ArrayList FpkcList = new ArrayList();
        
        StringBuffer sql = new StringBuffer("");
        
        sql.append("select b.fpkfdm, b.fpzldm, b.jcsj from fpdb.fp_jl_fpkcmx a, ( ")
           .append("select c.fpkfdm, c.fpzldm, max(c.jcsj) jcsj from fpdb.fp_jl_fpkcmx c ") 
           .append(conditions)
           .append("group by c.fpkfdm, c.fpzldm) b where a.fpkfdm = b.fpkfdm and a.fpzldm = b.fpzldm ")
           .append("and a.jcsj = b.jcsj and a.sl <> '"+Constants.FP_SL_ZERO+"' "); 
        
        //Debug.out("queryMaxTime:"+sql);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            
            while (rs.next()) 
            {
            	
            	fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	fpkc.setFpzldm(rs.getString("fpzldm"));
            	fpkc.setJcsj(rs.getTimestamp("jcsj"));
               
            	FpkcList.add(fpkc);
            }
            
            if ((FpkcList != null) && (FpkcList.size() > 0))
            {
            	fpkc = (Fpkc) FpkcList.get(0);

                if (fpkc != null)
                {
                    newTime = fpkc.getJcsj();
                }
            }
            
        } catch (SQLException e) {
        	throw e;
        } finally {
        	try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }
        return newTime;
    }
    
    
    /**
     * 根据主键获取发票库存数据最新操作时间的结存记录
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList queryMaxList(Connection conn,String conditions) throws
            SQLException {
        ArrayList FpkcList = new ArrayList();
        String sql = "select  fpkfdm,fpzldm,jcsj,qshm,jzhm,sl,swjgzzjgdm,cjr,cjrq,lrr,lrrq,bz,rkbs from fpdb.fp_jl_fpkcmx " + conditions;
        
        //Debug.out("queryMaxList:"+sql);
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	Fpkc fpkc = new Fpkc();
            	
            	fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	fpkc.setFpzldm(rs.getString("fpzldm"));
            	fpkc.setJcsj(rs.getTimestamp("jcsj"));
            	fpkc.setQshm(rs.getString("qshm"));
            	fpkc.setJzhm(rs.getString("jzhm"));
            	fpkc.setSl(rs.getBigDecimal("sl"));
            	fpkc.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpkc.setCjr(rs.getString("cjr"));
            	fpkc.setCjrq(rs.getTimestamp("cjrq"));
            	fpkc.setLrr(rs.getString("lrr"));
            	fpkc.setLrrq(rs.getTimestamp("lrrq"));
            	fpkc.setBz(rs.getString("bz"));
            	fpkc.setRkbs(rs.getString("rkbs"));
            	
            	FpkcList.add(fpkc);
            }
            rs.close();
        } catch (SQLException e) {
        	throw e;
        } finally {
            ps.close();
        }
        return FpkcList;
    }

}
