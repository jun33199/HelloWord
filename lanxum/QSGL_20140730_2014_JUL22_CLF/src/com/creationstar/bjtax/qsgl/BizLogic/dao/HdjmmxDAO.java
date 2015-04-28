package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.ttsoft.common.util.Debug;


/**
 * �˶�������ϸ��DAO
 */
public class HdjmmxDAO extends BaseDAO {

    /**
     * ����һ���˶�������ϸ���¼
     * @param hdjmmx �˶�������ϸ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Hdjmmx hdjmmx, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_HDJMMX (ZCBH,HDTZSH,JMJE,LRR,LRRQ,CJR,CJRQ,BZ,ND) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //���߱��
            ps.setString(1, hdjmmx.zcbh);
            //�˶�֪ͨ���
            ps.setString(2, hdjmmx.hdtzsh);
            //������
            ps.setBigDecimal(3, hdjmmx.jmje);
            //¼����
            ps.setString(4, hdjmmx.lrr);
            //¼������
            ps.setTimestamp(5, hdjmmx.lrrq);
            //������
            ps.setString(6, hdjmmx.cjr);
            //��������
            ps.setTimestamp(7, hdjmmx.cjrq);
            //��ע
            ps.setString(8, hdjmmx.bz);
            //���
            ps.setString(9, hdjmmx.nd);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���˶�������ϸ���¼
     * @param hdjmmx �˶�������ϸ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Hdjmmx hdjmmx, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_HDJMMX set ZCBH=?,HDTZSH=?,JMJE=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,BZ=?,ND=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdjmmx.zcbh);
            ps.setString(2, hdjmmx.hdtzsh);
            ps.setBigDecimal(3, hdjmmx.jmje);
            ps.setString(4, hdjmmx.lrr);
            ps.setTimestamp(5, hdjmmx.lrrq);
            ps.setString(6, hdjmmx.cjr);
            ps.setTimestamp(7, hdjmmx.cjrq);
            ps.setString(8, hdjmmx.bz);
            ps.setString(9, hdjmmx.nd);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������˶�������ϸ���¼
     * @param hdjmmxList �˶�������ϸ��ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList hdjmmxList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_HDJMMX  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < hdjmmxList.size(); i++) {
                Hdjmmx hdjmmx = (Hdjmmx) hdjmmxList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ�˶�������ϸ��ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �˶�������ϸ��ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList HdjmmxList = new ArrayList();
        String sql =
                "select ZCBH,HDTZSH,JMJE,LRR,LRRQ,CJR,CJRQ,BZ,ND from QSDB.QS_JL_HDJMMX " +
                condition;
        Debug.out("query sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hdjmmx Hdjmmx1 = new Hdjmmx();
                Hdjmmx1.setZcbh(rs.getString("ZCBH"));
                Hdjmmx1.setHdtzsh(rs.getString("HDTZSH"));
                Hdjmmx1.setJmje(rs.getBigDecimal("JMJE"));
                Hdjmmx1.setLrr(rs.getString("LRR"));
                Hdjmmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Hdjmmx1.setCjr(rs.getString("CJR"));
                Hdjmmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Hdjmmx1.setBz(rs.getString("BZ"));
                Hdjmmx1.setNd(rs.getString("ND"));
                HdjmmxList.add(Hdjmmx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return HdjmmxList;
    }

    /**
     * ����������ȡ�˶�������ϸ��ֵ����
     * @param hdjmmx �˶�������ϸ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�˶�������ϸ��ֵ����
     * @throws SQLException
     */
    public static Object get(Hdjmmx hdjmmx, Connection conn) throws
            SQLException {
        Hdjmmx Hdjmmx1 = new Hdjmmx();
        String sql = "select ZCBH,HDTZSH,JMJE,LRR,LRRQ,CJR,CJRQ,BZ,ND from QSDB.QS_JL_HDJMMX   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hdjmmx1.setZcbh(rs.getString("ZCBH"));
                Hdjmmx1.setHdtzsh(rs.getString("HDTZSH"));
                Hdjmmx1.setJmje(rs.getBigDecimal("JMJE"));
                Hdjmmx1.setLrr(rs.getString("LRR"));
                Hdjmmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Hdjmmx1.setCjr(rs.getString("CJR"));
                Hdjmmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Hdjmmx1.setBz(rs.getString("BZ"));
                Hdjmmx1.setNd(rs.getString("ND"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Hdjmmx1;
    }


}
