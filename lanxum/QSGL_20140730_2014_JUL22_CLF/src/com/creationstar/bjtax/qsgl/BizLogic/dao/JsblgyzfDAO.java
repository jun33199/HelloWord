package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.ttsoft.common.util.Debug;


/**
 * 即时办理基础信息-公有住房DAO
 */
public class JsblgyzfDAO extends BaseDAO {

    /**
     * 插入一条即时办理基础信息-公有住房记录
     * @param jsblgyzf 即时办理基础信息-公有住房值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_JSBLGYZF (YGGYZFQSZSH,ZLDZ,QDSJ,JZMJ,CJJG,SYE,ZTBS,LRR,LRRQ,CJR,CJRQ,ND,BZ,SYEYWBZ,FWQSZSH) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //已购公有住房权属证书号
            ps.setString(1, jsblgyzf.yggyzfqszsh);
            //座落地址
            ps.setString(2, jsblgyzf.zldz);
            //出售合同（契约）签订时间
            ps.setTimestamp(3, jsblgyzf.qdsj);
            //建筑面积
            ps.setBigDecimal(4, jsblgyzf.jzmj);
            //成交价格
            ps.setBigDecimal(5, jsblgyzf.cjjg);
            //剩余额
            ps.setBigDecimal(6, jsblgyzf.sye);
            //状态标识
            ps.setString(7, jsblgyzf.ztbs);
            //录入人
            ps.setString(8, jsblgyzf.lrr);
            //录入日期
            ps.setTimestamp(9, jsblgyzf.lrrq);
            //创建人
            ps.setString(10, jsblgyzf.cjr);
            //创建日期
            ps.setTimestamp(11, jsblgyzf.cjrq);
            //年度
            ps.setString(12, jsblgyzf.nd);
            //备注
            ps.setString(13, jsblgyzf.bz);
            //剩余额用完标志
            ps.setString(14, jsblgyzf.syeywbz);
            //房屋权属证书号
            ps.setString(15,jsblgyzf.fwqszsh);            
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条即时办理基础信息-公有住房记录
     * @param jsblgyzf 即时办理基础信息-公有住房值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_JSBLGYZF set YGGYZFQSZSH=?,ZLDZ=?,QDSJ=?,JZMJ=?,CJJG=?,SYE=?,ZTBS=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,ND=?,BZ=?,SYEYWBZ=?,FWQSZSH=?   where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblgyzf.yggyzfqszsh);
            ps.setString(2, jsblgyzf.zldz);
            ps.setTimestamp(3, jsblgyzf.qdsj);
            ps.setBigDecimal(4, jsblgyzf.jzmj);
            ps.setBigDecimal(5, jsblgyzf.cjjg);
            ps.setBigDecimal(6, jsblgyzf.sye);
            ps.setString(7, jsblgyzf.ztbs);
            ps.setString(8, jsblgyzf.lrr);
            ps.setTimestamp(9, jsblgyzf.lrrq);
            ps.setString(10, jsblgyzf.cjr);
            ps.setTimestamp(11, jsblgyzf.cjrq);
            ps.setString(12, jsblgyzf.nd);
            ps.setString(13, jsblgyzf.bz);
            ps.setString(14, jsblgyzf.syeywbz);
            ps.setString(15,jsblgyzf.fwqszsh);
            ps.setString(16,jsblgyzf.yggyzfqszsh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条即时办理基础信息-公有住房记录
     * @param jsblgyzfList 即时办理基础信息-公有住房值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList jsblgyzfList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_JSBLGYZF  where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jsblgyzfList.size(); i++) {
                Jsblgyzf jsblgyzf = (Jsblgyzf) jsblgyzfList.get(i);
                ps.setString(1, jsblgyzf.yggyzfqszsh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取即时办理基础信息-公有住房值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 即时办理基础信息-公有住房值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JsblgyzfList = new ArrayList();
        String sql = "select YGGYZFQSZSH,ZLDZ,QDSJ,JZMJ,CJJG,SYE,ZTBS,LRR,LRRQ,CJR,CJRQ,ND,BZ,SYEYWBZ,FWQSZSH from QSDB.QS_JL_JSBLGYZF " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblgyzf Jsblgyzf1 = new Jsblgyzf();
                Jsblgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Jsblgyzf1.setZldz(rs.getString("ZLDZ"));
                Jsblgyzf1.setQdsj(rs.getTimestamp("QDSJ"));
                Jsblgyzf1.setJzmj(rs.getBigDecimal("JZMJ"));
                Jsblgyzf1.setCjjg(rs.getBigDecimal("CJJG"));
                Jsblgyzf1.setSye(rs.getBigDecimal("SYE"));
                Jsblgyzf1.setZtbs(rs.getString("ZTBS"));
                Jsblgyzf1.setLrr(rs.getString("LRR"));
                Jsblgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblgyzf1.setCjr(rs.getString("CJR"));
                Jsblgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblgyzf1.setNd(rs.getString("ND"));
                Jsblgyzf1.setBz(rs.getString("BZ"));
                Jsblgyzf1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblgyzf1.setFwqszsh(rs.getString("FWQSZSH"));                
                JsblgyzfList.add(Jsblgyzf1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsblgyzfList;
    }

    /**
     * 根据主键获取即时办理基础信息-公有住房值对象
     * @param jsblgyzf 即时办理基础信息-公有住房值对象
     * @param conn 数据库连接对象
     * @return即时办理基础信息-公有住房值对象
     * @throws SQLException
     */
    public static Object get(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        Jsblgyzf Jsblgyzf1 = null;
        String sql = "select YGGYZFQSZSH,ZLDZ,QDSJ,JZMJ,CJJG,SYE,ZTBS,LRR,LRRQ,CJR,CJRQ,ND,BZ,SYEYWBZ,FWQSZSH from QSDB.QS_JL_JSBLGYZF   where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblgyzf.yggyzfqszsh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jsblgyzf1 = new Jsblgyzf();
                Jsblgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Jsblgyzf1.setZldz(rs.getString("ZLDZ"));
                Jsblgyzf1.setQdsj(rs.getTimestamp("QDSJ"));
                Jsblgyzf1.setJzmj(rs.getBigDecimal("JZMJ"));
                Jsblgyzf1.setCjjg(rs.getBigDecimal("CJJG"));
                Jsblgyzf1.setSye(rs.getBigDecimal("SYE"));
                Jsblgyzf1.setZtbs(rs.getString("ZTBS"));
                Jsblgyzf1.setLrr(rs.getString("LRR"));
                Jsblgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblgyzf1.setCjr(rs.getString("CJR"));
                Jsblgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblgyzf1.setNd(rs.getString("ND"));
                Jsblgyzf1.setBz(rs.getString("BZ"));
                Jsblgyzf1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblgyzf1.setFwqszsh(rs.getString("FWQSZSH"));                
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jsblgyzf1;
    }

