package com.lscdz.qysds.common.service;

import com.lscdz.qysds.common.service.djjbsj.IDjjbsjService;
import com.lscdz.qysds.common.service.djjbsj.processor.DjjbsjServer;
import com.lscdz.qysds.common.service.qysdsCheck.IQysdsCheckServer;
import com.lscdz.qysds.common.service.qysdsCheck.processor.QysdsCheckServer;
import com.lscdz.qysds.common.service.QysdsInfo.IQysdsInfoServer;
import com.lscdz.qysds.common.service.QysdsInfo.processor.QysdsInfoServer;
import com.lscdz.qysds.common.service.sfgl.processor.SfglService;

public class ServiceManager {
	//单例实例
	private static ServiceManager serviceManager;
	private ServiceManager(){
		
	}
	
	private SfglService sfglService=new SfglService();
	private DjjbsjServer djjbsjServic=new DjjbsjServer();
	private QysdsCheckServer qysdsCheckServer=new QysdsCheckServer();
	private QysdsInfoServer qysdsInfoServer=new QysdsInfoServer();
	/**
	 * 获取单例
	 * @return
	 */
	public synchronized static ServiceManager getInstance(){
		if(serviceManager == null){
			serviceManager = new ServiceManager();
		}
		return serviceManager;
	}
	/**
	 * 获取税费管理接口
	 * @return
	 */
	public SfglService getSfglService(){
		return sfglService;
	}
	/**
	 * 获取登记基本数据接口
	 * @return
	 */
	public IDjjbsjService getDjjbsjService(){
		return djjbsjServic;
	}
	/**
	 * 获取企业所得税鉴定接口（包含征管范围鉴定）
	 * @return
	 */
	public IQysdsCheckServer getQysdsCheckServer(){
		return qysdsCheckServer;
	}
	
	public IQysdsInfoServer getQysdsInfoServer(){
		return qysdsInfoServer;
	}
}
