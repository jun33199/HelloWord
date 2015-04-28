package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsfs;


/**
 * 缴税方式代码表DAO
 */
public class JsfsDAO extends BaseDAO {

    /**
     * 插入一条缴税方式代码表记录
     * @param jsfs 缴税方式代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Jsfs jsfs, Connection conn) throws SQLException {
        String sql = "insert into DMDB.QS_DM_JSFS (JSFSDM,JSFSMC,LRR,LRRQ,BZ,ZXBS) values (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //缴税方式代码
            ps.setString(1, jsfs.jsfsdm);
            //缴税方式名称
            ps.setString(2, jsfs.jsfsmc);
            //录入人
            ps.setString(3, jsfs.lrr);
            //录入日期
            ps.setTimestamp(4, jsfs.lrrq);
            //备注
            ps.setString(5, jsfs.bz);
            //注销标识
            ps.setString(6, jsfs.zxbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条缴税方式代码表记录
     * @param jsfs 缴税方式代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Jsfs jsfs, Connection conn) throws SQLException {
        String sql = "update  DMDB.QS_DM_JSFS set JSFSDM=?,JSFSMC=?,LRR=?,LRRQ=?,BZ=?,ZXBS=?   where jsfsdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsfs.jsfsdm);
            ps.setString(2, jsfs.jsfsmc);
            ps.setString(3, jsfs.lrr);
            ps.setTimestamp(4, jsfs.lrrq);
            ps.setString(5, jsfs.bz);
            ps.setString(6, jsfs.zxbs);
            ps.setString(7, jsfs.jsfsdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条缴税方式代码表记录
     * @param jsfsList 缴税方式代码表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList jsfsList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.QS_DM_JSFS  where jsfsdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jsfsList.size(); i++) {
                Jsfs jsfs = (Jsfs) jsfsList.get(i);
                ps.setString(1, jsfs.jsfsdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取缴税方式代码表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 缴税方式代码表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JsfsList = new ArrayList();
        String sql =
                "select JSFSDM,JSFSMC,LRR,LRRQ,BZ,ZXBS from DMDB.QS_DM_JSFS " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsfs Jsfs1 = new Jsfs();
                Jsfs1.setJsfsdm(rs.getString("JSFSDM"));
                Jsfs1.setJsfsmc(rs.getString("JSFSMC"));
                Jsfs1.setLrr(rs.getString("LRR"));
                Jsfs1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsfs1.setBz(rs.getString("BZ"));
                Jsfs1.setZxbs(rs.getString("ZXBS"));
                JsfsList.add(Jsfs1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsfsList;
    }

    /**
     * 根据主键获取缴税方式代码表值对象
     * @param jsfs 缴税方式代码表值对象
     * @param conn 数据库连接对象
     * @return缴税方式代码表值对象
     * @throws SQLException
     */
    public static Object get(Jsfs jsfs, Connection conn) throws SQLException {
        Jsfs Jsfs1 = new Jsfs();
        String sql =
                "select JSFSDM,JSFSMC,LRR,LRRQ,BZ,ZXBS from DMDB.QS_DM_JSFS   where jsfsdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsfs.jsfsdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jsfs1.setJsfsdm(rs.getString("JSFSDM"));
                Jsfs1.setJsfsmc(rs.getString("JSFSMC"));
                Jsfs1.setLrr(rs.getString("LRR"));
                Jsfs1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsfs1.setBz(rs.getString("BZ"));
                Jsfs1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jsfs1;
    }


}
