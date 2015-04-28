package com.lscdz.qysds.application.jmsba2014.base.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.util.QysdsHelperUtil;

public class JmsbaBaseAccessExt {
	/**
	 * �����������ݺ������嵥
	 */
	public boolean doSave(Connection conn, JmsbaBaseVo baseVo, Yh ud)
			throws FrameException {
		boolean jmsjlSaved = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// �������������
		String basqwsh = baseVo.getBasqwsh();
		// ����������
		String basqbh = baseVo.getBasqbh();
		// ���������
		String jsjdm = baseVo.getJsjdm();
		// �������
		String band = baseVo.getBand();
		// ����˰������
		String jmbasxdm = baseVo.getJmbasxdm();
		// ����˰����ִ�����
		// String
		// jmszczxqk=QysdsUtil.strNotNull(reqVo.getJmszczxqk())?reqVo.getJmszczxqk():"";
		String jmszczxqk = "";
		// String sftjsm=reqVo.getJmsbaBaseData().getSftjsm();
		String sftjsm = "";
		// ��ʼʱ��
		// String
		// qsrq=QysdsUtil.strNotNull(reqVo.getQsrq())?"to_date('"+reqVo.getQsrq()+"','yyyy-mm-dd')":"null";
		String qsrq = "null";
		// ��ֹʱ��
		// String
		// jzrq=QysdsUtil.strNotNull(reqVo.getJzrq())?"to_date('"+reqVo.getJzrq()+"','yyyy-mm-dd')":"null";
		String jzrq = "null";
		// ���ü���˰��
		// String bajmse =
		// QysdsUtil.strNotNull(reqVo.getBajmse())?reqVo.getBajmse():"";
		// String bajmbl =
		// QysdsUtil.strNotNull(reqVo.getBajmbl())?reqVo.getBajmbl():"";
		String bajmse = "";
		String bajmbl = "";

		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			String zbsql = "";

			// ��������
			sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ,"
					+ "SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"
					+ basqwsh
					+ "','"
					+ basqbh
					+ "','"
					+ jsjdm
					+ "','"
					+ band
					+ "','"
					+ jmbasxdm
					+ "','30','"
					+ ud.getSsdwdm()
					+ "','4','"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,'1','"
					+ bajmse
					+ "','"
					+ bajmbl
					+ "',?,"
					+ qsrq
					+ ","
					+ jzrq
					+ ",'"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',to_timestamp('"
					+ time
					+ "','yyyy-mm-dd hh24:mi:ss.ff'))";

			// �������

			ps = conn.prepareStatement(sql);
			ps.setString(1, jmszczxqk);
			ps.executeQuery();

			// String zl=reqVo.getZl();
			String zl = "�嵥1;�嵥2";
			// ���������嵥��
			if (!"".equals(zl)) {
				// ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// ����ñ�����������ŵ������嵥
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zlqd = zl.split(";");
				ps2 = conn.prepareStatement(zlqdsql);
				for (int i = 0; i < zlqd.length; i++) {
					String zlnr = zlqd[i].substring(0, zlqd[i].indexOf("|"));
					String zlsfkysc = zlqd[i].substring(zlqd[i].length() - 1,
							zlqd[i].length());

					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, "1");
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlSaved = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FrameException("���ݿⱣ���¼ʧ�ܣ�" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlSaved;
	}
	/**
	 * �����������״̬
	 */
	public boolean doChangeSqzt(Connection conn, JmsbaBaseVo baseVo)throws FrameException {	
		Statement st=null;
		ResultSet rs=null;
		String sql="select BASQWSH from SFDB.SF_JL_QYSDSJMSBAJL where BASQWSH='"+baseVo.getBasqwsh()+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()){
				sql="update SFDB.SF_JL_QYSDSJMSBAJL   set SQZT='"+baseVo.getSqzt()+"' where BASQWSH='"+baseVo.getBasqwsh()+"'";
				return st.execute(sql);
			}else{
				throw new FrameException("�ü�¼�����ڣ��޷���������״̬");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("��������״̬ʧ�ܣ�"+e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, null);
		}	
	}
	
	/**
	 * ��������嵥����Ƿ�ͨ��״̬������Ƿ�ͨ�������ʱ���޴����ϣ�0δ��ˣ�1���ʱ�У�2���ʱ��
	 */
	public boolean doChangeSfshtg(Connection conn, JmsbaBaseVo baseVo)throws FrameException {	
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="select Xh,BASQWSH from SFDB.SF_JL_QYSDSJMSBAJLZLQD where BASQWSH='"+baseVo.getBasqwsh()+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				ps=conn.prepareStatement("update SFDB.SF_JL_QYSDSJMSBAJL   set SFSHTG=? where BASQWSH=? and XH=?");
			}else{
				throw new FrameException("�ü�¼�����ڣ��޷����������嵥����Ƿ�ͨ��״̬");
			}
			while(rs.next()){
				ps.setString(1,"0");//ͨ��
				ps.setString(2, rs.getString("BASQWSH"));
				ps.setString(3, rs.getString("XH"));
				ps.addBatch(sql);
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("���������嵥����Ƿ�ͨ��״̬ʧ�ܣ�"+e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(st, rs, null);
			QysdsHelperUtil.dbResClose(ps, null, null);
		}
		return true;	
	}
}
