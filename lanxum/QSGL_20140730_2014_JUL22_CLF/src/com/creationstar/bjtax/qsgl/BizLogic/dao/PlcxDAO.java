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
        // ��ѯ��SQL���
        StringBuffer sql = new StringBuffer(
                "select * from QSDB.QS_JL_DRPCINFO a");
        String where = " where a.sjly='02' and 1=1" + conditions;
        // �жϲ�ѯ����
        // �����ṩ������
        System.out.println("///////////////////////");
        System.out.println("�����ṩ������:" + plcxBo.getSjtgz());
        System.out.println("������:" + plcxBo.getPch());
        System.out.println("����ʱ���:" + plcxBo.getDrsjBegin());
        System.out.println("����ʱ���:" + plcxBo.getDrsjEnd());
        System.out.println("///////////////////////");
        String sjtgz = plcxBo.getSjtgz();
        if (sjtgz != null && !sjtgz.trim().equals("")) {
            where += (" and a.tgzmc='" + sjtgz + "'");
        }
        // ������
        String pch = plcxBo.getPch();
        if (pch != null && !pch.trim().equals("")) {
            where += (" and a.drpch='" + pch + "'");
        }
        // ����ʱ���
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
        // �ύʱ���
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
//            Debug.out("����ѯ���" + sql.append(where+" and rownum<202").toString());
            ps = conn.prepareStatement(sql.append(where +
                    " and rownum<202 order by drsj").
                                       toString());
            System.out.println(sql.append(where +
                                          " and rownum<202 order by drsj").
                               toString());
            rs = ps.executeQuery();
            // ͳ�Ʋ�ѯ���
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
            // ������
            int i = 0;
            while (rs != null && rs.next()) {
                // ��ѯ�������200������ʾ�������ȷ��ѯ����
                if (i++ > 200) {
                    throw new ApplicationException(
                            "��ѯ���������ֻ��ʾǰ200�������������ȷ�Ĳ�ѯ������");
                }
                PreparedStatement tmpPs = null;
                ResultSet tmpRs = null;
                PlcxBo tmpBo = new PlcxBo();
                //������
                String recordPch = rs.getString("drpch");
                tmpBo.setPch(recordPch);
                //����ʱ��
                tmpBo.setDrsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "drsj")));
                //�ύʱ��
                tmpBo.setTjsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "tjsj")));
                //�������
//                Debug.out(drSql.toString() + " and a.drpch='" + recordPch + "'");
                tmpPs = conn.prepareStatement(drSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setDrbs(tmpRs.getString("drbs"));
                }
                tmpPs.close();
                //�ύ����
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setTjbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //����ͨ������
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
                //����ͨ���ı���
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and (c.ztbs='" +
                                              Constants.ZB_ZTBS_YFH +
                                              "' or c.ztbs='" +
                                              Constants.ZB_ZTBS_YRK + "')");
//                Debug.out("����ͨ���ı���"+tjSql.toString() +
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
                //���ͨ������
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
                //���ʱ��
                tmpPs = conn.prepareStatement(shsjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setShsj(DataConvert.TimeStamp2String(tmpRs.
                            getTimestamp("shsj")));
                }
                tmpPs.close();
                //����ͨ�����걨��Ӧ��˰��
