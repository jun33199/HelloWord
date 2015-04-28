package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BlQueryJksDAO extends BaseDAO {

    public static ArrayList queryJks(StringBuffer condition,
                                     ArrayList resultList, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.*, ")
                .append(
                "b.szsmdm mx_szsmdm, b.jkpzh mx_jkpzh, b.jsjdm mx_jsjdm, ")
                .append(
                "b.yskmdm mx_yskmdm, b.ysjcdm mx_ysjcdm, b.kssl mx_kssl, ")
                .append(
                "b.jsje mx_jsje, b.skssksrq mx_skssksrq, b.skssjsrq mx_skssjsrq, ")
                .append(
                "b.rkje mx_rkje, b.sbbh mx_sbbh, b.sjfc mx_sjfc, b.qjfc mx_qjfc, ")
                .append("b.swjgzzjgdm mx_swjgzzjgdm, b.nd mx_nd, b.sl mx_sl, ")
                .append(
                "b.cjrq mx_cjrq, b.lrrq mx_lrrq, b.qxdm mx_qxdm, b.sjse mx_sjse ")
                .append("FROM sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b ")
                .append("WHERE  a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition)
                .append(
                " AND (a.sklxdm = '100' or (a.sklxdm = '110' AND a.sjly != '51') ) ")
                .append(
                " AND substr(a.zwbs,1,1) != '0' AND substr(a.zwbs,10,1) = '0' ")
                .append(" AND substr(a.zwbs,11,1) = '0' ")
                .append(" ORDER BY a.cjrq ");

        Debug.out("BlQueryJksDAO queryJks say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();

                Sbjkzb sbjkzb = new Sbjkzb();
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkzb.setJkpzh(rs.getString("JKPZH"));
                sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                sbjkzb.setJsjdm(rs.getString("JSJDM"));
                sbjkzb.setFsdm(rs.getString("FSDM"));
                sbjkzb.setLsgxdm(rs.getString("LSGXDM"));
                sbjkzb.setYhdm(rs.getString("YHDM"));
                sbjkzb.setYhmc(rs.getString("YHMC"));
                sbjkzb.setZh(rs.getString("ZH"));
                sbjkzb.setDjzclxdm(rs.getString("DJZCLXDM"));
                sbjkzb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                sbjkzb.setZsswjgzzjgdm(rs.getString("ZSSWJGZZJGDM"));
                sbjkzb.setGjbzhydm(rs.getString("GJBZHYDM"));
                sbjkzb.setGkzzjgdm(rs.getString("GKZZJGDM"));
                sbjkzb.setYskmdm(rs.getString("YSKMDM"));
                sbjkzb.setYsjcdm(rs.getString("YSJCDM"));
                sbjkzb.setSzdm(rs.getString("SZDM"));
                sbjkzb.setLrrq(rs.getTimestamp("LRRQ"));
                sbjkzb.setSbrq(rs.getTimestamp("SBRQ"));
                sbjkzb.setJksj(rs.getTimestamp("JKSJ"));
                sbjkzb.setXjrq(rs.getTimestamp("XJRQ"));
                sbjkzb.setClbjdm(rs.getString("CLBJDM"));
                sbjkzb.setSjje(rs.getBigDecimal("SJJE"));
                sbjkzb.setZyrq(rs.getTimestamp("ZYRQ"));
                sbjkzb.setRkje(rs.getBigDecimal("RKJE"));
                sbjkzb.setZwbs(rs.getString("ZWBS"));
                sbjkzb.setHxrdm(rs.getString("HXRDM"));
                sbjkzb.setHxrmc(rs.getString("HXRMC"));
                sbjkzb.setLrr(rs.getString("LRR"));
                sbjkzb.setBz(rs.getString("BZ"));
                sbjkzb.setHxrq(rs.getTimestamp("HXRQ"));
                sbjkzb.setSbbh(rs.getString("SBBH"));
                sbjkzb.setJydzlxdm(rs.getString("JYDZLXDM"));
                sbjkzb.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                sbjkzb.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                sbjkzb.setSjly(rs.getString("SJLY"));
                sbjkzb.setNd(rs.getString("ND"));
                sbjkzb.setCjrq(rs.getTimestamp("CJRQ"));
                sbjkzb.setQxdm(rs.getString("QXDM"));

                sbjkmx.setCjrq(rs.getTimestamp("mx_cjrq"));
                sbjkmx.setJkpzh(rs.getString("mx_jkpzh"));
                sbjkmx.setJsjdm(rs.getString("mx_jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("mx_jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("mx_kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrq"));
                sbjkmx.setNd(rs.getString("mx_nd"));
                sbjkmx.setQjfc(rs.getBigDecimal("mx_qjfc"));
                sbjkmx.setQxdm(rs.getString("mx_qxdm"));
                sbjkmx.setRkje(rs.getBigDecimal("mx_rkje"));
                sbjkmx.setSbbh(rs.getString("mx_sbbh"));
                sbjkmx.setSjfc(rs.getBigDecimal("mx_sjfc"));
                sbjkmx.setSjse(rs.getBigDecimal("mx_sjse"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("mx_sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("mx_szsmdm"));
                sbjkmx.setYsjcdm(rs.getString("mx_ysjcdm"));
                sbjkmx.setYskmdm(rs.getString("mx_yskmdm"));

                bo.setSbjkzb(sbjkzb);
                bo.setSbjkmx(sbjkmx);
                bo.setSklxdm(sbjkzb.getSklxdm());
                bo.setType(Constants.PT_JKS); //代表普通缴款书

                resultList.add(bo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return resultList;
    }


    public static ArrayList queryDzJks(StringBuffer condition,
                                       ArrayList resultList, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.*, ")
                .append(
                "b.szsmdm mx_szsmdm, b.jkpzh mx_jkpzh, b.jsjdm mx_jsjdm, ")
                .append(
                "b.yskmdm mx_yskmdm, b.ysjcdm mx_ysjcdm, b.kssl mx_kssl, ")
                .append(
                "b.jsje mx_jsje, b.skssksrq mx_skssksrq, b.skssjsrq mx_skssjsrq, ")
                .append(
                "b.rkje mx_rkje, b.sbbh mx_sbbh, b.sjfc mx_sjfc, b.qjfc mx_qjfc, ")
                .append("b.swjgzzjgdm mx_swjgzzjgdm, b.sl mx_sl, ")
                .append("b.cjrq mx_cjrq, b.lrrq mx_lrrq, b.sjse mx_sjse, ")
                .append("b.zwbs mx_zwbs ")
                .append("FROM jkdb.kj_jl_zwtzjkszb a, jkdb.kj_jl_zwtzjksmx b ")
                .append(
                "WHERE a.zbxh = b.zbxh AND a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition)
                .append(" AND (a.sklxdm = '100' or a.sklxdm = '110' ) ")
                .append(" AND substr(b.zwbs,11,1) = '0' ")
                .append(" AND substr(b.zwbs,1,1) != '0' ORDER BY a.cjrq ");

        Debug.out("BlQueryJksDAO queryDzJks say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();

                Sbjkzb sbjkzb = new Sbjkzb();
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkzb.setJkpzh(rs.getString("JKPZH"));
                sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                sbjkzb.setJsjdm(rs.getString("JSJDM"));
                sbjkzb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                sbjkzb.setGjbzhydm(rs.getString("GJBZHYDM"));
                sbjkzb.setGkzzjgdm(rs.getString("GKZZJGDM"));
                sbjkzb.setSzdm(rs.getString("SZDM"));
                sbjkzb.setLrrq(rs.getTimestamp("LRRQ"));
                sbjkzb.setZyrq(rs.getTimestamp("ZYRQ"));
                sbjkzb.setZwbs(rs.getString("MX_ZWBS"));
                sbjkzb.setSbbh(rs.getString("MX_SBBH"));
                sbjkzb.setCjrq(rs.getTimestamp("CJRQ"));

                sbjkmx.setCjrq(rs.getTimestamp("mx_cjrq"));
                sbjkmx.setJkpzh(rs.getString("mx_jkpzh"));
                sbjkmx.setJsjdm(rs.getString("mx_jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("mx_jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("mx_kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrq"));
                sbjkmx.setQjfc(rs.getBigDecimal("mx_qjfc"));
                sbjkmx.setRkje(rs.getBigDecimal("mx_rkje"));
                sbjkmx.setSbbh(rs.getString("mx_sbbh"));
                sbjkmx.setSjfc(rs.getBigDecimal("mx_sjfc"));
                sbjkmx.setSjse(rs.getBigDecimal("mx_sjse"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("mx_sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("mx_szsmdm"));
                sbjkmx.setYsjcdm(rs.getString("mx_ysjcdm"));
                sbjkmx.setYskmdm(rs.getString("mx_yskmdm"));

                bo.setSbjkzb(sbjkzb);
                bo.setSbjkmx(sbjkmx);
                bo.setSklxdm(sbjkzb.getSklxdm());
                bo.setType(Constants.DZ_JKS); //代表调帐的缴款书
                bo.setZbxh(rs.getString("zbxh"));

                resultList.add(bo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return resultList;
    }


    /**
     * 用户输入了查询条件的查询调帐缴款书方法
     * @param condition StringBuffer 基本的查询条件，如计算机代码等
     * @param sjse String          用户输入的查询条件--计税金额
     * @param resultList ArrayList  返回的查询结果
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryDzJks(StringBuffer condition, String sjse,
                                       ArrayList resultList, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT t1.*, ")
                .append("t2.szsmdm mx_szsmdm, t2.jkpzh mx_jkpzh, t2.jsjdm mx_jsjdm, t2.yskmdm mx_yskmdm, t2.ysjcdm mx_ysjcdm, ")
                .append("t2.kssl mx_kssl, t2.jsje mx_jsje, t2.skssksrq mx_skssksrq, t2.skssjsrq mx_skssjsrq, t2.rkje mx_rkje,  ")
                .append("t2.sbbh mx_sbbh, t2.sjfc mx_sjfc, t2.qjfc mx_qjfc, t2.swjgzzjgdm mx_swjgzzjgdm, t2.sl mx_sl, ")
                .append(
                "t2.cjrq mx_cjrq, t2.lrrq mx_lrrq, t2.sjse mx_sjse, t2.zwbs mx_zwbs ")
                .append(
                "FROM jkdb.kj_jl_zwtzjkszb t1, jkdb.kj_jl_zwtzjksmx t2, ")
                .append(
                "(SELECT a.jkpzh, a.jsjdm, sum(b.jsje) total_jsje, sum(b.sjse) total_sjse ")
                .append(" FROM jkdb.kj_jl_zwtzjkszb a, jkdb.kj_jl_zwtzjksmx b ")
                .append(
                " WHERE a.zbxh = b.zbxh AND a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition)
                .append(" AND (a.sklxdm = '100' or a.sklxdm = '110' ) ")
                .append(" AND substr(b.zwbs,1,1) != '0' ")
                .append(" AND substr(b.zwbs,11,1) = '0' ")
                .append(" GROUP BY a.jkpzh, a.jsjdm) t ")
                .append(" WHERE t1.zbxh = t2.zbxh AND t1.jkpzh = t2.jkpzh AND ")
                .append(
                " t1.jsjdm = t2.jsjdm  AND t1.jkpzh = t.jkpzh AND t1.jsjdm = t.jsjdm ")
                .append(" AND t.total_sjse = ").append(sjse).append(
                " ORDER BY t1.cjrq");

        Debug.out("BlQueryJksDAO queryDzJks say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();

                Sbjkzb sbjkzb = new Sbjkzb();
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkzb.setJkpzh(rs.getString("JKPZH"));
                sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                sbjkzb.setJsjdm(rs.getString("JSJDM"));
                sbjkzb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                sbjkzb.setGjbzhydm(rs.getString("GJBZHYDM"));
                sbjkzb.setGkzzjgdm(rs.getString("GKZZJGDM"));
                sbjkzb.setSzdm(rs.getString("SZDM"));
                sbjkzb.setLrrq(rs.getTimestamp("LRRQ"));
                sbjkzb.setZyrq(rs.getTimestamp("ZYRQ"));
                sbjkzb.setZwbs(rs.getString("MX_ZWBS"));
                sbjkzb.setSbbh(rs.getString("MX_SBBH"));
                sbjkzb.setCjrq(rs.getTimestamp("CJRQ"));

                sbjkmx.setCjrq(rs.getTimestamp("mx_cjrq"));
                sbjkmx.setJkpzh(rs.getString("mx_jkpzh"));
                sbjkmx.setJsjdm(rs.getString("mx_jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("mx_jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("mx_kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrq"));
                sbjkmx.setQjfc(rs.getBigDecimal("mx_qjfc"));
                sbjkmx.setRkje(rs.getBigDecimal("mx_rkje"));
                sbjkmx.setSbbh(rs.getString("mx_sbbh"));
                sbjkmx.setSjfc(rs.getBigDecimal("mx_sjfc"));
                sbjkmx.setSjse(rs.getBigDecimal("mx_sjse"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("mx_sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("mx_szsmdm"));
                sbjkmx.setYsjcdm(rs.getString("mx_ysjcdm"));
                sbjkmx.setYskmdm(rs.getString("mx_yskmdm"));

                bo.setSbjkzb(sbjkzb);
                bo.setSbjkmx(sbjkmx);
                bo.setSklxdm(sbjkzb.getSklxdm());
                bo.setType(Constants.DZ_JKS); //代表调帐的缴款书
                bo.setZbxh(rs.getString("zbxh"));

                resultList.add(bo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return resultList;
    }


    public static ArrayList queryWszh(String sbhzdh, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();
        ArrayList wszhList = new ArrayList();
        ArrayList sbbhList = new ArrayList();

        StringBuffer sql = new StringBuffer(
                "SELECT a.wszh,a.sbbh FROM SBDB.SB_JL_QSWSZZ a ");
        sql.append("WHERE a.sbhzdh = '").append(sbhzdh).append("' AND ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).append(
                "' ");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                String wszh = rs.getString(1);
                wszhList.add(wszh);
                String sbbh = rs.getString(2);
                sbbhList.add(sbbh);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }
        resultList.add(wszhList);
        resultList.add(sbbhList);
        return resultList;
    }

    public static void updateSbJks(String jkpzh, String sbbh, String jsjdm,
                                   String zwbs, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "UPDATE SBDB.SB_JL_SBJKZB SET sbbh = '");
        sql.append(sbbh).append("', zwbs = '").append(zwbs)
                .append("' WHERE jkpzh = '").append(jkpzh)
                .append("' AND jsjdm = '").append(jsjdm).append("' ");

        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.execute(sql.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            stm.close();
        }
    }


    public static void updateSbDzJks(String jkpzh, String sbbh, String jsjdm,
                                     String zbxh, String zwbs, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "UPDATE jkdb.kj_jl_zwtzjksmx SET sbbh = '");
        sql.append(sbbh).append("', zwbs = '").append(zwbs)
                .append("' WHERE jkpzh = '").append(jkpzh)
                .append("' AND jsjdm = '").append(jsjdm)
                .append("' AND zbxh = '").append(zbxh).append("' ");

        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.execute(sql.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            stm.close();
        }
    }


    public static ArrayList HzWsz(String condition, Connection conn) throws
            SQLException {
        ArrayList sbjkmxList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT b.szsmdm, ")
                .append("SUM(b.jsje) jsje_total, SUM(b.sjse) sjse_total, ")
                .append("SUM(b.yjhkc) yjhkc_total, COUNT(a.wszh) kssl ")
                .append("FROM (SELECT * FROM SBDB.SB_JL_QSWSZZ WHERE sbbh in (")
                .append(condition).append(") ) ")
                .append("a, sbdb.sb_jl_qswszmx b ")
                .append("WHERE a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm AND ")
//           .append("a.gjbzhydm = '").append(Constants.WSZ_GJBZHYDM).append("' AND ")
                .append("(a.sbhzdh is null OR a.sbhzdh = '') AND ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS).append(
                "' ")
                //.append("a.jbdh is not null")
                //.append("GROUP BY a.lrr, b.szsmdm ")
                .append(" GROUP BY b.szsmdm ")
                .append(" ORDER BY b.szsmdm asc");

        Debug.out("BlQueryJksDAO HzWsz say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Sbjkmx mxVo = new Sbjkmx();

                mxVo.setSzsmdm(rs.getString("szsmdm"));
                mxVo.setJsje(rs.getBigDecimal("jsje_total"));
                mxVo.setKssl(rs.getBigDecimal("kssl"));
                mxVo.setSjse(rs.getBigDecimal("sjse_total"));
                mxVo.setRkje(rs.getBigDecimal("sjse_total"));

                sbjkmxList.add(mxVo);
                System.out.println("汇总出来的税种税目：" + mxVo.getSzsmdm());
                System.out.println("汇总出来的金额：" + mxVo.getSjse());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            ps.close();
        }
        return sbjkmxList;
    }

    public static ArrayList querySbjkmx(String jkpzh, String jsjdm,
                                        Connection conn) throws SQLException {
        ArrayList sbjkmxList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.* ")
                .append("FROM sbdb.sb_jl_sbjkmx a ")
                .append("WHERE a.jkpzh = '").append(jkpzh).append(
                "' AND a.jsjdm = '")
                .append(jsjdm).append("' ORDER BY a.szsmdm asc ");

        Debug.out("BlQueryJksDAO querySbjkmx say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkmx.setCjrq(rs.getTimestamp("cjrq"));
                sbjkmx.setJkpzh(rs.getString("jkpzh"));
                sbjkmx.setJsjdm(rs.getString("jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("lrrq"));
                sbjkmx.setNd(rs.getString("nd"));
                sbjkmx.setQjfc(rs.getBigDecimal("qjfc"));
                sbjkmx.setQxdm(rs.getString("qxdm"));
                sbjkmx.setRkje(rs.getBigDecimal("rkje"));
                sbjkmx.setSbbh(rs.getString("sbbh"));
                sbjkmx.setSjfc(rs.getBigDecimal("sjfc"));
                sbjkmx.setSjse(rs.getBigDecimal("sjse"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("szsmdm"));
                sbjkmx.setYsjcdm(rs.getString("ysjcdm"));
                sbjkmx.setYskmdm(rs.getString("yskmdm"));

                sbjkmxList.add(sbjkmx);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return sbjkmxList;
    }


    public static ArrayList queryDzSbjkmx(String jkpzh, String jsjdm,
                                          String zbxh, Connection conn) throws
            SQLException {
        ArrayList sbjkmxList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.* ")
                .append("FROM jkdb.kj_jl_zwtzjksmx a ")
                .append("WHERE a.jkpzh = '").append(jkpzh).append(
                "' AND a.jsjdm = '")
                .append(jsjdm).append("' AND a.zbxh = '")
                .append(zbxh).append("' and a.sjse>0 ORDER BY a.szsmdm asc ");

        Debug.out("BlQueryJksDAO querySbjkmx say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkmx.setCjrq(rs.getTimestamp("cjrq"));
                sbjkmx.setJkpzh(rs.getString("jkpzh"));
                sbjkmx.setJsjdm(rs.getString("jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("lrrq"));
                sbjkmx.setRkje(rs.getBigDecimal("rkje"));
                sbjkmx.setSjse(rs.getBigDecimal("sjse"));
                sbjkmx.setSbbh(rs.getString("sbbh"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("szsmdm"));

                sbjkmxList.add(sbjkmx);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return sbjkmxList;
    }


    /**
     * 根据主键获取申报缴款主表数据值对象
     * @param sbjkzb 申报缴款主表数据值对象
     * @param conn 数据库连接对象
     * @return申报缴款主表数据值对象
     * @throws SQLException
     */
    public static Object getDzJks(Sbjkzb sbjkzb, Connection conn) throws
            SQLException {
        ArrayList dzList = new ArrayList();
        String sql = "select b.* FROM jkdb.kj_jl_zwtzjkszb a, jkdb.kj_jl_zwtzjksmx b WHERE a.zbxh = b.zbxh AND a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm AND a.jkpzh=? AND b.jsjdm=? and b.sjse>0";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbjkzb.getJkpzh());
            ps.setString(2, sbjkzb.getJsjdm());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbjkzb dzMx = new Sbjkzb();
                dzMx.setJkpzh(rs.getString("JKPZH"));
                dzMx.setSklxdm(rs.getString("SKLXDM"));
                dzMx.setJsjdm(rs.getString("JSJDM"));
                dzMx.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                dzMx.setSzdm(rs.getString("SZDM"));
                dzMx.setLrrq(rs.getTimestamp("LRRQ"));
                //???????????????
                dzMx.setSjje(rs.getBigDecimal("SJSE"));

                dzMx.setRkje(rs.getBigDecimal("RKJE"));
                dzMx.setZwbs(rs.getString("ZWBS"));
                dzMx.setSbbh(rs.getString("SBBH"));
                dzMx.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                dzMx.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                dzMx.setCjrq(rs.getTimestamp("CJRQ"));

                dzList.add(dzMx);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            ps.close();
        }
        return dzList;
    }


    /**
     * 断开汇总的缴款书关联（就是删除完税证汇总表的相关数据）
     * @param qswszhz Qswszhz   完税证汇总表vo
     * @param conn Connection
     * @throws SQLException
     */
    public void removeQswszhzConnect(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        String sbhzdh = qswszhz.getSbhzdh();
        String jkpzh = qswszhz.getJkpzh();
        String jsjdm = qswszhz.getJsjdm();

        StringBuffer sql = new StringBuffer("DELETE FROM SBDB.SB_JL_QSWSZHZ ");
        sql.append(" WHERE jkpzh = '").append(jkpzh)
                .append("' AND jsjdm = '").append(jsjdm)
                .append("' AND sbhzdh = '").append(sbhzdh).append("' ");
        System.out.println("removeQswszhzConnect say SQL :" + sql.toString());
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.execute(sql.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            stm.close();
        }
    }


    public Qswszz getQswszz(String condition, Connection conn) throws
            SQLException {
        Qswszz qswszz = null;

        StringBuffer sql = new StringBuffer("");
        sql.append(
                "SELECT a.wszh, a.ndzb, a.pzzldm, a.clbjdm FROM SBDB.SB_JL_QSWSZZ a ")
                .append("WHERE ")
                .append(condition);

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            if (rs.next()) {
                qswszz = new Qswszz();
                qswszz.setWszh(rs.getString(1));
                qswszz.setNd(rs.getString(2));
                qswszz.setPzzldm(rs.getString(3));
                qswszz.setClbjdm(rs.getString(4));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }
        return qswszz;
    }

    /*
         public static void CallTiaoZhengSKLXProc(String jkpzh, String oldSklxdm, String newSklxdm, UserData ud, String sjly, Connection conn) throws SQLException
         {
     String procedure = "{call jkdb.jk_pkg_zwtz.tiaozhengsklx(?,?,?,?,?,?)}";
        CallableStatement proc = null;

        try
        {
            proc = conn.prepareCall(procedure);
            proc.setString(1, jkpzh );         //缴款凭证号
            proc.setString(2, oldSklxdm );         //原来的税款类型代码
            proc.setString(3, newSklxdm);        //调整后的税款类型代码
            proc.setString(4, CommonUtil.getQxdm(ud) );       //调整人员区县代码
            proc.setString(5, ud.getYhid() );                //调整人员代码
            proc.setString(6, "契税补录调整");       //调整原因
            proc.setString(7, sjly);           //数据来源

            proc.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        finally
        {
            if (proc != null)
            {
                try
                {
                    proc.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
         }*/

    public static ArrayList queryBlJks(StringBuffer condition,
                                       ArrayList resultList, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.*, ")
                .append(
                "b.szsmdm mx_szsmdm, b.jkpzh mx_jkpzh, b.jsjdm mx_jsjdm, ")
                .append(
                "b.yskmdm mx_yskmdm, b.ysjcdm mx_ysjcdm, b.kssl mx_kssl, ")
                .append(
                "b.jsje mx_jsje, b.skssksrq mx_skssksrq, b.skssjsrq mx_skssjsrq, ")
                .append(
                "b.rkje mx_rkje, b.sbbh mx_sbbh, b.sjfc mx_sjfc, b.qjfc mx_qjfc, ")
                .append("b.swjgzzjgdm mx_swjgzzjgdm, b.nd mx_nd, b.sl mx_sl, ")
                .append(
                "b.cjrq mx_cjrq, b.lrrq mx_lrrq, b.qxdm mx_qxdm, b.sjse mx_sjse ")
                .append("FROM sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b ")
                .append("WHERE  a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition)
                .append(
                " AND (a.sklxdm = '100' or (a.sklxdm = '110' AND a.sjly != '51') ) ")
                .append(
                " AND substr(a.zwbs,1,1) != '0' AND substr(a.zwbs,10,1) = '0' ")
                .append(" AND substr(a.zwbs,11,1) = '1' ") //如果为1，标志为补录修改后的缴款书
                .append(" ORDER BY a.cjrq ");

        Debug.out("BlQueryJksDAO queryJks say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();

                Sbjkzb sbjkzb = new Sbjkzb();
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkzb.setJkpzh(rs.getString("JKPZH"));
                sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                sbjkzb.setJsjdm(rs.getString("JSJDM"));
                sbjkzb.setFsdm(rs.getString("FSDM"));
                sbjkzb.setLsgxdm(rs.getString("LSGXDM"));
                sbjkzb.setYhdm(rs.getString("YHDM"));
                sbjkzb.setYhmc(rs.getString("YHMC"));
                sbjkzb.setZh(rs.getString("ZH"));
                sbjkzb.setDjzclxdm(rs.getString("DJZCLXDM"));
                sbjkzb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                sbjkzb.setZsswjgzzjgdm(rs.getString("ZSSWJGZZJGDM"));
                sbjkzb.setGjbzhydm(rs.getString("GJBZHYDM"));
                sbjkzb.setGkzzjgdm(rs.getString("GKZZJGDM"));
                sbjkzb.setYskmdm(rs.getString("YSKMDM"));
                sbjkzb.setYsjcdm(rs.getString("YSJCDM"));
                sbjkzb.setSzdm(rs.getString("SZDM"));
                sbjkzb.setLrrq(rs.getTimestamp("LRRQ"));
                sbjkzb.setSbrq(rs.getTimestamp("SBRQ"));
                sbjkzb.setJksj(rs.getTimestamp("JKSJ"));
                sbjkzb.setXjrq(rs.getTimestamp("XJRQ"));
                sbjkzb.setClbjdm(rs.getString("CLBJDM"));
                sbjkzb.setSjje(rs.getBigDecimal("SJJE"));
                sbjkzb.setZyrq(rs.getTimestamp("ZYRQ"));
                sbjkzb.setRkje(rs.getBigDecimal("RKJE"));
                sbjkzb.setZwbs(rs.getString("ZWBS"));
                sbjkzb.setHxrdm(rs.getString("HXRDM"));
                sbjkzb.setHxrmc(rs.getString("HXRMC"));
                sbjkzb.setLrr(rs.getString("LRR"));
                sbjkzb.setBz(rs.getString("BZ"));
                sbjkzb.setHxrq(rs.getTimestamp("HXRQ"));
                sbjkzb.setSbbh(rs.getString("SBBH"));
                sbjkzb.setJydzlxdm(rs.getString("JYDZLXDM"));
                sbjkzb.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                sbjkzb.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                sbjkzb.setSjly(rs.getString("SJLY"));
                sbjkzb.setNd(rs.getString("ND"));
                sbjkzb.setCjrq(rs.getTimestamp("CJRQ"));
                sbjkzb.setQxdm(rs.getString("QXDM"));

                sbjkmx.setCjrq(rs.getTimestamp("mx_cjrq"));
                sbjkmx.setJkpzh(rs.getString("mx_jkpzh"));
                sbjkmx.setJsjdm(rs.getString("mx_jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("mx_jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("mx_kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrq"));
                sbjkmx.setNd(rs.getString("mx_nd"));
                sbjkmx.setQjfc(rs.getBigDecimal("mx_qjfc"));
                sbjkmx.setQxdm(rs.getString("mx_qxdm"));
                sbjkmx.setRkje(rs.getBigDecimal("mx_rkje"));
                sbjkmx.setSbbh(rs.getString("mx_sbbh"));
                sbjkmx.setSjfc(rs.getBigDecimal("mx_sjfc"));
                sbjkmx.setSjse(rs.getBigDecimal("mx_sjse"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("mx_sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("mx_szsmdm"));
                sbjkmx.setYsjcdm(rs.getString("mx_ysjcdm"));
                sbjkmx.setYskmdm(rs.getString("mx_yskmdm"));

                bo.setSbjkzb(sbjkzb);
                bo.setSbjkmx(sbjkmx);
                bo.setSklxdm(sbjkzb.getSklxdm());
                bo.setType(Constants.PT_JKS); //代表普通缴款书

                resultList.add(bo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return resultList;
    }


    public static ArrayList queryBlDzJks(StringBuffer condition,
                                         ArrayList resultList, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.*, ")
                .append(
                "b.szsmdm mx_szsmdm, b.jkpzh mx_jkpzh, b.jsjdm mx_jsjdm, ")
                .append(
                "b.yskmdm mx_yskmdm, b.ysjcdm mx_ysjcdm, b.kssl mx_kssl, ")
                .append(
                "b.jsje mx_jsje, b.skssksrq mx_skssksrq, b.skssjsrq mx_skssjsrq, ")
                .append(
                "b.rkje mx_rkje, b.sbbh mx_sbbh, b.sjfc mx_sjfc, b.qjfc mx_qjfc, ")
                .append("b.swjgzzjgdm mx_swjgzzjgdm, b.sl mx_sl, ")
                .append("b.cjrq mx_cjrq, b.lrrq mx_lrrq, b.sjse mx_sjse, ")
                .append("b.zwbs mx_zwbs ")
                .append("FROM jkdb.kj_jl_zwtzjkszb a, jkdb.kj_jl_zwtzjksmx b ")
                .append(
                "WHERE a.zbxh = b.zbxh AND a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition)
                .append(" AND (a.sklxdm = '100' or a.sklxdm = '110' ) ")
                .append(" AND substr(b.zwbs,11,1) = '1' ") //如果为1，标志为补录修改后的缴款书
                .append(" AND substr(b.zwbs,1,1) != '0' ORDER BY a.cjrq ");

        Debug.out("BlQueryJksDAO queryDzJks say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();

                Sbjkzb sbjkzb = new Sbjkzb();
                Sbjkmx sbjkmx = new Sbjkmx();

                sbjkzb.setJkpzh(rs.getString("JKPZH"));
                sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                sbjkzb.setJsjdm(rs.getString("JSJDM"));
                sbjkzb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                sbjkzb.setGjbzhydm(rs.getString("GJBZHYDM"));
                sbjkzb.setGkzzjgdm(rs.getString("GKZZJGDM"));
                sbjkzb.setSzdm(rs.getString("SZDM"));
                sbjkzb.setLrrq(rs.getTimestamp("LRRQ"));
                sbjkzb.setZyrq(rs.getTimestamp("ZYRQ"));
                sbjkzb.setZwbs(rs.getString("MX_ZWBS"));
                sbjkzb.setSbbh(rs.getString("MX_SBBH"));
                sbjkzb.setCjrq(rs.getTimestamp("CJRQ"));

                sbjkmx.setCjrq(rs.getTimestamp("mx_cjrq"));
                sbjkmx.setJkpzh(rs.getString("mx_jkpzh"));
                sbjkmx.setJsjdm(rs.getString("mx_jsjdm"));
                sbjkmx.setJsje(rs.getBigDecimal("mx_jsje"));
                sbjkmx.setKssl(rs.getBigDecimal("mx_kssl"));
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrq"));
                sbjkmx.setQjfc(rs.getBigDecimal("mx_qjfc"));
                sbjkmx.setRkje(rs.getBigDecimal("mx_rkje"));
                sbjkmx.setSbbh(rs.getString("mx_sbbh"));
                sbjkmx.setSjfc(rs.getBigDecimal("mx_sjfc"));
                sbjkmx.setSjse(rs.getBigDecimal("mx_sjse"));
                sbjkmx.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                sbjkmx.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                sbjkmx.setSl(rs.getBigDecimal("mx_sl"));
                sbjkmx.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                sbjkmx.setSzsmdm(rs.getString("mx_szsmdm"));
                sbjkmx.setYsjcdm(rs.getString("mx_ysjcdm"));
                sbjkmx.setYskmdm(rs.getString("mx_yskmdm"));

                bo.setSbjkzb(sbjkzb);
                bo.setSbjkmx(sbjkmx);
                bo.setSklxdm(sbjkzb.getSklxdm());
                bo.setType(Constants.DZ_JKS); //代表调帐的缴款书
                bo.setZbxh(rs.getString("zbxh"));

                resultList.add(bo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return resultList;
    }
}