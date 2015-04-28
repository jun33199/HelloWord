package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.ttsoft.common.util.Debug;


/**
 * ��ʱ���������Ϣ-����ס��DAO
 */
public class JsblgyzfDAO extends BaseDAO {

    /**
     * ����һ����ʱ���������Ϣ-����ס����¼
     * @param jsblgyzf ��ʱ���������Ϣ-����ס��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_JSBLGYZF (YGGYZFQSZSH,ZLDZ,QDSJ,JZMJ,CJJG,SYE,ZTBS,LRR,LRRQ,CJR,CJRQ,ND,BZ,SYEYWBZ,FWQSZSH) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�ѹ�����ס��Ȩ��֤���
            ps.setString(1, jsblgyzf.yggyzfqszsh);
            //�����ַ
            ps.setString(2, jsblgyzf.zldz);
            //���ۺ�ͬ����Լ��ǩ��ʱ��
            ps.setTimestamp(3, jsblgyzf.qdsj);
            //�������
            ps.setBigDecimal(4, jsblgyzf.jzmj);
            //�ɽ��۸�
            ps.setBigDecimal(5, jsblgyzf.cjjg);
            //ʣ���
            ps.setBigDecimal(6, jsblgyzf.sye);
            //״̬��ʶ
            ps.setString(7, jsblgyzf.ztbs);
            //¼����
            ps.setString(8, jsblgyzf.lrr);
            //¼������
            ps.setTimestamp(9, jsblgyzf.lrrq);
            //������
            ps.setString(10, jsblgyzf.cjr);
            //��������
            ps.setTimestamp(11, jsblgyzf.cjrq);
            //���
            ps.setString(12, jsblgyzf.nd);
            //��ע
            ps.setString(13, jsblgyzf.bz);
            //ʣ��������־
            ps.setString(14, jsblgyzf.syeywbz);
            //����Ȩ��֤���
            ps.setString(15,jsblgyzf.fwqszsh);            
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����ʱ���������Ϣ-����ס����¼
     * @param jsblgyzf ��ʱ���������Ϣ-����ס��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_JSBLGYZF set YGGYZFQSZSH=?,ZLDZ=?,QDSJ=?,JZMJ=?,CJJG=?,SYE=?,ZTBS=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,ND=?,BZ=?,SYEYWBZ=?,FWQSZSH=?   where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblgyzf.yggyzfqszsh);
            ps.setString(2, jsblgyzf.zldz);
            ps.setTimestamp(3, jsblgyzf.qdsj);
            ps.setBigDecimal(4, jsblgyzf.jzmj);
            ps.setBigDecimal(5, jsblgyzf.cjjg);
            ps.setBigDecimal(6, jsblgyzf.sye);
            ps.setString(7, jsblgyzf.ztbs);
            ps.setString(8, jsblgyzf.lrr);
            ps.setTimestamp(9, jsblgyzf.lrrq);
            ps.setString(10, jsblgyzf.cjr);
            ps.setTimestamp(11, jsblgyzf.cjrq);
            ps.setString(12, jsblgyzf.nd);
            ps.setString(13, jsblgyzf.bz);
            ps.setString(14, jsblgyzf.syeywbz);
            ps.setString(15,jsblgyzf.fwqszsh);
            ps.setString(16,jsblgyzf.yggyzfqszsh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������ʱ���������Ϣ-����ס����¼
     * @param jsblgyzfList ��ʱ���������Ϣ-����ס��ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList jsblgyzfList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_JSBLGYZF  where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jsblgyzfList.size(); i++) {
                Jsblgyzf jsblgyzf = (Jsblgyzf) jsblgyzfList.get(i);
                ps.setString(1, jsblgyzf.yggyzfqszsh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ��ʱ���������Ϣ-����ס��ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��ʱ���������Ϣ-����ס��ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JsblgyzfList = new ArrayList();
        String sql = "select YGGYZFQSZSH,ZLDZ,QDSJ,JZMJ,CJJG,SYE,ZTBS,LRR,LRRQ,CJR,CJRQ,ND,BZ,SYEYWBZ,FWQSZSH from QSDB.QS_JL_JSBLGYZF " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblgyzf Jsblgyzf1 = new Jsblgyzf();
                Jsblgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Jsblgyzf1.setZldz(rs.getString("ZLDZ"));
                Jsblgyzf1.setQdsj(rs.getTimestamp("QDSJ"));
                Jsblgyzf1.setJzmj(rs.getBigDecimal("JZMJ"));
                Jsblgyzf1.setCjjg(rs.getBigDecimal("CJJG"));
                Jsblgyzf1.setSye(rs.getBigDecimal("SYE"));
                Jsblgyzf1.setZtbs(rs.getString("ZTBS"));
                Jsblgyzf1.setLrr(rs.getString("LRR"));
                Jsblgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblgyzf1.setCjr(rs.getString("CJR"));
                Jsblgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblgyzf1.setNd(rs.getString("ND"));
                Jsblgyzf1.setBz(rs.getString("BZ"));
                Jsblgyzf1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblgyzf1.setFwqszsh(rs.getString("FWQSZSH"));                
                JsblgyzfList.add(Jsblgyzf1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsblgyzfList;
    }

    /**
     * ����������ȡ��ʱ���������Ϣ-����ס��ֵ����
     * @param jsblgyzf ��ʱ���������Ϣ-����ס��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��ʱ���������Ϣ-����ס��ֵ����
     * @throws SQLException
     */
    public static Object get(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        Jsblgyzf Jsblgyzf1 = null;
        String sql = "select YGGYZFQSZSH,ZLDZ,QDSJ,JZMJ,CJJG,SYE,ZTBS,LRR,LRRQ,CJR,CJRQ,ND,BZ,SYEYWBZ,FWQSZSH from QSDB.QS_JL_JSBLGYZF   where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsblgyzf.yggyzfqszsh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jsblgyzf1 = new Jsblgyzf();
                Jsblgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Jsblgyzf1.setZldz(rs.getString("ZLDZ"));
                Jsblgyzf1.setQdsj(rs.getTimestamp("QDSJ"));
                Jsblgyzf1.setJzmj(rs.getBigDecimal("JZMJ"));
                Jsblgyzf1.setCjjg(rs.getBigDecimal("CJJG"));
                Jsblgyzf1.setSye(rs.getBigDecimal("SYE"));
                Jsblgyzf1.setZtbs(rs.getString("ZTBS"));
                Jsblgyzf1.setLrr(rs.getString("LRR"));
                Jsblgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblgyzf1.setCjr(rs.getString("CJR"));
                Jsblgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblgyzf1.setNd(rs.getString("ND"));
                Jsblgyzf1.setBz(rs.getString("BZ"));
                Jsblgyzf1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblgyzf1.setFwqszsh(rs.getString("FWQSZSH"));                
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jsblgyzf1;
    }

