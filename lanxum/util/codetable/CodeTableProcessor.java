package com.lscdz.util.codetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lscdz.util.StringUtil;
import com.lscdz.util.dao.CodeTableAccessExt;

/**
 * @author wangcy
 *
 */
public class CodeTableProcessor   
{
	/**
	 * 获取代码表信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Object fetchCodeTables(Map data) throws Exception
	{
		System.out.println("========coming into CodeTableProcessor.fetchCodeTables=========");
		Map rtnMap = new HashMap();
		try
		{
//			List keyList = (List) vo.getData();
			// 需要加载的代码表
			List keyList = (List) data.get(CodeTableManager.MAP_KEY_KEYLIST);
			
			for(int i=0; i < keyList.size(); i++)
			{
				// 获取代码表对应bo的class的名称
				String boClass = (String) keyList.get(i);
				// 实例化当前的bo对象
				CodeTableInterface bo = (CodeTableInterface) Class.forName(boClass).newInstance();
				//获取代码表表名
				String tableName=bo.getTableName();
				
				if(StringUtil.isEmpty(tableName)){
					continue;
				}
				/**
				 * 拼接Order
				 *    如果有多个排序字段（即有分隔符“|”分割），则先获取用“|”分割的字符串集合，然后再将各个元集合二次分割，
				 *    拼成order返回
				 */
				// 获取排序字符
				String orderStr = bo.getOrder();
				StringBuffer orders = new StringBuffer(" ");
				if(null != orderStr && !"".equals(orderStr))
				{
					orders.append(" order by");
					if(StringUtil.checkRepeatedStr(orderStr, "|"))
					{
						List list = StringUtil.splitByChar(orderStr, "|");
						for(int j = 0; j < list.size(); j++)
						{
							String tmpStr = (String) list.get(j);
							orders.append(" ").append(getOrder(tmpStr)).append(",") ;
						}
					}
					else
					{
						orders.append(" ").append(getOrder(orderStr)).append(",");
					}
				}
				
				List recordList = CodeTableAccessExt.readRecords(Class.forName(boClass).newInstance(),tableName,bo.getSqlWhere(),orders.substring(0, orders.length()-1).toString());
//				System.out.println("recordList size = " + recordList.size());
				// 以代码表名称为键值，封装代码表信息
				rtnMap.put(boClass, recordList == null ? new ArrayList() : recordList);
			}
			
//			/**
//			 * 封装级联需要加载的规则
//			 */
//			// 需要加载的级联关系ID表
//			List relationIDList = (List) data.get(CodeTableManager.MAP_KEY_RELATION_ID);
//			RowSetDynaClass rs = null;
//			// 局方要求不得使用schema
//			String queryById = "select id, relation_name, opration_name, execute_sql from dm_gjss_relation_config where id=?";
//			for(int j = 0; j < relationIDList.size(); j++)
//			{
//				// 级联ID
//				String id = (String) relationIDList.get(j);
////				String[] str = new String[1];
////				str[0] = id;
//				rs = this.getDao().findBySQL(queryById, new Object[]{id});
////				log.debug("id = " + id);
//				// 获取查询结果
//				List resultList = rs.getRows();
//				if(resultList != null && resultList.size() > 0)
//				{
//					DynaBean bean = (DynaBean) resultList.iterator().next();
//					log.debug("execute_sql = " + bean.get("execute_sql"));
//					
//					rs = this.getDao().findBySQL(String.valueOf(bean.get("execute_sql")), null);
//					Iterator it = rs.getRows().iterator();
//					List relationList = new ArrayList();
//					while(it.hasNext())
//					{
//						DynaBean result = (DynaBean) it.next();
//	//					log.debug("result = " + result.get("result_0"));
//						relationList.add(result.get("result_0"));
//					}
//					rtnMap.put(id, relationList);
//				}
//			}
			
			return rtnMap;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	/**
	 * 将格式为“排序字段1,排序方式”的字符串拼成Order返回
	 * @param orderStr
	 * @return
	 */
	private String  getOrder(String orderStr)
	{
		String order = null;
		List list = StringUtil.splitByChar(orderStr, ",");
		String order_Str = (String) list.get(0);
		String orderType = (String) list.get(1);
		if("asc".equals(orderType))
		{
			order = " "+ order_Str + " asc ";
		}
		else if("desc".equals(orderType))
		{
			order = " "+ order_Str + " desc ";
		}
		
		return order;
	}
}
