package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;

public class CqxxbDAO extends BaseDAO {
    /**
     * ���Ǩ��Ϣ�����������
     *
     * @param cqxxb
     *            Cqxxb
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void insert(Cqxxb cqxxb, Connection conn) throws SQLException {
        String sql = "INSERT INTO QSDB.QS_JL_CQXXB "
                     +
                     "(CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                     +
                "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                     +
                "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS) "
                     +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // '��Ǩ������'
            ps.setString(1, cqxxb.getCqrmc());
            // '��Ǩ��Χ'
            ps.setString(2, cqxxb.getCqfw());
            // '����Ǩ������'
            ps.setString(3, cqxxb.getBcqrmc());
            // '����Ǩ�����ʹ���'
            ps.setString(4, cqxxb.getBcqrlxdm());
            // '����Ǩ����������'
            ps.setString(5, cqxxb.getBcqrlxmc());
            // '֤�����ʹ���'
            ps.setString(6, cqxxb.getZjlxdm());
            // '֤����������'
            ps.setString(7, cqxxb.getZjlxmc());
            // '֤������'
            ps.setString(8, cqxxb.getZjhm());
            // '��Ǩ��ϸ��ַ'
            ps.setString(9, cqxxb.getCqxxdz());
            // '�������'
            ps.setBigDecimal(10, cqxxb.getBcje());
            // '�������ʹ���'
            ps.setString(11, cqxxb.getBclxdm());
            // '������������'
            ps.setString(12, cqxxb.getBclxmc());
            // '�������'
            ps.setBigDecimal(13, cqxxb.getBcmj());
            // '�������ݵ�ַ'
            ps.setString(14, cqxxb.getBcfwdz());
            // '��Ǩ���֤��'
            ps.setString(15, cqxxb.getCqxkzh());
            // '���ش���'
            ps.setString(16, cqxxb.getQxdm());
            // '¼����'
            ps.setString(17, cqxxb.getLrr());
            // '������'
            ps.setString(18, cqxxb.getCjr());
            // '¼������'
            ps.setTimestamp(19, cqxxb.getLrrq());
            // '��������'
            ps.setTimestamp(20, cqxxb.getCjrq());
            // '��Ǩ��Ϣ���'
            ps.setString(21, cqxxb.getCqxxbh());
            // '������Դ'
            ps.setString(22, cqxxb.getSjly());
            // '��������'
            ps.setString(23, cqxxb.getSzqx());
            // '˰�������֯��������'
            ps.setString(24, cqxxb.getSwjgzzjgdm());
            // '��Ǩ��Ŀ����'
            ps.setString(25, cqxxb.getCqxmmc());
            // '��Ǩ���'
            ps.setBigDecimal(26, cqxxb.getCqmj());
            // ��Ǩ���֤����ʱ��
            ps.setTimestamp(27, cqxxb.getCqxkzspsj());
            // ����������
            ps.setString(28, cqxxb.getGjrmc());
            // ��ʽ���ݼ���
            ps.setString(29, cqxxb.getZsfwjs());
            // ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ����¼
     *
     * @param bhList
     *            ArrayList ��Ǩ��Ϣ���
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void delete(ArrayList bhList, Connection conn) throws
            SQLException {
        String sql = "DELETE FROM QSDB.QS_JL_CQXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < bhList.size(); i++) {
                Cqxxb cqxxb = (Cqxxb) bhList.get(i);
                // String bh = (String)bhList.get(i);

                ps.setString(1, cqxxb.getCqxxbh());
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * ��ѯ��Ǩ��Ϣ
     *
     * @param condition
     *            String
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList rsList = new ArrayList();
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS from QSDB.QS_JL_CQXXB "
                + condition;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cqxxb cqxxb = new Cqxxb();
                cqxxb.setCqrmc(rs.getString("CQRMC")); // '��Ǩ������'
                cqxxb.setCqfw(rs.getString("CQFW")); // '��Ǩ��Χ'
                cqxxb.setBcqrmc(rs.getString("BCQRMC")); // '����Ǩ������'
                cqxxb.setBcqrlxdm(rs.getString("BCQRLXDM")); // '����Ǩ�����ʹ���'
                cqxxb.setBcqrlxmc(rs.getString("BCQRLXMC")); // '����Ǩ����������'
                cqxxb.setZjlxdm(rs.getString("ZJLXDM")); // '֤�����ʹ���'
                cqxxb.setZjlxmc(rs.getString("ZJLXMC")); // '֤����������'
                cqxxb.setZjhm(rs.getString("ZJHM")); // '֤������'
                cqxxb.setCqxxdz(rs.getString("CQXXDZ")); // '��Ǩ��ϸ��ַ'
                cqxxb.setBcje(rs.getBigDecimal("BCJE")); // '�������'
                cqxxb.setBclxdm(rs.getString("BCLXDM")); // '�������ʹ���'
                cqxxb.setBclxmc(rs.getString("BCLXMC")); // '������������'
                cqxxb.setBcmj(rs.getBigDecimal("BCMJ")); // '�������'
                cqxxb.setBcfwdz(rs.getString("BCFWDZ")); // '�������ݵ�ַ'
                cqxxb.setCqxkzh(rs.getString("CQXKZH")); // '��Ǩ���֤��'
                cqxxb.setQxdm(rs.getString("QXDM")); // '���ش���'
                cqxxb.setLrr(rs.getString("LRR")); // '¼����'
                cqxxb.setCjr(rs.getString("CJR")); // '������'
                cqxxb.setLrrq(rs.getTimestamp("LRRQ")); // '¼������'
                cqxxb.setCjrq(rs.getTimestamp("CJRQ")); // '��������'
                cqxxb.setCqxxbh(rs.getString("cqxxbh")); // '��Ǩ��Ϣ���'
                cqxxb.setSjly(rs.getString("SJLY")); // '������Դ'
                cqxxb.setSzqx(rs.getString("szqx")); // '��������'
                cqxxb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '˰�������֯��������'
                cqxxb.setCqxmmc(rs.getString("CQXMMC")); // '��Ǩ��Ŀ����'
                cqxxb.setCqmj(rs.getBigDecimal("CQMJ")); // '��Ǩ���'

                // ��Ǩ���֤����ʱ��
                cqxxb.setCqxkzspsj(rs.getTimestamp("CQXKZHSPSJ"));
                // ����������
                cqxxb.setGjrmc(rs.getString("GJRMC"));
                // ��ʽ���ݼ���
                cqxxb.setZsfwjs(rs.getString("ZSFWJS"));

                rsList.add(cqxxb);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return rsList;
    }

    /**
     * ��ѯ��Ǩ��Ϣ
     *
     * @param condition
     *            String
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static Cqxxb get(String cqxxbh, Connection conn) throws SQLException {
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS from QSDB.QS_JL_CQXXB where cqxxbh="
                + cqxxbh;
        // System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        Cqxxb cqxxb = new Cqxxb();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cqxxb.setCqrmc(rs.getString("CQRMC")); // '��Ǩ������'
                cqxxb.setCqfw(rs.getString("CQFW")); // '��Ǩ��Χ'
                cqxxb.setBcqrmc(rs.getString("BCQRMC")); // '����Ǩ������'
                cqxxb.setBcqrlxdm(rs.getString("BCQRLXDM")); // '����Ǩ�����ʹ���'
                cqxxb.setBcqrlxmc(rs.getString("BCQRLXMC")); // '����Ǩ����������'
                cqxxb.setZjlxdm(rs.getString("ZJLXDM")); // '֤�����ʹ���'
                cqxxb.setZjlxmc(rs.getString("ZJLXMC")); // '֤����������'
                cqxxb.setZjhm(rs.getString("ZJHM")); // '֤������'
                cqxxb.setCqxxdz(rs.getString("CQXXDZ")); // '��Ǩ��ϸ��ַ'
                cqxxb.setBcje(rs.getBigDecimal("BCJE")); // '�������'
                cqxxb.setBclxdm(rs.getString("BCLXDM")); // '�������ʹ���'
                cqxxb.setBclxmc(rs.getString("BCLXMC")); // '������������'
                cqxxb.setBcmj(rs.getBigDecimal("BCMJ")); // '�������'
                cqxxb.setBcfwdz(rs.getString("BCFWDZ")); // '�������ݵ�ַ'
                cqxxb.setCqxkzh(rs.getString("CQXKZH")); // '��Ǩ���֤��'
                cqxxb.setQxdm(rs.getString("QXDM")); // '���ش���'
                cqxxb.setLrr(rs.getString("LRR")); // '¼����'
                cqxxb.setCjr(rs.getString("CJR")); // '������'
                cqxxb.setLrrq(rs.getTimestamp("LRRQ")); // '¼������'
                cqxxb.setCjrq(rs.getTimestamp("CJRQ")); // '��������'
                cqxxb.setCqxxbh(rs.getString("cqxxbh")); // '��Ǩ��Ϣ���'
                cqxxb.setSjly(rs.getString("SJLY")); // '������Դ'
                cqxxb.setSzqx(rs.getString("szqx")); // '��������'
                cqxxb.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '˰�������֯��������'
                cqxxb.setCqxmmc(rs.getString("CQXMMC")); // '��Ǩ��Ŀ����'
                cqxxb.setCqmj(rs.getBigDecimal("CQMJ")); // '��Ǩ���'

                // ��Ǩ���֤����ʱ��
                cqxxb.setCqxkzspsj(rs.getTimestamp("CQXKZHSPSJ"));
                // ����������
                cqxxb.setGjrmc(rs.getString("GJRMC"));
                // ��ʽ���ݼ���
                cqxxb.setZsfwjs(rs.getString("ZSFWJS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return cqxxb;
    }

    /**
     * ���²�Ǩ��Ϣ
     *
     * @param cqxxb
     *            String ��Ǩ��Ϣvo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void update(Cqxxb cqxxb, Connection conn) throws SQLException {
        String sql = "update QSDB.QS_JL_CQXXB SET "
                     +
                     "CQRMC=?,CQFW=?,BCQRMC=?,BCQRLXDM=?,BCQRLXMC=?,ZJLXDM=?,"
                     +
                "ZJLXMC=?,ZJHM=?,CQXXDZ=?,BCJE=?,BCLXDM=?,BCLXMC=?,BCMJ=?,"
                     + "BCFWDZ=?,CQXKZH=?,QXDM=?,LRR=?,"
                     + "LRRQ=?,SZQX=?,CQXMMC=?,CQMJ=?,CQXKZHSPSJ=?,GJRMC=?,ZSFWJS=?,SWJGZZJGDM=? WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            // '��Ǩ������'
            ps.setString(1, cqxxb.getCqrmc());

            // '��Ǩ��Χ'
            ps.setString(2, cqxxb.getCqfw());

            // '����Ǩ������'
            ps.setString(3, cqxxb.getBcqrmc());

            // '����Ǩ�����ʹ���'
            ps.setString(4, cqxxb.getBcqrlxdm());

            // '����Ǩ����������'
            ps.setString(5, cqxxb.getBcqrlxmc());

            // '֤�����ʹ���'
            ps.setString(6, cqxxb.getZjlxdm());

            // '֤����������'
            ps.setString(7, cqxxb.getZjlxmc());

            // '֤������'
            ps.setString(8, cqxxb.getZjhm());

            // '��Ǩ��ϸ��ַ'
            ps.setString(9, cqxxb.getCqxxdz());

            // '�������'
            ps.setBigDecimal(10, cqxxb.getBcje());

            // '�������ʹ���'
            ps.setString(11, cqxxb.getBclxdm());

            // '������������'
            ps.setString(12, cqxxb.getBclxmc());

            // '�������'
            ps.setBigDecimal(13, cqxxb.getBcmj());

            // '�������ݵ�ַ'
            ps.setString(14, cqxxb.getBcfwdz());

            // '��Ǩ���֤��'
            ps.setString(15, cqxxb.getCqxkzh());

            // '���ش���'
            ps.setString(16, cqxxb.getQxdm());

            // '¼����'
            ps.setString(17, cqxxb.getLrr());

            // '������'
            // ps.setString(18, cqxxb.getCjr());

            // '¼������'
            ps.setTimestamp(18, cqxxb.getLrrq());

            // '��������'
            // ps.setTimestamp(20, cqxxb.getCjrq());

            // '������Դ'
            // ps.setString(21, cqxxb.getSjly());

            // '��������'
            ps.setString(19, cqxxb.getSzqx());

            // '��Ǩ��Ŀ����'
            ps.setString(20, cqxxb.getCqxmmc());

            // '��Ǩ���'
            ps.setBigDecimal(21, cqxxb.getCqmj());

            // ��Ǩ���֤����ʱ��
            ps.setTimestamp(22, cqxxb.getCqxkzspsj());

            // ����������
            ps.setString(23, cqxxb.getGjrmc());

            // ��ʽ���ݼ���
            ps.setString(24, cqxxb.getZsfwjs());

            // '��Ǩ��Ϣ���'
            ps.setString(25, cqxxb.getCqxxbh());

//			 '˰�������֯��������'
            ps.setString(26, cqxxb.getSwjgzzjgdm());

            // ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ���²�Ǩ��Ϣ
     *
     * @param cqxxb
     *            String ��Ǩ��Ϣvo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void update(Cqxxb cqxxb, String condition, Connection conn) throws
            SQLException {
        String sql = "update QSDB.QS_JL_CQXXB SET "
                     +
                     "CQRMC=?,CQFW=?,BCQRMC=?,BCQRLXDM=?,BCQRLXMC=?,ZJLXDM=?,"
                     +
                "ZJLXMC=?,ZJHM=?,CQXXDZ=?,BCJE=?,BCLXDM=?,BCLXMC=?,BCMJ=?,"
                     + "BCFWDZ=?,CQXKZH=?,QXDM=?,LRR=?,"
                     +
                "LRRQ=?,SZQX=?,CQXMMC=?,CQMJ=?,CQXKZHSPSJ=?,GJRMC=?,ZSFWJS=?,SWJGZZJGDM=? "
                     + condition;
        // System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            // '��Ǩ������'
            ps.setString(1, cqxxb.getCqrmc());

            // '��Ǩ��Χ'
            ps.setString(2, cqxxb.getCqfw());

            // '����Ǩ������'
            ps.setString(3, cqxxb.getBcqrmc());

            // '����Ǩ�����ʹ���'
            ps.setString(4, cqxxb.getBcqrlxdm());

            // '����Ǩ����������'
            ps.setString(5, cqxxb.getBcqrlxmc());

            // '֤�����ʹ���'
            ps.setString(6, cqxxb.getZjlxdm());

            // '֤����������'
            ps.setString(7, cqxxb.getZjlxmc());

            // '֤������'
            ps.setString(8, cqxxb.getZjhm());

            // '��Ǩ��ϸ��ַ'
            ps.setString(9, cqxxb.getCqxxdz());

            // '�������'
            ps.setBigDecimal(10, cqxxb.getBcje());

            // '�������ʹ���'
            ps.setString(11, cqxxb.getBclxdm());

            // '������������'
            ps.setString(12, cqxxb.getBclxmc());

            // '�������'
            ps.setBigDecimal(13, cqxxb.getBcmj());

            // '�������ݵ�ַ'
            ps.setString(14, cqxxb.getBcfwdz());

            // '��Ǩ���֤��'
            ps.setString(15, cqxxb.getCqxkzh());

            // '���ش���'
            ps.setString(16, cqxxb.getQxdm());

            // '¼����'
            ps.setString(17, cqxxb.getLrr());

            // '������'
            // ps.setString(18, cqxxb.getCjr());

            // '¼������'
            ps.setTimestamp(18, cqxxb.getLrrq());

            // '��������'
            // ps.setTimestamp(20, cqxxb.getCjrq());

            // '��Ǩ��Ϣ���'
            // ps.setString(20, cqxxb.getCqxxbh());

            // '������Դ'
            // ps.setString(21, cqxxb.getSjly());

            // '��������'
            ps.setString(19, cqxxb.getSzqx());

            // '��Ǩ��Ŀ����'
            ps.setString(20, cqxxb.getCqxmmc());

            // '��Ǩ���'
            ps.setBigDecimal(21, cqxxb.getCqmj());

            // ��Ǩ���֤����ʱ��
            ps.setTimestamp(22, cqxxb.getCqxkzspsj());

            // ����������
            ps.setString(23, cqxxb.getGjrmc());

            // ��ʽ���ݼ���
            ps.setString(24, cqxxb.getZsfwjs());

//			 '˰�������֯��������'
            ps.setString(25, cqxxb.getSwjgzzjgdm());

            // ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ��ѯ��Ǩ��Ϣ
     *
     * @param condition
     *            String
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList querySfzhmIstwo(String condition, Connection conn) throws
            SQLException {
        ArrayList rsList = new ArrayList();
        String sql = "select distinct(BCQRMC),ZJHM" + " from QSDB.QS_JL_CQXXB "
                     + condition;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cqxxb cqxxb = new Cqxxb();
                cqxxb.setBcqrmc(rs.getString("BCQRMC")); // '����Ǩ������'
                cqxxb.setZjhm(rs.getString("ZJHM")); // '֤������'

                rsList.add(cqxxb);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return rsList;
    }

    /**ɾ����¼
     * @param cqxxb
     * @param conn
     * @throws SQLException
     */
    public static void delete(Cqxxb cqxxb, Connection conn) throws SQLException {
        String sql = "DELETE FROM QSDB.QS_JL_CQXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, cqxxb.getCqxxbh());
            ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
