package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;


/**
 * 契税完税证申报汇总数据DAO
 */
public class QswszhzDAO extends BaseDAO {

    /**
     * 插入一条契税完税证申报汇总数据记录
     * @param qswszhz 契税完税证申报汇总数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_QSWSZHZ (JSJDM,SBHZDH,JKPZH,HZRQ,SJSE,HZKSRQ,HZJSRQ,CLBJDM,SWJGZZJGDM,HZFS,ND,LRRQ,CJRQ,LRR,CJR,HZFSMC,ZSDDM,ZSDMC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //计算机代码
            ps.setString(1, qswszhz.jsjdm);
            //申报汇总单号
            ps.setString(2, qswszhz.sbhzdh);
            //缴款凭证号
            ps.setString(3, qswszhz.jkpzh);
            //汇总日期
            ps.setTimestamp(4, qswszhz.hzrq);
            //实缴税额
            ps.setBigDecimal(5, qswszhz.sjse);

            //modified by zhaobo
            //因为按照批次号汇总是不需要汇总日期的，但是数据库中此两个字段为必填项
            //因此将汇总时候的系统时间录入到数据库中
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            //汇总开始日期
            ps.setTimestamp(6, ts);
            //汇总结束日期
            ps.setTimestamp(7, ts);
            //ended modified

            //处理标记代码
            ps.setString(8, qswszhz.clbjdm);
            //税务机关组织机构代码
            ps.setString(9, qswszhz.swjgzzjgdm);
            //汇总方式
            ps.setString(10, qswszhz.hzfs);
            //年度
            ps.setString(11, qswszhz.nd);
            //录入时间
            ps.setTimestamp(12, qswszhz.lrrq);
            //创建时间
            ps.setTimestamp(13, qswszhz.cjrq);
            //录入人代码
            ps.setString(14, qswszhz.lrr);
            //创建人
            ps.setString(15, qswszhz.cjr);
            ps.setString(16, qswszhz.hzfsmc);
            ps.setString(17, qswszhz.zsddm);
            ps.setString(18, qswszhz.zsdmc);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条契税完税证申报汇总数据记录
     * @param qswszhz 契税完税证申报汇总数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_QSWSZHZ set JSJDM=?,SBHZDH=?,JKPZH=?,HZRQ=?,SJSE=?,HZKSRQ=?,HZJSRQ=?,CLBJDM=?,SWJGZZJGDM=?,HZFS=?,ND=?,LRRQ=?,CJRQ=?,LRR=?,CJR=?,HZFSMC=?  ZSDDM=? ZSDMC=? where jkpzh = ?  and jsjdm = ?  and sbhzdh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //计算机代码
            ps.setString(1, qswszhz.jsjdm);
            //申报汇总单号
            ps.setString(2, qswszhz.sbhzdh);
            //缴款凭证号
            ps.setString(3, qswszhz.jkpzh);
            //汇总日期
            ps.setTimestamp(4, qswszhz.hzrq);
            //实缴税额
            ps.setBigDecimal(5, qswszhz.sjse);
            //汇总开始日期
            ps.setTimestamp(6, qswszhz.hzksrq);
            //汇总结束日期
            ps.setTimestamp(7, qswszhz.hzjsrq);
            //处理标记代码
            ps.setString(8, qswszhz.clbjdm);
            //税务机关组织机构代码
            ps.setString(9, qswszhz.swjgzzjgdm);
            //汇总方式
            ps.setString(10, qswszhz.hzfs);
            //年度
            ps.setString(11, qswszhz.nd);
            //录入时间
            ps.setTimestamp(12, qswszhz.lrrq);
            //创建时间
            ps.setTimestamp(13, qswszhz.cjrq);
            //录入人代码
            ps.setString(14, qswszhz.lrr);
            //创建人
            ps.setString(15, qswszhz.cjr);
            ps.setString(16, qswszhz.hzfsmc);
            ps.setString(17, qswszhz.zsddm);
            ps.setString(18, qswszhz.zsdmc);
            //缴款凭证号
            ps.setString(19, qswszhz.jkpzh);
            //计算机代码
            ps.setString(20, qswszhz.jsjdm);
            //申报汇总单号
            ps.setString(21, qswszhz.sbhzdh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条契税完税证申报汇总数据记录
     * @param qswszhzList 契税完税证申报汇总数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList qswszhzList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_QSWSZHZ  where jkpzh = ?  and jsjdm = ?  and sbhzdh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qswszhzList.size(); i++) {
                Qswszhz qswszhz = (Qswszhz) qswszhzList.get(i);
                ps.setString(1, qswszhz.jkpzh);
                ps.setString(2, qswszhz.jsjdm);
                ps.setString(3, qswszhz.sbhzdh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 撤销缴款书使用
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "delete from  SBDB.SB_JL_QSWSZHZ where ");
        sql.append(condition);

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * 根据主键获取契税完税证申报汇总数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证申报汇总数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QswszhzList = new ArrayList();
        String sql = "select JSJDM,SBHZDH,JKPZH,HZRQ,SJSE,HZKSRQ,HZJSRQ,CLBJDM,SWJGZZJGDM,HZFS,ND,LRRQ,CJRQ,LRR,CJR,HZFSMC,ZSDDM,ZSDMC from SBDB.SB_JL_QSWSZHZ " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszhz Qswszhz1 = new Qswszhz();
                Qswszhz1.setJsjdm(rs.getString("JSJDM"));
                Qswszhz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszhz1.setJkpzh(rs.getString("JKPZH"));
                Qswszhz1.setHzrq(rs.getTimestamp("HZRQ"));
                Qswszhz1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszhz1.setHzksrq(rs.getTimestamp("HZKSRQ"));
                Qswszhz1.setHzjsrq(rs.getTimestamp("HZJSRQ"));
                Qswszhz1.setClbjdm(rs.getString("CLBJDM"));
                Qswszhz1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszhz1.setHzfs(rs.getString("HZFS"));
                Qswszhz1.setNd(rs.getString("ND"));
                Qswszhz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszhz1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszhz1.setLrr(rs.getString("LRR"));
                Qswszhz1.setCjr(rs.getString("CJR"));
                Qswszhz1.setHzfsmc(rs.getString("HZFSMC"));
                Qswszhz1.setZsddm(rs.getString("ZSDDM"));
                Qswszhz1.setZsdmc(rs.getString("ZSDMC"));
                QswszhzList.add(Qswszhz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QswszhzList;
    }

    /**
     * 撤销缴款书时查是否分票的方法
     * @param jsjdm String      缴款书主表中的计算机代码
     * @param jkpzh String      缴款书主表中的缴款凭证号
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList query(String jsjdm, String jkpzh, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("");
        sql.append(
                "SELECT t.jkpzh,t.jsjdm,t.sbhzdh FROM SBDB.SB_JL_QSWSZHZ t, ")
                .append(
                "(SELECT t.sbhzdh FROM SBDB.SB_JL_QSWSZHZ t WHERE t.jkpzh = '")
                .append(jkpzh).append("' AND t.jsjdm = '").append(jsjdm).append(
                "') tt ")
                .append("WHERE t.sbhzdh = tt.sbhzdh");

        Debug.out("撤销缴款书时查是否分票的方法 sql : " + sql.toString());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszhz Qswszhz1 = new Qswszhz();
                Qswszhz1.setJsjdm(rs.getString("JSJDM"));
                Qswszhz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszhz1.setJkpzh(rs.getString("JKPZH"));

                list.add(Qswszhz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return list;
    }

    /**
     * 根据主键获取契税完税证申报汇总数据值对象
     * @param qswszhz 契税完税证申报汇总数据值对象
     * @param conn 数据库连接对象
     * @return契税完税证申报汇总数据值对象
     * @throws SQLException
     */
    public static Object get(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        Qswszhz Qswszhz1 = null;
        String sql = "select JSJDM,SBHZDH,JKPZH,HZRQ,SJSE,HZKSRQ,HZJSRQ,CLBJDM,SWJGZZJGDM,HZFS,ND,LRRQ,CJRQ,LRR,CJR,HZFSMC,ZSDDM,ZSDMC from SBDB.SB_JL_QSWSZHZ   where jkpzh = ?  and jsjdm = ?  and sbhzdh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qswszhz.jkpzh);
            ps.setString(2, qswszhz.jsjdm);
            ps.setString(3, qswszhz.sbhzdh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qswszhz1 = new Qswszhz();
                Qswszhz1.setJsjdm(rs.getString("JSJDM"));
                Qswszhz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszhz1.setJkpzh(rs.getString("JKPZH"));
                Qswszhz1.setHzrq(rs.getTimestamp("HZRQ"));
                Qswszhz1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszhz1.setHzksrq(rs.getTimestamp("HZKSRQ"));
                Qswszhz1.setHzjsrq(rs.getTimestamp("HZJSRQ"));
                Qswszhz1.setClbjdm(rs.getString("CLBJDM"));
                Qswszhz1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszhz1.setHzfs(rs.getString("HZFS"));
                Qswszhz1.setNd(rs.getString("ND"));
                Qswszhz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszhz1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszhz1.setLrr(rs.getString("LRR"));
                Qswszhz1.setCjr(rs.getString("CJR"));
                Qswszhz1.setHzfsmc(rs.getString("HZFSMC"));
                Qswszhz1.setZsddm(rs.getString("ZSDDM"));
                Qswszhz1.setZsdmc(rs.getString("ZSDMC"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qswszhz1;
    }


    /**
     * 补录汇总生成的缴款书时，查询完税证汇总表看该缴款书是否有分票的情况或者是否已经完成的分票
     * 只要看其sbhzdh是否为默认的常量即可，是则为有分票的情况且尚未完成分票
     * @param jsjdm String   计算机代码
     * @param sbhzdh String  申报汇总单号
     * @param jkpzh String   缴款凭证号
     * @param conn Connection
     * @return boolean
     * @throws SQLException
     */
    public static String get(String jsjdm, String jkpzh, Connection conn) throws
            SQLException {
        String result = "";

        StringBuffer sql = new StringBuffer(
                "select sbhzdh from SBDB.SB_JL_QSWSZHZ ");
        sql.append("where jsjdm = '").append(jsjdm).append("' AND jkpzh = '")
                .append(jkpzh).append("' ");

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString("sbhzdh");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return result;

    }

    /**
     * 补录汇总生成的缴款书中，用来更新有分票情况的缴款书对应的完税证汇总表数据，将申报汇总单号设为一致
     * @param jsjdm String   计算机代码
     * @param sbhzdh String  申报汇总单号
     * @param jkpzh String   缴款凭证号
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String jsjdm, String sbhzdh, String jkpzh,
                              Connection conn) throws SQLException {

        StringBuffer sql = new StringBuffer(
                "UPDATE SBDB.SB_JL_QSWSZHZ set sbhzdh = '");
        sql.append(sbhzdh).append("' where jsjdm = '").append(jsjdm)
                .append("' AND sbhzdh = '").append(Constants.WSZ_SBHZDH_DEFAULT)
                .append("' AND jkpzh = '").append(jkpzh).append("' ");

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

    }

}
