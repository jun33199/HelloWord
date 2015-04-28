package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.ttsoft.common.util.Debug;


/**
 * �걨�ɿ���ϸ����DAO
 */
public class SbjkmxDAO extends BaseDAO {

    /**
     * ����һ���걨�ɿ���ϸ���ݼ�¼
     * @param sbjkmx �걨�ɿ���ϸ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Sbjkmx sbjkmx, Connection conn) throws
            SQLException {
        String sql = "insert into SBDB.SB_JL_SBJKMX (SZSMDM,JKPZH,JSJDM,YSKMDM,YSJCDM,KSSL,JSJE,SJSE,SKSSKSRQ,SKSSJSRQ,RKJE,SBBH,SJFC,QJFC,SWJGZZJGDM,ND,SL,CJRQ,LRRQ,QXDM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //˰��˰Ŀ����
            ps.setString(1, sbjkmx.szsmdm);
            //�ɿ�ƾ֤��
            ps.setString(2, sbjkmx.jkpzh);
            //���������
            ps.setString(3, sbjkmx.jsjdm);
            //Ԥ���Ŀ����
            ps.setString(4, sbjkmx.yskmdm);
            //Ԥ�㼶��
            ps.setString(5, sbjkmx.ysjcdm);
            //��˰����
            ps.setBigDecimal(6, sbjkmx.kssl);
            //��˰���
            ps.setBigDecimal(7, sbjkmx.jsje);
            //ʵ��˰��
            ps.setBigDecimal(8, sbjkmx.sjse);
            //˰��������ʼ����
            ps.setTimestamp(9, sbjkmx.skssksrq);
            //˰��������������
            ps.setTimestamp(10, sbjkmx.skssjsrq);
            //�����
            ps.setBigDecimal(11, sbjkmx.rkje);
            //�걨���
            ps.setString(12, sbjkmx.sbbh);
            //�м��ֳ�
            ps.setBigDecimal(13, sbjkmx.sjfc);
            //�����ֳ�
            ps.setBigDecimal(14, sbjkmx.qjfc);
            //˰�������֯��������
            ps.setString(15, sbjkmx.swjgzzjgdm);
            //���
            ps.setString(16, sbjkmx.nd);
            //˰��
            ps.setBigDecimal(17, sbjkmx.sl);
            //����ʱ��
            ps.setTimestamp(18, sbjkmx.cjrq);
            //¼��ʱ��
            ps.setTimestamp(19, sbjkmx.lrrq);
            //���ش���
            ps.setString(20, sbjkmx.qxdm);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���걨�ɿ���ϸ���ݼ�¼
     * @param sbjkmx �걨�ɿ���ϸ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Sbjkmx sbjkmx, Connection conn) throws
            SQLException {
        String sql = "update  SBDB.SB_JL_SBJKMX set SZSMDM=?,JKPZH=?,JSJDM=?,YSKMDM=?,YSJCDM=?,KSSL=?,JSJE=?,SJSE=?,SKSSKSRQ=?,SKSSJSRQ=?,RKJE=?,SBBH=?,SJFC=?,QJFC=?,SWJGZZJGDM=?,ND=?,SL=?,CJRQ=?,LRRQ=?,QXDM=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbjkmx.szsmdm);
            ps.setString(2, sbjkmx.jkpzh);
            ps.setString(3, sbjkmx.jsjdm);
            ps.setString(4, sbjkmx.yskmdm);
            ps.setString(5, sbjkmx.ysjcdm);
            ps.setBigDecimal(6, sbjkmx.kssl);
            ps.setBigDecimal(7, sbjkmx.jsje);
            ps.setBigDecimal(8, sbjkmx.sjse);
            ps.setTimestamp(9, sbjkmx.skssksrq);
            ps.setTimestamp(10, sbjkmx.skssjsrq);
            ps.setBigDecimal(11, sbjkmx.rkje);
            ps.setString(12, sbjkmx.sbbh);
            ps.setBigDecimal(13, sbjkmx.sjfc);
            ps.setBigDecimal(14, sbjkmx.qjfc);
            ps.setString(15, sbjkmx.swjgzzjgdm);
            ps.setString(16, sbjkmx.nd);
            ps.setBigDecimal(17, sbjkmx.sl);
            ps.setTimestamp(18, sbjkmx.cjrq);
            ps.setTimestamp(19, sbjkmx.lrrq);
            ps.setString(20, sbjkmx.qxdm);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������걨�ɿ���ϸ���ݼ�¼
     * @param sbjkmxList �걨�ɿ���ϸ����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList sbjkmxList, Connection conn) throws
            SQLException {
        String sql = "delete from  SBDB.SB_JL_SBJKMX  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbjkmxList.size(); i++) {
                Sbjkmx sbjkmx = (Sbjkmx) sbjkmxList.get(i);
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
                "delete from  SBDB.SB_JL_SBJKMX  where ");
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
     * ����������ȡ�걨�ɿ���ϸ����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList �걨�ɿ���ϸ����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbjkmxList = new ArrayList();
        String sql = "select SZSMDM,JKPZH,JSJDM,YSKMDM,YSJCDM,KSSL,JSJE,SJSE,SKSSKSRQ,SKSSJSRQ,RKJE,SBBH,SJFC,QJFC,SWJGZZJGDM,ND,SL,CJRQ,LRRQ,QXDM from SBDB.SB_JL_SBJKMX " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sbjkmx Sbjkmx1 = new Sbjkmx();
                Sbjkmx1.setSzsmdm(rs.getString("SZSMDM"));
                Sbjkmx1.setJkpzh(rs.getString("JKPZH"));
                Sbjkmx1.setJsjdm(rs.getString("JSJDM"));
                Sbjkmx1.setYskmdm(rs.getString("YSKMDM"));
                Sbjkmx1.setYsjcdm(rs.getString("YSJCDM"));
                Sbjkmx1.setKssl(rs.getBigDecimal("KSSL"));
                Sbjkmx1.setJsje(rs.getBigDecimal("JSJE"));
                Sbjkmx1.setSjse(rs.getBigDecimal("SJSE"));
                Sbjkmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Sbjkmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Sbjkmx1.setRkje(rs.getBigDecimal("RKJE"));
                Sbjkmx1.setSbbh(rs.getString("SBBH"));
                Sbjkmx1.setSjfc(rs.getBigDecimal("SJFC"));
                Sbjkmx1.setQjfc(rs.getBigDecimal("QJFC"));
                Sbjkmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbjkmx1.setNd(rs.getString("ND"));
                Sbjkmx1.setSl(rs.getBigDecimal("SL"));
                Sbjkmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbjkmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbjkmx1.setQxdm(rs.getString("QXDM"));
                SbjkmxList.add(Sbjkmx1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SbjkmxList;
    }

    /**
     * ����������ȡ�걨�ɿ���ϸ����ֵ����
     * ע�⣺�벻Ҫ�޸ĳɰ�������������ѯ����Ϊ����ֱ�����ɷ�ʽ���ɵĽɿ�����˵��һ���������Ѿ�����ȷ��һ���ɿ�������
     * ��Ϊ��һƱһ˰�ģ�ֻ�л��ܷ�ʽ���ɵĽɿ�����ж��˰Ŀ
     * �ټ���jsjdm��Ϊ�˲�ѯ��Ѹ��
     *
     * @param sbjkmx �걨�ɿ���ϸ����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return�걨�ɿ���ϸ����ֵ����
     * @throws SQLException
     */
    public static Object get(Sbjkmx sbjkmx, Connection conn) throws
            SQLException {
        Sbjkmx Sbjkmx1 = new Sbjkmx();
        String sql = "select SZSMDM,JKPZH,JSJDM,YSKMDM,YSJCDM,KSSL,JSJE,SJSE,SKSSKSRQ,SKSSJSRQ,RKJE,SBBH,SJFC,QJFC,SWJGZZJGDM,ND,SL,CJRQ,LRRQ,QXDM from SBDB.SB_JL_SBJKMX   where JKPZH='" +
                     sbjkmx.jkpzh + "'AND JSJDM='" + sbjkmx.jsjdm + "'";
        Debug.out("sbjkmxDAO get() say: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sbjkmx1.setSzsmdm(rs.getString("SZSMDM"));
                Sbjkmx1.setJkpzh(rs.getString("JKPZH"));
                Sbjkmx1.setJsjdm(rs.getString("JSJDM"));
                Sbjkmx1.setYskmdm(rs.getString("YSKMDM"));
                Sbjkmx1.setYsjcdm(rs.getString("YSJCDM"));
                Sbjkmx1.setKssl(rs.getBigDecimal("KSSL"));
                Sbjkmx1.setJsje(rs.getBigDecimal("JSJE"));
                Sbjkmx1.setSjse(rs.getBigDecimal("SJSE"));
                Sbjkmx1.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                Sbjkmx1.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                Sbjkmx1.setRkje(rs.getBigDecimal("RKJE"));
                Sbjkmx1.setSbbh(rs.getString("SBBH"));
                Sbjkmx1.setSjfc(rs.getBigDecimal("SJFC"));
                Sbjkmx1.setQjfc(rs.getBigDecimal("QJFC"));
                Sbjkmx1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbjkmx1.setNd(rs.getString("ND"));
                Sbjkmx1.setSl(rs.getBigDecimal("SL"));
                Sbjkmx1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbjkmx1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbjkmx1.setQxdm(rs.getString("QXDM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Sbjkmx1;
    }
}
