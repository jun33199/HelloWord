package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.processor;


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
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.GdzcjszjyjqkjbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.ZfjgqysdsjbForm;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;

import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import java.util.HashMap;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
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
import java.sql.SQLException;

import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import java.sql.Statement;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import java.text.DecimalFormat;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.QysdsUtil2014;




public class GdzcjszjyjqkjbProcessor implements Processor
{
   
    public GdzcjszjyjqkjbProcessor()
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

        case CodeConstant.SMSB_QUERYACTION:
            result = doQuery(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
            result = doDelete(vo);
            break;

        default:
            throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
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
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) vo.getData();
        Connection conn = null;
        
        UserData userData=(UserData)vo.getUserData();
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(gdzcjszjyjqkjbForm.getSkssjsrq()));
		// ��ȡ˰���������
		// String sknd = this.getNd(jd, getsbnd, CzzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// ��ҳ����ȡ��˰�������ں����
		String nd = gdzcjszjyjqkjbForm.getSkssksrq().substring(0, 4);
		// ���ü���
		gdzcjszjyjqkjbForm.setQh(jd);
		// �������
		gdzcjszjyjqkjbForm.setSknd(nd);
		gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		gdzcjszjyjqkjbForm.setLrr(userData.getYhid());
        try {
//			��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            System.out.println("jsjdm: "+gdzcjszjyjqkjbForm.getJsjdm());
            gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)QysdsNewUtil.queryDjxxByInterfaceDJ(conn, gdzcjszjyjqkjbForm, userData);
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsNewUtil.setQysdsReport(report, gdzcjszjyjqkjbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
            table.setTableName(CodeConstant.TABLE_NAME_GDZCJSZJYJQK_2014);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            int[] arrs = {1, 50};

            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
            List listgd = (List)this.translate2Page(QysdsNewUtil.putSpace(
                    table, arrs)).get("GD");
            gdzcjszjyjqkjbForm.setGdzcjszjyjqkjbList(listgd);
            
            //�����ݿ��л�ȡ������ҵ����
            this.getSshydm(gdzcjszjyjqkjbForm,table);

        } catch (Exception ex) {
//			�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }

        return gdzcjszjyjqkjbForm;
    }
    /**
     * �����ݿ��л�ȡ������ҵ���룬hcΪ��999��
     * @param table
     * @return
     */
    private void getSshydm(GdzcjszjyjqkjbForm jbForm,QysdsReportsTableDeclare table) {
    	String yz="";
		if (table.getCellContentList().get("999") != null)
        {
			QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) table
					.getCellContentList().get("999");
			yz=qrtid.getItemValue();
			if (yz == null) {
				yz= "";
			} 
		}
		jbForm.setGjbzhydm(yz);
		if(!"".equals(yz.trim())){
			jbForm.setSshy(yz);
		}
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
    	
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) vo.getData();    	
        Connection con = null;
        UserData userData=(UserData)vo.getUserData();
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(gdzcjszjyjqkjbForm.getSkssjsrq()));
		// ��ȡ˰���������
		// String sknd = this.getNd(jd, getsbnd, CzzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// ��ҳ����ȡ��˰�������ں����
		String nd = gdzcjszjyjqkjbForm.getSkssksrq().substring(0, 4);
		// ���ü���
		gdzcjszjyjqkjbForm.setQh(jd);
		// �������
		gdzcjszjyjqkjbForm.setSknd(nd);
		gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		gdzcjszjyjqkjbForm.setLrr(userData.getYhid());
		
        System.out.println("jd: "+jd+"nd: "+nd+"jsjdm: "+gdzcjszjyjqkjbForm.getJsjdm());
        System.out.println("NSRMC: "+gdzcjszjyjqkjbForm.getNsrmc());
        try {
            //��ȡ���ݿ�����
            con = SfDBResource.getConnection();
            
            gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)QysdsNewUtil.queryDjxxByInterfaceDJ(con, gdzcjszjyjqkjbForm, userData);
            //�����ǰ�˻�ȡ��������ҵ����
            gdzcjszjyjqkjbForm.setSshy(gdzcjszjyjqkjbForm.getGjbzhydm());
            //��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(
                    gdzcjszjyjqkjbForm);
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
        return gdzcjszjyjqkjbForm;
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
        int DB_HC;
        for(int i=0;i<13;i++){
        	HashMap rowmap=new HashMap();
        	for(int j=1;j<=11;j++){
    			String yz="";
    			DB_HC=i*11+j;
    			
				if (table.getCellContentList().get(DB_HC+"") != null)
                {
					QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) table
							.getCellContentList().get(DB_HC+"");
					yz=qrtid.getItemValue();
					if (yz == null) {
						yz= "";
					} 
				}
				System.out.println("query yz: "+yz);
				rowmap.put(new GdzcjszjyjqkjbForm().getSb_columns()[j-1], yz);
				
        	}
        	rowmap.put("hc", String.valueOf(i+1));
        	pagelist.add(rowmap);
        }
        
        
        backMap.put("GD", pagelist);
        return backMap;
    }
    /**
     * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
     * ҳ�����ݽṹ-->�ӿ����ݽṹ
     * @param 
     * @return ��ҵ����˰������������
     */
    
    private QysdsReportsDeclare translate2Interface(GdzcjszjyjqkjbForm jbForm) {
//		��ҵ����˰������������
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsNewUtil.setQysdsReport(report, jbForm); //��report ����һϵ�е�����
//		��ҵ����˰�����ڵ�����������
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
        table.setTableName(CodeConstant.TABLE_NAME_GDZCJSZJYJQK_2014);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        List list = jbForm.getGdzcjszjyjqkjbList(); //��Ź̶��е�LIST
        int DB_HC;
        for(int i=0;i<list.size();i++){
        	HashMap rowmap=(HashMap) list.get(i);
        	for(int j=1;j<=11;j++){
        		
    			String yz=(String) rowmap.get(new GdzcjszjyjqkjbForm().getSb_columns()[j-1]);
    			DB_HC=i*11+j;
    			if(yz==null||"".equals(yz.trim())) continue;
    			
    			
    			System.out.println(".............zhangj..yz: "+yz+"dbhc: "+DB_HC);
                //���ù̶��е�һ������
                QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                item.setItemID(DB_HC+"");
                item.setItemValue(yz);
                item.setItemType("11");
                table.getCellContentList().put(item.getItemID(), item);
        	}
        }
        

        saveSshy(jbForm,table);//����ҳ�洫����������ҵ����������ݿ��У�hcΪ��999��(�����ϱ����ݴ���һ�ű���)
        
        report.getTableContentList().put(table.getTableId(),
                QysdsNewUtil.cleanSpace(table));
        return report;
	}
    /**
     * ����ҳ�洫����������ҵ����������ݿ��У�hcΪ��999��(�����ϱ����ݴ���һ�ű���)
     * @param jbForm
     * @param table
     */
    private void saveSshy(GdzcjszjyjqkjbForm jbForm,QysdsReportsTableDeclare table){
        QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
        item.setItemID("999");
        item.setItemValue(jbForm.getSshy());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);
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
    private Object doDelete(VOPackage vo) throws BaseException
    {
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) vo.getData();
        Connection conn = null;
        
        UserData userData=(UserData)vo.getUserData();
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(gdzcjszjyjqkjbForm.getSkssjsrq()));
		// ��ȡ˰���������
		// String sknd = this.getNd(jd, getsbnd, CzzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// ��ҳ����ȡ��˰�������ں����
		String nd = gdzcjszjyjqkjbForm.getSkssksrq().substring(0, 4);
		// ���ü���
		gdzcjszjyjqkjbForm.setQh(jd);
		// �������
		gdzcjszjyjqkjbForm.setSknd(nd);
		gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		gdzcjszjyjqkjbForm.setLrr(userData.getYhid());
        try {
//			��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            
            gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)QysdsNewUtil.queryDjxxByInterfaceDJ(conn, gdzcjszjyjqkjbForm, userData);
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsNewUtil.setQysdsReport(report, gdzcjszjyjqkjbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
            table.setTableName(CodeConstant.TABLE_NAME_GDZCJSZJYJQK_2014);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);
            
        } catch (Exception ex) {
//			�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }

        return gdzcjszjyjqkjbForm;
    }
}
