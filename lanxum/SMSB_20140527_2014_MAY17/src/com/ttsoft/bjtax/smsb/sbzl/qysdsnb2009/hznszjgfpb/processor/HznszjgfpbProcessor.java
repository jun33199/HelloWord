package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hznszjgfpb.processor;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������˼������ϵͳ���޹�˾</p>
 *
 * @author wangxq
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.QysdsUtil2012;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hznszjgfpb.web.HznszjgfpbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


public class HznszjgfpbProcessor implements Processor
{
    // ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.23";
    private QysdsUtil2012 util = new QysdsUtil2012();

    public HznszjgfpbProcessor()
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
       case CodeConstant.SMSB_CHECKACTION:
             result = doCheck(vo);
             break;

        default:
            throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }
    /**
     * doCheck    ����У��ҳ������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    private Object doCheck(VOPackage vo) throws BaseException {
    	HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) vo.getData();
        Connection con = null;
        
        
        try {
//			�������ݿ�����
            con = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(hznszjgfpbForm);
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
            hznszjgfpbForm.setCheckList(listSingle);
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
        return hznszjgfpbForm;
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
        HznszjgfpbForm form = (HznszjgfpbForm) vo.getData();
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
        // �õ�Action���ݹ���HznszjgfpbForm����
        HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) vo.getData();
        Connection conn = null;

        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // ��ȡ˰����������
            //String jd = QysdsUtil2009.preQuarter(SfDateUtil.getDate(hznszjgfpbForm.getSkssjsrq()));

            System.out.println(hznszjgfpbForm.getJsjdm() + "��hznszjgfpbForm.getSbrq()��" + hznszjgfpbForm.getSbrq());
            //System.out.println(hznszjgfpbForm.getJsjdm() + "��jd��" + jd);
           

            // ��ҳ����ȡ��˰�������ں����
            String nd = hznszjgfpbForm.getSkssksrq().substring(0, 4);

            // ���ü���
            hznszjgfpbForm.setQh("1");
            // �������
            hznszjgfpbForm.setSknd(nd);
            System.out.println(hznszjgfpbForm.getJsjdm() + "��hznszjgfpbForm.setSknd��" + hznszjgfpbForm.getSknd());

            // ����form��������������
            hznszjgfpbForm = (HznszjgfpbForm) QysdsUtil2009.queryDjxxByInterfaceDJ(conn, hznszjgfpbForm, vo.getUserData());
            
            
            System.out.println(hznszjgfpbForm.getJsjdm() + "����˰�����ƣ�" + hznszjgfpbForm.getNsrmc());
            System.out.println(hznszjgfpbForm.getJsjdm() + "����˰��ʶ��ţ�" + hznszjgfpbForm.getNsrsbh());
            
            // ˰�Ѻ˶���Ϣ
			this.getHdxx(hznszjgfpbForm);
            /* ���շ�ʽ */
            String zsfs = hznszjgfpbForm.getZsfs();

            System.out.println(hznszjgfpbForm.getJsjdm() + "�����շ�ʽ���룺" + zsfs);

            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException(
                        "����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼��˶����ռ����걨��");
            }

            /*
            //��ȡ�������ջ�����˰���������ж��Ƿ�Ϊ����д�����û����ܻ�������֧������д��
            int result = this.checkCzzsNsff(conn, hznszjgfpbForm);
            switch(result)
            {
                case CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_NO_DATA);
                    //break;
                case CodeConstant.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
            }
            
            System.out.println("-------hznszjgfpbForm.getJglx()-------" + hznszjgfpbForm.getJglx());

            //��ѯ�������ձ�������������
            this.getCzzsFtse(conn, hznszjgfpbForm);
*/
            // ����QysdsReportsDeclare����
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            // ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
            QysdsUtil2009.setQysdsReport(report, hznszjgfpbForm);

            System.out.println("--------------" + report.getSbrq());
            System.out.println("--------------" + report.getSkssksrq());
            System.out.println("--------------" + report.getSkssjsrq());

            // ����QysdsReportsTableDeclare�Ļ�����Ϣ
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_2012_12);
            table.setTableName(CodeConstant.TABLE_NAME_2012_12);
            table.setTbblx(hznszjgfpbForm.getBbqlx()); //������code = "1"��
            // ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
            report.getTableContentList().put(table.getTableId(), table);

            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // ���ò�ѯ�������в�ѯ
            iApp.querySingleTable(report);
            // ��ȡ��ѯ���ľ�������
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2012_12);

            //���ݲ�ѯ���Ĺ������ݹ�����Ӧ��QysdsReportsItemDeclare
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            System.out.println("###############table.getCellContentList().size()="+table.getCellContentList().size());
            if (table.getCellContentList().size() > 0) {
            	System.out.println("---------------������--------------------");
            	hznszjgfpbForm.setJsjdm(hznszjgfpbForm.getJsjdm());
                hznszjgfpbForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
                hznszjgfpbForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
                hznszjgfpbForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
            }else{
            	/*
              if(hznszjgfpbForm.getJglx().equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG)){
            	hznszjgfpbForm.setZjgmc(hznszjgfpbForm.getNsrmc());
              }else{
              	hznszjgfpbForm.setNsrsbh("");
              	
//              	item.setItemID("12.1");
//              	item.setItemType("11");
//              	item.setItemValue(hznszjgfpbForm.getNsrsbh());
//              	table.getCellContentList().put(item.getItemID(), item);
//              	
//              	item.setItemID("13.1");
//              	item.setItemType("11");
//              	item.setItemValue(hznszjgfpbForm.getZjgmc());
//              	table.getCellContentList().put(item.getItemID(), item);
// 
//              	item.setItemID("17.1");
//              	item.setItemType("11");
//              	item.setItemValue(hznszjgfpbForm.getFzjgfpbl());
//              	table.getCellContentList().put(item.getItemID(), item);
//            
//              	item.setItemID("18.1");
//              	item.setItemType("11");
//              	item.setItemValue(hznszjgfpbForm.getFzjgfpse());
//              	table.getCellContentList().put(item.getItemID(), item);            
              }
              
              */
            	/*
              	//hznszjgfpbForm.setNsrsbh("");
          	item.setItemID("12.1");
          	item.setItemType("11");
          	item.setItemValue(hznszjgfpbForm.getNsrsbh());
          	table.getCellContentList().put(item.getItemID(), item);
          	
          	item.setItemID("13.1");
          	item.setItemType("11");
          	item.setItemValue(hznszjgfpbForm.getZjgmc());
          	table.getCellContentList().put(item.getItemID(), item);

          	item.setItemID("17.1");
          	item.setItemType("11");
          	item.setItemValue(hznszjgfpbForm.getFzjgfpbl());
          	table.getCellContentList().put(item.getItemID(), item);
        
          	item.setItemID("18.1");
          	item.setItemType("11");
          	item.setItemValue(hznszjgfpbForm.getFzjgfpse());
          	table.getCellContentList().put(item.getItemID(), item);  
            */	
            	
          		hznszjgfpbForm.setSrehj("0.00");
          		hznszjgfpbForm.setGzehj("0.00");
          		hznszjgfpbForm.setZcehj("0.00");
          		hznszjgfpbForm.setFpblhj("0");
          		hznszjgfpbForm.setFpsehj("0.00");
            	System.out.println("---------------������--------------------");
            	
            	
            	
            }


