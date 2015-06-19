package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.ttsoft.common.util.Debug;


/**
 * ���ء�������Ϣ��DAO
 */
public class TufwxxDAO extends BaseDAO {

    /**
     * ����һ�����ء�������Ϣ���¼
     * @param tufwxx ���ء�������Ϣ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Tufwxx tufwxx, Connection conn) throws
            SQLException {
        String sql =
                "insert into QSDB.QS_JL_TUFWXX (TDFWID,FDCXMMC,HTQDSJ,FLDM,"
                + "TDFWZLDZ,TDFWQSZYLX,FWLXDM,TDFWQSZYMJ,FWJZMJ,CJJGRMB,"
                + "CJJGWB,BZDM,HL,ZHJGRMB,PGJGRMB,lrr,lrrq,CJR,CJRQ,BZ,"
                + "ND,TDFWQSZYLXMC,FLMC,FWLXMC,BZMC,RJL,TDJC,FWQY,SFESF)"
                +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //���ء�����ϵͳΨһ��ʶ
            ps.setString(1, tufwxx.tdfwid);
            //���ز���Ŀ����
            ps.setString(2, tufwxx.fdcxmmc);
            //��ͬ����Լ��ǩ��ʱ��
            ps.setTimestamp(3, tufwxx.htqdsj);
            //����
            ps.setString(4, tufwxx.fldm);
            //���ء����������ַ
            ps.setString(5, tufwxx.tdfwzldz);
            //���ء�����Ȩ��ת������
            ps.setString(6, tufwxx.tdfwqszylx);
            //�������
            ps.setString(7, tufwxx.fwlxdm);
            //���ء�����Ȩ��ת�����
            ps.setBigDecimal(8, tufwxx.tdfwqszymj);
            //���ݽ������
            ps.setBigDecimal(9, tufwxx.fwjzmj);
            //�ɽ��۸�����ң�
            ps.setBigDecimal(10, tufwxx.cjjgrmb);
            //�ɽ��۸���ң�
            ps.setBigDecimal(11, tufwxx.cjjgwb);
            //���ִ���
            ps.setString(12, tufwxx.bzdm);
            //���ʴ���
            ps.setBigDecimal(13, tufwxx.hldm);
            //�ۺϼ۸�����ң�
            ps.setBigDecimal(14, tufwxx.zhjgrmb);
            //�����۸�����ң�
            ps.setBigDecimal(15, tufwxx.pgjgrmb);
            //¼����
            ps.setString(16, tufwxx.lrr);
            //¼������
            ps.setTimestamp(17, tufwxx.lrrq);
            //������
            ps.setString(18, tufwxx.cjr);
            //��������
            ps.setTimestamp(19, tufwxx.cjrq);
            //��ע
            ps.setString(20, tufwxx.bz);
            //���
            ps.setString(21, tufwxx.nd);
            //���ط���Ȩ��ת������
            ps.setString(22, tufwxx.tdfwqszymc);
            //��������
            ps.setString(23, tufwxx.flmc);
            //������������
            ps.setString(24, tufwxx.fwlxmc);
            //��������
            ps.setString(25, tufwxx.bzmc);
            //�ݻ���
            ps.setString(26, tufwxx.rjl);
            //���ؼ���
            ps.setString(27, tufwxx.tdjc);
            //��������
            ps.setString(28, tufwxx.tdjc);
            // �Ƿ���ַ�
            String sfesf=tufwxx.sfesf;
            if("on".equals(sfesf)){
            	sfesf="01";
            }
            ps.setString(29, sfesf);

            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ�����ء�������Ϣ���¼
     * @param tufwxx ���ء�������Ϣ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Tufwxx tufwxx, Connection conn) throws
            SQLException {
        String sql = "update  QSDB.QS_JL_TUFWXX set FDCXMMC=?,HTQDSJ=?,FLDM=?,"
                     +
                     "TDFWZLDZ=?,TDFWQSZYLX=?,FWLXDM=?,TDFWQSZYMJ=?,FWJZMJ=?,"
                     + "CJJGRMB=?,CJJGWB=?,BZDM=?,HL=?,ZHJGRMB=?,PGJGRMB=?,"
                     + "lrr=?,lrrq=?,CJR=?,CJRQ=?,BZ=?,ND=?,"
                     +
                "TDFWQSZYLXMC=?,FLMC=?,FWLXMC=?,BZMC=?,RJL=?,TDJC=?,FWQY=?,SFESF=?"
                     + " where tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, tufwxx.fdcxmmc);
            ps.setTimestamp(2, tufwxx.htqdsj);
            ps.setString(3, tufwxx.fldm);
            ps.setString(4, tufwxx.tdfwzldz);
            ps.setString(5, tufwxx.tdfwqszylx);
            ps.setString(6, tufwxx.fwlxdm);
            ps.setBigDecimal(7, tufwxx.tdfwqszymj);
            ps.setBigDecimal(8, tufwxx.fwjzmj);
            ps.setBigDecimal(9, tufwxx.cjjgrmb);
            ps.setBigDecimal(10, tufwxx.cjjgwb);
            ps.setString(11, tufwxx.bzdm);
            ps.setBigDecimal(12, tufwxx.hldm);
            ps.setBigDecimal(13, tufwxx.zhjgrmb);
            ps.setBigDecimal(14, tufwxx.pgjgrmb);
            ps.setString(15, tufwxx.lrr);
            ps.setTimestamp(16, tufwxx.lrrq);
            ps.setString(17, tufwxx.cjr);
            ps.setTimestamp(18, tufwxx.cjrq);
            ps.setString(19, tufwxx.bz);
            ps.setString(20, tufwxx.nd);
            ps.setString(21, tufwxx.tdfwqszymc);
            ps.setString(22, tufwxx.flmc);
            ps.setString(23, tufwxx.fwlxmc);
            ps.setString(24, tufwxx.bzmc);
            //�ݻ���
            ps.setString(25, tufwxx.rjl);
            //���ؼ���
            ps.setString(26, tufwxx.tdjc);
            //��������
            ps.setString(27, tufwxx.tdjc);
            // �Ƿ���ַ�
            String sfesf=tufwxx.sfesf;
            if("on".equals(sfesf)){
            	sfesf="01";
            }
            ps.setString(28, sfesf);

            ps.setString(29, tufwxx.tdfwid);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ���������ء�������Ϣ���¼
     * @param tufwxxList ���ء�������Ϣ��ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList tufwxxList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_TUFWXX  where tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < tufwxxList.size(); i++) {
                Tufwxx tufwxx = (Tufwxx) tufwxxList.get(i);
                ps.setString(1, tufwxx.tdfwid);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ���ء�������Ϣ��ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ���ء�������Ϣ��ֵ����ļ���
     * @throws SQLException
     */
    /** delete by gaowt not used. If use, must modify.
         public static ArrayList query( String condition, Connection conn) throws SQLException
         {
        ArrayList TufwxxList = new ArrayList();
        String sql = "select TDFWID,FDCXMMC,HTQDSJ,FLDM,TDFWZLDZ,TDFWQSZYLX,FWLXDM,TDFWQSZYMJ,FWJZMJ,CJJGRMB,CJJGWB,BZDM,HL,ZHJGRMB,PGJGRMB,lrr,lrrq,CJR,CJRQ,BZ,ND,TDFWQSZYLXMC,FLMC,FWLXMC,BZMC from QSDB.QS_JL_TUFWXX "+condition;
        Debug.out("TufwxxDAO.query sql: " + sql);
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Tufwxx Tufwxx1 = new Tufwxx();
                Tufwxx1.setTdfwid(rs.getString("TDFWID"));
                Tufwxx1.setFdcxmmc(rs.getString("FDCXMMC"));
                Tufwxx1.setHtqdsj(rs.getTimestamp("HTQDSJ"));
                Tufwxx1.setFldm(rs.getString("FLDM"));
                Tufwxx1.setTdfwzldz(rs.getString("TDFWZLDZ"));
                Tufwxx1.setTdfwqszylx(rs.getString("TDFWQSZYLX"));
                Tufwxx1.setFwlxdm(rs.getString("FWLXDM"));
                Tufwxx1.setTdfwqszymj(rs.getBigDecimal("TDFWQSZYMJ"));
                Tufwxx1.setFwjzmj(rs.getBigDecimal("FWJZMJ"));
                Tufwxx1.setCjjgrmb(rs.getBigDecimal("CJJGRMB"));
                Tufwxx1.setCjjgwb(rs.getBigDecimal("CJJGWB"));
                Tufwxx1.setBzdm(rs.getString("BZDM"));
                Tufwxx1.setHldm(rs.getBigDecimal("HL"));
                Tufwxx1.setZhjgrmb(rs.getBigDecimal("ZHJGRMB"));
                Tufwxx1.setPgjgrmb(rs.getBigDecimal("PGJGRMB"));
                Tufwxx1.setLrr(rs.getString("lrr"));
                Tufwxx1.setLrrq(rs.getTimestamp("lrrq"));
                Tufwxx1.setCjr(rs.getString("CJR"));
                Tufwxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Tufwxx1.setBz(rs.getString("BZ"));
                Tufwxx1.setNd(rs.getString("ND"));
                Tufwxx1.setTdfwqszymc(rs.getString("TDFWQSZYLXMC"));
                Tufwxx1.setFlmc(rs.getString("FLMC"));
                Tufwxx1.setFwlxmc(rs.getString("FWLXMC"));
                Tufwxx1.setBzmc(rs.getString("BZMC"));

                TufwxxList.add(Tufwxx1);
            }
            rs.close();
        }
        catch(SQLException e)
        {
            throw e;
        }
        finally
        {
            ps.close();
        }
        return TufwxxList;
         }
     **/


