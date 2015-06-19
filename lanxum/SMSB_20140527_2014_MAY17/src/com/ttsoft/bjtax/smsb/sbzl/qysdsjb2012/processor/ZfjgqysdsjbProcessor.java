package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.processor;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.web.ZfjgqysdsjbForm;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import java.util.HashMap;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.syax.creports.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import java.sql.Statement;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import java.text.DecimalFormat;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.QysdsUtil2012;


public class ZfjgqysdsjbProcessor implements Processor
{
    // ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.23";
    private QysdsUtil2012 util = new QysdsUtil2012();

    public ZfjgqysdsjbProcessor()
    {
    }

    /**
     * ʵ��Processor�ӿ�
     *
     * @param vo
     *            ҵ�����
     * @return Object VOPackage������
     * @throws BaseException
     *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     *             �����쳣�׳���EJB��process��������
     */

    public Object process(VOPackage vo) throws BaseException
    {

        Object result = null;
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
            // case CodeConstant.SMSB_CHECKACTION:
            // result = doCheck(vo);
            // break;

        default:
            throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }


    /**
     * doShow��ʼ������ҳ����ϢҪ��
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */
    private Object doShow(VOPackage vo) throws BaseException
    {

        // �õ�Action���ݹ���ZfjgqysdsjbForm����
        ZfjgqysdsjbForm form = (ZfjgqysdsjbForm) vo.getData();
        // �õ���ǰʱ���������
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        Map getsbjd = this.quarterSkssrq(curTime);
        Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
        // ˰��������ʼ����
        form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
        // ˰��������������
        form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
        // ˰���걨����
        form.setSbrq(SfDateUtil.getDate());
        return form;
    }

    /**
     * doQuery ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     *
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        // �õ�Action���ݹ���ZfjgqysdsjbForm����
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) vo.getData();
        Connection conn = null;

        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // ��ȡ˰����������
            String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(zfjgForm.getSkssjsrq()));

            System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.getSbrq()��" + zfjgForm.getSbrq());
            System.out.println(zfjgForm.getJsjdm() + "��jd��" + jd);
           

            // ��ҳ����ȡ��˰�������ں����
            String nd = zfjgForm.getSkssksrq().substring(0, 4);

            // ���ü���
            zfjgForm.setQh(jd);
            // �������
            zfjgForm.setSknd(nd);
            System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.setSknd��" + zfjgForm.getSknd());

            // ����form��������������
            zfjgForm = (ZfjgqysdsjbForm) QysdsNewUtil.queryDjxxByInterfaceDJ(conn, zfjgForm, vo.getUserData());
            
            
            System.out.println(zfjgForm.getJsjdm() + "����˰�����ƣ�" + zfjgForm.getNsrmc());
            System.out.println(zfjgForm.getJsjdm() + "����˰��ʶ��ţ�" + zfjgForm.getNsrsbh());
            
            // ˰�Ѻ˶���Ϣ
			this.getHdxx(zfjgForm);
            /* ���շ�ʽ */
            String zsfs = zfjgForm.getZsfs();

