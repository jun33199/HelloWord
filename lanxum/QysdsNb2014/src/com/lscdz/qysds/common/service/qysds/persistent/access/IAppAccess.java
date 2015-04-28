package com.lscdz.qysds.common.service.qysds.persistent.access;

import java.sql.SQLException;

import yangjian.frame.util.FrameException;


/**
 * @author 哈正则 应用管理器接口
 */
public interface IAppAccess {
	
	/**
	 * 获取当前应用活动版本号
	 * @param appid String 应用ID
	 * @return String 当前应用活动版本号
	 */
	public String getCurrentVersion(String appid);
	
	/**
	 * 设置审核结果方法方法
	 * @param obj 删除对象,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param checkFlag 标志位,参见QysdsReportsConstants.CREPORTS_CHECK_QYSDS_NOPASS和QysdsReportsConstants.CREPORTS_CHECK_QYSDS_PASS
	 * @exception FrameException 业务异常
	 */
	public void setCheckResult(Object obj, String checkFlag) throws FrameException;
	
	
	/**
	 * 保存方法
	 * @param obj 保存对象
	 */
	public void save(Object obj) throws FrameException;
	
	/**
	 * 保存方法,处理单表对象
	 * @param obj 保存对象
	 */
	public void saveSingleTable(Object obj) throws FrameException;
	
	
	/**
	 * 删除方法
	 * @param obj 删除对象
	 */
	public void delete(Object obj) throws FrameException;
	
	/**
	 * 删除方法,处理单表对象
	 * @param obj 删除对象
	 */
	public void deleteSingleTable(Object obj) throws FrameException;
	
	
	/**
	 * 查询方法,数据库有的数据均覆盖到该对象
	 * @param obj 查询参数,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return 查询结果,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 */
	public Object query(Object obj) throws FrameException;
	
	/**
	 * 查询单表数据方法,只查询具备表成员的数据
	 * @param obj 查询参数,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return 查询结果,例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 */
	public Object querySingleTable(Object obj) throws FrameException;
	
	/**
	 * 更新审核状态信息表
	 * @param obj 例如,成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param shbz 审核标志
	 * @throws FrameException
	 * @return true：更新成功；false：更新失败
	 * @author wofei
	 */
	public boolean updateCheckStatus(Object obj,String shbz) throws FrameException;
	
	/**
	 * 查询审核状态
	 * @param obj 成员为com.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return 审核状态
	 * @throws FrameException
	 * @author wofei
	 */
	public String queryCheckStatus(Object obj) throws FrameException, SQLException;
}
