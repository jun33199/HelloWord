package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Pzlr;
import com.creationstar.bjtax.qsgl.model.bo.PzlrBo;
import com.creationstar.bjtax.qsgl.util.Arith;
import com.creationstar.bjtax.qsgl.util.DataConvert;


/**
 * ����¼��DAO
 */
public class PzlrDAO extends BaseDAO {
    /**
     * ���ݼ���������ȡ��˰�˻�����Ϣ
     * @param bo PzlrBo
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static Object getNsrxx(PzlrBo bo, Connection conn) throws
            SQLException {

        //��ʼ��Statement
        PreparedStatement ps = null;
        String sql = "select a.nsrmc,b.swjgzzjgmc "
                     + "from djdb.dj_jl_jbsj a,dmdb.gy_dm_swjgzzjg b "
                     + "where a.jsjdm='" + bo.getJsjdm()
                     + "' and a.swjgzzjgdm=b.swjgzzjgdm";
        System.out.println("sql===" + sql);
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bo.setNsrmc(rs.getString("NSRMC"));
                bo.setSsdwmc(rs.getString("SWJGZZJGMC"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return bo;
    }

    /**
     * ���ݷ��ز���Ŀ���ƻ�ȡ������Ϣ
     * @param bo PzlrBo ���ҵ���������װǰ̨����Ĳ���
     * @param conn Connection
     * @return Object
     * @throws SQLException
     */
    public static Object getFwxx(PzlrBo bo, Connection conn) throws
            SQLException {
        //��ʼ��Statement
        PreparedStatement ps = null;
        List list = new ArrayList();
        //��ѯ�����Ƿ����������Ա��
        String sql =
                "select id,fdcxmmc,jsjdm,tdjb,rjl,jzmj,pjjyjg,fwmtjg,to_char(syqsrq,'yyyymmdd') qsrq,"
                + "to_char(syjsrq,'yyyymmdd') jsrq,czcqxx,czcsxx,czfwjyxx,czr,to_char(czrq,'yyyymmdd') czrq from qsdb.qs_jl_pzlr ";
        /**
         * ����ҳ��������id�ж��ǽ��и��²������ǽ������������������������������
         * ���������ͷ��ز���Ŀ���ƽ��в�ѯ�����²��������ҳ�洫����id���в�ѯ��
         * ��ȷ��������ʷ��ķ��ز���Ŀ����Ϊ�޸�֮ǰ����Ŀ����
         */
        if (null != bo.getId() && "" != bo.getId()) {
            sql = sql + "where id=" + Integer.parseInt(bo.getId());
        } else {
            sql = sql + "where jsjdm='" + bo.getJsjdm() + "' and fdcxmmc='" +
                  bo.getFdcxmmc() + "'";
        }

        System.out.println("sql===" + sql);
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pzlr vo = new Pzlr();
                vo.setId(rs.getString("ID"));
                vo.setFdcxmmc(rs.getString("FDCXMMC"));

                vo.setTdjb(rs.getString("TDJB"));
                vo.setRjl(rs.getString("RJL"));
                vo.setJzmj(rs.getString("JZMJ"));
                vo.setPjjyjg(rs.getString("PJJYJG"));
                vo.setFwmtjg(rs.getString("FWMTJG"));
                vo.setQsrq(rs.getString("QSRQ"));
                vo.setJsrq(rs.getString("JSRQ"));
                vo.setCzcqxx(rs.getString("CZCQXX"));
                vo.setCzcsxx(rs.getString("CZCSXX"));
                vo.setCzfwjyxx(rs.getString("CZFWJYXX"));
                vo.setCzr(rs.getString("CZR"));
                vo.setCzsj(rs.getString("CZRQ"));
                list.add(vo);
            }

            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return list;

    }

    /**
     * ���淿����Ϣ
     * @param bo PzlrBo
     * @param conn Connection
     * @throws SQLException
     */
    public static String saveFwxx(PzlrBo bo, Connection conn) throws
            SQLException {
        if (bo.getId() != null && !"".equals(bo.getId())) {
            List list = (List) getFwxx(bo, conn);
            //�ж��ǽ��в���������ǽ��и��²�����list����0�����и���
            if (null != list && list.size() > 0) {
                Pzlr vo = (Pzlr) list.get(0);
                return updateFwxx(vo, bo, conn);
            }
        }

        //��ʼ��Statement
        PreparedStatement ps = null;

        String result = "true";

        String insert_sql = "insert into qsdb.qs_jl_pzlr values(qsdb.seq_jl_pzlr.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            ps = conn.prepareStatement(insert_sql);
            ps.setString(1, bo.getJsjdm());
            ps.setString(2, bo.getFdcxmmc());
            ps.setString(3, bo.getTdjb());
            ps.setString(4, bo.getRjl());
            String jzmj = Arith.roundStr(bo.getJzmj(), 3);
            System.out.println("jzmj===" + DataConvert.String2BigDecimal(jzmj));
            ps.setBigDecimal(5, DataConvert.String2BigDecimal(jzmj));
            ps.setBigDecimal(6, DataConvert.String2BigDecimal(bo.getPjjyjg()));
            ps.setTimestamp(7, DataConvert.String2Timestamp(bo.getQsrq()));
            ps.setTimestamp(8, DataConvert.String2Timestamp(bo.getJzrq()));
            ps.setString(9, bo.getCzcq());
            ps.setString(10, bo.getCzcsxx());
            ps.setString(11, bo.getCzfwjyxx());
            ps.setString(12, bo.getCzry());
            ps.setTimestamp(13, DataConvert.String2Timestamp(bo.getXtdqsj()));
            ps.setString(14, bo.getCzry());
            ps.setTimestamp(15, DataConvert.String2Timestamp(bo.getXtdqsj()));
            System.out.println("fwmtjg===" + DataConvert.String2BigDecimal(bo.getFwmtjg()));
            ps.setBigDecimal(16, DataConvert.String2BigDecimal(bo.getFwmtjg()));
            ps.execute();
            result = getId(bo, conn);
        } catch (Exception e) {
            result = "false";
            e.printStackTrace();
        } finally {
            ps.close();
        }
        return result;
    }

    /**
     * �Ѿ����ڵ���Ŀ���Խ����޸�
     * @param bo PzlrBo
     * @param conn Connection
     * @return String
     * @throws SQLException
     */
    public static String updateFwxx(Pzlr vo, PzlrBo bo, Connection conn) throws
            SQLException {
        String jzmj = Arith.roundStr(bo.getJzmj(), 3);
        StringBuffer sb = new StringBuffer();
        sb.append("update qsdb.qs_jl_pzlr set ");
        sb.append("fdcxmmc='" + bo.getFdcxmmc() + "',");
        sb.append("tdjb='" + bo.getTdjb() + "',");
        sb.append("rjl='" + bo.getRjl() + "',");
        sb.append("jzmj='" + DataConvert.String2BigDecimal(jzmj) + "',");
        sb.append("pjjyjg='" + DataConvert.String2BigDecimal(bo.getPjjyjg()) +
                  "',");
        sb.append("fwmtjg='" + DataConvert.String2BigDecimal(bo.getFwmtjg()) +
                "',");
        sb.append("syqsrq=to_date(" + bo.getQsrq() + ",'yyyy-mm-dd'),");
        sb.append("syjsrq=to_date(" + bo.getJzrq() + ",'yyyy-mm-dd'),");
        sb.append("czcqxx='" + bo.getCzcq() + "',");
        sb.append("czcsxx='" + bo.getCzcsxx() + "',");
        sb.append("czfwjyxx='" + bo.getCzfwjyxx() + "',");
        sb.append("czr='" + bo.getCzry() + "',");
        sb.append("czrq=to_date(" + bo.getXtdqsj() + ",'yyyy-mm-dd'),");
        sb.append("cjr='" + bo.getCzry() + "',");
        sb.append("cjrq=to_date(" + bo.getXtdqsj() + ",'yyyy-mm-dd') ");
        sb.append("where id=" + Integer.parseInt(vo.getId()));
        System.out.println("update sql==" + sb.toString());

        //��ʼ��Statement
        PreparedStatement ps = null;

        String result = "";

        try {
            //ִ���޸�����¼������Ĳ���
            ps = conn.prepareStatement(sb.toString());
            ps.execute();
            //ִ�в�����ʷ��Ĳ���
            result = insertLsb(vo, bo, conn, "0");

        } catch (Exception e) {
            result = "false";

            e.printStackTrace();
        } finally {
            ps.close();
        }
        return result;
    }


    public static String insertLsb(Pzlr vo, PzlrBo bo, Connection conn,
                                   String czlx) throws SQLException {
        String result = bo.getId();
        String sql = "insert into qsdb.qs_jl_pzlr_lsb values("
                     + "qsdb.seq_jl_pzlr.nextval,?,?,?,?,?,"
                     + "?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //��ʼ��Statement
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if ("1".equals(czlx)) {
                ps.setInt(1, Integer.parseInt(bo.getId()));
                ps.setString(3, bo.getFdcxmmc());
                ps.setString(4, bo.getTdjb());
                ps.setString(5, bo.getRjl());
                String jzmj = Arith.roundStr(bo.getJzmj(), 3);

                ps.setBigDecimal(6, DataConvert.String2BigDecimal(jzmj));
                ps.setBigDecimal(7, DataConvert.String2BigDecimal(bo.getPjjyjg()));
                ps.setTimestamp(8, DataConvert.String2Timestamp(bo.getQsrq()));
                ps.setTimestamp(9, DataConvert.String2Timestamp(bo.getJzrq()));
                ps.setString(10, bo.getCzcq());
                ps.setString(11, bo.getCzcsxx());
                ps.setString(12, bo.getCzfwjyxx());
                ps.setString(13, bo.getCzry());
                ps.setTimestamp(14, DataConvert.String2Timestamp(bo.getXtdqsj()));
                ps.setBigDecimal(18, DataConvert.String2BigDecimal(bo.getFwmtjg()));

            } else {
                ps.setInt(1, Integer.parseInt(vo.getId()));
                ps.setString(3, vo.getFdcxmmc());
                ps.setString(4, vo.getTdjb());
                ps.setString(5, vo.getRjl());
                String jzmj = Arith.roundStr(vo.getJzmj(), 3);

                ps.setBigDecimal(6, DataConvert.String2BigDecimal(jzmj));
                ps.setBigDecimal(7, DataConvert.String2BigDecimal(vo.getPjjyjg()));
                ps.setTimestamp(8, DataConvert.String2Timestamp(vo.getQsrq()));
                ps.setTimestamp(9, DataConvert.String2Timestamp(vo.getJsrq()));
                ps.setString(10, vo.getCzcqxx());
                ps.setString(11, vo.getCzcsxx());
                ps.setString(12, vo.getCzfwjyxx());
                ps.setString(13, vo.getCzr());
                ps.setTimestamp(14, DataConvert.String2Timestamp(vo.getCzsj()));
                ps.setBigDecimal(18, DataConvert.String2BigDecimal(bo.getFwmtjg()));

            }

            ps.setString(2, bo.getJsjdm());
            ps.setString(15, czlx);
            ps.setString(16, bo.getCzry());
            ps.setTimestamp(17, DataConvert.String2Timestamp(bo.getXtdqsj()));
            System.out.println("lsb sql==" + ps.toString());
            ps.execute();

        } catch (Exception e) {
            result = "false";

            e.printStackTrace();
        } finally {
            ps.close();
        }
        return result;
    }

    /**
     * ɾ��������Ϣ
     * @param bo PzlrBo
     * @param conn Connection
     * @return String
     * @throws SQLException
     */
    public static void delFwxx(PzlrBo bo, Connection conn) throws SQLException {

        String sql = "delete from qsdb.qs_jl_pzlr where id=" +
                     Integer.parseInt(bo.getId());
        PreparedStatement ps = null;
        Pzlr vo = new Pzlr();
        try {
            ps = conn.prepareStatement(sql);
            ps.execute();
            insertLsb(vo, bo, conn, "1");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            ps.close();
        }
    }

    /**
     * ������Ŀ���ƺ�jsjdm��ѯ������id�ֶ�
     * @param bo PzlrBo
     * @param conn Connection
     * @throws SQLException
     */
    public static String getId(PzlrBo bo, Connection conn) throws SQLException {
        String sql = "select id from qsdb.qs_jl_pzlr "
                     + "where jsjdm='" + bo.getJsjdm() + "' "
                     + "and fdcxmmc='" + bo.getFdcxmmc() + "'";
        PreparedStatement ps = null;
        String id = "";
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("ID");
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            ps.close();
        }
        return id;
    }
//    /**
//     * ������Ŀ���ƺ�jsjdm��ѯ������id�ֶ�
//     * @param bo PzlrBo
//     * @param conn Connection
//     * @throws SQLException
//     */
//    public static String getXmmc(PzlrBo bo,Connection conn)throws SQLException{
//        String sql = "select fdcxmmc from qsdb.qs_jl_pzlr where id="+bo.getId();
//
//        PreparedStatement ps = null;
//        String xmmc="";
//        try{
//             ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery();
//             while(rs.next()){
//                 xmmc = rs.getString("FDCXMMC");
//             }
//         }catch(Exception e){
//
//                e.printStackTrace();
//         }finally{
//             ps.close();
//         }
//         return xmmc;
//    }


}
