package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;


/**
 * 申报主表与土地、房屋信息表的关联表DAO
 */
public class SbtdfwglDAO extends BaseDAO {

    /**
     * 插入一条申报主表与土地、房屋信息表的关联表记录
     * @param sbtdfwgl 申报主表与土地、房屋信息表的关联表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Sbtdfwgl sbtdfwgl, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_SBTDFWGL (SBBH,TDFWID,LRR,CJR,CJRQ,LRRQ) values (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //申报表号
            ps.setString(1, sbtdfwgl.sbbh);
            //土地、房屋唯一标识
            ps.setString(2, sbtdfwgl.tdfwid);
            //录入人
            ps.setString(3, sbtdfwgl.lrr);
            //创建人
            ps.setString(4, sbtdfwgl.cjr);
            //创建日期
            ps.setTimestamp(5, sbtdfwgl.cjrq);
            //录入日期
            ps.setTimestamp(6, sbtdfwgl.lrrq);
            /*
             System.out.println("////////////////////////////////");
                         System.out.println("申报编号" + sbtdfwgl.sbbh);
                         System.out.println("土地、房屋Id" + sbtdfwgl.tdfwid);
                         System.out.println("录入人" + sbtdfwgl.lrr);
                         System.out.println("创建人" + sbtdfwgl.cjr);
                         System.out.println("创建日期" +sbtdfwgl.cjrq);
                         System.out.println("录入日期" + sbtdfwgl.lrrq);
             System.out.println("////////////////////////////////");
             */
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条申报主表与土地、房屋信息表的关联表记录
     * @param sbtdfwgl 申报主表与土地、房屋信息表的关联表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Sbtdfwgl sbtdfwgl, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_SBTDFWGL set SBBH=?,TDFWID=?,LRR=?,CJR=?,CJRQ=?,LRRQ=?   where sbbh = ?  and tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbtdfwgl.sbbh);
            ps.setString(2, sbtdfwgl.tdfwid);
            ps.setString(3, sbtdfwgl.lrr);
            ps.setString(4, sbtdfwgl.cjr);
            ps.setTimestamp(5, sbtdfwgl.cjrq);
            ps.setTimestamp(6, sbtdfwgl.lrrq);
            ps.setString(7, sbtdfwgl.sbbh);
            ps.setString(8, sbtdfwgl.tdfwid);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条申报主表与土地、房屋信息表的关联表记录
     * @param sbtdfwglList 申报主表与土地、房屋信息表的关联表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList sbtdfwglList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  QSDB.QS_JL_SBTDFWGL  where sbbh = ?  and tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbtdfwglList.size(); i++) {
                Sbtdfwgl sbtdfwgl = (Sbtdfwgl) sbtdfwglList.get(i);
                ps.setString(1, sbtdfwgl.sbbh);
                ps.setString(2, sbtdfwgl.tdfwid);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取申报主表与土地、房屋信息表的关联表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 申报主表与土地、房屋信息表的关联表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbtdfwglList = new ArrayList();
        String sql =
                "select SBBH,TDFWID,LRR,CJR,CJRQ,LRRQ from QSDB.QS_JL_SBTDFWGL " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbtdfwgl Sbtdfwgl1 = new Sbtdfwgl();
                Sbtdfwgl1.setSbbh(rs.getString("SBBH"));
                Sbtdfwgl1.setTdfwid(rs.getString("TDFWID"));
                Sbtdfwgl1.setLrr(rs.getString("LRR"));
                Sbtdfwgl1.setCjr(rs.getString("CJR"));
                Sbtdfwgl1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbtdfwgl1.setLrrq(rs.getTimestamp("LRRQ"));
                SbtdfwglList.add(Sbtdfwgl1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbtdfwglList;
    }

    /**
     * 根据主键获取申报主表与土地、房屋信息表的关联表值对象
     * @param sbtdfwgl 申报主表与土地、房屋信息表的关联表值对象
     * @param conn 数据库连接对象
     * @return申报主表与土地、房屋信息表的关联表值对象
     * @throws SQLException
     */
    public static Object get(Sbtdfwgl sbtdfwgl, Connection conn) throws
            SQLException {
        Sbtdfwgl Sbtdfwgl1 = new Sbtdfwgl();
        String sql = "select SBBH,TDFWID,LRR,CJR,CJRQ,LRRQ from QSDB.QS_JL_SBTDFWGL   where sbbh = ?  and tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbtdfwgl.sbbh);
            ps.setString(2, sbtdfwgl.tdfwid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbtdfwgl1.setSbbh(rs.getString("SBBH"));
                Sbtdfwgl1.setTdfwid(rs.getString("TDFWID"));
                Sbtdfwgl1.setLrr(rs.getString("LRR"));
                Sbtdfwgl1.setCjr(rs.getString("CJR"));
                Sbtdfwgl1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbtdfwgl1.setLrrq(rs.getTimestamp("LRRQ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbtdfwgl1;
    }

    /**
     * 根据拆迁协议号码获取申报拆迁对照数据
     * @param sbcqgl 申报主表与拆迁表的关联表值对象
     * @param conn 数据库连接对象
     * @return申报主表与拆迁表的关联表值对象
     * @throws SQLException
     */
    public static ArrayList queryByTdfwid(String tdfwid, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "select SBBH,TDFWID,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBTDFWGL   where tdfwid = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tdfwid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
                sbtdfwgl.setSbbh(rs.getString("SBBH"));
                sbtdfwgl.setTdfwid(rs.getString("TDFWID"));
                sbtdfwgl.setLrr(rs.getString("LRR"));
                sbtdfwgl.setLrrq(rs.getTimestamp("LRRQ"));
                sbtdfwgl.setCjr(rs.getString("CJR"));
                sbtdfwgl.setCjrq(rs.getTimestamp("CJRQ"));
                list.add(sbtdfwgl);
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
