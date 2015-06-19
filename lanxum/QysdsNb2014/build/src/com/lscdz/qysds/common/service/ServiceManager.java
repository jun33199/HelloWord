package com.lscdz.qysds.common.service;

import com.lscdz.qysds.common.service.djjbsj.IDjjbsjService;
import com.lscdz.qysds.common.service.djjbsj.processor.DjjbsjServer;
import com.lscdz.qysds.common.service.qysdsCheck.IQysdsCheckServer;
import com.lscdz.qysds.common.service.qysdsCheck.processor.QysdsCheckServer;
import com.lscdz.qysds.common.service.QysdsInfo.IQysdsInfoServer;
import com.lscdz.qysds.common.service.QysdsInfo.processor.QysdsInfoServer;
import com.lscdz.qysds.common.service.sfgl.processor.SfglService;

public class ServiceManager {
	//����ʵ��
	private static ServiceManager serviceManager;
	private ServiceManager(){
		
	}
	
	private SfglService sfglService=new SfglService();
	private DjjbsjServer djjbsjServic=new DjjbsjServer();
	private QysdsCheckServer qysdsCheckServer=new QysdsCheckServer();
	private QysdsInfoServer qysdsInfoServer=new QysdsInfoServer();
	/**
	 * ��ȡ����
	 * @return
	 */
	public synchronized static ServiceManager getInstance(){
		if(serviceManager == null){
			serviceManager = new ServiceManager();
		}
		return serviceManager;
	}
	/**
	 * ��ȡ˰�ѹ���ӿ�
	 * @return
	 */
	public SfglService getSfglService(){
		return sfglService;
	}
	/**
	 * ��ȡ�Ǽǻ������ݽӿ�
	 * @return
	 */
	public IDjjbsjService getDjjbsjService(){
		return djjbsjServic;
	}
	/**
	 * ��ȡ��ҵ����˰�����ӿڣ��������ܷ�Χ������
	 * @return
	 */
	public IQysdsCheckServer getQysdsCheckServer(){
		return qysdsCheckServer;
	}
	
	public IQysdsInfoServer getQysdsInfoServer(){
		return qysdsInfoServer;
	}
}
