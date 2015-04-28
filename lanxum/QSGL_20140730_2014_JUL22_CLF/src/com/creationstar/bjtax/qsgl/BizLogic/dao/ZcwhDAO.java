package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.ttsoft.common.util.Debug;


/**
 * 政策维护DAO
 */
public class ZcwhDAO extends BaseDAO {

    /**
     * 插入一条政策维护记录
     * @param zcwh 政策维护值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Zcwh zcwh, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_ZCWH (ZBDM,ZBMC,ZBZ,SXQSRQ,SXJZRQ,BZ,LRR,LRRQ,ZXBS) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //指标代码
            ps.setString(1, zcwh.zbdm);
            //指标名称
            ps.setString(2, zcwh.zbmc);
            //指标值
            ps.setString(3, zcwh.zbz);
            //生效起始日期
            ps.setTimestamp(4, zcwh.sxqsrq);
            //生效截止日期
            ps.setTimestamp(5, zcwh.sxjzrq);
            //备注
            ps.setString(6, zcwh.bz);
            //录入人
            ps.setString(7, zcwh.lrr);
            //录入日期
            ps.setTimestamp(8, zcwh.lrrq);
            //注销标识
            ps.setString(9, zcwh.zxbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条政策维护记录
     * @param zcwh 政策维护值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Zcwh zcwh, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_ZCWH set ZBDM=?,ZBMC=?,ZBZ=?,SXQSRQ=?,SXJZRQ=?,BZ=?,LRR=?,LRRQ=?,ZXBS=?   where zbdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zcwh.zbdm);
            ps.setString(2, zcwh.zbmc);
            ps.setString(3, zcwh.zbz);
            ps.setTimestamp(4, zcwh.sxqsrq);
            ps.setTimestamp(5, zcwh.sxjzrq);
            ps.setString(6, zcwh.bz);
            ps.setString(7, zcwh.lrr);
            ps.setTimestamp(8, zcwh.lrrq);
            ps.setString(9, zcwh.zxbs);
            ps.setString(10, zcwh.zbdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条政策维护记录
     * @param zcwhList 政策维护值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList zcwhList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_ZCWH  where zbdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < zcwhList.size(); i++) {
                Zcwh zcwh = (Zcwh) zcwhList.get(i);
                ps.setString(1, zcwh.zbdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取政策维护值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 政策维护值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList ZcwhList = new ArrayList();
        String sql =
                "select ZBDM,ZBMC,ZBZ,SXQSRQ,SXJZRQ,BZ,LRR,LRRQ,ZXBS from QSDB.QS_JL_ZCWH " +
                condition;
        Debug.out("政策维护的查询语句 " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Zcwh Zcwh1 = new Zcwh();
                Zcwh1.setZbdm(rs.getString("ZBDM"));
                Zcwh1.setZbmc(rs.getString("ZBMC"));
                Zcwh1.setZbz(rs.getString("ZBZ"));
                Zcwh1.setSxqsrq(rs.getTimestamp("SXQSRQ"));
                Zcwh1.setSxjzrq(rs.getTimestamp("SXJZRQ"));
                Zcwh1.setBz(rs.getString("BZ"));
                Zcwh1.setLrr(rs.getString("LRR"));
                Zcwh1.setLrrq(rs.getTimestamp("LRRQ"));
                Zcwh1.setZxbs(rs.getString("ZXBS"));
                ZcwhList.add(Zcwh1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return ZcwhList;
    }

    /**
     * 根据主键获取政策维护值对象
     * @param zcwh 政策维护值对象
     * @param conn 数据库连接对象
     * @return政策维护值对象
     * @throws SQLException
     */
    public static Object get(Zcwh zcwh, Connection conn) throws SQLException {
        Zcwh Zcwh1 = new Zcwh();
        String sql = "select ZBDM,ZBMC,ZBZ,SXQSRQ,SXJZRQ,BZ,LRR,LRRQ,ZXBS from QSDB.QS_JL_ZCWH   where zbdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zcwh.zbdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Zcwh1.setZbdm(rs.getString("ZBDM"));
                Zcwh1.setZbmc(rs.getString("ZBMC"));
                Zcwh1.setZbz(rs.getString("ZBZ"));
                Zcwh1.setSxqsrq(rs.getTimestamp("SXQSRQ"));
                Zcwh1.setSxjzrq(rs.getTimestamp("SXJZRQ"));
                Zcwh1.setBz(rs.getString("BZ"));
                Zcwh1.setLrr(rs.getString("LRR"));
                Zcwh1.setLrrq(rs.getTimestamp("LRRQ"));
                Zcwh1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Zcwh1;
    }


}
