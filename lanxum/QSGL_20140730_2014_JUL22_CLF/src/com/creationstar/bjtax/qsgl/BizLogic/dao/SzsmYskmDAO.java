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
 * ˰��˰Ŀ��Ԥ���Ŀ���ձ�DAO
 */
public class SzsmYskmDAO extends BaseDAO {

    /**
     * ����һ��˰��˰Ŀ��Ԥ���Ŀ���ձ��¼
     * @param yskm ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(SzsmYskm yskm, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.KJ_DM_SZSM_YSKM (SZSMDM,YSKMDM,MRYSKMDM,LRR,LRRQ) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //˰��˰Ŀ����
            ps.setString(1, yskm.szsmdm);
            //Ԥ���Ŀ����
            ps.setString(2, yskm.yskmdm);
            //Ĭ��Ԥ���Ŀ����
            ps.setString(3, yskm.mryskmdm);
            //¼����
            ps.setString(4, yskm.lrr);
            //¼������
            ps.setTimestamp(5, yskm.lrrq);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��˰��˰Ŀ��Ԥ���Ŀ���ձ��¼
     * @param yskm ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
     * @param conn ���ݿ����Ӷ���
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
     * ɾ������˰��˰Ŀ��Ԥ���Ŀ���ձ��¼
     * @param yskmList ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
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
     * ����������ȡ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����ļ���
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
     * ����������ȡ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
     * @param yskm ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
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

        //Ԥ���Ŀ�����޸ĺ�Ӽƻ�ӿ�ȡ��Ԥ���Ŀ����2006��12��20

        Yskm yskm_jk = null;

        try {
            System.out.println("yskm.szsmdm��˰����ͨ������ȡ��Ԥ���Ŀ�����˰��˰Ŀ���룺" +
                               yskm.szsmdm);
            yskm_jk = JKAdapter.getInstance().getYskm(yskm.szsmdm,
                    Constants.WSZ_DJZCLX, Constants.WSZ_GJBZHYDM,
                    Constants.YSJCDM_DF);

            Yskm1.setSzsmdm(yskm.szsmdm);
            Yskm1.setYskmdm(yskm_jk.getYskmdm());
            Yskm1.setMryskmdm(yskm_jk.getYskmdm());
            Yskm1.setLrr("system");
            Yskm1.setLrrq(CommonUtil.getDBtime(conn));

            System.out.println("yskm_jk.getYskmdm()��˰����ͨ������ȡ��Ԥ���Ŀ���룺" +
                               yskm_jk.getYskmdm());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw e;
        }

        return Yskm1;
    }


}
