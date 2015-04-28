package com.ttsoft.bjtax.shenbao.fangtu.processor.alter;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;

import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.FangtuAlterBVO;
import com.ttsoft.bjtax.shenbao.fangtu.KeyUtil;
import com.ttsoft.bjtax.shenbao.fangtu.SimpleTimestampConvert;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuForm;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDZiyongVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.AlterFangtuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDZiyongAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDZiyongDataVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDJBXX;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

public class AlterTDZiyongProcessor extends FangtuAlterProcessor {

	protected String alterTable = "SFDB.SF_JL_ZYTDBGXX";
	private String jsjdm;
	Logger logger = Logger.getLogger(AlterTDZiyongProcessor.class.getName());

	/**
	 * 统一接口
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("process AlterTDZiyongProcessor.");

		alterFangtuVO = (AlterFangtuVO) vo.getData();
		userData = vo.getUserData();

		Connection conn = null;
		List list = null;
		FangtuForm form = new FangtuForm();
		
		try {
			// 获取数据库操作
			conn = DBResource.getConnection();
			ORManager orMgr = DBResource.getORManager();
			DBAccess dao = new DBAccess(conn, orMgr);

			List vos = alterFangtuVO.getTdZiyongList();
			
			if (vos != null) {
				logger.debug("vos size: " + vos.size());

				String djbh = KeyUtil.getKey();// 登记编号
				jsjdm=alterFangtuVO.getJsjdm();
				
				for (int i = 0; i < vos.size(); i++) {
					TDZiyongDataVO bvo = (TDZiyongDataVO) vos.get(i);

					String opFlag = bvo.getOpFlag();
					
					// 已复核登记数据处理
					if (FangtuAlterBVO.OP_OLD.equals(opFlag)) {
						logger.debug("handle 已复核登记数据处理");
						handleOld(dao, bvo, djbh);
					}
					// 未复核登记数据处理
					else if (FangtuAlterBVO.OP_OLD_UNCHECK.equals(opFlag)) {
						logger.debug("handle 未复核登记数据处理");
						handleOldUncheck(dao, bvo, djbh);
					}
					// “增加”变更数据处理
					else if (FangtuAlterBVO.OP_NEW.equals(opFlag)) {
						logger.debug("handle “增加”变更数据处理");
						handleAdd(dao, bvo, djbh);
					}
					// “删除”变更数据处理
					else if (FangtuAlterBVO.OP_DELETE.equals(opFlag)) {
						logger.debug("handle “删除”变更数据处理");
						handleDelete(dao, bvo);
					}
					// “修改”变更数据处理
					else if (FangtuAlterBVO.OP_UPDATE.equals(opFlag)) {
						logger.debug("handle “修改”变更数据处理");
						handleUpdate(dao, bvo);
					} else {
						logger.error("Unknown opFlag[" + opFlag + "].");
					}

				}

				list = doQuery(dao, Integer.parseInt( alterFangtuVO.getDestCat() ),
										alterFangtuVO.getJsjdm());
				
				form.setZhengceList(doQueryZhengceList(dao));
				
				logger.debug("set pageable info.");
				
				form.setCurrentPageNum(alterFangtuVO.getCurrentPageNum());
				form.setTotalItemsNum(alterFangtuVO.getTotalItemsNum());
				form.setTotalPageNum(alterFangtuVO.getTotalPageNum());
				form.setPageSize(alterFangtuVO.getPageSize());
				
				logger.debug("end set pageable info.");
				
			}
		} finally {
			// 释放数据库连接
			DBResource.destroyConnection(conn);
		}
		
		form.setList(list);
		return form;

	}

	/**
	 * “修改”变更数据处理
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            房土数据BVO
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleUpdate(DBAccess dao, TDZiyongDataVO bvo)
			throws BaseException {
		if ("1".equals(bvo.getDeleteFlag())) {
			// delete the update_alter data
			logger.debug("delete update_alter data");
			deleteAlterData(dao, bvo.getAlterVO().getId());
		}
		TDZiyongAlterVO alterVO =  bvo.getAlterVO();
		ZYTDBGXX alterData = new ZYTDBGXX();
		
		try {
			ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
			BeanUtils.copyProperties(alterData, alterVO);
			ConvertUtils.deregister(Timestamp.class);
		} catch (Exception e) {
			logger.error("将xmlvo反射到ORMapping时出错",e);
			e.printStackTrace();
			throw new ApplicationException("将xmlvo反射到ORMapping时出错",e);
		}
			
		if ("1".equals(bvo.getUpdateFlag())) {
			// update the update_alter data
			logger.debug("update update_alter data");
			if (alterData == null) {
				logger.error(EMPTY_ALTER_DATA);
				throw new ApplicationException(EMPTY_ALTER_DATA);
			}
			createBaseVO(alterData, ConstantFangtu.AUDIT_FLAG_NO,
					userData.yhid, userData.ssdwdm, true);
			logger.debug("alterData.getCjrq(): " + alterData.getCjrq());
			//修改变更数据时,填写基础编号
			alterData.setJcbh(bvo.getRegVO().getId());
			
			dao.update(alterData);

		}
	}

	/**
	 * “删除”变更数据处理
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            房土数据BVO
	 * @throws ApplicationException
	 */
	private void handleDelete(DBAccess dao, TDZiyongDataVO bvo)
			throws ApplicationException {
		if ("1".equals(bvo.getDeleteFlag())) {
			// delete the delete_alter data
			deleteAlterData(dao, bvo.getAlterVO().getId());
		}
		if ("1".equals(bvo.getUpdateFlag())) {
			// unexpect op flag
			logger.warn("unexpect op flag");
		}
	}

