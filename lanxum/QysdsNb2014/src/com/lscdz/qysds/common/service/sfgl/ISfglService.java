package com.lscdz.qysds.common.service.sfgl;

import java.sql.Connection;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import yangjian.frame.util.FrameException;
/**
 * @description : ˰�ѹ����service
 * @author zhangj
 * @version 1.0
 * @time -10-20 ����02:00:09
 */
public interface  ISfglService {
	
	/**
	 * �����������
	 * 
	 * @param table
	 * @return
	 */

	 public abstract void insertJmProce(Connection con, QysdsReportsDeclare declarein)
			throws FrameException;
	
}
