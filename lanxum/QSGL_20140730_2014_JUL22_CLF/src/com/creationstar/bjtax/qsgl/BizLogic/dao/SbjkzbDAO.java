package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;


/**
 * 申报缴款主表数据DAO
 */
public class SbjkzbDAO extends BaseDAO {

    /**
     * 插入一条申报缴款主表数据记录
     * @param sbjkzb 申报缴款主表数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Sbjkzb sbjkzb, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_SBJKZB (JKPZH,SKLXDM,JSJDM,FSDM,LSGXDM,YHDM,YHMC,ZH,DJZCLXDM,SWJGZZJGDM,ZSSWJGZZJGDM,GJBZHYDM,GKZZJGDM,YSKMDM,YSJCDM,SZDM,LRRQ,SBRQ,JKSJ,XJRQ,CLBJDM,SJJE,ZYRQ,RKJE,ZWBS,HXRDM,HXRMC,LRR,BZ,HXRQ,SBBH,JYDZLXDM,SKSSKSRQ,SKSSJSRQ,SJLY,ND,CJRQ,QXDM,SPHM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //缴款凭证号
            ps.setString(1, sbjkzb.jkpzh);
            //税款类型代码
            ps.setString(2, sbjkzb.sklxdm);
            //计算机代码
            ps.setString(3, sbjkzb.jsjdm);
            //登记申报方式代码
            ps.setString(4, sbjkzb.fsdm);
            //隶属关系代码
            ps.setString(5, sbjkzb.lsgxdm);
            //银行代码
            ps.setString(6, sbjkzb.yhdm);
            //银行名称
            ps.setString(7, sbjkzb.yhmc);
            //帐号
            ps.setString(8, sbjkzb.zh);
            //登记注册类型代码
            ps.setString(9, sbjkzb.djzclxdm);
            //税务机关组织机构代码
            ps.setString(10, sbjkzb.swjgzzjgdm);
            //征收机关代码
            ps.setString(11, sbjkzb.zsswjgzzjgdm);
            //国家标准行业代码
            ps.setString(12, sbjkzb.gjbzhydm);
            //国库组织机构代码
            ps.setString(13, sbjkzb.gkzzjgdm);
            //预算科目代码
            ps.setString(14, sbjkzb.yskmdm);
            //预算级次
            ps.setString(15, sbjkzb.ysjcdm);
            //税种代码
            ps.setString(16, sbjkzb.szdm);
            //录入时间
            ps.setTimestamp(17, sbjkzb.lrrq);
            //申报日期
            ps.setTimestamp(18, sbjkzb.sbrq);
            //银行收款时间
            ps.setTimestamp(19, sbjkzb.jksj);
            //限缴日期
            ps.setTimestamp(20, sbjkzb.xjrq);
            //处理标记代码
            ps.setString(21, sbjkzb.clbjdm);
            //实缴金额
            ps.setBigDecimal(22, sbjkzb.sjje);
            //帐页日期
            ps.setTimestamp(23, sbjkzb.zyrq);
            //入库金额
            ps.setBigDecimal(24, sbjkzb.rkje);
            //帐务标识
            ps.setString(25, sbjkzb.zwbs);
            //核销人代码
            ps.setString(26, sbjkzb.hxrdm);
            //核销人名称
            ps.setString(27, sbjkzb.hxrmc);
            //录入人代码
            ps.setString(28, sbjkzb.lrr);
            //备注
            ps.setString(29, sbjkzb.bz);
            //核销日期
            ps.setTimestamp(30, sbjkzb.hxrq);
            //申报编号
            ps.setString(31, sbjkzb.sbbh);
            //经营地址联系电话
            ps.setString(32, sbjkzb.jydzlxdm);
            //税款所属开始日期
            ps.setTimestamp(33, sbjkzb.skssksrq);
            //税款所属结束日期
            ps.setTimestamp(34, sbjkzb.skssjsrq);
            //数据来源
            ps.setString(35, sbjkzb.sjly);
            //年度
            ps.setString(36, sbjkzb.nd);
            //创建时间
            ps.setTimestamp(37, sbjkzb.cjrq);
            //区县代码
            ps.setString(38, sbjkzb.qxdm);
            //税票号码
            ps.setString(39, sbjkzb.jkpzh);

            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条申报缴款主表数据记录
     * @param sbjkzb 申报缴款主表数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Sbjkzb sbjkzb, Connection conn) throws
            SQLException {
        String sql = "update SBDB.SB_JL_SBJKZB set JKPZH=?,SKLXDM=?,JSJDM=?,FSDM=?,LSGXDM=?,YHDM=?,YHMC=?,ZH=?,DJZCLXDM=?,SWJGZZJGDM=?,ZSSWJGZZJGDM=?,GJBZHYDM=?,GKZZJGDM=?,YSKMDM=?,YSJCDM=?,SZDM=?,LRRQ=?,SBRQ=?,JKSJ=?,XJRQ=?,CLBJDM=?,SJJE=?,ZYRQ=?,RKJE=?,ZWBS=?,HXRDM=?,HXRMC=?,LRR=?,BZ=?,HXRQ=?,SBBH=?,JYDZLXDM=?,SKSSKSRQ=?,SKSSJSRQ=?,SJLY=?,ND=?,CJRQ=?,QXDM=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbjkzb.jkpzh);
            ps.setString(2, sbjkzb.sklxdm);
            ps.setString(3, sbjkzb.jsjdm);
            ps.setString(4, sbjkzb.fsdm);
            ps.setString(5, sbjkzb.lsgxdm);
            ps.setString(6, sbjkzb.yhdm);
            ps.setString(7, sbjkzb.yhmc);
            ps.setString(8, sbjkzb.zh);
            ps.setString(9, sbjkzb.djzclxdm);
            ps.setString(10, sbjkzb.swjgzzjgdm);
            ps.setString(11, sbjkzb.zsswjgzzjgdm);
            ps.setString(12, sbjkzb.gjbzhydm);
            ps.setString(13, sbjkzb.gkzzjgdm);
            ps.setString(14, sbjkzb.yskmdm);
            ps.setString(15, sbjkzb.ysjcdm);
            ps.setString(16, sbjkzb.szdm);
            ps.setTimestamp(17, sbjkzb.lrrq);
            ps.setTimestamp(18, sbjkzb.sbrq);
            ps.setTimestamp(19, sbjkzb.jksj);
            ps.setTimestamp(20, sbjkzb.xjrq);
            ps.setString(21, sbjkzb.clbjdm);
            ps.setBigDecimal(22, sbjkzb.sjje);
            ps.setTimestamp(23, sbjkzb.zyrq);
            ps.setBigDecimal(24, sbjkzb.rkje);
            ps.setString(25, sbjkzb.zwbs);
            ps.setString(26, sbjkzb.hxrdm);
            ps.setString(27, sbjkzb.hxrmc);
            ps.setString(28, sbjkzb.lrr);
            ps.setString(29, sbjkzb.bz);
            ps.setTimestamp(30, sbjkzb.hxrq);
            ps.setString(31, sbjkzb.sbbh);
            ps.setString(32, sbjkzb.jydzlxdm);
            ps.setTimestamp(33, sbjkzb.skssksrq);
            ps.setTimestamp(34, sbjkzb.skssjsrq);
            ps.setString(35, sbjkzb.sjly);
            ps.setString(36, sbjkzb.nd);
            ps.setTimestamp(37, sbjkzb.cjrq);
            ps.setString(38, sbjkzb.qxdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 打印缴款书后update打印状态
     * @param condition StringBuffer
     * @param conn Connection
     */
    public void update(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        sql.append("UPDATE SBDB.SB_JL_SBJKZB SET clbjdm = '")
                .append(Constants.WSZ_CLBJDM_YWS).append("' WHERE ").append(
                condition);

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
     * 删除多条申报缴款主表数据记录
     * @param sbjkzbList 申报缴款主表数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList sbjkzbList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_SBJKZB  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbjkzbList.size(); i++) {
                Sbjkzb sbjkzb = (Sbjkzb) sbjkzbList.get(i);
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
                "delete from  SBDB.SB_JL_SBJKZB  where ");
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
     * 根据主键获取申报缴款主表数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 申报缴款主表数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbjkzbList = new ArrayList();
        String sql = "select JKPZH,SKLXDM,JSJDM,FSDM,LSGXDM,YHDM,YHMC,ZH,DJZCLXDM,SWJGZZJGDM,ZSSWJGZZJGDM,GJBZHYDM,GKZZJGDM,YSKMDM,YSJCDM,SZDM,LRRQ,SBRQ,JKSJ,XJRQ,CLBJDM,SJJE,ZYRQ,RKJE,ZWBS,HXRDM,HXRMC,LRR,BZ,HXRQ,SBBH,JYDZLXDM,SKSSKSRQ,SKSSJSRQ,SJLY,ND,CJRQ,QXDM from SBDB.SB_JL_SBJKZB " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbjkzb Sbjkzb1 = new Sbjkzb();
                Sbjkzb1.setJkpzh(rs.getString("JKPZH"));
                Sbjkzb1.setSklxdm(rs.getString("SKLXDM"));
                Sbjkzb1.setJsjdm(rs.getString("JSJDM"));
                Sbjkzb1.setFsdm(rs.getString("FSDM"));
                Sbjkzb1.setLsgxdm(rs.getString("LSGXDM"));
                Sbjkzb1.setYhdm(rs.getString("YHDM"));
                Sbjkzb1.setYhmc(rs.getString("YHMC"));
                Sbjkzb1.setZh(rs.getString("ZH"));
                Sbjkzb1.setDjzclxdm(rs.getString("DJZCLXDM"));
                Sbjkzb1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbjkzb1.setZsswjgzzjgdm(rs.getString("ZSSWJGZZJGDM"));
                Sbjkzb1.setGjbzhydm(rs.getString("GJBZHYDM"));
                Sbjkzb1.setGkzzjgdm(rs.getString("GKZZJGDM"));
                Sbjkzb1.setYskmdm(rs.getString("YSKMDM"));
                Sbjkzb1.setYsjcdm(rs.getString("YSJCDM"));
                Sbjkzb1.setSzdm(rs.getString("SZDM"));
                Sbjkzb1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbjkzb1.setSbrq(rs.getTimestamp("SBRQ"));
                Sbjkzb1.setJksj(rs.getTimestamp("JKSJ"));
                Sbjkzb1.setXjrq(rs.getTimestamp("XJRQ"));
                Sbjkzb1.setClbjdm(rs.getString("CLBJDM"));
                Sbjkzb1.setSjje(rs.getBigDecimal("SJJE"));
                Sbjkzb1.setZyrq(rs.getTimestamp("ZYRQ"));
                Sbjkzb1.setRkje(rs.getBigDecimal("RKJE"));
                Sbjkzb1.setZwbs(rs.getString("ZWBS"));
                Sbjkzb1.setHxrdm(rs.getString("HXRDM"));
                Sbjkzb1.setHxrmc(rs.getString("HXRMC"));
                Sbjkzb1.setLrr(rs.getString("LRR"));
                Sbjkzb1.setBz(rs.getString("BZ"));
                Sbjkzb1.setHxrq(rs.getTimestamp("HXRQ"));
                Sbjkzb1.setSbbh(rs.getString("SBBH"));
                Sbjkzb1.setJydzlxdm(rs.getString("JYDZLXDM"));
                Sbjkzb1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Sbjkzb1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Sbjkzb1.setSjly(rs.getString("SJLY"));
                Sbjkzb1.setNd(rs.getString("ND"));
                Sbjkzb1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbjkzb1.setQxdm(rs.getString("QXDM"));
                SbjkzbList.add(Sbjkzb1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbjkzbList;
    }

