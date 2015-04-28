package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;


/**
 * ˰�������֯���������DAO
 */
public class SwjgzzjgDAO extends BaseDAO {

    /**
     * ����һ��˰�������֯����������¼
     * @param swjgzzjg ˰�������֯���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Swjgzzjg swjgzzjg, Connection conn) throws
            SQLException {
        String sql = "insert into DMDB.GY_DM_SWJGZZJG (SWJGZZJGDM,QXFJDM,JGZNLX,XZQDM,SWJGZZJGDZ,YZBM,SWJGZZJGDH,SKGK,GKJHH,JGCCGX,SWJGZZJGMC,JC,WSJC,BBBZDWMC,BZ,LRR,LRRQ,ZXBS,GKZZJGDM,JSJDM,FJDDM,CCBS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //˰�������֯��������
            ps.setString(1, swjgzzjg.swjgzzjgdm);
            //���ط־ִ���
            ps.setString(2, swjgzzjg.qxfjdm);
            //����ְ������
            ps.setString(3, swjgzzjg.jgznlx);
            //����������
            ps.setString(4, swjgzzjg.xzqdm);
            //˰�������֯������ַ
            ps.setString(5, swjgzzjg.swjgzzjgdz);
            //��������
            ps.setString(6, swjgzzjg.yzbm);
            //˰�������֯������ϵ�绰
            ps.setString(7, swjgzzjg.swjgzzjgdh);
            //�տ����
            ps.setString(8, swjgzzjg.skgk);
            //���⽻����
            ps.setString(9, swjgzzjg.gkjhh);
            //������ι�ϵ
            ps.setString(10, swjgzzjg.jgccgx);
            //˰�������֯��������
            ps.setString(11, swjgzzjg.swjgzzjgmc);
            //���
            ps.setString(12, swjgzzjg.jc);
            //������
            ps.setString(13, swjgzzjg.wsjc);
            //������Ƶ�λ����
            ps.setString(14, swjgzzjg.bbbzdwmc);
            //��ע
            ps.setString(15, swjgzzjg.bz);
            //¼����
            ps.setString(16, swjgzzjg.lrr);
            //¼������
            ps.setTimestamp(17, swjgzzjg.lrrq);
            //ע����ʶ
            ps.setString(18, swjgzzjg.zxbs);
            //������֯��������
            ps.setString(19, swjgzzjg.gkzzjgdm);
            //���������
            ps.setString(20, swjgzzjg.jsjdm);
            //���ڵ����
            ps.setString(21, swjgzzjg.fjddm);
            //��α�ʶ
            ps.setString(22, swjgzzjg.ccbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��˰�������֯����������¼
     * @param swjgzzjg ˰�������֯���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Swjgzzjg swjgzzjg, Connection conn) throws
            SQLException {
        String sql = "update  DMDB.GY_DM_SWJGZZJG set SWJGZZJGDM=?,QXFJDM=?,JGZNLX=?,XZQDM=?,SWJGZZJGDZ=?,YZBM=?,SWJGZZJGDH=?,SKGK=?,GKJHH=?,JGCCGX=?,SWJGZZJGMC=?,JC=?,WSJC=?,BBBZDWMC=?,BZ=?,LRR=?,LRRQ=?,ZXBS=?,GKZZJGDM=?,JSJDM=?,FJDDM=?,CCBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, swjgzzjg.swjgzzjgdm);
            ps.setString(2, swjgzzjg.qxfjdm);
            ps.setString(3, swjgzzjg.jgznlx);
            ps.setString(4, swjgzzjg.xzqdm);
            ps.setString(5, swjgzzjg.swjgzzjgdz);
            ps.setString(6, swjgzzjg.yzbm);
            ps.setString(7, swjgzzjg.swjgzzjgdh);
            ps.setString(8, swjgzzjg.skgk);
            ps.setString(9, swjgzzjg.gkjhh);
            ps.setString(10, swjgzzjg.jgccgx);
            ps.setString(11, swjgzzjg.swjgzzjgmc);
            ps.setString(12, swjgzzjg.jc);
            ps.setString(13, swjgzzjg.wsjc);
            ps.setString(14, swjgzzjg.bbbzdwmc);
            ps.setString(15, swjgzzjg.bz);
            ps.setString(16, swjgzzjg.lrr);
            ps.setTimestamp(17, swjgzzjg.lrrq);
            ps.setString(18, swjgzzjg.zxbs);
            ps.setString(19, swjgzzjg.gkzzjgdm);
            ps.setString(20, swjgzzjg.jsjdm);
            ps.setString(21, swjgzzjg.fjddm);
            ps.setString(22, swjgzzjg.ccbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������˰�������֯����������¼
     * @param swjgzzjgList ˰�������֯���������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList swjgzzjgList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.GY_DM_SWJGZZJG  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < swjgzzjgList.size(); i++) {
                Swjgzzjg swjgzzjg = (Swjgzzjg) swjgzzjgList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ˰�������֯���������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ˰�������֯���������ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SwjgzzjgList = new ArrayList();
        String sql = "select SWJGZZJGDM,QXFJDM,JGZNLX,XZQDM,SWJGZZJGDZ,YZBM,SWJGZZJGDH,SKGK,GKJHH,JGCCGX,SWJGZZJGMC,JC,WSJC,BBBZDWMC,BZ,LRR,LRRQ,ZXBS,GKZZJGDM,JSJDM,FJDDM,CCBS,LWZT from DMDB.GY_DM_SWJGZZJG " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Swjgzzjg Swjgzzjg1 = new Swjgzzjg();
                Swjgzzjg1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Swjgzzjg1.setQxfjdm(rs.getString("QXFJDM"));
                Swjgzzjg1.setJgznlx(rs.getString("JGZNLX"));
                Swjgzzjg1.setXzqdm(rs.getString("XZQDM"));
                Swjgzzjg1.setSwjgzzjgdz(rs.getString("SWJGZZJGDZ"));
                Swjgzzjg1.setYzbm(rs.getString("YZBM"));
                Swjgzzjg1.setSwjgzzjgdh(rs.getString("SWJGZZJGDH"));
                Swjgzzjg1.setSkgk(rs.getString("SKGK"));
                Swjgzzjg1.setGkjhh(rs.getString("GKJHH"));
                Swjgzzjg1.setJgccgx(rs.getString("JGCCGX"));
                Swjgzzjg1.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
                Swjgzzjg1.setJc(rs.getString("JC"));
                Swjgzzjg1.setWsjc(rs.getString("WSJC"));
                Swjgzzjg1.setBbbzdwmc(rs.getString("BBBZDWMC"));
                Swjgzzjg1.setBz(rs.getString("BZ"));
                Swjgzzjg1.setLrr(rs.getString("LRR"));
                Swjgzzjg1.setLrrq(rs.getTimestamp("LRRQ"));
                Swjgzzjg1.setZxbs(rs.getString("ZXBS"));
                Swjgzzjg1.setGkzzjgdm(rs.getString("GKZZJGDM"));
                Swjgzzjg1.setJsjdm(rs.getString("JSJDM"));
                Swjgzzjg1.setFjddm(rs.getString("FJDDM"));
                Swjgzzjg1.setCcbs(rs.getString("CCBS"));
                Swjgzzjg1.setLwzt(rs.getString("LWZT"));
                SwjgzzjgList.add(Swjgzzjg1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SwjgzzjgList;
    }

    /**
     * ����������ȡ˰�������֯���������ֵ����
     * @param swjgzzjg ˰�������֯���������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return˰�������֯���������ֵ����
     * @throws SQLException
     */
    public static Object get(com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg
                             swjgzzjg, Connection conn) throws SQLException {
        Swjgzzjg Swjgzzjg1 = new Swjgzzjg();
        String sql = "select SWJGZZJGDM,QXFJDM,JGZNLX,XZQDM,SWJGZZJGDZ,YZBM,SWJGZZJGDH,SKGK,GKJHH,JGCCGX,SWJGZZJGMC,JC,WSJC,BBBZDWMC,BZ,LRR,LRRQ,ZXBS,GKZZJGDM,JSJDM,FJDDM,CCBS,LWZT from DMDB.GY_DM_SWJGZZJG   where swjgzzjgdm='" +
                     swjgzzjg.swjgzzjgdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Swjgzzjg1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Swjgzzjg1.setQxfjdm(rs.getString("QXFJDM"));
                Swjgzzjg1.setJgznlx(rs.getString("JGZNLX"));
                Swjgzzjg1.setXzqdm(rs.getString("XZQDM"));
                Swjgzzjg1.setSwjgzzjgdz(rs.getString("SWJGZZJGDZ"));
                Swjgzzjg1.setYzbm(rs.getString("YZBM"));
                Swjgzzjg1.setSwjgzzjgdh(rs.getString("SWJGZZJGDH"));
                Swjgzzjg1.setSkgk(rs.getString("SKGK"));
                Swjgzzjg1.setGkjhh(rs.getString("GKJHH"));
                Swjgzzjg1.setJgccgx(rs.getString("JGCCGX"));
                Swjgzzjg1.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
                Swjgzzjg1.setJc(rs.getString("JC"));
                Swjgzzjg1.setWsjc(rs.getString("WSJC"));
                Swjgzzjg1.setBbbzdwmc(rs.getString("BBBZDWMC"));
                Swjgzzjg1.setBz(rs.getString("BZ"));
                Swjgzzjg1.setLrr(rs.getString("LRR"));
                Swjgzzjg1.setLrrq(rs.getTimestamp("LRRQ"));
                Swjgzzjg1.setZxbs(rs.getString("ZXBS"));
                Swjgzzjg1.setGkzzjgdm(rs.getString("GKZZJGDM"));
                Swjgzzjg1.setJsjdm(rs.getString("JSJDM"));
                Swjgzzjg1.setFjddm(rs.getString("FJDDM"));
                Swjgzzjg1.setCcbs(rs.getString("CCBS"));
                Swjgzzjg1.setLwzt(rs.getString("LWZT"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Swjgzzjg1;
    }


}