//                Debug.out("Ӧ��˰���������"+tjynseSql.toString() +
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

    //˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
    public static ArrayList get2(PlcxBo2 plcxBo, String conditions,
                                 Connection conn) throws
            Exception {
        ArrayList result = new ArrayList();
        // ��ѯ��SQL���
        StringBuffer sql = new StringBuffer(
                "select * from QSDB.QS_JL_DRPCINFO a");
        String where = " where a.sjly='01' and 1=1" + conditions;
        // �жϲ�ѯ����
        // �����ṩ������
        String sjtgz = plcxBo.getSjtgz();
        if (sjtgz != null && !sjtgz.trim().equals("")) {
            where += (" and a.tgzmc='" + sjtgz + "'");
        }
        // ������
        String pch = plcxBo.getPch();
        if (pch != null && !pch.trim().equals("")) {
            where += (" and a.drpch='" + pch + "'");
        }
        // ����ʱ���
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
        // �ύʱ���
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
//            Debug.out("����ѯ���" + sql.append(where+" and rownum<202").toString());
            ps = conn.prepareStatement(sql.append(where +
                    " and rownum<202 order by drsj").
                                       toString());
            rs = ps.executeQuery();
            // ͳ�Ʋ�ѯ���
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
            // ������
            int i = 0;
            while (rs != null && rs.next()) {
                // ��ѯ�������200������ʾ�������ȷ��ѯ����
                if (i++ > 200) {
                    throw new ApplicationException(
                            "��ѯ���������ֻ��ʾǰ200�������������ȷ�Ĳ�ѯ������");
                }
                PreparedStatement tmpPs = null;
                ResultSet tmpRs = null;
                PlcxBo2 tmpBo = new PlcxBo2();
                //������
                String recordPch = rs.getString("drpch");
                tmpBo.setPch(recordPch);
                //����ʱ��
                tmpBo.setDrsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "drsj")));
                //�ύʱ��
                tmpBo.setTjsj(DataConvert.TimeStamp2String(rs.getTimestamp(
                        "tjsj")));
                //�������
//                Debug.out(drSql.toString() + " and a.drpch='" + recordPch + "'");
                tmpPs = conn.prepareStatement(drSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setDrbs(tmpRs.getString("drbs"));
                }
                tmpPs.close();
                //�ύ����
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs.next()) {
                    tmpBo.setTjbs(tmpRs.getString("tjbs"));
                }
                tmpPs.close();
                //����ͨ������
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
                //����ͨ���ı���
                tmpPs = conn.prepareStatement(tjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'"
                                              +
                                              " and (c.ztbs='" +
                                              Constants.ZB_ZTBS_YFH +
                                              "' or c.ztbs='" +
                                              Constants.ZB_ZTBS_YRK + "')");
//                Debug.out("����ͨ���ı���"+tjSql.toString() +
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
                //���ͨ������
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
                //���ʱ��
                tmpPs = conn.prepareStatement(shsjSql.toString() +
                                              " and a.drpch='" +
                                              recordPch + "'");
                tmpRs = tmpPs.executeQuery();
                if (tmpRs != null && tmpRs.next()) {
                    tmpBo.setShsj(DataConvert.TimeStamp2String(tmpRs.
                            getTimestamp("shsj")));
                }
                tmpPs.close();
                //����ͨ�����걨��Ӧ��˰��
