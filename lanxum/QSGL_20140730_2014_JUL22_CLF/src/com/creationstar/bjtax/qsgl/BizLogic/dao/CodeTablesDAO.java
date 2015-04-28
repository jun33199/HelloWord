package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Bz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Bzqkdm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fwlb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Gjdq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmxz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsfs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsdwxz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qstdfwyt;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qszyxs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szqy;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tdjc;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwcqzbzzflx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qszyxsmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Kplx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.ttsoft.common.util.Debug;

/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class CodeTablesDAO extends BaseDAO {
    /**
     * 取得政策维护代码表
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryZCWH(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT zbdm, zbmc, zbz FROM QSDB.qs_jl_zcwh ORDER BY ZBMC";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zcwh vo = new Zcwh();
                vo.setZbdm(rs.getString("zbdm")); // 指标代码
                vo.setZbmc(rs.getString("zbmc")); // 指标名称
                vo.setZbz(rs.getString("zbz")); // 指标值

                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得币种代码表，每个币种放入到币种vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryBZ(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT bzdm, bzmc FROM DMDB.gy_dm_bz ORDER BY BZMC";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bz vo = new Bz();
                vo.setBzdm(rs.getString("bzdm"));
                vo.setBzmc(rs.getString("bzmc"));
                // Debug.out("取得的币种代码表： " + vo.getBzmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得房屋类型代码表，每个房屋类型放入到房屋类型vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryFWLX(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT fwlxdm, fwlxmc FROM DMDB.qs_dm_fwlb ORDER BY fwlxmc";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Fwlb vo = new Fwlb();
                vo.setFwlxdm(rs.getString("fwlxdm"));
                vo.setFwlxmc(rs.getString("fwlxmc"));
                // Debug.out("取得的房屋类型 ：" + vo.getFwlxmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得缴款方式代码表，每个缴款方式放入到缴款方式vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryJSFS(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT jsfsdm, jsfsmc FROM DMDB.qs_dm_jsfs ORDER BY jsfsmc";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Jsfs vo = new Jsfs();

                vo.setJsfsdm(rs.getString("jsfsdm"));
                vo.setJsfsmc(rs.getString("jsfsmc"));
                // Debug.out("取得的缴税方式名称 ：" + vo.getJsfsmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得纳税人类型代码表，每个纳税人类型放入到纳税人类型vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryNSRLX(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT qsdwxzdm, qsdwxzmc FROM DMDB.sf_dm_qsdwxz ORDER BY qsdwxzmc";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qsdwxz vo = new Qsdwxz();

                vo.setNsrlxdm(rs.getString("qsdwxzdm"));
                vo.setNsrlxmc(rs.getString("qsdwxzmc"));

                // Debug.out("取得的纳税人类型名称 ：" + vo.getNsrlxmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得不征情况代码表，每个不征情况放入到不征情况vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static HashMap queryBZQK(Connection conn) throws SQLException {
        HashMap map = new HashMap();
        String sql = "select BZQKDM,BZQKMC,LRR,ZXBS,BZ,BZQKMS,BZZCLY from dmdb.qs_dm_bzqkdm WHERE zxbs='0'";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bzqkdm bzqkdm = new Bzqkdm();
                bzqkdm.setBzqkdm(rs.getString("BZQKDM"));
                bzqkdm.setBzqkmc(rs.getString("BZQKMC"));
                bzqkdm.setLrr(rs.getString("LRR"));
                bzqkdm.setBz(rs.getString("BZ"));
                bzqkdm.setBzqkms(rs.getString("BZQKMS"));
                bzqkdm.setBzzcly(rs.getString("BZZCLY"));
                map.put(bzqkdm.bzqkdm, bzqkdm);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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
        Debug.out("不征情况理由的 map.size() is " + map.size());
        return map;
    }

    /**
     * 取得证件类型代码表，每个证件类型放入到证件类型vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryZJLX(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT zjlxdm, zjlxmc FROM DMDB.gy_dm_zjlx where zxbs='0' and clfs='1' ORDER BY zjlxdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Zjlx vo = new Zjlx();

                vo.setZjlxdm(rs.getString("zjlxdm"));
                vo.setZjlxmc(rs.getString("zjlxmc"));

                // Debug.out("取得的证件类型名称 ：" + vo.getZjlxmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得土地房屋用途代码表，每个土地房屋用途放入到土地房屋用途vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryTDFWYT(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT qstdfwytdm, qstdfwytmc FROM DMDB.sf_dm_qstdfwyt where qstdfwytdm not like '5%' and qstdfwytdm not like '7%'  ORDER BY qstdfwytdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qstdfwyt vo = new Qstdfwyt();

                vo.setQstdfwytdm(rs.getString("qstdfwytdm"));
                vo.setQstdfwytmc(rs.getString("qstdfwytmc"));

                // Debug.out("取得的土地房屋用途名称 ：" + vo.getQstdfwytmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /*zzb  20090820 add begin*/
        /**
         * 取得土地房屋用途代码表，每个土地房屋用途放入到土地房屋用途vo，存放到ArrayList中返回
         * 减免非个人
         *
         * @param conn
         *            Connection
         * @return ArrayList
         * @throws SQLException
         */
        public static ArrayList queryJMTDFWYTFGR(Connection conn) throws SQLException {
            ArrayList list = new ArrayList();
            String sql = "SELECT qstdfwytdm, qstdfwytmc FROM DMDB.sf_dm_qstdfwyt where qstdfwytdm  like '0%' or qstdfwytdm in ('71','75', '99','76')  ORDER BY qstdfwytdm";

            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Qstdfwyt vo = new Qstdfwyt();

                    vo.setQstdfwytdm(rs.getString("qstdfwytdm"));
                    vo.setQstdfwytmc(rs.getString("qstdfwytmc"));

                    // Debug.out("取得的土地房屋用途名称 ：" + vo.getQstdfwytmc());
                    list.add(vo);
                }
            } catch (SQLException e) {
                Debug.printException(e);
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

            return list;
        }
