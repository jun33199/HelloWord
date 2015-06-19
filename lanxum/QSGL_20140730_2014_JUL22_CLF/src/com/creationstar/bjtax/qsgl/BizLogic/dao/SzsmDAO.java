package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;


/**
 * ˰��˰Ŀ�����DAO
 */
public class SzsmDAO extends BaseDAO {

    /**
     * ����һ��˰��˰Ŀ������¼
     * @param szsm ˰��˰Ŀ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Szsm szsm, Connection conn) throws SQLException {
        String sql = "insert into DMDB.SB_DM_SZSM (SZSMDM,SZSMMC,SL,JSJS,SSKCS,JFYS,JLDW,ZXKSRQ,ZXJSRQ,ASLJBS,YNSQSS,YNSZZS,JSGS,SJLYZD,ZDKBJBZ,PYSY,BZ,SFFJS,ZQLXDM,FJDDM,BHSDSSL,BHSDSQS,BHSDSZZ,BHSDSKCS,ZXBS,CCBS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //˰��˰Ŀ����
            ps.setString(1, szsm.szsmdm);
            //˰��˰Ŀ����
            ps.setString(2, szsm.szsmmc);
            //˰��
            ps.setBigDecimal(3, szsm.sl);
            //��˰����
            ps.setBigDecimal(4, szsm.jsjs);
            //����۳���
            ps.setBigDecimal(5, szsm.sskcs);
            //�����ö�
            ps.setBigDecimal(6, szsm.jfys);
            //������λ
            ps.setString(7, szsm.jldw);
            //ִ�п�ʼ����
            ps.setTimestamp(8, szsm.zxksrq);
            //ִ�н�������
            ps.setTimestamp(9, szsm.zxjsrq);
            //�������Ʊ�ʶ
            ps.setString(10, szsm.asljbs);
            //Ӧ��˰��ʼ��
            ps.setBigDecimal(11, szsm.ynsqss);
            //Ӧ��˰��ֹ��
            ps.setBigDecimal(12, szsm.ynszzs);
            //���㹫ʽ
            ps.setString(13, szsm.jsgs);
            //������Դ�ֶ�
            ps.setString(14, szsm.sjlyzd);
            //�����ֶοɱ༭��־
            ps.setString(15, szsm.zdkbjbz);
            //ƴ������
            ps.setString(16, szsm.pysy);
            //��ע
            ps.setString(17, szsm.bz);
            //�Ƿ񸽼�˰
            ps.setString(18, szsm.sffjs);
            //�������ʹ���
            ps.setString(19, szsm.zqlxdm);
            //���ڵ����
            ps.setString(20, szsm.fjddm);
            //����˰����˰��
            ps.setBigDecimal(21, szsm.bhsdssl);
            //����˰������ʼ
            ps.setBigDecimal(22, szsm.bhsdsqs);
            //����˰������ֹ
            ps.setBigDecimal(23, szsm.bhsdszz);
            //����˰��������۳���
            ps.setBigDecimal(24, szsm.bhsdskcs);
            //ע����ʶ
            ps.setString(25, szsm.zxbs);
            //��α�ʶ
            ps.setString(26, szsm.ccbs);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ��˰��˰Ŀ������¼
     * @param szsm ˰��˰Ŀ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Szsm szsm, Connection conn) throws SQLException {
        String sql = "update  DMDB.SB_DM_SZSM set SZSMDM=?,SZSMMC=?,SL=?,JSJS=?,SSKCS=?,JFYS=?,JLDW=?,ZXKSRQ=?,ZXJSRQ=?,ASLJBS=?,YNSQSS=?,YNSZZS=?,JSGS=?,SJLYZD=?,ZDKBJBZ=?,PYSY=?,BZ=?,SFFJS=?,ZQLXDM=?,FJDDM=?,BHSDSSL=?,BHSDSQS=?,BHSDSZZ=?,BHSDSKCS=?,ZXBS=?,CCBS=?   where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, szsm.szsmdm);
            ps.setString(2, szsm.szsmmc);
            ps.setBigDecimal(3, szsm.sl);
            ps.setBigDecimal(4, szsm.jsjs);
            ps.setBigDecimal(5, szsm.sskcs);
            ps.setBigDecimal(6, szsm.jfys);
            ps.setString(7, szsm.jldw);
            ps.setTimestamp(8, szsm.zxksrq);
            ps.setTimestamp(9, szsm.zxjsrq);
            ps.setString(10, szsm.asljbs);
            ps.setBigDecimal(11, szsm.ynsqss);
            ps.setBigDecimal(12, szsm.ynszzs);
            ps.setString(13, szsm.jsgs);
            ps.setString(14, szsm.sjlyzd);
            ps.setString(15, szsm.zdkbjbz);
            ps.setString(16, szsm.pysy);
            ps.setString(17, szsm.bz);
            ps.setString(18, szsm.sffjs);
            ps.setString(19, szsm.zqlxdm);
            ps.setString(20, szsm.fjddm);
            ps.setBigDecimal(21, szsm.bhsdssl);
            ps.setBigDecimal(22, szsm.bhsdsqs);
            ps.setBigDecimal(23, szsm.bhsdszz);
            ps.setBigDecimal(24, szsm.bhsdskcs);
            ps.setString(25, szsm.zxbs);
            ps.setString(26, szsm.ccbs);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ������˰��˰Ŀ������¼
     * @param szsmList ˰��˰Ŀ�����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList szsmList, Connection conn) throws
            SQLException {
        String sql = "delete from  DMDB.SB_DM_SZSM  where ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < szsmList.size(); i++) {
                Szsm szsm = (Szsm) szsmList.get(i);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ˰��˰Ŀ�����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ˰��˰Ŀ�����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SzsmList = new ArrayList();
        String sql = "select SZSMDM,SZSMMC,SL,JSJS,SSKCS,JFYS,JLDW,ZXKSRQ,ZXJSRQ,ASLJBS,YNSQSS,YNSZZS,JSGS,SJLYZD,ZDKBJBZ,PYSY,BZ,SFFJS,ZQLXDM,FJDDM,BHSDSSL,BHSDSQS,BHSDSZZ,BHSDSKCS,ZXBS,CCBS from DMDB.SB_DM_SZSM " +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Szsm Szsm1 = new Szsm();
                Szsm1.setSzsmdm(rs.getString("SZSMDM"));
                Szsm1.setSzsmmc(rs.getString("SZSMMC"));
                Szsm1.setSl(rs.getBigDecimal("SL"));
                Szsm1.setJsjs(rs.getBigDecimal("JSJS"));
                Szsm1.setSskcs(rs.getBigDecimal("SSKCS"));
                Szsm1.setJfys(rs.getBigDecimal("JFYS"));
                Szsm1.setJldw(rs.getString("JLDW"));
                Szsm1.setZxksrq(rs.getTimestamp("ZXKSRQ"));
                Szsm1.setZxjsrq(rs.getTimestamp("ZXJSRQ"));
                Szsm1.setAsljbs(rs.getString("ASLJBS"));
                Szsm1.setYnsqss(rs.getBigDecimal("YNSQSS"));
                Szsm1.setYnszzs(rs.getBigDecimal("YNSZZS"));
                Szsm1.setJsgs(rs.getString("JSGS"));
                Szsm1.setSjlyzd(rs.getString("SJLYZD"));
                Szsm1.setZdkbjbz(rs.getString("ZDKBJBZ"));
                Szsm1.setPysy(rs.getString("PYSY"));
                Szsm1.setBz(rs.getString("BZ"));
                Szsm1.setSffjs(rs.getString("SFFJS"));
                Szsm1.setZqlxdm(rs.getString("ZQLXDM"));
                Szsm1.setFjddm(rs.getString("FJDDM"));
                Szsm1.setBhsdssl(rs.getBigDecimal("BHSDSSL"));
                Szsm1.setBhsdsqs(rs.getBigDecimal("BHSDSQS"));
                Szsm1.setBhsdszz(rs.getBigDecimal("BHSDSZZ"));
                Szsm1.setBhsdskcs(rs.getBigDecimal("BHSDSKCS"));
                Szsm1.setZxbs(rs.getString("ZXBS"));
                Szsm1.setCcbs(rs.getString("CCBS"));
                SzsmList.add(Szsm1);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return SzsmList;
    }

    /**
     * ����������ȡ˰��˰Ŀ�����ֵ����
     * @param szsm ˰��˰Ŀ�����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return˰��˰Ŀ�����ֵ����
     * @throws SQLException
     */
    public static Object get(Szsm szsm, Connection conn) throws SQLException {
        Szsm Szsm1 = new Szsm();
        String sql = "select SZSMDM,SZSMMC,SL,JSJS,SSKCS,JFYS,JLDW,ZXKSRQ,ZXJSRQ,ASLJBS,YNSQSS,YNSZZS,JSGS,SJLYZD,ZDKBJBZ,PYSY,BZ,SFFJS,ZQLXDM,FJDDM,BHSDSSL,BHSDSQS,BHSDSZZ,BHSDSKCS,ZXBS,CCBS from DMDB.SB_DM_SZSM   where SZSMDM='" +
                     szsm.szsmdm + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Szsm1.setSzsmdm(rs.getString("SZSMDM"));
                Szsm1.setSzsmmc(rs.getString("SZSMMC"));
                Szsm1.setSl(rs.getBigDecimal("SL"));
                Szsm1.setJsjs(rs.getBigDecimal("JSJS"));
                Szsm1.setSskcs(rs.getBigDecimal("SSKCS"));
                Szsm1.setJfys(rs.getBigDecimal("JFYS"));
                Szsm1.setJldw(rs.getString("JLDW"));
                Szsm1.setZxksrq(rs.getTimestamp("ZXKSRQ"));
                Szsm1.setZxjsrq(rs.getTimestamp("ZXJSRQ"));
                Szsm1.setAsljbs(rs.getString("ASLJBS"));
                Szsm1.setYnsqss(rs.getBigDecimal("YNSQSS"));
                Szsm1.setYnszzs(rs.getBigDecimal("YNSZZS"));
                Szsm1.setJsgs(rs.getString("JSGS"));
                Szsm1.setSjlyzd(rs.getString("SJLYZD"));
                Szsm1.setZdkbjbz(rs.getString("ZDKBJBZ"));
                Szsm1.setPysy(rs.getString("PYSY"));
                Szsm1.setBz(rs.getString("BZ"));
                Szsm1.setSffjs(rs.getString("SFFJS"));
                Szsm1.setZqlxdm(rs.getString("ZQLXDM"));
                Szsm1.setFjddm(rs.getString("FJDDM"));
                Szsm1.setBhsdssl(rs.getBigDecimal("BHSDSSL"));
                Szsm1.setBhsdsqs(rs.getBigDecimal("BHSDSQS"));
                Szsm1.setBhsdszz(rs.getBigDecimal("BHSDSZZ"));
                Szsm1.setBhsdskcs(rs.getBigDecimal("BHSDSKCS"));
                Szsm1.setZxbs(rs.getString("ZXBS"));
                Szsm1.setCcbs(rs.getString("CCBS"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Szsm1;
    }


}