//                Debug.out("Ӧ��˰���������"+tjynseSql.toString() +
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
        // ��ѯ�����ε�����Ϣ
        if (zt.equals(Constants.PC)) {
            try {
                // ��ʱ��������
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
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "�Ѽ��";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "�ѵ���";
                    }
                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //���ز���ַ
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //Ӧ��˰��
                    mxBo.setYnse("");
                    result.add(mxBo);
                    Debug.out(String.valueOf(i++));
                }

                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "������";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "�������";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "�����";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "�Ѹ���";
                    }
                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʱ��������
                sql = "select * from QSDB.QS_JL_DRZB where drpch='" + pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String drsjnr = rs.getString("drsjnr");
                    PldrBo pldrBo = (PldrBo) QsglPcclXmlUtil.getRecord(drsjnr);
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "�Ѽ��";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "�ѵ���";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_RECIVE)) {
                        drzbzt = "���ύ";
                    }
                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //���ز���ַ
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "�������";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "�����";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "�Ѹ���";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "������";
                    }

                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    mxBo.setZt("�������");
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and (a.ztbs='" + Constants.ZB_ZTBS_YFH +
                      "' or a.ztbs='" + Constants.ZB_ZTBS_YRK + "')";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    mxBo.setZt("�Ѹ���");
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY_HD + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo mxBo = new PlcxMxBo();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    mxBo.setZt("�����");
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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


    //˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
    public static ArrayList getDetail2(PlcxMxBo2 plcxMxBo, Connection conn) throws
            Exception {
        ArrayList result = new ArrayList();
        String zt = plcxMxBo.getZt();
        String pch = plcxMxBo.getPch();
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        // ��ѯ�����ε�����Ϣ
        if (zt.equals(Constants.PC)) {
            try {
                // ��ʱ��������
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
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "�Ѽ��";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "�ѵ���";
                    }
                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //���ز���ַ
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //Ӧ��˰��
                    mxBo.setYnse("");
                    result.add(mxBo);
                    Debug.out(String.valueOf(i++));
                }

                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "������";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "�������";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "�����";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "�Ѹ���";
                    }
                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʱ��������
                sql = "select * from QSDB.QS_JL_DRZB where drpch='" + pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String drsjnr = rs.getString("drsjnr");
                    PldrBo2 pldrBo = (PldrBo2) QsglPcclXmlUtil.getRecord2(
                            drsjnr);
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.DRZB_ZT_CHECKED)) {
                        drzbzt = "�Ѽ��";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_XINZENG)) {
                        drzbzt = "�ѵ���";
                    } else if (drzbzt.equals(Constants.DRZB_ZT_RECIVE)) {
                        drzbzt = "���ύ";
                    }
                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(pldrBo.getTufwxx().getSbbh());
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(pldrBo.getTufwxx().getFdcxmmc());
                    //���ز���ַ
                    mxBo.setFdcdz(pldrBo.getTufwxx().getTdfwzldz());
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(pldrBo.
                            getTufwxx().getHtqdsj()));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(CommonUtil.getCjjg(
                            pldrBo.getTufwxx()), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch +
                      "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    String drzbzt = rs.getString("ztbs");
                    if (drzbzt.equals(Constants.ZB_ZTBS_DY)) {
                        drzbzt = "�������";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_DY_HD)) {
                        drzbzt = "�����";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_YFH) ||
                               drzbzt.equals(Constants.ZB_ZTBS_YRK)) {
                        drzbzt = "�Ѹ���";
                    } else if (drzbzt.equals(Constants.ZB_ZTBS_BC)) {
                        drzbzt = "������";
                    }

                    mxBo.setZt(drzbzt);
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    mxBo.setZt("�������");
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and (a.ztbs='" + Constants.ZB_ZTBS_YFH +
                      "' or a.ztbs='" + Constants.ZB_ZTBS_YRK + "')";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    mxBo.setZt("�Ѹ���");
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
                // ��ʽ��������
                sql = "select a.*,b.nsrmc,b.nsrjsjdm,b.fdcxmmc,b.fdcdz,b.htqdrq from qsdb.qs_jl_sbzb a,qsdb.qs_jl_drzb b where a.sbbh=b.sbbh and b.drpch='" +
                      pch + "' and a.ztbs='" + Constants.ZB_ZTBS_DY_HD + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlcxMxBo2 mxBo = new PlcxMxBo2();
                    //���κ�
                    mxBo.setPch(pch);
                    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
                    mxBo.setZt("�����");
                    //�걨���
                    mxBo.setSbbh(rs.getString("sbbh"));
                    //��˰������
                    mxBo.setNsrmc(rs.getString("nsrmc"));
                    //��˰�˼��������
                    mxBo.setJsjdm(rs.getString("nsrjsjdm"));
                    //���ز���Ŀ����
                    mxBo.setFdcxmmc(rs.getString("fdcxmmc"));
                    //���ز���ַ
                    mxBo.setFdcdz(rs.getString("fdcdz"));
                    //��ͬǩ������
                    mxBo.setHtqdrq(DataConvert.TimeStamp2String(rs.getTimestamp(
                            "htqdrq")));
                    //��˰���
                    mxBo.setJsje(DataConvert.double2Currency(rs.getDouble(
                            "jsje"), 2));
                    //Ӧ��˰��
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
