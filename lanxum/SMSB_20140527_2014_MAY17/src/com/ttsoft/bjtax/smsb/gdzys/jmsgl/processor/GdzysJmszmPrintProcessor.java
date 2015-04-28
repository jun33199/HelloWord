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
 * <p>Description: 耕地占用税证明打印Processor</p>
 * @author 开发部-霍洪波
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
				throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
			}
			return result;
	}
	//打印减免税证明，更新减免税证明表和打印日志表
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
			//获得数据库连接
			conn = SfDBResource.getConnection();
			//更新减免税证明表打印标识为（1，已打印），打印日期，打印人员
			String notsql="update SBDB.SB_JL_GDZYS_JMSZM set dybz='1',dyrq=?,dyry=? where sbbxlh=? and jmszmbh=?";
				ps=conn.prepareStatement(notsql);
				//从数据库中取出当前的日期
				Timestamp ts=GdzysDateUtil.getTimestampFromDB();
				ps.setTimestamp(1, ts);
				ps.setString(2, user);
				ps.setString(3, sbbxlh);
				ps.setString(4, jmszmbh);
				
				ps.executeUpdate();
				//往打印日志表中插入一条打印数据
				String rzsql="insert into SBDB.SB_JL_GDZYS_JMSZMDYRZ(sbbxlh,jmszmbh,dyrq,dyry,cjr,cjrq,lrr,lrrq) values(?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(rzsql);
				//申报序列号
				ps.setString(1, sbbxlh);
				//减免税证明号
				ps.setString(2, jmszmbh);
				//打印日期
				ps.setTimestamp(3,  ts);
				//打印人员
				ps.setString(4, user);
				//创建人
				ps.setString(5, user);
				//创建日期
				ps.setTimestamp(6,  ts);
				//录入人
				ps.setString(7, user);
				//录入日期
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
