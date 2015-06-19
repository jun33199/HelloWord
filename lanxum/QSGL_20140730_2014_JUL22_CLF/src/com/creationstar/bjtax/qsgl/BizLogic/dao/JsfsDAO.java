package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsfs;


/**
 * ��˰��ʽ�����DAO
 */
public class JsfsDAO extends BaseDAO {

    /**
     * ����һ����˰��ʽ������¼
     * @param jsfs ��˰��ʽ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Jsfs jsfs, Connection conn) throws SQLException {
        String sql = "insert into DMDB.QS_DM_JSFS (JSFSDM,JSFSMC,LRR,LRRQ,BZ,ZXBS) values (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //��˰��ʽ����
            ps.setString(1, jsfs.jsfsdm);
            //��˰��ʽ����
            ps.setString(2, jsfs.jsfsmc);
            //¼����
            ps.setString(3, jsfs.lrr);
            //¼������
            ps.setTimestamp(4, jsfs.lrrq);
            //��ע
            ps.setString(5, jsfs.bz);
            //ע����ʶ
            ps.setString(6, jsfs.zxbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰��ʽ������¼
     * @param jsfs ��˰��ʽ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Jsfs jsfs, Connection conn) throws SQLException {
        String sql = "update  DMDB.QS_DM_JSFS set JSFSDM=?,JSFSMC=?,LRR=?,LRRQ=?,BZ=?,ZXBS=?   where jsfsdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsfs.jsfsdm);
            ps.setString(2, jsfs.jsfsmc);
            ps.setString(3, jsfs.lrr);
            ps.setTimestamp(4, jsfs.lrrq);
            ps.setString(5, jsfs.bz);
            ps.setString(6, jsfs.zxbs);
            ps.setString(7, jsfs.jsfsdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������˰��ʽ������¼
     * @param jsfsList ��˰��ʽ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList jsfsList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.QS_DM_JSFS  where jsfsdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < jsfsList.size(); i++) {
                Jsfs jsfs = (Jsfs) jsfsList.get(i);
                ps.setString(1, jsfs.jsfsdm);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ��˰��ʽ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰��ʽ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList JsfsList = new ArrayList();
        String sql =
                "select JSFSDM,JSFSMC,LRR,LRRQ,BZ,ZXBS from DMDB.QS_DM_JSFS " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jsfs Jsfs1 = new Jsfs();
                Jsfs1.setJsfsdm(rs.getString("JSFSDM"));
                Jsfs1.setJsfsmc(rs.getString("JSFSMC"));
                Jsfs1.setLrr(rs.getString("LRR"));
                Jsfs1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsfs1.setBz(rs.getString("BZ"));
                Jsfs1.setZxbs(rs.getString("ZXBS"));
                JsfsList.add(Jsfs1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return JsfsList;
    }

    /**
     * ����������ȡ��˰��ʽ�����ֵ����
     * @param jsfs ��˰��ʽ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰��ʽ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Jsfs jsfs, Connection conn) throws SQLException {
        Jsfs Jsfs1 = new Jsfs();
        String sql =
                "select JSFSDM,JSFSMC,LRR,LRRQ,BZ,ZXBS from DMDB.QS_DM_JSFS   where jsfsdm = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsfs.jsfsdm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jsfs1.setJsfsdm(rs.getString("JSFSDM"));
                Jsfs1.setJsfsmc(rs.getString("JSFSMC"));
                Jsfs1.setLrr(rs.getString("LRR"));
                Jsfs1.setLrrq(rs.getTimestamp("LRRQ"));
                Jsfs1.setBz(rs.getString("BZ"));
                Jsfs1.setZxbs(rs.getString("ZXBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Jsfs1;
    }


}