            System.out.println(zfjgForm.getJsjdm() + "�����շ�ʽ���룺" + zsfs);

            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException(
                        "����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼��˶����ռ����걨��");
            }

            //��ȡ�������ջ�����˰���������ж��Ƿ�Ϊ����д�����û����ܻ�������֧������д��
            int result = this.checkCzzsNsff(conn, zfjgForm);
            switch(result)
            {
                case CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_NO_DATA);
                    //break;
                case CodeConstant.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
            }
            
            System.out.println("-------zfjgForm.getJglx()-------" + zfjgForm.getJglx());

            //��ѯ�������ձ�������������
            this.getCzzsFtse(conn, zfjgForm);

            // ����QysdsReportsDeclare����
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            // ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
            QysdsNewUtil.setQysdsReport(report, zfjgForm);

            System.out.println("--------------" + report.getSbrq());
            System.out.println("--------------" + report.getSkssksrq());
            System.out.println("--------------" + report.getSkssjsrq());

            // ����QysdsReportsTableDeclare�Ļ�����Ϣ
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_HZJNFZJG_2008);
            table.setTableName(CodeConstant.TABLE_NAME_HZJNFZJG_2008);
            table.setTbblx(zfjgForm.getBbqlx()); //������code = "1"��
            // ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
            report.getTableContentList().put(table.getTableId(), table);

            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // ���ò�ѯ�������в�ѯ
            iApp.querySingleTable(report);
            // ��ȡ��ѯ���ľ�������
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);

            //���ݲ�ѯ���Ĺ������ݹ�����Ӧ��QysdsReportsItemDeclare
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            
            if (table.getCellContentList().size() > 0) {
            	
            	System.out.println("---------------������--------------------");
            	zfjgForm.setJsjdm(zfjgForm.getJsjdm());
                zfjgForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
                zfjgForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
                zfjgForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
            }else{
              if(zfjgForm.getJglx().equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG)){
            	zfjgForm.setZjgmc(zfjgForm.getNsrmc());
              }else{
              	zfjgForm.setNsrsbh("");
              	
//              	item.setItemID("12.1");
//              	item.setItemType("11");
//              	item.setItemValue(zfjgForm.getNsrsbh());
//              	table.getCellContentList().put(item.getItemID(), item);
//              	
//              	item.setItemID("13.1");
//              	item.setItemType("11");
//              	item.setItemValue(zfjgForm.getZjgmc());
//              	table.getCellContentList().put(item.getItemID(), item);
// 
//              	item.setItemID("17.1");
//              	item.setItemType("11");
//              	item.setItemValue(zfjgForm.getFzjgfpbl());
//              	table.getCellContentList().put(item.getItemID(), item);
//            
//              	item.setItemID("18.1");
//              	item.setItemType("11");
//              	item.setItemValue(zfjgForm.getFzjgfpse());
//              	table.getCellContentList().put(item.getItemID(), item);            
              }
          		zfjgForm.setSrehj("0.00");
          		zfjgForm.setGzehj("0.00");
          		zfjgForm.setZcehj("0.00");
          		zfjgForm.setFpblhj("0");
          		zfjgForm.setFpsehj("0.00");
            	System.out.println("---------------������--------------------");
            	
            	
            	
            }


