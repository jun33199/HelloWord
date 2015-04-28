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
 * 契税核定通知书DAO
 */
public class HdtzsDAO extends BaseDAO {

    /**
     * 插入一条契税核定通知书记录
     * @param hdtzs 契税核定通知书值对象
     * @param conn 数据库连接对象
     * @throws SQLException
     */
    public static void insert(Hdtzs hdtzs, Connection conn) throws SQLException {
        String sql = "insert into QSDB.QS_JL_HDTZS (HDTZSH,SBBH,SQR,SPFXMMC,ZLDI,CJJG,JSYJ,JZQS,KCQYZFX,SJYZ,DYSJ,JBR,LXDH,LRR,LRRQ,CJR,CJRQ,BZBS,NDZB,WSJC,LSH,FWHM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //核定通知书号
            ps.setString(1, hdtzs.hdtzsh);
            //申报表号
            ps.setString(2, hdtzs.sbbh);
            //申请人
            ps.setString(3, hdtzs.sqr);
            //商品房项目名称
            ps.setString(4, hdtzs.spfxmmc);
            //坐落地址
            ps.setString(5, hdtzs.zldi);
            //成交价格
            ps.setBigDecimal(6, hdtzs.cjjg);
            //计税依据
            ps.setBigDecimal(7, hdtzs.jsyj);
            //计征契税
            ps.setBigDecimal(8, hdtzs.jzqs);
            //扣除出售已购公有住房收入项
            ps.setBigDecimal(9, hdtzs.kcqyzfx);
            //实际应征契税
            ps.setBigDecimal(10, hdtzs.sjyz);
            //打印时间
            ps.setTimestamp(11, hdtzs.dysj);
            //经办人
            ps.setString(12, hdtzs.jbr);
            //联系电话
            ps.setString(13, hdtzs.lxdh);
            //录入人
            ps.setString(14, hdtzs.lrr);
            //录入日期
            ps.setTimestamp(15, hdtzs.lrrq);
            //创建人
            ps.setString(16, hdtzs.cjr);
            //创建日期
            ps.setTimestamp(17, hdtzs.cjrq);
            //不征标识
            ps.setString(18, hdtzs.bzbs);
            ps.setString(19, hdtzs.ndzb);
            ps.setString(20, hdtzs.wsjc);
            ps.setBigDecimal(21, hdtzs.lsh);
            ps.setString(22, hdtzs.fwhm);
            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条契税核定通知书记录
     * @param hdtzs 契税核定通知书值对象
     * @param conn 数据库连接对象
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
     * 更新一条契税核定通知书防伪号码
     * @param hdtzs 契税核定通知书值对象
     * @param conn 数据库连接对象
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
     * 根据原核定通知书号码更新核定通知书号码
     * @param hdtzsh 原核定通知书号
     * @param hdtzsh_xg 修改后的核定通知书号
     * @param conn 数据库连接对象
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
            //更新旧核定通知书对象的核定通知书号为修改后的核定通知书号
            ps = conn.prepareStatement(sql);
            ps.setString(1, hdtzsh_xg);
            ps.setString(2, hdtzsh);
            ps.execute();

            //更新明细表数据
            ps = conn.prepareStatement(sqlmx);
            ps.setString(1, hdtzsh_xg);
            ps.setString(2, hdtzsh);
            ps.execute();

            //删除核定通知书主表的旧核定通知书号的数据。
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
     * 删除多条契税核定通知书记录
     * @param hdtzsList 契税核定通知书值对象集合
     * @param conn 数据库连接对象
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
     * 删除契税核定通知书记录
     * @param hdtzsList 契税核定通知书值对象集合
     * @param conn 数据库连接对象
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
     * 根据主键获取契税核定通知书值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 契税核定通知书值对象的集合
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
     * 根据主键获取契税核定通知书值对象
     * @param hdtzs 契税核定通知书值对象
     * @param conn 数据库连接对象
     * @return契税核定通知书值对象
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
     * 根据主键获取契税核定通知书值对象
     * @param hdtzs 契税核定通知书值对象
     * @param conn 数据库连接对象
     * @return契税核定通知书值对象
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
     * 专门为打印核定通知书时依据公有住房or经济适用房插入核定减免明细表的数据影响打印对应文件依据的方法 
     * @param sbbh String
     * @param conn Connection
     * @throws SQLException
     */
    public static ArrayList getFwlx(String sbbh, Connection conn) throws
        SQLException
    {
        //默认房屋类型为公有住房
    	ArrayList list=new ArrayList();
        String sqlj = "select sum(bcdke) jej from qsdb.qs_jl_sbgyzf WHERE sbbh = ? AND yggyzfqszsh LIKE '%契经字%' ";
        String sqlg = "select sum(bcdke) jeg from qsdb.qs_jl_sbgyzf WHERE sbbh = ? AND yggyzfqszsh LIKE '%契公字%' ";
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
