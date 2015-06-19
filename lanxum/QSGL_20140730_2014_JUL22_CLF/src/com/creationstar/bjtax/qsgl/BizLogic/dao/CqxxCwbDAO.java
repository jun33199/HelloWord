package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo;

public class CqxxCwbDAO extends BaseDAO {
    /**
     * ���Ǩ��Ϣ�����������
     *
     * @param cqxxImportErrbvo
     *            CqxxImportErrbvo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void insert(CqxxImportErrbvo cqxxImportErrbvo,
                              Connection conn) throws SQLException {
        String sql = "INSERT INTO QSDB.QS_JL_CQCWXXB "
                     +
                     "(CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                     +
                "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                     + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS,CWLX,CWLXMC) "
                     +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // '��Ǩ������'
            ps.setString(1, cqxxImportErrbvo.getCqrmc());
            // '��Ǩ��Χ'
            ps.setString(2, cqxxImportErrbvo.getCqfw());
            // '����Ǩ������'
            ps.setString(3, cqxxImportErrbvo.getBcqrmc());
            // '����Ǩ�����ʹ���'
            ps.setString(4, cqxxImportErrbvo.getBcqrlxdm());
            // '����Ǩ����������'
            ps.setString(5, cqxxImportErrbvo.getBcqrlxmc());
            // '֤�����ʹ���'
            ps.setString(6, cqxxImportErrbvo.getZjlxdm());
            // '֤����������'
            ps.setString(7, cqxxImportErrbvo.getZjlxmc());
            // '֤������'
            ps.setString(8, cqxxImportErrbvo.getZjhm());
            // '��Ǩ��ϸ��ַ'
            ps.setString(9, cqxxImportErrbvo.getCqxxdz());
            // '�������'
            ps.setString(10, cqxxImportErrbvo.getBcje());
            // '�������ʹ���'
            ps.setString(11, cqxxImportErrbvo.getBclxdm());
            // '������������'
            ps.setString(12, cqxxImportErrbvo.getBclxmc());
            // '�������'
            ps.setString(13, cqxxImportErrbvo.getBcmj());
            // '�������ݵ�ַ'
            ps.setString(14, cqxxImportErrbvo.getBcfwdz());
            // '��Ǩ���֤��'
            ps.setString(15, cqxxImportErrbvo.getCqxkzh());
            // '���ش���'
            ps.setString(16, cqxxImportErrbvo.getQxdm());
            // '¼����'
            ps.setString(17, cqxxImportErrbvo.getLrr());
            // '������'
            ps.setString(18, cqxxImportErrbvo.getCjr());
            // '¼������'
            ps.setTimestamp(19, cqxxImportErrbvo.getLrrq());
            // '��������'
            ps.setTimestamp(20, cqxxImportErrbvo.getCjrq());
            // '��Ǩ��Ϣ���'
            ps.setString(21, cqxxImportErrbvo.getCqxxbh());
            // '������Դ'
            ps.setString(22, cqxxImportErrbvo.getSjly());
            // '��������'
            ps.setString(23, cqxxImportErrbvo.getSzqx());
            // '˰�������֯��������'
            ps.setString(24, cqxxImportErrbvo.getSwjgzzjgdm());
            // '��Ǩ��Ŀ����'
            ps.setString(25, cqxxImportErrbvo.getCqxmmc());
            // '��Ǩ���'
            ps.setString(26, cqxxImportErrbvo.getCqmj());
            // ��Ǩ���֤����ʱ��
            ps.setString(27, cqxxImportErrbvo.getCqxkzspsj());
            // ����������
            ps.setString(28, cqxxImportErrbvo.getGjrmc());
            // ��ʽ���ݼ���
            ps.setString(29, cqxxImportErrbvo.getZsfwjs());
            // ��������
            ps.setString(30, cqxxImportErrbvo.getCwlx());
            // ������������
            ps.setString(31, cqxxImportErrbvo.getCwlxmc());
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
        String sql = "DELETE FROM QSDB.QS_JL_CQCWXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < bhList.size(); i++) {
                CqxxImportErrbvo cqxxImportErrbvo = (CqxxImportErrbvo) bhList
                        .get(i);
                // String bh = (String)bhList.get(i);

                ps.setString(1, cqxxImportErrbvo.getCqxxbh());
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**ɾ����¼
     * @param cqxxImportErrbvo
     * @param conn
     * @throws SQLException
     */
    public static void delete(CqxxImportErrbvo cqxxImportErrbvo,
                              Connection conn) throws SQLException {
        String sql = "DELETE FROM QSDB.QS_JL_CQCWXXB WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, cqxxImportErrbvo.getCqxxbh());
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
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList rsList = new ArrayList();
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS,CWLX,CWLXMC from QSDB.QS_JL_CQCWXXB "
                + condition;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CqxxImportErrbvo cqxxImportErrbvo = new CqxxImportErrbvo();
                cqxxImportErrbvo.setCqrmc(rs.getString("CQRMC")); // '��Ǩ������'
                cqxxImportErrbvo.setCqfw(rs.getString("CQFW")); // '��Ǩ��Χ'
                cqxxImportErrbvo.setBcqrmc(rs.getString("BCQRMC")); // '����Ǩ������'
                cqxxImportErrbvo.setBcqrlxdm(rs.getString("BCQRLXDM")); // '����Ǩ�����ʹ���'
                cqxxImportErrbvo.setBcqrlxmc(rs.getString("BCQRLXMC")); // '����Ǩ����������'
                cqxxImportErrbvo.setZjlxdm(rs.getString("ZJLXDM")); // '֤�����ʹ���'
                cqxxImportErrbvo.setZjlxmc(rs.getString("ZJLXMC")); // '֤����������'
                cqxxImportErrbvo.setZjhm(rs.getString("ZJHM")); // '֤������'
                cqxxImportErrbvo.setCqxxdz(rs.getString("CQXXDZ")); // '��Ǩ��ϸ��ַ'
                cqxxImportErrbvo.setBcje(rs.getString("BCJE")); // '�������'
                cqxxImportErrbvo.setBclxdm(rs.getString("BCLXDM")); // '�������ʹ���'
                cqxxImportErrbvo.setBclxmc(rs.getString("BCLXMC")); // '������������'
                cqxxImportErrbvo.setBcmj(rs.getString("BCMJ")); // '�������'
                cqxxImportErrbvo.setBcfwdz(rs.getString("BCFWDZ")); // '�������ݵ�ַ'
                cqxxImportErrbvo.setCqxkzh(rs.getString("CQXKZH")); // '��Ǩ���֤��'
                cqxxImportErrbvo.setQxdm(rs.getString("QXDM")); // '���ش���'
                cqxxImportErrbvo.setLrr(rs.getString("LRR")); // '¼����'
                cqxxImportErrbvo.setCjr(rs.getString("CJR")); // '������'
                cqxxImportErrbvo.setLrrq(rs.getTimestamp("LRRQ")); // '¼������'
                cqxxImportErrbvo.setCjrq(rs.getTimestamp("CJRQ")); // '��������'
                cqxxImportErrbvo.setCqxxbh(rs.getString("cqxxbh")); // '��Ǩ��Ϣ���'
                cqxxImportErrbvo.setSjly(rs.getString("SJLY")); // '������Դ'
                cqxxImportErrbvo.setSzqx(rs.getString("szqx")); // '��������'
                cqxxImportErrbvo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '˰�������֯��������'
                cqxxImportErrbvo.setCqxmmc(rs.getString("CQXMMC")); // '��Ǩ��Ŀ����'
                cqxxImportErrbvo.setCqmj(rs.getString("CQMJ")); // '��Ǩ���'

                // ��Ǩ���֤����ʱ��
                cqxxImportErrbvo.setCqxkzspsj(rs.getString("CQXKZHSPSJ"));
                // ����������
                cqxxImportErrbvo.setGjrmc(rs.getString("GJRMC"));
                // ��ʽ���ݼ���
                cqxxImportErrbvo.setZsfwjs(rs.getString("ZSFWJS"));

                // ��������
                cqxxImportErrbvo.setCwlx(rs.getString("CWLX"));
                // ������������
                cqxxImportErrbvo.setCwlxmc(rs.getString("CWLXMC"));

                rsList.add(cqxxImportErrbvo);
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
    public static CqxxImportErrbvo get(String cqxxbh, Connection conn) throws
            SQLException {
        String sql =
                "select CQRMC,CQFW,BCQRMC,BCQRLXDM,BCQRLXMC,ZJLXDM,ZJLXMC,ZJHM,"
                + "CQXXDZ,BCJE,BCLXDM,BCLXMC,BCMJ,BCFWDZ,CQXKZH,QXDM,LRR,CJR,"
                + "LRRQ,CJRQ,CQXXBH,SJLY,SZQX,SWJGZZJGDM,CQXMMC,CQMJ,CQXKZHSPSJ,GJRMC,ZSFWJS,CWLX,CWLXMC from QSDB.QS_JL_CQCWXXB where cqxxbh="
                + cqxxbh;
        System.out.println("sql===============" + sql);
        PreparedStatement ps = null;
        CqxxImportErrbvo cqxxImportErrbvo = new CqxxImportErrbvo();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cqxxImportErrbvo.setCqrmc(rs.getString("CQRMC")); // '��Ǩ������'
                cqxxImportErrbvo.setCqfw(rs.getString("CQFW")); // '��Ǩ��Χ'
                cqxxImportErrbvo.setBcqrmc(rs.getString("BCQRMC")); // '����Ǩ������'
                cqxxImportErrbvo.setBcqrlxdm(rs.getString("BCQRLXDM")); // '����Ǩ�����ʹ���'
                cqxxImportErrbvo.setBcqrlxmc(rs.getString("BCQRLXMC")); // '����Ǩ����������'
                cqxxImportErrbvo.setZjlxdm(rs.getString("ZJLXDM")); // '֤�����ʹ���'
                cqxxImportErrbvo.setZjlxmc(rs.getString("ZJLXMC")); // '֤����������'
                cqxxImportErrbvo.setZjhm(rs.getString("ZJHM")); // '֤������'
                cqxxImportErrbvo.setCqxxdz(rs.getString("CQXXDZ")); // '��Ǩ��ϸ��ַ'
                cqxxImportErrbvo.setBcje(rs.getString("BCJE")); // '�������'
                cqxxImportErrbvo.setBclxdm(rs.getString("BCLXDM")); // '�������ʹ���'
                cqxxImportErrbvo.setBclxmc(rs.getString("BCLXMC")); // '������������'
                cqxxImportErrbvo.setBcmj(rs.getString("BCMJ")); // '�������'
                cqxxImportErrbvo.setBcfwdz(rs.getString("BCFWDZ")); // '�������ݵ�ַ'
                cqxxImportErrbvo.setCqxkzh(rs.getString("CQXKZH")); // '��Ǩ���֤��'
                cqxxImportErrbvo.setQxdm(rs.getString("QXDM")); // '���ش���'
                cqxxImportErrbvo.setLrr(rs.getString("LRR")); // '¼����'
                cqxxImportErrbvo.setCjr(rs.getString("CJR")); // '������'
                cqxxImportErrbvo.setLrrq(rs.getTimestamp("LRRQ")); // '¼������'
                cqxxImportErrbvo.setCjrq(rs.getTimestamp("CJRQ")); // '��������'
                cqxxImportErrbvo.setCqxxbh(rs.getString("cqxxbh")); // '��Ǩ��Ϣ���'
                cqxxImportErrbvo.setSjly(rs.getString("SJLY")); // '������Դ'
                cqxxImportErrbvo.setSzqx(rs.getString("szqx")); // '��������'
                cqxxImportErrbvo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM")); // '˰�������֯��������'
                cqxxImportErrbvo.setCqxmmc(rs.getString("CQXMMC")); // '��Ǩ��Ŀ����'
                cqxxImportErrbvo.setCqmj(rs.getString("CQMJ")); // '��Ǩ���'

                // ��Ǩ���֤����ʱ��
                cqxxImportErrbvo.setCqxkzspsj(rs.getString("CQXKZHSPSJ"));
                // ����������
                cqxxImportErrbvo.setGjrmc(rs.getString("GJRMC"));
                // ��ʽ���ݼ���
                cqxxImportErrbvo.setZsfwjs(rs.getString("ZSFWJS"));
                // ��������
                cqxxImportErrbvo.setCwlx(rs.getString("CWLX"));
                // ������������
                cqxxImportErrbvo.setCwlxmc(rs.getString("CWLXMC"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return cqxxImportErrbvo;
    }

    /**
     * ���²�Ǩ��Ϣ
     *
     * @param cqxxImportErrbvo
     *            String ��Ǩ��Ϣvo
     * @param conn
     *            Connection
     * @throws SQLException
     */
    public static void update(CqxxImportErrbvo cqxxImportErrbvo,
                              Connection conn) throws SQLException {
        String sql = "update QSDB.QS_JL_CQCWXXB SET "
                     +
                     "CQRMC=?,CQFW=?,BCQRMC=?,BCQRLXDM=?,BCQRLXMC=?,ZJLXDM=?,"
                     +
                "ZJLXMC=?,ZJHM=?,CQXXDZ=?,BCJE=?,BCLXDM=?,BCLXMC=?,BCMJ=?,"
                     + "BCFWDZ=?,CQXKZH=?,"
                     + "LRRQ=?,SZQX=?,CQXMMC=?,CQMJ=?,CQXKZHSPSJ=?,GJRMC=?,ZSFWJS=?,CWLX=?,CWLXMC=? WHERE CQXXBH=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // '��Ǩ������'
            ps.setString(1, cqxxImportErrbvo.getCqrmc());
            // '��Ǩ��Χ'
            ps.setString(2, cqxxImportErrbvo.getCqfw());
            // '����Ǩ������'
            ps.setString(3, cqxxImportErrbvo.getBcqrmc());
            // '����Ǩ�����ʹ���'
            ps.setString(4, cqxxImportErrbvo.getBcqrlxdm());
            // '����Ǩ����������'
            ps.setString(5, cqxxImportErrbvo.getBcqrlxmc());
            // '֤�����ʹ���'
            ps.setString(6, cqxxImportErrbvo.getZjlxdm());
            // '֤����������'
            ps.setString(7, cqxxImportErrbvo.getZjlxmc());
            // '֤������'
            ps.setString(8, cqxxImportErrbvo.getZjhm());
            // '��Ǩ��ϸ��ַ'
            ps.setString(9, cqxxImportErrbvo.getCqxxdz());
            // '�������'
            ps.setString(10, cqxxImportErrbvo.getBcje());
            // '�������ʹ���'
            ps.setString(11, cqxxImportErrbvo.getBclxdm());
            // '������������'
            ps.setString(12, cqxxImportErrbvo.getBclxmc());
            // '�������'
            ps.setString(13, cqxxImportErrbvo.getBcmj());
            // '�������ݵ�ַ'
            ps.setString(14, cqxxImportErrbvo.getBcfwdz());
            // '��Ǩ���֤��'
            ps.setString(15, cqxxImportErrbvo.getCqxkzh());
            // '���ش���'
            // ps.setString(16, cqxxImportErrbvo.getQxdm());
            // '¼����'
            // ps.setString(17, cqxxImportErrbvo.getLrr());
            // '������'
            // ps.setString(18, cqxxImportErrbvo.getCjr());
            // '¼������'
            ps.setTimestamp(16, cqxxImportErrbvo.getLrrq());
            // '��������'
            // ps.setTimestamp(20, cqxxImportErrbvo.getCjrq());
            // '��Ǩ��Ϣ���'
            ps.setString(20, cqxxImportErrbvo.getCqxxbh());
            // '������Դ'
            // ps.setString(21, cqxxImportErrbvo.getSjly());
            // '��������'
            ps.setString(17, cqxxImportErrbvo.getSzqx());
            // '˰�������֯��������'
            // ps.setString(23, cqxxImportErrbvo.getSwjgzzjgdm());
            // '��Ǩ��Ŀ����'
            ps.setString(18, cqxxImportErrbvo.getCqxmmc());
            // '��Ǩ���'
            ps.setString(19, cqxxImportErrbvo.getCqmj());

            // ��Ǩ���֤����ʱ��
            ps.setString(20, cqxxImportErrbvo.getCqxkzspsj());
            // ����������
            ps.setString(21, cqxxImportErrbvo.getGjrmc());
            // ��ʽ���ݼ���
            ps.setString(22, cqxxImportErrbvo.getZsfwjs());
            // ��������
            ps.setString(23, cqxxImportErrbvo.getCwlx());
            // ������������
            ps.setString(24, cqxxImportErrbvo.getCwlxmc());

            // ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
