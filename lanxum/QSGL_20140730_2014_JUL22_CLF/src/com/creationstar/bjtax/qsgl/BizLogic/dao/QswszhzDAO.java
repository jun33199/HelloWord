package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;


/**
 * ��˰��˰֤�걨��������DAO
 */
public class QswszhzDAO extends BaseDAO {

    /**
     * ����һ����˰��˰֤�걨�������ݼ�¼
     * @param qswszhz ��˰��˰֤�걨��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_QSWSZHZ (JSJDM,SBHZDH,JKPZH,HZRQ,SJSE,HZKSRQ,HZJSRQ,CLBJDM,SWJGZZJGDM,HZFS,ND,LRRQ,CJRQ,LRR,CJR,HZFSMC,ZSDDM,ZSDMC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //���������
            ps.setString(1, qswszhz.jsjdm);
            //�걨���ܵ���
            ps.setString(2, qswszhz.sbhzdh);
            //�ɿ�ƾ֤��
            ps.setString(3, qswszhz.jkpzh);
            //��������
            ps.setTimestamp(4, qswszhz.hzrq);
            //ʵ��˰��
            ps.setBigDecimal(5, qswszhz.sjse);

            //modified by zhaobo
            //��Ϊ�������κŻ����ǲ���Ҫ�������ڵģ��������ݿ��д������ֶ�Ϊ������
            //��˽�����ʱ���ϵͳʱ��¼�뵽���ݿ���
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            //���ܿ�ʼ����
            ps.setTimestamp(6, ts);
            //���ܽ�������
            ps.setTimestamp(7, ts);
            //ended modified

            //�����Ǵ���
            ps.setString(8, qswszhz.clbjdm);
            //˰�������֯��������
            ps.setString(9, qswszhz.swjgzzjgdm);
            //���ܷ�ʽ
            ps.setString(10, qswszhz.hzfs);
            //���
            ps.setString(11, qswszhz.nd);
            //¼��ʱ��
            ps.setTimestamp(12, qswszhz.lrrq);
            //����ʱ��
            ps.setTimestamp(13, qswszhz.cjrq);
            //¼���˴���
            ps.setString(14, qswszhz.lrr);
            //������
            ps.setString(15, qswszhz.cjr);
            ps.setString(16, qswszhz.hzfsmc);
            ps.setString(17, qswszhz.zsddm);
            ps.setString(18, qswszhz.zsdmc);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰��˰֤�걨�������ݼ�¼
     * @param qswszhz ��˰��˰֤�걨��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_QSWSZHZ set JSJDM=?,SBHZDH=?,JKPZH=?,HZRQ=?,SJSE=?,HZKSRQ=?,HZJSRQ=?,CLBJDM=?,SWJGZZJGDM=?,HZFS=?,ND=?,LRRQ=?,CJRQ=?,LRR=?,CJR=?,HZFSMC=?  ZSDDM=? ZSDMC=? where jkpzh = ?  and jsjdm = ?  and sbhzdh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //���������
            ps.setString(1, qswszhz.jsjdm);
            //�걨���ܵ���
            ps.setString(2, qswszhz.sbhzdh);
            //�ɿ�ƾ֤��
            ps.setString(3, qswszhz.jkpzh);
            //��������
            ps.setTimestamp(4, qswszhz.hzrq);
            //ʵ��˰��
            ps.setBigDecimal(5, qswszhz.sjse);
            //���ܿ�ʼ����
            ps.setTimestamp(6, qswszhz.hzksrq);
            //���ܽ�������
            ps.setTimestamp(7, qswszhz.hzjsrq);
            //�����Ǵ���
            ps.setString(8, qswszhz.clbjdm);
            //˰�������֯��������
            ps.setString(9, qswszhz.swjgzzjgdm);
            //���ܷ�ʽ
            ps.setString(10, qswszhz.hzfs);
            //���
            ps.setString(11, qswszhz.nd);
            //¼��ʱ��
            ps.setTimestamp(12, qswszhz.lrrq);
            //����ʱ��
            ps.setTimestamp(13, qswszhz.cjrq);
            //¼���˴���
            ps.setString(14, qswszhz.lrr);
            //������
            ps.setString(15, qswszhz.cjr);
            ps.setString(16, qswszhz.hzfsmc);
            ps.setString(17, qswszhz.zsddm);
            ps.setString(18, qswszhz.zsdmc);
            //�ɿ�ƾ֤��
            ps.setString(19, qswszhz.jkpzh);
            //���������
            ps.setString(20, qswszhz.jsjdm);
            //�걨���ܵ���
            ps.setString(21, qswszhz.sbhzdh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������˰��˰֤�걨�������ݼ�¼
     * @param qswszhzList ��˰��˰֤�걨��������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList qswszhzList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_QSWSZHZ  where jkpzh = ?  and jsjdm = ?  and sbhzdh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qswszhzList.size(); i++) {
                Qswszhz qswszhz = (Qswszhz) qswszhzList.get(i);
                ps.setString(1, qswszhz.jkpzh);
                ps.setString(2, qswszhz.jsjdm);
                ps.setString(3, qswszhz.sbhzdh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * �����ɿ���ʹ��
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "delete from  SBDB.SB_JL_QSWSZHZ where ");
        sql.append(condition);

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * ����������ȡ��˰��˰֤�걨��������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰��˰֤�걨��������ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QswszhzList = new ArrayList();
        String sql = "select JSJDM,SBHZDH,JKPZH,HZRQ,SJSE,HZKSRQ,HZJSRQ,CLBJDM,SWJGZZJGDM,HZFS,ND,LRRQ,CJRQ,LRR,CJR,HZFSMC,ZSDDM,ZSDMC from SBDB.SB_JL_QSWSZHZ " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszhz Qswszhz1 = new Qswszhz();
                Qswszhz1.setJsjdm(rs.getString("JSJDM"));
                Qswszhz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszhz1.setJkpzh(rs.getString("JKPZH"));
                Qswszhz1.setHzrq(rs.getTimestamp("HZRQ"));
                Qswszhz1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszhz1.setHzksrq(rs.getTimestamp("HZKSRQ"));
                Qswszhz1.setHzjsrq(rs.getTimestamp("HZJSRQ"));
                Qswszhz1.setClbjdm(rs.getString("CLBJDM"));
                Qswszhz1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszhz1.setHzfs(rs.getString("HZFS"));
                Qswszhz1.setNd(rs.getString("ND"));
                Qswszhz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszhz1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszhz1.setLrr(rs.getString("LRR"));
                Qswszhz1.setCjr(rs.getString("CJR"));
                Qswszhz1.setHzfsmc(rs.getString("HZFSMC"));
                Qswszhz1.setZsddm(rs.getString("ZSDDM"));
                Qswszhz1.setZsdmc(rs.getString("ZSDMC"));
                QswszhzList.add(Qswszhz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QswszhzList;
    }

    /**
     * �����ɿ���ʱ���Ƿ��Ʊ�ķ���
     * @param jsjdm String      �ɿ��������еļ��������
     * @param jkpzh String      �ɿ��������еĽɿ�ƾ֤��
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList query(String jsjdm, String jkpzh, Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("");
        sql.append(
                "SELECT t.jkpzh,t.jsjdm,t.sbhzdh FROM SBDB.SB_JL_QSWSZHZ t, ")
                .append(
                "(SELECT t.sbhzdh FROM SBDB.SB_JL_QSWSZHZ t WHERE t.jkpzh = '")
                .append(jkpzh).append("' AND t.jsjdm = '").append(jsjdm).append(
                "') tt ")
                .append("WHERE t.sbhzdh = tt.sbhzdh");

        Debug.out("�����ɿ���ʱ���Ƿ��Ʊ�ķ��� sql : " + sql.toString());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszhz Qswszhz1 = new Qswszhz();
                Qswszhz1.setJsjdm(rs.getString("JSJDM"));
                Qswszhz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszhz1.setJkpzh(rs.getString("JKPZH"));

                list.add(Qswszhz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return list;
    }

    /**
     * ����������ȡ��˰��˰֤�걨��������ֵ����
     * @param qswszhz ��˰��˰֤�걨��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰��˰֤�걨��������ֵ����
     * @throws SQLException
     */
    public static Object get(Qswszhz qswszhz, Connection conn) throws
            SQLException {
        Qswszhz Qswszhz1 = null;
        String sql = "select JSJDM,SBHZDH,JKPZH,HZRQ,SJSE,HZKSRQ,HZJSRQ,CLBJDM,SWJGZZJGDM,HZFS,ND,LRRQ,CJRQ,LRR,CJR,HZFSMC,ZSDDM,ZSDMC from SBDB.SB_JL_QSWSZHZ   where jkpzh = ?  and jsjdm = ?  and sbhzdh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qswszhz.jkpzh);
            ps.setString(2, qswszhz.jsjdm);
            ps.setString(3, qswszhz.sbhzdh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qswszhz1 = new Qswszhz();
                Qswszhz1.setJsjdm(rs.getString("JSJDM"));
                Qswszhz1.setSbhzdh(rs.getString("SBHZDH"));
                Qswszhz1.setJkpzh(rs.getString("JKPZH"));
                Qswszhz1.setHzrq(rs.getTimestamp("HZRQ"));
                Qswszhz1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszhz1.setHzksrq(rs.getTimestamp("HZKSRQ"));
                Qswszhz1.setHzjsrq(rs.getTimestamp("HZJSRQ"));
                Qswszhz1.setClbjdm(rs.getString("CLBJDM"));
                Qswszhz1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszhz1.setHzfs(rs.getString("HZFS"));
                Qswszhz1.setNd(rs.getString("ND"));
                Qswszhz1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszhz1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszhz1.setLrr(rs.getString("LRR"));
                Qswszhz1.setCjr(rs.getString("CJR"));
                Qswszhz1.setHzfsmc(rs.getString("HZFSMC"));
                Qswszhz1.setZsddm(rs.getString("ZSDDM"));
                Qswszhz1.setZsdmc(rs.getString("ZSDMC"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qswszhz1;
    }


    /**
     * ��¼�������ɵĽɿ���ʱ����ѯ��˰֤���ܱ��ýɿ����Ƿ��з�Ʊ����������Ƿ��Ѿ���ɵķ�Ʊ
     * ֻҪ����sbhzdh�Ƿ�ΪĬ�ϵĳ������ɣ�����Ϊ�з�Ʊ���������δ��ɷ�Ʊ
     * @param jsjdm String   ���������
     * @param sbhzdh String  �걨���ܵ���
     * @param jkpzh String   �ɿ�ƾ֤��
     * @param conn Connection
     * @return boolean
     * @throws SQLException
     */
    public static String get(String jsjdm, String jkpzh, Connection conn) throws
            SQLException {
        String result = "";

        StringBuffer sql = new StringBuffer(
                "select sbhzdh from SBDB.SB_JL_QSWSZHZ ");
        sql.append("where jsjdm = '").append(jsjdm).append("' AND jkpzh = '")
                .append(jkpzh).append("' ");

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString("sbhzdh");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return result;

    }

    /**
     * ��¼�������ɵĽɿ����У����������з�Ʊ����Ľɿ����Ӧ����˰֤���ܱ����ݣ����걨���ܵ�����Ϊһ��
     * @param jsjdm String   ���������
     * @param sbhzdh String  �걨���ܵ���
     * @param jkpzh String   �ɿ�ƾ֤��
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String jsjdm, String sbhzdh, String jkpzh,
                              Connection conn) throws SQLException {

        StringBuffer sql = new StringBuffer(
                "UPDATE SBDB.SB_JL_QSWSZHZ set sbhzdh = '");
        sql.append(sbhzdh).append("' where jsjdm = '").append(jsjdm)
                .append("' AND sbhzdh = '").append(Constants.WSZ_SBHZDH_DEFAULT)
                .append("' AND jkpzh = '").append(jkpzh).append("' ");

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

    }

}
