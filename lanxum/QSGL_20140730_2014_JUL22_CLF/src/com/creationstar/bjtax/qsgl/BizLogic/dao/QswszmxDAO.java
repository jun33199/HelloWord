package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.ttsoft.common.util.Debug;


/**
 * ��˰��˰֤��ϸ����DAO
 */
public class QswszmxDAO extends BaseDAO {

    /**
     * ����һ����˰��˰֤��ϸ���ݼ�¼
     * @param qswszmx ��˰��˰֤��ϸ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Qswszmx qswszmx, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_QSWSZMX (WSZH,NDZB,PZZLDM,SZSMDM,JSJDM,SWJGZZJGDM,ZJHM,SZDM,JSJE,SL,YJHKC,SJSE,SKSSKSRQ,SKSSJSRQ,JZBZ,YSKMDM,YSJCDM,ND,CJRQ,LRRQ,QSZYMJ,LRR,CJR,SZSMMC,SZMC,YSKMMC,YSJCMC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //��˰֤��
            ps.setString(1, qswszmx.wszh);
            //����ֱ�
            ps.setString(2, qswszmx.ndzb);
            //Ʊ֤�������
            ps.setString(3, qswszmx.pzzldm);
            //˰��˰Ŀ����
            ps.setString(4, qswszmx.szsmdm);
            Debug.out("qswszmx.szsmdm" + qswszmx.szsmdm + "'");
            //���������
            ps.setString(5, qswszmx.jsjdm);
            //˰�������֯��������
            ps.setString(6, qswszmx.swjgzzjgdm);
            //���֤������
            ps.setString(7, qswszmx.zjhm);
            //˰�ִ���
            ps.setString(8, qswszmx.szdm);
            //��˰���
            ps.setBigDecimal(9, qswszmx.jsje);
            //˰��
            ps.setBigDecimal(10, qswszmx.sl);
            //�ѽɻ�۳�
            ps.setBigDecimal(11, qswszmx.yjhkc);
            //ʵ��˰��
            ps.setBigDecimal(12, qswszmx.sjse);
            //˰��������ʼ����
            ps.setTimestamp(13, qswszmx.skssksrq);
            //˰��������������
            ps.setTimestamp(14, qswszmx.skssjsrq);
            //���ʱ�־
            ps.setString(15, qswszmx.jzbz);
            //Ԥ���Ŀ����
            ps.setString(16, qswszmx.yskmdm);
            //Ԥ�㼶�δ���
            ps.setString(17, qswszmx.ysjcdm);
            //���
            ps.setString(18, qswszmx.nd);
            //����ʱ��
            ps.setTimestamp(19, qswszmx.cjrq);
            //¼��ʱ��
            ps.setTimestamp(20, qswszmx.lrrq);
            //���ز�Ȩ��ת�����
            ps.setBigDecimal(21, qswszmx.qszymj);
            ps.setString(22, qswszmx.lrr);
            ps.setString(23, qswszmx.cjr);
            ps.setString(24, qswszmx.szsmmc);
            Debug.out("qswszmx.szsmmc" + qswszmx.szsmmc + "'");
            ps.setString(25, qswszmx.szmc);
            ps.setString(26, qswszmx.yskmmc);
            Debug.out("qswszmx.yskmmc" + qswszmx.yskmmc + "'");
            ps.setString(27, qswszmx.ysjcmc);
            Debug.out("qswszmx.ysjcmc" + qswszmx.ysjcmc + "'");
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰��˰֤��ϸ���ݼ�¼
     * @param qswszmx ��˰��˰֤��ϸ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Qswszmx qswszmx, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_QSWSZMX set WSZH=?,NDZB=?,PZZLDM=?,SZSMDM=?,JSJDM=?,SWJGZZJGDM=?,ZJHM=?,SZDM=?,JSJE=?,SL=?,YJHKC=?,SJSE=?,SKSSKSRQ=?,SKSSJSRQ=?,JZBZ=?,YSKMDM=?,YSJCDM=?,ND=?, LRR=?,CJRQ=?,QSZYMJ=?,LRRQ=?, CJR=?, SZSMMC=?, SZMC=?, YSKMMC=? YSJCMC=? where ndzb = ?  and pzzldm = ?  and szsmdm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //��˰֤��
            ps.setString(1, qswszmx.wszh);
            //����ֱ�
            ps.setString(2, qswszmx.ndzb);
            //Ʊ֤�������
            ps.setString(3, qswszmx.pzzldm);
            //˰��˰Ŀ����
            ps.setString(4, qswszmx.szsmdm);
            //���������
            ps.setString(5, qswszmx.jsjdm);
            //˰�������֯��������
            ps.setString(6, qswszmx.swjgzzjgdm);
            //���֤������
            ps.setString(7, qswszmx.zjhm);
            //˰�ִ���
            ps.setString(8, qswszmx.szdm);
            //��˰���
            ps.setBigDecimal(9, qswszmx.jsje);
            //˰��
            ps.setBigDecimal(10, qswszmx.sl);
            //�ѽɻ�۳�
            ps.setBigDecimal(11, qswszmx.yjhkc);
            //ʵ��˰��
            ps.setBigDecimal(12, qswszmx.sjse);
            //˰��������ʼ����
            ps.setTimestamp(13, qswszmx.skssksrq);
            //˰��������������
            ps.setTimestamp(14, qswszmx.skssjsrq);
            //���ʱ�־
            ps.setString(15, qswszmx.jzbz);
            //Ԥ���Ŀ����
            ps.setString(16, qswszmx.yskmdm);
            //Ԥ�㼶�δ���
            ps.setString(17, qswszmx.ysjcdm);
            //���
            ps.setString(18, qswszmx.nd);

            ps.setString(19, qswszmx.lrr);
            //����ʱ��
            ps.setTimestamp(20, qswszmx.cjrq);
            //���ز�Ȩ��ת�����
            ps.setBigDecimal(21, qswszmx.qszymj);
            //¼��ʱ��
            ps.setTimestamp(22, qswszmx.lrrq);
            ps.setString(23, qswszmx.cjr);

            ps.setString(24, qswszmx.szsmmc);
            ps.setString(25, qswszmx.szmc);
            ps.setString(26, qswszmx.yskmmc);
            ps.setString(27, qswszmx.ysjcmc);
            ps.setString(28, qswszmx.ndzb);
            ps.setString(29, qswszmx.pzzldm);
            ps.setString(30, qswszmx.szsmdm);
            ps.setString(31, qswszmx.wszh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������˰��˰֤��ϸ���ݼ�¼
     * @param qswszmxList ��˰��˰֤��ϸ����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList qswszmxList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_QSWSZMX  where ndzb = ?  and pzzldm = ?  and szsmdm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < qswszmxList.size(); i++) {
                Qswszmx qswszmx = (Qswszmx) qswszmxList.get(i);
                ps.setString(1, qswszmx.ndzb);
                ps.setString(2, qswszmx.pzzldm);
                ps.setString(3, qswszmx.szsmdm);
                ps.setString(4, qswszmx.wszh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ������˰֤ʹ��
     * @param condition StringBuffer
     * @param conn Connection
     * @throws SQLException
     */
    public static void delete(StringBuffer condition, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer(
                "delete from  SBDB.SB_JL_QSWSZMX  where ");
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
     * ����������ȡ��˰��˰֤��ϸ����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰��˰֤��ϸ����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList QswszmxList = new ArrayList();
        String sql = "select WSZH,NDZB,PZZLDM,SZSMDM,JSJDM,SWJGZZJGDM,ZJHM,SZDM,JSJE,SL,YJHKC,SJSE,SKSSKSRQ,SKSSJSRQ,JZBZ,YSKMDM,YSJCDM,ND,CJRQ,LRRQ,QSZYMJ,LRR,CJR,SZSMMC,SZMC,YSKMMC,YSJCMC from SBDB.SB_JL_QSWSZMX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Qswszmx Qswszmx1 = new Qswszmx();
                Qswszmx1.setWszh(rs.getString("WSZH"));
                Qswszmx1.setNdzb(rs.getString("NDZB"));
                Qswszmx1.setPzzldm(rs.getString("PZZLDM"));
                Qswszmx1.setSzsmdm(rs.getString("SZSMDM"));
                Qswszmx1.setJsjdm(rs.getString("JSJDM"));
                Qswszmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszmx1.setZjhm(rs.getString("ZJHM"));
                Qswszmx1.setSzdm(rs.getString("SZDM"));
                Qswszmx1.setJsje(rs.getBigDecimal("JSJE"));
                Qswszmx1.setSl(rs.getBigDecimal("SL"));
                Qswszmx1.setYjhkc(rs.getBigDecimal("YJHKC"));
                Qswszmx1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Qswszmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Qswszmx1.setJzbz(rs.getString("JZBZ"));
                Qswszmx1.setYskmdm(rs.getString("YSKMDM"));
                Qswszmx1.setYsjcdm(rs.getString("YSJCDM"));
                Qswszmx1.setNd(rs.getString("ND"));
                Qswszmx1.setNd(rs.getString("LRR"));
                Qswszmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszmx1.setQszymj(rs.getBigDecimal("QSZYMJ"));
                Qswszmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszmx1.setNd(rs.getString("CJR"));
                Qswszmx1.setNd(rs.getString("SZSMMC"));
                Qswszmx1.setNd(rs.getString("SZMC"));
                Qswszmx1.setNd(rs.getString("YSKMMC"));
                Qswszmx1.setNd(rs.getString("YSJCMC"));

                QswszmxList.add(Qswszmx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return QswszmxList;
    }

    /**
     * ����������ȡ��˰��˰֤��ϸ����ֵ����
     * @param qswszmx ��˰��˰֤��ϸ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰��˰֤��ϸ����ֵ����
     * @throws SQLException
     */
    public static Object get(Qswszmx qswszmx, Connection conn) throws
            SQLException {
        Qswszmx Qswszmx1 = new Qswszmx();
        String sql = "select WSZH,NDZB,PZZLDM,SZSMDM,JSJDM,SWJGZZJGDM,ZJHM,SZDM,JSJE,SL,YJHKC,SJSE,SKSSKSRQ,SKSSJSRQ,JZBZ,YSKMDM,YSJCDM,ND,CJRQ,LRRQ,QSZYMJ,LRR,CJR,SZSMMC,SZMC,YSKMMC,YSJCMC from SBDB.SB_JL_QSWSZMX   where ndzb = ?  and pzzldm = ?  and szsmdm = ?  and wszh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, qswszmx.ndzb);
            ps.setString(2, qswszmx.pzzldm);
            ps.setString(3, qswszmx.szsmdm);
            ps.setString(4, qswszmx.wszh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Qswszmx1.setWszh(rs.getString("WSZH"));
                Qswszmx1.setNdzb(rs.getString("NDZB"));
                Qswszmx1.setPzzldm(rs.getString("PZZLDM"));
                Qswszmx1.setSzsmdm(rs.getString("SZSMDM"));
                Qswszmx1.setJsjdm(rs.getString("JSJDM"));
                Qswszmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Qswszmx1.setZjhm(rs.getString("ZJHM"));
                Qswszmx1.setSzdm(rs.getString("SZDM"));
                Qswszmx1.setJsje(rs.getBigDecimal("JSJE"));
                Qswszmx1.setSl(rs.getBigDecimal("SL"));
                Qswszmx1.setYjhkc(rs.getBigDecimal("YJHKC"));
                Qswszmx1.setSjse(rs.getBigDecimal("SJSE"));
                Qswszmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Qswszmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Qswszmx1.setJzbz(rs.getString("JZBZ"));
                Qswszmx1.setYskmdm(rs.getString("YSKMDM"));
                Qswszmx1.setYsjcdm(rs.getString("YSJCDM"));
                Qswszmx1.setNd(rs.getString("ND"));
                Qswszmx1.setNd(rs.getString("LRR"));
                Qswszmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Qswszmx1.setQszymj(rs.getBigDecimal("QSZYMJ"));
                Qswszmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Qswszmx1.setNd(rs.getString("CJR"));
                Qswszmx1.setNd(rs.getString("SZSMMC"));
                Qswszmx1.setNd(rs.getString("SZMC"));
                Qswszmx1.setNd(rs.getString("YSKMMC"));
                Qswszmx1.setNd(rs.getString("YSJCMC"));

            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Qswszmx1;
    }

    /**
     * ����һ����˰��˰֤��ϸ���¼��������ӡ��˰֤���ź����ʹ��
     * @param sql String
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String sql, Connection conn) throws SQLException {
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.execute(sql);

        } catch (SQLException e) {
            throw e;
        } finally {
            stm.close();
        }
    }

}
