package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;


/**
 * 税务机关组织机构代码表DAO
 */
public class SwjgzzjgDAO extends BaseDAO {

    /**
     * 插入一条税务机关组织机构代码表记录
     * @param swjgzzjg 税务机关组织机构代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Swjgzzjg swjgzzjg, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.GY_DM_SWJGZZJG (SWJGZZJGDM,QXFJDM,JGZNLX,XZQDM,SWJGZZJGDZ,YZBM,SWJGZZJGDH,SKGK,GKJHH,JGCCGX,SWJGZZJGMC,JC,WSJC,BBBZDWMC,BZ,LRR,LRRQ,ZXBS,GKZZJGDM,JSJDM,FJDDM,CCBS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //税务机关组织机构代码
            ps.setString(1, swjgzzjg.swjgzzjgdm);
            //区县分局代码
            ps.setString(2, swjgzzjg.qxfjdm);
            //机构职能类型
            ps.setString(3, swjgzzjg.jgznlx);
            //行政区代码
            ps.setString(4, swjgzzjg.xzqdm);
            //税务机关组织机构地址
            ps.setString(5, swjgzzjg.swjgzzjgdz);
            //邮政编码
            ps.setString(6, swjgzzjg.yzbm);
            //税务机关组织机构联系电话
            ps.setString(7, swjgzzjg.swjgzzjgdh);
            //收款国库
            ps.setString(8, swjgzzjg.skgk);
            //国库交换号
            ps.setString(9, swjgzzjg.gkjhh);
            //机构层次关系
            ps.setString(10, swjgzzjg.jgccgx);
            //税务机关组织机构名称
            ps.setString(11, swjgzzjg.swjgzzjgmc);
            //简称
            ps.setString(12, swjgzzjg.jc);
            //文书简称
            ps.setString(13, swjgzzjg.wsjc);
            //报表编制单位名称
            ps.setString(14, swjgzzjg.bbbzdwmc);
            //备注
            ps.setString(15, swjgzzjg.bz);
            //录入人
            ps.setString(16, swjgzzjg.lrr);
            //录入日期
            ps.setTimestamp(17, swjgzzjg.lrrq);
            //注销标识
            ps.setString(18, swjgzzjg.zxbs);
            //国库组织机构代码
            ps.setString(19, swjgzzjg.gkzzjgdm);
            //计算机代码
            ps.setString(20, swjgzzjg.jsjdm);
            //父节点代码
            ps.setString(21, swjgzzjg.fjddm);
            //层次标识
            ps.setString(22, swjgzzjg.ccbs);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条税务机关组织机构代码表记录
     * @param swjgzzjg 税务机关组织机构代码表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(Swjgzzjg swjgzzjg, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.GY_DM_SWJGZZJG set SWJGZZJGDM=?,QXFJDM=?,JGZNLX=?,XZQDM=?,SWJGZZJGDZ=?,YZBM=?,SWJGZZJGDH=?,SKGK=?,GKJHH=?,JGCCGX=?,SWJGZZJGMC=?,JC=?,WSJC=?,BBBZDWMC=?,BZ=?,LRR=?,LRRQ=?,ZXBS=?,GKZZJGDM=?,JSJDM=?,FJDDM=?,CCBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, swjgzzjg.swjgzzjgdm);
            ps.setString(2, swjgzzjg.qxfjdm);
            ps.setString(3, swjgzzjg.jgznlx);
            ps.setString(4, swjgzzjg.xzqdm);
            ps.setString(5, swjgzzjg.swjgzzjgdz);
            ps.setString(6, swjgzzjg.yzbm);
            ps.setString(7, swjgzzjg.swjgzzjgdh);
            ps.setString(8, swjgzzjg.skgk);
            ps.setString(9, swjgzzjg.gkjhh);
            ps.setString(10, swjgzzjg.jgccgx);
            ps.setString(11, swjgzzjg.swjgzzjgmc);
            ps.setString(12, swjgzzjg.jc);
            ps.setString(13, swjgzzjg.wsjc);
            ps.setString(14, swjgzzjg.bbbzdwmc);
            ps.setString(15, swjgzzjg.bz);
            ps.setString(16, swjgzzjg.lrr);
            ps.setTimestamp(17, swjgzzjg.lrrq);
            ps.setString(18, swjgzzjg.zxbs);
            ps.setString(19, swjgzzjg.gkzzjgdm);
            ps.setString(20, swjgzzjg.jsjdm);
            ps.setString(21, swjgzzjg.fjddm);
            ps.setString(22, swjgzzjg.ccbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条税务机关组织机构代码表记录
     * @param swjgzzjgList 税务机关组织机构代码表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList swjgzzjgList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.GY_DM_SWJGZZJG  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < swjgzzjgList.size(); i++) {
                Swjgzzjg swjgzzjg = (Swjgzzjg) swjgzzjgList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取税务机关组织机构代码表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 税务机关组织机构代码表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SwjgzzjgList = new ArrayList();
        String sql = "select SWJGZZJGDM,QXFJDM,JGZNLX,XZQDM,SWJGZZJGDZ,YZBM,SWJGZZJGDH,SKGK,GKJHH,JGCCGX,SWJGZZJGMC,JC,WSJC,BBBZDWMC,BZ,LRR,LRRQ,ZXBS,GKZZJGDM,JSJDM,FJDDM,CCBS,LWZT from DMDB.GY_DM_SWJGZZJG " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Swjgzzjg Swjgzzjg1 = new Swjgzzjg();
                Swjgzzjg1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Swjgzzjg1.setQxfjdm(rs.getString("QXFJDM"));
                Swjgzzjg1.setJgznlx(rs.getString("JGZNLX"));
                Swjgzzjg1.setXzqdm(rs.getString("XZQDM"));
                Swjgzzjg1.setSwjgzzjgdz(rs.getString("SWJGZZJGDZ"));
                Swjgzzjg1.setYzbm(rs.getString("YZBM"));
                Swjgzzjg1.setSwjgzzjgdh(rs.getString("SWJGZZJGDH"));
                Swjgzzjg1.setSkgk(rs.getString("SKGK"));
                Swjgzzjg1.setGkjhh(rs.getString("GKJHH"));
                Swjgzzjg1.setJgccgx(rs.getString("JGCCGX"));
                Swjgzzjg1.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
                Swjgzzjg1.setJc(rs.getString("JC"));
                Swjgzzjg1.setWsjc(rs.getString("WSJC"));
                Swjgzzjg1.setBbbzdwmc(rs.getString("BBBZDWMC"));
                Swjgzzjg1.setBz(rs.getString("BZ"));
                Swjgzzjg1.setLrr(rs.getString("LRR"));
                Swjgzzjg1.setLrrq(rs.getTimestamp("LRRQ"));
                Swjgzzjg1.setZxbs(rs.getString("ZXBS"));
                Swjgzzjg1.setGkzzjgdm(rs.getString("GKZZJGDM"));
                Swjgzzjg1.setJsjdm(rs.getString("JSJDM"));
                Swjgzzjg1.setFjddm(rs.getString("FJDDM"));
                Swjgzzjg1.setCcbs(rs.getString("CCBS"));
                Swjgzzjg1.setLwzt(rs.getString("LWZT"));
                SwjgzzjgList.add(Swjgzzjg1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SwjgzzjgList;
    }

    /**
     * 根据主键获取税务机关组织机构代码表值对象
     * @param swjgzzjg 税务机关组织机构代码表值对象
     * @param conn 数据库连接对象
     * @return税务机关组织机构代码表值对象
     * @throws SQLException
     */
    public static Object get(com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg
                             swjgzzjg, Connection conn) throws SQLException {
        Swjgzzjg Swjgzzjg1 = new Swjgzzjg();
        String sql = "select SWJGZZJGDM,QXFJDM,JGZNLX,XZQDM,SWJGZZJGDZ,YZBM,SWJGZZJGDH,SKGK,GKJHH,JGCCGX,SWJGZZJGMC,JC,WSJC,BBBZDWMC,BZ,LRR,LRRQ,ZXBS,GKZZJGDM,JSJDM,FJDDM,CCBS,LWZT from DMDB.GY_DM_SWJGZZJG   where swjgzzjgdm='" +
                     swjgzzjg.swjgzzjgdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Swjgzzjg1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Swjgzzjg1.setQxfjdm(rs.getString("QXFJDM"));
                Swjgzzjg1.setJgznlx(rs.getString("JGZNLX"));
                Swjgzzjg1.setXzqdm(rs.getString("XZQDM"));
                Swjgzzjg1.setSwjgzzjgdz(rs.getString("SWJGZZJGDZ"));
                Swjgzzjg1.setYzbm(rs.getString("YZBM"));
                Swjgzzjg1.setSwjgzzjgdh(rs.getString("SWJGZZJGDH"));
                Swjgzzjg1.setSkgk(rs.getString("SKGK"));
                Swjgzzjg1.setGkjhh(rs.getString("GKJHH"));
                Swjgzzjg1.setJgccgx(rs.getString("JGCCGX"));
                Swjgzzjg1.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
                Swjgzzjg1.setJc(rs.getString("JC"));
                Swjgzzjg1.setWsjc(rs.getString("WSJC"));
                Swjgzzjg1.setBbbzdwmc(rs.getString("BBBZDWMC"));
                Swjgzzjg1.setBz(rs.getString("BZ"));
                Swjgzzjg1.setLrr(rs.getString("LRR"));
                Swjgzzjg1.setLrrq(rs.getTimestamp("LRRQ"));
                Swjgzzjg1.setZxbs(rs.getString("ZXBS"));
                Swjgzzjg1.setGkzzjgdm(rs.getString("GKZZJGDM"));
                Swjgzzjg1.setJsjdm(rs.getString("JSJDM"));
                Swjgzzjg1.setFjddm(rs.getString("FJDDM"));
                Swjgzzjg1.setCcbs(rs.getString("CCBS"));
                Swjgzzjg1.setLwzt(rs.getString("LWZT"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Swjgzzjg1;
    }


}
