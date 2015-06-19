/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.web.NstzmxzjbForm;
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
 * @author Shi Yanfeng
 * @version 1.1
 */

public class NstzmxzjbProcessor
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
		
		NstzmxzjbForm nbForm = (NstzmxzjbForm) vo.getData();
		Connection conn = null;
		try
		{
//			��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_NSTZMXZJB);
			table.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			int[] arrs={1,3,4,6,7,9,10,12,13,15,16,18,19,21,22,24,25,27,28,30,31,33,34,36,37,39,
					40,42,43,45,46,48,49,51,52,54,55,57,58,60,61,63,64,66,67,69,
					70,72,73,75,76,78,79,81,82,84,85,87,88,90,91,93,94,96,97,99,
					100,102,103,105,106,108,109,111,116,116};
				
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_NSTZMXZJB);
			List listgd = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("GD");
			List listdt = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("DT");
			nbForm.setNstzzj_List(listdt);
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
		
		NstzmxzjbForm nstzmxzjbForm = (NstzmxzjbForm)vo.getData();
		Connection con = null;
		try
		{
			//��ȡ���ݿ�����
			con = SfDBResource.getConnection();
           //��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(nstzmxzjbForm);
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
		return nstzmxzjbForm;
	}
	 /**
	   * doDelete    ����ɾ��ҳ������
	   * @param     vo ҵ�����
	   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException    ��������������׳��쳣��Ϣ
	   */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		NstzmxzjbForm form = (NstzmxzjbForm) vo.getData();
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
			table.setTableId(CodeConstant.TABLE_ID_NSTZMXZJB);
			table.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_NSTZMXZJB);
			int[] arrs={1,3,4,6,7,9,10,12,13,15,16,18,19,21,22,24,25,27,28,30,31,33,34,36,37,39,
					40,42,43,45,46,48,49,51,52,54,55,57,58,60,61,63,64,66,67,69,
					70,72,73,75,76,78,79,81,82,84,85,87,88,90,91,93,94,96,97,99,
					100,102,103,105,106,108,109,111,116,116};
			List listgd = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("GD");
			List listdt = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("DT");
			form.setNstzzj_List(listdt);
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
		NstzmxzjbForm nstzmxzjbForm = (NstzmxzjbForm)vo.getData();
		Connection con = null;
		try
		{
//			�������ݿ�����
			con = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(nstzmxzjbForm);
			/**
			 * ����У��
			 * ͨ�������ִ�У�
			 * δͨ���˳�ִ�У�ҳ����ʾУ������Ϣ
			 */
			Checker checker=CheckerFactory.getAInstance(con,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			nstzmxzjbForm.setCheckList(listSingle);
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
		return nstzmxzjbForm;
	}
	
	
	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param ZcmxbForm 
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(NstzmxzjbForm nbForm)
	{
//		��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report, nbForm); //��report ����һϵ�е�����
//		��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_NSTZMXZJB);
		table.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=nbForm.getDataList(); //��Ź̶��е�LIST
		List nstzzj_list = this.filterList(nbForm.getNstzzj_List(), "xm", "nstzzj_bqfss", "nstzzj_sqkcxe", "nstzzj_nstzje"); //������е�LIST
		//����̶�������
		for(int i=0;i<list.size();i++)
		{
			HashMap map = new HashMap();
			map = (HashMap)list.get(i);
			String hc =(String)map.get("hc");
			int hcnum = Integer.parseInt(hc);
			if("38".equals(hc))
			{
				QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
				item.setItemID("116");
				item.setItemValue((String)map.get("nstzje"));
				item.setItemType("11");
				table.getCellContentList().put(item.getItemID(),item);
			}else
			{   
				//���ù̶��е�һ������
				QysdsReportsItemDeclare item1=new QysdsReportsItemDeclare();				
				item1.setItemID((3*hcnum-2)+"");
				item1.setItemValue((String)map.get("bqfss"));
				item1.setItemType("11");
				table.getCellContentList().put(item1.getItemID(),item1);
				
				//���ù̶��еڶ�������
				QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
				item2.setItemID((3*hcnum-1)+"");
				item2.setItemValue((String)map.get("sqkcxe"));
				item2.setItemType("11");
				table.getCellContentList().put(item2.getItemID(),item2);
				
				//���ù̶��е���������
				QysdsReportsItemDeclare item3=new QysdsReportsItemDeclare();
				item3.setItemID((3*hcnum)+"");
				item3.setItemValue((String)map.get("nstzje"));
				item3.setItemType("11");
				table.getCellContentList().put(item3.getItemID(),item3);
			}
			
		}

		//���붯̬������
		
			int num = nstzzj_list.size();
			for(int i=0;i<num;i++)
			{   
				HashMap map = (HashMap)nstzzj_list.get(i);
				if(num==1)
				{
					QysdsReportsItemDeclare item1=new QysdsReportsItemDeclare();
					item1.setItemID("112");
					item1.setItemValue((String)map.get("xm"));
					item1.setItemType("11");
					table.getCellContentList().put(item1.getItemID(),item1);
					
					QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
					item2.setItemID("113");
					item2.setItemValue((String)map.get("nstzzj_bqfss"));
					item2.setItemType("11");
					table.getCellContentList().put(item2.getItemID(),item2);
					
					QysdsReportsItemDeclare item3=new QysdsReportsItemDeclare();
					item3.setItemID("114");
					item3.setItemValue((String)map.get("nstzzj_sqkcxe"));
					item3.setItemType("11");
					table.getCellContentList().put(item3.getItemID(),item3);
					
					QysdsReportsItemDeclare item4=new QysdsReportsItemDeclare();
					item4.setItemID("115");
					item4.setItemValue((String)map.get("nstzzj_nstzje"));
					item4.setItemType("11");
					table.getCellContentList().put(item4.getItemID(),item4);
				}else
				{
				
				QysdsReportsItemDeclare item1=new QysdsReportsItemDeclare();
				item1.setItemID("112."+String.valueOf(i+1));
				item1.setItemValue((String)map.get("xm"));
				item1.setItemType("11");
				table.getCellContentList().put(item1.getItemID(),item1);
				
				
				QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
				item2.setItemID("113."+String.valueOf(i+1));
				item2.setItemValue((String)map.get("nstzzj_bqfss"));
				item2.setItemType("11");
				table.getCellContentList().put(item2.getItemID(),item2);
				
				
				QysdsReportsItemDeclare item3=new QysdsReportsItemDeclare();
				item3.setItemID("114."+String.valueOf(i+1));
				item3.setItemValue((String)map.get("nstzzj_sqkcxe"));
				item3.setItemType("11");
				table.getCellContentList().put(item3.getItemID(),item3);
				
				
				QysdsReportsItemDeclare item4=new QysdsReportsItemDeclare();
				item4.setItemID("115."+String.valueOf(i+1));
				item4.setItemValue((String)map.get("nstzzj_nstzje"));
				item4.setItemType("11");
				table.getCellContentList().put(item4.getItemID(),item4);
			}
		}
		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));
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
			map.put("hc","38");
			map.put("bqfss","*");
			map.put("sqkcxe","*");
			map.put("nstzje",((QysdsReportsItemDeclare)table.getCellContentList().get("116")).getItemValue());
			pagelist.add(map);
		}
		

		for(int i=1;i<38;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("bqfss",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(3*i-2))).getItemValue());
			map.put("sqkcxe",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(3*i-1))).getItemValue());
			map.put("nstzje",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(3*i))).getItemValue());
		    pagelist.add(map);
		}
		
