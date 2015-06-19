package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.ttsoft.common.util.Debug;

/**
 * ����������ϢDAO
 */
public class DrpcInfoDAO extends BaseDAO {
    /**
     * ����һ��������Ϣ��¼
     * @param drpcInfo ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Drpcinfo drpcInfo, Connection conn, String sjly) throws
            SQLException {
        String sql = "insert into QSDB.QS_JL_DRPCINFO (drpch,drbs,tgzlx,tgzmc,tgzgjdm,tgzgjmc,tgzsfzjlx,tgzsfzjhm,tgzjsjdm,tjsj,drsj,jsfsdm,jsfmc,sjly) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            System.out.println("////////////////");
            System.out.println("�������κţ�" + drpcInfo.drpch);
            System.out.println("���������" + drpcInfo.drbs);
            System.out.println("////////////////");

            //�������κ�
            ps.setString(1, drpcInfo.drpch);
            //�������
            ps.setBigDecimal(2, drpcInfo.drbs);
            //�����ṩ������
            ps.setString(3, drpcInfo.tgzlx);
            //�����ṩ������
            ps.setString(4, drpcInfo.tgzmc);
            //�����ṩ�߹�������
            ps.setString(5, drpcInfo.tgzgjdm);
            //�����ṩ�߹�������
            ps.setString(6, drpcInfo.tgzgjmc);
            //�����ṩ�����֤������
            ps.setString(7, drpcInfo.tgzsfzjlx);
            //�����ṩ�����֤������
            ps.setString(8, drpcInfo.tgzsfzjhm);
            //�����ṩ�߼��������
            ps.setString(9, drpcInfo.tgzjsjdm);
            //�ύʱ��
            ps.setTimestamp(10, drpcInfo.tjsj);
            //����ʱ��
            ps.setTimestamp(11, drpcInfo.drsj);
            ps.setString(12, drpcInfo.jsfsdm);
            ps.setString(13, drpcInfo.jsfmc);
            //������Դ
            ps.setString(14, sjly);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��������Ϣ��¼
     * @param DrpcInfo ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Drpcinfo drpcInfo, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_DRPCINFO set drpch=?,drbs=?,tgzlx=?,tgzmc=?,tgzgjdm=?,tgzgjmc=?,tgzsfzjlx=?,tgzsfzjhm=?,tgzjsjdm=?,tjsj=?,drsj=?,jsfsdm=?,jsfmc=?   where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�������κ�
            ps.setString(1, drpcInfo.drpch);
            //�������
            ps.setBigDecimal(2, drpcInfo.drbs);
            //�����ṩ������
            ps.setString(3, drpcInfo.tgzlx);
            //�����ṩ������
            ps.setString(4, drpcInfo.tgzmc);
            //�����ṩ�߹�������
            ps.setString(5, drpcInfo.tgzgjdm);
            //�����ṩ�߹�������
            ps.setString(6, drpcInfo.tgzgjmc);
            //�����ṩ�����֤������
            ps.setString(7, drpcInfo.tgzsfzjhm);
            //�����ṩ�����֤������
            ps.setString(8, drpcInfo.tgzsfzjhm);
            //�����ṩ�߼��������
            ps.setString(9, drpcInfo.tgzjsjdm);
            //�ύʱ��
            ps.setTimestamp(10, drpcInfo.tjsj);
            //����ʱ��
            ps.setTimestamp(11, drpcInfo.drsj);
            ps.setString(12, drpcInfo.jsfsdm);
            ps.setString(13, drpcInfo.jsfmc);
            ps.setString(14, drpcInfo.drpch);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������������Ϣ��¼
     * @param drpcInfoList ������������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList drpcInfoList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_DRPCINFO where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < drpcInfoList.size(); i++) {
                Drpcinfo drpcinfo = (Drpcinfo) drpcInfoList.get(i);
                ps.setString(1, drpcinfo.drpch);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ������Ϣ��ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList DrpcList = new ArrayList();
        String sql = "select drpch,drbs,tgzlx,tgzmc,tgzgjdm,tgzgjmc,tgzsfzjlx,tgzsfzjhm,tgzjsjdm,tjsj,drsj,jsfsdm,jsfmc from QSDB.QS_JL_DRPCINFO " +
                     condition;
        Debug.out("������Ϣ���Ĳ�ѯ��� " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Drpcinfo drpcinfo = new Drpcinfo();
                drpcinfo.setDrpch(rs.getString("drpch"));
                drpcinfo.setDrbs(rs.getBigDecimal("drbs"));
                drpcinfo.setTgzlx(rs.getString("tgzlx"));
                drpcinfo.setTgzmc(rs.getString("tgzmc"));
                drpcinfo.setTgzgjdm(rs.getString("tgzgjdm"));
                drpcinfo.setTgzgjmc(rs.getString("tgzgjmc"));
                drpcinfo.setTgzsfzjlx(rs.getString("tgzsfzjlx"));
                drpcinfo.setTgzsfzjhm(rs.getString("tgzsfzjhm"));
                drpcinfo.setTgzjsjdm(rs.getString("tgzjsjdm"));
                drpcinfo.setTjsj(rs.getTimestamp("tjsj"));
                drpcinfo.setDrsj(rs.getTimestamp("drsj"));
                drpcinfo.setJsfsdm(rs.getString("jsfsdm"));
                drpcinfo.setJsfmc(rs.getString("jsfmc"));
                DrpcList.add(drpcinfo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return DrpcList;
    }

    /**
     * ����������ȡ������Ϣ����ֵ����
     * @param drpcinfo ������Ϣֵ����
     * @param conn ���ݿ����Ӷ���
     * @return������Ϣֵ����
     * @throws SQLException
     */
    public static Object get(Drpcinfo drpcinfo, Connection conn) throws
            SQLException {
        Drpcinfo drpcinfo1 = new Drpcinfo();
        String sql = "select drpch,drbs,tgzlx,tgzmc,tgzgjdm,tgzgjmc,tgzsfzjlx,tgzsfzjhm,tgzjsjdm,tjsj,drsj,jsfsdm,jsfmc from QSDB.QS_JL_DRPCINFO   where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, drpcinfo.drpch);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                drpcinfo1.setDrpch(rs.getString("drpch"));
                drpcinfo1.setDrbs(rs.getBigDecimal("drbs"));
                drpcinfo1.setTgzlx(rs.getString("tgzlx"));
                drpcinfo1.setTgzmc(rs.getString("tgzmc"));
                drpcinfo1.setTgzgjdm(rs.getString("tgzgjdm"));
                drpcinfo1.setTgzgjmc(rs.getString("tgzgjmc"));
                drpcinfo1.setTgzsfzjlx(rs.getString("tgzsfzjlx"));
                drpcinfo1.setTgzsfzjhm(rs.getString("tgzsfzjhm"));
                drpcinfo1.setTgzjsjdm(rs.getString("tgzjsjdm"));
                drpcinfo1.setTjsj(rs.getTimestamp("tjsj"));
                drpcinfo1.setDrsj(rs.getTimestamp("drsj"));
                drpcinfo1.setJsfsdm(rs.getString("jsfsdm"));
                drpcinfo1.setJsfmc(rs.getString("jsfmc"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return drpcinfo1;
    }

    /**
     * ����һ���������������¼״̬
     * @param zcwh ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateTjsj(Drpcinfo drif, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_DRPCINFO set TJSJ=? where drpch = ?";
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql);
            //��˰������
            ps.setTimestamp(1, drif.getTjsj());
            //�걨���
            ps.setString(2, drif.getDrpch());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ��������Ϣ��¼
     * @param drpcInfoList ������������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void deletePc(String pch, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_DRPCINFO where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pch);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
