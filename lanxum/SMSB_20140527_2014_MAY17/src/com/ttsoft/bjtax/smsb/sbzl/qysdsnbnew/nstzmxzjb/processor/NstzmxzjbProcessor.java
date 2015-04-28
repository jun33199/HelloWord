/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class NstzmxzjbProcessor
    implements Processor 
    {
	
	 /**
	   * 实现Processor接口
	   * @param vo 业务参数
	   * @return Object VOPackage型数据
	   * @throws BaseException 业务异常
	   * 		1 当传过来的操作类型不对时抛出
	   * 		2 当调用的业务方法抛出业务异常时向上传递抛出
	   * 	其他异常抛出由EJB的process方法处理。
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
	            "用户执行了系统不支持的方法或功能.");
	    }

	    return result;
	  }
	
	/**
	 * doShow初始化对象页面信息要素
	 * @param vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private Object doShow(VOPackage vo) throws BaseException {
		
		NstzmxzjbForm nbForm = (NstzmxzjbForm) vo.getData();
		Connection conn = null;
		try
		{
//			获取数据库连接
			conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_NSTZMXZJB);
			table.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
//			 获取数据库接口，调用save方法进行数据保存
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
//			抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		
		return nbForm;
	}
	
	/**
	   * doSave    用于保存页面信息
	   * @param     vo 业务参数
	   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	   * @throws BaseException    当其他情况都会抛出异常信息
	   */
	private Object doSave(VOPackage vo) throws BaseException
	{
		
		NstzmxzjbForm nstzmxzjbForm = (NstzmxzjbForm)vo.getData();
		Connection con = null;
		try
		{
			//获取数据库连接
			con = SfDBResource.getConnection();
           //将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(nstzmxzjbForm);
//			获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp=AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
//			更新审核状态为“保存成功”
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
	   * doDelete    用于删除页面数据
	   * @param     vo 业务参数
	   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	   * @throws BaseException    当其他情况都会抛出异常信息
	   */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		NstzmxzjbForm form = (NstzmxzjbForm) vo.getData();
		Connection conn = null;
		try
		{
//			获取数据库连接

			conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(form);
//			获取数据库接口，调用save方法进行数据保存
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
	   * doCheck    用于校验页面数据
	   * @param     vo 业务参数
	   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	   * @throws BaseException    当其他情况都会抛出异常信息
	   */
	private Object doCheck(VOPackage vo)throws BaseException
	{
		NstzmxzjbForm nstzmxzjbForm = (NstzmxzjbForm)vo.getData();
		Connection con = null;
		try
		{
//			创建数据库连接
			con = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(nstzmxzjbForm);
			/**
			 * 单表校验
			 * 通过后继续执行；
			 * 未通过退出执行，页面提示校验结果信息
			 */
			Checker checker=CheckerFactory.getAInstance(con,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			nstzmxzjbForm.setCheckList(listSingle);
			/*如果校验通过，调用接口保存数据*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0))
			{
				iApp.saveSingleTable(report);
				//更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//单表审核未通过
				iApp.updateCheckStatus(report,"");
			}
		}catch(Exception ex)
		{
//			抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
//			释放数据库连接
			SfDBResource.freeConnection(con);
		}
		return nstzmxzjbForm;
	}
	
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param ZcmxbForm 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(NstzmxzjbForm nbForm)
	{
//		企业所得税报表申明对象
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report, nbForm); //对report 进行一系列的设置
//		企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_NSTZMXZJB);
		table.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=nbForm.getDataList(); //存放固定行的LIST
		List nstzzj_list = this.filterList(nbForm.getNstzzj_List(), "xm", "nstzzj_bqfss", "nstzzj_sqkcxe", "nstzzj_nstzje"); //存放子行的LIST
		//翻译固定行数据
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
				//设置固定行第一列数据
				QysdsReportsItemDeclare item1=new QysdsReportsItemDeclare();				
				item1.setItemID((3*hcnum-2)+"");
				item1.setItemValue((String)map.get("bqfss"));
				item1.setItemType("11");
				table.getCellContentList().put(item1.getItemID(),item1);
				
				//设置固定行第二列数据
				QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
				item2.setItemID((3*hcnum-1)+"");
				item2.setItemValue((String)map.get("sqkcxe"));
				item2.setItemType("11");
				table.getCellContentList().put(item2.getItemID(),item2);
				
				//设置固定行第三列数据
				QysdsReportsItemDeclare item3=new QysdsReportsItemDeclare();
				item3.setItemID((3*hcnum)+"");
				item3.setItemValue((String)map.get("nstzje"));
				item3.setItemType("11");
				table.getCellContentList().put(item3.getItemID(),item3);
			}
			
		}

		//翻译动态行数据
		
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
	 * 将接口数据结构中的数据转换置入页面要求的数据结构
	 * 接口数据结构-->页面数据结构
	 * @param QysdsReportsTableDeclare
	 * @return 企业所得税报表申明对象
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
		
//		拆分table.getCellContentList()
		Map nstz_1 =this.getCellMap(table, "112");
		Map nstz_2 =this.getCellMap(table, "113");
		Map nstz_3 =this.getCellMap(table, "114");
		Map nstz_4 =this.getCellMap(table, "115");
		
//		处理为等长度Map
		List nstzlist=this.processMap(nstz_1, nstz_2, nstz_3,nstz_4,
				"112","113","114","115",
				"xm", "nstzzj_bqfss", "nstzzj_sqkcxe","nstzzj_nstzje");
		
		backMap.put("GD", pagelist);
		backMap.put("DT", nstzlist);
		return backMap;
	}
	/**
	 * 过滤页面List中的空值
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
	
//	按子行单元格进行分类,返回子行单元格对应的map
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
	
//	此方法返回子行对应的List
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

		//如果下列判断条件为真,则子行里内容全部为空
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0 &&map4.size()==0){
			/**
			 * @todo
			 * 往四个map里放入
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
		
		//如果满足此判断条件,则子行只有一行
		}else if(flagMuti==false){
			//放一条数据和2条空行
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

		//如果满足下列判断条件,则子行为多行 
		if(flagMuti==true){
			int max=0;
			String[] arr;
			String temp=new String("0");
			//取map1中下标的最大值,即子行的数目
			if(map1.size()!=0){
				it = map1.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				max=getMax(arr);
			}
			//取map2中下标的最大值,即子行的数目
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
			//插入子行的数据
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
//			如果子行的数目为2行,则插入1个空行数据
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
    
//	取得子行三个单元格map下标的最大值,以此值为准计算子行的行数
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