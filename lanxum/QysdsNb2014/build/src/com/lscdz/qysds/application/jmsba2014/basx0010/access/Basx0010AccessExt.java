package com.lscdz.qysds.application.jmsba2014.basx0010.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.inner.Basx0010Vo;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010SaveReq;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.util.QysdsHelperUtil;

public class Basx0010AccessExt {
	/**
	 * doSave保存对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	public boolean doSave(Connection conn,Basx0010SaveReq reqVo,Yh ud) throws FrameException {
		boolean saveSuccess=false;
//		Connection conn = null;
		PreparedStatement ps = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		//备案申请文书号
		String basqwsh = reqVo.getBasx0010Data().getBasqwsh();
		//备案申请编号
		String basqbh = reqVo.getJmsbaBaseData().getBasqbh();
		//计算机代码
		String jsjdm = reqVo.getJmsbaBaseData().getJsjdm();
		//备案年度
		String band = reqVo.getJmsbaBaseData().getBand();
		//减免税类别代码
		String jmbasxdm = reqVo.getJmsbaBaseData().getJmbasxdm();
		//减免税政策执行情况
//		String jmszczxqk=QysdsUtil.strNotNull(reqVo.getJmszczxqk())?reqVo.getJmszczxqk():"";
		String jmszczxqk="";
//		String sftjsm=reqVo.getJmsbaBaseData().getSftjsm();
		String sftjsm="";
		//设置减免税额
		Timestamp time = new Timestamp(new Date().getTime());
		try {
//			conn = ResourceManager.getConnection();	
			StringBuffer zbsql = new StringBuffer();
				
			//获取数据库表序号
			reqVo.getBasx0010Data().setXh(qysdsJmsbaUtil.getSequence(conn));
			//插入子表			
			zbsql.append("INSERT INTO sfdb.sf_jl_qysdsjmsba_01 (XH,BASQWSH,ZYZHLYZLDM,WJMC,JSJDM,BAND,SWJGZZJGDM,WH,ZSBH,ZSYXKSRQ,ZSYXJZRQ,SFTJSM,QDSR,JJSR,SHBJ,CJR,CJRQ,LRR,LRRQ) ")
			.append("VALUES(?,?,?,?,?,?,")
			.append("(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),?,?,?,?,?,?,?,?,?,sysdate,?,?");
			//子表操作

			ps = conn.prepareStatement(zbsql.toString());
			ps.setString(1,reqVo.getBasx0010Data().getXh());
			ps.setString(2,basqwsh);
			ps.setString(3,reqVo.getBasx0010Data().getZyzhlyzldm());
			ps.setString(4,reqVo.getBasx0010Data().getWjmc());
			ps.setString(5,jsjdm);
			ps.setString(6,band);
			ps.setString(7,reqVo.getBasx0010Data().getWh());
			ps.setString(8,reqVo.getBasx0010Data().getZsbh());
			ps.setTimestamp(9, DateUtils.getDateTime(reqVo.getBasx0010Data().getZsyxksrq()));
			ps.setTimestamp(10, DateUtils.getDateTime(reqVo.getBasx0010Data().getZsyxjzrq()));
			ps.setString(11,sftjsm);
			ps.setString(12,reqVo.getBasx0010Data().getQdsr());
			ps.setString(13,reqVo.getBasx0010Data().getJjsr());
			ps.setString(14,"0");			
			ps.setString(15,ud.getYhid());
			ps.setString(16,ud.getYhid());
			ps.setTimestamp(17,time);
			ps.executeQuery();
			
			saveSuccess=true;
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new FrameException("数据库保存记录失败！" + reqVo.getJmsbaBaseData().getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			QysdsHelperUtil.dbResClose(ps,null,null);
		}
		return saveSuccess;
	}
	/**
	 * 更改审核标记，审核标记,0:通过,1:不通过
	 */
	public boolean doChangeShbj(Connection conn,Basx0010SaveReq reqVo) throws FrameException {
		
		
		Statement st=null;
		ResultSet rs=null;
		Basx0010Vo basxVo=reqVo.getBasx0010Data();
		String sql="select XH,BASQWSH from SFDB.SF_JL_QYSDSJMSBAJL where BASQWSH='"+basxVo.getBasqwsh()+"' and Xh='"+basxVo.getXh()+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()){
				sql="update  sfdb.sf_jl_qysdsjmsba_01   set SHBJ='"+basxVo.getShbj()+"' where BASQWSH='"+basxVo.getBasqwsh()+"' and Xh='"+basxVo.getXh()+"'";
				return st.execute(sql);
			}else{
				throw new FrameException("该记录不存在，无法更新审核标记");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("更新审核标记失败！"+e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, null);
		}
		
	}
	
	
}
