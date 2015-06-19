package com.lscdz.qysds.common.service.qysds.check;

import java.util.ArrayList;
import java.util.List;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.bo.CheckResult;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;
/**
 * ��������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�Checker   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����1:45:35   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����1:45:35   
 * �޸ı�ע��   
 * @version  1.0
 */
public abstract class Checker {

	public DBAccess idba;

	/**
	 * �����
	 */
	@SuppressWarnings("rawtypes")
	List crs = new ArrayList();

	/**
	 * ��ʱ������
	 */
	CheckResult cr;

	/**
	 * ���캯��
	 * @param idba DBAccess
	 */
	public Checker(DBAccess idba) {
		this.idba = idba;
	}

	/**
	 * ����鷽��
	 * 
	 * @param contents ��������
	 * @param formulas ��ʽ����
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException ����쳣
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkMain(Object contents, List formulas) throws FrameException;

	/**
	 * �����鷽��
	 * 
	 * @param contents  ��������
	 * @param formulas ��ʽ����
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException ����쳣
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkSingeTable(Object contents, List formulas)throws FrameException;

	/**
	 * ����鷽��
	 * 
	 * @param contents ��������
	 * @param fsdm ��ʽ���� �μ�Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants. CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException   ����쳣
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkMain(Object contents, String fsdm)throws FrameException;

	/**
	 * �����鷽��
	 * 
	 * @param contents ��������
	 * @param fsdm ��ʽ���� �μ�Constants.CREPORTS_SYSTEM_FS_WANGSHANG,Constants.CREPORTS_SYSTEM_FS_SHANGMENG
	 * @return ����������Ϊ�ջ�û�г�Ա���ʾ���ͨ��
	 * @exception FrameException ����쳣
	 */
	@SuppressWarnings("rawtypes")
	public abstract List checkSingeTable(Object contents, String fsdm)throws FrameException;

}
