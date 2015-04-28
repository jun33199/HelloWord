package com.lscdz.util.codetable;

/**
 * 代码表封装类
 *    提供加载后代码表的各种获取方式
 * @author wangcy
 *
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;



public class CodeTableWrapper
{
    /**
     * 代码表Map集合的总集合：其中各个对象为一个map，每个map都由code, 单元对象组成
     * 	  codeTableMaps:[tableKey, tableMap]
     * 								|--tableMap[code, bean]
     */
    private final Map codeTableMaps = new TreeMap();
    /**
     * 代码表List集合的总集合：其中各个对相对应一个list，每个list都有代码表所有单元对象（单元对象对应的类即
     * 自定义实现CodeTableConfig类中声明的各代码表对应的java bean）组成
     */
    private final Map codeTableLists = new HashMap();
    
    /**
     * 级联需要加载的关系
     *    --map的key为dm_gjss_relation_config对应的规则ID，值为根据对应sql查询出来的List集合
     */
    private final Map relationLists = new HashMap();

    
    /**
     * 构造器
     */
    CodeTableWrapper()
    {
    }
    
    /**
     * 将获取的代码表信息封装成list、map两种形式供调用
     * @param map
     * @param data
     */
    protected void putAll(Map map, Map data)
    {
        String tableKey;
        List tableList;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); put(tableKey, tableList, data))
        {
            tableKey = (String) iterator.next();
            tableList = (List) map.get(tableKey);
//            System.out.println("tableKey = " + tableKey + "   list size = " + tableList.size());
        }

    }
    
    /**
     * 将指定的代码表List集合转换成Map集合，放入内存供调用
     * @param tableKey
     * @param tableList
     * @param data
     */
    protected void put(String tableKey, List tableList, Map data)
    {
    	List keyList = (List) data.get(CodeTableManager.MAP_KEY_KEYLIST);
    	List relationIDList = (List) data.get(CodeTableManager.MAP_KEY_RELATION_ID);
    	// 设置codeTableLists
    	if(keyList != null && keyList.contains(tableKey))
    	{
    		codeTableLists.put(tableKey, tableList);
    	
	        Map tableMap = new HashMap();
	        for (int i = 0; i < tableList.size(); i++) 
	        {
	        	// 获取当前数据表中的记录元对象（及实现了CodeTableInterface的java bean）
	            CodeTableInterface cti = (CodeTableInterface) tableList.get(i);
	            // 以当前元对象的代码值为key，封装map集合
	            tableMap.put(cti.getOptionCode(), cti);
	        }
	        // 将转换后的代码表集合以代码表键值为key，封装成Map
	        codeTableMaps.put(tableKey, tableMap);
    	}
    	else if(relationIDList != null && relationIDList.contains(tableKey))
    	{
    		relationLists.put(tableKey, tableList);
    	}
    }
    
    /**
     * 按照指定的代码表键值返回代码表Map集合
     * @param tableKey
     * @return
     */
    public Map getCodeTableMap(String tableKey)
    {
        return (Map) codeTableMaps.get(tableKey);
    }
    
    /**
     * 按照指定的代码表键值返回代码表List集合
     * @param tableKey
     * @return
     */
    public List getCodeTableList(String tableKey)
    {
        return (List) codeTableLists.get(tableKey);
    }
    
    /**
     * 按照指定的规则ID返回级联关系List集合
     * @param relationID
     * @return
     */
    public List getRelationleList(String relationID)
    {
        return (List) relationLists.get(relationID);
    }
    
    /**
     * 移除指定代码表信息
     * @param tableKey
     * @return
     */
    protected List remove(String tableKey)
    {
        List oldList = (List) codeTableLists.remove(tableKey);
        codeTableMaps.remove(tableKey);
        return oldList;
    }
    
    /**
     * 移除所有加载的代码表信息
     */
    protected void removeAll()
    {
        codeTableLists.clear();
        codeTableMaps.clear();
    }
    
    /**
     * 获取指定代码表、指定代码的元对象
     * @param tableKey
     * @param code
     * @return
     */
    public Object getObjectByCode(String tableKey, String code)
    {
    	// 获取指定代码表的map集合
        Map map = getCodeTableMap(tableKey);
        if (map == null){
            return null;
        }
        else{
            return map.get(code);
        }
    }
    
    /**
     * 获取指定代码表、指定代码的元对象对应的代码名称
     * @param tableKey
     * @param code
     * @return
     */
    public String getNameByCode(String tableKey, String code)
    {
    	// 获取指定的元对象
        CodeTableInterface cti = (CodeTableInterface) getObjectByCode(tableKey, code);
        if (cti == null){
            return null;
        }
        else{
            return cti.getOptionName();
        }
    }
    
    /**
     * 获取活动的代码表清单
     * @return List
     */
    public List getAvailableTableKeys()
    {
        return new ArrayList(codeTableLists.keySet());
    }
    
    /**
     * 获取codeTableLists
     * @return codeTableLists
     */
    protected Map getCodeTableLists()
    {
        return codeTableLists;
    }
    
    /**
     * 获取codeTableMaps
     * @return codeTableMaps
     */
    protected Map getCodeTableMaps()
    {
        return codeTableMaps;
    }

	/**
	 * @return the relationLists
	 */
	public Map getRelationLists() {
		return relationLists;
	}
}
