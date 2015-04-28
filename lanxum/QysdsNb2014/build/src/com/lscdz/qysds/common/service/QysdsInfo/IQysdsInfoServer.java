package com.lscdz.qysds.common.service.QysdsInfo;

import java.sql.Connection;
import java.util.Date;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.QysdsInfo.bo.QysdsSet;

public interface IQysdsInfoServer {

	public abstract QysdsSet getQysdsInfo(Connection con, String jsjdm, Date rq, Date qsrq,
			Date jzrq, String bbfs) throws FrameException;

}
