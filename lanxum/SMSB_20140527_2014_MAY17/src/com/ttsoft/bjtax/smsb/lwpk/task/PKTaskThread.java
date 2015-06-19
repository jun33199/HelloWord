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
 * <p>Title: ������˰��������ϵͳ-˰����</p>
 * <p>Description: ��ѯʱ�������ҵ�����</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author ������
 * @version 1.0
 */
public class PKTaskThread extends Thread{

	//��������Ϊ���ɴ���
    public final static String SMSB_SCDKRWLX = "01";
   //��������Ϊ���ʹ�����Ϣ
    public final static String SMSB_FSDKRWLX = "02";
    //ִ�гɹ�
    public final static int SMSB_ZXJG_SUCCESS = 0;
    
    
	//�̹߳��췽��
	public PKTaskThread() {
		
	}
	
	//�߳�ֹͣ���
	boolean stopFlag = false;
	//�߳�ֹͣ����
	public void stopThread(){
		stopFlag = true;
	}
	
	public void run(){
		System.out.println(new Date()+"��PKTaskThread�������߳�");
		while(!stopFlag){
		    try {
		    	try{
		    		//ִ�н��
		    		int zxjg=3;
		    		//��������б�
		    		List list = new ArrayList();
		    		//��ȡδִ�������б�
		    		list = getwzxrwList();
			    	//��������ӿ�
			    	PKTaskInterface pkTaskInterface;
			    	System.out.println(new Date()+"#############δִ������������"+list.size());
			    	if(list.size()>0){
			    		//���������б�
						for (int i = 0; i < list.size(); i++) {
							//ִ�н��
							//��ȡʱ��ƻ������
							PKTaskModel pkTaskModel = (PKTaskModel)list.get(i);
							//����ִ��״̬Ϊִ����
							pkTaskModel.setZxzt("01");
							//����ִ�н��Ϊִ����
							pkTaskModel.setZxjg("ִ����");
							//�޸�ʱ��ƻ����е�״̬Ϊִ����
							String rows = (String) updatestate(pkTaskModel);
							//����޸ĳɹ�
							if(rows.trim().equals("1")){
								//��ȡ��������
								String rwlx = pkTaskModel.getRwlx().trim();
								//�����������Ϊ01���ɴ��ۣ�����GenDKXXTask
								if(rwlx.equals(SMSB_SCDKRWLX)){
									System.out.println(new Date()+"######################���ɿۿ���Ϣ######################");
									pkTaskInterface =  new GenDKXXTask();
									zxjg = pkTaskInterface.execute(pkTaskModel);
								//�����������Ϊ02���ʹ�����Ϣ����PLDKTask
								}else if(rwlx.equals(SMSB_FSDKRWLX)){
									System.out.println(new Date()+"######################���Ϳۿ���Ϣ######################");
									//�����������
									pkTaskModel = getfzList(pkTaskModel);
								    //���Ϳۿ���Ϣ
									pkTaskInterface =  new PLDKTask();
									zxjg = pkTaskInterface.execute(pkTaskModel);
									
								}else{
									System.out.println(new Date()+"RWLX�д����Ҳ�����Ӧ����������");
								}
								//ִ�н������޸�ִ��״̬
								pkTaskModel.setZxzt("02");
								if(zxjg==SMSB_ZXJG_SUCCESS){
									//����ִ�н��Ϊִ����
									pkTaskModel.setZxjg("ִ�гɹ�");
								}else{
									//����ִ�н��Ϊִ����
									pkTaskModel.setZxjg("ִ����ɵ����쳣");
								}
								//�޸�ʱ��ƻ����е�״̬Ϊִ�����
								updatestate(pkTaskModel);
							}
							
						}
			    	}
		    	} catch (Exception e){
		    		e.printStackTrace();
		    		//��¼��־
					PKLogCommon.saveLog("�����ۿ��߳��������","����PKTaskThread�߳������ɴ��ۻ��Ϳۿ���Ϣʱ�������쳣",e);
		    	}

				//�߳�˯��һ����
				Thread.sleep(1000*60);
				
			} catch (Exception e) {
				e.printStackTrace();
				//��¼��־
				PKLogCommon.saveLog("�����ۿ��߳��������","����PKTaskThread�߳������ɴ��ۻ��Ϳۿ���Ϣʱ�������쳣",e);
			}
			
		}
		
	}
	//��ȡδִ�������б�
	public List getwzxrwList(){
		List list = new ArrayList();
		VOPackage vo = new VOPackage();
	    vo.setAction(CodeConstant.SMSB_WZXTASKACTION);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
    	//��ѯ���ݿ�����Ҫִ�е�����
    	try {
			list = (List)ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			//��¼��־
			PKLogCommon.saveLog("�����ۿ��߳��������","����PKTaskThread��getwzxrwList������ȡδִ������ʱ�������쳣",e);
		}
		return list;
	}
	//�޸�ʱ��ƻ���ִ��״̬
	public Object updatestate(PKTaskModel pkTaskModel){
		//�޸ĵ�����
		String rows = "";
		VOPackage vo = new VOPackage();
		vo.setData(pkTaskModel);
		vo.setAction(CodeConstant.SMSB_UPDATESTATE);
		vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		try {
			rows = (String) ZhsbProxy.getInstance().process(vo);
			//����޸�����Ϊ0ʱ��¼��־
//			if(rows.trim().equals("0")){
//				//��¼��־
//				PKLogCommon.saveLog("�����ۿ��߳��������","����PKTaskThread��updatestate�����޸�ʱ��ƻ����ִ��״̬ʱ��δ�޸ĳɹ�","�޸���0��ʱ��ƻ����ִ��״̬״̬");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			//��¼�쳣��־
			PKLogCommon.saveLog("�����ۿ��߳��������","����PKTaskThread��updatestate�����޸�ʱ��ƻ����ִ��״̬ʱ��δ�޸ĳɹ�",e);
		}
		return rows;
	}
	//��ȡ���У����أ������������б�
	public PKTaskModel getfzList(PKTaskModel pkTaskModel){
		VOPackage vo = new VOPackage();
		vo.setData(pkTaskModel);
	    vo.setAction(CodeConstant.SMSB_PKFSXX);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
	    //�����������ر�������б�
	    List pkfsList;
		try {
			pkfsList = (List)ZhsbProxy.getInstance().process(vo);
			//��Ϣ�б���õ�������
			pkTaskModel.setPkfsList(pkfsList);
		} catch (Exception e) {
			//��¼�쳣��־
			PKLogCommon.saveLog("�����ۿ��߳��������","����PKTaskThread��getfzList���������к����ؽ��з���ʱ�������쳣",e);
		}
		return pkTaskModel;
	}
	
	
	
	
}