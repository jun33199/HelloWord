package com.lscdz.qysds.common.service.qysds.persistent.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.rowset.CachedRowSet;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsConstants;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.util.ReportsUtils;
import com.lscdz.qysds.common.service.qysds.util.StringUtils;



/**
 * <p>Title: �Զ��屨��-��ҵ����˰���ݹ�����</p>
 * <p>Description: �������к���ҵ����˰�걨����ص����ݲ�������صĹ��߲�����</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class QysdsJBAppAccess extends AppAccess implements IAppAccess {

    /**
     * ���캯��
     * @param loger ��־
     * @param idba ���ݿ�
     */
    public QysdsJBAppAccess(DBAccess idba) {
        super(idba);
    }

    /**
     * ��ȡ��ǰӦ�û�汾��
     * @param appid String Ӧ��ID
     * @return String ��ǰӦ�û�汾��
     */
    public String getCurrentVersion(String appid) {
        return QysdsReportsConstants.CREPORTS_VERSION_QYSDS;
    }

    /**
     * ������˽����������
     * @param obj ɾ������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @param checkFlag ��־λ,�μ�QysdsReportsConstants.CREPORTS_CHECK_QYSDS_NOPASS��QysdsReportsConstants.CREPORTS_CHECK_QYSDS_PASS
     * @exception FrameException ҵ���쳣
     */
    public void setCheckResult(Object obj, String checkFlag) throws
    FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        //1.����У��
        if (obj == null) {
            throw new FrameException("���뱣�����Ϊ�գ�");
        }
        //2.��ʼ��
        qrd = (QysdsReportsDeclare) obj;
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.���б��������У��
            if (!qrd.checkValid(2)) {
                throw new FrameException("���뱣������Ƿ���");
            }
            ///3.1.���±����걨����
            ////3.1.0.
            sb = new StringBuffer();
            sb.append("UPDATE SBDB.SB_JL_QYSDSSBB_CZZB SET SHBZ=");
            sb.append(StringUtils.getSQLStr(checkFlag));
            sb.append(" WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            this.idba.executeUpdate(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
    }


    /**
     * ɾ������
     * @param obj ɾ������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @exception FrameException ҵ���쳣
     */
    public void delete(Object obj) throws FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        //1.����У��
        if (obj == null) {
            throw new FrameException("���뱣�����Ϊ�գ�");
        }
        //2.��ʼ��
        qrd = (QysdsReportsDeclare) obj;
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.���б��������У��
            if (!qrd.checkValid(2)) {
                throw new FrameException("���뱣������Ƿ���");
            }

            ///3.1.ɾ�������걨����
            ////3.1.0.
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            this.idba.executeUpdate(sb.toString());
            ////3.1.1.
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ start**/
            //sb.append("DELETE /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/ FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            this.idba.executeUpdate(sb.toString());
            ////3.1.2.
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            this.idba.executeUpdate(sb.toString());
            ////3.1.3.
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ start**/
            //sb.append("DELETE /*+ index (t PK_SB_JL_QYSDSSBB_ZB_JD)*/ FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            this.idba.executeUpdate(sb.toString());
            ///3.2.ɾ������������
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_QYSDSSBB_CZZB WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            this.idba.executeUpdate(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
    }

    /**
     * ɾ������,ֻ����߱����Ա������
     * @param obj ɾ������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @exception FrameException ҵ���쳣
     */
    public void deleteSingleTable(Object obj) throws FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        QysdsReportsTableDeclare qrtd = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        Connection conn;
        PreparedStatement pstat_nd;
        PreparedStatement pstat_nd_child;
        PreparedStatement pstat_jd;
        PreparedStatement pstat_jd_child;
        //1.����У��
        if (obj == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷������걨������");
        }
        //2.��ʼ��
        conn = this.idba.getConn();
        qrd = (QysdsReportsDeclare) obj;
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.��ʼ�����ݲ���
            boolean flag_nd = false;
            boolean flag_jd = false;
            pstat_nd = conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
            pstat_jd = conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
           /**modify by lianglw 20071008 ����ǿ������ start**/
            //pstat_nd = conn.prepareStatement("DELETE  /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/  FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
            //pstat_jd = conn.prepareStatement("DELETE  /*+ index (t PK_SB_JL_QYSDSSBB_ZB_JD)*/  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            pstat_nd_child = conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
            pstat_jd_child = conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
            ///3.1.ѭ�����ɶ��󼯺ϣ�����������ʹӶ���
            Iterator iterator = qrd.getTableContentList().keySet().iterator();
            String tID;
            while (iterator.hasNext()) {
                qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().
                        get(iterator.next()));
                tID = qrtd.getTableId();
