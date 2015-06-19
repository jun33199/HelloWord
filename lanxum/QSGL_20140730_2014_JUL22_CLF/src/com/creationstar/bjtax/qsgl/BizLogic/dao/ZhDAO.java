package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;


/**
 * nullDAO
 */
public class ZhDAO extends BaseDAO {

    /**
     * ����һ��null��¼
     * @param zh nullֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Zh zh, Connection conn) throws SQLException {
        String sql = "insert into DMDB.PZ_DM_ZH (ZHDM,ZHMC,LXDH,SJZHDM,ZTBS,LRR,LRRQ,SWJGZZJGDM,LXBS,JSJDM) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�ʻ�����
            ps.setString(1, zh.zhdm);
            //�ʻ�����
            ps.setString(2, zh.zhmc);
            //��ϵ�绰(�˻�)
            ps.setString(3, zh.lxdh);
            //�ϼ��ʻ�����
            ps.setString(4, zh.sjzhdm);
            //״̬��ʶ
            ps.setString(5, zh.ztbs);
            //¼����
            ps.setString(6, zh.lrr);
            //¼������
            ps.setTimestamp(7, zh.lrrq);
            //˰�������֯��������
            ps.setString(8, zh.swjgzzjgdm);
            //���ͱ�ʶ
            ps.setString(9, zh.lxbs);
            //���������
            ps.setString(10, zh.jsjdm);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��null��¼
     * @param zh nullֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Zh zh, Connection conn) throws SQLException {
        String sql = "update  DMDB.PZ_DM_ZH set ZHDM=?,ZHMC=?,LXDH=?,SJZHDM=?,ZTBS=?,LRR=?,LRRQ=?,SWJGZZJGDM=?,LXBS=?,JSJDM=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zh.zhdm);
            ps.setString(2, zh.zhmc);
            ps.setString(3, zh.lxdh);
            ps.setString(4, zh.sjzhdm);
            ps.setString(5, zh.ztbs);
            ps.setString(6, zh.lrr);
            ps.setTimestamp(7, zh.lrrq);
            ps.setString(8, zh.swjgzzjgdm);
            ps.setString(9, zh.lxbs);
            ps.setString(10, zh.jsjdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������null��¼
     * @param zhList nullֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList zhList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.PZ_DM_ZH  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < zhList.size(); i++) {
                Zh zh = (Zh) zhList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡnullֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList nullֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList ZhList = new ArrayList();
        String sql = "select ZHDM,ZHMC,LXDH,SJZHDM,ZTBS,LRR,LRRQ,SWJGZZJGDM,LXBS,JSJDM from DMDB.PZ_DM_ZH " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Zh Zh1 = new Zh();
                Zh1.setZhdm(rs.getString("ZHDM"));
                Zh1.setZhmc(rs.getString("ZHMC"));
                Zh1.setLxdh(rs.getString("LXDH"));
                Zh1.setSjzhdm(rs.getString("SJZHDM"));
                Zh1.setZtbs(rs.getString("ZTBS"));
                Zh1.setLrr(rs.getString("LRR"));
                Zh1.setLrrq(rs.getTimestamp("LRRQ"));
                Zh1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Zh1.setLxbs(rs.getString("LXBS"));
                Zh1.setJsjdm(rs.getString("JSJDM"));
                ZhList.add(Zh1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return ZhList;
    }

    /**
     * ����������ȡnullֵ����
     * @param zh nullֵ����
     * @param conn ���ݿ����Ӷ���
     * @returnnullֵ����
     * @throws SQLException
     */
    public static Object get(Zh zh, Connection conn) throws SQLException {
        Zh Zh1 = new Zh();
        String sql = "select ZHDM,ZHMC,LXDH,SJZHDM,ZTBS,LRR,LRRQ,SWJGZZJGDM,LXBS,JSJDM from DMDB.PZ_DM_ZH   where ZHDM='" +
                     zh.zhdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Zh1.setZhdm(rs.getString("ZHDM"));
                Zh1.setZhmc(rs.getString("ZHMC"));
                Zh1.setLxdh(rs.getString("LXDH"));
                Zh1.setSjzhdm(rs.getString("SJZHDM"));
                Zh1.setZtbs(rs.getString("ZTBS"));
                Zh1.setLrr(rs.getString("LRR"));
                Zh1.setLrrq(rs.getTimestamp("LRRQ"));
                Zh1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Zh1.setLxbs(rs.getString("LXBS"));
                Zh1.setJsjdm(rs.getString("JSJDM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Zh1;
    }


}
