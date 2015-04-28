package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.LabelValueBean;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuPrintBVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZFWBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUFWBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.JMZC;
import com.ttsoft.bjtax.shenbao.model.domain.ZYFWBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDJBXX;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

public class FTPrintProcessor implements Processor {
	Logger logger = Logger.getLogger(FTPrintProcessor.class);
	
	UserData userData = null;
	FangtuPrintBVO printBVO = null;
	
	/**
	 * 统一接口
	 */
	public Object process(VOPackage vo) throws BaseException {

		printBVO = (FangtuPrintBVO) vo.getData();
		userData = vo.getUserData();

		FangtuPrintBVO tempBVO = null;
		
		switch(vo.getAction()){
		case ConstantFangtu.PRINT_DJ_ACTION:
			tempBVO = (FangtuPrintBVO)doPrintDJ();
			break;
		case ConstantFangtu.PRINT_BG_ACTION:
			tempBVO = (FangtuPrintBVO)doPrintBG();
			break;
		default:
			break;
		}


		return tempBVO;
	}

	public Object doPrintDJ() throws BaseException 
	{
		FangtuPrintBVO tempBVO = new FangtuPrintBVO();
		
		List ziyongFWList = new ArrayList();
		List chengzuFWList = new ArrayList();
		List chuzuFWList = new ArrayList();
		List ziyongTDList = new ArrayList();
		List chengzuTDList = new ArrayList();
		List chuzuTDList = new ArrayList();

		Vector cond = new Vector();
		cond.add("FHBS='" + 0 + "'");
		cond.add("JSJDM='" + printBVO.getJsjdm() + "'");

		Connection conn = null;

		try {
			// 获取数据库操作
			conn = DBResource.getConnection();
			ORManager orMgr = DBResource.getORManager();
			DBAccess dao = new DBAccess(conn, orMgr);

			ziyongFWList = dao.query(ZYFWJBXX.class, cond);


			chengzuFWList = dao.query(CZUFWJBXX.class, cond);


			chuzuFWList = dao.query(CZFWJBXX.class, cond);


			ziyongTDList = dao.query(ZYTDJBXX.class, cond);


			chengzuTDList = dao.query(CZUTDJBXX.class, cond);


			chuzuTDList = dao.query(CZTDJBXX.class, cond);

			tempBVO.setZhengceList(doQueryZhengceList(dao));

		} finally {
			// 释放数据库连接
			DBResource.destroyConnection(conn);
		}

		tempBVO.setZiyongFWList(ziyongFWList);
		tempBVO.setChengzuFWList(chengzuFWList);
		tempBVO.setChuzuFWList(chuzuFWList);
		tempBVO.setZiyongTDList(ziyongTDList);
		tempBVO.setChengzuTDList(chengzuTDList);
		tempBVO.setChuzuTDList(chuzuTDList);		
		return tempBVO;
	}

	public Object doPrintBG() throws BaseException 
	{
		FangtuPrintBVO tempBVO = new FangtuPrintBVO();
		
		List bgziyongFWList = new ArrayList();
		List bgchengzuFWList = new ArrayList();
		List bgchuzuFWList = new ArrayList();
		List bgziyongTDList = new ArrayList();
		List bgchengzuTDList = new ArrayList();
		List bgchuzuTDList = new ArrayList();

		Vector cond = new Vector();
		cond.add("FHBS='" + 0 + "'");
		cond.add("JSJDM='" + printBVO.getJsjdm() + "'");

		Connection conn = null;

		try {
			// 获取数据库操作
			conn = DBResource.getConnection();
			ORManager orMgr = DBResource.getORManager();
			DBAccess dao = new DBAccess(conn, orMgr);

			bgziyongFWList = dao.query(ZYFWBGXX.class, cond);


			bgchengzuFWList = dao.query(CZUFWBGXX.class, cond);


			bgchuzuFWList = dao.query(CZFWBGXX.class, cond);


			bgziyongTDList = dao.query(ZYTDBGXX.class, cond);


			bgchengzuTDList = dao.query(CZUTDBGXX.class, cond);


			bgchuzuTDList = dao.query(CZTDBGXX.class, cond);

			tempBVO.setZhengceList(doQueryZhengceList(dao));
			
		} finally {
			// 释放数据库连接
			DBResource.destroyConnection(conn);
		}

		tempBVO.setBgziyongFWList(bgziyongFWList);
		tempBVO.setBgchengzuFWList(bgchengzuFWList);
		tempBVO.setBgchuzuFWList(bgchuzuFWList);
		tempBVO.setBgziyongTDList(bgziyongTDList);
		tempBVO.setBgchengzuTDList(bgchengzuTDList);
		tempBVO.setBgchuzuTDList(bgchuzuTDList);	
		
		;
		return tempBVO;
	}
	
	protected List doQueryZhengceList(DBAccess dao) throws BaseException {
		Vector cond = new Vector();
		cond.add("ZFBS='" + 0 + "'");

		List list = dao.query(JMZC.class, cond);
		logger.debug("from db list size: " + list.size());
		
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			JMZC vo = (JMZC) iter.next();
			newList.add(new LabelValueBean(vo.getJmzcwh(), vo.getJmzcdm()));
		}
		logger.debug("new list size: " + newList.size());
		return newList;
	}

}