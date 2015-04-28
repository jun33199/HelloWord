package com.lscdz.util.codetable;

/**
 * ����������
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
	
	// ����Map
	private static final Map configMap = new HashMap();
    // ������װ�࣬�ṩ�������Ϣ��ȡ����
    private static final CodeTableWrapper wrapper = new CodeTableWrapper();
	// ʹ�õĽӿ�
	private static CodeTableConfig tableConfig;
	// ������嵥List��Ӧ��key
	public static final String MAP_KEY_KEYLIST = "keyList";
	// ��Ҫ���صļ�����ϵID�嵥
	public static final String MAP_KEY_RELATION_ID = "relationIDList";

	/**
	 * ������
	 */
	public CodeTableManager()
	{
	}
	
	/**
	 * ��ʼ���������Ϣ
	 * @param codeTableConfig ʵ����CodeTableConfig���Զ�����
	 * @param configPath ���ص������ļ�·��
	 */
	public static void initCodeTable(CodeTableConfig codeTableConfig)
	{
		try
		{
			// ���Զ���ʵ���ึ��ȫ�ֱ���
			tableConfig = codeTableConfig;
			
//			System.out.println("tableConfig) = " + tableConfig);
//			System.out.println("configName = " + tableConfig.getClass().getName());
			
			// ��ȡ��Ҫ���صĴ�����嵥
			List keyList = tableConfig.getAllKeyList();
			// ��ȡ��Ҫ���صļ��������ID�嵥
			List relationIDList = tableConfig.getRelationIDList();
			// ��װ����
			Map data = new HashMap();
			data.put(MAP_KEY_KEYLIST, keyList);
			data.put(MAP_KEY_RELATION_ID, relationIDList);
			// ��ȡ���غ�Ĵ�����¼��
			Map map = updateCodeTables(tableConfig.getClass().getName(), data);
			/**
			 * ��������˴������Ϣ������֤���صĴ������Ϣ�Ƿ�����
			 */
			if(map != null && map.size() > 0)
			{
				for (int i = 0; i < keyList.size(); i++)
				{
					// ��ȡ��ǰ�Ĵ����key
					String tableKey = (String)keyList.get(i);
//					System.out.println("tableKey = " + tableKey);
					// ��ȡ������¼����
					List list = wrapper.getCodeTableList(tableKey);
					if (list == null){
						System.out.println("���ܻ�ȡ CodeTableKey �ӿ��ж���� \"" + tableKey + "\" ���Զ�Ӧ�Ĵ����");
					}
					else{
						System.out.println("�ѳ�ʼ������� " + tableKey + "���� " + list.size() + " ����¼��");
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
	 * ��ȡ�������Ϣ
	 * @param configName ������Ϣ����
	 * @param data ��Ҫ��ȡ�Ĵ�����嵥�����У�
	 * 			key=keyList��Ӧ��Ҫ���صĴ�����嵥List;key=relationIDList��Ӧ��Ҫ���صļ�����ϵID�嵥
	 * @param configPath �����ļ�·��
	 * @return ��ȡ�Ĵ�����¼��
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
            // ����ȡ�Ĵ�������ݷ����ڴ�
			wrapper.putAll(map, data);

            // ���ػ�ȡ�Ĵ��������
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
	 * ��ȡָ���������Ϣ
	 * @param tableKey
	 * @return
	 */
	public static List updateCodeTable(String tableKey)
	{
		// ��ȡָ������ָ������������������
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
	 * ��ȡָ��������ϵ�б�
	 * @param relationID
	 * @return
	 */
	public static List updateRelationList(String relationID)
	{
		// ��ȡָ������ָ������������������
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
	 * ��Ӵ����������Ϣ
	 * @param configName
	 * @return ������Ӻ�����ü��ϣ����򷵻ؿ�
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
	 * ��ȡָ��������Map���϶���
	 * @param tableKey
	 * @return
	 */
	public static Map getCodeTableMap(String tableKey)
	{
		/**
		 * ��ȡָ����������ڴ����Ѿ����ص�map���϶���
		 *   ���ȡΪ�գ���ˢ�´˴������Ϣ��������
		 *   ����Ȼû�У��򷵻ؿ�
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
	 * ��ȡָ��������List���϶���
	 * @param tableKey
	 * @return
	 */
	public static List getCodeTableList(String tableKey)
	{
		/**
		 * ��ȡָ����������ڴ����Ѿ����ص�list���϶���
		 *   ���ȡΪ�գ���ˢ�´˴������Ϣ
		 *   ����Ȼû�У��򷵻ؿ�
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
	 * ��ȡָ��������List���϶���
	 * @param relationID
	 * @return
	 */
	public static List getRelationList(String relationID)
	{
		/**
		 * ��ȡָ����������ڴ����Ѿ����ص�list���϶���
		 *   ���ȡΪ�գ���ˢ�´˴������Ϣ
		 *   ����Ȼû�У��򷵻ؿ�
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
	 * ��ȡָ������ָ������������������
	 * @param tableKey
	 * @return
	 */
	private static String findConfigByTableKey(String tableKey)
	{
		for (Iterator iterator = configMap.keySet().iterator(); iterator.hasNext();)
		{
			// ��ȡ��ע��ĵ�ǰ������package
			String configName = (String)iterator.next();
			// ��ȡ��ǰ�Զ���������
			CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
			/**
			 * ���ָ����������صĴ�����嵥�Ƿ����ָ��������򷵻ص�ǰ�������Pacakge
			 */
			if (configClass.getAllKeyList().contains(tableKey)){
				return configName;
			}
		}

		return null;
	}
	
	/**
	 * ��ȡָ������ָ������������������
	 * @param relationID
	 * @return
	 */
	private static String findConfigByRelationID(String relationID)
	{
		for (Iterator iterator = configMap.keySet().iterator(); iterator.hasNext();)
		{
			// ��ȡ��ע��ĵ�ǰ������package
			String configName = (String)iterator.next();
			// ��ȡ��ǰ�Զ���������
			CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
			/**
			 * ���ָ����������صļ�����ϵ�嵥�Ƿ����ָ��������򷵻ص�ǰ�������Pacakge
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
     * ��ȡָ�������ָ�������Ԫ����
     * @param tableKey
     * @param code
     * @return Object
     */
	public static Object getObjectByCode(String tableKey, String code)
	{
		return wrapper.getObjectByCode(tableKey, code);
	}
	
	/**
	 * ��ȡָ�������ָ�������Ԫ�����Ӧ�Ĵ�������
	 * @param tableKey
	 * @param code
	 * @return String
	 */
	public static String getNameByCode(String tableKey, String code)
	{
		return wrapper.getNameByCode(tableKey, code);
	}
	
	/**
	 * ��ȡָ����������Ҫ���صĴ�����嵥
	 */
	public static List getAllKeyList(String configName)
	{
		// ��������ָ����������Ϣ�������
		if (!configMap.containsKey(configName)){
			addConfig(configName);
		}
		
		// ��ȡָ����������Ϣ
		CodeTableConfig configClass = (CodeTableConfig)configMap.get(configName);
		if (configClass == null){
			return null;
		}
		else{
			return configClass.getAllKeyList();
		}
	}
	
	/**
	 * �Ƴ����ݱ����
	 * @param String configName
	 * @return �����Ƴ�ָ���������Ϣ��ļ���
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
