package com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;

/**
 * 发票开票明细DAO
 * @author tutu
 * 2013-05-07(1)
 */
public class FpczmxDAO extends BaseDAO {

	/**
     * 插入一条发票操作数据记录
     * @param fpcz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Fpczmx fpczmx, Connection conn) throws
            SQLException {
        String sql = "insert into fpdb.fp_jl_fpkpmx (fpkfdm,fpzldm,fphm,kprq,pm,dj,sl,je,cjr,cjrq,lrr,lrrq,swjgzzjgdm,bz) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, fpczmx.fpkfdm);
            ps.setString(2, fpczmx.fpzldm);
            ps.setString(3, fpczmx.fphm);
            ps.setTimestamp(4, fpczmx.kprq);
            ps.setString(5, fpczmx.pm);
            ps.setBigDecimal(6, fpczmx.dj);
            ps.setBigDecimal(7, fpczmx.sl);
            ps.setBigDecimal(8, fpczmx.je);
            ps.setString(9, fpczmx.cjr);
            ps.setTimestamp(10, fpczmx.cjrq);
            ps.setString(11, fpczmx.lrr);
            ps.setTimestamp(12, fpczmx.lrrq);
            ps.setString(13, fpczmx.swjgzzjgdm);
            ps.setString(14, fpczmx.bz);

            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 更新一条发票操作数据记录
     * @param fpcz 发票操作数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     * @throws SQLException
     */
    public static void update(Fpczmx fpczmx, Connection conn) throws
            SQLException {
        String sql = "update  fpdb.fp_jl_fpkpmx set fpkfdm=?,fpzldm=?,fphm=?,kprq=?,pm=?,dj=?,sl=?,je=?,cjr=?,cjrq=?,lrr=?,lrrq=?,swjgzzjgdm=?,bz=? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fpczmx.fpkfdm);
            ps.setString(2, fpczmx.fpzldm);
            ps.setString(3, fpczmx.fphm);
            ps.setTimestamp(4, fpczmx.kprq);
            ps.setString(5, fpczmx.pm);
            ps.setBigDecimal(6, fpczmx.dj);
            ps.setBigDecimal(7, fpczmx.sl);
            ps.setBigDecimal(8, fpczmx.je);
            ps.setString(9, fpczmx.cjr);
            ps.setTimestamp(10, fpczmx.cjrq);
            ps.setString(11, fpczmx.lrr);
            ps.setTimestamp(12, fpczmx.lrrq);
            ps.setString(13, fpczmx.swjgzzjgdm);
            ps.setString(14, fpczmx.bz);
            
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
    
    /**
     * 删除多条发票操作数据记录
     * @param fpczList 发票操作数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList fpczmxList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  fpdb.fp_jl_fpkpmx  where fpzldm = ?  and fphm = ?  and pm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < fpczmxList.size(); i++) {
            	Fpczmx fpczmx = (Fpczmx) fpczmxList.get(i);
            	
                ps.setString(1, fpczmx.fpzldm);
                ps.setString(2, fpczmx.fphm);
                ps.setString(3, fpczmx.pm);

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
                "delete from  fpdb.fp_jl_fpkpmx   where ");
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
     * 根据主键获取发票操作数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList FpczList = new ArrayList();
        String sql = "select  fpkfdm,fpzldm,fphm,kprq,pm,dj,sl,je,cjr,cjrq,lrr,lrrq,swjgzzjgdm,bz from fpdb.fp_jl_fpkpmx  " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Fpczmx fpczmx = new Fpczmx();
            	
            	fpczmx.setFpkfdm(rs.getString("fpkfdm"));
            	fpczmx.setFpzldm(rs.getString("fpzldm"));
            	fpczmx.setFphm(rs.getString("fphm"));
            	fpczmx.setKprq(rs.getTimestamp("kprq"));
            	fpczmx.setPm(rs.getString("pm"));
            	fpczmx.setDj(rs.getBigDecimal("dj"));
            	fpczmx.setSl(rs.getBigDecimal("sl"));
            	fpczmx.setJe(rs.getBigDecimal("je"));
            	fpczmx.setCjr(rs.getString("cjr"));
            	fpczmx.setCjrq(rs.getTimestamp("cjrq"));
            	fpczmx.setLrr(rs.getString("lrr"));
            	fpczmx.setLrrq(rs.getTimestamp("lrrq"));
            	fpczmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczmx.setBz(rs.getString("bz"));
               
            	FpczList.add(fpczmx);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FpczList;
    }

}
