package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;

public class CqxxbDAO extends BaseDAO {
    /**
     * 向拆迁信息表插入新数据
     *
     * @param cqxxb
     *            Cqxxb
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void insert(Cqxxb cqxxb, Connection conn) throws SQLException {
        String sql = "INSERT INTO QSDB.QS_JL_CQXXB "
                     +
                     "(CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                     +
                "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                     +
                "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS) "
                     +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // '拆迁人名称'
            ps.setString(1, cqxxb.getCqrmc());
            // '拆迁范围'
            ps.setString(2, cqxxb.getCqfw());
            // '被拆迁人名称'
            ps.setString(3, cqxxb.getBcqrmc());
            // '被拆迁人类型代码'
            ps.setString(4, cqxxb.getBcqrlxdm());
            // '被拆迁人类型名称'
            ps.setString(5, cqxxb.getBcqrlxmc());
            // '证件类型代码'
            ps.setString(6, cqxxb.getZjlxdm());
            // '证件类型名称'
            ps.setString(7, cqxxb.getZjlxmc());
            // '证件号码'
            ps.setString(8, cqxxb.getZjhm());
            // '拆迁详细地址'
            ps.setString(9, cqxxb.getCqxxdz());
            // '补偿金额'
            ps.setBigDecimal(10, cqxxb.getBcje());
            // '补偿类型代码'
            ps.setString(11, cqxxb.getBclxdm());
            // '补偿类型名称'
            ps.setString(12, cqxxb.getBclxmc());
            // '补偿面积'
            ps.setBigDecimal(13, cqxxb.getBcmj());
            // '补偿房屋地址'
            ps.setString(14, cqxxb.getBcfwdz());
            // '拆迁许可证号'
            ps.setString(15, cqxxb.getCqxkzh());
            // '区县代码'
            ps.setString(16, cqxxb.getQxdm());
            // '录入人'
            ps.setString(17, cqxxb.getLrr());
            // '创建人'
            ps.setString(18, cqxxb.getCjr());
            // '录入日期'
            ps.setTimestamp(19, cqxxb.getLrrq());
            // '创建日期'
            ps.setTimestamp(20, cqxxb.getCjrq());
            // '拆迁信息编号'
            ps.setString(21, cqxxb.getCqxxbh());
            // '数据来源'
            ps.setString(22, cqxxb.getSjly());
            // '所在区县'
            ps.setString(23, cqxxb.getSzqx());
            // '税务机关组织机构代码'
            ps.setString(24, cqxxb.getSwjgzzjgdm());
            // '拆迁项目名称'
            ps.setString(25, cqxxb.getCqxmmc());
            // '拆迁面积'
            ps.setBigDecimal(26, cqxxb.getCqmj());
            // 拆迁许可证审批时间
            ps.setTimestamp(27, cqxxb.getCqxkzspsj());
            // 共居人姓名
            ps.setString(28, cqxxb.getGjrmc());
            // 正式房屋间数
            ps.setString(29, cqxxb.getZsfwjs());
            // 执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除记录
     *
     * @param bhList
     *            ArrayList 拆迁信息编号
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void delete(ArrayList bhList, Connection conn) throws
            SQLException {
        String sql = "DELETE FROM QSDB.QS_JL_CQXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < bhList.size(); i++) {
                Cqxxb cqxxb = (Cqxxb) bhList.get(i);
                // String bh = (String)bhList.get(i);

                ps.setString(1, cqxxb.getCqxxbh());
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * 查询拆迁信息
     *
     * @param condition
     *            String
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList rsList = new ArrayList();
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS from QSDB.QS_JL_CQXXB "
                + condition;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cqxxb cqxxb = new Cqxxb();
                cqxxb.setCqrmc(rs.getString("CQRMC")); // '拆迁人名称'
                cqxxb.setCqfw(rs.getString("CQFW")); // '拆迁范围'
                cqxxb.setBcqrmc(rs.getString("BCQRMC")); // '被拆迁人名称'
                cqxxb.setBcqrlxdm(rs.getString("BCQRLXDM")); // '被拆迁人类型代码'
                cqxxb.setBcqrlxmc(rs.getString("BCQRLXMC")); // '被拆迁人类型名称'
                cqxxb.setZjlxdm(rs.getString("ZJLXDM")); // '证件类型代码'
                cqxxb.setZjlxmc(rs.getString("ZJLXMC")); // '证件类型名称'
                cqxxb.setZjhm(rs.getString("ZJHM")); // '证件号码'
                cqxxb.setCqxxdz(rs.getString("CQXXDZ")); // '拆迁详细地址'
                cqxxb.setBcje(rs.getBigDecimal("BCJE")); // '补偿金额'
                cqxxb.setBclxdm(rs.getString("BCLXDM")); // '补偿类型代码'
                cqxxb.setBclxmc(rs.getString("BCLXMC")); // '补偿类型名称'
                cqxxb.setBcmj(rs.getBigDecimal("BCMJ")); // '补偿面积'
                cqxxb.setBcfwdz(rs.getString("BCFWDZ")); // '补偿房屋地址'
                cqxxb.setCqxkzh(rs.getString("CQXKZH")); // '拆迁许可证号'
                cqxxb.setQxdm(rs.getString("QXDM")); // '区县代码'
                cqxxb.setLrr(rs.getString("LRR")); // '录入人'
                cqxxb.setCjr(rs.getString("CJR")); // '创建人'
                cqxxb.setLrrq(rs.getTimestamp("LRRQ")); // '录入日期'
                cqxxb.setCjrq(rs.getTimestamp("CJRQ")); // '创建日期'
                cqxxb.setCqxxbh(rs.getString("cqxxbh")); // '拆迁信息编号'
                cqxxb.setSjly(rs.getString("SJLY")); // '数据来源'
                cqxxb.setSzqx(rs.getString("szqx")); // '所在区县'
                cqxxb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '税务机关组织机构代码'
                cqxxb.setCqxmmc(rs.getString("CQXMMC")); // '拆迁项目名称'
                cqxxb.setCqmj(rs.getBigDecimal("CQMJ")); // '拆迁面积'

                // 拆迁许可证审批时间
                cqxxb.setCqxkzspsj(rs.getTimestamp("CQXKZHSPSJ"));
                // 共居人姓名
                cqxxb.setGjrmc(rs.getString("GJRMC"));
                // 正式房屋间数
                cqxxb.setZsfwjs(rs.getString("ZSFWJS"));

                rsList.add(cqxxb);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return rsList;
    }

    /**
     * 查询拆迁信息
     *
     * @param condition
     *            String
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static Cqxxb get(String cqxxbh, Connection conn) throws SQLException {
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS from QSDB.QS_JL_CQXXB where cqxxbh="
                + cqxxbh;
        // System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        Cqxxb cqxxb = new Cqxxb();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cqxxb.setCqrmc(rs.getString("CQRMC")); // '拆迁人名称'
                cqxxb.setCqfw(rs.getString("CQFW")); // '拆迁范围'
                cqxxb.setBcqrmc(rs.getString("BCQRMC")); // '被拆迁人名称'
                cqxxb.setBcqrlxdm(rs.getString("BCQRLXDM")); // '被拆迁人类型代码'
                cqxxb.setBcqrlxmc(rs.getString("BCQRLXMC")); // '被拆迁人类型名称'
                cqxxb.setZjlxdm(rs.getString("ZJLXDM")); // '证件类型代码'
                cqxxb.setZjlxmc(rs.getString("ZJLXMC")); // '证件类型名称'
                cqxxb.setZjhm(rs.getString("ZJHM")); // '证件号码'
                cqxxb.setCqxxdz(rs.getString("CQXXDZ")); // '拆迁详细地址'
                cqxxb.setBcje(rs.getBigDecimal("BCJE")); // '补偿金额'
                cqxxb.setBclxdm(rs.getString("BCLXDM")); // '补偿类型代码'
                cqxxb.setBclxmc(rs.getString("BCLXMC")); // '补偿类型名称'
                cqxxb.setBcmj(rs.getBigDecimal("BCMJ")); // '补偿面积'
                cqxxb.setBcfwdz(rs.getString("BCFWDZ")); // '补偿房屋地址'
                cqxxb.setCqxkzh(rs.getString("CQXKZH")); // '拆迁许可证号'
                cqxxb.setQxdm(rs.getString("QXDM")); // '区县代码'
                cqxxb.setLrr(rs.getString("LRR")); // '录入人'
                cqxxb.setCjr(rs.getString("CJR")); // '创建人'
                cqxxb.setLrrq(rs.getTimestamp("LRRQ")); // '录入日期'
                cqxxb.setCjrq(rs.getTimestamp("CJRQ")); // '创建日期'
                cqxxb.setCqxxbh(rs.getString("cqxxbh")); // '拆迁信息编号'
                cqxxb.setSjly(rs.getString("SJLY")); // '数据来源'
                cqxxb.setSzqx(rs.getString("szqx")); // '所在区县'
                cqxxb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '税务机关组织机构代码'
                cqxxb.setCqxmmc(rs.getString("CQXMMC")); // '拆迁项目名称'
                cqxxb.setCqmj(rs.getBigDecimal("CQMJ")); // '拆迁面积'

                // 拆迁许可证审批时间
                cqxxb.setCqxkzspsj(rs.getTimestamp("CQXKZHSPSJ"));
                // 共居人姓名
                cqxxb.setGjrmc(rs.getString("GJRMC"));
                // 正式房屋间数
                cqxxb.setZsfwjs(rs.getString("ZSFWJS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return cqxxb;
    }

    /**
     * 更新拆迁信息
     *
     * @param cqxxb
     *            String 拆迁信息vo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void update(Cqxxb cqxxb, Connection conn) throws SQLException {
        String sql = "update QSDB.QS_JL_CQXXB SET "
                     +
                     "CQRMC=?,CQFW=?,BCQRMC=?,BCQRLXDM=?,BCQRLXMC=?,ZJLXDM=?,"
                     +
                "ZJLXMC=?,ZJHM=?,CQXXDZ=?,BCJE=?,BCLXDM=?,BCLXMC=?,BCMJ=?,"
                     + "BCFWDZ=?,CQXKZH=?,QXDM=?,LRR=?,"
                     + "LRRQ=?,SZQX=?,CQXMMC=?,CQMJ=?,CQXKZHSPSJ=?,GJRMC=?,ZSFWJS=?,SWJGZZJGDM=? WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            // '拆迁人名称'
            ps.setString(1, cqxxb.getCqrmc());

            // '拆迁范围'
            ps.setString(2, cqxxb.getCqfw());

            // '被拆迁人名称'
            ps.setString(3, cqxxb.getBcqrmc());

            // '被拆迁人类型代码'
            ps.setString(4, cqxxb.getBcqrlxdm());

            // '被拆迁人类型名称'
            ps.setString(5, cqxxb.getBcqrlxmc());

            // '证件类型代码'
            ps.setString(6, cqxxb.getZjlxdm());

            // '证件类型名称'
            ps.setString(7, cqxxb.getZjlxmc());

            // '证件号码'
            ps.setString(8, cqxxb.getZjhm());

            // '拆迁详细地址'
            ps.setString(9, cqxxb.getCqxxdz());

            // '补偿金额'
            ps.setBigDecimal(10, cqxxb.getBcje());

            // '补偿类型代码'
            ps.setString(11, cqxxb.getBclxdm());

            // '补偿类型名称'
            ps.setString(12, cqxxb.getBclxmc());

            // '补偿面积'
            ps.setBigDecimal(13, cqxxb.getBcmj());

            // '补偿房屋地址'
            ps.setString(14, cqxxb.getBcfwdz());

            // '拆迁许可证号'
            ps.setString(15, cqxxb.getCqxkzh());

            // '区县代码'
            ps.setString(16, cqxxb.getQxdm());

            // '录入人'
            ps.setString(17, cqxxb.getLrr());

            // '创建人'
            // ps.setString(18, cqxxb.getCjr());

            // '录入日期'
            ps.setTimestamp(18, cqxxb.getLrrq());

            // '创建日期'
            // ps.setTimestamp(20, cqxxb.getCjrq());

            // '数据来源'
            // ps.setString(21, cqxxb.getSjly());

            // '所在区县'
            ps.setString(19, cqxxb.getSzqx());

            // '拆迁项目名称'
            ps.setString(20, cqxxb.getCqxmmc());

            // '拆迁面积'
            ps.setBigDecimal(21, cqxxb.getCqmj());

            // 拆迁许可证审批时间
            ps.setTimestamp(22, cqxxb.getCqxkzspsj());

            // 共居人姓名
            ps.setString(23, cqxxb.getGjrmc());

            // 正式房屋间数
            ps.setString(24, cqxxb.getZsfwjs());

            // '拆迁信息编号'
            ps.setString(25, cqxxb.getCqxxbh());

//			 '税务机关组织机构代码'
            ps.setString(26, cqxxb.getSwjgzzjgdm());

            // 执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新拆迁信息
     *
     * @param cqxxb
     *            String 拆迁信息vo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void update(Cqxxb cqxxb, String condition, Connection conn) throws
            SQLException {
        String sql = "update QSDB.QS_JL_CQXXB SET "
                     +
                     "CQRMC=?,CQFW=?,BCQRMC=?,BCQRLXDM=?,BCQRLXMC=?,ZJLXDM=?,"
                     +
                "ZJLXMC=?,ZJHM=?,CQXXDZ=?,BCJE=?,BCLXDM=?,BCLXMC=?,BCMJ=?,"
                     + "BCFWDZ=?,CQXKZH=?,QXDM=?,LRR=?,"
                     +
                "LRRQ=?,SZQX=?,CQXMMC=?,CQMJ=?,CQXKZHSPSJ=?,GJRMC=?,ZSFWJS=?,SWJGZZJGDM=? "
                     + condition;
        // System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            // '拆迁人名称'
            ps.setString(1, cqxxb.getCqrmc());

            // '拆迁范围'
            ps.setString(2, cqxxb.getCqfw());

            // '被拆迁人名称'
            ps.setString(3, cqxxb.getBcqrmc());

            // '被拆迁人类型代码'
            ps.setString(4, cqxxb.getBcqrlxdm());

            // '被拆迁人类型名称'
            ps.setString(5, cqxxb.getBcqrlxmc());

            // '证件类型代码'
            ps.setString(6, cqxxb.getZjlxdm());

            // '证件类型名称'
            ps.setString(7, cqxxb.getZjlxmc());

            // '证件号码'
            ps.setString(8, cqxxb.getZjhm());

            // '拆迁详细地址'
            ps.setString(9, cqxxb.getCqxxdz());

            // '补偿金额'
            ps.setBigDecimal(10, cqxxb.getBcje());

            // '补偿类型代码'
            ps.setString(11, cqxxb.getBclxdm());

            // '补偿类型名称'
            ps.setString(12, cqxxb.getBclxmc());

            // '补偿面积'
            ps.setBigDecimal(13, cqxxb.getBcmj());

            // '补偿房屋地址'
            ps.setString(14, cqxxb.getBcfwdz());

            // '拆迁许可证号'
            ps.setString(15, cqxxb.getCqxkzh());

            // '区县代码'
            ps.setString(16, cqxxb.getQxdm());

            // '录入人'
            ps.setString(17, cqxxb.getLrr());

            // '创建人'
            // ps.setString(18, cqxxb.getCjr());

            // '录入日期'
            ps.setTimestamp(18, cqxxb.getLrrq());

            // '创建日期'
            // ps.setTimestamp(20, cqxxb.getCjrq());

            // '拆迁信息编号'
            // ps.setString(20, cqxxb.getCqxxbh());

            // '数据来源'
            // ps.setString(21, cqxxb.getSjly());

            // '所在区县'
            ps.setString(19, cqxxb.getSzqx());

            // '拆迁项目名称'
            ps.setString(20, cqxxb.getCqxmmc());

            // '拆迁面积'
            ps.setBigDecimal(21, cqxxb.getCqmj());

            // 拆迁许可证审批时间
            ps.setTimestamp(22, cqxxb.getCqxkzspsj());

            // 共居人姓名
            ps.setString(23, cqxxb.getGjrmc());

            // 正式房屋间数
            ps.setString(24, cqxxb.getZsfwjs());

//			 '税务机关组织机构代码'
            ps.setString(25, cqxxb.getSwjgzzjgdm());

            // 执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 查询拆迁信息
     *
     * @param condition
     *            String
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList querySfzhmIstwo(String condition, Connection conn) throws
            SQLException {
        ArrayList rsList = new ArrayList();
        String sql = "select distinct(BCQRMC),ZJHM" + " from QSDB.QS_JL_CQXXB "
                     + condition;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cqxxb cqxxb = new Cqxxb();
                cqxxb.setBcqrmc(rs.getString("BCQRMC")); // '被拆迁人名称'
                cqxxb.setZjhm(rs.getString("ZJHM")); // '证件号码'

                rsList.add(cqxxb);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return rsList;
    }

    /**删除记录
     * @param cqxxb
     * @param conn
     * @throws SQLException
     */
    public static void delete(Cqxxb cqxxb, Connection conn) throws SQLException {
        String sql = "DELETE FROM QSDB.QS_JL_CQXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, cqxxb.getCqxxbh());
            ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
