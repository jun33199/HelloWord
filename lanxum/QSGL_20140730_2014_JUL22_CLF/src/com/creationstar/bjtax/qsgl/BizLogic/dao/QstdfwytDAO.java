package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qstdfwyt;


/**
 *  契税土地房屋用途DAO
 */
public class QstdfwytDAO extends BaseDAO {

    /**
     * 插入一条 契税土地房屋用途记录
     * @param qstdfwyt  契税土地房屋用途值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Qstdfwyt qstdfwyt, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.SF_DM_QSTDFWYT (QSTDFWYTDM,QSTDFWYTMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //契税土地房屋用途代码
            ps.setString(1, qstdfwyt.qstdfwytdm);
            //契税土地房屋用途名称
            ps.setString(2, qstdfwyt.qstdfwytmc);
            //录入人
            ps.setString(3, qstdfwyt.lrr);
            //录入日期
            ps.setTimestamp(4, qstdfwyt.lrrq);
            //注销标识
            ps.setString(5, qstdfwyt.zxbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条 契税土地房屋用途记录
     * @param qstdfwyt  契税土地房屋用途值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Qstdfwyt qstdfwyt, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.SF_DM_QSTDFWYT set QSTDFWYTDM=?,QSTDFWYTMC=?,LRR=?,LRRQ=?,ZXBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qstdfwyt.qstdfwytdm);
            ps.setString(2, qstdfwyt.qstdfwytmc);
            ps.setString(3, qstdfwyt.lrr);
            ps.setTimestamp(4, qstdfwyt.lrrq);
            ps.setString(5, qstdfwyt.zxbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条 契税土地房屋用途记录
     * @param qstdfwytList  契税土地房屋用途值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList qstdfwytList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_QSTDFWYT  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qstdfwytList.size(); i++) {
                Qstdfwyt qstdfwyt = (Qstdfwyt) qstdfwytList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取 契税土地房屋用途值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList  契税土地房屋用途值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QstdfwytList = new ArrayList();
        String sql =
                "select QSTDFWYTDM,QSTDFWYTMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSTDFWYT " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qstdfwyt Qstdfwyt1 = new Qstdfwyt();
                Qstdfwyt1.setQstdfwytdm(rs.getString("QSTDFWYTDM"));
                Qstdfwyt1.setQstdfwytmc(rs.getString("QSTDFWYTMC"));
                Qstdfwyt1.setLrr(rs.getString("LRR"));
                Qstdfwyt1.setLrrq(rs.getTimestamp("LRRQ"));
                Qstdfwyt1.setZxbs(rs.getString("ZXBS"));
                QstdfwytList.add(Qstdfwyt1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QstdfwytList;
    }

    /**
     * 根据主键获取 契税土地房屋用途值对象
     * @param qstdfwyt  契税土地房屋用途值对象
     * @param conn 数据库连接对象
     * @return 契税土地房屋用途值对象
     * @throws SQLException
     */
    public static Object get(Qstdfwyt qstdfwyt, Connection conn) throws
            SQLException {
        Qstdfwyt Qstdfwyt1 = new Qstdfwyt();
        String sql =
                "select QSTDFWYTDM,QSTDFWYTMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSTDFWYT   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qstdfwyt1.setQstdfwytdm(rs.getString("QSTDFWYTDM"));
                Qstdfwyt1.setQstdfwytmc(rs.getString("QSTDFWYTMC"));
                Qstdfwyt1.setLrr(rs.getString("LRR"));
                Qstdfwyt1.setLrrq(rs.getTimestamp("LRRQ"));
                Qstdfwyt1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qstdfwyt1;
    }


}
