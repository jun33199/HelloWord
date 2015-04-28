package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Bz;


/**
 * ���ִ����DAO
 */
public class BzDAO extends BaseDAO {

    /**
     * ����һ�����ִ�����¼
     * @param bz ���ִ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Bz bz, Connection conn) throws SQLException {
        String sql = "insert into DMDB.GY_DM_BZ (BZDM,BZMC,ZHGXSJ,LRR,LRRQ,ZXBS,XSSX) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //���ִ���
            ps.setString(1, bz.bzdm);
            //��������
            ps.setString(2, bz.bzmc);
            //������ʱ��
            ps.setTimestamp(3, bz.zhgxsj);
            //¼����
            ps.setString(4, bz.lrr);
            //¼������
            ps.setTimestamp(5, bz.lrrq);
            //ע����ʶ
            ps.setString(6, bz.zxbs);
            //��ʾ˳��
            ps.setBigDecimal(7, bz.xssx);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ�����ִ�����¼
     * @param bz ���ִ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Bz bz, Connection conn) throws SQLException {
        String sql = "update  DMDB.GY_DM_BZ set BZDM=?,BZMC=?,ZHGXSJ=?,LRR=?,LRRQ=?,ZXBS=?,XSSX=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, bz.bzdm);
            ps.setString(2, bz.bzmc);
            ps.setTimestamp(3, bz.zhgxsj);
            ps.setString(4, bz.lrr);
            ps.setTimestamp(5, bz.lrrq);
            ps.setString(6, bz.zxbs);
            ps.setBigDecimal(7, bz.xssx);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ���������ִ�����¼
     * @param bzList ���ִ����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList bzList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.GY_DM_BZ  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < bzList.size(); i++) {
                Bz bz = (Bz) bzList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ���ִ����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ���ִ����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList BzList = new ArrayList();
        String sql =
                "select BZDM,BZMC,ZHGXSJ,LRR,LRRQ,ZXBS,XSSX from DMDB.GY_DM_BZ " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bz Bz1 = new Bz();
                Bz1.setBzdm(rs.getString("BZDM"));
                Bz1.setBzmc(rs.getString("BZMC"));
                Bz1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Bz1.setLrr(rs.getString("LRR"));
                Bz1.setLrrq(rs.getTimestamp("LRRQ"));
                Bz1.setZxbs(rs.getString("ZXBS"));
                Bz1.setXssx(rs.getBigDecimal("XSSX"));
                BzList.add(Bz1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return BzList;
    }

    /**
     * ����������ȡ���ִ����ֵ����
     * @param bz ���ִ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return���ִ����ֵ����
     * @throws SQLException
     */
    public static Object get(Bz bz, Connection conn) throws SQLException {
        Bz Bz1 = new Bz();
        String sql =
                "select BZDM,BZMC,ZHGXSJ,LRR,LRRQ,ZXBS,XSSX from DMDB.GY_DM_BZ   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Bz1.setBzdm(rs.getString("BZDM"));
                Bz1.setBzmc(rs.getString("BZMC"));
                Bz1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Bz1.setLrr(rs.getString("LRR"));
                Bz1.setLrrq(rs.getTimestamp("LRRQ"));
                Bz1.setZxbs(rs.getString("ZXBS"));
                Bz1.setXssx(rs.getBigDecimal("XSSX"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Bz1;
    }


}
