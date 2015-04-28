package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.ttsoft.common.util.Debug;


/**
 * 申报缴款明细数据DAO
 */
public class SbjkmxDAO extends BaseDAO {

    /**
     * 插入一条申报缴款明细数据记录
     * @param sbjkmx 申报缴款明细数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Sbjkmx sbjkmx, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_SBJKMX (SZSMDM,JKPZH,JSJDM,YSKMDM,YSJCDM,KSSL,JSJE,SJSE,SKSSKSRQ,SKSSJSRQ,RKJE,SBBH,SJFC,QJFC,SWJGZZJGDM,ND,SL,CJRQ,LRRQ,QXDM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //税种税目代码
            ps.setString(1, sbjkmx.szsmdm);
            //缴款凭证号
            ps.setString(2, sbjkmx.jkpzh);
            //计算机代码
            ps.setString(3, sbjkmx.jsjdm);
            //预算科目代码
            ps.setString(4, sbjkmx.yskmdm);
            //预算级次
            ps.setString(5, sbjkmx.ysjcdm);
            //课税数量
            ps.setBigDecimal(6, sbjkmx.kssl);
            //计税金额
            ps.setBigDecimal(7, sbjkmx.jsje);
            //实缴税额
            ps.setBigDecimal(8, sbjkmx.sjse);
            //税款所属开始日期
            ps.setTimestamp(9, sbjkmx.skssksrq);
            //税款所属结束日期
            ps.setTimestamp(10, sbjkmx.skssjsrq);
            //入库金额
            ps.setBigDecimal(11, sbjkmx.rkje);
            //申报编号
            ps.setString(12, sbjkmx.sbbh);
            //市级分成
            ps.setBigDecimal(13, sbjkmx.sjfc);
            //区级分成
            ps.setBigDecimal(14, sbjkmx.qjfc);
            //税务机关组织机构代码
            ps.setString(15, sbjkmx.swjgzzjgdm);
            //年度
            ps.setString(16, sbjkmx.nd);
            //税率
            ps.setBigDecimal(17, sbjkmx.sl);
            //创建时间
            ps.setTimestamp(18, sbjkmx.cjrq);
            //录入时间
            ps.setTimestamp(19, sbjkmx.lrrq);
            //区县代码
            ps.setString(20, sbjkmx.qxdm);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条申报缴款明细数据记录
     * @param sbjkmx 申报缴款明细数据值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Sbjkmx sbjkmx, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_SBJKMX set SZSMDM=?,JKPZH=?,JSJDM=?,YSKMDM=?,YSJCDM=?,KSSL=?,JSJE=?,SJSE=?,SKSSKSRQ=?,SKSSJSRQ=?,RKJE=?,SBBH=?,SJFC=?,QJFC=?,SWJGZZJGDM=?,ND=?,SL=?,CJRQ=?,LRRQ=?,QXDM=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbjkmx.szsmdm);
            ps.setString(2, sbjkmx.jkpzh);
            ps.setString(3, sbjkmx.jsjdm);
            ps.setString(4, sbjkmx.yskmdm);
            ps.setString(5, sbjkmx.ysjcdm);
            ps.setBigDecimal(6, sbjkmx.kssl);
            ps.setBigDecimal(7, sbjkmx.jsje);
            ps.setBigDecimal(8, sbjkmx.sjse);
            ps.setTimestamp(9, sbjkmx.skssksrq);
            ps.setTimestamp(10, sbjkmx.skssjsrq);
            ps.setBigDecimal(11, sbjkmx.rkje);
            ps.setString(12, sbjkmx.sbbh);
            ps.setBigDecimal(13, sbjkmx.sjfc);
            ps.setBigDecimal(14, sbjkmx.qjfc);
            ps.setString(15, sbjkmx.swjgzzjgdm);
            ps.setString(16, sbjkmx.nd);
            ps.setBigDecimal(17, sbjkmx.sl);
            ps.setTimestamp(18, sbjkmx.cjrq);
            ps.setTimestamp(19, sbjkmx.lrrq);
            ps.setString(20, sbjkmx.qxdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条申报缴款明细数据记录
     * @param sbjkmxList 申报缴款明细数据值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList sbjkmxList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_SBJKMX  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbjkmxList.size(); i++) {
                Sbjkmx sbjkmx = (Sbjkmx) sbjkmxList.get(i);
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
                "delete from  SBDB.SB_JL_SBJKMX  where ");
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
     * 根据主键获取申报缴款明细数据值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 申报缴款明细数据值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbjkmxList = new ArrayList();
        String sql = "select SZSMDM,JKPZH,JSJDM,YSKMDM,YSJCDM,KSSL,JSJE,SJSE,SKSSKSRQ,SKSSJSRQ,RKJE,SBBH,SJFC,QJFC,SWJGZZJGDM,ND,SL,CJRQ,LRRQ,QXDM from SBDB.SB_JL_SBJKMX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbjkmx Sbjkmx1 = new Sbjkmx();
                Sbjkmx1.setSzsmdm(rs.getString("SZSMDM"));
                Sbjkmx1.setJkpzh(rs.getString("JKPZH"));
                Sbjkmx1.setJsjdm(rs.getString("JSJDM"));
                Sbjkmx1.setYskmdm(rs.getString("YSKMDM"));
                Sbjkmx1.setYsjcdm(rs.getString("YSJCDM"));
                Sbjkmx1.setKssl(rs.getBigDecimal("KSSL"));
                Sbjkmx1.setJsje(rs.getBigDecimal("JSJE"));
                Sbjkmx1.setSjse(rs.getBigDecimal("SJSE"));
                Sbjkmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Sbjkmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Sbjkmx1.setRkje(rs.getBigDecimal("RKJE"));
                Sbjkmx1.setSbbh(rs.getString("SBBH"));
                Sbjkmx1.setSjfc(rs.getBigDecimal("SJFC"));
                Sbjkmx1.setQjfc(rs.getBigDecimal("QJFC"));
                Sbjkmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbjkmx1.setNd(rs.getString("ND"));
                Sbjkmx1.setSl(rs.getBigDecimal("SL"));
                Sbjkmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbjkmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbjkmx1.setQxdm(rs.getString("QXDM"));
                SbjkmxList.add(Sbjkmx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbjkmxList;
    }

    /**
     * 根据主键获取申报缴款明细数据值对象
     * 注意：请不要修改成按照三个主键查询，因为对于直接生成方式生成的缴款书来说，一个条件就已经可以确定一条缴款数据了
     * 因为是一票一税的，只有汇总方式生成的缴款书会有多个税目
     * 再加入jsjdm是为了查询更迅速
     *
     * @param sbjkmx 申报缴款明细数据值对象
     * @param conn 数据库连接对象
     * @return申报缴款明细数据值对象
     * @throws SQLException
     */
    public static Object get(Sbjkmx sbjkmx, Connection conn) throws
            SQLException {
        Sbjkmx Sbjkmx1 = new Sbjkmx();
        String sql = "select SZSMDM,JKPZH,JSJDM,YSKMDM,YSJCDM,KSSL,JSJE,SJSE,SKSSKSRQ,SKSSJSRQ,RKJE,SBBH,SJFC,QJFC,SWJGZZJGDM,ND,SL,CJRQ,LRRQ,QXDM from SBDB.SB_JL_SBJKMX   where JKPZH='" +
                     sbjkmx.jkpzh + "'AND JSJDM='" + sbjkmx.jsjdm + "'";
        Debug.out("sbjkmxDAO get() say: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbjkmx1.setSzsmdm(rs.getString("SZSMDM"));
                Sbjkmx1.setJkpzh(rs.getString("JKPZH"));
                Sbjkmx1.setJsjdm(rs.getString("JSJDM"));
                Sbjkmx1.setYskmdm(rs.getString("YSKMDM"));
                Sbjkmx1.setYsjcdm(rs.getString("YSJCDM"));
                Sbjkmx1.setKssl(rs.getBigDecimal("KSSL"));
                Sbjkmx1.setJsje(rs.getBigDecimal("JSJE"));
                Sbjkmx1.setSjse(rs.getBigDecimal("SJSE"));
                Sbjkmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Sbjkmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Sbjkmx1.setRkje(rs.getBigDecimal("RKJE"));
                Sbjkmx1.setSbbh(rs.getString("SBBH"));
                Sbjkmx1.setSjfc(rs.getBigDecimal("SJFC"));
                Sbjkmx1.setQjfc(rs.getBigDecimal("QJFC"));
                Sbjkmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbjkmx1.setNd(rs.getString("ND"));
                Sbjkmx1.setSl(rs.getBigDecimal("SL"));
                Sbjkmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbjkmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbjkmx1.setQxdm(rs.getString("QXDM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbjkmx1;
    }
}
