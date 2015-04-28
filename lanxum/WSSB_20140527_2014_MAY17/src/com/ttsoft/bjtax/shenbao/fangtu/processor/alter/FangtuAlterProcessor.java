package com.ttsoft.bjtax.shenbao.fangtu.processor.alter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.FangtuAlterBVO;
import com.ttsoft.bjtax.shenbao.fangtu.KeyUtil;
import com.ttsoft.bjtax.shenbao.fangtu.PageableHelper;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.AlterFangtuVO;
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
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public abstract class FangtuAlterProcessor implements Processor {

	protected static final String EMPTY_ALTER_DATA = "变更数据为空，不能处理对应的变更请求.";

	protected static final String EMPTY_REG_DATA = "登记数据为空，不能处理对应的变更请求.";

	private Logger logger = Logger.getLogger(FangtuAlterProcessor.class);

	protected UserData userData = null;

	//protected FangtuForm form = null;
	AlterFangtuVO alterFangtuVO = null;
	private Timestamp inputDate = null; //填表日期

	/**
	 * 统一接口
	 */
	public abstract Object process(VOPackage vo) throws BaseException;

	/**
	 * 查询房土信息
	 * 
	 * @param dao
	 * @param destCat
	 * @return
	 * @throws BaseException
	 */
	protected List doQuery(DBAccess dao, int destCat, String jsjdm)
			throws BaseException {

		logger.debug("FangtuAlterProcessor doQuery: destCat:" + destCat
				+ ";jsjdm:" + jsjdm);

		String auditFlag = "0";
		List list = null;

		switch (destCat) {
		case ConstantFangtu.CAT_FW_ZIYONG:
			list = queryAlterInfo(dao, ZYFWJBXX.class, ZYFWBGXX.class, jsjdm,
					auditFlag);
			break;
		case ConstantFangtu.CAT_FW_CHUZU:
			list = queryAlterInfo(dao, CZFWJBXX.class, CZFWBGXX.class, jsjdm,
					auditFlag);
			break;
		case ConstantFangtu.CAT_FW_CHENGZU:
			list = queryAlterInfo(dao, CZUFWJBXX.class, CZUFWBGXX.class, jsjdm,
					auditFlag);
			break;
		case ConstantFangtu.CAT_TD_ZIYONG:
			list = queryAlterInfo(dao, ZYTDJBXX.class, ZYTDBGXX.class, jsjdm,
					auditFlag);
			break;
		case ConstantFangtu.CAT_TD_CHUZU:
			list = queryAlterInfo(dao, CZTDJBXX.class, CZTDBGXX.class, jsjdm,
					auditFlag);
			break;
		case ConstantFangtu.CAT_TD_CHENGZU:
			list = queryAlterInfo(dao, CZUTDJBXX.class, CZUTDBGXX.class, jsjdm,
					auditFlag);
			break;
		default:
			break;
		}

		return list;
	}

	protected List doQueryZhengceList(DBAccess dao) throws BaseException {
		Vector cond = new Vector();
		cond.add("ZFBS='" + 0 + "'");

		List list = dao.query(JMZC.class, cond);
		return list;
	}
	
	/**
	 * 查询用户的变更信息以及登记信息
	 * 
	 */
	private List queryAlterInfo(DBAccess dao, Class regClass, Class alterClass,
			String jsjdm, String auditFlag) throws BaseException {

		// 查询用户的变更信息以及登记信息

		// 查询未复核的房土信息
		Vector cond = new Vector();
		cond.add("FHBS='" + auditFlag + "'");
		cond.add("JSJDM='" + jsjdm + "'");

		List alterList = dao.query(alterClass, cond);

		// 查询登记信息,不包括已经查询出来的有变更信息的登记数据(修改, 删除业务)
		cond = new Vector();

		// 20060817 fsha comment, hazz 要求带出未复核登记数据，针对自用房屋，自用土地信息
		if ( ! regClass.getName().equals( ZYFWJBXX.class.getName()) &&
				 ! regClass.getName().equals( ZYTDJBXX.class.getName())  ) {
			 cond.add("FHBS='1'"); // 要复核通过的登记数据
		}
		

		cond.add("JSJDM='" + jsjdm + "'");

		List oldRegList = dao.query(regClass, cond);
		Map repeatMap = new HashMap();

		// 过滤已经查询出来的有变更信息的登记数据(修改, 删除业务)
		List regList = filterRegInfo(alterList, oldRegList, repeatMap);

		// 组装成新的BVO LIST
		List list = new ArrayList();

		for (Iterator iter = alterList.iterator(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			FangtuAlterBVO bvo = new FangtuAlterBVO();
			bvo.setAlterData(obj);
			// BGLX 变更类型,0-增加,1-删除,2-修改
			try {
				String bglx = (String) PropertyUtils.getProperty(obj, "bglx");
				if (ConstantFangtu.ALTER_TYPE_ADD.equals(bglx)) {
					bvo.setOpFlag(FangtuAlterBVO.OP_NEW);
				} else if (ConstantFangtu.ALTER_TYPE_DELETE.equals(bglx)) {
					bvo.setOpFlag(FangtuAlterBVO.OP_DELETE);
				} else { // ConstantFangtu.ALTER_TYPE_UPDATE.equals(bglx)
					bvo.setOpFlag(FangtuAlterBVO.OP_UPDATE);
				}

			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new ApplicationException("取得变更记录的变更类型时出错!");
			}

			try {
				String jcbh = (String) PropertyUtils.getProperty(obj, "jcbh");
				if ( repeatMap.get(jcbh) != null ) {
					bvo.setRegData(repeatMap.get(jcbh));
				} else {
					bvo.setRegData(regClass.newInstance());
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new ApplicationException("创建新的登记类型实例时出错!");
			}
			list.add(bvo);

		}
		for (Iterator iter = regList.iterator(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			FangtuAlterBVO bvo = new FangtuAlterBVO();
			bvo.setRegData(obj);

			try {
				// 根据登记数据是否复核设置不同的opFlag
				// 复核标识,0-未复核,1-已复核
				String fhbs = (String) PropertyUtils.getProperty(obj, "fhbs");
				if ("0".equals(fhbs)) {
					bvo.setOpFlag(FangtuAlterBVO.OP_OLD_UNCHECK);
				} else { // "1".equals( fhbs )
					bvo.setOpFlag(FangtuAlterBVO.OP_OLD);
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new ApplicationException("取登记数据的复核标识时出错!");
			}

			try {
				bvo.setAlterData(alterClass.newInstance());
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new ApplicationException("创建新的登记类型实例时出错!");
			}
			list.add(bvo);
			
		}
		
		return PageableHelper.pageIt(list, alterFangtuVO);
		//return list;
	}

	/**
	 * 过滤已经查询出来的有变更信息的登记数据(修改, 删除业务)
	 * 
	 * @param alterList
	 * @param oldRegList
	 * @return
	 * @throws ApplicationException
	 */
	private List filterRegInfo(List alterList, List oldRegList, Map repeatMap)
			throws ApplicationException {
		List regList = new ArrayList();

		try {

			for (Iterator iter = oldRegList.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				String id = (String) PropertyUtils.getProperty(element, "id");
				logger.debug("id:" + id);

				boolean flag = true;
				for (Iterator iterator = alterList.iterator(); iterator
						.hasNext();) {
					Object obj = (Object) iterator.next();
					String jcbh = (String) PropertyUtils.getProperty(obj,
							"jcbh");
					if (id.equals(jcbh)) {
						flag = false;
						repeatMap.put(id, element);
						break;
					}
				}
				if (flag) {
					regList.add(element);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new ApplicationException("根据变更记录过滤登记数据时出错!");
		}
		return regList;
	}

	/**
	 * 根据ID主键删除变更数据
	 * 
	 * @param dao
	 * @param id
	 * @return
	 * @throws ApplicationException
	 */
	protected int deleteAlterData(DBAccess dao, String id)
			throws ApplicationException {

		String sql = "delete from " + getAlterTable() + " where ID='" + id
				+ "'";

		try {

			logger.debug(sql);
			return dao.updateSQL(sql);

		} catch (Exception ep) {
			ep.printStackTrace();
			throw new ApplicationException("更新自用房屋数据错误！");
		}
	}

	/**
	 * 子类需要实现当前processor处理的变更表名
	 * 
	 * @return
	 */
	public abstract String getAlterTable();

	/**
	 * 初始化VO
	 * 
	 * @param vo
	 *            初始化对应的VO
	 * @param auditFlag
	 *            复核标识
	 * @param yhid
	 *            当前用户名称
	 * @param jgdm
	 *            税务机关组织机构代码
	 * @param isUpdate
	 *            是否是更新
	 * @throws ApplicationException
	 */
	protected void createBaseVO(Object vo, String auditFlag, String yhid,
			String jgdm, boolean isUpdate) throws ApplicationException {

		Timestamp now = new Timestamp(System.currentTimeMillis());

		try {

			PropertyUtils.setProperty(vo, "fhbs", auditFlag); // 复核标识

			if (ConstantFangtu.AUDIT_FLAG_YES.equals(auditFlag)) {
				PropertyUtils.setProperty(vo, "fhr", yhid); // 复核人
				PropertyUtils.setProperty(vo, "fhrq", now); // 复核时间
			}
			PropertyUtils.setProperty(vo, "tbrq", getInputDate()); // 填表日期

			PropertyUtils.setProperty(vo, "swjgzzjgdm", jgdm); // 税务机关组织机构代码
			PropertyUtils.setProperty(vo, "qxdm", jgdm.substring(0, 2));

			PropertyUtils.setProperty(vo, "lrr", yhid); // 录入人
			PropertyUtils.setProperty(vo, "lrrq", now); // 录入日期

			if (!isUpdate) {
                PropertyUtils.setProperty(vo, "id", KeyUtil.getKey()); // ID
                
                PropertyUtils.setProperty(vo, "cjr", yhid); // 创建人
				PropertyUtils.setProperty(vo, "cjrq", now); // 创建日期
			}
		} catch (Exception e) {
			logger.error("初始化VO错误", e);
			e.printStackTrace();
			throw new ApplicationException("初始化VO错误");
		}
	}

	/**
	 * 取得填表日期
	 * @return
	 */
	protected Timestamp getInputDate() {
		if ( inputDate != null ) return inputDate;
		try {
			inputDate = DateTimeUtil.stringToTimestamp(this.alterFangtuVO.getInputDate(),
					ConstantFangtu.DATE_FORMAT);
		} catch (Exception e) {
			logger.warn("填表日期格式错误，使用系统当前时间.", e);
			inputDate = new Timestamp(System.currentTimeMillis());
		}
		return inputDate;
	}

}