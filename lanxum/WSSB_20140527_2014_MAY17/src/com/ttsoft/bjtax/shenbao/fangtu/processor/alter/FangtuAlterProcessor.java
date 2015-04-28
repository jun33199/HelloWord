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

	protected static final String EMPTY_ALTER_DATA = "�������Ϊ�գ����ܴ����Ӧ�ı������.";

	protected static final String EMPTY_REG_DATA = "�Ǽ�����Ϊ�գ����ܴ����Ӧ�ı������.";

	private Logger logger = Logger.getLogger(FangtuAlterProcessor.class);

	protected UserData userData = null;

	//protected FangtuForm form = null;
	AlterFangtuVO alterFangtuVO = null;
	private Timestamp inputDate = null; //�������

	/**
	 * ͳһ�ӿ�
	 */
	public abstract Object process(VOPackage vo) throws BaseException;

	/**
	 * ��ѯ������Ϣ
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
	 * ��ѯ�û��ı����Ϣ�Լ��Ǽ���Ϣ
	 * 
	 */
	private List queryAlterInfo(DBAccess dao, Class regClass, Class alterClass,
			String jsjdm, String auditFlag) throws BaseException {

		// ��ѯ�û��ı����Ϣ�Լ��Ǽ���Ϣ

		// ��ѯδ���˵ķ�����Ϣ
		Vector cond = new Vector();
		cond.add("FHBS='" + auditFlag + "'");
		cond.add("JSJDM='" + jsjdm + "'");

		List alterList = dao.query(alterClass, cond);

		// ��ѯ�Ǽ���Ϣ,�������Ѿ���ѯ�������б����Ϣ�ĵǼ�����(�޸�, ɾ��ҵ��)
		cond = new Vector();

		// 20060817 fsha comment, hazz Ҫ�����δ���˵Ǽ����ݣ�������÷��ݣ�����������Ϣ
		if ( ! regClass.getName().equals( ZYFWJBXX.class.getName()) &&
				 ! regClass.getName().equals( ZYTDJBXX.class.getName())  ) {
			 cond.add("FHBS='1'"); // Ҫ����ͨ���ĵǼ�����
		}
		

		cond.add("JSJDM='" + jsjdm + "'");

		List oldRegList = dao.query(regClass, cond);
		Map repeatMap = new HashMap();

		// �����Ѿ���ѯ�������б����Ϣ�ĵǼ�����(�޸�, ɾ��ҵ��)
		List regList = filterRegInfo(alterList, oldRegList, repeatMap);

		// ��װ���µ�BVO LIST
		List list = new ArrayList();

		for (Iterator iter = alterList.iterator(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			FangtuAlterBVO bvo = new FangtuAlterBVO();
			bvo.setAlterData(obj);
			// BGLX �������,0-����,1-ɾ��,2-�޸�
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
				throw new ApplicationException("ȡ�ñ����¼�ı������ʱ����!");
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
				throw new ApplicationException("�����µĵǼ�����ʵ��ʱ����!");
			}
			list.add(bvo);

		}
		for (Iterator iter = regList.iterator(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			FangtuAlterBVO bvo = new FangtuAlterBVO();
			bvo.setRegData(obj);

			try {
				// ���ݵǼ������Ƿ񸴺����ò�ͬ��opFlag
				// ���˱�ʶ,0-δ����,1-�Ѹ���
				String fhbs = (String) PropertyUtils.getProperty(obj, "fhbs");
				if ("0".equals(fhbs)) {
					bvo.setOpFlag(FangtuAlterBVO.OP_OLD_UNCHECK);
				} else { // "1".equals( fhbs )
					bvo.setOpFlag(FangtuAlterBVO.OP_OLD);
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new ApplicationException("ȡ�Ǽ����ݵĸ��˱�ʶʱ����!");
			}

			try {
				bvo.setAlterData(alterClass.newInstance());
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new ApplicationException("�����µĵǼ�����ʵ��ʱ����!");
			}
			list.add(bvo);
			
		}
		
		return PageableHelper.pageIt(list, alterFangtuVO);
		//return list;
	}

	/**
	 * �����Ѿ���ѯ�������б����Ϣ�ĵǼ�����(�޸�, ɾ��ҵ��)
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
			throw new ApplicationException("���ݱ����¼���˵Ǽ�����ʱ����!");
		}
		return regList;
	}

	/**
	 * ����ID����ɾ���������
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
			throw new ApplicationException("�������÷������ݴ���");
		}
	}

	/**
	 * ������Ҫʵ�ֵ�ǰprocessor����ı������
	 * 
	 * @return
	 */
	public abstract String getAlterTable();

	/**
	 * ��ʼ��VO
	 * 
	 * @param vo
	 *            ��ʼ����Ӧ��VO
	 * @param auditFlag
	 *            ���˱�ʶ
	 * @param yhid
	 *            ��ǰ�û�����
	 * @param jgdm
	 *            ˰�������֯��������
	 * @param isUpdate
	 *            �Ƿ��Ǹ���
	 * @throws ApplicationException
	 */
	protected void createBaseVO(Object vo, String auditFlag, String yhid,
			String jgdm, boolean isUpdate) throws ApplicationException {

		Timestamp now = new Timestamp(System.currentTimeMillis());

		try {

			PropertyUtils.setProperty(vo, "fhbs", auditFlag); // ���˱�ʶ

			if (ConstantFangtu.AUDIT_FLAG_YES.equals(auditFlag)) {
				PropertyUtils.setProperty(vo, "fhr", yhid); // ������
				PropertyUtils.setProperty(vo, "fhrq", now); // ����ʱ��
			}
			PropertyUtils.setProperty(vo, "tbrq", getInputDate()); // �������

			PropertyUtils.setProperty(vo, "swjgzzjgdm", jgdm); // ˰�������֯��������
			PropertyUtils.setProperty(vo, "qxdm", jgdm.substring(0, 2));

			PropertyUtils.setProperty(vo, "lrr", yhid); // ¼����
			PropertyUtils.setProperty(vo, "lrrq", now); // ¼������

			if (!isUpdate) {
                PropertyUtils.setProperty(vo, "id", KeyUtil.getKey()); // ID
                
                PropertyUtils.setProperty(vo, "cjr", yhid); // ������
				PropertyUtils.setProperty(vo, "cjrq", now); // ��������
			}
		} catch (Exception e) {
			logger.error("��ʼ��VO����", e);
			e.printStackTrace();
			throw new ApplicationException("��ʼ��VO����");
		}
	}

	/**
	 * ȡ���������
	 * @return
	 */
	protected Timestamp getInputDate() {
		if ( inputDate != null ) return inputDate;
		try {
			inputDate = DateTimeUtil.stringToTimestamp(this.alterFangtuVO.getInputDate(),
					ConstantFangtu.DATE_FORMAT);
		} catch (Exception e) {
			logger.warn("������ڸ�ʽ����ʹ��ϵͳ��ǰʱ��.", e);
			inputDate = new Timestamp(System.currentTimeMillis());
		}
		return inputDate;
	}

}