//            item.setItemID("6");
//            item.setItemType("11");
//            item.setItemValue(hznszjgfpbForm.getFzjgftse());
//            table.getCellContentList().put(item.getItemID(), item);
//            if(hznszjgfpbForm.getJglx().equals(CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG)){
//            	hznszjgfpbForm.setZjgmc(hznszjgfpbForm.getNsrmc());
//            	
//                item.setItemID("3");
//                item.setItemType("11");
//                item.setItemValue(hznszjgfpbForm.getYnsdse());
//                table.getCellContentList().put(item.getItemID(), item);
//                
//                item.setItemID("4");
//                item.setItemType("11");
//                item.setItemValue(hznszjgfpbForm.getZjgftse());
//                table.getCellContentList().put(item.getItemID(), item);
//                
//                item.setItemID("5");
//                item.setItemType("11");
//                item.setItemValue(hznszjgfpbForm.getZjgfpse());
//                table.getCellContentList().put(item.getItemID(), item);
//            }
            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = {1, 11};
//            hznszjgfpbForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            hznszjgfpbForm.setQysdsjbList(this.translate2Page(table));
            hznszjgfpbForm.setMaxIndex(this.getMxDateMaxIndex(conn, report, hznszjgfpbForm));

            // �����ã���ɺ�Ҫɾ��
            System.out.println(hznszjgfpbForm.getJsjdm());
            System.out.println(hznszjgfpbForm.getSbrq());
            System.out.println(hznszjgfpbForm.getNsrmc());
            System.out.println(hznszjgfpbForm.getSknd());
            System.out.println(hznszjgfpbForm.getQh());
            System.out.println(hznszjgfpbForm.getBbqlx());
            System.out.println(hznszjgfpbForm.getSkssksrq());
            System.out.println(hznszjgfpbForm.getSkssjsrq());
            System.out.println(hznszjgfpbForm.getSwjgzzjgdm());
            System.out.println(hznszjgfpbForm.getQxdm());
            System.out.println(hznszjgfpbForm.getLrr());
            System.out.println(hznszjgfpbForm.getZjgmc());
            System.out.println(hznszjgfpbForm.getNsrsbh());
            System.out.println(hznszjgfpbForm.getYnsdse());
            System.out.println(hznszjgfpbForm.getZjgftse());
            System.out.println(hznszjgfpbForm.getZjgfpse());
            System.out.println(hznszjgfpbForm.getFzjgftse());
            System.out.println(hznszjgfpbForm.getSrehj());
            System.out.println(hznszjgfpbForm.getGzehj());
            System.out.println(hznszjgfpbForm.getZcehj());
            System.out.println(hznszjgfpbForm.getFpblhj());
            System.out.println(hznszjgfpbForm.getFpsehj());            
            System.out.println(hznszjgfpbForm.getFzjgfpbl());
            System.out.println(hznszjgfpbForm.getFzjgfpse());

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
        return hznszjgfpbForm;
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
        // �õ�Action���ݹ���HznszjgfpbForm����
        HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) vo.getData();

        Connection conn = null;

        // ��ȡ˰����������
        String qh ="1"; //QysdsUtil2009.preQuarter(SfDateUtil.getDate(hznszjgfpbForm.getSkssjsrq()));

        // ��ҳ����ȡ��˰�������ں����
        String nd = hznszjgfpbForm.getSkssksrq().substring(0, 4);
        // ���ü���
        hznszjgfpbForm.setQh(qh);
        // �������
        hznszjgfpbForm.setSknd(nd);
        System.out.println(hznszjgfpbForm.getJsjdm() + "��hznszjgfpbForm.setSknd��" + hznszjgfpbForm.getSknd());
        System.out.println(hznszjgfpbForm.getJsjdm() + "��hznszjgfpbForm.setNsrmc��" + hznszjgfpbForm.getNsrmc());
        try
        {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // ��hznszjgfpbForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(hznszjgfpbForm);
            report.setVersion(CodeConstant.QYSDS_VERSION_2009);
            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // ����saveSingleTable�����������ݱ���
            iApp.saveSingleTable(report);

            // ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2012_12);
            table.getCellContentList().clear();

            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = { 1, 11 };
//            hznszjgfpbForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            hznszjgfpbForm.setQysdsjbList(this.translate2Page(table));
            hznszjgfpbForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,hznszjgfpbForm));
            
            
            System.out.println("������hznszjgfpbForm.getNsrsbh��" + hznszjgfpbForm.getNsrsbh());
            System.out.println("������hznszjgfpbForm.getJsjdm:::��" + hznszjgfpbForm.getJsjdm());
            vo.setData(hznszjgfpbForm);

            hznszjgfpbForm = (HznszjgfpbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // �׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ����ɹ�����czqysdsjbForm
        return hznszjgfpbForm;
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
        HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) vo.getData();

        Connection conn = null;
        // ��ȡ˰����������
        String qh ="1"; // = QysdsUtil2009.preQuarter(SfDateUtil.getDate(hznszjgfpbForm.getSkssjsrq()));

        // ��ҳ����ȡ��˰�������ں����
        String nd = hznszjgfpbForm.getSkssksrq().substring(0, 4);
        // ���ü���
        hznszjgfpbForm.setQh(qh);
        // �������
        hznszjgfpbForm.setSknd(nd);


        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();
            // ��czqysdsjbForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(hznszjgfpbForm);
            System.out.println("nd = " + report.getSknd() + "\nqh = " + report.getQh()
                               + "\nskssksrq = " + report.getSkssksrq() + "\nskssjsrq = " + report.getSkssjsrq()
                + "\njsjdm = " + report.getNsrjsjdm());

            // ��ȡ���ݿ�Ӧ�ýӿ�,����deleteSingleTable����ɾ������
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);

            // ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2012_12);
            System.out.println("11111table.getCellContentList() = " + table.getCellContentList().size());
            table.getCellContentList().clear();
            System.out.println("table.getCellContentList() = " + table.getCellContentList().size());
            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = { 1, 11 };
