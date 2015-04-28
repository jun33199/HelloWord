package com.lscdz.util.codetable;

import java.util.List;

/**
 * 加载代码表配置接口
 * @author wangcy
 *
 */
public interface CodeTableConfig 
{
	public String getPropertiesFileName();
	
	/**
	 * 获取加载代码表键值集合
	 * @return
	 */
	public List getAllKeyList();
	
	/**
	 * 获取级联ID集合
	 * @return
	 */
	public List getRelationIDList();
}
