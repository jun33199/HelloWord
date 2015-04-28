package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo;

public class CqxxCwbDAO extends BaseDAO {
    /**
     * 向拆迁信息表插入新数据
     *
     * @param cqxxImportErrbvo
     *            CqxxImportErrbvo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void insert(CqxxImportErrbvo cqxxImportErrbvo,
                              Connection conn) throws SQLException {
        String sql = "INSERT INTO QSDB.QS_JL_CQCWXXB "
                     +
                     "(CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                     +
                "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                     + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS,CWLX,CWLXMC) "
                     +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // '拆迁人名称'
            ps.setString(1, cqxxImportErrbvo.getCqrmc());
            // '拆迁范围'
            ps.setString(2, cqxxImportErrbvo.getCqfw());
            // '被拆迁人名称'
            ps.setString(3, cqxxImportErrbvo.getBcqrmc());
            // '被拆迁人类型代码'
            ps.setString(4, cqxxImportErrbvo.getBcqrlxdm());
            // '被拆迁人类型名称'
            ps.setString(5, cqxxImportErrbvo.getBcqrlxmc());
            // '证件类型代码'
            ps.setString(6, cqxxImportErrbvo.getZjlxdm());
            // '证件类型名称'
            ps.setString(7, cqxxImportErrbvo.getZjlxmc());
            // '证件号码'
            ps.setString(8, cqxxImportErrbvo.getZjhm());
            // '拆迁详细地址'
            ps.setString(9, cqxxImportErrbvo.getCqxxdz());
            // '补偿金额'
            ps.setString(10, cqxxImportErrbvo.getBcje());
            // '补偿类型代码'
            ps.setString(11, cqxxImportErrbvo.getBclxdm());
            // '补偿类型名称'
            ps.setString(12, cqxxImportErrbvo.getBclxmc());
            // '补偿面积'
            ps.setString(13, cqxxImportErrbvo.getBcmj());
            // '补偿房屋地址'
            ps.setString(14, cqxxImportErrbvo.getBcfwdz());
            // '拆迁许可证号'
            ps.setString(15, cqxxImportErrbvo.getCqxkzh());
            // '区县代码'
            ps.setString(16, cqxxImportErrbvo.getQxdm());
            // '录入人'
            ps.setString(17, cqxxImportErrbvo.getLrr());
            // '创建人'
            ps.setString(18, cqxxImportErrbvo.getCjr());
            // '录入日期'
            ps.setTimestamp(19, cqxxImportErrbvo.getLrrq());
            // '创建日期'
            ps.setTimestamp(20, cqxxImportErrbvo.getCjrq());
            // '拆迁信息编号'
            ps.setString(21, cqxxImportErrbvo.getCqxxbh());
            // '数据来源'
            ps.setString(22, cqxxImportErrbvo.getSjly());
            // '所在区县'
            ps.setString(23, cqxxImportErrbvo.getSzqx());
            // '税务机关组织机构代码'
            ps.setString(24, cqxxImportErrbvo.getSwjgzzjgdm());
            // '拆迁项目名称'
            ps.setString(25, cqxxImportErrbvo.getCqxmmc());
            // '拆迁面积'
            ps.setString(26, cqxxImportErrbvo.getCqmj());
            // 拆迁许可证审批时间
            ps.setString(27, cqxxImportErrbvo.getCqxkzspsj());
            // 共居人姓名
            ps.setString(28, cqxxImportErrbvo.getGjrmc());
            // 正式房屋间数
            ps.setString(29, cqxxImportErrbvo.getZsfwjs());
            // 错误类型
            ps.setString(30, cqxxImportErrbvo.getCwlx());
            // 错误类型名称
            ps.setString(31, cqxxImportErrbvo.getCwlxmc());
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
        String sql = "DELETE FROM QSDB.QS_JL_CQCWXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < bhList.size(); i++) {
                CqxxImportErrbvo cqxxImportErrbvo = (CqxxImportErrbvo) bhList
                        .get(i);
                // String bh = (String)bhList.get(i);

                ps.setString(1, cqxxImportErrbvo.getCqxxbh());
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**删除记录
     * @param cqxxImportErrbvo
     * @param conn
     * @throws SQLException
     */
    public static void delete(CqxxImportErrbvo cqxxImportErrbvo,
                              Connection conn) throws SQLException {
        String sql = "DELETE FROM QSDB.QS_JL_CQCWXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, cqxxImportErrbvo.getCqxxbh());
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
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList rsList = new ArrayList();
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS,CWLX,CWLXMC from QSDB.QS_JL_CQCWXXB "
                + condition;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CqxxImportErrbvo cqxxImportErrbvo = new CqxxImportErrbvo();
                cqxxImportErrbvo.setCqrmc(rs.getString("CQRMC")); // '拆迁人名称'
                cqxxImportErrbvo.setCqfw(rs.getString("CQFW")); // '拆迁范围'
                cqxxImportErrbvo.setBcqrmc(rs.getString("BCQRMC")); // '被拆迁人名称'
                cqxxImportErrbvo.setBcqrlxdm(rs.getString("BCQRLXDM")); // '被拆迁人类型代码'
                cqxxImportErrbvo.setBcqrlxmc(rs.getString("BCQRLXMC")); // '被拆迁人类型名称'
                cqxxImportErrbvo.setZjlxdm(rs.getString("ZJLXDM")); // '证件类型代码'
                cqxxImportErrbvo.setZjlxmc(rs.getString("ZJLXMC")); // '证件类型名称'
                cqxxImportErrbvo.setZjhm(rs.getString("ZJHM")); // '证件号码'
                cqxxImportErrbvo.setCqxxdz(rs.getString("CQXXDZ")); // '拆迁详细地址'
                cqxxImportErrbvo.setBcje(rs.getString("BCJE")); // '补偿金额'
                cqxxImportErrbvo.setBclxdm(rs.getString("BCLXDM")); // '补偿类型代码'
                cqxxImportErrbvo.setBclxmc(rs.getString("BCLXMC")); // '补偿类型名称'
                cqxxImportErrbvo.setBcmj(rs.getString("BCMJ")); // '补偿面积'
                cqxxImportErrbvo.setBcfwdz(rs.getString("BCFWDZ")); // '补偿房屋地址'
                cqxxImportErrbvo.setCqxkzh(rs.getString("CQXKZH")); // '拆迁许可证号'
                cqxxImportErrbvo.setQxdm(rs.getString("QXDM")); // '区县代码'
                cqxxImportErrbvo.setLrr(rs.getString("LRR")); // '录入人'
                cqxxImportErrbvo.setCjr(rs.getString("CJR")); // '创建人'
                cqxxImportErrbvo.setLrrq(rs.getTimestamp("LRRQ")); // '录入日期'
                cqxxImportErrbvo.setCjrq(rs.getTimestamp("CJRQ")); // '创建日期'
                cqxxImportErrbvo.setCqxxbh(rs.getString("cqxxbh")); // '拆迁信息编号'
                cqxxImportErrbvo.setSjly(rs.getString("SJLY")); // '数据来源'
                cqxxImportErrbvo.setSzqx(rs.getString("szqx")); // '所在区县'
                cqxxImportErrbvo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '税务机关组织机构代码'
                cqxxImportErrbvo.setCqxmmc(rs.getString("CQXMMC")); // '拆迁项目名称'
                cqxxImportErrbvo.setCqmj(rs.getString("CQMJ")); // '拆迁面积'

                // 拆迁许可证审批时间
                cqxxImportErrbvo.setCqxkzspsj(rs.getString("CQXKZHSPSJ"));
                // 共居人姓名
                cqxxImportErrbvo.setGjrmc(rs.getString("GJRMC"));
                // 正式房屋间数
                cqxxImportErrbvo.setZsfwjs(rs.getString("ZSFWJS"));

                // 错误类型
                cqxxImportErrbvo.setCwlx(rs.getString("CWLX"));
                // 错误类型名称
                cqxxImportErrbvo.setCwlxmc(rs.getString("CWLXMC"));

                rsList.add(cqxxImportErrbvo);
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
    public static CqxxImportErrbvo get(String cqxxbh, Connection conn) throws
            SQLException {
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS,CWLX,CWLXMC from QSDB.QS_JL_CQCWXXB where cqxxbh="
                + cqxxbh;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        CqxxImportErrbvo cqxxImportErrbvo = new CqxxImportErrbvo();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cqxxImportErrbvo.setCqrmc(rs.getString("CQRMC")); // '拆迁人名称'
                cqxxImportErrbvo.setCqfw(rs.getString("CQFW")); // '拆迁范围'
                cqxxImportErrbvo.setBcqrmc(rs.getString("BCQRMC")); // '被拆迁人名称'
                cqxxImportErrbvo.setBcqrlxdm(rs.getString("BCQRLXDM")); // '被拆迁人类型代码'
                cqxxImportErrbvo.setBcqrlxmc(rs.getString("BCQRLXMC")); // '被拆迁人类型名称'
                cqxxImportErrbvo.setZjlxdm(rs.getString("ZJLXDM")); // '证件类型代码'
                cqxxImportErrbvo.setZjlxmc(rs.getString("ZJLXMC")); // '证件类型名称'
                cqxxImportErrbvo.setZjhm(rs.getString("ZJHM")); // '证件号码'
                cqxxImportErrbvo.setCqxxdz(rs.getString("CQXXDZ")); // '拆迁详细地址'
                cqxxImportErrbvo.setBcje(rs.getString("BCJE")); // '补偿金额'
                cqxxImportErrbvo.setBclxdm(rs.getString("BCLXDM")); // '补偿类型代码'
                cqxxImportErrbvo.setBclxmc(rs.getString("BCLXMC")); // '补偿类型名称'
                cqxxImportErrbvo.setBcmj(rs.getString("BCMJ")); // '补偿面积'
                cqxxImportErrbvo.setBcfwdz(rs.getString("BCFWDZ")); // '补偿房屋地址'
                cqxxImportErrbvo.setCqxkzh(rs.getString("CQXKZH")); // '拆迁许可证号'
                cqxxImportErrbvo.setQxdm(rs.getString("QXDM")); // '区县代码'
                cqxxImportErrbvo.setLrr(rs.getString("LRR")); // '录入人'
                cqxxImportErrbvo.setCjr(rs.getString("CJR")); // '创建人'
                cqxxImportErrbvo.setLrrq(rs.getTimestamp("LRRQ")); // '录入日期'
                cqxxImportErrbvo.setCjrq(rs.getTimestamp("CJRQ")); // '创建日期'
                cqxxImportErrbvo.setCqxxbh(rs.getString("cqxxbh")); // '拆迁信息编号'
                cqxxImportErrbvo.setSjly(rs.getString("SJLY")); // '数据来源'
                cqxxImportErrbvo.setSzqx(rs.getString("szqx")); // '所在区县'
                cqxxImportErrbvo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '税务机关组织机构代码'
                cqxxImportErrbvo.setCqxmmc(rs.getString("CQXMMC")); // '拆迁项目名称'
                cqxxImportErrbvo.setCqmj(rs.getString("CQMJ")); // '拆迁面积'

                // 拆迁许可证审批时间
                cqxxImportErrbvo.setCqxkzspsj(rs.getString("CQXKZHSPSJ"));
                // 共居人姓名
                cqxxImportErrbvo.setGjrmc(rs.getString("GJRMC"));
                // 正式房屋间数
                cqxxImportErrbvo.setZsfwjs(rs.getString("ZSFWJS"));
                // 错误类型
                cqxxImportErrbvo.setCwlx(rs.getString("CWLX"));
                // 错误类型名称
                cqxxImportErrbvo.setCwlxmc(rs.getString("CWLXMC"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return cqxxImportErrbvo;
    }

    /**
     * 更新拆迁信息
     *
     * @param cqxxImportErrbvo
     *            String 拆迁信息vo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void update(CqxxImportErrbvo cqxxImportErrbvo,
                              Connection conn) throws SQLException {
        String sql = "update QSDB.QS_JL_CQCWXXB SET "
                     +
                     "CQRMC=?,CQFW=?,BCQRMC=?,BCQRLXDM=?,BCQRLXMC=?,ZJLXDM=?,"
                     +
                "ZJLXMC=?,ZJHM=?,CQXXDZ=?,BCJE=?,BCLXDM=?,BCLXMC=?,BCMJ=?,"
                     + "BCFWDZ=?,CQXKZH=?,"
                     + "LRRQ=?,SZQX=?,CQXMMC=?,CQMJ=?,CQXKZHSPSJ=?,GJRMC=?,ZSFWJS=?,CWLX=?,CWLXMC=? WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // '拆迁人名称'
            ps.setString(1, cqxxImportErrbvo.getCqrmc());
            // '拆迁范围'
            ps.setString(2, cqxxImportErrbvo.getCqfw());
            // '被拆迁人名称'
            ps.setString(3, cqxxImportErrbvo.getBcqrmc());
            // '被拆迁人类型代码'
            ps.setString(4, cqxxImportErrbvo.getBcqrlxdm());
            // '被拆迁人类型名称'
            ps.setString(5, cqxxImportErrbvo.getBcqrlxmc());
            // '证件类型代码'
            ps.setString(6, cqxxImportErrbvo.getZjlxdm());
            // '证件类型名称'
            ps.setString(7, cqxxImportErrbvo.getZjlxmc());
            // '证件号码'
            ps.setString(8, cqxxImportErrbvo.getZjhm());
            // '拆迁详细地址'
            ps.setString(9, cqxxImportErrbvo.getCqxxdz());
            // '补偿金额'
            ps.setString(10, cqxxImportErrbvo.getBcje());
            // '补偿类型代码'
            ps.setString(11, cqxxImportErrbvo.getBclxdm());
            // '补偿类型名称'
            ps.setString(12, cqxxImportErrbvo.getBclxmc());
            // '补偿面积'
            ps.setString(13, cqxxImportErrbvo.getBcmj());
            // '补偿房屋地址'
            ps.setString(14, cqxxImportErrbvo.getBcfwdz());
            // '拆迁许可证号'
            ps.setString(15, cqxxImportErrbvo.getCqxkzh());
            // '区县代码'
            // ps.setString(16, cqxxImportErrbvo.getQxdm());
            // '录入人'
            // ps.setString(17, cqxxImportErrbvo.getLrr());
            // '创建人'
            // ps.setString(18, cqxxImportErrbvo.getCjr());
            // '录入日期'
            ps.setTimestamp(16, cqxxImportErrbvo.getLrrq());
            // '创建日期'
            // ps.setTimestamp(20, cqxxImportErrbvo.getCjrq());
            // '拆迁信息编号'
            ps.setString(20, cqxxImportErrbvo.getCqxxbh());
            // '数据来源'
            // ps.setString(21, cqxxImportErrbvo.getSjly());
            // '所在区县'
            ps.setString(17, cqxxImportErrbvo.getSzqx());
            // '税务机关组织机构代码'
            // ps.setString(23, cqxxImportErrbvo.getSwjgzzjgdm());
            // '拆迁项目名称'
            ps.setString(18, cqxxImportErrbvo.getCqxmmc());
            // '拆迁面积'
            ps.setString(19, cqxxImportErrbvo.getCqmj());

            // 拆迁许可证审批时间
            ps.setString(20, cqxxImportErrbvo.getCqxkzspsj());
            // 共居人姓名
            ps.setString(21, cqxxImportErrbvo.getGjrmc());
            // 正式房屋间数
            ps.setString(22, cqxxImportErrbvo.getZsfwjs());
            // 错误类型
            ps.setString(23, cqxxImportErrbvo.getCwlx());
            // 错误类型名称
            ps.setString(24, cqxxImportErrbvo.getCwlxmc());

            // 执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
