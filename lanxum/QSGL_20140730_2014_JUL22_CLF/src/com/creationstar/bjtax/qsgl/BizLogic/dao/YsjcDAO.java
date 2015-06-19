package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Ysjc;


/**
 * Ԥ�㼶�δ����DAO
 */
public class YsjcDAO extends BaseDAO {

    /**
     * ����һ��Ԥ�㼶�δ�����¼
     * @param ysjc Ԥ�㼶�δ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Ysjc ysjc, Connection conn) throws SQLException {
        String sql =
                "insert into DMDB.SF_DM_YSJC (YSJCDM,YSJCMC,LRR,LRRQ,ZXBS) values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //Ԥ�㼶�δ���
            ps.setString(1, ysjc.ysjcdm);
            //Ԥ�㼶������
            ps.setString(2, ysjc.ysjcmc);
            //¼����
            ps.setString(3, ysjc.lrr);
            //¼������
            ps.setTimestamp(4, ysjc.lrrq);
            //ע����ʶ
            ps.setString(5, ysjc.zxbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��Ԥ�㼶�δ�����¼
     * @param ysjc Ԥ�㼶�δ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Ysjc ysjc, Connection conn) throws SQLException {
        String sql =
                "update  DMDB.SF_DM_YSJC set YSJCDM=?,YSJCMC=?,LRR=?,LRRQ=?,ZXBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ysjc.ysjcdm);
            ps.setString(2, ysjc.ysjcmc);
            ps.setString(3, ysjc.lrr);
            ps.setTimestamp(4, ysjc.lrrq);
            ps.setString(5, ysjc.zxbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������Ԥ�㼶�δ�����¼
     * @param ysjcList Ԥ�㼶�δ����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList ysjcList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SF_DM_YSJC  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < ysjcList.size(); i++) {
                Ysjc ysjc = (Ysjc) ysjcList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡԤ�㼶�δ����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList Ԥ�㼶�δ����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList YsjcList = new ArrayList();
        String sql = "select YSJCDM,YSJCMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_YSJC " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ysjc Ysjc1 = new Ysjc();
                Ysjc1.setYsjcdm(rs.getString("YSJCDM"));
                Ysjc1.setYsjcmc(rs.getString("YSJCMC"));
                Ysjc1.setLrr(rs.getString("LRR"));
                Ysjc1.setLrrq(rs.getTimestamp("LRRQ"));
                Ysjc1.setZxbs(rs.getString("ZXBS"));
                YsjcList.add(Ysjc1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return YsjcList;
    }

    /**
     * ����������ȡԤ�㼶�δ����ֵ����
     * @param ysjc Ԥ�㼶�δ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @returnԤ�㼶�δ����ֵ����
     * @throws SQLException
     */
    public static Object get(Ysjc ysjc, Connection conn) throws SQLException {
        Ysjc Ysjc1 = new Ysjc();
        String sql =
                "select YSJCDM,YSJCMC,LRR,LRRQ,ZXBS from DMDB.SF_DM_YSJC   where ysjcdm='" +
                ysjc.ysjcdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ysjc1.setYsjcdm(rs.getString("YSJCDM"));
                Ysjc1.setYsjcmc(rs.getString("YSJCMC"));
                Ysjc1.setLrr(rs.getString("LRR"));
                Ysjc1.setLrrq(rs.getTimestamp("LRRQ"));
                Ysjc1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Ysjc1;
    }


}
