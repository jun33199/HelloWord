package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.ttsoft.common.util.Debug;


/**
 * 非个人信息DAO
 */
public class FgrxxDAO extends BaseDAO {

    /**
     * 插入一条非个人信息记录
     * @param fgrxx 非个人信息值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Fgrxx fgrxx, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_FGRXX (SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ,CQRLX) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //申报表号
            ps.setString(1, fgrxx.sbbh);
            //计算机代码
            ps.setString(2, fgrxx.jsjdm);
            //纳税人名称
            ps.setString(3, fgrxx.nsrmc);
            //开户银行代码
            ps.setString(4, fgrxx.khyhdm);
            //开户银行名称
            ps.setString(5, fgrxx.khyhmc);
            //银行账号
            ps.setString(6, fgrxx.yhzh);
            //联系人姓名
            ps.setString(7, fgrxx.lxrxm);
            //联系电话
            ps.setString(8, fgrxx.lxdh);
            //纳税人类型代码
            ps.setString(9, fgrxx.nsrlxdm);
            //纳税人类型名称
            ps.setString(10, fgrxx.nsrlxmc);
            //房屋交换标识
            ps.setString(11, fgrxx.fwjhbs);
            //录入人
            ps.setString(12, fgrxx.lrr);
            //录入日期
            ps.setTimestamp(13, fgrxx.lrrq);
            //创建人
            ps.setString(14, fgrxx.cjr);
            //创建日期
            ps.setTimestamp(15, fgrxx.cjrq);
            //产权人类型
            ps.setString(16, fgrxx.cqrlx);

            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条非个人信息记录
     * @param fgrxx 非个人信息值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Fgrxx fgrxx, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_FGRXX set NSRMC=?,KHYHDM=?,KHYHMC=?,YHZH=?,LXRXM=?,LXDH=?,NSRLXDM=?,NSRLXMC=?,FWJHBS=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fgrxx.nsrmc);
            ps.setString(2, fgrxx.khyhdm);
            ps.setString(3, fgrxx.khyhmc);
            ps.setString(4, fgrxx.yhzh);
            ps.setString(5, fgrxx.lxrxm);
            ps.setString(6, fgrxx.lxdh);
            ps.setString(7, fgrxx.nsrlxdm);
            ps.setString(8, fgrxx.nsrlxmc);
            ps.setString(9, fgrxx.fwjhbs);
            ps.setString(10, fgrxx.lrr);
            ps.setTimestamp(11, fgrxx.lrrq);
            ps.setString(12, fgrxx.cjr);
            ps.setTimestamp(13, fgrxx.cjrq);
            ps.setString(14, fgrxx.sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条非个人信息记录
     * @param fgrxxList 非个人信息值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList fgrxxList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_FGRXX  where sbbh = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < fgrxxList.size(); i++) {
                Fgrxx fgrxx = (Fgrxx) fgrxxList.get(i);
                ps.setString(1, fgrxx.sbbh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取非个人信息值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 非个人信息值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList FgrxxList = new ArrayList();
        String sql = "select SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_FGRXX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fgrxx Fgrxx1 = new Fgrxx();
                Fgrxx1.setSbbh(rs.getString("SBBH"));
                Fgrxx1.setJsjdm(rs.getString("JSJDM"));
                Fgrxx1.setNsrmc(rs.getString("NSRMC"));
                Fgrxx1.setKhyhdm(rs.getString("KHYHDM"));
                Fgrxx1.setKhyhmc(rs.getString("KHYHMC"));
                Fgrxx1.setYhzh(rs.getString("YHZH"));
                Fgrxx1.setLxrxm(rs.getString("LXRXM"));
                Fgrxx1.setLxdh(rs.getString("LXDH"));
                Fgrxx1.setNsrlxdm(rs.getString("NSRLXDM"));
                Fgrxx1.setNsrlxmc(rs.getString("NSRLXMC"));
                Fgrxx1.setFwjhbs(rs.getString("FWJHBS"));
                Fgrxx1.setLrr(rs.getString("LRR"));
                Fgrxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Fgrxx1.setCjr(rs.getString("CJR"));
                Fgrxx1.setCjrq(rs.getTimestamp("CJRQ"));
                FgrxxList.add(Fgrxx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FgrxxList;
    }

    /**
     * 根据主键获取非个人信息值对象
     * @param fgrxx 非个人信息值对象
     * @param conn 数据库连接对象
     * @return非个人信息值对象
     * @throws SQLException
     */
    public static Object get(Fgrxx fgrxx, Connection conn) throws SQLException {
        Fgrxx Fgrxx1 = new Fgrxx();
        String sql = "select SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_FGRXX   where jsjdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fgrxx.jsjdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Fgrxx1.setSbbh(rs.getString("SBBH"));
                Fgrxx1.setJsjdm(rs.getString("JSJDM"));
                Fgrxx1.setNsrmc(rs.getString("NSRMC"));
                Fgrxx1.setKhyhdm(rs.getString("KHYHDM"));
                Fgrxx1.setKhyhmc(rs.getString("KHYHMC"));
                Fgrxx1.setYhzh(rs.getString("YHZH"));
                Fgrxx1.setLxrxm(rs.getString("LXRXM"));
                Fgrxx1.setLxdh(rs.getString("LXDH"));
                Fgrxx1.setNsrlxdm(rs.getString("NSRLXDM"));
                Fgrxx1.setNsrlxmc(rs.getString("NSRLXMC"));
                Fgrxx1.setFwjhbs(rs.getString("FWJHBS"));
                Fgrxx1.setLrr(rs.getString("LRR"));
                Fgrxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Fgrxx1.setCjr(rs.getString("CJR"));
                Fgrxx1.setCjrq(rs.getTimestamp("CJRQ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Fgrxx1;
    }

    /**
     * 根据申报表号查询非个人信息
     * @param sbbh String
     * @param conn Connection
     * @return Object
     */
    public static Object getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Fgrxx Fgrxx1 = new Fgrxx();
        String sql = "select SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_FGRXX  where sbbh = ? ";
        Debug.out("GrxxDAO.getBySbbh sql: " + sql + " sbbh = " + sbbh);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            if (rs.next()) {
                Fgrxx1.setSbbh(rs.getString("SBBH"));
                Fgrxx1.setJsjdm(rs.getString("JSJDM"));
                Fgrxx1.setNsrmc(rs.getString("NSRMC"));
                Fgrxx1.setKhyhdm(rs.getString("KHYHDM"));
                Fgrxx1.setKhyhmc(rs.getString("KHYHMC"));
                Fgrxx1.setYhzh(rs.getString("YHZH"));
                Fgrxx1.setLxrxm(rs.getString("LXRXM"));
                Fgrxx1.setLxdh(rs.getString("LXDH"));
                Fgrxx1.setNsrlxdm(rs.getString("NSRLXDM"));
                Fgrxx1.setNsrlxmc(rs.getString("NSRLXMC"));
                Fgrxx1.setFwjhbs(rs.getString("FWJHBS"));
                Fgrxx1.setLrr(rs.getString("LRR"));
                Fgrxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Fgrxx1.setCjr(rs.getString("CJR"));
                Fgrxx1.setCjrq(rs.getTimestamp("CJRQ"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return Fgrxx1;
    }

    /**
     * 更新一条个人信息记录
     * @param grxx 个人信息值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void updateFwjhbs(String fwjhbs, String sbbh, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_FGRXX set FWJHBS = ?   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fwjhbs);
            ps.setString(2, sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }


}
