package com.lscdz.util.codetable;

/**
 * ������װ��
 *    �ṩ���غ�����ĸ��ֻ�ȡ��ʽ
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
     * �����Map���ϵ��ܼ��ϣ����и�������Ϊһ��map��ÿ��map����code, ��Ԫ�������
     * 	  codeTableMaps:[tableKey, tableMap]
     * 								|--tableMap[code, bean]
     */
    private final Map codeTableMaps = new TreeMap();
    /**
     * �����List���ϵ��ܼ��ϣ����и��������Ӧһ��list��ÿ��list���д�������е�Ԫ���󣨵�Ԫ�����Ӧ���༴
     * �Զ���ʵ��CodeTableConfig���������ĸ�������Ӧ��java bean�����
     */
    private final Map codeTableLists = new HashMap();
    
    /**
     * ������Ҫ���صĹ�ϵ
     *    --map��keyΪdm_gjss_relation_config��Ӧ�Ĺ���ID��ֵΪ���ݶ�Ӧsql��ѯ������List����
     */
    private final Map relationLists = new HashMap();

    
    /**
     * ������
     */
    CodeTableWrapper()
    {
    }
    
    /**
     * ����ȡ�Ĵ������Ϣ��װ��list��map������ʽ������
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
     * ��ָ���Ĵ����List����ת����Map���ϣ������ڴ湩����
     * @param tableKey
     * @param tableList
     * @param data
     */
    protected void put(String tableKey, List tableList, Map data)
    {
    	List keyList = (List) data.get(CodeTableManager.MAP_KEY_KEYLIST);
    	List relationIDList = (List) data.get(CodeTableManager.MAP_KEY_RELATION_ID);
    	// ����codeTableLists
    	if(keyList != null && keyList.contains(tableKey))
    	{
    		codeTableLists.put(tableKey, tableList);
    	
	        Map tableMap = new HashMap();
	        for (int i = 0; i < tableList.size(); i++) 
	        {
	        	// ��ȡ��ǰ���ݱ��еļ�¼Ԫ���󣨼�ʵ����CodeTableInterface��java bean��
	            CodeTableInterface cti = (CodeTableInterface) tableList.get(i);
	            // �Ե�ǰԪ����Ĵ���ֵΪkey����װmap����
	            tableMap.put(cti.getOptionCode(), cti);
	        }
	        // ��ת����Ĵ�������Դ�����ֵΪkey����װ��Map
	        codeTableMaps.put(tableKey, tableMap);
    	}
    	else if(relationIDList != null && relationIDList.contains(tableKey))
    	{
    		relationLists.put(tableKey, tableList);
    	}
    }
    
    /**
     * ����ָ���Ĵ�����ֵ���ش����Map����
     * @param tableKey
     * @return
     */
    public Map getCodeTableMap(String tableKey)
    {
        return (Map) codeTableMaps.get(tableKey);
    }
    
    /**
     * ����ָ���Ĵ�����ֵ���ش����List����
     * @param tableKey
     * @return
     */
    public List getCodeTableList(String tableKey)
    {
        return (List) codeTableLists.get(tableKey);
    }
    
    /**
     * ����ָ���Ĺ���ID���ؼ�����ϵList����
     * @param relationID
     * @return
     */
    public List getRelationleList(String relationID)
    {
        return (List) relationLists.get(relationID);
    }
    
    /**
     * �Ƴ�ָ���������Ϣ
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
     * �Ƴ����м��صĴ������Ϣ
     */
    protected void removeAll()
    {
        codeTableLists.clear();
        codeTableMaps.clear();
    }
    
    /**
     * ��ȡָ�������ָ�������Ԫ����
     * @param tableKey
     * @param code
     * @return
     */
    public Object getObjectByCode(String tableKey, String code)
    {
    	// ��ȡָ��������map����
        Map map = getCodeTableMap(tableKey);
        if (map == null){
            return null;
        }
        else{
            return map.get(code);
        }
    }
    
    /**
     * ��ȡָ�������ָ�������Ԫ�����Ӧ�Ĵ�������
     * @param tableKey
     * @param code
     * @return
     */
    public String getNameByCode(String tableKey, String code)
    {
    	// ��ȡָ����Ԫ����
        CodeTableInterface cti = (CodeTableInterface) getObjectByCode(tableKey, code);
        if (cti == null){
            return null;
        }
        else{
            return cti.getOptionName();
        }
    }
    
    /**
     * ��ȡ��Ĵ�����嵥
     * @return List
     */
    public List getAvailableTableKeys()
    {
        return new ArrayList(codeTableLists.keySet());
    }
    
    /**
     * ��ȡcodeTableLists
     * @return codeTableLists
     */
    protected Map getCodeTableLists()
    {
        return codeTableLists;
    }
    
    /**
     * ��ȡcodeTableMaps
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
