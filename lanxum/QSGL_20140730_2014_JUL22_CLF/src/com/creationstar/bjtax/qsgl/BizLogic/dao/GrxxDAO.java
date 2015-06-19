package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.ttsoft.common.util.Debug;


/**
 * ������ϢDAO
 */
public class GrxxDAO extends BaseDAO {

    /**
     * ����һ��������Ϣ��¼
     * @param grxx ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(List nsrList, Connection conn) throws
            SQLException {
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx grxx = (Grxx) nsrList.get(i);
            insert(grxx, conn);
        }

    }

    /**
     * ����һ��������Ϣ��¼
     * @param grxx ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Grxx grxx, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_GRXX (SBBH,JSJDM,NSRMC,SFZJLX,SFZJLXMC,SFZJHM,LXDH,FWJHBS,LRR,LRRQ,CJR,CJRQ,GJDM,GJMC,CQRLX) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, grxx.sbbh);
            //���������
            ps.setString(2, grxx.jsjdm);
            //��˰������
            ps.setString(3, grxx.nsrmc);
            //֤������
            ps.setString(4, grxx.sfzjlx);
            //null
            ps.setString(5, grxx.sfzjlxmc);
            //֤������
            ps.setString(6, grxx.sfzjhm);
            //��ϵ�绰
            ps.setString(7, grxx.lxdh);
            //���ݽ�����ʶ
            ps.setString(8, grxx.fwjhbs);
            //¼����
            ps.setString(9, grxx.lrr);
            //¼������
            ps.setTimestamp(10, grxx.lrrq);
            //������
            ps.setString(11, grxx.cjr);
            //��������
            ps.setTimestamp(12, grxx.cjrq);
            //��������
            ps.setString(13, grxx.gjdm);
            //��������
            ps.setString(14, grxx.gjmc);
            //��Ȩ������
            ps.setString(15, grxx.cqrlx);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            Debug.printException(e);
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��������Ϣ��¼
     * @param grxx ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Grxx grxx, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_GRXX set NSRMC=?,SFZJLX=?,SFZJLXMC=?,SFZJHM=?,LXDH=?,FWJHBS=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,GJDM=?,GJMC=?,CQRLX=?   where jsjdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, grxx.nsrmc);
            ps.setString(2, grxx.sfzjlx);
            ps.setString(3, grxx.sfzjlxmc);
            ps.setString(4, grxx.sfzjhm);
            ps.setString(5, grxx.lxdh);
            ps.setString(6, grxx.fwjhbs);
            ps.setString(7, grxx.lrr);
            ps.setTimestamp(8, grxx.lrrq);
            ps.setString(9, grxx.cjr);
            ps.setTimestamp(10, grxx.cjrq);
            ps.setString(11, grxx.jsjdm);
            ps.setString(12, grxx.gjdm);
            ps.setString(13, grxx.gjmc);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��������Ϣ��¼
     * @param grxx ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateFwjhbs(String fwjhbs, String sbbh, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_GRXX set FWJHBS = ?   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, fwjhbs);
            ps.setString(2, sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����sbbhɾ������������Ϣ��¼
     * @param grxxList ������Ϣֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(String sbbh, Connection conn) throws SQLException {
        String sql = "delete from  QSDB.QS_JL_GRXX  where sbbh = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * ɾ������������Ϣ��¼
     * @param grxxList ������Ϣֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList grxxList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_GRXX  where sbbh = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < grxxList.size(); i++) {
                Grxx grxx = (Grxx) grxxList.get(i);
                ps.setString(1, grxx.sbbh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * ����������ȡ������Ϣֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������Ϣֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList GrxxList = new ArrayList();
        String sql = "select SBBH,JSJDM,NSRMC,GJDM,GJMC,SFZJLX,SFZJLXMC,SFZJHM,LXDH,FWJHBS,LRR,LRRQ,CJR,CJRQ,CQRLX from QSDB.QS_JL_GRXX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grxx Grxx1 = new Grxx();
                Grxx1.setSbbh(rs.getString("SBBH"));
                Grxx1.setJsjdm(rs.getString("JSJDM"));
                Grxx1.setNsrmc(rs.getString("NSRMC"));
                Grxx1.setSfzjlx(rs.getString("SFZJLX"));
                Grxx1.setSfzjlxmc(rs.getString("SFZJLXMC"));
                Grxx1.setSfzjhm(rs.getString("SFZJHM"));
                Grxx1.setLxdh(rs.getString("LXDH"));
                Grxx1.setFwjhbs(rs.getString("FWJHBS"));
                Grxx1.setLrr(rs.getString("LRR"));
                Grxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Grxx1.setCjr(rs.getString("CJR"));
                Grxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Grxx1.setCjr(rs.getString("GJDM"));
                Grxx1.setCjr(rs.getString("GJMC"));
                Grxx1.setCqrlx(rs.getString("CQRLX"));
                GrxxList.add(Grxx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return GrxxList;
    }

    /**
     * ����������ȡ������Ϣֵ����
     * @param grxx ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @return������Ϣֵ����
     * @throws SQLException
     */
    public static Object get(Grxx grxx, Connection conn) throws SQLException {
        Grxx Grxx1 = new Grxx();
        String sql = "select SBBH,JSJDM,NSRMC,GJDM,GJMC,SFZJLX,SFZJLXMC,SFZJHM,LXDH,FWJHBS,LRR,LRRQ,CJR,CJRQ,CQRLX from QSDB.QS_JL_GRXX   where jsjdm = ? ";
        Debug.out("GrxxDAO.get sql: " + sql + " jsjdm = " + grxx.jsjdm);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, grxx.jsjdm);
            rs = ps.executeQuery();
            if (rs.next()) {
                Debug.out("GrxxDao exist grxx data...");
                Grxx1.setSbbh(rs.getString("SBBH"));
                Grxx1.setJsjdm(rs.getString("JSJDM"));
                Grxx1.setNsrmc(rs.getString("NSRMC"));
                Grxx1.setSfzjlx(rs.getString("SFZJLX"));
                Grxx1.setSfzjlxmc(rs.getString("SFZJLXMC"));
                Grxx1.setSfzjhm(rs.getString("SFZJHM"));
                Grxx1.setLxdh(rs.getString("LXDH"));
                Grxx1.setFwjhbs(rs.getString("FWJHBS"));
                Grxx1.setLrr(rs.getString("LRR"));
                Grxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Grxx1.setCjr(rs.getString("CJR"));
                Grxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Grxx1.setGjdm(rs.getString("GJDM"));
                Grxx1.setGjmc(rs.getString("GJMC"));
                Grxx1.setCqrlx(rs.getString("CQRLX"));
                Debug.out("GrxxDAO grxx nsrmc: " + Grxx1.getNsrmc());
                Debug.out("GrxxDAO grxx sfzjlxmc: " + Grxx1.getSfzjlxmc());
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
        return Grxx1;
    }

