package com.ttsoft.bjtax.smsb.lwpk.processor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;
import com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class PKTaskProcessor implements Processor{

	
	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
	        if (vo == null)
	        {
	            throw new NullPointerException(" VOpackage is null ");
	        }
	        switch (vo.getAction())
	        {
	        	//��ʼ���ƻ���
	        	case CodeConstant.SMSB_INIT:
                result = doInit(vo);
                break;
	        	//��ѯ�ƻ���
	            case CodeConstant.SMSB_QUERYACTION:
	                result = doQuery(vo);
	                break;
	                //ɾ���ƻ���
	            case CodeConstant.SMSB_DELETEACTION:
	                result = doDelete(vo);
	                break;
	                //�����޸ĺ�ļƻ���
	            case CodeConstant.SMSB_UPDATEACTION:
	                result = doSaveModify(vo);
	                break;
	                //���������ӵļƻ���
	            case CodeConstant.SMSB_SAVEACTION:
	                result = doSaveAdd(vo);
	                break;
	                //��ȡδִ�е�����
	            case CodeConstant.SMSB_WZXTASKACTION:  
	                result = getWzxTask(vo);
	                break;
	            case CodeConstant.SMSB_UPDATESTATE:  
	                result = updateState(vo);
	                break;
	                //��ȡ��ӡ����
	            case CodeConstant.SMSB_PRINTDATA:  
	                result = getPrintData(vo);
	                break;
	            default:
	                throw new ApplicationException(
	                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
	        }
	        return result;

	}
	
	
	 
	  /**
	   * ��ѯҳ��
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object doQuery(VOPackage vo) throws BaseException {
		 System.out.println("��PKTaskProcessor����ѯʱ��ƻ�����");
		 PKTaskForm pktaskform = (PKTaskForm) vo.getData();
		 //�¶�
		 String yd = pktaskform.getYd();
		 //���
		 String nd = pktaskform.getNd();
		 String zxsj2 = nd ;
		 //�ж��¶���ĳһ���·ݻ���ȫ���·�
		 if(yd.equals("00")){
			
		 }else{
			zxsj2 = zxsj2+yd;
		 }
		 
		 //��������
		 String rwlx = pktaskform.getCxrwlx();
		 //��ѯ���
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE zxsj like '"+zxsj2+"%' and ");
		
		 //�ж���������
		 if(rwlx.equals("00")){
			 buffer.append(" 1=1");
		 }else{
			 buffer.append(" rwlx='"+rwlx+"'");
		 }
		 buffer.append(" ORDER BY ZXSJ");
		 
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
			//��Ž���б�
			List pkTaskList = new ArrayList();
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				String zxsj = rs.getString("zxsj");
				//���
				String nd2 = zxsj.substring(0,4);
				//�¶�
				String yd2 = zxsj.substring(4,6);
				//��
				String ri = zxsj.substring(6,8);
				//Сʱ
				String xs2 = zxsj.substring(8,10);
				//����
				String fz2 = zxsj.substring(10);
				//ִ��ʱ��
				model.setZxsj(zxsj);
				//ִ������
				model.setZxrq(nd2+"-"+yd2+"-"+ri);
				//ִ��ʱ��
				model.setSj(xs2+":"+fz2);
				
				//�ж�������������
				if(rs.getString("rwlx").trim().equals("01")){
					model.setRwlxmc("���ɴ�����Ϣʱ��");
				}else if(rs.getString("rwlx").trim().equals("02")){
					model.setRwlxmc("���Ϳۿ���Ϣʱ��");
				}else{
					model.setRwlxmc("ȫ��");
				}
			
				//ִ��״̬
				model.setZxzt(rs.getString("zxzt"));
				//ִ�н��
				model.setZxjg(rs.getString("zxjg"));
				//��������
				model.setRwlx(rs.getString("rwlx"));
				//������
				model.setCjr(rs.getString("cjr"));
				//��������
				model.setCjrq(rs.getDate("cjrq"));
				//¼����
				model.setLrr(rs.getString("lrr"));
				//¼������
				model.setLrrq(rs.getDate("lrrq"));
				//���
				model.setXh(rs.getString("xh"));
				
				pkTaskList.add(model);
				
			}
//			if(pkTaskList.size()<=0){
//				 throw new ApplicationException("δ��ѯ���������������ݣ�");
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		return pkTaskList;
	 }

	 /**
	   * �����޸ĺ������
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object doSaveModify(VOPackage vo) throws BaseException {
		 System.out.println("��PKTaskProcessor�������޸ĺ��ʱ��ƻ�����");
		 //����ֵ
		 String rows="";
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 UserData userData = vo.getUserData();
		//¼����id
		 String lrr = userData.getYhid(); 
		 //¼������
		// String lrrq = SfTimeUtil.getNowTimestamp().toString().substring(0, 10);
		 //ִ������
		 String zxrq = pkTaskForm.getZxrq();
		 
		 if("".equals(zxrq) || zxrq == null){
			 throw new ApplicationException("���ڲ���Ϊ�գ�");
		 }
		 //ִ��ʱ��
		 String sj = pkTaskForm.getZxsj();
		 //���
		 String nd = zxrq.substring(0,4);
		 //�·�
		 String yd = zxrq.substring(5,7);
		 //��
		 String ri = zxrq.substring(8);
		 //Сʱ
		 String xs = sj.substring(0,2);
		 //����
		 String fz = sj.substring(3);
		 //ִ��ʱ��
		 String zxsj = nd+yd+ri+xs+fz;
		 //��������
		 String rwlx = pkTaskForm.getZxrwlx();
		 //���
		 String xh = pkTaskForm.getXh();
		 
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 //��ѯ���
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("UPDATE SBDB.SB_JL_PLKKSJJHB  SET ");
		 buffer.append(" zxsj = '"+zxsj);
		 buffer.append("',rwlx = '"+rwlx);
		 buffer.append("',lrr = '"+lrr);
		 buffer.append("',lrrq = SYSDATE");
		 buffer.append(" WHERE xh = '"+xh+"'");
		 
		 try {
			ps = conn.prepareStatement(buffer.toString());
			//��Ӱ�������
			int row = ps.executeUpdate();
			if(row>0){
				rows="1";
			}else{
				rows="0";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		
		 return rows;
	 }
	 
	 
	 /**
	   * ɾ��ѡ�е�����
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object doDelete(VOPackage vo) throws BaseException {
		 System.out.println("��PKTaskProcessor��ɾ��ʱ��ƻ�����");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 String xh = pkTaskForm.getXh();
		 String zxrq = pkTaskForm.getZxrq();
		 //��Ž�������б�
		 List list = null;
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("DELETE FROM SBDB.SB_JL_PLKKSJJHB WHERE XH = '"+xh+"'");
		 try {
			 
			ps = conn.prepareStatement(buffer.toString());
			ps.executeUpdate();
			//ɾ����ı����
			list = toQuery(conn,zxrq);
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
	
		 return list;
	 }
	 
	 
	 /**
	   * ���������ӵ�����
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object doSaveAdd(VOPackage vo) throws BaseException {
		 System.out.println("��PKTaskProcessor�����������ӵ�ʱ��ƻ�����");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 //ִ������
		 String zxrq = pkTaskForm.getZxrq();
		 if("".equals(zxrq) || zxrq == null){
			 throw new ApplicationException("���ڲ���Ϊ�գ�");
		 }
		 //ִ��ʱ��
		 String sj = pkTaskForm.getZxsj();
		 //���
		 String nd = zxrq.substring(0,4);
		 //�·�
		 String yd = zxrq.substring(5,7);
		 //��
		 String ri = zxrq.substring(8);
		 //Сʱ
		 String xs = sj.substring(0,2);
		 //����
		 String fz = sj.substring(3);
		 //ִ��ʱ��
		 String zxsj = nd+yd+ri+xs+fz;
		 
		 //��������
		 String rwlx = pkTaskForm.getZxrwlx();
		 UserData userData = vo.getUserData();
		 //¼����id
		 String lrr = userData.getYhid();
		 
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("INSERT INTO SBDB.SB_JL_PLKKSJJHB(XH,ZXSJ,ZXZT,ZXJG,RWLX,CJR,CJRQ,LRR,LRRQ) VALUES(sbdb.seq_sb_lwpk.nextval,'");
		 buffer.append(zxsj+"','00','δִ��','"+rwlx+"','"+lrr+"',SYSDATE,'"+lrr+"',SYSDATE)");
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		 return null;
	 }
	 
	 /**
	   * ��ѯ����Ҫִ�е�����
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private List getWzxTask(VOPackage vo) throws BaseException {
		 System.out.println("��ȡδִ������ʼʱ��"+new Date());
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 List list = new ArrayList();
			
		//��ѯ��ʱ����Ƿ���Ҫִ�е�����
		 String sql = "select a.xh, a.rwlx, a.zxsj, a.zxzt from sbdb.sb_jl_plkksjjhb a where zxzt = '00' and sysdate >= to_date(a.zxsj,'yyyymmddHH24mi') and sysdate < (to_date(a.zxsj,'yyyymmddHH24mi')+30/60/24)";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				model.setXh(rs.getString("xh"));
				model.setRwlx(rs.getString("rwlx"));
				model.setZxsj(rs.getString("zxsj"));
				model.setZxzt(rs.getString("zxzt"));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		 System.out.println("��ȡδִ���������ʱ��ʱ��"+new Date());
		 return list;
	 }
	 
	 /**
	   * �޸�ִ��״̬
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object updateState(VOPackage vo) throws BaseException {
		 
		 System.out.println("�޸�ʱ��ƻ���״̬��ʼʱ��"+new Date());
		 //�޸ĵ�����
		 int rows = 0;
		 //��ȡʱ��ƻ������
		 PKTaskModel pkTaskModel = (PKTaskModel) vo.getData();
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 //����sql���
		 StringBuffer buffer = new StringBuffer();
		 //�ж��ǿ�ʼִ�л��ǽ���ִ��
		 if(pkTaskModel.getZxzt().equals("01")){
			 buffer.append("UPDATE SBDB.SB_JL_PLKKSJJHB SET ZXZT=?,ZXJG=?,SJZXKSSJ=SYSDATE WHERE XH = ? AND ZXZT = '00'");
		 }else{
			 buffer.append("UPDATE SBDB.SB_JL_PLKKSJJHB SET ZXZT=?,ZXJG=?,SJZXJSSJ=SYSDATE WHERE XH = ? AND ZXZT = '01'");
		 }
		try {
			ps = conn.prepareStatement(buffer.toString());
			//ִ��״̬
			ps.setString(1, pkTaskModel.getZxzt());
			//ִ�н��
			ps.setString(2, pkTaskModel.getZxjg());
			//���
			ps.setString(3, pkTaskModel.getXh());
			//ִ��
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		 System.out.println(new Date()+"�޸����ݿ������ݵ�������"+rows);
		System.out.println("�޸�ʱ��ƻ���״̬����ʱ��"+new Date());
		 return rows+"";
	 }
	 
	 /**
	   * ��ӡʱ��ƻ���
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object getPrintData(VOPackage vo) throws BaseException {
		 System.out.println("��PKTaskProcessor����ӡʱ��ƻ���");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 //��ȡ���
		 String nd = pkTaskForm.getNd();
		 String yd = pkTaskForm.getYd();
		 //��ѯsql
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE ZXSJ LIKE '");
		 //�ж��¶���ĳһ���·ݻ���ȫ���·�
		 if(yd.equals("00")){
			buffer.append(nd+"%' and ");
		 }else{
			 buffer.append(nd+yd+"%' and ");
		 }
		 
		 //��������
		 String rwlx = pkTaskForm.getCxrwlx();
		 //�ж���������
		 if(rwlx.equals("00")){
			 buffer.append(" 1=1");
		 }else{
			 buffer.append(" rwlx='"+rwlx+"'");
		 }
		 
		 List list = new ArrayList();
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				//��������
				model.setRwlx(rs.getString("rwlx"));
				if(model.getRwlx().trim().equals("01")){
					model.setRwlxmc("���ɴ�����Ϣʱ��");
				}else{
					model.setRwlxmc("���Ϳۿ���Ϣʱ��");
				}

				//ִ��ʱ��
				String zxsj = rs.getString("zxsj");
				String nd2 = zxsj.substring(0,4);
				String yd2 = zxsj.substring(4,6);
				String ri2 = zxsj.substring(6,8);
				//����
				String zxrq = nd2+"-"+yd2+"-"+ri2;
				
				String xs2 = zxsj.substring(8,10);
				String fz2 = zxsj.substring(10,12);
				//ʱ��
				String sj = xs2+":"+fz2;
				//ִ��ʱ��
				model.setZxsj(zxsj);
				//����
				model.setZxrq(zxrq);
				//ʱ��
				model.setSj(sj);
				
				list.add(model);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
           SfDBResource.freeConnection(conn);
       }
	
		 return list;
	 }

	 /**
	   * ��ʼ��ʱ��ƻ���
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	   * @throws BaseException
	   */
	 private Object doInit(VOPackage vo) throws BaseException {
		 System.out.println("��PKTaskProcessor����ʼ��ʱ��ƻ���");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 //��ȡ��ʼ����Ȳ���
		 PKTaskForm yForm = (PKTaskForm) vo.getData();
		 //��ȡ��ʼ�����
		 String nd = yForm.getNd();
		 //�ж��Ƿ��Ѿ���ʼ��
		 boolean bl = isInit(conn,nd);
		 if(!bl){
			 return "fasle";
		 }
		 //��ȡ�û���Ϣ
		 UserData userdata = vo.getUserData();
		 //�û�id
		 String lrr = userdata.getYhid();
		 
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("INSERT INTO SBDB.SB_JL_PLKKSJJHB(XH,ZXSJ,ZXZT,ZXJG,RWLX,CJR,CJRQ,LRR,LRRQ)");
		 buffer.append("VALUES(sbdb.seq_sb_lwpk.nextval,?,?,'δִ��',?,?,SYSDATE,?,SYSDATE)");
		 try {
			 ps = conn.prepareStatement(buffer.toString());
			 int k=1;
			//2013081618
			 if(nd.equals("2013")){
				 k=9;
			 }
			 for(int i=k;i<=12;i++){
				 
				 //���Ϊһ�·�
				 if( i==1){
					 //����һ�·ݵ�ʱ��ƻ�
					 for(int j=0;j<4;j++){
						
							 int day = j*5;
							 //ִ��ʱ��sbdb.seq_sb_lwpk
							 ps.setString(1, j==0?nd+"0"+i+"020100":day<10?nd+"0"+i+"0"+day+"0600":nd+"0"+i+day+"0600");
							 //ִ��״̬
							 ps.setString(2, "00");
							 //��������
							 ps.setString(3, j==0?"01":"02");
							 //������
							 ps.setString(4, lrr);
							 //¼����
							 ps.setString(5, lrr);
							 
							 ps.addBatch();
						 }
					
				 }else{
					 //��һ�µ������·�
					 
					 //�·�С��10
					 if(i<10){
						 for(int j=0;j<4;j++){
								
							 int day = j*5;
							 //ִ��ʱ��
							 ps.setString(1, j==0?nd+"0"+i+"010100":day<10?nd+"0"+i+"0"+day+"0600":nd+"0"+i+day+"0600");
							 ps.setString(2, "00");
							 //ִ��״̬
							 ps.setString(3, j==0?"01":"02");
							 //������
							 ps.setString(4, lrr);
							 //¼����
							 ps.setString(5, lrr);
							 
							 ps.addBatch();
						 }
					 }else{
						 //�·ݴ���10
						 for(int j=0;j<4;j++){
								
							 int day = j*5;
							 //ִ��ʱ��
							 ps.setString(1, j==0?nd+i+"010100":day<10?nd+i+"0"+day+"0600":nd+i+day+"0600");
							 //ִ��״̬
							 ps.setString(2, "00");
							 //��������
							 ps.setString(3, j==0?"01":"02");
							 //������
							 ps.setString(4, lrr);
							 //¼����
							 ps.setString(5, lrr);
								 
							 ps.addBatch();
							 }
						 }
					 }
					 
				 }//forѭ������
			 //����ִ�в������ݿ�
		ps.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
	           SfDBResource.freeConnection(conn);
	       }
		 
		 
		return "true";
	 }
	 
	 /**
	   * ɾ�����ٴβ�ѯ
	   * @return object
	 * @throws Exception 
	   * @throws BaseException
	   */
	 private List toQuery(Connection conn,String zxrq) throws BaseException{
		 System.out.println("��PKTaskProcessor����ѯʱ��ƻ���");
		 PreparedStatement ps;
		 String nd  = zxrq.substring(0,4);
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE ZXSJ LIKE '"+nd+"%'");
		 List list = new ArrayList();
		 
		 System.out.println(buffer.toString()+"==========");
		 
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				String zxsj = rs.getString("zxsj");
				//���
				String nd2 = zxsj.substring(0,4);
				//�¶�
				String yd2 = zxsj.substring(4,6);
				//��
				String ri = zxsj.substring(6,8);
				//Сʱ
				String xs2 = zxsj.substring(8,10);
				//����
				String fz2 = zxsj.substring(10);
				//ִ��ʱ��
				model.setZxsj(rs.getString("zxsj"));
				//ִ������
				model.setZxrq(nd2+"-"+yd2+"-"+ri);
				//ִ��ʱ��
				model.setSj(xs2+":"+fz2);
				//ִ��״̬
				model.setZxzt(rs.getString("zxzt"));
				//ִ�н��
				model.setZxjg(rs.getString("zxjg"));
				//��������
				model.setRwlx(rs.getString("rwlx"));
				//������
				model.setCjr(rs.getString("cjr"));
				//��������
				model.setCjrq(rs.getDate("cjrq"));
				//¼����
				model.setLrr(rs.getString("lrr"));
				//¼������
				model.setLrrq(rs.getDate("lrrq"));
				//���
				model.setXh(rs.getString("xh"));
				
				list.add(model);
			}
			ps.close();
			rs.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		
		return list; 
	 }
	 
	 /**
	   * �жϳ�ʼ���Ƿ��Ѵ���
	   * @param vo ���ݼ����󣨰���Form��UserData����
	   * @return object
	 * @throws Exception 
	   * @throws BaseException
	   */
	 private boolean isInit(Connection conn,String nd) throws BaseException{
		 System.out.println("��PKTaskProcessor���ж��Ƿ��ʼ��");
		 PreparedStatement ps;
		 String sql="SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE ZXSJ LIKE '"+nd+"%'";
		 System.out.println(sql);
		 boolean bl = true;
		 //��ǰʱ��
		 Date datetime = SfTimeUtil.getNowTimestamp();
		 //��ǰ��
		 int year = datetime.getYear();
		 //��Ҫ��ʼ�������
		 int y = Integer.parseInt(nd);
		 //�����Ҫ��ʼ�������С�ڵ�ǰ�꣬���ܳ�ʼ��
		 if(y<year){
			 return false;
		 }
		 try {
			ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 bl = false;
			 }	 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		
		return bl; 
	 }
	
	 
}
