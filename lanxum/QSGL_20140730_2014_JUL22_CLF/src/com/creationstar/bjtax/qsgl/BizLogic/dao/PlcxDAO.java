package com.creationstar.bjtax.qsgl.BizLogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.model.bo.PlcxBo;
import com.creationstar.bjtax.qsgl.model.bo.PlcxBo2;
import com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo;
import com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo2;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PlcxDAO extends BaseDAO {
    public static ArrayList get(PlcxBo plcxBo, String conditions,
                                Connection conn) throws
            Exception {
        ArrayList result = new ArrayList();
        // 查询用SQL语句
        StringBuffer sql = new StringBuffer(
                "select * from QSDB.QS_JL_DRPCINFO a");
        String where = " where a.sjly='02' and 1=1" + conditions;
        // 判断查询条件
        // 数据提供者名称
        System.out.println("///////////////////////");
        System.out.println("数据提供者名称:" + plcxBo.getSjtgz());
        System.out.println("批量号:" + plcxBo.getPch());
        System.out.println("导入时间段:" + plcxBo.getDrsjBegin());
        System.out.println("导入时间段:" + plcxBo.getDrsjEnd());
        System.out.println("///////////////////////");
        String sjtgz = plcxBo.getSjtgz();
        if (sjtgz != null && !sjtgz.trim().equals("")) {
            where += (" and a.tgzmc='" + sjtgz + "'");
        }
        // 批量号
        String pch = plcxBo.getPch();
        if (pch != null && !pch.trim().equals("")) {
            where += (" and a.drpch='" + pch + "'");
        }
        // 导入时间段
        String drsjBegin = plcxBo.getDrsjBegin();
        String drsjEnd = plcxBo.getDrsjEnd();
        if (drsjBegin != null && !drsjBegin.trim().equals("")) {
            where += (" and a.drsj>=TO_DATE('" + drsjBegin +
                      "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (drsjEnd != null && !drsjEnd.trim().equals("")) {
            where +=
                    (" and a.drsj<=TO_DATE('" + drsjEnd +
                     " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }
        // 提交时间段
        String tjsjBegin = plcxBo.getTjsjBegin(), tjsjEnd = plcxBo.getTjsjEnd();
        if (tjsjBegin != null && !tjsjBegin.trim().equals("")) {
            where +=
                    (" and a.tjsj>=TO_DATE('" + tjsjBegin +
                     "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (tjsjEnd != null && !tjsjEnd.trim().equals("")) {
            where +=
                    (" and a.tjsj<=TO_DATE('" + tjsjEnd +
                     " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            Debug.out("主查询语句" + sql.append(where+" and rownum<202").toString());
            ps = conn.prepareStatement(sql.append(where +
                    " and rownum<202 order by drsj").
                                       toString());
            System.out.println(sql.append(where +
                                          " and rownum<202 order by drsj").
                               toString());
            rs = ps.executeQuery();
            // 统计查询语句
            StringBuffer drSql = new StringBuffer(
                    "select count(*) as drbs from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_DRZB b ").
                                 append(where).append(
                    " and a.sjly='02' and b.sjly='02' and a.DRPCH=b.drpch");
            StringBuffer tjSql = new StringBuffer(
                    "select count(*) as tjbs from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_SBZB c ").
                                 append(where).append(
                    " and a.sjly='02' and c.sjly='05' and a.DRPCH=c.drpch");
            StringBuffer tjynseSql = new StringBuffer(
                    "select sum(c.ynse) as tjynse from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_SBZB c ").
                                     append(where).append(
                                             " and a.sjly='02' and c.sjly='05' and c.DRPCH=a.drpch"); ;

            StringBuffer shsjSql = new StringBuffer("select d.dysj as shsj from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_DRZB b,QSDB.QS_JL_SBZB c,QSDB.QS_JL_HDTZS d ").
                                   append(where).append(
                                           " and a.sjly='02' and b.sjly='02' and c.sjly='05' and a.drpch=b.drpch and c.drpch=b.drpch and c.sbbh=d.sbbh"); ;
            // 计数器
            int i = 0;
            while (rs != null && rs.next()) {
                // 查询结果大于200条则提示输入更精确查询条件
                if (i++ > 200) {
                    throw new ApplicationException(
                            "查询结果集过大只显示前200条，请输入更精确的查询条件！");
                }
                PreparedStatement tmpPs = null;
                ResultSet tmpRs = null;
                PlcxBo tmpBo = new PlcxBo();
                //批量号
                String recordPch = rs.getString("drpch");
                tmpBo.setPch(recordPch);
                //导入时间
                tmpBo.setDrsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "drsj")));
                //提交时间
                tmpBo.setTjsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "tjsj")));
                //导入笔数
//                Debug.out(drSql.toString() + " and a.drpch='" + recordPch + "'");
                tmpPs = conn.prepareStatement(drSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setDrbs(tmpRs.getString("drbs"));
                }
                tmpPs.close();
                //提交笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setTjbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //受理通过笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and c.ztbs='" +
                                              Constants.ZB_ZTBS_DY + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setSltgbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //复核通过的笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and (c.ztbs='" +
                                              Constants.ZB_ZTBS_YFH +
                                              "' or c.ztbs='" +
                                              Constants.ZB_ZTBS_YRK + "')");
//                Debug.out("复核通过的笔数"+tjSql.toString() +
//                                              " and a.drpch='" +
//                                              recordPch + "'"
//                                              +
//                                              " and (c.ztbs='" +
//                                              Constants.ZB_ZTBS_YFH + "' or c.ztbs='" +
//                                              Constants.ZB_ZTBS_YRK + "')");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setFstgbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //审核通过笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and c.ztbs='" +
                                              Constants.ZB_ZTBS_DY_HD + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setShtgbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //审核时间
                tmpPs = conn.prepareStatement(shsjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setShsj(DataConvert.TimeStamp2String(tmpRs.
                            getTimestamp("shsj")));
                }
                tmpPs.close();
                //复核通过的申报的应纳税额
//                Debug.out("应纳税额＝＝＝＝＝"+tjynseSql.toString() +
//                                              " and a.drpch='" +
//                                              recordPch + "' and (c.ztbs='" +
//                                              Constants.ZB_ZTBS_YFH + "' or c.ztbs='" +
//                                              Constants.ZB_ZTBS_YRK + "')");
                tmpPs = conn.prepareStatement(tjynseSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "' and (c.ztbs='" +
                                              Constants.ZB_ZTBS_YFH +
                                              "' or c.ztbs='" +
                                              Constants.ZB_ZTBS_YRK + "')");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setSumYnse(DataConvert.double2Currency(tmpRs.
                            getDouble("tjynse"), 2));
                }
                result.add(tmpBo);
                tmpPs.close();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return result;
    }

    //说明：该方法是针对页面中"批量受理(税务人员)"模块
    public static ArrayList get2(PlcxBo2 plcxBo, String conditions,
                                 Connection conn) throws
            Exception {
        ArrayList result = new ArrayList();
        // 查询用SQL语句
        StringBuffer sql = new StringBuffer(
                "select * from QSDB.QS_JL_DRPCINFO a");
        String where = " where a.sjly='01' and 1=1" + conditions;
        // 判断查询条件
        // 数据提供者名称
        String sjtgz = plcxBo.getSjtgz();
        if (sjtgz != null && !sjtgz.trim().equals("")) {
            where += (" and a.tgzmc='" + sjtgz + "'");
        }
        // 批量号
        String pch = plcxBo.getPch();
        if (pch != null && !pch.trim().equals("")) {
            where += (" and a.drpch='" + pch + "'");
        }
        // 导入时间段
        String drsjBegin = plcxBo.getDrsjBegin();
        String drsjEnd = plcxBo.getDrsjEnd();
        if (drsjBegin != null && !drsjBegin.trim().equals("")) {
            where += (" and a.drsj>=TO_DATE('" + drsjBegin +
                      "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (drsjEnd != null && !drsjEnd.trim().equals("")) {
            where +=
                    (" and a.drsj<=TO_DATE('" + drsjEnd +
                     " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }
        // 提交时间段
        String tjsjBegin = plcxBo.getTjsjBegin(), tjsjEnd = plcxBo.getTjsjEnd();
        if (tjsjBegin != null && !tjsjBegin.trim().equals("")) {
            where +=
                    (" and a.tjsj>=TO_DATE('" + tjsjBegin +
                     "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (tjsjEnd != null && !tjsjEnd.trim().equals("")) {
            where +=
                    (" and a.tjsj<=TO_DATE('" + tjsjEnd +
                     " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            Debug.out("主查询语句" + sql.append(where+" and rownum<202").toString());
            ps = conn.prepareStatement(sql.append(where +
                    " and rownum<202 order by drsj").
                                       toString());
            rs = ps.executeQuery();
            // 统计查询语句
            StringBuffer drSql = new StringBuffer(
                    "select count(*) as drbs from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_DRZB b ").
                                 append(where).append(
                    " and a.sjly='01' and b.sjly='02' and a.DRPCH=b.drpch");
            StringBuffer tjSql = new StringBuffer(
                    "select count(*) as tjbs from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_SBZB c ").
                                 append(where).append(
                    " and a.sjly='01' and c.sjly='04' and a.DRPCH=c.drpch");
            StringBuffer tjynseSql = new StringBuffer(
                    "select sum(c.ynse) as tjynse from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_SBZB c ").
                                     append(where).append(
                                             " and a.sjly='01' and c.sjly='04' and c.DRPCH=a.drpch"); ;

            StringBuffer shsjSql = new StringBuffer("select d.dysj as shsj from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_DRZB b,QSDB.QS_JL_SBZB c,QSDB.QS_JL_HDTZS d ").
                                   append(where).append(
                                           " and a.sjly='01' and b.sjly='01' and c.sjly='04' and a.drpch=b.drpch and c.drpch=b.drpch and c.sbbh=d.sbbh"); ;
            // 计数器
            int i = 0;
            while (rs != null && rs.next()) {
                // 查询结果大于200条则提示输入更精确查询条件
                if (i++ > 200) {
                    throw new ApplicationException(
                            "查询结果集过大只显示前200条，请输入更精确的查询条件！");
                }
                PreparedStatement tmpPs = null;
                ResultSet tmpRs = null;
                PlcxBo2 tmpBo = new PlcxBo2();
                //批量号
                String recordPch = rs.getString("drpch");
                tmpBo.setPch(recordPch);
                //导入时间
                tmpBo.setDrsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "drsj")));
                //提交时间
                tmpBo.setTjsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "tjsj")));
                //导入笔数
//                Debug.out(drSql.toString() + " and a.drpch='" + recordPch + "'");
                tmpPs = conn.prepareStatement(drSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setDrbs(tmpRs.getString("drbs"));
                }
                tmpPs.close();
                //提交笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setTjbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //受理通过笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and c.ztbs='" +
                                              Constants.ZB_ZTBS_DY + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setSltgbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //复核通过的笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and (c.ztbs='" +
                                              Constants.ZB_ZTBS_YFH +
                                              "' or c.ztbs='" +
                                              Constants.ZB_ZTBS_YRK + "')");
//                Debug.out("复核通过的笔数"+tjSql.toString() +
//                                              " and a.drpch='" +
//                                              recordPch + "'"
//                                              +
//                                              " and (c.ztbs='" +
//                                              Constants.ZB_ZTBS_YFH + "' or c.ztbs='" +
//                                              Constants.ZB_ZTBS_YRK + "')");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setFstgbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //审核通过笔数
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and c.ztbs='" +
                                              Constants.ZB_ZTBS_DY_HD + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setShtgbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //审核时间
                tmpPs = conn.prepareStatement(shsjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setShsj(DataConvert.TimeStamp2String(tmpRs.
                            getTimestamp("shsj")));
                }
                tmpPs.close();
                //复核通过的申报的应纳税额
//                Debug.out("应纳税额＝＝＝＝＝"+tjynseSql.toString() +
//                                              " and a.drpch='" +
//                                              recordPch + "' and (c.ztbs='" +
//                                              Constants.ZB_ZTBS_YFH + "' or c.ztbs='" +
//                                              Constants.ZB_ZTBS_YRK + "')");
                tmpPs = conn.prepareStatement(tjynseSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "' and (c.ztbs='" +
                                              Constants.ZB_ZTBS_YFH +
                                              "' or c.ztbs='" +
                                              Constants.ZB_ZTBS_YRK + "')");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setSumYnse(DataConvert.double2Currency(tmpRs.
                            getDouble("tjynse"), 2));
                }
                result.add(tmpBo);
                tmpPs.close();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
        return result;
    }


    public static ArrayList getDetail(PlcxMxBo plcxMxBo, Connection conn) throws
            Exception {
        ArrayList result = new ArrayList();
        String zt = plcxMxBo.getZt();
        String pch = plcxMxBo.getPch();
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 查询本批次导入信息
        if (zt.equals(Constants.PC)) {
            try {
                // 临时表中数据
                sql = "select * from QSDB.QS_JL_DRZB where drpch='" + pch +
                      "' and ztbs<>'" + Constants.DRZB_ZT_RECIVE + "'";
                Debug.out(sql);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int i = 0;
                    String drsjnr = rs.getString("drsjnr");
                    PldrBo pldrBo = (PldrBo) QsglPcclXmlUtil.getRecord(drsjnr);
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "已检查";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "已导入";
                    }
                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //房地产地址
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //应纳税额
                    mxBo.setYnse("");
                    result.add(mxBo);
                    Debug.out(String.valueOf(i++));
                }

                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "已受理";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "受理完毕";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "已审核";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "已复核";
                    }
                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));
                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.DRZB_ZT_XINZENG)) {
            try {
                // 临时表中数据
                sql = "select * from QSDB.QS_JL_DRZB where drpch='" + pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String drsjnr = rs.getString("drsjnr");
                    PldrBo pldrBo = (PldrBo) QsglPcclXmlUtil.getRecord(drsjnr);
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "已检查";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "已导入";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_RECIVE)) {
                        drzbzt = "已提交";
                    }
                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //房地产地址
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //应纳税额
                    mxBo.setYnse("");
                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;

        } else if (zt.equals(Constants.DRZB_ZT_RECIVE)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "受理完毕";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "已审核";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "已复核";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "已受理";
                    }

                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));
                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.SBZB_ZT_SHOULI)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    mxBo.setZt("受理完毕");
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));

                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.SBZB_ZT_FUHE)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and (a.ztbs='" + Constants.ZB_ZTBS_YFH +
                      "' or a.ztbs='" + Constants.ZB_ZTBS_YRK + "')";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    mxBo.setZt("已复核");
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));

                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.SBZB_ZT_SHENHE)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY_HD + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    mxBo.setZt("已审核");
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));

                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        }
        return result;
    }


    //说明：该方法是针对页面中"批量受理(税务人员)"模块
    public static ArrayList getDetail2(PlcxMxBo2 plcxMxBo, Connection conn) throws
            Exception {
        ArrayList result = new ArrayList();
        String zt = plcxMxBo.getZt();
        String pch = plcxMxBo.getPch();
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 查询本批次导入信息
        if (zt.equals(Constants.PC)) {
            try {
                // 临时表中数据
                sql = "select * from QSDB.QS_JL_DRZB where drpch='" + pch +
                      "' and ztbs<>'" + Constants.DRZB_ZT_RECIVE + "'";
                Debug.out(sql);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int i = 0;
                    String drsjnr = rs.getString("drsjnr");
                    PldrBo2 pldrBo = (PldrBo2) QsglPcclXmlUtil.getRecord2(
                            drsjnr);
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "已检查";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "已导入";
                    }
                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //房地产地址
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //应纳税额
                    mxBo.setYnse("");
                    result.add(mxBo);
                    Debug.out(String.valueOf(i++));
                }

                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "已受理";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "受理完毕";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "已审核";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "已复核";
                    }
                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));
                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.DRZB_ZT_XINZENG)) {
            try {
                // 临时表中数据
                sql = "select * from QSDB.QS_JL_DRZB where drpch='" + pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String drsjnr = rs.getString("drsjnr");
                    PldrBo2 pldrBo = (PldrBo2) QsglPcclXmlUtil.getRecord2(
                            drsjnr);
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "已检查";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "已导入";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_RECIVE)) {
                        drzbzt = "已提交";
                    }
                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //房地产地址
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //应纳税额
                    mxBo.setYnse("");
                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;

        } else if (zt.equals(Constants.DRZB_ZT_RECIVE)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "受理完毕";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "已审核";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "已复核";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "已受理";
                    }

                    mxBo.setZt(drzbzt);
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));
                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.SBZB_ZT_SHOULI)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    mxBo.setZt("受理完毕");
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));

                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.SBZB_ZT_FUHE)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and (a.ztbs='" + Constants.ZB_ZTBS_YFH +
                      "' or a.ztbs='" + Constants.ZB_ZTBS_YRK + "')";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    mxBo.setZt("已复核");
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));

                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        } else if (zt.equals(Constants.SBZB_ZT_SHENHE)) {
            try {
                // 正式表中数据
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY_HD + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //批次号
                    mxBo.setPch(pch);
                    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
                    mxBo.setZt("已审核");
                    //申报表号
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //纳税人名称
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //纳税人计算机代码
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //房地产项目名称
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //房地产地址
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //合同签订日期
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //计税金额
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //应纳税额
                    mxBo.setYnse(DataConvert.double2Currency(rs.getDouble(
                            "ynse"), 2));

                    result.add(mxBo);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                ps.close();
            }
            return result;
        }
        return result;
    }

}
