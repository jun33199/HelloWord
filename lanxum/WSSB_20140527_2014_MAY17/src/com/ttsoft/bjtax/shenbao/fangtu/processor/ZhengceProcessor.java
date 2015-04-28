package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuZhengceBVO;
import com.ttsoft.bjtax.shenbao.model.domain.JMZC;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

public class ZhengceProcessor implements Processor {
	private Logger logger = Logger.getLogger(ZhengceProcessor.class);

	UserData userData = null;

	FangtuZhengceBVO form = null;

	/**
	 * 统一接口
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("enter ZhengceProcessor.");
		
		form = (FangtuZhengceBVO) vo.getData();
		userData = vo.getUserData();

		Connection conn = null;

		try {
			// 获取数据库操作
			conn = DBResource.getConnection();
			ORManager orMgr = DBResource.getORManager();
			DBAccess dao = new DBAccess(conn, orMgr);
	
			
			if ( form != null && form.getJmzc() != null) {
				JMZC jmzc = doQuery(dao, form.getJmzc().getJmzcdm());
				if ( jmzc != null) 
					form.setJmzc(jmzc);
			}

		} finally {
			// 释放数据库连接
			DBResource.destroyConnection(conn);
		}

		logger.debug("jmzc zw: " + form.getJmzc().getJmzczw());
		return form;
	}

	private JMZC doQuery(DBAccess dao, String jmzcdm) throws BaseException {
		Vector cond = new Vector();
		cond.add("JMZCDM='" + jmzcdm + "'");

		List list = dao.query(JMZC.class, cond);
		if (list != null && list.size() > 0)
			return (JMZC) list.get(0);
		else
			return null;
	}

}