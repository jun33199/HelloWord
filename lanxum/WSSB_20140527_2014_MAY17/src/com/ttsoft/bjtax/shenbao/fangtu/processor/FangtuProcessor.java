package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORObject;
import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.PageableHelper;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuForm;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDJBXX;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

public abstract class FangtuProcessor implements Processor {

	private static Logger logger = Logger.getLogger(FangtuProcessor.class);

	UserData userData = null;
	FangtuVO fangtuVO = null;
	FangtuForm form = new FangtuForm();
	
	/**
	 * 统一接口
	 */
	public abstract Object process(VOPackage vo) throws BaseException;

	protected List doQuery(DBAccess dao, int destCat)
			throws BaseException {

		String jsjdm = userData.getYhid();
		logger.debug("Fangtu doQuery: destCat:" + destCat + ";jsjdm:" + jsjdm);

		List ret = new ArrayList();

		Vector cond = new Vector();
		cond.add("FHBS='" + 0 + "'");
		cond.add("JSJDM='" + jsjdm + "'");

		switch (destCat) {
		case ConstantFangtu.CAT_FW_ZIYONG:
			ret = query(dao, cond, ZYFWJBXX.class);
			break;
		case ConstantFangtu.CAT_FW_CHUZU:
			ret = query(dao, cond, CZFWJBXX.class);
			break;
		case ConstantFangtu.CAT_FW_CHENGZU:
			ret = query(dao, cond, CZUFWJBXX.class);
			break;
		case ConstantFangtu.CAT_TD_ZIYONG:
			ret = query(dao, cond, ZYTDJBXX.class);
			break;
		case ConstantFangtu.CAT_TD_CHUZU:
			ret = query(dao, cond, CZTDJBXX.class);
			break;
		case ConstantFangtu.CAT_TD_CHENGZU:
			ret = query(dao, cond, CZUTDJBXX.class);
			break;
		default:
			break;
		}

		return ret;
	}

	/**
	 * @param dao
	 * @param cond
	 * @param voClass TODO
	 * @return
	 * @throws BaseException
	 */
	private List query(DBAccess dao, Vector cond, Class voClass) throws BaseException {
		List list = dao.query(voClass, cond);
		setTotal(form, Integer.parseInt(fangtuVO.getDestCat()), list);
		return PageableHelper.pageIt(list, fangtuVO);
	}

	protected List doQueryZJLX( DBAccess dao) throws BaseException {
		//select zjlxdm value,zjlxmc descr from dmdb.gy_dm_zjlx where zjlxdm<>'01' and zjlxdm<>'80'
		return null;
	}
	
	
	/**
	 * @param addList
	 * @param updateList
	 * @param deleteList
	 * @return
	 * @throws BaseException
	 */
	protected FangtuForm execute(List addList, List updateList, List deleteList) throws BaseException {
		List list = null;
		Connection conn = null;
	
		try {
			// 获取数据库操作
			conn = DBResource.getConnection();
			ORManager orMgr = DBResource.getORManager();
			DBAccess dao = new DBAccess(conn, orMgr);
	
			doAdd(addList, dao);
			doUpdate(updateList, dao);
			doDelete(deleteList, dao);
	
			list = doQuery(dao, Integer.parseInt(fangtuVO.getDestCat()));
			int a = (list == null) ? 0 : list.size();
			logger.debug("doQuery list size: " + a);
	
		} finally {
			// 释放数据库连接
			DBResource.destroyConnection(conn);
		}
		
		
		form.setList(list);
		
		logger.debug("set pageable info.");
		
		form.setCurrentPageNum(fangtuVO.getCurrentPageNum());
		form.setTotalItemsNum(fangtuVO.getTotalItemsNum());
		form.setTotalPageNum(fangtuVO.getTotalPageNum());
		form.setPageSize(fangtuVO.getPageSize());
		
		logger.debug("end set pageable info.");
		
		return form;
	}

	protected abstract void doDelete(List deleteList, DBAccess dao) throws BaseException;

	protected abstract  void doUpdate(List updateList, DBAccess dao) throws BaseException;

	protected abstract  void doAdd(List addList, DBAccess dao) throws BaseException;

