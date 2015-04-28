package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbgyzf;


/**
 * �걨�������ѹ�����ס�����������Ĺ�����DAO
 */
public class SbgyzfDAO extends BaseDAO {

    /**
     * ����һ���걨�������ѹ�����ס�����������Ĺ������¼
     * @param sbgyzf �걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Sbgyzf sbgyzf, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_SBGYZF (SBBH,YGGYZFQSZSH,LRR,CJR,CJRQ,LRRQ,BCDKE) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, sbgyzf.sbbh);
            //�ѹ�����ס��Ȩ��֤���
            ps.setString(2, sbgyzf.yggyzfqszsh);
            //¼����
            ps.setString(3, sbgyzf.lrr);
            //������
            ps.setString(4, sbgyzf.cjr);
            //��������
            ps.setTimestamp(5, sbgyzf.cjrq);
            //¼������
            ps.setTimestamp(6, sbgyzf.lrrq);
            //���εֿ۶�
            ps.setBigDecimal(7, sbgyzf.bcdke);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���걨�������ѹ�����ס�����������Ĺ������¼
     * @param sbgyzf �걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Sbgyzf sbgyzf, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_SBGYZF set SBBH=?,YGGYZFQSZSH=?,LRR=?,CJR=?,CJRQ=?,LRRQ=?,BCDKE=?   where sbbh = ?  and yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbgyzf.sbbh);
            ps.setString(2, sbgyzf.yggyzfqszsh);
            ps.setString(3, sbgyzf.lrr);
            ps.setString(4, sbgyzf.cjr);
            ps.setTimestamp(5, sbgyzf.cjrq);
            ps.setTimestamp(6, sbgyzf.lrrq);
            ps.setBigDecimal(7, sbgyzf.bcdke);
            ps.setString(8, sbgyzf.sbbh);
            ps.setString(9, sbgyzf.yggyzfqszsh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������걨�������ѹ�����ס�����������Ĺ������¼
     * @param sbgyzfList �걨�������ѹ�����ס�����������Ĺ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList sbgyzfList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  QSDB.QS_JL_SBGYZF  where sbbh = ?  and yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbgyzfList.size(); i++) {
                Sbgyzf sbgyzf = (Sbgyzf) sbgyzfList.get(i);
                ps.setString(1, sbgyzf.sbbh);
                ps.setString(2, sbgyzf.yggyzfqszsh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ�걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �걨�������ѹ�����ס�����������Ĺ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbgyzfList = new ArrayList();
        String sql =
                "select SBBH,YGGYZFQSZSH,LRR,CJR,CJRQ,LRRQ,BCDKE from QSDB.QS_JL_SBGYZF " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbgyzf Sbgyzf1 = new Sbgyzf();
                Sbgyzf1.setSbbh(rs.getString("SBBH"));
                Sbgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Sbgyzf1.setLrr(rs.getString("LRR"));
                Sbgyzf1.setCjr(rs.getString("CJR"));
                Sbgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbgyzf1.setBcdke(rs.getBigDecimal("BCDKE"));
                SbgyzfList.add(Sbgyzf1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbgyzfList;
    }

    /**
     * ����������ȡ�걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @param sbgyzf �걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Sbgyzf sbgyzf, Connection conn) throws
            SQLException {
        Sbgyzf Sbgyzf1 = new Sbgyzf();
        String sql = "select SBBH,YGGYZFQSZSH,LRR,CJR,CJRQ,LRRQ,BCDKE from QSDB.QS_JL_SBGYZF   where sbbh = ?  and yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbgyzf.sbbh);
            ps.setString(2, sbgyzf.yggyzfqszsh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbgyzf1.setSbbh(rs.getString("SBBH"));
                Sbgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Sbgyzf1.setLrr(rs.getString("LRR"));
                Sbgyzf1.setCjr(rs.getString("CJR"));
                Sbgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbgyzf1.setBcdke(rs.getBigDecimal("BCDKE"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbgyzf1;
    }

    /**
     * ���ݺ�ͬ�Ż�ȡ�걨����ס��������Ϣ
     * @param sbgyzf �걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨�������ѹ�����ס�����������Ĺ�����ֵ����
     * @throws SQLException
     */
    public static ArrayList queryByHth(String hth, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "select SBBH,YGGYZFQSZSH,LRR,CJR,CJRQ,LRRQ,BCDKE from QSDB.QS_JL_SBGYZF   where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hth);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sbgyzf Sbgyzf1 = new Sbgyzf();
                Sbgyzf1.setSbbh(rs.getString("SBBH"));
                Sbgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Sbgyzf1.setLrr(rs.getString("LRR"));
                Sbgyzf1.setCjr(rs.getString("CJR"));
                Sbgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbgyzf1.setBcdke(rs.getBigDecimal("BCDKE"));
                list.add(Sbgyzf1);
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
