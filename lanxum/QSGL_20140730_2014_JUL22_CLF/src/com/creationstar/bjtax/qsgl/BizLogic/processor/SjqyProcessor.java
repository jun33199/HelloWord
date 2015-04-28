/*
 * Created on 2006-2-21
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.model.bo.SjqyErrBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author guzx
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SjqyProcessor implements Processor {

    /**
     *
     */
    public SjqyProcessor() {
        super();
    }

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is SjqyProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doInsert(vo);

            break;
        case ActionType.UPDATE:
            result = doUpdate(vo);

            break;
        case ActionType.BL_CREATECONNECT_HZJKS:
            result = doQyHz(vo);

            break;

        default:
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    //迁移第一步
    private Object doInsert(VOPackage vo) throws BaseException {
        Map bo = null;

        String xxzl = null;
        bo = (Map) vo.getData();
        xxzl = (String) bo.get("xxzl");
        Debug.out("SjqyProcessor   信息种类====" + xxzl);

        if (xxzl != null) {
            if (xxzl.equals("0")) { //个人
                return doInsert0(vo);
            } else if (xxzl.equals("1")) { //非个人
                return doInsert1(vo);
            } else if (xxzl.equals("2")) { //不征
                return doInsert2(vo);
            }
        }
        return null;
    }

    //迁移第二步
    private Object doUpdate(VOPackage vo) throws BaseException {
        String xxzl = null;
        Map bo = (Map) vo.getData();
        xxzl = (String) bo.get("xxzl");
        Debug.out("SjqyProcessor   信息种类====" + xxzl);
        String qxdm = (String) bo.get("qxdm"); ;
        Connection conn = null;
        List l = new ArrayList();
        String sql = "";
        String sbb = "qsdb.qs_jl_sjqygr";
        PreparedStatement ps = null;
        CallableStatement cs = null;
        String csql = "BEGIN  qsdb.qs_sjqy_wj(?, ?, ?, ?, ?, ?,?,?); END;";
        SjqyErrBo err = new SjqyErrBo();

        if (xxzl != null) {
            if (xxzl.equals("1")) { //非个人
                sbb = "qsdb.qs_jl_sjqyfgr";
            }
            sql = " select sbbh,slr,swjgzzjgdm,wszh,zyjksh,glsbbh,jksjsjdm,(select QZ.szsmdm from DMDB.SF_DM_QSZYXS QZ where qszyxsmc=zylx) szsmdm,ynse,wszzb from "
                  + sbb + " where  qxdm = '" + qxdm
                  + "' and qyzt='1' and glsbbh is not null and YNSE>0  and (wszh is not null or ZYJKSH is not null)"
                  + " and rownum<=" + (String) bo.get("cc") +
                  " order by sbbh asc ";

            try {
                String jsfsdm = (String) bo.get("jsfsdm");
                UserData ud = vo.getUserData();
                //本次操作的户数，按区县按年度，房屋交换的暂不处理

                Debug.out("SjqyProcessor  sql====" + sql);
                conn = QSDBUtil.getConnection();
                ps = conn.prepareStatement(sql);
                cs = conn.prepareCall(csql);
                ResultSet rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i++;
                    if ((i % 100) == 0) {
                        System.out.println("-----已经处理：" + i + "笔记录！-----");
                    }
                    String sbbh = rs.getString("sbbh");
                    String glsbbh = rs.getString("glsbbh");
                    String wszh = rs.getString("wszh");
                    String jksjsjdm = rs.getString("jksjsjdm");
                    BigDecimal sjse = rs.getBigDecimal("YNSE");
                    err = getSjqyErrBo(glsbbh, jksjsjdm, "缴款书计算机代码",
                                       rs.getString("slr"), "", qxdm, "", xxzl,
                                       rs.getString("swjgzzjgdm"), wszh,
                                       sjse.toString());

                    if (wszh != null && !wszh.equals("")) {
                        //生成完税证信息
                        jsfsdm = Constants.WSZ_JSFS_XJ;
                        if (rs.getString("wszzb") == null ||
                            rs.getString("wszzb").equals("")) {
                            //完税证字别为空，记录错误
                            update("update   " + sbb + "  set qyzt='"
                                   + Constants.QY_ZT_FAILURE_2 + "',errno='"
                                   + Constants.QY_ERROR_7 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_7 +
                                   "',qyrq=sysdate where sbbh='" + sbbh
                                   + "' and qxdm='" + qxdm + "' and glsbbh='" +
                                   glsbbh + "'", conn);
                            err.setErrcode(Constants.QY_ERROR_7);
                            err.setErrmsg(Constants.QY_ERRORMSG_7);
                            l.add(err);
                            continue;
                        }

                        setCs(cs, new String[] {glsbbh, qxdm, jsfsdm, xxzl, "0",
                              wszh});
                        //System.out.println("wszh== "+wszh+"  glsbbh== "+glsbbh);
                    } else if (jksjsjdm == null || jksjsjdm.equals("")) {
                        //缴款书计算机代码为空，记录错误
                        /*update("update  "+sbb+"  set qyzt='"
                          + Constants.QY_ZT_FAILURE_2 + "',errno='"
                          + Constants.QY_ERROR_7 + "',errmsg='"
                         + Constants.QY_ERRORMSG_7 + "',qyrq=sysdate where sbbh='" + sbbh
                         + "' and qxdm='" + qxdm + "' and glsbbh='"+glsbbh+"'", conn);
                                err.setErrcode(Constants.QY_ERROR_7);
                                err.setErrmsg(Constants.QY_ERRORMSG_7);
                                l.add(err);	*/
                        continue;
                    } else { //生成专用缴款书信息
                        jsfsdm = Constants.WSZ_JSFS_ZXJN;
                        List jkl = queryJks(rs.getString("szsmdm"),
                                            DataConvert.
                                            round(sjse.doubleValue(), 2) + "",
                                            jksjsjdm, rs.getString("swjgzzjgdm"),
                                            1, conn);
                        if (jkl.size() < 1) {
                            update("update  " + sbb + " set qyzt='"
                                   + Constants.QY_ZT_FAILURE_2 + "',errno='"
                                   + Constants.QY_ERROR_3 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_3 +
                                   "',qyrq=sysdate where sbbh='" + sbbh
                                   + "' and qxdm='" + qxdm + "' and glsbbh='" +
                                   glsbbh + "'", conn);
                            err.setErrcode(Constants.QY_ERROR_3);
                            err.setErrmsg(Constants.QY_ERRORMSG_3);
                            l.add(err);
                            continue;
                        } else if (jkl.size() > 1) {
                            update("update  " + sbb + " set qyzt='"
                                   + Constants.QY_ZT_FAILURE_2 + "',errno='"
                                   + Constants.QY_ERROR_4 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_4 +
                                   "',qyrq=sysdate where sbbh='" + sbbh
                                   + "' and qxdm='" + qxdm + "' and glsbbh='" +
                                   glsbbh + "'", conn);
                            err.setErrcode(Constants.QY_ERROR_4);
                            err.setErrmsg(Constants.QY_ERRORMSG_4);
                            l.add(err);
                            continue;
                        }
                        QueryBlJksBo blbo = (QueryBlJksBo) jkl.get(0);

                        setCs(cs, new String[] {glsbbh, qxdm, blbo.getZbxh(),
                              xxzl, "1", blbo.getJkpzh()});
                        //System.out.println("jkpzh== "+blbo.getJkpzh()+"  glsbbh== "+glsbbh);
                    }

                    try {

                        cs.execute();
                        if (!cs.getString(7).equals("0")) {
                            err.setErrcode(cs.getString(7));
                            err.setErrmsg(cs.getString(8));
                            l.add(err);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        update("update  " + sbb + " set qyzt='"
                               + Constants.QY_ZT_FAILURE_2 + "',errno='"
                               + Constants.QY_ERROR_99 + "',errmsg='"
                               + Constants.QY_ERRORMSG_99 +
                               "',qyrq=sysdate where sbbh='" + sbbh
                               + "' and qxdm='" + qxdm + "' and glsbbh='" +
                               glsbbh + "'", conn);
                        err.setErrcode(Constants.QY_ERROR_99);
                        err.setErrmsg(Constants.QY_ERRORMSG_99);
                        l.add(err);
                        continue;
                    }

                }
                Debug.out("-----------QS SJQY WJ COUNT====" + i);
                rs.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (cs != null) {
                        cs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                QSDBUtil.freeConnection(conn);
            }
        }

        return l;
    }

    //迁移第二步,个人非个人的完税证可能汇在一张缴款书上
    private Object doQyHz(VOPackage vo) throws BaseException {
        String xxzl = null;
        Map bo = (Map) vo.getData();
        //xxzl = (String) bo.get("xxzl");
        String qxdm = (String) bo.get("qxdm"); ;
        Debug.out("SjqyProcessor  qxdm====" + qxdm);
        Connection conn = null;
        List l = new ArrayList();
        StringBuffer sql = new StringBuffer();
        String sbb = "qsdb.qs_jl_sjqygr";
        PreparedStatement ps = null;
        CallableStatement cs = null;
        String csql = "BEGIN  qsdb.qs_sjqy_hz(?, ?, ?, ?, ?, ?,?,?,?); END;";
        SjqyErrBo err = new SjqyErrBo();

        if (qxdm != null) {
            sql.append(
                    "select hzjksh,jksjsjdm,swjgzzjgdm,sum(YNSE) ynse,sum(cc) cc from ")
                    .append("( select hzjksh,jksjsjdm,swjgzzjgdm,sum(YNSE) ynse,count(*) cc from qsdb.qs_jl_sjqygr gr ")
                    .append("where  qxdm = '" + qxdm + "' and qyzt='2' and YNSE>0 and wszh is not null and wszzb is not null and GLSBBH is not null and hzjksh is not null and jksjsjdm is not null ")
                    .append(" group by  hzjksh,jksjsjdm,swjgzzjgdm  union ")
                    .append(" select hzjksh,jksjsjdm,swjgzzjgdm,sum(YNSE) ynse,count(*) cc from qsdb.qs_jl_sjqyfgr fgr")
                    .append(" where  qxdm = '" + qxdm + "' and qyzt='2' and YNSE>0 and wszh is not null and wszzb is not null and GLSBBH is not null and hzjksh is not null and jksjsjdm is not null ")
                    .append(" group by  hzjksh,jksjsjdm,swjgzzjgdm ) hz ")
                    .append(
                    " group by  hzjksh,jksjsjdm,swjgzzjgdm order by  hzjksh ");

            try {
                UserData ud = vo.getUserData();

                Debug.out("SjqyProcessor  sql====" + sql);
                conn = QSDBUtil.getConnection();
                ps = conn.prepareStatement(sql.toString());
                cs = conn.prepareCall(csql);
                ResultSet rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i++;
                    String hzjksh = rs.getString("hzjksh");
                    String jksjsjdm = rs.getString("jksjsjdm");
                    BigDecimal sjse = rs.getBigDecimal("YNSE");
                    int cc = rs.getInt("CC");
                    err = getSjqyErrBo(cc + "", jksjsjdm, "缴款书计算机代码", "", "",
                                       qxdm, "", "", rs.getString("swjgzzjgdm"),
                                       hzjksh, sjse.toString());

                    if (jksjsjdm == null || jksjsjdm.equals("")) {
                        //缴款书计算机代码为空，记录错误
                        update("update  qsdb.qs_jl_sjqygr  set qyzt='"
                               + Constants.QY_ZT_FAILURE_3 + "',errno='"
                               + Constants.QY_ERROR_7 + "',errmsg='"
                               + Constants.QY_ERRORMSG_7 +
                               "',qyrq=sysdate where hzjksh='" + hzjksh
                               + "' and qxdm='" + qxdm +
                                "'  and GLSBBH is not null  and qyzt='2' and jksjsjdm is not null ",
                               conn);
                        update("update  qsdb.qs_jl_sjqyfgr  set qyzt='"
                               + Constants.QY_ZT_FAILURE_3 + "',errno='"
                               + Constants.QY_ERROR_7 + "',errmsg='"
                               + Constants.QY_ERRORMSG_7 +
                               "',qyrq=sysdate where hzjksh='" + hzjksh
                               + "' and qxdm='" + qxdm +
                                "' and GLSBBH is not null  and qyzt='2' and jksjsjdm is not null ",
                               conn);
                        err.setErrcode(Constants.QY_ERROR_7);
                        err.setErrmsg(Constants.QY_ERRORMSG_7);
                        l.add(err);
                        continue;
                    } else { //生成汇总缴款书信息,比较课税数量是否相等
                        List jkl = queryJks("",
                                            DataConvert.round(sjse.doubleValue(),
                                2) + "", jksjsjdm, rs.getString("swjgzzjgdm"),
                                            cc, conn);
                        if (jkl.size() < 1) {
                            update("update qsdb.qs_jl_sjqygr set qyzt='"
                                   + Constants.QY_ZT_FAILURE_3 + "',errno='"
                                   + Constants.QY_ERROR_3 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_3 +
                                   "',qyrq=sysdate where hzjksh='" + hzjksh
                                   + "' and qxdm='" + qxdm +
                                    "'  and GLSBBH is not null and qyzt='2'  and jksjsjdm is not null ",
                                   conn);
                            update("update qsdb.qs_jl_sjqyfgr set qyzt='"
                                   + Constants.QY_ZT_FAILURE_3 + "',errno='"
                                   + Constants.QY_ERROR_3 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_3 +
                                   "',qyrq=sysdate where hzjksh='" + hzjksh
                                   + "' and qxdm='" + qxdm +
                                    "'  and GLSBBH is not null and qyzt='2' and jksjsjdm is not null ",
                                   conn);
                            err.setErrcode(Constants.QY_ERROR_3);
                            err.setErrmsg(Constants.QY_ERRORMSG_3);
                            l.add(err);
                            continue;
                        } else if (jkl.size() > 1) {
                            update("update qsdb.qs_jl_sjqygr set qyzt='"
                                   + Constants.QY_ZT_FAILURE_3 + "',errno='"
                                   + Constants.QY_ERROR_4 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_4 +
                                   "',qyrq=sysdate where  hzjksh='" + hzjksh
                                   + "' and qxdm='" + qxdm +
                                   "'  and GLSBBH is not null  and qyzt='2'",
                                   conn);
                            update("update qsdb.qs_jl_sjqyfgr set qyzt='"
                                   + Constants.QY_ZT_FAILURE_3 + "',errno='"
                                   + Constants.QY_ERROR_4 + "',errmsg='"
                                   + Constants.QY_ERRORMSG_4 +
                                   "',qyrq=sysdate where  hzjksh='" + hzjksh
                                   + "' and qxdm='" + qxdm +
                                    "'  and GLSBBH is not null and qyzt='2' and jksjsjdm is not null ",
                                   conn);
                            err.setErrcode(Constants.QY_ERROR_4);
                            err.setErrmsg(Constants.QY_ERRORMSG_4);
                            l.add(err);
                            continue;
                        }
                        QueryBlJksBo blbo = (QueryBlJksBo) jkl.get(0);
                        String sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);

                        setCs(cs, new String[] {hzjksh, qxdm, jksjsjdm,
                              blbo.getZbxh(), blbo.getJkpzh(), sbhzdh,
                              DataConvert.round(sjse.doubleValue(), 2) + ""});
                    }

                    try {
                        cs.execute();
                        if (!cs.getString(8).equals("0")) {
                            err.setErrcode(cs.getString(8));
                            err.setErrmsg(cs.getString(9));
                            l.add(err);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        update("update qsdb.qs_jl_sjqygr set qyzt='"
                               + Constants.QY_ZT_FAILURE_3 + "',errno='"
                               + Constants.QY_ERROR_99 + "',errmsg='"
                               + Constants.QY_ERRORMSG_99 +
                               "',qyrq=sysdate where hzjksh='" + hzjksh
                               + "' and qxdm='" + qxdm +
                                "'  and GLSBBH is not null and qyzt='2' and jksjsjdm is not null ",
                               conn);
                        update("update qsdb.qs_jl_sjqyfgr set qyzt='"
                               + Constants.QY_ZT_FAILURE_3 + "',errno='"
                               + Constants.QY_ERROR_99 + "',errmsg='"
                               + Constants.QY_ERRORMSG_99 +
                               "',qyrq=sysdate where hzjksh='" + hzjksh
                               + "' and qxdm='" + qxdm +
                                "'  and GLSBBH is not null and qyzt='2' and jksjsjdm is not null ",
                               conn);
                        err.setErrcode(Constants.QY_ERROR_99);
                        err.setErrmsg(Constants.QY_ERRORMSG_99);
                        l.add(err);
                        continue;
                    }

                }
                Debug.out("-----------QS SJQY HZ COUNT====" + i);
                rs.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    //
                    e.printStackTrace();
                }
                try {
                    if (cs != null) {
                        cs.close();
                    }
                } catch (SQLException e) {
                    //
                    e.printStackTrace();
                }
                QSDBUtil.freeConnection(conn);
            }
        }

        return l;
    }

    private Object doInsert0(VOPackage vo) throws BaseException {
        Map bo = null;
        String qxdm = null;
        Connection conn = null;
        List l = new ArrayList();
        String sql = "";
        PreparedStatement ps = null;
        CallableStatement cs = null;
        String csql =
                "BEGIN  qsdb.qs_sjqy_gr(?, ?,?, ?, ?, ?, ?,?,?,?,?,?); END;";
        SjqyErrBo err = new SjqyErrBo();

        try {
            bo = (Map) vo.getData();
            qxdm = (String) bo.get("qxdm");
            String jsfsdm = (String) bo.get("jsfsdm");
            UserData ud = vo.getUserData();
            //本次操作的户数，按区县按年度，房屋交换的暂不处理

            sql = " select sbbh,zjhm,nsrmc,sfzjlx,zj,slr,swjgzzjgdm,dz,lxdh,wszh,zyjksh,wsjc from qsdb.qs_jl_sjqygr"
                  + " where  qxdm = '" + qxdm
                  + "' and qyzt='0'"
                  + " and rownum<=" + (String) bo.get("cc") +
                  " order by sbbh asc ";

            Debug.out("SjqyProcessor  sql====" + sql);
            conn = QSDBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            cs = conn.prepareCall(csql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if ((i % 100) == 0) {
                    System.out.println("-----已经处理：" + i + "笔记录！-----");
                }

                String sbbh = rs.getString("sbbh");
                String wsjc = rs.getString("wsjc");
                String zjhm = DataConvert.qjToBj(rs.getString("zjhm"));
                String sfzjlx = rs.getString("sfzjlx");
                String swjgzzjgdm = rs.getString("swjgzzjgdm");
                String jsjdm = "";
                String wszh = rs.getString("wszh");
                String jksh = rs.getString("zyjksh");
                err = getSjqyErrBo(sbbh, zjhm, rs.getString("zj"),
                                   rs.getString("slr"), rs.getString("nsrmc"),
                                   qxdm, rs.getString("dz"), "0", "", "", "");

                if (zjhm != null && zjhm.length() > 30) {
                    update("update  qsdb.qs_jl_sjqygr set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_6 + "',errmsg='"
                           + Constants.QY_ERRORMSG_6 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_6);
                    err.setErrmsg(Constants.QY_ERRORMSG_6);
                    l.add(err);
                    continue;
                }

                if (sfzjlx == null || sfzjlx.equals(Constants.SFZJLX_HZ)) { //护照
                    update("update  qsdb.qs_jl_sjqygr set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_8 + "',errmsg='"
                           + Constants.QY_ERRORMSG_8 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_8);
                    err.setErrmsg(Constants.QY_ERRORMSG_8);
                    l.add(err);
                    continue;
                }

                try {
                    jsjdm = CommonUtil.checkZrr(sfzjlx, zjhm, err.getNsrmc(),
                                                Constants.GJ_CHN, err.getSlr(),
                                                swjgzzjgdm);
                } catch (Exception ex) {
                    update("update  qsdb.qs_jl_sjqygr set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_1 + "',errmsg='"
                           + Constants.QY_ERRORMSG_1 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_1);
                    err.setErrmsg(Constants.QY_ERRORMSG_1);
                    l.add(err);
                    continue;
                }

                if (wszh != null && !wszh.equals("")) { //缴税方式:现金					
                    jsfsdm = Constants.WSZ_JSFS_XJ;
                }
                if (jksh != null && !jksh.equals("")) { //缴税方式：自行
                    jsfsdm = Constants.WSZ_JSFS_ZXJN;
                }

                try {
                    setCs(cs, new String[] {sbbh, wsjc, qxdm, jsfsdm,
                          (String) bo.get("ztbs"), (String) bo.get("blbs"),
                          jsjdm, zjhm, Constants.GJ_CHN,
                          DataConvert.qjToBj(rs.getString("lxdh"))});

                    cs.execute();
                    if (!cs.getString(11).equals("0")) {
                        err.setErrcode(cs.getString(11));
                        err.setErrmsg(cs.getString(12));
                        l.add(err);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    update("update  qsdb.qs_jl_sjqygr set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_99 + "',errmsg='"
                           + Constants.QY_ERRORMSG_99 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_99);
                    err.setErrmsg(Constants.QY_ERRORMSG_99);
                    l.add(err);
                    continue;
                }

            }
            Debug.out("-----------QS SJQY COUNT====" + i);
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
            QSDBUtil.freeConnection(conn);
        }
        return l;
    }

    public static Map getFgrDjInfo(String jsjdm, Connection conn) throws
            Exception {
        String sql =
                "select dj.jsjdm,dj.swjgzzjgdm,dj.nsrmc,dj.nsrzt,yh.yhdm,yh.zh,yh.khyhmc "
                + " from djdb.dj_jl_jbsj dj,djdb.dj_jl_yhzh yh where dj.jsjdm=yh.jsjdm(+) and dj.jsjdm=?";
        PreparedStatement ps = null;
        Map djinfo = new HashMap();
        List yhs = new ArrayList();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, jsjdm);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                djinfo.put("jsjdm", jsjdm);
                djinfo.put("swjgzzjgdm", rs.getString("swjgzzjgdm"));
                String zh = rs.getString("zh");
                if (zh != null && !zh.equals("")) {
                    YHZH yzh = new YHZH();
                    yzh.setKhyhmc(rs.getString("khyhmc"));
                    yzh.setZh(rs.getString("zh"));
                    yzh.setYhdm(rs.getString("yhdm"));
                    yhs.add(yzh);
                }
            }
            djinfo.put("YHZH", yhs);
            if (djinfo.get("jsjdm") == null) {
                throw new ApplicationException("登记信息不存在");
            }
        } catch (Exception ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return djinfo;
    }

    //非个人
    private Object doInsert1(VOPackage vo) throws BaseException {
        Map bo = null;
        String qxdm = null;
        Connection conn = null;
        List l = new ArrayList();
        String sql = "";
        PreparedStatement ps = null;
        CallableStatement cs = null;
        String csql =
                "BEGIN  qsdb.qs_sjqy_fgr(?, ?, ?, ?, ?, ?, ?,?,?,?,?); END;";
        SjqyErrBo err = new SjqyErrBo();

        try {
            bo = (Map) vo.getData();
            qxdm = (String) bo.get("qxdm");
            String jsfsdm = (String) bo.get("jsfsdm");
            UserData ud = vo.getUserData();
            //本次操作的户数，按区县按年度，房屋交换的暂不处理

            sql = " select sbbh,swjsjdm,nsrmc,nsrlx,slr,swjgzzjgdm,dz,lxdh,wszh,zyjksh,yhzh,wsjc from qsdb.qs_jl_sjqyfgr"
                  + " where  qxdm = '" + qxdm
                  + "' and qyzt='0' "
                  + " and rownum<=" + (String) bo.get("cc") +
                  " order by sbbh asc ";

            Debug.out("SjqyProcessor  sql====" + sql);
            conn = QSDBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            cs = conn.prepareCall(csql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if ((i % 100) == 0) {
                    System.out.println("-----已经处理：" + i + "笔记录！-----");
                }
                String sbbh = rs.getString("sbbh");
                String wsjc = rs.getString("wsjc");
                String jsjdm = DataConvert.qjToBj(rs.getString("swjsjdm"));
                String wszh = rs.getString("wszh");
                String jksh = rs.getString("zyjksh");
                String yhzh = rs.getString("yhzh");

                err = getSjqyErrBo(sbbh, jsjdm, rs.getString("nsrlx"),
                                   rs.getString("slr"), rs.getString("nsrmc"),
                                   qxdm, rs.getString("dz"), "1", "", "", "");
                //Debug.out("sbbh ==== " + sbbh+"  jsjdm ==== " + jsjdm);

                String khyhdm = null;
                try {
                    if (yhzh != null && !yhzh.equals("")) {
                        Map djinfo = getFgrDjInfo(jsjdm, conn);
                        //SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
                        List yhList = (List) djinfo.get("YHZH");
                        if ((yhList != null) && (yhList.size() > 0)) {
                            for (int j = 0; j < yhList.size(); j++) {
                                YHZH yzh = (YHZH) yhList.get(j);
                                if (yhzh.equals(yzh.getZh())) {
                                    khyhdm = yzh.getYhdm();
                                    break;
                                }
                            }
                        }
                        if (khyhdm == null) {
                            /*update("update  qsdb.qs_jl_sjqyfgr set qyzt='"
                              + Constants.QY_ZT_FAILURE + "',errno='"
                              + Constants.QY_ERROR_9 + "',errmsg='"
                             + Constants.QY_ERRORMSG_9 + "',qyrq=sysdate where sbbh='" + sbbh
                             + "' and qxdm='" + qxdm + "' and wsjc='"+wsjc+"'", conn);
                                       err.setErrcode(Constants.QY_ERROR_9);
                                       err.setErrmsg(Constants.QY_ERRORMSG_9);
                                       l.add(err);
                                       continue; */
                            khyhdm = "";
                        }
                    }

                } catch (ApplicationException ex) {
                    /*Debug.out("fgrsb err sql >>update  qsdb.qs_jl_sjqyfgr set qyzt='"
                      + Constants.QY_ZT_FAILURE + "',errno='"
                      + Constants.QY_ERROR_1 + "',errmsg='"
                     + Constants.QY_ERRORMSG_1 + "',qyrq=sysdate where sbbh='" + sbbh
                      + "' and qxdm='" + qxdm + "' and wsjc='"+wsjc+"'");*/
                    update("update  qsdb.qs_jl_sjqyfgr set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_1 + "',errmsg='"
                           + Constants.QY_ERRORMSG_1 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_1);
                    err.setErrmsg(Constants.QY_ERRORMSG_1);
                    l.add(err);
                    continue;
                }

                if (wszh != null && !wszh.equals("")) { //缴税方式:现金					
                    jsfsdm = Constants.WSZ_JSFS_XJ;
                }
                if (jksh != null && !jksh.equals("")) { //缴税方式：自行
                    jsfsdm = Constants.WSZ_JSFS_ZXJN;
                }

                try {
                    setCs(cs, new String[] {sbbh, wsjc, qxdm, jsfsdm,
                          (String) bo.get("ztbs"), (String) bo.get("blbs"),
                          jsjdm,
                          DataConvert.qjToBj(rs.getString("lxdh")), khyhdm});
                    //Debug.out("setcs");
                    cs.execute();
                    if (!cs.getString(10).equals("0")) {
                        err.setErrcode(cs.getString(10));
                        err.setErrmsg(cs.getString(11));
                        l.add(err);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    update("update  qsdb.qs_jl_sjqyfgr set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_99 + "',errmsg='"
                           + Constants.QY_ERRORMSG_99 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_99);
                    err.setErrmsg(Constants.QY_ERRORMSG_99);
                    l.add(err);
                    continue;
                }

            }
            Debug.out("-----------QS SJQY COUNT====" + i);
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
            QSDBUtil.freeConnection(conn);
        }
        return l;
    }

    //不征
    private Object doInsert2(VOPackage vo) throws BaseException {
        Map bo = null;
        String qxdm = null;
        Connection conn = null;
        List l = new ArrayList();
        String sql = "";
        PreparedStatement ps = null;
        CallableStatement cs = null;
        String csql =
                "BEGIN  qsdb.qs_sjqy_bz(?, ?,?, ?, ?, ?, ?,?,?,?,?); END;";
        SjqyErrBo err = new SjqyErrBo();

        try {
            bo = (Map) vo.getData();
            qxdm = (String) bo.get("qxdm");
            String jsfsdm = (String) bo.get("jsfsdm");
            UserData ud = vo.getUserData();
            //本次操作的户数，按区县按年度，房屋交换的暂不处理

            sql = " select sbbh,zjhm,sqr,sfzjlx,zj,slr,swjgzzjgdm,dz,lxdh,wsjc from qsdb.qs_jl_sjqybz"
                  + " where  qxdm = '" + qxdm
                  + "' and qyzt='0' "
                  + " and rownum<=" + (String) bo.get("cc") +
                  " order by sbbh asc ";

            Debug.out("SjqyProcessor  sql====" + sql);
            conn = QSDBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            cs = conn.prepareCall(csql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if ((i % 100) == 0) {
                    System.out.println("-----已经处理：" + i + "笔记录！-----");
                }
                String sbbh = rs.getString("sbbh");
                String wsjc = rs.getString("wsjc");
                String zjhm = DataConvert.qjToBj(rs.getString("zjhm"));
                String sfzjlx = rs.getString("sfzjlx");
                String swjgzzjgdm = rs.getString("swjgzzjgdm");
                String jsjdm = "";
                err = getSjqyErrBo(sbbh, zjhm, rs.getString("zj"),
                                   rs.getString("slr"), rs.getString("sqr"),
                                   qxdm, rs.getString("dz"), "2", "", "", "");

                if (zjhm != null && zjhm.length() > 30) {
                    update("update  qsdb.qs_jl_sjqybz set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_6 + "',errmsg='"
                           + Constants.QY_ERRORMSG_6 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_6);
                    err.setErrmsg(Constants.QY_ERRORMSG_6);
                    l.add(err);
                    continue;
                }
                if (sfzjlx == null || sfzjlx.equals(Constants.SFZJLX_HZ)) { //护照
                    update("update  qsdb.qs_jl_sjqybz set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_8 + "',errmsg='"
                           + Constants.QY_ERRORMSG_8 + "' where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_8);
                    err.setErrmsg(Constants.QY_ERRORMSG_8);
                    l.add(err);
                    continue;
                }

                try {
                    jsjdm = CommonUtil.checkZrr(sfzjlx, zjhm, err.getNsrmc(),
                                                Constants.GJ_CHN, err.getSlr(),
                                                swjgzzjgdm);
                } catch (Exception ex) {
                    update("update  qsdb.qs_jl_sjqybz set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_1 + "',errmsg='"
                           + Constants.QY_ERRORMSG_1 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_1);
                    err.setErrmsg(Constants.QY_ERRORMSG_1);
                    l.add(err);
                    continue;
                }

                try {
                    setCs(cs, new String[] {sbbh, wsjc, qxdm,
                          (String) bo.get("ztbs"), (String) bo.get("blbs"),
                          jsjdm, zjhm, Constants.GJ_CHN,
                          DataConvert.qjToBj(rs.getString("lxdh"))});
                    cs.execute();
                    if (!cs.getString(10).equals("0")) {
                        err.setErrcode(cs.getString(10));
                        err.setErrmsg(cs.getString(11));
                        l.add(err);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    update("update  qsdb.qs_jl_sjqybz set qyzt='"
                           + Constants.QY_ZT_FAILURE + "',errno='"
                           + Constants.QY_ERROR_99 + "',errmsg='"
                           + Constants.QY_ERRORMSG_99 +
                           "',qyrq=sysdate where sbbh='" + sbbh
                           + "' and qxdm='" + qxdm + "' and wsjc='" + wsjc +
                           "'", conn);
                    err.setErrcode(Constants.QY_ERROR_99);
                    err.setErrmsg(Constants.QY_ERRORMSG_99);
                    l.add(err);
                    continue;
                }
            }
            Debug.out("-----------QS SJQY COUNT====" + i);
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
            QSDBUtil.freeConnection(conn);
        }
        return l;
    }

    /**
     * 专门为即时办理写的审核同意或不同意的update
     * @param zt String
     * @param conditions String
     * @param conn Connection
     * @throws SQLException
     */
    public static void update(String sql, Connection conn) throws SQLException {
        //Debug.out("update sql: " + sql);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
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

    private SjqyErrBo getSjqyErrBo(String sbbh, String zjhm, String zjmc,
                                   String slr, String nsrmc, String qxdm,
                                   String dz, String xxzl, String jgdm
                                   , String wszh, String ynse) {
        SjqyErrBo err = new SjqyErrBo();
        err.setNsrmc(nsrmc);
        err.setQxdm(qxdm);
        err.setSbbh(sbbh);
        err.setSfzjhm(zjhm);
        err.setSfzjlx(zjmc);
        err.setSlr(slr);
        err.setFdcxmdz(dz);
        err.setSwjgdm(jgdm);
        err.setXxzl(xxzl);
        err.setWszh(wszh);
        err.setYnse(ynse);

        return err;
    }

    private void setCs(CallableStatement cs, String[] paras) throws
            SQLException {
        cs.clearParameters();
        for (int i = 0; i < paras.length; i++) {
            cs.setString(i + 1, paras[i]);
        }
        cs.registerOutParameter(paras.length + 1, Types.VARCHAR);
        cs.registerOutParameter(paras.length + 2, Types.VARCHAR);

    }

    //查询专用缴款书
    private List queryJks(String szsmdm, String sjse, String jsjdm,
                          String swjgdm, int kssl, Connection conn) throws
            SQLException {
        StringBuffer sql = new StringBuffer("");
        PreparedStatement ps = null;
        ResultSet rs = null;
        List rl = new ArrayList();

        try {
            sql.append("SELECT a.jkpzh,a.zwbs,a.sklxdm,sum(b.rkje) rkje, sum(b.sjse) sjse,nvl(sum(b.kssl),0) kssl ")
                    .append("FROM sbdb.sb_jl_sbjkzb a, sbdb.sb_jl_sbjkmx b ")
                    .append("WHERE  a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                    .append(
                    " AND (a.sklxdm = '100' or (a.sklxdm = '110' AND a.sjly != '51') ) ")
                    .append(
                    " AND substr(a.zwbs,1,1) != '0' AND substr(a.zwbs,10,1) = '0' ")
                    .append(" AND substr(a.zwbs,11,1) = '0'   ")
                    .append("  AND a.szdm ='74' ");
            if (szsmdm != null && !szsmdm.equals("")) {
                sql.append("  AND b.sjse=" + sjse + " AND b.SZSMDM='" + szsmdm +
                           "' AND a.jsjdm='" + jsjdm + "' AND a.SWJGZZJGDM='" +
                           swjgdm + "' ")
                        .append(
                        "  group by a.jkpzh,a.zwbs,a.sklxdm ORDER BY a.jkpzh ");
            } else {
                sql.append("  AND a.sjje=" + sjse + " AND a.jsjdm='" + jsjdm +
                           "' AND a.SWJGZZJGDM='" + swjgdm + "' ")
                        .append(
                        "  group by a.jkpzh,a.zwbs,a.sklxdm having sum(b.kssl)=" +
                        kssl + " ORDER BY a.jkpzh ");
            }

            Debug.out("QSQY Query Jks:" + sql);
            ps = conn.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();
                /*   Sbjkzb sbjkzb = new Sbjkzb();
                                 sbjkzb.setJkpzh(rs.getString("JKPZH"));
                                 sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                                 sbjkzb.setJsjdm(jsjdm);
                                 sbjkzb.setSwjgzzjgdm(swjgdm);
                                 sbjkzb.setZwbs(rs.getString("ZWBS"));
                                 sbjkzb.setSjje(rs.getBigDecimal("sjse"));
                                 sbjkzb.setRkje(rs.getBigDecimal("rkje"));
                                 sbjkzb.setKssl(rs.getInt("kssl"));
                                 sbjkzb.setSzdm(szsmdm);
                              //   sbjkzb.setCjrq(rs.getTimestamp("cjrq"));

                                 bo.setSbjkzb(sbjkzb);*/
                bo.setJkpzh(rs.getString("JKPZH"));
                bo.setSklxdm(rs.getString("SKLXDM"));
                bo.setType(Constants.PT_JKS);

                rl.add(bo);
            }

            rs.close();

            String condition = " ";
            if (szsmdm != null && !szsmdm.equals("")) {
                condition = "  AND b.sjse=" + sjse + "  AND b.SZSMDM='" +
                            szsmdm + "' AND a.jsjdm='" + jsjdm +
                            "' AND a.SWJGZZJGDM='" + swjgdm + "' ";
            } else {
                condition = " AND a.jsjdm='" + jsjdm + "' AND a.SWJGZZJGDM='" +
                            swjgdm + "' ";
            }
            sql = new StringBuffer("");
            sql.append("SELECT t1.zbxh,t1.jkpzh,t1.sklxdm , t.total_rkje rkje,")
                    .append(
                    " t1.cjrq,t.total_kssl kssl, t.total_sjse sjse, t.zwbs ")
                    .append(" FROM jkdb.kj_jl_zwtzjkszb t1, ")
                    .append(" (SELECT a.jkpzh, a.jsjdm, b.zwbs, sum(b.rkje) total_rkje, sum(b.sjse) total_sjse, nvl(sum(b.kssl),0) total_kssl ")
                    .append(
                    " FROM jkdb.kj_jl_zwtzjkszb a, jkdb.kj_jl_zwtzjksmx b ")
                    .append(
                    " WHERE a.zbxh = b.zbxh AND a.jkpzh = b.jkpzh AND a.jsjdm = b.jsjdm ")
                    .append("  AND a.szdm ='74' ")
                    .append(condition)
                    .append(" AND (a.sklxdm = '100' or a.sklxdm = '110' ) ")
                    .append(
                    " AND substr(b.zwbs,1,1) != '0' AND substr(b.zwbs,10,1) = '0'  ")
                    .append(" AND substr(b.zwbs,11,1) = '0' ")
                    .append(" AND b.sjse>0 ")
                    .append(" GROUP BY a.jkpzh, a.jsjdm,b.zwbs ) t ")
                    .append(
                    " WHERE  t1.jkpzh = t.jkpzh AND t1.jsjdm = t.jsjdm ")
                    .append(" AND t.total_sjse =" + sjse +
                            " AND t.total_kssl =" + kssl)
                    .append("  ORDER BY t1.cjrq");

            Debug.out("QSQY Query DZ Jks:" + sql);
            ps = conn.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                QueryBlJksBo bo = new QueryBlJksBo();
                /*           Sbjkzb sbjkzb = new Sbjkzb();
                                sbjkzb.setJkpzh(rs.getString("JKPZH"));
                                 sbjkzb.setSklxdm(rs.getString("SKLXDM"));
                                 sbjkzb.setJsjdm(jsjdm);
                                 sbjkzb.setSwjgzzjgdm(swjgdm);
                                 sbjkzb.setSzdm(szsmdm);
                                 sbjkzb.setZwbs(rs.getString("ZWBS"));
                              //   sbjkzb.setCjrq(rs.getTimestamp("CJRQ"));
                                 sbjkzb.setRkje(rs.getBigDecimal("rkje"));
                                 sbjkzb.setSjje(rs.getBigDecimal("sjse"));

                                 bo.setSbjkzb(sbjkzb);*/
                bo.setJkpzh(rs.getString("JKPZH"));
                bo.setSklxdm(rs.getString("SKLXDM"));
                bo.setType(Constants.DZ_JKS); //代表调帐的缴款书
                bo.setZbxh(rs.getString("zbxh"));

                rl.add(bo);

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ps.close();
        }

        return rl;
    }

}