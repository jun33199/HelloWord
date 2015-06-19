package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;


/**
 * ֤�����ʹ����DAO
 */
public class ZjlxDAO extends BaseDAO {

    /**
     * ����һ��֤�����ʹ�����¼
     * @param zjlx ֤�����ʹ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Zjlx zjlx, Connection conn) throws SQLException {
        String sql = "insert into DMDB.GY_DM_ZJLX (ZJLXDM,ZJLXMC,ZHGXSJ,LRR,LRRQ,ZXBS,CLFS) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //֤�����ʹ���
            ps.setString(1, zjlx.zjlxdm);
            //֤����������
            ps.setString(2, zjlx.zjlxmc);
            //������ʱ��
            ps.setTimestamp(3, zjlx.zhgxsj);
            //¼����
            ps.setString(4, zjlx.lrr);
            //¼������
            ps.setTimestamp(5, zjlx.lrrq);
            //ע����ʶ
            ps.setString(6, zjlx.zxbs);
            //����ʽ
            ps.setString(7, zjlx.clfs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��֤�����ʹ�����¼
     * @param zjlx ֤�����ʹ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Zjlx zjlx, Connection conn) throws SQLException {
        String sql = "update  DMDB.GY_DM_ZJLX set ZJLXDM=?,ZJLXMC=?,ZHGXSJ=?,LRR=?,LRRQ=?,ZXBS=?,CLFS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zjlx.zjlxdm);
            ps.setString(2, zjlx.zjlxmc);
            ps.setTimestamp(3, zjlx.zhgxsj);
            ps.setString(4, zjlx.lrr);
            ps.setTimestamp(5, zjlx.lrrq);
            ps.setString(6, zjlx.zxbs);
            ps.setString(7, zjlx.clfs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������֤�����ʹ�����¼
     * @param zjlxList ֤�����ʹ����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList zjlxList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.GY_DM_ZJLX  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < zjlxList.size(); i++) {
                Zjlx zjlx = (Zjlx) zjlxList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ֤�����ʹ����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ֤�����ʹ����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList ZjlxList = new ArrayList();
        String sql =
                "select ZJLXDM,ZJLXMC,ZHGXSJ,LRR,LRRQ,ZXBS,CLFS from DMDB.GY_DM_ZJLX " +
                condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Zjlx Zjlx1 = new Zjlx();
                Zjlx1.setZjlxdm(rs.getString("ZJLXDM"));
                Zjlx1.setZjlxmc(rs.getString("ZJLXMC"));
                Zjlx1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Zjlx1.setLrr(rs.getString("LRR"));
                Zjlx1.setLrrq(rs.getTimestamp("LRRQ"));
                Zjlx1.setZxbs(rs.getString("ZXBS"));
                Zjlx1.setClfs(rs.getString("CLFS"));
                ZjlxList.add(Zjlx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return ZjlxList;
    }

    /**
     * ����������ȡ֤�����ʹ����ֵ����
     * @param zjlx ֤�����ʹ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return֤�����ʹ����ֵ����
     * @throws SQLException
     */
    public static Object get(Zjlx zjlx, Connection conn) throws SQLException {
        Zjlx Zjlx1 = new Zjlx();
        String sql =
                "select ZJLXDM,ZJLXMC,ZHGXSJ,LRR,LRRQ,ZXBS,CLFS from DMDB.GY_DM_ZJLX   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Zjlx1.setZjlxdm(rs.getString("ZJLXDM"));
                Zjlx1.setZjlxmc(rs.getString("ZJLXMC"));
                Zjlx1.setZhgxsj(rs.getTimestamp("ZHGXSJ"));
                Zjlx1.setLrr(rs.getString("LRR"));
                Zjlx1.setLrrq(rs.getTimestamp("LRRQ"));
                Zjlx1.setZxbs(rs.getString("ZXBS"));
                Zjlx1.setClfs(rs.getString("CLFS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Zjlx1;
    }


}