//            hznszjgfpbForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            hznszjgfpbForm.setQysdsjbList(this.translate2Page(table));
            hznszjgfpbForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,hznszjgfpbForm));
            
            //vo.setData(hznszjgfpbForm);            
            //hznszjgfpbForm = (HznszjgfpbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // �׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ɾ���ɹ�����hdzssdsnbForm
        return hznszjgfpbForm;
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

        System.out.println("**��ʾQysdsUtil2009�е�putSpace()**");

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
        int k=0;
        while (it.hasNext()) {
        	k++;
            String key = (String) it.next();
            System.out.println(k+"#key="+key+"#################="+key);
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            
            System.out.println(k+"#item.getItemID()"+item.getItemID()+"#################="+item.getItemID());
            System.out.println(k+"#item.getItemValue()"+item.getItemValue()+"#################="+item.getItemValue());
            
            //���÷������Ϊ�ٷ�����ʽ
            if(key.indexOf(".") > 0)
            {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 17)
                {
                      String value = item.getItemValue();
	                  if(value==null||value.trim().equals("")){
	                	  item.setItemValue("0.00");
	                  }else{
                    	 System.out.println("id = " + key + "\nvalue = " + Arith.round(Double.parseDouble(value) * 100, 2));
                         //item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                    	 //item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)));
                    	 item.setItemValue(value);
	                  }
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
    private QysdsReportsDeclare translate2Interface(HznszjgfpbForm form) {

        // ����QysdsReportsDeclare����
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        // ��form�еĻ�����Ϣת��QysdsReportsDeclare������
        QysdsUtil2009.setQysdsReport(report, form);

        // ������ҵ����˰�����ڵ����������󣬲����������Ϣ
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        //�ֻܷ�����
        table.setTableId(CodeConstant.TABLE_ID_2012_12);
        table.setTableName(CodeConstant.TABLE_NAME_2012_12);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

        // ����ҳ���ܻ�������
        this.translateZjgDate2Interface(form, table);
        // ����ҳ���֧������ϸ����
        this.translateFzjgmxDate2Interface(form, table);
        // ��Ԫ���ֵ����
        report.getTableContentList().put(table.getTableId(), QysdsUtil2009.cleanSpace(table));

        return report;
    }

    /**
     * ����ҳ���ܻ�������
     *    �����ݴ�HznszjgfpbForm��ȡ����䵽QysdsReportsTableDeclare����
     * @param form HznszjgfpbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateZjgDate2Interface(HznszjgfpbForm form, QysdsReportsTableDeclare table)
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
     *     �����ݴ�HznszjgfpbForm��ȡ����䵽QysdsReportsTableDeclare����
     * @param form HznszjgfpbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateFzjgmxDate2Interface(HznszjgfpbForm form, QysdsReportsTableDeclare table)
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
                            if(temp==null||temp.trim().equals("")){
                            	temp="0";
                            }
                            if(!temp.equals("0")){
                            	//temp = temp.substring(0, (temp.length() - 1));
                            }
                            //System.out.println("id = " + id + "\n value = " + Arith.round(Double.parseDouble(temp)/100, 4));
                            //item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(temp)/100, 4)));
                            item.setItemValue(temp);
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
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report, HznszjgfpbForm form) throws BaseException
    {
        int maxIndex = 0;
        //��ȡQysdsReportsTableDeclare����
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2012_12);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql���
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from SBDB.SB_JL_QYSDSSBB_CB_ND ");
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
    private void getHdxx(HznszjgfpbForm form) throws BaseException
    {
        String qyzssllx = "3"; // ȱʡΪ�����걨

        // ��ҵ��˰��˰�� �������ҵ��˰��˰������
        String qyzssl = QYSDS_SL;

        // ��������˰��
        String dezsse = "0.00";

        // ��ǰʱ��
        // ���걨ҳ��ȡ���걨���ں�˰����������
        Timestamp sbrq = QysdsUtil2009.getNxetTimestamp(form.getSkssjsrq());

        // Map getsbjd = this.quarterSkssrq(sbrq);
        Timestamp skssksrq = QysdsUtil2009.getTimestamp(form.getSkssksrq());
        Timestamp skssjsrq = QysdsUtil2009.getTimestamp(form.getSkssjsrq());

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
     * @param form HznszjgfpbForm
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, HznszjgfpbForm form) throws BaseException
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
     * @param form HznszjgfpbForm
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, HznszjgfpbForm form) throws BaseException
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