    /**
     * �����걨��Ų�ѯ������Ϣ������һ��
     * @param sbbh String
     * @param conn Connection
     * @return Object
     */
    public static Object getOneBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Grxx Grxx1 = new Grxx();
        String sql = "select SBBH,JSJDM,NSRMC,GJDM,GJMC,SFZJLX,SFZJLXMC,SFZJHM,LXDH,FWJHBS,LRR,LRRQ,CJR,CJRQ,CQRLX from QSDB.QS_JL_GRXX   where sbbh = ? order by cqrlx asc";
        Debug.out("GrxxDAO.getBySbbh sql: " + sql + " sbbh = " + sbbh);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            if (rs.next()) {
                Debug.out("GrxxDao exist grxx data...");
                Grxx1 = new Grxx();
                Grxx1.setSbbh(rs.getString("SBBH"));
                Grxx1.setJsjdm(rs.getString("JSJDM"));
                Grxx1.setNsrmc(rs.getString("NSRMC"));
                Grxx1.setSfzjlx(rs.getString("SFZJLX"));
                Grxx1.setSfzjlxmc(rs.getString("SFZJLXMC"));
                Grxx1.setSfzjhm(rs.getString("SFZJHM"));
                Grxx1.setLxdh(rs.getString("LXDH"));
                Grxx1.setFwjhbs(rs.getString("FWJHBS"));
                Grxx1.setLrr(rs.getString("LRR"));
                Grxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Grxx1.setCjr(rs.getString("CJR"));
                Grxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Grxx1.setGjdm(rs.getString("GJDM"));
                Grxx1.setGjmc(rs.getString("GJMC"));
                Grxx1.setCqrlx(rs.getString("CQRLX"));
                Debug.out("GrxxDAO grxx nsrmc: " + Grxx1.getNsrmc());
                Debug.out("GrxxDAO grxx sfzjlxmc: " + Grxx1.getSfzjlxmc());
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
        return Grxx1;
    }

