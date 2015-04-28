package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.ttsoft.common.util.Debug;


/**
 * 即时办理基础信息-拆迁DAO
 */
public class JsblcqDAO extends BaseDAO {

    /**
     * 插入一条即时办理基础信息-拆迁记录
     * @param jsblcq 即时办理基础信息-拆迁值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_JSBLCQ (CQXYH,ZLDZ,CQBCE,CQBCSYE,YHBS,ZTBS,CJR,CJRQ,LRR,LRRQ,ND,BZ,SYEYWBZ) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //拆迁协议号码
            ps.setString(1, jsblcq.cqxyh);
            //被拆迁房屋坐落地址
            ps.setString(2, jsblcq.zldz);
            //拆迁补偿额
            ps.setBigDecimal(3, jsblcq.cqbce);
            //拆迁补偿剩余额
            ps.setBigDecimal(4, jsblcq.cqbcsye);
            //用户标识
            ps.setString(5, jsblcq.yhbs);
            //状态标识
            ps.setString(6, jsblcq.ztbs);
            //创建人
            ps.setString(7, jsblcq.cjr);
            //创建日期
            ps.setTimestamp(8, jsblcq.cjrq);
            //录入人
            ps.setString(9, jsblcq.lrr);
            //录入日期
            ps.setTimestamp(10, jsblcq.lrrq);
            //年度
            ps.setString(11, jsblcq.nd);
            //备注
            ps.setString(12, jsblcq.bz);
            //备注
            ps.setString(13, jsblcq.syeywbz);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条即时办理基础信息-拆迁记录
     * @param jsblcq 即时办理基础信息-拆迁值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_JSBLCQ set CQXYH=?,ZLDZ=?,CQBCE=?,CQBCSYE=?,YHBS=?,ZTBS=?,CJR=?,CJRQ=?,LRR=?,LRRQ=?,ND=?,BZ=?,SYEYWBZ=? where cqxyh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblcq.cqxyh);
            ps.setString(2, jsblcq.zldz);
            ps.setBigDecimal(3, jsblcq.cqbce);
            ps.setBigDecimal(4, jsblcq.cqbcsye);
            ps.setString(5, jsblcq.yhbs);
            ps.setString(6, jsblcq.ztbs);
            ps.setString(7, jsblcq.cjr);
            ps.setTimestamp(8, jsblcq.cjrq);
            ps.setString(9, jsblcq.lrr);
            ps.setTimestamp(10, jsblcq.lrrq);
            ps.setString(11, jsblcq.nd);
            ps.setString(12, jsblcq.bz);
            ps.setString(13, jsblcq.syeywbz);
            ps.setString(14, jsblcq.cqxyh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条即时办理基础信息-拆迁记录,恢复剩余补偿额
     * @param jsblcq 即时办理基础信息-拆迁值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void updateBcsye(String sbbh, ArrayList cqList,
                                   Connection conn) throws SQLException {
        if (cqList == null) {
            return;
        }
        String sql = "update  QSDB.QS_JL_JSBLCQ set CQBCSYE = CQBCSYE + "
                     + "(select BCSYBCE from QSDB.QS_JL_SBCQGL "
                     + " where SBBH = ? and CQXYH = ? ) "
                     + " where CQXYH = ? ";
        Debug.out("update bcsye: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int size = cqList.size();
            for (int i = 0; i < size; i++) {
                Jsblcq jsblcq = (Jsblcq) cqList.get(i);
                ps.setString(1, sbbh);
                ps.setString(2, jsblcq.cqxyh);
                ps.setString(3, jsblcq.cqxyh);
                ps.execute();
                ps.clearParameters();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条即时办理基础信息-拆迁记录
     * @param jsblcqList 即时办理基础信息-拆迁值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList jsblcqList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_JSBLCQ  where cqxyh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jsblcqList.size(); i++) {
                Jsblcq jsblcq = (Jsblcq) jsblcqList.get(i);
                ps.setString(1, jsblcq.cqxyh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取即时办理基础信息-拆迁值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 即时办理基础信息-拆迁值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JsblcqList = new ArrayList();
        String sql = "select CQXYH,ZLDZ,CQBCE,CQBCSYE,YHBS,ZTBS,CJR,CJRQ,LRR,LRRQ,ND,BZ,SYEYWBZ from QSDB.QS_JL_JSBLCQ " +
                     condition;
        Debug.out("JsblcqDAO query sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblcq Jsblcq1 = new Jsblcq();
                Jsblcq1.setCqxyh(rs.getString("CQXYH"));
                Jsblcq1.setZldz(rs.getString("ZLDZ"));
                Jsblcq1.setCqbce(rs.getBigDecimal("CQBCE"));
                Jsblcq1.setCqbcsye(rs.getBigDecimal("CQBCSYE"));
                Jsblcq1.setYhbs(rs.getString("YHBS"));
                Jsblcq1.setZtbs(rs.getString("ZTBS"));
                Jsblcq1.setCjr(rs.getString("CJR"));
                Jsblcq1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblcq1.setLrr(rs.getString("LRR"));
                Jsblcq1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblcq1.setNd(rs.getString("ND"));
                Jsblcq1.setBz(rs.getString("BZ"));
                Jsblcq1.setSyeywbz(rs.getString("SYEYWBZ"));
                JsblcqList.add(Jsblcq1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsblcqList;
    }

    /**
     * 根据主键获取即时办理基础信息-拆迁值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 即时办理基础信息-拆迁值对象的集合
     * @throws SQLException
     */
    public static ArrayList queryBySbbh(String sbbh, Connection conn) throws
            SQLException {
        ArrayList JsblcqList = new ArrayList();
        String sql = "select c.CQXYH,c.ZLDZ,c.CQBCE,c.CQBCSYE,c.YHBS,"
                     + "c.ZTBS,c.CJR,c.CJRQ,c.LRR,c.LRRQ,c.ND,c.BZ,c.SYEYWBZ,g.BCSYBCE from QSDB.QS_JL_JSBLCQ c,"
                     +
                "QSDB.QS_JL_SBCQGL g where c.CQXYH = g.CQXYH and g.SBBH = ?";
        Debug.out("JsblcqDAO query sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblcq Jsblcq1 = new Jsblcq();
                Jsblcq1.setCqxyh(rs.getString("CQXYH"));
                Jsblcq1.setZldz(rs.getString("ZLDZ"));
                Jsblcq1.setCqbce(rs.getBigDecimal("CQBCE"));
                Jsblcq1.setCqbcsye(rs.getBigDecimal("CQBCSYE"));
                Jsblcq1.setYhbs(rs.getString("YHBS"));
                Jsblcq1.setZtbs(rs.getString("ZTBS"));
                Jsblcq1.setCjr(rs.getString("CJR"));
                Jsblcq1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblcq1.setLrr(rs.getString("LRR"));
                Jsblcq1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblcq1.setNd(rs.getString("ND"));
                Jsblcq1.setBz(rs.getString("BZ"));
                Jsblcq1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblcq1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                JsblcqList.add(Jsblcq1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsblcqList;
    }

    /**
     * 根据主键获取即时办理基础信息-拆迁值对象
     * @param jsblcq 即时办理基础信息-拆迁值对象
     * @param conn 数据库连接对象
     * @return即时办理基础信息-拆迁值对象
     * @throws SQLException
     */
    public static Object get(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        Jsblcq Jsblcq1 = null;
        String sql = "select CQXYH,ZLDZ,CQBCE,CQBCSYE,YHBS,ZTBS,CJR,CJRQ,LRR,LRRQ,ND,BZ,SYEYWBZ from QSDB.QS_JL_JSBLCQ   where cqxyh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblcq.cqxyh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jsblcq1 = new Jsblcq();
                Jsblcq1.setCqxyh(rs.getString("CQXYH"));
                Jsblcq1.setZldz(rs.getString("ZLDZ"));
                Jsblcq1.setCqbce(rs.getBigDecimal("CQBCE"));
                Jsblcq1.setCqbcsye(rs.getBigDecimal("CQBCSYE"));
                Jsblcq1.setYhbs(rs.getString("YHBS"));
                Jsblcq1.setZtbs(rs.getString("ZTBS"));
                Jsblcq1.setCjr(rs.getString("CJR"));
                Jsblcq1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblcq1.setLrr(rs.getString("LRR"));
                Jsblcq1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblcq1.setNd(rs.getString("ND"));
                Jsblcq1.setBz(rs.getString("BZ"));
                Jsblcq1.setSyeywbz(rs.getString("SYEYWBZ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jsblcq1;
    }

    /**
     * 更新一条即时办理基础信息的剩余额-公有住房记录
     * @param jsblgyzf 即时办理基础信息-公有住房值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void updateSye(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_JSBLCQ set CQBCSYE=?  where CQXYH = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, jsblcq.cqbcsye);
            ps.setString(2, jsblcq.cqxyh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
