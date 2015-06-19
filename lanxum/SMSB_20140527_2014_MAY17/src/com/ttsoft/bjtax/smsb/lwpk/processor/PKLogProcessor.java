package com.ttsoft.bjtax.smsb.lwpk.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKLogModel;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
/**
 *��¼˰����ҵ���쳣��־
 *kanght
 *20130924
 * 
 */
public class PKLogProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
		
		Object result = null;
		if(vo == null){
			throw new NullPointerException();
		}
		
		switch(vo.getAction()){
		case CodeConstant.SMSB_INSERT_LOG:
			result = doInsert(vo);
			break;
		default:
			 throw new UnsupportedOperationException(
	            "Method process() not yet implemented.");
		
		}
		
		
		return result;
	}
	/**
	 *����ҵ���쳣��־ 
	 * 
	 */
	private Object doInsert(VOPackage vo) {
		//���ݿ�����
		Connection conn;
		PreparedStatement ps;
		//��ȡ�쳣����
		PKLogModel pkLogModel = (PKLogModel) vo.getData();
		//�쳣��Ϣ
		String ex = pkLogModel.getYcxx();
		//��ȡ�쳣��Ϣǰ400�ַ�
		String ycxx;
		if(ex.length()>200){
			ycxx =ex.substring(0, 200);
		}else{
			ycxx =ex.substring(0, ex.length());
		}
		
		
		//sql���
		String buffer = "insert into SKHDB.SKH_JL_YWYCRZ(xh,ywlx,zwycms,ycxx,cjsj,clbz) values(SKHDB.SEQ_SKH_YWYCRZ.nextval,?,?,?,sysdate,'0')";
		try {
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(buffer);
			//ҵ������
			ps.setString(1, pkLogModel.getYwlx());
			//�����쳣��Ϣ
			ps.setString(2, pkLogModel.getZwycms());
			//�쳣��Ϣ
			ps.setString(3, ycxx);
			
			//ִ�����ݿ����
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//��ȡ�쳣��Ϣ
	public String getException(String ex){
		//��ȡ���ַ���
		String str ;
		if(ex.length()>400){
			str = ex.substring(0, 400);
		}else{
			str = ex;
		}
		 String temp = null; 
		 //ƥ�人��
         Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");  
         Matcher m = p.matcher(str); 
         //��ȡ�ַ����ĳ���
         int len = str.length();
         while (m.find())  
         {  
        	 //��ȡ����
             temp = m.group(0);
             //��ȡ�����±�
             len = len - temp.length();
         }
         //�쳣��Ϣ
         String ycxx = ex.substring(0, len);
         
		return ycxx;
	}
	
}
