package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;


/**
 * ������������˰���������ϢDAO
 */
public class SpjgxxDAO extends BaseDAO {

    /**
     * ����һ��������������˰���������Ϣ��¼
     * @param spjgxx ������������˰���������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Spjgxx spjgxx, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_SPJGXX (SBBH,JMSJE,HDTZSZH,JMLYDM,LRR,LRRQ,CJR,CJRQ,BZ) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, spjgxx.sbbh);
            //����˰���
            ps.setBigDecimal(2, spjgxx.jmsje);
            //�˶�֪ͨ���ֺ�
            ps.setString(3, spjgxx.hdtzszh);
            //�������ɴ���
            ps.setString(4, spjgxx.jmlydm);
            //¼����
            ps.setString(5, spjgxx.lrr);
            //¼������
            ps.setTimestamp(6, spjgxx.lrrq);
            //������
            ps.setString(7, spjgxx.cjr);
            //��������
            ps.setTimestamp(8, spjgxx.cjrq);
            //��ע
            ps.setString(9, spjgxx.bz);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��������������˰���������Ϣ��¼
     * @param spjgxx ������������˰���������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Spjgxx spjgxx, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_SPJGXX set SBBH=?,JMSJE=?,HDTZSZH=?,JMLYDM=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,BZ=?   where hdtzszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, spjgxx.sbbh);
            ps.setBigDecimal(2, spjgxx.jmsje);
            ps.setString(3, spjgxx.hdtzszh);
            ps.setString(4, spjgxx.jmlydm);
            ps.setString(5, spjgxx.lrr);
            ps.setTimestamp(6, spjgxx.lrrq);
            ps.setString(7, spjgxx.cjr);
            ps.setTimestamp(8, spjgxx.cjrq);
            ps.setString(9, spjgxx.bz);
            ps.setString(10, spjgxx.hdtzszh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������������������˰���������Ϣ��¼
     * @param spjgxxList ������������˰���������Ϣֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList spjgxxList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_SPJGXX  where hdtzszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < spjgxxList.size(); i++) {
                Spjgxx spjgxx = (Spjgxx) spjgxxList.get(i);
                ps.setString(1, spjgxx.hdtzszh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ������������˰���������Ϣֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������˰���������Ϣֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SpjgxxList = new ArrayList();
        String sql =
                "select SBBH,JMSJE,HDTZSZH,JMLYDM,LRR,LRRQ,CJR,CJRQ,BZ from QSDB.QS_JL_SPJGXX " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Spjgxx Spjgxx1 = new Spjgxx();
                Spjgxx1.setSbbh(rs.getString("SBBH"));
                Spjgxx1.setJmsje(rs.getBigDecimal("JMSJE"));
                Spjgxx1.setHdtzszh(rs.getString("HDTZSZH"));
                Spjgxx1.setJmlydm(rs.getString("JMLYDM"));
                Spjgxx1.setLrr(rs.getString("LRR"));
                Spjgxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Spjgxx1.setCjr(rs.getString("CJR"));
                Spjgxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Spjgxx1.setBz(rs.getString("BZ"));
                SpjgxxList.add(Spjgxx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SpjgxxList;
    }

    /**
     * ����������ȡ������������˰���������Ϣֵ����
     * @param spjgxx ������������˰���������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @return������������˰���������Ϣֵ����
     * @throws SQLException
     */
    public static Object get(Spjgxx spjgxx, Connection conn) throws
            SQLException {
        Spjgxx Spjgxx1 = new Spjgxx();
        String sql = "select SBBH,JMSJE,HDTZSZH,JMLYDM,LRR,LRRQ,CJR,CJRQ,BZ from QSDB.QS_JL_SPJGXX   where hdtzszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, spjgxx.hdtzszh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Spjgxx1.setSbbh(rs.getString("SBBH"));
                Spjgxx1.setJmsje(rs.getBigDecimal("JMSJE"));
                Spjgxx1.setHdtzszh(rs.getString("HDTZSZH"));
                Spjgxx1.setJmlydm(rs.getString("JMLYDM"));
                Spjgxx1.setLrr(rs.getString("LRR"));
                Spjgxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Spjgxx1.setCjr(rs.getString("CJR"));
                Spjgxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Spjgxx1.setBz(rs.getString("BZ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Spjgxx1;
    }

    /**
     * ����������ȡ������������˰���������Ϣֵ����
     * @param spjgxx ������������˰���������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @return������������˰���������Ϣֵ����
     * @throws SQLException
     */
    public static Object getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Spjgxx Spjgxx1 = null;
        String sql = "select SBBH,JMSJE,HDTZSZH,JMLYDM,LRR,LRRQ,CJR,CJRQ,BZ from QSDB.QS_JL_SPJGXX   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Spjgxx1 = new Spjgxx();
                Spjgxx1.setSbbh(rs.getString("SBBH"));
                Spjgxx1.setJmsje(rs.getBigDecimal("JMSJE"));
                Spjgxx1.setHdtzszh(rs.getString("HDTZSZH"));
                Spjgxx1.setJmlydm(rs.getString("JMLYDM"));
                Spjgxx1.setLrr(rs.getString("LRR"));
                Spjgxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Spjgxx1.setCjr(rs.getString("CJR"));
                Spjgxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Spjgxx1.setBz(rs.getString("BZ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return Spjgxx1;
    }


}
