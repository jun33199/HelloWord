package com.lscdz.util.codetable;

import java.util.List;

/**
 * ���ش�������ýӿ�
 * @author wangcy
 *
 */
public interface CodeTableConfig 
{
	public String getPropertiesFileName();
	
	/**
	 * ��ȡ���ش�����ֵ����
	 * @return
	 */
	public List getAllKeyList();
	
	/**
	 * ��ȡ����ID����
	 * @return
	 */
	public List getRelationIDList();
}
