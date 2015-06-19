package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.bo.JmsbblBo;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBzqsBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;


/**
 * ��˰�걨����DAO
 */
public class SbzbDAO extends BaseDAO {

    /**
     * ����һ����˰�걨�����¼
     * @param sbzb ��˰�걨����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Sbzb sbzb, Connection conn) throws SQLException {
        //---modify by jiq at 20061211--------------
        String sql = "insert into QSDB.QS_JL_SBZB (SBBH,JWYWBH,HTBH,SBRQ,JSFSDM,JSFSMC,FWTDBMDM,NSRLXDM,SWJGZZJGDM,ZSRYMC,SL,JSJE,YNSE,BLJMSBS,BLBS,YHBS,ZTBS,BZ,LRR,CJR,CJRQ,LRRQ,DFSBBH,PZZHDM,PZZHMC,DRPCH,SETZ,SJLY) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�걨���
            ps.setString(1, sbzb.sbbh);

            //���ӽ�ίҵ�����ֶΣ�modify by fujx,20081125
            ps.setString(2, sbzb.getJwywbh());
            //���Ӻ�ͬ����ֶΣ�modify by fujx,20081125
            ps.setString(3, sbzb.getHtbh());

            //�걨����
            ps.setTimestamp(4, sbzb.sbrq);
            //��˰��ʽ
            ps.setString(5, sbzb.jsfsdm);
            //��˰��ʽ����
            ps.setString(6, sbzb.jsfsmc);
            //�������ز��������
            ps.setString(7, sbzb.fwtdbmdm);
            //��˰�����ʹ���
            ps.setString(8, sbzb.nsrlxdm);
            //����˰�������֯��������
            ps.setString(9, sbzb.swjgzzjgdm);
            //������Ա����
            ps.setString(10, sbzb.zsrymc);
            //˰��
            ps.setBigDecimal(11, sbzb.sl);
            //��˰���
            ps.setBigDecimal(12, sbzb.jsje);
            //Ӧ��˰��
            ps.setBigDecimal(13, sbzb.ynse);
            //�������˰��ʶ
            ps.setString(14, sbzb.bljmsbs);
            //��¼��ʶ
            ps.setString(15, sbzb.blbs);
            //�û���ʶ
            ps.setString(16, sbzb.yhbs);
            //״̬��ʶ
            ps.setString(17, sbzb.ztbs);
            //��ע
            ps.setString(18, sbzb.bz);
            //¼����
            ps.setString(19, sbzb.lrr);
            //������
            ps.setString(20, sbzb.cjr);
            //��������
            ps.setTimestamp(21, sbzb.cjrq);
            //¼������
            ps.setTimestamp(22, sbzb.lrrq);
            //���ݽ����ĶԷ��걨���
            ps.setString(23, sbzb.dfsbbh);
            //Ʊ֤�ʻ�����
            ps.setString(24, sbzb.pzzhdm);
            //Ʊ֤�ʻ�����
            ps.setString(25, sbzb.pzzhmc);
            //�������κ�
            ps.setString(26, sbzb.drpch);
            //˰�����
            ps.setString(27, sbzb.setz);

            //����������Դ��modify by hazz,20081205
            ps.setString(28, sbzb.getSjly());

            //ִ��
            ps.execute();
        } catch (SQLException e) {
            Debug.printException(e);
            throw e;
        } finally {

            try {
                ps.close();
            } catch (Exception ex) {
            }

        }
    }

    /**
     * ����һ����˰�걨�����¼
     * @param sbzb ��˰�걨����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Sbzb sbzb, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_SBZB set SBRQ=?,JSFSDM=?,JSFSMC=?,FWTDBMDM=?,NSRLXDM=?,SWJGZZJGDM=?,ZSRYMC=?,SL=?,JSJE=?,YNSE=?,BLJMSBS=?,BLBS=?,YHBS=?,ZTBS=?,BZ=?,LRR=?,CJR=?,CJRQ=?,LRRQ=?,DFSBBH=?,SETZ=?,SJLY=?   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, sbzb.sbrq);
            ps.setString(2, sbzb.jsfsdm);
            ps.setString(3, sbzb.jsfsmc);
            ps.setString(4, sbzb.fwtdbmdm);
            ps.setString(5, sbzb.nsrlxdm);
            ps.setString(6, sbzb.swjgzzjgdm);
            ps.setString(7, sbzb.zsrymc);
            ps.setBigDecimal(8, sbzb.sl);
            ps.setBigDecimal(9, sbzb.jsje);
            ps.setBigDecimal(10, sbzb.ynse);
            ps.setString(11, sbzb.bljmsbs);
            ps.setString(12, sbzb.blbs);
            ps.setString(13, sbzb.yhbs);
            ps.setString(14, sbzb.ztbs);
            ps.setString(15, sbzb.bz);
            ps.setString(16, sbzb.lrr);
            ps.setString(17, sbzb.cjr);
            ps.setTimestamp(18, sbzb.cjrq);
            ps.setTimestamp(19, sbzb.lrrq);
            ps.setString(20, sbzb.dfsbbh);
            ps.setString(21, sbzb.setz);

            //����������Դ��modify by hazz,20081205
            ps.setString(22, sbzb.getSjly());
            ps.setString(23, sbzb.sbbh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * ɾ��������˰�걨�����¼
     * @param sbzbList ��˰�걨����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList sbzbList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_SBZB  where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < sbzbList.size(); i++) {
                Sbzb sbzb = (Sbzb) sbzbList.get(i);
                ps.setString(1, sbzb.sbbh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {

            try {
                ps.close();
            } catch (Exception ex) {
            }

        }
    }


    /**
     * update�������Ӧ��¼��״̬��ʶ
     * @param sbzbList ��˰�걨����ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void deleteBZQS(Collection c, Connection conn, String state) throws
            SQLException {
        String sql = "UPDATE QSDB.QS_JL_SBZB SET bljmsbs = ? WHERE sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (Iterator iter = c.iterator(); iter.hasNext(); ) {
                Sbzb sbzb = (Sbzb) (((QueryBzqsBo) iter.next()).getSbzb());
                ps.setString(1, state);
                ps.setString(2, sbzb.getSbbh());
                ps.execute();
            }
            /*            for (int i = 0; i < sbzbList.size(); i++)
                        {
                            Sbzb sbzb = (Sbzb)sbzbList.get(i);
                            ps.setString(1, sbzb.sbbh);
                            ps.execute();
                        }*/
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * ����������ȡ��˰�걨����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰�걨����ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList SbzbList = new ArrayList();
        String sql = "select SBBH,SBRQ,JSFSDM,JSFSMC,FWTDBMDM,NSRLXDM,SWJGZZJGDM,ZSRYMC,SL,JSJE,YNSE,BLJMSBS,BLBS,YHBS,ZTBS,BZ,LRR,CJR,CJRQ,LRRQ,DFSBBH,PZZHDM,PZZHMC,DRPCH,SETZ,SJLY from QSDB.QS_JL_SBZB " +
                     condition;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sbzb Sbzb1 = new Sbzb();
                Sbzb1.setSbbh(rs.getString("SBBH"));
                Sbzb1.setSbrq(rs.getTimestamp("SBRQ"));
                Sbzb1.setJsfsdm(rs.getString("JSFSDM"));
                Sbzb1.setJsfsmc(rs.getString("JSFSMC"));
                Sbzb1.setFwtdbmdm(rs.getString("FWTDBMDM"));
                Sbzb1.setNsrlxdm(rs.getString("NSRLXDM"));
                Sbzb1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbzb1.setZsrymc(rs.getString("ZSRYMC"));
                Sbzb1.setSl(rs.getBigDecimal("SL"));
                Sbzb1.setJsje(rs.getBigDecimal("JSJE"));
                Sbzb1.setYnse(rs.getBigDecimal("YNSE"));
                Sbzb1.setBljmsbs(rs.getString("BLJMSBS"));
                Sbzb1.setBlbs(rs.getString("BLBS"));
                Sbzb1.setYhbs(rs.getString("YHBS"));
                Sbzb1.setZtbs(rs.getString("ZTBS"));
                Sbzb1.setBz(rs.getString("BZ"));
                Sbzb1.setLrr(rs.getString("LRR"));
                Sbzb1.setCjr(rs.getString("CJR"));
                Sbzb1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbzb1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbzb1.setDfsbbh(rs.getString("DFSBBH"));
                Sbzb1.setPzzhdm(rs.getString("PZZHDM"));
                Sbzb1.setPzzhmc(rs.getString("PZZHMC"));
                Sbzb1.setDrpch(rs.getString("DRPCH"));
                Sbzb1.setSetz(rs.getString("SETZ"));
                Sbzb1.setSjly(rs.getString("SJLY"));
                SbzbList.add(Sbzb1);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return SbzbList;
    }

    /**
     * ����������ȡ��˰�걨�����С�������걨���
     * @param sbzb ��˰�걨����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰�걨����ֵ����
     * @throws SQLException
     */
    public static String getDrsbbh(String sbbh, Connection conn) throws
            SQLException {
        String sql = "select x.WSJC || '��' || x.SBBH || '��' SBBH from ("
                     +
                " select sbbh,wsjc,qxdm,glsbbh from qsdb.qs_jl_sjqygr   where glsbbh = ?"
                     +
                " union	select  sbbh,wsjc,qxdm,glsbbh from qsdb.qs_jl_sjqyfgr  where glsbbh = ?"
                     + " union	select  sbbh,wsjc,qxdm,glsbbh from qsdb.qs_jl_sjqybz  where glsbbh = ?) x";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ps.setString(2, sbbh);
            ps.setString(3, sbbh);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("SBBH");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            try {
                ps.close();
            } catch (Exception ex) {
            }

        }
        return "";
    }

    /**
     * ����������ȡ��˰�걨����ֵ����
     * @param sbzb ��˰�걨����ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰�걨����ֵ����
     * @throws SQLException
     */
    public static Object get(Sbzb sbzb, Connection conn) throws SQLException {
        Sbzb Sbzb1 = new Sbzb();
        //����ϼ���ӡ���ͬ��š��ֶβ�ѯ
        String sql = "select SBBH,SBRQ,JSFSDM,JSFSMC,FWTDBMDM,NSRLXDM,SWJGZZJGDM,ZSRYMC,SL,JSJE,YNSE,BLJMSBS,BLBS,YHBS,ZTBS,BZ,LRR,CJR,CJRQ,LRRQ,DFSBBH,PZZHDM,PZZHMC,DRPCH,SETZ,SJLY,HTBH from QSDB.QS_JL_SBZB   where sbbh = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbzb.sbbh);
            rs = ps.executeQuery();
            if (rs.next()) {
                Sbzb1.setSbbh(rs.getString("SBBH"));
                Sbzb1.setSbrq(rs.getTimestamp("SBRQ"));
                Sbzb1.setJsfsdm(rs.getString("JSFSDM"));
                Sbzb1.setJsfsmc(rs.getString("JSFSMC"));
                Sbzb1.setFwtdbmdm(rs.getString("FWTDBMDM"));
                Sbzb1.setNsrlxdm(rs.getString("NSRLXDM"));
                Sbzb1.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                Sbzb1.setZsrymc(rs.getString("ZSRYMC"));
                Sbzb1.setSl(rs.getBigDecimal("SL"));
                Sbzb1.setJsje(rs.getBigDecimal("JSJE"));
                Sbzb1.setYnse(rs.getBigDecimal("YNSE"));
                Sbzb1.setBljmsbs(rs.getString("BLJMSBS"));
                Sbzb1.setBlbs(rs.getString("BLBS"));
                Sbzb1.setYhbs(rs.getString("YHBS"));
                Sbzb1.setZtbs(rs.getString("ZTBS"));
                Sbzb1.setBz(rs.getString("BZ"));
                Sbzb1.setLrr(rs.getString("LRR"));
                Sbzb1.setCjr(rs.getString("CJR"));
                Sbzb1.setCjrq(rs.getTimestamp("CJRQ"));
                Sbzb1.setLrrq(rs.getTimestamp("LRRQ"));
                Sbzb1.setDfsbbh(rs.getString("DFSBBH"));
                Sbzb1.setPzzhdm(rs.getString("PZZHDM"));
                Sbzb1.setPzzhmc(rs.getString("PZZHMC"));
                Sbzb1.setDrpch(rs.getString("DRPCH"));
                Sbzb1.setSetz(rs.getString("SETZ"));
                Sbzb1.setSjly(rs.getString("SJLY"));
                Sbzb1.setHtbh(rs.getString("HTBH"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            try {
                ps.close();
            } catch (Exception ex) {
            }

        }
        return Sbzb1;
    }


    /**
     * Ϊ��ʱ����--��ѯ�걨��Ϣ���صķ�����person���������ǲ�ѯ���˻��ǷǸ���
     * @param condition String
     * @param conn Connection
     * @param person boolean
     * @return ArrayList
     */
    public static ArrayList query(String condition, Connection conn,
                                  boolean person) throws SQLException {
        Debug.out("SbzbDAO.query person: " + person);
        ArrayList resultList = new ArrayList();
        if (person) {
            //��ѯ���˵�sql���
            /** String sql =
                   "SELECT  a.sbbh, a.yhbs, a.nsrlxdm, a.bz,a.pzzhdm,a.pzzhmc,a.drpch,b.fdcxmmc, b.tdfwzldz, " +
                   "b.cjjgrmb, d.nsrmc, d.sfzjlx, d.jsjdm, d.lxdh,d.sfzjhm " +
             "FROM QSDB.QS_JL_SBZB a, QSDB.QS_JL_TUFWXX b, QSDB.QS_JL_SBTDFWGL c, " +
                   "QSDB.QS_JL_GRXX d " +
                   "WHERE a.sbbh = c.sbbh AND b.tdfwid = c.tdfwid AND " +
                   "a.sbbh = d.sbbh AND d.cqrlx='0' AND a.sbbh in (" +
                   "SELECT a.sbbh " +
             "FROM QSDB.QS_JL_SBZB a, QSDB.QS_JL_TUFWXX b, QSDB.QS_JL_SBTDFWGL c, " +
                   "QSDB.QS_JL_GRXX d " +
                   "WHERE a.sbbh = c.sbbh AND b.tdfwid = c.tdfwid AND " +
                   "a.sbbh = d.sbbh " + condition +
                   ") order by a.sbbh desc";
             */


            String sql =
                    "SELECT a.sbbh, a.yhbs, a.nsrlxdm, a.bz,a.pzzhdm,a.pzzhmc,a.drpch,b.fdcxmmc, b.tdfwzldz, " +
                    "b.cjjgrmb, d.nsrmc, d.sfzjlx, d.jsjdm, d.lxdh,d.sfzjhm " +
                    "FROM QSDB.QS_JL_SBZB a, QSDB.QS_JL_TUFWXX b, QSDB.QS_JL_SBTDFWGL c, " +
                    "QSDB.QS_JL_GRXX d " +
                    "WHERE a.sbbh = c.sbbh AND b.tdfwid = c.tdfwid AND " +
                    "a.sbbh = d.sbbh AND d.cqrlx='0' " + condition +
                    " order by a.sbbh desc";

            PreparedStatement ps = null;
            Debug.out("SbzbDAO.query(1,2,3) = " + sql);
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    JsblBo bo = new JsblBo();
                    bo.setSbbh(rs.getString("sbbh"));
                    bo.setYhbs(rs.getString("yhbs"));
                    bo.setNsrlxdm(rs.getString("nsrlxdm"));
                    bo.setBz(rs.getString("bz"));
                    bo.setDrpch(rs.getString("drpch"));

                    bo.setFdcxmmc(rs.getString("fdcxmmc"));
                    bo.setTdfwzldz(rs.getString("tdfwzldz"));
                    bo.setCjjgrmb(rs.getBigDecimal("cjjgrmb"));

                    bo.setJsjdm(rs.getString("jsjdm"));
                    //bo.setNsrmc(rs.getString("nsrmc"));
                    bo.setNsrmc(GrxxDAO.getNsrmcBySbbh(bo.getSbbh(), conn));
                    bo.setSfzjlx(rs.getString("sfzjlx"));
                    bo.setSfzjhm(rs.getString("sfzjhm"));
                    bo.setLxdh(rs.getString("lxdh"));

                    resultList.add(bo);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
                try {
                    ps.close();
                } catch (Exception ex) {
                }

            }
        } else {
            //��ѯ�Ǹ��˵�sql���
            String sql =
                    "SELECT a.sbbh, a.yhbs, a.bz, a.drpch,b.fdcxmmc, b.tdfwzldz, " +
                    "b.cjjgrmb, d.nsrmc, d.jsjdm, d.lxdh,d.nsrlxdm " +
                    "FROM QSDB.QS_JL_SBZB a, QSDB.QS_JL_TUFWXX b, QSDB.QS_JL_SBTDFWGL c, " +
                    "QSDB.QS_JL_FGRXX d " +
                    "WHERE a.sbbh = c.sbbh AND b.tdfwid = c.tdfwid AND " +
                    "a.sbbh = d.sbbh " + condition +
                    " order by a.sbbh desc";
            Debug.out("SbzbDAO.query(1,2,3) = " + sql);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    JsblBo bo = new JsblBo();
                    bo.setSbbh(rs.getString("sbbh"));
                    bo.setYhbs(rs.getString("yhbs"));
                    bo.setBz(rs.getString("bz"));
                    bo.setDrpch(rs.getString("drpch"));

                    bo.setFdcxmmc(rs.getString("fdcxmmc"));
                    bo.setTdfwzldz(rs.getString("tdfwzldz"));
                    bo.setCjjgrmb(rs.getBigDecimal("cjjgrmb"));

                    bo.setNsrmc(rs.getString("nsrmc"));
                    bo.setJsjdm(rs.getString("jsjdm"));
                    bo.setNsrlxdm(rs.getString("nsrlxdm"));
                    bo.setLxdh(rs.getString("lxdh"));

                    resultList.add(bo);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
                try {
                    ps.close();
                } catch (Exception ex) {
                }
            }

        }
        return resultList;
    }

    /**
     * ר��Ϊ������˰��ѯд�ķ���
     * @param conditons String  ��processor����ƴ�Ĳ�ѯ����
     * @param conn Connection
     * @param person boolean    true������ˣ�false����Ǹ���
     * @return ArrayList
     */
    public static ArrayList queryBzqs(String conditon, Connection conn,
                                      boolean person) throws SQLException {
        ArrayList resultList = new ArrayList();

        if (person) {
            //��ѯ���˵�sql���

            String sql = "select a.*, b.jsjdm, b.nsrmc, b.sfzjlx, b.sfzjlxmc, " +
                         "b.sfzjhm, b.lxdh, b.fwjhbs, d.* " +
                         "from qsdb.qs_jl_sbzb a, qsdb.qs_jl_grxx b, " +
                         "qsdb.qs_jl_sbtdfwgl c, qsdb.qs_jl_tufwxx d " +
                         "where a.sbbh = b.sbbh and a.sbbh = c.sbbh and " +
                         "c.tdfwid = d.tdfwid AND b.cqrlx='0' AND a.sbbh in (" +
                         "select a.sbbh " +
                         "from qsdb.qs_jl_sbzb a, qsdb.qs_jl_grxx b, " +
                         "qsdb.qs_jl_sbtdfwgl c, qsdb.qs_jl_tufwxx d " +
                         "where a.sbbh = b.sbbh and a.sbbh = c.sbbh and " +
                         "c.tdfwid = d.tdfwid " + conditon + " )";

            Debug.out("SbzbDAO.queryBzqs(1,2,true) = " + sql);

            PreparedStatement ps = null;
            ResultSet rs = null;

            try {

                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    QueryBzqsBo bo = new QueryBzqsBo();
                    Sbzb sbzb = new Sbzb();
                    Grxx grxx = new Grxx();
                    Tufwxx tufwxx = new Tufwxx();

                    //ȡ���걨����Ĳ�ѯ������ŵ�sbzb vo��
                    sbzb.setSbbh(rs.getString("sbbh"));
                    sbzb.setBlbs(rs.getString("blbs"));
                    sbzb.setBljmsbs(rs.getString("bljmsbs"));
                    sbzb.setBz(rs.getString("bz"));
                    sbzb.setCjr(rs.getString("cjr"));
                    sbzb.setCjrq(rs.getTimestamp("cjrq"));
                    sbzb.setFwtdbmdm(rs.getString("fwtdbmdm"));
                    sbzb.setJsfsdm(rs.getString("jsfsdm"));
                    sbzb.setJsfsmc(rs.getString("jsfsmc"));
                    sbzb.setJsje(rs.getBigDecimal("jsje"));
                    sbzb.setLrr(rs.getString("lrr"));
                    sbzb.setLrrq(rs.getTimestamp("lrrq"));
                    sbzb.setNsrlxdm(rs.getString("nsrlxdm"));
                    sbzb.setSbrq(rs.getTimestamp("sbrq"));
                    sbzb.setSl(rs.getBigDecimal("sl"));
                    sbzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                    sbzb.setYhbs(rs.getString("yhbs"));
                    sbzb.setZsrymc(rs.getString("zsrymc"));
                    sbzb.setZtbs(rs.getString("ztbs"));
                    sbzb.setDfsbbh(rs.getString("DFSBBH"));
                    sbzb.setSjly(rs.getString("SJLY"));

                    //ȡ��������Ϣ���ŵ�grxx vo��
                    grxx.setJsjdm(rs.getString("jsjdm"));
                    grxx.setSfzjhm(rs.getString("sfzjhm"));
                    grxx.setSfzjlx(rs.getString("sfzjlx"));
                    grxx.setSfzjlxmc(rs.getString("sfzjlxmc"));
                    //grxx.setNsrmc(rs.getString("nsrmc"));
                    grxx.setNsrmc(GrxxDAO.getNsrmcBySbbh(sbzb.getSbbh(), conn));
                    grxx.setLxdh(rs.getString("lxdh"));
                    grxx.setFwjhbs(rs.getString("fwjhbs"));
                    //grxx.setCqrlx(rs.getString("CQRLX"));

                    //ȡ�����ط�����Ϣ���ŵ�tufwxx vo��
                    tufwxx.setBz(rs.getString("bz"));
                    tufwxx.setBzdm(rs.getString("bzdm"));
                    tufwxx.setCjjgrmb(rs.getBigDecimal("cjjgrmb"));
                    tufwxx.setCjjgwb(rs.getBigDecimal("cjjgwb"));
                    //tufwxx.setCjr(rs.getString("cjr") );
                    //tufwxx.setCjrq(rs.getTimestamp("cjrq") );
                    tufwxx.setFdcxmmc(rs.getString("fdcxmmc"));
                    tufwxx.setFldm(rs.getString("fldm"));
                    //tufwxx.setFlmc(rs.getString("flmc") );    ���ݿ�����δ������ֶ�
                    tufwxx.setFwjzmj(rs.getBigDecimal("fwjzmj"));
                    tufwxx.setFwlxdm(rs.getString("fwlxdm"));
                    tufwxx.setHldm(rs.getBigDecimal("hldm"));
                    tufwxx.setHtqdsj(rs.getTimestamp("htqdsj"));
                    //tufwxx.setLrr(rs.getString("lrr") );
                    //tufwxx.setLrrq(rs.getTimestamp("lrrq") );
                    tufwxx.setNd(rs.getString("nd"));
                    tufwxx.setPgjgrmb(rs.getBigDecimal("pgjgrmb"));
                    //tufwxx.setSbbh(rs.getString("sbbh"));
                    tufwxx.setTdfwid(rs.getString("tdfwid"));
                    tufwxx.setTdfwqszylx(rs.getString("tdfwqszylx"));
                    tufwxx.setTdfwzldz(rs.getString("tdfwzldz"));
                    tufwxx.setZhjgrmb(rs.getBigDecimal("zhjgrmb"));
                    tufwxx.setTdfwqszymj(rs.getBigDecimal("tdfwqszymj"));

                    bo.setSbzb(sbzb);
                    bo.setGrxx(grxx);
                    bo.setTufwxx(tufwxx);

                    resultList.add(bo);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                rs.close();
                ps.close();
            }

        } else {

            //��ѯ�Ǹ��˵�sql���
            String sql = "select a.*, b.jsjdm, b.nsrmc, b.nsrlxdm, b.nsrlxmc, " +
                         "b.khyhdm, b.khyhmc, b.yhzh, b.lxrxm, b.lxdh, b.fwjhbs, d.* " +
                         "from qsdb.qs_jl_sbzb a, qsdb.qs_jl_fgrxx b, " +
                         "qsdb.qs_jl_sbtdfwgl c, qsdb.qs_jl_tufwxx d " +
                         "where a.sbbh = b.sbbh and a.sbbh = c.sbbh and " +
                         "c.tdfwid = d.tdfwid " + conditon;

            Debug.out("SbzbDAO.queryBzqs(1,2,false) = " + sql);

            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    QueryBzqsBo bo = new QueryBzqsBo();
                    Sbzb sbzb = new Sbzb();
                    Fgrxx fgrxx = new Fgrxx();
                    Tufwxx tufwxx = new Tufwxx();

                    //ȡ���걨����Ĳ�ѯ������ŵ�sbzb vo��
                    sbzb.setSbbh(rs.getString("sbbh"));
                    sbzb.setBlbs(rs.getString("blbs"));
                    sbzb.setBljmsbs(rs.getString("bljmsbs"));
                    sbzb.setBz(rs.getString("bz"));
                    sbzb.setCjr(rs.getString("cjr"));
                    sbzb.setCjrq(rs.getTimestamp("cjrq"));
                    sbzb.setFwtdbmdm(rs.getString("fwtdbmdm"));
                    sbzb.setJsfsdm(rs.getString("jsfsdm"));
                    sbzb.setJsfsmc(rs.getString("jsfsmc"));
                    sbzb.setJsje(rs.getBigDecimal("jsje"));
                    sbzb.setLrr(rs.getString("lrr"));
                    sbzb.setLrrq(rs.getTimestamp("lrrq"));
                    sbzb.setNsrlxdm(rs.getString("nsrlxdm"));
                    sbzb.setSbrq(rs.getTimestamp("sbrq"));
                    sbzb.setSl(rs.getBigDecimal("sl"));
                    sbzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                    sbzb.setYhbs(rs.getString("yhbs"));
                    sbzb.setZsrymc(rs.getString("zsrymc"));
                    sbzb.setZtbs(rs.getString("ztbs"));
                    sbzb.setDfsbbh("DFSBBH");
                    sbzb.setSjly(rs.getString("SJLY"));

                    //ȡ��������Ϣ���ŵ�grxx vo��
                    fgrxx.setJsjdm(rs.getString("jsjdm"));
                    fgrxx.setNsrmc(rs.getString("nsrmc"));
                    fgrxx.setNsrlxdm(rs.getString("nsrlxdm"));
                    fgrxx.setNsrlxmc(rs.getString("nsrlxmc"));
                    fgrxx.setKhyhdm(rs.getString("khyhdm"));
                    fgrxx.setKhyhmc(rs.getString("khyhmc"));
                    fgrxx.setYhzh(rs.getString("yhzh"));
                    fgrxx.setLxrxm(rs.getString("lxrxm"));
                    fgrxx.setLxdh(rs.getString("lxdh"));
                    fgrxx.setFwjhbs(rs.getString("fwjhbs"));

                    //ȡ�����ط�����Ϣ���ŵ�tufwxx vo��
                    tufwxx.setBz(rs.getString("bz"));
                    tufwxx.setBzdm(rs.getString("bzdm"));
                    tufwxx.setCjjgrmb(rs.getBigDecimal("cjjgrmb"));
                    tufwxx.setCjjgwb(rs.getBigDecimal("cjjgwb"));
                    //tufwxx.setCjr(rs.getString("cjr") );
                    //tufwxx.setCjrq(rs.getTimestamp("cjrq") );
                    tufwxx.setFdcxmmc(rs.getString("fdcxmmc"));
                    tufwxx.setFldm(rs.getString("fldm"));
                    //tufwxx.setFlmc(rs.getString("flmc") );    ���ݿ�����δ������ֶ�
                    tufwxx.setFwjzmj(rs.getBigDecimal("fwjzmj"));
                    tufwxx.setFwlxdm(rs.getString("fwlxdm"));
                    tufwxx.setHldm(rs.getBigDecimal("hldm"));
                    tufwxx.setHtqdsj(rs.getTimestamp("htqdsj"));
                    //tufwxx.setLrr(rs.getString("lrr") );
                    //tufwxx.setLrrq(rs.getTimestamp("lrrq") );
                    tufwxx.setNd(rs.getString("nd"));
                    tufwxx.setPgjgrmb(rs.getBigDecimal("pgjgrmb"));
                    //tufwxx.setSbbh(rs.getString("sbbh"));
                    tufwxx.setTdfwid(rs.getString("tdfwid"));
                    tufwxx.setTdfwqszylx(rs.getString("tdfwqszylx"));
                    tufwxx.setTdfwzldz(rs.getString("tdfwzldz"));
                    tufwxx.setZhjgrmb(rs.getBigDecimal("zhjgrmb"));
                    tufwxx.setTdfwqszymj(rs.getBigDecimal("tdfwqszymj"));

                    bo.setSbzb(sbzb);
                    bo.setFgrxx(fgrxx);
                    bo.setTufwxx(tufwxx);

                    resultList.add(bo);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                rs.close();
                ps.close();
            }

        }

        return resultList;
    }

    /**
     * ר��Ϊ��ʱ����д�����ͬ���ͬ���update
     * @param zt String
     * @param conditions String
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String zt, String sbbh, Connection conn) throws
            SQLException {
        Debug.out("update sbzb ztbs: " + zt + "  --- sbbh: " + sbbh);
        String sql = "UPDATE qsdb.qs_jl_sbzb SET ztbs = ? WHERE sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, zt);
            ps.setString(2, sbbh);

            ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {

            }
        }
    }

    /**
     * ���¼���˰��ʶ
     * @param zt String
     * @param conditions String
     * @param conn Connection
     * @throws SQLException
     */
    public static void updateJmsbs(String jmsbs, String sbbh, Connection conn) throws
            SQLException {
        Debug.out("update sbzb jmsbs: " + jmsbs + "  --- sbbh: " + sbbh);
        String sql = "UPDATE qsdb.qs_jl_sbzb SET bljmsbs = ? WHERE sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jmsbs);
            ps.setString(2, sbbh);

            ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * ר��Ϊ¼�뽻����Ϣʱ���¶Է��걨���
     * @param zt String
     * @param conditions String
     * @param conn Connection
     * @throws SQLException
     */
    public static void updateDfsbbh(String sbbh, String dfsbbh, Connection conn) throws
            SQLException {
        Debug.out("update sbzb sbbh dfsbbh: " + sbbh + "  ---  " + dfsbbh);
        String sql = "UPDATE qsdb.qs_jl_sbzb SET dfsbbh = ? WHERE sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, dfsbbh);
            ps.setString(2, sbbh);

            ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {

            }
        }
    }


    /**
     * ��¼�ɿ���󣬸��������״̬��ʶ
     * @param sbbh String
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String sbbh, Connection conn) throws SQLException {
        String sql = "UPDATE qsdb.qs_jl_sbzb SET ztbs = ? WHERE sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, Constants.ZB_ZTBS_YRK);
            ps.setString(2, sbbh);

            ps.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

    }


    /**
     * Ϊ�����걨--��ѯ�걨��Ϣ���صķ�����person���������ǲ�ѯ���˻��ǷǸ���
     * @param condition String
     * @param conn Connection
     * @param person boolean
     * @return ArrayList
     */
    public static ArrayList queryJmsb(String condition, Connection conn,
                                      boolean person) throws SQLException {
        Debug.out("SbzbDAO.query person: " + person);
        ArrayList resultList = new ArrayList();
        if (person) {
            //��ѯ���˵�sql���


            String sql =
                    "SELECT distinct a.sbbh, a.yhbs, a.nsrlxdm, a.bz,a.pzzhdm,a.pzzhmc,a.drpch,b.fdcxmmc, b.tdfwzldz, " +
                    "b.cjjgrmb, d.nsrmc, d.sfzjlx, d.jsjdm, d.lxdh,d.sfzjhm " +
                    "FROM QSDB.QS_JL_SBZB a, QSDB.QS_JL_TUFWXX b, QSDB.QS_JL_SBTDFWGL c, " +
                    "QSDB.QS_JL_GRXX d, QSDB.QS_JL_JMSBB e ,QSDB.QS_JL_HDTZS f " +
                    "WHERE a.sbbh = c.sbbh AND b.tdfwid = c.tdfwid AND " +
                    "a.sbbh = d.sbbh AND d.cqrlx='0' AND a.sbbh = e.sbbh AND a.sbbh=f.sbbh" +
                    condition +
                    " order by a.sbbh desc";

            PreparedStatement ps = null;
            Debug.out("SbzbDAO.query(1,2,3) = " + sql);
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    JmsbblBo bo = new JmsbblBo();
                    bo.setSbbh(rs.getString("sbbh"));
                    bo.setYhbs(rs.getString("yhbs"));
                    bo.setNsrlxdm(rs.getString("nsrlxdm"));
                    bo.setBz(rs.getString("bz"));
                    bo.setDrpch(rs.getString("drpch"));

                    bo.setFdcxmmc(rs.getString("fdcxmmc"));
                    bo.setTdfwzldz(rs.getString("tdfwzldz"));
                    bo.setCjjgrmb(rs.getBigDecimal("cjjgrmb"));

                    bo.setJsjdm(rs.getString("jsjdm"));
                    //bo.setNsrmc(rs.getString("nsrmc"));
                    bo.setNsrmc(GrxxDAO.getNsrmcBySbbh(bo.getSbbh(), conn));
                    bo.setSfzjlx(rs.getString("sfzjlx"));
                    bo.setSfzjhm(rs.getString("sfzjhm"));
                    bo.setLxdh(rs.getString("lxdh"));

                    resultList.add(bo);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
                try {
                    ps.close();
                } catch (Exception ex) {
                }

            }
        } else {
            //��ѯ�Ǹ��˵�sql���
            String sql =
                    "SELECT distinct a.sbbh, a.yhbs, a.bz, a.drpch,b.fdcxmmc, b.tdfwzldz, " +
                    "b.cjjgrmb, d.nsrmc, d.jsjdm, d.lxdh,d.nsrlxdm " +
                    "FROM QSDB.QS_JL_SBZB a, QSDB.QS_JL_TUFWXX b, QSDB.QS_JL_SBTDFWGL c, " +
                    "QSDB.QS_JL_FGRXX d , QSDB.QS_JL_JMSBB e " +
                    "WHERE a.sbbh = c.sbbh AND b.tdfwid = c.tdfwid AND " +
                    "a.sbbh = d.sbbh AND a.sbbh = e.sbbh " + condition +
                    " order by a.sbbh desc";
            Debug.out("SbzbDAO.query(1,2,3) = " + sql);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    JmsbblBo bo = new JmsbblBo();
                    bo.setSbbh(rs.getString("sbbh"));
                    bo.setYhbs(rs.getString("yhbs"));
                    bo.setBz(rs.getString("bz"));
                    bo.setDrpch(rs.getString("drpch"));

                    bo.setFdcxmmc(rs.getString("fdcxmmc"));
                    bo.setTdfwzldz(rs.getString("tdfwzldz"));
                    bo.setCjjgrmb(rs.getBigDecimal("cjjgrmb"));

                    bo.setNsrmc(rs.getString("nsrmc"));
                    bo.setJsjdm(rs.getString("jsjdm"));
                    bo.setNsrlxdm(rs.getString("nsrlxdm"));
                    bo.setLxdh(rs.getString("lxdh"));

                    resultList.add(bo);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
                try {
                    ps.close();
                } catch (Exception ex) {
                }
            }

        }
        return resultList;
    }
}
