package com.ttsoft.bjtax.smsb.lwpk.common;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKLogModel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.util.VOPackage;
/**
 *��¼��־ 
 * kanght
 * 20130924
 */
public class PKLogCommon {

	/**
	 *������־ 
	 * kanght
	 */
	public static void saveLog(String ywlx ,String zwycms,Exception ex){
		VOPackage vo = new VOPackage();
		PKLogModel log = new PKLogModel();
		//����ҵ������
		log.setYwlx(ywlx);
		//���������쳣��Ϣ
		log.setZwycms(zwycms);
		//�����쳣��Ϣ
		log.setYcxx(getExceptionDetail(ex));
		//����processor����
	    vo.setAction(CodeConstant.SMSB_INSERT_LOG);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKLogProcessor");
	    vo.setData(log);
	    	try {
				ZhsbProxy.getInstance().process(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	/*
     *kanght 
     * 20131023
     * ��ȡ�쳣��Ϣ
     */
    public static String getExceptionDetail(Exception e) {  
        StringBuffer msg = new StringBuffer("null");  
        if (e != null) {  
            msg = new StringBuffer("");  
            String message = e.toString();  
            int length = e.getStackTrace().length;  
            if (length > 0) {  
                msg.append(message + " ");  
                for (int i = 0; i < length; i++) {  
                    msg.append("\n\t" + e.getStackTrace()[i] + " ");  
                }  
            } else {  
                msg.append(message);  
            }  
        } 
        return msg.toString();  
    }
	
}
