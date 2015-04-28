package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbcqgl;


/**
 * �걨�������Ǩ��Ĺ�����DAO
 */
public class SbcqglDAO extends BaseDAO {

    /**
     * ����һ���걨�������Ǩ��Ĺ������¼
     * @param sbcqgl �걨�������Ǩ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Sbcqgl sbcqgl, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_SBCQGL (SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, sbcqgl.sbbh);
            //��ǨЭ�����
            ps.setString(2, sbcqgl.cqxyh);
            //����ʹ�ò�����
            ps.setBigDecimal(3, sbcqgl.bcsybce);
            //¼����
            ps.setString(4, sbcqgl.lrr);
            //¼������
            ps.setTimestamp(5, sbcqgl.lrrq);
            //������
            ps.setString(6, sbcqgl.cjr);
            //��������
            ps.setTimestamp(7, sbcqgl.cjrq);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���걨�������Ǩ��Ĺ������¼
     * @param sbcqgl �걨�������Ǩ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Sbcqgl sbcqgl, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_SBCQGL set SBBH=?,CQXYH=?,BCSYBCE=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?   where cqxyh = ?  and sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbcqgl.sbbh);
            ps.setString(2, sbcqgl.cqxyh);
            ps.setBigDecimal(3, sbcqgl.bcsybce);
            ps.setString(4, sbcqgl.lrr);
            ps.setTimestamp(5, sbcqgl.lrrq);
            ps.setString(6, sbcqgl.cjr);
            ps.setTimestamp(7, sbcqgl.cjrq);
            ps.setString(8, sbcqgl.cqxyh);
            ps.setString(9, sbcqgl.sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������걨�������Ǩ��Ĺ������¼
     * @param sbcqglList �걨�������Ǩ��Ĺ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList sbcqglList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  QSDB.QS_JL_SBCQGL  where cqxyh = ?  and sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbcqglList.size(); i++) {
                Sbcqgl sbcqgl = (Sbcqgl) sbcqglList.get(i);
                ps.setString(1, sbcqgl.cqxyh);
                ps.setString(2, sbcqgl.sbbh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ�걨�������Ǩ��Ĺ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �걨�������Ǩ��Ĺ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbcqglList = new ArrayList();
        String sql =
                "select SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBCQGL " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbcqgl Sbcqgl1 = new Sbcqgl();
                Sbcqgl1.setSbbh(rs.getString("SBBH"));
                Sbcqgl1.setCqxyh(rs.getString("CQXYH"));
                Sbcqgl1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                Sbcqgl1.setLrr(rs.getString("LRR"));
                Sbcqgl1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbcqgl1.setCjr(rs.getString("CJR"));
                Sbcqgl1.setCjrq(rs.getTimestamp("CJRQ"));
                SbcqglList.add(Sbcqgl1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbcqglList;
    }

    /**
     * ����������ȡ�걨�������Ǩ��Ĺ�����ֵ����
     * @param sbcqgl �걨�������Ǩ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨�������Ǩ��Ĺ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Sbcqgl sbcqgl, Connection conn) throws
            SQLException {
        Sbcqgl Sbcqgl1 = null;
        String sql = "select SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBCQGL   where cqxyh = ?  and sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbcqgl.cqxyh);
            ps.setString(2, sbcqgl.sbbh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbcqgl1 = new Sbcqgl();
                Sbcqgl1.setSbbh(rs.getString("SBBH"));
                Sbcqgl1.setCqxyh(rs.getString("CQXYH"));
                Sbcqgl1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                Sbcqgl1.setLrr(rs.getString("LRR"));
                Sbcqgl1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbcqgl1.setCjr(rs.getString("CJR"));
                Sbcqgl1.setCjrq(rs.getTimestamp("CJRQ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbcqgl1;
    }

    /**
     * ���ݲ�ǨЭ������ȡ�걨��Ǩ��������
     * @param sbcqgl �걨�������Ǩ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨�������Ǩ��Ĺ�����ֵ����
     * @throws SQLException
     */
    public static ArrayList queryByCqxyh(String cqxyh, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "select SBBH,CQXYH,BCSYBCE,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBCQGL   where cqxyh = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cqxyh);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sbcqgl Sbcqgl1 = new Sbcqgl();
                Sbcqgl1.setSbbh(rs.getString("SBBH"));
                Sbcqgl1.setCqxyh(rs.getString("CQXYH"));
                Sbcqgl1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                Sbcqgl1.setLrr(rs.getString("LRR"));
                Sbcqgl1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbcqgl1.setCjr(rs.getString("CJR"));
                Sbcqgl1.setCjrq(rs.getTimestamp("CJRQ"));
                list.add(Sbcqgl1);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return list;
    }


}
