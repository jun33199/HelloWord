package com.lscdz.util.codetable;


/**
 * 代码表接口
 * 	  所有需要加载的代码表对应的java bean都必须实现此接口
 * @author wangcy
 * 
 *
 */
public interface CodeTableInterface 
{
	/**
	 * 代码表表名要含所在空间名
	 * @return
	 */
	public String getTableName();
	/**
	 * 获取单元对象名称
	 * @return
	 */
	public String getOptionName();
	
	/**
	 * 获取单元对象值代码
	 * @return
	 */
	public String getOptionCode();
	
	/**
	 * 获取排序方式
	 * @return 排序方式   格式：排序字段1,排序方式|排序字段2,排序方式|...|排序字段n,排序方式（其中：排序方式有asc,desc两种）
	 */
	public String getOrder();
	
	/**
	 * 查询条件
	 * @return
	 */
	public String getSqlWhere();
	
}