    /**
     * �����걨��Ż�ȡ���ء�������Ϣ��ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ���ء�������Ϣ��ֵ����ļ���
     * @throws SQLException
     */
    /** delete by gaowt for not used.
         public static ArrayList queryBySbbh(String sbbh, Connection conn) throws SQLException
         {
        ArrayList TufwxxList = new ArrayList();
        String sql = "select TDFWID,FDCXMMC,HTQDSJ,FLDM,TDFWZLDZ,TDFWQSZYLX,"
                     + "FWLXDM,TDFWQSZYMJ,FWJZMJ,CJJGRMB,CJJGWB,BZDM,HL,"
                     + "ZHJGRMB,PGJGRMB,lrr,lrrq,CJR,CJRQ,BZ,ND"
                     + " from QSDB.QS_JL_TUFWXX t, QSDB.QS_JL_ZBTDFWGL g "
                     + " where t.TDFWID = g.TDFWID and g.SBBH = ?";
        Debug.out("TufwxxDAO.query sql: " + sql);
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,sbbh);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Tufwxx Tufwxx1 = new Tufwxx();
                Tufwxx1.setTdfwid(rs.getString("TDFWID"));
                Tufwxx1.setFdcxmmc(rs.getString("FDCXMMC"));
                Tufwxx1.setHtqdsj(rs.getTimestamp("HTQDSJ"));
                Tufwxx1.setFldm(rs.getString("FLDM"));
                Tufwxx1.setTdfwzldz(rs.getString("TDFWZLDZ"));
                Tufwxx1.setTdfwqszylx(rs.getString("TDFWQSZYLX"));
                Tufwxx1.setFwlxdm(rs.getString("FWLXDM"));
                Tufwxx1.setTdfwqszymj(rs.getBigDecimal("TDFWQSZYMJ"));
                Tufwxx1.setFwjzmj(rs.getBigDecimal("FWJZMJ"));
                Tufwxx1.setCjjgrmb(rs.getBigDecimal("CJJGRMB"));
                Tufwxx1.setCjjgwb(rs.getBigDecimal("CJJGWB"));
                Tufwxx1.setBzdm(rs.getString("BZDM"));
                Tufwxx1.setHldm(rs.getBigDecimal("HL"));
                Tufwxx1.setZhjgrmb(rs.getBigDecimal("ZHJGRMB"));
                Tufwxx1.setPgjgrmb(rs.getBigDecimal("PGJGRMB"));
                Tufwxx1.setLrr(rs.getString("lrr"));
                Tufwxx1.setLrrq(rs.getTimestamp("lrrq"));
                Tufwxx1.setCjr(rs.getString("CJR"));
                Tufwxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Tufwxx1.setBz(rs.getString("BZ"));
                Tufwxx1.setNd(rs.getString("ND"));
                TufwxxList.add(Tufwxx1);
            }
            rs.close();
        }
        catch(SQLException e)
        {
            throw e;
        }
        finally
        {
            if (ps != null)
                ps.close();
        }
        return TufwxxList;
         }
     **/

