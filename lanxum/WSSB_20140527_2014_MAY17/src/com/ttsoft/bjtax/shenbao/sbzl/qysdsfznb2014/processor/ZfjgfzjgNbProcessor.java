package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.processor;


import com.ttsoft.framework.processor.Processor;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.QysdsNbConstant2014;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.QysdsNbUtil2014;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.bo.ZfjgfzjgNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.ZfjgqysdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;

import java.util.Iterator;
import java.util.Map;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import java.sql.Timestamp;

import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import java.util.HashMap;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.syax.creports.util.Arith;

/**
 * 
 * @description : ������˰��������ϵͳ���������걨 -- ������˰��֧���������Processor</p>
 * @author zhangj
 * @version 2014-9-17 ����11:59:14
 */
public class ZfjgfzjgNbProcessor implements Processor
{
    //��ʼ��������
    private QysdsNbUtil2014 sdsUtil = new QysdsNbUtil2014();

    public ZfjgfzjgNbProcessor()
    {
    }

    /**
     * ����ҵ���������ֵ����ҵ�����
     *
     * @param vo
     *            VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        // ����ҵ���������ֵ����ҵ�����
        try {
            switch(vo.getAction()) {
                // ��ѯ
                case QysdsksActionConstant.INT_ACTION_QUERY: {
                    return doQuery( (Map)vo.getData());
                }
                // ����
                case QysdsksActionConstant.INT_ACTION_SAVE: {
                    return doSave(vo);
                }
                // ɾ��
                case QysdsksActionConstant.INT_ACTION_DELETE: {
                    doDelete(vo);
                    return null;
                }
                default:
                    throw new SystemException("no such method");
            }
        }
        catch(Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ѯ��ҵ����˰������˰��֧�����������Ϣ
     *
     * @param pData
     *            Map
     * @return Map
     * @throws BaseException
     */
    private Object doQuery(Map pData) throws BaseException
    {
        // ���ݿ����Ӷ���
        Connection conn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        try {
            // ������ݿ�����
            conn = DBResource.getConnection();
            ZfjgfzjgNbBO zfjgbo = new ZfjgfzjgNbBO();
            // ���������
            String jsjdm = null;
            // ��ǰ����
            Timestamp curDate = null;
            // ˰��Ǽǻ�������ֵ����
            SWDJJBSJ djjbsj = (SWDJJBSJ)pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
            zfjgbo.setJsjdm(djjbsj.getJsjdm());
            zfjgbo.setNsrmc(djjbsj.getNsrmc());
            zfjgbo.setNsrsbh(djjbsj.getSwdjzh());

            // ȡ�ü��������
            jsjdm = (String)pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
            // ȡ�����ڲ���
            curDate = (Timestamp)pData.get(QysdsksMapConstant.STRING_KEY_DATE);
            zfjgbo.setSbrq(curDate);
            zfjgbo.setSbrqshow(sdf.format(curDate));
            // ȡ�����ڵļ���
            String jd = Skssrq.preQuarter(curDate);
            zfjgbo.setJd(jd);
            // �������� - ��ID
            String bblx = (String)pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
            // �������� - �ں�
            String jdlx = (String)pData.get(QysdsNbConstant2014.STRING_KEY_NDLX);
            zfjgbo.setJd(jdlx); //�ں�-�����ںż�Ϊ������
            System.out.println("jsjdm = " + jsjdm + "\nbblx = " + bblx + "\nqh = " + jdlx);

            // ȡ��˰����������Map
            Map skssrq = new HashMap();
            if(bblx.equals(QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014)) {
                skssrq = Skssrq.yearSkssrq(curDate);
            }
            else {
                throw new ApplicationException("���ݵı���ID����");
            }
            // ȡ��˰��������ʼ�ͽ�������
            Timestamp skssksrq = (Timestamp)skssrq.get(Skssrq.SKSSKSRQ);
            Timestamp skssjsrq = (Timestamp)skssrq.get(Skssrq.SKSSJSRQ);
            zfjgbo.setSkssksrq(skssksrq);
            zfjgbo.setSkssjsrq(skssjsrq);

            // ȡ�����
            String nd = (String)skssrq.get(Skssrq.SKSSRQ_ND);
            zfjgbo.setNd(nd);
            System.out.println("nd = " + nd);

            //����������
            zfjgbo.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);

            //��ȡ�������ջ�����˰���������ж��Ƿ�Ϊ����д�����û�
            int result = this.checkCzzsNsff(conn, zfjgbo);
            switch(result) {
                case QysdsNbConstant2014.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException("����ҵ��δ���ҵ����˰��֧���������˰�걨�������ڴ�¼�룬����¼����ҵ����˰��֧���������˰�걨��");
                    //break;
                case QysdsNbConstant2014.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(QysdsNbConstant2014.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
                //case Constant.CHECK_HZNSFF_TYPE_HZNS_FZJG:
                    //throw new ApplicationException(Constant.CHECK_HZNSFF_MESSAGE_FZJG);
                    //break;
            }

            //��ѯ�������ձ��̯˰�����
            this.getCzzsFtse(conn, zfjgbo);

            // ����QysdsReportsDeclare����
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            // ����QysdsReportsTableDeclare�Ļ�����Ϣ
            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();

            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //version
            qrd.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
            qrd.setNsrjsjdm(jsjdm); //���������
            qrd.setSknd(nd); //˰�����
            // ������ǻ�����˰��֧�����������쳣
            if(bblx.equals(QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014)) {
                //����������
                qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
                //�ں�
                qrd.setQh(jdlx);

                //����ID
                qrtd.setTableId(bblx);
                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
            }
            else {
                throw new ApplicationException("���ݵı���ID����");
            }
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            // ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
            Map tmp = new HashMap();
            tmp.put(bblx, qrtd);
            qrd.setTableContentList(tmp);

            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

            // ���ò�ѯ�������в�ѯ
            qrd = (QysdsReportsDeclare)iApp.querySingleTable(qrd);

            // ��ȡ��ѯ���ľ�������
            qrtd = (QysdsReportsTableDeclare)qrd.getTableContentList().get(bblx);

            //���ݲ�ѯ���ķ�̯˰�����Ӧ��QysdsReportsItemDeclare
            /** ����������ʲô�ã��������ݶ�����translate2Page�н��д�����ʱ���Σ�guoxh,2014-3-6
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID("6");
            item.setItemType("11");
            item.setItemValue(zfjgbo.getFtse());
            qrtd.getCellContentList().put(item.getItemID(), item);
            **/

            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
            if(qrtd == null) {
                System.out.println("======no value======");
            }
            else {
                //��ȡ��֧������ϸ��Ϣ������
                int rows = this.getMxDateMaxIndex(conn, qrd, pData);

                //��ȡ���������Map,��id, value ��ʽ����
                HashMap map = (HashMap)this.translate2Page(qrtd, rows);

                zfjgbo.setSbsj(map);
            }
            return zfjgbo;
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰������˰��֧������������ʧ��");
        }
        finally {
            // �ر����ݿ�����
            DBResource.destroyConnection(conn);
        }
    }
    /**
     * �жϵ�ǰ��˰�˲��������걨��ʽ
     * @param conn Connection
     * @param bo ZfjgqysdsjbBO
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, ZfjgfzjgNbBO bo) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        //������˰-�ܻ���
        int resultType = QysdsNbConstant2014.CHECK_HZNSFF_TYPE_HZNS_ZJG;

        HashMap result = new HashMap();
        try {
            sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(bo.getBbqlx()).append("' ");
            sql.append("and qh = '").append(bo.getJd()).append("' ");
            sql.append("and sknd = '").append(bo.getNd()).append("' ");
            sql.append("and sbdm = '").append(QysdsNbConstant2014.TABLE_ID_CZZSSDSNB_2014).append("' ");
            sql.append("and to_number(hc) < 3 order by to_number(hc) ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            while(rs.next()) {
                result.put(rs.getString("hc"), rs.getString("yz"));
            }
            System.out.println("result.size() = " + result.size());
            if(result.size() == 0) {
                //û������
                resultType = QysdsNbConstant2014.CHECK_HZNSFF_TYPE_NO_DATA;
            }
            else {
                String hzff = (String)result.get("1");
                System.out.println("hzff = " + hzff);
                if(hzff.equals(QysdsNbConstant2014.HZNSFF_QYSDSNB2014_CZZSSDS_HZNS)) {
                    String hzfs = (String)result.get("2") == null ? "" : (String)result.get("2");
                    System.out.println("hzfs = " + hzfs);
                    if(hzfs.equals(QysdsNbConstant2014.HZNSFS_QYSDSNB2014_CZZSSDS_FZJG)) {
                        //������˰-��֧����
                        resultType = QysdsNbConstant2014.CHECK_HZNSFF_TYPE_HZNS_FZJG;
                    }
                }
                else if(hzff.equals(QysdsNbConstant2014.HZNSFF_QYSDSNB2014_CZZSSDS_DLNS)) {
                    //������˰
                    resultType = QysdsNbConstant2014.CHECK_HZNSFF_TYPE_DLNS;
                }
            }
            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("��ѯ�������ձ���˰��������");
        }
        return resultType;
    }
    /**
     * ��ѯ�������ձ��̯˰�����
     * @param conn Connection
     * @param bo ZfjgqysdsjbBO
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, ZfjgfzjgNbBO bo) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(bo.getBbqlx()).append("' ");
            sql.append("and qh = '").append(bo.getJd()).append("' ");
            sql.append("and sknd = '").append(bo.getNd()).append("' ");
            sql.append("and sbdm = '").append(QysdsNbConstant2014.TABLE_ID_CZZSSDSNB_2014).append("' ");
            sql.append("and hc = '26' ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            String ftse = "0.00";
            while(rs.next())
            {
                ftse = rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            }
            bo.setFtse(ftse);

            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("��ѯ�������ձ��̯˰�����");
        }
    }
    /**
     * ��ѯ��ϸ�������index
     *    �������JSJDM�����ID��ȡ��Ӧ��ϸ���ݵ����index
     * @param con Connection
     * @param report QysdsReportsDeclare
     * @return int
     * @throws BaseException
     * @author gaoyh
     * @modify_date 2014-03-15
     */
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report, Map pData) throws BaseException
    {
        int maxIndex = 0;
        HashMap hpData = (HashMap)pData;
        //��ȡQysdsReportsTableDeclare����
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get(QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // ��ǰ����
        Timestamp curDate = null;
        // ȡ�����ڲ���
        curDate = (Timestamp)hpData.get(QysdsksMapConstant.STRING_KEY_DATE);
        // ȡ��˰����������Map
		Map skssrq = new HashMap();	
		skssrq = Skssrq.yearSkssrq(curDate);
		// ȡ�����
		String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);

        //sql���
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("'");
        sql.append("and bbqlx = '").append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
        sql.append("and qh = '").append((String)hpData.get(QysdsNbConstant2014.STRING_KEY_NDLX)).append("' ");
        sql.append("and sknd = '").append(nd).append("' ");

        System.out.println("sql:\n" + sql.toString());

        try {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);

            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex) {
            throw new ApplicationException("��ѯ��ϸ�������indexʧ�ܣ�");
        }

        return maxIndex;
    }
    /**
     * ������ת����ҳ��չʾ����ʽ
     * @param table QysdsReportsTableDeclare
     * @param maxIndex int
     * @return Map
     */
    private Map translate2Page(QysdsReportsTableDeclare table, int maxIndex)
    {
        HashMap dataMap = new HashMap();
        Map data = table.getCellContentList();
        System.out.println("Map size = " + data.size());
        java.util.Iterator it = data.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String) it.next();
            System.out.println("key = " + key);
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare)table.getCellContentList().get(key);
            if(key.indexOf(".") > 0) {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 17) {
                    String value = item.getItemValue();
                    System.out.println("value = " + value);
                    System.out.println("change = " + Double.parseDouble(value) * 100);
                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                }
            }
            System.out.println("---------");
            dataMap.put(item.getItemID(), item.getItemValue());
        }
		System.out.println("==========processor========");
		System.out.println(dataMap.toString());
        //�����������ݲ���Ĭ������,�����������
        if(maxIndex < QysdsNbConstant2014.ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER) {
            int statrIndex = maxIndex + 1;
            int endIndex = QysdsNbConstant2014.ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER + 1;

            for(int k = statrIndex; k < endIndex; k++) {
                for(int j = 12; j < 19; j++) {
                    //
                    String id = String.valueOf(j) + "." + String.valueOf(k);
//                    System.out.println("add id = " + id);
                    switch(j) {
                        case 12:
                            //��֧������˰��ʶ���
                            dataMap.put(id, "");
                            break;
                        case 13:
                            //��֧��������
                            dataMap.put(id, "");
                            break;
                        case 14:
                            //��֧���������ܶ�
                            dataMap.put(id, "0.00");
                            break;
                        case 15:
                            //��֧���������ܶ�
                            dataMap.put(id, "0.00");
                            break;
                        case 16:
                            //��֧�����ʲ��ܶ�
                            dataMap.put(id, "0.00");
                            break;
                        case 17:
                            //��֧�����������
                            dataMap.put(id, "0");
                            break;
                        case 18:
                            //��֧��������˰��
                            dataMap.put(id, "0.00");
                            break;
                    }
                }
            }
            //�����������
            dataMap.put(QysdsNbConstant2014.ZFJGSDSNB_2014_MAX_ROW, String.valueOf(QysdsNbConstant2014.ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER));
        }
        else {
            //�����������
            dataMap.put(QysdsNbConstant2014.ZFJGSDSNB_2014_MAX_ROW, String.valueOf(maxIndex));
        }

        // ����Map����
        return dataMap;
    }
    
    
    /**
     * ������ҵ����˰����
     *
     * @param data
     *            Map
     * @throws BaseException
     */
    private Map doSave(VOPackage vop) throws BaseException
    {
        DzyjHelper dh = new DzyjHelper();
        Map retData = new HashMap();
        Map data = (Map)vop.getData();
        UserData ud = vop.getUserData();
        Connection conn = null;

        //ZfjgqysdsjbBO
        ZfjgqysdsNbVO qysdsnbvo = (ZfjgqysdsNbVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        //DzyjsjVO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        //ZfjgqysdsjbBO
        ZfjgfzjgNbBO qysdsjbbo = (ZfjgfzjgNbBO)data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
        // ��������
        String tableID = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // �������
        String ndlx = (String)data.get(QysdsNbConstant2014.STRING_KEY_NDLX);
//        System.out.println("tableID = " + tableID + "\njdlx = " + jdlx);
        // ˰��Ǽǻ�������ֵ����
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
        try {
            // ������ݿ�����
            conn = DBResource.getConnection();
            QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsjbbo, tableID, ndlx, djjbsj);

            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get("28");

            // ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.saveSingleTable(report);
			DBResource.destroyConnection(conn);
        }
        catch(Exception ex) {
		    DBResource.destroyConnection(conn);
            throw ExceptionUtil.getBaseException(ex);
        }

        if(ud.getCaflag()) {
            System.out.println("===========ǩ����ʼ==========");
            try {
                String ywid = qysdsnbvo.getZjgxx().getJsjdm() + "+" + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========ǩ������==========");
            }
            catch(Exception ex) {
                System.out.println("===========ǩ��ʧ��==========");
                throw ExceptionUtil.getBaseException(ex);
            }

            retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }

        return retData;
    }
    
    /**
     * ���ݷ���ӿ�
     *
     * @param qysdsbo
     *            ��ҵ����˰BO����
     * @param tableID
     *            ����ID
     * @param jdlx
     *            ��������
     * @param djjbsj
     *            �Ǽ����ݶ���
     * @return
     */
    private QysdsReportsDeclare ConvertBoToReportsDeclare(ZfjgfzjgNbBO qysdsbo,String tableID, String ndlx, SWDJJBSJ djjbsj)
    {
        QysdsReportsDeclare report = new QysdsReportsDeclare();

        // ������Ϣ
        Jbxx jbxx = new Jbxx();
        // ������Ϣ(JBXX)-���������
        //jbxx.setJsjdm(qysdsbo.getJsjdm());
        // ������Ϣ(JBXX)-��˰������
        //jbxx.setNsrmc(qysdsbo.getNsrmc());
        
        // ������Ϣ(JBXX)-���������
        jbxx.setJsjdm(djjbsj.getJsjdm());
        // ������Ϣ(JBXX)-��˰������
        jbxx.setNsrmc(djjbsj.getNsrmc());
        
        // ������Ϣ(JBXX)-��ϵ�绰
        jbxx.setLxdh(djjbsj.getJydzlxdm());
        // ������Ϣ(JBXX)-������ҵ
        jbxx.setSshy(djjbsj.getGjbzhydm());
        // ������Ϣ(JBXX)-���շ�ʽ
        jbxx.setZsfs("");

        // �򱨱��������˰�˻�����Ϣ
        report.setJbxx(jbxx);

        /**
         * Ӧ�ñ��
         */
        report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
        /**
         * �汾��
         */
        //report.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
        report.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
        /**
         * ��˰�˼��������
         */
        //report.setNsrjsjdm(qysdsbo.getJsjdm());
        report.setNsrjsjdm(djjbsj.getJsjdm());
        /**
         * ��˰������
         */
        //report.setNsrmc(qysdsbo.getNsrmc());
        report.setNsrmc(djjbsj.getNsrmc());
        /**
         * ����������
         */
        // ������걨������̶������ں�Ϊ1������Ǽ��������������������ں�Ϊ1��2��3��4
        report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
        /**�ں�   1*/
        report.setQh(ndlx);
        // ȡ��˰����������Map
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        Map skssrq = new HashMap();

        skssrq = Skssrq.yearSkssrq(curDate);

        String sbrq = "";
        try {
            /**˰��������*/
            //skssksrq
            String skssksrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
            //skssjsrq
            String skssjsrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
            // �걨����
            sbrq = DateTimeUtil.timestampToString(curDate);
            report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        }
        catch(Exception e) {
            System.out.println("ת���걨��ʱ����");
        }
        // ˰�����
        report.setSknd( (String)skssrq.get(Skssrq.SKSSRQ_ND));
        /**
         * ˰�������֯��������
         */
        report.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
        /**
         * ���ش���
         */
        report.setQxdm(djjbsj.getSwjgzzjgdm().substring(0,2));
        /**
         * ¼����
         */
        report.setLrr(qysdsbo.getJsjdm());
        /**
         * ¼������
         */
        report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        /**
         * ������
         */
        report.setCjr(qysdsbo.getJsjdm());
        /**
         * ��������
         */
        report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));

        // ��ҵ����˰�����ڵ�����������
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(tableID);
        table.setTableName(QysdsNbConstant2014.TABLE_NAME_ZFJGSDSNB_2014);
        /**
         * ������ͣ��ͱ���������һ��
         *
         */
        table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);

        Map sbsj = qysdsbo.getSbsj();

        Iterator it = sbsj.keySet().iterator();
        while(it.hasNext()) {
            String id = (String)it.next();
            String value = (String) sbsj.get(id);
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID(id);
            //���÷������Ϊ�ٷ�����ʽ
            if(id.indexOf(".") > 0)
            {
                String head = id.substring(0, id.indexOf("."));
                //��֧�������������
                if(Integer.parseInt(head) == 17)
                {
                	if(!value.equals("0")){
                		value = value.substring(0, (value.length() - 1));
                	}
                    value = String.valueOf(Arith.round(Double.parseDouble(value)/100, 4));
                }
            }
            item.setItemValue(value);
            item.setItemType("11");
            table.getCellContentList().put(item.getItemID(), item);
        }
        report.getTableContentList().put(table.getTableId(), table);

        return report;
    }
    
    
    /**
     * ɾ���걨����
     *
     * @param data
     *            Map
     * @throws BaseException
     */
    private void doDelete(VOPackage vop) throws BaseException
    {
        DzyjHelper dh = new DzyjHelper();
        Map retData = new HashMap();
        Map data = (Map)vop.getData();
        UserData ud = vop.getUserData();
        Connection conn = null;

        //ZfjgqysdsjbVO
        ZfjgqysdsNbVO qysdsNbvo = (ZfjgqysdsNbVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);

        //DzyjsjVO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        // ��������
        String bblx = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // ��������
        String ndlx = (String)data.get(QysdsNbConstant2014.STRING_KEY_NDLX);
        // ˰��Ǽǻ�������ֵ����
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.STRING_KEY_JBSJ);

        try {
            // ������ݿ�����
            conn = DBResource.getConnection();
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //�汾
            qrd.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
            qrd.setNsrjsjdm(qysdsNbvo.getZjgxx().getJsjdm()); //���������
            qrd.setSknd(qysdsNbvo.getSbxx().getNd()); //˰�����
            qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR); //�걨
            qrd.setQh(ndlx); //�ں�
            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
            qrtd.setTableId(bblx); //����ID
            qrtd.setTableName(QysdsNbConstant2014.TABLE_NAME_ZFJGSDSNB_2014); //��������
            qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);

            Map tmp = new HashMap();
            tmp.put(bblx, qrtd);
            qrd.setTableContentList(tmp);
            // ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(qrd);
            
            //��ҵ����˰�����ڵ�����������
			QysdsReportsTableDeclare table32 = new QysdsReportsTableDeclare();
			table32.setTableId(QysdsNbConstant2014.TABLE_ID_CZZSSDSNB_2014);
			table32.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// set table
			qrd.getTableContentList().put(table32.getTableId(), table32);
			// ����delete������������ɾ��
			iApp.deleteSingleTable(qrd);
			DBResource.destroyConnection(conn);
        }
        catch(Exception ex) {
		    DBResource.destroyConnection(conn);
            throw ExceptionUtil.getBaseException(ex);
        }

        if(ud.getCaflag()) {

            System.out.println("===========ǩ����ʼ==========");
            try {
                String ywid = qysdsNbvo.getZjgxx().getJsjdm() + "+" + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========ǩ������==========");
            }
            catch(Exception ex) {
                System.out.println("===========ǩ��ʧ��==========");
                throw ExceptionUtil.getBaseException(ex);
            }
            retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
    }

}
