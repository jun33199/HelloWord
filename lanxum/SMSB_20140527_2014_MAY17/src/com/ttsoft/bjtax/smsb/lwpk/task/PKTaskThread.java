package com.ttsoft.bjtax.smsb.lwpk.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.PKLogCommon;
import com.ttsoft.bjtax.smsb.lwpk.model.PKLogModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税核心征管系统-税库行</p>
 * <p>Description: 轮询时间表，发起业务操作</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 康洪涛
 * @version 1.0
 */
public class PKTaskThread extends Thread{

	//任务类型为生成代扣
    public final static String SMSB_SCDKRWLX = "01";
   //任务类型为发送代扣信息
    public final static String SMSB_FSDKRWLX = "02";
    //执行成功
    public final static int SMSB_ZXJG_SUCCESS = 0;
    
    
	//线程构造方法
	public PKTaskThread() {
		
	}
	
	//线程停止标记
	boolean stopFlag = false;
	//线程停止方法
	public void stopThread(){
		stopFlag = true;
	}
	
	public void run(){
		System.out.println(new Date()+"【PKTaskThread】启动线程");
		while(!stopFlag){
		    try {
		    	try{
		    		//执行结果
		    		int zxjg=3;
		    		//任务对象列表
		    		List list = new ArrayList();
		    		//获取未执行任务列表
		    		list = getwzxrwList();
			    	//批扣任务接口
			    	PKTaskInterface pkTaskInterface;
			    	System.out.println(new Date()+"#############未执行任务条数："+list.size());
			    	if(list.size()>0){
			    		//遍历任务列表
						for (int i = 0; i < list.size(); i++) {
							//执行结果
							//获取时间计划表对象
							PKTaskModel pkTaskModel = (PKTaskModel)list.get(i);
							//设置执行状态为执行中
							pkTaskModel.setZxzt("01");
							//设置执行结果为执行中
							pkTaskModel.setZxjg("执行中");
							//修改时间计划表中的状态为执行中
							String rows = (String) updatestate(pkTaskModel);
							//如果修改成功
							if(rows.trim().equals("1")){
								//获取任务类型
								String rwlx = pkTaskModel.getRwlx().trim();
								//如果任务类型为01生成代扣，调用GenDKXXTask
								if(rwlx.equals(SMSB_SCDKRWLX)){
									System.out.println(new Date()+"######################生成扣款信息######################");
									pkTaskInterface =  new GenDKXXTask();
									zxjg = pkTaskInterface.execute(pkTaskModel);
								//如果任务类型为02发送代扣信息调用PLDKTask
								}else if(rwlx.equals(SMSB_FSDKRWLX)){
									System.out.println(new Date()+"######################发送扣款信息######################");
									//返回任务对象
									pkTaskModel = getfzList(pkTaskModel);
								    //发送扣款信息
									pkTaskInterface =  new PLDKTask();
									zxjg = pkTaskInterface.execute(pkTaskModel);
									
								}else{
									System.out.println(new Date()+"RWLX有错误，找不到相应的任务类型");
								}
								//执行结束后修改执行状态
								pkTaskModel.setZxzt("02");
								if(zxjg==SMSB_ZXJG_SUCCESS){
									//设置执行结果为执行中
									pkTaskModel.setZxjg("执行成功");
								}else{
									//设置执行结果为执行中
									pkTaskModel.setZxjg("执行完成但有异常");
								}
								//修改时间计划表中的状态为执行完成
								updatestate(pkTaskModel);
							}
							
						}
			    	}
		    	} catch (Exception e){
		    		e.printStackTrace();
		    		//记录日志
					PKLogCommon.saveLog("批量扣款线程任务调度","调用PKTaskThread线程在生成代扣或发送扣款信息时出现了异常",e);
		    	}

				//线程睡眠一分钟
				Thread.sleep(1000*60);
				
			} catch (Exception e) {
				e.printStackTrace();
				//记录日志
				PKLogCommon.saveLog("批量扣款线程任务调度","调用PKTaskThread线程在生成代扣或发送扣款信息时出现了异常",e);
			}
			
		}
		
	}
	//获取未执行任务列表
	public List getwzxrwList(){
		List list = new ArrayList();
		VOPackage vo = new VOPackage();
	    vo.setAction(CodeConstant.SMSB_WZXTASKACTION);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
    	//查询数据库中需要执行的任务
    	try {
			list = (List)ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			//记录日志
			PKLogCommon.saveLog("批量扣款线程任务调度","调用PKTaskThread的getwzxrwList方法获取未执行任务时发生了异常",e);
		}
		return list;
	}
	//修改时间计划表执行状态
	public Object updatestate(PKTaskModel pkTaskModel){
		//修改的行数
		String rows = "";
		VOPackage vo = new VOPackage();
		vo.setData(pkTaskModel);
		vo.setAction(CodeConstant.SMSB_UPDATESTATE);
		vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		try {
			rows = (String) ZhsbProxy.getInstance().process(vo);
			//如果修改行数为0时记录日志
//			if(rows.trim().equals("0")){
//				//记录日志
//				PKLogCommon.saveLog("批量扣款线程任务调度","调用PKTaskThread的updatestate方法修改时间计划表的执行状态时，未修改成功","修改了0行时间计划表的执行状态状态");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			//记录异常日志
			PKLogCommon.saveLog("批量扣款线程任务调度","调用PKTaskThread的updatestate方法修改时间计划表的执行状态时，未修改成功",e);
		}
		return rows;
	}
	//获取银行，区县，笔数分组后的列表
	public PKTaskModel getfzList(PKTaskModel pkTaskModel){
		VOPackage vo = new VOPackage();
		vo.setData(pkTaskModel);
	    vo.setAction(CodeConstant.SMSB_PKFSXX);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
	    //返回银行区县笔数结果列表
	    List pkfsList;
		try {
			pkfsList = (List)ZhsbProxy.getInstance().process(vo);
			//信息列表放置到对象中
			pkTaskModel.setPkfsList(pkfsList);
		} catch (Exception e) {
			//记录异常日志
			PKLogCommon.saveLog("批量扣款线程任务调度","调用PKTaskThread的getfzList方法按银行和区县进行分组时发生了异常",e);
		}
		return pkTaskModel;
	}
	
	
	
	
}