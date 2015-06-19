package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ygyjzjlnstzb.processor;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.check.Checker;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.HashMap;
import com.ttsoft.framework.exception.ApplicationException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.sql.Connection;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ygyjzjlnstzb.web.YgyjzjlnstzbForm;
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
public class YgyjzjlnstzbProcessor implements Processor {
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

        YgyjzjlnstzbForm nbForm = (YgyjzjlnstzbForm) vo.getData();
        Connection conn = null;
        try {
//			��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsUtil2008.setQysdsReport(report, nbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_2008_7);
            table.setTableName(CodeConstant.TABLE_NAME_2008_7);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
            report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            int[] arrs = {1, 50};

            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_2008_7);
            List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                    table, arrs)).get("GD");
            nbForm.setDataList(listgd);
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

        YgyjzjlnstzbForm ygyjzjlnstzbForm = (YgyjzjlnstzbForm) vo.getData();
        Connection con = null;
        try {
            //��ȡ���ݿ�����
            con = SfDBResource.getConnection();
            //��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(
                    ygyjzjlnstzbForm);
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
        return ygyjzjlnstzbForm;
    }

    /**
     * doDelete    ����ɾ��ҳ������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        YgyjzjlnstzbForm form = (YgyjzjlnstzbForm) vo.getData();
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
                table.setTableId(CodeConstant.TABLE_ID_2008_7);
                table.setTableName(CodeConstant.TABLE_NAME_2008_7);
                table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
                table = (QysdsReportsTableDeclare) report.getTableContentList().
                        get(CodeConstant.TABLE_ID_2008_7);
                int[] arrs = {1, 50};
                List listgd = (List)this.translate2Page(QysdsUtil2008.putSpace(
                        table, arrs)).get("GD");
                form.setDataList(listgd);

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
        YgyjzjlnstzbForm ygyjzjlnstzbForm = (YgyjzjlnstzbForm) vo.getData();
        Connection con = null;
        try {
//			�������ݿ�����
            con = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(
                    ygyjzjlnstzbForm);
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
            ygyjzjlnstzbForm.setCheckList(listSingle);
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
        return ygyjzjlnstzbForm;
    }


    /**
     * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
     * ҳ�����ݽṹ-->�ӿ����ݽṹ
     * @param ZcmxbForm
     * @return ��ҵ����˰������������
     */
    private QysdsReportsDeclare translate2Interface(YgyjzjlnstzbForm nbForm) {
//		��ҵ����˰������������
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsUtil2008.setQysdsReport(report, nbForm); //��report ����һϵ�е�����
//		��ҵ����˰�����ڵ�����������
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_2008_7);
        table.setTableName(CodeConstant.TABLE_NAME_2008_7);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
        List list = nbForm.getDataList(); //��Ź̶��е�LIST

        //����̶�������
        for (int i = 0; i < list.size(); i++) {
            HashMap map = new HashMap();
            map = (HashMap) list.get(i);
            String hc = (String) map.get("hc");
            int hcnum = Integer.parseInt(hc);

            //���ù̶��е�һ������
            QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
            item1.setItemID((5 * hcnum - 4) + "");
            item1.setItemValue((String) map.get("qczzje"));
            item1.setItemType("11");
            table.getCellContentList().put(item1.getItemID(), item1);

            //���ù̶��еڶ�������
            QysdsReportsItemDeclare item2 = new QysdsReportsItemDeclare();
            item2.setItemID((5 * hcnum - 3) + "");
            item2.setItemValue((String) map.get("qcjsjc"));
            item2.setItemType("11");
            table.getCellContentList().put(item2.getItemID(), item2);

            //���ù̶��е���������
            QysdsReportsItemDeclare item3 = new QysdsReportsItemDeclare();
            item3.setItemID((5 * hcnum - 2) + "");
            item3.setItemValue((String) map.get("qmzzje"));
            item3.setItemType("11");
            table.getCellContentList().put(item3.getItemID(), item3);

            //���ù̶��е���������
            QysdsReportsItemDeclare item4 = new QysdsReportsItemDeclare();
            item4.setItemID((5 * hcnum - 1) + "");
            item4.setItemValue((String) map.get("qmjsjc"));
            item4.setItemType("11");
            table.getCellContentList().put(item4.getItemID(), item4);

            //���ù̶��е���������
            QysdsReportsItemDeclare item5 = new QysdsReportsItemDeclare();
            item5.setItemID((5 * hcnum) + "");
            item5.setItemValue((String) map.get("nstze"));
            item5.setItemType("11");
            table.getCellContentList().put(item5.getItemID(), item5);

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
        List pagelist = new ArrayList();

        for (int i = 1; i < 11; i++) {

            HashMap map = new HashMap();
            map.put("hc", String.valueOf(i));
            map.put("qczzje",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 4))).
                    getItemValue());
            map.put("qcjsjc",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 3))).
                    getItemValue());
            map.put("qmzzje",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i -2))).
                    getItemValue());
            map.put("qmjsjc",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i - 1))).
                    getItemValue());
            map.put("nstze",
                    ((QysdsReportsItemDeclare) table.
                     getCellContentList().get(String.valueOf(5 * i))).
                    getItemValue());
            pagelist.add(map);
        }

        backMap.put("GD", pagelist);

        return backMap;
    }

}
