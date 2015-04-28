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
 *记录税库行业务异常日志
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
	 *插入业务异常日志 
	 * 
	 */
	private Object doInsert(VOPackage vo) {
		//数据库连接
		Connection conn;
		PreparedStatement ps;
		//获取异常对象
		PKLogModel pkLogModel = (PKLogModel) vo.getData();
		//异常信息
		String ex = pkLogModel.getYcxx();
		//截取异常信息前400字符
		String ycxx;
		if(ex.length()>200){
			ycxx =ex.substring(0, 200);
		}else{
			ycxx =ex.substring(0, ex.length());
		}
		
		
		//sql语句
		String buffer = "insert into SKHDB.SKH_JL_YWYCRZ(xh,ywlx,zwycms,ycxx,cjsj,clbz) values(SKHDB.SEQ_SKH_YWYCRZ.nextval,?,?,?,sysdate,'0')";
		try {
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(buffer);
			//业务类型
			ps.setString(1, pkLogModel.getYwlx());
			//中文异常信息
			ps.setString(2, pkLogModel.getZwycms());
			//异常信息
			ps.setString(3, ycxx);
			
			//执行数据库操作
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//截取异常信息
	public String getException(String ex){
		//截取的字符串
		String str ;
		if(ex.length()>400){
			str = ex.substring(0, 400);
		}else{
			str = ex;
		}
		 String temp = null; 
		 //匹配汉字
         Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");  
         Matcher m = p.matcher(str); 
         //截取字符串的长度
         int len = str.length();
         while (m.find())  
         {  
        	 //获取汉字
             temp = m.group(0);
             //获取汉字下标
             len = len - temp.length();
         }
         //异常信息
         String ycxx = ex.substring(0, len);
         
		return ycxx;
	}
	
}
