package com.lscdz.qysds.common.service.qysdsCheck;

import java.sql.Connection;

import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;

import yangjian.frame.util.FrameException;

public interface IQysdsCheckServer {

	public abstract String check(Connection con, CheckBean checkBean) throws FrameException;

}
