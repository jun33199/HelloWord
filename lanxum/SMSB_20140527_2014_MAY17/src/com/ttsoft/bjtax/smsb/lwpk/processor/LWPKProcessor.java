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
 * <p>Title: �����п�˰������ϵͳ</p>
 * <p>Description: ���ɡ����ʹ�����Ϣ</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author ������
 * @version 1.0
 */
public class LWPKProcessor implements Processor {

	
	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
		    if (vo == null) {
		      throw new NullPointerException();
		    }

		    switch (vo.getAction()) {
		    	//���ɴ���
		      case CodeConstant.SMSB_SAVEACTION:
		        result = doGenDKXX(vo);
		        break;
		      case CodeConstant.SMSB_QUERYACTION:
		    	  //���Ϳۿ���Ϣ
		        result = doSendPLKK(vo);
		        break;
		        //��ѯ������Ϣ����
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
	   * ���ɴ�����Ϣ
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	  private Object doGenDKXX(VOPackage vo) throws BaseException {
		  System.out.println(new Date()+"��LWPKProcessor�����ɴ�����Ϣ");
		  //����ʱע��
		  PKTaskModel pkTaskModel = (PKTaskModel) vo.getData();
		  //��ȡִ��ʱ��
		  String zxsj = pkTaskModel.getZxsj();
		  //ִ�н��
		  String zxjg = null;
		  
		  //���ô洢�������ɴ�����Ϣ
		  Connection conn ;
		  conn = SfDBResource.getConnection();
		  try {
			  //��ʼִ�д洢����
			  long time1 = System.currentTimeMillis();
			  //���ô洢����
			  CallableStatement c = conn.prepareCall("{call SBDB.SB_PKG_PLKK.invoke(?,?,?,?,?)}");
			  c.setString(1, "01");
			  c.setString(2, zxsj);
			  c.setString(3, "90");
			  c.setString(4, "system_plkk");
			  c.registerOutParameter(5, java.sql.Types.VARCHAR);
			  c.execute();
			  zxjg = c.getString(5);
			//ִ�н���ʱ��
			long time2 = System.currentTimeMillis();
			
			System.out.println(new Date()+"�����ɴ���ִ��ʱ�䡿"+ (time2-time1)+"����");
			System.out.println(new Date()+"�����ɴ���ִ�н����"+zxjg);
			
		} catch (SQLException e) {
			e.printStackTrace();
			 throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		  return "success";
	  }
	  
	  /**
	   * ���Ϳۿ���Ϣ
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	 * @throws SystemException 
	   */
	  private Object doSendPLKK(VOPackage vo) throws BaseException{
		  System.out.println(new Date()+"��LWPKProcessor�����Ϳۿ���Ϣ");
		 
		  PKFSModel pkfsModel = (PKFSModel) vo.getData();
			  try {
				  //���
					String nd = pkfsModel.getNd();
				  //�¶�
				  String yd = pkfsModel.getYd();
				  //���ش���
				  String qxdm = pkfsModel.getQxdm();
				  //���д���
				  String yhdm = pkfsModel.getYhdm();
				  //�ܱ���
				  int zbs = pkfsModel.getZbs();
				  SKHSjjhProxy.sendBatchKKRequestModel(qxdm,yhdm,nd,yd,zbs);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ApplicationException(e.getMessage());
					
				}
		  return "success";
	  }
	  
	  /**
	   * ��ȡ������Ϣ
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 
	 private List getPKFSList(VOPackage vo) throws BaseException {
		 
		 PKTaskModel pkTaskModel =  (PKTaskModel)vo.getData();
		 
		 List pklist = new ArrayList();
		 //��ȡִ��ʱ��
		  String zxsj = pkTaskModel.getZxsj();
		  System.out.println(zxsj);
		  //���
		  String nd = zxsj.substring(0, 4);
		  //�¶�
		  String yd = zxsj.substring(4, 6);
		  
		  //������ȣ��¶ȣ���ѯ���ش��룬���д��룬�ܱ���
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
					//�����ؽ��в�ѯ���
					int zbs = rs.getInt("zbs");
					String qxdm = rs.getString("qxdm");
					String yhdm = rs.getString("yhdm");
					//�����ܱ���
					pkfsModel.setZbs(zbs);
					//�������ش���
					pkfsModel.setQxdm(qxdm);
					//�������д���
					pkfsModel.setYhdm(yhdm);
					//�������
					pkfsModel.setNd(nd);
					//�����¶�
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




