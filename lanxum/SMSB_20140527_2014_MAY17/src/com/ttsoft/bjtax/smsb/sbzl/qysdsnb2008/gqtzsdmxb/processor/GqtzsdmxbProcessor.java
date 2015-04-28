package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.gqtzsdmxb.processor;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import java.util.Map;
import com.syax.creports.check.Checker;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.HashMap;
import com.ttsoft.framework.exception.ApplicationException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.web.NstzmxzjbForm;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.sql.Connection;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import java.util.Iterator;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.gqtzsdmxb.web.GqtzsdmxbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class GqtzsdmxbProcessor implements Processor {
    /**
     * ʵ��Processor�ӿ�
     * @param vo ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     * 		1 ���������Ĳ������Ͳ���ʱ�׳�
     * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     * 	�����쳣�׳���EJB��process��������
     */
    public Object process(VOPackage vo) throws BaseException {

        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case CodeConstant.SMSB_SHOWACTION:
            result = doShow(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
            result = doDelete(vo);
            break;
        case CodeConstant.SMSB_CHECKACTION:
            result = doCheck(vo);
            break;
        default:
            throw new ApplicationException(
                    "�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }

    /**
     * doShow��ʼ������ҳ����ϢҪ��
     * @param vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private Object doShow(VOPackage vo) throws BaseException {

        GqtzsdmxbForm nbForm = (GqtzsdmxbForm) vo.getData();
        Connection conn = null;
        try {
//			��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsUtil2008.setQysdsReport(report, nbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_2008_11);
            table.setTableName(CodeConstant.TABLE_NAME_2008_11);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
            report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_2008_11);
            int[] arrs = {17, 58};
            List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("GD");
            List listhj = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("HJ");
            List listpczl = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("PC");
            nbForm.setHjList(listhj);
            nbForm.setDataList(listgd);
            nbForm.setSbbczlList(listpczl);

            nbForm.setBz32(((QysdsReportsItemDeclare) table.
                            getCellContentList().get("32")).getItemValue());
            nbForm.setItem58(((QysdsReportsItemDeclare) table.
                              getCellContentList().get("58")).getItemValue());

        } catch (Exception ex) {
//			�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }

        return nbForm;
    }

    /**
     * doSave    ���ڱ���ҳ����Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    private Object doSave(VOPackage vo) throws BaseException {

        GqtzsdmxbForm nstzmxzjbForm = (GqtzsdmxbForm) vo.getData();
        Connection con = null;
        try {
            //��ȡ���ݿ�����
            con = SfDBResource.getConnection();
            //��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(nstzmxzjbForm);
//			��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(con,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.saveSingleTable(report);
//			�������״̬Ϊ������ɹ���
            iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(con);
        }
        return nstzmxzjbForm;
    }

    /**
     * doDelete    ����ɾ��ҳ������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        GqtzsdmxbForm form = (GqtzsdmxbForm) vo.getData();
        Connection conn = null;
        try {
//			��ȡ���ݿ�����

            conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(form);
//			��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            try {
                iApp.deleteSingleTable(report);
                iApp.updateCheckStatus(report, "");
                QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
                table.setTableId(CodeConstant.TABLE_ID_2008_11);
                table.setTableName(CodeConstant.TABLE_NAME_2008_11);
                table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
                table = (QysdsReportsTableDeclare) report.getTableContentList().
                        get(CodeConstant.TABLE_ID_2008_11);
                int[] arrs = {17, 58};
                List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                        table, arrs)).get("GD");
                List listhj = (List)this.translate2Page(QysdsUtil2008.putSpace(
                        table, arrs)).get("HJ");
                List listpczl = (List)this.translate2Page(QysdsUtil2008.
                        putSpace(
                                table, arrs)).get("PC");
                form.setHjList(listhj);
                form.setDataList(listgd);
                form.setSbbczlList(listpczl);

                form.setBz32(((QysdsReportsItemDeclare) table.
                              getCellContentList().get("32")).getItemValue());
                form.setItem58(((QysdsReportsItemDeclare) table.
                                getCellContentList().get("58")).getItemValue());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * doCheck    ����У��ҳ������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    private Object doCheck(VOPackage vo) throws BaseException {
        GqtzsdmxbForm nstzmxzjbForm = (GqtzsdmxbForm) vo.getData();
        Connection con = null;
        try {
//			�������ݿ�����
            con = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(nstzmxzjbForm);
            /**
             * ����У��
             * ͨ�������ִ�У�
             * δͨ���˳�ִ�У�ҳ����ʾУ������Ϣ
             */
            Checker checker = CheckerFactory.getAInstance(con,
                    CheckerFactory.ACCESS_MODEL_APP_QYSDS);
            IAppAccess iApp = AppAccessFactory.getAInstance(con,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            //���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
            List listSingle = checker.checkSingeTable(report,
                    Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
            nstzmxzjbForm.setCheckList(listSingle);
            /*���У��ͨ�������ýӿڱ�������*/
            if (listSingle == null ||
                (listSingle != null && listSingle.size() == 0)) {
                iApp.saveSingleTable(report);
                //�������״̬Ϊ���������ͨ����
                iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SINGLE_PASS);
            } else if (listSingle.size() > 0) {
                //�������δͨ��
                iApp.updateCheckStatus(report, "");
            }
        } catch (Exception ex) {
//			�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
//			�ͷ����ݿ�����
            SfDBResource.freeConnection(con);
        }
        return nstzmxzjbForm;
    }


    /**
     * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
     * ҳ�����ݽṹ-->�ӿ����ݽṹ
     * @param ZcmxbForm
     * @return ��ҵ����˰������������
     */
    private QysdsReportsDeclare translate2Interface(GqtzsdmxbForm nbForm) {
//		��ҵ����˰������������
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsUtil2008.setQysdsReport(report, nbForm); //��report ����һϵ�е�����
//		��ҵ����˰�����ڵ�����������
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_2008_11);
        table.setTableName(CodeConstant.TABLE_NAME_2008_11);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        List list = nbForm.getDataList(); //��Ź̶��е�LIST
        List pcList = nbForm.getSbbczlList();

        QysdsReportsItemDeclare itembz = new QysdsReportsItemDeclare();
        itembz.setItemID("32");
        itembz.setItemValue(nbForm.getBz32());
        itembz.setItemType("11");
        table.getCellContentList().put(itembz.getItemID(), itembz);

        QysdsReportsItemDeclare item58 = new QysdsReportsItemDeclare();
        item58.setItemID("58");
        item58.setItemValue(nbForm.getItem58());
        item58.setItemType("11");
        table.getCellContentList().put(item58.getItemID(), item58);

        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);

            /*��ǰ�д�*/
//			String hc=(String)map.get("zjhc");
            String hc = Integer.toString(i + 1);
            ((Map) nbForm.getDataList().get(i)).put("hc", hc);
            Iterator it = map.keySet().iterator();

            while (it.hasNext()) {
                /*�ؼ���*/
                String key = (String) it.next();

                /*ITEM ID*/
                String itemId = "";

                if (key.startsWith("C")) {
                    QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                    String col = key.substring(1);
                    /*�����Ͻǵֿ۷���ITEM ID �涨Ϊ1��ֱ�ӵ�������ID��2��ʼ�����к�+1=ITEM ID*/
                    itemId = col;

                    item.setItemID(itemId + "." + hc);
                    item.setItemValue((String) map.get(key));
                    item.setItemType("11");
                    table.getCellContentList().put(item.getItemID(), item);

                }
            }

        }

        //����̶���������������
        for (int i = 0; i < pcList.size(); i++) {
            HashMap map = new HashMap();
            map = (HashMap) pcList.get(i);
            String hc = (String) map.get("hc1");
            int hcnum = Integer.parseInt(hc);
            //���ù̶��е�һ������
            QysdsReportsItemDeclare item0 = new QysdsReportsItemDeclare();
            item0.setItemID(String.valueOf(5 * hcnum - 4 + 32));
            item0.setItemValue((String) map.get("P_1"));
            item0.setItemType("11");
            table.getCellContentList().put(item0.getItemID(), item0);

            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID(String.valueOf(5 * hcnum - 3 + 32));
            item.setItemValue((String) map.get("P_2"));
            item.setItemType("11");
            table.getCellContentList().put(item.getItemID(), item);

            QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
            item1.setItemID(String.valueOf(5 * hcnum - 2 + 32));
            item1.setItemValue((String) map.get("P_3"));
            item1.setItemType("11");
            table.getCellContentList().put(item1.getItemID(), item1);

            QysdsReportsItemDeclare item2 = new QysdsReportsItemDeclare();
            item2.setItemID(String.valueOf(5 * hcnum - 1 + 32));
            item2.setItemValue((String) map.get("P_4"));
            item2.setItemType("11");
            table.getCellContentList().put(item2.getItemID(), item2);

            QysdsReportsItemDeclare item3 = new QysdsReportsItemDeclare();
            item3.setItemID(String.valueOf(5 * hcnum + 32));
            item3.setItemValue((String) map.get("P_5"));
            item3.setItemType("11");
            table.getCellContentList().put(item3.getItemID(), item3);

        }

        /**
         * ����ϼ�������
         */
        List hjList  = nbForm.getHjList();
        for (int i = 0; i < hjList.size(); i++) {
            Map map = (Map) hjList.get(i);

            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            item.setItemID((String) map.get("hjhc"));
            item.setItemValue((String) map.get("hjje"));
            item.setItemType("11");
            table.getCellContentList().put(item.getItemID(), item);
        }

        report.getTableContentList().put(table.getTableId(),
                                         QysdsUtil2008.cleanSpace(table));
        return report;
    }

    /**
     * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
     * �ӿ����ݽṹ-->ҳ�����ݽṹ
     * @param QysdsReportsTableDeclare
     * @return ��ҵ����˰������������
     */
    private HashMap translate2Page(QysdsReportsTableDeclare table) {
        HashMap backMap = new HashMap();
        Map zjMap = new HashMap();
        List pagelist = new ArrayList();
        List hjList = new ArrayList();
        List pczlPageList = new ArrayList();
        Iterator it = table.getCellContentList().keySet().iterator();
        /*�������ʱʹ��*/
        int curRow = 0;
        int MaxRow = 0;

        while (it.hasNext()) {

            String key = (String) it.next();
            String mainItemID = "";

            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.
                                           getCellContentList().get(key);

            if (key.indexOf(".") > 0) {
                mainItemID = key.substring(0, key.indexOf("."));
                curRow = Integer.parseInt(key.substring(key.indexOf(".") + 1));
            } else {
                mainItemID = key;
            }
            /**
             * ITEM ID 1--->16
             */
            if (Integer.parseInt(mainItemID) >= 1 &&
                Integer.parseInt(mainItemID) <= 16) {
                zjMap.put(key, item.getItemValue());
                MaxRow = curRow > MaxRow ? curRow : MaxRow;
            }
            /**
             * �ϼ��� ITEM ID 17--->31
             */
            if (Integer.parseInt(mainItemID) >= 17 &&
                Integer.parseInt(mainItemID) <= 31) {
                Map hjMap = new HashMap();
                hjMap.put(key, item.getItemValue());
                hjMap.put("hjhc", item.getItemID());
                hjMap.put("hjje", item.getItemValue());
                hjList.add(hjMap);
            }

        }

        for (int i = 1; i < 6; i++) {

            HashMap map = new HashMap();
            map.put("hc1", String.valueOf(i));
            map.put("P_1",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 4 + 32))).
                    getItemValue());
            map.put("P_2",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 3 + 32))).
                    getItemValue());
            map.put("P_3",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 2 + 32))).
                    getItemValue());
            map.put("P_4",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 1 + 32))).
                    getItemValue());
            map.put("P_5",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i + 32))).
                    getItemValue());
            pczlPageList.add(map);
        }

        for (int i = 1; i <= MaxRow; i++) {
            Map map = new HashMap();
            map.put("hc", Integer.toString(i));
            Iterator iterator = zjMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                int row = Integer.parseInt(key.substring(key.indexOf(".") + 1));
                int id = Integer.parseInt(key.substring(0, key.indexOf(".")));
                if (i == row) {
                    map.put("C" + (id), zjMap.get(key));
                }
            }
            pagelist.add(map);
        }

        backMap.put("GD", pagelist);
        backMap.put("HJ", hjList);
        backMap.put("PC", pczlPageList);
        return backMap;
    }
}