    /**
     * 汇总生成的缴款书的查询
     * @param condition StringBuffer
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryAll(StringBuffer condition, Connection conn) throws
            SQLException {
        ArrayList resultList = new ArrayList();

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
                "b.cjrq mx_cjrq, b.lrrq mx_lrrg, b.qxdm mx_qxdm, b.sjse mx_sjse ")
                .append("FROM sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b ")
                .append("WHERE a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition);

        Debug.out("SbjkzbDAO queryAll(huizong) say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                JksBo bo = new JksBo();

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
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrg"));
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
     * 根据主键获取申报缴款主表数据值对象
     * @param sbjkzb 申报缴款主表数据值对象
     * @param conn 数据库连接对象
     * @return申报缴款主表数据值对象
     * @throws SQLException
     */
    public static Object get(Sbjkzb sbjkzb, Connection conn) throws
            SQLException {
        Sbjkzb Sbjkzb1 = null;
        String sql = "select JKPZH,SKLXDM,JSJDM,FSDM,LSGXDM,YHDM,YHMC,ZH,DJZCLXDM,SWJGZZJGDM,ZSSWJGZZJGDM,GJBZHYDM,GKZZJGDM,YSKMDM,YSJCDM,SZDM,LRRQ,SBRQ,JKSJ,XJRQ,CLBJDM,SJJE,ZYRQ,RKJE,ZWBS,HXRDM,HXRMC,LRR,BZ,HXRQ,SBBH,JYDZLXDM,SKSSKSRQ,SKSSJSRQ,SJLY,ND,CJRQ,QXDM from SBDB.SB_JL_SBJKZB   where jkpzh=? AND jsjdm=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbjkzb.getJkpzh());
            ps.setString(2, sbjkzb.getJsjdm());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbjkzb1 = new Sbjkzb();
                Sbjkzb1.setJkpzh(rs.getString("JKPZH"));
                Sbjkzb1.setSklxdm(rs.getString("SKLXDM"));
                Sbjkzb1.setJsjdm(rs.getString("JSJDM"));
                Sbjkzb1.setFsdm(rs.getString("FSDM"));
                Sbjkzb1.setLsgxdm(rs.getString("LSGXDM"));
                Sbjkzb1.setYhdm(rs.getString("YHDM"));
                Sbjkzb1.setYhmc(rs.getString("YHMC"));
                Sbjkzb1.setZh(rs.getString("ZH"));
                Sbjkzb1.setDjzclxdm(rs.getString("DJZCLXDM"));
                Sbjkzb1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbjkzb1.setZsswjgzzjgdm(rs.getString("ZSSWJGZZJGDM"));
                Sbjkzb1.setGjbzhydm(rs.getString("GJBZHYDM"));
                Sbjkzb1.setGkzzjgdm(rs.getString("GKZZJGDM"));
                Sbjkzb1.setYskmdm(rs.getString("YSKMDM"));
                Sbjkzb1.setYsjcdm(rs.getString("YSJCDM"));
                Sbjkzb1.setSzdm(rs.getString("SZDM"));
                Sbjkzb1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbjkzb1.setSbrq(rs.getTimestamp("SBRQ"));
                Sbjkzb1.setJksj(rs.getTimestamp("JKSJ"));
                Sbjkzb1.setXjrq(rs.getTimestamp("XJRQ"));
                Sbjkzb1.setClbjdm(rs.getString("CLBJDM"));
                Sbjkzb1.setSjje(rs.getBigDecimal("SJJE"));
                Sbjkzb1.setZyrq(rs.getTimestamp("ZYRQ"));
                Sbjkzb1.setRkje(rs.getBigDecimal("RKJE"));
                Sbjkzb1.setZwbs(rs.getString("ZWBS"));
                Sbjkzb1.setHxrdm(rs.getString("HXRDM"));
                Sbjkzb1.setHxrmc(rs.getString("HXRMC"));
                Sbjkzb1.setLrr(rs.getString("LRR"));
                Sbjkzb1.setBz(rs.getString("BZ"));
                Sbjkzb1.setHxrq(rs.getTimestamp("HXRQ"));
                Sbjkzb1.setSbbh(rs.getString("SBBH"));
                Sbjkzb1.setJydzlxdm(rs.getString("JYDZLXDM"));
                Sbjkzb1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Sbjkzb1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Sbjkzb1.setSjly(rs.getString("SJLY"));
                Sbjkzb1.setNd(rs.getString("ND"));
                Sbjkzb1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbjkzb1.setQxdm(rs.getString("QXDM"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            ps.close();
        }
        return Sbjkzb1;
    }

    /**
     * 用于得到缴款书主表和明细表的数据
     * @param condition StringBuffer
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static Object get(StringBuffer condition, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        Sbjkzb sbjkzb = new Sbjkzb();

        int kssl = 0; //课税数量，也就是缴款书中汇总的完税证数量

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
                "b.cjrq mx_cjrq, b.lrrq mx_lrrg, b.qxdm mx_qxdm, b.sjse mx_sjse ")
                .append("FROM sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b ")
                .append("WHERE a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                .append(condition);

        Debug.out("用于得到缴款书主表和明细表的数据 的sql : " + sql);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
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
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrg"));
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

                kssl += sbjkmx.getKssl().intValue();

                list.add(sbjkmx);
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

        sbjkzb.setKssl(kssl);
        sbjkzb.setMxList(list);

        return sbjkzb;

    }

    /**
     * 通过已汇总的完税证，查该完税证汇总到了哪个缴款书中
     * @param qswszz Qswszz
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static Object query(Qswszz qswszz, Qswszmx qswszmx, Connection conn) throws
            SQLException {
        //Sbjkzb sbjkzb = new Sbjkzb();

        String jkpzh = "";

        //原来的sql，数据量大了就会超时
        /*StringBuffer sql = new StringBuffer("select distinct t.* ");
                 sql.append("from sbdb.sb_jl_sbjkzb t, sbdb.sb_jl_sbjkmx s, ")
            .append("(select distinct b.jkpzh,b.jsjdm ")
            .append("from sbdb.sb_jl_qswszz a, sbdb.sb_jl_qswszhz b ")
         .append("where a.jsjdm = b.jsjdm and ").append("a.sbhzdh = b.sbhzdh and ")
            .append("a.wszh = '").append(qswszz.getWszh()).append("') t1, ")
            .append("(select distinct b.szsmdm ")
            .append("from sbdb.sb_jl_qswszz a, sbdb.sb_jl_qswszmx b ")
            .append("where a.wszh = b.wszh and a.ndzb = b.ndzb and ")
            .append("a.pzzldm = b.pzzldm and ")
         .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).append("' and ")
            .append("a.wszh = '").append(qswszz.getWszh()).append("') t2 ")
         .append("where t.jkpzh = s.jkpzh and ").append("t.jsjdm = s.jsjdm and ")
            .append("t.jkpzh = t1.jkpzh and t.jsjdm = t1.jsjdm and s.szsmdm = t2.szsmdm ");*/

        //新的sql，去掉了sbjkzb和sbjkmx的关联，去掉了qswszz和qswszmx两个表
        StringBuffer sql = new StringBuffer("select distinct t.jkpzh ");
        sql.append("from sbdb.sb_jl_sbjkmx t, ")
                .append("(select distinct a.jkpzh, a.jsjdm ")
                .append("from sbdb.sb_jl_qswszhz a ")
                .append("WHERE a.sbhzdh = '").append(qswszz.getSbhzdh()).append(
                "') t1 ")
                .append("WHERE t.jkpzh = t1.jkpzh AND ")
                .append("t.jsjdm = t1.jsjdm AND ")
                .append("t.szsmdm = '").append(qswszmx.getSzsmdm()).append("'");

        Debug.out("SbjkzbDAO query(wsz to jks)'s SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                //由于改了sql语句，而且用不到缴款书的详细数据，所以现在改为只返回缴款凭证号
                jkpzh = rs.getString("JKPZH");

                /*sbjkzb.setJkpzh(rs.getString("JKPZH"));
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
                                 sbjkzb.setQxdm(rs.getString("QXDM"));*/
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return jkpzh;
    }

    /**
     * 通过已汇总的完税证，查该完税证汇总到了哪个缴款书中－－用于退税
     * @param qswszz Qswszz
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static Object query(Qswszz qswszz, Connection conn) throws
            SQLException {
        Sbjkzb sbjkzb = new Sbjkzb();

        StringBuffer sql = new StringBuffer("select distinct t.* ");
        sql.append("from sbdb.sb_jl_sbjkzb t, sbdb.sb_jl_sbjkmx s, ")
                .append("(select distinct b.jkpzh,b.jsjdm ")
                .append("from sbdb.sb_jl_qswszz a, sbdb.sb_jl_qswszhz b ")
                .append("where a.jsjdm = b.jsjdm and ").append(
                "a.sbhzdh = b.sbhzdh and ")
                .append("a.wszh = '").append(qswszz.getWszh()).append("') t1, ")
                .append("(select distinct b.szsmdm ")
                .append("from sbdb.sb_jl_qswszz a, sbdb.sb_jl_qswszmx b ")
                .append("where a.wszh = b.wszh and a.ndzb = b.ndzb and ")
                .append("a.pzzldm = b.pzzldm and ")
                .append("a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).append(
                "' and ")
                .append("a.wszh = '").append(qswszz.getWszh()).append("') t2 ")
                .append("where t.jkpzh = s.jkpzh and ").append(
                "t.jsjdm = s.jsjdm and ")
                .append(
                "t.jkpzh = t1.jkpzh and t.jsjdm = t1.jsjdm and s.szsmdm = t2.szsmdm ");

        Debug.out("SbjkzbDAO query(wsz to jks)'s SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {

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
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return sbjkzb;
    }


    /**
     * 直接生成的缴款书的查询
     * @param condition StringBuffer
     * @param sbzb_condition StringBuffer
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static ArrayList queryAll(StringBuffer condition,
                                     StringBuffer sbzb_condition,
                                     Connection conn) throws SQLException {
        ArrayList jksList = new ArrayList();

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
                "b.cjrq mx_cjrq, b.lrrq mx_lrrg, b.qxdm mx_qxdm, b.sjse mx_sjse ")
                .append("FROM sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b, ")
                .append(
                "(select s.sbbh, s.jsjdm from qsdb.qs_jl_sbzb t, qsdb.qs_jl_grxx s ")
                .append("where t.sbbh = s.sbbh ").append(sbzb_condition)
                .append(" UNION ")
                .append(
                "select q.sbbh, q.jsjdm from qsdb.qs_jl_sbzb t, qsdb.qs_jl_fgrxx q ")
                .append("where t.sbbh = q.sbbh ").append(sbzb_condition).append(
                " ) t1 ")
                .append("WHERE a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm AND")
                .append(" a.sbbh = t1.sbbh ")
                .append(condition);

        Debug.out("SbjkzbDAO queryAll(direct) say SQL : " + sql.toString());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                JksBo bo = new JksBo();

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
                sbjkmx.setLrrq(rs.getTimestamp("mx_lrrg"));
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

                jksList.add(bo);
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

        return jksList;
    }

}
