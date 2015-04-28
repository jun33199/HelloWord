package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;


/**
 * 汇率代码表DAO
 */
public class WbhsDAO extends BaseDAO {

    /**
     * 插入一条汇率代码表记录
     * @param wbhs 汇率代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Wbhs wbhs, Connection conn) throws SQLException {
        String sql = "insert into DMDB.SB_DM_WBHS (BZDM,XH,JE,WHPJ,ZHRMB,BZMC,ND,YF) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //币种
            ps.setString(1, wbhs.bzdm);
            //序号
            ps.setBigDecimal(2, wbhs.xh);
            //金额
            ps.setBigDecimal(3, wbhs.je);
            //外汇牌价
            ps.setBigDecimal(4, wbhs.whpj);
            //折合人民币
            ps.setBigDecimal(5, wbhs.zhrmb);
            //币种名称
            ps.setString(6, wbhs.bzmc);
            //年度
            ps.setString(7, wbhs.nd);
            //月份
            ps.setString(8, wbhs.yf);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条汇率代码表记录
     * @param wbhs 汇率代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Wbhs wbhs, Connection conn) throws SQLException {
        String sql = "update  DMDB.SB_DM_WBHS set BZDM=?,XH=?,JE=?,WHPJ=?,ZHRMB=?,BZMC=?,ND=?,YF=?   where bzdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, wbhs.bzdm);
            ps.setBigDecimal(2, wbhs.xh);
            ps.setBigDecimal(3, wbhs.je);
            ps.setBigDecimal(4, wbhs.whpj);
            ps.setBigDecimal(5, wbhs.zhrmb);
            ps.setString(6, wbhs.bzmc);
            ps.setString(7, wbhs.nd);
            ps.setString(8, wbhs.yf);
            ps.setString(9, wbhs.bzdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条汇率代码表记录
     * @param wbhsList 汇率代码表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList wbhsList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SB_DM_WBHS  where bzdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < wbhsList.size(); i++) {
                Wbhs wbhs = (Wbhs) wbhsList.get(i);
                ps.setString(1, wbhs.bzdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取汇率代码表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 汇率代码表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList WbhsList = new ArrayList();
        String sql =
                "select BZDM,XH,JE,WHPJ,ZHRMB,BZMC,ND,YF from DMDB.SB_DM_WBHS " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wbhs Wbhs1 = new Wbhs();
                Wbhs1.setBzdm(rs.getString("BZDM"));
                Wbhs1.setXh(rs.getBigDecimal("XH"));
                Wbhs1.setJe(rs.getBigDecimal("JE"));
                Wbhs1.setWhpj(rs.getBigDecimal("WHPJ"));
                Wbhs1.setZhrmb(rs.getBigDecimal("ZHRMB"));
                Wbhs1.setBzmc(rs.getString("BZMC"));
                Wbhs1.setNd(rs.getString("ND"));
                Wbhs1.setYf(rs.getString("YF"));
                WbhsList.add(Wbhs1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return WbhsList;
    }

    /**
     * 根据主键获取汇率代码表值对象
     * @param wbhs 汇率代码表值对象
     * @param conn 数据库连接对象
     * @return汇率代码表值对象
     * @throws SQLException
     */
    public static Object get(Wbhs wbhs, Connection conn) throws SQLException {
        Wbhs Wbhs1 = new Wbhs();
        String sql = "select BZDM,XH,JE,WHPJ,ZHRMB,BZMC,ND,YF from DMDB.SB_DM_WBHS  WHERE BZDM=? and ND=? and YF=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, wbhs.bzdm);
            ps.setString(2, wbhs.nd);
            ps.setString(3, wbhs.yf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Wbhs1.setBzdm(rs.getString("BZDM"));
                Wbhs1.setXh(rs.getBigDecimal("XH"));
                Wbhs1.setJe(rs.getBigDecimal("JE"));
                Wbhs1.setWhpj(rs.getBigDecimal("WHPJ"));
                Wbhs1.setZhrmb(rs.getBigDecimal("ZHRMB"));
                Wbhs1.setBzmc(rs.getString("BZMC"));
                Wbhs1.setNd(rs.getString("ND"));
                Wbhs1.setYf(rs.getString("YF"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Wbhs1;
    }


}
