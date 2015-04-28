package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.ttsoft.common.util.Debug;

/**
 * 导入批次信息DAO
 */
public class DrpcInfoDAO extends BaseDAO {
    /**
     * 插入一条批次信息记录
     * @param drpcInfo 批量信息值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Drpcinfo drpcInfo, Connection conn, String sjly) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_DRPCINFO (drpch,drbs,tgzlx,tgzmc,tgzgjdm,tgzgjmc,tgzsfzjlx,tgzsfzjhm,tgzjsjdm,tjsj,drsj,jsfsdm,jsfmc,sjly) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            System.out.println("////////////////");
            System.out.println("导入批次号：" + drpcInfo.drpch);
            System.out.println("导入笔数：" + drpcInfo.drbs);
            System.out.println("////////////////");

            //导入批次号
            ps.setString(1, drpcInfo.drpch);
            //导入笔数
            ps.setBigDecimal(2, drpcInfo.drbs);
            //数据提供者类型
            ps.setString(3, drpcInfo.tgzlx);
            //数据提供者名称
            ps.setString(4, drpcInfo.tgzmc);
            //数据提供者国籍代码
            ps.setString(5, drpcInfo.tgzgjdm);
            //数据提供者国籍名称
            ps.setString(6, drpcInfo.tgzgjmc);
            //数据提供者身份证件类型
            ps.setString(7, drpcInfo.tgzsfzjlx);
            //数据提供者身份证件号码
            ps.setString(8, drpcInfo.tgzsfzjhm);
            //数据提供者计算机代码
            ps.setString(9, drpcInfo.tgzjsjdm);
            //提交时间
            ps.setTimestamp(10, drpcInfo.tjsj);
            //导入时间
            ps.setTimestamp(11, drpcInfo.drsj);
            ps.setString(12, drpcInfo.jsfsdm);
            ps.setString(13, drpcInfo.jsfmc);
            //数据来源
            ps.setString(14, sjly);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条批次信息记录
     * @param DrpcInfo 批次值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Drpcinfo drpcInfo, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_DRPCINFO set drpch=?,drbs=?,tgzlx=?,tgzmc=?,tgzgjdm=?,tgzgjmc=?,tgzsfzjlx=?,tgzsfzjhm=?,tgzjsjdm=?,tjsj=?,drsj=?,jsfsdm=?,jsfmc=?   where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //导入批次号
            ps.setString(1, drpcInfo.drpch);
            //导入笔数
            ps.setBigDecimal(2, drpcInfo.drbs);
            //数据提供者类型
            ps.setString(3, drpcInfo.tgzlx);
            //数据提供者名称
            ps.setString(4, drpcInfo.tgzmc);
            //数据提供者国籍代码
            ps.setString(5, drpcInfo.tgzgjdm);
            //数据提供者国籍名称
            ps.setString(6, drpcInfo.tgzgjmc);
            //数据提供者身份证件类型
            ps.setString(7, drpcInfo.tgzsfzjhm);
            //数据提供者身份证件号码
            ps.setString(8, drpcInfo.tgzsfzjhm);
            //数据提供者计算机代码
            ps.setString(9, drpcInfo.tgzjsjdm);
            //提交时间
            ps.setTimestamp(10, drpcInfo.tjsj);
            //导入时间
            ps.setTimestamp(11, drpcInfo.drsj);
            ps.setString(12, drpcInfo.jsfsdm);
            ps.setString(13, drpcInfo.jsfmc);
            ps.setString(14, drpcInfo.drpch);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条批次信息记录
     * @param drpcInfoList 批量倒入主表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList drpcInfoList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_DRPCINFO where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < drpcInfoList.size(); i++) {
                Drpcinfo drpcinfo = (Drpcinfo) drpcInfoList.get(i);
                ps.setString(1, drpcinfo.drpch);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取批次信息表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 批量倒入主表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList DrpcList = new ArrayList();
        String sql = "select drpch,drbs,tgzlx,tgzmc,tgzgjdm,tgzgjmc,tgzsfzjlx,tgzsfzjhm,tgzjsjdm,tjsj,drsj,jsfsdm,jsfmc from QSDB.QS_JL_DRPCINFO " +
                     condition;
        Debug.out("批次信息表表的查询语句 " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Drpcinfo drpcinfo = new Drpcinfo();
                drpcinfo.setDrpch(rs.getString("drpch"));
                drpcinfo.setDrbs(rs.getBigDecimal("drbs"));
                drpcinfo.setTgzlx(rs.getString("tgzlx"));
                drpcinfo.setTgzmc(rs.getString("tgzmc"));
                drpcinfo.setTgzgjdm(rs.getString("tgzgjdm"));
                drpcinfo.setTgzgjmc(rs.getString("tgzgjmc"));
                drpcinfo.setTgzsfzjlx(rs.getString("tgzsfzjlx"));
                drpcinfo.setTgzsfzjhm(rs.getString("tgzsfzjhm"));
                drpcinfo.setTgzjsjdm(rs.getString("tgzjsjdm"));
                drpcinfo.setTjsj(rs.getTimestamp("tjsj"));
                drpcinfo.setDrsj(rs.getTimestamp("drsj"));
                drpcinfo.setJsfsdm(rs.getString("jsfsdm"));
                drpcinfo.setJsfmc(rs.getString("jsfmc"));
                DrpcList.add(drpcinfo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return DrpcList;
    }

    /**
     * 根据主键获取批次信息主表值对象
     * @param drpcinfo 批次信息值对象
     * @param conn 数据库连接对象
     * @return批次信息值对象
     * @throws SQLException
     */
    public static Object get(Drpcinfo drpcinfo, Connection conn) throws
            SQLException {
        Drpcinfo drpcinfo1 = new Drpcinfo();
        String sql = "select drpch,drbs,tgzlx,tgzmc,tgzgjdm,tgzgjmc,tgzsfzjlx,tgzsfzjhm,tgzjsjdm,tjsj,drsj,jsfsdm,jsfmc from QSDB.QS_JL_DRPCINFO   where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, drpcinfo.drpch);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                drpcinfo1.setDrpch(rs.getString("drpch"));
                drpcinfo1.setDrbs(rs.getBigDecimal("drbs"));
                drpcinfo1.setTgzlx(rs.getString("tgzlx"));
                drpcinfo1.setTgzmc(rs.getString("tgzmc"));
                drpcinfo1.setTgzgjdm(rs.getString("tgzgjdm"));
                drpcinfo1.setTgzgjmc(rs.getString("tgzgjmc"));
                drpcinfo1.setTgzsfzjlx(rs.getString("tgzsfzjlx"));
                drpcinfo1.setTgzsfzjhm(rs.getString("tgzsfzjhm"));
                drpcinfo1.setTgzjsjdm(rs.getString("tgzjsjdm"));
                drpcinfo1.setTjsj(rs.getTimestamp("tjsj"));
                drpcinfo1.setDrsj(rs.getTimestamp("drsj"));
                drpcinfo1.setJsfsdm(rs.getString("jsfsdm"));
                drpcinfo1.setJsfmc(rs.getString("jsfmc"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return drpcinfo1;
    }

    /**
     * 更新一条批量倒入主表记录状态
     * @param zcwh 倒入主表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void updateTjsj(Drpcinfo drif, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_DRPCINFO set TJSJ=? where drpch = ?";
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql);
            //纳税人名称
            ps.setTimestamp(1, drif.getTjsj());
            //申报表号
            ps.setString(2, drif.getDrpch());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除批次信息记录
     * @param drpcInfoList 批量倒入主表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void deletePc(String pch, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_DRPCINFO where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pch);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
