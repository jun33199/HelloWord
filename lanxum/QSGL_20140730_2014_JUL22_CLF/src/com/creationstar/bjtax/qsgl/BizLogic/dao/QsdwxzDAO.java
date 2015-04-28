package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsdwxz;


/**
 * ��˰�����ʹ���DAO
 */
public class QsdwxzDAO extends BaseDAO {

    /**
     * ����һ����˰�����ʹ����¼
     * @param qsdwxz ��˰�����ʹ���ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Qsdwxz qsdwxz, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.SF_DM_QSDWXZ (NSRLXDM,NSRLXMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //��˰��λ���ʴ���
            ps.setString(1, qsdwxz.nsrlxdm);
            //��˰��λ��������
            ps.setString(2, qsdwxz.nsrlxmc);
            //¼����
            ps.setString(3, qsdwxz.lrr);
            //¼������
            ps.setTimestamp(4, qsdwxz.lrrq);
            //ע����ʶ
            ps.setString(5, qsdwxz.zxbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰�����ʹ����¼
     * @param qsdwxz ��˰�����ʹ���ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Qsdwxz qsdwxz, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.SF_DM_QSDWXZ set NSRLXDM=?,NSRLXMC=?,LRR=?,LRRQ=?,ZXBS=?   where nsrlxdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qsdwxz.nsrlxdm);
            ps.setString(2, qsdwxz.nsrlxmc);
            ps.setString(3, qsdwxz.lrr);
            ps.setTimestamp(4, qsdwxz.lrrq);
            ps.setString(5, qsdwxz.zxbs);
            ps.setString(6, qsdwxz.nsrlxdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������˰�����ʹ����¼
     * @param qsdwxzList ��˰�����ʹ���ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList qsdwxzList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_QSDWXZ  where nsrlxdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qsdwxzList.size(); i++) {
                Qsdwxz qsdwxz = (Qsdwxz) qsdwxzList.get(i);
                ps.setString(1, qsdwxz.nsrlxdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ��˰�����ʹ���ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰�����ʹ���ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QsdwxzList = new ArrayList();
        String sql =
                "select NSRLXDM,NSRLXMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSDWXZ " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qsdwxz Qsdwxz1 = new Qsdwxz();
                Qsdwxz1.setNsrlxdm(rs.getString("NSRLXDM"));
                Qsdwxz1.setNsrlxmc(rs.getString("NSRLXMC"));
                Qsdwxz1.setLrr(rs.getString("LRR"));
                Qsdwxz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qsdwxz1.setZxbs(rs.getString("ZXBS"));
                QsdwxzList.add(Qsdwxz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QsdwxzList;
    }

    /**
     * ����������ȡ��˰�����ʹ���ֵ����
     * @param qsdwxz ��˰�����ʹ���ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰�����ʹ���ֵ����
     * @throws SQLException
     */
    public static Object get(Qsdwxz qsdwxz, Connection conn) throws
            SQLException {
        Qsdwxz Qsdwxz1 = new Qsdwxz();
        String sql = "select NSRLXDM,NSRLXMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSDWXZ   where nsrlxdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qsdwxz.nsrlxdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qsdwxz1.setNsrlxdm(rs.getString("NSRLXDM"));
                Qsdwxz1.setNsrlxmc(rs.getString("NSRLXMC"));
                Qsdwxz1.setLrr(rs.getString("LRR"));
                Qsdwxz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qsdwxz1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qsdwxz1;
    }


}
