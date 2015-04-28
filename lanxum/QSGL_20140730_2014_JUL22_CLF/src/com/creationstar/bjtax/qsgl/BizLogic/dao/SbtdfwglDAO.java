package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;


/**
 * �걨���������ء�������Ϣ��Ĺ�����DAO
 */
public class SbtdfwglDAO extends BaseDAO {

    /**
     * ����һ���걨���������ء�������Ϣ��Ĺ������¼
     * @param sbtdfwgl �걨���������ء�������Ϣ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Sbtdfwgl sbtdfwgl, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_SBTDFWGL (SBBH,TDFWID,LRR,CJR,CJRQ,LRRQ) values (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, sbtdfwgl.sbbh);
            //���ء�����Ψһ��ʶ
            ps.setString(2, sbtdfwgl.tdfwid);
            //¼����
            ps.setString(3, sbtdfwgl.lrr);
            //������
            ps.setString(4, sbtdfwgl.cjr);
            //��������
            ps.setTimestamp(5, sbtdfwgl.cjrq);
            //¼������
            ps.setTimestamp(6, sbtdfwgl.lrrq);
            /*
             System.out.println("////////////////////////////////");
                         System.out.println("�걨���" + sbtdfwgl.sbbh);
                         System.out.println("���ء�����Id" + sbtdfwgl.tdfwid);
                         System.out.println("¼����" + sbtdfwgl.lrr);
                         System.out.println("������" + sbtdfwgl.cjr);
                         System.out.println("��������" +sbtdfwgl.cjrq);
                         System.out.println("¼������" + sbtdfwgl.lrrq);
             System.out.println("////////////////////////////////");
             */
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���걨���������ء�������Ϣ��Ĺ������¼
     * @param sbtdfwgl �걨���������ء�������Ϣ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Sbtdfwgl sbtdfwgl, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_SBTDFWGL set SBBH=?,TDFWID=?,LRR=?,CJR=?,CJRQ=?,LRRQ=?   where sbbh = ?  and tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbtdfwgl.sbbh);
            ps.setString(2, sbtdfwgl.tdfwid);
            ps.setString(3, sbtdfwgl.lrr);
            ps.setString(4, sbtdfwgl.cjr);
            ps.setTimestamp(5, sbtdfwgl.cjrq);
            ps.setTimestamp(6, sbtdfwgl.lrrq);
            ps.setString(7, sbtdfwgl.sbbh);
            ps.setString(8, sbtdfwgl.tdfwid);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������걨���������ء�������Ϣ��Ĺ������¼
     * @param sbtdfwglList �걨���������ء�������Ϣ��Ĺ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList sbtdfwglList, Connection conn) throws
            SQLException {
        String sql =
                "delete from  QSDB.QS_JL_SBTDFWGL  where sbbh = ?  and tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbtdfwglList.size(); i++) {
                Sbtdfwgl sbtdfwgl = (Sbtdfwgl) sbtdfwglList.get(i);
                ps.setString(1, sbtdfwgl.sbbh);
                ps.setString(2, sbtdfwgl.tdfwid);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ�걨���������ء�������Ϣ��Ĺ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �걨���������ء�������Ϣ��Ĺ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbtdfwglList = new ArrayList();
        String sql =
                "select SBBH,TDFWID,LRR,CJR,CJRQ,LRRQ from QSDB.QS_JL_SBTDFWGL " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbtdfwgl Sbtdfwgl1 = new Sbtdfwgl();
                Sbtdfwgl1.setSbbh(rs.getString("SBBH"));
                Sbtdfwgl1.setTdfwid(rs.getString("TDFWID"));
                Sbtdfwgl1.setLrr(rs.getString("LRR"));
                Sbtdfwgl1.setCjr(rs.getString("CJR"));
                Sbtdfwgl1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbtdfwgl1.setLrrq(rs.getTimestamp("LRRQ"));
                SbtdfwglList.add(Sbtdfwgl1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbtdfwglList;
    }

    /**
     * ����������ȡ�걨���������ء�������Ϣ��Ĺ�����ֵ����
     * @param sbtdfwgl �걨���������ء�������Ϣ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨���������ء�������Ϣ��Ĺ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Sbtdfwgl sbtdfwgl, Connection conn) throws
            SQLException {
        Sbtdfwgl Sbtdfwgl1 = new Sbtdfwgl();
        String sql = "select SBBH,TDFWID,LRR,CJR,CJRQ,LRRQ from QSDB.QS_JL_SBTDFWGL   where sbbh = ?  and tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbtdfwgl.sbbh);
            ps.setString(2, sbtdfwgl.tdfwid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbtdfwgl1.setSbbh(rs.getString("SBBH"));
                Sbtdfwgl1.setTdfwid(rs.getString("TDFWID"));
                Sbtdfwgl1.setLrr(rs.getString("LRR"));
                Sbtdfwgl1.setCjr(rs.getString("CJR"));
                Sbtdfwgl1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbtdfwgl1.setLrrq(rs.getTimestamp("LRRQ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbtdfwgl1;
    }

    /**
     * ���ݲ�ǨЭ������ȡ�걨��Ǩ��������
     * @param sbcqgl �걨�������Ǩ��Ĺ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨�������Ǩ��Ĺ�����ֵ����
     * @throws SQLException
     */
    public static ArrayList queryByTdfwid(String tdfwid, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "select SBBH,TDFWID,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_SBTDFWGL   where tdfwid = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tdfwid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
                sbtdfwgl.setSbbh(rs.getString("SBBH"));
                sbtdfwgl.setTdfwid(rs.getString("TDFWID"));
                sbtdfwgl.setLrr(rs.getString("LRR"));
                sbtdfwgl.setLrrq(rs.getTimestamp("LRRQ"));
                sbtdfwgl.setCjr(rs.getString("CJR"));
                sbtdfwgl.setCjrq(rs.getTimestamp("CJRQ"));
                list.add(sbtdfwgl);
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
