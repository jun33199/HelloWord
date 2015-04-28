package com.lscdz.qysds.common.service.qysds.check;

import java.util.ArrayList;
import java.util.List;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.bo.CheckResult;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
/**
 * 检查类基类
 * 项目名称：QysdsNb2014   
 * 类名称：Checker   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午1:45:35   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午1:45:35   
 * 修改备注：   
 * @version  1.0
 */
public abstract class Checker {

	public DBAccess idba;

	/**
	 * 检查结果
	 */
	@SuppressWarnings("rawtypes")
	List crs = new ArrayList();

	/**
	 * 临时结果句柄
	 */
	CheckResult cr;

	/**
	 * 构造函数
	 * @param idba DBAccess
	 */
	public Checker(DBAccess idba) {
		this.idba = idba;
	}

	/**
	 * 主检查方法
	 * 
	 * @param contents 报表内容
	 * @param formulas 公式内容
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException 审核异常
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkMain(Object contents, List formulas) throws FrameException;

	/**
	 * 单表检查方法
	 * 
	 * @param contents  报表内容
	 * @param formulas 公式内容
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException 审核异常
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkSingeTable(Object contents, List formulas)throws FrameException;

	/**
	 * 主检查方法
	 * 
	 * @param contents 报表内容
	 * @param fsdm 方式代码 参见Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants. CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException   审核异常
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkMain(Object contents, String fsdm)throws FrameException;

	/**
	 * 单表检查方法
	 * 
	 * @param contents 报表内容
	 * @param fsdm 方式代码 参见Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants.CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return 检查结果，结果为空或没有成员则表示检查通过
	 * @exception FrameException 审核异常
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkSingeTable(Object contents, String fsdm)throws FrameException;

}
