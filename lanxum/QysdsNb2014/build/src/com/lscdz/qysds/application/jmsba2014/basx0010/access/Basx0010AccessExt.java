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
	 * doSave�������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	public boolean doSave(Connection conn,Basx0010SaveReq reqVo,Yh ud) throws FrameException {
		boolean saveSuccess=false;
//		Connection conn = null;
		PreparedStatement ps = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		//�������������
		String basqwsh = reqVo.getBasx0010Data().getBasqwsh();
		//����������
		String basqbh = reqVo.getJmsbaBaseData().getBasqbh();
		//���������
		String jsjdm = reqVo.getJmsbaBaseData().getJsjdm();
		//�������
		String band = reqVo.getJmsbaBaseData().getBand();
		//����˰������
		String jmbasxdm = reqVo.getJmsbaBaseData().getJmbasxdm();
		//����˰����ִ�����
//		String jmszczxqk=QysdsUtil.strNotNull(reqVo.getJmszczxqk())?reqVo.getJmszczxqk():"";
		String jmszczxqk="";
//		String sftjsm=reqVo.getJmsbaBaseData().getSftjsm();
		String sftjsm="";
		//���ü���˰��
		Timestamp time = new Timestamp(new Date().getTime());
		try {
//			conn = ResourceManager.getConnection();	
			StringBuffer zbsql = new StringBuffer();
				
			//��ȡ���ݿ�����
			reqVo.getBasx0010Data().setXh(qysdsJmsbaUtil.getSequence(conn));
			//�����ӱ�			
			zbsql.append("INSERT INTO sfdb.sf_jl_qysdsjmsba_01 (XH,BASQWSH,ZYZHLYZLDM,WJMC,JSJDM,BAND,SWJGZZJGDM,WH,ZSBH,ZSYXKSRQ,ZSYXJZRQ,SFTJSM,QDSR,JJSR,SHBJ,CJR,CJRQ,LRR,LRRQ) ")
			.append("VALUES(?,?,?,?,?,?,")
			.append("(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),?,?,?,?,?,?,?,?,?,sysdate,?,?");
			//�ӱ����

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
			throw new FrameException("���ݿⱣ���¼ʧ�ܣ�" + reqVo.getJmsbaBaseData().getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			QysdsHelperUtil.dbResClose(ps,null,null);
		}
		return saveSuccess;
	}
	/**
	 * ������˱�ǣ���˱��,0:ͨ��,1:��ͨ��
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
				throw new FrameException("�ü�¼�����ڣ��޷�������˱��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("������˱��ʧ�ܣ�"+e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, null);
		}
		
	}
	
	
}
