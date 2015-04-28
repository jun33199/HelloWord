package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;


/**
 * nullDAO
 */
public class ZhDAO extends BaseDAO {

    /**
     * 插入一条null记录
     * @param zh null值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Zh zh, Connection conn) throws SQLException {
        String sql = "insert into DMDB.PZ_DM_ZH (ZHDM,ZHMC,LXDH,SJZHDM,ZTBS,LRR,LRRQ,SWJGZZJGDM,LXBS,JSJDM) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //帐户代码
            ps.setString(1, zh.zhdm);
            //帐户名称
            ps.setString(2, zh.zhmc);
            //联系电话(账户)
            ps.setString(3, zh.lxdh);
            //上级帐户代码
            ps.setString(4, zh.sjzhdm);
            //状态标识
            ps.setString(5, zh.ztbs);
            //录入人
            ps.setString(6, zh.lrr);
            //录入日期
            ps.setTimestamp(7, zh.lrrq);
            //税务机关组织机构代码
            ps.setString(8, zh.swjgzzjgdm);
            //类型标识
            ps.setString(9, zh.lxbs);
            //计算机代码
            ps.setString(10, zh.jsjdm);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条null记录
     * @param zh null值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Zh zh, Connection conn) throws SQLException {
        String sql = "update  DMDB.PZ_DM_ZH set ZHDM=?,ZHMC=?,LXDH=?,SJZHDM=?,ZTBS=?,LRR=?,LRRQ=?,SWJGZZJGDM=?,LXBS=?,JSJDM=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zh.zhdm);
            ps.setString(2, zh.zhmc);
            ps.setString(3, zh.lxdh);
            ps.setString(4, zh.sjzhdm);
            ps.setString(5, zh.ztbs);
            ps.setString(6, zh.lrr);
            ps.setTimestamp(7, zh.lrrq);
            ps.setString(8, zh.swjgzzjgdm);
            ps.setString(9, zh.lxbs);
            ps.setString(10, zh.jsjdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条null记录
     * @param zhList null值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList zhList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.PZ_DM_ZH  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < zhList.size(); i++) {
                Zh zh = (Zh) zhList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取null值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList null值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList ZhList = new ArrayList();
        String sql = "select ZHDM,ZHMC,LXDH,SJZHDM,ZTBS,LRR,LRRQ,SWJGZZJGDM,LXBS,JSJDM from DMDB.PZ_DM_ZH " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Zh Zh1 = new Zh();
                Zh1.setZhdm(rs.getString("ZHDM"));
                Zh1.setZhmc(rs.getString("ZHMC"));
                Zh1.setLxdh(rs.getString("LXDH"));
                Zh1.setSjzhdm(rs.getString("SJZHDM"));
                Zh1.setZtbs(rs.getString("ZTBS"));
                Zh1.setLrr(rs.getString("LRR"));
                Zh1.setLrrq(rs.getTimestamp("LRRQ"));
                Zh1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Zh1.setLxbs(rs.getString("LXBS"));
                Zh1.setJsjdm(rs.getString("JSJDM"));
                ZhList.add(Zh1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return ZhList;
    }

    /**
     * 根据主键获取null值对象
     * @param zh null值对象
     * @param conn 数据库连接对象
     * @returnnull值对象
     * @throws SQLException
     */
    public static Object get(Zh zh, Connection conn) throws SQLException {
        Zh Zh1 = new Zh();
        String sql = "select ZHDM,ZHMC,LXDH,SJZHDM,ZTBS,LRR,LRRQ,SWJGZZJGDM,LXBS,JSJDM from DMDB.PZ_DM_ZH   where ZHDM='" +
                     zh.zhdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Zh1.setZhdm(rs.getString("ZHDM"));
                Zh1.setZhmc(rs.getString("ZHMC"));
                Zh1.setLxdh(rs.getString("LXDH"));
                Zh1.setSjzhdm(rs.getString("SJZHDM"));
                Zh1.setZtbs(rs.getString("ZTBS"));
                Zh1.setLrr(rs.getString("LRR"));
                Zh1.setLrrq(rs.getTimestamp("LRRQ"));
                Zh1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Zh1.setLxbs(rs.getString("LXBS"));
                Zh1.setJsjdm(rs.getString("JSJDM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Zh1;
    }


}
