/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zcmxb.processor;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zcmxb.web.ZcmxbForm;
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

public class ZcmxbProcessor implements Processor
{
	/**
	 * ҳ����������
	 */
	private final String[] columns ={"zyjz","xj","zzfy","glfy","yyfy",
			"zjgc","jrqt","zcpjjz","zcjscb","zjtxe","nstze","sjxcy","ndkczjtx",
			"ndjze","sqkce","ljjzhndkc"};
	
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
		ZcmxbForm zcmxbForm = (ZcmxbForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report,zcmxbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_ZCMXB);
			table.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			int[] arrs={1,271};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZCMXB);
			zcmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arrs)));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return zcmxbForm;
	}
	/**
	 * ��ʼ��
	 * @para
	 * @throws BseException
	 */
	private void initForm(ZcmxbForm zcmxbForm) throws BaseException {
		
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
		ZcmxbForm zcmxbForm = (ZcmxbForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(zcmxbForm);
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
		return zcmxbForm;
	}
	
	/**
	 * doCheck   ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * @param   vo ҵ�����
	 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		ZcmxbForm zcmxbForm = (ZcmxbForm) vo.getData();
		Connection conn = null;
		try {
			//�������ݿ�����
			conn = SfDBResource.getConnection();
			
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(zcmxbForm);
			
			//��ȡУ��ӿ�
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
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
			zcmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return zcmxbForm;
	}
	
	/**
	 * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
	 * @param    vo ҵ�����
	 * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException ��������������׳��쳣��Ϣ
	 */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		ZcmxbForm zcmxbForm = (ZcmxbForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(zcmxbForm);
			//��ȡ���ݿ�ӿڣ�����delete������������ɾ��,iApp����һ��report����
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_ZCMXB);
			table.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int[] arrs={1,271};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZCMXB);
			zcmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arrs)));
		}catch (Exception ex) { 
			//�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return zcmxbForm;
	}
	
	
	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param ZcmxbForm 
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(ZcmxbForm form){
		
		//��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report,form);
		
		//��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_ZCMXB);
		table.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=form.getDataList();
		for(int i=0;i<list.size();i++){
			Map rowMap=(HashMap)list.get(i);
			if(i<10){
				for(int j=1;j<=16;j++){
					String itemID=String.valueOf(i*16+j);
					QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
					item.setItemID(itemID);
					item.setItemValue((String)rowMap.get(columns[j-1]));
					table.getCellContentList().put(item.getItemID(),item); 
				}
			}else if(i>10){
				for(int j=1;j<=16;j++){
					String itemID=String.valueOf(i*16+j-1);
					QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
					item.setItemID(itemID);
					item.setItemValue((String)rowMap.get(columns[j-1]));
					table.getCellContentList().put(item.getItemID(),item); 
				}
			}else if(i==10){
				for(int j=1;j<=16;j++){
					if(j<=9){
						String itemID=String.valueOf(i*16+j);
						QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
						item.setItemID(itemID);
						item.setItemValue((String)rowMap.get(columns[j-1]));
						table.getCellContentList().put(item.getItemID(),item); 
					}else if(j>10){
						String itemID=String.valueOf(i*16+j-1);
						QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
						item.setItemID(itemID);
						item.setItemValue((String)rowMap.get(columns[j-1]));
						table.getCellContentList().put(item.getItemID(),item); 
					}
				}
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
	private List translate2Page(QysdsReportsTableDeclare table)
	{
		List list=new ArrayList();
		Map map1=new HashMap();
		Map map2=new HashMap();
		Map map3=new HashMap();
		Map map4=new HashMap();
		Map map5=new HashMap();
		Map map6=new HashMap();
		Map map7=new HashMap();
		Map map8=new HashMap();
		Map map9=new HashMap();
		Map map10=new HashMap();
		Map map11=new HashMap();
		Map map12=new HashMap();
		Map map13=new HashMap();
		Map map14=new HashMap();
		Map map15=new HashMap();
		Map map16=new HashMap();
		Map map17=new HashMap();
		
		Iterator it =table.getCellContentList().keySet().iterator();
		while (it.hasNext())
		{
			String key=(String)it.next();
			int iKey=Integer.parseInt(key);
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get(key); 
			if(iKey<=169)
			{
				//��ǰITEM������
				int row=0;
				//��ǰITEM������
				int col=iKey%16;
				if(col==0){
					row=(iKey/16);
				}else{
					row=(iKey/16+1);
				}
				
				if(col==0){
					col=16;
				}

				//��һ��
				if(row==1){
					map1.put(columns[col-1],item.getItemValue());
				}
				//�ڶ���
				if(row==2){
					map2.put(columns[col-1],item.getItemValue());
				}
				//������
				if(row==3){
					map3.put(columns[col-1],item.getItemValue());
				}
				if(row==4){
					map4.put(columns[col-1],item.getItemValue());
				}
				if(row==5){
					map5.put(columns[col-1],item.getItemValue());
				}
				if(row==6){
					map6.put(columns[col-1],item.getItemValue());
				}
				if(row==7){
					map7.put(columns[col-1],item.getItemValue());
				}
				if(row==8){
					map8.put(columns[col-1],item.getItemValue());
				}
				if(row==9){
					map9.put(columns[col-1],item.getItemValue());
				}
				if(row==10){
					map10.put(columns[col-1],item.getItemValue());
				}
				if(row==11){
					map11.put(columns[col-1],item.getItemValue());
				}
				
			}
			else
			{
				//��ǰITEM������
				int col=(iKey+1)%16;
				//��ǰITEM������
				int row=0;
				if(col==0){
					row=((iKey+1)/16);
				}else{
					row=((iKey+1)/16+1);
				}
				if(col==0){
					col=16;
				}
				
				//��һ��
				if(row==11){
					map11.put(columns[col-1],item.getItemValue());
				}
				//�ڶ���
				if(row==12){
					map12.put(columns[col-1],item.getItemValue());
				}
				if(row==13){
					map13.put(columns[col-1],item.getItemValue());
				}
				if(row==14){
					map14.put(columns[col-1],item.getItemValue());
				}
				if(row==15){
					map15.put(columns[col-1],item.getItemValue());
				}
				if(row==16){
					map16.put(columns[col-1],item.getItemValue());
				}
				if(row==17){
					map17.put(columns[col-1],item.getItemValue());
				}
			}
		}
		map11.put("zjtxe","*");
		map1.put("hc","1");
		map2.put("hc","2");
		map3.put("hc","3");
		map4.put("hc","4");
		map5.put("hc","5");
		map6.put("hc","6");
		map7.put("hc","7");
		map8.put("hc","8");
		map9.put("hc","9");
		map10.put("hc","10");
		map11.put("hc","11");
		map12.put("hc","12");
		map13.put("hc","13");
		map14.put("hc","14");
		map15.put("hc","15");
		map16.put("hc","16");
		map17.put("hc","17");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);
		list.add(map9);
		list.add(map10);
		list.add(map11);
		list.add(map12);
		list.add(map13);
		list.add(map14);
		list.add(map15);
		list.add(map16);
		list.add(map17);
		for(int h=0;h<list.size();h++)
		{
			HashMap mapcs=(HashMap)list.get(h);
			String hc=(String)mapcs.get("hc");
			String je1=(String)mapcs.get("zyjz");
			String je2=(String)mapcs.get("xj");
			String je3=(String)mapcs.get("zzfy");
			String je4=(String)mapcs.get("glfy");
			String je5=(String)mapcs.get("yyfy");
			String je6=(String)mapcs.get("zjgc");
			String je7=(String)mapcs.get("jrqt");
			String je8=(String)mapcs.get("zcpjjz");
			String je9=(String)mapcs.get("zcjscb");
			String je10=(String)mapcs.get("zjtxe");
			String je11=(String)mapcs.get("nstze");
			String je12=(String)mapcs.get("sjxcy");
			String je13=(String)mapcs.get("ndkczjtx");
			String je14=(String)mapcs.get("ndjze");
			String je15=(String)mapcs.get("sqkce");
			String je16=(String)mapcs.get("ljjzhndkc");
			System.out.println("hc-"+hc+"  zyjz-"+je1 +"  xj-"+je2+" zzfy-"+je3+"  glfy-"+je4+"  yyfy-"+je5+"  zjgc-"+je6+"  jrqt-"+je7+"  zcpjjz-"+je8+" zcjscb-"+je9+" zjtxe-"+je10+"nstze-"+je11+"sjxcy-"+je12+"ndkczjtx-"+je13+"ndjze-"+je14+"sqkce-"+je15+" ljjzhndkc-"+je16);
		}

		return list;
	}
	
	
}
