package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;


/**
 * 证件类型代码表DAO
 */
public class ZjlxDAO extends BaseDAO {

    /**
     * 插入一条证件类型代码表记录
     * @param zjlx 证件类型代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Zjlx zjlx, Connection conn) throws SQLException {
        String sql = "insert into DMDB.GY_DM_ZJLX (ZJLXDM,ZJLXMC,ZHGXSJ,LRR,LRRQ,ZXBS,CLFS) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //证件类型代码
            ps.setString(1, zjlx.zjlxdm);
            //证件类型名称
            ps.setString(2, zjlx.zjlxmc);
            //最后更新时间
            ps.setTimestamp(3, zjlx.zhgxsj);
            //录入人
            ps.setString(4, zjlx.lrr);
            //录入日期
            ps.setTimestamp(5, zjlx.lrrq);
            //注销标识
            ps.setString(6, zjlx.zxbs);
            //处理方式
            ps.setString(7, zjlx.clfs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条证件类型代码表记录
     * @param zjlx 证件类型代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Zjlx zjlx, Connection conn) throws SQLException {
        String sql = "update  DMDB.GY_DM_ZJLX set ZJLXDM=?,ZJLXMC=?,ZHGXSJ=?,LRR=?,LRRQ=?,ZXBS=?,CLFS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zjlx.zjlxdm);
            ps.setString(2, zjlx.zjlxmc);
            ps.setTimestamp(3, zjlx.zhgxsj);
            ps.setString(4, zjlx.lrr);
            ps.setTimestamp(5, zjlx.lrrq);
            ps.setString(6, zjlx.zxbs);
            ps.setString(7, zjlx.clfs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条证件类型代码表记录
     * @param zjlxList 证件类型代码表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList zjlxList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.GY_DM_ZJLX  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < zjlxList.size(); i++) {
                Zjlx zjlx = (Zjlx) zjlxList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取证件类型代码表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 证件类型代码表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList ZjlxList = new ArrayList();
        String sql =
                "select ZJLXDM,ZJLXMC,ZHGXSJ,LRR,LRRQ,ZXBS,CLFS from DMDB.GY_DM_ZJLX " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Zjlx Zjlx1 = new Zjlx();
                Zjlx1.setZjlxdm(rs.getString("ZJLXDM"));
                Zjlx1.setZjlxmc(rs.getString("ZJLXMC"));
                Zjlx1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Zjlx1.setLrr(rs.getString("LRR"));
                Zjlx1.setLrrq(rs.getTimestamp("LRRQ"));
                Zjlx1.setZxbs(rs.getString("ZXBS"));
                Zjlx1.setClfs(rs.getString("CLFS"));
                ZjlxList.add(Zjlx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return ZjlxList;
    }

    /**
     * 根据主键获取证件类型代码表值对象
     * @param zjlx 证件类型代码表值对象
     * @param conn 数据库连接对象
     * @return证件类型代码表值对象
     * @throws SQLException
     */
    public static Object get(Zjlx zjlx, Connection conn) throws SQLException {
        Zjlx Zjlx1 = new Zjlx();
        String sql =
                "select ZJLXDM,ZJLXMC,ZHGXSJ,LRR,LRRQ,ZXBS,CLFS from DMDB.GY_DM_ZJLX   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Zjlx1.setZjlxdm(rs.getString("ZJLXDM"));
                Zjlx1.setZjlxmc(rs.getString("ZJLXMC"));
                Zjlx1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Zjlx1.setLrr(rs.getString("LRR"));
                Zjlx1.setLrrq(rs.getTimestamp("LRRQ"));
                Zjlx1.setZxbs(rs.getString("ZXBS"));
                Zjlx1.setClfs(rs.getString("CLFS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Zjlx1;
    }


}