    /**
     * ����������ȡ��ʱ���������Ϣ-����ס��ֵ����
     * @param jsblgyzf ��ʱ���������Ϣ-����ס��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��ʱ���������Ϣ-����ס��ֵ����
     * @throws SQLException
     */
    public static ArrayList getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        ArrayList jsblgyzfList = new ArrayList();
        String sql = "select a.YGGYZFQSZSH,a.ZLDZ,a.QDSJ,a.JZMJ,a.CJJG,a.SYE,"
                     + "a.ZTBS,a.LRR,a.LRRQ,a.CJR,a.CJRQ,a.ND,a.BZ,a.SYEYWBZ,b.bcdke,a.FWQSZSH from QSDB.QS_JL_JSBLGYZF a,"
                     +
                " QSDB.QS_JL_SBGYZF b where a.yggyzfqszsh = b.yggyzfqszsh "
                     + " and b.sbbh = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsblgyzf Jsblgyzf1 = new Jsblgyzf();
                Jsblgyzf1.setYggyzfqszsh(rs.getString("YGGYZFQSZSH"));
                Jsblgyzf1.setZldz(rs.getString("ZLDZ"));
                Jsblgyzf1.setQdsj(rs.getTimestamp("QDSJ"));
                Jsblgyzf1.setJzmj(rs.getBigDecimal("JZMJ"));
                Jsblgyzf1.setCjjg(rs.getBigDecimal("CJJG"));
                Jsblgyzf1.setSye(rs.getBigDecimal("SYE"));
                Jsblgyzf1.setZtbs(rs.getString("ZTBS"));
                Jsblgyzf1.setLrr(rs.getString("LRR"));
                Jsblgyzf1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsblgyzf1.setCjr(rs.getString("CJR"));
                Jsblgyzf1.setCjrq(rs.getTimestamp("CJRQ"));
                Jsblgyzf1.setNd(rs.getString("ND"));
                Jsblgyzf1.setBz(rs.getString("BZ"));
                Jsblgyzf1.setSyeywbz(rs.getString("SYEYWBZ"));
                Jsblgyzf1.setBcdke(rs.getBigDecimal("BCDKE"));
                Jsblgyzf1.setFwqszsh(rs.getString("FWQSZSH"));                  
                jsblgyzfList.add(Jsblgyzf1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return jsblgyzfList;
    }

    /**
     * ����һ����ʱ���������Ϣ-�ѹ�����ס����¼,�ָ�ʣ���
     * @param
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateDksye(String sbbh, ArrayList gyzfList,
                                   Connection conn) throws SQLException {
        if (gyzfList == null) {
            return;
        }
        String sql = "update  QSDB.QS_JL_JSBLGYZF set SYE = SYE + "
                     + "(select BCDKE from QSDB.QS_JL_SBGYZF "
                     + " where SBBH = ? and YGGYZFQSZSH = ? ) "
                     + " where YGGYZFQSZSH = ? ";
        Debug.out("update QS_JL_JSBLGYZF SYE: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int size = gyzfList.size();
            for (int i = 0; i < size; i++) {
                Jsblgyzf jsblgyzf = (Jsblgyzf) gyzfList.get(i);
                ps.setString(1, sbbh);
                ps.setString(2, jsblgyzf.yggyzfqszsh);
                ps.setString(3, jsblgyzf.yggyzfqszsh);
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
     * ����һ����ʱ���������Ϣ��ʣ���-����ס����¼
     * @param jsblgyzf ��ʱ���������Ϣ-����ס��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateSye(Jsblgyzf jsblgyzf, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_JSBLGYZF set SYE=?  where yggyzfqszsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, jsblgyzf.sye);
            ps.setString(2, jsblgyzf.yggyzfqszsh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
