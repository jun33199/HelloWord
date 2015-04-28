/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hzssmxb.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hzssmxb.web.HzssmxbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class HzssmxbProcessor implements Processor 
{
	/**
	 * ʵ��Processor�ӿ�
	 * @param vo ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException ҵ���쳣
	 * 		1 ���������Ĳ������Ͳ���ʱ�׳�
	 * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 * 	�����쳣�׳���EJB��process��������
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
	
	private Object doShow(VOPackage vo) throws BaseException 
	{
		HzssmxbForm hzssmxbForm = (HzssmxbForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();
			
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report,hzssmxbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_HZSSMXB);
			table.setTableName(CodeConstant.TABLE_NAME_HZSSMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZSSMXB);
			int []arra={1,16};
			hzssmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arra)));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		
		return hzssmxbForm;
	}
	
	/**
	 * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	 * @param     vo ҵ�����
	 * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException    ��������������׳��쳣��Ϣ
	 *
	 */
	
	private Object doQuery(VOPackage vo) throws BaseException {
		
		return null;
	}
	
	/**
	 * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * @param   vo ҵ�����
	 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	
	private Object doSave(VOPackage vo) throws BaseException
	{
		HzssmxbForm form = (HzssmxbForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(form);
			
			//��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		}catch (Exception ex) { 
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
		
	}
	
	/**
	 * doCheck  ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * @param   vo ҵ�����
	 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		HzssmxbForm hzssmxbForm = (HzssmxbForm) vo.getData();
		Connection conn = null;
		try {
			//�������ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(hzssmxbForm);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			
			 //��ȡУ��ӿ�
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			/*���У��ͨ�������ýӿڱ�������*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0))
			{
				iApp.saveSingleTable(report);
				//�������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//�������δͨ��
				iApp.updateCheckStatus(report,"");
			}
			hzssmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//�ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return hzssmxbForm;
	}
	/**
	 * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
	 * @param    vo ҵ�����
	 * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	
	private Object doDelete(VOPackage vo) throws BaseException 
	{
		HzssmxbForm hzssmxbForm = (HzssmxbForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(hzssmxbForm);
			
			//��ȡ���ݿ�ӿڣ�����delete������������ɾ��,iApp����һ��report����
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_HZSSMXB);
			table.setTableName(CodeConstant.TABLE_NAME_HZSSMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int []arra={1,16};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_HZSSMXB);
			hzssmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arra)));
		}catch (Exception ex) { 
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return hzssmxbForm;
	}
	
	/**
	 * doUpdate  ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * @param    vo ҵ�����
	 * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	
	private Object doUpdate(VOPackage vo) throws BaseException {
		
		QysdsnbForm nbForm = (QysdsnbForm) vo.getData();
		
		return nbForm;
	}
	
	/**
	 * ��ʼ��
	 * @param nbForm ��������
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	
	private void initForm(QysdsnbForm nbForm) throws BaseException {
		
	}
	
	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param HzssmxbForm 
	 * @return ��ҵ����˰������������
	 */
	
	private QysdsReportsDeclare translate2Interface(HzssmxbForm form){
		//��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report,form);
		//��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_HZSSMXB);
		table.setTableName(CodeConstant.TABLE_NAME_HZSSMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		List list=form.getDataList();
		for(int i=0;i<list.size();i++){
			HashMap map=(HashMap)list.get(i);
			
			String hc=(String)map.get("hc");
			if("1".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("1");
				item_1_1.setItemValue((String)map.get("kjje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("2");
				item_1_2.setItemValue((String)map.get("ssje"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
			}	
			if("2".equals(hc)){
				QysdsReportsItemDeclare item_2_1=new QysdsReportsItemDeclare();
				item_2_1.setItemID("3");
				item_2_1.setItemValue((String)map.get("kjje"));
				item_2_1.setItemType("11");
				table.getCellContentList().put(item_2_1.getItemID(),item_2_1);
				
				QysdsReportsItemDeclare item_2_2=new QysdsReportsItemDeclare();
				item_2_2.setItemID("4");
				item_2_2.setItemValue((String)map.get("ssje"));
				item_2_2.setItemType("11");
				table.getCellContentList().put(item_2_2.getItemID(),item_2_2);
				
				QysdsReportsItemDeclare item_2_3=new QysdsReportsItemDeclare();
				item_2_3.setItemID("5");
				item_2_3.setItemValue((String)map.get("nstze"));
				item_2_3.setItemType("11");
				table.getCellContentList().put(item_2_3.getItemID(),item_2_3);
			}
			if("3".equals(hc)){
				QysdsReportsItemDeclare item_3_1=new QysdsReportsItemDeclare();
				item_3_1.setItemID("6");
				item_3_1.setItemValue((String)map.get("kjje"));
				item_3_1.setItemType("11");
				table.getCellContentList().put(item_3_1.getItemID(),item_3_1);
				
				QysdsReportsItemDeclare item_3_2=new QysdsReportsItemDeclare();
				item_3_2.setItemID("7");
				item_3_2.setItemValue((String)map.get("ssje"));
				item_3_2.setItemType("11");
				table.getCellContentList().put(item_3_2.getItemID(),item_3_2);
				
				QysdsReportsItemDeclare item_3_3=new QysdsReportsItemDeclare();
				item_3_3.setItemID("8");
				item_3_3.setItemValue((String)map.get("nstze"));
				item_3_3.setItemType("11");
				table.getCellContentList().put(item_3_3.getItemID(),item_3_3);
			}
			
			if("4".equals(hc)){
				QysdsReportsItemDeclare item_4_1=new QysdsReportsItemDeclare();
				item_4_1.setItemID("9");
				item_4_1.setItemValue((String)map.get("kjje"));
				item_4_1.setItemType("11");
				table.getCellContentList().put(item_4_1.getItemID(),item_4_1);
				
				QysdsReportsItemDeclare item_4_2=new QysdsReportsItemDeclare();
				item_4_2.setItemID("10");
				item_4_2.setItemValue((String)map.get("ssje"));
				item_4_2.setItemType("11");
				table.getCellContentList().put(item_4_2.getItemID(),item_4_2);
				
				QysdsReportsItemDeclare item_4_3=new QysdsReportsItemDeclare();
				item_4_3.setItemID("11");
				item_4_3.setItemValue((String)map.get("nstze"));
				item_4_3.setItemType("11");
				table.getCellContentList().put(item_4_3.getItemID(),item_4_3);
			}
			
			if("5".equals(hc)){
				QysdsReportsItemDeclare item_5_1=new QysdsReportsItemDeclare();
				item_5_1.setItemID("12");
				item_5_1.setItemValue((String)map.get("kjje"));
				item_5_1.setItemType("11");
				table.getCellContentList().put(item_5_1.getItemID(),item_5_1);
				
				QysdsReportsItemDeclare item_5_2=new QysdsReportsItemDeclare();
				item_5_2.setItemID("13");
				item_5_2.setItemValue((String)map.get("ssje"));
				item_5_2.setItemType("11");
				table.getCellContentList().put(item_5_2.getItemID(),item_5_2);
				
				QysdsReportsItemDeclare item_5_3=new QysdsReportsItemDeclare();
				item_5_3.setItemID("14");
				item_5_3.setItemValue((String)map.get("nstze"));
				item_5_3.setItemType("11");
				table.getCellContentList().put(item_5_3.getItemID(),item_5_3);
			}
			if("6".equals(hc)){
				QysdsReportsItemDeclare item_6_1=new QysdsReportsItemDeclare();
				item_6_1.setItemID("15");
				item_6_1.setItemValue((String)map.get("kjje"));
				item_6_1.setItemType("11");
				table.getCellContentList().put(item_6_1.getItemID(),item_6_1);
				
				QysdsReportsItemDeclare item_6_2=new QysdsReportsItemDeclare();
				item_6_2.setItemID("16");
				item_6_2.setItemValue((String)map.get("ssje"));
				item_6_2.setItemType("11");
				table.getCellContentList().put(item_6_2.getItemID(),item_6_2);
			}
		}
		report.getTableContentList().put(table.getTableId(), QysdsNewUtil.cleanSpace(table));
		return report;
		
	}
	
	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
	 * �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * @param QysdsReportsTableDeclare
	 * @return ��ҵ����˰������������
	 */
	private List translate2Page(QysdsReportsTableDeclare table){
		List list=new ArrayList();
		Map map1=new HashMap();
		Map map2=new HashMap();
		Map map3=new HashMap();
		Map map4=new HashMap();
		Map map5=new HashMap();
		Map map6=new HashMap();
		Map map=table.getCellContentList();
		map1.put("hc","1");
		QysdsReportsItemDeclare kjje_1_1=(QysdsReportsItemDeclare)map.get("1");
		String kjje1=kjje_1_1.getItemValue();
		map1.put("kjje",kjje1);
		QysdsReportsItemDeclare ssje_1_2=(QysdsReportsItemDeclare)map.get("2");
		String ssje1=ssje_1_2.getItemValue();
		map1.put("ssje",ssje1);
		map1.put("nstze","*");
		list.add(map1);
		
		map2.put("hc","2");
		QysdsReportsItemDeclare kjje_2_1=(QysdsReportsItemDeclare)map.get("3");
		String kjje2=kjje_2_1.getItemValue();
		map2.put("kjje",kjje2);
		QysdsReportsItemDeclare ssje_2_2=(QysdsReportsItemDeclare)map.get("4");
		String ssje2=ssje_2_2.getItemValue();
		map2.put("ssje",ssje2);
		QysdsReportsItemDeclare nstze_2_3=(QysdsReportsItemDeclare)map.get("5");
		String nstze2=nstze_2_3.getItemValue();
		map2.put("nstze",nstze2);
		list.add(map2);
		
		map3.put("hc","3");
		QysdsReportsItemDeclare kjje_3_1=(QysdsReportsItemDeclare)map.get("6");
		String kjje3=kjje_3_1.getItemValue();
		map3.put("kjje",kjje3);
		QysdsReportsItemDeclare ssje_3_2=(QysdsReportsItemDeclare)map.get("7");
		String ssje3=ssje_3_2.getItemValue();
		map3.put("ssje",ssje3);
		QysdsReportsItemDeclare nstze_3_3=(QysdsReportsItemDeclare)map.get("8");
		String nstze3=nstze_3_3.getItemValue();
		map3.put("nstze",nstze3);
		list.add(map3);
		
		map4.put("hc","4");
		QysdsReportsItemDeclare kjje_4_1=(QysdsReportsItemDeclare)map.get("9");
		String kjje4=kjje_4_1.getItemValue();
		map4.put("kjje",kjje4);
		QysdsReportsItemDeclare ssje_4_2=(QysdsReportsItemDeclare)map.get("10");
		String ssje4=ssje_4_2.getItemValue();
		map4.put("ssje",ssje4);
		QysdsReportsItemDeclare nstze_4_3=(QysdsReportsItemDeclare)map.get("11");
		String nstze4=nstze_4_3.getItemValue();
		map4.put("nstze",nstze4);
		list.add(map4);
		
		map5.put("hc","5");
		QysdsReportsItemDeclare kjje_5_1=(QysdsReportsItemDeclare)map.get("12");
		String kjje5=kjje_5_1.getItemValue();
		map5.put("kjje",kjje5);
		QysdsReportsItemDeclare ssje_5_2=(QysdsReportsItemDeclare)map.get("13");
		String ssje5=ssje_5_2.getItemValue();
		map5.put("ssje",ssje5);
		QysdsReportsItemDeclare nstze_5_3=(QysdsReportsItemDeclare)map.get("14");
		String nstze5=nstze_5_3.getItemValue();
		map5.put("nstze",nstze5);
		list.add(map5);
		
		map6.put("hc","6");
		
		QysdsReportsItemDeclare kjje_6_1=(QysdsReportsItemDeclare)map.get("15");
		String kjje6=kjje_6_1.getItemValue();
		map6.put("kjje",kjje6);
		
		QysdsReportsItemDeclare ssje_6_2=(QysdsReportsItemDeclare)map.get("16");
		String ssje6=ssje_6_2.getItemValue();
		map6.put("ssje",ssje6);
		
		map6.put("nstze","*");
		list.add(map6);
		
		for(int i=0;i<list.size();i++)
		{
			HashMap mapcs=(HashMap)list.get(i);
			String hc=(String)mapcs.get("hc");
			String kjje=(String)mapcs.get("kjje");
			String ssje=(String)mapcs.get("ssje");
			String nstze=(String)mapcs.get("nstze");
			System.out.println("hc-"+hc+"    kjje-"+kjje +"   sjje-"+ssje+"   nstze-"+nstze);
			
		}
		
		return list;
		
		
	}
	
}

