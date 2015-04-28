package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.ttsoft.common.util.Debug;


/**
 * 契税完税证明细数据DAO
 */
public class QswszmxDAO extends BaseDAO {

    /**
     * 插入一条契税完税证明细数据记录
     * @param qswszmx 契税完税证明细数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Qswszmx qswszmx, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_QSWSZMX (WSZH,NDZB,PZZLDM,SZSMDM,JSJDM,SWJGZZJGDM,ZJHM,SZDM,JSJE,SL,YJHKC,SJSE,SKSSKSRQ,SKSSJSRQ,JZBZ,YSKMDM,YSJCDM,ND,CJRQ,LRRQ,QSZYMJ,LRR,CJR,SZSMMC,SZMC,YSKMMC,YSJCMC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //完税证号
            ps.setString(1, qswszmx.wszh);
            //年度字别
            ps.setString(2, qswszmx.ndzb);
            //票证种类代码
            ps.setString(3, qswszmx.pzzldm);
            //税种税目代码
            ps.setString(4, qswszmx.szsmdm);
            Debug.out("qswszmx.szsmdm" + qswszmx.szsmdm + "'");
            //计算机代码
            ps.setString(5, qswszmx.jsjdm);
            //税务机关组织机构代码
            ps.setString(6, qswszmx.swjgzzjgdm);
            //身份证件号码
            ps.setString(7, qswszmx.zjhm);
            //税种代码
            ps.setString(8, qswszmx.szdm);
            //计税金额
            ps.setBigDecimal(9, qswszmx.jsje);
            //税率
            ps.setBigDecimal(10, qswszmx.sl);
            //已缴或扣除
            ps.setBigDecimal(11, qswszmx.yjhkc);
            //实纳税额
            ps.setBigDecimal(12, qswszmx.sjse);
            //税款所属开始日期
            ps.setTimestamp(13, qswszmx.skssksrq);
            //税款所属结束日期
            ps.setTimestamp(14, qswszmx.skssjsrq);
            //记帐标志
            ps.setString(15, qswszmx.jzbz);
            //预算科目代码
            ps.setString(16, qswszmx.yskmdm);
            //预算级次代码
            ps.setString(17, qswszmx.ysjcdm);
            //年度
            ps.setString(18, qswszmx.nd);
            //创建时间
            ps.setTimestamp(19, qswszmx.cjrq);
            //录入时间
            ps.setTimestamp(20, qswszmx.lrrq);
            //房地产权属转移面积
            ps.setBigDecimal(21, qswszmx.qszymj);
            ps.setString(22, qswszmx.lrr);
            ps.setString(23, qswszmx.cjr);
            ps.setString(24, qswszmx.szsmmc);
            Debug.out("qswszmx.szsmmc" + qswszmx.szsmmc + "'");
            ps.setString(25, qswszmx.szmc);
            ps.setString(26, qswszmx.yskmmc);
            Debug.out("qswszmx.yskmmc" + qswszmx.yskmmc + "'");
            ps.setString(27, qswszmx.ysjcmc);
            Debug.out("qswszmx.ysjcmc" + qswszmx.ysjcmc + "'");
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条契税完税证明细数据记录
     * @param qswszmx 契税完税证明细数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Qswszmx qswszmx, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_QSWSZMX set WSZH=?,NDZB=?,PZZLDM=?,SZSMDM=?,JSJDM=?,SWJGZZJGDM=?,ZJHM=?,SZDM=?,JSJE=?,SL=?,YJHKC=?,SJSE=?,SKSSKSRQ=?,SKSSJSRQ=?,JZBZ=?,YSKMDM=?,YSJCDM=?,ND=?, LRR=?,CJRQ=?,QSZYMJ=?,LRRQ=?, CJR=?, SZSMMC=?, SZMC=?, YSKMMC=? YSJCMC=? where ndzb = ?  and pzzldm = ?  and szsmdm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //完税证号
            ps.setString(1, qswszmx.wszh);
            //年度字别
            ps.setString(2, qswszmx.ndzb);
            //票证种类代码
            ps.setString(3, qswszmx.pzzldm);
            //税种税目代码
            ps.setString(4, qswszmx.szsmdm);
            //计算机代码
            ps.setString(5, qswszmx.jsjdm);
            //税务机关组织机构代码
            ps.setString(6, qswszmx.swjgzzjgdm);
            //身份证件号码
            ps.setString(7, qswszmx.zjhm);
            //税种代码
            ps.setString(8, qswszmx.szdm);
            //计税金额
            ps.setBigDecimal(9, qswszmx.jsje);
            //税率
            ps.setBigDecimal(10, qswszmx.sl);
            //已缴或扣除
            ps.setBigDecimal(11, qswszmx.yjhkc);
            //实纳税额
            ps.setBigDecimal(12, qswszmx.sjse);
            //税款所属开始日期
            ps.setTimestamp(13, qswszmx.skssksrq);
            //税款所属结束日期
            ps.setTimestamp(14, qswszmx.skssjsrq);
            //记帐标志
            ps.setString(15, qswszmx.jzbz);
            //预算科目代码
            ps.setString(16, qswszmx.yskmdm);
            //预算级次代码
            ps.setString(17, qswszmx.ysjcdm);
            //年度
            ps.setString(18, qswszmx.nd);

            ps.setString(19, qswszmx.lrr);
            //创建时间
            ps.setTimestamp(20, qswszmx.cjrq);
            //房地产权属转移面积
            ps.setBigDecimal(21, qswszmx.qszymj);
            //录入时间
            ps.setTimestamp(22, qswszmx.lrrq);
            ps.setString(23, qswszmx.cjr);

            ps.setString(24, qswszmx.szsmmc);
            ps.setString(25, qswszmx.szmc);
            ps.setString(26, qswszmx.yskmmc);
            ps.setString(27, qswszmx.ysjcmc);
            ps.setString(28, qswszmx.ndzb);
            ps.setString(29, qswszmx.pzzldm);
            ps.setString(30, qswszmx.szsmdm);
            ps.setString(31, qswszmx.wszh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条契税完税证明细数据记录
     * @param qswszmxList 契税完税证明细数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList qswszmxList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_QSWSZMX  where ndzb = ?  and pzzldm = ?  and szsmdm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qswszmxList.size(); i++) {
                Qswszmx qswszmx = (Qswszmx) qswszmxList.get(i);
                ps.setString(1, qswszmx.ndzb);
                ps.setString(2, qswszmx.pzzldm);
                ps.setString(3, qswszmx.szsmdm);
                ps.setString(4, qswszmx.wszh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 撤销完税证使用
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "delete from  SBDB.SB_JL_QSWSZMX  where ");
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
     * 根据主键获取契税完税证明细数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证明细数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QswszmxList = new ArrayList();
        String sql = "select WSZH,NDZB,PZZLDM,SZSMDM,JSJDM,SWJGZZJGDM,ZJHM,SZDM,JSJE,SL,YJHKC,SJSE,SKSSKSRQ,SKSSJSRQ,JZBZ,YSKMDM,YSJCDM,ND,CJRQ,LRRQ,QSZYMJ,LRR,CJR,SZSMMC,SZMC,YSKMMC,YSJCMC from SBDB.SB_JL_QSWSZMX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszmx Qswszmx1 = new Qswszmx();
                Qswszmx1.setWszh(rs.getString("WSZH"));
                Qswszmx1.setNdzb(rs.getString("NDZB"));
                Qswszmx1.setPzzldm(rs.getString("PZZLDM"));
                Qswszmx1.setSzsmdm(rs.getString("SZSMDM"));
                Qswszmx1.setJsjdm(rs.getString("JSJDM"));
                Qswszmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszmx1.setZjhm(rs.getString("ZJHM"));
                Qswszmx1.setSzdm(rs.getString("SZDM"));
                Qswszmx1.setJsje(rs.getBigDecimal("JSJE"));
                Qswszmx1.setSl(rs.getBigDecimal("SL"));
                Qswszmx1.setYjhkc(rs.getBigDecimal("YJHKC"));
                Qswszmx1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Qswszmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Qswszmx1.setJzbz(rs.getString("JZBZ"));
                Qswszmx1.setYskmdm(rs.getString("YSKMDM"));
                Qswszmx1.setYsjcdm(rs.getString("YSJCDM"));
                Qswszmx1.setNd(rs.getString("ND"));
                Qswszmx1.setNd(rs.getString("LRR"));
                Qswszmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszmx1.setQszymj(rs.getBigDecimal("QSZYMJ"));
                Qswszmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszmx1.setNd(rs.getString("CJR"));
                Qswszmx1.setNd(rs.getString("SZSMMC"));
                Qswszmx1.setNd(rs.getString("SZMC"));
                Qswszmx1.setNd(rs.getString("YSKMMC"));
                Qswszmx1.setNd(rs.getString("YSJCMC"));

                QswszmxList.add(Qswszmx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QswszmxList;
    }

    /**
     * 根据主键获取契税完税证明细数据值对象
     * @param qswszmx 契税完税证明细数据值对象
     * @param conn 数据库连接对象
     * @return契税完税证明细数据值对象
     * @throws SQLException
     */
    public static Object get(Qswszmx qswszmx, Connection conn) throws
            SQLException {
        Qswszmx Qswszmx1 = new Qswszmx();
        String sql = "select WSZH,NDZB,PZZLDM,SZSMDM,JSJDM,SWJGZZJGDM,ZJHM,SZDM,JSJE,SL,YJHKC,SJSE,SKSSKSRQ,SKSSJSRQ,JZBZ,YSKMDM,YSJCDM,ND,CJRQ,LRRQ,QSZYMJ,LRR,CJR,SZSMMC,SZMC,YSKMMC,YSJCMC from SBDB.SB_JL_QSWSZMX   where ndzb = ?  and pzzldm = ?  and szsmdm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qswszmx.ndzb);
            ps.setString(2, qswszmx.pzzldm);
            ps.setString(3, qswszmx.szsmdm);
            ps.setString(4, qswszmx.wszh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qswszmx1.setWszh(rs.getString("WSZH"));
                Qswszmx1.setNdzb(rs.getString("NDZB"));
                Qswszmx1.setPzzldm(rs.getString("PZZLDM"));
                Qswszmx1.setSzsmdm(rs.getString("SZSMDM"));
                Qswszmx1.setJsjdm(rs.getString("JSJDM"));
                Qswszmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszmx1.setZjhm(rs.getString("ZJHM"));
                Qswszmx1.setSzdm(rs.getString("SZDM"));
                Qswszmx1.setJsje(rs.getBigDecimal("JSJE"));
                Qswszmx1.setSl(rs.getBigDecimal("SL"));
                Qswszmx1.setYjhkc(rs.getBigDecimal("YJHKC"));
                Qswszmx1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Qswszmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Qswszmx1.setJzbz(rs.getString("JZBZ"));
                Qswszmx1.setYskmdm(rs.getString("YSKMDM"));
                Qswszmx1.setYsjcdm(rs.getString("YSJCDM"));
                Qswszmx1.setNd(rs.getString("ND"));
                Qswszmx1.setNd(rs.getString("LRR"));
                Qswszmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszmx1.setQszymj(rs.getBigDecimal("QSZYMJ"));
                Qswszmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszmx1.setNd(rs.getString("CJR"));
                Qswszmx1.setNd(rs.getString("SZSMMC"));
                Qswszmx1.setNd(rs.getString("SZMC"));
                Qswszmx1.setNd(rs.getString("YSKMMC"));
                Qswszmx1.setNd(rs.getString("YSJCMC"));

            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qswszmx1;
    }

    /**
     * 更新一条契税完税证明细表记录，仅供打印完税证换号后更新使用
     * @param sql String
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String sql, Connection conn) throws SQLException {
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.execute(sql);

        } catch (SQLException e) {
            throw e;
        } finally {
            stm.close();
        }
    }

}
