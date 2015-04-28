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
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChuzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.AlterFangtuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDChuzuAlterVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.TDChuzuDataVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDBGXX;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

public class AlterTDChuzuProcessor extends FangtuAlterProcessor {

	protected String alterTable = "SFDB.SF_JL_CZTDBGXX";
	private String jsjdm;
	Logger logger = Logger.getLogger(AlterTDChuzuProcessor.class.getName());

	/**
	 * ͳһ�ӿ�
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("process AlterTDChuzuProcessor.");

		alterFangtuVO = (AlterFangtuVO) vo.getData();
		userData = vo.getUserData();

		Connection conn = null;
		List list = null;
		FangtuForm form = new FangtuForm();
		
		try {
			// ��ȡ���ݿ����
			conn = DBResource.getConnection();
			ORManager orMgr = DBResource.getORManager();
			DBAccess dao = new DBAccess(conn, orMgr);

			List vos = alterFangtuVO.getTdChuzuList();

			if (vos != null) {
				logger.debug("vos size: " + vos.size());

				String djbh = KeyUtil.getKey();// �ǼǱ��
				jsjdm=alterFangtuVO.getJsjdm();
				
				for (int i = 0; i < vos.size(); i++) {
					TDChuzuDataVO bvo = (TDChuzuDataVO) vos.get(i);

					String opFlag = bvo.getOpFlag();

					// �Ѹ��˵Ǽ����ݴ���
					if (FangtuAlterBVO.OP_OLD.equals(opFlag)) {
						logger.debug("handle �Ѹ��˵Ǽ����ݴ���");
						handleOld(dao, bvo, djbh);
					}
					// �����ӡ�������ݴ���
					else if (FangtuAlterBVO.OP_NEW.equals(opFlag)) {
						logger.debug("handle �����ӡ�������ݴ���");
						handleAdd(dao, bvo, djbh);
					}
					// ��ɾ����������ݴ���
					else if (FangtuAlterBVO.OP_DELETE.equals(opFlag)) {
						logger.debug("handle ��ɾ����������ݴ���");
						handleDelete(dao, bvo);
					}
					// ���޸ġ�������ݴ���
					else if (FangtuAlterBVO.OP_UPDATE.equals(opFlag)) {
						logger.debug("handle ���޸ġ�������ݴ���");
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
			// �ͷ����ݿ�����
			DBResource.destroyConnection(conn);
		}
		
		form.setList(list);
		return form;

	}

	/**
	 * ���޸ġ�������ݴ���
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            ��������BVO
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleUpdate(DBAccess dao, TDChuzuDataVO bvo)
			throws BaseException {
		if ("1".equals(bvo.getDeleteFlag())) {
			// delete the update_alter data
			logger.debug("delete update_alter data");
			deleteAlterData(dao, bvo.getAlterVO().getId());
		}
		TDChuzuAlterVO alterVO =  bvo.getAlterVO();
		CZTDBGXX alterData = new CZTDBGXX();
		
		try {
			ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
			BeanUtils.copyProperties(alterData, alterVO);
			ConvertUtils.deregister(Timestamp.class);
		} catch (Exception e) {
			logger.error("��xmlvo���䵽ORMappingʱ����",e);
			e.printStackTrace();
			throw new ApplicationException("��xmlvo���䵽ORMappingʱ����",e);
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
			alterData.setJcbh(bvo.getRegVO().getId());
			dao.update(alterData);

		}
	}

	/**
	 * ��ɾ����������ݴ���
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            ��������BVO
	 * @throws ApplicationException
	 */
	private void handleDelete(DBAccess dao, TDChuzuDataVO bvo)
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
	 * �����ӡ�������ݴ����������������ӱ���;ɵ����ӱ���Ĵ���
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            ��������BVO
	 * @param djbh
	 *            �ǼǱ��
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleAdd(DBAccess dao, TDChuzuDataVO bvo, String djbh)
			throws BaseException {

		TDChuzuAlterVO alterVO =  bvo.getAlterVO();
		CZTDBGXX alterData = new CZTDBGXX();
		
		try {
			ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
			BeanUtils.copyProperties(alterData, alterVO);
			ConvertUtils.deregister(Timestamp.class);
		} catch (Exception e) {
			logger.error("��xmlvo���䵽ORMappingʱ����",e);
			e.printStackTrace();
			throw new ApplicationException("��xmlvo���䵽ORMappingʱ����",e);
		}
		
		
		boolean isOldData = false;
		if (alterData != null && alterData.getId() != null
				&& !alterData.getId().trim().equals("")) {
			isOldData = true;
		}

		if ("1".equals(bvo.getDeleteFlag())) {
			// ���alterData id ���ڣ����ʾҪɾ������������ݣ��������
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
	 * �Ѹ��˵Ǽ����ݴ���
	 * 
	 * @param dao
	 *            DAO
	 * @param bvo
	 *            ��������BVO
	 * @param djbh
	 *            �ǼǱ��
	 * @throws ApplicationException
	 * @throws BaseException
	 */
	private void handleOld(DBAccess dao, TDChuzuDataVO bvo, String djbh)
			throws BaseException {
		if ("1".equals(bvo.getDeleteFlag())) {
			// create delete_alter data
			logger.debug("create delete_alter data...");
			if (bvo.getRegVO() == null) {
				logger.error(EMPTY_REG_DATA);
				throw new ApplicationException(EMPTY_REG_DATA);
			}

			TDChuzuAlterVO alterVO =  bvo.getAlterVO();
			CZTDBGXX alterData = new CZTDBGXX();
			
			try {
				ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
				BeanUtils.copyProperties(alterData, alterVO);
				ConvertUtils.deregister(Timestamp.class);
			} catch (Exception e) {
				logger.error("��xmlvo���䵽ORMappingʱ����",e);
				e.printStackTrace();
				throw new ApplicationException("��xmlvo���䵽ORMappingʱ����",e);
			}

			if (alterData == null) {
				alterData = new CZTDBGXX();
			}
			alterData.setBglx(ConstantFangtu.ALTER_TYPE_DELETE);

			initAlterData(djbh, bvo.getRegVO(), alterData);
			clearBghData(alterData);
			// TODO: ����Ƿ����ظ��ı������
			dao.insert(alterData);

		}
		if ("1".equals(bvo.getUpdateFlag())) {
			// create update_alter data
			logger.debug("create update_alter data...");
			if (bvo.getRegVO() == null) {
				logger.error(EMPTY_REG_DATA);
				throw new ApplicationException(EMPTY_REG_DATA);
			}

			TDChuzuAlterVO alterVO =  bvo.getAlterVO();
			CZTDBGXX alterData = new CZTDBGXX();
			
			try {
				ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
				BeanUtils.copyProperties(alterData, alterVO);
				ConvertUtils.deregister(Timestamp.class);
			} catch (Exception e) {
				logger.error("��xmlvo���䵽ORMappingʱ����",e);
				e.printStackTrace();
				throw new ApplicationException("��xmlvo���䵽ORMappingʱ����",e);
			}

			if (alterData == null) {
				logger.error(EMPTY_ALTER_DATA);
				throw new ApplicationException(EMPTY_ALTER_DATA);
			}

			alterData.setBglx(ConstantFangtu.ALTER_TYPE_UPDATE);

			initAlterData(djbh, bvo.getRegVO(), alterData);

			// TODO: ����Ƿ����ظ��ı������
			dao.insert(alterData);
		}
	}

	private void initAlterData(String djbh, TDChuzuVO regVO, CZTDBGXX alterData) throws ApplicationException {

		CZTDJBXX regData = new CZTDJBXX();
		
		try {
			ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
			BeanUtils.copyProperties(regData, regVO);
			ConvertUtils.deregister(Timestamp.class);
			initAlterData(djbh, regData, alterData);
		} catch (Exception e) {
			logger.error("��xmlvo���䵽ORMappingʱ����",e);
			e.printStackTrace();
			throw new ApplicationException("��xmlvo���䵽ORMappingʱ����",e);
		}
	}

	/**
	 * ��ʼ���������, ���Ǽ����ݵ���Ϣ���Ƶ����ǰ
	 * 
	 * @param djbh
	 *            �ǼǱ��
	 * @param regData
	 *            �Ǽ�����
	 * @param alterData
	 *            �������
	 * @throws ApplicationException
	 */
	private void initAlterData(String djbh, CZTDJBXX regData, CZTDBGXX alterData)
			throws ApplicationException {
		
		//alterData.setJsjdm(userData.getYhid());
		alterData.setJsjdm(this.jsjdm);
		
		alterData.setDjbh(djbh);
		alterData.setJcbh(regData.getId());
		
		alterData.setBgqmpfmse(regData.getMpfmse()); //���ǰÿƽ����˰��
		alterData.setBgqnynse(regData.getNynse()); //���ǰ��Ӧ��˰��
		alterData.setBgqqzmsmj(regData.getQzmsmj()); //���ǰ������˰���
		alterData.setBgqqzysmj(regData.getQzysmj()); //���ǰ����Ӧ˰���
		alterData.setBgqtdmj(regData.getTdmj()); //���ǰ�������
		alterData.setBgqtdzl(regData.getTdzl()); //���ǰ��������
		alterData.setBgqtdzsh(regData.getTdzsh()); //���ǰ����֤���
		alterData.setBgqbz(regData.getBz()); //���ǰ��ע

		createBaseVO(alterData, ConstantFangtu.AUDIT_FLAG_NO, userData.yhid,
				userData.ssdwdm, false);
	}
	/**
	 * ����ɾ���ĵǼ�����, ������������, 
	 * @param alterData
	 */
	private void clearBghData(CZTDBGXX alterData) {
		alterData.setBghmpfmse(0);
		alterData.setBghnynse(0);
		alterData.setBghqzmsmj(0);
		alterData.setBghqzysmj(0);
		alterData.setBghtdmj(0);
		alterData.setBghtdzl(null);
		alterData.setBghtdzsh(null);
		alterData.setBghbz(null);
	}
	/**
	 * ȡ�ö�Ӧ�ı���
	 */
	public String getAlterTable() {
		return alterTable;
	}

}