package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.ttsoft.common.util.Debug;


/**
 * ��˰�˶�֪ͨ��DAO
 */
public class HdtzsDAO extends BaseDAO {

    /**
     * ����һ����˰�˶�֪ͨ���¼
     * @param hdtzs ��˰�˶�֪ͨ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void insert(Hdtzs hdtzs, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_HDTZS (HDTZSH,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,NDZB,WSJC,LSH,FWHM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //�˶�֪ͨ���
            ps.setString(1, hdtzs.hdtzsh);
            //�걨���
            ps.setString(2, hdtzs.sbbh);
            //������
            ps.setString(3, hdtzs.sqr);
            //��Ʒ����Ŀ����
            ps.setString(4, hdtzs.spfxmmc);
            //�����ַ
            ps.setString(5, hdtzs.zldi);
            //�ɽ��۸�
            ps.setBigDecimal(6, hdtzs.cjjg);
            //��˰����
            ps.setBigDecimal(7, hdtzs.jsyj);
            //������˰
            ps.setBigDecimal(8, hdtzs.jzqs);
            //�۳������ѹ�����ס��������
            ps.setBigDecimal(9, hdtzs.kcqyzfx);
            //ʵ��Ӧ����˰
            ps.setBigDecimal(10, hdtzs.sjyz);
            //��ӡʱ��
            ps.setTimestamp(11, hdtzs.dysj);
            //������
            ps.setString(12, hdtzs.jbr);
            //��ϵ�绰
            ps.setString(13, hdtzs.lxdh);
            //¼����
            ps.setString(14, hdtzs.lrr);
            //¼������
            ps.setTimestamp(15, hdtzs.lrrq);
            //������
            ps.setString(16, hdtzs.cjr);
            //��������
            ps.setTimestamp(17, hdtzs.cjrq);
            //������ʶ
            ps.setString(18, hdtzs.bzbs);
            ps.setString(19, hdtzs.ndzb);
            ps.setString(20, hdtzs.wsjc);
            ps.setBigDecimal(21, hdtzs.lsh);
            ps.setString(22, hdtzs.fwhm);
            //ִ��
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰�˶�֪ͨ���¼
     * @param hdtzs ��˰�˶�֪ͨ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void update(Hdtzs hdtzs, Connection conn) throws SQLException {
        String sql = "update  QSDB.QS_JL_HDTZS set HDTZSH=?,SBBH=?,SQR=?,SPFXMMC=?,ZLDI=?,CJJG=?,JSYJ=?,JZQS=?,KCQYZFX=?,SJYZ=?,DYSJ=?,JBR=?,LXDH=?,LRR=?,LRRQ=?,CJR=?,CJRQ=?,BZBS=?,FWHM=?   where hdtzsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdtzs.hdtzsh);
            ps.setString(2, hdtzs.sbbh);
            ps.setString(3, hdtzs.sqr);
            ps.setString(4, hdtzs.spfxmmc);
            ps.setString(5, hdtzs.zldi);
            ps.setBigDecimal(6, hdtzs.cjjg);
            ps.setBigDecimal(7, hdtzs.jsyj);
            ps.setBigDecimal(8, hdtzs.jzqs);
            ps.setBigDecimal(9, hdtzs.kcqyzfx);
            ps.setBigDecimal(10, hdtzs.sjyz);
            ps.setTimestamp(11, hdtzs.dysj);
            ps.setString(12, hdtzs.jbr);
            ps.setString(13, hdtzs.lxdh);
            ps.setString(14, hdtzs.lrr);
            ps.setTimestamp(15, hdtzs.lrrq);
            ps.setString(16, hdtzs.cjr);
            ps.setTimestamp(17, hdtzs.cjrq);
            ps.setString(18, hdtzs.bzbs);
            ps.setString(19, hdtzs.fwhm);
            ps.setString(20, hdtzs.hdtzsh);
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ����һ����˰�˶�֪ͨ���α����
     * @param hdtzs ��˰�˶�֪ͨ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updatefwhm(Hdtzs hdtzs, Connection conn) throws
            SQLException {
        String sql =
                "update  QSDB.QS_JL_HDTZS set FWHM=?   where sbbh = ? and hdtzsh = ? ";
        PreparedStatement ps = null;
        //Debug.out("update FWHM: sql: " + sql);
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdtzs.fwhm);
            ps.setString(2, hdtzs.sbbh);
            ps.setString(3, hdtzs.hdtzsh);

            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * ����ԭ�˶�֪ͨ�������º˶�֪ͨ�����
     * @param hdtzsh ԭ�˶�֪ͨ���
     * @param hdtzsh_xg �޸ĺ�ĺ˶�֪ͨ���
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void updateHdtzsHm(String hdtzsh, String hdtzsh_xg,
                                     Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_HDTZS (HDTZSH,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,NDZB,WSJC,LSH,FWHM)"
                     + " (select ?,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,NDZB,WSJC,LSH,FWHM"
                     + " from QSDB.QS_JL_HDTZS where hdtzsh=? )";

        String sqlmx = "update QSDB.QS_JL_HDJMMX  set hdtzsh=? where hdtzsh=? ";

        String sql_del = "delete from  QSDB.QS_JL_HDTZS  where hdtzsh = ? ";

        Debug.out("into update HdtzsBy hdtzsh: sqlmx: " + sqlmx);

        PreparedStatement ps = null;

        try {
            //���¾ɺ˶�֪ͨ�����ĺ˶�֪ͨ���Ϊ�޸ĺ�ĺ˶�֪ͨ���
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdtzsh_xg);
            ps.setString(2, hdtzsh);
            ps.execute();

            //������ϸ������
            ps = conn.prepareStatement(sqlmx);
            ps.setString(1, hdtzsh_xg);
            ps.setString(2, hdtzsh);
            ps.execute();

            //ɾ���˶�֪ͨ������ľɺ˶�֪ͨ��ŵ����ݡ�
            ps = conn.prepareStatement(sql_del);
            ps.setString(1, hdtzsh);
            ps.execute();
        } catch (SQLException e) {
            Debug.out(e);
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }

    }


    /**
     * ɾ��������˰�˶�֪ͨ���¼
     * @param hdtzsList ��˰�˶�֪ͨ��ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void delete(ArrayList hdtzsList, Connection conn) throws
            SQLException {
        String sql = "delete from  QSDB.QS_JL_HDTZS  where hdtzsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < hdtzsList.size(); i++) {
                Hdtzs hdtzs = (Hdtzs) hdtzsList.get(i);
                ps.setString(1, hdtzs.hdtzsh);
                ps.execute();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * ɾ����˰�˶�֪ͨ���¼
     * @param hdtzsList ��˰�˶�֪ͨ��ֵ���󼯺�
     * @param conn ���ݿ����Ӷ���
     * @throws SQLException
     */
    public static void deleteHdtzsBySbbh(String sbbh, Connection conn) throws
            SQLException {
        String sqlmx = "delete from  QSDB.QS_JL_HDJMMX  where hdtzsh in "
                       +
                       "(select hdtzsh from QSDB.QS_JL_HDTZS where sbbh = ? )";
        String sql = "delete from  QSDB.QS_JL_HDTZS  where sbbh = ? ";

        Debug.out("into delete HdtzsBy sbbh: sqlmx: " + sqlmx);
        Debug.out("into delete HdtzsBy sbbh: sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sqlmx);
            ps.setString(1, sbbh);
            ps.execute();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ps.execute();
        } catch (SQLException e) {
            Debug.out(e);
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * ����������ȡ��˰�˶�֪ͨ��ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ��˰�˶�֪ͨ��ֵ����ļ���
     * @throws SQLException
     */
    public static ArrayList query(String condition, Connection conn) throws
            SQLException {
        ArrayList HdtzsList = new ArrayList();
        String sql = "select HDTZSH,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,FWHM from QSDB.QS_JL_HDTZS " +
                     condition;
        //Debug.out("into query Hdtzs: sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hdtzs Hdtzs1 = new Hdtzs();
                Hdtzs1.setHdtzsh(rs.getString("HDTZSH"));
                Hdtzs1.setSbbh(rs.getString("SBBH"));
                Hdtzs1.setSqr(rs.getString("SQR"));
                Hdtzs1.setSpfxmmc(rs.getString("SPFXMMC"));
                Hdtzs1.setZldi(rs.getString("ZLDI"));
                Hdtzs1.setCjjg(rs.getBigDecimal("CJJG"));
                Hdtzs1.setJsyj(rs.getBigDecimal("JSYJ"));
                Hdtzs1.setJzqs(rs.getBigDecimal("JZQS"));
                Hdtzs1.setKcqyzfx(rs.getBigDecimal("KCQYZFX"));
                Hdtzs1.setSjyz(rs.getBigDecimal("SJYZ"));
                Hdtzs1.setDysj(rs.getTimestamp("DYSJ"));
                Hdtzs1.setJbr(rs.getString("JBR"));
                Hdtzs1.setLxdh(rs.getString("LXDH"));
                Hdtzs1.setLrr(rs.getString("LRR"));
                Hdtzs1.setLrrq(rs.getTimestamp("LRRQ"));
                Hdtzs1.setCjr(rs.getString("CJR"));
                Hdtzs1.setCjrq(rs.getTimestamp("CJRQ"));
                Hdtzs1.setBzbs(rs.getString("BZBS"));
                Hdtzs1.setBzbs(rs.getString("BZBS"));
                Hdtzs1.setFwhm(rs.getString("FWHM"));
                HdtzsList.add(Hdtzs1);
            }
            rs.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return HdtzsList;
    }

    /**
     * ����������ȡ��˰�˶�֪ͨ��ֵ����
     * @param hdtzs ��˰�˶�֪ͨ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰�˶�֪ͨ��ֵ����
     * @throws SQLException
     */
    public static Object get(Hdtzs hdtzs, Connection conn) throws SQLException {
        Hdtzs Hdtzs1 = new Hdtzs();
        String sql = "select HDTZSH,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,FWHM from QSDB.QS_JL_HDTZS   where hdtzsh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdtzs.hdtzsh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hdtzs1.setHdtzsh(rs.getString("HDTZSH"));
                Hdtzs1.setSbbh(rs.getString("SBBH"));
                Hdtzs1.setSqr(rs.getString("SQR"));
                Hdtzs1.setSpfxmmc(rs.getString("SPFXMMC"));
                Hdtzs1.setZldi(rs.getString("ZLDI"));
                Hdtzs1.setCjjg(rs.getBigDecimal("CJJG"));
                Hdtzs1.setJsyj(rs.getBigDecimal("JSYJ"));
                Hdtzs1.setJzqs(rs.getBigDecimal("JZQS"));
                Hdtzs1.setKcqyzfx(rs.getBigDecimal("KCQYZFX"));
                Hdtzs1.setSjyz(rs.getBigDecimal("SJYZ"));
                Hdtzs1.setDysj(rs.getTimestamp("DYSJ"));
                Hdtzs1.setJbr(rs.getString("JBR"));
                Hdtzs1.setLxdh(rs.getString("LXDH"));
                Hdtzs1.setLrr(rs.getString("LRR"));
                Hdtzs1.setLrrq(rs.getTimestamp("LRRQ"));
                Hdtzs1.setCjr(rs.getString("CJR"));
                Hdtzs1.setCjrq(rs.getTimestamp("CJRQ"));
                Hdtzs1.setBzbs(rs.getString("BZBS"));
                Hdtzs1.setFwhm(rs.getString("FWHM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Hdtzs1;
    }

    /**
     * ����������ȡ��˰�˶�֪ͨ��ֵ����
     * @param hdtzs ��˰�˶�֪ͨ��ֵ����
     * @param conn ���ݿ����Ӷ���
     * @return��˰�˶�֪ͨ��ֵ����
     * @throws SQLException
     */
    public static Object getBySbbh(String sbbh, Connection conn) throws
            SQLException {
        Hdtzs Hdtzs1 = null;
        String sql = "select HDTZSH,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,FWHM from QSDB.QS_JL_HDTZS   where sbbh = ? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sbbh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hdtzs1 = new Hdtzs();
                Hdtzs1.setHdtzsh(rs.getString("HDTZSH"));
                Hdtzs1.setSbbh(rs.getString("SBBH"));
                Hdtzs1.setSqr(rs.getString("SQR"));
                Hdtzs1.setSpfxmmc(rs.getString("SPFXMMC"));
                Hdtzs1.setZldi(rs.getString("ZLDI"));
                Hdtzs1.setCjjg(rs.getBigDecimal("CJJG"));
                Hdtzs1.setJsyj(rs.getBigDecimal("JSYJ"));
                Hdtzs1.setJzqs(rs.getBigDecimal("JZQS"));
                Hdtzs1.setKcqyzfx(rs.getBigDecimal("KCQYZFX"));
                Hdtzs1.setSjyz(rs.getBigDecimal("SJYZ"));
                Hdtzs1.setDysj(rs.getTimestamp("DYSJ"));
                Hdtzs1.setJbr(rs.getString("JBR"));
                Hdtzs1.setLxdh(rs.getString("LXDH"));
                Hdtzs1.setLrr(rs.getString("LRR"));
                Hdtzs1.setLrrq(rs.getTimestamp("LRRQ"));
                Hdtzs1.setCjr(rs.getString("CJR"));
                Hdtzs1.setCjrq(rs.getTimestamp("CJRQ"));
                Hdtzs1.setBzbs(rs.getString("BZBS"));
                Hdtzs1.setFwhm(rs.getString("FWHM"));
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return Hdtzs1;
    }

    /**
     * ר��Ϊ��ӡ�˶�֪ͨ��ʱ���ݹ���ס��or�������÷�����˶�������ϸ�������Ӱ���ӡ��Ӧ�ļ����ݵķ��� 
     * @param sbbh String
     * @param conn Connection
     * @throws SQLException
     */
    public static ArrayList getFwlx(String sbbh, Connection conn) throws
        SQLException
    {
        //Ĭ�Ϸ�������Ϊ����ס��
    	ArrayList list=new ArrayList();
        String sqlj = "select sum(bcdke) jej from qsdb.qs_jl_sbgyzf WHERE sbbh = ? AND yggyzfqszsh LIKE '%������%' ";
        String sqlg = "select sum(bcdke) jeg from qsdb.qs_jl_sbgyzf WHERE sbbh = ? AND yggyzfqszsh LIKE '%������%' ";
        PreparedStatement ps = null;
        ResultSet rs = null;        
        try
        {
            ps = conn.prepareStatement(sqlj);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            if(rs.next())
            {
            	if(rs.getString("jej")!=null){
                	HashMap map = new HashMap();
            		map.put("lx","2");
                	map.put("je",rs.getBigDecimal("jej"));
                	list.add(map);
            	}
            }

            ps = conn.prepareStatement(sqlg);
            ps.setString(1, sbbh);
            rs = ps.executeQuery();
            if(rs.next())
            {
            	if(rs.getString("jeg")!=null){
                	HashMap map = new HashMap();
            		map.put("lx","1");
                	map.put("je",rs.getBigDecimal("jeg"));
                	list.add(map);           		
            	}
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
            ps.close();
        }
        return list;
    }
}
