package com.lscdz.qysds.common.service.djjbsj;

import java.sql.Connection;

import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;


import yangjian.frame.util.FrameException;

public interface IDjjbsjService {
	public abstract Djjbsj query(Connection con, String jsjdm, String swjgzzjgdm)
			throws FrameException;

	public abstract Djjbsj query(Connection con, String jsjdm) throws FrameException;

}
