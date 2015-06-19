package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.ttsoft.common.util.Debug;


/**
 * �Ǹ�����ϢDAO
 */
public class FgrxxDAO extends BaseDAO {

    /**
     * ����һ���Ǹ�����Ϣ��¼
     * @param fgrxx �Ǹ�����Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Fgrxx fgrxx, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_FGRXX (SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ,CQRLX) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, fgrxx.sbbh);
            //���������
            ps.setString(2, fgrxx.jsjdm);
            //��˰������
            ps.setString(3, fgrxx.nsrmc);
            //�������д���
            ps.setString(4, fgrxx.khyhdm);
            //������������
            ps.setString(5, fgrxx.khyhmc);
            //�����˺�
            ps.setString(6, fgrxx.yhzh);
            //��ϵ������
            ps.setString(7, fgrxx.lxrxm);
            //��ϵ�绰
            ps.setString(8, fgrxx.lxdh);
            //��˰�����ʹ���
            ps.setString(9, fgrxx.nsrlxdm);
            //��˰����������
            ps.setString(10, fgrxx.nsrlxmc);
            //���ݽ�����ʶ
            ps.setString(11, fgrxx.fwjhbs);
            //¼����
            ps.setString(12, fgrxx.lrr);
            //¼������
            ps.setTimestamp(13, fgrxx.lrrq);
            //������
            ps.setString(14, fgrxx.cjr);
            //��������
            ps.setTimestamp(15, fgrxx.cjrq);
            //��Ȩ������
            ps.setString(16, fgrxx.cqrlx);

            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���Ǹ�����Ϣ��¼
     * @param fgrxx �Ǹ�����Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Fgrxx fgrxx, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_FGRXX set NSRMC=?,KHYHDM=?,KHYHMC=?,YHZH=?,LXRXM=?,LXDH=?,NSRLXDM=?,NSRLXMC=?,FWJHBS=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fgrxx.nsrmc);
            ps.setString(2, fgrxx.khyhdm);
            ps.setString(3, fgrxx.khyhmc);
            ps.setString(4, fgrxx.yhzh);
            ps.setString(5, fgrxx.lxrxm);
            ps.setString(6, fgrxx.lxdh);
            ps.setString(7, fgrxx.nsrlxdm);
            ps.setString(8, fgrxx.nsrlxmc);
            ps.setString(9, fgrxx.fwjhbs);
            ps.setString(10, fgrxx.lrr);
            ps.setTimestamp(11, fgrxx.lrrq);
            ps.setString(12, fgrxx.cjr);
            ps.setTimestamp(13, fgrxx.cjrq);
            ps.setString(14, fgrxx.sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������Ǹ�����Ϣ��¼
     * @param fgrxxList �Ǹ�����Ϣֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList fgrxxList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_FGRXX  where sbbh = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < fgrxxList.size(); i++) {
                Fgrxx fgrxx = (Fgrxx) fgrxxList.get(i);
                ps.setString(1, fgrxx.sbbh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ�Ǹ�����Ϣֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �Ǹ�����Ϣֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList FgrxxList = new ArrayList();
        String sql = "select SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_FGRXX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fgrxx Fgrxx1 = new Fgrxx();
                Fgrxx1.setSbbh(rs.getString("SBBH"));
                Fgrxx1.setJsjdm(rs.getString("JSJDM"));
                Fgrxx1.setNsrmc(rs.getString("NSRMC"));
                Fgrxx1.setKhyhdm(rs.getString("KHYHDM"));
                Fgrxx1.setKhyhmc(rs.getString("KHYHMC"));
                Fgrxx1.setYhzh(rs.getString("YHZH"));
                Fgrxx1.setLxrxm(rs.getString("LXRXM"));
                Fgrxx1.setLxdh(rs.getString("LXDH"));
                Fgrxx1.setNsrlxdm(rs.getString("NSRLXDM"));
                Fgrxx1.setNsrlxmc(rs.getString("NSRLXMC"));
                Fgrxx1.setFwjhbs(rs.getString("FWJHBS"));
                Fgrxx1.setLrr(rs.getString("LRR"));
                Fgrxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Fgrxx1.setCjr(rs.getString("CJR"));
                Fgrxx1.setCjrq(rs.getTimestamp("CJRQ"));
                FgrxxList.add(Fgrxx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return FgrxxList;
    }

    /**
     * ����������ȡ�Ǹ�����Ϣֵ����
     * @param fgrxx �Ǹ�����Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�Ǹ�����Ϣֵ����
     * @throws SQLException
     */
    public static Object get(Fgrxx fgrxx, Connection conn) throws SQLException {
        Fgrxx Fgrxx1 = new Fgrxx();
        String sql = "select SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_FGRXX   where jsjdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fgrxx.jsjdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Fgrxx1.setSbbh(rs.getString("SBBH"));
                Fgrxx1.setJsjdm(rs.getString("JSJDM"));
                Fgrxx1.setNsrmc(rs.getString("NSRMC"));
                Fgrxx1.setKhyhdm(rs.getString("KHYHDM"));
                Fgrxx1.setKhyhmc(rs.getString("KHYHMC"));
                Fgrxx1.setYhzh(rs.getString("YHZH"));
                Fgrxx1.setLxrxm(rs.getString("LXRXM"));
                Fgrxx1.setLxdh(rs.getString("LXDH"));
                Fgrxx1.setNsrlxdm(rs.getString("NSRLXDM"));
                Fgrxx1.setNsrlxmc(rs.getString("NSRLXMC"));
                Fgrxx1.setFwjhbs(rs.getString("FWJHBS"));
                Fgrxx1.setLrr(rs.getString("LRR"));
                Fgrxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Fgrxx1.setCjr(rs.getString("CJR"));
                Fgrxx1.setCjrq(rs.getTimestamp("CJRQ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Fgrxx1;
    }

    /**
     * �����걨��Ų�ѯ�Ǹ�����Ϣ
     * @param sbbh String
     * @param conn Connection
     * @return Object
     */
    public static Object getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Fgrxx Fgrxx1 = new Fgrxx();
        String sql = "select SBBH,JSJDM,NSRMC,KHYHDM,KHYHMC,YHZH,LXRXM,LXDH,NSRLXDM,NSRLXMC,FWJHBS,LRR,LRRQ,CJR,CJRQ from QSDB.QS_JL_FGRXX  where sbbh = ? ";
        Debug.out("GrxxDAO.getBySbbh sql: " + sql + " sbbh = " + sbbh);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            if (rs.next()) {
                Fgrxx1.setSbbh(rs.getString("SBBH"));
                Fgrxx1.setJsjdm(rs.getString("JSJDM"));
                Fgrxx1.setNsrmc(rs.getString("NSRMC"));
                Fgrxx1.setKhyhdm(rs.getString("KHYHDM"));
                Fgrxx1.setKhyhmc(rs.getString("KHYHMC"));
                Fgrxx1.setYhzh(rs.getString("YHZH"));
                Fgrxx1.setLxrxm(rs.getString("LXRXM"));
                Fgrxx1.setLxdh(rs.getString("LXDH"));
                Fgrxx1.setNsrlxdm(rs.getString("NSRLXDM"));
                Fgrxx1.setNsrlxmc(rs.getString("NSRLXMC"));
                Fgrxx1.setFwjhbs(rs.getString("FWJHBS"));
                Fgrxx1.setLrr(rs.getString("LRR"));
                Fgrxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Fgrxx1.setCjr(rs.getString("CJR"));
                Fgrxx1.setCjrq(rs.getTimestamp("CJRQ"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return Fgrxx1;
    }

    /**
     * ����һ��������Ϣ��¼
     * @param grxx ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateFwjhbs(String fwjhbs, String sbbh, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_FGRXX set FWJHBS = ?   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fwjhbs);
            ps.setString(2, sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }


}
