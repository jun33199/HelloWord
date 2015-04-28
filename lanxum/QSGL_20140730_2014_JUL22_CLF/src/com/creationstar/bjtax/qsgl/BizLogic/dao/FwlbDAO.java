package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fwlb;


/**
 * 房屋类型代码数据DAO
 */
public class FwlbDAO extends BaseDAO {

    /**
     * 插入一条房屋类型代码数据记录
     * @param fwlb 房屋类型代码数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Fwlb fwlb, Connection conn) throws SQLException {
        String sql = "insert into DMDB.QS_DM_FWLB (FWLXDM,FWLXMC,BZ,LRRQ,LRR,ZXBS) values (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //房屋类型代码
            ps.setString(1, fwlb.fwlxdm);
            //房屋类型名称
            ps.setString(2, fwlb.fwlxmc);
            //备注
            ps.setString(3, fwlb.bz);
            //录入日期
            ps.setTimestamp(4, fwlb.lrrq);
            //录入人
            ps.setString(5, fwlb.lrr);
            //注销标识
            ps.setString(6, fwlb.zxbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条房屋类型代码数据记录
     * @param fwlb 房屋类型代码数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Fwlb fwlb, Connection conn) throws SQLException {
        String sql = "update  DMDB.QS_DM_FWLB set FWLXDM=?,FWLXMC=?,BZ=?,LRRQ=?,LRR=?,ZXBS=?   where fwlxdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fwlb.fwlxdm);
            ps.setString(2, fwlb.fwlxmc);
            ps.setString(3, fwlb.bz);
            ps.setTimestamp(4, fwlb.lrrq);
            ps.setString(5, fwlb.lrr);
            ps.setString(6, fwlb.zxbs);
            ps.setString(7, fwlb.fwlxdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条房屋类型代码数据记录
     * @param fwlbList 房屋类型代码数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList fwlbList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.QS_DM_FWLB  where fwlxdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < fwlbList.size(); i++) {
                Fwlb fwlb = (Fwlb) fwlbList.get(i);
                ps.setString(1, fwlb.fwlxdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取房屋类型代码数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 房屋类型代码数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList FwlbList = new ArrayList();
        String sql =
                "select FWLXDM,FWLXMC,BZ,LRRQ,LRR,ZXBS from DMDB.QS_DM_FWLB " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fwlb Fwlb1 = new Fwlb();
                Fwlb1.setFwlxdm(rs.getString("FWLXDM"));
                Fwlb1.setFwlxmc(rs.getString("FWLXMC"));
                Fwlb1.setBz(rs.getString("BZ"));
                Fwlb1.setLrrq(rs.getTimestamp("LRRQ"));
                Fwlb1.setLrr(rs.getString("LRR"));
                Fwlb1.setZxbs(rs.getString("ZXBS"));
                FwlbList.add(Fwlb1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FwlbList;
    }

    /**
     * 根据主键获取房屋类型代码数据值对象
     * @param fwlb 房屋类型代码数据值对象
     * @param conn 数据库连接对象
     * @return房屋类型代码数据值对象
     * @throws SQLException
     */
    public static Object get(Fwlb fwlb, Connection conn) throws SQLException {
        Fwlb Fwlb1 = new Fwlb();
        String sql =
                "select FWLXDM,FWLXMC,BZ,LRRQ,LRR,ZXBS from DMDB.QS_DM_FWLB   where fwlxdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fwlb.fwlxdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Fwlb1.setFwlxdm(rs.getString("FWLXDM"));
                Fwlb1.setFwlxmc(rs.getString("FWLXMC"));
                Fwlb1.setBz(rs.getString("BZ"));
                Fwlb1.setLrrq(rs.getTimestamp("LRRQ"));
                Fwlb1.setLrr(rs.getString("LRR"));
                Fwlb1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Fwlb1;
    }


}