    /**
     * �����걨��Ų�ѯ������Ϣ
     * @param sbbh String
     * @param conn Connection
     * @return Object
     */
    public static Object getAllBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Grxx Grxx1 = new Grxx();
        List l = new ArrayList();
        String sql = "select SBBH,JSJDM,NSRMC,GJDM,GJMC,SFZJLX,SFZJLXMC,SFZJHM,LXDH,FWJHBS,LRR,LRRQ,CJR,CJRQ,CQRLX from QSDB.QS_JL_GRXX   where sbbh = ? order by cqrlx asc";
        Debug.out("GrxxDAO.getBySbbh sql: " + sql + " sbbh = " + sbbh);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            while (rs.next()) {
                Debug.out("GrxxDao exist grxx data...");
                Grxx1 = new Grxx();
                Grxx1.setSbbh(rs.getString("SBBH"));
                Grxx1.setJsjdm(rs.getString("JSJDM"));
                Grxx1.setNsrmc(rs.getString("NSRMC"));
                Grxx1.setSfzjlx(rs.getString("SFZJLX"));
                Grxx1.setSfzjlxmc(rs.getString("SFZJLXMC"));
                Grxx1.setSfzjhm(rs.getString("SFZJHM"));
                Grxx1.setLxdh(rs.getString("LXDH"));
                Grxx1.setFwjhbs(rs.getString("FWJHBS"));
                Grxx1.setLrr(rs.getString("LRR"));
                Grxx1.setLrrq(rs.getTimestamp("LRRQ"));
                Grxx1.setCjr(rs.getString("CJR"));
                Grxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Grxx1.setGjdm(rs.getString("GJDM"));
                Grxx1.setGjmc(rs.getString("GJMC"));
                Grxx1.setCqrlx(rs.getString("CQRLX"));
                Debug.out("GrxxDAO grxx nsrmc: " + Grxx1.getNsrmc());
                Debug.out("GrxxDAO grxx sfzjlxmc: " + Grxx1.getSfzjlxmc());
                l.add(Grxx1);
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
        return l;
    }

    /**
     * �����걨��Ų�ѯ������Ϣ��˰������
     * @param sbbh String
     * @param conn Connection
     * @return Object
     */
    public static String getNsrmcBySbbh(String sbbh, Connection conn) throws
            SQLException {
        String sql =
                "select NSRMC from QSDB.QS_JL_GRXX   where sbbh = ? order by cqrlx asc";
        //Debug.out("GrxxDAO.getNSRMCBySbbh sql: " + sql + " sbbh = " + sbbh);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String nsrmc = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            StringBuffer s = new StringBuffer("");
            String sepl = ",";
            while (rs.next()) {
                nsrmc = rs.getString("NSRMC");
                s.append(sepl + nsrmc);
            }
            nsrmc = s.toString();
            nsrmc = nsrmc.substring(1);
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
        return nsrmc;
    }

}
