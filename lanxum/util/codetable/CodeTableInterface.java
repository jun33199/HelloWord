package com.lscdz.util.codetable;


/**
 * �����ӿ�
 * 	  ������Ҫ���صĴ�����Ӧ��java bean������ʵ�ִ˽ӿ�
 * @author wangcy
 * 
 *
 */
public interface CodeTableInterface 
{
	/**
	 * ��������Ҫ�����ڿռ���
	 * @return
	 */
	public String getTableName();
	/**
	 * ��ȡ��Ԫ��������
	 * @return
	 */
	public String getOptionName();
	
	/**
	 * ��ȡ��Ԫ����ֵ����
	 * @return
	 */
	public String getOptionCode();
	
	/**
	 * ��ȡ����ʽ
	 * @return ����ʽ   ��ʽ�������ֶ�1,����ʽ|�����ֶ�2,����ʽ|...|�����ֶ�n,����ʽ�����У�����ʽ��asc,desc���֣�
	 */
	public String getOrder();
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public String getSqlWhere();
	
}