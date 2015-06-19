/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.processor;

import java.sql.Connection;
import java.util.ArrayList;
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
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web.BxzbjForm;
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

public class BxzbjbProcessor implements Processor
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
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();
			
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report,bxzbjForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
			table.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(table.getTableId());
			int []arra={1,78,83,100};
			HashMap map=translate2Page(QysdsNewUtil.putSpace(table,arra));
			bxzbjForm.setDataList((ArrayList)map.get("pagelist"));
			bxzbjForm.setQtzbjList((ArrayList)map.get("qtzbjlist"));
			bxzbjForm.setQtList((ArrayList)map.get("qtlist"));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		
		return bxzbjForm;
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
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(bxzbjForm);
			
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
		return bxzbjForm;
	}
	
	/**
	 * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * @param   vo ҵ�����
	 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			//�������ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(bxzbjForm);
			
			 //��ȡУ��ӿ�
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			
			/*���У��ͨ�������ýӿڱ�������*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0)){
				iApp.saveSingleTable(report);
				//�������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//�������δͨ��
				iApp.updateCheckStatus(report,"");
			}
			bxzbjForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//�ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return bxzbjForm;
	}
	
	/**
	 * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
	 * @param    vo ҵ�����
	 * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	private Object doDelete(VOPackage vo) throws BaseException 
	{
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(bxzbjForm);
			//��ȡ���ݿ�ӿڣ�����Delete������������ɾ��
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);	
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
			table.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);		
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_BXZBJ);
			//ȡlist
			int []arra={1,78,83,100};
			HashMap map=translate2Page(QysdsNewUtil.putSpace(table,arra));
			bxzbjForm.setDataList((ArrayList)map.get("pagelist"));
			bxzbjForm.setQtzbjList((ArrayList)map.get("qtzbjlist"));
			bxzbjForm.setQtList((ArrayList)map.get("qtlist"));
		} catch (Exception ex) {
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return bxzbjForm;
	}
	
	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param bxzbjForm 
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(BxzbjForm form){
		
		//��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report,form);
		
		//��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
		table.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=form.getDataList();
		for(int i=0;i<list.size();i++){
			HashMap map = (HashMap)list.get(i);
			int hc = Integer.parseInt((String)map.get("hc"));
			if (hc<27){
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID(String.valueOf(hc*3-2));
				item_1.setItemValue((String)map.get("sjfse"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
				
				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID(String.valueOf(hc*3-1));
				item_2.setItemValue((String)map.get("sfgdkce"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
				
				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID(String.valueOf(hc*3));
				item_3.setItemValue((String)map.get("tze"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
			
			if(hc>26&&hc<33){
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID(String.valueOf(hc*3+4-2));
				item_1.setItemValue((String)map.get("sjfse"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
				
				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID(String.valueOf(hc*3+4-1));
				item_2.setItemValue((String)map.get("sfgdkce"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
				
				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID(String.valueOf(hc*3+4));
				item_3.setItemValue((String)map.get("tze"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);				
			}			
		}
		
		
		
		List qtzbjList=this.filterList(form.getQtzbjList(),"qtzbj_xm", "qtzbj_sjfse", "qtzbj_sfgdkce","qtzbj_tze");		
		for(int i=0;i<qtzbjList.size();i++){
			HashMap map=(HashMap)qtzbjList.get(i);
			if(qtzbjList.size()==1){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("79");
				item_1.setItemValue((String)map.get("qtzbj_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("80");
				item_2.setItemValue((String)map.get("qtzbj_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("81");
				item_3.setItemValue((String)map.get("qtzbj_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("82");
				item_4.setItemValue((String)map.get("qtzbj_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("79"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("qtzbj_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("80"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("qtzbj_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("81"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("qtzbj_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("82"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("qtzbj_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
			}
		}
		
		List qtList=this.filterList(form.getQtList(),"qt_xm", "qt_sjfse", "qt_sfgdkce","qt_tze");		
		for(int i=0;i<qtList.size();i++){
			HashMap map=(HashMap)qtList.get(i);
			if(qtList.size()==1){
				
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("101");
				item_1.setItemValue((String)map.get("qt_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("102");
				item_2.setItemValue((String)map.get("qt_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("103");
				item_3.setItemValue((String)map.get("qt_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("104");
				item_4.setItemValue((String)map.get("qt_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("101"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("qt_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("102"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("qt_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("103"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("qt_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("104"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("qt_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
			}
		}		
		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));
		
		//		������
		Iterator it= table.getCellContentList().keySet().iterator();		
		while(it.hasNext()){
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get((String)it.next());
			System.out.println(item.getItemID()+"  ------------- "+item.getItemValue());
		}
		return report;
	}
	
	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
	 * �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * @param QysdsReportsTableDeclare
	 * @return ��ҵ����˰������������
	 */
	private HashMap translate2Page(QysdsReportsTableDeclare table){
		
		//ȡ�̶��е����� list ���л��� 
		List pagelist = new ArrayList();
		for(int i = 1;i<=26;i++){
			HashMap stat_map = new HashMap();
			stat_map.put("hc",String.valueOf(i));
			int a = 3*i;
			int b = 3*i-1;
			int c = 3*i-2;
			stat_map.put("tze",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(a))).getItemValue());
			stat_map.put("sfgdkce",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(b))).getItemValue());
			stat_map.put("sjfse",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(c))).getItemValue());
			pagelist.add(stat_map);		
		}
		
		for(int i = 27;i<=32;i++){
			HashMap stat_map = new HashMap();
			stat_map.put("hc",String.valueOf(i));
			int a = 3*i+4;
			int b = 3*i+4-1;
			int c = 3*i+4-2;
			stat_map.put("tze",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(a))).getItemValue());
			stat_map.put("sfgdkce",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(b))).getItemValue());
			stat_map.put("sjfse",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(c))).getItemValue());
			pagelist.add(stat_map);		
		}
		
		
		
		//���table.getCellContentList()��ʱmap�е����������д�ȡ�ġ�
		Map qtzbj_1 = this.getCellMap(table, "79");
		Map qtzbj_2 = this.getCellMap(table, "80");
		Map qtzbj_3 = this.getCellMap(table, "81");
		Map qtzbj_4 = this.getCellMap(table, "82");
		
		Map qt_1 = this.getCellMap(table, "101");
		Map qt_2 = this.getCellMap(table, "102");
		Map qt_3 = this.getCellMap(table, "103");
		Map qt_4 = this.getCellMap(table, "104");
		
		//����Ϊ�ȳ���Map,���õ���Ҫ���Ե�ҳ�����������Ӧ��List
		List qtzbjlist=this.processMap(qtzbj_1, qtzbj_2, qtzbj_3,qtzbj_4,
				"79","80","81","82",
				"qtzbj_xm","qtzbj_sjfse","qtzbj_sfgdkce","qtzbj_tze",
		"2");
		List qtlist=this.processMap(qt_1, qt_2, qt_3,qt_4,
				"101","102","103","104",
				"qt_xm","qt_sjfse","qt_sfgdkce","qt_tze",
		"4");
		
		//�����л��Ե�ҳ�����������Ӧ��List ����һ��map����
		HashMap totalmap = new HashMap();
		totalmap.put("pagelist", pagelist);
		totalmap.put("qtzbjlist", qtzbjlist);
		totalmap.put("qtlist", qtlist);
		
		return totalmap;
	}
	
	
	//�����е�Ԫ����з���,�������е�Ԫ���Ӧ��map
	private HashMap getCellMap(QysdsReportsTableDeclare table,String flag)
	{
		HashMap map = new HashMap();		
		Iterator it = table.getCellContentList().keySet().iterator();
		
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				if(flag.equals(key)){
					map.put(key, table.getCellContentList().get(key));
					System.out.println("flag----"+flag+"          key--------"+key);
				}
			}else if(flag.equals(key.substring(0, key.indexOf(".")))){
				String est=key.substring(0, key.indexOf("."));
				System.out.println("est ----------"+est);
				map.put(key, table.getCellContentList().get(key));
				System.out.println("flag----"+flag+"          key--------"+key);			
			}			
		}
		
		return map;		
	}
	
	//�˷����������ж�Ӧ��List
	private List processMap(Map map1,Map map2,Map map3,Map map4,
			String keyFlag1,String keyFlag2,String keyFlag3,String keyFlag4,
			String L1,String L2,String L3,String L4,String off ){
		
		boolean flagMuti=false;
		List list=new ArrayList();
		
		//���ĸ�map����������Ӧ�����������ж�
		Iterator it = map1.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map2.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map3.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map4.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		//��������ж�����Ϊ��,������������ȫ��Ϊ��,���������
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0&&map4.size()==0){
			/**
			 * @todo
			 * ��ͬһ��map�����4����ֵ
			 */
			for (int i=0;i<Integer.parseInt(off);i++){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "");
				list.add(rowMap);
			}		
			return list;			
			
			//���������ж�����,������ֻ��һ��,����������
		}else if(flagMuti==false){
			//��һ�����ݺ�һ������
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			rowMap.put(L3, map3.get(keyFlag3)==null?"":((QysdsReportsItemDeclare)map3.get(keyFlag3)).getItemValue());
			rowMap.put(L4, map4.get(keyFlag4)==null?"":((QysdsReportsItemDeclare)map4.get(keyFlag4)).getItemValue());
			list.add(rowMap);
			for(int j=1;j<Integer.parseInt(off);j++){
				Map rowMap1=new HashMap();
				rowMap1.put(L1, "");
				rowMap1.put(L2, "");
				rowMap1.put(L3, "");
				rowMap1.put(L4, "");
				list.add(rowMap1);
			}
			return list;
		}
		
		//������������ж�����,������Ϊ���� ,���������ݲ���
		if(flagMuti==true){
			int max=0;
			String[] arr;
			String temp=new String("0");
			//ȡmap1���±�����ֵ,�����е���Ŀ
			if(map1.size()!=0){
				it = map1.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				max=getMax(arr);
			}
			//ȡmap2���±����ֵ,����map1�е��±�Ƚ�,ȡ�����еĴ�ֵ
			if(map2.size()!=0){
				it = map2.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max2=this.getMax(arr);
				if (max<=max2){
					max=max2;
				}
			}
			//ȡmap3���±����ֵ,����ǰ�߱Ƚ�,ȡ�����еĴ�ֵ
			if(map3.size()!=0){
				it = map3.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max3=this.getMax(arr);
				if (max<=max3){
					max=max3;
				}
			}
//			ȡmap4���±����ֵ,����ǰ�߱Ƚ�,ȡ�����еĴ�ֵ
			if(map4.size()!=0){
				it = map4.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max4=this.getMax(arr);
				if (max<=max4){
					max=max4;
				}
			}
			//�������е�����
			for(int i=0;i<max;i++){
				Map rowMap=new HashMap();
				String key1=keyFlag1+"."+String.valueOf(i+1);
				String key2=keyFlag2+"."+String.valueOf(i+1);
				String key3=keyFlag3+"."+String.valueOf(i+1);
				String key4=keyFlag4+"."+String.valueOf(i+1);
				rowMap.put(L1, map1.get(key1)==null?"":((QysdsReportsItemDeclare)map1.get(key1)).getItemValue());
				rowMap.put(L2, map2.get(key2)==null?"":((QysdsReportsItemDeclare)map2.get(key2)).getItemValue());
				rowMap.put(L3, map3.get(key3)==null?"":((QysdsReportsItemDeclare)map3.get(key3)).getItemValue());
				rowMap.put(L4, map4.get(key4)==null?"":((QysdsReportsItemDeclare)map4.get(key4)).getItemValue());
				list.add(rowMap);								
			}
			if(off.equals("4")){
				for(int h=0;h<4-max;h++){
					Map rowMap=new HashMap();
					rowMap.put(L1, "");
					rowMap.put(L2, "");
					rowMap.put(L3, "");
					rowMap.put(L4, "");
					list.add(rowMap);
				}
			}
		}
		
		return list;
	}
	
	//ȡ������������Ԫ��map�±�����ֵ,�Դ�ֵΪ׼�������е�����
	private  int getMax(String[] arr){
		int i=arr.length;
		int temp=0;
		for (int j=0;j<i;j++){
			if(temp<Integer.parseInt(arr[j])){
				temp=Integer.parseInt(arr[j]);
			}			
		}
		return temp;
	}
	
	
	
	/**
	 * ����ҳ��List�еĿ�ֵ
	 * @param list
	 */
	private List filterList(List list,String L1,String L2,String L3,String L4){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String strL1=(String)map.get(L1);
			String strL2=(String)map.get(L2);
			String strL3=(String)map.get(L3);	
			String strL4=(String)map.get(L4);
			if(!strL1.equals("")||!strL2.equals("")||!strL3.equals("")||!strL4.equals("")){
				rtnList.add(map);
			}
		}		
		return rtnList;
	}	
}
