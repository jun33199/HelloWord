package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qszyxs;


/**
 * 土地房屋权属转移形式代码表DAO
 */
public class QszyxsDAO extends BaseDAO {

    /**
     * 插入一条土地房屋权属转移形式代码表记录
     * @param qszyxs 土地房屋权属转移形式代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Qszyxs qszyxs, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.SF_DM_QSZYXS (QSZYXSDM,QSZYXSMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //土地房屋权属转移形式代码
            ps.setString(1, qszyxs.qszyxsdm);
            //土地房屋权属转移形式名称
            ps.setString(2, qszyxs.qszyxsmc);
            //录入人
            ps.setString(3, qszyxs.lrr);
            //录入日期
            ps.setTimestamp(4, qszyxs.lrrq);
            //注销标识
            ps.setString(5, qszyxs.zxbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条土地房屋权属转移形式代码表记录
     * @param qszyxs 土地房屋权属转移形式代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Qszyxs qszyxs, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.SF_DM_QSZYXS set QSZYXSDM=?,QSZYXSMC=?,LRR=?,LRRQ=?,ZXBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qszyxs.qszyxsdm);
            ps.setString(2, qszyxs.qszyxsmc);
            ps.setString(3, qszyxs.lrr);
            ps.setTimestamp(4, qszyxs.lrrq);
            ps.setString(5, qszyxs.zxbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条土地房屋权属转移形式代码表记录
     * @param qszyxsList 土地房屋权属转移形式代码表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList qszyxsList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_QSZYXS  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qszyxsList.size(); i++) {
                Qszyxs qszyxs = (Qszyxs) qszyxsList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取土地房屋权属转移形式代码表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 土地房屋权属转移形式代码表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QszyxsList = new ArrayList();
        String sql =
                "select QSZYXSDM,QSZYXSMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSZYXS " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qszyxs Qszyxs1 = new Qszyxs();
                Qszyxs1.setQszyxsdm(rs.getString("QSZYXSDM"));
                Qszyxs1.setQszyxsmc(rs.getString("QSZYXSMC"));
                Qszyxs1.setLrr(rs.getString("LRR"));
                Qszyxs1.setLrrq(rs.getTimestamp("LRRQ"));
                Qszyxs1.setZxbs(rs.getString("ZXBS"));
                QszyxsList.add(Qszyxs1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QszyxsList;
    }

    /**
     * 根据主键获取土地房屋权属转移形式代码表值对象
     * @param qszyxs 土地房屋权属转移形式代码表值对象
     * @param conn 数据库连接对象
     * @return土地房屋权属转移形式代码表值对象
     * @throws SQLException
     */
    public static Object get(Qszyxs qszyxs, Connection conn) throws
            SQLException {
        Qszyxs Qszyxs1 = new Qszyxs();
        String sql =
                "select QSZYXSDM,QSZYXSMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSZYXS   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qszyxs1.setQszyxsdm(rs.getString("QSZYXSDM"));
                Qszyxs1.setQszyxsmc(rs.getString("QSZYXSMC"));
                Qszyxs1.setLrr(rs.getString("LRR"));
                Qszyxs1.setLrrq(rs.getTimestamp("LRRQ"));
                Qszyxs1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qszyxs1;
    }


}
