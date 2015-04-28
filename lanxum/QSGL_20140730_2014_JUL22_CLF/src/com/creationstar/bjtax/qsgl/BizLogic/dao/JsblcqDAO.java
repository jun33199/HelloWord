package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.ttsoft.common.util.Debug;


/**
 * ��ʱ���������Ϣ-��ǨDAO
 */
public class JsblcqDAO extends BaseDAO {

    /**
     * ����һ����ʱ���������Ϣ-��Ǩ��¼
     * @param jsblcq ��ʱ���������Ϣ-��Ǩֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_JSBLCQ (CQXYH,ZLDZ,CQBCE,CQBCSYE,YHBS,ZTBS,CJR,CJRQ,LRR,LRRQ,ND,BZ,SYEYWBZ) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //��ǨЭ�����
            ps.setString(1, jsblcq.cqxyh);
            //����Ǩ���������ַ
            ps.setString(2, jsblcq.zldz);
            //��Ǩ������
            ps.setBigDecimal(3, jsblcq.cqbce);
            //��Ǩ����ʣ���
            ps.setBigDecimal(4, jsblcq.cqbcsye);
            //�û���ʶ
            ps.setString(5, jsblcq.yhbs);
            //״̬��ʶ
            ps.setString(6, jsblcq.ztbs);
            //������
            ps.setString(7, jsblcq.cjr);
            //��������
            ps.setTimestamp(8, jsblcq.cjrq);
            //¼����
            ps.setString(9, jsblcq.lrr);
            //¼������
            ps.setTimestamp(10, jsblcq.lrrq);
            //���
            ps.setString(11, jsblcq.nd);
            //��ע
            ps.setString(12, jsblcq.bz);
            //��ע
            ps.setString(13, jsblcq.syeywbz);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����ʱ���������Ϣ-��Ǩ��¼
     * @param jsblcq ��ʱ���������Ϣ-��Ǩֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_JSBLCQ set CQXYH=?,ZLDZ=?,CQBCE=?,CQBCSYE=?,YHBS=?,ZTBS=?,CJR=?,CJRQ=?,LRR=?,LRRQ=?,ND=?,BZ=?,SYEYWBZ=? where cqxyh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblcq.cqxyh);
            ps.setString(2, jsblcq.zldz);
            ps.setBigDecimal(3, jsblcq.cqbce);
            ps.setBigDecimal(4, jsblcq.cqbcsye);
            ps.setString(5, jsblcq.yhbs);
            ps.setString(6, jsblcq.ztbs);
            ps.setString(7, jsblcq.cjr);
            ps.setTimestamp(8, jsblcq.cjrq);
            ps.setString(9, jsblcq.lrr);
            ps.setTimestamp(10, jsblcq.lrrq);
            ps.setString(11, jsblcq.nd);
            ps.setString(12, jsblcq.bz);
            ps.setString(13, jsblcq.syeywbz);
            ps.setString(14, jsblcq.cqxyh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����ʱ���������Ϣ-��Ǩ��¼,�ָ�ʣ�ಹ����
     * @param jsblcq ��ʱ���������Ϣ-��Ǩֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateBcsye(String sbbh, ArrayList cqList,
                                   Connection conn) throws SQLException {
        if (cqList == null) {
            return;
        }
        String sql = "update  QSDB.QS_JL_JSBLCQ set CQBCSYE = CQBCSYE + "
                     + "(select BCSYBCE from QSDB.QS_JL_SBCQGL "
                     + " where SBBH = ? and CQXYH = ? ) "
                     + " where CQXYH = ? ";
        Debug.out("update bcsye: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int size = cqList.size();
            for (int i = 0; i < size; i++) {
                Jsblcq jsblcq = (Jsblcq) cqList.get(i);
                ps.setString(1, sbbh);
                ps.setString(2, jsblcq.cqxyh);
                ps.setString(3, jsblcq.cqxyh);
                ps.execute();
                ps.clearParameters();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������ʱ���������Ϣ-��Ǩ��¼
     * @param jsblcqList ��ʱ���������Ϣ-��Ǩֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList jsblcqList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_JSBLCQ  where cqxyh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jsblcqList.size(); i++) {
                Jsblcq jsblcq = (Jsblcq) jsblcqList.get(i);
                ps.setString(1, jsblcq.cqxyh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ��ʱ���������Ϣ-��Ǩֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��ʱ���������Ϣ-��Ǩֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JsblcqList = new ArrayList();
        String sql = "select CQXYH,ZLDZ,CQBCE,CQBCSYE,YHBS,ZTBS,CJR,CJRQ,LRR,LRRQ,ND,BZ,SYEYWBZ from QSDB.QS_JL_JSBLCQ " +
                     condition;
        Debug.out("JsblcqDAO query sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblcq Jsblcq1 = new Jsblcq();
                Jsblcq1.setCqxyh(rs.getString("CQXYH"));
                Jsblcq1.setZldz(rs.getString("ZLDZ"));
                Jsblcq1.setCqbce(rs.getBigDecimal("CQBCE"));
                Jsblcq1.setCqbcsye(rs.getBigDecimal("CQBCSYE"));
                Jsblcq1.setYhbs(rs.getString("YHBS"));
                Jsblcq1.setZtbs(rs.getString("ZTBS"));
                Jsblcq1.setCjr(rs.getString("CJR"));
                Jsblcq1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblcq1.setLrr(rs.getString("LRR"));
                Jsblcq1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblcq1.setNd(rs.getString("ND"));
                Jsblcq1.setBz(rs.getString("BZ"));
                Jsblcq1.setSyeywbz(rs.getString("SYEYWBZ"));
                JsblcqList.add(Jsblcq1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsblcqList;
    }

    /**
     * ����������ȡ��ʱ���������Ϣ-��Ǩֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��ʱ���������Ϣ-��Ǩֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList queryBySbbh(String sbbh, Connection conn) throws
            SQLException {
        ArrayList JsblcqList = new ArrayList();
        String sql = "select c.CQXYH,c.ZLDZ,c.CQBCE,c.CQBCSYE,c.YHBS,"
                     + "c.ZTBS,c.CJR,c.CJRQ,c.LRR,c.LRRQ,c.ND,c.BZ,c.SYEYWBZ,g.BCSYBCE from QSDB.QS_JL_JSBLCQ c,"
                     +
                "QSDB.QS_JL_SBCQGL g where c.CQXYH = g.CQXYH and g.SBBH = ?";
        Debug.out("JsblcqDAO query sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblcq Jsblcq1 = new Jsblcq();
                Jsblcq1.setCqxyh(rs.getString("CQXYH"));
                Jsblcq1.setZldz(rs.getString("ZLDZ"));
                Jsblcq1.setCqbce(rs.getBigDecimal("CQBCE"));
                Jsblcq1.setCqbcsye(rs.getBigDecimal("CQBCSYE"));
                Jsblcq1.setYhbs(rs.getString("YHBS"));
                Jsblcq1.setZtbs(rs.getString("ZTBS"));
                Jsblcq1.setCjr(rs.getString("CJR"));
                Jsblcq1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblcq1.setLrr(rs.getString("LRR"));
                Jsblcq1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblcq1.setNd(rs.getString("ND"));
                Jsblcq1.setBz(rs.getString("BZ"));
                Jsblcq1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblcq1.setBcsybce(rs.getBigDecimal("BCSYBCE"));
                JsblcqList.add(Jsblcq1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsblcqList;
    }

    /**
     * ����������ȡ��ʱ���������Ϣ-��Ǩֵ����
     * @param jsblcq ��ʱ���������Ϣ-��Ǩֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��ʱ���������Ϣ-��Ǩֵ����
     * @throws SQLException
     */
    public static Object get(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        Jsblcq Jsblcq1 = null;
        String sql = "select CQXYH,ZLDZ,CQBCE,CQBCSYE,YHBS,ZTBS,CJR,CJRQ,LRR,LRRQ,ND,BZ,SYEYWBZ from QSDB.QS_JL_JSBLCQ   where cqxyh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblcq.cqxyh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jsblcq1 = new Jsblcq();
                Jsblcq1.setCqxyh(rs.getString("CQXYH"));
                Jsblcq1.setZldz(rs.getString("ZLDZ"));
                Jsblcq1.setCqbce(rs.getBigDecimal("CQBCE"));
                Jsblcq1.setCqbcsye(rs.getBigDecimal("CQBCSYE"));
                Jsblcq1.setYhbs(rs.getString("YHBS"));
                Jsblcq1.setZtbs(rs.getString("ZTBS"));
                Jsblcq1.setCjr(rs.getString("CJR"));
                Jsblcq1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblcq1.setLrr(rs.getString("LRR"));
                Jsblcq1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblcq1.setNd(rs.getString("ND"));
                Jsblcq1.setBz(rs.getString("BZ"));
                Jsblcq1.setSyeywbz(rs.getString("SYEYWBZ"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jsblcq1;
    }

    /**
     * ����һ����ʱ���������Ϣ��ʣ���-����ס����¼
     * @param jsblgyzf ��ʱ���������Ϣ-����ס��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateSye(Jsblcq jsblcq, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_JSBLCQ set CQBCSYE=?  where CQXYH = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, jsblcq.cqbcsye);
            ps.setString(2, jsblcq.cqxyh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }
}