	private void setTotal(FangtuForm fangtuForm, int destCat, List arr) {
		if (arr != null && arr.size() != 0) {
			// 处理小计
			switch (destCat) {
			case ConstantFangtu.CAT_FW_ZIYONG: {
				double fcyz = 0.0;
				double swjggz = 0.0;
				double qzmsyz = 0.0;
				double qzysyz = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					ZYFWJBXX obj = (ZYFWJBXX) iter.next();
					logger.debug("fcyz: " + obj.getFcyz());
					
					fcyz += obj.getFcyz();
					logger.debug("fcyz: " + fcyz);
					
					swjggz += obj.getSwjggz();
					qzmsyz += obj.getQzmsyz();
					qzysyz += obj.getQzysyz();
					nynse += obj.getNynse();
				}
				

				fangtuForm.setFwZiyongTotal(new FwZiyongTotal(fcyz, swjggz,
						qzmsyz, qzysyz, nynse));
			}
				break;
			case ConstantFangtu.CAT_FW_CHUZU: {
				double nzjsr = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					CZFWJBXX obj = (CZFWJBXX) iter.next();
					nzjsr += obj.getNzjsr();
					nynse += obj.getNynse();
				}

				fangtuForm.setFwChuzuTotal(new FwChuzuTotal(nzjsr, nynse));
			}
				break;
			case ConstantFangtu.CAT_TD_ZIYONG: {
				double tdmj = 0.0;
				double qzmsmj = 0.0;
				double qzysmj = 0.0;
				double mpfmse = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					ZYTDJBXX obj = (ZYTDJBXX) iter.next();

					tdmj += obj.getTdmj();
					qzmsmj += obj.getQzmsmj();
					qzysmj += obj.getQzysmj();
					mpfmse += obj.getMpfmse();
					nynse += obj.getNynse();
				}

				fangtuForm.setTdZiyongTotal(new TdZiyongTotal(tdmj, qzmsmj,
						qzysmj, mpfmse, nynse));
			}
				break;
			case ConstantFangtu.CAT_TD_CHUZU:{
				double tdmj = 0.0;
				double qzmsmj = 0.0;
				double qzysmj = 0.0;
				double mpfmse = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					CZTDJBXX obj = (CZTDJBXX) iter.next();

					tdmj += obj.getTdmj();
					qzmsmj += obj.getQzmsmj();
					qzysmj += obj.getQzysmj();
					mpfmse += obj.getMpfmse();
					nynse += obj.getNynse();
				}

				fangtuForm.setTdChuzuTotal(new TdChuzuTotal(tdmj, qzmsmj,
						qzysmj, mpfmse, nynse));
				
			}

			default:

			}
		}
	}

	/**
	 * 校验是否符合应用逻辑主键
	 * 
	 * @param vos
	 * @param dao
	 * @param clazz TODO
	 * @param uniqueColumn
	 * @return 如果没有错误返回空
	 * @throws BaseException
	 */
	protected String checkDupRecord(List vos, DBAccess dao, Class clazz, String[] uniqueColumn) throws BaseException {
	
		if (uniqueColumn == null || uniqueColumn.length == 0)
			return null;
	
		for (Iterator iter = vos.iterator(); iter.hasNext();) {
			ORObject vo = (ORObject) iter.next();
			Vector cond = new Vector();
			for (int i = 0; i < uniqueColumn.length; i++) {
				try {
					String column = uniqueColumn[i];
					Object property = PropertyUtils.getProperty(vo, column);
					String s = column + "='" + property + "'";
					if (property == null || property.equals("")) {
						s = "(" + column + " is null or " + column + " = '"
								+ property + "')";
					}

					logger.debug("cond [" + i + "]: " + s);
					cond.add(s);
				} catch (Exception e) {
					e.printStackTrace();
					ExceptionUtil.getBaseException(e);
				}
			}
	
			// cond.add("JSJDM='" + vo.getJsjdm() + "'");
			// cond.add("FWZL='" + vo.getFwzl() + "'");
			// cond.add("CQZSH='" + vo.getCqzsh() + "'");
	
			List list = dao.query(clazz, cond);
	
			if (list != null && list.size() != 0) {
				
				try {
					//如果唯一查出的记录等于传进来的vo的id, 说明是同一条记录, 直接返回
					if ( list.size() == 1 ) {
						ORObject obj = (ORObject)list.get(0);
						String id = (String) PropertyUtils.getProperty(vo, "id");
						String oldId = (String) PropertyUtils.getProperty(obj, "id");
						if ( oldId.equals(id) ) return null;
					}
				} catch (Exception e ) {
					throw new ApplicationException(e.getMessage(), e);
				}
				
				// 如果存在重复记录, 返回重复的逻辑主键
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < uniqueColumn.length; i++) {
					try {
						sb.append("'" + PropertyUtils.getProperty(vo, uniqueColumn[i]) + "',");
					} catch (Exception e) {}
				}
				return sb.toString();
	
			}
		}
	
		return null;
	
	}	
}
