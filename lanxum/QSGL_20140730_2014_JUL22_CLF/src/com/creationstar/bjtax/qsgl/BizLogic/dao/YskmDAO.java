package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;


/**
 * Ԥ���Ŀ�����DAO
 */
public class YskmDAO extends BaseDAO {

    /**
     * ����һ��Ԥ���Ŀ������¼
     * @param yskm Ԥ���Ŀ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Yskm yskm, Connection conn) throws SQLException {
        String sql = "insert into DMDB.KJ_DM_YSKM (YSKMDM,YSKMMC,ZYFCBL,QXFCBL,LRRQ,LRR,SJFCBL,YSJCDM,ZXBS,MRSZSMDM,SXRQ,ND,FJDDM,CCBS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //Ԥ���Ŀ����
            ps.setString(1, yskm.yskmdm);
            //Ԥ���Ŀ����
            ps.setString(2, yskm.yskmmc);
            //����ֳɱ���
            ps.setBigDecimal(3, yskm.zyfcbl);
            //���طֳɱ���
            ps.setBigDecimal(4, yskm.qxfcbl);
            //¼������
            ps.setTimestamp(5, yskm.lrrq);
            //¼����
            ps.setString(6, yskm.lrr);
            //�оֳַɱ���
            ps.setBigDecimal(7, yskm.sjfcbl);
            //Ԥ�㼶�δ���
            ps.setString(8, yskm.ysjcdm);
            //ע����ʶ
            ps.setString(9, yskm.zxbs);
            //Ĭ��˰��˰Ŀ����
            ps.setString(10, yskm.mrszsmdm);
            //��Ч����
            ps.setTimestamp(11, yskm.sxrq);
            //���
            ps.setString(12, yskm.nd);
            //���ڵ����
            ps.setString(13, yskm.fjddm);
            //��α�ʶ
            ps.setString(14, yskm.ccbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��Ԥ���Ŀ������¼
     * @param yskm Ԥ���Ŀ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Yskm yskm, Connection conn) throws SQLException {
        String sql = "update  DMDB.KJ_DM_YSKM set YSKMDM=?,YSKMMC=?,ZYFCBL=?,QXFCBL=?,LRRQ=?,LRR=?,SJFCBL=?,YSJCDM=?,ZXBS=?,MRSZSMDM=?,SXRQ=?,ND=?,FJDDM=?,CCBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, yskm.yskmdm);
            ps.setString(2, yskm.yskmmc);
            ps.setBigDecimal(3, yskm.zyfcbl);
            ps.setBigDecimal(4, yskm.qxfcbl);
            ps.setTimestamp(5, yskm.lrrq);
            ps.setString(6, yskm.lrr);
            ps.setBigDecimal(7, yskm.sjfcbl);
            ps.setString(8, yskm.ysjcdm);
            ps.setString(9, yskm.zxbs);
            ps.setString(10, yskm.mrszsmdm);
            ps.setTimestamp(11, yskm.sxrq);
            ps.setString(12, yskm.nd);
            ps.setString(13, yskm.fjddm);
            ps.setString(14, yskm.ccbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������Ԥ���Ŀ������¼
     * @param yskmList Ԥ���Ŀ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList yskmList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.KJ_DM_YSKM  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < yskmList.size(); i++) {
                Yskm yskm = (Yskm) yskmList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡԤ���Ŀ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList Ԥ���Ŀ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList YskmList = new ArrayList();
        String sql = "select YSKMDM,YSKMMC,ZYFCBL,QXFCBL,LRRQ,LRR,SJFCBL,YSJCDM,ZXBS,MRSZSMDM,SXRQ,ND,FJDDM,CCBS from DMDB.KJ_DM_YSKM " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Yskm Yskm1 = new Yskm();
                Yskm1.setYskmdm(rs.getString("YSKMDM"));
                Yskm1.setYskmmc(rs.getString("YSKMMC"));
                Yskm1.setZyfcbl(rs.getBigDecimal("ZYFCBL"));
                Yskm1.setQxfcbl(rs.getBigDecimal("QXFCBL"));
                Yskm1.setLrrq(rs.getTimestamp("LRRQ"));
                Yskm1.setLrr(rs.getString("LRR"));
                Yskm1.setSjfcbl(rs.getBigDecimal("SJFCBL"));
                Yskm1.setYsjcdm(rs.getString("YSJCDM"));
                Yskm1.setZxbs(rs.getString("ZXBS"));
                Yskm1.setMrszsmdm(rs.getString("MRSZSMDM"));
                Yskm1.setSxrq(rs.getTimestamp("SXRQ"));
                Yskm1.setNd(rs.getString("ND"));
                Yskm1.setFjddm(rs.getString("FJDDM"));
                Yskm1.setCcbs(rs.getString("CCBS"));
                YskmList.add(Yskm1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return YskmList;
    }

    /**
     * ����������ȡԤ���Ŀ�����ֵ����
     * @param yskm Ԥ���Ŀ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @returnԤ���Ŀ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Yskm yskm, Connection conn) throws SQLException {
        Yskm Yskm1 = new Yskm();
        String sql = "select YSKMDM,YSKMMC,ZYFCBL,QXFCBL,LRRQ,LRR,SJFCBL,YSJCDM,ZXBS,MRSZSMDM,SXRQ,ND,FJDDM,CCBS from DMDB.KJ_DM_YSKM   where yskmdm='" +
                     yskm.yskmdm + "'" + " and  nd = to_char(sysdate,'yyyy') ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Yskm1.setYskmdm(rs.getString("YSKMDM"));
                Yskm1.setYskmmc(rs.getString("YSKMMC"));
                Yskm1.setZyfcbl(rs.getBigDecimal("ZYFCBL"));
                Yskm1.setQxfcbl(rs.getBigDecimal("QXFCBL"));
                Yskm1.setLrrq(rs.getTimestamp("LRRQ"));
                Yskm1.setLrr(rs.getString("LRR"));
                Yskm1.setSjfcbl(rs.getBigDecimal("SJFCBL"));
                Yskm1.setYsjcdm(rs.getString("YSJCDM"));
                Yskm1.setZxbs(rs.getString("ZXBS"));
                Yskm1.setMrszsmdm(rs.getString("MRSZSMDM"));
                Yskm1.setSxrq(rs.getTimestamp("SXRQ"));
                Yskm1.setNd(rs.getString("ND"));
                Yskm1.setFjddm(rs.getString("FJDDM"));
                Yskm1.setCcbs(rs.getString("CCBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Yskm1;
    }


}
