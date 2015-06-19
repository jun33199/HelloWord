package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qstdfwyt;


/**
 *  ��˰���ط�����;DAO
 */
public class QstdfwytDAO extends BaseDAO {

    /**
     * ����һ�� ��˰���ط�����;��¼
     * @param qstdfwyt  ��˰���ط�����;ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Qstdfwyt qstdfwyt, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.SF_DM_QSTDFWYT (QSTDFWYTDM,QSTDFWYTMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //��˰���ط�����;����
            ps.setString(1, qstdfwyt.qstdfwytdm);
            //��˰���ط�����;����
            ps.setString(2, qstdfwyt.qstdfwytmc);
            //¼����
            ps.setString(3, qstdfwyt.lrr);
            //¼������
            ps.setTimestamp(4, qstdfwyt.lrrq);
            //ע����ʶ
            ps.setString(5, qstdfwyt.zxbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ�� ��˰���ط�����;��¼
     * @param qstdfwyt  ��˰���ط�����;ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Qstdfwyt qstdfwyt, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.SF_DM_QSTDFWYT set QSTDFWYTDM=?,QSTDFWYTMC=?,LRR=?,LRRQ=?,ZXBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qstdfwyt.qstdfwytdm);
            ps.setString(2, qstdfwyt.qstdfwytmc);
            ps.setString(3, qstdfwyt.lrr);
            ps.setTimestamp(4, qstdfwyt.lrrq);
            ps.setString(5, qstdfwyt.zxbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������ ��˰���ط�����;��¼
     * @param qstdfwytList  ��˰���ط�����;ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList qstdfwytList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_QSTDFWYT  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qstdfwytList.size(); i++) {
                Qstdfwyt qstdfwyt = (Qstdfwyt) qstdfwytList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ ��˰���ط�����;ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList  ��˰���ط�����;ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QstdfwytList = new ArrayList();
        String sql =
                "select QSTDFWYTDM,QSTDFWYTMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSTDFWYT " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qstdfwyt Qstdfwyt1 = new Qstdfwyt();
                Qstdfwyt1.setQstdfwytdm(rs.getString("QSTDFWYTDM"));
                Qstdfwyt1.setQstdfwytmc(rs.getString("QSTDFWYTMC"));
                Qstdfwyt1.setLrr(rs.getString("LRR"));
                Qstdfwyt1.setLrrq(rs.getTimestamp("LRRQ"));
                Qstdfwyt1.setZxbs(rs.getString("ZXBS"));
                QstdfwytList.add(Qstdfwyt1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QstdfwytList;
    }

    /**
     * ����������ȡ ��˰���ط�����;ֵ����
     * @param qstdfwyt  ��˰���ط�����;ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return ��˰���ط�����;ֵ����
     * @throws SQLException
     */
    public static Object get(Qstdfwyt qstdfwyt, Connection conn) throws
            SQLException {
        Qstdfwyt Qstdfwyt1 = new Qstdfwyt();
        String sql =
                "select QSTDFWYTDM,QSTDFWYTMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSTDFWYT   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qstdfwyt1.setQstdfwytdm(rs.getString("QSTDFWYTDM"));
                Qstdfwyt1.setQstdfwytmc(rs.getString("QSTDFWYTMC"));
                Qstdfwyt1.setLrr(rs.getString("LRR"));
                Qstdfwyt1.setLrrq(rs.getTimestamp("LRRQ"));
                Qstdfwyt1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qstdfwyt1;
    }


}
