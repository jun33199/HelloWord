package com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmscxForm;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysDateUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
/**
 * 
 * <p>Title: </p>
 * <p>Description: ����ռ��˰֤����ӡProcessor</p>
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmszmPrintProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
			Object result = null;

			/** @todo Implement this com.ttsoft.framework.processor.Processor method */
			if (vo == null) {
				throw new NullPointerException();
			}

			switch (vo.getAction()) {
			case GdzysCodeConstant.SMSB_SAVEACTION:
				result = doUpdate(vo);
				break;
			default:
				throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
			}
			return result;
	}
	//��ӡ����˰֤�������¼���˰֤����ʹ�ӡ��־��
	private Object doUpdate(VOPackage vo) throws BaseException{
		Connection conn = null;
		PreparedStatement ps= null;
		PreparedStatement ps1=null;
		GdzysJmscxForm myForm = (GdzysJmscxForm) vo.getData();
		UserData ud=vo.getUserData();
		String sbbxlh=myForm.getSbxlh();
		String jmszmbh=myForm.getJmszmbh();
		//String user=ud.getRoleId();
		String user=ud.getYhid();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//������ݿ�����
			conn = SfDBResource.getConnection();
			//���¼���˰֤�����ӡ��ʶΪ��1���Ѵ�ӡ������ӡ���ڣ���ӡ��Ա
			String notsql="update SBDB.SB_JL_GDZYS_JMSZM set dybz='1',dyrq=?,dyry=? where sbbxlh=? and jmszmbh=?";
				ps=conn.prepareStatement(notsql);
				//�����ݿ���ȡ����ǰ������
				Timestamp ts=GdzysDateUtil.getTimestampFromDB();
				ps.setTimestamp(1, ts);
				ps.setString(2, user);
				ps.setString(3, sbbxlh);
				ps.setString(4, jmszmbh);
				
				ps.executeUpdate();
				//����ӡ��־���в���һ����ӡ����
				String rzsql="insert into SBDB.SB_JL_GDZYS_JMSZMDYRZ(sbbxlh,jmszmbh,dyrq,dyry,cjr,cjrq,lrr,lrrq) values(?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(rzsql);
				//�걨���к�
				ps.setString(1, sbbxlh);
				//����˰֤����
				ps.setString(2, jmszmbh);
				//��ӡ����
				ps.setTimestamp(3,  ts);
				//��ӡ��Ա
				ps.setString(4, user);
				//������
				ps.setString(5, user);
				//��������
				ps.setTimestamp(6,  ts);
				//¼����
				ps.setString(7, user);
				//¼������
				ps.setTimestamp(8,ts);
				
			int a=ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SfDBResource.freeConnection(conn);
		}
		return myForm;
	}

}
