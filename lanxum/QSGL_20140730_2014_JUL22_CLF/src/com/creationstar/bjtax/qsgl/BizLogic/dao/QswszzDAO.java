package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.model.bo.QueryWszBo;
import com.creationstar.bjtax.qsgl.model.bo.WszBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;


/**
 * 契税完税证主数据DAO
 */
public class QswszzDAO extends BaseDAO {

    /**
     * 插入一条契税完税证主数据记录
     * @param qswszz 契税完税证主数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Qswszz qswszz, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_QSWSZZ (WSZH,NDZB,PZZLDM,JSJDM,SWJGZZJGDM,DJZCLXDM,DZ,HJSJJE,CLBJDM,SBHZDH,JBDH,ZJLXDM,ZJHM,FSDM,GJBZHYDM,ND,CJR,CJRQ,LRR,LRRQ,YQTS,ZNJZJE,FDCWZ,HTCLRQ,JSJE,MSJS,SBBH,SWJGZZJGDM2,NSRJSJDM,DJZCLXMC,ZJLXMC,FSMC,GJBZHYMC,JSFSDM,JSFSMC,NSRMC,ZSDDM,ZSDMC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, qswszz.wszh);
            ps.setString(2, qswszz.ndzb);
            ps.setString(3, qswszz.pzzldm);
            ps.setString(4, qswszz.jsjdm);
            ps.setString(5, qswszz.swjgzzjgdm);
            ps.setString(6, qswszz.djzclxdm);
            ps.setString(7, qswszz.dz);
            ps.setBigDecimal(8, qswszz.hjsjje);
            ps.setString(9, qswszz.clbjdm);
            ps.setString(10, qswszz.sbhzdh);
            ps.setString(11, qswszz.jbdh);
            ps.setString(12, qswszz.zjlxdm);
            ps.setString(13, qswszz.zjhm);
            ps.setString(14, qswszz.fsdm);
            ps.setString(15, qswszz.gjbzhydm);
            ps.setString(16, qswszz.nd);
            ps.setString(17, qswszz.cjr);
            ps.setTimestamp(18, qswszz.cjrq);
            ps.setString(19, qswszz.lrr);
            ps.setTimestamp(20, qswszz.lrrq);
            ps.setBigDecimal(21, qswszz.yqts);
            ps.setBigDecimal(22, qswszz.znjzje);
            ps.setString(23, qswszz.fdcwz);
            ps.setTimestamp(24, qswszz.htclrq);
            ps.setBigDecimal(25, qswszz.jsje);
            ps.setBigDecimal(26, qswszz.msjs);
            ps.setString(27, qswszz.sbbh);
            ps.setString(28, qswszz.swjgzzjgdm2);
            ps.setString(29, qswszz.nsrjsjdm);
            ps.setString(30, qswszz.djzclxmc);
            ps.setString(31, qswszz.zjlxmc);
            ps.setString(32, qswszz.fsmc);
            ps.setString(33, qswszz.gjbzhymc);
            ps.setString(34, qswszz.jsfsdm);
            ps.setString(35, qswszz.jsfsmc);
            ps.setString(36, qswszz.nsrmc);
            ps.setString(37, qswszz.zsddm);
            ps.setString(38, qswszz.zsdmc);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条契税完税证主数据记录
     * @param qswszz 契税完税证主数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Qswszz qswszz, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_QSWSZZ set WSZH=?,NDZB=?,PZZLDM=?,JSJDM=?,SWJGZZJGDM=?,DJZCLXDM=?,DZ=?,HJSJJE=?,CLBJDM=?,SBHZDH=?,JBDH=?,ZJLXDM=?,ZJHM=?,FSDM=?,GJBZHYDM=?,ND=?,CJR=?,CJRQ=?,LRR=?,LRRQ=?,YQTS=?,ZNJZJE=?,FDCWZ=?,HTCLRQ=?,JSJE=?,MSJS=?,SBBH=?,SWJGZZJGDM2=?,NSRJSJDM=?,DJZCLXMC=?,ZJLXMC=?,FSMC=?,GJBZHYMC=?,JSFSDM=?,JSFSMC=?,NSRMC=? ZSDDM=? ZSDMC=?  where ndzb = ?  and pzzldm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qswszz.wszh);
            ps.setString(2, qswszz.ndzb);
            ps.setString(3, qswszz.pzzldm);
            ps.setString(4, qswszz.jsjdm);
            ps.setString(5, qswszz.swjgzzjgdm);
            ps.setString(6, qswszz.djzclxdm);
            ps.setString(7, qswszz.dz);
            ps.setBigDecimal(8, qswszz.hjsjje);
            ps.setString(9, qswszz.clbjdm);
            ps.setString(10, qswszz.sbhzdh);
            ps.setString(11, qswszz.jbdh);
            ps.setString(12, qswszz.zjlxdm);
            ps.setString(13, qswszz.zjhm);
            ps.setString(14, qswszz.fsdm);
            ps.setString(15, qswszz.gjbzhydm);
            ps.setString(16, qswszz.nd);
            ps.setString(17, qswszz.cjr);
            ps.setTimestamp(18, qswszz.cjrq);
            ps.setString(19, qswszz.lrr);
            ps.setTimestamp(20, qswszz.lrrq);
            ps.setBigDecimal(21, qswszz.yqts);
            ps.setBigDecimal(22, qswszz.znjzje);
            ps.setString(23, qswszz.fdcwz);
            ps.setTimestamp(24, qswszz.htclrq);
            ps.setBigDecimal(25, qswszz.jsje);
            ps.setBigDecimal(26, qswszz.msjs);
            ps.setString(27, qswszz.sbbh);
            ps.setString(28, qswszz.swjgzzjgdm2);
            ps.setString(29, qswszz.nsrjsjdm);
            ps.setString(30, qswszz.djzclxmc);
            ps.setString(31, qswszz.zjlxmc);
            ps.setString(32, qswszz.fsmc);
            ps.setString(33, qswszz.gjbzhymc);
            ps.setString(34, qswszz.jsfsdm);
            ps.setString(35, qswszz.jsfsmc);
            ps.setString(36, qswszz.nsrmc);
            ps.setString(37, qswszz.zsddm);
            ps.setString(38, qswszz.zsdmc);
            ps.setString(39, qswszz.ndzb);
            ps.setString(40, qswszz.pzzldm);
            ps.setString(41, qswszz.wszh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 打印完税证后update打印状态
     * @param condition StringBuffer
     * @param conn Connection
     */
    public void update(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("UPDATE SBDB.SB_JL_QSWSZZ SET clbjdm = '")
                .append(Constants.WSZ_CLBJDM_YWS).append("' ").append(condition);
        Debug.out("update打印状态" + sql);

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ps.close();
        }
    }


    /**
     * 删除多条契税完税证主数据记录
     * @param qswszzList 契税完税证主数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList qswszzList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  SBDB.SB_JL_QSWSZZ  where ndzb = ?  and pzzldm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qswszzList.size(); i++) {
                Qswszz qswszz = (Qswszz) qswszzList.get(i);
                ps.setString(1, qswszz.ndzb);
                ps.setString(2, qswszz.pzzldm);
                ps.setString(3, qswszz.wszh);
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
                "delete from  SBDB.SB_JL_QSWSZZ  where ");
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
     * 根据主键获取契税完税证主数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税完税证主数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QswszzList = new ArrayList();
        String sql = "select WSZH,NDZB,PZZLDM,JSJDM,SWJGZZJGDM,DJZCLXDM,DZ,HJSJJE,CLBJDM,SBHZDH,JBDH,ZJLXDM,ZJHM,FSDM,GJBZHYDM,ND,CJR,CJRQ,LRR,LRRQ,YQTS,ZNJZJE,FDCWZ,HTCLRQ,JSJE,MSJS,SBBH,SWJGZZJGDM2,NSRJSJDM,DJZCLXMC,ZJLXMC,FSMC,GJBZHYMC,JSFSDM,JSFSMC,NSRMC,ZSDDM,ZSDMC from SBDB.SB_JL_QSWSZZ " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszz Qswszz1 = new Qswszz();
                Qswszz1.setWszh(rs.getString("WSZH"));
                Qswszz1.setNdzb(rs.getString("NDZB"));
                Qswszz1.setPzzldm(rs.getString("PZZLDM"));
                Qswszz1.setJsjdm(rs.getString("JSJDM"));
                Qswszz1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszz1.setDjzclxdm(rs.getString("DJZCLXDM"));
                Qswszz1.setNsrjsjdm(rs.getString("NSRJSJDM"));
                Qswszz1.setDz(rs.getString("DZ"));
                Qswszz1.setHjsjje(rs.getBigDecimal("HJSJJE"));
                Qswszz1.setClbjdm(rs.getString("CLBJDM"));
                Qswszz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszz1.setJbdh(rs.getString("JBDH"));
                Qswszz1.setZjlxdm(rs.getString("ZJLXDM"));
                Qswszz1.setZjhm(rs.getString("ZJHM"));
                Qswszz1.setFsdm(rs.getString("FSDM"));
                Qswszz1.setGjbzhydm(rs.getString("GJBZHYDM"));
                Qswszz1.setNd(rs.getString("ND"));
                Qswszz1.setCjr(rs.getString("CJR"));
                Qswszz1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszz1.setLrr(rs.getString("LRR"));
                Qswszz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszz1.setYqts(rs.getBigDecimal("YQTS"));
                Qswszz1.setZnjzje(rs.getBigDecimal("ZNJZJE"));
                Qswszz1.setFdcwz(rs.getString("FDCWZ"));
                Qswszz1.setHtclrq(rs.getTimestamp("HTCLRQ"));
                Qswszz1.setJsje(rs.getBigDecimal("JSJE"));
                Qswszz1.setMsjs(rs.getBigDecimal("MSJS"));
                Qswszz1.setSbbh(rs.getString("SBBH"));
                Qswszz1.setSwjgzzjgdm2(rs.getString("SWJGZZJGDM2"));
                Qswszz1.setNsrjsjdm(rs.getString("NSRJSJDM"));
                Qswszz1.setDjzclxmc(rs.getString("DJZCLXMC"));
                Qswszz1.setZjlxmc(rs.getString("ZJLXMC"));
                Qswszz1.setFsmc(rs.getString("FSMC"));
                Qswszz1.setGjbzhymc(rs.getString("GJBZHYMC"));
                Qswszz1.setJsfsdm(rs.getString("JSFSDM"));
                Qswszz1.setJsfsmc(rs.getString("JSFSMC"));
                Qswszz1.setNsrmc(rs.getString("NSRMC"));
                Qswszz1.setZsddm(rs.getString("ZSDDM"));
                Qswszz1.setZsdmc(rs.getString("ZSDMC"));
                QswszzList.add(Qswszz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QswszzList;
    }

    /**
     * 与上个query方法不同的地方在于，此query方法是完税证子表和明细表以及汇总表的关联查询
     * @param conditon StringBuffer
     * @param conn Connection
     * @return ArrayList
     */
    public static ArrayList queryAll(String condition, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.*, ")
                .append("b.wszh mx_wszh, b.ndzb mx_ndzb, b.pzzldm mx_pzzldm, ")
                .append(
                "b.szsmdm mx_szsmdm, b.jsjdm mx_jsjdm, b.swjgzzjgdm mx_swjgzzjgdm, ")
                .append(
                "b.zjhm mx_zjhm, b.szdm mx_szdm, b.jsje mx_jsje, b.sl mx_sl, ")
                .append(
                "b.yjhkc mx_yjhkc, b.sjse mx_sjse, b.skssksrq mx_skssksrq, ")
                .append(
                "b.skssjsrq mx_skssjsrq, b.jzbz mx_jzbz, b.yskmdm mx_yskmdm, ")
                .append(
                "b.ysjcdm mx_ysjcdm, b.nd mx_nd, b.lrr mx_lrr, b.cjrq mx_cjrq, ")
                .append("b.qszymj mx_qszymj, b.lrrq mx_lrrq, b.cjr mx_cjr, ")
                .append(
                "b.szsmmc mx_szsmmc, b.szmc mx_szmc, b.yskmmc mx_yskmmc,b.ysjcmc mx_ysjcmc ")
                //.append("c.jkpzh ")
                .append("FROM SBDB.SB_JL_QSWSZZ a, sbdb.sb_jl_qswszmx b ")
                .append("WHERE a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm ")
                //.append("a.sbhzdh = c.sbhzdh(+) ")
                .append(condition)
                .append(" AND rownum <= 201 ") //add by hanlei 20050315，为了控制查询的速度，只取前201条数据
                .append(" ORDER BY a.cjrq desc");

        Debug.out("QswszzDAO queryAll()'s SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                QueryWszBo bo = new QueryWszBo();

                Qswszz wszVo = new Qswszz();
                Qswszmx mxVo = new Qswszmx();
                //Qswszhz zhzVo = new Qswszhz();
                wszVo.setWszh(rs.getString("wszh"));
                wszVo.setNdzb(rs.getString("ndzb"));
                wszVo.setPzzldm(rs.getString("pzzldm"));
                wszVo.setJsjdm(rs.getString("jsjdm"));
                wszVo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                wszVo.setDjzclxdm(rs.getString("djzclxdm"));
                wszVo.setDz(rs.getString("dz"));
                wszVo.setHjsjje(rs.getBigDecimal("hjsjje"));
                wszVo.setClbjdm(rs.getString("clbjdm"));
                wszVo.setSbhzdh(rs.getString("sbhzdh"));
                wszVo.setJbdh(rs.getString("jbdh"));
                wszVo.setZjlxdm(rs.getString("zjlxdm"));
                wszVo.setZjhm(rs.getString("zjhm"));
                wszVo.setFsdm(rs.getString("fsdm"));
                wszVo.setGjbzhydm(rs.getString("gjbzhydm"));
                wszVo.setNd(rs.getString("nd"));
                wszVo.setCjr(rs.getString("cjr"));
                wszVo.setCjrq(rs.getTimestamp("cjrq"));
                wszVo.setLrr(rs.getString("lrr"));
                wszVo.setLrrq(rs.getTimestamp("lrrq"));
                wszVo.setYqts(rs.getBigDecimal("yqts"));
                wszVo.setZnjzje(rs.getBigDecimal("znjzje"));
                wszVo.setFdcwz(rs.getString("fdcwz"));
                wszVo.setHtclrq(rs.getTimestamp("htclrq"));
                wszVo.setJsje(rs.getBigDecimal("jsje"));
                wszVo.setMsjs(rs.getBigDecimal("msjs"));
                wszVo.setSbbh(rs.getString("sbbh"));
                wszVo.setSwjgzzjgdm2(rs.getString("swjgzzjgdm2"));
                wszVo.setNsrjsjdm(rs.getString("nsrjsjdm"));
                wszVo.setDjzclxmc(rs.getString("djzclxmc"));
                wszVo.setZjlxmc(rs.getString("zjlxmc"));
                wszVo.setFsmc(rs.getString("fsmc"));
                wszVo.setGjbzhymc(rs.getString("gjbzhymc"));
                wszVo.setJsfsdm(rs.getString("jsfsdm"));
                wszVo.setJsfsmc(rs.getString("jsfsmc"));
                wszVo.setNsrmc(rs.getString("nsrmc"));
                wszVo.setZsddm(rs.getString("zsddm"));
                wszVo.setZsdmc(rs.getString("zsdmc"));

                mxVo.setWszh(rs.getString("mx_wszh"));
                mxVo.setNdzb(rs.getString("mx_ndzb"));
                mxVo.setPzzldm(rs.getString("mx_pzzldm"));
                mxVo.setSzsmdm(rs.getString("mx_szsmdm"));
                mxVo.setJsjdm(rs.getString("mx_jsjdm"));
                mxVo.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                mxVo.setZjhm(rs.getString("zjhm"));
                mxVo.setSzdm(rs.getString("mx_szdm"));
                mxVo.setJsje(rs.getBigDecimal("mx_jsje"));
                mxVo.setSl(rs.getBigDecimal("mx_sl"));
                mxVo.setYjhkc(rs.getBigDecimal("mx_yjhkc"));
                mxVo.setSjse(rs.getBigDecimal("mx_sjse"));
                mxVo.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                mxVo.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                mxVo.setJzbz(rs.getString("mx_jzbz"));
                mxVo.setYskmdm(rs.getString("mx_yskmdm"));
                mxVo.setYsjcdm(rs.getString("mx_ysjcdm"));
                mxVo.setNd(rs.getString("mx_nd"));
                mxVo.setLrr(rs.getString("mx_lrr"));
                mxVo.setCjrq(rs.getTimestamp("mx_cjrq"));
                mxVo.setQszymj(rs.getBigDecimal("mx_qszymj"));
                mxVo.setLrrq(rs.getTimestamp("mx_lrrq"));
                mxVo.setCjr(rs.getString("mx_cjr"));
                mxVo.setSzsmmc(rs.getString("mx_szsmmc"));
                mxVo.setSzmc(rs.getString("mx_szmc"));
                mxVo.setYskmmc(rs.getString("mx_yskmmc"));
                mxVo.setYsjcmc(rs.getString("mx_ysjcmc"));

                //zhzVo.setJkpzh(rs.getString("jkpzh") );
                //bo.setJkpzh(rs.getString("jkpzh"));
                bo.setQswszzVo(wszVo);
                bo.setMxVo(mxVo);
                //bo.setZhzVo(zhzVo);

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
     * 根据主键获取契税完税证主数据值对象
     * @param qswszz 契税完税证主数据值对象
     * @param conn 数据库连接对象
     * @return契税完税证主数据值对象
     * @throws SQLException
     */
    public static Object get(Qswszz qswszz, Connection conn) throws
            SQLException {
        Qswszz Qswszz1 = null;
        String sql = "select WSZH,NDZB,PZZLDM,JSJDM,SWJGZZJGDM,DJZCLXDM,DZ,HJSJJE,CLBJDM,SBHZDH,JBDH,ZJLXDM,ZJHM,FSDM,GJBZHYDM,ND,CJR,CJRQ,LRR,LRRQ,YQTS,ZNJZJE,FDCWZ,HTCLRQ,JSJE,MSJS,SBBH,SWJGZZJGDM2,NSRJSJDM,DJZCLXMC,ZJLXMC,FSMC,GJBZHYMC,JSFSDM,JSFSMC,NSRMC, ZSDDM,ZSDMC from SBDB.SB_JL_QSWSZZ   where ndzb = ?  and pzzldm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qswszz.ndzb);
            ps.setString(2, qswszz.pzzldm);
            ps.setString(3, qswszz.wszh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qswszz1 = new Qswszz();
                Qswszz1.setWszh(rs.getString("WSZH"));
                Qswszz1.setNdzb(rs.getString("NDZB"));
                Qswszz1.setPzzldm(rs.getString("PZZLDM"));
                Qswszz1.setJsjdm(rs.getString("JSJDM"));
                Qswszz1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszz1.setDjzclxdm(rs.getString("DJZCLXDM"));
                Qswszz1.setNsrjsjdm(rs.getString("NSRJSJDM"));
                Qswszz1.setDz(rs.getString("DZ"));
                Qswszz1.setHjsjje(rs.getBigDecimal("HJSJJE"));
                Qswszz1.setClbjdm(rs.getString("CLBJDM"));
                Qswszz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszz1.setJbdh(rs.getString("JBDH"));
                Qswszz1.setZjlxdm(rs.getString("ZJLXDM"));
                Qswszz1.setZjhm(rs.getString("ZJHM"));
                Qswszz1.setFsdm(rs.getString("FSDM"));
                Qswszz1.setGjbzhydm(rs.getString("GJBZHYDM"));
                Qswszz1.setNd(rs.getString("ND"));
                Qswszz1.setCjr(rs.getString("CJR"));
                Qswszz1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszz1.setLrr(rs.getString("LRR"));
                Qswszz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszz1.setYqts(rs.getBigDecimal("YQTS"));
                Qswszz1.setZnjzje(rs.getBigDecimal("ZNJZJE"));
                Qswszz1.setFdcwz(rs.getString("FDCWZ"));
                Qswszz1.setHtclrq(rs.getTimestamp("HTCLRQ"));
                Qswszz1.setJsje(rs.getBigDecimal("JSJE"));
                Qswszz1.setMsjs(rs.getBigDecimal("MSJS"));
                Qswszz1.setSbbh(rs.getString("SBBH"));
                Qswszz1.setSwjgzzjgdm2(rs.getString("SWJGZZJGDM2"));
                Qswszz1.setNsrjsjdm(rs.getString("NSRJSJDM"));
                Qswszz1.setDjzclxmc(rs.getString("DJZCLXMC"));
                Qswszz1.setZjlxmc(rs.getString("ZJLXMC"));
                Qswszz1.setFsmc(rs.getString("FSMC"));
                Qswszz1.setGjbzhymc(rs.getString("GJBZHYMC"));
                Qswszz1.setJsfsdm(rs.getString("JSFSDM"));
                Qswszz1.setJsfsmc(rs.getString("JSFSMC"));
                Qswszz1.setNsrmc(rs.getString("NSRMC"));
                Qswszz1.setZsddm(rs.getString("ZSDDM"));
                Qswszz1.setZsdmc(rs.getString("ZSDMC"));

            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qswszz1;
    }

    /**
     * 用于撤销完税证时得到具体的完税证主表和明细表信息
     * @param condition StringBuffer
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static Object get(StringBuffer condition, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        Qswszz wszVo = new Qswszz();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.*, ")
                .append("b.wszh mx_wszh, b.ndzb mx_ndzb, b.pzzldm mx_pzzldm, ")
                .append(
                "b.szsmdm mx_szsmdm, b.jsjdm mx_jsjdm, b.swjgzzjgdm mx_swjgzzjgdm, ")
                .append(
                "b.zjhm mx_zjhm, b.szdm mx_szdm, b.jsje mx_jsje, b.sl mx_sl, ")
                .append(
                "b.yjhkc mx_yjhkc, b.sjse mx_sjse, b.skssksrq mx_skssksrq, ")
                .append(
                "b.skssjsrq mx_skssjsrq, b.jzbz mx_jzbz, b.yskmdm mx_yskmdm, ")
                .append(
                "b.ysjcdm mx_ysjcdm, b.nd mx_nd, b.lrr mx_lrr, b.cjrq mx_cjrq, ")
                .append("b.qszymj mx_qszymj, b.lrrq mx_lrrq, b.cjr mx_cjr, ")
                .append(
                "b.szsmmc mx_szsmmc, b.szmc mx_szmc, b.yskmmc mx_yskmmc,b.ysjcmc mx_ysjcmc ")
                .append("FROM SBDB.SB_JL_QSWSZZ a, sbdb.sb_jl_qswszmx b ")
                .append("WHERE a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm ")
                .append(condition)
                .append(" ORDER BY a.ndzb, a.wszh");

        Debug.out("QswszzDAO get say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Qswszmx mxVo = new Qswszmx();

                wszVo.setWszh(rs.getString("wszh"));
                wszVo.setNdzb(rs.getString("ndzb"));
                wszVo.setPzzldm(rs.getString("pzzldm"));
                wszVo.setJsjdm(rs.getString("jsjdm"));
                wszVo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                wszVo.setDjzclxdm(rs.getString("djzclxdm"));
                wszVo.setDz(rs.getString("dz"));
                wszVo.setHjsjje(rs.getBigDecimal("hjsjje"));
                wszVo.setClbjdm(rs.getString("clbjdm"));
                wszVo.setSbhzdh(rs.getString("sbhzdh"));
                wszVo.setJbdh(rs.getString("jbdh"));
                wszVo.setZjlxdm(rs.getString("zjlxdm"));
                wszVo.setZjhm(rs.getString("zjhm"));
                wszVo.setFsdm(rs.getString("fsdm"));
                wszVo.setGjbzhydm(rs.getString("gjbzhydm"));
                wszVo.setNd(rs.getString("nd"));
                wszVo.setCjr(rs.getString("cjr"));
                wszVo.setCjrq(rs.getTimestamp("cjrq"));
                wszVo.setLrr(rs.getString("lrr"));
                wszVo.setLrrq(rs.getTimestamp("lrrq"));
                wszVo.setYqts(rs.getBigDecimal("yqts"));
                wszVo.setZnjzje(rs.getBigDecimal("znjzje"));
                wszVo.setFdcwz(rs.getString("fdcwz"));
                wszVo.setHtclrq(rs.getTimestamp("htclrq"));
                wszVo.setJsje(rs.getBigDecimal("jsje"));
                wszVo.setMsjs(rs.getBigDecimal("msjs"));
                wszVo.setSbbh(rs.getString("sbbh"));
                wszVo.setSwjgzzjgdm2(rs.getString("swjgzzjgdm2"));
                wszVo.setNsrjsjdm(rs.getString("nsrjsjdm"));
                wszVo.setDjzclxmc(rs.getString("djzclxmc"));
                wszVo.setZjlxmc(rs.getString("zjlxmc"));
                wszVo.setFsmc(rs.getString("fsmc"));
                wszVo.setGjbzhymc(rs.getString("gjbzhymc"));
                wszVo.setJsfsdm(rs.getString("jsfsdm"));
                wszVo.setJsfsmc(rs.getString("jsfsmc"));
                wszVo.setNsrmc(rs.getString("nsrmc"));
                wszVo.setZsddm(rs.getString("zsddm"));
                wszVo.setZsdmc(rs.getString("zsdmc"));

                mxVo.setWszh(rs.getString("mx_wszh"));
                mxVo.setNdzb(rs.getString("mx_ndzb"));
                mxVo.setPzzldm(rs.getString("mx_pzzldm"));
                mxVo.setSzsmdm(rs.getString("mx_szsmdm"));
                mxVo.setJsjdm(rs.getString("mx_jsjdm"));
                mxVo.setSwjgzzjgdm(rs.getString("mx_swjgzzjgdm"));
                mxVo.setZjhm(rs.getString("zjhm"));
                mxVo.setSzdm(rs.getString("mx_szdm"));
                mxVo.setJsje(rs.getBigDecimal("mx_jsje"));
                mxVo.setSl(rs.getBigDecimal("mx_sl"));
                mxVo.setYjhkc(rs.getBigDecimal("mx_yjhkc"));
                mxVo.setSjse(rs.getBigDecimal("mx_sjse"));
                mxVo.setSkssksrq(rs.getTimestamp("mx_skssksrq"));
                mxVo.setSkssjsrq(rs.getTimestamp("mx_skssjsrq"));
                mxVo.setJzbz(rs.getString("mx_jzbz"));
                mxVo.setYskmdm(rs.getString("mx_yskmdm"));
                mxVo.setYsjcdm(rs.getString("mx_ysjcdm"));
                mxVo.setNd(rs.getString("mx_nd"));
                mxVo.setLrr(rs.getString("mx_lrr"));
                mxVo.setCjrq(rs.getTimestamp("mx_cjrq"));
                mxVo.setQszymj(rs.getBigDecimal("mx_qszymj"));
                mxVo.setLrrq(rs.getTimestamp("mx_lrrq"));
                mxVo.setCjr(rs.getString("mx_cjr"));
                mxVo.setSzsmmc(rs.getString("mx_szsmmc"));
                mxVo.setSzmc(rs.getString("mx_szmc"));
                mxVo.setYskmmc(rs.getString("mx_yskmmc"));
                mxVo.setYsjcmc(rs.getString("mx_ysjcmc"));

                list.add(mxVo);
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

        wszVo.setMxList(list);
        return wszVo;
    }

    /**
     * 按个人方式汇总完税证
     * @param condition String
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList HzWszGr(String condition, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.lrr, b.szsmdm, a.jsfsdm, ")
                .append("SUM(b.jsje) jsje_total, SUM(b.sjse) sjse_total, ")
                .append("SUM(b.yjhkc) yjhkc_total, COUNT(a.wszh) kssl ")
                .append("FROM SBDB.SB_JL_QSWSZZ a, sbdb.sb_jl_qswszmx b ")
                .append(
                "WHERE substr(a.sbbh,0,1) != 'P' AND a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm AND ")
//           .append("a.gjbzhydm = '").append(Constants.WSZ_GJBZHYDM).append("' AND ")
                .append("(a.sbhzdh is null OR a.sbhzdh = '') AND ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS).append(
                "' ")
                //.append("a.jbdh is not null")
                .append(condition)
                //.append("GROUP BY a.lrr, b.szsmdm ")
                .append(" GROUP BY a.lrr, b.szsmdm, a.jsfsdm ")
                .append(" ORDER BY b.szsmdm asc");

        Debug.out("QswszzDAO HzWszgr say SQL : " + sql.toString());

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

                resultList.add(mxVo);

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
     * 按非个人方式汇总完税证－－税务机关
     * @param condition String
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList HzWszSwjg(String condition, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.jsjdm, b.szsmdm, a.jsfsdm, ")
                .append("SUM(b.jsje) jsje_total, SUM(b.sjse) sjse_total, ")
                .append("SUM(b.yjhkc) yjhkc_total, COUNT(a.wszh) kssl ")
                .append("FROM SBDB.SB_JL_QSWSZZ a, sbdb.sb_jl_qswszmx b ")
                .append("WHERE a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm AND")
                .append("(a.sbhzdh is null OR a.sbhzdh = '') AND ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS).append(
                "' ")
                //.append("a.jbdh is not null")
                .append(condition)
                .append(" GROUP BY a.jsjdm, b.szsmdm, a.jsfsdm ")
                .append(" ORDER BY b.szsmdm asc");

        Debug.out("QswszzDAO HzWszSwjg say SQL : " + sql.toString());

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

                resultList.add(mxVo);

                /*mxVo.setCjrq();
                                 mxVo.setJkpzh();
                                 mxVo.setJsjdm();
                                 mxVo.setJsje(rs.getBigDecimal("jsje_total") );
                                 mxVo.setKssl();
                                 mxVo.setLrrq();
                                 mxVo.setNd();
                                 mxVo.setQjfc();
                                 mxVo.setQxdm();
                                 mxVo.setRkje();
                                 mxVo.setSbbh(rs.getString("sbbh") );
                                 mxVo.setSjfc();
                                 mxVo.setSjse(rs.getBigDecimal("sjse_total") );
                                 mxVo.setSkssjsrq();
                                 mxVo.setSkssksrq();
                                 mxVo.setSl();
                                 mxVo.setSwjgzzjgdm();
                                 mxVo.setSzsmdm(rs.getString("szsmdm") );
                                 mxVo.setYsjcdm();
                                 mxVo.setYskmdm();*/

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            rs.close();
            ps.close();
        }
        return resultList;
    }

    /**
     * 按非个人方式汇总完税证－－征收点
     * @param condition String
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList HzWszZsd(String condition, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT b.szsmdm, a.jsfsdm, ")
                .append("SUM(b.jsje) jsje_total, SUM(b.sjse) sjse_total, ")
                .append("SUM(b.yjhkc) yjhkc_total, COUNT(a.wszh) kssl ")
                .append("FROM SBDB.SB_JL_QSWSZZ a, sbdb.sb_jl_qswszmx b ")
                .append("WHERE a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm AND")
                .append("(a.sbhzdh is null OR a.sbhzdh = '') AND ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS).append(
                "' ")
                //.append("a.jbdh is not null")
                .append(condition)
                .append(" GROUP BY b.szsmdm, a.jsfsdm ")
                .append(" ORDER BY b.szsmdm asc");

        Debug.out("QswszzDAO HzWszZsd say SQL : " + sql.toString());

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

                resultList.add(mxVo);

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            rs.close();
            ps.close();
        }
        return resultList;
    }


    /**
     * 批次导入的汇总
     * @param drpch String    导入批次号，也就是把所有导入批次号相同的申报表生成的完税证进行汇总
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList HzWszPl(WszBo bo, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT a.lrr, b.szsmdm, a.jsfsdm, ")
                .append("SUM(b.jsje) jsje_total, SUM(b.sjse) sjse_total, ")
                .append("SUM(b.yjhkc) yjhkc_total, COUNT(a.wszh) kssl ")
                .append("FROM SBDB.SB_JL_QSWSZZ a, sbdb.sb_jl_qswszmx b ")
                .append("WHERE substr(a.sbbh,0,1) = 'P' AND ")
                .append(
                "a.sbbh in (SELECT sbbh FROM QSDB.QS_JL_SBZB WHERE drpch = '")
                .append(bo.getDrpch()).append("') AND a.JSFSDM = '").append(bo.
                getJsfs()).append("' AND ")
                .append("a.wszh = b.wszh AND ")
                .append("a.ndzb = b.ndzb AND ")
                .append("a.pzzldm = b.pzzldm AND ")
                .append("(a.sbhzdh is null OR a.sbhzdh = '') AND ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS).append(
                "' ")
                .append(" GROUP BY a.lrr, b.szsmdm, a.jsfsdm ")
                .append(" ORDER BY b.szsmdm asc");

        Debug.out("QswszzDAO HzWszpl say SQL : " + sql.toString());

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

                resultList.add(mxVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            rs.close();
            ps.close();
        }

        return resultList;
    }


    /**
     * 更新一条契税完税证主数据记录，仅供汇总完税证后返填处理标记代码、申报汇总单号使用
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

    /**
     * 通过缴款书中的缴款凭证号，查询该缴款书中汇总的完税证
     * @param sbjkzb Sbjkzb
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static ArrayList query(Sbjkzb sbjkzb, Connection conn) throws
            SQLException {
        ArrayList wszList = new ArrayList();

        StringBuffer sql = new StringBuffer("select distinct t.* ");
        sql.append("FROM sbdb.sb_jl_qswszz t, sbdb.sb_jl_qswszmx s, ")
                .append(
                "(select b.szsmdm from sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b ")
                .append("where a.jkpzh = b.jkpzh and a.jsjdm = b.jsjdm and ")
                .append("a.jkpzh = '").append(sbjkzb.getJkpzh()).append(
                "') t1, ")
                .append(
                "(select distinct a.wszh from sbdb.sb_jl_qswszz a, sbdb.sb_jl_qswszhz b ")
                .append("where a.jsjdm = b.jsjdm and a.sbhzdh = b.sbhzdh and ")
                .append("b.jkpzh = '").append(sbjkzb.getJkpzh()).append(
                "') t2 ")
                .append("where t.wszh = s.wszh and t.ndzb = s.ndzb and ")
                .append("t.pzzldm = s.pzzldm and t.clbjdm = '").append(
                Constants.WSZ_CLBJDM_YJZ)
                .append("' and t.wszh = t2.wszh and s.szsmdm = t1.szsmdm ");

        Debug.out("QswszzDAO query(jks to wsz)'s SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Qswszz wszVo = new Qswszz();

                wszVo.setWszh(rs.getString("WSZH"));
                wszVo.setNdzb(rs.getString("NDZB"));
                wszVo.setPzzldm(rs.getString("PZZLDM"));
                wszVo.setJsjdm(rs.getString("JSJDM"));
                wszVo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                wszVo.setDjzclxdm(rs.getString("DJZCLXDM"));
                wszVo.setNsrjsjdm(rs.getString("NSRJSJDM"));
                wszVo.setDz(rs.getString("DZ"));
                wszVo.setHjsjje(rs.getBigDecimal("HJSJJE"));
                wszVo.setClbjdm(rs.getString("CLBJDM"));
                wszVo.setSbhzdh(rs.getString("SBHZDH"));
                wszVo.setJbdh(rs.getString("JBDH"));
                wszVo.setZjlxdm(rs.getString("ZJLXDM"));
                wszVo.setZjhm(rs.getString("ZJHM"));
                wszVo.setFsdm(rs.getString("FSDM"));
                wszVo.setGjbzhydm(rs.getString("GJBZHYDM"));
                wszVo.setNd(rs.getString("ND"));
                wszVo.setCjr(rs.getString("CJR"));
                wszVo.setCjrq(rs.getTimestamp("CJRQ"));
                wszVo.setLrr(rs.getString("LRR"));
                wszVo.setLrrq(rs.getTimestamp("LRRQ"));
                wszVo.setYqts(rs.getBigDecimal("YQTS"));
                wszVo.setZnjzje(rs.getBigDecimal("ZNJZJE"));
                wszVo.setFdcwz(rs.getString("FDCWZ"));
                wszVo.setHtclrq(rs.getTimestamp("HTCLRQ"));
                wszVo.setJsje(rs.getBigDecimal("JSJE"));
                wszVo.setMsjs(rs.getBigDecimal("MSJS"));
                wszVo.setSbbh(rs.getString("SBBH"));
                wszVo.setSwjgzzjgdm2(rs.getString("SWJGZZJGDM2"));
                wszVo.setNsrjsjdm(rs.getString("NSRJSJDM"));
                wszVo.setDjzclxmc(rs.getString("DJZCLXMC"));
                wszVo.setZjlxmc(rs.getString("ZJLXMC"));
                wszVo.setFsmc(rs.getString("FSMC"));
                wszVo.setGjbzhymc(rs.getString("GJBZHYMC"));
                wszVo.setJsfsdm(rs.getString("JSFSDM"));
                wszVo.setJsfsmc(rs.getString("JSFSMC"));
                wszVo.setNsrmc(rs.getString("NSRMC"));
                wszVo.setZsddm(rs.getString("ZSDDM"));
                wszVo.setZsdmc(rs.getString("ZSDMC"));

                wszList.add(wszVo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return wszList;
    }

    /**
     * 撤销完税证时查询分票的完税证
     * @param qswszz Qswszz
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList query(Qswszz qswszz, Connection conn) throws
            SQLException {
        ArrayList wszList = new ArrayList();

        StringBuffer sql = new StringBuffer(
                "select t.* from sbdb.sb_jl_qswszz t, ");
        sql.append("(select s.sbbh from sbdb.sb_jl_qswszz s where s.wszh = '")
                .append(qswszz.getWszh()).append("' and s.ndzb= '").append(
                qswszz.getNdzb())
                .append("' and s.pzzldm = '" + qswszz.getPzzldm() +
                        "' ) t1 where t.sbbh = t1.sbbh ");

        Debug.out("QswszzDAO query(qswszz,conn)'s SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Qswszz wszVo = new Qswszz();

                wszVo.setWszh(rs.getString("WSZH"));
                wszVo.setNdzb(rs.getString("NDZB"));
                wszVo.setPzzldm(rs.getString("PZZLDM"));
                wszVo.setJsjdm(rs.getString("JSJDM"));
                wszVo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                wszVo.setDjzclxdm(rs.getString("DJZCLXDM"));
                wszVo.setNsrjsjdm(rs.getString("NSRJSJDM"));
                wszVo.setDz(rs.getString("DZ"));
                wszVo.setHjsjje(rs.getBigDecimal("HJSJJE"));
                wszVo.setClbjdm(rs.getString("CLBJDM"));
                wszVo.setSbhzdh(rs.getString("SBHZDH"));
                wszVo.setJbdh(rs.getString("JBDH"));
                wszVo.setZjlxdm(rs.getString("ZJLXDM"));
                wszVo.setZjhm(rs.getString("ZJHM"));
                wszVo.setFsdm(rs.getString("FSDM"));
                wszVo.setGjbzhydm(rs.getString("GJBZHYDM"));
                wszVo.setNd(rs.getString("ND"));
                wszVo.setCjr(rs.getString("CJR"));
                wszVo.setCjrq(rs.getTimestamp("CJRQ"));
                wszVo.setLrr(rs.getString("LRR"));
                wszVo.setLrrq(rs.getTimestamp("LRRQ"));
                wszVo.setYqts(rs.getBigDecimal("YQTS"));
                wszVo.setZnjzje(rs.getBigDecimal("ZNJZJE"));
                wszVo.setFdcwz(rs.getString("FDCWZ"));
                wszVo.setHtclrq(rs.getTimestamp("HTCLRQ"));
                wszVo.setJsje(rs.getBigDecimal("JSJE"));
                wszVo.setMsjs(rs.getBigDecimal("MSJS"));
                wszVo.setSbbh(rs.getString("SBBH"));
                wszVo.setSwjgzzjgdm2(rs.getString("SWJGZZJGDM2"));
                wszVo.setNsrjsjdm(rs.getString("NSRJSJDM"));
                wszVo.setDjzclxmc(rs.getString("DJZCLXMC"));
                wszVo.setZjlxmc(rs.getString("ZJLXMC"));
                wszVo.setFsmc(rs.getString("FSMC"));
                wszVo.setGjbzhymc(rs.getString("GJBZHYMC"));
                wszVo.setJsfsdm(rs.getString("JSFSDM"));
                wszVo.setJsfsmc(rs.getString("JSFSMC"));
                wszVo.setNsrmc(rs.getString("NSRMC"));
                wszVo.setZsddm(rs.getString("ZSDDM"));
                wszVo.setZsdmc(rs.getString("ZSDMC"));

                wszList.add(wszVo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return wszList;
    }

}