/*zzb  20090820 add end*/


    /**
     * 取得土地房屋用途代码表，每个土地房屋用途放入到土地房屋用途vo，存放到ArrayList中返回 个人
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryBZTDFWYTGR(Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT qstdfwytdm, qstdfwytmc FROM DMDB.sf_dm_qstdfwyt where qstdfwytdm like '5%' or qstdfwytdm like '9%'   ORDER BY qstdfwytdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qstdfwyt vo = new Qstdfwyt();

                vo.setQstdfwytdm(rs.getString("qstdfwytdm"));
                vo.setQstdfwytmc(rs.getString("qstdfwytmc"));

                // Debug.out("取得的土地房屋用途名称 ：" + vo.getQstdfwytmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得土地房屋用途代码表，每个土地房屋用途放入到土地房屋用途vo，存放到ArrayList中返回 非个人
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryBZTDFWYTFGR(Connection conn) throws
            SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT qstdfwytdm, qstdfwytmc FROM DMDB.sf_dm_qstdfwyt where qstdfwytdm like '7%' or qstdfwytdm like '9%'   ORDER BY qstdfwytdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qstdfwyt vo = new Qstdfwyt();

                vo.setQstdfwytdm(rs.getString("qstdfwytdm"));
                vo.setQstdfwytmc(rs.getString("qstdfwytmc"));

                // Debug.out("取得的土地房屋用途名称 ：" + vo.getQstdfwytmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得土地房屋权属转移形式代码表，每个土地房屋权属转移形式放入到土地房屋权属转移形式vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryQSZYXS(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT qszyxsdm, qszyxsmc FROM DMDB.sf_dm_qszyxs  ORDER BY qszyxsdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qszyxs vo = new Qszyxs();

                vo.setQszyxsdm(rs.getString("qszyxsdm"));
                vo.setQszyxsmc(rs.getString("qszyxsmc"));

                // Debug.out("取得的权属转移形式名称 ：" + vo.getQszyxsmc());
                if (vo.getQszyxsdm() != null && !vo.getQszyxsdm().equals("")) {
                    list.add(vo);
                }
//				list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得减免政策代码表，每个减免政策放入到减免政策vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryJMZC(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT qsjmlbdm, qsjmlbmc, zxbs, qsjmlbms, qsjmlbzcyj,jmxzdm FROM DMDB.sf_dm_qsjmlb where zxbs = '0' ORDER BY qsjmlbdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qsjmlb vo = new Qsjmlb();

                vo.setQsjmlbdm(rs.getString("qsjmlbdm"));
                vo.setQsjmlbmc(rs.getString("qsjmlbmc"));
                vo.setZxbs(rs.getString("zxbs"));
                vo.setQsjmlbms(rs.getString("qsjmlbms"));
                vo.setQsjmlbzcyj(rs.getString("qsjmlbzcyj"));
                vo.setJmxzdm(rs.getString("jmxzdm"));
                // Debug.out("取得的减免政策代码表： " + vo.getQsjmlbmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得减免政策代码表，每个减免政策放入到减免政策vo，存放到HashMap中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static HashMap queryJMZCMap(Connection conn) throws SQLException {
        HashMap map = new HashMap();
        String sql = "SELECT qsjmlbdm, qsjmlbmc, zxbs, qsjmlbms, qsjmlbzcyj,jmxzdm FROM DMDB.sf_dm_qsjmlb where zxbs = '0' ORDER BY qsjmlbdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qsjmlb vo = new Qsjmlb();
                vo.setQsjmlbdm(rs.getString("qsjmlbdm"));
                vo.setQsjmlbmc(rs.getString("qsjmlbmc"));
                vo.setZxbs(rs.getString("zxbs"));
                vo.setQsjmlbms(rs.getString("qsjmlbms"));
                vo.setQsjmlbzcyj(rs.getString("qsjmlbzcyj"));
                vo.setJmxzdm(rs.getString("jmxzdm"));
                map.put(vo.getQsjmlbdm(), vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return map;
    }

    /**
     * 取得减免政策代码表，每个减免政策放入到减免政策vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryJMZCGR(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT qsjmlbdm, qsjmlbmc, zxbs, qsjmlbms, qsjmlbzcyj,jmxzdm  FROM DMDB.sf_dm_qsjmlb where fw in ('0','1') and zxbs='0' ORDER BY qsjmlbdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qsjmlb vo = new Qsjmlb();

                vo.setQsjmlbdm(rs.getString("qsjmlbdm"));
                vo.setQsjmlbmc(rs.getString("qsjmlbmc"));
                vo.setZxbs(rs.getString("zxbs"));
                vo.setQsjmlbms(rs.getString("qsjmlbms"));
                vo.setQsjmlbzcyj(rs.getString("qsjmlbzcyj"));
                vo.setJmxzdm(rs.getString("jmxzdm"));
                // Debug.out("取得的减免政策代码表： " + vo.getQsjmlbmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得减免政策代码表，每个减免政策放入到减免政策vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryJMZCFGR(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT qsjmlbdm, qsjmlbmc, zxbs, qsjmlbms, qsjmlbzcyj,jmxzdm  FROM DMDB.sf_dm_qsjmlb where fw in ('0','2') and zxbs='0' ORDER BY qsjmlbdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Qsjmlb vo = new Qsjmlb();

                vo.setQsjmlbdm(rs.getString("qsjmlbdm"));
                vo.setQsjmlbmc(rs.getString("qsjmlbmc"));
                vo.setZxbs(rs.getString("zxbs"));
                vo.setQsjmlbms(rs.getString("qsjmlbms"));
                vo.setQsjmlbzcyj(rs.getString("qsjmlbzcyj"));
                vo.setJmxzdm(rs.getString("jmxzdm"));
                // Debug.out("取得的减免政策代码表： " + vo.getQsjmlbmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得国籍代码表，每个国籍放入到减免政策vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryGJ(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT gjdqdm, gjdqmc FROM DMDB.GY_DM_GJDQ  ORDER BY gjdqmc";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gjdq vo = new Gjdq();

                vo.setGjdqdm(rs.getString("gjdqdm"));
                vo.setGjdqmc(rs.getString("gjdqmc"));

                // Debug.out("取得的国籍代码表： " + vo.getGjdqmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得税务机关组织机构代码表，税务机关组织机构放入到Swjgzzjg，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList querySWJGZZJG(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT swjgzzjgdm, swjgzzjgmc FROM DMDB.GY_DM_SWJGZZJG  ORDER BY swjgzzjgdm";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Swjgzzjg vo = new Swjgzzjg();

                vo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                vo.setSwjgzzjgmc(rs.getString("swjgzzjgmc"));

                // Debug.out("取得的税务机关代码表： " + vo.getSwjgzzjgmc());
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }

    /**
     * 取得土地级次代码表，土地级次代码放入到Tdjc，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryTDJC(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql =
                "SELECT TDJCDM,TDJCMC FROM DMDB.QS_DM_TDJC  ORDER BY TDJCDM ";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tdjc vo = new Tdjc();

                vo.setTdjcdm(rs.getString("TDJCDM"));
                vo.setTdjcmc(rs.getString("TDJCMC"));

                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }

    /**
     * 取得房屋所在区域代码表，房屋所在区域代码放入到Szqy，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList querySZQY(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        //String sql =
         //       "SELECT FWSZQYDM,FWSZQYMC FROM DMDB.QS_DM_FWSZQY  ORDER BY FWSZQYDM ";
        StringBuffer sqlBuf=new StringBuffer();
        sqlBuf.append("SELECT FWSZQYDM,FWSZQYMC FROM ");
        sqlBuf.append(" (");
        sqlBuf.append(" SELECT FWSZQYDM,FWSZQYMC,1 PX FROM DMDB.QS_DM_FWSZQY where FWSZQYDM ='00'");
        sqlBuf.append(" UNION ");
        sqlBuf.append(" SELECT FWSZQYDM,FWSZQYMC,2 PX FROM DMDB.QS_DM_FWSZQY where FWSZQYDM  LIKE '1%'");
        sqlBuf.append(" UNION");
        sqlBuf.append(" SELECT '00' FWSZQYDM ,'================' FWSZQYMC,3 PX FROM DUAL");
        sqlBuf.append(" UNION");
        sqlBuf.append(" SELECT FWSZQYDM,FWSZQYMC,4 PX FROM DMDB.QS_DM_FWSZQY where FWSZQYDM  LIKE '0%' AND FWSZQYDM<>'00'");
        sqlBuf.append(" )");
        sqlBuf.append(" ORDER BY PX");
        
        //System.out.println("##########sqlBuf="+sqlBuf.toString());
        
        /*
        SELECT FWSZQYDM,FWSZQYMC FROM 
        (
        SELECT FWSZQYDM,FWSZQYMC,1 PX FROM DMDB.QS_DM_FWSZQY where FWSZQYDM ='00'
        UNION 
        SELECT FWSZQYDM,FWSZQYMC,2 PX FROM DMDB.QS_DM_FWSZQY where FWSZQYDM  LIKE '1%'
        UNION 
        SELECT '00' FWSZQYDM ,'================' FWSZQYMC,3 PX FROM DUAL
        UNION 
        SELECT FWSZQYDM,FWSZQYMC,4 PX FROM DMDB.QS_DM_FWSZQY where FWSZQYDM  LIKE '0%' AND FWSZQYDM<>'00' 
        )
        ORDER BY PX
        */
        

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sqlBuf.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Szqy vo = new Szqy();

                vo.setFwszqydm(rs.getString("FWSZQYDM"));
                vo.setFwszqymc(rs.getString("FWSZQYMC"));

                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }
    
    
    /**
     * 取得房屋所在区域代码表，房屋所在区域代码放入到fwszqy，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryFWSZQY(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();

        StringBuffer sqlBuf=new StringBuffer();
        //added by zhangj,2014.10.17
        //sqlBuf.append("SELECT FWSZQYMC,FWSZQYDM,nvl(FWSZQYMPMJGSX,0.00) FWSZQYMPMJGSX,nvl(FWSZQYJE,0.00) FWSZQYJE FROM dmdb.qs_dm_fwszqy where zxbs ='0' order by FWSZQYDM asc");
        sqlBuf.append("select FWSZQYMC,FWSZQYDM,nvl(FWSZQYMPMJGSX,0.00) FWSZQYMPMJGSX,nvl(FWSZQYJE,0.00) FWSZQYJE,")
        	.append("(case when fwszqydm='00' then 0  when fwszqydm>'00' and fwszqydm<'10' then 2 ")
        	.append("when fwszqydm>'10' and fwszqydm<'20' then 3 when fwszqydm>'20' and fwszqydm<'30' then 1 end) pxh ")
        	.append("from DMDB.QS_DM_FWSZQY where zxbs ='0' order by pxh,fwszqydm");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sqlBuf.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Szqy vo = new Szqy();

                vo.setFwszqymc(rs.getString("FWSZQYMC"));
                vo.setFwszqydm(rs.getString("FWSZQYDM"));
                vo.setFwszqympmjgsx(rs.getString("FWSZQYMPMJGSX"));
                vo.setFwszqyje(rs.getString("FWSZQYJE"));
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }
    
    public static ArrayList queryJmxzdmList(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();

        StringBuffer sqlBuf=new StringBuffer();
        sqlBuf.append("SELECT JMSLXDM,JMSLXMC,WH,BZ FROM DMDB.SB_DM_ZJQYJMSLX WHERE SZDM='74' AND ZXBZ='0' ORDER BY JMSLXDM");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sqlBuf.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
            	Jmxz vo = new Jmxz();

                vo.setJmxzdm(rs.getString("JMSLXDM"));
                vo.setJmxzmc(rs.getString("JMSLXMC"));
                vo.setJmxzwh(rs.getString("WH"));
                vo.setJmxzbz(rs.getString("BZ"));
                
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }
    
    
    
    /**
     * 取得发票种类代码表，每个缴款方式放入到缴款方式vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryFPZL(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT FPZLDM, FPZLMC,FPHMCD FROM DMDB.FP_DM_FPZL WHERE ZXBS = '0' ORDER BY FPZLDM";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Fpzl vo = new Fpzl();
            	
            	vo.setFpzldm(rs.getString("FPZLDM"));
            	vo.setFpzlmc(rs.getString("FPZLMC"));
            	vo.setFphmcd(rs.getString("FPHMCD"));

                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }
    
    /**
     * 取得开票类型代码表，每个缴款方式放入到缴款方式vo，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryKplx(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "SELECT kplxdm, kplxmc FROM dmdb.fp_dm_kplx WHERE ZXBS = '0' ORDER BY kplxdm";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Kplx vo = new Kplx();
            	
            	vo.setKplxdm(rs.getString("kplxdm"));
            	vo.setKplxmc(rs.getString("kplxmc"));

                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;
    }
    
    /**
     * 取得房屋产权证标注住房类型代码表，房屋产权证标注住房类型代码放入到Fwcqzbzzflx，存放到ArrayList中返回
     *
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryFwcqzbzzflx(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        //added by zhangj,2014.10.17
        //String sql="SELECT fwcqzbzzflxdm,fwcqzbzzflxmc FROM dmdb.qs_dm_fwcqzbzzflx WHERE ZXBS = '0' ORDER BY fwcqzbzzflxdm";
        String sql = "SELECT fwcqzbzzflxdm,fwcqzbzzflxmc,(case when fwcqzbzzflxdm='12' then 1 when fwcqzbzzflxdm<>'12' then 0 end) pxh FROM dmdb.qs_dm_fwcqzbzzflx WHERE ZXBS = '0' ORDER BY pxh,fwcqzbzzflxdm ";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Fwcqzbzzflx vo = new Fwcqzbzzflx();

                vo.setFwcqzbzzflxdm(rs.getString("fwcqzbzzflxdm"));
                vo.setFwcqzbzzflxmc(rs.getString("fwcqzbzzflxmc"));
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }
    
    
    /**
     * 取得房屋性质代码并放入到Fwxz，存放到ArrayList中返回
     *
     * @param conn Connection
     * @return ArrayList
     * @throws SQLException
     * @author 唐昌富
     */
    public static ArrayList queryFwxz(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();
        String sql = "select t.fwxzdm,t.fwxzmc from dmdb.qs_dm_fwxz t where t.zxbs ='0'";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Fwxz vo = new Fwxz();

                vo.setFwxzdm(rs.getString("fwxzdm"));
                vo.setFwxzmc(rs.getString("fwxzmc"));
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }
    
    /**
     * 取得房屋所在区域代码表，房屋所在区域代码放入到fwszqy，存放到ArrayList中返回
     *
     * @param conn
     *            Connection
     * @return ArrayList
     * @throws SQLException
     */
    public static ArrayList queryQSZYXSMX(Connection conn) throws SQLException {
        ArrayList list = new ArrayList();

        StringBuffer sqlBuf=new StringBuffer();
        sqlBuf.append("SELECT QSZYXSDM,QSZYXSMXMC,QSZYXSMXDM FROM dmdb.qs_dm_qszyxsmx where zxbs ='0' order by QSZYXSMXDM asc");
        System.out.println("queryQSZYXSMX start...............");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sqlBuf.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
            	Qszyxsmx vo = new Qszyxsmx();
            	vo.setQszyxsdm(rs.getString("QSZYXSDM"));
                vo.setQszyxsmxmc(rs.getString("QSZYXSMXMC"));
                vo.setQszyxsmxdm(rs.getString("QSZYXSMXDM"));           
                list.add(vo);
            }
        } catch (SQLException e) {
            Debug.printException(e);
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

        return list;

    }

}
