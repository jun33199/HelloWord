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
 * 土地、房屋信息表DAO
 */
public class TufwxxDAO extends BaseDAO {

    /**
     * 插入一条土地、房屋信息表记录
     * @param tufwxx 土地、房屋信息表值对象
     * @param conn 数据库连接对象
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
            //土地、房屋系统唯一标识
            ps.setString(1, tufwxx.tdfwid);
            //房地产项目名称
            ps.setString(2, tufwxx.fdcxmmc);
            //合同（契约）签订时间
            ps.setTimestamp(3, tufwxx.htqdsj);
            //分类
            ps.setString(4, tufwxx.fldm);
            //土地、房屋座落地址
            ps.setString(5, tufwxx.tdfwzldz);
            //土地、房屋权属转移类型
            ps.setString(6, tufwxx.tdfwqszylx);
            //房屋类别
            ps.setString(7, tufwxx.fwlxdm);
            //土地、房屋权属转移面积
            ps.setBigDecimal(8, tufwxx.tdfwqszymj);
            //房屋建筑面积
            ps.setBigDecimal(9, tufwxx.fwjzmj);
            //成交价格（人民币）
            ps.setBigDecimal(10, tufwxx.cjjgrmb);
            //成交价格（外币）
            ps.setBigDecimal(11, tufwxx.cjjgwb);
            //币种代码
            ps.setString(12, tufwxx.bzdm);
            //汇率代码
            ps.setBigDecimal(13, tufwxx.hldm);
            //折合价格（人民币）
            ps.setBigDecimal(14, tufwxx.zhjgrmb);
            //评估价格（人民币）
            ps.setBigDecimal(15, tufwxx.pgjgrmb);
            //录入人
            ps.setString(16, tufwxx.lrr);
            //录入日期
            ps.setTimestamp(17, tufwxx.lrrq);
            //创建人
            ps.setString(18, tufwxx.cjr);
            //创建日期
            ps.setTimestamp(19, tufwxx.cjrq);
            //备注
            ps.setString(20, tufwxx.bz);
            //年度
            ps.setString(21, tufwxx.nd);
            //土地房屋权属转移名称
            ps.setString(22, tufwxx.tdfwqszymc);
            //分类名称
            ps.setString(23, tufwxx.flmc);
            //房屋类型名称
            ps.setString(24, tufwxx.fwlxmc);
            //币种名称
            ps.setString(25, tufwxx.bzmc);
            //容积率
            ps.setString(26, tufwxx.rjl);
            //土地级次
            ps.setString(27, tufwxx.tdjc);
            //房屋区域
            ps.setString(28, tufwxx.tdjc);
            // 是否二手房
            String sfesf=tufwxx.sfesf;
            if("on".equals(sfesf)){
            	sfesf="01";
            }
            ps.setString(29, sfesf);

            //执行
            ps.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    /**
     * 更新一条土地、房屋信息表记录
     * @param tufwxx 土地、房屋信息表值对象
     * @param conn 数据库连接对象
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
            //容积率
            ps.setString(25, tufwxx.rjl);
            //土地级次
            ps.setString(26, tufwxx.tdjc);
            //房屋区域
            ps.setString(27, tufwxx.tdjc);
            // 是否二手房
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
     * 删除多条土地、房屋信息表记录
     * @param tufwxxList 土地、房屋信息表值对象集合
     * @param conn 数据库连接对象
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
     * 根据主键获取土地、房屋信息表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 土地、房屋信息表值对象的集合
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
     * 根据申报表号获取土地、房屋信息表值对象
     * @param condition 含有where 的条件字句
     * @param conn 数据库连接对象
     * @return ArrayList 土地、房屋信息表值对象的集合
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
     * 根据主键获取土地、房屋信息表值对象
     * @param tufwxx 土地、房屋信息表值对象
     * @param conn 数据库连接对象
     * @return土地、房屋信息表值对象
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
     * 根据申报表号获取土地、房屋信息表值对象
     *
     * @param tufwxx 土地、房屋信息表值对象
     * @param conn 数据库连接对象
     * @return土地、房屋信息表值对象
     * @throws SQLException
     */
    /** @todo  如果以后出现一张申报表对应多条房屋、土地记录的现象，需要修改此方法
     * 或者再命名一个方法 */
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
