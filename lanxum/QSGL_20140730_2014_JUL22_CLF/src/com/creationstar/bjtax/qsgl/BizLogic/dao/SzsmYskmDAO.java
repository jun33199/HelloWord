package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.SzsmYskm;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;


/**
 * 税种税目与预算科目对照表DAO
 */
public class SzsmYskmDAO extends BaseDAO {

    /**
     * 插入一条税种税目与预算科目对照表记录
     * @param yskm 税种税目与预算科目对照表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(SzsmYskm yskm, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.KJ_DM_SZSM_YSKM (SZSMDM,YSKMDM,MRYSKMDM,LRR,LRRQ) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //税种税目代码
            ps.setString(1, yskm.szsmdm);
            //预算科目代码
            ps.setString(2, yskm.yskmdm);
            //默认预算科目代码
            ps.setString(3, yskm.mryskmdm);
            //录入人
            ps.setString(4, yskm.lrr);
            //录入日期
            ps.setTimestamp(5, yskm.lrrq);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条税种税目与预算科目对照表记录
     * @param yskm 税种税目与预算科目对照表值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void update(SzsmYskm yskm, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.KJ_DM_SZSM_YSKM set SZSMDM=?,YSKMDM=?,MRYSKMDM=?,LRR=?,LRRQ=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, yskm.szsmdm);
            ps.setString(2, yskm.yskmdm);
            ps.setString(3, yskm.mryskmdm);
            ps.setString(4, yskm.lrr);
            ps.setTimestamp(5, yskm.lrrq);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 删除多条税种税目与预算科目对照表记录
     * @param yskmList 税种税目与预算科目对照表值对象集合
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void delete(ArrayList yskmList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.KJ_DM_SZSM_YSKM  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < yskmList.size(); i++) {
                SzsmYskm yskm = (SzsmYskm) yskmList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 根据主键获取税种税目与预算科目对照表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 税种税目与预算科目对照表值对象的集合
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList YskmList = new ArrayList();
        String sql =
                "select SZSMDM,YSKMDM,MRYSKMDM,LRR,LRRQ from DMDB.KJ_DM_SZSM_YSKM " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SzsmYskm Yskm1 = new SzsmYskm();
                Yskm1.setSzsmdm(rs.getString("SZSMDM"));
                Yskm1.setYskmdm(rs.getString("YSKMDM"));
                Yskm1.setMryskmdm(rs.getString("MRYSKMDM"));
                Yskm1.setLrr(rs.getString("LRR"));
                Yskm1.setLrrq(rs.getTimestamp("LRRQ"));
                YskmList.add(Yskm1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return YskmList;
    }

    /**
     * 根据主键获取税种税目与预算科目对照表值对象
     * @param yskm 税种税目与预算科目对照表值对象
     * @param conn 数据库连接对象
     * @return税种税目与预算科目对照表值对象
     * @throws SQLException
     */
    public static Object get(SzsmYskm yskm, Connection conn) throws Exception {
        SzsmYskm Yskm1 = new SzsmYskm();
//        String sql = "select SZSMDM,YSKMDM,MRYSKMDM,LRR,LRRQ from DMDB.KJ_DM_SZSM_YSKM   where SZSMDM='" + yskm.szsmdm + "'";
//        PreparedStatement ps = null;
//        try{
//            ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next())
//            {
//                Yskm1.setSzsmdm(rs.getString("SZSMDM"));
//                Yskm1.setYskmdm(rs.getString("YSKMDM"));
//                Yskm1.setMryskmdm(rs.getString("MRYSKMDM"));
//                Yskm1.setLrr(rs.getString("LRR"));
//                Yskm1.setLrrq(rs.getTimestamp("LRRQ"));
//            }
//            rs.close();
//        }
//        catch(SQLException e)
//        {
//            throw e;
//        }
//        finally
//        {
//            ps.close();
//        }

        //预算科目代码修改后从计会接口取得预算科目代码2006－12－20

        Yskm yskm_jk = null;

        try {
            System.out.println("yskm.szsmdm契税管理通过帐务取得预算科目代码的税种税目代码：" +
                               yskm.szsmdm);
            yskm_jk = JKAdapter.getInstance().getYskm(yskm.szsmdm,
                    Constants.WSZ_DJZCLX, Constants.WSZ_GJBZHYDM,
                    Constants.YSJCDM_DF);

            Yskm1.setSzsmdm(yskm.szsmdm);
            Yskm1.setYskmdm(yskm_jk.getYskmdm());
            Yskm1.setMryskmdm(yskm_jk.getYskmdm());
            Yskm1.setLrr("system");
            Yskm1.setLrrq(CommonUtil.getDBtime(conn));

            System.out.println("yskm_jk.getYskmdm()契税管理通过帐务取得预算科目代码：" +
                               yskm_jk.getYskmdm());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw e;
        }

        return Yskm1;
    }


}
