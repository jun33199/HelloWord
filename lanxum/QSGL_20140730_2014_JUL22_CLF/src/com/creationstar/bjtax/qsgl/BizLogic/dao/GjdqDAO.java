package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Gjdq;


/**
 * ���������DAO
 */
public class GjdqDAO extends BaseDAO {

    /**
     * ����һ������������¼
     * @param gjdq ���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Gjdq gjdq, Connection conn) throws SQLException {
        String sql = "insert into GY_DM_GJDQ (ZHGXSJ,LRR,LRRQ,ZXBS,GJDQDM,GJDQMC,XSSX) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //������ʱ��
            ps.setTimestamp(1, gjdq.zhgxsj);
            //¼����
            ps.setString(2, gjdq.lrr);
            //¼������
            ps.setTimestamp(3, gjdq.lrrq);
            //ע����ʶ
            ps.setString(4, gjdq.zxbs);
            //�������������
            ps.setString(5, gjdq.gjdqdm);
            //�������������
            ps.setString(6, gjdq.gjdqmc);
            //��ʾ˳��
            ps.setString(7, gjdq.xssx);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ������������¼
     * @param gjdq ���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Gjdq gjdq, Connection conn) throws SQLException {
        String sql = "update  GY_DM_GJDQ set ZHGXSJ=?,LRR=?,LRRQ=?,ZXBS=?,GJDQDM=?,GJDQMC=?,XSSX=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, gjdq.zhgxsj);
            ps.setString(2, gjdq.lrr);
            ps.setTimestamp(3, gjdq.lrrq);
            ps.setString(4, gjdq.zxbs);
            ps.setString(5, gjdq.gjdqdm);
            ps.setString(6, gjdq.gjdqmc);
            ps.setString(7, gjdq.xssx);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ����������������¼
     * @param gjdqList ���������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList gjdqList, Connection conn) throws
            SQLException {
        String sql = "delete from  GY_DM_GJDQ  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < gjdqList.size(); i++) {
                Gjdq gjdq = (Gjdq) gjdqList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ���������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ���������ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList GjdqList = new ArrayList();
        String sql =
                "select ZHGXSJ,LRR,LRRQ,ZXBS,GJDQDM,GJDQMC,XSSX from GY_DM_GJDQ " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gjdq Gjdq1 = new Gjdq();
                Gjdq1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Gjdq1.setLrr(rs.getString("LRR"));
                Gjdq1.setLrrq(rs.getTimestamp("LRRQ"));
                Gjdq1.setZxbs(rs.getString("ZXBS"));
                Gjdq1.setGjdqdm(rs.getString("GJDQDM"));
                Gjdq1.setGjdqmc(rs.getString("GJDQMC"));
                Gjdq1.setXssx(rs.getString("XSSX"));
                GjdqList.add(Gjdq1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return GjdqList;
    }

    /**
     * ����������ȡ���������ֵ����
     * @param gjdq ���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return���������ֵ����
     * @throws SQLException
     */
    public static Object get(Gjdq gjdq, Connection conn) throws SQLException {
        Gjdq Gjdq1 = new Gjdq();
        String sql =
                "select ZHGXSJ,LRR,LRRQ,ZXBS,GJDQDM,GJDQMC,XSSX from GY_DM_GJDQ   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Gjdq1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Gjdq1.setLrr(rs.getString("LRR"));
                Gjdq1.setLrrq(rs.getTimestamp("LRRQ"));
                Gjdq1.setZxbs(rs.getString("ZXBS"));
                Gjdq1.setGjdqdm(rs.getString("GJDQDM"));
                Gjdq1.setGjdqmc(rs.getString("GJDQMC"));
                Gjdq1.setXssx(rs.getString("XSSX"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Gjdq1;
    }


}
