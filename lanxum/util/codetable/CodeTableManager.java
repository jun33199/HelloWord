package com.lscdz.util.codetable;

/**
 * 代码表管理类
 * @author wangcy
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CodeTableManager
{
	
	// 配置Map
	private static final Map configMap = new HashMap();
    // 代码表包装类，提供代码表信息获取方法
    private static final CodeTableWrapper wrapper = new CodeTableWrapper();
	// 使用的接口
	private static CodeTableConfig tableConfig;
	// 代码表清单List对应的key
	public static final String MAP_KEY_KEYLIST = "keyList";
	// 需要加载的级联关系ID清单
	public static final String MAP_KEY_RELATION_ID = "relationIDList";

	/**
	 * 构造器
	 */
	public CodeTableManager()
	{
	}
	
	/**
	 * 初始化代码表信息
	 * @param codeTableConfig 实现了CodeTableConfig的自定义类
	 * @param configPath 加载的配置文件路径
	 */
	public static void initCodeTable(CodeTableConfig codeTableConfig)
	{
		try
		{
			// 将自定义实现类付给全局变量
			tableConfig = codeTableConfig;
			
//			System.out.println("tableConfig) = " + tableConfig);
//			System.out.println("configName = " + tableConfig.getClass().getName());
			
			// 获取需要加载的代码表清单
			List keyList = tableConfig.getAllKeyList();
			// 获取需要加载的级联代码表ID清单
			List relationIDList = tableConfig.getRelationIDList();
			// 封装对象
			Map data = new HashMap();
			data.put(MAP_KEY_KEYLIST, keyList);
			data.put(MAP_KEY_RELATION_ID, relationIDList);
			// 获取加载后的代码表记录数
			Map map = updateCodeTables(tableConfig.getClass().getName(), data);
			/**
			 * 如果加载了代码表信息，则验证加载的代码表信息是否完整
			 */
			if(map != null && map.size() > 0)
			{
				for (int i = 0; i < keyList.size(); i++)
				{
					// 获取当前的代码表key
					String tableKey = (String)keyList.get(i);
//					System.out.println("tableKey = " + tableKey);
					// 获取代码表记录集合
					List list = wrapper.getCodeTableList(tableKey);
					if (list == null){
						System.out.println("不能获取 CodeTableKey 接口中定义的 \"" + tableKey + "\" 属性对应的代码表！");
					}
					else{
						System.out.println("已初始化代码表 " + tableKey + "，共 " + list.size() + " 条记录！");
					}
				}
			}
		}
		catch (Exception e)
		{
//			ExceptionUtil.handleException(e, "");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取代码表信息
	 * @param configName 配置信息名称
	 * @param data 需要获取的代码表清单，其中：
	 * 			key=keyList对应需要加载的代码表清单List;key=relationIDList对应需要加载的级联关系ID清单
	 * @param configPath 配置文件路径
	 * @return 获取的代码表记录数
	 */
	public static Map updateCodeTables(String configName, Map data)
	{
		if (!configMap.containsKey(configName)){
			addConfig(configName);
		}
		try
		{

			CodeTableProcessor cp=new CodeTableProcessor();
			Map map = (Map)cp.fetchCodeTables(data);
            // 将获取的代码表数据放入内存
			wrapper.putAll(map, data);

            // 返回获取的代码表数量
			return map;
		}
		catch (Exception e)
		{
//			ExceptionUtil.handleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取指定代码表信息
	 * @param tableKey
	 * @return
	 */
	public static List updateCodeTable(String tableKey)
	{
		// 获取指定加载指定代码表的配置类名称
		String configName = findConfigByTableKey(tableKey);
		
		if (configName != null)
			try
			{
				List keyList = new ArrayList();
				keyList.add(tableKey);
				Map data = new HashMap();
				data.put(MAP_KEY_KEYLIST, keyList);
				Map map = updateCodeTables(configName, data);
				List list = (List) map.get(tableKey);
				
				if (list != null && list.size() > 0)
				{
					wrapper.put(tableKey, list, data);
					return list;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * 获取指定级联关系列表
	 * @param relationID
	 * @return
	 */
	public static List updateRelationList(String relationID)
	{
		// 获取指定加载指定代码表的配置类名称
		String configName = findConfigByRelationID(relationID);
		
		if (configName != null)
		{
			try
			{
				List relationList = new ArrayList();
				relationList.add(relationID);
				Map data = new HashMap();
				data.put(MAP_KEY_RELATION_ID, relationList);
				Map map = updateCodeTables(configName, data);
				List list = (List) map.get(relationID);
				
				if (list != null && list.size() > 0)
				{
					wrapper.put(relationID, list, data);
					return list;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 添加代码表配置信息
	 * @param configName
	 * @return 返回添加后的配置集合，否则返回空
	 */
	public static CodeTableConfig addConfig(String configName)
	{
		try
		{
			if (tableConfig != null){
				return (CodeTableConfig) configMap.put(configName, tableConfig);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取指定代码表的Map集合对象
	 * @param tableKey
	 * @return
	 */
	public static Map getCodeTableMap(String tableKey)
	{
		/**
		 * 获取指定代码表在内存中已经加载的map集合对象
		 *   如获取为空，则刷新此代码表信息，并返回
		 *   如仍然没有，则返回空
		 */
		Map map = wrapper.getCodeTableMap(tableKey);
		if (map == null)
		{
			List list = updateCodeTable(tableKey);
			if (list == null){
				return new HashMap();
			}
		}
		
		return wrapper.getCodeTableMap(tableKey);
	}
	
	/**
	 * 获取指定代码表的List集合对象
	 * @param tableKey
	 * @return
	 */
	public static List getCodeTableList(String tableKey)
	{
		/**
		 * 获取指定代码表在内存中已经加载的list集合对象
		 *   如获取为空，则刷新此代码表信息
		 *   如仍然没有，则返回空
		 */
		List list = wrapper.getCodeTableList(tableKey);
		if (list == null)
		{
			list = updateCodeTable(tableKey);
			if (list == null){
				return new ArrayList();
			}
		}
		return list;
	}
	
	/**
	 * 获取指定代码表的List集合对象
	 * @param relationID
	 * @return
	 */
	public static List getRelationList(String relationID)
	{
		/**
		 * 获取指定代码表在内存中已经加载的list集合对象
		 *   如获取为空，则刷新此代码表信息
		 *   如仍然没有，则返回空
		 */
		List list = wrapper.getRelationleList(relationID);
		if (list == null)
		{
			list = updateCodeTable(relationID);
			if (list == null){
				return new ArrayList();
			}
		}
		return list;
	}
	
	/**
	 * 获取指定加载指定代码表的配置类名称
	 * @param tableKey
	 * @return
	 */
	private static String findConfigByTableKey(String tableKey)
	{
		for (Iterator iterator = configMap.keySet().iterator(); iterator.hasNext();)
		{
			// 获取已注册的当前配置类package
			String configName = (String)iterator.next();
			// 获取当前自定义配置类
			CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
			/**
			 * 如果指定配置类加载的代码表清单是否包含指定代码表，则返回当前配置类的Pacakge
			 */
			if (configClass.getAllKeyList().contains(tableKey)){
				return configName;
			}
		}

		return null;
	}
	
	/**
	 * 获取指定加载指定代码表的配置类名称
	 * @param relationID
	 * @return
	 */
	private static String findConfigByRelationID(String relationID)
	{
		for (Iterator iterator = configMap.keySet().iterator(); iterator.hasNext();)
		{
			// 获取已注册的当前配置类package
			String configName = (String)iterator.next();
			// 获取当前自定义配置类
			CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
			/**
			 * 如果指定配置类加载的级联关系清单是否包含指定代码表，则返回当前配置类的Pacakge
			 */
			if (configClass.getRelationIDList().contains(relationID)){
				return configName;
			}
		}

		return null;
	}
	
//	public static void updateCodeTables(List keyList)
//	{
//		int count = 0;
//		for (Iterator iterator = configMap.keySet().iterator(); iterator.hasNext();)
//		{
//			String configName = (String)iterator.next();
//			CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
//			List fetchList = null;
//			if (wrapper != null)
//				fetchList = ListUtils.intersection(keyList, configClass.getAllKeyList());
//			if (fetchList != null && fetchList.size() > 0)
//				count += updateCodeTables(configName, keyList);
//		}
//
//	}
	
	/**
     * 获取指定代码表、指定代码的元对象
     * @param tableKey
     * @param code
     * @return Object
     */
	public static Object getObjectByCode(String tableKey, String code)
	{
		return wrapper.getObjectByCode(tableKey, code);
	}
	
	/**
	 * 获取指定代码表、指定代码的元对象对应的代码名称
	 * @param tableKey
	 * @param code
	 * @return String
	 */
	public static String getNameByCode(String tableKey, String code)
	{
		return wrapper.getNameByCode(tableKey, code);
	}
	
	/**
	 * 获取指定配置类需要加载的代码表清单
	 */
	public static List getAllKeyList(String configName)
	{
		// 若不存在指定配置类信息，则添加
		if (!configMap.containsKey(configName)){
			addConfig(configName);
		}
		
		// 获取指定配置类信息
		CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
		if (configClass == null){
			return null;
		}
		else{
			return configClass.getAllKeyList();
		}
	}
	
	/**
	 * 移除数据表对象
	 * @param String configName
	 * @return 返回移除指定代码表信息后的集合
	 */
	private static CodeTableConfig removeConfig(String configName)
	{
		return (CodeTableConfig)configMap.remove(configName);
	}

	/**
	 * @return the codeTableConfig
	 */
	public static CodeTableConfig getCodeTableConfig()
	{
		return tableConfig;
	}

	/**
	 * @param codeTableConfig the codeTableConfig to set
	 */
	public void setCodeTableConfig(CodeTableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}
}
