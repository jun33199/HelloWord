package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbcqgl;


/**
 * 申报主表与拆迁表的关联表DAO
 */
public class SbcqglDAO extends BaseDAO {

    /**
     * 插入一条申报主表与拆迁表的关联表记录
     * @param sbcqgl 申报主表与拆迁表的关联表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Sbcqgl sbcqgl, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_SBCQGL (SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //申报表号
            ps.setString(1, sbcqgl.sbbh);
            //拆迁协议号码
            ps.setString(2, sbcqgl.cqxyh);
            //本次使用补偿额
            ps.setBigDecimal(3, sbcqgl.bcsybce);
            //录入人
            ps.setString(4, sbcqgl.lrr);
            //录入日期
            ps.setTimestamp(5, sbcqgl.lrrq);
            //创建人
            ps.setString(6, sbcqgl.cjr);
            //创建日期
            ps.setTimestamp(7, sbcqgl.cjrq);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条申报主表与拆迁表的关联表记录
     * @param sbcqgl 申报主表与拆迁表的关联表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Sbcqgl sbcqgl, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_SBCQGL set SBBH=?,CQXYH=?,BCSYBCE=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?   where cqxyh = ?  and sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbcqgl.sbbh);
            ps.setString(2, sbcqgl.cqxyh);
            ps.setBigDecimal(3, sbcqgl.bcsybce);
            ps.setString(4, sbcqgl.lrr);
            ps.setTimestamp(5, sbcqgl.lrrq);
            ps.setString(6, sbcqgl.cjr);
            ps.setTimestamp(7, sbcqgl.cjrq);
            ps.setString(8, sbcqgl.cqxyh);
            ps.setString(9, sbcqgl.sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条申报主表与拆迁表的关联表记录
     * @param sbcqglList 申报主表与拆迁表的关联表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList sbcqglList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  QSDB.QS_JL_SBCQGL  where cqxyh = ?  and sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbcqglList.size(); i++) {
                Sbcqgl sbcqgl = (Sbcqgl) sbcqglList.get(i);
                ps.setString(1, sbcqgl.cqxyh);
                ps.setString(2, sbcqgl.sbbh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取申报主表与拆迁表的关联表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 申报主表与拆迁表的关联表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbcqglList = new ArrayList();
        String sql =
                "select SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBCQGL " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbcqgl Sbcqgl1 = new Sbcqgl();
                Sbcqgl1.setSbbh(rs.getString("SBBH"));
                Sbcqgl1.setCqxyh(rs.getString("CQXYH"));
                Sbcqgl1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                Sbcqgl1.setLrr(rs.getString("LRR"));
                Sbcqgl1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbcqgl1.setCjr(rs.getString("CJR"));
                Sbcqgl1.setCjrq(rs.getTimestamp("CJRQ"));
                SbcqglList.add(Sbcqgl1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbcqglList;
    }

    /**
     * 根据主键获取申报主表与拆迁表的关联表值对象
     * @param sbcqgl 申报主表与拆迁表的关联表值对象
     * @param conn 数据库连接对象
     * @return申报主表与拆迁表的关联表值对象
     * @throws SQLException
     */
    public static Object get(Sbcqgl sbcqgl, Connection conn) throws
            SQLException {
        Sbcqgl Sbcqgl1 = null;
        String sql = "select SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBCQGL   where cqxyh = ?  and sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbcqgl.cqxyh);
            ps.setString(2, sbcqgl.sbbh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbcqgl1 = new Sbcqgl();
                Sbcqgl1.setSbbh(rs.getString("SBBH"));
                Sbcqgl1.setCqxyh(rs.getString("CQXYH"));
                Sbcqgl1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                Sbcqgl1.setLrr(rs.getString("LRR"));
                Sbcqgl1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbcqgl1.setCjr(rs.getString("CJR"));
                Sbcqgl1.setCjrq(rs.getTimestamp("CJRQ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbcqgl1;
    }

    /**
     * 根据拆迁协议号码获取申报拆迁对照数据
     * @param sbcqgl 申报主表与拆迁表的关联表值对象
     * @param conn 数据库连接对象
     * @return申报主表与拆迁表的关联表值对象
     * @throws SQLException
     */
    public static ArrayList queryByCqxyh(String cqxyh, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "select SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBCQGL   where cqxyh = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cqxyh);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sbcqgl Sbcqgl1 = new Sbcqgl();
                Sbcqgl1.setSbbh(rs.getString("SBBH"));
                Sbcqgl1.setCqxyh(rs.getString("CQXYH"));
                Sbcqgl1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                Sbcqgl1.setLrr(rs.getString("LRR"));
                Sbcqgl1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbcqgl1.setCjr(rs.getString("CJR"));
                Sbcqgl1.setCjrq(rs.getTimestamp("CJRQ"));
                list.add(Sbcqgl1);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return list;
    }


}
