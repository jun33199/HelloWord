package com.lscdz.qysds.common.service.qysds.persistent.access;

import java.sql.SQLException;

import yangjian.frame.util.FrameException;


/**
 * @author ������ Ӧ�ù������ӿ�
 */
public interface IAppAccess {
	
	/**
	 * ��ȡ��ǰӦ�û�汾��
	 * @param appid String Ӧ��ID
	 * @return String ��ǰӦ�û�汾��
	 */
	public String getCurrentVersion(String appid);
	
	/**
	 * ������˽����������
	 * @param obj ɾ������,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param checkFlag ��־λ,�μ�QysdsReportsConstants.CREPORTS_CHECK_QYSDS_NOPASS��QysdsReportsConstants.CREPORTS_CHECK_QYSDS_PASS
	 * @exception FrameException ҵ���쳣
	 */
	public void setCheckResult(Object obj, String checkFlag) throws FrameException;
	
	
	/**
	 * ���淽��
	 * @param obj �������
	 */
	public void save(Object obj) throws FrameException;
	
	/**
	 * ���淽��,���������
	 * @param obj �������
	 */
	public void saveSingleTable(Object obj) throws FrameException;
	
	
	/**
	 * ɾ������
	 * @param obj ɾ������
	 */
	public void delete(Object obj) throws FrameException;
	
	/**
	 * ɾ������,���������
	 * @param obj ɾ������
	 */
	public void deleteSingleTable(Object obj) throws FrameException;
	
	
	/**
	 * ��ѯ����,���ݿ��е����ݾ����ǵ��ö���
	 * @param obj ��ѯ����,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return ��ѯ���,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 */
	public Object query(Object obj) throws FrameException;
	
	/**
	 * ��ѯ�������ݷ���,ֻ��ѯ�߱����Ա������
	 * @param obj ��ѯ����,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return ��ѯ���,����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 */
	public Object querySingleTable(Object obj) throws FrameException;
	
	/**
	 * �������״̬��Ϣ��
	 * @param obj ����,��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @param shbz ��˱�־
	 * @throws FrameException
	 * @return true�����³ɹ���false������ʧ��
	 * @author wofei
	 */
	public boolean updateCheckStatus(Object obj,String shbz) throws FrameException;
	
	/**
	 * ��ѯ���״̬
	 * @param obj ��ԱΪcom.syax.creports.bo.qysds.QysdsReportsDeclare
	 * @return ���״̬
	 * @throws FrameException
	 * @author wofei
	 */
	public String queryCheckStatus(Object obj) throws FrameException, SQLException;
}
