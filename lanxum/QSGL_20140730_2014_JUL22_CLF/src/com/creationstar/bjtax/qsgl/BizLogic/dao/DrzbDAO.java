package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo2;
import com.creationstar.bjtax.qsgl.util.ZipUtils;
import com.ttsoft.common.util.Debug;

/**
 * ��������DAO
 */
public class DrzbDAO extends BaseDAO {


    /**
     * ��ѯ����Ϣ�Ƿ��Ѿ��������
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static boolean querydrFgrxx(String fdcmc, String zldz, String jsjdm,
                                       String nsrmc, Connection conn) throws
            Exception {
        boolean flag = false;
        String sql = "select * from qsdb.qs_jl_tufwxx a,qsdb.QS_JL_SBTDFWGL b,qsdb.qs_jl_fgrxx c,qsdb.qs_jl_sbzb d " +
                     "where a.tdfwid=b.tdfwid and b.sbbh=c.sbbh and b.sbbh=d.sbbh and d.sjly='05' and (to_char(sysdate,'yyyy')-to_char(d.cjrq,'yyyy'))<1 and a.fdcxmmc='" +
                     fdcmc + "' and " +
                     " a.tdfwzldz='" + zldz + "' and c.jsjdm='" + jsjdm +
                     "' and c.nsrmc='" + nsrmc + "'";
        //Debug.out("������������Ĳ�ѯ��� " + sql);
        //System.out.println("�����Բ�ѯ�Ǹ���: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return flag;
    }

    /**
     * ��ѯ����Ϣ�Ƿ��Ѿ��������
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static boolean querydrFgrxx2(String fdcmc, String zldz, String jsjdm,
                                        String nsrmc, Connection conn) throws
            Exception {
        boolean flag = false;
        String sql = "select * from qsdb.qs_jl_tufwxx a,qsdb.QS_JL_SBTDFWGL b,qsdb.qs_jl_fgrxx c,qsdb.qs_jl_sbzb d " +
                     "where a.tdfwid=b.tdfwid and b.sbbh=c.sbbh and b.sbbh=d.sbbh and d.sjly='04' and (to_char(sysdate,'yyyy')-to_char(d.cjrq,'yyyy'))<1 and a.fdcxmmc='" +
                     fdcmc + "' and " +
                     " a.tdfwzldz='" + zldz + "' and c.jsjdm='" + jsjdm +
                     "' and c.nsrmc='" + nsrmc + "'";
        //Debug.out("������������Ĳ�ѯ��� " + sql);
        //System.out.println("�����Բ�ѯ�Ǹ���: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return flag;
    }

    /**
     * ��ѯ����Ϣ�Ƿ��Ѿ��������
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static boolean querydrGrxx(String fdcmc, String zldz, String zjlx,
                                      String zjhm, String nsrmc,
                                      Connection conn) throws
            Exception {
        boolean flag = false;
        String sql = " select * from qsdb.qs_jl_tufwxx a,qsdb.QS_JL_SBTDFWGL b,qsdb.qs_jl_grxx c,qsdb.qs_jl_sbzb d " +
                     " where a.tdfwid=b.tdfwid and b.sbbh=c.sbbh and b.sbbh=d.sbbh and d.sjly='05' and (to_char(sysdate,'yyyy')-to_char(d.cjrq,'yyyy')) <1 and a.fdcxmmc='" +
                     fdcmc + "' and a.tdfwzldz='" + zldz + "' and " +
                     " c.sfzjlx='" + zjlx + "' and c.sfzjhm='" + zjhm +
                     "' and c.nsrmc='" + nsrmc + "'";
        //Debug.out("������������Ĳ�ѯ��� " + sql);
        //System.out.println("�����Բ�ѯ����: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return flag;
    }

    /**
     * ��ѯ����Ϣ�Ƿ��Ѿ��������
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static boolean querydrGrxx2(String fdcmc, String zldz, String zjlx,
                                       String zjhm, String nsrmc,
                                       Connection conn) throws
            Exception {
        boolean flag = false;
        String sql = " select * from qsdb.qs_jl_tufwxx a,qsdb.QS_JL_SBTDFWGL b,qsdb.qs_jl_grxx c,qsdb.qs_jl_sbzb d " +
                     " where a.tdfwid=b.tdfwid and b.sbbh=c.sbbh and b.sbbh=d.sbbh and d.sjly='04' and (to_char(sysdate,'yyyy')-to_char(d.cjrq,'yyyy'))<1 and a.fdcxmmc='" +
                     fdcmc + "' and a.tdfwzldz='" + zldz + "' and " +
                     " c.sfzjlx='" + zjlx + "' and c.sfzjhm='" + zjhm +
                     "' and c.nsrmc='" + nsrmc + "'";
        //Debug.out("������������Ĳ�ѯ��� " + sql);
        //System.out.println("�����Բ�ѯ����: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return flag;
    }


    /**
     * ��������ʱ��ȡ����xml��������Ϣ
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static boolean queryPzxx(String jsjdm, String nsrmc, Connection conn) throws
            Exception {
        boolean flag = false;
        String sql = "select * from qsdb.qs_jl_pzlr a where a.jsjdm='" + jsjdm +
                     "' and a.fdcxmmc='" + nsrmc + "'";
        //Debug.out("������������Ĳ�ѯ��� " + sql);
        //System.out.println("������������Ĳ�ѯ��� " + sql);
        System.out.println("=====DrzbDAO.queryPzxx====sql=====>>>>" + sql.toString());
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return flag;
    }


    /**
     * ����һ���������������¼
     * @param Drzb ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void insert(Drzb drzb, Connection conn) throws Exception {
        String sql = "insert into QSDB.QS_JL_DRZB (drpch,xh,nsrmc,nsrjsjdm,fdcxmmc,fdcdz,htqdrq,cjjg,drsjnr,ztbs,drczr,drsj,bz,sbbh) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�������κ�
            ps.setString(1, drzb.drpch);
            //���
            ps.setBigDecimal(2, drzb.xh);
            //��˰������
            ps.setString(3, drzb.nsrmc);
            //��˰�˼��������
            ps.setString(4, drzb.nsrjsjdm);
            //���ز���Ŀ����
            ps.setString(5, drzb.fdcxmmc);
            //���ز���ַ
            ps.setString(6, drzb.fdcdz);
            //��ͬǩ������
            ps.setTimestamp(7, drzb.htqdrq);
            //�ɽ��۸�
            ps.setBigDecimal(8, drzb.cjjg);
            //������������
            ps.setString(9, ZipUtils.compress(drzb.drsjnr));
            //״̬��ʶ
            ps.setString(10, drzb.ztbs);
            //���������
            ps.setString(11, drzb.drczr);
            //����ʱ��
            ps.setTimestamp(12, drzb.drsj);
            //��ע
            ps.setString(13, drzb.bz);
            //�걨���
            ps.setString(14, drzb.sbbh);
            //ִ��
            ps.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���������������¼
     * @param Drzb ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void insertSql(Drzb drzb, Connection conn, String sjly) throws
            Exception {
        StringBuffer sql = new StringBuffer();
        Statement ps = null;
        try {
            ps = conn.createStatement();
            String htqdrq = drzb.htqdrq.toString().substring(0, 19);
            String drsj = drzb.drsj.toString().substring(0, 19);
            String drsjnr = ZipUtils.compress(drzb.drsjnr);

            sql = sql.append("insert into QSDB.QS_JL_DRZB (drpch,xh,nsrmc,nsrjsjdm,fdcxmmc,fdcdz,htqdrq,cjjg,drsjnr,ztbs,drczr,drsj,bz,sbbh,sjly)")
                  .append(" values (")
                  .append("'" + drzb.drpch + "'")
                  .append(",")
                  .append("'" + drzb.xh + "'")
                  .append(",")
                  .append("'" + drzb.nsrmc + "'")
                  .append(",")
                  .append("'" + drzb.nsrjsjdm + "'")
                  .append(",")
                  .append("'" + drzb.fdcxmmc + "'")
                  .append(",")
                  .append("'" + drzb.fdcdz + "'")
                  .append(",")
                  .append("to_date('" + htqdrq + "','yyyy-mm-dd hh24:mi:ss')")
                  .append(",")
                  .append("'" + drzb.cjjg + "'")
                  .append(",")
                  .append("'" + drsjnr + "'")
                  .append(",")
                  .append("'" + drzb.ztbs + "'")
                  .append(",")
                  .append("'" + drzb.drczr + "'")
                  .append(",")
                  .append("to_date('" + drsj + "', 'yyyy-mm-dd hh24:mi:ss')")
                  .append(",")
                  .append("'" + drzb.bz + "'")
                  .append(",")
                  .append("'" + drzb.sbbh + "'")
                  .append(",")
                  .append("'" + sjly + "'")
                  .append(" )");
            //System.out.println("��˰��������sql:" + sql.toString());
            ps.execute(sql.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���������������¼
     * @param zcwh ����ά��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void update(Drzb drzb, Connection conn) throws Exception {
        String sql = "update  QSDB.QS_JL_DRZB set drpch=?,xh=?,nsrmc=?,nsrjsjdm=?,fdcxmmc=?,fdcdz=?,htqdrq=?,cjjg=?,drsjnr=?,ztbs=?,drczr=?,drsj=?,bz=?,sbbh=?   where drpch = ? and xh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�������κ�
            ps.setString(1, drzb.drpch);
            //���
            ps.setBigDecimal(2, drzb.xh);
            //��˰������
            ps.setString(3, drzb.nsrmc);
            //��˰�˼��������
            ps.setString(4, drzb.nsrjsjdm);
            //���ز���Ŀ����
            ps.setString(5, drzb.fdcxmmc);
            //���ز���ַ
            ps.setString(6, drzb.fdcdz);
            //��ͬǩ������
            ps.setTimestamp(7, drzb.htqdrq);
            //�ɽ��۸�
            ps.setBigDecimal(8, drzb.cjjg);
            //������������
            ps.setString(9, ZipUtils.compress(drzb.drsjnr));
            //״̬��ʶ
            ps.setString(10, drzb.ztbs);
            //���������
            ps.setString(11, drzb.drczr);
            //����ʱ��
            ps.setTimestamp(12, drzb.drsj);
            //��ע
            ps.setString(13, drzb.bz);
            ps.setString(14, drzb.sbbh);

            ps.setString(15, drzb.drpch);
            ps.setBigDecimal(16, drzb.xh);
            ps.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���������������¼״̬
     * @param zcwh ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void updateZt(Drzb drzb, Connection conn) throws Exception {
        String sql =
                "update  QSDB.QS_JL_DRZB set ztbs=?   where drpch = ? and xh = ? ";
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql);
            //��˰������
            ps.setString(1, drzb.ztbs);
            //�������κ�
            ps.setString(2, drzb.drpch);
            //���
            ps.setBigDecimal(3, drzb.xh);

            ps.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ���������������¼�걨���
     * @param zcwh ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void updateSbbh(Drzb drzb, Connection conn) throws
            Exception {
        String sql =
                "update  QSDB.QS_JL_DRZB set sbbh=?   where drpch = ? and xh = ? ";
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql);
            //��˰������
            ps.setString(1, drzb.sbbh);
            //�������κ�
            ps.setString(2, drzb.drpch);
            //���
            ps.setBigDecimal(3, drzb.xh);

            ps.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ�������������������¼
     * @param drzbList ������������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void delete(ArrayList drzbList, Connection conn) throws
            Exception {
        String sql =
                "delete from  QSDB.QS_JL_DRZB where drpch = ? and xh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < drzbList.size(); i++) {
                Drzb drzb = (Drzb) drzbList.get(i);
                ps.setString(1, drzb.drpch);
                ps.setBigDecimal(2, drzb.xh);
                ps.execute();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����������ȡ������������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static ArrayList query(String condition, Connection conn) throws
            Exception {
        ArrayList DrzbList = new ArrayList();
        String sql = "select drpch,xh,nsrmc,nsrjsjdm,fdcxmmc,fdcdz,htqdrq,cjjg,drsjnr,ztbs,drczr,drsj,bz,sbbh from QSDB.QS_JL_DRZB where 1=1" +
                     condition;
        Debug.out("������������Ĳ�ѯ��� " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Drzb drzb = new Drzb();
                drzb.setDrpch(rs.getString("drpch"));
                drzb.setXh(rs.getBigDecimal("xh"));
                drzb.setNsrmc(rs.getString("nsrmc"));
                drzb.setNsrjsjdm(rs.getString("nsrjsjdm"));
                drzb.setFdcxmmc(rs.getString("fdcxmmc"));
                drzb.setFdcdz(rs.getString("fdcdz"));
                drzb.setHtqdrq(rs.getTimestamp("htqdrq"));
                drzb.setCjjg(rs.getBigDecimal("cjjg"));
                drzb.setDrsjnr(ZipUtils.decompress(rs.getString("drsjnr")));
                drzb.setZtbs(rs.getString("ztbs"));
                drzb.setDrczr(rs.getString("drczr"));
                drzb.setDrsj(rs.getTimestamp("drsj"));
                drzb.setBz(rs.getString("bz"));
                drzb.setSbbh(rs.getString("sbbh"));

                DrzbList.add(drzb);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return DrzbList;
    }

    /**
     * ����������ȡ������������ֵ����
     * @param drzb ����ά��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return����ά��ֵ����
     * @throws Exception
     */
    public static Object get(Drzb drzb, Connection conn) throws Exception {
        Drzb drzb1 = new Drzb();
        String sql = "select drpch,xh,nsrmc,nsrjsjdm,fdcxmmc,fdcdz,htqdrq,cjjg,drsjnr,ztbs,drczr,drsj,bz,sbbh from QSDB.QS_JL_DRZB   where drpch = ? and xh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, drzb.drpch);
            ps.setBigDecimal(2, drzb.xh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                drzb1.setDrpch(rs.getString("drpch"));
                drzb1.setXh(rs.getBigDecimal("xh"));
                drzb1.setNsrmc(rs.getString("nsrmc"));
                drzb1.setNsrjsjdm(rs.getString("nsrjsjdm"));
                drzb1.setFdcxmmc(rs.getString("fdcxmmc"));
                drzb1.setFdcdz(rs.getString("fdcdz"));
                drzb1.setHtqdrq(rs.getTimestamp("htqdrq"));
                drzb1.setCjjg(rs.getBigDecimal("cjjg"));
                drzb1.setDrsjnr(ZipUtils.decompress(rs.getString("drsjnr")));
                drzb1.setZtbs(rs.getString("ztbs"));
                drzb1.setDrczr(rs.getString("drczr"));
                drzb1.setDrsj(rs.getTimestamp("drsj"));
                drzb1.setBz(rs.getString("bz"));
                drzb1.setSbbh(rs.getString("sbbh"));
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return drzb1;
    }


    /**
     * ����������ȡ������������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     */
    public static ArrayList queryDrxx(String condition, Connection conn) throws
            Exception {
        ArrayList DrzbList = new ArrayList();
        String sql = "select a.drpch,a.xh,a.nsrmc,a.nsrjsjdm,a.fdcxmmc,a.fdcdz,a.htqdrq,a.cjjg,a.drsjnr,a.ztbs,a.drczr,a.drsj,a.bz,a.sbbh,b.drbs,b.tgzlx,b.tgzmc,b.tgzgjdm,b.tgzgjmc,b.tgzsfzjlx,b.tgzsfzjhm,b.tgzjsjdm,b.tjsj,b.drsj,b.jsfsdm,b.jsfmc from QSDB.QS_JL_DRZB a,QSDB.QS_JL_DRPCINFO b where a.sjly='02' and b.sjly='02' and 1=1" +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlsbBo bo = new PlsbBo();
                Drzb drzb = new Drzb();
                Drpcinfo drpcinfo = new Drpcinfo();

                drzb.setDrpch(rs.getString("drpch"));
                drzb.setXh(rs.getBigDecimal("xh"));
                drzb.setNsrmc(rs.getString("nsrmc"));
                drzb.setNsrjsjdm(rs.getString("nsrjsjdm"));
                drzb.setFdcxmmc(rs.getString("fdcxmmc"));
                drzb.setFdcdz(rs.getString("fdcdz"));
                drzb.setHtqdrq(rs.getTimestamp("htqdrq"));
                drzb.setCjjg(rs.getBigDecimal("cjjg"));
                drzb.setDrsjnr(ZipUtils.decompress(rs.getString("drsjnr")));
                drzb.setZtbs(rs.getString("ztbs"));
                drzb.setDrczr(rs.getString("drczr"));
                drzb.setDrsj(rs.getTimestamp("drsj"));
                drzb.setBz(rs.getString("bz"));
                drzb.setSbbh(rs.getString("sbbh"));

                drpcinfo.setDrpch(drzb.getDrpch());
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
//                bo.setDrpch(drzb.drpch);
//                bo.setXh(drzb.xh.toString());
                bo.setDrzb(drzb);
                bo.setDrpcInfo(drpcinfo);
                DrzbList.add(bo);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return DrzbList;
    }

    /**
     * ����������ȡ������������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ������������ֵ����ļ���
     * @throws Exception
     *
     * ˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
     */
    public static ArrayList queryDrxx2(String condition, Connection conn) throws
            Exception {
        ArrayList DrzbList = new ArrayList();
        String sql = "select a.drpch,a.xh,a.nsrmc,a.nsrjsjdm,a.fdcxmmc,a.fdcdz,a.htqdrq,a.cjjg,a.drsjnr,a.ztbs,a.drczr,a.drsj,a.bz,a.sbbh,b.drbs,b.tgzlx,b.tgzmc,b.tgzgjdm,b.tgzgjmc,b.tgzsfzjlx,b.tgzsfzjhm,b.tgzjsjdm,b.tjsj,b.drsj,b.jsfsdm,b.jsfmc from QSDB.QS_JL_DRZB a,QSDB.QS_JL_DRPCINFO b where a.sjly='01' and b.sjly='01' and 1=1" +
                     condition;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlsbBo2 bo = new PlsbBo2();
                Drzb drzb = new Drzb();
                Drpcinfo drpcinfo = new Drpcinfo();

                drzb.setDrpch(rs.getString("drpch"));
                drzb.setXh(rs.getBigDecimal("xh"));
                drzb.setNsrmc(rs.getString("nsrmc"));
                drzb.setNsrjsjdm(rs.getString("nsrjsjdm"));
                drzb.setFdcxmmc(rs.getString("fdcxmmc"));
                drzb.setFdcdz(rs.getString("fdcdz"));
                drzb.setHtqdrq(rs.getTimestamp("htqdrq"));
                drzb.setCjjg(rs.getBigDecimal("cjjg"));
                drzb.setDrsjnr(ZipUtils.decompress(rs.getString("drsjnr")));
                drzb.setZtbs(rs.getString("ztbs"));
                drzb.setDrczr(rs.getString("drczr"));
                drzb.setDrsj(rs.getTimestamp("drsj"));
                drzb.setBz(rs.getString("bz"));
                drzb.setSbbh(rs.getString("sbbh"));

                drpcinfo.setDrpch(drzb.getDrpch());
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
//                bo.setDrpch(drzb.drpch);
//                bo.setXh(drzb.xh.toString());
                bo.setDrzb(drzb);
                bo.setDrpcInfo(drpcinfo);
                DrzbList.add(bo);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return DrzbList;
    }


    /**
     * �õ��������
     * @param drpch �������κ�
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */

    public static Object getMaxXh(String drpch, Connection conn) throws
            Exception {
        String lsMaxXh = null;
        String sql = "select max(xh) from QSDB.QS_JL_DRZB   where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, drpch);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lsMaxXh = rs.getString(1);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return lsMaxXh;
    }

    /**
     * ����һ���������������¼״̬
     * @param zcwh ��������ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void updateAll(Drzb drzb, Connection conn) throws
            Exception {
        String sql =
                "update  QSDB.QS_JL_DRZB set ztbs=?,sbbh=?   where drpch = ? and xh = ? ";
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql);
            //��˰������
            ps.setString(1, drzb.ztbs);
            //�걨���
            ps.setString(2, drzb.sbbh);
            //�������κ�
            ps.setString(3, drzb.drpch);
            //���
            ps.setBigDecimal(4, drzb.xh);

            ps.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * �õ��Ѿ�����ı���
     *
     * @param lsDrpch �������κ�
     * @param conn ���ݿ����Ӷ���
     * @return int ���صı���
     */
    public static int getDrbs(String lsDrpch, Connection conn) throws Exception {
        int liDrbs = 0;
        String sql = "select count(*) from QSDB.QS_JL_DRZB   where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, lsDrpch);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                liDrbs = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return liDrbs;
    }


    /**
     * ɾ��������
     * @param drzbList ������������ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws Exception
     */
    public static void deletePc(String pch, Connection conn) throws
            Exception {
        String sql =
                "delete from  QSDB.QS_JL_DRZB where drpch = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pch);
            ps.execute();

        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

}