    /**
     * ����������ȡ���ء�������Ϣ��ֵ����
     * @param tufwxx ���ء�������Ϣ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return���ء�������Ϣ��ֵ����
     * @throws SQLException
     */
    public static Object get(Tufwxx tufwxx, Connection conn) throws
            SQLException {
        Tufwxx Tufwxx1 = null;

        Debug.out("into ftxxDao get ...");
        String sql = "select TDFWID,FDCXMMC,HTQDSJ,FLDM,TDFWZLDZ,TDFWQSZYLX,"
                     +
                     "FWLXDM,TDFWQSZYMJ,FWJZMJ,CJJGRMB,CJJGWB,BZDM,HL,ZHJGRMB,"
                     +
                "PGJGRMB,lrr,lrrq,CJR,CJRQ,BZ,ND,TDFWQSZYLXMC,FLMC,FWLXMC,"
                     +
                "BZMC,RJL,TDJC,SFESF from QSDB.QS_JL_TUFWXX where tdfwid = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tufwxx.tdfwid);
            ResultSet rs = ps.executeQuery();
            Debug.out("after sql execute...");
            if (rs.next()) {
                Tufwxx1 = new Tufwxx();
                Tufwxx1.setTdfwid(rs.getString("TDFWID"));
                Tufwxx1.setFdcxmmc(rs.getString("FDCXMMC"));
                Tufwxx1.setHtqdsj(rs.getTimestamp("HTQDSJ"));
                Tufwxx1.setFldm(rs.getString("FLDM"));
                Tufwxx1.setTdfwzldz(rs.getString("TDFWZLDZ"));
                Tufwxx1.setTdfwqszylx(rs.getString("TDFWQSZYLX"));
                Tufwxx1.setFwlxdm(rs.getString("FWLXDM"));
                Tufwxx1.setTdfwqszymj(rs.getBigDecimal("TDFWQSZYMJ"));
                Tufwxx1.setFwjzmj(rs.getBigDecimal("FWJZMJ"));
                Tufwxx1.setCjjgrmb(rs.getBigDecimal("CJJGRMB"));
                Tufwxx1.setCjjgwb(rs.getBigDecimal("CJJGWB"));
                Tufwxx1.setBzdm(rs.getString("BZDM"));
                Tufwxx1.setHldm(rs.getBigDecimal("HL"));
                Tufwxx1.setZhjgrmb(rs.getBigDecimal("ZHJGRMB"));
                Tufwxx1.setPgjgrmb(rs.getBigDecimal("PGJGRMB"));
                Tufwxx1.setLrr(rs.getString("lrr"));
                Tufwxx1.setLrrq(rs.getTimestamp("lrrq"));
                Tufwxx1.setCjr(rs.getString("CJR"));
                Tufwxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Tufwxx1.setBz(rs.getString("BZ"));
                Tufwxx1.setNd(rs.getString("ND"));
                Tufwxx1.setTdfwqszymc(rs.getString("TDFWQSZYLXMC"));
                Tufwxx1.setFlmc(rs.getString("FLMC"));
                Tufwxx1.setFwlxmc(rs.getString("FWLXMC"));
                Tufwxx1.setBzmc(rs.getString("BZMC"));
                Tufwxx1.setRjl(rs.getString("RJL"));
                Tufwxx1.setTdjc(rs.getString("TDJC"));
                Tufwxx1.setSfesf(rs.getString("SFESF"));
            }
            Debug.out("after got data before return...");
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Tufwxx1;
    }

