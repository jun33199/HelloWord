package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qszyxs;


/**
 * ���ط���Ȩ��ת����ʽ�����DAO
 */
public class QszyxsDAO extends BaseDAO {

    /**
     * ����һ�����ط���Ȩ��ת����ʽ������¼
     * @param qszyxs ���ط���Ȩ��ת����ʽ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Qszyxs qszyxs, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.SF_DM_QSZYXS (QSZYXSDM,QSZYXSMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //���ط���Ȩ��ת����ʽ����
            ps.setString(1, qszyxs.qszyxsdm);
            //���ط���Ȩ��ת����ʽ����
            ps.setString(2, qszyxs.qszyxsmc);
            //¼����
            ps.setString(3, qszyxs.lrr);
            //¼������
            ps.setTimestamp(4, qszyxs.lrrq);
            //ע����ʶ
            ps.setString(5, qszyxs.zxbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ�����ط���Ȩ��ת����ʽ������¼
     * @param qszyxs ���ط���Ȩ��ת����ʽ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Qszyxs qszyxs, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.SF_DM_QSZYXS set QSZYXSDM=?,QSZYXSMC=?,LRR=?,LRRQ=?,ZXBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qszyxs.qszyxsdm);
            ps.setString(2, qszyxs.qszyxsmc);
            ps.setString(3, qszyxs.lrr);
            ps.setTimestamp(4, qszyxs.lrrq);
            ps.setString(5, qszyxs.zxbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ���������ط���Ȩ��ת����ʽ������¼
     * @param qszyxsList ���ط���Ȩ��ת����ʽ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList qszyxsList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_QSZYXS  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qszyxsList.size(); i++) {
                Qszyxs qszyxs = (Qszyxs) qszyxsList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ���ط���Ȩ��ת����ʽ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ���ط���Ȩ��ת����ʽ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QszyxsList = new ArrayList();
        String sql =
                "select QSZYXSDM,QSZYXSMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSZYXS " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qszyxs Qszyxs1 = new Qszyxs();
                Qszyxs1.setQszyxsdm(rs.getString("QSZYXSDM"));
                Qszyxs1.setQszyxsmc(rs.getString("QSZYXSMC"));
                Qszyxs1.setLrr(rs.getString("LRR"));
                Qszyxs1.setLrrq(rs.getTimestamp("LRRQ"));
                Qszyxs1.setZxbs(rs.getString("ZXBS"));
                QszyxsList.add(Qszyxs1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QszyxsList;
    }

    /**
     * ����������ȡ���ط���Ȩ��ת����ʽ�����ֵ����
     * @param qszyxs ���ط���Ȩ��ת����ʽ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return���ط���Ȩ��ת����ʽ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Qszyxs qszyxs, Connection conn) throws
            SQLException {
        Qszyxs Qszyxs1 = new Qszyxs();
        String sql =
                "select QSZYXSDM,QSZYXSMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_QSZYXS   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qszyxs1.setQszyxsdm(rs.getString("QSZYXSDM"));
                Qszyxs1.setQszyxsmc(rs.getString("QSZYXSMC"));
                Qszyxs1.setLrr(rs.getString("LRR"));
                Qszyxs1.setLrrq(rs.getTimestamp("LRRQ"));
                Qszyxs1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qszyxs1;
    }


}