	/**
	 * “增加”变更数据处理，包括新增的增加变更和旧的增加变更的处理
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            房土数据BVO
	 * @param djbh
	 *            登记编号
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleAdd(DBAccess dao, TDZiyongDataVO bvo, String djbh)
			throws BaseException {

		TDZiyongAlterVO alterVO =  bvo.getAlterVO();
		ZYTDBGXX alterData = new ZYTDBGXX();
		
		try {
			ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
			BeanUtils.copyProperties(alterData, alterVO);
			ConvertUtils.deregister(Timestamp.class);
		} catch (Exception e) {
			logger.error("将xmlvo反射到ORMapping时出错",e);
			e.printStackTrace();
			throw new ApplicationException("将xmlvo反射到ORMapping时出错",e);
		}
		
		
		boolean isOldData = false;
		if (alterData != null && alterData.getId() != null
				&& !alterData.getId().trim().equals("")) {
			isOldData = true;
		}

		if ("1".equals(bvo.getDeleteFlag())) {
			// 如果alterData id 存在，则表示要删除该条变更数据，否则不理会
			if (isOldData) {
				deleteAlterData(dao, alterData.getId());
			}

		} else if ("1".equals(bvo.getUpdateFlag())) {
			// unexpect op flag
			if (isOldData) {
				createBaseVO(alterData, ConstantFangtu.AUDIT_FLAG_NO,
						userData.getYhid(), userData.ssdwdm, true);
				dao.update(alterData);
			}
		} else {
			if (!isOldData) {
				if (alterData == null) {
					logger.error(EMPTY_ALTER_DATA);
					throw new ApplicationException(EMPTY_ALTER_DATA);
				}
				alterData.setBglx(ConstantFangtu.ALTER_TYPE_ADD);
				alterData.setDjbh(djbh);
				alterData.setJsjdm(this.alterFangtuVO.getJsjdm());
				createBaseVO(alterData, ConstantFangtu.AUDIT_FLAG_NO,
						userData.yhid, userData.ssdwdm, false);
				dao.insert(alterData);
			}
		}
	}

	/**
	 * 未复核登记数据处理
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            房土数据BVO
	 * @param djbh
	 *            登记编号
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleOldUncheck(DBAccess dao, TDZiyongDataVO bvo, String djbh)
			throws BaseException {
		if ("1".equals(bvo.getDeleteFlag())) {
			// unexpect op flag
			logger.warn("unexpect op flag");
		}
		if ("1".equals(bvo.getUpdateFlag())) {
			// create update_alter data, only update its
			// 减免信息
			logger.debug("create update_alter data, only update its 减免信息...");
			if (bvo.getRegVO() == null) {
				logger.error(EMPTY_REG_DATA);
				throw new ApplicationException(EMPTY_REG_DATA);
			}

			TDZiyongAlterVO alterVO =  bvo.getAlterVO();
			ZYTDBGXX alterData = new ZYTDBGXX();
			
			try {
				ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
				BeanUtils.copyProperties(alterData, alterVO);
				ConvertUtils.deregister(Timestamp.class);
			} catch (Exception e) {
				logger.error("将xmlvo反射到ORMapping时出错",e);
				e.printStackTrace();
				throw new ApplicationException("将xmlvo反射到ORMapping时出错",e);
			}

			if (alterData == null) {
				logger.error(EMPTY_ALTER_DATA);
				throw new ApplicationException(EMPTY_ALTER_DATA);
			}

			alterData.setBglx(ConstantFangtu.ALTER_TYPE_UPDATE);

			initAlterData(djbh, bvo.getRegVO(), alterData);
			// TODO: 检查是否有重复的变更请求
			dao.insert(alterData);
		}
	}

	/**
	 * 已复核登记数据处理
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            房土数据BVO
	 * @param djbh
	 *            登记编号
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleOld(DBAccess dao, TDZiyongDataVO bvo, String djbh)
			throws BaseException {
		if ("1".equals(bvo.getDeleteFlag())) {
			// create delete_alter data
			logger.debug("create delete_alter data...");
			if (bvo.getRegVO() == null) {
				logger.error(EMPTY_REG_DATA);
				throw new ApplicationException(EMPTY_REG_DATA);
			}

			TDZiyongAlterVO alterVO =  bvo.getAlterVO();
			ZYTDBGXX alterData = new ZYTDBGXX();
			
			try {
				ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
				BeanUtils.copyProperties(alterData, alterVO);
				ConvertUtils.deregister(Timestamp.class);
			} catch (Exception e) {
				logger.error("将xmlvo反射到ORMapping时出错",e);
				e.printStackTrace();
				throw new ApplicationException("将xmlvo反射到ORMapping时出错",e);
			}

			if (alterData == null) {
				alterData = new ZYTDBGXX();
			}
			alterData.setBglx(ConstantFangtu.ALTER_TYPE_DELETE);

			initAlterData(djbh, bvo.getRegVO(), alterData);
			clearBghData(alterData);
			// TODO: 检查是否有重复的变更请求
			dao.insert(alterData);

		}
		if ("1".equals(bvo.getUpdateFlag())) {
			// create update_alter data
			logger.debug("create update_alter data...");
			if (bvo.getRegVO() == null) {
				logger.error(EMPTY_REG_DATA);
				throw new ApplicationException(EMPTY_REG_DATA);
			}

			TDZiyongAlterVO alterVO =  bvo.getAlterVO();
			ZYTDBGXX alterData = new ZYTDBGXX();
			
			try {
				ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
				BeanUtils.copyProperties(alterData, alterVO);
				ConvertUtils.deregister(Timestamp.class);
			} catch (Exception e) {
				logger.error("将xmlvo反射到ORMapping时出错",e);
				e.printStackTrace();
				throw new ApplicationException("将xmlvo反射到ORMapping时出错",e);
			}

			if (alterData == null) {
				logger.error(EMPTY_ALTER_DATA);
				throw new ApplicationException(EMPTY_ALTER_DATA);
			}

			alterData.setBglx(ConstantFangtu.ALTER_TYPE_UPDATE);

			initAlterData(djbh, bvo.getRegVO(), alterData);

			// TODO: 检查是否有重复的变更请求
			dao.insert(alterData);
		}
	}

	private void initAlterData(String djbh, TDZiyongVO regVO, ZYTDBGXX alterData) throws ApplicationException {

		ZYTDJBXX regData = new ZYTDJBXX();
		
		try {
			ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
			BeanUtils.copyProperties(regData, regVO);
			ConvertUtils.deregister(Timestamp.class);
			initAlterData(djbh, regData, alterData);
		} catch (Exception e) {
			logger.error("将xmlvo反射到ORMapping时出错",e);
			e.printStackTrace();
			throw new ApplicationException("将xmlvo反射到ORMapping时出错",e);
		}
	}

	/**
	 * 初始化变更数据, 将登记数据的信息复制到变更前
	 * 
	 * @param djbh
	 *            登记编号
	 * @param regData
	 *            登记数据
	 * @param alterData
	 *            变更数据
	 * @throws ApplicationException
	 */
	private void initAlterData(String djbh, ZYTDJBXX regData, ZYTDBGXX alterData)
			throws ApplicationException {
		
		//alterData.setJsjdm(userData.getYhid());
		alterData.setJsjdm(this.jsjdm);
		alterData.setDjbh(djbh);
		alterData.setJcbh(regData.getId());
		
		alterData.setBgqmpfmse(regData.getMpfmse()); //变更前每平方米税额
		alterData.setBgqnynse(regData.getNynse()); //变更前年应纳税额
		alterData.setBgqqzmsmj(regData.getQzmsmj()); //变更前其中免税面积
		alterData.setBgqqzysmj(regData.getQzysmj()); //变更前其中应税面积
		alterData.setBgqtdmj(regData.getTdmj()); //变更前土地面积
		alterData.setBgqtdzl(regData.getTdzl()); //变更前土地坐落
		alterData.setBgqtdzsh(regData.getTdzsh()); //变更前土地证书号
		alterData.setBgqbz(regData.getBz()); //变更前备注
		alterData.setBgqsfdj(regData.getSfdj());//变更前是否代缴
		alterData.setBgqsfjnws(regData.getSfjnws());//是否缴纳外商投资企业土地使用费
		
		createBaseVO(alterData, ConstantFangtu.AUDIT_FLAG_NO, userData.yhid,
				userData.ssdwdm, false);
	}
	/**
	 * 对于删除的登记数据, 清除变更后数据, 
	 * @param alterData
	 */
	private void clearBghData(ZYTDBGXX alterData) {
		alterData.setBghmpfmse(0);
		alterData.setBghnynse(0);
		alterData.setBghqzmsmj(0);
		alterData.setBghqzysmj(0);
		alterData.setBghtdmj(0);
		alterData.setBghtdzl(null);
		alterData.setBghtdzsh(null);
		alterData.setBghbz(null);
		alterData.setBghsfdj(null);
		alterData.setBghsfjnws(null);
	}
	/**
	 * 取得对应的表名
	 */
	public String getAlterTable() {
		return alterTable;
	}

}