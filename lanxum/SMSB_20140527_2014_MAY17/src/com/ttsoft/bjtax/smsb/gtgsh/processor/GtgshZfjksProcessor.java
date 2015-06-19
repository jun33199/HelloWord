/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszsbhz;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszz;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant; //import com.ttsoft.bjtax.
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshZfjksForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻�������˰֤����</p>
 * @author �������飭��½��
 * @version 1.1
 */

public class GtgshZfjksProcessor implements Processor {
    public GtgshZfjksProcessor() {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        /**@todo Implement this com.ttsoft.framework.processor.Processor method*/
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case CodeConstant.SMSB_SHOWACTION:
            result = doShow(vo);
            break;
        case CodeConstant.SMSB_QUERYACTION:
            result = doQuery(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
            result = doDelete(vo);
            break;
        case CodeConstant.SMSB_UPDATEACTION:
            result = doUpdate(vo);
            break;
        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ҳ���ʼ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow(VOPackage vo) throws BaseException {
        GtgshZfjksForm pf = new GtgshZfjksForm();
        try {
            //����˽�з������в�ѯ
            pf = (GtgshZfjksForm) doQuery(vo);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return pf;
    }

    /**
     * ��ѯ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList dataList = new ArrayList();
        ArrayList detailList = new ArrayList();
        //UserData����
        UserData ud = (UserData) vo.getUserData();
        Connection conn = null;
        SfDBUtils sfDB = null;
        String whereCon = "";
        //���ø�ʽ������
        DecimalFormat deFormat = new DecimalFormat("#0.00");

        GtgshZfjksForm pf = (GtgshZfjksForm) vo.getData();
        String hzfs = pf.getHzfs();
        //���ϱ��˻��� 1 / ���ϱ���λ���� 0
        if (hzfs.equals(CodeConstant.HZFS_DW)) {
            whereCon = " and a.hzfs='" + hzfs +
                       "' and a.jsjdm='" + pf.getJsjdm() +
                       "'";
        }
        if (hzfs.equals(CodeConstant.HZFS_GR)) {
            whereCon = " and a.hzfs='" + hzfs +
                       "' and a.jsjdm='" + pf.getJsjdm() +
                       "' and a.lrr='" + ud.getYhid() + "'";
        }
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            sfDB = new SfDBUtils(conn);
            ResultSet rsCan = null;
            ResultSet rsDetail = null;
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);

            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
                pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //��֯��������
                pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //��֯��������
            } catch (Exception ex5) {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

            //���ò�ѯ���������ܲ�ѯ
            /**20040415 Shi Yanfeng**/
            /***ֻ���Գ����ᱨ����Ϊ�յĽɿ���**/
//            String strSql = "select distinct m.sbhzdh,m.hzrq,m.jsjdm," +
//                            " m.sjse,m.zs from (select distinct a.sbhzdh, " +
//                            " to_char(a.hzrq,'yyyymmdd hh24:mi:ss') hzrq,a.jsjdm," +
//                            " sum(a.sjse) sjse,sum(1) zs " +
//                            " from sbdb.sb_jl_gtgshwszsbhz a " +
//                            " where a.jkpzh in (select b.jkpzh from sbdb.sb_jl_sbjkzb b " +
//                            " where b.sjly='" + CodeConstant.SMSB_SJLY_GTGSHHZ +
//                            "' " +
//                            " and b.zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
//                            CodeConstant.SMSB_ZWBS + "'" +
//                            " and b.qxdm='" + qxdm + "'" +
//                            " and b.jsjdm='" + pf.getJsjdm() + "'" +
//                            " and b.jkpzh like '" + pf.getJsjdm() + "%' ) " +
//                            whereCon + " and a.qxdm='" + qxdm + "' " +
//                            " group by a.sbhzdh,a.hzrq,a.jsjdm " +
//                            " order by hzrq desc, a.sbhzdh asc ) m,sbdb.sb_jl_gtgshwszz n" +
//                            " where m.sbhzdh=n.sbhzdh and n.jbdh is null";
            String strSql = "select a.sbhzdh,b.sbbh,a.jsjdm," +
                            "sum(substr(b.zwbs,1,1) || substr(b.zwbs,20,1)) Count," +
                            "sum(a.sjse) sjse,sum(1) zs " +
                            "from sbdb.sb_jl_sbjkzb b,sbdb.sb_jl_gtgshwszsbhz a" +
                            " where a.jkpzh=b.jkpzh and b.sjly='" +
                            CodeConstant.SMSB_SJLY_GTGSHHZ +
                            "' " + " and b.zwbs like '" +
                            CodeConstant.SMSB_ZWBS + "%" +
                            CodeConstant.SMSB_ZWBS + "'" +
                            " and b.qxdm='" + qxdm + "'" +
                            " and b.jsjdm='" + pf.getJsjdm() + "'" +
                            " and b.jkpzh like '" + pf.getJsjdm() + "%'" +
                            whereCon +
                            " group by a.sbhzdh,b.sbbh,a.jsjdm" +
                            " having sum(substr(b.zwbs,1,1) || substr(b.zwbs,20,1))=0";
            System.out.println("���幤�̻��������ܲ�ѯ���������ܲ�ѯ" + strSql);
            try {
                dataList = (ArrayList) sfDB.getDataList(strSql);
                rsCan = da.querySQL(strSql);
            } catch (Exception ex1) {
                ex1.printStackTrace();
                throw new ApplicationException("��ѯ���ݳ���");
            }
            System.out.println("��ѯ�����������������������"+dataList.size());
            if (dataList.size()<=0) {
                throw new ApplicationException("û�з������������ݣ�");
            }

            //���ò�ѯ��������ѯ��ϸ��Ϣ
//            strSql = "select a.sbhzdh,a.jkpzh,a.sjse, " +
//                     " substr(a.jkpzh,1,length(a.jkpzh)-1) jkpzh_ypds " +
//                     " from sbdb.sb_jl_gtgshwszsbhz a " +
//                     " where a.jkpzh in (select b.jkpzh from sbdb.sb_jl_sbjkzb b " +
//                     " where b.sjly='" + CodeConstant.SMSB_SJLY_GTGSHHZ + "' " +
//                     " and b.zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
//                     CodeConstant.SMSB_ZWBS + "' " +
//                     " and b.qxdm='" + qxdm + "'" +
//                     " and b.jsjdm='" + pf.getJsjdm() + "'" +
//                     " and b.jkpzh like '" + pf.getJsjdm() + "%' ) " +
//                     whereCon + " and a.qxdm='" + qxdm + "' " +
//                     " order by to_number(a.sbhzdh) asc,to_number(a.jkpzh) asc";
            while (rsCan.next()) {
                String sbbh = rsCan.getString("sbbh");
                strSql =
                        "SELECT DISTINCT c.JKPZH,c.SJSE,c.SBHZDH FROM SBDB.SB_JL_GTGSHWSZZ a,SBDB.SB_JL_GTGSHWSZMX b," +
                        "SBDB.SB_JL_GTGSHWSZSBHZ c,SBDB.SB_JL_SBJKZB d " +
                        "WHERE b.WSZXH=a.WSZXH AND a.NSRJSJDM=b.NSRJSJDM and a.ndzb=b.ndzb " +
                        "AND a.JSJDM=c.JSJDM " +
                        "AND a.SBHZDH=c.SBHZDH AND c.JKPZH=d.JKPZH and c.jsjdm=d.jsjdm " +
                        "AND d.SBBH='" + sbbh + "' AND d.jsjdm='" + pf.getJsjdm() + "'";
                System.out.println("���幤�̻���ϸ��ѯ============" + strSql);
                try {
                    rsDetail = da.querySQL(strSql);
                    while(rsDetail.next())
                    {
                        HashMap tmpMap = new HashMap();
                        tmpMap.put("jkpzh_ypds", rsDetail.getString("JKPZH"));
                        tmpMap.put("jkpzh", rsDetail.getString("JKPZH"));
                        tmpMap.put("sjse", rsDetail.getString("SJSE"));
                        tmpMap.put("sbhzdh",rsDetail.getString("SBHZDH"));
                        tmpMap.put("sbbh", sbbh);
                        detailList.add(tmpMap);
                        System.out.println("con=====");
                    }
                    rsDetail.close();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                    throw new ApplicationException("��ѯ���ݳ���");
                }
            }
            rsCan.close();
            pf.setDataList(dataList);
            pf.setDetailList(detailList);

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException {
        return null;
    }

    /**
     * ɾ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        List dataList = new ArrayList();

        Connection conn = null;
        UserData ud = new UserData();
        //from����
        GtgshZfjksForm pf = new GtgshZfjksForm();
        pf = (GtgshZfjksForm) vo.getData();
        String sbbh = pf.getSbbh();
        String hzfs = pf.getHzfs();

        //ormapping����
        Sbjkzb orObjJkzb = new Sbjkzb();
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            ud = (UserData) vo.getUserData();
            /**
             * ���Ͻɿ�����־
             * 20040429 Shi Yanfeng
             */
            TinyTools.makeLog4GtgshZfjks(ud, pf.getSbhzdh());
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);

            //1.�����걨����ҵ����н���ƾ֤�ţ����ڶ�Ӧ�걨���ܱ��ҵ���Ӧ���걨���ܵ��ţ�
            Vector tempVector = new Vector();
            tempVector.addElement(" sbbh='" + sbbh+"'");
            tempVector.addElement(" qxdm='" + qxdm + "'");
            tempVector.addElement(" jsjdm='" + pf.getJsjdm() + "'");
            tempVector.addElement(" 1=1 order by sbbh desc");
            try {
                dataList = da.query(Sbjkzb.class, tempVector);
            } catch (BaseException ex1) {
                ex1.printStackTrace();
                throw new ApplicationException("��ȡ��ɾ�������ݳ���");
            }
            for (int i = 0; i < dataList.size(); i++) {
                Sbjkzb orObjHz = (Sbjkzb) dataList.get(i);
                String jkpzh = orObjHz.getJkpzh();
                String wszhzSql = "SELECT SBHZDH FROM SBDB.SB_JL_GTGSHWSZSBHZ " +
                                  "WHERE JKPZH='" + jkpzh + "' AND HZFS='"+hzfs+"'";
                ResultSet wszhzRs = da.querySQL(wszhzSql);
                while (wszhzRs.next()) {
                    String sbhzdh = wszhzRs.getString("SBHZDH");
                    //2.����˰֤��������걨���ܵ����ֶ�Ϊ�գ�
                    String updateWszzSql = "UPDATE SBDB. SB_JL_GTGSHWSZZ " +
                                           " SET SBHZDH='',LRRQ=SYSDATE " +
                                           " WHERE QXDM='" + qxdm + "' " +
                                           " AND SBHZDH='" + sbhzdh + "'";
                    try {
                        da.updateSQL(updateWszzSql);
                    } catch (BaseException ex1) {
                        ex1.printStackTrace();
                        throw new ApplicationException("������˰֤�������ݳ���");
                    }
                    //3.ɾ���걨���ܱ�����
                    String delwszsbhzSql = "DELETE FROM SBDB.SB_JL_GTGSHWSZSBHZ "+
                                          " WHERE QXDM='" + qxdm + "' " +
                                          " AND JSJDM='" + pf.getJsjdm() +
                                          "'" + " AND JKPZH='" + jkpzh +
                                          "' AND SBHZDH='"+sbhzdh+"'";
                    try {
                        da.updateSQL(delwszsbhzSql);
                    } catch (BaseException ex1) {
                        ex1.printStackTrace();
                        throw new ApplicationException("ɾ�����ܱ����ݳ���");
                    }
                    //4.ɾ�����������
                    //ɾ���걨�ɿ���ϸ������
                    String delSbjkmxSql = "DELETE FROM SBDB.SB_JL_SBJKMX "+
                                          " WHERE QXDM='" + qxdm + "' " +
                                          " AND JSJDM='" + pf.getJsjdm() +
                                          "'" + " AND JKPZH='" + jkpzh + "'";
                    try {
                        da.updateSQL(delSbjkmxSql);
                    } catch (BaseException ex1) {
                        ex1.printStackTrace();
                        throw new ApplicationException("ɾ���걨�ɿ���ϸ�����ݳ���");
                    }
                    //ɾ���걨�ɿ���������
                    orObjJkzb.setQxdm(qxdm);
                    orObjJkzb.setJsjdm(pf.getJsjdm());
                    orObjJkzb.setJkpzh(jkpzh);
                    try {
                        int nret = da.updateSQL(
                                "delete from sbdb.sb_jl_sbjkzb where qxdm='" +
                                orObjJkzb.getQxdm() +
                                "' and jkpzh='" + orObjJkzb.getJkpzh() +
                                "'  and jsjdm='" + orObjJkzb.getJsjdm() +
                                "' AND zwbs like '" +
                                CodeConstant.SMSB_ZWBS +
                                "%" +
                                CodeConstant.SMSB_ZWBS + "'");
                        if (nret == 0) {
                            //���û�п�ɾ��������ع�
                            throw new ApplicationException("�����ɿ���ʧ�ܣ�");
                        }
                    } catch (BaseException ex1) {
                        ex1.printStackTrace();
                        throw new ApplicationException("ɾ���걨�ɿ��������ݳ���");
                    }
                }
            }
        }catch(SQLException sqlep){
            throw new ApplicationException("��ȡ��˰֤���ܱ�ɿ�ƾ֤�ų���");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate(VOPackage vo) throws BaseException {
        return null;
    }

}
//:-)