//		���table.getCellContentList()
		Map nstz_1 =this.getCellMap(table, "112");
		Map nstz_2 =this.getCellMap(table, "113");
		Map nstz_3 =this.getCellMap(table, "114");
		Map nstz_4 =this.getCellMap(table, "115");
		
//		����Ϊ�ȳ���Map
		List nstzlist=this.processMap(nstz_1, nstz_2, nstz_3,nstz_4,
				"112","113","114","115",
				"xm", "nstzzj_bqfss", "nstzzj_sqkcxe","nstzzj_nstzje");
		
		backMap.put("GD", pagelist);
		backMap.put("DT", nstzlist);
		return backMap;
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
			if(!strL1.equals("")||!strL2.equals("")||!strL3.equals("") || !strL4.equals("")){
				rtnList.add(map);
			}
		}		
		return rtnList;
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
	
//	�˷����������ж�Ӧ��List
	private List processMap(Map map1,Map map2,Map map3,Map map4,
			String keyFlag1,String keyFlag2,String keyFlag3,String keyFlag4,
			String L1,String L2,String L3,
			String L4 ){

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

		it = map3.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else
			{
				flagMuti=true;
				break;
			}
		}
		
		it = map4.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else
			{
				flagMuti=true;
				break;
			}
		}

		//��������ж�����Ϊ��,������������ȫ��Ϊ��
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0 &&map4.size()==0){
			/**
			 * @todo
			 * ���ĸ�map�����
			 */
			for (int i=0;i<3;i++){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "");
				list.add(rowMap);
			}		
			return list;			
		
		//���������ж�����,������ֻ��һ��
		}else if(flagMuti==false){
			//��һ�����ݺ�2������
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			rowMap.put(L3, map3.get(keyFlag3)==null?"":((QysdsReportsItemDeclare)map3.get(keyFlag3)).getItemValue());
			rowMap.put(L4, map4.get(keyFlag4)==null?"":((QysdsReportsItemDeclare)map4.get(keyFlag4)).getItemValue());
			
			list.add(rowMap);
			
			Map rowMap1=new HashMap();
			rowMap1.put(L1, "");
			rowMap1.put(L2, "");
			rowMap1.put(L3, "");
			rowMap1.put(L4, "");
			
			list.add(rowMap1);
			
			Map rowMap2=new HashMap();
			rowMap2.put(L1, "");
			rowMap2.put(L2, "");
			rowMap2.put(L3, "");
			rowMap2.put(L4, "");	
			list.add(rowMap2);
			
			return list;
		}

		//������������ж�����,������Ϊ���� 
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
			//ȡmap2���±�����ֵ,�����е���Ŀ
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

			if(map3.size()!=0)
			{
				it = map3.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max3=this.getMax(arr);
				if (max<=max3)
				{
					max=max3;
				}
			}
			if(map4.size()!=0)
			{
				it = map4.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max4=this.getMax(arr);
				if (max<=max4)
				{
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
//			������е���ĿΪ2��,�����1����������
			if(max==2){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "");				
				list.add(rowMap);
			}
		}

		return list;
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