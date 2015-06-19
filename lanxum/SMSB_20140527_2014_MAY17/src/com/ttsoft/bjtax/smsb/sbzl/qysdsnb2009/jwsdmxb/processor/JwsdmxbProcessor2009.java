/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.web.JwsdmxbForm2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JwsdmxbProcessor2009 implements Processor {

	private final String[] columns = { "L1", "L2", "L3", "L4", "L5", "L6",
			"L7", "L8", "L9", "L10", "L11", "L12", "L13" };
	
	public static void main(String[] args) {
		
		System.out.println("7.1".substring(0,"7.1".indexOf(".")));
		int max=0;
		int num1=7;
		int num2=9;
		max=num1>num2?num1:num2;
		System.out.println(max);
		
		
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		JwsdmxbForm2009 form=new JwsdmxbForm2009();
		JwsdmxbProcessor2009 p=new JwsdmxbProcessor2009();
		QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
		item.setItemID("7.1");
		item.setItemValue("12412341");
		
		QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
		item2.setItemID("7.1");
		item2.setItemValue("12412341");
		
		table.getCellContentList().put(item.getItemID(),item);
		table.getCellContentList().put(item2.getItemID(),item2);
		p.translate2Page(form,table);
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

	public Object process(VOPackage vo) throws BaseException {

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
		case CodeConstant.SMSB_UPDATEACTION:
			result = doUpdate(vo);
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
	 * doShow��ʼ������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doShow(VOPackage vo) throws BaseException {

		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();

		Connection conn = null;

		try {
			// ��ȡ���ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2009.setQysdsReport(report, jwsdmxbForm);

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_6);
			table.setTableName(CodeConstant.TABLE_NAME_2009_6);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			report.getTableContentList().put(table.getTableId(), table);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_6);
			
			jwsdmxbForm=this.translate2Page(jwsdmxbForm,table);
			
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return jwsdmxbForm;
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

	private Object doQuery(VOPackage vo) throws BaseException {

		return null;
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

		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();

		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jwsdmxbForm);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return jwsdmxbForm;
	}

	/**
	 * doCheck   ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * @param   vo ҵ�����
	 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();
		Connection conn = null;
		try {
			//�������ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(jwsdmxbForm);
			
			//��ȡУ��ӿ�
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			
			/*���У��ͨ�������ýӿڱ�������*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0)){
				iApp.saveSingleTable(report);
				//�������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//�������δͨ��
				iApp.updateCheckStatus(report,"");
			}
			
			jwsdmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//�ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return jwsdmxbForm;
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
		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();
		Connection conn = null;
		
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();
			
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jwsdmxbForm);

			// ��ȡ���ݿ�ӿڣ�����delete������������ɾ��
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			
			iApp.updateCheckStatus(report,"");
			
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_6);
			table.setTableId(CodeConstant.TABLE_NAME_2009_6);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(CodeConstant.TABLE_ID_2009_6);
			int []array ={15,26};
			jwsdmxbForm=this.translate2Page(jwsdmxbForm,QysdsUtil2009.putSpace(table,array));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return jwsdmxbForm;
	}

	/**
	 * doUpdate ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doUpdate(VOPackage vo) throws BaseException {

		QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

		return nbForm;
	}

	private QysdsReportsDeclare translate2Interface(JwsdmxbForm2009 form) {

		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();

		QysdsUtil2009.setQysdsReport(report, form);

		// ��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2009_6);
		table.setTableName(CodeConstant.TABLE_NAME_2009_6);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		//������������˰��ֿ۷���
		QysdsReportsItemDeclare itemDK = new QysdsReportsItemDeclare();
		itemDK.setItemID("1");
		itemDK.setItemValue(form.getJwsddk());
		itemDK.setItemType("11");
		table.getCellContentList().put(itemDK.getItemID(),itemDK);
		
		/**
		 * ������������Ҫ����
		 * 1��ֱ�ӵ�������
		 * 2����ӵ�������
		 * 3���ϼ�������
		 */
		
		/**
		 * ֱ�ӵ������ݷ���
		 */
		List zjdmList=form.getZjdmList();
		for(int i=0;i<zjdmList.size();i++){
			Map map=(Map)zjdmList.get(i);
			
			/*��ǰ�д�*/
//			String hc=(String)map.get("zjhc");
			String hc=Integer.toString(i+1);
			((Map)form.getZjdmList().get(i)).put("zjhc",hc);
			Iterator it = map.keySet().iterator();
			
			while (it.hasNext()) {
				/*�ؼ���*/
				String key = (String) it.next();
			
				/*ITEM ID*/
				String itemId="";
				
				if(key.startsWith("ZJDM_L")){
					QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
					String col=key.substring(6);
					/*�����Ͻǵֿ۷���ITEM ID �涨Ϊ1��ֱ�ӵ�������ID��2��ʼ�����к�+1=ITEM ID*/
					itemId=Integer.toString(Integer.parseInt(col)+1);
					
					item.setItemID(itemId+"."+hc);
					item.setItemValue((String)map.get(key));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(), item);
					
				}
			}
			
		}
		
		/**
		 * ��ӵ������ݷ���
		 */
		List jjdmList=form.getJjdmList();
		for(int i=0;i<jjdmList.size();i++){
			Map map=(Map)jjdmList.get(i);
			
			/*��ǰ�д�*/
//			String hc=(String)map.get("jjhc");
			String hc=Integer.toString(i+1);
			((Map)form.getJjdmList().get(i)).put("jjhc",hc);

			Iterator it = map.keySet().iterator();
			
			while (it.hasNext()) {
				/*�ؼ���*/
				String key = (String) it.next();
				
				/*ITEM ID*/
				String itemId="";
				
				if(key.startsWith("JJDM_L")){
					QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
					/*��ǰ��Ԫ������������*/
					String col=key.substring(6);
					/*��ӵ�������ID��19��ʼ�����к�+18=ITEM ID*/
					itemId=Integer.toString(Integer.parseInt(col)+18);
					
					item.setItemID(itemId+"."+hc);
					item.setItemValue((String)map.get(key));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(), item);
				}
			}
		}
		
		/**
		 * ����ϼ�������
		 */
		List list=form.getHjList();
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map)list.get(i);
			
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String)map.get("hjhc"));
			item.setItemValue((String)map.get("hjje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2009.cleanSpace(table));
		return report;
	}
	

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * ��������form����
	 * @param form
	 * @param table
	 * @return
	 */
	private JwsdmxbForm2009 translate2Page(JwsdmxbForm2009 form,QysdsReportsTableDeclare table){
		
		//������������˰��ֿ۷���
		if(table.getCellContentList().get("1")!=null){
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get("1");
			form.setJwsddk(item.getItemValue());
		}
		
		List zjList=new ArrayList();
		List jjList=new ArrayList();
		List hjList=new ArrayList();
		Map zjMap=new HashMap();
		Map jjMap=new HashMap();
		
		
		/*�������ʱʹ��*/
		int curRow=0;
		int zjMaxRow=0;
		int jjMaxRow=0;
		
		Iterator it = table.getCellContentList().keySet().iterator();
		
		/**
		 * ����ѭ��������¹���
		 * 1����ֱ�ӵ��⡢��ӵ��⡢�ϼ������ݷֱ�ժ����,�ϼ�������ժ������ɷ���
		 * 2�������ֱ�ӵ��⡢��ӵ�����������
		 */
		while (it.hasNext()) {
			
			String key = (String) it.next();
			String mainItemID="";
			
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get(key);
			
			if(key.indexOf(".")>0){
				mainItemID=key.substring(0,key.indexOf("."));
				curRow=Integer.parseInt(key.substring(key.indexOf(".")+1));
			}else{
				mainItemID=key;
			}
			
			/**
			 * ֱ�Ӽ��� ITEM ID 2--->18
			 */
			if(Integer.parseInt(mainItemID)>=2 && Integer.parseInt(mainItemID)<=18 ){
				zjMap.put(key,item.getItemValue());
				zjMaxRow=curRow>zjMaxRow?curRow:zjMaxRow;
			}
			
			/**
			 * ��Ӽ��� ITEM ID 19--->30
			 */
			if(Integer.parseInt(mainItemID)>=19 && Integer.parseInt(mainItemID)<=30 ){
				jjMap.put(key,item.getItemValue());
				jjMaxRow=curRow>jjMaxRow?curRow:jjMaxRow;
			}
			
			/**
			 * �ϼ��� ITEM ID 31--->45
			 */
			if(Integer.parseInt(mainItemID)>=31 && Integer.parseInt(mainItemID)<=45 ){
				Map hjMap=new HashMap();
				hjMap.put(key,item.getItemValue());
				hjMap.put("hjhc",item.getItemID());
				hjMap.put("hjje",item.getItemValue());
				hjList.add(hjMap);
			}
			
		}
		
		/**
		 * ����ֱ�ӵ�������
		 */
		for (int i = 1; i <=zjMaxRow; i++) {
			Map map=new HashMap();
			map.put("zjhc",Integer.toString(i));
			Iterator iterator = zjMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				int row=Integer.parseInt(key.substring(key.indexOf(".")+1));
				int id=Integer.parseInt(key.substring(0,key.indexOf(".")));
				if(i==row){
					map.put("ZJDM_L"+(id-1),zjMap.get(key));
				}
			}
			zjList.add(map);
		}
		
		/**
		 * �����ӵ�������
		 */
		for (int i = 1; i <= jjMaxRow; i++) {
			Map map=new HashMap();
			map.put("jjhc",Integer.toString(i));
			Iterator iterator = jjMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				int row=Integer.parseInt(key.substring(key.indexOf(".")+1));
				int id=Integer.parseInt(key.substring(0,key.indexOf(".")));
				if(i==row){
					map.put("JJDM_L"+(id-18),jjMap.get(key));
				}
			}
			jjList.add(map);
		}
		
		form.setZjdmList(zjList);
		form.setJjdmList(jjList);
		form.setHjList(hjList);
		
		return form;
	}


}