//            item.setItemID("6");
//            item.setItemType("11");
//            item.setItemValue(zfjgForm.getFzjgftse());
//            table.getCellContentList().put(item.getItemID(), item);
//            if(zfjgForm.getJglx().equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG)){
//            	zfjgForm.setZjgmc(zfjgForm.getNsrmc());
//            	
//                item.setItemID("3");
//                item.setItemType("11");
//                item.setItemValue(zfjgForm.getYnsdse());
//                table.getCellContentList().put(item.getItemID(), item);
//                
//                item.setItemID("4");
//                item.setItemType("11");
//                item.setItemValue(zfjgForm.getZjgftse());
//                table.getCellContentList().put(item.getItemID(), item);
//                
//                item.setItemID("5");
//                item.setItemType("11");
//                item.setItemValue(zfjgForm.getZjgfpse());
//                table.getCellContentList().put(item.getItemID(), item);
//            }
            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = {1, 11};
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsjbList(this.translate2Page(table));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report, zfjgForm));

            // �����ã���ɺ�Ҫɾ��
            System.out.println(zfjgForm.getJsjdm());
            System.out.println(zfjgForm.getSbrq());
            System.out.println(zfjgForm.getNsrmc());
            System.out.println(zfjgForm.getSknd());
            System.out.println(zfjgForm.getQh());
            System.out.println(zfjgForm.getBbqlx());
            System.out.println(zfjgForm.getSkssksrq());
            System.out.println(zfjgForm.getSkssjsrq());
            System.out.println(zfjgForm.getSwjgzzjgdm());
            System.out.println(zfjgForm.getQxdm());
            System.out.println(zfjgForm.getLrr());
            System.out.println(zfjgForm.getZjgmc());
            System.out.println(zfjgForm.getNsrsbh());
            System.out.println(zfjgForm.getYnsdse());
            System.out.println(zfjgForm.getZjgftse());
            System.out.println(zfjgForm.getZjgfpse());
            System.out.println(zfjgForm.getFzjgftse());
            System.out.println(zfjgForm.getSrehj());
            System.out.println(zfjgForm.getGzehj());
            System.out.println(zfjgForm.getZcehj());
            System.out.println(zfjgForm.getFpblhj());
            System.out.println(zfjgForm.getFpsehj());            
            System.out.println(zfjgForm.getFzjgfpbl());
            System.out.println(zfjgForm.getFzjgfpse());

        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ��ѯ�ɹ�����czqysdsjbForm
        return zfjgForm;
    }

    /**
     * doSave ���ڴ洢ҳ���ύ���꾡������Ϣ
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */
    private Object doSave(VOPackage vo) throws BaseException {
        // �õ�Action���ݹ���ZfjgqysdsjbForm����
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) vo.getData();

        Connection conn = null;

        // ��ȡ˰����������
        String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(zfjgForm.getSkssjsrq()));

        // ��ҳ����ȡ��˰�������ں����
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // ���ü���
        zfjgForm.setQh(jd);
        // �������
        zfjgForm.setSknd(nd);
        System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.setSknd��" + zfjgForm.getSknd());
        System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.setNsrmc��" + zfjgForm.getNsrmc());
        try
        {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // ��zfjgForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);
            report.setVersion(CodeConstant.SBZL_QYSDSJB_VERSION_2012);
            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // ����saveSingleTable�����������ݱ���
            iApp.saveSingleTable(report);

            // ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);
            table.getCellContentList().clear();

            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsjbList(this.translate2Page(table));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,zfjgForm));
            
            
            System.out.println("������zfjgForm.getNsrsbh��" + zfjgForm.getNsrsbh());
            System.out.println("������zfjgForm.getJsjdm:::��" + zfjgForm.getJsjdm());
            vo.setData(zfjgForm);

            zfjgForm = (ZfjgqysdsjbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // �׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ����ɹ�����czqysdsjbForm
        return zfjgForm;
    }

    /**
     * doDelete ����ɾ��ҳ���ύ���꾡������Ϣ
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        System.out.println("---------doDelete");
        ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) vo.getData();

        Connection conn = null;
        // ��ȡ˰����������
        String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(zfjgForm.getSkssjsrq()));

        // ��ҳ����ȡ��˰�������ں����
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // ���ü���
        zfjgForm.setQh(jd);
        // �������
        zfjgForm.setSknd(nd);


        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();
            // ��czqysdsjbForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);
            System.out.println("nd = " + report.getSknd() + "\nqh = " + report.getQh()
                               + "\nskssksrq = " + report.getSkssksrq() + "\nskssjsrq = " + report.getSkssjsrq()
                + "\njsjdm = " + report.getNsrjsjdm());

            // ��ȡ���ݿ�Ӧ�ýӿ�,����deleteSingleTable����ɾ������
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);

            // ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);
            System.out.println("11111table.getCellContentList() = " + table.getCellContentList().size());
            table.getCellContentList().clear();
            System.out.println("table.getCellContentList() = " + table.getCellContentList().size());
            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsjbList(this.translate2Page(table));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,zfjgForm));
            
            //vo.setData(zfjgForm);            
            //zfjgForm = (ZfjgqysdsjbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // �׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ɾ���ɹ�����hdzssdsnbForm
        return zfjgForm;
    }


    /**
     * ���㼾�����͵�˰����������
     *
     * @param curDate
     *            ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp ʹ��Key ��
     *         Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp ʹ��Key �� Skssrq.SKSSRQ_ND �õ�
     *         ˰�������������ڵ���� String
     */
    public Map quarterSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        int jd = month / 3;
        if (jd == 0) {
            year--;
            jd = 4;
        }
        String nd = new Integer(year).toString();
        Timestamp skssksrqDate = new Timestamp(
            new GregorianCalendar(year, 0, 1).getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            (jd - 1) * 3 + 2, new GregorianCalendar(year, (jd - 1) * 3 + 2,
            1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
                                               .getTime());
        Map retMap = new HashMap();
        retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
        retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
        retMap.put(Skssrq.SKSSRQ_ND, nd);
        return retMap;
    }

    /**
     * �Ѵ������ʱ���˵��Ŀո�ԭ
     *
     * @param table
     * @param a
     * @return
     */
    public static QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table, int arrs[])
    {
        String flag = null;

        if (table.getCellContentList().size() == 0) {
            flag = "0.00";
        } else {
            flag = "";
        }

        System.out.println("**��ʾqysdsNewUtil�е�putSpace()**");

        for (int j = 1; j <= arrs.length; j = j + 2) {
            System.out.println("j___  " + j + "***" + arrs.length);
            for (int i = arrs[j - 1]; i <= arrs[j]; i++) {
                QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
                        .getCellContentList().get(String.valueOf(i));
                if (item == null) {
                    System.out.println("aaaaaaaa");
                    QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
                    item1.setItemID(String.valueOf(i));
                    item1.setItemValue(flag);
                    item1.setItemType("11");
                    table.getCellContentList().put(String.valueOf(i), item1);
                } else if (item != null && item.getItemValue() != null
                        && "".equals(item.getItemValue().trim())) {
                 System.out.println("bbbbbbbbb");
                    QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
                    item1.setItemID(String.valueOf(i));
                    item1.setItemValue(flag);
                    item1.setItemType("11");
                    table.getCellContentList().put(String.valueOf(i), item1);
                }
            }
        }
        return table;
    }

    /**
     * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
     *
     * @param QysdsReportsTableDeclare
     * @return ҳ������ݵ�List����
     */
    private List translate2Page(QysdsReportsTableDeclare table)
    {
        // ����List�����������ҳ�������
        ArrayList pagelist = new ArrayList();
        // �Բ���*�ŵ��е����ݽ��з���
        Iterator it = table.getCellContentList().keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            //���÷������Ϊ�ٷ�����ʽ
            if(key.indexOf(".") > 0)
            {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 17)
                {
                    String value = item.getItemValue();
                    System.out.println("id = " + key + "\nvalue = " + Arith.round(Double.parseDouble(value) * 100, 2));
                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                }
            }
            HashMap map = new HashMap();
            System.out.println("2Page item.getItemID() = " + item.getItemID());
            if(item.getItemID().equals("1")){
            	if("".equals(item.getItemValue()) || (item.getItemValue() == null)){
            		item.setItemValue("");
            	}
            }
            if(item.getItemID().equals("2")){
            	if("".equals(item.getItemValue()) || (item.getItemValue() == null)){
            		item.setItemValue("");
            	}
            }

            map.put("hc", item.getItemID());
            map.put("value", item.getItemValue());
            pagelist.add(map);
        }
        // ����List����
        return pagelist;
    }

    /**
     * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
     *
     * @param form
     * @return ��ҵ����˰������������
     */
    private QysdsReportsDeclare translate2Interface(ZfjgqysdsjbForm form) {

        // ����QysdsReportsDeclare����
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        // ��form�еĻ�����Ϣת��QysdsReportsDeclare������
        QysdsNewUtil.setQysdsReport(report, form);

        // ������ҵ����˰�����ڵ����������󣬲����������Ϣ
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        //�ֻܷ�����
        table.setTableId(CodeConstant.TABLE_ID_HZJNFZJG_2008);
        table.setTableName(CodeConstant.TABLE_NAME_HZJNFZJG_2008);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);

        // ����ҳ���ܻ�������
        this.translateZjgDate2Interface(form, table);
        // ����ҳ���֧������ϸ����
        this.translateFzjgmxDate2Interface(form, table);
        // ��Ԫ���ֵ����
        report.getTableContentList().put(table.getTableId(), QysdsNewUtil.cleanSpace(table));

        return report;
    }

    /**
     * ����ҳ���ܻ�������
     *    �����ݴ�ZfjgqysdsjbForm��ȡ����䵽QysdsReportsTableDeclare����
     * @param form ZfjgqysdsjbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateZjgDate2Interface(ZfjgqysdsjbForm form, QysdsReportsTableDeclare table)
    {
        //QysdsReportsItemDeclare����
        QysdsReportsItemDeclare item;
        /**
         * �ܻ�������
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("1");
        item.setItemValue(form.getZjgmc());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ܻ�����˰��ʶ���
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("2");
        item.setItemValue(form.getNsrsbh());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * Ӧ������˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("3");
        item.setItemValue(form.getYnsdse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ܻ�����̯����˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("4");
        item.setItemValue(form.getZjgftse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ܻ�����������������˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("5");
        item.setItemValue(form.getZjgfpse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * ��֧������̯����˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("6");
        item.setItemValue(form.getFzjgftse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �����ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("7");
        item.setItemValue(form.getSrehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * ���ʶ�ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("8");
        item.setItemValue(form.getGzehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ʲ���ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("9");
        item.setItemValue(form.getZcehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);
        
        /**
         * ���ʶ�ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("10");
        item.setItemValue(form.getFpblhj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ʲ���ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("11");
        item.setItemValue(form.getFpsehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);        
    }

    /**
     * ����ҳ���֧������ϸ����
     *     �����ݴ�ZfjgqysdsjbForm��ȡ����䵽QysdsReportsTableDeclare����
     * @param form ZfjgqysdsjbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateFzjgmxDate2Interface(ZfjgqysdsjbForm form, QysdsReportsTableDeclare table)
    {
        // ��ҳ���֧������ϸ���ݷ�������ݿ�ӿڵ����ݸ�ʽ
        List list = form.getQysdsjbList();

        for (int i = 0; i < list.size(); i++)
        {
            HashMap map = (HashMap) list.get(i);

            boolean flag =  true;
            for(int j = 12; j < 19; j++)
            {
                QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                String id = String.valueOf(j) + "." + String.valueOf(i + 1);
//                System.out.println("iid = " + id);
                //iid
                item.setItemID(id);
                switch(j)
                {
                    case 12:
                        //��֧������˰��ʶ���
                        if(map.get("fzjgnsrsbh") == null || map.get("fzjgnsrsbh").equals(""))
                        {
                            flag = false;
                            item.setItemValue("");
                        }
                        else
                        {
                            item.setItemValue((String) map.get("fzjgnsrsbh"));
                            item.setItemType("11");
                        }
                        break;
                    case 13:
                        if(flag)
                        {
                            //��֧��������
                            item.setItemValue((String) map.get("fzjgmc"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 14:
                        if(flag)
                        {
                            //��֧���������ܶ�
                            item.setItemValue((String) map.get("fzjgsrze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 15:
                        if(flag)
                        {
                            //��֧���������ܶ�
                            item.setItemValue((String) map.get("fzjggzze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 16:
                        if(flag)
                        {
                            //��֧�����ʲ��ܶ�
                            item.setItemValue((String) map.get("fzjgzcze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 17:
                        if(flag)
                        {
                            //��֧�����������
                            String temp = (String) map.get("fzjgfpbl");
                            if(!temp.equals("0")){
                            	temp = temp.substring(0, (temp.length() - 1));
                            }
                            //System.out.println("id = " + id + "\n value = " + Arith.round(Double.parseDouble(temp)/100, 4));
                            item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(temp)/100, 4)));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 18:
                        if(flag)
                        {
                            //��֧��������˰��
                            item.setItemValue((String) map.get("fzjgfpse"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                }
//                System.out.println("flag_" + j + " = " + flag);
//                System.out.println("item value = " + item.getItemValue());
                table.getCellContentList().put(item.getItemID(), item);
            }
        }
    }

    /**
     * ��ѯ��ϸ�������index
     *    �������JSJDM�����ID��ȡ��Ӧ��ϸ���ݵ����index
     * @param con Connection
     * @param report QysdsReportsDeclare
     * @return int
     * @throws BaseException
     */
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report, ZfjgqysdsjbForm form) throws BaseException
    {
        int maxIndex = 0;
        //��ȡQysdsReportsTableDeclare����
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZJNFZJG_2008);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql���
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("' ");
        sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
        sql.append("and qh = '").append(form.getQh()).append("' ");
        sql.append("and sknd = '").append(form.getSknd()).append("' ");

        System.out.println("sql:\n" + sql.toString());
        
        try
        {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);

            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            throw new ApplicationException("��ѯ��ϸ�������indexʧ�ܣ�");
        }

        return maxIndex;
    }

    /**
     * ȡ��һ�ʺ���ҵ��˰����,����ҳ��У��
     *
     * @param form
     * @throws BaseException
     */
    private void getHdxx(ZfjgqysdsjbForm form) throws BaseException
    {
        String qyzssllx = "3"; // ȱʡΪ�����걨

        // ��ҵ��˰��˰�� �������ҵ��˰��˰������
        String qyzssl = QYSDS_SL;

        // ��������˰��
        String dezsse = "0.00";

        // ��ǰʱ��
        // ���걨ҳ��ȡ���걨���ں�˰����������
        Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());

        // Map getsbjd = this.quarterSkssrq(sbrq);
        Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
        Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());

        System.out.println(form.getJsjdm() + "sbrq��" + sbrq);
        System.out.println(form.getJsjdm() + "skssksrq��" + skssksrq);
        System.out.println(form.getJsjdm() + "skssjsrq��" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh��" + form.getQh());

        ServiceProxy proxy = new ServiceProxy();

        String bblx = form.getBbqlx();
        String jsjdm = form.getJsjdm();

        // ��ѯ˰�ѽӿ�
        QysdsSet qysdsSet = null;

        // �����ʸ��ʶ
        boolean jm_type = false;
        form.setJmzg("0"); // �м����ʸ�

        try {
            if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR))
            {
                //�걨
                qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
                                              CodeConstant.SFGL_QYSDS_BBFS_NB);
            }
            else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR))
            {
                //����
                if (form.getQh() == null || (form.getQh() != null && form.getQh().trim().equals("")))
                {
                    /* �ںŲ���Ϊ�գ����Ϊ���׳��쳣 */
                    throw new ApplicationException("ϵͳ�����쳣���ں�Ϊ�գ�����ϵͳ����Ա��ϵ��");
                }
                System.out.println("form.getQh()::" + form.getQh());

                if ("4".equals(form.getQh()))
                {
                    /* ���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ */
                    qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
                }
                else
                {
                    qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
                    //����Zsfs
                    Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
                    qysdsSet.setZsfs(zsfs);
                }
            }
        }
        catch (com.ttsoft.framework.exception.BaseException e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        // 1����ѯ��ҵ���շ�ʽ
        Zsfs zsfs = qysdsSet.getZsfs();
        if (zsfs != null) {
            form.setZsfs(zsfs.getZsfsdm() == null ? CodeConstant.ZSFSDM_CZZS : zsfs.getZsfsdm());
        }
        else
        {
            // 20070208���շ�ʽ���ȡ��Ϊ������Ϊ�ǲ���������ҵ�ġ�
            form.setZsfs(CodeConstant.ZSFSDM_CZZS);
        }

        /* ���¼�����ҵ�϶����� */
        Date gxqyrdrq = qysdsSet.getGxjsqy();

        // ��ֵ
        form.setCyl("0");
        form.setXzqy("0");
        form.setDezsse("0.00");
        form.setYbjmsl("0.00");

        if (zsfs != null)
        {
            String zsfsdm = zsfs.getZsfsdm();
            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS))
            {
                if (gxqyrdrq == null)
                {
                    // ����������
                    qyzssllx = "2";
                }
                else
                {
                    // ���¼����ʹ�������ҵ
                    qyzssllx = "5";
                    qyzssl = "0.15";
                    form.setJmzg("1"); // �м����ʸ�
                }
                form.setCyl(zsfs.getCyl());
            }
            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS))
            {
                // ��������
                qyzssllx = "4";
                // ��ʱ���ֶδ�����ҵ�˶�˰��
                // ynsdse = zsfs.getDe();
                dezsse = zsfs.getDe();
                form.setDezsse(dezsse);
            }
        }

        // 2����ѯ�Ƿ��Ǹ��¼�����ҵ
        if (gxqyrdrq != null) {
            if (zsfs != null
                && zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
                // ���¼����ʹ�������ҵ
                qyzssllx = "5";
            }
            else {
                // ����Ϊ���¼�����ҵ
                qyzssllx = "1";
            }
            qyzssl = "0.15";
            form.setJmzg("1"); // �м����ʸ�

        }
        else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
            // �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
            if (qysdsSet.isXzqy()) {
                form.setXzqy("1");
                form.setJmzg("1"); // �м����ʸ�
            }
        }

        if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
            && qysdsSet.getYbjmsl() != null) {
            // ��������ҵ�ļ������
            form.setJmzg("1"); // �м����ʸ�
            DecimalFormat ft = new DecimalFormat("0.00");
            form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
        }
        form.setQyzslx(qyzssllx);
        form.setSysl(qyzssl);

        /* �˶���Ϣ��� */
        System.out.println("-------------�˶���Ϣ--------------");
        System.out.println("��ҵ����˰������-" + qyzssllx);
        System.out.println("�����ʸ�-" + form.getJmzg());
        System.out.println("һ�����˰��-" + form.getYbjmsl());
        System.out.println("���շ�ʽ-" + form.getZsfs());
        System.out.println("������-" + form.getCyl());
        System.out.println("����-" + form.getDezsse());
        System.out.println("����˰��-" + form.getSysl());
        System.out.println("-------------�˶���Ϣ--------------");
    }

    /**
     * �жϵ�ǰ��˰�˲��������걨��ʽ
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, ZfjgqysdsjbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        //������˰-�ܻ���
        int resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_ZJG;

        HashMap result = new HashMap();
        try
        {
            sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZQYSDSJB_2008).append("' ");
            sql.append("and to_number(hc) < 3 order by to_number(hc) ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            while(rs.next())
            {
                result.put(rs.getString("hc"), rs.getString("yz"));
            }
            if(result.size() == 0)
            {
                //û������
                resultType = CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA;
            }
            else
            {
                String hzff = (String) result.get("1");
                if(hzff.equals(CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS))
                {
                    String hzfs = (String) result.get("2") == null ? "" : (String) result.get("2");
                    if(hzfs.equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG))
                    {
                        //������˰-��֧����
                        resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_FZJG;
                        form.setJglx(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG);
                    }else
                    {
                    	form.setJglx(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG);
                    }
                }
                else if(hzff.equals(CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS))
                {
                    //������˰
                    resultType = CodeConstant.CHECK_HZNSFF_TYPE_DLNS;
                }
            }
            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("��ѯ�������ձ���˰��������");
        }
        return resultType;
    }

    /**
     * ��ѯ�������ձ�������������
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, ZfjgqysdsjbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try
        {
            sql.append("select hc,yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZQYSDSJB_2008).append("' ");
            sql.append("and hc in('12','24','25','26','29','30') ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            String ynsdse="0.00";
            String zjgftse="0.00";
            String zjgfpse="0.00";
            String fzjgftse = "0.00";
            String fzjgfpbl = "0.00";
            String fzjgfpse = "0.00";
            while(rs.next())
            {
            	if(rs.getString("hc").equals("12")){
            		ynsdse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("24")){
            		zjgftse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("25")){
            		zjgfpse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("26")){
            		fzjgftse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("29")){
            		fzjgfpbl=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("30")){
            		fzjgfpse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	
            	
            }
            System.out.println("query ynsdse = " + ynsdse);
            System.out.println("query zjgftse = " + zjgftse);
            System.out.println("query zjgfpse = " + zjgfpse);
            System.out.println("query fzjgftse = " + fzjgftse);
            System.out.println("query fzjgfpbl = " + fzjgfpbl);
            System.out.println("query fzjgfpse = " + fzjgfpse);
            
           if(form.getJglx().equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG))
           {
        	   form.setFzjgftse(fzjgftse);
        	   form.setFzjgfpbl(fzjgfpbl);
        	   form.setFzjgfpse(fzjgfpse);
        	   form.setFzjgnsrsbh(form.getNsrsbh());
        	   form.setFzjgnsrmc(form.getNsrmc());
           }else{
        	   form.setYnsdse(ynsdse);
        	   form.setZjgftse(zjgftse);
        	   form.setZjgfpse(zjgfpse);
        	   form.setFzjgftse(fzjgftse);        	   
           }

            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("��ѯ�������ձ��̯˰�����");
        }
    }

}