//                Iterator iterator1 = qrtd.getCellContentList().keySet().
//                                     iterator();
                if (this.checkAndGetTableSpace(tID) == 0) { //�����һ���洢�ռ�
                    flag_nd = true;
                    pstat_nd.setString(1, nsrjsjdm);
                    pstat_nd.setString(2, nd);
                    pstat_nd.setString(3, bbqlx);
                    pstat_nd.setString(4, qh);
                    pstat_nd.setString(5, tID);
                    pstat_nd.addBatch();
                    pstat_nd_child.setString(1, nsrjsjdm);
                    pstat_nd_child.setString(2, nd);
                    pstat_nd_child.setString(3, bbqlx);
                    pstat_nd_child.setString(4, qh);
                    pstat_nd_child.setString(5, tID);
                    pstat_nd_child.addBatch();
                } else if (this.checkAndGetTableSpace(tID) == 1) { //����ڶ����洢�ռ�
                    flag_jd = true;
                    pstat_jd.setString(1, nsrjsjdm);
                    pstat_jd.setString(2, nd);
                    pstat_jd.setString(3, bbqlx);
                    pstat_jd.setString(4, qh);
                    pstat_jd.setString(5, tID);
                    pstat_jd.addBatch();
                    pstat_jd_child.setString(1, nsrjsjdm);
                    pstat_jd_child.setString(2, nd);
                    pstat_jd_child.setString(3, bbqlx);
                    pstat_jd_child.setString(4, qh);
                    pstat_jd_child.setString(5, tID);
                    pstat_jd_child.addBatch();
                }
            }
            ///3.2.ִ�����ݲ���
            if (flag_nd) {
                pstat_nd.executeBatch();
                pstat_nd_child.executeBatch();
            }
            if (flag_jd) {
                pstat_jd.executeBatch();
                pstat_jd_child.executeBatch();
            }
            ///3.3.�ر����ݲ���
            pstat_nd.close();
            pstat_jd.close();
            pstat_nd_child.close();
            pstat_jd_child.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
    }


    /**
     * ��ѯ����
     * @param obj ��ѯ����,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @return ��ѯ���,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @exception FrameException ҵ���쳣
     */
    public Object query(Object obj) throws FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        QysdsReportsTableDeclare qrtd = null;
        QysdsReportsItemDeclare qrid = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        CachedRowSet crs = null;
        //1.����У��
        qrd = (QysdsReportsDeclare) obj;
        if (obj == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷������걨������");
        }
        //2.��ʼ��
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            String tid = null;
            String iid = null;
            String yzbs = null;
            ///3.-1.��ѯ������������
            qrd=this.queryDeclareMainOperation(qrd);
            ///3.0.��ѯ����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ start**/
            //sb.append("SELECT /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/ NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                if (QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE.
                    equals(yzbs)) {
                    qrid = new QysdsReportsItemDeclare();
                    qrid.setItemID(crs.getString("HC"));
                    qrid.setItemType(yzbs);
                    qrid.setItemValue(crs.getString("YZ"));
                    qrtd.getCellContentList().put(qrid.getItemID(), qrid);
                }
            }
            crs.close();
            ///3.1.��ѯ�����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ start**/
            //sb.append("SELECT /*+ index (t PK_SB_JL_QYSDSSBB_ZB_JD)*/ NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                if (QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE.
                    equals(yzbs)) {
                    qrid = new QysdsReportsItemDeclare();
                    qrid.setItemID(crs.getString("HC"));
                    qrid.setItemType(yzbs);
                    qrid.setItemValue(crs.getString("YZ"));
                    qrtd.getCellContentList().put(qrid.getItemID(), qrid);
                }
            }
            crs.close();
            ///3.2.��ѯ����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                qrid = new QysdsReportsItemDeclare();
                qrid.setItemID(crs.getString("HC") + "." + crs.getString("ZHS"));
                qrid.setItemType(yzbs);
                qrid.setItemValue(crs.getString("YZ"));
                qrtd.getCellContentList().put(qrid.getItemID(), qrid);
            }
            crs.close();

            ///3.2.��ѯ�����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                qrid = new QysdsReportsItemDeclare();
                qrid.setItemID(crs.getString("HC") + "." + crs.getString("ZHS"));
                qrid.setItemType(yzbs);
                qrid.setItemValue(crs.getString("YZ"));
                qrtd.getCellContentList().put(qrid.getItemID(), qrid);
            }
            crs.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
        return qrd;
    }

    /**
     * ��ѯ�������ݷ���,ֻ����߱����Ա������
     * @param obj ��ѯ����,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @return ��ѯ���,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @exception FrameException ҵ���쳣
     */
    public Object querySingleTable(Object obj) throws FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        QysdsReportsTableDeclare qrtd = null;
        QysdsReportsItemDeclare qrid = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        CachedRowSet crs = null;
        //1.����У��
        qrd = (QysdsReportsDeclare) obj;
        if (obj == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷���ѯ�걨������");
        }
        if (!(qrd.getTableContentList().size() > 0)) {
            throw new FrameException("���뱣������Ϊ�գ��޷���ѯ�걨������");
        }
        //2.��ʼ��
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        ///2.0.�����IDȨ���ֶ�
        String tids = "";
        Iterator iterator = qrd.getTableContentList().keySet().iterator();
        sb = new StringBuffer();
        while (iterator.hasNext()) {
            qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().
                                               get(iterator.
                    next()));
            sb.append("'");
            sb.append(qrtd.getTableId());
            sb.append("'");
            sb.append(",");
        }
        sb.append("'-1'");
        tids = sb.toString();
        //3.ҵ�����
        try {
            String tid = null;
            String iid = null;
            String yzbs = null;
            ///3.-1.��ѯ������������
            qrd=this.queryDeclareMainOperation(qrd);
            ///3.0.��ѯ����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ start**/
            //sb.append("SELECT /*+ index (t PK_SB_JL_QYSDSSBB_ZB_ND)*/ NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_ND t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            sb.append(" AND SBDM IN (");
            sb.append(tids);
            sb.append(")");
            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                if (QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE.
                    equals(yzbs)) {
                    qrid = new QysdsReportsItemDeclare();
                    qrid.setItemID(crs.getString("HC"));
                    qrid.setItemType(yzbs);
                    qrid.setItemValue(crs.getString("YZ"));
                    qrtd.getCellContentList().put(qrid.getItemID(), qrid);
                }
            }
            crs.close();
            ///3.1.��ѯ�����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ start**/
            //sb.append("SELECT /*+ index (t PK_SB_JL_QYSDSSBB_ZB_JD)*/ NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=");
            /**modify by lianglw 20071008 ����ǿ������ end**/
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            sb.append(" AND SBDM IN (");
            sb.append(tids);
            sb.append(")");

            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                if (QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE.
                    equals(yzbs)) {
                    qrid = new QysdsReportsItemDeclare();
                    qrid.setItemID(crs.getString("HC"));
                    qrid.setItemType(yzbs);
                    qrid.setItemValue(crs.getString("YZ"));
                    qrtd.getCellContentList().put(qrid.getItemID(), qrid);
                }
            }
            crs.close();
            ///3.2.��ѯ����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_ND WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            sb.append(" AND SBDM IN (");
            sb.append(tids);
            sb.append(")");

            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                qrid = new QysdsReportsItemDeclare();
                qrid.setItemID(crs.getString("HC") + "." + crs.getString("ZHS"));
                qrid.setItemType(yzbs);
                qrid.setItemValue(crs.getString("YZ"));
                qrtd.getCellContentList().put(qrid.getItemID(), qrid);
            }
            crs.close();

            ///3.3.��ѯ�����걨������
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            sb.append(" AND SBDM IN (");
            sb.append(tids);
            sb.append(")");
            crs = this.idba.executeQuery(sb.toString());
            //
            while (crs.next()) {
                tid = crs.getString("SBDM");
                if (qrd.getTableContentList().get(tid) == null) { //�����������������������������
                    //--�����±�������ö�Ӧֵ
                    qrtd = new QysdsReportsTableDeclare();
                    qrd.getTableContentList().put(tid, qrtd);
                    qrtd.setTableId(tid);
                    qrtd.setTableName(crs.getString("SBBM"));
                    qrtd.setTbblx(crs.getString("TBBLX"));
                } else { //�������������������ȡ��
                    qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().
                           get(tid);
                }
                //--��������������ö�Ӧֵ
                yzbs = crs.getString("YZBS");
                qrid = new QysdsReportsItemDeclare();
                qrid.setItemID(crs.getString("HC") + "." + crs.getString("ZHS"));
                qrid.setItemType(yzbs);
                qrid.setItemValue(crs.getString("YZ"));
                qrtd.getCellContentList().put(qrid.getItemID(), qrid);
            }
            crs.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
        return qrd;

    }

    /**
     * ���淽��
     * @param obj �������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @exception FrameException ҵ���쳣
     */
    public void save(Object obj) throws FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        //1.����У��
        if (obj == null) {
            throw new FrameException("���뱣�����Ϊ�գ�");
        }
        //2.��ʼ��
        qrd = (QysdsReportsDeclare) obj;
        //3.ҵ�����
        try {
            ///3.0.���б��������У��
            if (!qrd.checkValid(0)) {
//                this.loger.write(qrd.getContents(), ILog.LOG_LEVEL_NORMAL);
                throw new FrameException("���뱣������Ƿ���");
            }
            ///3.1.���ȫ����������
            this.delete(qrd);
            ///3.2.���ɵǼ���������
            this.saveDeclareMainOperation(qrd);
            ///3.3.�����걨������
            //modified by Ha Zhengze 20090409 start
            //this.saveDeclareMainData(qrd);
            this.saveDeclareMainDataBatch(qrd);
            //modified by Ha Zhengze 20090409 end
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
    }

    /**
     * ���淽��,ֻ����߱����Ա������
     * @param obj �������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @exception FrameException ҵ���쳣
     */
    public void saveSingleTable(Object obj) throws FrameException {
        //0.�������
        QysdsReportsDeclare qrd = null;
        //1.����У��
        if (obj == null) {
//            this.loger.write(qrd.getContents(), ILog.LOG_LEVEL_NORMAL);
            throw new FrameException("���뱣�����Ϊ�գ�");
        }
        //2.��ʼ��
        qrd = (QysdsReportsDeclare) obj;
        //3.ҵ�����
        try {
            ///3.0.���б��������У��
            if (!qrd.checkValid(0)) {
                throw new FrameException("���뱣������Ƿ���");
            }
            ///3.1.���ȫ����������
            this.deleteSingleTable(qrd);
            ///3.2.���ɵǼ���������
            this.saveDeclareMainOperation(qrd);
            ///3.3.�����걨������
            //modified by Ha zhengze 20090409 start
            //this.saveDeclareMainData(qrd);
            this.saveDeclareMainDataBatch(qrd);
            //modified by Ha zhengze 20090409 end
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
    }
    
    /**
	 * �������״̬��Ϣ��
	 * @param obj ����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param shbz ��˱�־
	 * @throws FrameException
	 * @return true�����³ɹ���false������ʧ��
	 * @author wofei
	 */
	public boolean updateCheckStatus(Object obj,String shbz) throws FrameException{
		
		StringBuffer buffer=new StringBuffer();
		QysdsReportsDeclare report =(QysdsReportsDeclare)obj;
		QysdsReportsTableDeclare table=null;
		HashMap map = (HashMap)report.getTableContentList();
		Iterator it=map.keySet().iterator();
		while(it.hasNext()){
			String key=(String)it.next();
			table=(QysdsReportsTableDeclare)map.get(key);
		}
		
		if(table==null){
			throw new FrameException("�������󣡲�ѯ���״̬ʱ�봫���걨��ID�����飡");
		}
		
		buffer.append(" DELETE FROM SBDB.SB_JL_QYSDSSBB_TABLE_INFO");
		buffer.append(" WHERE NSRJSJDM=");
		buffer.append(StringUtils.getSQLStr(report.getNsrjsjdm()));
		buffer.append(" AND BBQLX=");
		buffer.append(StringUtils.getSQLStr(report.getBbqlx()));
		buffer.append(" AND QH=");
		buffer.append(StringUtils.getSQLStr(report.getQh()));
		buffer.append(" AND SKND=");
		buffer.append(StringUtils.getSQLStr(report.getSknd()));
		buffer.append(" AND SBDM=");
		buffer.append(StringUtils.getSQLStr(table.getTableId()));
		
		this.idba.executeUpdate(buffer.toString());
		buffer.delete(0,buffer.length());
		
		//������״̬Ϊ�գ���ɾ����¼���ٽ��в���
		if("".equals(shbz) || shbz==null){
			return true;
		}
		
		buffer.append(" INSERT INTO SBDB.SB_JL_QYSDSSBB_TABLE_INFO");
		buffer.append(" (NSRJSJDM,SBDM,BBQLX,QH,SKND,SHBZ) VALUES(");
		buffer.append( StringUtils.getSQLStr(report.getNsrjsjdm())+",");
		buffer.append(StringUtils.getSQLStr(table.getTableId())+",");
		buffer.append(StringUtils.getSQLStr(report.getBbqlx())+",");
		buffer.append(StringUtils.getSQLStr(report.getQh())+",");
		buffer.append(StringUtils.getSQLStr(report.getSknd())+",");
		buffer.append(StringUtils.getSQLStr(shbz)+")");
		int rows=this.idba.executeUpdate(buffer.toString());
		
		if(rows>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ��ѯ���״̬
	 * @param obj ��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return ���״̬
	 * @throws FrameException
	 * @author wofei
	 * @throws SQLException 
	 */
	public String queryCheckStatus(Object obj) throws FrameException, SQLException{
		StringBuffer buffer=new StringBuffer();
		QysdsReportsDeclare report =(QysdsReportsDeclare)obj;
		QysdsReportsTableDeclare table=null;
		HashMap map = (HashMap)report.getTableContentList();
		Iterator it=map.keySet().iterator();
		while(it.hasNext()){
			String key=(String)it.next();
			table=(QysdsReportsTableDeclare)map.get(key);
		}
		
		if(table==null){
			throw new FrameException("�������󣡲�ѯ���״̬ʱ�봫���걨��ID�����飡");
		}
		
		report.getNsrjsjdm();
		buffer.append(" SELECT SHBZ FROM SBDB.SB_JL_QYSDSSBB_TABLE_INFO");
		buffer.append(" WHERE NSRJSJDM=");
		buffer.append(StringUtils.getSQLStr(report.getNsrjsjdm()));
		buffer.append(" AND BBQLX=");
		buffer.append(StringUtils.getSQLStr(report.getBbqlx()));
		buffer.append(" AND QH=");
		buffer.append(StringUtils.getSQLStr(report.getQh()));
		buffer.append(" AND SKND=");
		buffer.append(StringUtils.getSQLStr(report.getSknd()));
		buffer.append(" AND SBDM=");
		buffer.append(StringUtils.getSQLStr(table.getTableId()));
		
		CachedRowSet crs=this.idba.executeQuery(buffer.toString());
		
		while(crs.next()){
			return crs.getString("SHBZ");
		}
		
		return "";
	}
	
    /**
     * ��ѯ��ҵ����˰�걨������
     * @param qrd QysdsReportsDeclare ��ڲ���
     * @return QysdsReportsDeclare ���ڲ���
     * @throws FrameException ϵͳ�쳣
     */
    private QysdsReportsDeclare queryDeclareMainOperation(QysdsReportsDeclare
            qrd) throws
            FrameException {
        //0.�������
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        CachedRowSet crs = null;
        //1.����У��
        if (qrd == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷������걨������");
        }
        //2.��ʼ��
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.��ѯ�걨����
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,TO_CHAR(SBRQ,'yyyymmdd') SBRQ,SBBBBH,TO_CHAR(SKSSSQQ,'yyyymmdd') SKSSSQQ,TO_CHAR(SKSSSQZ,'yyyymmdd') SKSSSQZ,SHBZ,SWJSJDM,SWJGZZJGDM,CJR,TO_CHAR(CJSJ,'yyyymmdd') CJSJ,LRR,TO_CHAR(LRSJ,'yyyymmdd') LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_CZZB WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            crs = this.idba.executeQuery(sb.toString());
            Timestamp  date = null;
            if (crs.next()) {
                qrd.setNsrjsjdm(crs.getString("NSRJSJDM"));
                qrd.setNsrmc(crs.getString("NSRMC"));
                qrd.setBbqlx(crs.getString("BBQLX"));
                qrd.setQh(crs.getString("QH"));
                qrd.setSknd(crs.getString("SKND"));
                date=DateUtils.getDateTime(crs.getString("SBRQ"));
                qrd.setSbrq(date);
                qrd.setVersion(crs.getString("SBBBBH"));
                date=DateUtils.getDateTime(crs.getString("SKSSSQQ"));
                qrd.setSkssksrq(date);
                date=DateUtils.getDateTime(crs.getString("SKSSSQZ"));
                qrd.setSkssjsrq(date);
                qrd.setSwjsjdm(crs.getString("SWJSJDM"));
                qrd.setSwjgzzjgdm(crs.getString("SWJGZZJGDM"));
                qrd.setLrr(crs.getString("LRR"));
                date=DateUtils.getDateTime(crs.getString("LRSJ"));
                qrd.setLrrq(date);
                qrd.setCjr(crs.getString("CJR"));
                date=DateUtils.getDateTime(crs.getString("CJSJ"));
                qrd.setCjrq(date);
                qrd.setQxdm(crs.getString("QXDM"));
                if (QysdsReportsConstants.CREPORTS_CHECK_QYSDS_PASS.equals(crs.
                        getString("SHBZ"))) {
                    qrd.setCheckFlag(true);
                } else {
                    qrd.setCheckFlag(false);
                }
                crs.close();
            } else {
                crs.close();
                //throw new FrameException("������걨�����ݲ�ѯ�������޷��ҵ���¼");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
        return qrd;
    }


    /**
     * �����걨�������ݣ������������������и���
     * @param qrd �������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @return QysdsReportsDeclare ������ϵĶ���
     * @exception FrameException ҵ���쳣
     */
    private QysdsReportsDeclare saveDeclareMainOperation(QysdsReportsDeclare
            qrd) throws
            FrameException {
        //0.�������
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        CachedRowSet crs = null;
        //1.����У��
        if (qrd == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷������걨������");
        }
        //2.��ʼ��
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.��ѯ�Ƿ���������걨����
            sb = new StringBuffer();
            sb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,SKSSSQQ,SKSSSQZ,SHBZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_CZZB WHERE NSRJSJDM=");
            sb.append(StringUtils.getSQLStr(nsrjsjdm));
            sb.append(" AND BBQLX=");
            sb.append(StringUtils.getSQLStr(bbqlx));
            sb.append(" AND QH=");
            sb.append(StringUtils.getSQLStr(qh));
            sb.append(" AND SKND=");
            sb.append(StringUtils.getSQLStr(nd));
            crs = this.idba.executeQuery(sb.toString());
            ///3.2.����3.1.�Ĳ�ѯ��������Ƿ������걨������
            if (crs.next()) { //������ִ�и���
//                this.loger.write("���β������걨�����ݣ�ִ�и��²�����" + nsrjsjdm,
//                                 ILog.LOG_LEVEL_DEBUG_NORMAL);
                sb = new StringBuffer();
                sb.append("UPDATE SBDB.SB_JL_QYSDSSBB_CZZB");
                sb.append(" SET NSRMC=");
                sb.append(StringUtils.getSQLStr(qrd.getNsrmc()));
                sb.append(",SBRQ=");
                sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSbrq())));
                sb.append(",SBBBBH=");
                sb.append(StringUtils.getSQLStr(qrd.getVersion()));
                sb.append(",SKSSSQQ=");
                sb.append(StringUtils.getSQLDate(
                        DateUtils.getDate(qrd.getSkssksrq())));
                sb.append(",SKSSSQZ=");
                sb.append(StringUtils.getSQLDate(
                        DateUtils.getDate(qrd.getSkssjsrq())));
                sb.append(",SHBZ=");
                sb.append(StringUtils.getSQLStr(QysdsReportsConstants.
                                                CREPORTS_CHECK_QYSDS_NOPASS));
                sb.append(",SWJSJDM=");
                sb.append(StringUtils.getSQLStr(qrd.getSwjsjdm()));
                sb.append(",SWJGZZJGDM=");
                sb.append(StringUtils.getSQLStr(qrd.getSwjgzzjgdm()));
                sb.append(",LRR=");
                sb.append(StringUtils.getSQLStr(qrd.getLrr()));
                sb.append(",LRSJ=sysdate");
                sb.append(",QXDM=");
                sb.append(StringUtils.getSQLStr(qrd.getQxdm()));
                sb.append(" WHERE NSRJSJDM=");
                sb.append(StringUtils.getSQLStr(nsrjsjdm));
                sb.append(" AND BBQLX=");
                sb.append(StringUtils.getSQLStr(bbqlx));
                sb.append(" AND QH=");
                sb.append(StringUtils.getSQLStr(qh));
                sb.append(" AND SKND=");
                sb.append(StringUtils.getSQLStr(nd));
                this.idba.executeUpdate(sb.toString());
            } else { //������ִ�в���
//                this.loger.write("���β������걨�����ݣ�ִ�в��������" + nsrjsjdm,
//                                 ILog.LOG_LEVEL_DEBUG_NORMAL);
                sb = new StringBuffer();
                sb.append("INSERT INTO SBDB.SB_JL_QYSDSSBB_CZZB (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,SKSSSQQ,SKSSSQZ,SHBZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (");
                sb.append(StringUtils.getSQLStr(nsrjsjdm));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getNsrmc()));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(bbqlx));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qh));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(nd));
                sb.append(",");
                sb.append(StringUtils.getSQLDate(DateUtils.getDate(qrd.getSbrq())));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getVersion()));
                sb.append(",");
                sb.append(StringUtils.getSQLDate(
                        DateUtils.getDate(qrd.getSkssksrq())));
                sb.append(",");
                sb.append(StringUtils.getSQLDate(
                        DateUtils.getDate(qrd.getSkssjsrq())));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(QysdsReportsConstants.
                                                CREPORTS_CHECK_QYSDS_NOPASS));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getSwjsjdm()));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getSwjgzzjgdm()));
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getLrr()));
                sb.append(",sysdate");
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getLrr()));
                sb.append(",sysdate");
                sb.append(",");
                sb.append(StringUtils.getSQLStr(qrd.getQxdm()));
                sb.append(")");
                this.idba.executeUpdate(sb.toString());
            }
            crs.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
        return qrd;
    }


    /**
     * �����걨�������ݣ������������������и���
     * @param qrd �������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @return QysdsReportsDeclare ������ϵĶ���
     * @exception FrameException ҵ���쳣
     */
    private QysdsReportsDeclare saveDeclareMainData(QysdsReportsDeclare qrd) throws
            FrameException {
        //0.�������
        QysdsReportsTableDeclare qrtd = null;
        QysdsReportsItemDeclare qrid = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        Connection conn;
        PreparedStatement pstat_nd;
        PreparedStatement pstat_nd_child;
        PreparedStatement pstat_jd;
        PreparedStatement pstat_jd_child;
        //1.����У��
        if (qrd == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷������걨������");
        }
        //2.��ʼ��
        conn = this.idba.getConn();
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.��ʼ�����ݲ���
            boolean flag_nd = false;
            boolean flag_nd_child = false;
            boolean flag_jd = false;
            boolean flag_jd_child = false;
            pstat_nd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_ND (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstat_jd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstat_nd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_ND (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstat_jd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ///3.1.ѭ�����ɶ��󼯺ϣ�����������ʹӶ���
            Iterator iterator = qrd.getTableContentList().keySet().iterator();
            String tID;
            String tType;
            String iID; //����
            String iType; //������
            String iValue; ///��ֵ
            while (iterator.hasNext()) {
                qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().
                        get(iterator.next()));
                tID = qrtd.getTableId();
                tType = qrtd.getTbblx();
                Iterator iterator1 = qrtd.getCellContentList().keySet().
                                     iterator();
                QysdsReportsItemDeclare qid;
                ArrayList tmpList_1 = new ArrayList(); //��ʱ���ڵ��¼��
                ArrayList tmpList_2 = new ArrayList();
                if (this.checkAndGetTableSpace(tID) == 0) { //�����һ���洢�ռ�
                    while (iterator1.hasNext()) {
                        qid = (QysdsReportsItemDeclare) (qrtd.
                                getCellContentList().
                                get(iterator1.next()));
                        iID = qid.getItemID();
                        iValue = qid.getItemValue();
                        String[] tmpiID = ReportsUtils.splitItemID(iID); //��ȡ��ֵ�id
                        if (tmpiID == null) { //����û���ӽڵ㴦��
                            flag_nd = true;
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                            //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                            pstat_nd.setString(1, nsrjsjdm);
                            pstat_nd.setString(2,
                                               StringUtils.killNull(qrd.
                                    getNsrmc()));
                            pstat_nd.setString(3, bbqlx);
                            pstat_nd.setString(4, qh);
                            pstat_nd.setString(5, nd);
                            pstat_nd.setTimestamp(6, qrd.getSbrq());
                            pstat_nd.setString(7, qrd.getVersion());
                            pstat_nd.setString(8, tType);
                            pstat_nd.setString(9, qrtd.getTableId());
                            pstat_nd.setString(10,
                                               StringUtils.killNull(qrtd.
                                    getTableName()));
                            pstat_nd.setString(11, iID);
                            pstat_nd.setTimestamp(12, qrd.getSkssksrq());
                            pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
                            pstat_nd.setString(14,
                                               QysdsReportsConstants.
                                               CREPORTS_ITEM_FIELD_FLAG_SINGLELINE); //��ʾΪ����
                            pstat_nd.setString(15, iValue);
                            pstat_nd.setString(16, qrd.getSwjsjdm());
                            pstat_nd.setString(17, qrd.getSwjgzzjgdm());
                            pstat_nd.setString(18, qrd.getCjr());
                            pstat_nd.setTimestamp(19, qrd.getCjrq());
                            pstat_nd.setString(20, qrd.getLrr());
                            pstat_nd.setTimestamp(21, qrd.getLrrq());
                            pstat_nd.setString(22, qrd.getQxdm());
                            pstat_nd.addBatch();
                        } else { //�������ӽڵ㴦��
                            if (!tmpList_1.contains(tmpiID[0])) { //����Ѿ����ɹ����ڵ���ʲô��������û�����������ɸ��ڵ�
                                //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                                //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                                flag_nd = true;
                                tmpList_1.add(tmpiID[0]);
                                pstat_nd.setString(1, nsrjsjdm);
                                pstat_nd.setString(2,
                                        StringUtils.killNull(qrd.getNsrmc()));
                                pstat_nd.setString(3, bbqlx);
                                pstat_nd.setString(4, qh);
                                pstat_nd.setString(5, nd);
                                pstat_nd.setTimestamp(6, qrd.getSbrq());
                                pstat_nd.setString(7, qrd.getVersion());
                                pstat_nd.setString(8, tType);
                                pstat_nd.setString(9, qrtd.getTableId());
                                pstat_nd.setString(10,
                                        StringUtils.killNull(qrtd.getTableName()));
                                pstat_nd.setString(11, tmpiID[0]);
                                pstat_nd.setTimestamp(12, qrd.getSkssksrq());
                                pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
                                pstat_nd.setString(14,
                                        QysdsReportsConstants.
                                        CREPORTS_ITEM_FIELD_FLAG_MULITLINES); //��ʾΪ����
                                pstat_nd.setString(15, ""); //��Ϊ�������򱾼�¼��ֵΪ��
                                pstat_nd.setString(16, qrd.getSwjsjdm());
                                pstat_nd.setString(17, qrd.getSwjgzzjgdm());
                                pstat_nd.setString(18, qrd.getCjr());
                                pstat_nd.setTimestamp(19, qrd.getCjrq());
                                pstat_nd.setString(20, qrd.getLrr());
                                pstat_nd.setTimestamp(21, qrd.getLrrq());
                                pstat_nd.setString(22, qrd.getQxdm());
                                pstat_nd.addBatch();
                            }
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS
                            //,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ
                            flag_nd_child = true;
                            pstat_nd_child.setString(1, nsrjsjdm);
                            pstat_nd_child.setString(2,
                                    StringUtils.killNull(qrd.getNsrmc()));
                            pstat_nd_child.setString(3, bbqlx);
                            pstat_nd_child.setString(4, qh);
                            pstat_nd_child.setString(5, nd);
                            pstat_nd_child.setString(6, qrd.getVersion());
                            pstat_nd_child.setString(7, tType);
                            pstat_nd_child.setString(8, qrtd.getTableId());
                            pstat_nd_child.setString(9,
                                    StringUtils.killNull(qrtd.getTableName()));
                            pstat_nd_child.setString(10, tmpiID[0]);
                            pstat_nd_child.setString(11, tmpiID[1]);
                            pstat_nd_child.setString(12, "");
                            pstat_nd_child.setString(13, iValue); //��Ϊ�������򱾼�¼��ֵΪ�ӽڵ���ֵ
                            pstat_nd_child.setString(14, qrd.getSwjgzzjgdm());
                            pstat_nd_child.setString(15, qrd.getCjr());
                            pstat_nd_child.setTimestamp(16, qrd.getCjrq());
                            pstat_nd_child.setString(17, qrd.getLrr());
                            pstat_nd_child.setTimestamp(18, qrd.getLrrq());
                            pstat_nd_child.addBatch();
                        }
                    }
                } else if (this.checkAndGetTableSpace(tID) == 1) { //����ڶ����洢�ռ�
                    while (iterator1.hasNext()) {
                        qid = (QysdsReportsItemDeclare) (qrtd.
                                getCellContentList().
                                get(iterator1.next()));
                        iID = qid.getItemID();
                        iValue = qid.getItemValue();
                        String[] tmpiID = ReportsUtils.splitItemID(iID); //��ȡ��ֵ�id
                        if (tmpiID == null) { //����û���ӽڵ㴦��
                            flag_jd = true;
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                            //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                            pstat_jd.setString(1, nsrjsjdm);
                            pstat_jd.setString(2,
                                               StringUtils.killNull(qrd.
                                    getNsrmc()));
                            pstat_jd.setString(3, bbqlx);
                            pstat_jd.setString(4, qh);
                            pstat_jd.setString(5, nd);
                            pstat_jd.setTimestamp(6, qrd.getSbrq());
                            pstat_jd.setString(7, qrd.getVersion());
                            pstat_jd.setString(8, tType);
                            pstat_jd.setString(9, qrtd.getTableId());
                            pstat_jd.setString(10,
                                               StringUtils.killNull(qrtd.
                                    getTableName()));
                            pstat_jd.setString(11, iID);
                            pstat_jd.setTimestamp(12, qrd.getSkssksrq());
                            pstat_jd.setTimestamp(13, qrd.getSkssjsrq());
                            pstat_jd.setString(14,
                                               QysdsReportsConstants.
                                               CREPORTS_ITEM_FIELD_FLAG_SINGLELINE); //��ʾΪ����
                            pstat_jd.setString(15, iValue);
                            pstat_jd.setString(16, qrd.getSwjsjdm());
                            pstat_jd.setString(17, qrd.getSwjgzzjgdm());
                            pstat_jd.setString(18, qrd.getCjr());
                            pstat_jd.setTimestamp(19, qrd.getCjrq());
                            pstat_jd.setString(20, qrd.getLrr());
                            pstat_jd.setTimestamp(21, qrd.getLrrq());
                            pstat_jd.setString(22, qrd.getQxdm());
                            pstat_jd.addBatch();
                        } else { //�������ӽڵ㴦��
                            if (!tmpList_2.contains(tmpiID[0])) { //����Ѿ����ɹ����ڵ���ʲô��������û�����������ɸ��ڵ�
                                //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                                //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                                flag_jd = true;
                                tmpList_2.add(tmpiID[0]);
                                pstat_jd.setString(1, nsrjsjdm);
                                pstat_jd.setString(2,
                                        StringUtils.killNull(qrd.getNsrmc()));
                                pstat_jd.setString(3, bbqlx);
                                pstat_jd.setString(4, qh);
                                pstat_jd.setString(5, nd);
                                pstat_jd.setTimestamp(6, qrd.getSbrq());
                                pstat_jd.setString(7, qrd.getVersion());
                                pstat_jd.setString(8, tType);
                                pstat_jd.setString(9, qrtd.getTableId());
                                pstat_jd.setString(10,
                                        StringUtils.killNull(qrtd.getTableName()));
                                pstat_jd.setString(11, tmpiID[0]);
                                pstat_jd.setTimestamp(12, qrd.getSkssksrq());
                                pstat_jd.setTimestamp(13, qrd.getSkssjsrq());
                                pstat_jd.setString(14,
                                        QysdsReportsConstants.
                                        CREPORTS_ITEM_FIELD_FLAG_MULITLINES); //��ʾΪ����
                                pstat_jd.setString(15, ""); //��Ϊ�������򱾼�¼��ֵΪ��
                                pstat_jd.setString(16, qrd.getSwjsjdm());
                                pstat_jd.setString(17, qrd.getSwjgzzjgdm());
                                pstat_jd.setString(18, qrd.getCjr());
                                pstat_jd.setTimestamp(19, qrd.getCjrq());
                                pstat_jd.setString(20, qrd.getLrr());
                                pstat_jd.setTimestamp(21, qrd.getLrrq());
                                pstat_jd.setString(22, qrd.getQxdm());
                                pstat_jd.addBatch();
                            }
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS
                            //,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ
                            flag_jd_child = true;
                            pstat_jd_child.setString(1, nsrjsjdm);
                            pstat_jd_child.setString(2,
                                    StringUtils.killNull(qrd.getNsrmc()));
                            pstat_jd_child.setString(3, bbqlx);
                            pstat_jd_child.setString(4, qh);
                            pstat_jd_child.setString(5, nd);
                            pstat_jd_child.setString(6, qrd.getVersion());
                            pstat_jd_child.setString(7, tType);
                            pstat_jd_child.setString(8, qrtd.getTableId());
                            pstat_jd_child.setString(9,
                                    StringUtils.killNull(qrtd.getTableName()));
                            pstat_jd_child.setString(10, tmpiID[0]);
                            pstat_jd_child.setString(11, tmpiID[1]);
                            pstat_jd_child.setString(12, "");
                            pstat_jd_child.setString(13, iValue); //��Ϊ�������򱾼�¼��ֵΪ�ӽڵ���ֵ
                            pstat_jd_child.setString(14, qrd.getSwjgzzjgdm());
                            pstat_jd_child.setString(15, qrd.getCjr());
                            pstat_jd_child.setTimestamp(16, qrd.getCjrq());
                            pstat_jd_child.setString(17, qrd.getLrr());
                            pstat_jd_child.setTimestamp(18, qrd.getLrrq());
                            pstat_jd_child.addBatch();
                        }
                    }
                }
            }
            ///3.2.ִ�����ݲ���
            if (flag_nd) {
                pstat_nd.executeBatch();
            }
            if (flag_nd_child) {
                pstat_nd_child.executeBatch();
            }
            if (flag_jd) {
                pstat_jd.executeBatch();
            }
            if (flag_jd_child) {
                pstat_jd_child.executeBatch();
            }
            ///3.3.�ر����ݲ���
            pstat_nd.close();
            pstat_jd.close();
            pstat_nd_child.close();
            pstat_jd_child.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
        return qrd;
    }
    



    /**
     * Add by Ha Zhengze 20090409 ���Ӱ��걨���������洦����
     * �����걨�������ݣ������������������и��£����걨�����������������
     * @param qrd �������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
     * @return QysdsReportsDeclare ������ϵĶ���
     * @exception FrameException ҵ���쳣
     */
    private QysdsReportsDeclare saveDeclareMainDataBatch(QysdsReportsDeclare qrd) throws
            FrameException {
        //0.�������
        QysdsReportsTableDeclare qrtd = null;
        QysdsReportsItemDeclare qrid = null;
        StringBuffer sb;
        String nsrjsjdm; //��˰�˼��������
        String nd; //���
        String bbqlx; //����������
        String qh; //�ں�
        Connection conn;
        PreparedStatement pstat_nd;
        PreparedStatement pstat_nd_child;
        PreparedStatement pstat_jd;
        PreparedStatement pstat_jd_child;
        //1.����У��
        if (qrd == null) {
            throw new FrameException("���뱣�����Ϊ�գ��޷������걨������");
        }
        //2.��ʼ��
        conn = this.idba.getConn();
        nsrjsjdm = qrd.getNsrjsjdm();
        nd = qrd.getSknd();
        bbqlx = qrd.getBbqlx();
        qh = qrd.getQh();
        //3.ҵ�����
        try {
            ///3.0.��ʼ�����ݲ���
            boolean flag_nd = false;
            boolean flag_nd_child = false;
            boolean flag_jd = false;
            boolean flag_jd_child = false;
            //Deleted by Ha zhengze 20090409 start
            /**
            pstat_nd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_ND (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstat_jd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstat_nd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_ND (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstat_jd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            */
            //Deleted by Ha zhengze 20090409 end
            ///3.1.ѭ�����ɶ��󼯺ϣ�����������ʹӶ���
            Iterator iterator = qrd.getTableContentList().keySet().iterator();
            String tID;
            String tType;
            String iID; //����
            String iType; //������
            String iValue; ///��ֵ
            while (iterator.hasNext()) {
            	//Addied by Ha zhengze 20090409 start
            	//����prepareStatement����
                pstat_nd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_ND (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pstat_jd = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pstat_nd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_ND (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pstat_jd_child = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                //Addied by Ha zhengze 20090409 end
                //����������
                qrtd = (QysdsReportsTableDeclare) (qrd.getTableContentList().
                        get(iterator.next()));
                tID = qrtd.getTableId();
                tType = qrtd.getTbblx();
                Iterator iterator1 = qrtd.getCellContentList().keySet().
                                     iterator();
                QysdsReportsItemDeclare qid;
                ArrayList tmpList_1 = new ArrayList(); //��ʱ���ڵ��¼��
                ArrayList tmpList_2 = new ArrayList();
                if (this.checkAndGetTableSpace(tID) == 0) { //�����һ���洢�ռ�
                    while (iterator1.hasNext()) {
                        qid = (QysdsReportsItemDeclare) (qrtd.
                                getCellContentList().
                                get(iterator1.next()));
                        iID = qid.getItemID();
                        iValue = qid.getItemValue();
                        String[] tmpiID = ReportsUtils.splitItemID(iID); //��ȡ��ֵ�id
                        if (tmpiID == null) { //����û���ӽڵ㴦��
                            flag_nd = true;
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                            //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                            pstat_nd.setString(1, nsrjsjdm);
                            pstat_nd.setString(2,
                                               StringUtils.killNull(qrd.
                                    getNsrmc()));
                            pstat_nd.setString(3, bbqlx);
                            pstat_nd.setString(4, qh);
                            pstat_nd.setString(5, nd);
                            pstat_nd.setTimestamp(6, qrd.getSbrq());
                            pstat_nd.setString(7, qrd.getVersion());
                            pstat_nd.setString(8, tType);
                            pstat_nd.setString(9, qrtd.getTableId());
                            pstat_nd.setString(10,
                                               StringUtils.killNull(qrtd.
                                    getTableName()));
                            pstat_nd.setString(11, iID);
                            pstat_nd.setTimestamp(12, qrd.getSkssksrq());
                            pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
                            pstat_nd.setString(14,
                                               QysdsReportsConstants.
                                               CREPORTS_ITEM_FIELD_FLAG_SINGLELINE); //��ʾΪ����
                            pstat_nd.setString(15, iValue);
                            pstat_nd.setString(16, qrd.getSwjsjdm());
                            pstat_nd.setString(17, qrd.getSwjgzzjgdm());
                            pstat_nd.setString(18, qrd.getCjr());
                            pstat_nd.setTimestamp(19, qrd.getCjrq());
                            pstat_nd.setString(20, qrd.getLrr());
                            pstat_nd.setTimestamp(21, qrd.getLrrq());
                            pstat_nd.setString(22, qrd.getQxdm());
                            pstat_nd.addBatch();
                        } else { //�������ӽڵ㴦��
                            if (!tmpList_1.contains(tmpiID[0])) { //����Ѿ����ɹ����ڵ���ʲô��������û�����������ɸ��ڵ�
                                //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                                //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                                flag_nd = true;
                                tmpList_1.add(tmpiID[0]);
                                pstat_nd.setString(1, nsrjsjdm);
                                pstat_nd.setString(2,
                                        StringUtils.killNull(qrd.getNsrmc()));
                                pstat_nd.setString(3, bbqlx);
                                pstat_nd.setString(4, qh);
                                pstat_nd.setString(5, nd);
                                pstat_nd.setTimestamp(6, qrd.getSbrq());
                                pstat_nd.setString(7, qrd.getVersion());
                                pstat_nd.setString(8, tType);
                                pstat_nd.setString(9, qrtd.getTableId());
                                pstat_nd.setString(10,
                                        StringUtils.killNull(qrtd.getTableName()));
                                pstat_nd.setString(11, tmpiID[0]);
                                pstat_nd.setTimestamp(12, qrd.getSkssksrq());
                                pstat_nd.setTimestamp(13, qrd.getSkssjsrq());
                                pstat_nd.setString(14,
                                        QysdsReportsConstants.
                                        CREPORTS_ITEM_FIELD_FLAG_MULITLINES); //��ʾΪ����
                                pstat_nd.setString(15, ""); //��Ϊ�������򱾼�¼��ֵΪ��
                                pstat_nd.setString(16, qrd.getSwjsjdm());
                                pstat_nd.setString(17, qrd.getSwjgzzjgdm());
                                pstat_nd.setString(18, qrd.getCjr());
                                pstat_nd.setTimestamp(19, qrd.getCjrq());
                                pstat_nd.setString(20, qrd.getLrr());
                                pstat_nd.setTimestamp(21, qrd.getLrrq());
                                pstat_nd.setString(22, qrd.getQxdm());
                                pstat_nd.addBatch();
                            }
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS
                            //,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ
                            flag_nd_child = true;
                            pstat_nd_child.setString(1, nsrjsjdm);
                            pstat_nd_child.setString(2,
                                    StringUtils.killNull(qrd.getNsrmc()));
                            pstat_nd_child.setString(3, bbqlx);
                            pstat_nd_child.setString(4, qh);
                            pstat_nd_child.setString(5, nd);
                            pstat_nd_child.setString(6, qrd.getVersion());
                            pstat_nd_child.setString(7, tType);
                            pstat_nd_child.setString(8, qrtd.getTableId());
                            pstat_nd_child.setString(9,
                                    StringUtils.killNull(qrtd.getTableName()));
                            pstat_nd_child.setString(10, tmpiID[0]);
                            pstat_nd_child.setString(11, tmpiID[1]);
                            pstat_nd_child.setString(12, "");
                            pstat_nd_child.setString(13, iValue); //��Ϊ�������򱾼�¼��ֵΪ�ӽڵ���ֵ
                            pstat_nd_child.setString(14, qrd.getSwjgzzjgdm());
                            pstat_nd_child.setString(15, qrd.getCjr());
                            pstat_nd_child.setTimestamp(16, qrd.getCjrq());
                            pstat_nd_child.setString(17, qrd.getLrr());
                            pstat_nd_child.setTimestamp(18, qrd.getLrrq());
                            pstat_nd_child.addBatch();
                        }
                    }
                } else if (this.checkAndGetTableSpace(tID) == 1) { //����ڶ����洢�ռ�
                    while (iterator1.hasNext()) {
                        qid = (QysdsReportsItemDeclare) (qrtd.
                                getCellContentList().
                                get(iterator1.next()));
                        iID = qid.getItemID();
                        iValue = qid.getItemValue();
                        String[] tmpiID = ReportsUtils.splitItemID(iID); //��ȡ��ֵ�id
                        if (tmpiID == null) { //����û���ӽڵ㴦��
                            flag_jd = true;
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                            //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                            pstat_jd.setString(1, nsrjsjdm);
                            pstat_jd.setString(2,
                                               StringUtils.killNull(qrd.
                                    getNsrmc()));
                            pstat_jd.setString(3, bbqlx);
                            pstat_jd.setString(4, qh);
                            pstat_jd.setString(5, nd);
                            pstat_jd.setTimestamp(6, qrd.getSbrq());
                            pstat_jd.setString(7, qrd.getVersion());
                            pstat_jd.setString(8, tType);
                            pstat_jd.setString(9, qrtd.getTableId());
                            pstat_jd.setString(10,
                                               StringUtils.killNull(qrtd.
                                    getTableName()));
                            pstat_jd.setString(11, iID);
                            pstat_jd.setTimestamp(12, qrd.getSkssksrq());
                            pstat_jd.setTimestamp(13, qrd.getSkssjsrq());
                            pstat_jd.setString(14,
                                               QysdsReportsConstants.
                                               CREPORTS_ITEM_FIELD_FLAG_SINGLELINE); //��ʾΪ����
                            pstat_jd.setString(15, iValue);
                            pstat_jd.setString(16, qrd.getSwjsjdm());
                            pstat_jd.setString(17, qrd.getSwjgzzjgdm());
                            pstat_jd.setString(18, qrd.getCjr());
                            pstat_jd.setTimestamp(19, qrd.getCjrq());
                            pstat_jd.setString(20, qrd.getLrr());
                            pstat_jd.setTimestamp(21, qrd.getLrrq());
                            pstat_jd.setString(22, qrd.getQxdm());
                            pstat_jd.addBatch();
                        } else { //�������ӽڵ㴦��
                            if (!tmpList_2.contains(tmpiID[0])) { //����Ѿ����ɹ����ڵ���ʲô��������û�����������ɸ��ڵ�
                                //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC
                                //,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM
                                flag_jd = true;
                                tmpList_2.add(tmpiID[0]);
                                pstat_jd.setString(1, nsrjsjdm);
                                pstat_jd.setString(2,
                                        StringUtils.killNull(qrd.getNsrmc()));
                                pstat_jd.setString(3, bbqlx);
                                pstat_jd.setString(4, qh);
                                pstat_jd.setString(5, nd);
                                pstat_jd.setTimestamp(6, qrd.getSbrq());
                                pstat_jd.setString(7, qrd.getVersion());
                                pstat_jd.setString(8, tType);
                                pstat_jd.setString(9, qrtd.getTableId());
                                pstat_jd.setString(10,
                                        StringUtils.killNull(qrtd.getTableName()));
                                pstat_jd.setString(11, tmpiID[0]);
                                pstat_jd.setTimestamp(12, qrd.getSkssksrq());
                                pstat_jd.setTimestamp(13, qrd.getSkssjsrq());
                                pstat_jd.setString(14,
                                        QysdsReportsConstants.
                                        CREPORTS_ITEM_FIELD_FLAG_MULITLINES); //��ʾΪ����
                                pstat_jd.setString(15, ""); //��Ϊ�������򱾼�¼��ֵΪ��
                                pstat_jd.setString(16, qrd.getSwjsjdm());
                                pstat_jd.setString(17, qrd.getSwjgzzjgdm());
                                pstat_jd.setString(18, qrd.getCjr());
                                pstat_jd.setTimestamp(19, qrd.getCjrq());
                                pstat_jd.setString(20, qrd.getLrr());
                                pstat_jd.setTimestamp(21, qrd.getLrrq());
                                pstat_jd.setString(22, qrd.getQxdm());
                                pstat_jd.addBatch();
                            }
                            //NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS
                            //,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ
                            flag_jd_child = true;
                            pstat_jd_child.setString(1, nsrjsjdm);
                            pstat_jd_child.setString(2,
                                    StringUtils.killNull(qrd.getNsrmc()));
                            pstat_jd_child.setString(3, bbqlx);
                            pstat_jd_child.setString(4, qh);
                            pstat_jd_child.setString(5, nd);
                            pstat_jd_child.setString(6, qrd.getVersion());
                            pstat_jd_child.setString(7, tType);
                            pstat_jd_child.setString(8, qrtd.getTableId());
                            pstat_jd_child.setString(9,
                                    StringUtils.killNull(qrtd.getTableName()));
                            pstat_jd_child.setString(10, tmpiID[0]);
                            pstat_jd_child.setString(11, tmpiID[1]);
                            pstat_jd_child.setString(12, "");
                            pstat_jd_child.setString(13, iValue); //��Ϊ�������򱾼�¼��ֵΪ�ӽڵ���ֵ
                            pstat_jd_child.setString(14, qrd.getSwjgzzjgdm());
                            pstat_jd_child.setString(15, qrd.getCjr());
                            pstat_jd_child.setTimestamp(16, qrd.getCjrq());
                            pstat_jd_child.setString(17, qrd.getLrr());
                            pstat_jd_child.setTimestamp(18, qrd.getLrrq());
                            pstat_jd_child.addBatch();
                        }
                    }
                }
                //Addied by Ha Zhengze 20090409 start
                ///3.2.ִ�����ݲ���
                if (flag_nd) {
                    pstat_nd.executeBatch();
                }
                if (flag_nd_child) {
                    pstat_nd_child.executeBatch();
                }
                if (flag_jd) {
                    pstat_jd.executeBatch();
                }
                if (flag_jd_child) {
                    pstat_jd_child.executeBatch();
                }
                ///3.3.�ر����ݲ���
                pstat_nd.close();
                pstat_jd.close();
                pstat_nd_child.close();
                pstat_jd_child.close();
                //Addied by Ha Zhengze 20090409 end
            }

            //Deleteded by Ha Zhengze 20090409 start
            ///3.2.ִ�����ݲ���
            /**
            if (flag_nd) {
                pstat_nd.executeBatch();
            }
            if (flag_nd_child) {
                pstat_nd_child.executeBatch();
            }
            if (flag_jd) {
                pstat_jd.executeBatch();
            }
            if (flag_jd_child) {
                pstat_jd_child.executeBatch();
            }
            ///3.3.�ر����ݲ���
            pstat_nd.close();
            pstat_jd.close();
            pstat_nd_child.close();
            pstat_jd_child.close();
            */
            //Deleteded by Ha Zhengze 20090409 end
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException(e.getMessage());
        }
        //99.����ֵ
        return qrd;
    }

    /**
     * ��ȡ��ǰ���Ӧ����洢����
     * @param tid String �� ID
     * @return String �洢λ��
     * @throws FrameException Ӧ���쳣
     */
    private String getZbTableName(String tid) throws FrameException {
        String result = "";
        int tmp = this.checkAndGetTableSpace(tid);
        if (tmp == 0) {
            result = QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND_TABLENAME;
        } else if (tmp == 1) {
            result = QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_JD_TABLENAME;
        } else {
            throw new FrameException("�޷��ҵ���ǰ�������洢�ռ䣡����tid=" + tid);
        }
        return result;
    }

    /**
     * ��ȡ��ǰ���Ӧ�ӱ�洢����
     * @param tid String �� ID
     * @return String �洢λ��
     * @throws FrameException Ӧ���쳣
     */
    private String getCbTableName(String tid) throws FrameException {
        String result = "";
        int tmp = this.checkAndGetTableSpace(tid);
        if (tmp == 0) {
            result = QysdsReportsConstants.
                     CREPORTS_TABLEIDS_QYSDS_ND_CHILDTABLENAME;
        } else if (tmp == 1) {
            result = QysdsReportsConstants.
                     CREPORTS_TABLEIDS_QYSDS_JD_CHILDTABLENAME;
        } else {
            throw new FrameException("�޷��ҵ���ǰ��Ĵӱ�洢�ռ䣡����tid=" + tid);
        }
        return result;
    }

    /**
     * ���ݱ�ID��ȡ�洢λ��
     * @param tid String �� ID
     * @return int �洢λ�� 0-��ȱ�1-���ȱ�
     */
    private int checkAndGetTableSpace(String tid) {
        int result = -1;
        for (int i = 0;
                     i < QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND.
                     length;
                     i++) {
            if (QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_ND[i].equals(tid)) {
                result = 0;
                break;
            }
        }
        for (int i = 0;
                     i < QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_JD.
                     length;
                     i++) {
            if (QysdsReportsConstants.CREPORTS_TABLEIDS_QYSDS_JD[i].equals(tid)) {
                result = 1;
                break;
            }
        }
        return result;
    }


}
