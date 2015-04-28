package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Ysjc;


/**
 * 预算级次代码表DAO
 */
public class YsjcDAO extends BaseDAO {

    /**
     * 插入一条预算级次代码表记录
     * @param ysjc 预算级次代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Ysjc ysjc, Connection conn) throws SQLException {
        String sql =
                "insert into DMDB.SF_DM_YSJC (YSJCDM,YSJCMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //预算级次代码
            ps.setString(1, ysjc.ysjcdm);
            //预算级次名称
            ps.setString(2, ysjc.ysjcmc);
            //录入人
            ps.setString(3, ysjc.lrr);
            //录入日期
            ps.setTimestamp(4, ysjc.lrrq);
            //注销标识
            ps.setString(5, ysjc.zxbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条预算级次代码表记录
     * @param ysjc 预算级次代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Ysjc ysjc, Connection conn) throws SQLException {
        String sql =
                "update  DMDB.SF_DM_YSJC set YSJCDM=?,YSJCMC=?,LRR=?,LRRQ=?,ZXBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ysjc.ysjcdm);
            ps.setString(2, ysjc.ysjcmc);
            ps.setString(3, ysjc.lrr);
            ps.setTimestamp(4, ysjc.lrrq);
            ps.setString(5, ysjc.zxbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条预算级次代码表记录
     * @param ysjcList 预算级次代码表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList ysjcList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_YSJC  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < ysjcList.size(); i++) {
                Ysjc ysjc = (Ysjc) ysjcList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取预算级次代码表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 预算级次代码表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList YsjcList = new ArrayList();
        String sql = "select YSJCDM,YSJCMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_YSJC " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ysjc Ysjc1 = new Ysjc();
                Ysjc1.setYsjcdm(rs.getString("YSJCDM"));
                Ysjc1.setYsjcmc(rs.getString("YSJCMC"));
                Ysjc1.setLrr(rs.getString("LRR"));
                Ysjc1.setLrrq(rs.getTimestamp("LRRQ"));
                Ysjc1.setZxbs(rs.getString("ZXBS"));
                YsjcList.add(Ysjc1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return YsjcList;
    }

    /**
     * 根据主键获取预算级次代码表值对象
     * @param ysjc 预算级次代码表值对象
     * @param conn 数据库连接对象
     * @return预算级次代码表值对象
     * @throws SQLException
     */
    public static Object get(Ysjc ysjc, Connection conn) throws SQLException {
        Ysjc Ysjc1 = new Ysjc();
        String sql =
                "select YSJCDM,YSJCMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_YSJC   where ysjcdm='" +
                ysjc.ysjcdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ysjc1.setYsjcdm(rs.getString("YSJCDM"));
                Ysjc1.setYsjcmc(rs.getString("YSJCMC"));
                Ysjc1.setLrr(rs.getString("LRR"));
                Ysjc1.setLrrq(rs.getTimestamp("LRRQ"));
                Ysjc1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Ysjc1;
    }


}