    /**
     * 根据主键获取即时办理基础信息-公有住房值对象
     * @param jsblgyzf 即时办理基础信息-公有住房值对象
     * @param conn 数据库连接对象
     * @return即时办理基础信息-公有住房值对象
     * @throws SQLException
     */
    public static ArrayList getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        ArrayList jsblgyzfList = new ArrayList();
        String sql = "select a.YGGYZFQSZSH,a.ZLDZ,a.QDSJ,a.JZMJ,a.CJJG,a.SYE,"
                     + "a.ZTBS,a.LRR,a.LRRQ,a.CJR,a.CJRQ,a.ND,a.BZ,a.SYEYWBZ,b.bcdke,a.FWQSZSH from QSDB.QS_JL_JSBLGYZF a,"
                     +
                " QSDB.QS_JL_SBGYZF b where a.yggyzfqszsh = b.yggyzfqszsh "
                     + " and b.sbbh = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblgyzf Jsblgyzf1 = new Jsblgyzf();
                Jsblgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Jsblgyzf1.setZldz(rs.getString("ZLDZ"));
                Jsblgyzf1.setQdsj(rs.getTimestamp("QDSJ"));
                Jsblgyzf1.setJzmj(rs.getBigDecimal("JZMJ"));
                Jsblgyzf1.setCjjg(rs.getBigDecimal("CJJG"));
                Jsblgyzf1.setSye(rs.getBigDecimal("SYE"));
                Jsblgyzf1.setZtbs(rs.getString("ZTBS"));
                Jsblgyzf1.setLrr(rs.getString("LRR"));
                Jsblgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblgyzf1.setCjr(rs.getString("CJR"));
                Jsblgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblgyzf1.setNd(rs.getString("ND"));
                Jsblgyzf1.setBz(rs.getString("BZ"));
                Jsblgyzf1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblgyzf1.setBcdke(rs.getBigDecimal("BCDKE"));
                Jsblgyzf1.setFwqszsh(rs.getString("FWQSZSH"));                  
                jsblgyzfList.add(Jsblgyzf1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return jsblgyzfList;
    }

    /**
     * 更新一条即时办理基础信息-已购公有住房记录,恢复剩余额
     * @param
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void updateDksye(String sbbh, ArrayList gyzfList,
                                   Connection conn) throws SQLException {
        if (gyzfList == null) {
            return;
        }
        String sql = "update  QSDB.QS_JL_JSBLGYZF set SYE = SYE + "
                     + "(select BCDKE from QSDB.QS_JL_SBGYZF "
                     + " where SBBH = ? and YGGYZFQSZSH = ? ) "
                     + " where YGGYZFQSZSH = ? ";
        Debug.out("update QS_JL_JSBLGYZF SYE: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int size = gyzfList.size();
            for (int i = 0; i < size; i++) {
                Jsblgyzf jsblgyzf = (Jsblgyzf) gyzfList.get(i);
                ps.setString(1, sbbh);
                ps.setString(2, jsblgyzf.yggyzfqszsh);
                ps.setString(3, jsblgyzf.yggyzfqszsh);
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
     * 更新一条即时办理基础信息的剩余额-公有住房记录
     * @param jsblgyzf 即时办理基础信息-公有住房值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void updateSye(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_JSBLGYZF set SYE=?  where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, jsblgyzf.sye);
            ps.setString(2, jsblgyzf.yggyzfqszsh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
