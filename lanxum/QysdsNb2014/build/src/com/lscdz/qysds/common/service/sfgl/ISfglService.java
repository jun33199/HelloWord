package com.lscdz.qysds.common.service.sfgl;

import java.sql.Connection;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import yangjian.frame.util.FrameException;
/**
 * @description : 税费管理的service
 * @author zhangj
 * @version 1.0
 * @time -10-20 下午02:00:09
 */
public interface  ISfglService {
	
	/**
	 * 插入减免数据
	 * 
	 * @param table
	 * @return
	 */

	 public abstract void insertJmProce(Connection con, QysdsReportsDeclare declarein)
			throws FrameException;
	
}