    /**
     * �����걨��Ż�ȡ���ء�������Ϣ��ֵ����
     *
     * @param tufwxx ���ء�������Ϣ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return���ء�������Ϣ��ֵ����
     * @throws SQLException
     */
    /** @todo  ����Ժ����һ���걨���Ӧ�������ݡ����ؼ�¼��������Ҫ�޸Ĵ˷���
     * ����������һ������ */
    public static Object getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Tufwxx Tufwxx1 = null;
        String sql =
                "select t1.TDFWID,t1.FDCXMMC,t1.HTQDSJ,t1.FLDM,t1.TDFWZLDZ, ";
        sql = sql +
              "t1.TDFWQSZYLX,t1.FWLXDM,t1.TDFWQSZYMJ,t1.FWJZMJ,t1.CJJGRMB, ";
        sql = sql +
              "t1.CJJGWB,t1.BZDM,t1.HL,t1.ZHJGRMB,t1.PGJGRMB,t1.lrr,t1.lrrq, ";
        sql = sql +
              "t1.CJR,t1.CJRQ,t1.BZ,t1.ND,t1.TDFWQSZYLXMC,t1.FLMC,t1.FWLXMC, ";
        sql = sql + "t1.BZMC,t1.RJL,t1.TDJC,t1.SFESF from QSDB.QS_JL_TUFWXX t1, QSDB.QS_JL_SBTDFWGL t2 ";
        sql = sql + "where sbbh = ? and t1.tdfwid = t2.tdfwid";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Tufwxx1 = new Tufwxx();
                Tufwxx1.setTdfwid(rs.getString("TDFWID"));
                Tufwxx1.setFdcxmmc(rs.getString("FDCXMMC"));
                Tufwxx1.setHtqdsj(rs.getTimestamp("HTQDSJ"));
                Tufwxx1.setFldm(rs.getString("FLDM"));
                Tufwxx1.setTdfwzldz(rs.getString("TDFWZLDZ"));
                Tufwxx1.setTdfwqszylx(rs.getString("TDFWQSZYLX"));
                Tufwxx1.setFwlxdm(rs.getString("FWLXDM"));
                Tufwxx1.setTdfwqszymj(rs.getBigDecimal("TDFWQSZYMJ"));
                Tufwxx1.setFwjzmj(rs.getBigDecimal("FWJZMJ"));
                Tufwxx1.setCjjgrmb(rs.getBigDecimal("CJJGRMB"));
                Tufwxx1.setCjjgwb(rs.getBigDecimal("CJJGWB"));
                Tufwxx1.setBzdm(rs.getString("BZDM"));
                Tufwxx1.setHldm(rs.getBigDecimal("HL"));
                Tufwxx1.setZhjgrmb(rs.getBigDecimal("ZHJGRMB"));
                Tufwxx1.setPgjgrmb(rs.getBigDecimal("PGJGRMB"));
                Tufwxx1.setLrr(rs.getString("lrr"));
                Tufwxx1.setLrrq(rs.getTimestamp("lrrq"));
                Tufwxx1.setCjr(rs.getString("CJR"));
                Tufwxx1.setCjrq(rs.getTimestamp("CJRQ"));
                Tufwxx1.setBz(rs.getString("BZ"));
                Tufwxx1.setNd(rs.getString("ND"));
                Tufwxx1.setTdfwqszymc(rs.getString("TDFWQSZYLXMC"));
                Tufwxx1.setFlmc(rs.getString("FLMC"));
                Tufwxx1.setFwlxmc(rs.getString("FWLXMC"));
                Tufwxx1.setBzmc(rs.getString("BZMC"));
                Tufwxx1.setRjl(rs.getString("RJL"));
                Tufwxx1.setTdjc(rs.getString("TDJC"));
                Tufwxx1.setSfesf(rs.getString("SFESF"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Tufwxx1;
    }

}
