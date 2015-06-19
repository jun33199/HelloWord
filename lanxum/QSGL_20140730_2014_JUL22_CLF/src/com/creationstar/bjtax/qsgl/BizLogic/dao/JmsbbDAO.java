package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmsbb;
import com.ttsoft.common.util.Debug;

/**
 * ��˰�����걨��DAO
 */
public class JmsbbDAO extends BaseDAO {

    /**
     * ����һ����˰�����걨���¼
     *
     * @param jmsbb
     *            ��˰�����걨��ֵ����
     * @param conn
     *            ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Jmsbb jmsbb, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_JMSBB (JMSBBH,SBBH,JMZCDM,DYBS,ZTBS,BZ,LRR,LRRQ,CJR,CJRQ,ND,JMLXDM,JMXZDM) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // �����걨���
            ps.setString(1, jmsbb.sbbh);
            // �걨���
            ps.setString(2, jmsbb.sbbh);
            // �������ߴ���
            ps.setString(3, jmsbb.jmzcdm);
            // ��ӡ��ʶ
            ps.setString(4, jmsbb.dybs);
            // ״̬��ʶ
            ps.setString(5, jmsbb.ztbs);
            // ��ע
            ps.setString(6, jmsbb.bz);
            // ¼����
            ps.setString(7, jmsbb.lrr);
            // ¼������
            ps.setTimestamp(8, jmsbb.lrrq);
            // ������
            ps.setString(9, jmsbb.cjr);
            // ��������
            ps.setTimestamp(10, jmsbb.cjrq);
            // ���
            ps.setString(11, jmsbb.nd);
            // ��������
            ps.setString(12, jmsbb.jmlxdm);
            // ��������
            ps.setString(13, jmsbb.jmxzdm);
            // ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰�����걨���¼
     *
     * @param jmsbb
     *            ��˰�����걨��ֵ����
     * @param conn
     *            ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Jmsbb jmsbb, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_JMSBB set JMSBBH=?,SBBH=?,JMZCDM=?,DYBS=?,ZTBS=?,BZ=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,ND=?,JMLXDM=?   where jmsbbh = ?  and jmzcdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jmsbb.sbbh);
            ps.setString(2, jmsbb.sbbh);
            ps.setString(3, jmsbb.jmzcdm);
            ps.setString(4, jmsbb.dybs);
            ps.setString(5, jmsbb.ztbs);
            ps.setString(6, jmsbb.bz);
            ps.setString(7, jmsbb.lrr);
            ps.setTimestamp(8, jmsbb.lrrq);
            ps.setString(9, jmsbb.cjr);
            ps.setTimestamp(10, jmsbb.cjrq);
            ps.setString(11, jmsbb.nd);
            // ��������
            ps.setString(12, jmsbb.jmlxdm);
            ps.setString(13, jmsbb.sbbh);
            ps.setString(14, jmsbb.jmzcdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������˰�����걨���¼
     *
     * @param jmsbbList
     *            ��˰�����걨��ֵ���󼯺�
     * @param conn
     *            ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList jmsbbList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  QSDB.QS_JL_JMSBB  where jmsbbh = ?  and jmzcdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jmsbbList.size(); i++) {
                Jmsbb jmsbb = (Jmsbb) jmsbbList.get(i);
                ps.setString(1, jmsbb.sbbh);
                ps.setString(2, jmsbb.jmzcdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * �����걨���ɾ����˰�����걨���¼
     *
     * @param sbbh
     *            ��˰�����걨��ֵ���󼯺�
     * @param conn
     *            ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(String sbbh, Connection conn) throws SQLException {
        String sql = "delete from  QSDB.QS_JL_JMSBB  where jmsbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ��˰�����걨��ֵ����
     *
     * @param condition
     *            ����where �������־�
     * @param conn
     *            ���ݿ����Ӷ���
     * @return ArrayList ��˰�����걨��ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JmsbbList = new ArrayList();
        String sql = "select JMSBBH,SBBH,JMZCDM,DYBS,ZTBS,BZ,LRR,LRRQ,CJR,CJRQ,ND,JMLXDM,SPJGDM,SPRQ,JMXZDM from QSDB.QS_JL_JMSBB "
                     + condition;
        Debug.out("JmsbbDAO query sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jmsbb Jmsbb1 = new Jmsbb();
//				Jmsbb1.setJmsbbh(rs.getString("JMSBBH"));
                Jmsbb1.setSbbh(rs.getString("SBBH"));
                Jmsbb1.setJmzcdm(rs.getString("JMZCDM"));
                Jmsbb1.setDybs(rs.getString("DYBS"));
                Jmsbb1.setZtbs(rs.getString("ZTBS"));
                Jmsbb1.setBz(rs.getString("BZ"));
                Jmsbb1.setLrr(rs.getString("LRR"));
                Jmsbb1.setLrrq(rs.getTimestamp("LRRQ"));
                Jmsbb1.setCjr(rs.getString("CJR"));
                Jmsbb1.setCjrq(rs.getTimestamp("CJRQ"));
                Jmsbb1.setNd(rs.getString("ND"));
                Jmsbb1.setJmlxdm(rs.getString("JMLXDM"));
                Jmsbb1.setSpjg(rs.getString("SPJGDM"));
                Jmsbb1.setSprq(rs.getTimestamp("SPRQ"));
                Jmsbb1.setJmxzdm(rs.getString("JMXZDM"));
                JmsbbList.add(Jmsbb1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JmsbbList;
    }

    /**
     * ����������ȡ��˰�����걨��ֵ����
     *
     * @param jmsbb
     *            ��˰�����걨��ֵ����
     * @param conn
     *            ���ݿ����Ӷ���
     * @return��˰�����걨��ֵ����
     * @throws SQLException
     */
    public static Object get(Jmsbb jmsbb, Connection conn) throws SQLException {
        Jmsbb Jmsbb1 = new Jmsbb();
        String sql = "select JMSBBH,SBBH,JMZCDM,DYBS,ZTBS,BZ,LRR,LRRQ,CJR,CJRQ,ND,JMLXDM from QSDB.QS_JL_JMSBB   where jmsbbh = ?  and jmzcdm = ? ";
        Debug.out("JmsbbDAO get sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jmsbb.sbbh);
            ps.setString(2, jmsbb.jmzcdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//				Jmsbb1.setJmsbbh(rs.getString("JMSBBH"));
                Jmsbb1.setSbbh(rs.getString("SBBH"));
                Jmsbb1.setJmzcdm(rs.getString("JMZCDM"));
                Jmsbb1.setDybs(rs.getString("DYBS"));
                Jmsbb1.setZtbs(rs.getString("ZTBS"));
                Jmsbb1.setBz(rs.getString("BZ"));
                Jmsbb1.setLrr(rs.getString("LRR"));
                Jmsbb1.setLrrq(rs.getTimestamp("LRRQ"));
                Jmsbb1.setCjr(rs.getString("CJR"));
                Jmsbb1.setCjrq(rs.getTimestamp("CJRQ"));
                Jmsbb1.setNd(rs.getString("ND"));
                Jmsbb1.setJmlxdm(rs.getString("JMLXDM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jmsbb1;
    }

    /**
     * update״̬��ʶ�ʹ�ӡ��ʶ
     *
     * @param zt
     *            String
     * @param conditions
     *            String
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void updateBs(Jmsbb jmsbb, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_JMSBB set DYBS=?,ZTBS=?   where jmsbbh = ?  and jmzcdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jmsbb.dybs);
            ps.setString(2, jmsbb.ztbs);
            ps.setString(3, jmsbb.sbbh);
            ps.setString(4, jmsbb.jmzcdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * update�������ء���������
     *
     * @param zt
     *            String
     * @param conditions
     *            String
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void updateSpjg(String spjgdm, Timestamp sprq, String sbbh,
                                  Connection conn) throws SQLException {
        String sql =
                "update  QSDB.QS_JL_JMSBB set spjgdm=?,sprq=?   where jmsbbh = ?  ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, spjgdm);
            ps.setTimestamp(2, sprq);
            ps.setString(3, sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
