package com.ttsoft.bjtax.smsb.lwpk.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.syax.frame.exception.SystemException;
import com.syax.skh.sjjh.service.SKHSjjhProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKFSModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;



/**
 * <p>Title: 北京市库税银联网系统</p>
 * <p>Description: 生成、发送代扣信息</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 康洪涛
 * @version 1.0
 */
public class LWPKProcessor implements Processor {

	
	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
		    if (vo == null) {
		      throw new NullPointerException();
		    }

		    switch (vo.getAction()) {
		    	//生成代扣
		      case CodeConstant.SMSB_SAVEACTION:
		        result = doGenDKXX(vo);
		        break;
		      case CodeConstant.SMSB_QUERYACTION:
		    	  //发送扣款信息
		        result = doSendPLKK(vo);
		        break;
		        //查询批扣信息条件
		      case CodeConstant.SMSB_PKFSXX:
		    	  result = getPKFSList(vo);
		    	  break;
		      default:
		        throw new UnsupportedOperationException(
		            "Method process() not yet implemented.");
		    }
		    return result;
	}
	
	
	 /**
	   * 生成代扣信息
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	  private Object doGenDKXX(VOPackage vo) throws BaseException {
		  System.out.println(new Date()+"【LWPKProcessor】生成代扣信息");
		  //测试时注释
		  PKTaskModel pkTaskModel = (PKTaskModel) vo.getData();
		  //获取执行时间
		  String zxsj = pkTaskModel.getZxsj();
		  //执行结果
		  String zxjg = null;
		  
		  //调用存储过程生成代扣信息
		  Connection conn ;
		  conn = SfDBResource.getConnection();
		  try {
			  //开始执行存储过程
			  long time1 = System.currentTimeMillis();
			  //调用存储过程
			  CallableStatement c = conn.prepareCall("{call SBDB.SB_PKG_PLKK.invoke(?,?,?,?,?)}");
			  c.setString(1, "01");
			  c.setString(2, zxsj);
			  c.setString(3, "90");
			  c.setString(4, "system_plkk");
			  c.registerOutParameter(5, java.sql.Types.VARCHAR);
			  c.execute();
			  zxjg = c.getString(5);
			//执行结束时间
			long time2 = System.currentTimeMillis();
			
			System.out.println(new Date()+"【生成代扣执行时间】"+ (time2-time1)+"毫秒");
			System.out.println(new Date()+"【生成代扣执行结果】"+zxjg);
			
		} catch (SQLException e) {
			e.printStackTrace();
			 throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		  return "success";
	  }
	  
	  /**
	   * 发送扣款信息
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	 * @throws SystemException 
	   */
	  private Object doSendPLKK(VOPackage vo) throws BaseException{
		  System.out.println(new Date()+"【LWPKProcessor】发送扣款信息");
		 
		  PKFSModel pkfsModel = (PKFSModel) vo.getData();
			  try {
				  //年度
					String nd = pkfsModel.getNd();
				  //月度
				  String yd = pkfsModel.getYd();
				  //区县代码
				  String qxdm = pkfsModel.getQxdm();
				  //银行代码
				  String yhdm = pkfsModel.getYhdm();
				  //总笔数
				  int zbs = pkfsModel.getZbs();
				  SKHSjjhProxy.sendBatchKKRequestModel(qxdm,yhdm,nd,yd,zbs);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ApplicationException(e.getMessage());
					
				}
		  return "success";
	  }
	  
	  /**
	   * 获取发包信息
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 
	 private List getPKFSList(VOPackage vo) throws BaseException {
		 
		 PKTaskModel pkTaskModel =  (PKTaskModel)vo.getData();
		 
		 List pklist = new ArrayList();
		 //获取执行时间
		  String zxsj = pkTaskModel.getZxsj();
		  System.out.println(zxsj);
		  //年度
		  String nd = zxsj.substring(0, 4);
		  //月度
		  String yd = zxsj.substring(4, 6);
		  
		  //根据年度，月度，查询区县代码，银行代码，总笔数
		  Connection conn ;
		  conn = SfDBResource.getConnection();
		  PreparedStatement ps;
		  
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(" select count(sphm) zbs,qxdm,yhdm from SBDB.SB_JL_PLKKSPXX_LW");
		  buffer.append(" where nd = ? and yd = ? and (KKBZ = '00' OR KKBZ = '21' OR KKBZ = '99')");
		  buffer.append(" group by qxdm,yhdm");
		  
			try {
				ps = conn.prepareStatement(buffer.toString());
				ps.setString(1, nd);
				ps.setString(2, yd);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					PKFSModel pkfsModel = new PKFSModel();
					//按区县进行查询组包
					int zbs = rs.getInt("zbs");
					String qxdm = rs.getString("qxdm");
					String yhdm = rs.getString("yhdm");
					//设置总笔数
					pkfsModel.setZbs(zbs);
					//设置区县代码
					pkfsModel.setQxdm(qxdm);
					//设置银行代码
					pkfsModel.setYhdm(yhdm);
					//设置年度
					pkfsModel.setNd(nd);
					//设置月度
					pkfsModel.setYd(yd);
					pklist.add(pkfsModel);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e.getMessage());
			}finally {
	      		SfDBResource.freeConnection(conn);
	    	}
	
		 return pklist;
	 }
	 
	 
	 
	
	 
}




