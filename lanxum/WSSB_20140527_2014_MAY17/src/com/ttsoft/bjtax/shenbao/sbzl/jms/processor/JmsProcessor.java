package com.ttsoft.bjtax.shenbao.sbzl.jms.processor;

import java.util.*;
import java.sql.Connection;
import java.sql.Timestamp;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.common.model.UserData;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.JmsVO;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Zjqyjmslx;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;

import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;

/**
 * 
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: ����˰�걨Processor
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 * @version 1.0
 */
public class JmsProcessor implements Processor {

	/**
	 * �ܿ�����
	 */
	private UserData userData;

	/**
	 * orManage�ĳ���
	 */
	private static final long SESSIONID = 0;

	/**
	 * �������ݹ�����VOPackage������action�ж��Ǻ��ֲ���
	 * 
	 * @param voPackage
	 *            ҵ�����
	 * @return vopackage
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage voPackage) throws BaseException {
		this.userData = voPackage.getUserData();
		// ����ҵ���������ֵ����ҵ�����
		try {
			switch (voPackage.getAction()) {
			// ��ѯ
			case JmsActionConstant.INT_ACTION_QUERY:
				voPackage.setData(doQuery((Map) voPackage.getData()));
				return voPackage;

			// ����
			case JmsActionConstant.INT_ACTION_SAVE:
				return doSave(voPackage);

			// ɾ��
			case JmsActionConstant.INT_ACTION_DELETE:
				return doDelete(voPackage);

			// û�пɵ��õķ���
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 1.ͨ��key = JmsMapConstant.LIST_JMSBȡ���걨���ݣ��ж����ݵĺϷ��� 2.ͨ��key =
	 * JmsMapConstant.LIST_JMYSBȡ�ñ������걨���� 3.ɾ�����걨���ݣ������걨���� 4.����ɹ�������key =
	 * JmsMapConstant.RESULT���뱣�����������׳��쳣
	 * 
	 * @param data
	 *            ҵ�����
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	private Map doSave(VOPackage voPackage) throws BaseException {
		Map data = (Map) voPackage.getData();
		DzyjsjVO dzyj = saveSignData(voPackage);
		data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		Connection connection = null;
		try {
			// �걨����
			List jmsb = (List) data.get(JmsMapConstant.LIST_JMSB);

			// �������걨����
			List bqysb = (List) data.get(JmsMapConstant.LIST_JMYSB);

			String[] zqlxdm = (String[]) data.get(JmsMapConstant.ZQLXDM);

			ORManager orManager = DBResource.getORManager();
			connection = DBResource.getConnection();

			// ɾ�����걨����
			for (int i = 0; i < bqysb.size(); i++) {
				orManager.deleteObject(SESSIONID, connection, bqysb.get(i));
			}

			// ���浱ǰ�걨����
			for (int i = 0; i < jmsb.size(); i++) {
				Jm jmTmp = (Jm) jmsb.get(i);
				// Ԥ�㼶�δ���
				jmTmp.setYsjcdm(FriendHelper.getYsjc(jmTmp.getJsjdm(),
						jmTmp.getSzsmdm(), jmTmp.getSkssjsrq()).getYsjcdm());

				// Ԥ���Ŀ����
				jmTmp.setYskmdm(JKAdapter.getInstance().getYskm(
						jmTmp.getSzsmdm(), jmTmp.getDjzclxdm(),
						jmTmp.getGjbzhydm(), jmTmp.getYsjcdm()).getYskmdm());
				jmTmp.setSjly(CodeConstant.WSSB_JMSB_SJLY);
				orManager.makePersistent(SESSIONID, connection, jmTmp);
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "�������ʧ��!");
		} finally {
			DBResource.destroyConnection(connection);
		}
		return data;
	}

	/**
	 * ʹ��keyΪJmsMapConstant.SBRQȡ�걨���� ͨ�����������͵�ǰ����ȡ�ñ������걨���ݣ����£�
	 * ��keyΪJmsMapConstant.LIST_JMYSB�ű������걨����
	 * 
	 * @param data
	 *            ҵ�����
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	private Map doQuery(Map data) throws BaseException {
		// ���������ݽṹ
		Map map = new HashMap();

		// �������ݿ�����
		Connection connection = null;

		// �������걨����
		List bqysbList = null;
		// ˰��˰Ŀ����
		List szsmList = null;
		
		//������Ŀ������
		List jmxmList = new ArrayList();

		try {
			// ȡ��ҵ���������ʹ��keyΪCzzsjdMapConstant.JSJDM
			String jsjdm = this.userData.yhid;
			// �Ǽ�ע�����ʹ���
			String djzclxdm = (String) data.get(JmsMapConstant.DJZCLXDM);

			// �걨����
			Timestamp sbrq = (Timestamp) data.get(JmsMapConstant.SBRQ);

			// ��� ORManagerʵ��
			ORManager orManager = DBResource.getORManager();
			// ������ݿ�����
			connection = DBResource.getConnection();
			// ORManager���������
			Vector criteria = new Vector();
			// ORManager������������
			ORContext orContext = null;

			// ȡ�����걨����
			// ʹ��ORManager�Ա������걨���ݱ�ȡ�������걨����
			// ��������������롢��ǰ����
			criteria.add("jsjdm = '" + jsjdm + "'");

			// �����걨����ȡ�õ�ǰ�µ�һ������µ�һ��
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(sbrq);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			Timestamp ksrq = new Timestamp(
					new GregorianCalendar(year, month, 1).getTime().getTime());
			Timestamp jsrq = new Timestamp(new GregorianCalendar(year,
					month + 1, 1).getTime().getTime());

			criteria.add("sbrq >= to_date('" + ksrq.toString().substring(0, 10)
					+ "','yyyy-MM-dd')");
			criteria.add("sbrq < to_date('" + jsrq.toString().substring(0, 10)
					+ "','yyyy-MM-dd')");

			// ���˱�ǵĵ�һλΪ��
			criteria.add("substr(jzbz, 1, 1) = '0'");
			// ȡ�����걨����
			criteria.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
			// ȡ����˰������Դ
			criteria.add("sjly = '" + CodeConstant.JMSSB_SJLY + "'");
			// ���ش���
			String qxdm = (String) data.get(JmsMapConstant.QXDM);
			criteria.add("qxdm = '" + qxdm + "'");
			
			orContext = new ORContext(Jm.class.getName(), criteria);
			bqysbList = orManager.query(this.SESSIONID, connection, orContext);
			map.put(JmsMapConstant.LIST_JMYSB, bqysbList);

			// ȡ��˰��˰Ŀ����List
			criteria.removeAllElements();

			String sql = "zxbs = '0' "
					+ " and (sffjs is null or sffjs <> '3')"
					+ " and substr(szsmdm,0,2) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = '"
					+ djzclxdm
					+ "')"
					+ " and substr(szsmdm,0,4) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = '"
					+ djzclxdm
					+ "')"
					+ " and szsmdm not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = '"
					+ djzclxdm + "') order by szsmdm";

			criteria.add(sql);
			orContext = new ORContext(Szsm.class.getName(), criteria);

			szsmList = orManager.query(this.SESSIONID, connection, orContext);
			map.put(JmsMapConstant.LIST_SZSM, szsmList);
			
			//��ѯ������Ŀ������ ����˰��Ŀ  tujb 200404
            Vector criJmxm = new Vector();
            ORContext jmxmContext = null;
			criJmxm.add("zxbz = '" + CodeConstant.JMXM_ZXBS_0 + "' and yxbs = '" + CodeConstant.JMXM_YXBS_0 + "' order by jmslxdm");
			jmxmContext = new ORContext(Zjqyjmslx.class.getName(), criJmxm);
			List tmpList = orManager.query(this.SESSIONID, connection, jmxmContext);
			if(tmpList != null && tmpList.size() > 0 )
			{
				for(int i =0 ;i < tmpList.size(); i++)
				{
					Zjqyjmslx jmxm = new  Zjqyjmslx();
					Zjqyjmslx zjqyjmList = (Zjqyjmslx) tmpList.get(i);

					jmxm.setJmslxdm(zjqyjmList.getJmslxdm());
					jmxm.setJmslxmc(TinyTools.replaceBlank(zjqyjmList.getJmslxmc()));
					jmxm.setJmszcqsnd(TinyTools.replaceBlank(zjqyjmList.getJmszcqsnd()));
					jmxm.setSz(TinyTools.replaceBlank(zjqyjmList.getSz()));
					jmxm.setSzdm(TinyTools.replaceBlank(zjqyjmList.getSzdm()));
					jmxm.setWh(TinyTools.replaceBlank(zjqyjmList.getWh()));

					jmxmList.add(jmxm);
				}
				
			}
            map.put(JmsMapConstant.LIST_JMXM, jmxmList);

			return map;
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
		} finally {
			DBResource.destroyConnection(connection);
		}
	}

	/**
	 * 1.ͨ��key = JmsMapConstant.LIST_JMYSBȡ�ñ������걨���� 2.ɾ�����걨���� 3.����ɹ�������key =
	 * JmsMapConstant.RESULT���뱣�����������׳��쳣
	 * 
	 * @param data
	 *            ҵ�����
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	private Map doDelete(VOPackage voPackage) throws BaseException {
		Map data = (Map) voPackage.getData();
		DzyjsjVO dzyj = saveSignData(voPackage);
		data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		// ���������ݽṹ
		Map map = new HashMap(1);
		// �������ݿ�����
		Connection connection = null;
		try {
			// �������걨����
			List bqysb = (List) data.get(JmsMapConstant.LIST_JMYSB);

			ORManager orManager = DBResource.getORManager();
			connection = DBResource.getConnection();

			// ɾ�����걨����
			for (int i = 0; i < bqysb.size(); i++) {
				orManager.deleteObject(SESSIONID, connection, bqysb.get(i));
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "ɾ������ʧ��!");
		} finally {
			DBResource.destroyConnection(connection);
		}
		return data;
	}

    private DzyjsjVO saveSignData(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        JmsVO jms = (JmsVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        UserData ud = vo.getUserData();
        DzyjsjVO dzyj = null;
        dzyj = (DzyjsjVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

        try
        {
            dzyj = CAUtils.saveDzyjsj(ud, dzyj,jms.getSbxx().getNd(), "", jms.getSbxx().getSkssksrq(), "");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return dzyj;
    }
}