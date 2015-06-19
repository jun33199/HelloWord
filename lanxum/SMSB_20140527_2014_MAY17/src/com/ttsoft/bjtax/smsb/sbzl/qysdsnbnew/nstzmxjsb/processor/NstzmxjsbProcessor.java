/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxjsb.processor;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxjsb.web.NstzmxjsbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.Constants;
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author ����ϼ
 * @version 1.1
 */

public class NstzmxjsbProcessor
implements Processor 
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

		NstzmxjsbForm nbForm = (NstzmxjsbForm) vo.getData();
		Connection conn = null;
		try
		{
//			��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_NSTZMXJSB);
			table.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			int[] arrs={1,17,20,20};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_NSTZMXJSB);
			
			List listgd = (List)this.translate2Page( QysdsNewUtil.putSpace(table,arrs)).get("GD");
			List listdt = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("DT");
			nbForm.setNstzjs_List(listdt);
			nbForm.setDataList(listgd);
		}catch(Exception ex)
		{
//			�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
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
	private Object doSave(VOPackage vo) throws BaseException
	{
		
		NstzmxjsbForm nstzmxjsbForm = (NstzmxjsbForm)vo.getData();
		Connection con = null;
		try
		{
			//��ȡ���ݿ�����
			con = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(nstzmxjsbForm);

//			��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
//			�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally 
		{
			SfDBResource.freeConnection(con);
		}

		return nstzmxjsbForm;

	}
	/**
	 * doDelete    ����ɾ��ҳ������
	 * @param     vo ҵ�����
	 * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException    ��������������׳��쳣��Ϣ
	 */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		NstzmxjsbForm form = (NstzmxjsbForm)vo.getData();
		Connection conn = null;
		try
		{
//			��ȡ���ݿ�����

			conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(form);
//			��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			try
			{
				iApp.deleteSingleTable(report);	
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
				
				table.setTableId(CodeConstant.TABLE_ID_NSTZMXJSB);
				table.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_NSTZMXJSB);
				int[] arrs={1,17,20,20};
				List listgd = (List)this.translate2Page( QysdsNewUtil.putSpace(table,arrs)).get("GD");
				List listdt = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("DT");
				form.setNstzjs_List(listdt);
				form.setDataList(listgd);
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}catch(Exception ex)
		{
		   ex.printStackTrace();
		   throw ExceptionUtil.getBaseException(ex);
		}finally
		{
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
	private Object doCheck(VOPackage vo)throws BaseException
	{
		NstzmxjsbForm nstzmxjsbForm = (NstzmxjsbForm)vo.getData();
		Connection con = null;
		try
		{
//			�������ݿ�����
			con = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(nstzmxjsbForm);
			/**
			 * ����У��
			 * ͨ�������ִ�У�
			 * δͨ���˳�ִ�У�ҳ����ʾУ������Ϣ
			 */
			Checker checker=CheckerFactory.getAInstance(con,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			nstzmxjsbForm.setCheckList(listSingle);
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
		}catch(Exception ex)
		{
//			�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
//			�ͷ����ݿ�����
			SfDBResource.freeConnection(con);
		}
		return nstzmxjsbForm;
	}


	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param ZcmxbForm 
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(NstzmxjsbForm nbForm)
	{
//		��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report, nbForm); //��report ����һϵ�е�����

//		��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_NSTZMXJSB);
		table.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=nbForm.getDataList(); //��Ź̶�������
		List dynaList = this.filterList(nbForm.getNstzjs_List(), "xm", "nstzjs_je");
		//����̶���
		for(int i=0;i<list.size();i++)
		{
			HashMap map = (HashMap)list.get(i);
			String hc = (String) map.get("hc");

			if ("18".equals(hc)){
				QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
				item.setItemID("20");
				item.setItemValue((String)map.get("je"));
				item.setItemType("11");
				table.getCellContentList().put(item.getItemID(),item);
			}else{
				QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
				item.setItemID(hc);
				item.setItemValue((String)map.get("je"));
				item.setItemType("11");
				table.getCellContentList().put(item.getItemID(),item);
			}
		}

	//���붯̬�е�����
		int num = dynaList.size();
		for(int i=0;i<num;i++)
		{
			HashMap map = (HashMap)dynaList.get(i);
			if(num==1)
			{
				QysdsReportsItemDeclare item1=new QysdsReportsItemDeclare();
				item1.setItemID("18");
				item1.setItemValue((String)map.get("xm"));
				item1.setItemType("11");
				table.getCellContentList().put(item1.getItemID(),item1);
				
				QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
				item2.setItemID("19");
				item2.setItemValue((String)map.get("nstzjs_je"));
				item2.setItemType("11");
				table.getCellContentList().put(item2.getItemID(),item2);
			}else
			{
				QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
				item.setItemID("18."+String.valueOf(i+1));
				item.setItemValue((String)map.get("xm"));
				item.setItemType("11");
				table.getCellContentList().put(item.getItemID(),item);
				
				QysdsReportsItemDeclare item1=new QysdsReportsItemDeclare();
				item1.setItemID("19."+String.valueOf(i+1));
				item1.setItemValue((String)map.get("nstzjs_je"));
				item1.setItemType("11");
				table.getCellContentList().put(item1.getItemID(),item1);
			}
		}

		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));

		//ceshi
	//	Iterator it=table.getCellContentList().keySet().iterator();
//		while(it.hasNext())
//		{
//			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get((String)it.next());
//			
//			System.out.println(item.getItemID()+"   "+item.getItemValue());
//		}
		return report;
	}
	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
	 * �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * @param QysdsReportsTableDeclare
	 * @return ��ҵ����˰������������
	 */
	private HashMap translate2Page(QysdsReportsTableDeclare table)
	{
		HashMap backMap = new HashMap();
		List pagelist = new ArrayList();
	
		if(true)
		{
			HashMap map = new HashMap();
			map.put("hc","18");
			map.put("je",((QysdsReportsItemDeclare)table.getCellContentList().get("20")).getItemValue());
			pagelist.add(map);
		}
		for(int i=1;i<18;i++)
		{
			
			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("je",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(i))).getItemValue());
		    pagelist.add(map);
		}
//		���table.getCellContentList()
		Map nstzjs_1 =this.getCellMap(table, "18");
		Map nstzjs_2 =this.getCellMap(table, "19");
		
//		����Ϊ�ȳ���Map
		List nstzlist=this.processMap(nstzjs_1, nstzjs_2, 
				"18","19",
				"xm", "nstzjs_je");
		backMap.put("GD", pagelist);
		backMap.put("DT", nstzlist);
		return backMap;
	}
	
//	�˷����������ж�Ӧ��List
	private List processMap(Map map1,Map map2,
			String keyFlag1,String keyFlag2,
			String L1,String L2
			){

		boolean flagMuti=false;
		List list=new ArrayList();

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

		//��������ж�����Ϊ��,������������ȫ��Ϊ��
		if(flagMuti==false && map1.size()==0 && map2.size()==0 )
		{
			/**
			 * @todo
			 * ��2��map�����
			 */
			for (int i=0;i<3;i++)
			{
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				list.add(rowMap);
			}		
			return list;			
		
		//���������ж�����,������ֻ��һ��
		}else if(flagMuti==false)
		{
			//��һ�����ݺ�2������
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			
			list.add(rowMap);
			
			Map rowMap1=new HashMap();
			rowMap1.put(L1, "");
			rowMap1.put(L2, "");			
			list.add(rowMap1);
			
			Map rowMap2=new HashMap();
			rowMap2.put(L1, "");
			rowMap2.put(L2, "");
			list.add(rowMap2);
			
			return list;
		}

		//������������ж�����,������Ϊ���� 
		if(flagMuti==true)
		{
			int max=0;
			String[] arr;
			String temp=new String("0");
			//ȡmap1���±�����ֵ,�����е���Ŀ
			if(map1.size()!=0)
			{
				it = map1.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				max=getMax(arr);
			}
			//ȡmap2���±�����ֵ,�����е���Ŀ
			if(map2.size()!=0)
			{
				it = map2.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max2=this.getMax(arr);
				if (max<=max2)
				{
					max=max2;
				}
			}
			//�������е�����
			for(int i=0;i<max;i++)
			{
				Map rowMap=new HashMap();
				String key1=keyFlag1+"."+String.valueOf(i+1);
				String key2=keyFlag2+"."+String.valueOf(i+1);
				rowMap.put(L1, map1.get(key1)==null?"":((QysdsReportsItemDeclare)map1.get(key1)).getItemValue());
				rowMap.put(L2, map2.get(key2)==null?"":((QysdsReportsItemDeclare)map2.get(key2)).getItemValue());
				list.add(rowMap);
			}
//			������е���ĿΪ2��,�����1����������
			if(max==2)
			{					
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");		
				list.add(rowMap);
			}
		}
        
		return list;
	}
	
	
	
//	�����е�Ԫ����з���,�������е�Ԫ���Ӧ��map
	private HashMap getCellMap(QysdsReportsTableDeclare table,String flag){
		HashMap map = new HashMap();		
		Iterator it = table.getCellContentList().keySet().iterator();		
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				if(flag.equals(key)){
					map.put(key, table.getCellContentList().get(key));
				}
			}else if(flag.equals(key.substring(0, key.indexOf(".")))){
				String est=key.substring(0, key.indexOf("."));
				map.put(key, table.getCellContentList().get(key));			
			}						
			
		}
		return map;
		
	}
	/**
	 * ����ҳ��List�еĿ�ֵ
	 * @param list
	 */
	private List filterList(List list,String L1,String L2){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String strL1=(String)map.get(L1);
			String strL2=(String)map.get(L2);
			if(!strL1.equals("")||!strL2.equals("")){
				rtnList.add(map);
			}
		}		
		return rtnList;
	}
//	ȡ������������Ԫ��map�±�����ֵ,�Դ�ֵΪ׼�������е�����
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
}